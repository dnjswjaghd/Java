<%@ page contentType="text/html;charset=utf-8" import="java.util.*, jin.mv.model.boardDTO"%> 
	<jsp:useBean id="boardDAO" class="jin.mv.model.boardDAO" scope="application"/>
	<jsp:useBean id="dto" class = "jin.mv.model.boardDTO"/>
	<jsp:setProperty name="dto" property="*"/>
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
		Address List MV model
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
		ArrayList<boardDTO> list = boardDAO.list();
		if(list != null){
			int size = list.size();
			System.out.println("size: "+size);
			if(size != 0){
			for(boardDTO dto1 :list){
%>
			<tr>
				<td align='center'><%=dto1.getSeq()%></td> 
				<td><%=dto1.getName()%></td>
				<td><%=dto1.getEmail()%></td>
				<td><a href='content.jsp?seq=<%=dto1.getSeq()%>'><%=dto1.getSubject()%></a></td>
				<td><%=dto1.getRdate()%></td>
			</tr>
<%
				}
			}else{
%>
			<tr>
				<td colspan="5" align='center'>데이터가 하나도 없네요</td>
			</tr>
<% 
				}
			}
		
%>
		


		
	</table>
</center>

