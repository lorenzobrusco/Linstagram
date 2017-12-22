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
      }
    });
  };

  createStories();
