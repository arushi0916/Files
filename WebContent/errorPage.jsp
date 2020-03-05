<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
<%
String role="login.jsp";
if(session.getAttribute("role")!=null &&  request.getAttribute("error")!=null){
	if(session.getAttribute("role").equals("usr")){
		role="userPage.jsp"; 
	}
	else if(session.getAttribute("role").equals("agnt")){
		role="agent.jsp"; 
	}
	else{
		role="admin.jsp"; 
	}
}
else {
	response.sendRedirect("login.jsp");
}
%>
</head>
<body background="images/error.png">

<div class="navbar">
  <a href=<%=role %>><img src="images/logo.png" style="width:15%;">Secure Fox</a>
  <a href="#news">About</a>
  <a href="termsAndCondition.jsp">Terms and conditions</a>
  <div class="dropdown">
    <button class="dropbtn">Contact Us
      <img src="drop.png" style="width:15px;height:15px;"></img>
    </button>
    <div class="dropdown-content">
      <a href="#">Helpline Numbers</a>
      <a href="#">Branches</a>
      <a href="#">Mail Us</a>
    </div>
  </div> 
  <% 
  if(session.getAttribute("role")!=null){
	  %>
  <a  href="logout">Logout</a>
  <%} 
  
  else{
	  
  %>
  
    <a  href="login.jsp">Login</a>
  <% }%>
</div>


</body>
</html>