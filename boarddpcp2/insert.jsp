<%@ page contentType="text/html;charset=utf-8" import="java.util.*, jin.mv.model.boardDTO"%> 
	<jsp:useBean id="boardDAO" class="jin.mv.model.boardDAO" scope="application"/>
	<jsp:useBean id="dto" class = "jin.mv.model.boardDTO"/>
	<jsp:setProperty name="dto" property="*"/> <%--getter setter가 되어있을때만 사용가능 --%>

<%
	  boolean flag = boardDAO.insert(dto);
%>
	  <script>
	  if(<%=flag%>){
		  alert("입력성공"); 
	  }else{
		  alert("입력실패");
	  }
	  location.href='list.jsp'
	  </script>
	  System.out.println("AddrServletIn name: "+name+", email: "+email+"");
