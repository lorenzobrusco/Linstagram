<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${posts}" var="post">
	<!--  Il numero dei like e comment e post-->
	<div class="col-md-4 col-sm-6 post-section">
		<div id="${post.id }" class="post">
			<c:forEach items="${post.media}" var="media">
				<div class="crop-img">
					<a href="post?id=${post.id }"> <img
						class="picture img-responsive" src="${media.url }">
					</a>
				</div>
			</c:forEach>
			<div class="links">
				<a><span class="fa fa-heart"> ${fn:length(post.likes)}</span></a> <a><span
					class="fa fa-comment"> ${fn:length(post.comments)}</span></a>
			</div>
		</div>
	</div>
</c:forEach>