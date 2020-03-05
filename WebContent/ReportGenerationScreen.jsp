<%@page import="com.cg.dto.ShowClaimDetails"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin Report Generation Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/searchbox.css">
<link rel="stylesheet" href="css/table.css">
<link rel="stylesheet" href="css/form.css">

	<script>
</script>
</head>
<body>

<div class="navbar">
  <a href="admin.jsp"><img src="images/logo.png" style="width:15%;">Secure Fox</a>
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

 Welcome Admin<br>
 <i><%=session.getAttribute("username") %> </i> 


 </div>
 </div> 

<script>

function defaultAccount(){

	var accountnum =document.getElementById("accountNumber").value;

	 if(accountnum=="")
		
		{
		 document.getElementById("accountNumber").value="0";
		} 
	
	return true;
	
}


</script>



<div class="table-div">
	<div class="search-box">
	<table>
	<form action="reportGenerationDetails" method="post" onsubmit="return defaultAccount()">
		  <tr><td><input type="number"  placeholder="Account Number" class="input-edit-searchbox" name="accountNumber" id="accountNumber"></td>
		  
		  <td><select class="input-edit filter"  name="filter" required>
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
	
	
	<%
	
	if(session.getAttribute("pdfPath")!=null){ %>
	<h3 style="text-align:center;color:#FF69B4">Download Location<%=session.getAttribute("pdfPath")%></h3>
	<% session.setAttribute("pdfPath", null);
 }%> 
	<table class="table-style1"  cellpadding="15%">
        <caption class="caption">Claim Details</caption>
	   <tr class="table-heading">
	   		<th>Account Number</th>
	   		<th>Policy Number</th>
			<th>Claim Number</th>
			<th>Claim Type</th>
			<th>Premium Amount</th>
			<th>Status</th>
			<th>View Report</th>
			<th>Download Report</th>
			<th style="width:25%;">Approval</th>
	   </tr>
	   
	   <%
	   	ArrayList<ShowClaimDetails> claimDetailsList = (ArrayList<ShowClaimDetails>) request.getAttribute("ClaimList");
	   //request.setAttribute("pdfList",claimDetailsList);
	   session.setAttribute("pdfList",claimDetailsList);
	   	for(ShowClaimDetails claimList:claimDetailsList){
	   %>
	   	<tr class="table-row">
	   		<td><%= claimList.getAccountNumber() %></td>
	   		<td><%= claimList.getPolicyNumber() %></td>
	   		<td><%= claimList.getClaimNumber()%></td>
	   		<td><%= claimList.getPolicyType() %></td>
	   		<td><%= claimList.getPolicyPremium() %>
	   		<td><%= claimList.getStatus() %></td>
	   	
	   		
			<%
			%>
			</td> 
			<td><a href="viewReport.jsp?claimNumber=<%=claimList.getClaimNumber()%>">ViewReport</a></td>
			
			<td><a href="pdfCreation?claimNum=<%=claimList.getClaimNumber()%>">Download report</a>
			
			
			
			<%
			if(claimList.getStatus().equals("Approved") || claimList.getStatus().equals("Rejected")) 
				
				
				{
				
				%>
			
					<td> Already <%=claimList.getStatus() %></td>
				
				<%
				}
			
				 else
					
				{
					%>
						<td>

							<a href="updateStatus?claimnumber=<%=claimList.getClaimNumber()%>&status=Approved"><button class="accept" onclick="" name = "status" value="Approved">Accept</button></a>
						
							<a href="updateStatus?claimnumber=<%=claimList.getClaimNumber()%>&status=Rejected"><button  class="reject" onclick="" name="status" value="Rejected">Reject</button></a></td> 
						<%} %>

	  
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