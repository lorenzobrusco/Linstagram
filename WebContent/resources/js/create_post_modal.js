Dropzone.autoDiscover = false;

function resize_canvas(canvas) {
	canvas.css("width", "100%");
//	canvas.css("height", "100%");
}

//function getContextPath() {
//var domanin=document.location.origin;
//return domanin+window.location.pathname.substring(0, window.location.pathname.indexOf("/",2))+"/";
//}

function UploadPic(canvas,filename) {
	canvas.toBlob(function(blob){
//		console.log(blob);
		var formData = new FormData();
		formData.append('file', blob,filename);
		formData.append('postDescription', $("#create-post-modal #post-description-input").val());
		$.ajax({
			type: 'POST',
			url: 'createPost',
			enctype: 'multipart/form-data',
			data: formData,
			processData: false,
			contentType: false,
			success: function(msg) {
				$(".close-create-post-modal").click(); //close modal
//				location.reload(true);
				
				new Noty({
					text: '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Complete!</p> Good! Your post is created!',
					theme: 'nest',
					type: 'success',
					layout: 'bottomLeft',
					timeout:2000,
					progressBar: true
				}).show();
				
			}
		});
	});
}

function createCanvas(canvas,imgURL){
	var ctx = canvas.getContext("2d");

	var MAX_WIDTH = 1920;
	var MAX_HEIGHT = 1080;

	var img = new Image;

	img.onload = function(){
		var width = img.width;
		var height = img.height;

		if (width > height) {
			if (width > MAX_WIDTH) {
				height *= MAX_WIDTH / width;
				width = MAX_WIDTH;
			}
		} else {
			if (height > MAX_HEIGHT) {
				width *= MAX_HEIGHT / height;
				height = MAX_HEIGHT;
			}
		}
		canvas.width = width;
		canvas.height = height;
		ctx.drawImage(img, 0, 0, width, height);
	};
	img.src = imgURL;

}



$(document).ready(function () {
	//	DROPZONE 
	dropzoneOptions = {
			url: 'upload',
			autoProcessQueue: false,
			uploadMultiple: true,
			parallelUploads: 4,
			maxFiles: 1,
			maxFilesize: 20, //MB
			acceptedFiles: "image/*",
			addRemoveLinks: true,
			dictDefaultMessage: 'Drop yuor photos or videos here',
			//     Tweek dropzone to use another container for file previews
			previewsContainer: "#create-post-modal .dropzone-previews",

			init: function () {
				var myDropzone = this;
				var submit_file = $('#create-post-modal #submit-file');
				var filename="";
				submit_file.prop("disabled", true);

				this.on("thumbnail", function (file) {
					if (myDropzone.getAcceptedFiles().length > 0) {
						//enable submit_file
						$("#create-post-modal div.dz-default.dz-message").addClass("hide");
						submit_file.prop("disabled", false);
					}
				});

				this.on("removedfile", function (file) {
					//console.log(myDropzone.getAcceptedFiles());
					//disable submit_file
					if (myDropzone.getAcceptedFiles().length == 0) {
						$("#create-post-modal div.dz-default.dz-message").removeClass("hide");
						submit_file.prop("disabled", true);
					}
				});


				submit_file.on("click", function (e) {
					// Make sure that the form isn't actually being sent.
					e.preventDefault();
					e.stopPropagation();
					// Remove rejected files
					var rejected_files = myDropzone.getRejectedFiles();
					for (var rejected_file of rejected_files) {
						myDropzone.removeFile(rejected_file);
					}
					// IMPORTANT
					// send original file to server ->  CORS policy problem 
					// https://en.wikipedia.org/wiki/Cross-origin_resource_sharing
					// CamanJS can only modify images that come from the same domain as the page in which it's loaded 
//					myDropzone.processQueue(); 
//					console.log(response[0].url);

					//hide dropzone section
					$("#create-post-modal #post-dropzone").addClass("hide");
					var file = myDropzone.getAcceptedFiles();
					filename = file[0].name;
					var filter_section = $("#create-post-modal #apply-filter-section");

					filter_section.append("<canvas class='filter-img' id='img-to-modify'></canvas>");
					filter_section.append('<button class="btn btn-submit"  id="submit-filter"><i class="fa fa-paper-plane" aria-hidden="true"></i> Apply Filter </button>');

					var canvas=$('#create-post-modal #apply-filter-section > canvas');

					createCanvas(canvas.get(0),file[0].dataURL);
					resize_canvas(canvas);
					filter_section.removeClass("hide");

					var post_description_section = $("#create-post-modal #post-description");
					var loader=$('#create-post-modal #loader');
					var submit_filter=$('#create-post-modal #submit-filter');

					$("#create-post-modal #apply-filter-section #filter-btn-group button").click(function () {
						var filterType = $(this).attr("id");
//						console.log(filterType);
						$("#create-post-modal #filter-btn-group button").removeClass("btn-active");
						$(this).addClass("btn-active");
						Caman(canvas[0], function () {
							startFilter(canvas);
							this.revert(); //revert previous filter
							eval("this." + filterType + "().render(function () {resize_canvas(canvas);endFilter(canvas);} );");
						});
					});

					function endFilter(canvas){
						loader.addClass('hide');
						$('#create-post-modal #apply-filter-section .btn').removeClass('hide');
						canvas.removeClass('hide');
						submit_filter.removeClass('hide');
					}

					function startFilter(canvas){
						canvas.addClass("hide");
						$('#create-post-modal #apply-filter-section .btn').addClass('hide');
						submit_filter.addClass('hide');
						loader.removeClass("hide");
					}

					//submit filter event -> go to description section
					$("#create-post-modal #submit-filter").on("click", function () {
						var canvas = $('#create-post-modal #apply-filter-section > canvas');
						endFilter(canvas);
						filter_section.addClass("hide");
						post_description_section.removeClass("hide");
					});


				});

				$("#create-post-modal #submit-description").click(e => {
//					myDropzone.processQueue(); 
					var canvas = $('#create-post-modal #apply-filter-section > canvas').get(0);
					UploadPic(canvas,filename);
				});


//				this.on("successmultiple", function (files, response) {});

			} //close init
	}; //close option

	var uploader = document.querySelector('#create-post-modal #post-dropzone');
	//	CREATE DROPZONE
	var myDropzone = new Dropzone(uploader,dropzoneOptions);

	//	Active modal
	var modalConfiguration = {
			modalTarget: 'create-post-modal',
			animatedIn: 'lightSpeedIn',
			animatedOut: 'bounceOutDown',
			color: '#fafafa80',
			// Callbacks
			beforeOpen: function () {},
			afterOpen: function () {},
			beforeClose: function () {},
			afterClose: function () {
				$("#create-post-modal #post-dropzone").removeClass("hide");
//				$("#apply-filter-section").empty();
				$("#create-post-modal #img-to-modify").remove();
				$("#create-post-modal #submit-filter").remove();
				$("#create-post-modal #apply-filter-section").addClass("hide");
				$("#create-post-modal #filter-btn-group button").removeClass("btn-active");
				$("#create-post-modal #post-description").addClass("hide");
				$("#create-post-modal #post-description-input").val("")//
				//clean dropzone uploads
				myDropzone.removeAllFiles(true);
			}
	}

	$("#open-create-post-modal").animatedModal(modalConfiguration);
	$("#create-post-modal #add-mobile").animatedModal(modalConfiguration);
});
