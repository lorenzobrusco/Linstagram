<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="row item-user-info">
	<ul>
		<li><b>@${user.username}</b></li>
		<c:if test="${user.username == userSession.username }">
			<li><a class="btn btn-default" href="modifyProfile" id="modify-profile-btn">modifica profilo</a></li>
		</c:if>
		<li></li>
	</ul>
</div>
<div class="row item-user-info">
	<ul>
		<li><span>${fn:length(user.posts)}</span> post</li>
		<li><span>${fn:length(user.followers)}</span> follower</li>
		<li><span>${fn:length(user.followings)}</span> profili seguiti</li>
	</ul>
</div>
<div class="row item-user-info" style="padding-bottom:0% !important">
	<ul>
	 <c:choose>
	  <c:when test ="!empty ${user.name}  || !empty ${user.surname}">
			<li>${user.name} ${user.surname}</li>
		</c:when>
		<c:otherwise>
			<li><b>Name</b> & <b>Surname</b> Unknow ..</li>
		</c:otherwise>
	</c:choose>
	</ul>
</div>
<div class="row item-user-info" style="padding-top:0% !important">
	<ul>
		<li><b>Biography</b> ${user.biography }</li>
	</ul>
</div>
<c:if test="${user.username != userSession.username }">
<div class="row item-user-info">
	<ul>
		<c:choose>
			<c:when test="${user.followed == false }">
				<li><button>Follow</button></li>
			</c:when>
			<c:otherwise>
				<li><button>Unfollow</button></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
</c:if>
