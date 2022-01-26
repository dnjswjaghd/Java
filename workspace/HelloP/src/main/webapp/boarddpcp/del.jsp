<%@ page contentType="text/html;charset=utf-8" import="java.sql.*, javax.sql.*"%> 
	<jsp:useBean id="dbcp" class="jin.dbcp.DbcpBean" scope="application"/>
<%!
	   Connection con;
	   Statement stmt;
	   PreparedStatement pstmt;
	   String sql = "delete from BOARD where SEQ=?";
	  
	  
		private int getSeq(HttpServletRequest request){
			  String seqStr = request.getParameter("seq");
			  int seq = -1;
			  if(seqStr != null) {
				seqStr = seqStr.trim();
				if(seqStr.length() !=0){
					try{
						seq = Integer.parseInt(seqStr);
						return seq;
					}catch(Exception se){
					}
				 }
			  }
			  return seq;
		}
%>  
<%
	DataSource ds = null;
	Connection con =null;
	Statement stmt = null;
	int seq = getSeq(request); 
	if(seq!=-1){
		 try{
			ds = dbcp.getDs(); 
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		  }catch(SQLException se){
		  } 
	}
	response.sendRedirect("list.jsp");
%>
