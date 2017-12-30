<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- VISUALIZZAZIONE DEI POST DELL'UTENTE [utente della sessione e altri] -->

<c:if test="${fn:length(user.bookmarks) == 0}">
	<div class="bookmark">
		<span></span>
		<div>Bookmark</div>
		<br>
		<div id="contentPost">
			Non sono stati salvati post.<br>
		</div>
	</div>
</c:if>

<c:forEach items="${user.bookmarks}" var="post">
	<!--  Il numero dei like e comment e post-->
	<div class="col-md-4 col-sm-6 post-section">
		<div id="${post.id }" class="post" data-toggle="modal" data-target="#modalPost">
			<c:forEach items="${post.media}" var="media">
				<img class="picture img-responsive" src="${media.url }">
			</c:forEach>
			<div class="links">
				<a href=""><span class="fa fa-heart"> ${fn:length(post.likes)}</span></a> <a
					href=""><span class="fa fa-comment"> ${fn:length(post.comments)}</span></a>
			</div>
		</div>
	</div>
</c:forEach>