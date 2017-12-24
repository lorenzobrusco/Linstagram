<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/animate.min.css">
<script
	src="${pageContext.request.contextPath}/resources/js/lib/animatedModal.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/dropzone.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/create_post_modal_style.css">
<script
	src="${pageContext.request.contextPath}/resources/js/lib/dropzone.js"></script>

<!-- TODO add modal for update photoProfile -->

<div class="photo">
	<div class="form-inline-profile">
		<div class="input-inline">
			<img class="user-img" src='${user.photoProfile}'>
		</div>
		<c:set var="name" value="${user.name}"/>
		<c:set var="surname" value="${user.surname}"/>
		<c:if test="${ empty name  && empty surname}">
			<label for="name"><i>Name Surname<i></label>
		</c:if>
		<c:if test="${ not empty name  || not empty surname}">
			<label for="name">${user.name} ${user.surname}</label>
		</c:if>
		<br> <a
			id="change-photo-profile" href="#upload-photoprofile-modal">Change
			profile image</a>
	</div>
</div>



<div id="upload-photoprofile-modal">
	<!--THIS IS IMPORTANT! to close the modal, the class name has to match the name given on the ID  class="close-animatedModal" -->
	<div id="modal-card">
		<div class="modal-header">
			<span class="modal-title">Upload Photo Profile</span>
			<div class="close-upload-photoprofile-modal"></div>
		</div>
		<div class="modal-body">
			<form class="dropzone" id="post-dropzone">
				<!-- this is were the previews should be shown. -->
				<div class="fallback">
					<input name="file" type="file" />
				</div>
				<div class="dropzone-previews">
					<div class="dz-default dz-message">
						<span>Drop your photos here</span>
					</div>
				</div>
				<button class="btn btn-submit" id="submit-photo">
					<i class="fa fa-paper-plane" aria-hidden="true"></i> Upload Photo
				</button>
			</form>
		</div>
	</div>
</div>

<script>
Dropzone.autoDiscover = false;

$(document).ready(function(){
	var modalConfiguration = {
			modalTarget: 'upload-photoprofile-modal',
			animatedIn: 'lightSpeedIn',
			animatedOut: 'bounceOutDown',
			color: '#fafafa80',
			// Callbacks
			beforeOpen: function () {},
			afterOpen: function () {},
			beforeClose: function () {},
			afterClose: function () {
			
			}
	}

	$("#change-photo-profile").animatedModal(modalConfiguration);
	
	dropzoneOptions = {
			url: 'uploadPhotoProfile',
			autoProcessQueue: false,
			uploadMultiple: false,
			maxFiles: 1,
			maxFilesize: 20, //MB
			acceptedFiles: "image/*",
			addRemoveLinks: true,
			dictDefaultMessage: 'Drop yuor photos here',
			//     Tweek dropzone to use another container for file previews
			previewsContainer: ".dropzone-previews",
			init: function () {
				var myDropzone = this;
				var submit_file = $('#submit-photo');
				submit_file.prop("disabled", true);
				
				this.on("thumbnail", function (file) {
					if (myDropzone.getAcceptedFiles().length > 0) {
						//enable submit_file
						$("div.dz-default.dz-message").addClass("hide");
						submit_file.prop("disabled", false);
					}
				});

				this.on("removedfile", function (file) {
					//console.log(myDropzone.getAcceptedFiles());
					//disable submit_file
					if (myDropzone.getAcceptedFiles().length == 0) {
						$("div.dz-default.dz-message").removeClass("hide");
						submit_file.prop("disabled", true);
					}
				});
				
				$("#submit-photo").click(e => {
					e.preventDefault();
					e.stopPropagation();
					var rejected_files = myDropzone.getRejectedFiles();
					for (var rejected_file of rejected_files) {
						myDropzone.removeFile(rejected_file);
					}
-					myDropzone.processQueue();
				})
				
				this.on("success", function (files, response) {
					$(".close-upload-photoprofile-modal").click(); //close modal
				});
			} //close init
	};	//close option
	
	var uploader = document.querySelector('#post-dropzone');
	//	CREATE DROPZONE
	var myDropzone = new Dropzone(uploader,dropzoneOptions);
	
	
	
});
</script>