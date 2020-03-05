<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.cg.dto.Questions"%>
<%@page import="java.util.ArrayList"%>
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
		
   }
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Questions</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/form.css">
<script type="text/javascript" >
   function preventBack(){window.history.forward();}
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
</script>
</head>
<body>

<%
String role="";
if(session.getAttribute("role").equals("usr")){
	role="userPage.jsp"; 
}
else{
	role="agent.jsp"; 
}

%>



<div class="navbar">
  <a href=<%=role %>><img src="images/logo.png" style="width:20%;height:10%;"></img>Secure Fox</a>
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
<div class="question-style" >
<form action="policyDetailsServlet" method="post">
	<table cellpadding="3%;">
		
			<%
		   	ArrayList<Questions> questionsList = (ArrayList<Questions>) request.getAttribute("questionsList");
			int count=1;
			String str = "";
		   	for(Questions question:questionsList){
		   		str="q";
		   		str+=count;
		   		session.setAttribute(str, question.getQuestionId());
		   		
		   		
		   %>
		   <tr>
		   <%-- <td><%= question.getQuestionId() %> --%>
		    <td><%= count %>
			<td><%= question.getQuestion()%></td>
			<td><input type="radio" value="yes" name =<%= count %> class="radio-style">Yes</td>
			<td><input type="radio" value="no" name = <%= count %> class="radio-style">No</td>
			
		<%
		   	count++;}
		   	
		   	session.setAttribute("noOfQuestions", count-1);
		%>
		</tr>
	<tr><td><input type="submit" class="button-edit"></td></tr>
	
	</table>
</form>
</div>



<div class="footer">
  <p>Copyright&#9400;</p>
</div>
</body>
</html>
