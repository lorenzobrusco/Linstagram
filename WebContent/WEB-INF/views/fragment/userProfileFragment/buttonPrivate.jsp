<!-- BOTTONI DEL PROFILO UTENTE PRIVATO [SEND_REQUEST, CANCEL_REQUEST, ACCEPT, REJECT, UNFOLLOW -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- <script src="./resources/js/follow_event/reload_user_follow_popup.js"></script> -->

<input type="hidden" id="private${userPublic.id }" value="${userPublic.privateProfile }"/>
<input id="username_hidden" type="hidden" name="username_hidden" value="${userPublic.username}"/>
<c:choose>
	<c:when test="${userPublic.request_send == false && userPublic.request_received == false && userPublic.followed == false}">
		<li><button name="${userPublic.id }" value="${userPublic.username }" id="followProfile-btn">Follow</button></li>
	</c:when>
	<c:when test="${userPublic.request_send == false && userPublic.request_received == false && userPublic.followed == true}">
		<li><button name="${userPublic.id }" value="${userPublic.username }" id="unfollowProfile-btn">Unfollow</button></li>
	</c:when>
<%-- 	<c:when test="${userPublic.request_send == true }">
		<li><button value="${userPublic.username }" id="acceptRequest-btn" class="btn btn-info">Accept</button></li>
		<li><button value="${userPublic.username }" id="rejectRequest-btn" class="btn btn-secondary">Reject</button></li>
	</c:when> --%>
	<c:when test="${userPublic.request_received == true }">
		<li><button name="${userPublic.id }" value="${userPublic.username }" id="cancelRequest-btn">Delete Request</button></li>
	</c:when>
</c:choose>