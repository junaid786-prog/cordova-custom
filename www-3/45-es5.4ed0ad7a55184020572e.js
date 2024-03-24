function _defineProperties(t,e){for(var n=0;n<e.length;n++){var l=e[n];l.enumerable=l.enumerable||!1,l.configurable=!0,"value"in l&&(l.writable=!0),Object.defineProperty(t,l.key,l)}}function _createClass(t,e,n){return e&&_defineProperties(t.prototype,e),n&&_defineProperties(t,n),t}function _classCallCheck(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}(window.webpackJsonp=window.webpackJsonp||[]).push([[45],{DaA9:function(t,e,n){"use strict";n.d(e,"c",(function(){return s})),n.d(e,"a",(function(){return c})),n.d(e,"b",(function(){return u})),n.d(e,"e",(function(){return h})),n.d(e,"d",(function(){return b}));var l=n("mrSG"),i=n("ZZ/e"),r=n("Zesz"),a=n("8Y7J"),o=n("xgBC"),s="evtPostureViewEnter",c="evtPostureAlertStateChange",u="evtPostureRemindIntervalChange",h=function(t){return t[t.Normal=0]="Normal",t[t.Slight=1]="Slight",t[t.Serious=2]="Serious",t[t.Severe=3]="Severe",t}({}),b=function(){var t=function t(e,n,i){var r=this;_classCallCheck(this,t),this.storage=e,this.platform=n,this.us=i,this.alertLevelNum=h.Serious,this.rollReference=null,this.pitchReference=null,this.firstCalRoll=null,this.firstCalPitch=null,this.reminderMethod="voice",this.recordLimit=60,this.remindInterval=10,this.firstCalLRBalance=null,this.LRBalanceReference=null,this.firstCalExePitch=null,this.exePitchReference=null,this.setRollReference=function(t){return l.b(r,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(t==this.rollReference){e.next=10;break}return this.rollReference=t,e.prev=2,e.next=5,this.storage.set("Calibration-rollValue",t);case 5:e.next=10;break;case 7:e.prev=7,e.t0=e.catch(2),console.log(e.t0);case 10:case"end":return e.stop()}}),e,this,[[2,7]])})))},this.eraseRollReference=function(){return l.b(r,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,this.rollReference=null,t.next=4,this.storage.remove("Calibration-rollValue");case 4:t.next=9;break;case 6:t.prev=6,t.t0=t.catch(0),console.log(t.t0);case 9:case"end":return t.stop()}}),t,this,[[0,6]])})))},this.setPitchReference=function(t){return l.b(r,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(t==this.pitchReference){e.next=10;break}return this.pitchReference=t,e.prev=2,e.next=5,this.storage.set("Calibration-pitchValue",t);case 5:e.next=10;break;case 7:e.prev=7,e.t0=e.catch(2),console.log(e.t0);case 10:case"end":return e.stop()}}),e,this,[[2,7]])})))},this.erasePitchReference=function(){return l.b(r,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,this.pitchReference=null,t.next=4,this.storage.remove("Calibration-pitchValue");case 4:t.next=9;break;case 6:t.prev=6,t.t0=t.catch(0),console.log(t.t0);case 9:case"end":return t.stop()}}),t,this,[[0,6]])})))},this.setLRBalanceReference=function(t){return l.b(r,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(t==this.LRBalanceReference){e.next=10;break}return this.LRBalanceReference=t,e.prev=2,e.next=5,this.storage.set("Calibration-LRBalance",t);case 5:e.next=10;break;case 7:e.prev=7,e.t0=e.catch(2),console.log(e.t0);case 10:case"end":return e.stop()}}),e,this,[[2,7]])})))},this.setExePitchReference=function(t){return l.b(r,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(t==this.exePitchReference){e.next=10;break}return this.exePitchReference=t,e.prev=2,e.next=5,this.storage.set("Calibration-exe-pitchValue",t);case 5:e.next=10;break;case 7:e.prev=7,e.t0=e.catch(2),console.log(e.t0);case 10:case"end":return e.stop()}}),e,this,[[2,7]])})))},this.setAlertLevelNum=function(t){return l.b(r,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(t==this.alertLevelNum){e.next=10;break}return this.alertLevelNum=t,e.prev=2,e.next=5,this.storage.set("PostureReminder-alertLevelNum",t);case 5:e.next=10;break;case 7:e.prev=7,e.t0=e.catch(2),console.log(e.t0);case 10:case"end":return e.stop()}}),e,this,[[2,7]])})))},this.setReminderMethod=function(t){return l.b(r,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(t==this.reminderMethod){e.next=10;break}return this.reminderMethod=t,e.prev=2,e.next=5,this.storage.set("PostureReminder-reminderMethod",t);case 5:e.next=10;break;case 7:e.prev=7,e.t0=e.catch(2),console.log(e.t0);case 10:case"end":return e.stop()}}),e,this,[[2,7]])})))},this.setRemindInterval=function(t){return l.b(r,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(t==this.remindInterval){e.next=10;break}return this.remindInterval=t,e.prev=2,e.next=5,this.storage.set("PostureReminder-reminderInterval",t);case 5:e.next=10;break;case 7:e.prev=7,e.t0=e.catch(2),console.log(e.t0);case 10:case"end":return e.stop()}}),e,this,[[2,7]])})))},this.setFirstCalRoll=function(t){r.firstCalRoll=t},this.setFirstCalPitch=function(t){r.firstCalPitch=t},this.setFirstCalExePitch=function(t){r.firstCalExePitch=t},this.setFirstCalLRBalance=function(t){r.firstCalLRBalance=t},this.setRecordLimit=function(t){r.recordLimit=t},this.getRollReference=function(){return r.rollReference},this.getPitchReference=function(){return r.pitchReference},this.isRollPitchReferencesCalibrated=function(){var t=r.getRollReference(),e=r.getPitchReference();return null!=t&&null!=e},this.getExePitchReference=function(){return r.exePitchReference},this.getLRBalanceReference=function(){return r.LRBalanceReference},this.getAlertLevelNum=function(){return r.alertLevelNum},this.getReminderMethod=function(){return r.reminderMethod},this.getRemindInterval=function(){return r.remindInterval},this.getFirstCalRoll=function(){return r.firstCalRoll},this.getFirstCalPitch=function(){return r.firstCalPitch},this.getFirstCalExePitch=function(){return r.firstCalExePitch},this.getFirstCalLRBalance=function(){return r.firstCalLRBalance},this.getRecordLimit=function(){return r.recordLimit},this.platform.ready().then((function(){return l.b(r,void 0,void 0,regeneratorRuntime.mark((function t(){var e;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,this.platform.is("desktop")&&(this.rollReference=0,this.pitchReference=0,this.LRBalanceReference=0),e=null,t.next=5,this.storage.get("Calibration-rollValue");case 5:if(t.t0=e=t.sent,t.t1=null!=t.t0,!t.t1){t.next=9;break}this.rollReference=e;case 9:return t.next=11,this.storage.get("Calibration-pitchValue");case 11:if(t.t2=e=t.sent,t.t3=null!=t.t2,!t.t3){t.next=15;break}this.pitchReference=e;case 15:return t.next=17,this.storage.get("Calibration-exe-pitchValue");case 17:if(t.t4=e=t.sent,t.t5=null!=t.t4,!t.t5){t.next=21;break}this.exePitchReference=e;case 21:return t.next=23,this.storage.get("Calibration-LRBalance");case 23:if(t.t6=e=t.sent,t.t7=null!=t.t6,!t.t7){t.next=27;break}this.LRBalanceReference=e;case 27:return t.next=29,this.storage.get("PostureReminder-alertLevelNum");case 29:if(t.t8=e=t.sent,t.t9=null!=t.t8,!t.t9){t.next=33;break}this.alertLevelNum=e;case 33:return t.next=35,this.storage.get("PostureReminder-reminderMethod");case 35:if(t.t10=e=t.sent,t.t11=null!=t.t10,!t.t11){t.next=39;break}this.reminderMethod=e;case 39:return t.next=41,this.storage.get("PostureReminder-reminderInterval");case 41:if(t.t12=e=t.sent,t.t13=null!=t.t12,!t.t13){t.next=45;break}this.remindInterval=e;case 45:t.next=50;break;case 47:t.prev=47,t.t14=t.catch(0),console.log(JSON.stringify(t.t14));case 50:case"end":return t.stop()}}),t,this,[[0,47]])})))}))};return t.ngInjectableDef=a.ac({factory:function(){return new t(a.bc(o.b),a.bc(i.Mb),a.bc(r.a))},token:t,providedIn:"root"}),t}()},z00K:function(t,e,n){"use strict";n.r(e);var l,i=n("8Y7J"),r=n("mrSG"),a=n("ZZ/e"),o=n("8qa8"),s=n("R3bG"),c=n("DaA9"),u=n("rk9M"),h=n("OUMn"),b=n("ZL72"),f=n("uUwQ"),p=n("TSSN"),d=((l=function t(e,n,l,i,a,o){var c=this;_classCallCheck(this,t),this.events=e,this.solosConnector=n,this.postureSettings=l,this.translate=i,this.audioPlayer=a,this.algo=o,this.LRBalanceDataArray=[],this.numOfDataSample=0,this.q1=null,this.q1SettleDown=!1,this.bStartGAM=!1,this.start=function(){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,this.q1=new b.a,this.q1SettleDown=!1,this.LRBalanceDataArray=[],this.numOfDataSample=0,this.events.subscribe(s.c,this.handleGAMBack),t.next=8,this.startGAM();case 8:t.next=13;break;case 10:t.prev=10,t.t0=t.catch(0),console.log(t.t0.message);case 13:case"end":return t.stop()}}),t,this,[[0,10]])})))},this.stop=function(){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,this.events.unsubscribe(s.c,this.handleGAMBack),t.next=4,this.stopGAM();case 4:t.next=9;break;case 6:t.prev=6,t.t0=t.catch(0),console.log(t.t0.message);case 9:case"end":return t.stop()}}),t,this,[[0,6]])})))},this.startGAM=function(){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(t.prev=0,t.t0=this.bStartGAM,t.t0){t.next=6;break}return t.next=5,this.solosConnector.startGAM();case 5:this.bStartGAM=!0;case 6:t.next=11;break;case 8:t.prev=8,t.t1=t.catch(0),console.log(t.t1);case 11:case"end":return t.stop()}}),t,this,[[0,8]])})))},this.stopGAM=function(){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(!this.bStartGAM){t.next=10;break}return t.prev=1,t.next=4,this.solosConnector.stopGAM();case 4:this.bStartGAM=!1,t.next=10;break;case 7:t.prev=7,t.t0=t.catch(1),console.log(t.t0);case 10:case"end":return t.stop()}}),t,this,[[1,7]])})))},this.handleGAMBack=function(t){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function e(){var n,l,i,r,a,o,s,c,u,h,b,f,p,d,m,g,x,v,k,C,w,R,y,M,S,P;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,n=t.gyroDataX,l=t.gyroDataY,i=t.gyroDataZ,r=t.acceDataX,a=t.acceDataY,o=t.acceDataZ,s=t.magnDataX,c=t.magnDataY,u=t.magnDataZ,h=this.solosConnector.getCalibratedGAM(r,a,o,n,l,i,s,c,u),b=h.Agx,f=h.Agy,p=h.Agz,d=h.Ggx,m=h.Ggy,g=h.Ggz,x=h.Bax,v=h.Bay,k=h.Baz,C=[0,0,1],w=[0,1,0],this.q1.update(d,m,g,b,f,p,x,v,k),this.numOfDataSample++,console.log("numOfDataSample : "+this.numOfDataSample),this.q1SettleDown){e.next=8;break}if(this.numOfDataSample>100){e.next=7;break}return e.abrupt("return");case 7:this.q1SettleDown=!0,this.numOfDataSample=0,this.events.publish("evtCalQ1SetteDown");case 8:R=this.q1.toVector(),y=[R.x,R.y,R.z],M=this.algo.RodriguesRotation(w,y,R.angle),S=this.algo.RodriguesRotation(C,y,R.angle),P=this.algo.LRbalance_analyzer_v2(S,M,0),this.LRBalanceDataArray.push(P.angle),e.next=15;break;case 12:e.prev=12,e.t0=e.catch(0),console.log(e.t0);case 15:case"end":return e.stop()}}),e,this,[[0,12]])})))},this.resetSample=function(){return r.b(c,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:this.numOfDataSample=0,this.LRBalanceDataArray=[];case 1:case"end":return t.stop()}}),t,this)})))},this.getQ1Ready=function(){return c.q1SettleDown},this.getLRBalanceData=function(){return c.LRBalanceDataArray}}).ngInjectableDef=i.ac({factory:function(){return new l(i.bc(a.f),i.bc(s.l),i.bc(c.d),i.bc(p.k),i.bc(o.f),i.bc(f.a))},token:l,providedIn:"root"}),l),m=n("Zesz"),g=function(){function t(e,n,l,i,a,c,u,h,b,f,p,d,m){var g=this;_classCallCheck(this,t),this.route=e,this.navCtrl=n,this.zone=l,this.events=i,this.audioPlayback=a,this.solosConnector=c,this.postureSettings=u,this.translate=h,this.platform=b,this.insomnia=f,this.popupCtrl=p,this.settleQ1Serivce=d,this.us=m,this.fromURL="",this.calibrationTitle="",this.calibrationTimeStr="",this.calibrationComplete="",this.calibrationBtn="",this.calibrationImage="",this.finishCal=!1,this.isSpeaking=!1,this.numOfCal=0,this.acceDataXArray=[],this.acceDataYArray=[],this.acceDataZArray=[],this.failureTime=0,this.numOfDataSample=0,this.rollDataArray=[],this.pitchDataArray=[],this.LRBalanceDataArray=[],this.bStartGAM=!1,this.subscribeAction=null,this.q1SettleDown=!1,this.evtSolosDisconnected=function(){return r.b(g,void 0,void 0,regeneratorRuntime.mark((function t(){var e=this;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,this.popupCtrl.presentAlertController({header:this.translate.instant("calibration.calibration-stopped"),message:this.translate.instant("calibration.stop-airgo-not-connected"),buttons:[{text:this.translate.instant("global.okay"),role:"cancel",handler:function(){e.backToPosture()}}]},!0);case 2:case"end":return t.stop()}}),t,this)})))},this.isLandscapeMode=function(){return g.us.isLandscapeMode()},this.handleGAMBack=function(t){g.isSpeaking||g.zone.run((function(){return r.b(g,void 0,void 0,regeneratorRuntime.mark((function e(){var n,l,i,r,a,s,c,u,h,b,f,p,d,m,g,x,v,k,C,w,R,y=this;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,this.numOfDataSample++,this.acceDataXArray.length<10?(this.acceDataXArray.push(-t.acceDataZ),this.acceDataYArray.push(t.acceDataY),this.acceDataZArray.push(-t.acceDataX)):(this.acceDataXArray[(this.numOfDataSample-1)%10]=-t.acceDataZ,this.acceDataYArray[(this.numOfDataSample-1)%10]=t.acceDataY,this.acceDataZArray[(this.numOfDataSample-1)%10]=-t.acceDataX),this.numOfDataSample%10!=0){e.next=39;break}for(n=0,l=0,i=0,r=0;r<this.acceDataXArray.length;r++)n+=this.acceDataXArray[r],l+=this.acceDataYArray[r],i+=this.acceDataZArray[r];if(a=l/this.acceDataYArray.length,s=i/this.acceDataZArray.length,c=Math.atan(n/this.acceDataXArray.length/s),u=Math.atan(a/s),u*=180/Math.PI,c*=180/Math.PI,0==this.rollDataArray.length){e.next=20;break}h=0;case 7:if(!(h<this.rollDataArray.length)){e.next=18;break}if(b=this.pitchDataArray[h]-u,Math.abs(this.rollDataArray[h]-c)<3&&Math.abs(b)<3){e.next=12;break}return this.rollDataArray=[],this.pitchDataArray=[],this.failureTime++,this.failureTime%5==0&&("",f=this.translate.instant("calibration.first-round-start"),this.ttsSpeak(f)),e.abrupt("break",18);case 12:if(h!=this.rollDataArray.length-1){e.next=15;break}return this.rollDataArray.push(c),this.pitchDataArray.push(u),e.abrupt("break",18);case 15:h++,e.next=7;break;case 18:e.next=21;break;case 20:this.rollDataArray.push(c),this.pitchDataArray.push(u);case 21:if(3!=this.rollDataArray.length){e.next=38;break}for(m=0,g=0,x=0;x<this.rollDataArray.length;x++)m+=this.rollDataArray[x],g+=this.pitchDataArray[x];return p=m/this.rollDataArray.length,d=g/this.pitchDataArray.length,0==this.numOfCal?(this.postureSettings.setFirstCalRoll(p),this.postureSettings.setFirstCalPitch(d)):1==this.numOfCal&&(v=this.firstPitchMean-d,Math.abs(this.firstRollMean-p)<3&&Math.abs(v)<3?(k=(this.firstPitchMean+d)/2,this.postureSettings.setRollReference((this.firstRollMean+p)/2),this.postureSettings.setPitchReference(k),this.postureSettings.setFirstCalRoll(null),this.postureSettings.setFirstCalPitch(null)):(this.numOfCal=-1,this.postureSettings.setFirstCalRoll(null),this.postureSettings.setFirstCalPitch(null))),this.rollDataArray=[],this.pitchDataArray=[],this.finishCal=!0,e.next=30,this.stopGAM();case 30:if(0!=this.numOfCal){e.next=35;break}C=this.translate.instant("calibration.first-round-complete"),this.ttsSpeak(C,(function(){y.zone.run((function(){y.goToSecondRound()}))})),e.next=36;break;case 35:-1==this.numOfCal?(w=this.translate.instant("calibration.calibration-not-match"),this.ttsSpeak(w,(function(){y.zone.run((function(){y.goToSecondRound()}))}))):(R=this.translate.instant("calibration.second-round-complete"),this.ttsSpeak(R));case 36:e.next=39;break;case 38:this.isSpeaking||this.audioPlayback.playMusic(!1,o.d);case 39:e.next=44;break;case 41:e.prev=41,e.t0=e.catch(0),console.log(e.t0);case 44:case"end":return e.stop()}}),e,this,[[0,41]])})))}))},this.speakCalibrationStart=function(t){var e;e=g.translate.instant(0==g.numOfCal?"calibration.first-round-start":"calibration.second-round-start"),g.ttsSpeak(e,t)},this.startGAM=function(){return r.b(g,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(t.prev=0,t.t0=this.bStartGAM,t.t0){t.next=7;break}return this.events.subscribe(s.c,this.handleGAMBack),t.next=6,this.solosConnector.startGAM();case 6:this.bStartGAM=!0;case 7:t.next=12;break;case 9:t.prev=9,t.t1=t.catch(0),console.log(t.t1);case 12:case"end":return t.stop()}}),t,this,[[0,9]])})))},this.stopGAM=function(){return r.b(g,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(!this.bStartGAM){t.next=11;break}return t.prev=1,this.events.unsubscribe(s.c,this.handleGAMBack),t.next=5,this.solosConnector.stopGAM();case 5:this.bStartGAM=!1,t.next=11;break;case 8:t.prev=8,t.t0=t.catch(1),console.log(t.t0);case 11:case"end":return t.stop()}}),t,this,[[1,8]])})))},this.skipCalibration=function(){return r.b(g,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:this.isSpeaking||!this.bStartGAM&&!this.finishCal||this.backToPosture();case 1:case"end":return t.stop()}}),t,this)})))},this.backToPosture=function(){g.stopGAM(),g.navCtrl.navigateBack(decodeURIComponent(g.fromURL))},this.alwaysScreenOn=function(){return r.b(g,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,this.insomnia.keepAwake();case 2:case"end":return t.stop()}}),t,this)})))},this.allowsScreenOff=function(){return r.b(g,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,this.insomnia.allowSleepAgain();case 2:case"end":return t.stop()}}),t,this)})))},this.ttsSpeak=function(t,e){g.isSpeaking||(g.zone.run((function(){g.isSpeaking=!0})),g.audioPlayback.speech(!0,t,"",(function(){g.zone.run((function(){g.isSpeaking=!1})),void 0!==e&&e()})))}}return _createClass(t,[{key:"ngOnInit",value:function(){switch(this.numOfCal=Number(this.route.snapshot.paramMap.get("numOfCal")),this.fromURL=this.route.snapshot.paramMap.get("from"),this.calibrationTitle="calibration.sit-straightly",this.calibrationImage="assets/imgs/posture_sit_n_stand.png",1==this.numOfCal&&(this.firstRollMean=this.postureSettings.getFirstCalRoll(),this.firstPitchMean=this.postureSettings.getFirstCalPitch(),this.firstLRMean=this.postureSettings.getFirstCalLRBalance(),null!=this.firstRollMean&&null!=this.firstPitchMean||(this.numOfCal=0)),this.numOfCal){case 0:this.calibrationTimeStr="calibration.first-calibration",this.calibrationComplete="calibration.first-completed",this.calibrationBtn="calibration.second-round";break;case 1:this.calibrationTimeStr="calibration.second-calibration",this.calibrationComplete="calibration.second-completed",this.calibrationBtn="calibration.finish-calibration"}}},{key:"ionViewWillEnter",value:function(){var t,e=this;this.bindedFunctions={},this.events.subscribe(s.k,t=this.evtSolosDisconnected),this.bindedFunctions[s.k]=t,this.q1SettleDown=!0,this.finishCal=!1,this.subscribeAction=this.platform.backButton.subscribeWithPriority(8964,(function(){e.skipCalibration()}))}},{key:"ionViewDidEnter",value:function(){var t=this;this.devInfo=this.solosConnector.getConnectedDevice(),null==this.devInfo?this.evtSolosDisconnected():this.speakCalibrationStart((function(){return r.b(t,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,this.startGAM();case 3:t.next=8;break;case 5:t.prev=5,t.t0=t.catch(0),console.log(t.t0);case 8:case"end":return t.stop()}}),t,this,[[0,5]])})))})),this.alwaysScreenOn()}},{key:"ionViewWillLeave",value:function(){for(var t in this.bindedFunctions)this.events.unsubscribe(t,this.bindedFunctions[t]);this.bindedFunctions=null,this.stopGAM(),this.subscribeAction.unsubscribe(),this.allowsScreenOff()}},{key:"goToSecondRound",value:function(){this.isSpeaking||(1==this.numOfCal?this.backToPosture():this.navCtrl.navigateForward("/calibration/".concat(this.numOfCal+1,"/").concat(encodeURIComponent(this.fromURL))))}}]),t}(),x=function t(){_classCallCheck(this,t)},v=n("pMnS"),k=n("oBZk"),C=n("SVse"),w=n("iInd"),R=i.Ab({encapsulation:0,styles:[[".div1[_ngcontent-%COMP%]{width:100%;height:100%;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-align:center;align-items:center}.main-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-align:center;align-items:center;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;height:100%;width:100%}.container-portrait[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;width:auto;height:100%}.details-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;-webkit-box-flex:1;flex:1}.image-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;width:80%;height:50%;-webkit-box-flex:1;flex:1}.image-content[_ngcontent-%COMP%]{-o-object-fit:contain;object-fit:contain;border:0 transparent;margin-top:calc(env(safe-area-inset-top) + 20px);margin-left:0;margin-right:0;margin-bottom:0;width:100%}.text-content-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;width:100%;height:100%;-webkit-box-flex:1;flex:1}.display-item[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:start;justify-content:flex-start;-webkit-box-align:center;align-items:center;-webkit-box-flex:1;flex-grow:1;height:100%}.sit-properly-text[_ngcontent-%COMP%]{color:var(--solos-color-a);font-size:35px;margin:0 20px;text-align:center}.dont-move-text[_ngcontent-%COMP%]{color:var(--solos-color-a);font-size:20px;margin-top:10px}.complete-time-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-align:center;align-items:center;-webkit-box-pack:center;justify-content:center;height:100%}.complete-time-text[_ngcontent-%COMP%]{color:var(--solos-color-d);font-size:20px}.complete-title-text[_ngcontent-%COMP%]{color:var(--solos-color-a);font-size:25px}.complete-icon-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;height:100%;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center}.complete-icon-content[_ngcontent-%COMP%]{color:var(--solos-color-d);font-size:128px}.cal-btn[_ngcontent-%COMP%]{border-style:solid;border-width:1px 0 0;border-color:var(--solos-color-a);-webkit-box-flex:0;flex-grow:0;width:100%;padding:12px 10vw 0;margin-bottom:calc(env(safe-area-inset-bottom) + 10px)}@media (min-width:1025px) and (max-width:1280px){.current-cell-landscape[_ngcontent-%COMP%]{height:70%;flex-basis:25%;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:reverse;flex-direction:column-reverse;padding:5px}.sit-properly-text[_ngcontent-%COMP%]{color:var(--solos-color-a);font-size:5em;margin:0 20px;text-align:center}.dont-move-text[_ngcontent-%COMP%]{color:var(--solos-color-a);font-size:3em;margin-top:10px}.complete-icon-content[_ngcontent-%COMP%]{color:var(--solos-color-d);font-size:350px}.complete-time-text[_ngcontent-%COMP%]{color:var(--solos-color-d);font-size:3em}.complete-title-text[_ngcontent-%COMP%]{color:var(--solos-color-a);font-size:3em}}"],[".image-container-landscape[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;width:100%;height:100%;-webkit-box-flex:1;flex:1}.image-content-landscape[_ngcontent-%COMP%]{-o-object-fit:contain;object-fit:contain;border:0 transparent;margin-top:calc(env(safe-area-inset-top) + 20px);margin-left:0;margin-right:0;margin-bottom:0;width:80%}.container-landscape[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;height:100%}.text-content-container-landscape[_ngcontent-%COMP%]{margin-top:2em;display:-webkit-box;display:flex;width:100%;height:100%;-webkit-box-flex:1;flex:1}.complete-time-container-landscape[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-align:center;align-items:center;-webkit-box-pack:center;justify-content:center;height:40%}.cal-btn-landscape[_ngcontent-%COMP%]{border-style:solid;border-width:1px 0 0;border-color:var(--solos-color-a);-webkit-box-flex:0;flex-grow:0;width:100%;-webkit-box-flex:1;flex:1;padding:12px 10vw 0;margin-bottom:calc(env(safe-area-inset-bottom) + 10px)}.details-container-landscape[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;-webkit-box-flex:1;flex:1}@media (min-width:1025px) and (max-width:1280px){.details-container-landscape[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;-webkit-box-flex:7;flex:7}.tablet-btn-style[_ngcontent-%COMP%]{height:2em;font-size:2em}}"]],data:{}});function y(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(t()(),i.Cb(1,0,null,null,0,"img",[["class","image-content"]],[[8,"src",4]],null,null,null,null))],null,(function(t,e){t(e,1,0,e.component.calibrationImage)}))}function M(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,16,"div",[["class","div1"]],null,null,null,null,null)),(t()(),i.Cb(1,0,null,null,10,"div",[["class","display-item"]],null,null,null,null,null)),(t()(),i.Cb(2,0,null,null,2,"div",[["class","sit-properly-text"]],null,null,null,null,null)),(t()(),i.Vb(3,null,[" "," "])),i.Pb(131072,p.j,[p.k,i.j]),(t()(),i.Cb(5,0,null,null,2,"div",[["class","dont-move-text"]],null,null,null,null,null)),(t()(),i.Vb(6,null,[" "," "])),i.Pb(131072,p.j,[p.k,i.j]),(t()(),i.Cb(8,0,null,null,3,"div",[["class","complete-time-container"]],null,null,null,null,null)),(t()(),i.Cb(9,0,null,null,2,"div",[["class","complete-time-text"]],null,null,null,null,null)),(t()(),i.Vb(10,null,["",""])),i.Pb(131072,p.j,[p.k,i.j]),(t()(),i.Cb(12,0,null,null,4,"div",[["class","cal-btn"]],null,null,null,null,null)),(t()(),i.Cb(13,0,null,null,3,"ion-button",[["color","solos-color-d"],["expand","block"],["shape","round"]],null,[[null,"click"]],(function(t,e,n){var l=!0;return"click"===e&&(l=!1!==t.component.skipCalibration()&&l),l}),k.K,k.c)),i.Bb(14,49152,null,0,a.m,[i.j,i.p,i.F],{color:[0,"color"],disabled:[1,"disabled"],expand:[2,"expand"],shape:[3,"shape"]},null),(t()(),i.Vb(15,0,[" "," "])),i.Pb(131072,p.j,[p.k,i.j])],(function(t,e){t(e,14,0,"solos-color-d",e.component.isSpeaking,"block","round")}),(function(t,e){var n=e.component;t(e,3,0,i.Wb(e,3,0,i.Ob(e,4).transform(n.calibrationTitle))),t(e,6,0,i.Wb(e,6,0,i.Ob(e,7).transform("calibration.stay-dont-move"))),t(e,10,0,i.Wb(e,10,0,i.Ob(e,11).transform(n.calibrationTimeStr))),t(e,15,0,i.Wb(e,15,0,i.Ob(e,16).transform("calibration.skip-calibration")))}))}function S(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,4,"div",[["class","cal-btn"]],null,null,null,null,null)),(t()(),i.Cb(1,0,null,null,3,"ion-button",[["color","solos-color-a"],["expand","block"],["shape","round"]],null,[[null,"click"]],(function(t,e,n){var l=!0;return"click"===e&&(l=!1!==t.component.goToSecondRound()&&l),l}),k.K,k.c)),i.Bb(2,49152,null,0,a.m,[i.j,i.p,i.F],{color:[0,"color"],disabled:[1,"disabled"],expand:[2,"expand"],shape:[3,"shape"]},null),(t()(),i.Vb(3,0,[" "," "])),i.Pb(131072,p.j,[p.k,i.j])],(function(t,e){t(e,2,0,"solos-color-a",e.component.isSpeaking,"block","round")}),(function(t,e){var n=e.component;t(e,3,0,i.Wb(e,3,0,i.Ob(e,4).transform(n.calibrationBtn)))}))}function P(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,9,"div",[["class","div1"]],null,null,null,null,null)),(t()(),i.Cb(1,0,null,null,6,"div",[["class","display-item"]],null,null,null,null,null)),(t()(),i.Cb(2,0,null,null,2,"div",[["class","complete-title-text"]],null,null,null,null,null)),(t()(),i.Vb(3,null,[" "," "])),i.Pb(131072,p.j,[p.k,i.j]),(t()(),i.Cb(5,0,null,null,2,"div",[["class","complete-icon-container"]],null,null,null,null,null)),(t()(),i.Cb(6,0,null,null,1,"ion-icon",[["class","complete-icon-content"],["name","ios-checkmark-circle-outline"]],null,null,null,k.R,k.j)),i.Bb(7,49152,null,0,a.E,[i.j,i.p,i.F],{name:[0,"name"]},null),(t()(),i.rb(16777216,null,null,1,null,S)),i.Bb(9,16384,null,0,C.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null)],(function(t,e){var n=e.component;t(e,7,0,"ios-checkmark-circle-outline"),t(e,9,0,1==n.numOfCal)}),(function(t,e){var n=e.component;t(e,3,0,i.Wb(e,3,0,i.Ob(e,4).transform(n.calibrationComplete)))}))}function D(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,4,"div",[["class","text-content-container"]],null,null,null,null,null)),(t()(),i.rb(16777216,null,null,1,null,M)),i.Bb(2,16384,null,0,C.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(t()(),i.rb(16777216,null,null,1,null,P)),i.Bb(4,16384,null,0,C.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null)],(function(t,e){var n=e.component;t(e,2,0,!n.finishCal),t(e,4,0,n.finishCal)}),null)}function A(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,2,"div",[["class","sit-properly-text"]],null,null,null,null,null)),(t()(),i.Vb(1,null,[" "," "])),i.Pb(131072,p.j,[p.k,i.j])],null,(function(t,e){var n=e.component;t(e,1,0,i.Wb(e,1,0,i.Ob(e,2).transform(n.calibrationTitle)))}))}function O(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,10,"div",[["class","div1"]],null,null,null,null,null)),(t()(),i.Cb(1,0,null,null,9,"div",[["class","display-item"]],null,null,null,null,null)),(t()(),i.rb(16777216,null,null,1,null,A)),i.Bb(3,16384,null,0,C.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(t()(),i.Cb(4,0,null,null,2,"div",[["class","dont-move-text"]],null,null,null,null,null)),(t()(),i.Vb(5,null,[" "," "])),i.Pb(131072,p.j,[p.k,i.j]),(t()(),i.Cb(7,0,null,null,3,"div",[["class","complete-time-container-landscape"]],null,null,null,null,null)),(t()(),i.Cb(8,0,null,null,2,"div",[["class","complete-time-text"]],null,null,null,null,null)),(t()(),i.Vb(9,null,[" "," "])),i.Pb(131072,p.j,[p.k,i.j])],(function(t,e){t(e,3,0,!e.component.isLandscapeMode())}),(function(t,e){var n=e.component;t(e,5,0,i.Wb(e,5,0,i.Ob(e,6).transform("calibration.stay-dont-move"))),t(e,9,0,i.Wb(e,9,0,i.Ob(e,10).transform(n.calibrationTimeStr)))}))}function B(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,7,"div",[["class","div1"]],null,null,null,null,null)),(t()(),i.Cb(1,0,null,null,6,"div",[["class","display-item"]],null,null,null,null,null)),(t()(),i.Cb(2,0,null,null,2,"div",[["class","complete-title-text"]],null,null,null,null,null)),(t()(),i.Vb(3,null,[" "," "])),i.Pb(131072,p.j,[p.k,i.j]),(t()(),i.Cb(5,0,null,null,2,"div",[["class","complete-icon-container"]],null,null,null,null,null)),(t()(),i.Cb(6,0,null,null,1,"ion-icon",[["class","complete-icon-content"],["name","ios-checkmark-circle-outline"]],null,null,null,k.R,k.j)),i.Bb(7,49152,null,0,a.E,[i.j,i.p,i.F],{name:[0,"name"]},null)],(function(t,e){t(e,7,0,"ios-checkmark-circle-outline")}),(function(t,e){var n=e.component;t(e,3,0,i.Wb(e,3,0,i.Ob(e,4).transform(n.calibrationComplete)))}))}function L(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,4,"div",[["class","cal-btn-landscape"]],null,null,null,null,null)),(t()(),i.Cb(1,0,null,null,3,"ion-button",[["class","tablet-btn-style"],["color","solos-color-d"],["expand","block"],["shape","round"]],null,[[null,"click"]],(function(t,e,n){var l=!0;return"click"===e&&(l=!1!==t.component.skipCalibration()&&l),l}),k.K,k.c)),i.Bb(2,49152,null,0,a.m,[i.j,i.p,i.F],{color:[0,"color"],disabled:[1,"disabled"],expand:[2,"expand"],shape:[3,"shape"]},null),(t()(),i.Vb(3,0,[" "," "])),i.Pb(131072,p.j,[p.k,i.j])],(function(t,e){t(e,2,0,"solos-color-d",e.component.isSpeaking,"block","round")}),(function(t,e){t(e,3,0,i.Wb(e,3,0,i.Ob(e,4).transform("calibration.skip-calibration")))}))}function I(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,4,"div",[["class","cal-btn-landscape"]],null,null,null,null,null)),(t()(),i.Cb(1,0,null,null,3,"ion-button",[["class","tablet-btn-style"],["color","solos-color-a"],["expand","block"],["shape","round"]],null,[[null,"click"]],(function(t,e,n){var l=!0;return"click"===e&&(l=!1!==t.component.goToSecondRound()&&l),l}),k.K,k.c)),i.Bb(2,49152,null,0,a.m,[i.j,i.p,i.F],{color:[0,"color"],disabled:[1,"disabled"],expand:[2,"expand"],shape:[3,"shape"]},null),(t()(),i.Vb(3,0,[" "," "])),i.Pb(131072,p.j,[p.k,i.j])],(function(t,e){t(e,2,0,"solos-color-a",e.component.isSpeaking,"block","round")}),(function(t,e){var n=e.component;t(e,3,0,i.Wb(e,3,0,i.Ob(e,4).transform(n.calibrationBtn)))}))}function j(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,12,"div",[["class","container-landscape"]],null,null,null,null,null)),(t()(),i.Cb(1,0,null,null,7,"div",[["class","details-container-landscape"]],null,null,null,null,null)),(t()(),i.Cb(2,0,null,null,1,"div",[["class","image-container-landscape"]],null,null,null,null,null)),(t()(),i.Cb(3,0,null,null,0,"img",[["class","image-content-landscape"]],[[8,"src",4]],null,null,null,null)),(t()(),i.Cb(4,0,null,null,4,"div",[["class","text-content-container-landscape"]],null,null,null,null,null)),(t()(),i.rb(16777216,null,null,1,null,O)),i.Bb(6,16384,null,0,C.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(t()(),i.rb(16777216,null,null,1,null,B)),i.Bb(8,16384,null,0,C.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(t()(),i.rb(16777216,null,null,1,null,L)),i.Bb(10,16384,null,0,C.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(t()(),i.rb(16777216,null,null,1,null,I)),i.Bb(12,16384,null,0,C.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null)],(function(t,e){var n=e.component;t(e,6,0,!n.finishCal),t(e,8,0,n.finishCal),t(e,10,0,!n.finishCal),t(e,12,0,n.finishCal&&1==n.numOfCal)}),(function(t,e){t(e,3,0,e.component.calibrationImage)}))}function _(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,8,"ion-content",[["scroll-y","false"],["style","--background: var(--solos-color-b);"]],null,null,null,k.O,k.g)),i.Bb(1,49152,null,0,a.w,[i.j,i.p,i.F],null,null),(t()(),i.Cb(2,0,null,0,6,"div",[["class","main-container"]],null,null,null,null,null)),(t()(),i.rb(16777216,null,null,1,null,y)),i.Bb(4,16384,null,0,C.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(t()(),i.rb(16777216,null,null,1,null,D)),i.Bb(6,16384,null,0,C.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(t()(),i.rb(16777216,null,null,1,null,j)),i.Bb(8,16384,null,0,C.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null)],(function(t,e){var n=e.component;t(e,4,0,!n.isLandscapeMode()),t(e,6,0,!n.isLandscapeMode()),t(e,8,0,n.isLandscapeMode())}),null)}var G=i.yb("app-calibration",g,(function(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,1,"app-calibration",[],null,null,null,_,R)),i.Bb(1,114688,null,0,g,[w.a,a.Jb,i.F,a.f,o.f,s.l,c.d,p.k,a.Mb,u.a,h.a,d,m.a],null,null)],(function(t,e){t(e,1,0)}),null)}),{},{},[]),F=n("s7LF");n.d(e,"CalibrationPageModuleNgFactory",(function(){return X}));var X=i.zb(x,[],(function(t){return i.Lb([i.Mb(512,i.m,i.kb,[[8,[v.a,G]],[3,i.m],i.D]),i.Mb(4608,C.n,C.m,[i.z,[2,C.z]]),i.Mb(4608,F.j,F.j,[]),i.Mb(4608,a.c,a.c,[i.F,i.g]),i.Mb(4608,a.Ib,a.Ib,[a.c,i.m,i.w]),i.Mb(4608,a.Nb,a.Nb,[a.c,i.m,i.w]),i.Mb(4608,p.g,p.f,[]),i.Mb(4608,p.c,p.e,[]),i.Mb(4608,p.i,p.d,[]),i.Mb(4608,p.b,p.a,[]),i.Mb(4608,p.k,p.k,[p.l,p.g,p.c,p.i,p.b,p.m,p.n]),i.Mb(1073742336,C.b,C.b,[]),i.Mb(1073742336,F.i,F.i,[]),i.Mb(1073742336,F.b,F.b,[]),i.Mb(1073742336,a.Fb,a.Fb,[]),i.Mb(1073742336,p.h,p.h,[]),i.Mb(1073742336,w.o,w.o,[[2,w.t],[2,w.m]]),i.Mb(1073742336,x,x,[]),i.Mb(256,p.n,void 0,[]),i.Mb(256,p.m,void 0,[]),i.Mb(1024,w.k,(function(){return[[{path:"",component:g}]]}),[])])}))}}]);