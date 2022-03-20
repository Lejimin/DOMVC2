package Control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.MemberBean;
import Model.MemberDAO;


@WebServlet("/Mproc.do")
public class MemberJoinProc extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	public void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		MemberBean bean = new MemberBean();
		bean.setId(request.getParameter("id"));
		String pass1 = request.getParameter("password1");
		String pass2 = request.getParameter("password2");
		
		bean.setPassword1(pass1);
		bean.setPassword2(pass2);
		
//		bean.setPassword1(request.getParameter("password1"));
//		bean.setPassword2(request.getParameter("password2"));
		bean.setName(request.getParameter("name"));
		bean.setEmail(request.getParameter("email"));
		bean.setAddress(request.getParameter("address"));
		
		request.setAttribute("bean", bean);
//		
//		RequestDispatcher dis = request.getRequestDispatcher("MemberJoinProc.jsp");
//		dis.forward(request, response);
		
		
		if(pass1.equals(pass2)) {
			MemberDAO mdao = new MemberDAO();
			mdao.insertMember(bean);
			
			RequestDispatcher dis = request.getRequestDispatcher("MemberList.jsp");
			dis.forward(request, response);
		}else {
			request.setAttribute("msg", "패스워드가 일치하지 않습니다.");
			RequestDispatcher dis = request.getRequestDispatcher("LoginError.jsp");
			dis.forward(request, response);
		}
		
		
		
		
		
		
		
	}

}
