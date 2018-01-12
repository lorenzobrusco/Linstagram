<!-- VISUALIZZAZIONE "TAGGED POST" DELL'UTENTE [Sia per utente nella sessione che per gli altri] -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${fn:length(posts) == 0}">
	<div id="tag">
		<div class="tags">
			<span></span>
			<div>No Tags</div>
			<br>
			<div id="contentTag">
				There isn't any tagged posts in this profile.<br>
			</div>
		</div>
	</div>
</c:if>
<jsp:include page="previewPost.jsp"></jsp:include>
