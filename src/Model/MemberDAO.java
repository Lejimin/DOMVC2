package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			Context envctx = (Context)initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/mysql");
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
			pstmt.setNString(1, bean.getId());
			pstmt.setNString(2, bean.getPassword1());
			pstmt.setNString(3, bean.getPassword2());
			pstmt.setNString(4, bean.getName());
			pstmt.setNString(5, bean.getEmail());
			pstmt.setNString(6, bean.getAddress());
			
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
