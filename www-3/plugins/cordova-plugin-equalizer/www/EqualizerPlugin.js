cordova.define("cordova-plugin-equalizer.EqualizerPlugin", function(require, exports, module) {
var exec = require('cordova/exec');

module.exports = {
  present : function(){
    exec(
      function(){},
      function(){},
      "EqualizerPlugin",
      "present",
      []
    )
  },
  available : function(success, error){
    exec(
      success,
      error,
      "EqualizerPlugin",
      "available",
      []
    )
  }
};


});
