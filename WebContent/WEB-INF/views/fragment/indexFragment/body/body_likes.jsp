<!-- BODY DEL POPUP DEL LIKE DEL POST NELL'HOMEPAGE -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul>
	<c:forEach items="${userLike}" var="user_like">
		<li id="users">
			<div id="user_enter">
				<form role="form" action="userPage">
					<button id="button_form" name="username" value="${user_like.username }">
						<div id="button_user">
	 						<div id="img_div">
	 							<c:choose>
	  								<c:when test="${empty user_like.photoProfile }">
	  									<img id="img" src="https://www.1plusx.com/app/mu-plugins/all-in-one-seo-pack-pro/images/default-user-image.png"></img>
	 								</c:when>
	 								<c:otherwise>
	 									<img id="img" src="${user_like.photoProfile }"></img>
	 								</c:otherwise>
	 							</c:choose>
	 						</div>
	 							
	 						<div id="info_div">
	 							<div id="username${user_like.id }"><b>${user_like.username }</b></div>
	 							<div id="name">${user_like.name } ${user_like.surname }</div>
	 						</div>
						</div>
					</button>
				</form>
			</div>
		</li>
	</c:forEach>
</ul>