// Events for handle follow/unfollow (public) request on user's page


$(document).ready(function() {
	$(document).on('click', '#follower-btn', function() {
		var id = $(this).attr('name');
		var username = $(this).attr('value');

		var btn = "#fol-div"+id+" div";
		$.ajax({
			url : "followUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#count_following").html(parseInt($("#count_following").html(), 10)+1)
					$(btn).empty();
					$(btn).append("<button id='unfollower-btn' name='"+id+"' value='"+username+"'>Unfollow</button>");
				}
			}
		});
	});
	
	$(document).on('click', '#unfollower-btn', function() {
		var id = $(this).attr('name');
		var username = $(this).attr('value');
		
		var btn = "#fol-div"+id+" div";
		$.ajax({
			url : "unfollowUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#count_following").html(parseInt($("#count_following").html(), 10)-1)
					$(btn).empty();
					$(btn).append("<button id='follower-btn' name='"+id+"' value='"+username+"'>Follow</button>");
				}
			}
		});
	});
	
	
	$(document).on('click', '#sendRequest-btn', function() {
		var username = $(this).attr('value');

		$.ajax({
			url : "sendRequest",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#follow_ul").empty();
					$("#follow_ul").append("<button value='"+username+"' id='sendedRequest-btn' disabled>Request Sended</button>");
				}
			}
		});
	});
	
	$(document).on('click', '#acceptRequest-btn', function() {
		var username = $(this).attr('value');

		$.ajax({
			url : "acceptRequest",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					location.reload();
				}
			}
		});
	});
	
	$(document).on('click', '#rejectRequest-btn', function() {
		var username = $(this).attr('value');

		$.ajax({
			url : "rejectRequest",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					location.reload();
				}
			}
		});
	});
});