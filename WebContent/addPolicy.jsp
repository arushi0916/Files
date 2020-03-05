<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Policy</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/form.css">


<script>

<%

if(request.getAttribute("errormessage")!=null){
	%>
	
	setTimeout(function(){
		
		document.getElementById("errormessage").style.display="none";
	},5000);
	
<% 


}

%>




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
<% 
if(request.getAttribute("errormessage")!=null){
    	%>
    	
<div id="errormessage" class="errormessage">
    <%=request.getAttribute("errormessage") %>

</div>
<% 
request.setAttribute("errormessage", null);

} %>






<div class="footer">
  <p>Copyright&#9400;</p>
</div>

<div class="claim-creation-form" id="popup">
	    
	     <form action="addPolicyServlet" method="post">
		    <table class="table-edit">
			    <caption class="caption-edit">Add Policy</caption>	
			    </select></td></tr>
				<tr><td><select class="input-edit" name="policyType">
					<option>---Select Policy Type----</option>
				    <option value="Vehicle">Vehicle</option>
				    <option value="Health">Health</option>
				    <option value="NaturalDisaster">Natural Disaster</option>
				    <option value="Fire">Fire Accident</option>
				    <option value="Home">Home</option>
				
				
				</select></td></tr>	
				<tr><td><input type="number" placeholder="Premium Amount" class="input-edit" name="policyPremium"></td></tr>
				<tr><td><input type="number" placeholder="Account Number" class="input-edit" name="accountNumber"></td></tr>
			
				
                <tr><td><input type="submit" value="Add Policy" class="button-edit"></td></tr>
			</table>
		 </form>


</div>


</body>
</html>