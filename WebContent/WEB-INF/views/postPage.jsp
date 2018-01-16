<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon-32x32.png" />
<link rel="icon" href="${pageContext.request.contextPath}/resources/images/favicon.icon" type="image/x-icon" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
	href="${pageContext.request.contextPath}/resources/css/modal_follow_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/page_post_style.css">
<script
	src="${pageContext.request.contextPath}/resources/js/event_post_page.js"></script>
</head>
<body>
	<jsp:include page="./fragment/navbar.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
			<div class="margin-"style="margin: 0 0 0 32px;">
					<div class="row content-post">
						<div class="col-md-8 posts">
							<c:forEach items="${post.media}" var="media">
								<c:if test="${media.type.value == 0}">
									<video width="100%" height="600px" style="background: black;"
										controls autoplay loop preload="auto"> <source
										class="img-post-page" src="${media.url}" type="video/mp4"></video>
								</c:if>
								<c:if test="${media.type.value == 1}">
									<img class="img-post-page" src="${media.url }">
								</c:if>
							</c:forEach>
						</div>
						<div class="col-md-4 details">
							<div class="details-post">
								<img class="user-img-post" src="${post.user.photoProfile }">
								<a href="userPage?username=${post.user.username}"><b>${post.user.username}</b></a>
								<hr class="hr_2">
							</div>
							<div class='caption-section'>
								<span class="post_cont_${post.id}">${post.content}</span>
							</div>
							<div class="comments">
								<div class='list-comments-section'>
									<div class='list-comments${post.id }'>
										<c:forEach items="${post.comments}" var="comment">
											<div class="comment">
												<a href='userPage?username=${comment.user.username}'><b>${comment.user.username}</b></a>
												<span> ${comment.content}</span>
											</div>
										</c:forEach>
									</div>
								</div>

							</div>
							<div class='action-section'>
								<div class='react'>
									<div id="love_div${post.id }">
										<c:choose>
											<c:when test="${fn:length(post.likes) == 0 }">
												<a name="${post.id }" id="love${post.id }" class="love"><span
													class='love'><i class="fa fa-heart-o fa-2x"
														aria-hidden="true"></i></span></a>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${post.likeUser == true}">
														<a name="${post.id }" id="loveFull${post.id }"
															class="loveFull"><span class='loveFull'><i
																class="fa fa-heart fa-2x" aria-hidden="true"></i></span></a>
													</c:when>
													<c:otherwise>
														<a name="${post.id }" id="love${post.id }" class="love">
															<span class='love'><i class="fa fa-heart-o fa-2x"
																aria-hidden="true"></i></span>
														</a>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</div>
									<div id="bookmark_div${post.id }">
										<c:choose>
											<c:when test="${fn:length(user.bookmarks) == 0 }">
												<a name="${post.id }" id="bookmark${post.id }"
													class="bookmarkempty"> <span class='save'><i
														class="fa fa-bookmark-o fa-2x" aria-hidden="true"></i></span></a>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${post.bookmarkUser }">
														<a name="${post.id }" id="bookmark${post.id }"
															class="bookmarkfull"> <span class='save'> <i
																class="fa fa-bookmark fa-2x" aria-hidden="true"></i>
														</span></a>
													</c:when>
													<c:otherwise>
														<a name="${post.id }" id="bookmark${post.id }"
															class="bookmarkempty"><span class='save'> <i
																class="fa fa-bookmark-o fa-2x" aria-hidden="true"></i>
														</span></a>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="likes-section" style="cursor: pointer;">
									<a id="likes" data-toggle="modal" data-target="#modalLike"
										name="${post.id }"><b>Likes to <span
											id="count_like${post.id }"> ${fn:length(post.likes)}</span>
											people
									</b></a>
								</div>
								<div class='time-section'>
									<p>${post.elapsedTime}</p>
								</div>

								<div class='comment-section'>
									<input id="comment${post.id }" name="comment" type='text'
										class='comment-text' placeholder='Add a comment...'>
									<button id="${post.id }" class="submit_comment" type="submit"></button>
									<input type="hidden" id="username" value="${user.username }" />

								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- end section -->

			</div>
			<div class="col-md-1"></div>
		</div>
	</div>

	<jsp:include page="./fragment/indexFragment/modalLike.jsp"></jsp:include>
	<jsp:include page="./fragment/footer.jsp"></jsp:include>
</body>
</html>