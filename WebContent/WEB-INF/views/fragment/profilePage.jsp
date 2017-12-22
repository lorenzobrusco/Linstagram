<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- <style>
/*Bootstrap button outline override*/
.btn-outline {
    background-color: transparent !important;
    color: inherit !important;
    transition: all .5s !important;
}

.btn-primary.btn-outline {
    color: #428bca !important;
}

.btn-success.btn-outline {
    color: #5cb85c !important;
}

.btn-info.btn-outline {
    color: #5bc0de !important;
}

.btn-warning.btn-outline {
    color: #f0ad4e !important;
}

.btn-danger.btn-outline {
    color: #d9534f !important;
}

.btn-primary.btn-outline:hover,
.btn-success.btn-outline:hover,
.btn-info.btn-outline:hover,
.btn-warning.btn-outline:hover,
.btn-danger.btn-outline:hover {
    color: #fff !important;
}
</style>
 -->

<div class='modify-profile'>
	<div class="photo">
		<div class="form-inline-profile">
			<div class="input-inline">
				<img class="user-img" src="resources/images/user_login_img.png">
			</div>
			<label for="name">PEPPE PEPPE</label><br> <span>Change
				profile image</span>
		</div>
	</div>
	<div class="info">
		<form id="">
			<div class="form-inline-profile">
				<label for="name">Nome</label>
				<div class="input-inline">
					<input type="text" class="form-control" id="name">
				</div>
			</div>
			<div class="form-inline-profile">
				<label for="user-name">Username</label>
				<div class="input-inline">
					<input type="text" class="form-control" id="user-name">
				</div>
			</div>
			<div class="form-inline-profile">
				<label for="email">Email</label>
				<div class="input-inline">
					<input type="email" class="form-control" id="email">
				</div>
			</div>

			<div class="form-inline-profile">
				<label for="selection">Gender</label>
				<div class="input-inline">
					<select id="selection" class="form-control">
						<option value="-1"></option>
					 	<option value="1">Uomo</option>
						<option value="2">Donna</option>
						<option value="3">Non specificato</option></select>
				</div>
			</div>

			<div class="form-inline-profile">
				<label for="datepicker">Birthdate</label>
				<div class="input-inline">
             				<input type="text" name="selDate" id="datepicker">
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
					        <label for="private" style="margin-left:-41%">Private</label>
							<label class="switch pull-right">
						  		<input onClick="verify_check(this)" type="checkbox" id="checkbox" checked>
						  		<span class="slider round"></span>
						  		<input type="hidden" id="check" value="true"/>
							</label>
					    </c:when>    
					    <c:otherwise>
					        <label for="private" style="margin-left:-41%">Private</label>
							<label class="switch pull-right">
						  		<input onClick="verify_check(this)" type="checkbox" id="checkbox">
						  		<span class="slider round"></span>
						  		<input type="hidden" id="check" value="false"/>
							</label>
					    </c:otherwise>
					</c:choose>
				</div>
			</div>
			
			<div class="popup popupOK">
				<span class="popuptext alert" id="popupOK">
				  <a  onclick="close" class="close">&times;</a>
				  <strong>Success!</strong> Le modifiche sono state effettuate.
				</span>
			</div>
			<div class="popup popupFAIL">
				<span class="popuptext alert" id="popupFAIL">
				  <a  onclick="close" class="close">&times;</a>
				  <br>
				  <br>
				  <a id="text"></a>
				</span>
			</div>
		</form>
	</div>
</div>
<button type="button" class="btn btn-outline-primary pull-right" id="modify-btn" style="margin: 0% 10% 2% 0%;">Submit</button>
