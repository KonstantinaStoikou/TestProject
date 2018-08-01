<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Edit my Profile</title>
        <link rel="stylesheet" type="text/css" href="styles/navbar.css">
        <link rel="stylesheet" type="text/css" href="styles/edit_profile.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
        <script src="scripts/edit_profile.js" async></script>
    </head>
    <body>

		<%@ page import="java.util.List, model.Experience" %>
		<% 
	 		if (session.getAttribute("email") == null) { 
				response.sendRedirect(request.getContextPath() + "/login.jsp"); 
			} 
		%>

        <!-- top navigation bar -->
        <div class="navbar">
            <img src="images/dots.png" />
            <a href="settings.jsp"><i class="fas fa-cogs"></i><br>Settings</a>
            <a href="profile.jsp" class="active"><i class="fas fa-user"></i><br>Profile</a>
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
                    <input type="text" name="position" placeholder="Position" autocomplete="off" spellcheck="false">
                    <br>
                    <input type="text" name="company" placeholder="Company" autocomplete="off" spellcheck="false">
                    <br><br>
                    <label>Start Date</label><br>
                    <input type="date" name="start_date">
                    <br><br>
                    <label>End Date</label><br>
                    <input type="date" name="end_date">
                    <br><br>
                    <input type="radio" name="privacy" value="private"> Private<br>
                    <input type="radio" name="privacy" value="public" checked> Public<br>
                    
                    <input type="hidden" name="action" value="experience_info">
                    <button type="submit" class="submit_button">Add</button>
                </form>
                
            </div>
            
			<%  
			// retrieve your list from the request, with casting 
			List<Experience> list = (List<Experience>) session.getAttribute("expList");
			%>
			
			<% for(Experience exp : list) { %>
			    <div class="container area_container">
	                <form action="editProfile" method="post">
	                    <button type="submit"><i class="fas fa-trash-alt"></i><span class="hoverable_text">Delete</span></button>
	                </form>
	                <span class="row1"><%= exp.getPosition() %></span>
	                <br><br>
	                <form action="" method="post">
	                    <button type="submit" class="lock"><i class="fas fa-lock"></i><i class="fas fa-unlock"></i></button>
	                </form>
	                <span class="row2"><%= exp.getCompany() %></span>
	                <br><br>
	                
	                <span class="row3"><%= df.format(exp.getStartDate()) %></span> - <span class="row3"><%= df.format(exp.getEndDate()) %></span>
            	</div>
			<% } %>

            <div class="container area_container">
                <form action="editProfile" method="post">
                    <button type="submit"><i class="fas fa-trash-alt"></i><span class="hoverable_text">Delete</span></button>
                </form>
                <span class="row1">Marketing Assistant</span>
                <br><br>
                <form action="" method="post">
                    <button type="submit" class="lock"><i class="fas fa-lock"></i><i class="fas fa-unlock"></i></button>
                </form>
                <span class="row2">Microsoft</span>
                <br><br>
                <span class="row3">2006-2007</span>
            </div>

            <!-- EDUCATION -->
            <hr>
            <span class="area">Education</span><button id="ed"><i class="fas fa-plus-circle"></i></button>

            <div class="container area_container" id="add_ed">
				<!-- hidden form to add new education -->
                <form class="new_info" action="editProfile" method="post">
                    <input type="text" name="institution" placeholder="Institution" autocomplete="off" spellcheck="false">
                    <br>
                    <input type="text" name="level" placeholder="Degree Level" autocomplete="off" spellcheck="false">
                    <br>
                    <input type="text" name="year" placeholder="Years" autocomplete="off" spellcheck="false">
                    <br><br>
                    <input type="radio" name="privacy" value="private"> Private<br>
                    <input type="radio" name="privacy" value="public" checked> Public<br>
                    
                    <input type="hidden" name="action" value="education_info">
                    <button type="submit" class="submit_button">Add</button>
                </form>
            </div>

            <div class="container area_container">
                <form action="editProfile" method="post">
                    <button type="submit"><i class="fas fa-trash-alt"></i><span class="hoverable_text">Delete</span></button>
                </form>
                <span class="row1">Florida University of Sciences</span>
                <br><br>
                <form action="" method="post">
                    <button type="submit" class="lock"><i class="fas fa-lock"></i><i class="fas fa-unlock"></i></button>
                </form>
                <span class="row2">Bachelor's</span>
                <br><br>
                <span class="row3">2001-2005</span>
            </div>

            <!-- SKILLS -->
            <hr>
            <span class="area">Skills</span><button id="sk"><i class="fas fa-plus-circle"></i></button>

            <div class="container skill_container" id="add_sk">
				<!-- hidden form to add new skill -->
                <form class="new_info" action="editProfile" method="post">
                    <input type="text" name="name" placeholder="Skill" autocomplete="off" spellcheck="false">
                    <br>
                    <input type="text" name="type" placeholder="Type" autocomplete="off" spellcheck="false">
                    <br><br>
                    <input type="radio" name="privacy" value="private"> Private<br>
                    <input type="radio" name="privacy" value="public" checked> Public<br>
                    
                    <input type="hidden" name="action" value="skill_info">
                    <button type="submit" class="submit_button">Add</button>
                </form>
            </div>

            <div class="flex_container">
                <div class="container skill_container">
                    <form action="ditProfile" method="post">
                        <button type="submit"><i class="fas fa-trash-alt"></i></button>
                    </form>
                    <span class="row1">Matlab</span>
                    <br><br><br>
                    <form action="" method="post">
                        <button type="submit" class="lock"><i class="fas fa-lock"></i><i class="fas fa-unlock"></i></button>
                    </form>
                    <span class="row3">Programming</span>
                </div>
            </div>
        </div>
    </body>
</html>
