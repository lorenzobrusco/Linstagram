<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="login-form text-center reset-pass-container">
	<form method="post" class="reset-pass">
		<h3 id="recover-pass-title">Recover Your Password</h3>
		<div class="group">
			<label for="user-recovery" class="label">Username</label> <input
				id="user-recovery" name="user-recovery" type="text" class="input"
				required>
		</div>
		<div class="group">
			<label for="email-recovery" class="label">Email Address</label> <input
				id="email-recovery" type="email" name="email-recovery" class="input"
				required>
		</div>
		<div class="hr"></div>
		<div class="group">
			<button class="button" id="confirm-recovery-btn">Confirm</button>
		</div>
		<div class="group">
			<a href="" class="button cancel-button" id="cancel-btn">Cancel</a>
		</div>
	</form>
</div>

<script>
	$(document)
			.ready(
					function() {

						var input = $('input');
						input.on("focusin", function() {
							$(".login-html").addClass("isFocused");
						});

						input.on("focusout", function() {
							$(".login-html").removeClass("isFocused");
						});

						//CONFIRM EVENT

						function validationMail(mail) {
							console.log(mail);
							var mail_pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
							if (mail == '')
								return false;
							else if (mail_pattern.test(mail) == false)
								return false;
							return true;
						}

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

						$("#confirm-recovery-btn").click(function(e) {
							e.preventDefault();
							var email = $("#email-recovery").val();
							var user = $("#user-recovery").val();
							console.log("email:" + email);
							if (validationMail(email)) {
								/*
								TODO: 
									1.send data to server 
									2.search user with this user and mail
										2.1. notify error if not exist
									3.generate random pass 
									4.send email
									5.notify success
									6.return to login
								*/
								console.log(user+" - "+email);
							} else {
								buildNoty("Sorry, but the entered EMAIL is NOT VALID");
							}

						});

					}); //close ready
</script>