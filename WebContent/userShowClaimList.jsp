<%@page import="com.cg.dto.ShowClaimDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cg.dto.Policy"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
   if(session.getAttribute("username")==null){
	   response.sendRedirect("login.jsp");
   }
   else{
	   if(session.getAttribute("role").equals("adm")){
			response.sendRedirect("admin.jsp");
		}
		if(session.getAttribute("role").equals("agnt")){
			response.sendRedirect("agent.jsp");
		} 
   }
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Claim List</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/searchbox.css">
<link rel="stylesheet" href="css/table.css">
<link rel="stylesheet" href="css/form.css">


</head>
<body>

<div class="navbar">
  <a href="userPage.jsp"><img src="images/logo.png" style="width:20%;height:10%;"></img>Secure Fox</a>
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

  Welcome User<br>
  <i><%=session.getAttribute("username") %></i>


 </div>
</div>



<div class="table-div">
	<div class="search-box">
	<table>
	<form action="userSearchBasedOnFilter" method="post">
		  <tr><!-- <td><input type="text" placeholder="Account Number" class="input-edit-searchbox"></td> -->
		 
		   
		   	<td><select class="input-edit filter" onchange="filter()" name="filter">
					    <option>---Filter---</option>
					    <option value="all" class="input-edit">ALL</option>
					    <option value="Approved" class="input-edit">Approved</option>
					    <option value="Rejected" class="input-edit">Rejected</option>
					    <option value="Pending" class="input-edit" >Pending</option>
					
					
					</select></td>
				  <td><input type="submit" value="Search" class="search-button" onclick="searchDetails()" style="margin-left:20px;"></td>	
		   </tr>
	   </form>
	 </table>
	</div>
	
	
	
	<table class="table-style"  cellpadding="15%">
        <caption class="caption">Claim Details</caption>
	   <tr class="table-heading">
	   		<th>Account Number</th>
	   		<th>Policy Number</th>
			
			<th>Claim Number</th>
			<th>Claim Type</th>
			<th>Premium amount</th>
			<th>Status</th>
	   </tr>
	   
	   <%
	   	ArrayList<ShowClaimDetails> claimDetailsList = (ArrayList<ShowClaimDetails>) request.getAttribute("ClaimList");
	   	for(ShowClaimDetails claimList:claimDetailsList){
	   %>
	   	<tr>
	   		<td><%= claimList.getAccountNumber() %></td>
	   		<td><%= claimList.getPolicyNumber() %></td>
	   		<td><%= claimList.getClaimNumber()%></td>
	   		<td><%= claimList.getPolicyType() %></td>
	   		<td><%= claimList.getPolicyPremium() %></td>
	   		<td><%= claimList.getStatus() %></td>
	   		
			<%-- <td><!-- <button class="link-button"onclick="claimCreation()">Click Here</button> -->
			<a href="claimCreation.jsp?policyType=<%= policy.getPolicyType()%>&policyNumber=<%= policy.getPolicyNumber()%>">claimCreation</a>
			
			</td> --%>
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
