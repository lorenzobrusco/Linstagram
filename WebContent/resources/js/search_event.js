$(document).ready(function(){	

	var search_input = $(".search-input");
	var search_list = $("#hint-list");
	var hr= "<hr class='hr-hint'>"
	
	$(document).mouseup(function(e) {
		if (!search_input.is(e.target) && !search_list.is(e.target) && search_input.has(e.target).length === 0 
					&& search_list.has(e.target).length === 0) {
							search_list.addClass("hide");
							search_input.val("");
	    }
	});
	
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
		elem += "</div></a>";
		return elem;
	}
	
	search_input.keyup(function(e) {
		var text = "";
		if(e.target.id == "search-input-desktop")
			var text = $("#search-input-desktop").val();
		if(e.target.id == "search-input-mobile")
			var text = $("#search-input-mobile").val();
		if(text==""){
			search_list.addClass("hide");
			return;
		}

		$.ajax({
			url : "research",
			type : "POST",
			data : {
				text : text
			},
			success : function(result) {
				$("#hint-list").empty();
				
				if(result.length==0){
					search_list.addClass("hide");
					return;
				}
				
				$("#hint-list").css("height",(result.length*65)+1);
				var count=result.length;
				
				$.each(result,function( key, value ){
					//console.log(key);
					count--;
					var title = value.title;
					var subtitle = value.subtitle;
					var iconUrl = value.iconUrl;
					var type = value.type;
					var item = createHintElement(iconUrl,title,subtitle,type);
//					console.log(item);
					$("#hint-list").append(item);
					if(count!=0)
						$("#hint-list").append(hr);
					

				});
				search_list.removeClass("hide");
			}
		});

	});


});