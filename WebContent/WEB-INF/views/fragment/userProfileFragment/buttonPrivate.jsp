<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="./resources/js/follow_event/event_get_follow.js"></script>

<c:choose>
	<c:when test="${request_send == false && request_received == false && userPublic.followed == false}">
		<li><button value="${userPublic.username }" id="sendRequest-btn">Send Request</button></li>
	</c:when>
	<c:when test="${request_send == false && request_received == false && userPublic.followed == true}">
		<li><button value="${userPublic.username }" id="unfollowerPrivate-btn">Unfollow</button></li>
	</c:when>
	<c:when test="${request_send == true }">
		<li><button value="${userPublic.username }" id="acceptRequest-btn" class="btn btn-info">Accept</button></li>
		<li><button value="${userPublic.username }" id="rejectRequest-btn" class="btn btn-secondary">Reject</button></li>
	</c:when>
	<c:when test="${request_received == true }">
		<li><button disabled id="sendedRequest-btn">Request Sended</button></li>
	</c:when>
</c:choose>