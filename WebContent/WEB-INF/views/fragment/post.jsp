<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:forEach items="${posts}" var="post">
	<section>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class='card'>
					<div class='top-section'>
						<a href='userPage?username=${post.user.username }'> 
						<img class="user-img"
							src=${post.user.photoProfile }></a> 
							<a
							href='userPage?username=${post.user.username }'
							class='user-name'>${post.user.username }</a>
					</div>
					<div class='body-section'>
						<div class="overlay">
							<span></span>
						</div>
						<c:forEach items="${post.media}" var="media">
							<img src="${media.url}" />
						</c:forEach>
					</div>
					<div class='action-section'>
						<div class='react'>
							<div id="love_div${post.id }">
								<c:choose>
									<c:when test="${fn:length(post.likes) == 0 }">
										<a name="${post.id }" id="love${post.id }" class="love"><span
											class='love'><i class="fa fa-heart-o fa-2x" aria-hidden="true"></i></span></a>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${post.likeUser == true}">
												<a name="${post.id }" id="loveFull${post.id }"
													class="loveFull"><span class='loveFull'><i class="fa fa-heart fa-2x" aria-hidden="true"></i></span></a>
											</c:when>
											<c:otherwise>
												<a name="${post.id }" id="love${post.id }" class="love">
												<span class='love'><i class="fa fa-heart-o fa-2x" aria-hidden="true"></i></span></a>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</div>
							<div id="bookmark_div${post.id }">
								<c:choose>
									<c:when test="${fn:length(user.bookmarks) == 0 }">
										<a name="${post.id }" id="bookmark${post.id }"
											class="bookmark">
											<span class='save'><i class="fa fa-bookmark-o fa-2x" aria-hidden="true"></i></span></a>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${post.bookmarkUser }">
												<a name="${post.id }" id="bookmark${post.id }" class="bookmarkFull">
												<span class='save'> <i class="fa fa-bookmark fa-2x" aria-hidden="true"></i> </span></a>
											</c:when>
											<c:otherwise>
												<a name="${post.id }" id="bookmark${post.id }"
													class="bookmark"><span class='save'> <i class="fa fa-bookmark-o fa-2x" aria-hidden="true"></i> </span></a>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="likes-section" style="cursor: pointer;">
							<a id="likes" data-toggle="modal" data-target="#modalLike"
								name="${post.id }"><b>Likes to  
								<span id="count_like${post.id }"> ${fn:length(post.likes)}</span>
									people
							</b></a>
						</div>
						<div class='caption-section'>
							<a href='userPage?username=${post.user.username}'>${post.user.username }</a>
							<span>${post.content}</span>
						</div>

						<div class='list-comments-section'>
							<a class="show-all-comments" href="#postcibo"
								data-toggle="collapse"><span class="show-comments"></span>
								Load other comments<img class="hide" id="loader-comments"
								src="${pageContext.request.contextPath}/resources/images/loaderComm.gif"></a><br>
							<div class='list-comments${post.id }'>
								<c:forEach items="${post.comments}" var="comment"
									varStatus="loop">
									<c:if test="${loop.index <4}">
										<div class="comment">
											<a href='userPage?username=${comment.user.username}'><b>${comment.user.username}</b></a>
											<span> ${comment.content}</span>
										</div>
									</c:if>
								</c:forEach>
							</div>
							<a class="hide hide-all-comments" href="#postcibo"
								data-toggle="collapse"><span class="hide-comments"></span>
								Hide comments</a><br>
						</div>

						<div class='time-section'>
							<p>
							${post.elapsedTime}
							</p>
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
