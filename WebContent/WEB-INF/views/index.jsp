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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index_style.css">


<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal_follow_style.css">

<!-- NOTY -->	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/noty/noty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/noty/themes/nest.css">
<script
	src="${pageContext.request.contextPath}/resources/js/lib/noty.min.js"></script>

	<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
</head>
<body>
	<jsp:include page="./fragment/navbar.jsp"></jsp:include>
	<div class="container" id="top">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div id="story-nav" style="margin-top: 20px;">
					<jsp:include page="./fragment/stories.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<div id="posts">
			<!-- MOSTRA POST -->
			<jsp:include page="./fragment/post.jsp"></jsp:include>

		</div>
		<div id="loading" class="fixed-bottom hide text-center"><img class="img-fluid" alt="" src="${pageContext.request.contextPath}/resources/images/loader.gif"></div>
		<div id="container-floating">

			<div class="nd1 nds" data-toggle="tooltip" data-placement="left"
				data-original-title="Story">
				<a id="open-story-modal" href="#story-modal"> <i
					class="fa fa-clock-o center-icon" aria-hidden="true"></i>
				</a>			
			</div>

			<div class="nd2 nds" data-toggle="tooltip" data-placement="left"
				data-original-title="Post ">
				<a id="open-create-post-modal" href="#create-post-modal"> <i
					class="fa fa-picture-o center-icon" aria-hidden="true"></i>
				</a>
			</div>

			<div id="cng-order" class="nd3 nds transparent" data-toggle="tooltip"
				data-placement="left" data-original-title="Popular posts" data-type="popular">
				<a><i class="fa fa-exchange black-icon"
					aria-hidden="true"></i></a>
			</div>
			
			<div class="nd4 nds transparent" data-toggle="tooltip"
				data-placement="left" data-original-title="Top">
				<a href="#top"><i class="fa fa-arrow-up black-icon"	aria-hidden="true"></i></a>
			</div>
			
			<div id="floating-button" data-toggle="tooltip" data-placement="left"
				data-original-title="Create">
				<p class="plus">+</p>
			</div>
		</div>

	<jsp:include page="./fragment/createPostModal.jsp"></jsp:include>
	<jsp:include page="./fragment/createStoryModal.jsp"></jsp:include>
	<jsp:include page="./fragment/indexFragment/modalLike.jsp"></jsp:include>
	
	</div>
	<jsp:include page="./fragment/footer.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/js/event_post.js"></script>
	
</body>

</html>
