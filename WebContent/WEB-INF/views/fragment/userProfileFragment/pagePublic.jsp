<!-- VISUALIZZAZIONE DEL PROFILO PUBBLICO, LA PRIMA PARTE [sono esclusi i post che sono fatti in postSection.jsp] -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="row item-user-info">
	<ul>
		<li><b>@${userPublic.username}</b></li>
		<%-- <c:if test="${user.username == user.username }">
			<li><a class="btn btn-default" href="modifyProfile" id="modify-profile-btn">modifica profilo</a></li>
		</c:if> --%>
		<li></li>
	</ul>
</div>
<div class="row item-user-info">
	<input id="username_hidden" type="hidden" name="username_hidden" value="${userPublic.username}"/>
	<ul>
		<li><span><b>${fn:length(posts)}</b></span> post</li>
		<li id="follower" data-toggle="modal" data-target="#modalFollower">
		<span><b id="count_follower">${fn:length(userPublic.followers)}</b></span> follower</li>
		<li id="following" data-toggle="modal" data-target="#modalFollowing">
		<span><b>${fn:length(userPublic.followings)}</b></span> profili seguiti</li>
	</ul>
</div>
<div class="row item-user-info">
	<ul>
		<c:set var="name" value="${userPublic.name}" />
		<c:set var="surname" value="${userPublic.surname}" />
		<c:if test="${ empty name  && empty surname}">
			<li><i>Name & Surname unknown</i></li>
		</c:if>
		<c:if test="${ not empty name  || not empty surname}">
			<li>${userPublic.name} ${userPublic.surname}</li>
		</c:if>
	</ul>
</div>
<div class="row item-user-info">
	<ul>
		<%-- <c:set var="bio" value="${user.biography}" /> --%>
		<c:if test="${not empty userPublic.biography}">
			<!-- <hr> -->
			<li>${userPublic.biography }</li>
		</c:if>
	</ul>
</div>
<c:if test="${userPublic.username != user.username }">
	<input type="hidden" id="private${userPublic.id }" value="false"/>
	<div class="row item-user-info">
		<ul id="follow_ul">
			<c:choose>
				<%-- <c:when test="${userPublic.request_send == true }">
					<li><button value="${userPublic.username }" id="acceptRequest-btn" class="btn btn-info">Accept</button></li>
					<li><button value="${userPublic.username }" id="rejectRequest-btn" class="btn btn-secondary">Reject</button></li>
				</c:when>
				<c:otherwise>
					<c:choose> --%>
				<c:when test="${userPublic.followed == false }">
					<li><button name="${userPublic.id }" value="${userPublic.username }" id="followProfile-btn">Follow</button></li>
				</c:when>
				<c:otherwise>
					<li><button name="${userPublic.id }" value="${userPublic.username }" id="unfollowProfile-btn">Unfollow</button></li>
				</c:otherwise>
<%-- 					</c:choose>
				</c:otherwise> --%>
			</c:choose>
		</ul>
	</div>
</c:if>

