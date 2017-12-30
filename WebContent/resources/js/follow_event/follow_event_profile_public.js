$(document).ready(function() {
	$(document).on('click', '#followProfile-btn', function() {
		var username = $('#username_hidden').val();
		var popupFail = document.getElementById("popupFAIL");
		
		$.ajax({
			url : "followUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#follow_ul").empty();
					$("#follow_ul").append("<li><button id='unfollowProfile-btn'>Unfollow</button></li>");
					$("#count_follower").html(parseInt($("#count_follower").html(), 10)+1)
				}
				/* else {
					$('#text').text(result);
					popupFail.classList.toggle("show");					
				} */
			}
		});
	});
	
	$(document).on('click', '#unfollowProfile-btn', function() {
		var username = $('#username_hidden').val();
		var popupFail = document.getElementById("popupFAIL");
		
		$.ajax({
			url : "unfollowUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#follow_ul").empty();
					$("#follow_ul").append("<li><button id='followProfile-btn'>Follow</button></li>");
					$("#count_follower").html(parseInt($("#count_follower").html(), 10)-1)
				}
				/* else {
					$('#text').text(result);
					popupFail.classList.toggle("show");			
				} */
			}
		});
	});
});
