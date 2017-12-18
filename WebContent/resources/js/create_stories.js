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
      localStorage: true,
      stories: [
      {
        id: "ramon",
        photo: "https://avatars1.githubusercontent.com/u/2271175?v=3&s=460",
        name: "Ramon",
        link: "https://ramon.codes",
        lastUpdated: timestamp(),
        items: [
        buildItem("ramon-1", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", '', false, false, timestamp()),
        buildItem("ramon-2", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", 'https://ramon.codes', 'Visit my Portfolio', false, timestamp()),
        buildItem("ramon-3", "video", 0, "https://scontent-gru2-2.cdninstagram.com/t50.2886-16/14965218_193969377722724_482497862983221248_n.mp4", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", '', false,  false, 1504023497)
        ]
      },
       {
        id: "lorenzo",
        photo: "https://scontent-mxp1-1.cdninstagram.com/t51.2885-19/s320x320/20482280_307729109689765_5209255955170066432_a.jpg",
        name: "Lorenzo",
        link: "https://ramon.codes",
        lastUpdated: timestamp(),
        items: [
        buildItem("ramon-1", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", '', false, false, timestamp()),
        buildItem("ramon-2", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", '', false, false, timestamp()),
        buildItem("ramon-3", "video", 0, "https://scontent-gru2-2.cdninstagram.com/t50.2886-16/14965218_193969377722724_482497862983221248_n.mp4", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", '', false,  false, 1504023497)
        ]
      },
      {
        id: "lorenzo1",
        photo: "https://scontent-mxp1-1.cdninstagram.com/t51.2885-19/s320x320/20482280_307729109689765_5209255955170066432_a.jpg",
        name: "Lorenzo",
        link: "https://ramon.codes",
        lastUpdated: timestamp(),
        items: [
        buildItem("ramon-1", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", '', false, false, timestamp()),
        buildItem("ramon-2", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", 'https://ramon.codes', 'Visit my Portfolio', false, timestamp()),
        buildItem("ramon-3", "video", 0, "https://scontent-gru2-2.cdninstagram.com/t50.2886-16/14965218_193969377722724_482497862983221248_n.mp4", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", '', false,  false, 1504023497)
        ]
      },
      {
        id: "lorenzo2",
        photo: "https://scontent-mxp1-1.cdninstagram.com/t51.2885-19/s320x320/20482280_307729109689765_5209255955170066432_a.jpg",
        name: "Lorenzo",
        link: "https://ramon.codes",
        lastUpdated: timestamp(),
        items: [
        buildItem("ramon-1", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", '', false, false, timestamp()),
        buildItem("ramon-2", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", 'https://ramon.codes', 'Visit my Portfolio', false, timestamp()),
        buildItem("ramon-3", "video", 0, "https://scontent-gru2-2.cdninstagram.com/t50.2886-16/14965218_193969377722724_482497862983221248_n.mp4", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", '', false,  false, 1504023497)
        ]
      },
      {
        id: "lorenzo3",
        photo: "https://scontent-mxp1-1.cdninstagram.com/t51.2885-19/s320x320/20482280_307729109689765_5209255955170066432_a.jpg",
        name: "Lorenzo",
        link: "https://ramon.codes",
        lastUpdated: timestamp(),
        items: [
        buildItem("ramon-1", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", '', false, false, timestamp()),
        buildItem("ramon-2", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", 'https://ramon.codes', 'Visit my Portfolio', false, timestamp()),
        buildItem("ramon-3", "video", 0, "https://scontent-gru2-2.cdninstagram.com/t50.2886-16/14965218_193969377722724_482497862983221248_n.mp4", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", '', false,  false, 1504023497)
        ]
      },
      {
        id: "lorenzo4",
        photo: "https://scontent-mxp1-1.cdninstagram.com/t51.2885-19/s320x320/20482280_307729109689765_5209255955170066432_a.jpg",
        name: "Lorenzo",
        link: "https://ramon.codes",
        lastUpdated: timestamp(),
        items: [
        buildItem("ramon-1", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", '', false, false, timestamp()),
        buildItem("ramon-2", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", 'https://ramon.codes', 'Visit my Portfolio', false, timestamp()),
        buildItem("ramon-3", "video", 0, "https://scontent-gru2-2.cdninstagram.com/t50.2886-16/14965218_193969377722724_482497862983221248_n.mp4", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", '', false,  false, 1504023497)
        ]
      },
      {
        id: "lorenzo5",
        photo: "https://scontent-mxp1-1.cdninstagram.com/t51.2885-19/s320x320/20482280_307729109689765_5209255955170066432_a.jpg",
        name: "Lorenzo",
        link: "https://ramon.codes",
        lastUpdated: timestamp(),
        items: [
        buildItem("ramon-1", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10810091_1527190460857578_541280638_n.jpg", '', false, false, timestamp()),
        buildItem("ramon-2", "photo", 3, "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", 'https://ramon.codes', 'Visit my Portfolio', false, timestamp()),
        buildItem("ramon-3", "video", 0, "https://scontent-gru2-2.cdninstagram.com/t50.2886-16/14965218_193969377722724_482497862983221248_n.mp4", "https://scontent-gru2-2.cdninstagram.com/t51.2885-15/e15/10597412_455246124639813_1360162248_n.jpg", '', false,  false, 1504023497)
        ]
      },
      {
        id: "gorillaz",
        photo: "https://lh3.googleusercontent.com/xYFz6B9FHMQq7fDOI_MA61gf0sNdzGBbdR7-mZ7i4rEVvE_N-kZEY_A4eP74Imcf8Sq3FYxAgd4eJA=w200",
        name: "Gorillaz",
        link: "",
        lastUpdated: timestamp(),
        items: [
        buildItem("gorillaz-1", "video", 0, "https://instagram.frao1-1.fna.fbcdn.net/t50.2886-16/17886251_1128605603951544_572796556789415936_n.mp4", "https://pbs.twimg.com/media/C8VgMQ8WAAE5i9M.jpg:small", '', false, false, timestamp()),
        buildItem("gorillaz-2", "photo", 3, "https://pbs.twimg.com/media/C8VgMQ8WAAE5i9M.jpg:large","https://pbs.twimg.com/media/C8VgMQ8WAAE5i9M.jpg:small", '', false, false, timestamp()),
        ]
      },
      {
        id: "starboy",
        photo: "https://lh3.googleusercontent.com/nMxfllzaAmaCCZJEMiKe2EARjyUNItQ-mdgAq6he-LWXwkIWbiiBIHyC3rGiqDu6fgyVK6NnjcgueA=w200",
        name: "The Weeknd",
        link: "",
        lastUpdated: timestamp(),
        items: [
        buildItem("starboy-1", "photo", 5, "https://pbs.twimg.com/media/C6f-dTqVQAAiy1K.jpg:large", "https://pbs.twimg.com/media/C6f-dTqVQAAiy1K.jpg:small", '', false, false, timestamp())
        ]
      },
      {
        id: "qotsa",
        photo: "https://lh3.googleusercontent.com/nE4grkY8s88P_1mFFA06mGCNshhqtIz4C4y2dV7AZbm0360zopRKDMCYtUHR0uQR2DAfYMRZdA=s180-p-e100-rwu-v1",
        name: "QOTSA",
        link: "",
        lastUpdated: timestamp(),
        items: [
        buildItem("qotsa-1", "photo", 10, "https://pbs.twimg.com/media/C8wTxgUVoAALPGA.jpg:large", "https://pbs.twimg.com/media/C8wTxgUVoAALPGA.jpg:small", '', false, false, timestamp())
        ]
      }
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
