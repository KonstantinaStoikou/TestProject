<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dots - Jobs</title>
		<link rel="icon" href="images/favicon.ico" type="image/x-icon">
		<link rel="stylesheet" type="text/css" href="styles/navbar.css">
		<link rel="stylesheet" type="text/css" href="styles/jobs.css">
		<link rel="stylesheet" type="text/css" href="styles/edit_profile.css">
		<link rel="stylesheet" type="text/css" href="styles/scrollbar.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
		<script src="scripts/post_job.js" async></script>
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
         		<button>See applicants to<br>your posted jobs</button>
        	</div>
        	 
        	<div class="container">
        		<span class="title">Post a new job :</span>
        		<form class="new_info" action="" method="post">
                    <input type="text" name="position" placeholder="Position" autocomplete="off" spellcheck="false" required>
                    <br>
                    <input type="text" name="company" placeholder="Company" autocomplete="off" spellcheck="false" required>
                    <br>
                    <textarea placeholder="Job description" required></textarea >
                    <span class="title1">Skills required :</span>
                    <div id="skills_flex"></div>
                </form>
                
   				<span class="title1">Add skill required for this job</span><button id="add_skill"><i class="fas fa-plus-circle"></i></button>
                <br>
                <div id="skill_div">
                	<input type="text" id="added_skill" placeholder="Skill" autocomplete="off" spellcheck="false">
                	<button id="skill_btn" onclick="addSkill()">Add</button>
                </div>
                <input type="submit" form="new_info" value="Post" />
        	</div>
	</body>
</html>