<%@page import="java.util.Random"%>
<%@page import="com.cg.dto.Policy"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%


   if(session.getAttribute("username")==null){
	   response.sendRedirect("login.jsp");
   }
   else{
		if(session.getAttribute("role").equals("usr")){
			response.sendRedirect("userPage.jsp");
		}
		if(session.getAttribute("role").equals("adm")){
			response.sendRedirect("admin.jsp");
		}
		
		
   }

%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agent Claim List</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/searchbox.css">
<link rel="stylesheet" href="agentTable.css">

</head>


<body>

<div class="navbar">
  <a href="agent.jsp"><img src="images/logo.png" style="width:20%;height:10%;"></img>Secure Fox</a>
  <a href="about.jsp">About</a>
  <a href="termsAndCondition.jsp">Terms and conditions</a>
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
  <a  href="logout">Logout</a>
  <div class="role">

  Welcome Agent<br>
  <i><%=session.getAttribute("username") %></i>


 </div>
</div>

 

<div class="table-div">
	<div class="search-box">
	 <table>
		<form action="searchUserName" method = "post">
		  <tr><td><input type="text" placeholder="UserName" class="input-edit-searchbox" name="clientUsername"></td>
		   <td><input type="submit" value="Search" class="search-button" onclick="search()"></td>
		</tr>
		</form>
	 </table>
	</div>
	<table class="table-style"  cellpadding="15%">
        <caption class="caption">Claim Details</caption>
	   <tr class="table-heading">
	   		<th>Policy Type</th>
			<th>Account Number</th>
			<th>Policy Number</th>
			<th>Premium Amount</th>
			<th>Create Claim Link</th>
	   </tr>
	   
	   <%
	   Random rand = new Random(); 
	   int rand_number=rand.nextInt(100000);
	   session.setAttribute("token", rand_number);
	   	ArrayList<Policy> policyList = (ArrayList<Policy>) request.getAttribute("policy");
	   	for(Policy policy:policyList){
	   %>
	   	<tr class="table-row">
	   		<td><%= policy.getPolicyType() %></td>
	   		<td><%= policy.getAccountNumber()%></td>
	   		<td><%=policy.getPolicyNumber() %></td>
	   		<td><%= policy.getPolicyPremium() %></td>
	   		
			<td><!-- <button class="link-button"onclick="claimCreation()">Click Here</button> -->
			<a href="agentClaimCreation.jsp?policyType=<%= policy.getPolicyType()%>&policyNumber=<%= policy.getPolicyNumber()%>&token=<%=rand_number%>">claimCreation</a>
			
			</td>
	   </tr>
	   <%
	   }%>
	
	   
	</table>
	
	<div id="insertFrame" class="frame-style">
	
	</div>

</div>

<div class="footer">
  <p>Copyright&#9400;</p>
</div>
</body>
</html>