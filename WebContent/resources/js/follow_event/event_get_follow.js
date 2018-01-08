//TO RELOAD FOLLOWER AND FOLLOWING POPUP OF USERS, BEFORE THAT THE MODAL IS OPENED.

$(document).ready(function() {
	$(function(){
		$('#modalFollowUserSession').on('show.bs.modal', function (e) {
			var username = $("#username_hidden").val();
			$.get({
				url : "getFollowings",
				data:{username:username},
				success : function(result) {
					$(".modal-body").empty();
					$(".modal-body").append(result);
				}
			});
			
	    });
	});
	
	$(function(){
		$('#modalFollowing').on('show.bs.modal', function (e) {
			var username = $("#username_hidden").val();
			$.get({
				url : "getFollowings",
				data:{username:username},
				success : function(result) {
					$(".modal-body").empty();
					$(".modal-body").append(result);
				}
			});
			
	    });
	});
	
	$(function(){
		$('#modalFollower').on('show.bs.modal', function (e) {
			var username = $("#username_hidden").val();
			$.get({
				url : "getFollowers",
				data:{username:username},
				success : function(result) {
					$(".modal-body").empty();
					$(".modal-body").append(result);
				}
			});
			
	    });
	});
});