<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Listagram</title>
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index_style.css">

<!-- NOTY -->	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/noty/noty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/noty/themes/nest.css">
<script
	src="${pageContext.request.contextPath}/resources/js/lib/noty.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal_follow_style.css">

<script src="${pageContext.request.contextPath}/resources/js/hashtagPage.js"></script>
</head>
<body>
	<jsp:include page="./fragment/navbar.jsp"></jsp:include>
	<div class="container" id="top">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 text-center">
				<h2>#${hashtag.hashtag}</h2>
				<h5><b>${hashtag.count}</b> public posts</h5>
			</div>
		</div>
		<div id="posts">
			<!-- MOSTRA POST -->
			<jsp:include page="./fragment/post.jsp"></jsp:include>
		</div>
		<div id="loading" class="fixed-bottom hide text-center"><img class="img-fluid" alt="" src="${pageContext.request.contextPath}/resources/images/loader.gif"></div>

	<jsp:include page="./fragment/indexFragment/modalLike.jsp"></jsp:include>
	
	</div>
	<jsp:include page="./fragment/footer.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/js/event_post.js"></script>
	
</body>

</html>
