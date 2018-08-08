<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dots - Network</title>
		<link rel="icon" href="images/favicon.ico" type="image/x-icon">
		<link rel="stylesheet" type="text/css" href="styles/navbar.css">
		<link rel="stylesheet" type="text/css" href="styles/network.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
		<script src="scripts/network.js" async></script>
	</head>
	<body>
		
		<%@ page import="java.util.List, model.User, model.Experience" %>
		<% 
	 		if (session.getAttribute("email") == null) { 
				response.sendRedirect(request.getContextPath() + "/login.jsp"); 
			} 
		%>
		
		<!-- top navigation bar -->
        <div class="navbar">
            <img src="images/dots.png" />
            <a href="settings.jsp"><i class="fas fa-cogs"></i><br>Settings</a>
            <a href="edit_profile.jsp"><i class="fas fa-user"></i><br>Profile</a>
            <a href="notifications.jsp"><i class="fas fa-bell"></i><br>Notifications</a>
            <a href="messages.jsp"><i class="fas fa-comments"></i><br>Messages</a>
            <a href="jobs.jsp"><i class="fas fa-newspaper"></i><br>Jobs</a>
            <a href="network.jsp" class="active"><i class="fas fa-users"></i><br>Network</a>
            <a href="home.jsp"><i class="fas fa-home"></i><br>Home</a>
        </div>
	
		<div class="container">
			<span>Find other professionals :</span>
			<form action="network" method="get" id="search" onmouseover="hoverFun()" onmouseout="unhoverFun()">
				<input type="hidden" name="action" value="search_form">
				<input type="text" name="search" id="textbox" placeholder="Search..." onfocus="focusFun()" onblur="unfocusFun()" autocomplete="off" spellcheck="false">
  				<button type="submit" id="search_button" onmouseover="hoverIconFun()" onmouseout="unhoverIconFun()"><i class="fas fa-search"></i></button>
			</form>
		</div>
		
		<%  
		// retrieve your list from the request, with casting 
		List<User> connectionList = (List<User>) session.getAttribute("connectionList");
		%>
		<div class="container">
			<span id="title">Your Connections :</span>
			<br>
			<!-- create connection items -->
			<% if(connectionList != null && !connectionList.isEmpty()) { %>
				<% for(User u : connectionList) { %>
					<div class="container user_container" onclick="submitForm(<%= String.valueOf(u.getId()) %>)">
						<img src= <%= "http://localhost:8080/TestProject/usersProfilePic?user=" + u.getEmail() + "" %> alt="">
						<br>
						<div class="row1">  <%= u.getFirstName() %> <%= u.getLastName() %> </div>
						<br>
						<% if(!u.getExperiences().isEmpty()) { %>
							<div class="row2"> <%= u.getExperiences().get(0).getPosition() %> </div>
							<br>
							<div class="row3"> at <%= u.getExperiences().get(0).getCompany() %> </div>
						<% } else { %>
							<div class="row2"> Unemployed </div>
						<% } %>
					</div>
				<% } %>
			<% } %>			
		</div>
		
		<!-- form to submit when clicking on a user -->
		<form action="network" id="submit_form" method="get">
			<input type="hidden" name="action" value="visit_user">
			<input type="hidden" id="user_input" name="user" value="">   
		</form>
	</body>
</html>