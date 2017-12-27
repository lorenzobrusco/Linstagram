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

$(document).on('click', '.love', function() {
	var postID = $(this).attr('name');
	var count_like = $("#count_like"+postID)
	var love_id = $("#love_div"+postID)

	$.ajax({
		url : "addLike",
		data:{postID:postID},
		success : function(result) {
			if(result == "OK") {
				$(count_like).html(parseInt($(count_like).html(), 10)+1)
				$(love_id).empty();
				$(love_id).append("<a name="+postID+" id=loveFull"+postID+
				" class=loveFull><span class=loveFull></span></a>");
			}
			//altrimenti dare un messaggio di errore??					
		}
	});
});

$(document).on('click', '.loveFull', function() {
	var postID = $(this).attr('name');
	var count_like = $("#count_like"+postID)
	var love_id = $("#love_div"+postID)

	$.ajax({
		url : "removeLike",
		data:{postID:postID},
		success : function(result) {
			if(result == "OK") {
				$(count_like).html(parseInt($(count_like).html(), 10)-1)
				$(love_id).empty();
				$(love_id).append("<a name="+postID+" id=love"+postID+
				" class=love><span class=love></span></a>");
			}
			//altrimenti dare un messaggio di errore??					
		}
	});
});

$(document).on('click', '.bookmark', function() {
	var postID = $(this).attr('name');
	var bookmark_id = $("#bookmark_div"+postID)
	
	$.ajax({
		url : "addBookmark",
		data:{postID:postID},
		success : function(result) {
			if(result == "OK") {
				$(bookmark_id).empty();
				$(bookmark_id).append("<a name="+postID+" id=bookmarkFull"+postID+
				" class=bookmarkFull><span class=saveFull></span></a>");
			}
			//altrimenti dare un messaggio di errore??					
		}
	});
});

$(document).on('click', '.bookmarkFull', function() {
	var postID = $(this).attr('name');
	var bookmark_id = $("#bookmark_div"+postID)
	
	$.ajax({
		url : "removeBookmark",
		data:{postID:postID},
		success : function(result) {
			if(result == "OK") {
				$(bookmark_id).empty();
				$(bookmark_id).append("<a name="+postID+" id=bookmark"+postID+
				" class=bookmark><span class=save></span></a>");
			}
			//altrimenti dare un messaggio di errore??					
		}
	});
});

$(document).on('click', '.submit_comment', function() {
	var postID = $(this).attr('id');
	var comm = $("#comment"+postID).val();
	var username = $("#username").val();
	
	var listComment = $('.list-comments'+postID);
	
	$.ajax({
		url : "addComment",
		data:{postID:postID, comment:comm},
		success : function(result) {		
			if(result == "OK") {
				$("#comment"+postID).val('');
				$(listComment).append("<a href='#'><b>"+username+"</b></a>"+
								"<span>"+comm+"</span><br>");
			}
			//altrimenti dare un messaggio di errore??					
		}
	});
});