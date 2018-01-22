function Stories(){

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
//		var list = $("#stories [data-id='"+idStory+"'] .items").children("li");
//		for(var i = 0; i< list.length;i++){
////			console.log($(list[i]));
//			if(!$(list[i]).hasClass("seen")){
//				return;
//			}
//		}
		for(var i = 0; i< zuck.data[idStory].items.length;i++){
			if(!zuck.data[idStory].items[i]["seen"]=="true")
				return;
		}
		$("#stories [data-id='"+idStory+"']").addClass("seen");
	}

	var zuck = new Zuck('stories', {
		backNative: false,
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
					
					if (typeof currStory === "undefined") 
						currStory=0;
					
					var id = $("#stories [data-id='"+storyId+"'] .items li:nth-child("+(currStory+1)+")").attr("data-id");
					setUserStoryModal();
					viewsStory(storyId,id);

					if(storyId == loggedUser)
					{
						loadStoriesViewer();
						var currStory = zuck.data[storyId]['currentItem'];
						setNumberViewers(id);
					}
					checkAllViewed(storyId);
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
					if(storyId == loggedUser){
						setNumberViewers(nextStoryId);
					}else{
						
					}
					viewsStory(storyId,nextStoryId);
					callback();
					checkAllViewed(storyId);
				}
			},
			'onView': function onView(storyId) {

				var currStory = zuck.data[storyId]['currentItem'];
				if (typeof currStory === "undefined") 
					currStory=0;
				var id = $("#stories [data-id='"+storyId+"'] .items li:nth-child("+(currStory+1)+")").attr("data-id");
				if(id != undefined)
				{
					if(storyId == loggedUser){
						setNumberViewers(id);
					}
					
					viewsStory(storyId,id);

					checkAllViewed(storyId);
				}
			},'onEnd': function(storyId, callback) { // on end story
				callback();
				setUserStories(loggedUser);
			},
		}
	});
	this.storiesList = zuck.data;

	var setUserStories = function(loggedUser){

		$("#stories [data-id='"+loggedUser+"']").addClass("logged-user");
		$(".logged-user").prependTo("#stories");
		$(".logged-user .info strong").html("Tu");

		if(zuck.data[loggedUser].items.length == 0){
			$(".logged-user").addClass("empty-stories");
			$(".logged-user > a").append('<a class="plus-badge"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span></a>');
		}else{
			$(".logged-user").removeClass("empty-stories");
			$(".logged-user  a.plus-badge").remove();

		}
	}

	var loadStoriesViewer = function(){
		$.ajax({
			url:"storiesViewer", 
			type:"GET",
			success: function(result) {
				viewers = [];
				for(var i=0; i < result.length; i++) {
					viewers[result[i].id]=[];
					viewers[result[i].id] = result[i].users;
				}
			}
		})
	} 
	var deleteStory =  function(){
		var currStory = zuck.data[loggedUser]['currentItem'];
		
		if (typeof currStory === "undefined") 
			currStory=0;
		
		var id = $("#stories [data-id='"+loggedUser+"'] .items li:nth-child("+(currStory+1)+")").attr("data-id");
		$.ajax({
			url:"deleteStory",
			type:"POST",
			data:{idStory:id},
			success: function(result) {
				if(result == "OK"){
					removeStoryItem(loggedUser,id);
					new Noty({
						text: '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Complete!</p> Good! Your story has been deleted!',
						theme: 'nest',
						type: 'success',
						layout: 'bottomLeft',
						timeout:2000,
						progressBar: true
					}).show();
				}else{
					new Noty({
							text: '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Failed!</p> You story has not been deleted!',
							theme: 'nest',
							type: 'error',
							layout: 'bottomLeft',
							timeout:4000,
							progressBar: true
					}).show();
				}
			}
		});
		
	}
		
	var setUserStoryModal = function(){
		var footer ='<div class="footer"><div class="buttons-container"></div></div>';
		$("#zuck-modal-content .story-viewer[data-story-id='"+loggedUser+"']").append(footer);
		var modalViewer = '<div class="modal fade" id="viewerModal" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h4 class="modal-title"><b>Viewer</b></h4></div><div class="modal-body"><ul></ul></div></div></div></div>';
//		$("#zuck-modal-content .story-viewer[data-story-id='"+loggedUser+"']").append(modalViewer);
		var viewerbutton = '<div class="left-button"><a id="viewers-button" data-toggle="modal" data-target="#viewerModal"><i class="glyphicon glyphicon-eye-open"></i><strong></strong></a></div>'
		$("#zuck-modal-content .story-viewer[data-story-id='"+loggedUser+"'] .buttons-container").append(viewerbutton);
		var removebutton ='<div class="right-button"><a id="remove-button"><i class="glyphicon glyphicon-trash"></i></a></div>';
		$("#zuck-modal-content .story-viewer[data-story-id='"+loggedUser+"'] .buttons-container").append(removebutton);
		
		//REMOVE STORY EVENT
		$("#remove-button").click(e => {
			$("#zuck-modal .viewing").addClass("paused");
			var n = new Noty({
			  text: '<h4>Do you want to remove the story?</h4>',
			  theme: 'nest',
			  type: 'alert',
				layout: 'center',
			  buttons: [
			    Noty.button('YES', 'btn btn-danger', function () {
						deleteStory();
						$("#removeModal").modal('toggle');
						$("#zuck-modal .close").click();
						n.close();
			    }, {id: 'button1', 'data-status': 'ok'}),
			    
			    Noty.button('NO', 'btn btn-default pull-right', function () {
			    		$("#zuck-modal .viewing").removeClass("paused");
			        n.close();
			    })
			  ]
			}).show();
			
		});
		
	}
	var viewsStory= function(user,story){
		$.ajax({
			url:"storyViewed", 
			data:{idStory:story},
			success: function(result) {
			}	
		})
		for(var i = 0; i< zuck.data[user].items.length;i++)
			if(zuck.data[user].items[i]["id"]==story){		
				zuck.data[user].items[i]["seen"]="true";
				$("#stories [data-id='"+user+"'] li[data-id='"+story+"']").addClass("seen");
				return;
			}
	}
	
	var setNumberViewers = function(idStory){
		$("#zuck-modal-content .story-viewer #viewers-button strong").text(viewers[idStory].length);
	}
	
	var removeStoryItem = function(user,story){
		
		$("#stories [data-id='"+user+"'] [data-id='"+story+"']").remove();
		var storylist = zuck.data[user].items;
		zuck.data[user]['currentItem'] = 0;
		for(var i=0; i < storylist.length;i++)
			if(storylist[i].id == story){
				storylist.splice(i,1);
				break;
			}
		setUserStories(user);
		
	}

	var addViewer = function(idStory){
		$("#viewerModal .modal-body ul").empty();
		var template ='<li class="viewer"><div id="user_enter"><form role="form" action="userPage"><button id="button_form" name="username" value=""><div id="button_user"><div id="img_div"><img id="img" src=""></img></div><div id="info_div"><div id="username"><b></b></div><div id="name"></div></div></div></button></form></li>';
		for(var i=0; i < viewers[idStory].length; i++) {
			var el = $(template);
			$(el).find("#img").attr("src",viewers[idStory][i].photo);
			$(el).find("#button_form").attr("value",viewers[idStory][i].username);
			$(el).find("#info_div #username b").text(viewers[idStory][i].username);
			$(el).find("#info_div #username").attr("id",viewers[idStory][i].username);
			var name="";
			
			if(viewers[idStory][i].name != undefined)
				name = name+viewers[idStory][i].name+" ";
			if(viewers[idStory][i].name != undefined)
				name = name+viewers[idStory][i].surname;
			
			$(el).find("#info_div #name").text(name);
			
			$("#viewerModal .modal-body ul").append(el);
		}

	}
	
	this.addStoryItem = function(data){
		var item = buildItem(data.id,data.type,data.length,data.url,"","",false,data.viewed,timestamp(data.date));
		zuck.addItem(loggedUser,item);
		viewers[data.id]=[];
		$("#stories [data-id='"+loggedUser+"']").removeClass("seen");
		$("#stories [data-id='"+loggedUser+"']").attr("data-seen","false");

		setUserStories(loggedUser);
		
		
	}
	
