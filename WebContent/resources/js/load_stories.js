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
    var timeIndex = 0;
    var shifts = [35, 60, 60*3, 60*60*2, 60*60*25, 60*60*24*4, 60*60*24*10];
    var timestamp = function() {
      var now = new Date();
      var shift = shifts[timeIndex++] || 0;
      var date = new Date( now - shift*1000);

      return date.getTime() / 1000;
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
        lastUpdated: timestamp(),
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
	      	  					timestamp()),
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
