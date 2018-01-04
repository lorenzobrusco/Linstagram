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
		var privateProfile = $('#private'+id).val();

		var btn = "#fol-div"+id+" div";
		$.ajax({
			url : "unfollowUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#count_following").html(parseInt($("#count_following").html(), 10)-1)
					$(btn).empty();
					if (privateProfile == "false")
						$(btn).append("<button id='follower-btn' name='"+id+"' value='"+username+"'>Follow</button>");
					else
						$(btn).append("<button value="+username+" name='"+id+"' id='sendRequestFollower-btn'>Send Request</button>")
				}
			}
		});
	});
	
	$(document).on('click', '#sendRequestFollower-btn', function() {
		var id = $(this).attr('name');
		var username = $(this).attr('value');

		var btn = "#fol-div"+id+" div";

		$.ajax({
			url : "sendRequest",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$(btn).empty();
					$(btn).append("<button value='"+username+"' name='"+id+"' id='cancelRequest-btn'>Cancel Request</button>");
				}
			}
		});
	});
	
	$(document).on('click', '#cancelRequest-btn', function() {
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
	
	
	
	
	//questo è per il profilo
	$(document).on('click', '#sendRequest-btn', function() {
		var id = $(this).attr('name');
		var username = $(this).attr('value');

		$.ajax({
			url : "sendRequest",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#follow_ul").empty();
					$("#follow_ul").append("<button value='"+username+"' name='"+id+"' id='cancelRequest-btn'>Cancel Request</button>");
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