<!doctype html>
<html lang="en">
  <head>
  	<%@  %>
	session_start();	
	ob_start();
	require("./includes/constants.php");
	require("./includes/db.php");
	require("./includes/functions.php");
	
	
	$message = isset($_SESSION['message'])?$_SESSION['message']:"";
	$message = flashMessage();
	
	
	
	<!--
	Author: <?php echo $name . "\n"; ?>
	Filename: <?php echo $file . "\n"; ?>
	Date: <?php echo $date . "\n";?>
	Description:  <?php echo $description; ?>
	-->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <title><?php echo $title; ?></title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/styles.css" rel="stylesheet">
	

	
  </head>
  <body>
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
																		<!-- Dynamically prints the name of the currently logged in user, if there is one -->
        <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">WEBD3201<?php if (isset($_SESSION['first'])) {echo " - " . $_SESSION['first'];} ?></a>
        <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
		<?php
		//if a session exists and a user is signed in
		if (isset($_SESSION['email']) && $_SESSION['email'] != NULL) 
		{
			echo "<a class='nav-link' href='./logout.php'>Sign out</a>";
		}
		//if a user isn't signed in
		else
		{
			echo "<a class='nav-link' href='./sign-in.php'>Log In</a>";
		}
		?>
        </li>
        </ul>
    </nav>
    <div class="container-fluid">
      <div class="row">
        
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
            <ul class="nav flex-column">
                <li class="nav-item">
                <a class="nav-link active" href="./index.php">
                    <span data-feather="home"></span>
                    Index <span class="sr-only">(current)</span>
                </a>
                </li>
				
				<?php
				//if a user is signed in
				if (isset($_SESSION['email']) && $_SESSION['email'] != NULL)
				{
					echo '
						<li class="nav-item">
						<a class="nav-link" href="./dashboard.php">
						<span data-feather="file"></span>
						Dashboard
						</a>
						</li>
					';
					//if the user is an admin
					if (isset($_SESSION['type']) && $_SESSION['type'] == "s")
					{
						echo '
								
								<li class="nav-item">
								<a class="nav-link" href="./salespeople.php">
								<span data-feather="sales-people"></span>
								Add Salespeople
								</a>
								</li>
						';
					}
					
					echo '
						<li class="nav-item">
						<a class="nav-link" href="./clients.php">
						<span data-feather="file"></span>
						Add A Client
						</a>
						</li>
						';
					//if the user is an Agent
					if (isset($_SESSION['type']) && $_SESSION['type'] == "a")
					{
					echo '
						<li class="nav-item">
						<a class="nav-link" href="./calls.php">
						<span data-feather="file"></span>
						Record a Call
						</a>
						</li>
						';
					}
					
					echo '
						<li class="nav-item">
						<a class="nav-link" href="./change-password.php">
						<span data-feather="file"></span>
						Change Password
						</a>
						</li>
						';
				}
				
				echo '
						<li class="nav-item">
						<a class="nav-link" href="./reset.php">
						<span data-feather="file"></span>
						Reset Account
						</a>
						</li>
						';
				?>
               
            </ul>
<!--
            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                <span>Saved reports</span>
                <a class="d-flex align-items-center text-muted" href="#">
                <span data-feather="plus-circle"></span>
                </a>
            </h6>
            <ul class="nav flex-column mb-2">
                <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="file-text"></span>
                    Current month
                </a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="file-text"></span>
                    Last quarter
                </a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="file-text"></span>
                    Social engagement
                </a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="file-text"></span>
                    Year-end sale
                </a>
                </li>
            </ul> -->
            </div>
        </nav>

        <main class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
          <div class="d-block justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">