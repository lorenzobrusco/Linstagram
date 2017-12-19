<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/stories.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/dropzone.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/animate.min.css">


<script
	src="${pageContext.request.contextPath}/resources/js/animatedModal.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/dropzone.js"></script>

<script>
	Dropzone.autoDiscover = false;
</script>

</head>
<body>
	<jsp:include page="./fragment/navbar.jsp"></jsp:include>
	<div class="container" id="top">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div id="story-nav">
					<div id="stories"></div>
				</div>
			</div>
		</div>
		<div id="posts">
			<section>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">

						<!-- start body-section -->
						<div class='card'>
							<div class='top-section'>
								<a href=''> <img class="user-img"
									src='https://scontent-mxp1-1.cdninstagram.com/t51.2885-19/s320x320/20482280_307729109689765_5209255955170066432_a.jpg'>
								</a> <a href='' class='user-name'>Lorenzo</a>
							</div>
							<div class='body-section'>
								<div class="overlay">
									<span></span>
								</div>
								<img
									src='http://www.buonissimo.org/archive/borg/qdBs0GRJIDseIkTw3%252FB%252BOfBYvoxMy9pkhjweUSL9FLKt57oUgnK%252FxA%253D%253D'>
							</div>
							<div class='action-section'>
								<div class='react'>
									<a href='#' role='button'><span class='love'></span></a> <a
										href='#' role='button'><span class='comment'></span></a> <a
										href='#' role='button'><span class='save'></span></a>
								</div>
								<div class="likes-section">
									<a href='#'><b>Piace a<span> 100000 persone</span></b></a>
								</div>
								<div class='caption-section'>
									<a href='#'>Peppe</a><span>Testo del cristiano che ha
										scritto </span>
								</div>

								<div class='list-comments-section'>
									<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									<div id="postcibo" class="collapse">
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									</div>


									<a class="show-all-comments" href="#postcibo"
										data-toggle="collapse"><span class="show-comments"></span>
										Carica altri commenti</a><br>


								</div>

								<div class='time-section'>
									<p>4 ore fa</p>
								</div>

								<div class='comment-section'>
									<input type='text' class='comment-text'
										placeholder='Add a comment...'>
								</div>
							</div>
						</div>
					</div>
					<!-- end body-section -->
					<div class="col-md-2"></div>
				</div>
			</section>
			<!-- end section -->

			<section>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<!-- start body-section -->
						<div class='card'>
							<div class='top-section'>
								<a href=''> <img class="user-img"
									src='http://static.sify.com/cms/image/mdxq4Aigdfhsi.jpg'>
								</a> <a href='' class='user-name'>Peppa</a>
							</div>
							<div class='body-section'>
								<div class="overlay">
									<span></span>
								</div>
								<img
									src='https://static.pexels.com/photos/160699/girl-dandelion-yellow-flowers-160699.jpeg'>
							</div>
							<div class='action-section'>
								<div class='react'>
									<a href='#' role='button'><span class='love'></span></a> <a
										href='#' role='button'><span class='comment'></span></a> <a
										href='#' role='button'><span class='save'></span></a>
								</div>
								<div class="likes-section">
									<a href='#'><b>Piace a<span> 100000 persone</span></b></a>
								</div>
								<div class='caption-section'>
									<a href='#'>Peppe</a><span>Testo del cristiano che ha
										scritto </span>
								</div>

								<div class='list-comments-section'>
									<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									<div id="post1" class="collapse">
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									</div>


									<a class="show-all-comments" href="#post1"
										data-toggle="collapse"><span class="show-comments"></span>
										Carica altri commenti</a><br>


								</div>

								<div class='time-section'>
									<p>4 ore fa</p>
								</div>

								<div class='comment-section'>
									<input type='text' class='comment-text'
										placeholder='Add a comment...'>
								</div>
							</div>
						</div>
					</div>
					<!-- end body-section -->
					<div class="col-md-2"></div>
				</div>
			</section>
			<!-- end section -->

			<section>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<!-- start body-section -->
						<div class='card'>
							<div class='top-section'>
								<a href=''> <img class="user-img"
									src='resources/images/user_login_img.png'>
								</a> <a href='' class='user-name'>Peppe</a>
							</div>
							<div class='body-section'>
								<div class="overlay">
									<span></span>
								</div>
								<img src='https://pbs.twimg.com/media/B-EZPXFIIAAmcb8.jpg:large'>
							</div>
							<div class='action-section'>
								<div class='react'>
									<a href='#' role='button'><span class='love'></span></a> <a
										href='#' role='button'><span class='comment'></span></a> <a
										href='#' role='button'><span class='save'></span></a>
								</div>
								<div class="likes-section">
									<a href='#'><b>Piace a<span> 100000 persone</span></b></a>
								</div>
								<div class='caption-section'>
									<a href='#'>Peppe</a><span>Testo del cristiano che ha
										scritto </span>
								</div>

								<div class='list-comments-section'>
									<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									<div id="post2" class="collapse">
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
									</div>


									<a class="show-all-comments" href="#post2"
										data-toggle="collapse"><span class="show-comments"></span>
										Carica altri commenti</a><br>


								</div>

								<div class='time-section'>
									<p>4 ore fa</p>
								</div>

								<div class='comment-section'>
									<input type='text' class='comment-text'
										placeholder='Add a comment...'>
								</div>
							</div>
						</div>
					</div>
					<!-- end body-section -->
					<div class="col-md-2"></div>
				</div>
			</section>
			<!-- end section -->
		</div>
		<div id="container-floating">

			<div class="nd1 nds" data-toggle="tooltip" data-placement="left"
				data-original-title="Story">
				<i class="fa fa-clock-o center-icon" aria-hidden="true"></i>
			</div>

			<div class="nd2 nds" data-toggle="tooltip" data-placement="left"
				data-original-title="Post ">
				<a id="open-create-post-modal" href="#create-post-modal"> <i
					class="fa fa-picture-o center-icon" aria-hidden="true"></i>
				</a>
			</div>


			<div class="nd3 nds transparent" data-toggle="tooltip"
				data-placement="left" data-original-title="Top">
				<a href="#top"><i class="fa fa-arrow-up black-icon"
					aria-hidden="true"></i></a>
			</div>

			<div id="floating-button" data-toggle="tooltip" data-placement="left"
				data-original-title="Create">
				<p class="plus">+</p>
			</div>
		</div>

		<!--CREATE POST MODAL-->
		<div id="create-post-modal">
			<!--THIS IS IMPORTANT! to close the modal, the class name has to match the name given on the ID  class="close-animatedModal" -->
			<div id="modal-card">
				<div class="modal-header">
					<span class="modal-title">Create Post</span>
					<div class="close-create-post-modal"></div>
				</div>
				<div class="modal-body">

					<form class="dropzone" id="post-dropzone" method="POST"
						action="upload" enctype="multipart/form-data">
						<!-- this is were the previews should be shown. -->
						<div class="fallback">
							<input name="file" type="file"/>
						</div>
						<br> <label for="post-description-input">Enter a
							description:</label>
						<textarea class="form-control" rows="5"
							id="post-description-input"> 
            </textarea>
						<button type="submit" class="btn btn-success btn-create"
							value="upload">
							<i class="fa fa-paper-plane" aria-hidden="true"></i> Create Post
						</button>
					</form>
				</div>
			</div>
		</div>



	</div>
	<jsp:include page="./fragment/footer.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/stories.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/create_stories.js"></script>

</body>

</html>
