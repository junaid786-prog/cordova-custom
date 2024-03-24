function _defineProperties(e,t){for(var n=0;n<t.length;n++){var i=t[n];i.enumerable=i.enumerable||!1,i.configurable=!0,"value"in i&&(i.writable=!0),Object.defineProperty(e,i.key,i)}}function _createClass(e,t,n){return t&&_defineProperties(e.prototype,t),n&&_defineProperties(e,n),e}function _classCallCheck(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(window.webpackJsonp=window.webpackJsonp||[]).push([[53],{DaA9:function(e,t,n){"use strict";n.d(t,"c",(function(){return c})),n.d(t,"a",(function(){return l})),n.d(t,"b",(function(){return u})),n.d(t,"e",(function(){return h})),n.d(t,"d",(function(){return p}));var i=n("mrSG"),r=n("ZZ/e"),s=n("Zesz"),a=n("8Y7J"),o=n("xgBC"),c="evtPostureViewEnter",l="evtPostureAlertStateChange",u="evtPostureRemindIntervalChange",h=function(e){return e[e.Normal=0]="Normal",e[e.Slight=1]="Slight",e[e.Serious=2]="Serious",e[e.Severe=3]="Severe",e}({}),p=function(){var e=function e(t,n,r){var s=this;_classCallCheck(this,e),this.storage=t,this.platform=n,this.us=r,this.alertLevelNum=h.Serious,this.rollReference=null,this.pitchReference=null,this.firstCalRoll=null,this.firstCalPitch=null,this.reminderMethod="voice",this.recordLimit=60,this.remindInterval=10,this.firstCalLRBalance=null,this.LRBalanceReference=null,this.firstCalExePitch=null,this.exePitchReference=null,this.setRollReference=function(e){return i.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(e==this.rollReference){t.next=10;break}return this.rollReference=e,t.prev=2,t.next=5,this.storage.set("Calibration-rollValue",e);case 5:t.next=10;break;case 7:t.prev=7,t.t0=t.catch(2),console.log(t.t0);case 10:case"end":return t.stop()}}),t,this,[[2,7]])})))},this.eraseRollReference=function(){return i.b(s,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,this.rollReference=null,e.next=4,this.storage.remove("Calibration-rollValue");case 4:e.next=9;break;case 6:e.prev=6,e.t0=e.catch(0),console.log(e.t0);case 9:case"end":return e.stop()}}),e,this,[[0,6]])})))},this.setPitchReference=function(e){return i.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(e==this.pitchReference){t.next=10;break}return this.pitchReference=e,t.prev=2,t.next=5,this.storage.set("Calibration-pitchValue",e);case 5:t.next=10;break;case 7:t.prev=7,t.t0=t.catch(2),console.log(t.t0);case 10:case"end":return t.stop()}}),t,this,[[2,7]])})))},this.erasePitchReference=function(){return i.b(s,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,this.pitchReference=null,e.next=4,this.storage.remove("Calibration-pitchValue");case 4:e.next=9;break;case 6:e.prev=6,e.t0=e.catch(0),console.log(e.t0);case 9:case"end":return e.stop()}}),e,this,[[0,6]])})))},this.setLRBalanceReference=function(e){return i.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(e==this.LRBalanceReference){t.next=10;break}return this.LRBalanceReference=e,t.prev=2,t.next=5,this.storage.set("Calibration-LRBalance",e);case 5:t.next=10;break;case 7:t.prev=7,t.t0=t.catch(2),console.log(t.t0);case 10:case"end":return t.stop()}}),t,this,[[2,7]])})))},this.setExePitchReference=function(e){return i.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(e==this.exePitchReference){t.next=10;break}return this.exePitchReference=e,t.prev=2,t.next=5,this.storage.set("Calibration-exe-pitchValue",e);case 5:t.next=10;break;case 7:t.prev=7,t.t0=t.catch(2),console.log(t.t0);case 10:case"end":return t.stop()}}),t,this,[[2,7]])})))},this.setAlertLevelNum=function(e){return i.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(e==this.alertLevelNum){t.next=10;break}return this.alertLevelNum=e,t.prev=2,t.next=5,this.storage.set("PostureReminder-alertLevelNum",e);case 5:t.next=10;break;case 7:t.prev=7,t.t0=t.catch(2),console.log(t.t0);case 10:case"end":return t.stop()}}),t,this,[[2,7]])})))},this.setReminderMethod=function(e){return i.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(e==this.reminderMethod){t.next=10;break}return this.reminderMethod=e,t.prev=2,t.next=5,this.storage.set("PostureReminder-reminderMethod",e);case 5:t.next=10;break;case 7:t.prev=7,t.t0=t.catch(2),console.log(t.t0);case 10:case"end":return t.stop()}}),t,this,[[2,7]])})))},this.setRemindInterval=function(e){return i.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(e==this.remindInterval){t.next=10;break}return this.remindInterval=e,t.prev=2,t.next=5,this.storage.set("PostureReminder-reminderInterval",e);case 5:t.next=10;break;case 7:t.prev=7,t.t0=t.catch(2),console.log(t.t0);case 10:case"end":return t.stop()}}),t,this,[[2,7]])})))},this.setFirstCalRoll=function(e){s.firstCalRoll=e},this.setFirstCalPitch=function(e){s.firstCalPitch=e},this.setFirstCalExePitch=function(e){s.firstCalExePitch=e},this.setFirstCalLRBalance=function(e){s.firstCalLRBalance=e},this.setRecordLimit=function(e){s.recordLimit=e},this.getRollReference=function(){return s.rollReference},this.getPitchReference=function(){return s.pitchReference},this.isRollPitchReferencesCalibrated=function(){var e=s.getRollReference(),t=s.getPitchReference();return null!=e&&null!=t},this.getExePitchReference=function(){return s.exePitchReference},this.getLRBalanceReference=function(){return s.LRBalanceReference},this.getAlertLevelNum=function(){return s.alertLevelNum},this.getReminderMethod=function(){return s.reminderMethod},this.getRemindInterval=function(){return s.remindInterval},this.getFirstCalRoll=function(){return s.firstCalRoll},this.getFirstCalPitch=function(){return s.firstCalPitch},this.getFirstCalExePitch=function(){return s.firstCalExePitch},this.getFirstCalLRBalance=function(){return s.firstCalLRBalance},this.getRecordLimit=function(){return s.recordLimit},this.platform.ready().then((function(){return i.b(s,void 0,void 0,regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,this.platform.is("desktop")&&(this.rollReference=0,this.pitchReference=0,this.LRBalanceReference=0),t=null,e.next=5,this.storage.get("Calibration-rollValue");case 5:if(e.t0=t=e.sent,e.t1=null!=e.t0,!e.t1){e.next=9;break}this.rollReference=t;case 9:return e.next=11,this.storage.get("Calibration-pitchValue");case 11:if(e.t2=t=e.sent,e.t3=null!=e.t2,!e.t3){e.next=15;break}this.pitchReference=t;case 15:return e.next=17,this.storage.get("Calibration-exe-pitchValue");case 17:if(e.t4=t=e.sent,e.t5=null!=e.t4,!e.t5){e.next=21;break}this.exePitchReference=t;case 21:return e.next=23,this.storage.get("Calibration-LRBalance");case 23:if(e.t6=t=e.sent,e.t7=null!=e.t6,!e.t7){e.next=27;break}this.LRBalanceReference=t;case 27:return e.next=29,this.storage.get("PostureReminder-alertLevelNum");case 29:if(e.t8=t=e.sent,e.t9=null!=e.t8,!e.t9){e.next=33;break}this.alertLevelNum=t;case 33:return e.next=35,this.storage.get("PostureReminder-reminderMethod");case 35:if(e.t10=t=e.sent,e.t11=null!=e.t10,!e.t11){e.next=39;break}this.reminderMethod=t;case 39:return e.next=41,this.storage.get("PostureReminder-reminderInterval");case 41:if(e.t12=t=e.sent,e.t13=null!=e.t12,!e.t13){e.next=45;break}this.remindInterval=t;case 45:e.next=50;break;case 47:e.prev=47,e.t14=e.catch(0),console.log(JSON.stringify(e.t14));case 50:case"end":return e.stop()}}),e,this,[[0,47]])})))}))};return e.ngInjectableDef=a.ac({factory:function(){return new e(a.bc(o.b),a.bc(r.Mb),a.bc(s.a))},token:e,providedIn:"root"}),e}()},IZRO:function(e,t,n){"use strict";n.r(t);var i,r=n("8Y7J"),s=n("mrSG"),a=n("R3bG"),o=n("DaA9"),c=n("ZZ/e"),l=n("8qa8"),u=n("TSSN"),h="stateReasonDevDisconnected",p=function(e){return e[e.Busy=0]="Busy",e[e.Started=1]="Started",e[e.Stopped=2]="Stopped",e}({}),f=((i=function e(t,n,i,r,o){var c=this;_classCallCheck(this,e),this.events=t,this.solosConnector=n,this.postureSettings=i,this.translate=r,this.audioPlayer=o,this.state=p.Stopped,this.bPause=!1,this.heading=0,this.actionID=-1,this.duration=0,this.successfulTime=0,this.failureTime=0,this.lastTimeStamp=0,this.bSpeakBusy=!1,this.movePositionPrompt="",this.keepPositionPrompt="",this.ttsSpeak=function(e,t){c.bSpeakBusy||(c.bSpeakBusy=!0,c.audioPlayer.speech(!0,e,"",(function(){c.bSpeakBusy=!1,null!=t&&t()})))},this.start=function(){return s.b(c,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,e.t0=this.state!=p.Busy&&this.state!=p.Started,!e.t0){e.next=14;break}return this.setStartStopState(p.Busy),this.lastTimeStamp=0,this.actionID=-1,this.bPause=!1,this.pitchReference=this.postureSettings.getPitchReference(),this.rollReference=this.postureSettings.getRollReference(),this.events.subscribe(a.k,this.evtSolosDisconnected),this.events.subscribe(a.c,this.handleGAMBack),e.next=13,this.solosConnector.startGAM();case 13:this.setStartStopState(p.Started);case 14:e.next=19;break;case 16:e.prev=16,e.t1=e.catch(0),console.log(e.t1);case 19:case"end":return e.stop()}}),e,this,[[0,16]])})))},this.stop=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"stateReasonAPIAction";return s.b(c,void 0,void 0,regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(this.state==p.Busy||this.state==p.Stopped){t.next=13;break}return t.prev=1,this.setStartStopState(p.Busy),t.next=5,this.solosConnector.stopGAM();case 5:this.events.unsubscribe(a.k,this.evtSolosDisconnected),this.events.unsubscribe(a.c,this.handleGAMBack),this.finalStop(e),t.next=13;break;case 10:t.prev=10,t.t0=t.catch(1),console.log(t.t0);case 13:case"end":return t.stop()}}),t,this,[[1,10]])})))},this.finalStop=function(e){c.bSpeakBusy?setTimeout(c.finalStop,1e3,e):c.setStartStopState(p.Stopped,e)},this.pause=function(){c.bPause=!0},this.resume=function(){c.bPause=!1},this.startExercise=function(e,t){return s.b(c,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:n.t0=e,n.next=0===n.t0?3:1===n.t0?5:2===n.t0?7:3===n.t0?9:10;break;case 3:return this.movePositionPrompt=this.translate.instant("stretch.voice-prompt-obliques-left"),n.abrupt("break",10);case 5:return this.movePositionPrompt=this.translate.instant("stretch.voice-prompt-obliques-right"),n.abrupt("break",10);case 7:return this.movePositionPrompt=this.translate.instant("stretch.voice-prompt-twist-left"),n.abrupt("break",10);case 9:this.movePositionPrompt=this.translate.instant("stretch.voice-prompt-twist-right");case 10:this.keepPositionPrompt=this.translate.instant("stretch.voice-prompt-keep-postion",{value:t/10}),this.ttsSpeak(this.movePositionPrompt),this.actionID=e,this.duration=t,this.successfulTime=0,this.failureTime=0,1==e&&(this.heading=0);case 11:case"end":return n.stop()}}),n,this)})))},this.stopExercise=function(){c.actionID=-1},this.isRunning=function(){return c.state!=p.Started},this.setStartStopState=function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"stateReasonAPIAction";c.state=e,c.events.publish("evtStretchStateChanged",{state:e,reason:t})},this.evtSolosDisconnected=function(e){c.stop(h)},this.handleGAMBack=function(e){var t;t=e.timeStamp;var n=0;if(0!=c.lastTimeStamp){n=t-c.lastTimeStamp;var i=-(e.gyroDataX-c.solosConnector.getCalGyroShiftX());Math.abs(i)>30&&(c.heading+=.00875*i*n/1e3)}var r=Math.atan(e.acceDataZ/e.acceDataX);if(r*=180/Math.PI,c.lastTimeStamp=t,c.actionID>=0&&!c.bPause)switch(c.actionID){case 0:c.checkAngle(r-c.rollReference>20);break;case 1:c.checkAngle(r-c.rollReference<-20);break;case 2:c.checkAngle(c.heading<-20);break;case 3:c.checkAngle(c.heading>20)}},this.checkAngle=function(e){if(!c.bSpeakBusy)if(e){if(c.failureTime=0,0==c.successfulTime)c.ttsSpeak(c.keepPositionPrompt);else if(c.successfulTime>=c.duration+10){var t=c.actionID;c.stopExercise(),3==t&&c.ttsSpeak(c.translate.instant("exercise.voice-prompt-ex-completed"),(function(){c.stop()})),c.events.publish("evtStretchCompleted",{actionID:t})}else c.successfulTime%10==0&&c.audioPlayer.playMusic(!1,l.d);c.successfulTime++}else c.successfulTime=0,c.failureTime%50==0&&c.ttsSpeak(c.movePositionPrompt),c.failureTime++}}).ngInjectableDef=r.ac({factory:function(){return new i(r.bc(c.f),r.bc(a.l),r.bc(o.d),r.bc(u.k),r.bc(l.f))},token:i,providedIn:"root"}),i),b=n("rk9M"),d=n("OUMn"),m=function(){function e(t,n,i,r,a,o,c,l,u){var f=this;_classCallCheck(this,e),this.route=t,this.navCtrl=n,this.events=i,this.zone=r,this.stretchExercise=a,this.translate=o,this.platform=c,this.insomnia=l,this.popupCtrl=u,this.imageUrl="",this.introTxt="",this.holdForSec="",this.bDisableSkipBtn=!1,this.actionID=0,this.level="",this.subscribeAction=null,this.skipExerciseOn=!1,this.actionSheetCtl=null,this.exerciseState=p.Stopped,this.evtExerciseStateChanged=function(e){f.zone.run((function(){return s.b(f,void 0,void 0,regeneratorRuntime.mark((function t(){var n=this;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(this.exerciseState=e.state,this.bDisableSkipBtn=this.exerciseState==p.Busy,t.t0=e.state==p.Stopped,!t.t0){t.next=10;break}if(e.reason!=h){t.next=9;break}return t.next=7,this.popupCtrl.presentAlertController({header:this.translate.instant("exercise.stopped"),message:this.translate.instant("exercise.stopped-airgo-not-connected"),buttons:[{text:this.translate.instant("global.okay"),role:"cancel",handler:function(){n.navCtrl.navigateBack("/tabs",{state:{activePage:"ai-care"}})}}]},!0);case 7:t.next=10;break;case 9:this.navCtrl.navigateBack("/tabs",{state:{activePage:"ai-care"}});case 10:case"end":return t.stop()}}),t,this)})))}))},this.evtExerciseCompleted=function(e){f.zone.run((function(){f.actionID<3?f.navCtrl.navigateForward("/stretch/".concat(f.level,"/").concat(f.actionID+1)):f.bDisableSkipBtn=!0}))},this.startExercise=function(){return s.b(f,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,e.t0=0==this.actionID,!e.t0){e.next=5;break}return e.next=5,this.stretchExercise.start();case 5:this.stretchExercise.startExercise(this.actionID,this.duration),e.next=11;break;case 8:e.prev=8,e.t1=e.catch(0),console.log(e.t1);case 11:case"end":return e.stop()}}),e,this,[[0,8]])})))},this.skipExercise=function(){return s.b(f,void 0,void 0,regeneratorRuntime.mark((function e(){var t=this;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(this.skipExerciseOn||this.bDisableSkipBtn){e.next=12;break}return e.prev=1,this.skipExerciseOn=!0,this.stretchExercise.pause(),e.next=6,this.popupCtrl.presentActionSheet({buttons:[{text:this.translate.instant("exercise.end"),role:"destructive",handler:function(){t.actionSheetCtl=null,t.stopExercise()}},{text:this.translate.instant("exercise.continue"),role:"cancel",handler:function(){t.actionSheetCtl=null,t.skipExerciseOn=!1,t.stretchExercise.resume()}}]},!0);case 6:this.actionSheetCtl=e.sent,e.next=12;break;case 9:e.prev=9,e.t0=e.catch(1),this.skipExerciseOn=!1,this.stretchExercise.resume(),console.log(e.t0);case 12:case"end":return e.stop()}}),e,this,[[1,9]])})))},this.stopExercise=function(){return s.b(f,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,this.stretchExercise.stop();case 3:e.next=8;break;case 5:e.prev=5,e.t0=e.catch(0),console.log(e.t0);case 8:case"end":return e.stop()}}),e,this,[[0,5]])})))},this.alwaysScreenOn=function(){return s.b(f,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,this.insomnia.keepAwake();case 2:case"end":return e.stop()}}),e,this)})))},this.allowsScreenOff=function(){return s.b(f,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,this.insomnia.allowSleepAgain();case 2:case"end":return e.stop()}}),e,this)})))}}return _createClass(e,[{key:"ngOnInit",value:function(){switch(this.actionID=Number(this.route.snapshot.paramMap.get("actionID")),this.level=this.route.snapshot.paramMap.get("level"),this.actionID){case 0:this.imageUrl="assets/imgs/oblique-stretch-left.png",this.introTxt="stretch.left-obliques";break;case 1:this.imageUrl="assets/imgs/oblique-stretch-right.png",this.introTxt="stretch.right-obliques";break;case 2:this.imageUrl="assets/imgs/twist-left.png",this.introTxt="stretch.left-twist";break;case 3:this.imageUrl="assets/imgs/twist-right.png",this.introTxt="stretch.right-twist"}switch(this.level){case"Easy":this.duration=50;break;case"Normal":this.duration=100;break;case"Expert":this.duration=200}this.holdForSec=this.translate.instant("stretch.hold-for-second",{value:this.duration/10})}},{key:"ionViewWillEnter",value:function(){var e,t=this;this.bindedFunctions={},this.events.subscribe("evtStretchCompleted",e=this.evtExerciseCompleted),this.bindedFunctions.evtStretchCompleted=e,this.events.subscribe("evtStretchStateChanged",e=this.evtExerciseStateChanged),this.bindedFunctions.evtStretchStateChanged=e,this.subscribeAction=this.platform.backButton.subscribeWithPriority(8964,(function(){t.skipExercise()}))}},{key:"ionViewDidEnter",value:function(){this.startExercise(),this.alwaysScreenOn()}},{key:"ionViewWillLeave",value:function(){for(var e in this.bindedFunctions)this.events.unsubscribe(e,this.bindedFunctions[e]);null!=this.actionSheetCtl&&(this.actionSheetCtl.dismiss(),this.actionSheetCtl=null),this.bindedFunctions=null,this.subscribeAction.unsubscribe(),this.allowsScreenOff()}}]),e}(),v=function e(){_classCallCheck(this,e)},g=n("pMnS"),x=n("oBZk"),k=n("iInd"),R=r.Ab({encapsulation:0,styles:[[".main-content[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-align:center;align-items:center;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;height:100%;width:100%}.img-container[_ngcontent-%COMP%]{width:100%;height:45%;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center}.img-content[_ngcontent-%COMP%]{margin-top:calc(env(safe-area-inset-top) + 20px);margin-left:10px;margin-right:10px;margin-bottom:0;width:100%;height:100%;-o-object-fit:contain;object-fit:contain;border:0 transparent}.display-item[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:space-evenly;justify-content:space-evenly;-webkit-box-align:center;align-items:center;-webkit-box-flex:1;flex-grow:1}.intro-text[_ngcontent-%COMP%]{color:var(--solos-color-a);margin:0 10px;font-size:18px;text-align:center}.duration-text[_ngcontent-%COMP%]{color:var(--solos-color-a);margin:0 10px;font-size:22px;-webkit-box-align:center;align-items:center;display:-webkit-box;display:flex;text-transform:uppercase;text-align:center}.exe-btn[_ngcontent-%COMP%]{border-style:solid;border-width:1px 0 0;border-color:var(--solos-color-a);-webkit-box-flex:0;flex-grow:0;width:100%;padding:12px 10vw 0;margin-bottom:calc(env(safe-area-inset-bottom) + 10px)}"]],data:{}});function S(e){return r.Xb(0,[(e()(),r.Cb(0,0,null,null,15,"ion-content",[["class","app-background"],["scroll-y","false"]],null,null,null,x.O,x.g)),r.Bb(1,49152,null,0,c.w,[r.j,r.p,r.F],null,null),(e()(),r.Cb(2,0,null,0,13,"div",[["class","main-content"]],null,null,null,null,null)),(e()(),r.Cb(3,0,null,null,1,"div",[["class","img-container"]],null,null,null,null,null)),(e()(),r.Cb(4,0,null,null,0,"img",[["class","img-content"]],[[8,"src",4]],null,null,null,null)),(e()(),r.Cb(5,0,null,null,5,"div",[["class","display-item"]],null,null,null,null,null)),(e()(),r.Cb(6,0,null,null,2,"div",[["class","intro-text"]],null,null,null,null,null)),(e()(),r.Vb(7,null,[" "," "])),r.Pb(131072,u.j,[u.k,r.j]),(e()(),r.Cb(9,0,null,null,1,"div",[["class","duration-text"]],null,null,null,null,null)),(e()(),r.Vb(10,null,[" "," "])),(e()(),r.Cb(11,0,null,null,4,"div",[["class","exe-btn"]],null,null,null,null,null)),(e()(),r.Cb(12,0,null,null,3,"ion-button",[["color","solos-color-d"],["expand","block"],["shape","round"]],null,[[null,"click"]],(function(e,t,n){var i=!0;return"click"===t&&(i=!1!==e.component.skipExercise()&&i),i}),x.K,x.c)),r.Bb(13,49152,null,0,c.m,[r.j,r.p,r.F],{color:[0,"color"],disabled:[1,"disabled"],expand:[2,"expand"],shape:[3,"shape"]},null),(e()(),r.Vb(14,0,[" "," "])),r.Pb(131072,u.j,[u.k,r.j])],(function(e,t){e(t,13,0,"solos-color-d",t.component.bDisableSkipBtn,"block","round")}),(function(e,t){var n=t.component;e(t,4,0,n.imageUrl),e(t,7,0,r.Wb(t,7,0,r.Ob(t,8).transform(n.introTxt))),e(t,10,0,n.holdForSec),e(t,14,0,r.Wb(t,14,0,r.Ob(t,15).transform("exercise.skip-exercise")))}))}var w=r.yb("app-stretch",m,(function(e){return r.Xb(0,[(e()(),r.Cb(0,0,null,null,1,"app-stretch",[],null,null,null,S,R)),r.Bb(1,114688,null,0,m,[k.a,c.Jb,c.f,r.F,f,u.k,c.Mb,b.a,d.a],null,null)],(function(e,t){e(t,1,0)}),null)}),{},{},[]),C=n("SVse"),P=n("s7LF");n.d(t,"StretchPageModuleNgFactory",(function(){return y}));var y=r.zb(v,[],(function(e){return r.Lb([r.Mb(512,r.m,r.kb,[[8,[g.a,w]],[3,r.m],r.D]),r.Mb(4608,C.n,C.m,[r.z,[2,C.z]]),r.Mb(4608,P.j,P.j,[]),r.Mb(4608,c.c,c.c,[r.F,r.g]),r.Mb(4608,c.Ib,c.Ib,[c.c,r.m,r.w]),r.Mb(4608,c.Nb,c.Nb,[c.c,r.m,r.w]),r.Mb(4608,u.g,u.f,[]),r.Mb(4608,u.c,u.e,[]),r.Mb(4608,u.i,u.d,[]),r.Mb(4608,u.b,u.a,[]),r.Mb(4608,u.k,u.k,[u.l,u.g,u.c,u.i,u.b,u.m,u.n]),r.Mb(1073742336,C.b,C.b,[]),r.Mb(1073742336,P.i,P.i,[]),r.Mb(1073742336,P.b,P.b,[]),r.Mb(1073742336,c.Fb,c.Fb,[]),r.Mb(1073742336,u.h,u.h,[]),r.Mb(1073742336,k.o,k.o,[[2,k.t],[2,k.m]]),r.Mb(1073742336,v,v,[]),r.Mb(256,u.n,void 0,[]),r.Mb(256,u.m,void 0,[]),r.Mb(1024,k.k,(function(){return[[{path:"",component:m}]]}),[])])}))}}]);