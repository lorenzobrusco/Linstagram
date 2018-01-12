$(document).ready(function () {
	window.scrollTo(0,0);

	$('[data-toggle="tooltip"]').tooltip();

	var entered=false;
	var currentTime = new Date();
	var lastScrollTop = 0;

	$(window).scroll(function(){
		
		var st = $(this).scrollTop();

		if (st > lastScrollTop){

			if (($(document).height() -  $(window).height()) <=  $(window).scrollTop()+10 && !entered) {
				var listSize = $("#posts").children("section").length;
				entered=true;
				$("#loading").removeClass("hide");

				setTimeout(function(){

					$.ajax({
						url:"otherHashtags", 
						data:{time:currentTime.getTime(),last:listSize},
						success: function(result) {
							var html = $.parseHTML(result)
							if(html.length != 1){
								$("#posts").append(html);
							}
							$("#loading").addClass("hide");
							entered=false;
						}	
					}) 		 
				},1000);
			}
		}
		lastScrollTop = st;
	});
});
