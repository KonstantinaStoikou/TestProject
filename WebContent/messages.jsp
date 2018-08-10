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
			<script src="scripts/network.js" async></script>
	</head>
	<body>
		<%@ page import="java.util.List, model.User, model.Message" %>
		<% 
	 		if (session.getAttribute("email") == null) { 
				response.sendRedirect(request.getContextPath() + "/login.jsp"); 
			} 
		%>
		
		<% User u = (User)request.getAttribute("messagedUser"); %>
		<% boolean noConversations = false; %>
		<% if (u == null) { 
			 u = (User)session.getAttribute("lastConvUser"); 
			 if (u == null) {
				 noConversations = true;
			 }
		 } %>
		
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
        
        
        <%   
			List<User> conversations = (List<User>) session.getAttribute("conversations");
		%>
		<% String classes = "container conv_container"; %>
		<!-- conversations that have started with users -->
        <div class="container left_container">
        	<span class="title">Your conversations :</span>
        	<div id="list">
        		<% if(conversations != null && !conversations.isEmpty()) { %>
					<% for(User convU : conversations) { %>
						<!-- check if this is the active user (opened in conversation) -->
						<% if (convU.getId() == u.getId()) {classes = "container conv_container conv_active";} %>
						<div class="<%= classes %>" onclick="submitForm(<%= String.valueOf(convU.getId()) %>)">
			        		<img src= "<%= "http://localhost:8080/TestProject/usersProfilePic?user=" + convU.getEmail() + "" %>" alt="">
		        			<span class="name">  <%= convU.getFirstName() %> <%= convU.getLastName() %> </span>
			        	</div>
			        	<% classes = "container conv_container"; %>
		        	<% } %>
	        	<% } %>
	        	
			</div>
        </div>
        
        <%   
			int currentId = (Integer)session.getAttribute("id");
		%>
		<!-- conversation currently open -->
        <div class="container">
        	<% if (noConversations == false) { %>
	        	<div id="open_info">
	        		<img src= <%= "http://localhost:8080/TestProject/usersProfilePic?user=" + u.getEmail() + "" %> alt="">
	        		<span id="open_name"> <% out.write(u.getFirstName()+ " " + u.getLastName()); %> </span>
	        	</div>
	        	<div id="conversation">
	        		<% if(u.getConversationMessages(currentId) != null && !u.getConversationMessages(currentId).isEmpty()) { %>
						<% for(Message msg : u.getConversationMessages(currentId)) { %>
							<div class="message">
								<% if (msg.getSender().getId() == currentId) { %>
									<div class="me">
										<span class="person">Me</span>
								<% } else { %>
									<div class="friend">
										<span class="person"><%= u.getFirstName() %></span>
								<% } %>
										<br>
					        			<div class="text"><%= msg.getText() %></div>
		        					</div>
							</div>
			        	<% } %>
		        	<% } %>
	        		
	        		
	        	</div>
	        	<form action="messages" method="post">
		    		<div id="wrapper">
		    			<input type="hidden" name="receiver" value = "<%= u.getId()  %>">
		    			<input type="text" name="text" placeholder="Write a message" autocomplete="off" spellcheck="false" required>
	        			<input type="submit" value="Send">
		    		</div>
	        	</form>
	        <% } else { %>
	        	<span class="title"> You don't have any conversations</span>
	        <% } %>
        </div>
        
        <!-- form to submit when clicking on a user from list of conversations -->
		<form action="messages" id="submit_form" method="get">
			<input type="hidden" id="user_input" name="user" value="">
		</form>
	</body>
</html>