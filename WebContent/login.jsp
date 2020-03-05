<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%
  
  
  
     if(session.getAttribute("username")!=null && session.getAttribute("role")!=null){

    	 if(session.getAttribute("role").equals("usr")){

 			response.sendRedirect("userPage.jsp");
 		}
    	 else if(session.getAttribute("role").equals("agnt")){
 			response.sendRedirect("agent.jsp");
 		}
    	 else{
    		 response.sendRedirect("admin.jsp");
    	 }

   }
    

  %>  
	
    
    
    
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/registration.css">
   
   <script>
   
       function visiblePopup(){
	        document.getElementById("popup").style.display="block";
			document.getElementById("navigation").disabled=true;
	   }
	   
	   function HidePopup(){
	        document.getElementById("popup").style.display="none";
	   }	
	   
	   window.onload=function(){
		   
		   <% 
		   if(request.getAttribute("error")!=null){
		   
		   %>
		   		document.getElementById("error").style.display="block";
		   		
		   <% 
		   } 
		   %>
		   
	   }
	  
	   
	   function removeError(){
		   	document.getElementById("error").style.display="none";
		   return true;
	   }
	   
	   
	  //for invalid credentials 
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
   <script type="text/javascript">
        function preventBack() { window.history.forward(); }
        setTimeout("preventBack()", 0);
        window.onunload = function () { null };
    </script>  
</head>
<body background="images/home4.jpg">
	<div class="navbar" id="navigation" disabled>
	  <a href="#home"><img src="images/logo.png" style="width:20%;height:10%;"></img>Secure Fox</a>
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
	  <a href="#news" onclick="visiblePopup()">Login</a>
	</div>
	
<!-- 	
	<div class="error-box" id="error">	
		<div class="error-display">
		User Name or Password is incorrect
		</div>
	</div>
	
	
	 -->
	
	<div class="company-name">
	     Welcome To SecureFox 
	</div> 
	
	
	<!--for invalid credentials!-->
	
	
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

<div class="registration-form" id="popup" onsubmit="return removeError()">
	     <img src="cross.png" style="width:5%;height:5%;margin-left:95%;border-radius:100%" onclick="HidePopup()">
	     <form action="login" method="post">
		    <table class="table-edit">
			    <caption class="caption-edit"><img src="symbol.jfif" style="width:35%;border-radius:100%"></caption>
				<tr><td><input type="text" placeholder="User Name" class="input-edit" name="username"></td></tr>
				<tr><td><input type="password" placeholder="password" class="input-edit" name="password"></td></tr>
			
                <tr><td><input type="submit" value="Login" class="button-edit"></td></tr>
			</table>
		 </form>


</div>


</body>
</html>
