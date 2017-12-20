$(document).ready(function() {
	$('#search-input').focusin(function(){
		$("#search-div").css("width","70%");
	});

	$('#search-input').focusout(function(){
		$("#search-div").css("width","50%");
	});
	$('[data-toggle="tooltip"]').tooltip();
});