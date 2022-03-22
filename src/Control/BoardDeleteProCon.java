package Control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BoardDAO;



@WebServlet("/BoardDeleteProCon.do")
public class BoardDeleteProCon extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request ,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request ,response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		request.setCharacterEncoding("UTF-8");
		
		//����ڷ� ���� �Ѿ�� ������ 3���� �޾��ݴϴ�
		int num = Integer.parseInt(request.getParameter("num"));
		String password =  request.getParameter("password");
		String pass =  request.getParameter("pass");
		
		//���� �н����� ���� delete form���� �ۼ��� �н����带 �� 
		if(pass.equals(password)){
			BoardDAO bdao = new BoardDAO();
			//�н����尡 �Ѵ� ���ٸ� 
			bdao.deleteBoard(num);
			
			//������ �Ϸ� �Ǿ��ٸ� ��ü �Խñ� ����� �̵� 
			request.setAttribute("msg","������ �Ϸ� �Ǿ����ϴ�");
			RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
			dis.forward(request ,response);
		}else{
			//��й�ȣ�� Ʋ�ȴٸ� ���� �������� �̵�
			request.setAttribute("msg","������ ��й�ȣ�� ���� �ʽ��ϴ�");
			RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
			dis.forward(request ,response);
		}	
	}

	
}
