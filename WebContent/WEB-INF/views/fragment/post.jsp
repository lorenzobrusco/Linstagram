<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
}
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
						<a href='' class='user-name'>${post.user.username }</a>
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
							<a href='#' role='button'><span class='love'></span></a> <a
								href='#' role='button'><span class='comment'></span></a> <a
								href='#' role='button'><span class='save'></span></a>
						</div>
						<div class="likes-section">
							<a href='#'><b>Piace a<span>
										${fn:length(post.likes)} persone</span></b></a>
						</div>
						<div class='caption-section'>
							<a href='#'>${post.user.username }</a><span>${post.content}</span>
						</div>

						<div class='list-comments-section'>
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
							<input name="comment" type='text' class='comment-text'
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
</c:forEach>
