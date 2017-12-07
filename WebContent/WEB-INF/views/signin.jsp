<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/signin_style.css">
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="login-wrap">
					<div class="login-html">
						<input id="tab-1" type="radio" name="tab" class="sign-in" checked>
						<label for="tab-1" class="tab">Sign In</label> <input id="tab-2"
							type="radio" name="tab" class="sign-up"> <label
							for="tab-2" class="tab">Sign Up</label>
						<div class="login-form">
							<div class="sign-in-htm">
								<div class="group">
									<label for="user" class="label">Username</label> <input
										id="user" name="username" type="text" class="input">
								</div>
								<div class="group">
									<label for="pass" class="label">Password</label> <input
										id="pass" name="password" type="password" class="input"
										data-type="password">
								</div>
								<!-- <div class="group">
									<input id="check" type="checkbox" class="check" checked>
									<label for="check"><span class="icon"></span> Keep me
										Signed in</label>
									</div> -->
								<div class="group">
									<input type="submit" class="button" value="Sign In">
								</div>
								<div class="hr"></div>
								<div class="group">
									<input type="submit" class="button facebook"
										value="Sign in with Facebook">
								</div>
								<!-- <div class="foot-lnk">
									<a href="#forgot">Forgot Password?</a>
								</div> -->
							</div>
							<div class="sign-up-htm">
								<div class="group">
									<label for="user" class="label">Username</label> <input
										id="user" name="username" type="text" class="input">
								</div>
								<div class="group">
									<label for="pass" class="label">Email Address</label> <input
										id="pass" name="email" type="email" class="input">
								</div>
								<div class="group">
									<label for="pass" class="label">Password</label> <input
										id="pass" name="password" type="password" class="input"
										data-type="password">
								</div>
								<div class="group">
									<label for="pass" class="label">Repeat Password</label> <input
										id="pass" type="password" class="input" data-type="password">
								</div>

								<div class="hr"></div>
								<div class="group">
									<input type="submit" class="button" value="Sign Up">
								</div>
								<div class="foot-lnk"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  Container close   -->
</body>
</html>
