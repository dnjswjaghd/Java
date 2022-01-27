<%@ page contentType="text/html;charset=utf-8" import="java.util.*, jin.mv.model.boardDTO"%> 
	<jsp:useBean id="boardDAO" class="jin.mv.model.boardDAO" scope="application"/>
	<jsp:useBean id="dto" class = "jin.mv.model.boardDTO"/>
	<jsp:setProperty name="dto" property="*"/>
	

<% 
	boardDAO.delete(dto.getSeq());
	response.sendRedirect("list.jsp");
%>
	