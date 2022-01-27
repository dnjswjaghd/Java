<%@ page contentType="text/html;charset=utf-8" import="java.util.*, jin.mv.model.boardDTO"%> 
	<jsp:useBean id="boardDAO" class="jin.mv.model.boardDAO" scope="application"/>
	<jsp:useBean id="dto" class = "jin.mv.model.boardDTO"/>
	<jsp:setProperty name="dto" property="*"/>

<%
	  request.setCharacterEncoding("UTF-8");
	  int seq = -1;
	  String seqStr = request.getParameter("seq");
	  seqStr = seqStr.trim();
	  if(seqStr.length() != 0){
		seq = Integer.parseInt(seqStr);
	  }else{
		response.sendRedirect("list.jsp");
	  }		  
	  String name = request.getParameter("writer"); 
	  boardDAO.updateGet(dto, name);
	  response.sendRedirect("list.jsp");
%>