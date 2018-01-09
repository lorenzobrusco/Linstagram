function getElapsedTime(postedTime){
	var now = (new Date()).getTime();
	var diff=now-postedTime;
	var second = diff/1000;
	var min = second/60;
	var round=Math.round(min);
	if(	round<=60)
		return round+" MIN AGO";
	else if(round > 60 && round < 24*60){
		round = round/60;
		round = Math.round(round);
		return round+" HOURS AGO";
	}
	else{
		round = round/60;
		round = round/24;
		round = Math.round(round);
		return round+" DAYS AGO";
	}	
};

$(document).ready(function () {
	
	window.scrollTo(0,0);
	//show comment event
//	$('.show-all-comments')
//	.click(
//	function (e) {
//	var target = $(e.target).find('span');
//	if (target.hasClass('show-comments')) {
//	$(e.target).html('<span class="hidden-comments"></span> Nascondi altri commenti');
//	} else {
//	$(e.target).html('<span class="show-comments"></span> Carica altri commenti');
//	}
//	});

	//Active tooltip
	$('[data-toggle="tooltip"]').tooltip();


	$("#create-story").click(e =>{
		console.log("Create Story");
	});

	var postsrequest=1;
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
						url:"otherPosts", 
						data:{time:currentTime.getTime(),last:listSize},
						success: function(result) {
							var html = $.parseHTML(result)
							if(html.length != 1){
								$("#posts").append(html);
								//allow to send comment with Enter button
								$(".comment-section").on("keypress", function(e) {
									if ( e.which == 13 ) { //enter press
										$(this).find("button").click();
									}
								});
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
