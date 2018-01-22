<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="login-form text-center reset-pass-container">
	<h3 id="recover-pass-title">Recover Your Password</h3>
	<form class="reset-pass">
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

						const errorNotyType = "error";
						const successNotyType = "success";

						function buildNoty(customtext, type) {
							var text = "";
							var time = 3000;
							if (type == "error") {
								text = '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Failed!</p>'
										+ customtext + ' !';
							} else if (type == "success") {
								text = '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Complete!</p>'
										+ customtext + ' !';
								time = 1500;
							}
							

							var notyconf = {
								text : text,
								theme : 'nest',
								type : type,
								layout : 'bottomLeft',
								timeout : time,
								progressBar : true
							}
							return new Noty(notyconf);
						}

						function returnToLogin() {
							location.reload();
						}

						function showLoading() {
							$("#loader").removeClass("hide");
							$(".login-wrap").addClass("hide");

						}

						function hideLoading() {
							$("#loader").addClass("hide");
							$(".login-wrap").removeClass("hide");
						}

						/*
						function showLoading(){
										$("#loader").removeClass("hide");
										$(".reset-pass").addClass("hide");
										
									}
									
									function hideLoading(){
										$("#loader").addClass("hide");
										$(".reset-pass").removeClass("hide");
									}
						 */

						$("#confirm-recovery-btn")
								.click(
										function(e) {
											e.preventDefault();
											var email = $("#email-recovery").val();
											var user = $("#user-recovery").val();
											if (email == "" || user == "") {
												buildNoty("Please, fill all fields", errorNotyType)
														.show();
												return;
											}

											if (validationMail(email)) {
												showLoading();
												$
														.ajax({
															url : "forgotPassword",
															method : 'post',
															data : {
																'username' : user,
																'email' : email
															},
															success : function(resp) {
																//$("#email-recovery").val("");
																//$("#user-recovery").val("");
																
																console.log(resp);
																if (resp == "USER_NOT_EXIST") {
																	buildNoty("Sorry, but the user not exist",errorNotyType).show();
																	hideLoading();
																} else if (resp == "OK") {
																	buildNoty(
																			"You will receive an email with the new credentials",
																			successNotyType).on('onClose',
																			function() {
																				returnToLogin();
																			}).show();
																}
															}
														});

												/*
													1.send data to server 
													2.search user with this user and mail
														2.1. notify error if not exist
													3.generate random pass 
													4.send email
													5.notify success
													6.return to login
												 */
												console.log(user + " - " + email);
											} else {
												buildNoty("Sorry, but the entered EMAIL is NOT VALID",
														errorNotyType).show();
											}

										});

					}); //close ready
</script>