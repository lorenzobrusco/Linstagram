<!-- BODY PER IL POPUP DEI FOLLOWER DEL PROFILO UTENTE [LOGGATO E NON] -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul>
	<c:forEach items="${followers}" var="follow">
		<li id="users">
			<div id="user_enter">
				<form role="form" action="userPage">
					<button id="button_form" name="usernameOther" value="${follow.username }">
						<div id="button_user">
	 						<div id="img_div">
	 							<c:choose>
	  								<c:when test="${empty follow.photoProfile }">
	  									<img id="img" src="https://www.1plusx.com/app/mu-plugins/all-in-one-seo-pack-pro/images/default-user-image.png"></img>
	 								</c:when>
	 								<c:otherwise>
	 									<img id="img" src="${follow.photoProfile }"></img>
	 								</c:otherwise>
	 							</c:choose>
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
												<button name="${follow.id }" value="${follow.username }" id="sendRequestPopup-btn">Send Request</button>
										</c:when>
										<c:when test="${follow.request_send == false && follow.request_received == false && follow.followed == true}">
											<button name="${follow.id }" value="${follow.username }" id="unfollower-btn">Unfollow</button>
										</c:when>
 										<c:when test="${follow.request_send == true }">
											<button disabled id="waiting-btn">Waiting...</button>
										</c:when> 
										<c:when test="${follow.request_received == true }">
											<button name="${follow.id }" value="${follow.username }" id="cancelRequestPopup-btn">Cancel Request</button>
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