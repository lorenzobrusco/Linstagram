//EVENTI PER SWITCHARE LA PAGINA DEI POST/TAGGED/BOOKMARK NEL PROFILO UTENTE.
//NEL CASO DI UN ALTRO PROFILO, IL BOOKMARK NON VERRÀ CHIAMATO PERCHÈ NON C'È LA SCHEDA.

$(document).ready(function() {
	
	//EVENTO PER CARICARE I "POST PUBBLICATI" DALL'UTENTE
	$("#post_user").on('click', function() {
		lastScrollTop=0;
		entered=false;
		$("#colum").empty();
		var active_tab = "Posts";
		console.log("Active tab post click:"+active_tab);
		lazyLoadContent(active_tab);
	});
	
	//EVENTO PER CARICARE I "TAGGED POST" DELL'UTENTE
	$("#tags").on('click', function() {
				lastScrollTop=0;
				entered=false;
				$("#tag").empty();
				var active_tab = "Tags";
				console.log("Active tab tag click:"+active_tab);
				lazyLoadContent(active_tab);
	});

	//EVENTO PER CARICARE I "BOOKMARKS POST" DELL'UTENTE
	$("#bookmarks").on('click', function() {
		lastScrollTop=0;
		entered=false;
		$("#bookmark").empty();
		var active_tab = "Bookmarks";
		console.log("Active tab tag click:"+active_tab);
		lazyLoadContent(active_tab);
	});
});