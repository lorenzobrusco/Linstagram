<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<ul>
<c:forEach items="${followings}" var="follow">
	<li id="users">
		<div id="user_enter">
			<form role="form" action="userPage">
				<button id="button_form" name="usernameOther" value="${follow.username }">
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
				<div id="fol-div${follow.id }">
					<c:choose>
						<c:when test="${follow.followed == true }">
							<div id="button${follow.id }" class="pull-right">
								<button name="${follow.id }" value="${follow.username }" id="unfollow-btn">Unfollow</button>
							</div>
						</c:when>
						<c:otherwise>
							<div id="button${follow.id }" class="pull-right">
								<button name="${follow.id }" value="${follow.username }" id="follow-btn">Follow</button>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>
			</div>
		</li>
	</c:forEach>
</ul>