<%@ page contentType="text/html;charset=utf-8" import="java.sql.*, javax.sql.*"%> 
	<jsp:useBean id="dbcp" class="jin.dbcp.DbcpBean" scope="application"/>
<%
	System.out.println("한글한글");
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
	<h1>
		Address List 
	</h1>
	<a href = "../">인덱스</a>
	<a href = "input.jsp">글쓰기</a>
	<table border='1' cellpadding='7' cellspacing='2' width='50%'>
	    <tr>
		    <th>글번호</th>
			<th>작성자</th>
			<th>이메일</th>
			<th>제목</th>
			<th>날짜</th>
		</tr>
		<%
			DataSource ds = null;
			ResultSet rs = null;
			Connection con = null;
			Statement stmt = null;
			String sql = "select * from BOARD order by seq desc"; 
            try{
               ds = dbcp.getDs();
			   con = ds.getConnection();
			   stmt = con.createStatement();
               rs = stmt.executeQuery(sql);
               while(rs.next()){
                  int seq = rs.getInt(1);
                  String name = rs.getString(2);
                  String email = rs.getString(3);
				  String subject = rs.getString(4);
                  Date rdate = rs.getDate(6);
                  System.out.println(" seq: "+seq+" name: "+name+" email: "+email);
				%>
				<tr>
					<td align='center'><%=seq%></td> 
					<td><%out.println(name);%></td>
					<td><%out.println(email);%></td>
					<td><a href='content.jsp?seq=<%=seq%>'><%out.println(subject);%></a></td>
					<td><%out.println(rdate);%></td>
				</tr>
				<%

               }
            }catch(SQLException se){
            }finally{
               try{
                  if(rs != null) rs.close();
               }catch(SQLException se){}
            }
		%>

		
	</table>
</center>

