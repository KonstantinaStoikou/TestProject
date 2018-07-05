<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dots</title>
        <link rel="stylesheet" type="text/css" href="styles/login.css">
	</head>
	<body>
        <div class="container center">
            <h1>Welcome to  <img src="images/dots.png"> : <br />the site to connect with professionals</h1>
            <form action="Login" method="post">
                <input type="email" name="email" placeholder="Email" autocomplete="off" autocorrect="off" spellcheck="false" required>
                <br>
                <br>
                <input type="password" name="password" placeholder="Password" required>
                <br>
                <br>
                <button id="signin" type="submit" name="signin">Sign In</button>
            </form>
            <h4>or if you don't have an account:</h4>
            <button id="signup" type="button" name="button">Sign Up</button>
        </div>
	</body>
</html>