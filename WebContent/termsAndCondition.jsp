<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Terms and conditions</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">

<style>
.content{
text-align:center;
color: #ff9933;
}
</style>





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


</head>
<body>
<div class="navbar">
  <a href=<%=role %>><img src="images/logo.png" style="width:15%;">Secure Fox</a>
  <a href="about.jsp">About</a>
  <a href="#news">Terms and conditions</a>
  <div class="dropdown">
    <button class="dropbtn">Contact Us
      <img src="drop.png" style="width:15px;height:15px;"></img>
    </button>
   <div class="dropdown-content">
      <a href="contactus.jsp">Contact Us</a>
      <a href="branches.jsp">Branches</a>
      <a href="feedback.jsp">Feedback</a>
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

<h1><center>Terms and Conditions</center></h1>
<p class="content">
The general terms and conditions of insurance contracts set out the
definitions used in insurance contracts, also the rights and obligations
of the insurer, policyholder and the insured person upon conclusion
and performance of a contract.<br>
</p>
<p>
1.1An insurance contract shall be concluded on the basis of an
insurance application.<br>
1.2. An insurance contract is deemed to be concluded if the policyholder has met the following conditions:
 has confirmed the conclusion of the insurance contract with
his/her signature<br>
 has paid the insurer the first insurance premium<br>
 has committed any other act agreed upon in the insurance
contract.<br>
1.3. The insurance contract is concluded without a term. The duration of insurance period shall be one year, unless stated
otherwise in the insurance contract. The insurer shall issue
a new insurance policy for each insurance period unless otherwise agreed upon in the insurance contract.<br>
1.4. An insurance contract may be concluded for a fixed term if it
is related to training, a stay in a foreign country, traveling, or
the performance of a fixed-term work or operation.<br>
1.5. If the insured person is not a policyholder, an insurance contract
can be concluded only upon the consent of the insured person. If the policyholder insures his/her child who is under his/
her custody and has not attained 18 years of age by the time of
conclusion of the insurance contract, the insurance application
shall be signed by the policyholder on behalf of the child.<br>



</html>