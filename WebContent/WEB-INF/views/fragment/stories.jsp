<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/stories.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/stories_style.css">

</head>
<body>
	<div id="stories">
		<c:forEach items="${followedUsersStories}" var="user">
			<div class="story" data-id="${user.username}"
				data-photo="${user.photoProfile}" data-seen="${user.allSeen}"
				data-last-updated="" data-logged-user="${user.loggedUser}">
				<a href=""><span><u
						style="background-image: url(${user.photoProfile})"></u></span> <span
					class="info"> <strong>${user.username}</strong> <span
						class="time"></span>
				</span> </a>
				<ul class="items">
					<c:forEach items="${user.stories}" var="story">
						<li data-id="${story.id}" data-time="${story.date}"><a
							href="${story.url}" data-time="${story.date}"
							data-seen="${story.viewed}" data-type="${story.type}"
							data-length="3" data-link="" data-linkText=""> <img src="">
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
	</div>

	<%-- 	<jsp:include page="../fragment/viewerModal.jsp"></jsp:include> --%>
	<div class="modal fade" id="removeModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Modal title</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Sicuro di voler eliminare la storia?</p>
				</div>
				<div class="modal-footer">
					<button id="remove-modal-button" type="button" class="btn btn-primary">Rimuovi</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="viewerModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<b>Viewer</b>
					</h4>
				</div>
				<div class="modal-body">
					<ul></ul>
				</div>
			</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/resources/js/lib/stories.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/stories.js"></script>
</body>
</html>