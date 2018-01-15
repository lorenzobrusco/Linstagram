//EVENTI DEI POST

//CALCOLO DEL TEMPO PASSATO DAL CARICAMENTO DEL POST
//function getElapsedTime(postedTime){
//var now = (new Date()).getTime();
//var diff=now-postedTime;
//var second = diff/1000;
//var min = second/60;
//var round=Math.round(min);
//if(	round<=60)
//return round+" MIN AGO";
//else if(round > 60 && round < 24*60){
//round = round/60;
//round = Math.round(round);
//return round+" HOURS AGO";
//}
//else{
//round = round/60;
//round = round/24;
//round = Math.round(round);
//return round+" DAYS AGO";
//}	
//};

$(document).ready(function() {
	
	function sendNotification(user) {
		  $.ajax({
		    url: "sendNotification",
		    type: "POST",
		    data:{
		    	user: user
		    }
		  });
		  return;
	}
	
	//EVENTO "INSERIMENTO LIKE"
	$(document).on('click', 'a.love', function() {
		var postID = $(this).attr('name');
		var count_like = $("#count_like"+postID)
		var love_id = $("#love_div"+postID)
		
		$.ajax({
			url : "addLike",
			data:{
				postID:postID
			},
			success : function(result) {
				result = JSON.parse(result);
				if(result.messageCode == "OK") {
					$(count_like).html(parseInt($(count_like).html(), 10)+1)
					$(love_id).empty();
					$(love_id).append("<a name="+postID+" id=loveFull"+postID+
					" class=loveFull><span class=loveFull><i class='fa fa-heart fa-2x' aria-hidden='true'></i></span></a>");
				}
			}
		});
	});

	//EVENTO "RIMOZIONE LIKE"
	$(document).on('click', 'a.loveFull', function() {
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
					" class=love><span class='love'><i class='fa fa-heart-o fa-2x' aria-hidden='true'></i></span></a>");
				}
			}
		});
	});

	//EVENTO "INSERIMENTO BOOKMARK"
	$(document).on('click', '.bookmarkempty', function() {
		var postID = $(this).attr('name');
		var bookmark_id = $("#bookmark_div"+postID)

		$.ajax({
			url : "addBookmark",
			data:{postID:postID},
			success : function(result) {
				if(result == "OK") {
					$(bookmark_id).empty();
					$(bookmark_id).append("<a name="+postID+" id=bookmarkfull"+postID+
					" class=bookmarkfull><span class=save><i class='fa fa-bookmark fa-2x' aria-hidden='true'></i></span></a>");
				}
			}
		});
	});

	//EVENTO "RIMOZIONE BOOKMARK"
	$(document).on('click', '.bookmarkfull', function() {
		var postID = $(this).attr('name');
		var bookmark_id = $("#bookmark_div"+postID)

		$.ajax({
			url : "removeBookmark",
			data:{postID:postID},
			success : function(result) {
				if(result == "OK") {
					$(bookmark_id).empty();
					$(bookmark_id).append("<a name="+postID+" id=bookmarkempty"+postID+
					" class=bookmarkempty><span class=save><i class='fa fa-bookmark-o fa-2x' aria-hidden='true'></i></span></a>");
				}
			}
		});
	});



	//EVENTO "INSERIMENTO COMMENTO"
	$(document).on('click', '.submit_comment', function() {		
		var postID = $(this).attr('id');
		var comm = $("#comment"+postID).val();
		var username = $("#username").val();

		var listComment = $('.list-comments'+postID);
		
		//prevent injection
		comm = $( $.parseHTML(comm) ).text();
		
		$.ajax({
			url : "addComment",
			data: {postID:postID, comment:comm},
			success : function(result) {					
				result = JSON.parse(result);
				if(result.messageCode == "OK") {
					$("#comment"+postID).val('');
					$(listComment).append("<div class='comment'><a href='userPage?username="+username+"'><b>"+username+"</b></a>"+
							"<span class='comment_body'>"+comm+"</span></div>");
					sendNotification(result.obj);
				}
			}
		});

	});

	//EVENTO PER APRIRE IL POPUP DEI LIKE E VEDERE GLI UTENTI CHE HANNO MESSO LIKE AD UN POST
	$(document).on('click', '#likes', function() {
		var post = $(this).attr("name");

		$.get({
			url : "getLikes",
			data:{post:post},
			success : function(result) {
				$(".modal-body").empty();
				$(".modal-body").append(result);
			}
		});
	});
	
	$(document).on("keypress", ".comment-section",function(e) {
		if ( e.which == 13 ) { //enter press
			$(this).find("button").click();
		}
	});
});
