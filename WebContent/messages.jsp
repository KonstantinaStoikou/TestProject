<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
			<meta charset="UTF-8">
			<title>Dots - Messages</title>
			<link rel="icon" href="images/favicon.ico" type="image/x-icon">
			<link rel="stylesheet" type="text/css" href="styles/navbar.css">
			<link rel="stylesheet" type="text/css" href="styles/messages.css">
			<link rel="stylesheet" type="text/css" href="styles/scrollbar.css">
			<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
	</head>
	<body>
		<%@ page import="java.util.List, model.User" %>
		<% 
	 		if (session.getAttribute("email") == null) { 
				response.sendRedirect(request.getContextPath() + "/login.jsp"); 
			} 
		%>
		
		<% User u = (User)request.getAttribute("messagedUser"); %>
		
		<!-- top navigation bar -->
        <div class="navbar">
            <img src="images/dots.png" />
            <a href="settings.jsp"><i class="fas fa-cogs"></i><br>Settings</a>
            <a href="edit_profile.jsp"><i class="fas fa-user"></i><br>Profile</a>
            <a href="notifications.jsp"><i class="fas fa-bell"></i><br>Notifications</a>
            <a href="messages.jsp" class="active"><i class="fas fa-comments"></i><br>Messages</a>
            <a href="jobs.jsp"><i class="fas fa-newspaper"></i><br>Jobs</a>
            <a href="network.jsp"><i class="fas fa-users"></i><br>Network</a>
            <a href="home.jsp"><i class="fas fa-home"></i><br>Home</a>
        </div>
        
		<!-- conversations that have started with users -->
        <div class="container left_container">
        	<span id="title">Your conversations :</span>
        	<div id="list">
				<!--  i need an if condition to check if active or not -->
        		<div class="container conv_container conv_active">
	        		<img src= "./images/handshake.jpg" alt="">
        			<span class="name"> Giorgos Georgiou </span>
	        	</div>
	        	<div class="container conv_container">
	        		<img src= "./images/handshake.jpg" alt="">
        			<span class="name"> Giorgos Gedfrsffffsfgiou </span>
	        	</div>
	        	<div class="container conv_container">
	        		<img src= "./images/handshake.jpg" alt="">
        			<span class="name"> Giorgos Georgiou </span>
	        	</div>
	        	<div class="container conv_container">
	        		<img src= "./images/handshake.jpg" alt="">
        			<span class="name"> Giorgos Geosfsdfsddsrgiou </span>
	        	</div>
	        	<div class="container conv_container">
	        		<img src= "./images/handshake.jpg" alt="">
        			<span class="name"> Giorgos Georgiou </span>
	        	</div>
	        	<div class="container conv_container">
	        		<img src= "./images/handshake.jpg" alt="">
        			<span class="name"> Giorgos Georgiou </span>
	        	</div>
			</div>
        </div>
        
		<!-- conversation currently open -->
        <div class="container">
        	<div id="open_info">
        		<img src= <%= "http://localhost:8080/TestProject/usersProfilePic?user=" + u.getEmail() + "" %> alt="">
        		<span id="open_name"> <% out.write(u.getFirstName()+ " " + u.getLastName()); %> </span>
        	</div>
        	<div id="conversation">
        		<div class="message">
        			<div class="me">
	        			<span class="person">Me</span>
	        			<br>
	        			<div class="text">
	        				dfgdfgdfgdrgdfsfsfdfsdfsdffgdfgdfgfdgdfgfgdfsdfdgdfg
	        			</div>
        			</div>
        		</div>
        		
        		<div class="message">
	        		<div class="friend">
	        			<span class="person">Georgios</span>
	        			<br>
	        			<div class="text">
	        				dfgfdgdfgfgdfsdfdgdfg
	        			</div>
	        		</div>
        		</div>
        		
        		
        		
        		
        	</div>
        	<form action="">
	    		<div id="wrapper">
	    			<input type="text" placeholder="Write a message">
        			<input type="submit" value="Send">
	    		</div>
        	</form>
        </div>
	</body>
</html>