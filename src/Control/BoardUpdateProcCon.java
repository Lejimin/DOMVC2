package Control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BoardDAO;




@WebServlet("/BoardUpdateProcCon.do")
public class BoardUpdateProcCon extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request ,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request ,response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");

		int num = Integer.parseInt(request.getParameter("num"));
		String password = request.getParameter("password");
		String pass = request.getParameter("pass");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		
		
		//password���� pass���� �� �ؾ��մϴ�
		if(password.equals(pass)) {//�н����� ���ٸ� �����͸� ����
			BoardDAO bdao = new BoardDAO();
			bdao.updateBoard(num,subject,content);
			
			//������ �Ϸ� �Ǿ��ٸ� ��ü �Խñ� ����� �̵� 
			request.setAttribute("msg","������ �Ϸ� �Ǿ����ϴ�");
			
			RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
			dis.forward(request ,response);
		}else {
			//��й�ȣ�� Ʋ�ȴٸ� ���� �������� �̵�
			request.setAttribute("msg","������ ��й�ȣ�� ���� �ʽ��ϴ�");
			RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
			dis.forward(request ,response);
		}
	}
}
