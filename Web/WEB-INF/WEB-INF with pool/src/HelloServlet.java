package jin.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class HelloServlet extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter pw = res.getWriter(); //PrintWriter ��ü��ȯ Ŭ���̾�Ʈ �������� ����ϱ�����
		pw.println("<center>"); // html�ۼ� 
		//pw.println("<script> alert('���������̴�'); </script>");
		pw.println("<h3 style='color:blue'>what's up? �ѱ۵�����</h3>");
		pw.println("<a href='index.html' style='text-decoration:none'>�ε���</a>");
		pw.println("</center>");
		
	}

}
