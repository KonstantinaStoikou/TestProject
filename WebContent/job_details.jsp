<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dots - Jobs Nameeeeeeeeeee</title>
		<link rel="icon" href="images/favicon.ico" type="image/x-icon">
		<link rel="stylesheet" type="text/css" href="styles/navbar.css">
		<link rel="stylesheet" type="text/css" href="styles/jobs.css">
		<link rel="stylesheet" type="text/css" href="styles/job_details.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
	</head>
	<body>
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
            <a href="jobs.jsp"  class="active"><i class="fas fa-newspaper"></i><br>Jobs</a>
            <a href="network.jsp"><i class="fas fa-users"></i><br>Network</a>
            <a href="home.jsp"><i class="fas fa-home"></i><br>Home</a>
        </div>
        
        	<div class="left">
        		<button><a href="post_job.jsp">Post a new job</a></button>
        		<br>
         		<button>See applicants for<br>your posted jobs</button>
        	</div>
        	 
        	<div class="container">
        		<span id="position">Java Developer</span>
        		<br>
        		<span id="company">Microsoft</span>
        		<div id="description">This is a job description fogggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg</div>
        		<div id="skills">
					<span class="span_flex">ssdfgdddfddfsfsd</span>
					<span class="span_flex">C++</span>
					<span class="span_flex">Communicative</span>
					<span class="span_flex">Organized</span>
					<span class="span_flex">Matlab</span>
					<span class="span_flex">Machine Learning</span>
					<span class="span_flex">ssdfsfsdfdsfsdfsdssfsd</span>
					<span class="span_flex">ssdfsfsd</span>
				</div>
   				<form action="">
   					<input type="submit" value="Apply for position">
   				</form>
        	</div>
	</body>
</html>