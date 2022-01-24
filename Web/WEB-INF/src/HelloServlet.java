package jin.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class HelloServlet extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter pw = res.getWriter(); //PrintWriter 객체반환 클라이언트 브라우저에 출력하기위함
		pw.println("<center>"); // html작성 
		//pw.println("<script> alert('난김진운이다'); </script>");
		pw.println("<h3 style='color:blue'>what's up? 한글도지원</h3>");
		pw.println("<a href='index.html' style='text-decoration:none'>인덱스</a>");
		pw.println("</center>");
		
	}

}
