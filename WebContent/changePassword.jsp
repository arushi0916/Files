<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Change Password</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/form.css">


<script>


function matchPassword(){
	
	var password = document.getElementById("password").value;
	var confPassword = document.getElementById("confPassword").value;
	if(password==confPassword){
		return true;
	}
	return false;
}
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

 Welcome <br>
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
	    
	     <form action="resetPasswordServlet" method="post" onsubmit="return matchPassword()">
		    <table class="table-edit">
			    <caption class="caption-edit">Change Password</caption>	

				<tr><td><input type="password" placeholder="New Password" class="input-edit" name="password" id="password"></td></tr>
				<tr><td><input type="password" placeholder="Confirm Password" class="input-edit" name="confirmPassword" id="confPassword"></td></tr>
			
				
                <tr><td><input type="submit" class="button-edit" value="Submit"></td></tr>
			</table>
		 </form>


</div>


</body>
</html>