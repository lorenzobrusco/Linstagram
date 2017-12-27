<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- VISUALIZZAZIONE DEL PROFILO PUBBLICO, LA PRIMA PARTE [sono esclusi i post che sono fatti in profilePost.jsp] -->

<script>
$(document).ready(function() {
	$(document).on('click', '#follow-btn', function() {
		var username = $('#username_hidden').val();
		var popupFail = document.getElementById("popupFAIL");
		
		$.ajax({
			url : "followUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#follow_ul").empty();
					$("#follow_ul").append("<li><button id='unfollow-btn'>Unfollow</button></li>");
					$("#count_follower").html(parseInt($("#count_follower").html(), 10)+1)
				}
				/* else {
					$('#text').text(result);
					popupFail.classList.toggle("show");					
				} */
			}
		});
	});
	
	$(document).on('click', '#unfollow-btn', function() {
		var username = $('#username_hidden').val();
		var popupFail = document.getElementById("popupFAIL");
		
		$.ajax({
			url : "unfollowUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#follow_ul").empty();
					$("#follow_ul").append("<li><button id='follow-btn'>Follow</button></li>");
					$("#count_follower").html(parseInt($("#count_follower").html(), 10)-1)
				}
				/* else {
					$('#text').text(result);
					popupFail.classList.toggle("show");			
				} */
			}
		});
	});
});
</script>

<div class="row item-user-info">
	<ul>
		<li><b>@${user.username}</b></li>
		<c:if test="${user.username == userSession.username }">
			<li><a class="btn btn-default" href="modifyProfile" id="modify-profile-btn">modifica profilo</a></li>
		</c:if>
		<li></li>
	</ul>
	<input id="username_hidden" type="hidden" name="username_hidden" value="${user.username}"/>
</div>
<div class="row item-user-info">
	<ul>
		<li><span><b>${fn:length(user.posts)}</b></span> post</li>
		<li id="follower" data-toggle="modal" data-target="#modalFollower">
		<span><b id="count_follower">${fn:length(user.followers)}</b></span> follower</li>
		<li id="following" data-toggle="modal" data-target="#modalFollowing">
		<span><b>${fn:length(user.followings)}</b></span> profili seguiti</li>
	</ul>
</div>
<div class="row item-user-info">
	<ul>
		<c:set var="name" value="${user.name}" />
		<c:set var="surname" value="${user.surname}" />
		<c:if test="${ empty name  && empty surname}">
			<li><i>Name & Surname unknown</i></li>
		</c:if>
		<c:if test="${ not empty name  || not empty surname}">
			<li>${user.name} ${user.surname}</li>
		</c:if>
	</ul>
</div>
<div class="row item-user-info">
	<ul>
		<%-- <c:set var="bio" value="${user.biography}" /> --%>
		<c:if test="${not empty user.biography}">
			<!-- <hr> -->
			<li>${user.biography }</li>
		</c:if>
	</ul>
</div>
<c:if test="${user.username != userSession.username }">
<div class="row item-user-info">
	<ul id="follow_ul">
		<c:choose>
			<c:when test="${user.followed == false }">
				<li><button id="follow-btn">Follow</button></li>
			</c:when>
			<c:otherwise>
				<li><button id="unfollow-btn">Unfollow</button></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
</c:if>

<!-- <div class="popup popupFAIL">
	<span class="popuptext alert" id="popupFAIL">
	 <a onclick="close" class="close">&times;</a> <br> <br> <a id="text"></a>
	</span>
</div> -->
