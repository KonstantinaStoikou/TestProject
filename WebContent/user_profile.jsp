<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User u = (User)request.getAttribute("user"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Dots - <% out.write(u.getFirstName()+ " " + u.getLastName());%> </title>
		<link rel="stylesheet" type="text/css" href="styles/navbar.css">
		<link rel="stylesheet" type="text/css" href="styles/edit_profile.css">
		<link rel="stylesheet" type="text/css" href="styles/user_profile.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
	</head>
	<body>
	
		<%@ page import="java.util.List, model.User, model.Experience, model.Education, model.Skill" %>
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
                <div id="floating_elements">
               		<img id="profile_photo" src= <%= "http://localhost:8080/TestProject/usersProfilePic?user=" + u.getEmail() + "" %> alt="">  	
            	</div>
            	<br>
            	<% String currentUserEmail = (String)session.getAttribute("email"); %>
            	<% if((boolean)request.getAttribute("connected") == false && !currentUserEmail.equals(u.getEmail())) { %>	
            	<form action="connection" id="connect_form" method="post">
            		<input type="hidden" name="user" value="<%= u.getEmail() %>">
					<input type="submit" id="connect_btn" class="submit_button" value="Connect">
				</form>
				<% } %>
          		<span id="fullname"> <% out.write(u.getFirstName()+ " " + u.getLastName()); %> </span>     		
          		<br>
          		<span id="phone"><i class="fas fa-phone"></i> <% out.write(u.getPhone()); %> </span>
          		<br>
          		<span id="email"><i class="fas fa-envelope"></i> <% out.write(u.getEmail()); %> </span>
            	<br>
            	<% if(!u.getExperiences().isEmpty()) { %>
					<span id="currently"> <% out.write(u.getExperiences().get(0).getPosition() + " at " +
            			u.getExperiences().get(0).getCompany()); %> </span>
				<% } else { %>
					<span id="currently"> Unemployed </span>
				<% } %>
			
			<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
			
            <!-- EXPERIENCE -->
            <hr>
            <span class="area">Experience</span>
            
			<!-- create experience items -->
			<% if(u.getExperiences() != null && !u.getExperiences().isEmpty()) { %>
				<% for(Experience exp : u.getExperiences()) { %>
					<% if(exp.getPrivacy() == false) { %>
					    <div class="container area_container">
			                <span class="row1"><%= exp.getPosition() %></span>
			                <br><br>
			                <span class="row2"><%= exp.getCompany() %></span>
			                <br><br>
			                <span class="row3"><%= df.format(exp.getStartDate()) %></span> - <span class="row3"><%= df.format(exp.getEndDate()) %></span>
		            	</div>
	            	<% } %>
            	<% } %>
			<% } %>

            <!-- EDUCATION -->
            <hr>
            <span class="area">Education</span>

			<!-- create education items -->
			<% if(u.getEducations() != null && !u.getEducations().isEmpty()) { %>
				<% for(Education ed : u.getEducations()) { %>
					<% if(ed.getPrivacy() == false) { %>
					    <div class="container area_container">
			                <span class="row1"><%= ed.getInstitution() %></span>
			                <br><br>
			                <span class="row2"><%= ed.getLevel() %></span>
			                <br><br>
			                <span class="row3"><%= df.format(ed.getStartDate()) %></span> - <span class="row3"><%= df.format(ed.getEndDate()) %></span>
		            	</div>
	            	<% } %>
            	<% } %>
			<% } %>

            <!-- SKILLS -->
            <hr>
            <span class="area">Skills</span>

			<div class="flex_container">
			<% if(u.getSkills() != null && !u.getSkills().isEmpty()) { %>
				<% for(Skill sk : u.getSkills()) { %>
					<% if(sk.getPrivacy() == false) { %>
						<div class="container skill_container">
							<span class="row1"><%= sk.getName() %></span>
							<br><br>
							<span class="row3"><%= sk.getType() %></span>
						</div>
					<% } %>
            	<% } %>
			<% } %>
			</div>
        </div>
        
        
	</body>
</html>




