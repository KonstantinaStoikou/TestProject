<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Dots - Edit my Profile</title>
        <link rel="stylesheet" type="text/css" href="styles/navbar.css">
        <link rel="stylesheet" type="text/css" href="styles/edit_profile.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
        <script src="scripts/edit_profile.js" async></script>
    </head>
    <body>

		<%@ page import="java.util.List, model.Experience, model.Education, model.Skill" %>
		<% 
	 		if (session.getAttribute("email") == null) { 
				response.sendRedirect(request.getContextPath() + "/login.jsp"); 
			} 
		%>

        <!-- top navigation bar -->
        <div class="navbar">
            <img src="images/dots.png" />
            <a href="settings.jsp"><i class="fas fa-cogs"></i><br>Settings</a>
            <a href="edit_profile.jsp" class="active"><i class="fas fa-user"></i><br>Profile</a>
            <a href="notifications.jsp"><i class="fas fa-bell"></i><br>Notifications</a>
            <a href="messages.jsp"><i class="fas fa-comments"></i><br>Messages</a>
            <a href="jobs.jsp"><i class="fas fa-newspaper"></i><br>Jobs</a>
            <a href="network.jsp"><i class="fas fa-users"></i><br>Network</a>
            <a href="home.jsp"><i class="fas fa-home"></i><br>Home</a>
        </div>

        <div class="container">
        
        	<!-- GENERAL INFORMATION -->
            <form action="editProfile" method="post" enctype="multipart/form-data">
                <div id="floating_elements">
                    <input type='file' id='getval' name="photo" accept="image/*">
                    <br>
               		<img id="profile_photo" src= "http://localhost:8080/TestProject/profilepic?" alt="">
                	
            	</div>
            	
	            <div id="forms">
	            <br><br>
	            	<label>First Name</label><br>
	                <input type="text" name="first_name" value="<%= session.getAttribute("first_name")%>" autocomplete="off" spellcheck="false">
	             	<br>
	                <label>Last Name</label><br>
	                <input type="text" name="last_name" value="<%= session.getAttribute("last_name")%>" autocomplete="off" spellcheck="false">
	          		<br>
	                <label>Phone</label><br>
	                <input type="number" name="phone" value="<%= session.getAttribute("phone")%>" autocomplete="off" spellcheck="false">
	           		<br><br>
	           		
	           		<!-- hidden field to know what request will be processed in servlet -->
		        	<input type="hidden" name="action" value="general_info">
	           		<button type="submit" class="submit_button">Apply</button>
	            </div>
			</form>
			
			<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
			
            <!-- EXPERIENCE -->
            <hr>
            <span class="area">Experience</span><button id="xp"><i class="fas fa-plus-circle"></i></button>

            <div class="container area_container" id="add_xp">
				<!-- hidden form to add new experience -->
                <form class="new_info" action="editProfile" method="post">
                    <input type="text" name="position" placeholder="Position" autocomplete="off" spellcheck="false" required>
                    <br>
                    <input type="text" name="company" placeholder="Company" autocomplete="off" spellcheck="false" required>
                    <br><br>
                    <label>Start Date</label><br>
                    <input type="date" name="start_date" required>
                    <br><br>
                    <label>End Date</label><br>
                    <input type="date" name="end_date" required>
                    <br><br>
                    <input type="radio" name="privacy" value="private"> Private<br>
                    <input type="radio" name="privacy" value="public" checked> Public<br>
                    
                    <input type="hidden" name="action" value="experience_info">
                    <button type="submit" class="submit_button">Add</button>
                </form>
                
            </div>
            
			<%  
			// retrieve your list from the request, with casting 
			List<Experience> expList = (List<Experience>) session.getAttribute("expList");
			%>
			<!-- create experience items -->
			<% if(expList != null && !expList.isEmpty()) { %>
				<% for(Experience exp : expList) { %>
				    <div class="container area_container">
						<!-- delete button -->
		                <form action="editProfile" method="post">
		                	<input type="hidden" name="delete_exp" value="<%= exp.getId().getId()%>">
		                    <button type="submit"><i class="fas fa-trash-alt"></i><span class="hoverable_text">Delete</span></button>
		                </form>
		                <span class="row1"><%= exp.getPosition() %></span>
		                <br><br>
		                <!-- lock/unlock button -->
		                <form action="" method="post">
		                	<input type="hidden" name="privacy_exp" value="<%= exp.getId().getId()%>">
		                <% if(exp.getPrivacy() == false) { %>
							<button type="submit" class="lock"><i class="fas fa-lock"></i><i class="fas fa-unlock"></i></button>
						<% } else { %>
							<button type="submit" class="lock"><i class="fas fa-unlock"></i><i class="fas fa-lock"></i></button>
						<% } %>
		                </form>
		                <span class="row2"><%= exp.getCompany() %></span>
		                <br><br>
		                
		                <span class="row3"><%= df.format(exp.getStartDate()) %></span> - <span class="row3"><%= df.format(exp.getEndDate()) %></span>
	            	</div>
            	<% } %>
			<% } %>

            <!-- EDUCATION -->
            <hr>
            <span class="area">Education</span><button id="ed"><i class="fas fa-plus-circle"></i></button>

            <div class="container area_container" id="add_ed">
				<!-- hidden form to add new education -->
                <form class="new_info" action="editProfile" method="post">
                    <input type="text" name="institution" placeholder="Institution" autocomplete="off" spellcheck="false" required>
                    <br>
                    <input type="text" name="level" placeholder="Degree Level" autocomplete="off" spellcheck="false" required>
                    <br><br>
                    <label>Start Date</label><br>
                    <input type="date" name="start_date" required>
                    <br><br>
                    <label>End Date</label><br>
                    <input type="date" name="end_date" required>
                    <br><br>
                    <input type="radio" name="privacy" value="private"> Private<br>
                    <input type="radio" name="privacy" value="public" checked> Public<br>
                    
                    <input type="hidden" name="action" value="education_info">
                    <button type="submit" class="submit_button">Add</button>
                </form>
            </div>

			<%  
			// retrieve your list from the request, with casting 
			List<Education> edList = (List<Education>) session.getAttribute("edList");
			%>
			<!-- create education items -->
			<% if(edList != null && !edList.isEmpty()) { %>
				<% for(Education ed : edList) { %>
				    <div class="container area_container">
						<!-- delete button -->
		                <form action="editProfile" method="post">
		                	<input type="hidden" name="delete_ed" value="<%= ed.getId().getId()%>">
		                    <button type="submit"><i class="fas fa-trash-alt"></i><span class="hoverable_text">Delete</span></button>
		                </form>
		                <span class="row1"><%= ed.getInstitution() %></span>
		                <br><br>
		                <!-- lock/unlock button -->
		                <form action="" method="post">
		                	<input type="hidden" name="privacy_ed" value="<%= ed.getId().getId()%>">
		                <% if(ed.getPrivacy() == false) { %>
							<button type="submit" class="lock"><i class="fas fa-lock"></i><i class="fas fa-unlock"></i></button>
						<% } else { %>
							<button type="submit" class="lock"><i class="fas fa-unlock"></i><i class="fas fa-lock"></i></button>
						<% } %>
		                </form>
		                <span class="row2"><%= ed.getLevel() %></span>
		                <br><br>
		                
		                <span class="row3"><%= df.format(ed.getStartDate()) %></span> - <span class="row3"><%= df.format(ed.getEndDate()) %></span>
	            	</div>
            	<% } %>
			<% } %>

            <!-- SKILLS -->
            <hr>
            <span class="area">Skills</span><button id="sk"><i class="fas fa-plus-circle"></i></button>

            <div class="container skill_container" id="add_sk">
				<!-- hidden form to add new skill -->
                <form class="new_info" action="editProfile" method="post">
                    <input type="text" name="name" placeholder="Skill" autocomplete="off" spellcheck="false" required>
                    <br>
                    <input type="text" name="type" placeholder="Type" autocomplete="off" spellcheck="false" required>
                    <br><br>
                    <input type="radio" name="privacy" value="private"> Private<br>
                    <input type="radio" name="privacy" value="public" checked> Public<br>
                    
                    <input type="hidden" name="action" value="skill_info">
                    <button type="submit" class="submit_button">Add</button>
                </form>
            </div>

			<%  
			// retrieve your list from the request, with casting 
			List<Skill> skList = (List<Skill>) session.getAttribute("skList");
			%>
			<!-- create skill items -->
			<div class="flex_container">
			<% if(skList != null && !skList.isEmpty()) { %>
				<% for(Skill sk : skList) { %>
					<div class="container skill_container">
						<!-- delete button -->
						<form action="editProfile" method="post">
							<input type="hidden" name="delete_sk" value="<%= sk.getId().getId()%>">
							<button type="submit"><i class="fas fa-trash-alt"></i></button>
						</form>
						<span class="row1"><%= sk.getName() %></span>
						<br><br>
						<form action="editProfile" method="post">
							<input type="hidden" name="privacy_sk" value="<%= sk.getId().getId()%>">
						<% if(sk.getPrivacy() == false) { %>
							<button type="submit" class="lock"><i class="fas fa-lock"></i><i class="fas fa-unlock"></i></button>
						<% } else { %>
							<button type="submit" class="lock"><i class="fas fa-unlock"></i><i class="fas fa-lock"></i></button>
						<% } %>
						</form>
						<span class="row3"><%= sk.getType() %></span>
					</div>
            	<% } %>
			<% } %>
			</div>
			
        </div>
    </body>
</html>
