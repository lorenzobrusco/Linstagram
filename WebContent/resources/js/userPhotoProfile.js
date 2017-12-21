$(document).ready(function() {
	$("#tags").on('click', function() {
		$.ajax({
			url : "taggedPhoto",
			success : function(result) {
				/* $("#tag").empty(); */
				$("#tag").html(result); //TODO fa un brutto effetto la prima volta!
			}
		});
	});
});

$(document).ready(function() {
	$("#bookmarks").on('click', function() {
		$.ajax({
			url : "bookmarkPhoto",
			success : function(result) {
				/* $("#tag").empty(); */
				$("#bookmark").html(result); //TODO fa un brutto effetto la prima volta!
			}
		});
	});
});