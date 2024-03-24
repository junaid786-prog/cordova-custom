cordova.define("cordova-plugin-audio-io.audioIOPlugin", function(require, exports, module) {
var exec = require('cordova/exec');

window.AudioIOPluginOnAudioInputFoundBegin = (function () {
});
window.AudioIOPluginOnAudioInputFound = (function (args) {
    // args.name
    // args.type
    // args.UID
});
window.AudioIOPluginOnAudioInputFoundEnd = (function () {
});
module.exports = {
    start: function () {
        exec(function(){}, function(){}, "AudioIOPlugin", "start", []);
    },
    stop: function () {
        exec(function(){}, function(){}, "AudioIOPlugin", "stop", []);
    },
    onAudioInputFound: function (f) {
        window.AudioIOPluginOnAudioInputFound = f;
    },
    onAudioInputFoundBegin: function(f) {
        window.AudioIOPluginOnAudioInputFoundBegin = f;
    },
    onAudioInputFoundEnd: function(f) {
        window.AudioIOPluginOnAudioInputFoundEnd = f;
    }
};

});
