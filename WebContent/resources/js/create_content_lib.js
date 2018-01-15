const acceptable_type = ["story","post"];

Dropzone.autoDiscover = false;

function resize_canvas(canvas) {
	canvas.css("width", "100%");
	//	canvas.css("height", "100%");
}

var complete_noty = new Noty({
	text: '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Complete!</p> Good! Your post is created!',
	theme: 'nest',
	type: 'success',
	layout: 'bottomLeft',
	timeout:2000,
	progressBar: true
}).on("onClose",function(){location.reload()});

var complete_noty_story = new Noty({
	text: '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Complete!</p> Good! Your story has been added!',
	theme: 'nest',
	type: 'success',
	layout: 'bottomLeft',
	timeout:2000,
	progressBar: true
});

var error_noty_story = new Noty({
	text: '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Failed!</p> You story has not been added!',
	theme: 'nest',
	type: 'error',
	layout: 'bottomLeft',
	timeout:4000,
	progressBar: true
})

function UploadPic(canvas, filename, container) {
	canvas = canvas.get(0); //go from jquery obj to normal js obj
	canvas.toBlob(function(blob){
		//		console.log(blob);
		var formData = new FormData();
		formData.append('file', blob,filename);
		var postDescription=$("#post-description-input").val();
		//preventing injection
		var safetext = $( $.parseHTML(postDescription) ).text();

		formData.append('postDescription', safetext);
		formData.append('type', 'image');
		$.ajax({
			type: 'POST',
			url: 'createPost',
			enctype: 'multipart/form-data',
			data: formData,
			processData: false,
			contentType: false,
			success: function(msg) {
				$(".close-create-post-modal").click(); //close modal
				complete_noty.show();

			}
		});
	});
}

function UploadFile(file, filename, type, container) {
	var formData = new FormData();
	formData.append('file', file, filename);
	var postDescription=$("#post-description-input").val();
	//preventing injection
	var safetext = $( $.parseHTML(postDescription) ).text();

	formData.append('postDescription', safetext);
	formData.append('type', type);
	$.ajax({
		type: 'POST',
		url: 'createPost',
		enctype: 'multipart/form-data',
		data: formData,
		processData: false,
		contentType: false,
		success: function(msg) {
			$(".close-create-post-modal").click(); //close modal
			complete_noty.show();
		}
	});
}

function UploadFileStory(file, filename, type, container) {
	var formData = new FormData();
	formData.append('file', file, filename);
	formData.append('type', type);
	$.ajax({
		type: 'POST',
		url: 'addStory',
		enctype: 'multipart/form-data',
		data: formData,
		processData: false,
		contentType: false,
		success: function(data) {
			if(data != null){
				Stories.addStoryItem(data);
				complete_noty_story.show();
				$(".close-story-modal").click(); //close modal
			}	else{
				error_noty_story.show();
			}
		}
	});

}

