$('#notification')
	.click(function (e) {
	$("#notification_list").html("");
	$.ajax({
			url : "openNotification",
			type : "GET",
			success : function(result) {
				var content_notification_popover = ""
				for (var i = 0; i < result.length; i++) {
					content_notification_popover += "<div class='notification_item'>"
					content_notification_popover += "<div class='user_from_notification'>"
					content_notification_popover += "<img src="+ result[i].userPhoto+">"
					content_notification_popover += "</div>"
					content_notification_popover += "<div class='context_notification'>"
					content_notification_popover += "<span><b>" + result[i].userName +"</b> " + result[i].context +"</span>"
					content_notification_popover += "</div>"
					if(result[i].urlPost == null){
						content_notification_popover += "<div class='follow_btn_notification'>"
						content_notification_popover += "<button class='btn btn-primary'>Follow</button>"
						content_notification_popover += "</div>"
					} else {
						content_notification_popover += "<div class='post_notification'>"
						content_notification_popover += "<img src=" + result[i].urlPost + ">"
						content_notification_popover += "</div>"	
					}

					content_notification_popover += "</div>"
						if(i < (result.length -1))
							content_notification_popover += "<hr class='hr_notification'>"
				}
				$("#notification_list").html("");
				$("#notification_list").append(content_notification_popover);
			}
		})
	});