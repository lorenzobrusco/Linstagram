<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="${pageContext.request.contextPath}/resources/js/lib/stories.js"></script>
</head>
<script type="text/javascript">

var createStories = function(){

	var timestamp = function(date) {
		return date/1000;
	};

	var stories = new Zuck('stories', {
		backNative: true,
		previousTap: true,
		autoFullScreen: false,
		skin: 'Snapgram',
		avatars: true,
		list: false,
		cubeEffect: true,
		localStorage: false,
		stories: [
			<c:forEach items="${followedUsersStories}" var="user">
			{
				id: "${user.username}",
				photo: "${user.photoProfile}",
				name: "${user.username}",
				seen:"${user.allSeen}",
				items: [
					<c:forEach items="${user.stories}" var="story">
					buildItem(	"${story.id}",
							"${story.type}",
							3,
							"${story.url}",
							"",
							'',
							false,
							"${story.viewed}", 
							timestamp("${story.date}")
					),
					</c:forEach>
					]
			},
			</c:forEach>
			], 
			callbacks : {
				'onOpen': function(storyId, callback) { // on open story viewer
					document.getElementById("posts").classList.add("hidden-posts");
					callback();
				},
				'onClose': function(storyId, callback) { // on close story viewer
					document.getElementById("posts").classList.remove("hidden-posts");
					callback();
				},
				'onNavigateItem': function(storyId, nextStoryId, callback) { // on navigate item of story
//					console.log("update viewer for story "+ nextStoryId);
				
					if(nextStoryId != undefined)
					{
						$.ajax({
							url:"storyViewed", 
							data:{idStory:nextStoryId},
							success: function(result) {
							}	
						})
					}
					callback();
				},
				'onView': function onView(storyId) {
					var currStory = stories.data[storyId]['currentItem'];
					var id = $("#stories [data-id='"+storyId+"'] .items li:nth-child("+(currStory+1)+")").attr("data-id");
//					console.log("update viewer for story "+id);
					
					if(id != undefined)
					{
						$.ajax({
							url:"storyViewed", 
							data:{idStory:id},
							success: function(result) {
							}	
						})
					}
				},
			}
	});
};

createStories();
</script>
</html>