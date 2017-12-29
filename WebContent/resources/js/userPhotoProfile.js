// eventi per switchare la pagine dei post/tagged/bookmark nel profilo utente
// nel caso di un altro profilo, il bookmark non verrà chiamato perchè non c'è la scheda

$(document).ready(function() {
	$("#tags").on('click', function() {
		var username = $('#username_hidden').val();
		
		$.ajax({
			url : "taggedPhoto",
			data:{username:username},
			success : function(result) {
				$("#tag").html(result);
			}
		});
	});

	$("#bookmarks").on('click', function() {
		$.ajax({
			url : "bookmarkPhoto",
			success : function(result) {
				$("#bookmark").html(result);
			}
		});
	});
});