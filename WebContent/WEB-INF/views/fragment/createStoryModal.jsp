<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/animate.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/create_post_modal_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/dropzone.css">
	
<script src="${pageContext.request.contextPath}/resources/js/lib/dropzone.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/js/story_modal.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/lib/animatedModal.min.js"></script>

<!--  <script src="https://cdnjs.cloudflare.com/ajax/libs/camanjs/4.0.0/caman.full.min.js"></script> -->
	<script src="${pageContext.request.contextPath}/resources/js/lib/camanjs.js"></script>
	
<!--CREATE STORY MODAL-->
<div id="story-modal">
	<!--THIS IS IMPORTANT! to close the modal, the class name has to match the name given on the ID  class="close-animatedModal" -->
	<div id="modal-card">

		<div class="modal-header">
			<span class="modal-title">Create Story</span>
			<div class="close-story-modal"></div>
		</div>

		<div class="modal-body">

			<form class="dropzone" id="story-dropzone">
				<!-- this is were the previews should be shown. -->
				<div class="fallback">
					<input name="file" type="file" multiple />
				</div>
				<div class="dropzone-previews">
					<div class="dz-default dz-message">
						<span>Drop your photos or videos here</span>
					</div>
				</div>
				<button class="btn btn-submit" id="submit-file">
					<i class="fa fa-paper-plane" aria-hidden="true"></i> Upload Media
				</button>
			</form>
					<div id="apply-filter-section" class="hide">
				<div id="filter-btn-group">
					<button type="button" class="btn-transparent" id="normal"><img class="img-circle img-small filter-active" src="${pageContext.request.contextPath}/resources/images/filters/37.jpg"/></button>
					<button type="button" class="btn-transparent" id="vintage"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/19.jpg"/></button>
					<button type="button" class="btn-transparent" id="lomo"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/20.jpg"/></button>
					<button type="button" class="btn-transparent" id="clarity"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/21.jpg"/></button>
					<button type="button" class="btn-transparent" id="jarques"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/22.jpg"/></button>
					<button type="button" class="btn-transparent" id="sinCity"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/23.jpg"/></button>
					<button type="button" class="btn-transparent" id="sunrise"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/24.jpg"/></button>
					<button type="button" class="btn-transparent" id="crossProcess"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/25.jpg"/></button>
					<button type="button" class="btn-transparent" id="orangePeel"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/26.jpg"/></button>
					<button type="button" class="btn-transparent" id="love"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/27.jpg"/></button>
					<button type="button" class="btn-transparent" id="grungy"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/28.jpg"/></button>
					<button type="button" class="btn-transparent" id="pinhole"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/29.jpg"/></button>
					<button type="button" class="btn-transparent" id="oldBoot"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/30.jpg"/></button>
					<button type="button" class="btn-transparent" id="glowingSun"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/31.jpg"/></button>
					<button type="button" class="btn-transparent" id="hazyDays"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/32.jpg"/></button>
					<button type="button" class="btn-transparent" id="herMajesty"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/33.jpg"/></button>
					<button type="button" class="btn-transparent" id="nostalgia"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/34.jpg"/></button>
					<button type="button" class="btn-transparent" id="hemingway"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/35.jpg"/></button>
					<button type="button" class="btn-transparent" id="concentrate"><img class="img-circle img-small" src="${pageContext.request.contextPath}/resources/images/filters/36.jpg"/></button>
				</div>
				
				<div id="loader" class="hide"></div>
			</div>
		</div>
	</div>
</div>

