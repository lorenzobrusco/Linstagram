Dropzone.autoDiscover = false;

$(document).ready(function () {
//	DROPZONE 
	dropzoneOptions = {
			url: 'upload',
			autoProcessQueue: false,
			uploadMultiple: true,
			parallelUploads: 4,
			maxFiles: 4,
			maxFilesize: 50, //MB
			acceptedFiles: "image/*",
			addRemoveLinks: true,
			dictDefaultMessage: 'Drop yuor photos or videos here',
			//     Tweek dropzone to use another container for file previews
			previewsContainer: ".dropzone-previews",
			init: function () {
				var myDropzone = this;

				var submit_button = $('.btn-create');
				submit_button.prop("disabled", true);
				this.on("thumbnail", function (file) {
					if (myDropzone.getAcceptedFiles().length > 0) {
						$("div.dz-default.dz-message").addClass("hide");
						submit_button.prop("disabled", false);
					}
				});

				this.on("removedfile", function (file) {
					console.log(myDropzone.getAcceptedFiles());
					if (myDropzone.getAcceptedFiles().length == 0) {
						$("div.dz-default.dz-message").removeClass("hide");
						submit_button.prop("disabled", true);
					}
				});

				// First change the button to actually tell Dropzone to process the queue.
				this.element.querySelector("button[type=submit]").addEventListener("click", function (e) {
					// Make sure that the form isn't actually being sent.
					e.preventDefault();
					e.stopPropagation();
					myDropzone.processQueue();

					// Remove rejected files
					var rejected_files = myDropzone.getRejectedFiles();
					for (var rejected_file of rejected_files) {
						myDropzone.removeFile(rejected_file);
					}

				});

				// Listen to the sendingmultiple event. In this case, it's the sendingmultiple event instead
				// of the sending event because uploadMultiple is set to true.
				this.on("sendingmultiple", function () {
					// Gets triggered when the form is actually being sent.
					// Hide the success button or the complete form.
				});
				this.on("successmultiple", function (files,
						response) {
					$("#post-dropzone").addClass("hide");
					$("#post-description").removeClass("hide");
					//        	$('#close-post-modal').click();
					// Gets triggered when the files have successfully been sent.
					// Redirect user or notify of success.
				});
				this.on("errormultiple", function (files,
						response) {
					// Gets triggered when there was an error sending the files.
					// Maybe show form again, and notify user of error
				});
			} //close init
	}; //close option

	var uploader = document
	.querySelector('#post-dropzone');
//	CREATE DROPZONE
	var myDropzone = new Dropzone(uploader,
			dropzoneOptions);

//	Active modal
	var modalConfiguration=
	{
			modalTarget: 'create-post-modal',
			animatedIn: 'lightSpeedIn',
			animatedOut: 'bounceOutDown',
			color: '#fafafa80',
			// Callbacks
			beforeOpen: function () {},
			afterOpen: function () {},
			beforeClose: function () {},
			afterClose: function () {
				$("#post-description-input").val("");
				//clean dropzone uploads
				myDropzone.removeAllFiles(true);
			}
	}

	$("#open-create-post-modal").animatedModal(modalConfiguration);
	$("#add-mobile").animatedModal(modalConfiguration);
});