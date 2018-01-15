$(document).ready(function(){
	var notification =  $("#notification_list");
	var notification_mobile_list = $("#notification_list_mobile");
	var arrow = $(".arrow");
	var img_empty='<div id="empty-notification-container"> <span class="empty-notification-img"></span><br><span id="empty-notification-text">No Notifications</span></div>';
	
	//pass the element that have to contain the notification items
	function createNotificationList(notification,mobile){
		$.ajax({
			url : "openNotification",
			type : "POST",
			success : function(result) {
				if(result.length==0){
					notification.append(img_empty);
					if(mobile == false){
						notification.css("height",150);
					}
					$(".loader_notification").addClass("hide");
					return;
				}
				
				var content_notification_popover = "";
					for (var i = 0; i < result.length; i++) {
						//alert(JSON.stringify(result[i]));
						content_notification_popover += "<div class='notification_item'>";
						content_notification_popover += "<div class='user_from_notification'>";
						content_notification_popover += "<a href='userPage?username="+result[i].userName+"'><img src="+ result[i].userPhoto+"></a>";
						content_notification_popover += "</div>";
						content_notification_popover += "<div class='context_notification'>";
						content_notification_popover += "<span><b><a href='userPage?username="+result[i].userName+"'>" + result[i].userName +"</b></a>  " + result[i].context +". <p> "+ result[i].date+"</p></span>"; 
						content_notification_popover += "</div>";
						if(result[i].urlPost == null){
							if(result[i].requestFrom){
								content_notification_popover += "<div class='follow_btn_notification'>";					
								content_notification_popover +="<button class='deleteRequestfollowProfile_btn' disabled>Waiting</button>";
								content_notification_popover += "<input type='hidden' value='" + JSON.stringify(result[i]) +"'>";
								content_notification_popover += "</div>";
							} else {
								if(result[i].requestTo){
								content_notification_popover += "<div class='follow_btn_notification'>";
								if(result[i].privateTo){
									content_notification_popover += "<button class='acceptfollowProfile_btn'>Accept</button>";
									content_notification_popover += "<button class='declineProfile_btn'>Decline</button>";
								}else{
									
									 if(result[i].alreadyFollowing){
										content_notification_popover += "<button class='unfollowProfile_btn'>Unfollow</button>";
									 }else{
										 content_notification_popover += "<button class='followProfile_btn'>Follow</button>"; 
									 }
								}
								content_notification_popover += "<input type='hidden' value='" + JSON.stringify(result[i]) +"'>";
								content_notification_popover += "</div>";
								} else {
									content_notification_popover += "<div class='follow_btn_notification'>";
									if(result[i].alreadyFollowing){
										content_notification_popover += "<button class='unfollowProfile_btn'>Unfollow</button>";
									}else{
										content_notification_popover += "<button class='followProfile_btn'>Follow</button>"; 
									}
									content_notification_popover += "<input type='hidden' value='" + JSON.stringify(result[i]) +"'>";
									content_notification_popover += "</div>";
								}
							}
						} else {
							content_notification_popover += "<div class='post_notification'>";
							if(result[i].video)
								content_notification_popover += "<a href='post?id="+result[i].idPost+"'><video height='40px' width='40px' style='background:black' src=" + result[i].urlPost + "></a>";
							else
								content_notification_popover += "<a href='post?id="+result[i].idPost+"'><img src=" + result[i].urlPost + "></a>";
							content_notification_popover += "</div>";	
						}
						if(i < (result.length -1))
								content_notification_popover += "<hr class='hr_notification'>";
						content_notification_popover += "</div>";
					}
					notification.empty();
					if(result.length == 1)
						notification.css("height",(result.length*63)+2);
					else
						notification.css("height",(result.length*72)+2);
						
					notification.html(content_notification_popover);
			}
		})
		return false;
	}
	
	$('#notification').click(function (e) {
		notification.css("height",100);
		e.preventDefault();
		notification.html("<div class='loader_notification' style='display:flex;justify-content:center;align-items:center;height:100%'><img height='50' width='50' src='resources/images/loader_notification.gif'></img></div>");
		
		$('#profile').popover('hide');
		
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
		createNotificationList(notification,false);
	});
	
	$('#notification-mobile').click(function (e) {
		e.preventDefault();
		notification_mobile_list.html("<div class='loader_notification' style='display:flex;justify-content:center;align-items:center;height:100%'><img height='50' width='50' src='resources/images/loader_notification.gif'></img></div>");
		if(!notification_mobile_list.hasClass("hide")){
			notification_mobile_list.addClass("hide");
			$("#top").css("z-index",1);
		}else{
			notification_mobile_list.removeClass("hide");
			$("#top").css("z-index",-1);
		}
//		e.stopPropagation();
		$(".badge").remove();
		createNotificationList(notification_mobile_list,true);
	});

	$(document).on('click', '.followProfile_btn', function(e) {
		var json = $(this).parent().find('input').attr('value');
		var notification = JSON.parse(json);
		var div = $(this).parent();
		div.empty();
		$.ajax({
			url : "followUser",
			data:{
				username:notification.userName
			},
			success : function(result) {
				if (result == "OK") {
					div.empty();
					if(notification.isPrivateFrom)
						div.append("<button class='deleteRequestfollowProfile_btn' disabled>Waiting</button>");
					else
						div.append("<button class='unfollowProfile_btn'>Unfollow</button>");
					div.append("<input class='" + notification.isPrivate +"' type='hidden' name='user' value='" + json +"'>");
					
				}
				else {
					showResultMessage("FAILED");
				}
			}
		});
	});
	
	$(document).on('click', '.unfollowProfile_btn', function(e) {
		var json = $(this).parent().find('input').attr('value');
		var notification = JSON.parse(json);
		var div = $(this).parent();
		div.empty();
		$.ajax({
			url : "unfollowUser",
			data:{
				username: notification.userName
			},
			success : function(result) {
				if (result == "OK") {
					div.empty();
					div.append("<button class='followProfile_btn'>follow</button>");
					div.append("<input class='" + notification.isPrivate +"' type='hidden' name='user' value='" + json +"'>");
					
				}
				else {
					showResultMessage("FAILED");
				}
			}
		});
	});
	
	$(document).on('click', '.acceptfollowProfile_btn', function(e) {
		var json = $(this).parent().find('input').attr('value');
		var notification = JSON.parse(json);;
		var div = $(this).parent();
		div.empty();
		$.ajax({
			url : "acceptRequest",
			data:{
				username: notification.userName
			},
			success : function(result) {
				if (result == "OK") {
					div.empty();
					if(notification.alreadyFollowing)
						div.append("<button class='unfollowProfile_btn'>Unfollow</button>");					
					else
						div.append("<button class='followProfile_btn'>Follow</button>");
					div.append("<input class='" + notification.isPrivate +"' type='hidden' name='user' value='" + json +"'>");
				} else {
					showResultMessage("FAILED");
				}
			}
		});
	});
	
	$(document).on('click', '.deleteRequestfollowProfile_btn', function(e) {
		var json = $(this).parent().find('input').attr('value');
		var notification = JSON.parse(json);
		var div = $(this).parent();
		div.empty();
		$.ajax({
			url : "deleteRequest",
			data:{
				username:notification.userName
			},
			success : function(result) {
				if (result == "OK") {
					div.empty();
					if(notification.alreadyFollowing)
						div.append("<button class='unfollowProfile_btn'>Unfollow</button>");					
					else
						div.append("<button class='followProfile_btn'>Follow</button>");
					div.append("<input class='" + notification.isPrivate +"' type='hidden' name='user' value='" + json +"'>");
				} else {
					showResultMessage("FAILED");
				}
			}
		});
		deleteNotification(notification, div.parent());
		
	});
	
	//EVENTO DEL TASTO "REJECT_REQUEST" [PER RIFIUTARE LA RICHIESTA]
	$(document).on('click', '.declineProfile_btn', function() {
		var json = $(this).parent().find('input').attr('value');
		var notification = JSON.parse(json);
		var div = $(this).parent();
		div.empty();
		$.ajax({
			url : "rejectRequest",
			data:{
				username: notification.userName
			},
			success : function(result) {
				if (result == "OK") {
					location.reload();
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
	
	function deleteNotification(notification, div){
		$.ajax({
			url : "deleteNotification",
			data:{
				id:notification.idNotification
			},
			success : function(result) {
				if (result == "OK") {
					div.empty();
				}
				else {
					showResultMessage("FAILED");
				}
			}
		});
	}
	
});

