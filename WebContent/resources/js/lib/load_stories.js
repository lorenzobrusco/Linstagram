var createStories = function(){
	skin = 'Snapgram';
	var skins = {
			'Snapgram': {
				'avatars': true,
				'list': false,
				'autoFullScreen': false,
				'cubeEffect': true
			}
	};
	var timestamp = function(date) {
		return date/1000;
	};

	var stories = new Zuck('stories', {
		backNative: true,
		previousTap: true,
		autoFullScreen: skins['autoFullScreen'],
		skin: skin,
		avatars: skins['avatars'],
		list: skins['list'],
		cubeEffect: skins['cubeEffect'],
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
