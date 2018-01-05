//EVENTI DEI POST

//CALCOLO DEL TEMPO PASSATO DAL CARICAMENTO DEL POST
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

$(document).ready(function() {
	//EVENTO "INSERIMENTO LIKE"
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
			}
		});
	});

	//EVENTO "RIMOZIONE LIKE"
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
			}
		});
	});

	//EVENTO "INSERIMENTO BOOKMARK"
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
			}
		});
	});

	//EVENTO "RIMOZIONE BOOKMARK"
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
			}
		});
	});

	//EVENTO "INSERIMENTO COMMENTO"
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
					$(listComment).append("<div class='comment'><a href='userPage?usernameOther="+username+"'><b>"+username+"</b></a>"+
							"<span>"+comm+"</span></div>");
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

	//EVENTO PER APRIRE IL POPUP DEI COMMENTI E VEDERE GLI UTENTI CHE HANNO COMMENTATO UN POST
//	$(document).on('click', '#comment', function() {
//	var post = $(this).attr("name");

//	$.get({
//	url : "getComment",
//	data:{post:post},
//	success : function(result) {
//	$(".modal-body").empty();
//	$(".modal-body").append(result);
//	}
//	});
//	});

	$(document).on('click', '.show-all-comments', function() {
		var postID = $(this).parent().siblings('.comment-section').children("button").attr("id");
		var thiss = this;
		var listComment = $('.list-comments'+postID);
		var listSize = $(listComment).children(".comment").length;
		$(this).children("#loader-comments").removeClass("hide");
		$.ajax({
			url : "getPostComment",
			data:{post:postID, index:listSize},
			success : function(result) {	
				if(result.length > 0){
					for(var i=0; i < result.length; i++)
						$(listComment).append("<div class='comment'><a href='userPage?usernameOther="+result[i].username+"'><b>"+result[i].username+"</b></a>"+
								"<span>"+result[i].comment+"</span></div>");

					$(listComment).siblings(".hide-all-comments").removeClass("hide");
				}
				$(thiss).children("#loader-comments").addClass("hide");
			}
		});
	});

	$(document).on('click', '.hide-all-comments', function() {
		var postID = $(this).parent().siblings('.comment-section').children("button").attr("id");
		var listComment = $('.list-comments'+postID);
		$(listComment).children(".comment:gt(3)").remove();
		$(this).addClass("hide");

	});

});


function getPostsByHashtags(h) {

	//TODO
	$.ajax({
		url : "hashtagPosts",
		type:"POST",
		data:{hashtag:h},
		success : function(result) {
//			var html = $.parseHTML(result);
//			$("#posts").append(html);

		}
	});


}

function getContentPost(content, tags, hashtags) {

	if (tags.length !=0)
	{
		tags= tags.sort(function(a,b){return a.username.length<b.username.length});
		console.log (tags);
		for (i = 0; i < tags.length; i++)
		{
			var startingIndex = content.indexOf(tags[i]["username"]);
			if (startingIndex >=1 && content[startingIndex-1] == "@")
			{
				content = content.replace("@"+tags[i]["username"], "<a href='userPage?usernameOther="+tags[i]["username"]+"'>"+"@"+tags[i]["username"]+"</a>");

			}
		}

	}
	if (hashtags.length !=0)
	{
		hashtags= hashtags.sort(function(a,b){return a.hashtag.length < b.hashtag.length});
		console.log (hashtags);

		for (i = 0; i < hashtags.length; i++)
		{
			var startingIndex = content.indexOf(hashtags[i]["hashtag"]);
			if (startingIndex >=1 && content[startingIndex-1] == "#")
			{
				content = content.replace("#"+hashtags[i]["hashtag"], "<a href='javascript:getPostsByHashtags(\""+hashtags[i]["hashtag"]+"\")'>"+"#"+hashtags[i]["hashtag"]+"</a>");

			}
		}
	}

	console.log("content "+ content);
	return content;

};
