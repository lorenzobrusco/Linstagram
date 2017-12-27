<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="./resources/js/event_post.js"></script>

<!-- start body-section -->
<c:forEach items="${posts}" var="post">
	<section>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class='card'>
					<div class='top-section'>
						<a href=''> <img class="user-img"src=${post.user.photoProfile }></a> 
						<a href='userPage?usernameOther=${post.user.username }' class='user-name'>${post.user.username }</a>
					</div>
					<div class='body-section'>
						<div class="overlay">
							<span></span>
						</div>
						<c:forEach items="${post.media}" var="media">
							<img src="${media.url}"/>
						</c:forEach>
					</div>
					<div class='action-section'>
						<div class='react'>
							<div id="love_div${post.id }">
								<c:choose>
									<c:when test="${fn:length(post.likes) == 0 }">
										<a name="${post.id }" id="love${post.id }" class="love"><span class='love'></span></a> 
									</c:when>
									<c:otherwise>
										<c:forEach  items="${post.likes}" var="like">
											<c:choose>
												<c:when test="${like.id == user.id}">
													<a name="${post.id }" id="loveFull${post.id }" class="loveFull"><span class='loveFull'></span></a> 
												</c:when>
												<c:otherwise>
													<a name="${post.id }" id="love${post.id }" class="love"><span class='love'></span></a> 
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
							<div id="comment_div">
								<a href='#' role='button'><span class='comment'></span></a>
							</div>
							<div id="bookmark_div${post.id }">
								<c:choose>
									<c:when test="${fn:length(user.bookmarks) == 0 }">
										<a name="${post.id }" id="bookmark${post.id }" class="bookmark"><span class='save'></span></a>
									</c:when>
									<c:otherwise>
										<c:set var="found" value="${false}"/>
										<c:forEach  items="${user.bookmarks}" var="bookmark">
											<c:if test="${bookmark.id == post.id}">
												<c:set var="found" value="${true}"/>
											</c:if>
										</c:forEach>
										<c:choose>
	       									<c:when test="${found }">
	       										<a name="${post.id }" id="bookmark${post.id }" class="bookmarkFull"><span class='saveFull'></span></a>
	       									</c:when>
			        						<c:otherwise>
				        						<a name="${post.id }" id="bookmark${post.id }" class="bookmark"><span class='save'></span></a>
			        						</c:otherwise>
		        						</c:choose>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="likes-section">
							<a href='#'><b>Piace a <span id="count_like${post.id }">
										${fn:length(post.likes)}</span> persone</b></a>
						</div>
						<div class='caption-section'>
							<a href='#'>${post.user.username }</a><span>${post.content}</span>
						</div>

						<div class='list-comments-section'>
							<div class='list-comments${post.id }'>
								<c:forEach items="${post.comments}" var="comment">
									<a href='#'><b>${comment.user.username}</b></a>
									<span> ${comment.content}</span>
									<br>
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
							</div>
							<a class="show-all-comments" href="#postcibo"
								data-toggle="collapse"><span class="show-comments"></span>
								Carica altri commenti</a><br>

						</div>

						<div class='time-section'>
							<p><script>
								var postedTime = "${post.postDate.getTimeInMillis()}";
								document.write(getElapsedTime(postedTime));
							</script></p>
						</div>

						<div class='comment-section'>
							<input id="comment${post.id }" name="comment" type='text' class='comment-text'
								placeholder='Add a comment...'>
							<button id="${post.id }" class="submit_comment" type="submit"></button>
							<input type="hidden" id="username" value="${user.username }"/>
							
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
