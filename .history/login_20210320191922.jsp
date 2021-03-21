<%! String dynamTitle = "Welcome to WEBD4201 Deliverable"; %>
<%@ include file="./header.jsp" %>

<div>

		<h2 style="text-align:center">Welcome Back!</h2>

		<h4 style="text-align:center"> Enter your login information below. </h4>
		<p style="text-align:center">
		  <br/>
		   If you are not a student, please return to the
		   <a class="text-dark" href="register.jsp">register</a> page. <br/>
		   or return to the <a class="text-dark" href="index.jsp">home</a> page.
	    </p>

</div>
	
	<form class="form-signin" method="POST" action="./Login">


    	<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    	<label for="inputEmail" class="sr-only">Email address</label>
    	<input type="email" id="inputEmail" class="form-control" name="email" value="" placeholder="Email address" required autofocus>

    	<label for="inputPassword" class="sr-only">Password</label>
    	<input type="password" id="inputPassword" class="form-control" name="passwd" value="" placeholder="Password" required>

    	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	</form>
	<p style="text-align:center">
	Please wait after pressing <strong>Sign In</strong>
	while we retrieve your records from our database.<br>
	<em>(This may take a few moments)</em>
	</p>

<%@ include file="./footer.jsp" %>