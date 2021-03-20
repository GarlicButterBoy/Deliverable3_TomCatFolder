<%! String dynamTitle = "Welcome to WEBD4201 Dashboard"; %>
<%@ include file="./header.jsp" %>

<div class="center">
	

	
	<h1><em>Welcome to my Dashboard!</em></h1>
	<hr>
	<h5><%= message %></h5>
	<hr>
	<p ></p>
	<strong>Bradshaw Marina provides:</strong><br>
	<table cellspacing="5" cellpadding="3">
	<tr>
		<td><li>
			Docks with electrical service only
		</li></td>
		<td><li>
			Docks with both electrical service and water
		</li></td>
	</tr>
	<tr>
		<td><li>
			Covered slips (all of which have electricity)
		</li></td>
		<td><li>
			Annual leases, with monthly or yearly payment options
		</li></td>
	</tr>
	</table>
	<center><br>If you are a Bradshaw Marina customer, please log in.
	<table align="center" bgcolor="lightgoldenrodyellow">
	<tr>
		<td width="100" align="Center">
			<a href="login.jsp">
			<strong><font size="+1">Log In</font></strong></a>
		</td>
	</tr>
	</table>
</div>

<%@ include file="./footer.jsp" %>