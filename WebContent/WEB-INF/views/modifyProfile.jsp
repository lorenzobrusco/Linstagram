<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon-32x32.png" />
<link rel="icon" href="${pageContext.request.contextPath}/resources/images/favicon.icon" type="image/x-icon" />
<title>Listagram</title>
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/profile_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/modify_profile.css">

<script
	src="${pageContext.request.contextPath}/resources/js/modify_profile.js"></script>

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- NOTY -->	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/noty/noty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/noty/themes/nest.css">
<script
	src="${pageContext.request.contextPath}/resources/js/lib/noty.min.js"></script>

</head>

<body>
	<jsp:include page="./fragment/navbar.jsp"></jsp:include>
	<div class="container">
		<section>
			<div class="row content-modify-profile">
				<div class="col-lg-3 sidenav">
					<ul class="nav nav-pills nav-stacked custom-nav">
						<li id="infoUser" class="active-nav">
							<a id="changeInfoUser" href="#section1">Edit Profile</a>
						</li>
						<li id="passUser">
							<a id="changePassword" href="#section2">Change Password</a>
						</li>
					</ul>
					<br>
				</div>
				<div class="col-lg-9" id="container_form">
					<!-- start body-section -->
					<div class='modify-profile'>
					<jsp:include page="./fragment/modifyProfileFragment/modifyProfileHeader.jsp"></jsp:include>
					<jsp:include
						page="./fragment/modifyProfileFragment/modifyProfileSection.jsp"></jsp:include>
					</div>
				</div>
			</div>

		</section>
		<!-- end section -->

	</div>
	<jsp:include page="./fragment/footer.jsp"></jsp:include>
</body>

</html>
