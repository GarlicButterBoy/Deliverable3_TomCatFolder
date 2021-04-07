<%! String dynamTitle = "Welcome to WEBD4201 Register"; %>
<%@ include file="./header.jsp" %>

<div class="center">
	

	
	<h1 style="text-align:center"><em>Welcome to my Register Page!</em></h1>
	<hr>
	<h5 style="text-align:center"><%= message %></h5>
	<hr>
	<form class="form-signin" method="POST" action="./Register">


    	<h1 class="h3 mb-3 font-weight-normal">Please Enter your information below to register</h1>
    	<label for="Login" class="sr-only">ID</label>
    	<input type="text" id="idRegister" name="idRegister" class="form-control" value="" placeholder="User ID" required autofocus>

		<label for="Login" class="sr-only">First Name</label>
    	<input type="text" id="Login" name="Login" class="form-control" value="" placeholder="First Name" required autofocus>

		<label for="Login" class="sr-only">Last Name</label>
    	<input type="text" id="Login" name="Login" class="form-control" value="" placeholder="Last Name" required autofocus>
		
		<label for="Login" class="sr-only">Email Address</label>
    	<input type="text" id="Login" name="Login" class="form-control" value="" placeholder="Email Address" required autofocus>

		<label for="inputPassword" class="sr-only">Password</label>
    	<input type="password" id="Password" name="Password" class="form-control" value="" placeholder="Password" required>

    	<label for="inputPassword" class="sr-only">Confirm Password</label>
    	<input type="password" id="Password" name="Password" class="form-control" value="" placeholder="Password" required>

    	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	</form>
	<p style="text-align:center">
	Please wait after pressing <strong>Sign In</strong>
	while we retrieve your records from our database.<br>
	<em>(This may take a few moments)</em>
	</p>
	
</div>

<%@ include file="./footer.jsp" %>