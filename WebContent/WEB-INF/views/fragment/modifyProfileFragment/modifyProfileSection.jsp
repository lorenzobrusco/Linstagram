<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
function verify_check(cb) {
	if (document.getElementById('checkbox').checked) 
		document.getElementById('check').value = "true";
	else
		document.getElementById('check').value = "false";

//	$('button').prop('disabled', false);
};
</script>

<div id="form-container">
	<div class="info">
		<form id="form_info">
			<div class="form-inline-profile">
				<label for="name">Nome</label>
				<div class="input-inline">
					<input type="text" class="form-control" id="name"
						value="${user.name}">
				</div>
			</div>
			<div class="form-inline-profile">
				<label for="name">Surname</label>
				<div class="input-inline">
					<input type="text" class="form-control" id="surname"
						value="${user.surname }">
				</div>
			</div>
			<div class="form-inline-profile">
				<label for="user-name">Username</label>
				<div class="input-inline">
					<input type="text" class="form-control" id="user-name"
						placeholder="${user.username }">
				</div>
			</div>
			<div class="form-inline-profile">
				<label for="email">Email</label>
				<div class="input-inline">
					<input type="email" class="form-control" id="email"
						placeholder="${user.email }">
				</div>
			</div>

			<div class="form-inline-profile">
				<label for="selection">Gender</label>
				<div class="input-inline">
					<select id="selection" class="form-control">
						<option value="-1" style="display:none;">${user.gender }</option>
						<option value="1">Male</option>
						<option value="2">Female</option>
						<option value="3">Unknown</option>
					</select>
				</div>
			</div>

			<div class="form-inline-profile">
				<label for="datepicker">Birthdate</label>
				<div class="input-inline">
					<input type="text" name="selDate" id="datepicker"
						class="form-control" placeholder=<fmt:formatDate value="${user.birthdate.time }" pattern="MM/dd/YYYY"/>>
				</div>
			</div>

			<div class="form-inline-profile">
				<label for="bio">Biografia</label>
				<div class="input-inline">
					<textarea class="form-control" id="bio">${user.biography }</textarea>
				</div>
			</div>

			<div class="form-inline-profile">
				<label for="private"></label>
				<div class="input-inline">
					<c:choose>
						<c:when test="${user.privateProfile == true}">
						<label class="private-switch" for="private" >Private</label>
							<label class="switch pull-right"> <input
								onClick="verify_check(this)" type="checkbox" id="checkbox"
								checked> <span class="slider round"></span> <input
								type="hidden" id="check" value="true" />
							</label>
						</c:when>
						<c:otherwise>
							<label class="private-switch" for="private" >Private</label>
							<label class="switch pull-right"> <input
								onClick="verify_check(this)" type="checkbox" id="checkbox">
								<span class="slider round"></span> <input type="hidden"
								id="check" value="false" />
							</label>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form>
	</div>
	<button type="button" class="btn confirm-btn pull-right" id="modify-btn">Submit</button>
</div>

