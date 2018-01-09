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

	//change order post

	var typeReq="latest";
	$("#cng-order").click(function(){
		$("#posts").empty();
		$("#loading").removeClass("hide");
		
		var nTypeReq="";
		var text="";

		typeReq= $(this).attr("data-type");
		if($(this).attr("data-type")== "popular"){
			nTypeReq = "latest";
			text="Latest posts"
		
		}else if($(this).attr("data-type")== "latest"){
			
			nTypeReq = "popular";
			text="Popular posts"
		}
		

		$.ajax({
			url:"getPosts", 
			data:{time:currentTime.getTime(),type:typeReq,lastIndex:0},
			success: function(result) {
				
				var html = $.parseHTML(result)
				if(html.length != 1){
					$("#posts").append(html);
					
				}
				$("#loading").addClass("hide");
				$("#cng-order").attr("data-type",nTypeReq);
				$("#cng-order").attr("data-original-title",text);
				
			}	
		}) 		 	
	});

	//Inifinity scroll
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
						url:"getPosts", 
						data:{type:typeReq,time:currentTime.getTime(),lastIndex:listSize},
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
