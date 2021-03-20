<%! String dynamTitle = "Welcome to WEBD4201 Deliverable"; %>
<%@ include file="./header.jsp" %>

<div class="center">
	

	
	<h1><em>Welcome to my Home Page!</em></h1>
	<hr>
	<h5><%= message %></h5>
	<hr>
	<h5>
		This is my index page for my WEBD4201 Deliverable. From here you will notice that while you are not logged in, the
		dynamic navigation bar will only have the login and register pages.
		<br/><br/>
		Once logged in you will notice that neither of those buttons exist anymore, instead the navbar is populated with a button to the home page

	</h5>
	
</div>

<%@ include file="./footer.jsp" %>