//	this.addStory = function(sData){
//		$.ajax({
//			type: 'POST',
//			url: 'addStory',
//			enctype: 'multipart/form-data',
//			data: sData,
//			processData: false,
//			contentType: false,
//			success: function(data) {
////				console.log(data);
//				if(data != null){
//					
//					addStoryItem(data);
//
//					new Noty({
//						text: '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Complete!</p> Good! Your story has been added!',
//						theme: 'nest',
//						type: 'success',
//						layout: 'bottomLeft',
//						timeout:2000,
//						progressBar: true
//					}).show();
//				}else{
//					
//					new Noty({
//						text: '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Failed!</p> You story has not been added!',
//						theme: 'nest',
//						type: 'error',
//						layout: 'bottomLeft',
//						timeout:4000,
//						progressBar: true
//				}).show();
//				}
//
//			}
//		});
//	}
	
	$("#viewerModal").on('show.bs.modal',function(e){
		$("#zuck-modal .viewing").addClass("paused");
		var currStory = zuck.data[loggedUser]['currentItem'];
		
		if (typeof currStory === "undefined") 
			currStory=0;
		
		var id = $("#stories [data-id='"+loggedUser+"'] .items li:nth-child("+(currStory+1)+")").attr("data-id");
		addViewer(id);
	});
	$("#viewerModal").on('hide.bs.modal',function(e){
		$("#zuck-modal .viewing").removeClass("paused");
	});
	
	$("#removeModal").on('show.bs.modal',function(e){
		$("#zuck-modal .viewing").addClass("paused");
	});
	
	$("#removeModal").on('hide.bs.modal',function(e){
		$("#zuck-modal .viewing").removeClass("paused");
	});
	
	setUserStories(loggedUser);
	loadStoriesViewer();
};

var Stories = new Stories();
