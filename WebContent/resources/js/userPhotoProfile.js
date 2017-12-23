// eventi per switchare la pagine dei post/tagged/bookmark nel profilo utente
// nel caso di un altro profilo, il bookmark non verrà chiamato perchè non c'è la scheda

$(document).ready(function() {
	$("#tags").on('click', function() {
		var username = $('#username_hidden').val();
		
		$.ajax({
			url : "taggedPhoto",
			data:{username:username},
			success : function(result) {
				/* $("#tag").empty(); */
				$("#tag").html(result); //TODO fa un brutto effetto la prima volta!
			}
		});
	});

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