<!-- VISUALIZZAZIONE DEL PROFILO PRIVATO, LA PRIMA PARTE [sono esclusi i post che sono fatti in postSection.jsp] -->

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
		<li><span><b>${fn:length(userPublic.posts)}</b></span> post</li>
		<li id="follower" data-toggle="modal" data-target="#modalFollower">
		<span><b id="count_follower">${fn:length(userPublic.followers)}</b></span> follower</li>
		<li id="following" data-toggle="modal" data-target="#modalFollowing">
		<span><b>${fn:length(userPublic.followings)}</b></span> profiles followed</li>
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
	<div class="row item-user-info">
		<ul id="follow_ul">
			<jsp:include page="./buttonPrivate.jsp"></jsp:include>
		</ul>
	</div>
</c:if>

<!-- <div class="popup popupFAIL">
	<span class="popuptext alert" id="popupFAIL">
	 <a onclick="close" class="close">&times;</a> <br> <br> <a id="text"></a>
	</span>
</div> -->
