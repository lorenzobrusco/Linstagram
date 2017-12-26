<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="form-container">
	<div class="info">
		<form id="form_info">
			<div class="form-inline-profile">
				<label for="name">Nome</label>
				<div class="input-inline">
					<input type="text" class="form-control" id="name"
						placeholder="${user.name }">
				</div>
			</div>
			<div class="form-inline-profile">
				<label for="name">Surname</label>
				<div class="input-inline">
					<input type="text" class="form-control" id="surname"
						placeholder="${user.surname }">
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
						<option value="-1"></option>
						<option value="1">Uomo</option>
						<option value="2">Donna</option>
						<option value="3">Non specificato</option>
					</select>
				</div>
			</div>

			<div class="form-inline-profile">
				<label for="datepicker">Birthdate</label>
				<div class="input-inline">
					<input type="text" name="selDate" id="datepicker"
						class="form-control">
				</div>
			</div>

			<div class="form-inline-profile">
				<label for="bio">Biografia</label>
				<div class="input-inline">
					<textarea class="form-control" id="bio"></textarea>
				</div>
			</div>

			<div class="form-inline-profile">
				<label for="private"></label>
				<div class="input-inline">
					<c:choose>
						<c:when test="${user.privateProfile == true}">
							<label for="private" style="margin-left: -41%">Private</label>
							<label class="switch pull-right"> <input
								onClick="verify_check(this)" type="checkbox" id="checkbox"
								checked> <span class="slider round"></span> <input
								type="hidden" id="check" value="true" />
							</label>
						</c:when>
						<c:otherwise>
							<label for="private" style="margin-left: -41%">Private</label>
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
	<button type="button" disabled="disabled"
	class="btn confirm-btn pull-right" id="modify-btn">Submit</button>
</div>

