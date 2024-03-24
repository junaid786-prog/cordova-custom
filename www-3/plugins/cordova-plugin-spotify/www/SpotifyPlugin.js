cordova.define("cordova-plugin-spotify.SpotifyPlugin", function(require, exports, module) {
var exec = require('cordova/exec');

module.exports = {
  init : function(){
    exec(
      function(){},
      function(){},
      "SpotifyPlugin",
      "init",
      []
    )
  },
  uninit : function(){
    exec(
      function(){},
      function(){},
      "SpotifyPlugin",
      "uninit",
      []
    )
  },
  pause : function(){
    exec(
      function(){},
      function(){},
      "SpotifyPlugin",
      "pause",
      []
    )
  },
  resume : function(){
    exec(
      function(){},
      function(){},
      "SpotifyPlugin",
      "resume",
      []
    )
  },
  next : function(){
    exec(
      function(){},
      function(){},
      "SpotifyPlugin",
      "next",
      []
    )
  },
  prev : function(){
    exec(
      function(){},
      function(){},
      "SpotifyPlugin",
      "prev",
      []
    )
  },
  getPlayerState: function() {
    exec(
      function(){},
      function(){},
      "SpotifyPlugin",
      "getAllPlayerStateNotifications",
      []
    )
  },
  Events : {
    onPause : function(args){
      cordova.fireWindowEvent('spotifyOnPause')
    },
    onPlay : function(args){
      cordova.fireWindowEvent('spotifyOnPlay')
    },
    onTrackChanged : function(args){
      cordova.fireWindowEvent('spotifyOnTrackChanged', {songTitle:args.title, songArtist:args.artist, songDuration:args.dur});
    },
    onPositionChanged : function(args){
      cordova.fireWindowEvent('spotifyOnPositionChanged', {songPosition:args});
    },
    onArtworkChanged : function(args){
      cordova.fireWindowEvent('spotifyOnArtworkChanged', {songArtworkFile:args.file});
    },
    onPlayError :function(error){
      // alert(error[0]);//error[0] - error message
    },
    onPrevTrackEnabled:function(args) {
      cordova.fireWindowEvent('spotifyOnPrevTrackEnabled', {enabled:args});
    },
    onSpotifyNotInstalled:function(args) {
      cordova.fireWindowEvent('spotifyOnSpotifyNotInstalled');
    }
  }
};


});
