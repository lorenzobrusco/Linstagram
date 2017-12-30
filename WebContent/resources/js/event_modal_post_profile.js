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