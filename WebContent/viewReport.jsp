<%@page import="java.net.ConnectException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cg.dto.UserResponses"%>
<%@page import="java.util.List"%>
<%@page import="com.cg.dto.Claim"%>
<%@page import="com.cg.DAO.GetClaimDetailsDAO"%>
<%@page import="com.cg.DAO.IGetClaimDetails"%>
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
		if(session.getAttribute("role").equals("agnt")){
			response.sendRedirect("agent.jsp");
		}
   }
	
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Report page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/table.css">
<script type="text/javascript" >
   function preventBack(){window.history.forward();}
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
    
    function addPolicy(){
    	window.location.href="addPolicy.jsp";
    }
    
    <%
    
    if(request.getAttribute("successMessage")!=null){
    	%>
    	
    	setTimeout(function(){
    		
    		document.getElementById("successmessage").style.display="none";
    	},5000);
    	
   <% 
   
   request.setAttribute("successmessage", null);
    
    }
    
    %>
    
    
    
    
</script>
</head>
<body>

<div class="navbar">
  <a href="admin.jsp"><img src="images/logo.png" style="width:15%;">Secure Fox</a>
  <a href="about.jsp">About</a>
  <a href="TermsAndCondition.jsp">Terms and conditions</a>
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

 Welcome Admin<br>
 <i><%=session.getAttribute("username") %> </i> 


 </div>
  
</div>
	
<div align="center">
	

	<%
	
		IGetClaimDetails claimDetails = new GetClaimDetailsDAO();
		Claim claim = new Claim();
		
		List<UserResponses> userList = new ArrayList<>();
		int claimNumber = Integer.parseInt(request.getParameter("claimNumber")); %>
	<%
		try {
			
			
			claim = claimDetails.getClaim(claimNumber);
			userList = claimDetails.getResponses(claim.getClaimNumber());
			
		} catch (Exception e) {
			
			
		}
		
	%>
	<table  class="table-style"  cellpadding="10%"  style="margin-top:2%;margin-left:2%;">
		<caption class="caption">Report for Claim</caption>
		<tr  class="table-row"><td>User Name<td><%= claim.getUserName() %>
		<tr  class="table-row"><td>Claim Reason<td><%= claim.getClaimReason() %>
		<tr  class="table-row"><td>Claim Number<td><%= claim.getClaimNumber() %>
		<tr  class="table-row"><td>Location <td><%=claim.getAccidentLocationStreet() %>
		<tr  class="table-row"><td>City <td><%= claim.getAccidentCity() %>
		<tr  class="table-row"><td>State<td><%= claim.getAccidentState() %>
		<tr  class="table-row"><td>Zip<td><%= claim.getAccidentZip() %>
		<tr  class="table-row"><td>Policy Type<td><%= claim.getClaimType() %>
		<tr  class="table-row"><td>PolicyNumber<td><%= claim.getPolicyNumber() %>
			
	</table>
	
	<table border="1" class="table-style" cellpadding="5%" style="margin-top:2%;margin-left:2%;margin-bottom:8%;">
	<caption class="caption">User Response</caption>
	<tr class="table-heading"><th>Question<th>Answer</tr>
		<%
			for(UserResponses answers: userList){		
		%>
			<tr class="table-row"><td><%= answers.getQuestion()%><td><%= answers.getAnswer() %></tr>
		<%
			}
		%>
	
	</table>
	

</div>

<div class="footer">
  <p>Copyright&#9400;</p>
</div>
</body>
</html>