<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Listagram</title>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/profile_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modify_profile.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/popup.css">

<script src="${pageContext.request.contextPath}/resources/js/modify_profile.js"></script>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>

<body>
	<jsp:include page="./fragment/navbar.jsp"></jsp:include>
	<div class="container">
	<section>
		<div class="row content-modify-profile">
			<div class="col-lg-3 sidenav">
				<ul class="nav nav-pills nav-stacked custom-nav">
					<li class="active-nav"><a href="#section1">Modifica
							profilo</a></li>
					<li><a href="#section2">Modifica password</a></li>
				</ul>
				<br>
			</div>
			<div class="col-lg-9">
				<!-- start body-section -->
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
			</div>
			<button class="pull-right" id="modify-btn">Submit</button>
		</div>


		
		
	</section>
	<!-- end section -->

	</div>
	<jsp:include page="./fragment/footer.jsp"></jsp:include>
</body>
</html>
