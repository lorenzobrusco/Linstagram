function restart(video) {
	var parent = video.parent();
	parent.find(".tag-pause").addClass("hide");
	parent.find(".tag-play").removeClass("hide");
}
$(document).ready(function() {
	$(document).on("click", ".tag-play",function(e) {
		console.log($(this));
		var video = $(this).parent().find("video");
		video[0].play();
		$(this).addClass("hide");
		$(this).parent().find(".tag-pause").removeClass("hide");
	});
	
	$(document).on("click", ".tag-pause",function(e) {
		console.log($(this));
		var video = $(this).parent().find("video");
		video[0].pause();
		$(this).addClass("hide");
		$(this).parent().find(".tag-play").removeClass("hide");
	});
	
	$(document).on("click", ".tag-audio",function(e) {
		console.log($(this));
		var video = $(this).parent().find("video");
		video[0].muted = !video[0].muted;
		if(video[0].muted){
			$(this).empty().append("<i class='fa fa-volume-off fa-1x' aria-hidden='true'>");
		}else{
			$(this).empty().append("<i class='fa fa-volume-up fa-1x' aria-hidden='true'>");
		}
	});
	
});

