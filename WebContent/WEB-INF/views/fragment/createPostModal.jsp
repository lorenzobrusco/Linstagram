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
	src="${pageContext.request.contextPath}/resources/js/create_post_modal.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/lib/animatedModal.min.js"></script>

<!--  <script src="https://cdnjs.cloudflare.com/ajax/libs/camanjs/4.0.0/caman.full.min.js"></script> -->
	<script src="${pageContext.request.contextPath}/resources/js/lib/camanjs.js"></script>
	
<!--CREATE POST MODAL-->
<div id="create-post-modal">
	<!--THIS IS IMPORTANT! to close the modal, the class name has to match the name given on the ID  class="close-animatedModal" -->
	<div id="modal-card">

		<div class="modal-header">
			<span class="modal-title">Create Post</span>
			<div class="close-create-post-modal"></div>
		</div>

		<div class="modal-body">

			<form class="dropzone" id="post-dropzone">
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
					<button type="button" class="btn btn-default" id="normal">Normal</button>
					<button type="button" class="btn btn-default" id="vintage">Vintage</button>
					<button type="button" class="btn btn-default" id="lomo">Lomo</button>
					<button type="button" class="btn btn-default" id="clarity">Clarity</button>
					<button type="button" class="btn btn-default" id="jarques">Jarques</button>
					<button type="button" class="btn btn-default" id="sinCity">Sin City</button>
					<button type="button" class="btn btn-default" id="sunrise">Sunrise</button>
					<button type="button" class="btn btn-default" id="crossProcess">Cross Process</button>
					<button type="button" class="btn btn-default" id="orangePeel">Orange Peel</button>
					<button type="button" class="btn btn-default" id="love">Love</button>
					<button type="button" class="btn btn-default" id="grungy">Grungy</button>
					<button type="button" class="btn btn-default" id="pinhole">Pinhole</button>
					<button type="button" class="btn btn-default" id="oldBoot">Old Boot</button>
					<button type="button" class="btn btn-default" id="glowingSun">Glowing Sun</button>
					<button type="button" class="btn btn-default" id="hazyDays">Hazy Days</button>
					<button type="button" class="btn btn-default" id="herMajesty">Her Majesty</button>
					<button type="button" class="btn btn-default" id="nostalgia">Nostalgia</button>
					<button type="button" class="btn btn-default" id="hemingway">Hemingway</button>
					<button type="button" class="btn btn-default" id="concentrate">Concentrate</button>
				</div>
				
				<div id="loader" class="hide"></div>
			</div>
			<!--TODO: CALL WITH AJAX FOR HIDE MODEL AND CONFIRM CREATION -->
			<div id="post-description" class="hide">
				<label for="post-description-input">Enter a description:</label>
				<textarea class="form-control" rows="5" id="post-description-input"
					name="postDescription"> </textarea>
				<button class="btn btn-submit" id="submit-description">
					<i class="fa fa-paper-plane" aria-hidden="true"></i> Create Post
				</button>
			</div>

		</div>
	</div>
</div>

