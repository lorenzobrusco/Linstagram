var loadStories = function(){

	var timestamp = function(date) {
		return date/1000;
	};
	
	var createStories = function(){
		var stories = [];
		var list = $(".story");
		for(var i=0; i < list.length;i++){
			stories[i] = [];
			var el = list[i];
			stories[i]["id"] = $(el).attr("data-id");
			stories[i]["photo"] = $(el).attr("data-photo");
			stories[i]["name"]= $(el).attr("data-id");
			stories[i]["seen"] = $(el).attr("data-seen");
			stories[i]["items"] = [];
			
			var items = $(el).children(".items").children("li");
			for(var j=0; j < items.length;j++){
				var a = $(items[j]).children("a");
				var item = buildItem(
						$(items[j]).attr("data-id"),
						$(a).attr("data-type"),
						$(a).attr("data-length"),
						$(a).attr("href"),
						"",
						"",
						false,
						$(a).attr("data-seen"),
						timestamp($(items[j]).attr("data-time")));
				stories[i]["items"][j] = item;
			}
			
		}
		return stories;
	}
	
	var zuck = new Zuck('stories', {
		backNative: true,
		previousTap: true,
		autoFullScreen: false,
		skin: 'Snapgram',
		avatars: true,
		list: false,
		cubeEffect: true,
		localStorage: false,
		stories: createStories(),
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
					console.log(stories);
					var currStory = zuck.data[storyId]['currentItem'];
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

loadStories();
