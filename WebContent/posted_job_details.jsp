<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Job j = (Job)request.getAttribute("job"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dots - <%= j.getPosition()%></title>
		<link rel="icon" href="images/favicon.ico" type="image/x-icon">
		<link rel="stylesheet" type="text/css" href="styles/navbar.css">
		<link rel="stylesheet" type="text/css" href="styles/jobs.css">
		<link rel="stylesheet" type="text/css" href="styles/job_details.css">
		<link rel="stylesheet" type="text/css" href="styles/posted_job_details.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
		<script src="scripts/network.js" async></script>
	</head>
	<body>
		
		<%@ page import="java.util.List, model.User, model.Job, model.Job_Skill" %>
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
        		<button><a href="posted_jobs.jsp">See applicants for<br>your posted jobs</a></button>
       	</div>
       	 
       	<div class="container">
       		<span id="position"><%= j.getPosition() %></span>
        		<br>
        		<span id="company"><%= j.getCompany() %></span>
        		<div id="description"><%= j.getDescription() %></div>
        		<div id="skills">
        			<% if(j.getJobSkills() != null && !j.getJobSkills().isEmpty()) { %>
	        			<% for(Job_Skill js : j.getJobSkills()) { %>
						<span class="span_flex"> <%= js.getName() %></span>
						<% } %>
					<% } %>
				</div>
       	</div>
       	
       	<div class="container">
			<span class="title">Users that have applied for this position :</span>
			<br>
			<% if(j.getAppliedUsers() != null && !j.getAppliedUsers().isEmpty()) { %>
				<% for(User u : j.getAppliedUsers()) { %>
					<div class="container user_container" onclick="submitForm(<%= String.valueOf(u.getId()) %>)">
						<img src= <%= "" + request.getContextPath() + "/usersProfilePic?user=" + u.getEmail() + "" %> alt="">
						<br>
						<div class="row1"> <%= u.getFirstName() %> <%= u.getLastName() %> </div>
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