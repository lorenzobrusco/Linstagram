<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="${pageContext.request.contextPath}/resources/js/video.js"></script>
<c:forEach items="${posts}" var="post">
	<section>
		<div class="row">
			<div class="col-md-2">
				<span class="pid hidden">${post.id}</span>
			</div>
			<div class="col-md-8">
				<div class='card'>
					<div class='top-section'>
						<a href='userPage?username=${post.user.username }'> <img
							class="user-img" src=${post.user.photoProfile }></a> <a
							href='userPage?username=${post.user.username }' class='user-name'>${post.user.username }</a>
					</div>
					<div class='body-section'>

						<c:forEach items="${post.media}" var="media">
							<c:if test="${media.type.value == 0}">
								<div class="overlay-video">
									<video id="video${post.id }" width="100%"
										style="max-height: 400px; background: black;" loop
										preload="auto">
										<source src="${media.url}" type="video/mp4">
									</video>
									<span class="tag-play"> <i class="fa fa-play fa-5x"
										aria-hidden="true"></i>
									</span> <span class="tag-pause hide"> <i
										class="fa fa-pause fa-1x" aria-hidden="true"></i>
									</span> <span class="tag-video"></span><span class="tag-audio"><i
										class="fa fa-volume-up fa-1x" aria-hidden="true"></i></span>
								</div>
							</c:if>
							<c:if test="${media.type.value == 1}">
								<div class="overlay">
									<c:choose>
										<c:when test="${fn:length(post.likes) == 0 }">
											<a name="${post.id }" id="love${post.id }" class="love"><span></span></a>
										</c:when>
										<c:otherwise>
											<a name="${post.id }" id="love${post.id }" class="love"><span></span></a>
										</c:otherwise>
									</c:choose>
								</div>
								<img src="${media.url}" />
							</c:if>
						</c:forEach>
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
											class="bookmark"> <span class='save'><i
												class="fa fa-bookmark-o fa-2x" aria-hidden="true"></i></span></a>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${post.bookmarkUser }">
												<a name="${post.id }" id="bookmark${post.id }"
													class="bookmarkFull"> <span class='save'> <i
														class="fa fa-bookmark fa-2x" aria-hidden="true"></i>
												</span></a>
											</c:when>
											<c:otherwise>
												<a name="${post.id }" id="bookmark${post.id }"
													class="bookmark"><span class='save'> <i
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
						<div class='caption-section'>
							<a href='userPage?username=${post.user.username}'>${post.user.username }</a>
							<span class="post_cont_${post.id}">${post.content}</span>
						</div>

						<div class='list-comments-section'>
							<a class="show-all-comments" href="#postcibo"
								data-toggle="collapse"><span class="show-comments"></span>
								Load other comments<img class="hide" id="loader-comments"
								src="${pageContext.request.contextPath}/resources/images/loaderComm.gif"></a><br>
							<div class='list-comments${post.id }'>
								<c:forEach items="${post.comments}" varStatus="status">
									<c:set var="comment" value="${post.comments[4-status.count]}"/>
									<div class="comment">
										<a href='userPage?username=${comment.user.username}'><b>${comment.user.username}</b></a>
										<span> ${comment.content}</span>
									</div>
								</c:forEach>
							</div>
							<a class="hide hide-all-comments" href="#postcibo"
								data-toggle="collapse"><span class="hide-comments"></span>
								Hide comments</a><br>
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
			<!-- end body-section -->
			<div class="col-md-2"></div>
		</div>
	</section>
</c:forEach>

