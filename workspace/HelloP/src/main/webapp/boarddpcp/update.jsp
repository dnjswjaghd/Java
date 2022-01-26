<%@ page contentType="text/html;charset=utf-8" import="java.sql.*, javax.sql.*"%> 
	<jsp:useBean id="dbcp" class="jin.dbcp.DbcpBean" scope="application"/>

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
<body onload='javascript:document.f.email.focus();'>
	<center>
		<hr width='600' size='2' noshade>
		<h2>Simple Board with Servlet</h2>
		<a href='list.jsp'>글목록</a>
		<hr width='600' size='2' noshade>
	</center>
	<%
		  request.setCharacterEncoding("utf-8");
		  String seqStr = request.getParameter("seq"); 
		  String name = ""; 
		  String email = "";
		  String subject = "";
		  String content = "";
		  int seq = -1;
			  if(seqStr != null) { 
				seqStr = seqStr.trim();
				try{
					seq = Integer.parseInt(seqStr);
				}catch(Exception se){
					response.sendRedirect("update.do"); 
					System.out.println("8");
				}
			  } 
		  ResultSet rs = null;
		  DataSource ds = null;
		  Connection con =null;
		  Statement stmt = null;
		  String sql = "select * from BOARD where SEQ="+seq+" order by seq desc";
		  try{
			ds = dbcp.getDs();
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql); 
				while(rs.next()){ 
				  name = rs.getString(2);
				  email = rs.getString(3);
				  subject = rs.getString(4);
				  content = rs.getString(5);
				}
			 }catch(Exception e){
			   System.out.println("5");
			 }	  
	%>
	<form name='f' method='post' action='updateGet.jsp'>
			<input type='hidden' name='seq' value='<%=seq%>'>
			<input type='hidden' name='writer' value='<%=name%>'>
		<table border='1' width='600' align='center' cellpadding='3' cellspacing='1'><tr>
			<td width='30%' align='center'>글쓴이</td>
			<td align='center'><input type='text' name='name' size='60' value='<%=name%>' disabled></td>
		</tr>
		<tr>
			<td width='30%' align='center'>이메일</td>
			<td align='center'><input type='text' name='email' size='60' value='<%=email%>'></td>
		</tr>
		<tr>
			<td width='30%' align='center'>글제목</td>
			<td align='center'><input type='text' name='subject' size='60' value='<%=subject%>'></td>
		</tr>
		<tr>
			<td width='30%' align='center'>글내용</td>
			<td align='center'><textarea name='content' rows='5' cols='53'><%=content%></textarea></td>
		</tr>
		<tr>
			<td colspan='2' align='center'>
			<input type='submit' value='수정'>
		</td>
		</tr>
		</table>
	</form>
</body>