<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/animate.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/create_post_modal_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/dropzone.css">

<script
	src="${pageContext.request.contextPath}/resources/js/create_post_modal.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/animatedModal.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/camanjs/4.0.0/caman.full.min.js"></script>


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
				<button type="submit" class="btn btn-success btn-create" id="submit-file">
					<i class="fa fa-paper-plane" aria-hidden="true"></i> Confirm
				</button>
				<div class="fallback">
					<input name="file" type="file" multiple />
				</div>
				<div class="dropzone-previews">
					<div class="dz-default dz-message">
						<span>Drop yuor photos or videos here</span>
					</div>
				</div>
			</form>
			<div id="apply-filter-section" class="hide">
				<div class="btn-group" role="group" id="filter-btn-group">
					<button type="button" class="btn btn-default" id="vintage">Vintage</button>
					<button type="button" class="btn btn-default" id="lomo">Lomo</button>
					<button type="button" class="btn btn-default" id="clarity">Clarity</button>
					<button type="button" class="btn btn-default" id="jarques">Jarques</button>
				</div>
			</div>
			<!--TODO: CALL WITH AJAX FOR HIDE MODEL AND CONFIRM CREATION -->
			<form id="post-description" class="hide" action="createPost">
				<label for="post-description-input">Enter a description:</label>
				<textarea class="form-control" rows="5" id="post-description-input"
					name="postDescription"> </textarea>
				<button type="submit" class="btn btn-success btn-create">
					<i class="fa fa-paper-plane" aria-hidden="true"></i> Confirm
				</button>
			</form>

		</div>
	</div>
</div>

