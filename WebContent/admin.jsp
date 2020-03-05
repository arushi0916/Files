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
<title>Admin Home Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
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
  <a href="#home"><img src="images/logo.png" style="width:15%;">Secure Fox</a>
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




<div class="image-slider" >
<img class="mySlides" src="images/adminSlider1.jpg" >
<img class="mySlides" src="images/adminSlider2.jpg" >
<img class="mySlides" src="images/adminSlider3.jpg" >
<img class="mySlides" src="images/adminSlider4.jpg" >
<img class="mySlides" src="images/adminSlider5.jpg" >
</div>
<script>

var myIndex = 0;
carousel();

function carousel() {
var i;
var x = document.getElementsByClassName("mySlides");
for (i = 0; i < x.length; i++) {
  x[i].style.display = "none";  
}
myIndex++;
if (myIndex > x.length) {myIndex = 1}    
x[myIndex-1].style.display = "block";  
setTimeout(carousel, 2000); // Change image every 2 seconds
}




</script>









<% 
if(request.getAttribute("successMessage")!=null){
    	%>
    	
<div id="successmessage" class="successmessage">
    <%=request.getAttribute("successMessage") %>

</div>
<% 
request.setAttribute("successMessage", null);

} %>


	<div class="button-style">
		<form action="profileCreation.jsp" method ="get">
			<button value="Profile" class="button"> Profile Creation</button>
		</form>
		<form action="reportGenerationDetails" method ="get">
			<button value="Profile" class="button"> Report Generation</button>
		</form>
		<button value="Profile" class="button" onClick="addPolicy()"> AddPolicy</button>
		

	</div>



<div class="footer">
  <p>Copyright&#9400;</p>
</div>
</body>
</html>
