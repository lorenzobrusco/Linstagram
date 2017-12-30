var loadStories = function(){

	var timestamp = function(date) {
		return date/1000;
	};
	var loggedUser = "";
	
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

			var isLoggedUser = $(el).attr("data-logged-user");
			if(isLoggedUser == "true"){
//				$(list[i]).addClass("logged-user");
				loggedUser = $(el).attr("data-id");
				stories[i]["name"]="Tu";

			}
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

	var checkAllViewed = function(idStory){
		var list = $("#stories [data-id='"+idStory+"'] .items").children("li");
		for(var i = 0; i< list.length;i++){
			if(!$(list[i]).hasClass("seen"))
				return;
		}
		$("#stories [data-id='"+idStory+"']").addClass("seen");

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
				if($("#stories [data-id='"+storyId+"'] .items").children("li").length != 0){
					document.getElementById("posts").classList.add("hidden-posts");
					callback();
				}
				else{
					$("#open-story-modal").click();
				}
			},
			'onClose': function(storyId, callback) { // on close story viewer
				callback();
				document.getElementById("posts").classList.remove("hidden-posts");
				setUserStories(loggedUser);
			},
			'onNavigateItem': function(storyId, nextStoryId, callback) { // on navigate item of story
				if(nextStoryId != undefined)
				{
					$.ajax({
						url:"storyViewed", 
						data:{idStory:nextStoryId},
						success: function(result) {
						}	
					})
					callback();
					checkAllViewed(storyId);
				}
			},
			'onView': function onView(storyId) {
				var currStory = zuck.data[storyId]['currentItem'];
				var id = $("#stories [data-id='"+storyId+"'] .items li:nth-child("+(currStory+1)+")").attr("data-id");
				if(id != undefined)
				{
					$.ajax({
						url:"storyViewed", 
						data:{idStory:id},
						success: function(result) {
						}	
					})
					checkAllViewed(storyId);
				}
			},'onEnd': function(storyId, callback) { // on end story
				callback();

				setUserStories(loggedUser);
			},
		}
	});

	var setUserStories = function(loggedUser){

		$("#stories [data-id='"+loggedUser+"']").addClass("logged-user");
		$(".logged-user").prependTo("#stories");
		$(".logged-user .info strong").html("Tu");

		if($(".logged-user .items").children("li").length == 0){
			$(".logged-user").addClass("empty-stories");
			$(".logged-user > a").append('<a class="plus-badge"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span></a>');
		}
	}
	
	setUserStories(loggedUser);
};



loadStories();
