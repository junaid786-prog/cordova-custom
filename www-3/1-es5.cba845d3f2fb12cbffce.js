function _createForOfIteratorHelper(e,t){var n;if("undefined"==typeof Symbol||null==e[Symbol.iterator]){if(Array.isArray(e)||(n=_unsupportedIterableToArray(e))||t&&e&&"number"==typeof e.length){n&&(e=n);var r=0,i=function(){};return{s:i,n:function(){return r>=e.length?{done:!0}:{done:!1,value:e[r++]}},e:function(e){throw e},f:i}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}var a,c=!0,o=!1;return{s:function(){n=e[Symbol.iterator]()},n:function(){var e=n.next();return c=e.done,e},e:function(e){o=!0,a=e},f:function(){try{c||null==n.return||n.return()}finally{if(o)throw a}}}}function _unsupportedIterableToArray(e,t){if(e){if("string"==typeof e)return _arrayLikeToArray(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?_arrayLikeToArray(e,t):void 0}}function _arrayLikeToArray(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function _classCallCheck(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function _defineProperties(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}function _createClass(e,t,n){return t&&_defineProperties(e.prototype,t),n&&_defineProperties(e,n),e}function _inherits(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function");e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,writable:!0,configurable:!0}}),t&&_setPrototypeOf(e,t)}function _setPrototypeOf(e,t){return(_setPrototypeOf=Object.setPrototypeOf||function(e,t){return e.__proto__=t,e})(e,t)}function _createSuper(e){var t=_isNativeReflectConstruct();return function(){var n,r=_getPrototypeOf(e);if(t){var i=_getPrototypeOf(this).constructor;n=Reflect.construct(r,arguments,i)}else n=r.apply(this,arguments);return _possibleConstructorReturn(this,n)}}function _possibleConstructorReturn(e,t){return!t||"object"!=typeof t&&"function"!=typeof t?_assertThisInitialized(e):t}function _assertThisInitialized(e){if(void 0===e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return e}function _isNativeReflectConstruct(){if("undefined"==typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"==typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],(function(){}))),!0}catch(e){return!1}}function _getPrototypeOf(e){return(_getPrototypeOf=Object.setPrototypeOf?Object.getPrototypeOf:function(e){return e.__proto__||Object.getPrototypeOf(e)})(e)}(window.webpackJsonp=window.webpackJsonp||[]).push([[1],{M3uO:function(e,t,n){"use strict";n.d(t,"a",(function(){return a})),n.d(t,"b",(function(){return c}));var r=n("mrSG"),i=n("yYM5"),a="hrTypeWearFit",c=function(e){_inherits(n,e);var t=_createSuper(n);function n(e,c){var o;return _classCallCheck(this,n),(o=t.call(this,e,c)).onWearFitConnected=function(e){if(void 0===o.notifySubcription){var t=o.getIdFromPeripheralData(e),n=o.getNotifyCharacteristicUUIDFromPeripheralData(e);null!=n&&(o.notifySubcription=o.ble.startNotification(t,o.serviceUUID,n).subscribe((function(e){var t=new Uint8Array(e);171==t[0]&&49==t[4]&&10==t[5]&&o.events.publish(i.c,{heartRate:t[6]})})))}},o.onWearFitDisconnected=function(){void 0!==o.notifySubcription&&(o.notifySubcription.unsubscribe(),o.notifySubcription=void 0)},o.connect=function(e){return r.b(_assertThisInitialized(o),void 0,void 0,regeneratorRuntime.mark((function t(){var n=this;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",this.connectInternal(e,(function(e){n.onWearFitConnected(e)}),(function(e){n.onWearFitDisconnected()})));case 1:case"end":return t.stop()}}),t,this)})))},o.disconnect=function(){return r.b(_assertThisInitialized(o),void 0,void 0,regeneratorRuntime.mark((function e(){var t=this;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,this.disconnectInternal(this.activePeripheralData,(function(){t.onWearFitDisconnected()}));case 3:return e.abrupt("return",e.sent);case 6:throw e.prev=6,e.t0=e.catch(0),e.t0;case 9:case"end":return e.stop()}}),e,this,[[0,6]])})))},o.type=a,o.serviceUUID="6E400001-B5A3-F393-E0A9-E50E24DCCA9E",o}return _createClass(n,[{key:"autoConnect",value:function(){return r.b(this,void 0,void 0,regeneratorRuntime.mark((function e(){var t=this;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,this.autoConnectInternal((function(e){t.onWearFitConnected(e)}),(function(e){t.onWearFitDisconnected()}));case 3:return e.abrupt("return",!0);case 6:e.prev=6,e.t0=e.catch(0),console.log(e.t0);case 9:return e.abrupt("return",!1);case 10:case"end":return e.stop()}}),e,this,[[0,6]])})))}},{key:"startHeartRateStreaming",value:function(){return r.b(this,void 0,void 0,regeneratorRuntime.mark((function e(){var t,n,r;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,void 0===this.activePeripheralData){e.next=19;break}if(void 0===(t=this.getWriteCharacteristicUUIDFromPeripheralData(this.activePeripheralData))){e.next=17;break}return n=this.getIdFromPeripheralData(this.activePeripheralData),(r=new Uint8Array(7))[0]=171,r[1]=0,r[2]=4,r[3]=255,r[4]=49,r[5]=10,r[6]=1,e.next=15,this.ble.writeWithoutResponse(n,this.serviceUUID,t,r.buffer);case 15:e.next=18;break;case 17:console.log("WearFit.startHeartRateStreaming() can't find writeCharacteristicUUID");case 18:return e.abrupt("return",!0);case 19:e.next=24;break;case 21:e.prev=21,e.t0=e.catch(0),console.log("WearFit.startHeartRateStreaming() ".concat(e.t0));case 24:return e.abrupt("return",!1);case 25:case"end":return e.stop()}}),e,this,[[0,21]])})))}},{key:"stopHeartRateStreaming",value:function(){return r.b(this,void 0,void 0,regeneratorRuntime.mark((function e(){var t,n,r;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,void 0===this.activePeripheralData){e.next=19;break}if(void 0===(t=this.getWriteCharacteristicUUIDFromPeripheralData(this.activePeripheralData))){e.next=17;break}return n=this.getIdFromPeripheralData(this.activePeripheralData),(r=new Uint8Array(7))[0]=171,r[1]=0,r[2]=4,r[3]=255,r[4]=49,r[5]=10,r[6]=0,e.next=15,this.ble.write(n,this.serviceUUID,t,r.buffer);case 15:e.next=18;break;case 17:console.log("WearFit.stopHeartRateStreaming() can't find writeCharacteristicUUID");case 18:return e.abrupt("return",!0);case 19:e.next=24;break;case 21:e.prev=21,e.t0=e.catch(0),console.log("WearFit.stopHeartRateStreaming() ".concat(e.t0));case 24:return e.abrupt("return",!1);case 25:case"end":return e.stop()}}),e,this,[[0,21]])})))}}]),n}(i.d)},cPjO:function(e,t,n){"use strict";var r=n("mrSG"),i=n("ZZ/e"),a=n("zP/x"),c=n("M3uO"),o=n("yYM5"),s="hrTypeUTE",u=function(e){_inherits(n,e);var t=_createSuper(n);function n(e,i){var a;return _classCallCheck(this,n),(a=t.call(this,e,i)).startScan=function(e){return r.b(_assertThisInitialized(a),void 0,void 0,regeneratorRuntime.mark((function t(){var n=this;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(t.prev=0,t.t0=!this.isConnecting(),!t.t0){t.next=6;break}return t.next=5,this.stopScan();case 5:t.t0=new Promise((function(t,r){n.scanDeviceCallback=e,cordova.plugins.UTEPlugin.startScan((function(){n.bScanningDevice=!0,t(!0)}),(function(e){n.scanDeviceCallback=void 0,console.log("UteWatch.startScan() failed => "+JSON.stringify(e)),r(e)}))}));case 6:return t.abrupt("return",t.t0);case 9:throw t.prev=9,t.t1=t.catch(0),t.t1;case 12:case"end":return t.stop()}}),t,this,[[0,9]])})))},a.stopScan=function(){return r.b(_assertThisInitialized(a),void 0,void 0,regeneratorRuntime.mark((function e(){var t=this;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",!this.isConnecting()&&new Promise((function(e,n){cordova.plugins.UTEPlugin.stopScan((function(){t.bScanningDevice=!1,t.scanDeviceCallback=void 0,e(!0)}),(function(e){console.log("UteWatch.stopScan() failed => "+JSON.stringify(e)),n(e)}))})));case 1:case"end":return e.stop()}}),e,this)})))},a.isScanning=function(){return a.bScanningDevice},a.connect=function(e){return r.b(_assertThisInitialized(a),void 0,void 0,regeneratorRuntime.mark((function t(){var n=this;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",new Promise((function(t,r){cordova.plugins.UTEPlugin.connect(e,(function(){n.bConnectingDevice=!0,t(!0)}),(function(e){console.log("UteWatch.connect() failed => "+JSON.stringify(e)),r(e)}))})));case 1:case"end":return t.stop()}}),t)})))},a.disconnect=function(){return r.b(_assertThisInitialized(a),void 0,void 0,regeneratorRuntime.mark((function e(){var t=this;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",new Promise((function(e,n){cordova.plugins.UTEPlugin.disconnect((function(){t.bConnectingDevice=!0,t.setLastDeviceId(""),e(!0)}),(function(e){console.log("UteWatch.connect() failed => "+JSON.stringify(e)),n(e)}))})));case 1:case"end":return e.stop()}}),e)})))},a.autoConnect=function(){return r.b(_assertThisInitialized(a),void 0,void 0,regeneratorRuntime.mark((function e(){var t=this;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",""!=this.getLastDeviceId()&&!this.isConnected()&&!this.isConnecting()&&(this.startScan((function(e){e.id==t.getLastDeviceId()&&t.connect(e.id)})),!0));case 1:case"end":return e.stop()}}),e,this)})))},a.isConnecting=function(){return a.bConnectingDevice},a.isConnected=function(){return void 0!==a.connectedDeviceId},a.getConnectedDeviceName=function(){return void 0!==a.connectedDeviceName?a.connectedDeviceName:null},a.getConnectedDeviceId=function(){return void 0!==a.connectedDeviceId?a.connectedDeviceId:null},a.startHeartRateStreaming=function(){a.bHeartRateOn=!0,setTimeout((function(){cordova.plugins.UTEPlugin.startHeartRateStreaming((function(){}),(function(e){console.log("UteWatch.startHeartRateStreaming() failed => "+JSON.stringify(e))}))}),1e3)},a.stopHeartRateStreaming=function(){cordova.plugins.UTEPlugin.stopHeartRateStreaming((function(){a.bHeartRateOn=!1}),(function(e){console.log("UteWatch.stopHeartRateStreaming() failed => "+JSON.stringify(e))}))},a.onDeviceFound=function(e){void 0!==a.scanDeviceCallback&&a.scanDeviceCallback(e)},a.onDeviceConnected=function(e){a.connectedDeviceName=e.name,a.connectedDeviceId=e.id,a.bConnectingDevice=!1,a.events.publish(o.a,e)},a.onDeviceDisconnected=function(e){console.log("UteWatch.onDeviceDisconnected => "+JSON.stringify(e));var t=a.connectedDeviceName;void 0===t&&(t="");var n=a.connectedDeviceId;void 0===n&&(n=""),a.connectedDeviceName=void 0,a.connectedDeviceId=void 0;var r=!1;a.bConnectingDevice&&(a.bConnectingDevice=!1,r=!0),a.events.publish(o.b,{name:t,id:n,type:a.type,manual:r})},a.onHeartRateStateChanged=function(e){console.log("UteWatch.onHeartRateStateChanged => "+JSON.stringify(e))},a.onHeartRateChanged=function(e){console.log("UteWatch.onHeartRateChanged => "+JSON.stringify(e)),a.events.publish(o.c,{heartRate:e.hr})},a.type=s,a.bScanningDevice=!1,a.bConnectingDevice=!1,a.bHeartRateOn=!1,window.addEventListener("uteOnDeviceFound",a.onDeviceFound,!1),window.addEventListener("uteOnConnected",a.onDeviceConnected,!1),window.addEventListener("uteOnDisconnected",a.onDeviceDisconnected,!1),window.addEventListener("uteOnHeartRateStateChanged",a.onHeartRateStateChanged,!1),window.addEventListener("uteOnHeartRateChanged",a.onHeartRateChanged,!1),a}return n}(o.d),v=n("8Y7J"),h=n("xgBC");n.d(t,"a",(function(){return p}));var f,d=function(e){return e[e.Wearfit=0]="Wearfit",e[e.UteWatch=1]="UteWatch",e}({}),p=((f=function e(t,n,i,a){var s=this;_classCallCheck(this,e),this.ble=t,this.events=n,this.platform=i,this.storage=a,this.autoConnectTimer=null,this.getKey=function(e){return"HeartRateDevice-"+e},this.selectDevice=function(e){e==d.Wearfit?s.heartRateDevice=new c.b(s.ble,s.events):e==d.UteWatch&&(s.heartRateDevice=new u(s.ble,s.events))},this.startScan=function(e){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(t.prev=0,void 0===this.heartRateDevice){t.next=5;break}return t.next=4,this.heartRateDevice.startScan(e);case 4:return t.abrupt("return",t.sent);case 5:t.next=10;break;case 7:t.prev=7,t.t0=t.catch(0),console.log(t.t0);case 10:return t.abrupt("return",!1);case 11:case"end":return t.stop()}}),t,this,[[0,7]])})))},this.stopScan=function(){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,void 0===this.heartRateDevice){e.next=5;break}return e.next=4,this.heartRateDevice.stopScan();case 4:return e.abrupt("return",e.sent);case 5:e.next=10;break;case 7:e.prev=7,e.t0=e.catch(0),console.log(e.t0);case 10:return e.abrupt("return",!1);case 11:case"end":return e.stop()}}),e,this,[[0,7]])})))},this.connect=function(e){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(t.prev=0,void 0===this.heartRateDevice){t.next=5;break}return t.next=4,this.heartRateDevice.connect(e);case 4:return t.abrupt("return",t.sent);case 5:t.next=10;break;case 7:t.prev=7,t.t0=t.catch(0),console.log(t.t0);case 10:return t.abrupt("return",!1);case 11:case"end":return t.stop()}}),t,this,[[0,7]])})))},this.autoConnect=function(){void 0!==s.heartRateDevice&&""!=s.heartRateDevice.getLastDeviceId()&&(s.heartRateDevice.autoConnect(),s.autoConnectTimer=setTimeout((function(){s.autoConnect()}),3e4))},this.stopAutoConnect=function(){null!=s.autoConnectTimer&&(clearTimeout(s.autoConnectTimer),s.autoConnectTimer=null)},this.disconnect=function(){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,void 0===this.heartRateDevice){e.next=5;break}return e.next=4,this.heartRateDevice.disconnect();case 4:return e.abrupt("return",e.sent);case 5:e.next=10;break;case 7:e.prev=7,e.t0=e.catch(0),console.log(e.t0);case 10:return e.abrupt("return",!1);case 11:case"end":return e.stop()}}),e,this,[[0,7]])})))},this.startHeartRateStreaming=function(){void 0!==s.heartRateDevice&&s.heartRateDevice.startHeartRateStreaming()},this.stopHeartRateStreaming=function(){void 0!==s.heartRateDevice&&s.heartRateDevice.stopHeartRateStreaming()},this.getLastDeviceId=function(){return void 0!==s.heartRateDevice?s.heartRateDevice.getLastDeviceId():null},this.setLastDeviceId=function(e){void 0!==s.heartRateDevice&&s.heartRateDevice.setLastDeviceId(e)},this.isScanning=function(){return void 0!==s.heartRateDevice&&s.heartRateDevice.isScanning()},this.isConnecting=function(){return void 0!==s.heartRateDevice&&s.heartRateDevice.isConnecting()},this.isConnected=function(){return!!s.platform.is("desktop")||void 0!==s.heartRateDevice&&s.heartRateDevice.isConnected()},this.getConnectedDeviceName=function(){return s.platform.is("desktop")?"M4":void 0!==s.heartRateDevice?s.heartRateDevice.getConnectedDeviceName():null},this.getConnectedDeviceId=function(){return s.platform.is("desktop")?"7258DFA7-356D-0C83-83CD-F11F366CD210":void 0!==s.heartRateDevice?s.heartRateDevice.getConnectedDeviceName():null},this.heartRateDeviceConnected=function(e){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){var n;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,n=e.id,t.next=4,this.storage.set(this.getKey("LastDeviceId"),n);case 4:this.stopAutoConnect(),t.next=10;break;case 7:t.prev=7,t.t0=t.catch(0),console.log(t.t0);case 10:case"end":return t.stop()}}),t,this,[[0,7]])})))},this.heartRateDeviceDisconnected=function(e){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(t.prev=0,!Boolean(e.manual)){t.next=6;break}return t.next=4,this.storage.set(this.getKey("LastDeviceId"),"");case 4:t.next=7;break;case 6:this.autoConnect();case 7:t.next=12;break;case 9:t.prev=9,t.t0=t.catch(0),console.log(t.t0);case 12:case"end":return t.stop()}}),t,this,[[0,9]])})))},this.platform.ready().then((function(){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,this.events.subscribe(o.a,this.heartRateDeviceConnected),this.events.subscribe(o.b,this.heartRateDeviceDisconnected),this.selectDevice(d.UteWatch),e.next=4,this.storage.get(this.getKey("LastDeviceId"));case 4:null!=(t=e.sent)&&(this.setLastDeviceId(t),this.autoConnect()),e.next=11;break;case 8:e.prev=8,e.t0=e.catch(0),console.log(e.t0);case 11:case"end":return e.stop()}}),e,this,[[0,8]])})))}))}).ngInjectableDef=v.ac({factory:function(){return new f(v.bc(a.a),v.bc(i.f),v.bc(i.Mb),v.bc(h.b))},token:f,providedIn:"root"}),f)},yYM5:function(e,t,n){"use strict";n.d(t,"a",(function(){return i})),n.d(t,"b",(function(){return a})),n.d(t,"c",(function(){return c})),n.d(t,"d",(function(){return s}));var r=n("mrSG"),i="evtHRDevConnected",a="evtHRDevDisconnected",c="evtHRDevHeartRateChanged",o="hrTypeUnknown",s=function(){function e(t,n){var c=this;_classCallCheck(this,e),this.startScan=function(e){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",void 0===this.scanSubscription&&(this.scanSubscription=this.ble.startScan([]).subscribe((function(t){"name"in t&&void 0!==e&&e(t)})),!0));case 1:case"end":return t.stop()}}),t,this)})))},this.stopScan=function(){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,void 0===this.scanSubscription){e.next=7;break}return e.next=4,this.ble.stopScan();case 4:return this.scanSubscription.unsubscribe(),this.scanSubscription=void 0,e.abrupt("return",!0);case 7:e.next=12;break;case 9:e.prev=9,e.t0=e.catch(0),console.log("HeartRateDevice.stopScan() ".concat(e.t0));case 12:return e.abrupt("return",!1);case 13:case"end":return e.stop()}}),e,this,[[0,9]])})))},this.onConnected=function(e,t,n){c.getServicesFromPeripheralData(e).map((function(e){return e.toUpperCase()})).includes(c.serviceUUID)?(c.bConnecting=!1,c.activePeripheralData=e,c.setLastDeviceId(c.getIdFromPeripheralData(e)),void 0!==t&&t(e),c.events.publish(i,{id:c.getIdFromPeripheralData(e),name:c.getNameFromPeripheralData(e)})):(console.log("HR connected but service UUID not matched : ".concat(JSON.stringify(e))),c.disconnectInternal(e,n))},this.onDisconnected=function(e,t,n){c.bConnecting=!1,c.activePeripheralData=void 0,t&&c.setLastDeviceId(""),void 0!==c.connectSubscription&&(c.connectSubscription.unsubscribe(),c.connectSubscription=void 0),void 0!==n&&n();var r=c.getNameFromPeripheralData(e),i=c.getIdFromPeripheralData(e);c.events.publish(a,{id:i,name:r,type:c.type,manual:t})},this.connect=function(e){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.abrupt("return",this.connectInternal(e));case 1:case"end":return t.stop()}}),t,this)})))},this.autoConnectInternal=function(e,t){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function n(){var r=this;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,this.disconnect(),""===this.lastDeviceId){n.next=6;break}return n.next=4,this.getConnectedPeripheral(this.lastDeviceId);case 4:return this.ble.autoConnect(this.lastDeviceId,(function(n){r.onConnected(n,e,t)}),(function(e){r.onDisconnected(e,!1,t)})),n.abrupt("return",!0);case 6:n.next=11;break;case 8:n.prev=8,n.t0=n.catch(0),console.log(n.t0);case 11:return n.abrupt("return",!1);case 12:case"end":return n.stop()}}),n,this,[[0,8]])})))},this.disconnectInternal=function(e,t){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function n(){var r;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,void 0===e){n.next=10;break}if(r=this.getIdFromPeripheralData(e),n.t0=null!=r,!n.t0){n.next=9;break}return this.bConnecting=!0,n.next=8,this.ble.disconnect(r);case 8:this.onDisconnected(e,!0,t);case 9:return n.abrupt("return",!0);case 10:return n.abrupt("return",!1);case 13:n.prev=13,n.t1=n.catch(0),console.log("HeartRateDevice.disconnect() ".concat(n.t1));case 16:case"end":return n.stop()}}),n,this,[[0,13]])})))},this.disconnect=function(){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.abrupt("return",this.disconnectInternal(this.activePeripheralData));case 4:throw e.prev=4,e.t0=e.catch(0),e.t0;case 7:case"end":return e.stop()}}),e,this,[[0,4]])})))},this.getConnectedPeripheral=function(e){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function t(){var n;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,this.ble.peripheralsWithIdentifiers([e]);case 3:if(1!=(n=t.sent).length){t.next=6;break}return t.abrupt("return",n[0]);case 6:t.next=11;break;case 8:t.prev=8,t.t0=t.catch(0),console.log("HeartRateDevice.isPeripheralConnected() ".concat(t.t0));case 11:return t.abrupt("return",null);case 12:case"end":return t.stop()}}),t,this,[[0,8]])})))},this.isScanning=function(){return void 0!==c.scanSubscription},this.isConnecting=function(){return c.bConnecting},this.isConnected=function(){return void 0!==c.activePeripheralData},this.getConnectedDeviceName=function(){if(void 0!==c.activePeripheralData){var e=c.getNameFromPeripheralData(c.activePeripheralData);return void 0!==e?e:null}return null},this.getConnectedDeviceId=function(){if(void 0!==c.activePeripheralData){var e=c.getIdFromPeripheralData(c.activePeripheralData);return void 0!==e?e:null}return null},this.getLastDeviceId=function(){return c.lastDeviceId},this.setLastDeviceId=function(e){null==e&&(e=""),c.lastDeviceId=e},this.getServicesFromPeripheralData=function(e){return e.services},this.getIdFromPeripheralData=function(e){return e.id},this.getNameFromPeripheralData=function(e){return e.name},this.getNotifyCharacteristicUUIDFromPeripheralData=function(e){var t,n=_createForOfIteratorHelper(e.characteristics);try{for(n.s();!(t=n.n()).done;){var r=t.value,i=r.service;if(r.properties.includes("Notify")&&void 0!==i&&i.toUpperCase()==c.serviceUUID&&void 0!==r.characteristic)return r.characteristic}}catch(a){n.e(a)}finally{n.f()}return console.log("HeartRateDevice.getNotifyCharacteristicUUIDFromPeripheralData() not able to get notify characteristic ".concat(JSON.stringify(e))),null},this.getWriteCharacteristicUUIDFromPeripheralData=function(e){var t,n=_createForOfIteratorHelper(e.characteristics);try{for(n.s();!(t=n.n()).done;){var r=t.value,i=r.service;if(r.properties.includes("Write")&&void 0!==i&&i.toUpperCase()==c.serviceUUID&&void 0!==r.characteristic)return r.characteristic}}catch(a){n.e(a)}finally{n.f()}return console.log("HeartRateDevice.getWriteCharacteristicUUIDFromPeripheralData() not able to get notify characteristic ".concat(JSON.stringify(e))),null},this.ble=t,this.events=n,this.type=o,this.lastDeviceId="",this.bConnecting=!1}return _createClass(e,[{key:"connectInternal",value:function(e,t,n){var r=this;return void 0===this.connectSubscription&&(this.bConnecting=!0,this.connectSubscription=this.ble.connect(e).subscribe((function(e){r.onConnected(e,t,n)}),(function(e){r.onDisconnected(e,!1,n)})),!0)}},{key:"autoConnect",value:function(){return r.b(this,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,this.autoConnectInternal();case 3:return e.abrupt("return",e.sent);case 6:e.prev=6,e.t0=e.catch(0),console.log(e.t0);case 9:return e.abrupt("return",!1);case 10:case"end":return e.stop()}}),e,this,[[0,6]])})))}},{key:"startHeartRateStreaming",value:function(){}},{key:"stopHeartRateStreaming",value:function(){}}]),e}()}}]);