var Stories = function(){

	var timestamp = function(date) {
		return date/1000;
	}
	var loggedUser = "";
	var viewers = "";

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
					if(storyId == loggedUser)
					{
						var currStory = zuck.data[storyId]['currentItem'];
						var id = $("#stories [data-id='"+storyId+"'] .items li:nth-child("+(currStory+1)+")").attr("data-id");
						addNumberViewersToStoryModal();
						setNumberViewers(id);
					}
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
					setNumberViewers(nextStoryId);
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
					setNumberViewers(id);
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

	var loadStoriesViewer = function(){
		$.ajax({
			url:"storiesViewer", 
			success: function(result) {
				viewers = [];
				for(var i=0; i < result.length; i++) {
					viewers[result[i].id]=[];
					viewers[result[i].id] = result[i].users;
				}
			}
		})
	} 

	var addNumberViewersToStoryModal = function(){
		var footer ='<div class="footer"><div class="left"><a id="viewers-button" data-toggle="modal" data-target="#viewerModal"><i class="glyphicon glyphicon-eye-open"></i><strong></strong></a></div><div class="right"></div></div>';
		$("#zuck-modal-content .story-viewer").append(footer);
		var modalViewer = '<div class="modal fade" id="viewerModal" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h4 class="modal-title"><b>Viewer</b></h4></div><div class="modal-body"><ul></ul></div></div></div></div>';
		$("#zuck-modal-content .story-viewer").append(modalViewer);

		$("#viewerModal").on('show.bs.modal',function(e){
			$("#zuck-modal .viewing").addClass("paused");
			var currStory = zuck.data[loggedUser]['currentItem'];
			var id = $("#stories [data-id='"+loggedUser+"'] .items li:nth-child("+(currStory+1)+")").attr("data-id");
			addViewer(id);
		});
//		$("#zuck-modal-content .story-viewer #viewers-button").click(function(event){
//		});

	}

	var setNumberViewers = function(idStory){
		$("#zuck-modal-content .story-viewer #viewers-button strong").text(viewers[idStory].length);
	}

	var addViewer = function(idStory){
		console.log(idStory);
		$("#zuck-modal-content .story-viewer .modal-body ul").empty();
		var template ='<li class="viewer"><div id="user_enter"><form role="form" action="userPage"><button id="button_form" name="usernameOther" value=""><div id="button_user"><div id="img_div"><img id="img" src=""></img></div><div id="info_div"><div id="username"><b></b></div><div id="name"></div></div></div></button></form></li>';
		for(var i=0; i < viewers[idStory].length; i++) {
			var el = $(template);
			console.log(viewers[idStory][i]);
			$(el).find("#img").attr("src",viewers[idStory][i].photo);
			$(el).find("#button_form").attr("value",viewers[idStory][i].username);
			$(el).find("#info_div #username b").text(viewers[idStory][i].username);
			$(el).find("#info_div #username").attr("id",viewers[idStory][i].username);
			$(el).find("#info_div #name").text(viewers[idStory][i].name+" "+viewers[idStory][i].surname);
			
			$("#zuck-modal-content .story-viewer .modal-body ul").append(el);
		}

	}

	setUserStories(loggedUser);
	loadStoriesViewer();

};

var Stories = new Stories();
