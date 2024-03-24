var exec = require('cordova/exec');

module.exports = {
  startScan : function(){
    exec(
      function(){},
      function(){},
      "ClassicBluetoothPlugin",
      "startScan",
      []
    )
  },
  stopScan : function(){
    exec(
      function(){},
      function(){},
      "ClassicBluetoothPlugin",
      "stopScan",
      []
    )
  },
  connect : function(name, uuid){
    var options = {'name':name, 'uuid':uuid}
    exec(
      function(){},
      function(){},
      "ClassicBluetoothPlugin",
      "connect",
      [options]
    )
  },
  Events : {
    onConnectResult : function(args){
      console.log("FROM inside")
      console.log(args)
      cordova.fireWindowEvent('classicBluetoothOnConnectResult', {result:args.result, name:args.name, uuid:args.uuid, macAddr:args.macAddr});
    },
    onDeviceFound: function(args) {
      cordova.fireWindowEvent('classicBluetoothOnDeviceFound', {name:args.name, uuid:args.uuid, macAddr:args.macAddr});

    }
  }
};