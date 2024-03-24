cordova.define("cordova-plugin-ute.UTEPlugin", function(require, exports, module) {
var exec = require('cordova/exec');

module.exports = {
  startScan : function(successCallback, errorCallback){
    return exec(
      successCallback,
      errorCallback,
      "UTEPlugin",
      "startScan",
      []
    )
  },
  stopScan : function(successCallback, errorCallback){
    return exec(
      successCallback,
      errorCallback,
      "UTEPlugin",
      "stopScan",
      []
    )
  },
  connect : function(id, successCallback, errorCallback){
    return exec(
      successCallback,
      errorCallback,
      "UTEPlugin",
      "connect",
      [id]
    )
  },
  disconnect : function(successCallback, errorCallback){
    return exec(
      successCallback,
      errorCallback,
      "UTEPlugin",
      "disconnect",
      []
    )
  },
  startHeartRateStreaming : function(successCallback, errorCallback){
    return exec(
      successCallback,
      errorCallback,
      "UTEPlugin",
      "startHeartRateStreaming",
      []
    )
  },
  stopHeartRateStreaming : function(successCallback, errorCallback){
    return exec(
      successCallback,
      errorCallback,
      "UTEPlugin",
      "stopHeartRateStreaming",
      []
    )
  },
  Events : {
    onDeviceFound : function(args){
      cordova.fireWindowEvent('uteOnDeviceFound', {name:args.name, id:args.identifier});
    },
    onConnected : function(args){
      cordova.fireWindowEvent('uteOnConnected', {name:args.name, id:args.identifier});
    },
    onDisconnected : function(args){
      cordova.fireWindowEvent('uteOnDisconnected', {name:args.name, id:args.identifier, error:args.error});
    },
    onHeartRateStateChanged : function(args) {
      cordova.fireWindowEvent('uteOnHeartRateStateChanged', {state:args.state});
    },
    onHeartRateChanged : function(args){
      cordova.fireWindowEvent('uteOnHeartRateChanged', {hr:args.hr, type:args.type});
    }
  }
};


});
