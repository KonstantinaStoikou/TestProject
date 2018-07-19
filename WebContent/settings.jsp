<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="styles/navbar.css">
		<link rel="stylesheet" type="text/css" href="styles/settings.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
	</head>
	<body>
	
		<% 
	 		if (session.getAttribute("email") == null) { 
				response.sendRedirect("/login.jsp"); 
			} 
		%>
	
		<!-- top navigation bar -->
        <div class="navbar">
            <img src="images/dots.png" />
            <a href="settings.jsp" class="active"><i class="fas fa-cogs"></i><br>Settings</a>
            <a href="profile.jsp"><i class="fas fa-user"></i><br>Profile</a>
            <a href="notifications.jsp"><i class="fas fa-bell"></i><br>Notifications</a>
            <a href="messages.jsp"><i class="fas fa-comments"></i><br>Messages</a>
            <a href="jobs.jsp"><i class="fas fa-newspaper"></i><br>Jobs</a>
            <a href="network.jsp"><i class="fas fa-users"></i><br>Network</a>
            <a href="home.jsp"><i class="fas fa-home"></i><br>Home</a>
        </div>
		
		<div class="container" id="first">
		    <form action="settings" method="post">
		        <label>Change email address :</label>
		        <input type="text" name="email" value="<%= session.getAttribute("email")%>">
		        
		        <!-- hidden field to know what request will be processed in servlet -->
		        <input type="hidden" name="action" value="email_change">
		        <button type="submit" name="email_change">Apply</button>
		    </form>
		</div>
		
		<div class="container">
		    <form action="settings" method="post">
		        <p>Change password </p>
		        <br>
		        <label>Enter current password : </label>
		        <input type="text" name="oldpass">
		        <br>
		        <br>
		        <label>Enter new password : </label>
		        <input type="text" name="newpass">
		        
				<!-- hidden field to know what request will be processed in servlet -->
		        <input type="hidden" name="action" value="password_change">
		        <button type="submit" name="password_change">Apply</button>
		    </form>
		</div>
	</body>
</html>