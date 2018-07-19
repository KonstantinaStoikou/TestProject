<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <title>Dots - Sign Up</title>
	    <link rel="stylesheet" type="text/css" href="styles/signup.css">
	    <link rel="stylesheet" type="text/css" href="styles/navbar.css">
	    <script src="scripts/signup.js" defer></script>
	</head>	
	<body>
		<div class="navbar">
            <img src="images/dots.png" />
            <a href="login.jsp">Login</a>
        </div>
        <div class="container">
            <p>Thanks for choosing Dots. <br> Please fill in your details to create an account :</p>

            <form action="signup" method="post" enctype="multipart/form-data">
            	<%
					if (request.getAttribute("errorMessage") != null) {
						out.write("<span>");
						out.println(request.getAttribute("errorMessage"));
						out.write("</span>");
						out.write("<br>");
						out.write("<br>");
					}
				%>
                <label>First Name :</label>
                <input type="text" name="first_name" autocomplete="off" spellcheck="false" required>
                <label>Last Name :</label>
                <input type="text" name="last_name" autocomplete="off" spellcheck="false" required>
                <label>Password :</label>
                <input type="password" name="password" autocomplete="off" spellcheck="false" required>
                <label>Confirm Password :</label>
                <input type="password" name="password_conf" autocomplete="off" spellcheck="false" required>
                <label>Email :</label>
                <input type="email" name="email" autocomplete="off" spellcheck="false" required">
                <label>Phone Number :</label>
                <input type="number" name="phone" min="0" autocomplete="off" spellcheck="false" required>
                <label>Profile Photo :</label>
                <input type='file' id='getval' name="photo" accept="image/*" required><br>
                <div id='clock'></div>
                <br>
                <button type="submit" name="signup">Create Account</button>
            </form>
        </div>
	</body>
</html>