<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon-32x32.png" />
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.icon"
	type="image/x-icon" />

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
<script src="${pageContext.request.contextPath}/resources/js/explore.js"></script>
</head>
<body>
	<jsp:include page="./fragment/navbar.jsp"></jsp:include>
	<div class="container profile">
		<section class="profile">
			<div class="posts-user-info">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div id="posts">
						<jsp:include page="./fragment/userProfileFragment/previewPost.jsp"></jsp:include>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div id="loading" class="fixed-bottom hide text-center">
							<img class="img-fluid" alt=""
								src="${pageContext.request.contextPath}/resources/images/loader.gif">
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<jsp:include page="./fragment/footer.jsp"></jsp:include>
	<jsp:include page="./fragment/followFragment/modalFollow.jsp"></jsp:include>
	<jsp:include page="./fragment/followFragment/modalFollower.jsp"></jsp:include>

	<!-- <jsp:include page="./fragment/userProfileFragment/modalPost.jsp"></jsp:include> -->
</body>



</html>
