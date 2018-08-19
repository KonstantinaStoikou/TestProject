<%@page import="com.sun.org.glassfish.external.probe.provider.annotations.Probe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dots - Home</title>
		<link rel="icon" href="images/favicon.ico" type="image/x-icon">
		<link rel="stylesheet" type="text/css" href="styles/navbar.css">
		<link rel="stylesheet" type="text/css" href="styles/home.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
		<script src="scripts/home.js" async></script>
	</head>
	<body>
	
		<%@ page import="java.util.List, model.User, model.Post, model.Comment, java.util.Collections" %>
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
            <a href="network.jsp"><i class="fas fa-users"></i><br>Network</a>
            <a href="home.jsp" class="active"><i class="fas fa-home"></i><br>Home</a>
        </div>
        
        <%  
			// retrieve your list from the request, with casting 
			List<User> connections = (List<User>) session.getAttribute("connectionList");
		%> 
        
        <div class="left">
        	<img src="http://localhost:8080/TestProject/profilepic?" alt="">
        	<span id="current_name"> <%= session.getAttribute("first_name")%> <%= session.getAttribute("last_name")%> </span>
       		<button><a href="edit_profile.jsp">Go to your profile</a></button>
       		<br>
       		<% if(connections != null) { %>
       			<span id="conn_num"> <%= connections.size() %></span>
       		<% } else { %>
       			<span id="conn_num"> 0 </span>
    		<% } %>
			<br> 
       		<span id="conn_text">connections</span>
       		<button><a href="network.jsp">See your connections</a></button>
       	</div>
       	
		<!-- form to make a new post -->
        <div class="container">
        	<form action="makePost" method="post" enctype="multipart/form-data">
        		<textarea name="text" placeholder="Share an idea, a video, a photo or an audio file"></textarea>
        		<button type="button" onclick="chooseFile(this.textContent)"><i class="fas fa-camera"></i>Photo</button>
        		<button type="button" onclick="chooseFile(this.textContent)"><i class="fas fa-video"></i>Video</button>
        		<button type="button" onclick="chooseFile(this.textContent)"><i class="fas fa-microphone"></i>Audio</button>
        		<input type="submit" value="Post">
        	</form>
        </div>
        
        <%  
		// retrieve your list from the request, with casting 
		List<Post> posts = (List<Post>) session.getAttribute("posts");
		%> 
		
		<% if(posts != null && !posts.isEmpty()) { %>
			<%//reverse list to show most recent posts first
        	Collections.reverse(posts); %>
			<% for(Post p : posts) { %>
				<div class="container">
					<img class="pic" src=<%= "http://localhost:8080/TestProject/usersProfilePic?user=" + p.getUser().getEmail() + "" %> alt="">
        			<span class="name"> <% out.write(p.getUser().getFirstName()+ " " + p.getUser().getLastName());%> </span>
					<br>
					<% if (p.getText() != null) { %>
						<div class="context"><%= p.getText() %></div> 
						<br>
					<% } %>
					<% if (p.getMediaType().equals("image")) { %>	
						<img class="size" src=<%= "http://localhost:8080/TestProject/fileServlet?filename=" + p.getFilePath() + "" %> alt=""> 
						
					<% } else if (p.getMediaType().equals("video")) { %>
						<video class="size" controls>
							<source src=<%= "http://localhost:8080/TestProject/fileServlet?filename=" + p.getFilePath() + "" %> type="video/ogg">
						</video>
					<% } else if (p.getMediaType().equals("audio")){ %>
						<audio class="size" controls>
							<source src=<%= "http://localhost:8080/TestProject/fileServlet?filename=" + p.getFilePath() + "" %> type="audio/ogg">
						</audio>
					<% } %>
		        	<div class="likes">
		        		<hr>
		        		<form id="likeform" action="like" method="post">
		        			<input type="hidden" name="post" value="<%= p.getId() %>">
		        		</form>
		        		<button type="submit" form="likeform"><i class="fas fa-thumbs-up"></i><%= p.getLikeUsers().size() %> Likes </button>
		        		<button type="button" id="<%= "btn" + p.getId()%>" onclick="showComments(<%= p.getId() %>, this.id)"><i class="fas fa-comment-alt"></i><%= p.getComments().size() %> Comments </button>
		        	</div>
		        	
		        	<div class="comments" id="<%= p.getId() %>">
		        		<hr>
		        		<% if(p.getComments() != null && !p.getComments().isEmpty()) { %>
							<% for(Comment c : p.getComments()) { %>
				        		<div class="com">
				        			<span class="writer"> <%= c.getUser().getFirstName() %> <%= c.getUser().getLastName() %></span>
				        			<br>
				        			<span class="text"> <%= c.getText() %></span>
				        		</div>
			        		<% } %>
						<% } %>	
		        		<form action="postComment" method="post">
		        			<input class="text" type="text" name="comment" placeholder="Add a comment">
		        			<input type="hidden" name="post" value="<%= p.getId() %>">
		        			<input type="submit" value="post">
		        		</form>
		        	</div>
				</div>
			<% } %>
		<% } %>	
	</body>
</html>





