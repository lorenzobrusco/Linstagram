//EVENTI DEI BOTTONI FOLLOW/UNFOLLOW DEL PROFILO UTENTE.
//SONO SEPARATI DA QUELLI DEL POPUP PERCHÈ ALTRIMENTI CLICCANDO QUELLI DEL POPUP POTEVA CAMBIARE QUELLO DEL PROFILO.

$(document).ready(function() {
	
	//EVENTO DEL BOTTONE "FOLLOW"
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
			}
		});
	});
	
	//EVENTO DEL BOTTONE "UNFOLLOW"
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
					$("#count_follower").text(parseInt($("#count_follower").html(), 10) - 1);
				}
			}
		});
	});
});
