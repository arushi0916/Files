<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About Secure Fox</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/about.css">


<%
String role="login.jsp";
if(session.getAttribute("role")!=null){
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
%>

<style>

body {
 background-image: url("images/fox.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center; 
}
</style>


</head>


<body>


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


<div class="heading">
Benefits of SecureFox
</div>
<div class="subheading">
Understand your options. Identify the best value. Enjoy peace of mind.
</div>
<br>
<br>
<br>
<br>
<div class="container">
<center>Welcome to SecureFox.</center><br>

Life insurance is a contract wherein an individual is offered financial coverage by an insurance company in exchange for a payment over a period. 
</div>

<div class="content">
Our sole objective is to help insurance applicants make an informed decision when they buy a policy online.

 </div>
 <div class="content2">
 Insurance companies offer various policies in order to fulfill the insurance needs of different insurance buyers.
</div>

<div class="content3">
 In order to come across the best insurance plan, it is necessary to compare insurance plans on the basis of features, benefits, offered coverage, and premium rates.
</div>



</body>
</body>
</html>