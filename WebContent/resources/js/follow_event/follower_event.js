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
});