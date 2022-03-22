package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	
	public void getCon() {
		
		try {
			Context initctx = new InitialContext();
			
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/mysql");
			
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	//전체 게시글의 개수를 리턴하는 메소드
	public int getAllCount() {
		int count=0;
		
		getCon();
		
		try {
			String sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	

	//모든(화면에 보여질 데이처를 10개씩 추출해서 리턴하는 메소드
	public Vector<BoardBean> getAllBoard(int startRow, int endRow){
		Vector<BoardBean> v = new Vector<>();
		
		getCon();
		try {
			String sql = "select * from (select A.* ,Rownum Rnum from (select *from board order by ref desc ,re_stop asc)A)"
					+ "where Rnum >= ? and Rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 BoardBean bean = new BoardBean();
				 bean.setNum(rs.getInt(1));
				 bean.setWrite(rs.getString(2));
				 bean.setEmail(rs.getString(3));
				 bean.setSubject(rs.getString(4));
				 bean.setPassword(rs.getString(5));
				 bean.setReg_date(rs.getDate(6).toString());
				 bean.setRef(rs.getInt(7));
				 bean.setRe_stop(rs.getInt(8));
				 bean.setRe_level(rs.getInt(9));
				 bean.setReadcount(rs.getInt(10));
				 bean.setContent(rs.getString(11));
				 v.add(bean);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	public void insertBoard(BoardBean bean) {
		getCon();
		//�� Ŭ������ �Ѿ���� �ʾҴ� �����͵��� �ʱ�ȭ ���־�� �մϴ�.
		int ref = 0;// �� �׷��� �ǹ� = ������ ������� ����ū ref ���� �������� +1�� �����ָ� �ȴ� 
		int re_stop = 1;//�����̱⿡ = �θ� ���̱⿡
		int re_level = 1;
		try {
			//����ū ref���� �о���� ���� �غ� 
			String refSQL = "select max(ref) from board";
			//�������� ��ü 
			pstmt = con.prepareStatement(refSQL);
			//������ ������ ����� ����
			 rs = pstmt.executeQuery();	
			if(rs.next()){//��� ���� �ִٸ�
				ref = rs.getInt(1)+1;//�ִ� ���� +1�� ���ؼ� �� �׷��� ����	
			}
			//������ �Խñ� ��ü���� ���̺��� ���� 
			String SQL = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate(),?,?,?,0,?)";
			pstmt = con.prepareStatement(SQL);
			//?�� ���� ����
			pstmt.setString(1, bean.getWrite());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_stop);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());		
			//������ �����Ͻÿ�
			pstmt.executeUpdate();	
			//�ڿ� �ݳ�
			con.close();
			}catch(Exception e) {
				e.printStackTrace();	
			}	
	}
	
	
	//BoardInfo �ϳ��� �Խñ� �����ϴ� �޼ҵ�
	public BoardBean getOneBoard(int num){	
		//���� Ÿ�� ����
		BoardBean bean = new BoardBean();
		getCon();
		try {
			//��ȸ�� ���� ���� 
			String readsql = "update board set readcount = readcount+1 where num=?";
			pstmt = con.prepareStatement(readsql);
			pstmt.setInt(1,num);
			pstmt.executeUpdate();	
				
			//�����غ�
			String SQL = "select * from board where num=?";
			//�������ఴü 
			//�������� ��ü 
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1,num);
			//���� ������ ����� ����
			rs= pstmt.executeQuery();
				
			if(rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setWrite(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_stop(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				}
				
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			return bean;
		}
	
	
	//�亯���� ����Ǵ� �޼ҵ� 
	public void reWriteBoard(BoardBean bean){
		//�θ�� �׷�� �۷��� �� ������ �о�帲 
		int ref =bean.getRef();
		int re_stop = bean.getRe_stop();
		int re_level = bean.getRe_level();
		
		getCon();
		
		try {
			/////////////////////�ٽ� �ڵ� //////////////////////// 
				//�θ�� ���� ū re_level�� ���� ���� 1�� ���������� 
				String  levelsql = "update board set re_level=re_level+1 where ref=? and re_level > ?";
				//���� ���� ��ü ���� 
				pstmt = con.prepareStatement(levelsql);
				pstmt.setInt(1 , ref);
				pstmt.setInt(2 , re_level);
				//���� ���� 
				pstmt.executeUpdate();
				//�亯�� �����͸� ����
				String sql ="insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate(),?,?,?,0,?)";
				pstmt = con.prepareStatement(sql);
				//?�� ���� ����
				pstmt.setString(1, bean.getWrite());
				pstmt.setString(2, bean.getEmail());
				pstmt.setString(3, bean.getSubject());
				pstmt.setString(4, bean.getPassword());
				pstmt.setInt(5, ref);//�θ��� ref ���� �־��� 
				pstmt.setInt(6, re_stop+1);//����̱⿡ �θ�� re_stop�� 1�� �־��� 
				pstmt.setInt(7, re_level + 1);
				pstmt.setString(8, bean.getContent());
				
				//������ �����Ͻÿ�
				pstmt.executeUpdate();	
				con.close();
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Boardupdate�� Delete�� �ϳ��� �Խñ��� ����
	public BoardBean getOneUpdateBoard(int num){	
		//���� Ÿ�� ����
		BoardBean bean = new BoardBean();
		getCon();
		try {
			//�����غ�
			String SQL = "select * from board where num=?";
			//�������ఴü 
			//�������� ��ü 
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1,num);
			//���� ������ ����� ����
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				 bean.setNum(rs.getInt(1));
				 bean.setWrite(rs.getString(2));
				 bean.setEmail(rs.getString(3));
				 bean.setSubject(rs.getString(4));
				 bean.setPassword(rs.getString(5));
				 bean.setReg_date(rs.getDate(6).toString());
				 bean.setRef(rs.getInt(7));
				 bean.setRe_stop(rs.getInt(8));
				 bean.setRe_level(rs.getInt(9));
				 bean.setReadcount(rs.getInt(10));
				 bean.setContent(rs.getString(11));
			}
			
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return bean;
	}
	
	
	//�ϳ��� �Խñ��� �����ϴ� �޼ҵ�
	public void updateBoard(int num ,String subject , String content){
		getCon();
			
		try {		
			//���� �غ� 
			String sql = "update board set subject=? , content= ? where num = ?";
				
			//���� ������ ��ü ����
			pstmt = con.prepareStatement(sql);
			
			//?�� ���� ���� �ϱ�
			pstmt.setString(1,subject);
			pstmt.setString(2,content);
			pstmt.setInt(3,num);
			
			//���� ������ ����� ����
			pstmt.executeUpdate();
				
			//�ڿ� �ݳ�
			con.close();
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}  
	
	
	//�ϳ��� �Խñ��� �����ϴ� �żҵ�
		public void deleteBoard(int num){
			getCon();
			
			try {		
				//���� �غ� 
				String sql = "delete from board where num=?";
				
				//���� ������ ��ü ����
				pstmt = con.prepareStatement(sql);
				//?
				pstmt.setInt(1,num);
				
				//���� ����
				pstmt.executeUpdate();
				
				//�ڿ� �ݳ�
				con.close();
					
			}catch(Exception e) {
				e.printStackTrace();
				
			}
		}
	
	
	
	
	
	
	
	
	
	
}
