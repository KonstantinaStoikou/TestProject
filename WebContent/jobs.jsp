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
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
		<script src="scripts/post_job.js" async></script>
	</head>
	<body>
	
		<%@ page import="java.util.List, model.Job" %>
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
        	 
        	
        	<%  
			// retrieve your list from the request, with casting 
			List<Job> recommendedJobs = (List<Job>) session.getAttribute("recommendedJobs");
			%> 
        	 
        	<div class="container">
        		<span class="title">Recommended jobs for you :</span>
        		<% if(recommendedJobs != null && !recommendedJobs.isEmpty()) { %>
					<% for(Job j : recommendedJobs) { %>
						<div class="container job_container" onclick="submitForm(<%= String.valueOf(j.getId()) %>)">
							<span class="position"> <%= j.getPosition() %></span>
		        			<br><br>
		        			<span class="company"> <%= j.getCompany() %></span>
		        			<br><br>
		        			<span class="description"> <%= j.getDescription() %></span>
		        		</div>
					<% } %>
				<% } %>		
        	</div>
        	
        	<!-- form to submit when clicking on a user -->
			<form action="postJob" id="submit_form" method="get">
				<input type="hidden" name="jsp" value="jobs">
				<input type="hidden" id="job_input" name="job" value="">   
			</form>
	</body>
</html>