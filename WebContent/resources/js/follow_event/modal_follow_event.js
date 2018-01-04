// Events for handle follow/unfollow (public) request on user's page


$(document).ready(function() {
	$(document).on('click', '#follow-btn', function() {
		var id = $(this).attr('name');
		var username = $(this).attr('value');

		var btn = "#button"+id;
		$.ajax({
			url : "followUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$(btn).empty();
					$(btn).append("<button id='unfollow-btn' name='"+id+"' value='"+username+"'>Unfollow</button>");
					$("#count_following").html(parseInt($("#count_following").html(), 10)+1)
				}
			}
		});
	});
	
	$(document).on('click', '#unfollow-btn', function(event) {
		var id = $(this).attr('name');
		var username = $(this).attr('value');
		var privateProfile = $('#private'+id).val();

		var btn = "#button"+id;
		$.ajax({
			url : "unfollowUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$(btn).empty();
					$("#count_following").html(parseInt($("#count_following").html(), 10)-1)
					if (privateProfile == "false")
						$(btn).append("<button id='follow-btn' name='"+id+"' value='"+username+"'>Follow</button>");
					else
						$(btn).append("<button value="+username+" name='"+id+"' id='sendRequestFollower-btn'>Send Request</button>")
				}
			}
		});
	});
});