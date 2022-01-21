package jin.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class LifeServlet extends HttpServlet
{
	public void init(){
		System.out.println("init()");
	}
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		System.out.println("service()");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter pw = res.getWriter(); //PrintWriter ��ü��ȯ Ŭ���̾�Ʈ �������� ����ϱ�����
		pw.println("<center>"); // html�ۼ� 
		pw.println("<h3 style='color:blue'>what's up? �ѱ۵�����</h3>");
		pw.println("<a href='index.html' style='text-decoration:none'>�ε���</a>");
		pw.println("</center>");
		
	}
	public void destroy(){
		System.out.println("destroy()");
		PrintWriter pw = null;
		FileWriter fw = null;

		try{
			fw = new FileWriter("log_destroy().txt");
			pw = new PrintWriter (fw, true);
			pw.println("destroy() �����");
			pw.close();
			fw.close();
		}catch(IOException ie){
		}
	}

}
