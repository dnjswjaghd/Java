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
		PrintWriter pw = res.getWriter(); //PrintWriter 객체반환 클라이언트 브라우저에 출력하기위함
		pw.println("<center>"); // html작성 
		pw.println("<h3 style='color:blue'>what's up? 한글도지원</h3>");
		pw.println("<a href='index.html' style='text-decoration:none'>인덱스</a>");
		pw.println("</center>");
		
	}
	public void destroy(){
		System.out.println("destroy()");
		PrintWriter pw = null;
		FileWriter fw = null;

		try{
			fw = new FileWriter("log_destroy().txt");
			pw = new PrintWriter (fw, true);
			pw.println("destroy() 수행됨");
			pw.close();
			fw.close();
		}catch(IOException ie){
		}
	}

}
