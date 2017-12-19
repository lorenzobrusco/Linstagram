<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


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
<script
	src="${pageContext.request.contextPath}/resources/js/animatedModal.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/dropzone.js"></script>
	

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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/create_post_modal_style.css">

</head>
<body>
	<jsp:include page="./fragment/navbar.jsp"></jsp:include>
	<div class="container" id="top">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div id="story-nav" style="margin-top: 20px;">
					<div id="stories"></div>
				</div>
			</div>
		</div>
		<div id="posts">
		
			<c:forEach items="${posts}" var="post">
				<section>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-8">
	
							<!-- start body-section -->
							<div class='card'>
								<div class='top-section'>
									<a href=''> <img class="user-img"
										src=${post.user.photoProfile }>
									</a> <a href='' class='user-name'>${post.user.username }</a>
								</div>
								<div class='body-section'>
									<div class="overlay">
										<span></span>
									</div>
									<c:forEach items="${post.media}" var="media">
										<img src=${media.url }>
									</c:forEach>
								</div>
								<div class='action-section'>
									<div class='react'>
										<form role="form" action="like">
											<a href='#' role='button'><span class='love'></span></a> 
											<input type="hidden" name="idPost" value="${post.id}">
											<button type="submit" class="btn btn-success">Submit</button>
										</form>
										<a	href='#' role='button'><span class='comment'></span></a> 
										<a	href='#' role='button'><span class='save'></span></a>
									</div>
									<div class="likes-section">
										<a href='#'><b>Piace a<span> ${post.likes.size() } persone</span></b></a>
									</div>
									<div class='caption-section'>
										<a href='#'>${post.user.username }</a><span>${post.content }</span>
									</div>
	
									<div class='list-comments-section'>
										<c:forEach items="${post.comments}" var="comment">
											<a href='#'>${comment.user.username }</a><span> ${comment.content }</span><br>
										</c:forEach>
										<!-- <a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										<div id="postcibo" class="collapse">
											<a href='#'>Lorenzo</a><span> testo del commento</span><br>
											<a href='#'>Lorenzo</a><span> testo del commento</span><br>
											<a href='#'>Lorenzo</a><span> testo del commento</span><br>
											<a href='#'>Lorenzo</a><span> testo del commento</span><br>
										</div> -->
	
	
										<a class="show-all-comments" href="#postcibo"
											data-toggle="collapse"><span class="show-comments"></span>
											Carica altri commenti</a><br>
	
	
									</div>
	
									<div class='time-section'>
										<p>4 ore fa</p>
									</div>
	
									<div class='comment-section'>
										<form role="form" action="comment">
											<input name="comment" type='text' class='comment-text'
												placeholder='Add a comment...'>
											<input type="hidden" name="idPost" value="${post.id}">
											<button type="submit" class="btn btn-success">Submit</button>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- end body-section -->
						<div class="col-md-2"></div>
					</div>
				</section>
				<!-- end section -->

			</c:forEach>
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
         
          <form class="dropzone" id="post-dropzone">
            <!-- this is were the previews should be shown. -->
            <button type="submit" class="btn btn-success btn-create">
              <i class="fa fa-paper-plane" aria-hidden="true"></i> Confirm
            </button>
            <div class="fallback">
              <input name="file" type="file" multiple />
            </div>
            <div class="dropzone-previews">
              <div class="dz-default dz-message"><span>Drop yuor photos or videos here</span></div>
            </div>
          </form>
          
          <!--TODO: CALL WITH AJAX FOR HIDE MODEL AND CONFIRM CREATION -->
          <form id="post-description" class="hide" action="createPost">
            <label for="post-description-input">Enter a description:</label>
            <textarea class="form-control" rows="5" id="post-description-input" name="postDescription"> </textarea>
            <button type="submit" class="btn btn-success btn-create">
              <i class="fa fa-paper-plane" aria-hidden="true"></i> Confirm
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
