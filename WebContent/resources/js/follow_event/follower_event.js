// Events for handle follow/unfollow (public) request on user's page


$(document).ready(function() {
	//EVENTO DEL TASTO "FOLLOW" [PER SEGUIRE UN UTENTE CON IL PROFILO PUBBLICO]
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

	//EVENTO DEL TASTO "UNFOLLOW" [PER NON SEGUIRE PIÙ UN UTENTE, SIA PUBBLICO CHE PRIVATO]
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
						$(btn).append("<button value="+username+" name='"+id+"' id='sendRequestPopup-btn'>Send Request</button>")
				}
			}
		});
	});
	
	//EVENTO DEL TASTO "SEND_REQUEST" NEL POPUP DEI FOLLOWER/FOLLOWING
	$(document).on('click', '#sendRequestPopup-btn', function() {
		var id = $(this).attr('name');
		var username = $(this).attr('value');

		var btn = "#fol-div"+id+" div";

		$.ajax({
			url : "sendRequest",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$(btn).empty();
					$(btn).append("<button value='"+username+"' name='"+id+"' id='cancelRequestPopup-btn'>Cancel Request</button>");
				}
			}
		});
	});
	
	//EVENTO DEL TASTO "SEND_REQUEST" NEL POPUP DEI FOLLOWER/FOLLOWING
	$(document).on('click', '#cancelRequestPopup-btn', function() {
		var username = $(this).attr('value');var id = $(this).attr('name');
		var username = $(this).attr('value');

		var btn = "#fol-div"+id+" div";
		
		$.ajax({
			url : "rejectRequest",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$(btn).empty();
					$(btn).append("<button value='"+username+"' name='"+id+"' id='sendRequestPopup-btn'>Send Request</button>");
				}
			}
		});
	});
	
	
	
	
	//EVENTO DEL TASTO "SEND_REQUEST" NEL PROFILO UTENTE [PER INVIARE LA RICHIESTA DI FOLLOW AD UN UTENTE PRIVATO]
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
	
	//EVENTO DEL TASTO "CANCEL_REQUEST" NEL PROFILO UTENTE [PER CANCELLARE LA RICHIESTA DI FOLLOW FATTA AD UN UTENTE PRIVATO]
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
	
	
	//EVENTO DEL TASTO "ACCEPT_REQUEST" [PER ACCETTARE LA RICHIESTA]
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
	
	//EVENTO DEL TASTO "REJECT_REQUEST" [PER RIFIUTARE LA RICHIESTA]
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