<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Listagram</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/signin_style.css">

<!-- NOTY -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/noty/noty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/noty/themes/nest.css">
<script
	src="${pageContext.request.contextPath}/resources/js/lib/noty.min.js"></script>
</head>

<body>
	<div class="row" id="container">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<div id="logo-container">
				<span id="logo"></span>
				<h1 id="logo-text">linstagram</h1>
			</div>
			<div class="login-wrap">
				<div class="login-html">
					<input id="tab-1" type="radio" name="tab" class="sign-in" checked>
					<label for="tab-1" class="tab">Sign In</label> <input id="tab-2"
						type="radio" name="tab" class="sign-up"> <label
						for="tab-2" class="tab">Sign Up</label>

					<div class="login-form">
						<form class="sign-in-htm" action="signInAttempt" method="post">
							<div class="group">
								<label for="user" class="label">Username</label> <input
									id="user-singin" name="username" type="text" class="input"
									required>
							</div>
							<div class="group">
								<label for="pass" class="label">Password</label> <input
									id="pass" name="password" type="password" class="input"
									data-type="password" required>
							</div>
							<!-- <div class="group">
<input id="check" type="checkbox" class="check" checked>
<label for="check"><span class="icon"></span> Keep me
Signed in</label>
</div> -->
							<div class="group">
								<input type="submit" class="button" value="Sign In">
							</div>
							<div id="forgot-pass">
								<a id="forgot-pass-btn" role="button">Forgot Password?</a>
							</div>
							<div class="hr"></div>
							<div class="group">
								<input class="button facebook" value="Sign in with Facebook">
							</div>
						</form>

						<form method="post" class="sign-up-htm">
							<div class="group">
								<label for="user" class="label">Username</label> <input
									id="user" name="username" type="text" class="input" required>
							</div>
							<div class="group">
								<label for="email" class="label">Email Address</label> <input
									id="email" type="email" name="email" class="input" required>
							</div>
							<div class="group">
								<label for="password-field" class="label">Password</label> <input
									name="password" type="password" class="input"
									data-type="password" id="password-field" required>
							</div>
							<div class="group">
								<label for="password-repeat" class="label">Repeat
									Password</label> <input type="password" class="input"
									data-type="password" id="password-repeat" required>
							</div>
							<div class="hr"></div>
							<div class="group">
								<button class="button" id="signup-btn">Sign Up</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  Container close   -->
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						const SUCCESS_SING_UP = "SUCCESS_SIGN_UP";
						const EMAIL_ALREADY_USED = "ERROR_EMAIL_ALREADY_USED";
						const USERNAME_ALREADY_USED = "ERROR_USERNAME_ALREADY_USED";

						function validationMail(mail) {
							console.log(mail);
							var mail_pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
							if (mail == '')
								return false;
							else if (mail_pattern.test(mail) == false)
								return false;
							return true;
						}

						var input = $('input');
						input.on("focusin", function() {
							$(".login-html").addClass("isFocused");
						});

						input.on("focusout", function() {
							$(".login-html").removeClass("isFocused");
						});

						function buildNoty(customtext) {
							var notyconf = {
								text : '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Failed!</p>'
										+ customtext + ' !',
								theme : 'nest',
								type : 'error',
								layout : 'bottomLeft',
								timeout : 4000,
								progressBar : true
							}
							new Noty(notyconf).show();
						}
						
						$("#forgot-pass-btn").click(function(){
							$.ajax({
								url: "forgotPassword",
								method : 'post',
								success:function(resp){
									$(".login-html").empty();
									$(".login-html").append(resp);
								} //close success
							});
						
						});

						$("#signup-btn")
								.click(
										function(e) {
											e.preventDefault();
											var user = $("#user").val();
											//check if pass and pass_repeat are equal
											if (user.length != 0) {
												var pass_field = $("#password-field");
												var pass_repeat = $("#password-repeat");
												if (pass_field.val().length < 6) {
													buildNoty("Sorry, but the PASSWORD must be of at least 6 character");
												} else {

													if (pass_field.val() != pass_repeat.val()) {
														buildNoty("Sorry, but the PASSWORD do not match");
														pass_field.addClass("input-with-error");
														pass_repeat.addClass("input-with-error");
														pass_field.focus();
													} else {
														pass_field.removeClass("input-with-error");
														pass_repeat.removeClass("input-with-error");

														var email = $("#email").val();
														var pass = pass_field.val();

														if (validationMail(email)) {
															$.ajax({
																		url : "signUpAttempt",
																		method : 'post',
																		data : {
																			email : email,
																			username : user,
																			password : pass
																		},
																		success : function(result) {
																			if (result == EMAIL_ALREADY_USED) {
																				buildNoty("Sorry, but the entered EMAIL is ALREADY USED");
																			} else if (result == USERNAME_ALREADY_USED) {
																				buildNoty("Sorry, but the entered USERNAME is ALREADY USED");
																			} else if (result == SUCCESS_SING_UP) {
																				//fill sign-in form with the data of the just registered user and submit 
																				$("#user-singin").val(user);
																				$("#pass").val(pass);
																				$(".sign-in-htm").submit();
																			}
																		} //close success
																	}); //close ajax
														} else {//close check email if
															buildNoty("Sorry, but the entered EMAIL is NOT VALID");
														}

													}

												}
											} else {
												buildNoty("Please, insert an USERNAME");
											}
										}); //close listener
					});
</script>

</html>
