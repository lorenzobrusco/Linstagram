<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modify_profile.css">
<script src="${pageContext.request.contextPath}/resources/js/modify_profile.js"></script>
<div id="form-container">
	<div class="info">
		<form>
			<div class="form-inline-profile">
				<label for="old_password">Old Password</label>
				<div class="input-inline">
					<input type="password" class="form-control" id="old_password">
				</div>
			</div>

			<br> <br>

			<div class="form-inline-profile">
				<label for="new_password">New Password</label>
				<div class="input-inline">
					<input type="password" class="form-control" id="new_password">
				</div>
			</div>
			<div class="form-inline-profile">
				<label for="repeat_password">Repeat Password</label>
				<div class="input-inline">
					<input type="password" class="form-control" id="repeat_password">
				</div>
			</div>
		</form>
	</div>
	<button type="button" disabled="disabled" class="btn confirm-btn pull-right"
	id="modifyPass-btn">Submit</button>
</div>

