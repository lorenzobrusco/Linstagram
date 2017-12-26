<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>
function getElapsedTime(postedTime){
	var now = (new Date()).getTime();
	var diff=now-postedTime;
	var second = diff/1000;
	var min = second/60;
	var round=Math.round(min);
	if(	round<=60)
		return round+" MIN AGO";
	else if(round > 60 && round < 24*60){
		round = round/60;
		round = Math.round(round);
		return round+" HOURS AGO";
	}
	else{
		round = round/60;
		round = round/24;
		round = Math.round(round);
		return round+" DAYS AGO";
	}	
};

$(document).on('click', '.love', function() {
	var postID = $(this).attr('name');
	var count_like = $("#count_like"+postID)
	var love_id = $("#love_div"+postID)

	$.ajax({
		url : "addLike",
		data:{postID:postID},
		success : function(result) {
			if(result == "OK") {
				$(count_like).html(parseInt($(count_like).html(), 10)+1)
				$(love_id).empty();
				$(love_id).append("<a name="+postID+" id=loveFull"+postID+
				" class=loveFull><span class=loveFull></span></a>");
			}
			//altrimenti dare un messaggio di errore??					
		}
	});
});

$(document).on('click', '.loveFull', function() {
	var postID = $(this).attr('name');
	var count_like = $("#count_like"+postID)
	var love_id = $("#love_div"+postID)

	$.ajax({
		url : "removeLike",
		data:{postID:postID},
		success : function(result) {
			if(result == "OK") {
				$(count_like).html(parseInt($(count_like).html(), 10)-1)
				$(love_id).empty();
				$(love_id).append("<a name="+postID+" id=love"+postID+
				" class=love><span class=love></span></a>");
			}
			//altrimenti dare un messaggio di errore??					
		}
	});
});

$(document).on('click', '.bookmark', function() {
	var postID = $(this).attr('name');
	var bookmark_id = $("#bookmark_div"+postID)
	
	$.ajax({
		url : "addBookmark",
		data:{postID:postID},
		success : function(result) {
			if(result == "OK") {
				$(bookmark_id).empty();
				$(bookmark_id).append("<a name="+postID+" id=bookmark"+postID+
				" class=bookmarkFull><span class=saveFull></span></a>");
			}
			//altrimenti dare un messaggio di errore??					
		}
	});
});

$(document).on('click', '.bookmarkFull', function() {
	var postID = $(this).attr('name');
	var bookmark_id = $("#bookmark_div"+postID)
	
	$.ajax({
		url : "removeBookmark",
		data:{postID:postID},
		success : function(result) {
			if(result == "OK") {
				$(bookmark_id).empty();
				$(bookmark_id).append("<a name="+postID+" id=bookmark"+postID+
				" class=bookmark><span class=save></span></a>");
			}
			//altrimenti dare un messaggio di errore??					
		}
	});
});

/* $(document).on('click', '.submit_comment', function() {
	var postID = $(this).attr('id');
	var comm = $("#comment"+postID).val()
	
	$.ajax({
		url : "comment",
		data:{postID:postID, comment:comm},
		success : function(result) {		if(resesult == "OK") {
				$(comm).val('');
				alert("AAAAAAAAAAAAAAAAAAAAAA")
			}
			else
				alert("NOOOOOOOOO");
			//altrimenti dare un messaggio di errore??					
		}
	});
}); */


</script>


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

						<div class='list-comments-section${post.id }'>
							<c:forEach items="${post.comments}" var="comment">
								<a href='#'>${comment.user.username}</a>
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
							<%-- <button id="${post.id }" class="submit_comment" type="submit">Submit</button> --%>
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
