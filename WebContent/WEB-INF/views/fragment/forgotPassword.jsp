<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="login-form text-center">
	<h3 id="recover-pass-title">Recover Your Password</h3>
	<form method="post" class="reset-pass">
		<div class="group">
			<label for="user" class="label">Username</label> <input id="user"
				name="username" type="text" class="input" required>
		</div>
		<div class="group">
			<label for="email" class="label">Email Address</label> <input
				id="email" type="email" name="email" class="input" required>
		</div>
		<div class="hr"></div>
		<div class="group">
			<button class="button" id="signup-btn">Confirm</button>
		</div>
		<div class="group">
			<button class="button" id="cancel-btn">Cancel</button>
		</div>
	</form>
</div>