<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dots - Login</title>
		<link rel="icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="styles/login.css">
	</head>
	<body>
        <div class="container center">
            <h1>Welcome to  <img src="images/dots.png"> : <br />the site to connect with professionals</h1>
            <form action="login" method="post">
				<%
					if (request.getAttribute("errorMessage") != null) {
						out.write("<span>");
						out.println(request.getAttribute("errorMessage"));
						out.write("</span>");
						out.write("<br>");
						out.write("<br>");
					}
				%>
                <input type="email" name="email" placeholder="Email" autocomplete="off" spellcheck="false" required>
                <br>
                <br>
                <input type="password" name="password" placeholder="Password" required>
                <br>
                <br>
                <button id="signin" type="submit" value=signin>Sign In</button>
            </form>
            <h4>or if you don't have an account:</h4>
            <a href="signup.jsp" id="signup">Sign Up</a>
        </div>
	</body>
</html>