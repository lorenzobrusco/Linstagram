//EVENTI DEI BOTTONI DELLE RICHIESTE FOLLOW PRIVATE E PUBBLICHE

$(document).ready(function() {
	
	var notyerror={
			text: '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Failed!</p> The changes have not been made!',
			theme: 'nest',
			type: 'error',
			layout: 'bottomLeft',
			timeout:4000,
			progressBar: true
	}
	
	function showResultMessage(result){
		if (result == "FAILED"){
			new Noty(notyerror).show();
		}
	}
	
	
//EVENTI DEI TASTI DEL POPUP DEL FOLLOWER/FOLLOWING
	
	//EVENTO DEL TASTO "FOLLOW" [PER SEGUIRE UN UTENTE CON IL PROFILO PUBBLICO]
	$(document).on('click', '#follower-btn', function() {
		var id = $(this).attr('name');
		var username = $(this).attr('value');
		
		var btn = "#fol-div"+id+" div";

		$(btn).empty();
		$(btn).append("<button id='unfollower-btn' name='"+id+"' value='"+username+"'>Unfollow</button>");
		$.ajax({
			url : "followUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#count_following").html(parseInt($("#count_following").html(), 10)+1)
				}
				else {
					$(btn).empty();
					$(btn).append("<button id='follower-btn' name='"+id+"' value='"+username+"'>Follow</button>");
				
					showResultMessage("FAILED");
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
		
		$(btn).empty();
		if (privateProfile == "false")
			$(btn).append("<button id='follower-btn' name='"+id+"' value='"+username+"'>Follow</button>");
		else
			$(btn).append("<button value="+username+" name='"+id+"' id='sendRequestPopup-btn'>Follow</button>")
		
		$.ajax({
			url : "unfollowUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#count_following").html(parseInt($("#count_following").html(), 10)-1)
				}
				else {
					$(btn).empty();
					$(btn).append("<button id='unfollower-btn' name='"+id+"' value='"+username+"'>Unfollow</button>");
					
					showResultMessage("FAILED");
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
			url : "followUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$(btn).empty();
					$(btn).append("<button value='"+username+"' name='"+id+"' id='cancelRequestPopup-btn'>Delete Request</button>");
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
					$(btn).append("<button value='"+username+"' name='"+id+"' id='sendRequestPopup-btn'>Follow</button>");
				}
			}
		});
	});
	
	

//EVENTI BOTTONI DEL PROFILO UTENTE
	
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
		var id = $(this).attr('name');
//		var popupFail = document.getElementById("popupFAIL");
		
		var privateProfile = $('#private'+id).val();

		$.ajax({
			url : "unfollowUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					$("#follow_ul").empty();
					
					if (privateProfile == "false")
						$("#follow_ul").append("<li><button value="+username+" name='"+id+"' id='followProfile-btn'>Follow</button></li>");
					else {
						//$("#follow_ul").append("<li><button value="+username+" name='"+id+"' id='sendRequestPopup-btn'>Send Request</button></li>")
						location.reload();
					}
					$("#count_follower").text(parseInt($("#count_follower").html(), 10) - 1);
				}
			}
		});
	});
	
	//EVENTO DEL TASTO "SEND_REQUEST" NEL PROFILO UTENTE [PER INVIARE LA RICHIESTA DI FOLLOW AD UN UTENTE PRIVATO]
	$(document).on('click', '#sendRequest-btn', function() {
		var id = $(this).attr('name');
		var username = $(this).attr('value');

		$.ajax({
			url : "followUser",
			data:{
				username:username
				},
			success : function(result) {
				if (result == "OK") {
					$("#follow_ul").empty();
					$("#follow_ul").append("<button value='"+username+"' name='"+id+"' id='cancelRequest-btn'>Delete Request</button>");
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