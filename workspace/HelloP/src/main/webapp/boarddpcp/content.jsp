<%@ page contentType="text/html;charset=utf-8" import="java.sql.*, javax.sql.*"%> 
	<jsp:useBean id="dbcp" class="jin.dbcp.DbcpBean" scope="application"/>
<%
			request.setCharacterEncoding("utf-8");
			String seqStr = request.getParameter("seq");
			int seq = -1;
			ResultSet rs = null;
			if(seqStr != null){
				seqStr = seqStr.trim();
				if(seqStr.length()!=0){
					try{
						seq = Integer.parseInt(seqStr);
					}catch(Exception ie){
						response.sendRedirect("content.jsp");
					}
				}else{
					response.sendRedirect("content.jsp");
				}
			} 
			DataSource ds = null;
			Connection con =null;
			Statement stmt = null;
			String name ="";
		    String email="";
		    String subject="";
		    String content ="";
            String sql = "select * from BOARD where SEQ="+seq+" order by seq desc";
            try{
            	ds = dbcp.getDs();
  			   	con = ds.getConnection();
				stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
               while(rs.next()){
                  seq = rs.getInt(1);
                  name = rs.getString(2);
                  email = rs.getString(3);
				  subject = rs.getString(4);
                  content = rs.getString(5);
               }
			
%>
	<meta charset='utf-8'>
	<style>
		table, th, td {
		   border: 1px solid black;
		   border-collapse: collapse;
		}
		th, td {
		   padding: 5px;
		}
		a { text-decoration:none }
	</style>
	<center>
	<hr width='600' size='2' noshade>
	<h2>Simple Board with Servlet with Jinun</h2>
	&nbsp;&nbsp;&nbsp;
	<a href='input.jsp'>글쓰기 </a>
	<hr width='600' size='2' noshade>
	<table border='1' width='600' align='center' cellpadding='3'>
	<tr>
		<td width='100' align='center'>글번호</td>
		<td><%=seq%></td>
		</tr>
	<tr>
		<td align='center'>글쓴이</td>
		<td><%=name%></td>
	</tr>
	<tr>
		<td align='center'>이메일</td>
		<td><%=email%></td>
	</tr>
	<tr>
		<td align='center'>글제목</td>
		<td><%=subject%></td>
	</tr>
	<tr>
		<td align='center'>글내용</td>
		<td><%=content%></td>
	</tr>
	</table>
	<hr width='600' size='2' noshade>
	<hr width='600' size='2' noshade>
	<b>
	<a  href='update.jsp?seq=<%=seq%>'>수정</a>
	 | 
	<a href='del.jsp?seq=<%=seq%>'>삭제</a> 
	 | 
	<a href='list.jsp'>목록!</a>
	</b>
	<hr width='600' size='2' noshade>
	</center>

<%
		}catch(SQLException se){
            }finally{
               try{
                  if(rs != null) rs.close();
               }catch(SQLException se){}
            }
%>