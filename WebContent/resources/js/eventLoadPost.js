//EVENTI PER SWITCHARE LA PAGINA DEI POST/TAGGED/BOOKMARK NEL PROFILO UTENTE.
//NEL CASO DI UN ALTRO PROFILO, IL BOOKMARK NON VERRÀ CHIAMATO PERCHÈ NON C'È LA SCHEDA.

$(document).ready(function() {
	
	//EVENTO PER CARICARE I "POST PUBBLICATI" DALL'UTENTE
	$("#post_user").on('click', function() {
		var username = $('#username_hidden').val();
		
		$.ajax({
			url : "postPhoto",
			data:{username:username},
			success : function(result) {
				$("#colum").html(result);
			}
		});
	});
	
	//EVENTO PER CARICARE I "TAGGED POST" DELL'UTENTE
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

	//EVENTO PER CARICARE I "BOOKMARKS POST" DELL'UTENTE
	$("#bookmarks").on('click', function() {
		$.ajax({
			url : "bookmarkPhoto",
			success : function(result) {
				$("#bookmark").html(result);
			}
		});
	});
});