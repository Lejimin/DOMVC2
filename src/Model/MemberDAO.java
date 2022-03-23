package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void getCon() {
		try {
			
			Context initctx = new InitialContext();
			DataSource ds = (DataSource)initctx.lookup("java:comp/env/jdbc/mysql");
			con = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//회원 한사람에 대한 저장
	public void insertMember(MemberBean bean) {
		getCon();
		
		try {
			String sql = "insert into jointbl values (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPassword1());
			pstmt.setString(3, bean.getPassword2());
			pstmt.setString(4, bean.getName());
			pstmt.setString(5, bean.getEmail());
			pstmt.setString(6, bean.getAddress());
			
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//모든 회원의 정보 리턴
	public Vector<MemberBean> getAllMember(){
		
		Vector<MemberBean> v = new Vector<>();
		
		getCon();
		
		try {
			String sql = "select * from jointbl";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberBean bean = new MemberBean();
				bean.setId(rs.getString("id"));
				bean.setPassword1(rs.getString("password1"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setAddress(rs.getString("address"));
				v.add(bean);
			}
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
}
