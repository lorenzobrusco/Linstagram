$(document).ready(function() {
	updateNotificationBadge();
	window.setInterval(function() {
		updateNotificationBadge();
	},60000);

});

function updateNotificationBadge(){
	$.ajax({
		url : "notificationToSee",
		type : "POST",
		success : function(result) {
			if(result > 0){
				var badge_notification="<span class='badge'>"+ result + "</span>"
				$(".badge").remove();
				$("#notification").append(badge_notification);
			}
		}
	});
}