function UploadPicStory(canvas,filename) {
	canvas = canvas.get(0); //go from jquery obj to normal js obj
	canvas.toBlob(function(blob){
		var formData = new FormData();
		formData.append('file', blob,filename);
		formData.append('type', "image");
		$.ajax({
			type: 'POST',
			url: 'addStory',
			enctype: 'multipart/form-data',
			data: formData,
			processData: false,
			contentType: false,
			success: function(data) {
//				console.log(data);
				if(data != null){
					Stories.addStoryItem(data);
					complete_noty_story.show();
					$(".close-story-modal").click(); //close modal
				}	else{
					error_noty_story.show();
				}
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

function setupContentCreator(container , type) {

	var dropzone_area = "";
	var final_submit = "";
//	console.log(type)
//	console.log(container)

	if (type == "story"){
		dropzone_area = container +" #story-dropzone";
		final_submit = container+" #submit-filter";
	}

	else if (type == "post"){
		dropzone_area = container +" #post-dropzone";
		final_submit = container+" #submit-description";
	}

	else{
		console.err("not allowed type");
		return;
	}

	function endFilter(canvas){
		$(container+' #loader').addClass('hide');
		$(container+' #filter-btn-group').removeClass('hide');
		canvas.removeClass('hide');
		$(container+' #submit-filter').removeClass('hide');
	}

	function startFilter(canvas){
		canvas.addClass("hide");
		$(container+' #filter-btn-group').addClass('hide');
		$(container+' #submit-filter').addClass('hide');
		$(container+' #loader').removeClass("hide");
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
				acceptedFiles: "image/*, video/*",
				addRemoveLinks: true,
				dictDefaultMessage: 'Drop yuor photos or videos here',
				//     Tweek dropzone to use another container for file previews
				previewsContainer: container+" .dropzone-previews",

				init: function () {
					var myDropzone = this;
					var file;
					var submit_file = $(container+' #submit-file');
					var filename="";
					submit_file.prop("disabled", true);

					this.on("thumbnail", function (file) {
						if (myDropzone.getAcceptedFiles().length > 0) {
							$(container+" div.dz-default.dz-message").addClass("hide");
							submit_file.prop("disabled", false);
						}
					});

					this.on("addedfile", function(file) { 
						if(~file.type.indexOf("video")){
							$(container+" div.dz-default.dz-message").addClass("hide");
							submit_file.prop("disabled", false);
						}
					});


					this.on("removedfile", function (file) {
						//console.log(myDropzone.getAcceptedFiles());
						//disable submit_file
						if (myDropzone.getAcceptedFiles().length == 0) {
							$(container+" div.dz-default.dz-message").removeClass("hide");
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

						//hide dropzone section
						$(dropzone_area).addClass("hide");
						file = myDropzone.getAcceptedFiles();
						filename = file[0].name;

						//if file is an image but not a git show filter
						if(~file[0].type.indexOf("image") && !(~file[0].type.indexOf("gif"))){
							var filter_section = $(container+" #apply-filter-section");
							var canvas_cont = $(container+" #apply-filter-section .canvas-cont");
//							filter_section.append("<canvas class='filter-img' id='img-to-modify'></canvas>");
							canvas_cont.append("<canvas class='filter-img' id='img-to-modify'></canvas>");
//							filter_section.append('<button class="btn btn-submit"  id="submit-filter"><i class="fa fa-paper-plane" aria-hidden="true"></i> Apply Filter </button>');

							var canvas=$(container+' .canvas-cont canvas');

							createCanvas(canvas.get(0),file[0].dataURL);
							resize_canvas(canvas);
							filter_section.removeClass("hide");
						}
						
						
						if(type=="story" && (~file[0].type.indexOf("gif") || ~file[0].type.indexOf("video"))) {
							if(~file[0].type.indexOf("video"))
								UploadFileStory(file[0], filename, "video",container);
							else if(~file[0].type.indexOf("gif"))
								UploadFileStory(file[0],filename, "image",container);
							return;
						}

						if(type != "story"){
							var post_description_section = $(container+' #post-description');
							if(~file[0].type.indexOf("video") || ~file[0].type.indexOf("gif") ){
								post_description_section.removeClass("hide");
							}
						}

						$(container+" #apply-filter-section #filter-btn-group button").click(function () {
							var filterType = $(this).attr("id");
//							console.log(filterType);
							$(container+" #filter-btn-group button .img-small").removeClass("filter-active");
							$(this).find('.img-small').addClass("filter-active");
							Caman(canvas[0], function () {
								startFilter(canvas);
								this.revert(); //revert previous filter
								if(filterType!="normal"){
									eval("this." + filterType + "().render(function () {resize_canvas(canvas);endFilter(canvas);} );");
								} else if(filterType=="normal"){
									resize_canvas(canvas);
									endFilter(canvas);
								}
							});
						});

						if(type != "story"){
							//submit filter event -> go to description section (only post)
							$(container+' #submit-filter').on("click", function () {
								var canvas = $(container+' #apply-filter-section > canvas');
								endFilter(canvas);
								filter_section.addClass("hide");
								post_description_section.removeClass("hide");
							});
						}

					});		//close pic/video submit

//					console.log(final_submit);
//					console.log($(final_submit));

					$(final_submit).click(e => {
						console.log(file[0].type);
						var canvas = $(container+' .canvas-cont canvas');
						
						if(type == "story") {
							if(~file[0].type.indexOf("video"))
								UploadFileStory(file[0], filename, "video",container);
							else if(~file[0].type.indexOf("gif"))
								UploadFileStory(file[0],filename, "image",container);
							else {
								console.log("log");
								UploadPicStory(canvas,filename); 
							}
						} 
						
						else if(type == "post"){
							if(~file[0].type.indexOf("video"))
								UploadFile(file[0], filename, "video",container);
							else if(~file[0].type.indexOf("gif"))
								UploadFile(file[0],filename, "image",container);
							else 
								UploadPic(canvas,filename); 
							} 

//						console.log(file[0].type);
//						var canvas = $(container+' .canvas-cont canvas');
////					myDropzone.processQueue(); 
//						if(~file[0].type.indexOf("video")){
//						UploadFile(file[0], filename, "video",container);
//						} 
//						else if(~file[0].type.indexOf("image")) {
//						if(type == "story"){
//						console.log("ok");
//						endFilter(canvas);
//						UploadPicStory(canvas,filename); 
//						}
//						else if(~file[0].type.indexOf("gif"))
//						UploadFile(file[0],filename, "image",container);
//						else 
//						UploadPic(canvas,filename); 
//						} 

					});

//					this.on("successmultiple", function (files, response) {});

				} //close init
		}; //close option

		//	CREATE DROPZONE
		var myDropzone = new Dropzone(dropzone_area,dropzoneOptions);

		var container_name = container.replace("#","");

		//	Active modal
		var modalConfiguration = {
				modalTarget: container_name,
				animatedIn: 'lightSpeedIn',
				animatedOut: 'bounceOutDown',
				color: '#fafafa80',
				// Callbacks
				beforeOpen: function () {},
				afterOpen: function () {},
				beforeClose: function () {},
				afterClose: function () {
					$(dropzone_area).removeClass("hide");
//					$("#apply-filter-section").empty();
					$(container+" #img-to-modify").remove();
					$(container+" #apply-filter-section").addClass("hide");
					$(container+" #filter-btn-group button .img-small").removeClass("filter-active");

					if(type == "post"){
						$(container+" #post-description").addClass("hide");
						$(container+" #post-description-input").val("")//
					}
					//clean dropzone uploads
					myDropzone.removeAllFiles(true);
				}
		}

		$("#open-create-post-modal").animatedModal(modalConfiguration);
//		$("#add-mobile").animatedModal(modalConfiguration);

		$("#open-story-modal").animatedModal(modalConfiguration);
//		$("#add-story").animatedModal(modalConfiguration);
		$("#create-story-btn").animatedModal(modalConfiguration);

		var sneackbar = $("#snackbar");

		$("#add-mobile").click( e =>{
			sneackbar.addClass("show");
		});

		$("#create-post-btn").animatedModal(modalConfiguration);

		$("#snackbar .btn").click(e=>{sneackbar.removeClass("show");})

		$(document).mouseup(function(e) {
			if (!sneackbar.is(e.target) && sneackbar.has(e.target).length === 0) {
				sneackbar.removeClass("show");
			}
		});

	});






}
