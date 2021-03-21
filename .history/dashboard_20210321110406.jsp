<%! String dynamTitle = "Welcome to my WEBD4201 Dashboard"; %>
<%@ include file="./header.jsp" %>

<%
//checking if a student (user) exists (is logged in).
    Student aStudent = (Student)session.getAttribute("aStudent");
    if (aStudent == null)
    {
      //redirect to login page
      session.setAttribute("message", "Please login.");
      response.sendRedirect("./login.jsp");
      return;
    }
%>

<div class="center">
	

	
	<h1><em>Welcome to my Dashboard!</em></h1>
	<hr>
	<h5><%= message %></h5>
	<hr>
	<p ></p>
	
</div>

<%@ include file="./footer.jsp" %>