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
				var content_notification_popover = ""
					for (var i = 0; i < result.length; i++) {
						content_notification_popover += "<div class='notification_item'>"
							content_notification_popover += "<div class='user_from_notification'>"
								content_notification_popover += "<img src="+ result[i].userPhoto+">"
								content_notification_popover += "</div>"
									content_notification_popover += "<div class='context_notification'>"
										content_notification_popover += "<span><b>" + result[i].userName +"</b> " + result[i].context +". <p> "+ result[i].date+"</p></span>" 
										content_notification_popover += "</div>"
											if(result[i].urlPost == null){
												if(result[i].isPrivate){
													content_notification_popover += "<div class='follow_btn_notification'>"
														if(result[i].alreadyFollow){
															content_notification_popover += "<button class='unfollowProfile_btn'>Unfollow</button>"
														}else{
															content_notification_popover += "<button class='followProfile_btn'>Accept</button>"
																content_notification_popover += "<button class='declineProfile_btn'>Decline</button>"
																	content_notification_popover += "</div>"
														}
												} else {
													content_notification_popover += "<div class='follow_btn_notification'>"
														if(result[i].alreadyFollow){
															content_notification_popover += "<button class='unfollowProfile_btn'>Unfollow</button>"
														}else {
															content_notification_popover += "<button class='followProfile_btn'>Follow</button>"
														}

													content_notification_popover += "</div>"
												}
											} else {
												content_notification_popover += "<div class='post_notification'>"
													content_notification_popover += "<img src=" + result[i].urlPost + ">"
													content_notification_popover += "</div>"	
											}

						content_notification_popover += "</div>"
							if(i < (result.length -1))
								content_notification_popover += "<hr class='hr_notification'>"
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

	notification.click(function(e) {
		e.stopPropagation();
	})

	$('body').click(function (e) {
		notification.addClass("hide");
		arrow.addClass("hide");
	});
	
	$('.followProfile_btn').click(function(e) {
		console.log("vado")
		var username = $(this).find('.username').text();
		console.log(username);
		var div = e.target().parent();
		console.log(div);
		div.empty();
		$.ajax({
			url : "followUser",
			data:{username:username},
			success : function(result) {
				if (result == "OK") {
					div.empty();
					div.append("<button class='unfollowProfile_btn'>Unfollow</button>");
				}
				else {
					showResultMessage("FAILED");
				}
			}
		});
	});
	
});

