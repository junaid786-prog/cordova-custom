cordova.define("cordova-plugin-applemusic.appleMusic", function(require, exports, module) {
var exec = require('cordova/exec');

window.appleMusicPluginPlaying = (function () {

});
window.appleMusicPluginStopped = (function () {

});
window.appleMusicPluginTrackChanged = (function (args) {
    // args.title
    // args.artist
    // args.dur
    // args.artworkFile
});
window.appleMusicPluginPositionChanged = (function (position) {
    // 
});
window.appleMusicPluginAuthorizationDenied = (function() {

});
window.appleMusicPluginAuthorizationRestricted = (function() {

});
module.exports = {
    init: function () {
        exec(function(){}, function(){}, "AppleMusic", "init", []);
    },
    uninit: function () {
        exec(function(){}, function(){}, "AppleMusic", "uninit", []);
    },
    pause: function () {
        exec(function(){}, function(){}, "AppleMusic", "pause", []);
    },
    resume: function () {
        exec(function(){}, function(){}, "AppleMusic", "resume", []);
    },
    next: function () {
        exec(function(){}, function(){}, "AppleMusic", "next", []);
    },
    prev: function () {
        exec(function(){}, function(){}, "AppleMusic", "prev", []);
    },
    getNowPlayingTrackInfo: function () {
        exec(function(){}, function(){}, "AppleMusic", "getNowPlayingTrackInfo", []);
    },
    onPlay: function (f) {
        window.appleMusicPluginPlaying = f;
    },
    onStop: function (f) {
        window.appleMusicPluginStopped = f;
    },
    onTrackChanged: function(f) {
        window.appleMusicPluginTrackChanged = f;
    },
    onPositionChanged: function(f) {
        window.appleMusicPluginPositionChanged = f;
    },
    onAuthorizationDenied: function(f) {
        window.appleMusicPluginAuthorizationDenied = f;
    },
    onAuthorizationRestricted: function(f) {
        window.appleMusicPluginAuthorizationRestricted = f;
    },
};

});
