$(document).ready(function(){	

	//TODO search on mobile	
	var search_input = $(".search-input");
	
	//if click on list close list
//	search_input.focusout(function() {
//		$("#hint-list").addClass("hide");
//	});
	
	function createHintElement(imgURL,title,subtitle,type){
		var elem="";
		if(type=="hashtag"){
			hashtag = title.replace("#", "");
			elem += "<a href='hashtags?hashtag="+hashtag+"' class='hint-item'>";
		}
		if(type=="user"){
			elem += "<a href='userPage?username="+title+"' class='hint-item'>";
		}
		elem += "<div class='hint-icon'>";
		elem += "<img src='"+ imgURL +"'>";
		elem += "</div>";
		elem += "<div class='context-hint'>";
		elem += "<span><b>"+title+"</b> "+ subtitle+"</p></span>"; 
		elem += "</div></a><hr class='hr_notification'/>";
		return elem;
	}
	
	search_input.keyup(function() {
		var text = search_input.val();
		

		$.ajax({
			url : "research",
			type : "POST",
			data : {
				text : text
			},
			success : function(result) {
				$("#hint-list").empty();
				console.log(result);
				$.each(result,function( key, value ){
					//console.log(key);
					var title = value.title;
					var subtitle = value.subtitle;
					var iconUrl = value.iconUrl;
					var type = value.type;
					var item = createHintElement(iconUrl,title,subtitle,type);
//					console.log(item);
					$("#hint-list").append(item);
					

				});
				$("#hint-list").removeClass("hide");
			}
		});

	});


});