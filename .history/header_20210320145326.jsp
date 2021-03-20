<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <title>Starter Template · Bootstrap v5.0</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/starter-template/">

    
<!-- IMPORTS -->
<%@ page import="java.util.*" %>
<%@ page import="webd4201.sturchflintn.*" %>

<!-- VARIABLES AND CONSTANTS -->
<%
    String hrefIndex = "index.jsp";
    String linkIndex = "Home";

    String hrefLogin = "login.jsp";
    String linkLogin = "Login";

    String hrefDashboard = "dashboard.jsp";
    String linkDashboard = "Dashboard";

    String hrefRegister = "register.jsp";
    String linkRegister = "Register";
%>


<!-- SESSIONS STUFF -->
<%
   // public HttpSession session = HttpServletRequest.getSession(); 
   //For the message flashing
    String message = (String)session.getAttribute("message");
    if (message == null)
    {
      message = ""; //prevents null pointer exceptions
    }
    else //there was a message but we have a copy
    {
      //clean the message so it does not redisplay on a different page
      session.removeAttribute("message");
    }

    //checking if a student (user) exists (is logged in).
    Student aStudent = (Student)session.getAttribute("student");
    if (aStudent == null)
    {
      //redirect to login page
      //session.setAttribute("message", "Please login.");
     // response.sendRedirect("./login.jsp");
      //return;
    }
    else
    {
      //Update the login and register links and text
      linkLogin = "Logout";

      linkRegister = "Update";
    }


 %>
    <!-- Bootstrap core CSS 
	<%@ include file="./resources/bootstrap.min.css" %>-->
<link href="./resources/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

<title> <%= dynamTitle %></title>
    
    <!-- Custom styles for this template -->
    <link href="./resources/styles.css" rel="stylesheet">
  </head>
  <body class="text-dark" style="background-color: #e3f2fd;">
    
<nav class="navbar navbar-expand-md navbar-dark bg-dark text-light fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">WEBD4201</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
      
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="<% hrefIndex; %>>"><% linkIndex; %></a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</a>
          <ul class="dropdown-menu" aria-labelledby="dropdown01">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>

<main class="container">
