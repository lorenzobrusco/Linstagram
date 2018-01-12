<!-- BODY PER IL POPUP DEI FOLLOWING DEL PROFILO UTENTE [NON LOGGATO, QUINDI GLI ALTRI UTENTI] -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal_follow_style.css">

<ul>
<c:forEach items="${followings}" var="follow">
	<li id="users">
		<div id="user_enter">
			<form role="form" action="userPage">
				<button id="button_form" name="username" value="${follow.username }">
					<div id="button_user">
						<div id="img_div">
							<img id="img" src="${follow.photoProfile }"></img>
						</div>
							
						<div id="info_div">
							<div id="username${follow.id }"><b>${follow.username }</b></div>
							<div id="name">${follow.name } ${follow.surname }</div>
						</div>
					</div>
				</button>
			</form>
			<c:if test="${follow.username != user.username }">
				<input type="hidden" id="private${follow.id }" value="${follow.privateProfile }"/>
				<div id="fol-div${follow.id }">
					<c:choose>
						<c:when test="${follow.privateProfile == false }">
							<div id="buttonFollow${follow.id }" class="pull-right">
								<c:choose>
									<c:when test="${follow.followed == true }">
											<button class="button" name="${follow.id }" value="${follow.username }" id="unfollower-btn">Unfollow</button>
									</c:when>
									<c:otherwise>
											<button class="button" name="${follow.id }" value="${follow.username }" id="follower-btn">Follow</button>
									</c:otherwise> 
							 	</c:choose>
							</div>
						</c:when>
						<c:otherwise>
							<div id="buttonFollow${follow.id }" class="pull-right">
								<c:choose>
									<c:when test="${follow.request_send == false && follow.request_received == false && follow.followed == false}">
											<button name="${follow.id }" value="${follow.username }" id="sendRequestPopup-btn">Follow</button>
									</c:when>
									<c:when test="${follow.request_send == false && follow.request_received == false && follow.followed == true}">
										<button name="${follow.id }" value="${follow.username }" id="unfollower-btn">Unfollow</button>
									</c:when>
									<c:when test="${follow.request_send == true }">
										<button disabled id="waiting-btn">Waiting...</button>
									</c:when> 
									<c:when test="${follow.request_received == true }">
										<button name="${follow.id }" value="${follow.username }" id="cancelRequestPopup-btn">Delete Request</button>
									</c:when>
								</c:choose>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>
			</div>
		</li>
	</c:forEach>
</ul>