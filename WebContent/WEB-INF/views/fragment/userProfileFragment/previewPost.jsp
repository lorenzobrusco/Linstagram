<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:forEach items="${posts}" var="post">
	<!--  Il numero dei like e comment e post-->
	<div class="col-md-4 col-sm-6 post-section">
		<div id="${post.id }" class="post">
			<c:forEach items="${post.media}" var="media">
				<c:if test="${media.type.value == 0}">
					<a href="post?id=${post.id }"> <video width="300px"
							height="300px" style="background: black;" autoplay loop muted
							preload="auto">
							<source class="picture" src="${media.url}" type="video/mp4">
						</video>
					</a>
				</c:if>
				<c:if test="${media.type.value == 1}">
					<div class="crop-img">
						<a href="post?id=${post.id }"> <img
							class="picture img-responsive" src="${media.url }">
						</a>
					</div>
				</c:if>
			</c:forEach>
			<div class="links">
				<a><span class="fa fa-heart"> ${post.nLikes}</span></a> <a><span
					class="fa fa-comment"> ${post.nComments}</span></a>
			</div>
		</div>
	</div>
</c:forEach>