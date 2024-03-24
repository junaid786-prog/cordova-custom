cordova.define("cordova-plugin-azure-storage.AzureStoragePlugin", function(require, exports, module) {
var exec = require('cordova/exec');

module.exports = {
  uploadCoreTrainingRawData : function(name, datafile, successCallback, errorCallback){
    return exec(
      successCallback,
      errorCallback,
      "AzureStoragePlugin",
      "uploadCoreTrainingRawData",
      [name, datafile]
    )
  }
};


});
