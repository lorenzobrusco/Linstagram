Dropzone.autoDiscover = false;


function resize_canvas(canvas) {
	canvas.css("width", "100%");
	canvas.css("height", "100%");
}

function getContextPath() {
	var domanin=document.location.origin;
	return domanin+window.location.pathname.substring(0, window.location.pathname.indexOf("/",2))+"/";
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
			previewsContainer: ".dropzone-previews",
			init: function () {
				var myDropzone = this;
				var submit_button = $('#submit-file');
				submit_button.prop("disabled", true);
				this.on("thumbnail", function (file) {
					if (myDropzone.getAcceptedFiles().length > 0) {
						//enable submit_button
						$("div.dz-default.dz-message").addClass("hide");
						submit_button.prop("disabled", false);
					}
				});

				this.on("removedfile", function (file) {
					//console.log(myDropzone.getAcceptedFiles());
					//disable submit_button
					if (myDropzone.getAcceptedFiles().length == 0) {
						$("div.dz-default.dz-message").removeClass("hide");
						submit_button.prop("disabled", true);
					}
				});

				submit_button.on("click", function (e) {
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
					myDropzone.processQueue(); 
				});


				this.on("successmultiple", function (files, response) {
					console.log(response[0].url);
					//hide dropzone section
					$("#post-dropzone").addClass("hide");
					var file = myDropzone.getAcceptedFiles();

					var filter_section = $("#apply-filter-section");
//					filter_section.append("<canvas class='filter-img' id='img-to-modify'></canvas>");
					filter_section.append("<img class='filter-img' src="+file[0].dataURL+"></img>");
					var post_description_section = $("#post-description");
					var loader=$('#loader');
					var submit_filter=$('#submit-filter');



					Caman("#apply-filter-section img",function () {
//						var canvas=$('#img-to-modify');
						//create and adapt canvas to screen size
//					createCanvas(canvas.get(0),file[0].dataURL);
						var canvas=$('#apply-filter-section > canvas');
						resize_canvas(canvas);
						filter_section.append('<button class="btn btn-success btn-create"  id="submit-filter"><i class="fa fa-paper-plane" aria-hidden="true"></i> Confirm</button>')
						filter_section.removeClass("hide");

						$("#apply-filter-section #filter-btn-group button").click(function () {
							var filterType = $(this).attr("id");
							console.log(filterType);
							Caman(canvas[0], function () {
								startFilter(canvas);
								this.revert(); //revert previous filter
								eval("this." + filterType + "().render(function () {resize_canvas(canvas);endFilter(canvas);} );");
							});
						});

						function endFilter(canvas){
							loader.addClass('hide');
							$('#apply-filter-section .btn').removeClass('hide');
							canvas.removeClass('hide');
							submit_filter.removeClass('hide');
						}

						function startFilter(canvas){
							canvas.addClass("hide");
							$('#apply-filter-section .btn').addClass('hide');
							submit_filter.addClass('hide');
							loader.removeClass("hide");
						}


						//submit filter event -> go to description section
						$("#submit-filter").on("click", function () {
							endFilter(canvas);
							//TODO send modified image
							filter_section.addClass("hide");
							post_description_section.removeClass("hide");
						});
					}); // close starter caman


				}); //successfullmultiple
			} //close init
	}; //close option

	var uploader = document.querySelector('#post-dropzone');
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
				$("#post-dropzone").removeClass("hide");
				$("#apply-filter-section").empty();
				$("#apply-filter-section").addClass("hide");
				$("#post-description").addClass("hide");
				$("#post-description-input").val("")//
				//clean dropzone uploads
				myDropzone.removeAllFiles(true);
			}
	}

	$("#open-create-post-modal").animatedModal(modalConfiguration);
	$("#add-mobile").animatedModal(modalConfiguration);
});