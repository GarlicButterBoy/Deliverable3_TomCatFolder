<%! String dynamTitle = "Welcome to WEBD4201 Deliverable"; %>
<%@ include file="./header.jsp" %>

<div>

		<h2>Please log in</h2>
		<p>
		   Enter your login information below.<br/>
		   If you are not a student, please return to the
		   <a class="text-dark" href="register.jsp">register</a> page. <br/>
		   or return to the <a></a>
	    </p>

</div>
	
	<form class="" method="POST" action="">


    	<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    	<label for="inputEmail" class="sr-only">Email address</label>
    	<input type="email" id="inputEmail" class="form-control" name="email" value="" placeholder="Email address" required autofocus>

    	<label for="inputPassword" class="sr-only">Password</label>
    	<input type="password" id="inputPassword" class="form-control" name="passwd" value="" placeholder="Password" required>

    	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	</form>
	Please wait after pressing <strong>Log in</strong>
	while we retrieve your records from our database.<br>
	<em>(This may take a few moments)</em>
	</center>

<%@ include file="./footer.jsp" %>