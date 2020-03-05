<%@page import="org.omg.CORBA.PolicyTypeHelper"%>
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
		String token1=Integer.toString((Integer)session.getAttribute("token"));
		String token2=(String)request.getParameter("token");
		 if(token1.equals(token2)){
			session.setAttribute("token", -1);	
		}
		else{
			response.sendRedirect("userPage.jsp");
		} 
   }
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Claim Creation</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
   <link rel="stylesheet" href="css/form.css">
   <script type="text/javascript" >
   function preventBack(){window.history.forward();}
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
    
    
    
    //for validation of zip code
    function invalidZip(){
	
	
	
	var zipCode =document.getElementById("zipCode").value;
	var num = parseInt(zipCode);
	 if(zipCode.length!=6)
		
		{
				if(num.toString().length!=6){
					alert("Please enter valid zip code");
					//document.write("asdlijfalksdjflaksjdflksajdi");
					//document.getElementById('zipCode').focus();
					
				}
			
		} 
	
	
	
}
    
    
    
    
    
    
    
    
    
</script>
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
<div class="footer">
  <p>Copyright&#9400;</p>
</div>

<div class="claim-creation-form" id="popup">
	    
	     <form action="claimCreation" method="post" onsubmit="return invalidZip()">
		    <table class="table-edit">
			    <caption class="caption-edit">Claim Creation</caption>
				<tr><td><textarea placeholder="Claim Reason" class="input-edit" name="claimReason" required></textarea></td></tr>
				<tr><td><textarea placeholder="Location" class="input-edit" name="location" required></textarea></td></tr>
		
				<tr><td><input type="text" placeholder="City" class="input-edit" name="city" required></td></tr>
				<tr><td><input type="text" placeholder="State" class="input-edit" name="state" required></td></tr>
				<tr><td><input type="number" placeholder="Zip Code" class="input-edit" name="zipCode" onblur="invalidZip()" id="zipCode" required></td></tr>

				<tr><td><input type="text" class="input-edit" value=<%= request.getParameter("policyType") %> readonly name="policyType" required></td></tr>
				<tr><td><input type="text" class="input-edit" value=<%= request.getParameter("policyNumber") %> readonly name="policyNumber" required></td></tr>
                <tr><td><input type="submit" value="Next" class="button-edit"></td></tr>
			</table>
		 </form>
	

</div>


</body>
</html>
