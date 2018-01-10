$(document).ready(function(){
	var notification =  $("#notification_list");
	var notification_mobile_list = $("#notification_list_mobile");
	var arrow = $(".arrow");
	
	//pass the element that have to contain the notification items
	function createNotificationList(notification){
		$.ajax({
			url : "openNotification",
			type : "POST",
			success : function(result) {
				var content_notification_popover = "";
					for (var i = 0; i < result.length; i++) {
						content_notification_popover += "<div class='notification_item'>";
						content_notification_popover += "<div class='user_from_notification'>";
						content_notification_popover += "<img src="+ result[i].userPhoto+">";
						content_notification_popover += "</div>";
						content_notification_popover += "<div class='context_notification'>";
						content_notification_popover += "<span><b>" + result[i].userName +"</b> " + result[i].context +". <p> "+ result[i].date+"</p></span>"; 
						content_notification_popover += "</div>";
						if(result[i].urlPost == null){
							if(result[i].isPrivate){
							content_notification_popover += "<div class='follow_btn_notification'>";
							console.log(result[i].isRequest)
							if(result[i].isRequest){
								
								content_notification_popover += "<button class='acceptfollowProfile_btn'>Accept</button>";
								content_notification_popover += "<button class='declineProfile_btn'>Decline</button>";
							}else{
								 if(result[i].alreadyFollowed){
										if(!result[i].alreadyFollowing){
											content_notification_popover += "<button class='followProfile_btn'>Follow</button>";
										}else{
											content_notification_popover += "<button class='unfollowProfile_btn'>Unfollow</button>";
										}
									}else{
										content_notification_popover += "<button class='acceptfollowProfile_btn'>Accept</button>";
										content_notification_popover += "<button class='declineProfile_btn'>Decline</button>";
									}
								}
							content_notification_popover += "<input type='hidden' name='user' value=" + result[i].userName +">";
							content_notification_popover += "</div>";
							} else {
								content_notification_popover += "<div class='follow_btn_notification'>";
								if(result[i].alreadyFollow){
									if(!result[i].alreadyFollowing){
										content_notification_popover += "<button class='followProfile_btn'>Follow</button>";
									}else{
										content_notification_popover += "<button class='unfollowProfile_btn'>Unfollow</button>";
									}
								}else {
									content_notification_popover += "<button class='followProfile_btn' >Follow</button>";
								}
								content_notification_popover += "<input type='hidden' name='user' value=" + result[i].userName +">";
								content_notification_popover += "</div>";
							}
						} else {
							content_notification_popover += "<div class='post_notification'>";
							content_notification_popover += "<img src=" + result[i].urlPost + ">";
							content_notification_popover += "</div>";	
						}

						content_notification_popover += "</div>";
							if(i < (result.length -1))
								content_notification_popover += "<hr class='hr_notification'>";
					}
					notification.empty();
					notification.html(content_notification_popover);
			}
		})
		return false;
	}
	
	$('#notification').click(function (e) {
		e.preventDefault();
		notification.html("");
		if(!notification.hasClass("hide")){
			notification.addClass("hide");
			arrow.addClass("hide");
		}

		else{
			notification.removeClass("hide");
			arrow.removeClass("hide");
		}
		e.stopPropagation();
		$(".badge").remove();
		createNotificationList(notification);
	});
	
	$('#notification-mobile').click(function (e) {
		e.preventDefault();
		notification_mobile_list.html("");
		if(!notification_mobile_list.hasClass("hide")){
			notification_mobile_list.addClass("hide");
			$("#top").removeClass("hide");
		}else{
			notification_mobile_list.removeClass("hide");
			$("#top").addClass("hide");
		}
		e.stopPropagation();
		$(".badge").remove();
		createNotificationList(notification_mobile_list);
	});

	$(document).on('click', '.followProfile_btn', function(e) {
		var username = $(this).parent().find('input').attr('value');
		alert(username);
		var div = $(this).parent();
		console.log(div);
		div.empty();
		$.ajax({
			url : "followUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					div.empty();
					div.append("<button class='unfollowProfile_btn'>Unfollow</button>");
					div.append("<input type='hidden' name='user' value=" + username +">");
				}
				else {
					showResultMessage("FAILED");
				}
			}
		});
	});
	
	$(document).on('click', '.unfollowProfile_btn', function(e) {
		var username = $(this).parent().find('input').attr('value');
		alert(username);
		var div = $(this).parent();
		console.log(div);
		div.empty();
		$.ajax({
			url : "unfollowUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					div.empty();
					div.append("<button class='followProfile_btn'>follow</button>");
					div.append("<input type='hidden' name='user' value=" + username +">");
				}
				else {
					showResultMessage("FAILED");
				}
			}
		});
	});
	
	$(document).on('click', '.acceptfollowProfile_btn', function(e) {
		var username = $(this).parent().find('input').attr('value');
		alert(username);
		var div = $(this).parent();
		console.log(div);
		div.empty();
		$.ajax({
			url : "acceptRequest",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					div.empty();
					div.append("accetto");
				}
				else {
					showResultMessage("FAILED");
				}
			}
		});
	});
	
	$(document).mouseup(function(e) {
		if (!notification.is(e.target) && notification.has(e.target).length === 0) {
			notification.addClass("hide");
			arrow.addClass("hide");
	    }
	});
	
});

