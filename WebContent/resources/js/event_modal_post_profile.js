//EVENTO PER APRIRE IL POPUP DI UN SINGOLO POST NEL PROFILO UTENTE

$(document).ready(function() {
	$(document).on('click', '.post', function (e) {
		var post = $(this).attr("id");
		$.get({
			url : "getPost",
			data:{post:post},
			success : function(result) {
				$(".modal-body").empty();
				$(".modal-body").append(result);
			}
		});
		
    });
});