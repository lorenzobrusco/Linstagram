<!-- VISUALIZZAZIONE DEI "BOOKMARK POST" DELL'UTENTE [utente della sessione e altri] -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${fn:length(posts) == 0}">
	<div class="bookmark">
		<span></span>
		<div>Bookmark</div>
		<br>
		<div id="contentPost">
			There isn't any bookmarks in this profile.<br>
		</div>
	</div>
</c:if>

<jsp:include page="previewPost.jsp"></jsp:include>