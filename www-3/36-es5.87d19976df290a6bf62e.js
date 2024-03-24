function _toConsumableArray(n){return _arrayWithoutHoles(n)||_iterableToArray(n)||_unsupportedIterableToArray(n)||_nonIterableSpread()}function _nonIterableSpread(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}function _unsupportedIterableToArray(n,e){if(n){if("string"==typeof n)return _arrayLikeToArray(n,e);var t=Object.prototype.toString.call(n).slice(8,-1);return"Object"===t&&n.constructor&&(t=n.constructor.name),"Map"===t||"Set"===t?Array.from(n):"Arguments"===t||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(t)?_arrayLikeToArray(n,e):void 0}}function _iterableToArray(n){if("undefined"!=typeof Symbol&&Symbol.iterator in Object(n))return Array.from(n)}function _arrayWithoutHoles(n){if(Array.isArray(n))return _arrayLikeToArray(n)}function _arrayLikeToArray(n,e){(null==e||e>n.length)&&(e=n.length);for(var t=0,l=new Array(e);t<e;t++)l[t]=n[t];return l}function _classCallCheck(n,e){if(!(n instanceof e))throw new TypeError("Cannot call a class as a function")}function _defineProperties(n,e){for(var t=0;t<e.length;t++){var l=e[t];l.enumerable=l.enumerable||!1,l.configurable=!0,"value"in l&&(l.writable=!0),Object.defineProperty(n,l.key,l)}}function _createClass(n,e,t){return e&&_defineProperties(n.prototype,e),t&&_defineProperties(n,t),n}(window.webpackJsonp=window.webpackJsonp||[]).push([[36],{KEUF:function(n,e,t){"use strict";t.r(e);var l=t("8Y7J"),r=t("mrSG"),i=t("ZZ/e"),o=t("3Cwc"),a=t("OUMn"),s=t("VjBM"),u=t("N3Z2"),c=t("4SHv"),b=t("8qa8"),g=t("h5tO"),d=3e3,p=function(){function n(e,t,l,i,a,c,p,h,m){var v=this;_classCallCheck(this,n),this.navCtrl=e,this.trainingSettings=t,this.translate=l,this.popupController=i,this.coachTraining=a,this.strava=c,this.zone=p,this.audioPlayer=h,this.activityStateManager=m,this.isEasy=!0,this.isNormal=!1,this.isExpert=!1,this.isCustom=!1,this.trainingTimeTxt="",this.trainingTime=10,this.targetCadenceTxt="",this.targetCadence=8,this.intervalTxt="",this.interval=0,this.isMovingTimeOn=!1,this.isDistanceOn=!1,this.isAutoPause=!1,this.isBGMOn=!1,this.isUploadStrava=!1,this.isStravaLoggedin=!1,this.beepPlayerTimout=null,this.viewDidEnter=!1,this.trainingTimeOpts=[10,15,20],this.targetCadenceOpts=[165,175,180],this.customTrainingTimeOpts=[1,5,10,15,20,25,30,35,40,45,50,55,60],this.setLevelSelected=function(n){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function e(){var t,l;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:e.prev=0,e.t0=n,e.next=e.t0===o.d.Easy?4:e.t0===o.d.Normal?6:e.t0===o.d.Expert?8:e.t0===o.d.Custom?10:11;break;case 4:return this.isEasy=!0,this.isNormal=!1,this.isExpert=!1,this.isCustom=!1,t=this.trainingTimeOpts[0],l=this.targetCadenceOpts[0],e.abrupt("break",11);case 6:return this.isEasy=!1,this.isNormal=!0,this.isExpert=!1,this.isCustom=!1,t=this.trainingTimeOpts[1],l=this.targetCadenceOpts[1],e.abrupt("break",11);case 8:return this.isEasy=!1,this.isNormal=!1,this.isExpert=!0,this.isCustom=!1,t=this.trainingTimeOpts[2],l=this.targetCadenceOpts[2],e.abrupt("break",11);case 10:this.isEasy=!1,this.isNormal=!1,this.isExpert=!1,this.isCustom=!0,t=this.trainingSettings.getCadenceTrainingTime(),l=this.trainingSettings.getTargetCadence(),0!=t&&0!=l||(t=this.trainingTime,l=this.targetCadence);case 11:return e.next=13,this.trainingSettings.setCadenceLevelSelected(n);case 13:return e.next=15,this.setTrainingTime(t,n==o.d.Custom);case 15:return e.next=17,this.setTargetCadence(l,n==o.d.Custom);case 17:e.next=22;break;case 19:e.prev=19,e.t1=e.catch(0),console.log(e.t1);case 22:case"end":return e.stop()}}),e,this,[[0,19]])})))},this.setTrainingTime=function(n){var e=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return r.b(v,void 0,void 0,regeneratorRuntime.mark((function t(){var l=this;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(t.prev=0,this.zone.run((function(){return r.b(l,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:try{this.trainingTime=n,this.trainingTimeTxt=1!=this.trainingTime?String(this.trainingTime)+" "+this.mins:String(this.trainingTime)+" "+this.min}catch(t){console.log(t)}case 1:case"end":return e.stop()}}),e,this)})))})),t.t0=e,!t.t0){t.next=6;break}return t.next=6,this.trainingSettings.setCadenceTrainingTime(this.trainingTime);case 6:t.next=11;break;case 8:t.prev=8,t.t1=t.catch(0),console.log(t.t1);case 11:case"end":return t.stop()}}),t,this,[[0,8]])})))},this.setTargetCadence=function(n){var e=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return r.b(v,void 0,void 0,regeneratorRuntime.mark((function t(){var l=this;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(t.prev=0,this.zone.run((function(){return r.b(l,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:try{this.targetCadence=n,this.targetCadenceTxt=String(this.targetCadence)+" "+this.spm}catch(t){console.log(t)}case 1:case"end":return e.stop()}}),e,this)})))})),t.t0=e,!t.t0){t.next=6;break}return t.next=6,this.trainingSettings.setTargetCadence(this.targetCadence);case 6:t.next=11;break;case 8:t.prev=8,t.t1=t.catch(0),console.log(t.t1);case 11:case"end":return t.stop()}}),t,this,[[0,8]])})))},this.setInterval=function(n){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function e(){var t=this;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,this.zone.run((function(){return r.b(t,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:try{this.interval=n,this.intervalTxt=1!=this.interval?String(this.interval)+" "+this.mins:String(this.interval)+" "+this.min}catch(t){console.log(t)}case 1:case"end":return e.stop()}}),e,this)})))})),e.next=4,this.trainingSettings.setReminderInterval(n);case 4:e.next=9;break;case 6:e.prev=6,e.t0=e.catch(0),console.log(e.t0);case 9:case"end":return e.stop()}}),e,this,[[0,6]])})))},this.turnEasy=function(){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:try{this.setLevelSelected(o.d.Easy)}catch(e){console.log(e)}case 1:case"end":return n.stop()}}),n,this)})))},this.turnNormal=function(){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:try{this.setLevelSelected(o.d.Normal)}catch(e){console.log(e)}case 1:case"end":return n.stop()}}),n,this)})))},this.turnExpert=function(){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:try{this.setLevelSelected(o.d.Expert)}catch(e){console.log(e)}case 1:case"end":return n.stop()}}),n,this)})))},this.turnCustom=function(){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:try{this.setLevelSelected(o.d.Custom)}catch(e){console.log(e)}case 1:case"end":return n.stop()}}),n,this)})))},this.trainTimeClicked=function(){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){var e,t=this;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:return(e=[]).push({name:"time",options:this.popupController.getColumnOptions(this.customTrainingTimeOpts),selectedIndex:this.popupController.getSelectedIndex(this.customTrainingTimeOpts,String(this.trainingTime))}),e.push({name:"unit",options:this.popupController.getColumnOptions([this.mins]),selectedIndex:0}),n.prev=2,n.next=5,this.popupController.presentNativePickerController(e,(function(n){return r.b(t,void 0,void 0,regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,t=Number(n[0].description),e.next=4,this.setTrainingTime(t);case 4:return e.next=6,this.setLevelSelected(o.d.Custom);case 6:e.next=12;break;case 8:return e.prev=8,e.t0=e.catch(0),e.next=12,this.presentAlert(e.t0);case 12:case"end":return e.stop()}}),e,this,[[0,8]])})))}));case 5:n.next=11;break;case 7:return n.prev=7,n.t0=n.catch(2),n.next=11,this.presentAlert(n.t0);case 11:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.targetCadenceClicked=function(){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){var e,t,l,i,a=this;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:e=Array.from(new Array(3),(function(n,e){return e})),t=Array.from(new Array(10),(function(n,e){return e})),l=Array.from(new Array(10),(function(n,e){return e})),(i=[]).push({name:"hundreds",options:this.popupController.getColumnOptions(e),selectedIndex:this.popupController.getSelectedIndex(e,Math.floor(this.targetCadence/100))}),i.push({name:"tens",options:this.popupController.getColumnOptions(t),selectedIndex:this.popupController.getSelectedIndex(t,Math.floor(this.targetCadence/10)%10)}),i.push({name:"ones",options:this.popupController.getColumnOptions(l),selectedIndex:this.popupController.getSelectedIndex(l,this.targetCadence%10)}),i.push({name:"unit",options:this.popupController.getColumnOptions([this.spm]),selectedIndex:0}),this.popupController.presentNativePickerController(i,(function(n){return r.b(a,void 0,void 0,regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,!((t=100*Number(n[0].description)+10*Number(n[1].description)+Number(n[2].description))<30)){e.next=7;break}return e.next=5,this.presentAlert({message:this.translate.instant("cadence-training.should-equal-greater-30")});case 5:e.next=16;break;case 7:if(!(t>220)){e.next=12;break}return e.next=10,this.presentAlert({message:this.translate.instant("cadence-training.should-equal-less-220")});case 10:e.next=16;break;case 12:return e.next=14,this.setTargetCadence(t);case 14:return e.next=16,this.setLevelSelected(o.d.Custom);case 16:e.next=22;break;case 18:return e.prev=18,e.t0=e.catch(0),e.next=22,this.presentAlert(e.t0);case 22:case"end":return e.stop()}}),e,this,[[0,18]])})))}));case 3:case"end":return n.stop()}}),n,this)})))},this.clearPlayBeepSoundTimeout=function(){null!=v.beepPlayerTimout&&(clearTimeout(v.beepPlayerTimout),v.beepPlayerTimout=null)},this.playBeepSound=function(){v.clearPlayBeepSoundTimeout(),v.audioPlayer.setVolume(b.e,v.beepVolume),v.audioPlayer.playMusic(!1,b.e,"",v.targetCadence),v.beepPlayerTimout=setTimeout((function(){v.stopBeepSound()}),d)},this.stopBeepSound=function(){null!=v.beepPlayerTimout&&(v.clearPlayBeepSoundTimeout(),v.audioPlayer.stopMusic(b.e))},this.beepVolumeDidChange=function(n){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,this.viewDidEnter){n.next=3;break}return n.abrupt("return");case 3:return n.next=5,this.trainingSettings.setBeepVolume(this.beepVolume);case 5:this.playBeepSound(),n.next=11;break;case 8:n.prev=8,n.t0=n.catch(0),console.log(n.t0);case 11:case"end":return n.stop()}}),n,this,[[0,8]])})))},this.intervalClicked=function(){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){var e,t,l,i=this;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:e=Array.from(new Array(61),(function(n,e){return e})),(t=[]).push({name:"tens",options:this.popupController.getColumnOptions(e),selectedIndex:this.popupController.getSelectedIndex(e,String(Math.floor(this.interval)))}),l=[this.translate.instant("training.mins")],t.push({name:"unit",options:this.popupController.getColumnOptions(l),selectedIndex:0}),this.popupController.presentNativePickerController(t,(function(n){return r.b(i,void 0,void 0,regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,!((t=Number(n[0].description))>60)){e.next=6;break}this.presentAlert({message:this.translate.instant("training.should-below-60")}),e.next=8;break;case 6:return e.next=8,this.setInterval(t);case 8:e.next=14;break;case 10:return e.prev=10,e.t0=e.catch(0),e.next=14,this.presentAlert(e.t0);case 14:case"end":return e.stop()}}),e,this,[[0,10]])})))}));case 5:case"end":return n.stop()}}),n,this)})))},this.movingTimeDidChange=function(n){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,this.viewDidEnter){n.next=3;break}return n.abrupt("return");case 3:return n.next=5,this.trainingSettings.setReminderTypeMovingTimeOn(this.isMovingTimeOn);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(0),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[0,7]])})))},this.distanceDidChange=function(n){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,this.viewDidEnter){n.next=3;break}return n.abrupt("return");case 3:return n.next=5,this.trainingSettings.setReminderTypeDistanceOn(this.isDistanceOn);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(0),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[0,7]])})))},this.autoPauseDidChange=function(n){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,this.viewDidEnter){n.next=3;break}return n.abrupt("return");case 3:return n.next=5,this.trainingSettings.setAutoPauseOn(this.isAutoPause);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(0),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[0,7]])})))},this.backgroundMusicDidChange=function(n){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,this.viewDidEnter){n.next=3;break}return n.abrupt("return");case 3:return n.next=5,this.trainingSettings.setBackdroundMusic(this.isBGMOn);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(0),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[0,7]])})))},this.uploadStravaDidChange=function(n){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,this.viewDidEnter){n.next=3;break}return n.abrupt("return");case 3:return n.next=5,this.trainingSettings.setUploadStrava(this.isUploadStrava);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(0),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[0,7]])})))},this.startTraining=function(){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function n(){var e,t,l;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,e=[].concat(_toConsumableArray(g.c),[g.a.BASIC_TRAINING,g.a.CORE_TRAINING,g.a.POSTURE_MONITORING]),!(t=this.activityStateManager.validate(e,g.a.CADENCE_TRAINING,"/cadence-training")).status){n.next=11;break}if(this.stopBeepSound(),l=new u.b(60*this.trainingTime,this.targetCadence),n.t0=this.coachTraining.start(s.b.Cadence,l),!n.t0){n.next=9;break}return n.next=9,this.navCtrl.navigateForward("/training-running/cadence");case 9:n.next=13;break;case 11:return n.next=13,this.popupController.presentAlertController({header:t.header,message:t.message,buttons:t.buttons},!0);case 13:n.next=18;break;case 15:n.prev=15,n.t1=n.catch(0),console.log(n.t1);case 18:case"end":return n.stop()}}),n,this,[[0,15]])})))},this.presentAlert=function(n){return r.b(v,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,this.popupController.presentAlertController({header:this.translate.instant("global.error"),message:n.message,buttons:[{text:this.translate.instant("global.okay")}]},!0);case 3:e.next=8;break;case 5:e.prev=5,e.t0=e.catch(0),console.log(e.t0);case 8:case"end":return e.stop()}}),e,this,[[0,5]])})))}}return _createClass(n,[{key:"ngOnInit",value:function(){}},{key:"ionViewWillEnter",value:function(){this.min=this.translate.instant("training.min"),this.mins=this.translate.instant("training.mins"),this.spm=this.translate.instant("training.spm");var n=this.trainingSettings.getReminderInterval();this.setInterval(n),this.isMovingTimeOn=this.trainingSettings.getReminderTypeMovingTimeOn(),this.isDistanceOn=this.trainingSettings.getReminderTypeDistanceOn(),this.isAutoPause=this.trainingSettings.getAutoPauseOn(),this.isBGMOn=this.trainingSettings.getBackdroundMusic(),this.isStravaLoggedin=this.strava.isAuthorized(),this.isUploadStrava=this.trainingSettings.getUploadStrava();var e=this.trainingSettings.getCadenceLevelSelected();this.setLevelSelected(e),this.beepVolume=this.trainingSettings.getBeepVolume()}},{key:"ionViewDidEnter",value:function(){this.viewDidEnter=!0}},{key:"ionViewWillLeave",value:function(){this.stopBeepSound()}}]),n}(),h=function n(){_classCallCheck(this,n)},m=t("pMnS"),v=t("oBZk"),C=t("TSSN"),f=t("SVse"),k=t("s7LF"),x=l.Ab({encapsulation:0,styles:[['.main-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column}.intro-text-container[_ngcontent-%COMP%]{text-align:center;padding:1rem .5rem 1.5rem;position:relative;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;margin-bottom:1rem}.intro-text-container[_ngcontent-%COMP%]::before{content:"";background-image:url(cadence-training-banner.c6d374c7f1235d3aefd0.png);background-size:cover;position:absolute;top:0;right:0;bottom:0;left:0;opacity:.5}.intro-text[_ngcontent-%COMP%]{color:var(--solos-color-a);position:relative}.choose-level-title[_ngcontent-%COMP%]{font-size:14px;margin:0 10px;text-align:center;color:var(--solos-color-a);text-transform:uppercase}.level-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:justify;justify-content:space-between;margin:20px}.level-selection[_ngcontent-%COMP%]{flex-basis:23%;font-size:12px;text-align:center;padding:12px 0;color:var(--solos-color-a)}.active[_ngcontent-%COMP%]{background:var(--solos-color-d);border-color:var(--solos-color-b)}.inactive[_ngcontent-%COMP%]{background:0 0;border-color:var(--solos-color-a);border-style:solid;border-width:1px}.start-button[_ngcontent-%COMP%]{width:80%}.footer-buttons[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-align:center;align-items:center;-webkit-box-pack:center;justify-content:center;padding:10px;background:var(--solos-color-b);border-width:1px 0 0;border-style:solid;border-color:var(--solos-color-a)}.footer-padding[_ngcontent-%COMP%]{height:calc(env(safe-area-inset-bottom));background:var(--solos-color-b)}.volume[_ngcontent-%COMP%]{--bar-background-active:var(--solos-color-d);--bar-background:var(--solos-color-c)}']],data:{}});function O(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,12,"ion-header",[["style","background: var(--solos-color-b);"]],null,null,null,v.Q,v.i)),l.Bb(1,49152,null,0,i.D,[l.j,l.p,l.F],null,null),(n()(),l.Cb(2,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,v.ob,v.G)),l.Bb(3,49152,null,0,i.Db,[l.j,l.p,l.F],{mode:[0,"mode"]},null),(n()(),l.Cb(4,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,v.L,v.d)),l.Bb(5,49152,null,0,i.n,[l.j,l.p,l.F],null,null),(n()(),l.Cb(6,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(n,e,t){var r=!0;return"click"===e&&(r=!1!==l.Ob(n,8).onClick(t)&&r),r}),v.J,v.b)),l.Bb(7,49152,null,0,i.i,[l.j,l.p,l.F],{text:[0,"text"]},null),l.Bb(8,16384,null,0,i.j,[[2,i.jb],i.Jb],null,null),(n()(),l.Cb(9,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,v.mb,v.E)),l.Bb(10,49152,null,0,i.Bb,[l.j,l.p,l.F],null,null),(n()(),l.Vb(11,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(13,0,null,null,146,"ion-content",[["class","app-background"]],null,null,null,v.O,v.g)),l.Bb(14,49152,null,0,i.w,[l.j,l.p,l.F],null,null),(n()(),l.Cb(15,0,null,0,144,"div",[["class","main-container"]],null,null,null,null,null)),(n()(),l.Cb(16,0,null,null,3,"div",[["class","intro-text-container"]],null,null,null,null,null)),(n()(),l.Cb(17,0,null,null,2,"div",[["class","intro-text"]],null,null,null,null,null)),(n()(),l.Vb(18,null,[" ",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(20,0,null,null,2,"div",[["class","choose-level-title"]],null,null,null,null,null)),(n()(),l.Vb(21,null,[" "," "])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(23,0,null,null,20,"div",[["class","level-container"]],null,null,null,null,null)),(n()(),l.Cb(24,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(n,e,t){var l=!0;return"click"===e&&(l=!1!==n.component.turnEasy()&&l),l}),null,null)),l.Sb(512,null,f.u,f.v,[l.x,l.y,l.p,l.L]),l.Bb(26,278528,null,0,f.j,[f.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(n()(),l.Vb(27,null,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(29,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(n,e,t){var l=!0;return"click"===e&&(l=!1!==n.component.turnNormal()&&l),l}),null,null)),l.Sb(512,null,f.u,f.v,[l.x,l.y,l.p,l.L]),l.Bb(31,278528,null,0,f.j,[f.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(n()(),l.Vb(32,null,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(34,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(n,e,t){var l=!0;return"click"===e&&(l=!1!==n.component.turnExpert()&&l),l}),null,null)),l.Sb(512,null,f.u,f.v,[l.x,l.y,l.p,l.L]),l.Bb(36,278528,null,0,f.j,[f.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(n()(),l.Vb(37,null,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(39,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(n,e,t){var l=!0;return"click"===e&&(l=!1!==n.component.turnCustom()&&l),l}),null,null)),l.Sb(512,null,f.u,f.v,[l.x,l.y,l.p,l.L]),l.Bb(41,278528,null,0,f.j,[f.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(n()(),l.Vb(42,null,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(44,0,null,null,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,v.Y,v.r)),l.Bb(45,49152,null,0,i.R,[l.j,l.p,l.F],null,null),(n()(),l.Cb(46,0,null,0,3,"ion-label",[],null,null,null,v.X,v.p)),l.Bb(47,49152,null,0,i.P,[l.j,l.p,l.F],null,null),(n()(),l.Vb(48,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(50,0,null,null,8,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(n,e,t){var l=!0;return"click"===e&&(l=!1!==n.component.trainTimeClicked()&&l),l}),v.W,v.o)),l.Bb(51,49152,null,0,i.J,[l.j,l.p,l.F],{button:[0,"button"],detail:[1,"detail"]},null),(n()(),l.Cb(52,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,v.X,v.p)),l.Bb(53,49152,null,0,i.P,[l.j,l.p,l.F],{color:[0,"color"]},null),(n()(),l.Vb(54,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(56,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,v.X,v.p)),l.Bb(57,49152,null,0,i.P,[l.j,l.p,l.F],{color:[0,"color"]},null),(n()(),l.Vb(58,0,["",""])),(n()(),l.Cb(59,0,null,null,8,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(n,e,t){var l=!0;return"click"===e&&(l=!1!==n.component.targetCadenceClicked()&&l),l}),v.W,v.o)),l.Bb(60,49152,null,0,i.J,[l.j,l.p,l.F],{button:[0,"button"],detail:[1,"detail"]},null),(n()(),l.Cb(61,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,v.X,v.p)),l.Bb(62,49152,null,0,i.P,[l.j,l.p,l.F],{color:[0,"color"]},null),(n()(),l.Vb(63,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(65,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,v.X,v.p)),l.Bb(66,49152,null,0,i.P,[l.j,l.p,l.F],{color:[0,"color"]},null),(n()(),l.Vb(67,0,["",""])),(n()(),l.Cb(68,0,null,null,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,v.Y,v.r)),l.Bb(69,49152,null,0,i.R,[l.j,l.p,l.F],null,null),(n()(),l.Cb(70,0,null,0,3,"ion-label",[],null,null,null,v.X,v.p)),l.Bb(71,49152,null,0,i.P,[l.j,l.p,l.F],null,null),(n()(),l.Vb(72,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(74,0,null,null,12,"ion-item",[["class","settings-item"]],null,null,null,v.W,v.o)),l.Bb(75,49152,null,0,i.J,[l.j,l.p,l.F],null,null),(n()(),l.Cb(76,0,null,0,10,"ion-range",[["class","volume"],["max","10"],["min","0"],["mode","ios"],["step","1"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(n,e,t){var r=!0,i=n.component;return"ionBlur"===e&&(r=!1!==l.Ob(n,77)._handleBlurEvent(t.target)&&r),"ionChange"===e&&(r=!1!==l.Ob(n,77)._handleChangeEvent(t.target)&&r),"ionChange"===e&&(r=!1!==i.beepVolumeDidChange(t)&&r),"ngModelChange"===e&&(r=!1!==(i.beepVolume=t)&&r),r}),v.eb,v.w)),l.Bb(77,16384,null,0,i.Pb,[l.p],null,null),l.Sb(1024,null,k.c,(function(n){return[n]}),[i.Pb]),l.Bb(79,671744,null,0,k.h,[[8,null],[8,null],[8,null],[6,k.c]],{model:[0,"model"]},{update:"ngModelChange"}),l.Sb(2048,null,k.d,null,[k.h]),l.Bb(81,16384,null,0,k.e,[[4,k.d]],null,null),l.Bb(82,49152,null,0,i.db,[l.j,l.p,l.F],{max:[0,"max"],min:[1,"min"],mode:[2,"mode"],step:[3,"step"]},null),(n()(),l.Cb(83,0,null,0,1,"ion-icon",[["color","solos-color-a"],["name","volume-low"],["slot","start"]],null,null,null,v.R,v.j)),l.Bb(84,49152,null,0,i.E,[l.j,l.p,l.F],{color:[0,"color"],name:[1,"name"]},null),(n()(),l.Cb(85,0,null,0,1,"ion-icon",[["color","solos-color-a"],["name","volume-high"],["slot","end"]],null,null,null,v.R,v.j)),l.Bb(86,49152,null,0,i.E,[l.j,l.p,l.F],{color:[0,"color"],name:[1,"name"]},null),(n()(),l.Cb(87,0,null,null,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,v.Y,v.r)),l.Bb(88,49152,null,0,i.R,[l.j,l.p,l.F],null,null),(n()(),l.Cb(89,0,null,0,3,"ion-label",[],null,null,null,v.X,v.p)),l.Bb(90,49152,null,0,i.P,[l.j,l.p,l.F],null,null),(n()(),l.Vb(91,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(93,0,null,null,8,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(n,e,t){var l=!0;return"click"===e&&(l=!1!==n.component.intervalClicked()&&l),l}),v.W,v.o)),l.Bb(94,49152,null,0,i.J,[l.j,l.p,l.F],{button:[0,"button"],detail:[1,"detail"]},null),(n()(),l.Cb(95,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,v.X,v.p)),l.Bb(96,49152,null,0,i.P,[l.j,l.p,l.F],{color:[0,"color"]},null),(n()(),l.Vb(97,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(99,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,v.X,v.p)),l.Bb(100,49152,null,0,i.P,[l.j,l.p,l.F],{color:[0,"color"]},null),(n()(),l.Vb(101,0,["",""])),(n()(),l.Cb(102,0,null,null,12,"ion-item",[["class","settings-item"]],null,null,null,v.W,v.o)),l.Bb(103,49152,null,0,i.J,[l.j,l.p,l.F],null,null),(n()(),l.Cb(104,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,v.X,v.p)),l.Bb(105,49152,null,0,i.P,[l.j,l.p,l.F],{color:[0,"color"]},null),(n()(),l.Vb(106,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(108,0,null,0,6,"ion-toggle",[["class","item-toggle"],["color","solos-color-d"],["mode","md"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(n,e,t){var r=!0,i=n.component;return"ionBlur"===e&&(r=!1!==l.Ob(n,109)._handleBlurEvent(t.target)&&r),"ionChange"===e&&(r=!1!==l.Ob(n,109)._handleIonChange(t.target)&&r),"ionChange"===e&&(r=!1!==i.movingTimeDidChange(t)&&r),"ngModelChange"===e&&(r=!1!==(i.isMovingTimeOn=t)&&r),r}),v.nb,v.F)),l.Bb(109,16384,null,0,i.d,[l.p],null,null),l.Sb(1024,null,k.c,(function(n){return[n]}),[i.d]),l.Bb(111,671744,null,0,k.h,[[8,null],[8,null],[8,null],[6,k.c]],{model:[0,"model"]},{update:"ngModelChange"}),l.Sb(2048,null,k.d,null,[k.h]),l.Bb(113,16384,null,0,k.e,[[4,k.d]],null,null),l.Bb(114,49152,null,0,i.Cb,[l.j,l.p,l.F],{color:[0,"color"],mode:[1,"mode"]},null),(n()(),l.Cb(115,0,null,null,12,"ion-item",[["class","settings-item"]],null,null,null,v.W,v.o)),l.Bb(116,49152,null,0,i.J,[l.j,l.p,l.F],null,null),(n()(),l.Cb(117,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,v.X,v.p)),l.Bb(118,49152,null,0,i.P,[l.j,l.p,l.F],{color:[0,"color"]},null),(n()(),l.Vb(119,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(121,0,null,0,6,"ion-toggle",[["class","item-toggle"],["color","solos-color-d"],["mode","md"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(n,e,t){var r=!0,i=n.component;return"ionBlur"===e&&(r=!1!==l.Ob(n,122)._handleBlurEvent(t.target)&&r),"ionChange"===e&&(r=!1!==l.Ob(n,122)._handleIonChange(t.target)&&r),"ionChange"===e&&(r=!1!==i.distanceDidChange(t)&&r),"ngModelChange"===e&&(r=!1!==(i.isDistanceOn=t)&&r),r}),v.nb,v.F)),l.Bb(122,16384,null,0,i.d,[l.p],null,null),l.Sb(1024,null,k.c,(function(n){return[n]}),[i.d]),l.Bb(124,671744,null,0,k.h,[[8,null],[8,null],[8,null],[6,k.c]],{model:[0,"model"]},{update:"ngModelChange"}),l.Sb(2048,null,k.d,null,[k.h]),l.Bb(126,16384,null,0,k.e,[[4,k.d]],null,null),l.Bb(127,49152,null,0,i.Cb,[l.j,l.p,l.F],{color:[0,"color"],mode:[1,"mode"]},null),(n()(),l.Cb(128,0,null,null,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,v.Y,v.r)),l.Bb(129,49152,null,0,i.R,[l.j,l.p,l.F],null,null),(n()(),l.Cb(130,0,null,0,3,"ion-label",[],null,null,null,v.X,v.p)),l.Bb(131,49152,null,0,i.P,[l.j,l.p,l.F],null,null),(n()(),l.Vb(132,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(134,0,null,null,12,"ion-item",[["class","settings-item"]],null,null,null,v.W,v.o)),l.Bb(135,49152,null,0,i.J,[l.j,l.p,l.F],null,null),(n()(),l.Cb(136,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,v.X,v.p)),l.Bb(137,49152,null,0,i.P,[l.j,l.p,l.F],{color:[0,"color"]},null),(n()(),l.Vb(138,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(140,0,null,0,6,"ion-toggle",[["class","item-toggle"],["color","solos-color-d"],["mode","md"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(n,e,t){var r=!0,i=n.component;return"ionBlur"===e&&(r=!1!==l.Ob(n,141)._handleBlurEvent(t.target)&&r),"ionChange"===e&&(r=!1!==l.Ob(n,141)._handleIonChange(t.target)&&r),"ionChange"===e&&(r=!1!==i.autoPauseDidChange(t)&&r),"ngModelChange"===e&&(r=!1!==(i.isAutoPause=t)&&r),r}),v.nb,v.F)),l.Bb(141,16384,null,0,i.d,[l.p],null,null),l.Sb(1024,null,k.c,(function(n){return[n]}),[i.d]),l.Bb(143,671744,null,0,k.h,[[8,null],[8,null],[8,null],[6,k.c]],{model:[0,"model"]},{update:"ngModelChange"}),l.Sb(2048,null,k.d,null,[k.h]),l.Bb(145,16384,null,0,k.e,[[4,k.d]],null,null),l.Bb(146,49152,null,0,i.Cb,[l.j,l.p,l.F],{color:[0,"color"],mode:[1,"mode"]},null),(n()(),l.Cb(147,0,null,null,12,"ion-item",[["class","settings-item"]],null,null,null,v.W,v.o)),l.Bb(148,49152,null,0,i.J,[l.j,l.p,l.F],null,null),(n()(),l.Cb(149,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,v.X,v.p)),l.Bb(150,49152,null,0,i.P,[l.j,l.p,l.F],{color:[0,"color"]},null),(n()(),l.Vb(151,0,["",""])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(153,0,null,0,6,"ion-toggle",[["class","item-toggle"],["color","solos-color-d"],["mode","md"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(n,e,t){var r=!0,i=n.component;return"ionBlur"===e&&(r=!1!==l.Ob(n,154)._handleBlurEvent(t.target)&&r),"ionChange"===e&&(r=!1!==l.Ob(n,154)._handleIonChange(t.target)&&r),"ionChange"===e&&(r=!1!==i.uploadStravaDidChange(t)&&r),"ngModelChange"===e&&(r=!1!==(i.isUploadStrava=t)&&r),r}),v.nb,v.F)),l.Bb(154,16384,null,0,i.d,[l.p],null,null),l.Sb(1024,null,k.c,(function(n){return[n]}),[i.d]),l.Bb(156,671744,null,0,k.h,[[8,null],[8,null],[8,null],[6,k.c]],{isDisabled:[0,"isDisabled"],model:[1,"model"]},{update:"ngModelChange"}),l.Sb(2048,null,k.d,null,[k.h]),l.Bb(158,16384,null,0,k.e,[[4,k.d]],null,null),l.Bb(159,49152,null,0,i.Cb,[l.j,l.p,l.F],{color:[0,"color"],disabled:[1,"disabled"],mode:[2,"mode"]},null),(n()(),l.Cb(160,0,null,null,7,"ion-footer",[],null,null,null,v.P,v.h)),l.Bb(161,49152,null,0,i.B,[l.j,l.p,l.F],null,null),(n()(),l.Cb(162,0,null,0,4,"div",[["class","footer-buttons"]],null,null,null,null,null)),(n()(),l.Cb(163,0,null,null,3,"ion-button",[["class","start-button"],["color","solos-color-a"],["expand","block"],["shape","round"]],null,[[null,"click"]],(function(n,e,t){var l=!0;return"click"===e&&(l=!1!==n.component.startTraining()&&l),l}),v.K,v.c)),l.Bb(164,49152,null,0,i.m,[l.j,l.p,l.F],{color:[0,"color"],expand:[1,"expand"],shape:[2,"shape"]},null),(n()(),l.Vb(165,0,[" "," "])),l.Pb(131072,C.j,[C.k,l.j]),(n()(),l.Cb(167,0,null,0,0,"div",[["class","footer-padding"]],null,null,null,null,null))],(function(n,e){var t=e.component;n(e,3,0,"ios"),n(e,7,0,""),n(e,26,0,"level-selection",t.isEasy?"active":"inactive"),n(e,31,0,"level-selection",t.isNormal?"active":"inactive"),n(e,36,0,"level-selection",t.isExpert?"active":"inactive"),n(e,41,0,"level-selection",t.isCustom?"active":"inactive"),n(e,51,0,"",""),n(e,53,0,"settings-left-label"),n(e,57,0,"settings-right-label"),n(e,60,0,"",""),n(e,62,0,"settings-left-label"),n(e,66,0,"settings-right-label"),n(e,79,0,t.beepVolume),n(e,82,0,"10","0","ios","1"),n(e,84,0,"solos-color-a","volume-low"),n(e,86,0,"solos-color-a","volume-high"),n(e,94,0,"",""),n(e,96,0,"settings-left-label"),n(e,100,0,"settings-right-label"),n(e,105,0,"settings-left-label"),n(e,111,0,t.isMovingTimeOn),n(e,114,0,"solos-color-d","md"),n(e,118,0,"settings-left-label"),n(e,124,0,t.isDistanceOn),n(e,127,0,"solos-color-d","md"),n(e,137,0,"settings-left-label"),n(e,143,0,t.isAutoPause),n(e,146,0,"solos-color-d","md"),n(e,150,0,"settings-left-label"),n(e,156,0,!t.isStravaLoggedin,t.isUploadStrava),n(e,159,0,"solos-color-d",!t.isStravaLoggedin,"md"),n(e,164,0,"solos-color-a","block","round")}),(function(n,e){var t=e.component;n(e,11,0,l.Wb(e,11,0,l.Ob(e,12).transform("cadence-training.title"))),n(e,18,0,l.Wb(e,18,0,l.Ob(e,19).transform("cadence-training.intro"))),n(e,21,0,l.Wb(e,21,0,l.Ob(e,22).transform("training.choose-level"))),n(e,27,0,l.Wb(e,27,0,l.Ob(e,28).transform("training.easy"))),n(e,32,0,l.Wb(e,32,0,l.Ob(e,33).transform("training.normal"))),n(e,37,0,l.Wb(e,37,0,l.Ob(e,38).transform("training.expert"))),n(e,42,0,l.Wb(e,42,0,l.Ob(e,43).transform("training.custom"))),n(e,48,0,l.Wb(e,48,0,l.Ob(e,49).transform("training.level-setting-header"))),n(e,54,0,l.Wb(e,54,0,l.Ob(e,55).transform("posture-training.training-time"))),n(e,58,0,t.trainingTimeTxt),n(e,63,0,l.Wb(e,63,0,l.Ob(e,64).transform("cadence-training.target-cadence"))),n(e,67,0,t.targetCadenceTxt),n(e,72,0,l.Wb(e,72,0,l.Ob(e,73).transform("training.beep"))),n(e,76,0,l.Ob(e,81).ngClassUntouched,l.Ob(e,81).ngClassTouched,l.Ob(e,81).ngClassPristine,l.Ob(e,81).ngClassDirty,l.Ob(e,81).ngClassValid,l.Ob(e,81).ngClassInvalid,l.Ob(e,81).ngClassPending),n(e,91,0,l.Wb(e,91,0,l.Ob(e,92).transform("training.reminder-settings"))),n(e,97,0,l.Wb(e,97,0,l.Ob(e,98).transform("training.reminder-interval"))),n(e,101,0,t.intervalTxt),n(e,106,0,l.Wb(e,106,0,l.Ob(e,107).transform("training.moving-time"))),n(e,108,0,l.Ob(e,113).ngClassUntouched,l.Ob(e,113).ngClassTouched,l.Ob(e,113).ngClassPristine,l.Ob(e,113).ngClassDirty,l.Ob(e,113).ngClassValid,l.Ob(e,113).ngClassInvalid,l.Ob(e,113).ngClassPending),n(e,119,0,l.Wb(e,119,0,l.Ob(e,120).transform("training.distance"))),n(e,121,0,l.Ob(e,126).ngClassUntouched,l.Ob(e,126).ngClassTouched,l.Ob(e,126).ngClassPristine,l.Ob(e,126).ngClassDirty,l.Ob(e,126).ngClassValid,l.Ob(e,126).ngClassInvalid,l.Ob(e,126).ngClassPending),n(e,132,0,l.Wb(e,132,0,l.Ob(e,133).transform("training.advanced-settings"))),n(e,138,0,l.Wb(e,138,0,l.Ob(e,139).transform("training.auto-pause"))),n(e,140,0,l.Ob(e,145).ngClassUntouched,l.Ob(e,145).ngClassTouched,l.Ob(e,145).ngClassPristine,l.Ob(e,145).ngClassDirty,l.Ob(e,145).ngClassValid,l.Ob(e,145).ngClassInvalid,l.Ob(e,145).ngClassPending),n(e,151,0,l.Wb(e,151,0,l.Ob(e,152).transform("training.upload-strava"))),n(e,153,0,l.Ob(e,158).ngClassUntouched,l.Ob(e,158).ngClassTouched,l.Ob(e,158).ngClassPristine,l.Ob(e,158).ngClassDirty,l.Ob(e,158).ngClassValid,l.Ob(e,158).ngClassInvalid,l.Ob(e,158).ngClassPending),n(e,165,0,l.Wb(e,165,0,l.Ob(e,166).transform("ai-coach.start")))}))}var y=l.yb("app-cadence-training",p,(function(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,1,"app-cadence-training",[],null,null,null,O,x)),l.Bb(1,114688,null,0,p,[i.Jb,o.e,C.k,a.a,s.a,c.c,l.F,b.f,g.b],null,null)],(function(n,e){n(e,1,0)}),null)}),{},{},[]),w=t("hrfs"),j=t("j1ZV"),B=t("iInd");t.d(e,"CadenceTrainingPageModuleNgFactory",(function(){return T}));var T=l.zb(h,[],(function(n){return l.Lb([l.Mb(512,l.m,l.kb,[[8,[m.a,y]],[3,l.m],l.D]),l.Mb(4608,f.n,f.m,[l.z,[2,f.z]]),l.Mb(4608,k.j,k.j,[]),l.Mb(4608,i.c,i.c,[l.F,l.g]),l.Mb(4608,i.Ib,i.Ib,[i.c,l.m,l.w]),l.Mb(4608,i.Nb,i.Nb,[i.c,l.m,l.w]),l.Mb(4608,C.g,C.f,[]),l.Mb(4608,C.c,C.e,[]),l.Mb(4608,C.i,C.d,[]),l.Mb(4608,C.b,C.a,[]),l.Mb(4608,C.k,C.k,[C.l,C.g,C.c,C.i,C.b,C.m,C.n]),l.Mb(1073742336,f.b,f.b,[]),l.Mb(1073742336,k.i,k.i,[]),l.Mb(1073742336,k.b,k.b,[]),l.Mb(1073742336,i.Fb,i.Fb,[]),l.Mb(1073742336,w.b,w.b,[]),l.Mb(1073742336,C.h,C.h,[]),l.Mb(1073742336,j.a,j.a,[]),l.Mb(1073742336,B.o,B.o,[[2,B.t],[2,B.m]]),l.Mb(1073742336,h,h,[]),l.Mb(256,C.n,void 0,[]),l.Mb(256,C.m,void 0,[]),l.Mb(1024,B.k,(function(){return[[{path:"",component:p}]]}),[])])}))},j1ZV:function(n,e,t){"use strict";t.d(e,"a",(function(){return l}));var l=function n(){_classCallCheck(this,n)}}}]);