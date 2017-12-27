// Events for handle follow/unfollow (public) request on user's page


$(document).ready(function() {
	$(document).on('click', '#followModal-btn', function() {
		var id = $(this).attr('name');
		var username = $(this).attr('value');

		var btn = "#buttonFollower"+id;
		$.ajax({
			url : "followUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$(btn).empty();
					$(btn).append("<button id='unfollowModal-btn' name='"+id+"' value='"+username+"'>Unfollow</button>");
				}
			}
		});
	});
	
	$(document).on('click', '#unfollowModal-btn', function(event) {
		var id = $(this).attr('name');
		var username = $(this).attr('value');
		
		var btn = "#buttonFollower"+id;
		$.ajax({
			url : "unfollowUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$(btn).empty();
					$(btn).append("<button id='followModal-btn' name='"+id+"' value='"+username+"'>Follow</button>");
				}
			}
		});
	});
});