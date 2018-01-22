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
	href="${pageContext.request.contextPath}/resources/css/index_style.css">
</head>
<body>
	<jsp:include page="./fragment/navbar.jsp"></jsp:include>
	<div class="container">
		<div class="row">
		<div class="col-md-2"></div>
			<div class="col-md-8" style="margin-top: 20%;text-align: center;">
				<div class="body-not-found">
					<img class="img-not-found"
						src="${pageContext.request.contextPath}/resources/images/page_404.png">
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>

</body>

</html>
