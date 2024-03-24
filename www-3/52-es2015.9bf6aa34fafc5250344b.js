(window.webpackJsonp=window.webpackJsonp||[]).push([[52],{GXAw:function(l,n,t){"use strict";t.r(n);var e=t("8Y7J"),i=t("mrSG"),o=t("ZZ/e"),s=t("8riC"),a=t("R3bG"),u=t("v8bm"),r=t("Z18M"),c=t("gPPD"),b=t("4SHv"),d=t("8YZb"),p=t("lIDq"),h=t("cPjO"),g=t("yYM5"),m=t("Zesz"),f=t("DaA9"),C=t("OUMn"),v=t("h5tO"),k=t("nhhH");class j{constructor(l,n,t,e,o,s,a,u,r,c,b,d,h,g,m,f,C,k,j){this.platform=l,this.navCtrl=n,this.zone=t,this.events=e,this.appVersion=o,this.translate=s,this.solosConnector=a,this.musicPlayer=u,this.backend=r,this.appLauncherService=c,this.strava=b,this.mapSettings=d,this.coachSettingsService=h,this.heartRateService=g,this.us=m,this.postureSetting=f,this.popupCtrl=C,this.activityStateManager=k,this.preferences=j,this.appVers="1.0.0",this.unit="",this.viewDidEnter=!1,this.evtSolosConnected=l=>{this.zone.run(()=>{this.devInfo=this.solosConnector.getConnectedDevice()})},this.evtSolosDisconnected=l=>{this.zone.run(()=>{this.devInfo=null})},this.isLandscapeMode=()=>this.us.isLandscapeMode(),this.userEmailCicked=()=>{null==this.backend.getLoginUser()?this.navCtrl.navigateBack("/tabs",{state:{activePage:"profile"}}):this.navCtrl.navigateForward("/profile-setting")},this.unitDidChange=l=>i.b(this,void 0,void 0,(function*(){if(!this.viewDidEnter)return;let l=p.e;"imperial"==this.unit&&(l=p.d),yield this.coachSettingsService.setUnit(l)})),this.languageClicked=()=>{this.navCtrl.navigateForward("/language-selection")},this.musicPlayerClicked=()=>{this.navCtrl.navigateForward("/music-player-selection")},this.mapClicked=()=>{this.navCtrl.navigateForward("/map-selection")},this.solosDeviceClicked=()=>{this.navCtrl.navigateForward("/device-info")},this.setupDeviceClicked=()=>{this.navCtrl.navigateRoot("/setup/0")},this.findDeviceClicked=()=>{this.navCtrl.navigateForward("/find-device")},this.stravaClicked=()=>i.b(this,void 0,void 0,(function*(){this.navCtrl.navigateForward("/strava-authorization")})),this.heartRateDeviceClicked=()=>{this.navCtrl.navigateForward(this.heartRateDeviceConnected?"/heart-rate-device-info":"/heart-rate-device-settings")},this.openCalibration=()=>i.b(this,void 0,void 0,(function*(){try{const l=[...v.c,v.a.BASIC_TRAINING,v.a.CORE_TRAINING,v.a.POSTURE_MONITORING],n=this.activityStateManager.validate(l,v.a.CALIBRATION,"/status");n.status?this.navCtrl.navigateForward("/calibration/0/"+encodeURIComponent("/tabs/ai-care")):yield this.popupCtrl.presentAlertController({header:n.header,message:n.message,buttons:n.buttons},!0)}catch(l){console.log(l.message)}})),this.onHeartRateDeviceConnected=()=>{this.zone.run(()=>{this.updateHeartRateConnectionStatus()})},this.onHeartRateDeviceDisconnected=()=>{this.zone.run(()=>{this.updateHeartRateConnectionStatus()})},this.updateHeartRateConnectionStatus=()=>{this.heartRateService.isConnected()?(this.heartRateDeviceConnecting=!1,this.heartRateDeviceConnected=!0,this.heartRateDeviceName=this.heartRateService.getConnectedDeviceName()):this.heartRateService.isConnecting()?(this.heartRateDeviceConnecting=!0,this.heartRateDeviceConnected=!1,this.heartRateDeviceName=this.translate.instant("heart-rate-device.connecting")):(this.heartRateDeviceConnecting=!1,this.heartRateDeviceConnected=!1,this.heartRateDeviceName=this.translate.instant("heart-rate-device.not-connected"))},this.feedbackClicked=()=>{this.navCtrl.navigateForward("/feedback")},this.tutorialClicked=()=>i.b(this,void 0,void 0,(function*(){this.navCtrl.navigateForward("/tutorial/status")})),this.videoTutorialClicked=()=>i.b(this,void 0,void 0,(function*(){this.appLauncherService.openBrowser("https://www.solos.com.hk/support/tutorial-video/")})),this.userManualClicked=()=>{this.appLauncherService.openBrowser("https://www.solos.com.hk/airgo-user-manual/")},this.faqClicked=()=>{this.appLauncherService.openBrowser("https://www.solos.com.hk/support/faq/")},this.privacyPolicyClicked=()=>{this.appLauncherService.openBrowser("https://www.solos.com.hk/airgo-privacy-policy/")},this.copyrightClicked=()=>{this.appLauncherService.openBrowser("https://www.solos.com.hk/airgo-copyright/")},this.termsAndConditionsClicked=()=>{this.appLauncherService.openBrowser("https://www.solos.com.hk/terms-conditions/")},this.resetSettingClicked=()=>i.b(this,void 0,void 0,(function*(){yield this.popupCtrl.presentAlertController({header:this.translate.instant("global.info"),message:this.translate.instant("global.setting-reset"),buttons:[{text:this.translate.instant("global.no"),role:"cancel"},{text:this.translate.instant("global.okay"),handler:()=>i.b(this,void 0,void 0,(function*(){try{this.postureSetting.erasePitchReference(),this.postureSetting.eraseRollReference()}catch(l){console.log(l)}}))}]})})),this.coreTrainingMotionDataCollectionDidChange=l=>i.b(this,void 0,void 0,(function*(){this.viewDidEnter&&(this.coreTrainingMotionDataCollection?yield this.popupCtrl.presentAlertController({header:this.translate.instant("core-training.motion-data-collection"),message:this.translate.instant("core-training.motion-data-collection-detail"),buttons:[{text:this.translate.instant("global.no"),role:"cancel",handler:()=>{this.zone.run(()=>i.b(this,void 0,void 0,(function*(){this.coreTrainingMotionDataCollection=!1,yield this.preferences.setCoreTrainingMotionDataCollection(this.coreTrainingMotionDataCollection)})))}},{text:this.translate.instant("global.yes"),handler:()=>i.b(this,void 0,void 0,(function*(){yield this.preferences.setCoreTrainingMotionDataCollection(!0)}))}]}):yield this.preferences.setCoreTrainingMotionDataCollection(this.coreTrainingMotionDataCollection))}))}ngOnInit(){return i.b(this,void 0,void 0,(function*(){if(this.platformiOS=this.platform.is("ios"),this.platform.is("ios")||this.platform.is("android"))try{const l=yield this.appVersion.getVersionNumber(),n=yield this.appVersion.getVersionCode();this.appVers=`${l} (${n})`}catch(l){console.log("Failed to get app version = "+l.message)}}))}ionViewWillEnter(){return i.b(this,void 0,void 0,(function*(){var l;this.bindedFunctions={},l=this.evtSolosDisconnected.bind(this),this.events.subscribe(a.k,l),this.bindedFunctions[a.k]=l,l=this.evtSolosConnected.bind(this),this.events.subscribe(a.j,l),this.bindedFunctions[a.j]=l,this.devInfo=this.solosConnector.getConnectedDevice(),this.userEmail=null!=this.backend.getLoginUser()?this.backend.getLoginUser().getUsername():this.translate.instant("profile.login");const n=this.musicPlayer.getPlayerType();this.selectedMusicPlayer=this.translate.instant(n==u.l?"music-player.itunes-music":n==u.n?"music-player.spotify":"music-player.none");const t=this.mapSettings.getMapType();this.selectedMap=this.translate.instant(t==d.a.APPLE?"map.apple-map":"map.google-map");let e=this.translate.currentLang;this.languageSet=this.translate.instant("zh-hans"==e?"language.simplified-chinese":"zh-hant"==e?"language.traditional-chinese":"zh-hant-hk"==e?"language.traditional-cantonese":"language.english"),this.stravaConnected=this.strava.isAuthorized()?this.translate.instant("strava.connected"):this.translate.instant("strava.not-connected");const i=this.coachSettingsService.getUnit();this.unit=i==p.d?"imperial":"metric",this.resetSetting=this.translate.instant("status.reset-setting"),this.updateHeartRateConnectionStatus(),l=this.onHeartRateDeviceConnected.bind(this),this.events.subscribe(g.a,l),this.bindedFunctions[g.a]=l,l=this.onHeartRateDeviceDisconnected.bind(this),this.events.subscribe(g.b,l),this.bindedFunctions[g.b]=l,this.coreTrainingMotionDataCollection=yield this.preferences.getCoreTrainingMotionDataCollection()}))}ionViewDidEnter(){this.viewDidEnter=!0}ionViewWillLeave(){for(let l in this.bindedFunctions)this.events.unsubscribe(l,this.bindedFunctions[l]);this.bindedFunctions=null}}class B{}var F=t("pMnS"),y=t("oBZk"),P=t("TSSN"),M=t("s7LF"),O=t("SVse"),D=e.Ab({encapsulation:0,styles:[[""]],data:{}});function S(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,4,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.solosDeviceClicked()&&e),e}),y.W,y.o)),e.Bb(1,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(2,0,null,0,2,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(3,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(4,0,["",""]))],(function(l,n){l(n,1,0,"","",n.component.isLandscapeMode()),l(n,3,0,"settings-left-label")}),(function(l,n){l(n,4,0,n.component.devInfo.name)}))}function w(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.openCalibration()&&e),e}),y.W,y.o)),e.Bb(1,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(2,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(3,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(4,0,["",""])),e.Pb(131072,P.j,[P.k,e.j])],(function(l,n){l(n,1,0,"","",n.component.isLandscapeMode()),l(n,3,0,"settings-left-label")}),(function(l,n){l(n,4,0,e.Wb(n,4,0,e.Ob(n,5).transform("calibration.title")))}))}function X(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.setupDeviceClicked()&&e),e}),y.W,y.o)),e.Bb(1,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(2,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(3,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(4,0,["",""])),e.Pb(131072,P.j,[P.k,e.j])],(function(l,n){l(n,1,0,"","",n.component.isLandscapeMode()),l(n,3,0,"settings-left-label")}),(function(l,n){l(n,4,0,e.Wb(n,4,0,e.Ob(n,5).transform("setup.title")))}))}function W(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.findDeviceClicked()&&e),e}),y.W,y.o)),e.Bb(1,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(2,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(3,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(4,0,["",""])),e.Pb(131072,P.j,[P.k,e.j])],(function(l,n){l(n,1,0,"","",n.component.isLandscapeMode()),l(n,3,0,"settings-left-label")}),(function(l,n){l(n,4,0,e.Wb(n,4,0,e.Ob(n,5).transform("find-device.find-device")))}))}function V(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.resetSettingClicked()&&e),e}),y.W,y.o)),e.Bb(1,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"]},null),(l()(),e.Cb(2,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(3,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(4,0,["",""])),e.Pb(131072,P.j,[P.k,e.j])],(function(l,n){l(n,1,0,"",""),l(n,3,0,"settings-left-label")}),(function(l,n){l(n,4,0,e.Wb(n,4,0,e.Ob(n,5).transform("status.reset-setting")))}))}function L(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,8,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.mapClicked()&&e),e}),y.W,y.o)),e.Bb(1,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(2,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(3,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(4,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(6,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,y.X,y.p)),e.Bb(7,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(8,0,["",""]))],(function(l,n){l(n,1,0,"","",n.component.isLandscapeMode()),l(n,3,0,"settings-left-label"),l(n,7,0,"settings-right-label")}),(function(l,n){var t=n.component;l(n,4,0,e.Wb(n,4,0,e.Ob(n,5).transform("map.map"))),l(n,8,0,t.selectedMap)}))}function I(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,y.Y,y.r)),e.Bb(1,49152,null,0,o.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(2,0,null,0,3,"ion-label",[],null,null,null,y.X,y.p)),e.Bb(3,49152,null,0,o.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(4,0,["",""])),e.Pb(131072,P.j,[P.k,e.j])],null,(function(l,n){l(n,4,0,e.Wb(n,4,0,e.Ob(n,5).transform("status.privacy")))}))}function A(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,12,"ion-item",[["class","settings-item"]],null,null,null,y.W,y.o)),e.Bb(1,49152,null,0,o.J,[e.j,e.p,e.F],null,null),(l()(),e.Cb(2,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(3,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(4,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(6,0,null,0,6,"ion-toggle",[["class","item-toggle"],["color","solos-color-d"],["mode","md"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(l,n,t){var i=!0,o=l.component;return"ionBlur"===n&&(i=!1!==e.Ob(l,7)._handleBlurEvent(t.target)&&i),"ionChange"===n&&(i=!1!==e.Ob(l,7)._handleIonChange(t.target)&&i),"ionChange"===n&&(i=!1!==o.coreTrainingMotionDataCollectionDidChange(t)&&i),"ngModelChange"===n&&(i=!1!==(o.coreTrainingMotionDataCollection=t)&&i),i}),y.nb,y.F)),e.Bb(7,16384,null,0,o.d,[e.p],null,null),e.Sb(1024,null,M.c,(function(l){return[l]}),[o.d]),e.Bb(9,671744,null,0,M.h,[[8,null],[8,null],[8,null],[6,M.c]],{model:[0,"model"]},{update:"ngModelChange"}),e.Sb(2048,null,M.d,null,[M.h]),e.Bb(11,16384,null,0,M.e,[[4,M.d]],null,null),e.Bb(12,49152,null,0,o.Cb,[e.j,e.p,e.F],{color:[0,"color"],mode:[1,"mode"]},null)],(function(l,n){var t=n.component;l(n,3,0,"settings-left-label"),l(n,9,0,t.coreTrainingMotionDataCollection),l(n,12,0,"solos-color-d","md")}),(function(l,n){l(n,4,0,e.Wb(n,4,0,e.Ob(n,5).transform("status.core-training-motion-data-collection"))),l(n,6,0,e.Ob(n,11).ngClassUntouched,e.Ob(n,11).ngClassTouched,e.Ob(n,11).ngClassPristine,e.Ob(n,11).ngClassDirty,e.Ob(n,11).ngClassValid,e.Ob(n,11).ngClassInvalid,e.Ob(n,11).ngClassPending)}))}function R(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,12,"ion-header",[],null,null,null,y.Q,y.i)),e.Bb(1,49152,null,0,o.D,[e.j,e.p,e.F],null,null),(l()(),e.Cb(2,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,y.ob,y.G)),e.Bb(3,49152,null,0,o.Db,[e.j,e.p,e.F],{mode:[0,"mode"]},null),(l()(),e.Cb(4,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,y.L,y.d)),e.Bb(5,49152,null,0,o.n,[e.j,e.p,e.F],null,null),(l()(),e.Cb(6,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(l,n,t){var i=!0;return"click"===n&&(i=!1!==e.Ob(l,8).onClick(t)&&i),i}),y.J,y.b)),e.Bb(7,49152,null,0,o.i,[e.j,e.p,e.F],{text:[0,"text"]},null),e.Bb(8,16384,null,0,o.j,[[2,o.jb],o.Jb],null,null),(l()(),e.Cb(9,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,y.mb,y.E)),e.Bb(10,49152,null,0,o.Bb,[e.j,e.p,e.F],null,null),(l()(),e.Vb(11,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(13,0,null,null,180,"ion-content",[["class","settings-background ion-padding-top"]],null,null,null,y.O,y.g)),e.Bb(14,49152,null,0,o.w,[e.j,e.p,e.F],null,null),(l()(),e.Cb(15,0,null,0,178,"ion-list",[["class","settings-body"]],null,null,null,y.Z,y.q)),e.Bb(16,49152,null,0,o.Q,[e.j,e.p,e.F],null,null),(l()(),e.Cb(17,0,null,0,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,y.Y,y.r)),e.Bb(18,49152,null,0,o.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(19,0,null,0,3,"ion-label",[],null,null,null,y.X,y.p)),e.Bb(20,49152,null,0,o.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(21,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.rb(16777216,null,0,1,null,S)),e.Bb(24,16384,null,0,O.l,[e.X,e.T],{ngIf:[0,"ngIf"]},null),(l()(),e.rb(16777216,null,0,1,null,w)),e.Bb(26,16384,null,0,O.l,[e.X,e.T],{ngIf:[0,"ngIf"]},null),(l()(),e.rb(16777216,null,0,1,null,X)),e.Bb(28,16384,null,0,O.l,[e.X,e.T],{ngIf:[0,"ngIf"]},null),(l()(),e.rb(16777216,null,0,1,null,W)),e.Bb(30,16384,null,0,O.l,[e.X,e.T],{ngIf:[0,"ngIf"]},null),(l()(),e.Cb(31,0,null,0,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,y.Y,y.r)),e.Bb(32,49152,null,0,o.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(33,0,null,0,3,"ion-label",[],null,null,null,y.X,y.p)),e.Bb(34,49152,null,0,o.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(35,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(37,0,null,0,4,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.userEmailCicked()&&e),e}),y.W,y.o)),e.Bb(38,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(39,0,null,0,2,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(40,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(41,0,["",""])),(l()(),e.Cb(42,0,null,0,8,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.stravaClicked()&&e),e}),y.W,y.o)),e.Bb(43,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(44,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(45,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(46,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(48,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,y.X,y.p)),e.Bb(49,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(50,0,["",""])),(l()(),e.Cb(51,0,null,0,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,y.Y,y.r)),e.Bb(52,49152,null,0,o.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(53,0,null,0,3,"ion-label",[],null,null,null,y.X,y.p)),e.Bb(54,49152,null,0,o.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(55,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(57,0,null,0,8,"ion-item",[["class","settings-item"]],null,null,null,y.W,y.o)),e.Bb(58,49152,null,0,o.J,[e.j,e.p,e.F],{disabled:[0,"disabled"]},null),(l()(),e.Cb(59,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(60,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(61,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(63,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,y.X,y.p)),e.Bb(64,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(65,0,["",""])),(l()(),e.Cb(66,0,null,0,26,"ion-item",[["class","settings-item"]],null,null,null,y.W,y.o)),e.Bb(67,49152,null,0,o.J,[e.j,e.p,e.F],{disabled:[0,"disabled"]},null),(l()(),e.Cb(68,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(69,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(70,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(72,0,null,0,20,"ion-item",[["class","settings-right-segment-buttons"],["lines","none"]],null,null,null,y.W,y.o)),e.Bb(73,49152,null,0,o.J,[e.j,e.p,e.F],{lines:[0,"lines"]},null),(l()(),e.Cb(74,0,null,0,18,"ion-segment",[],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(l,n,t){var i=!0,o=l.component;return"ionBlur"===n&&(i=!1!==e.Ob(l,75)._handleBlurEvent(t.target)&&i),"ionChange"===n&&(i=!1!==e.Ob(l,75)._handleChangeEvent(t.target)&&i),"ionChange"===n&&(i=!1!==o.unitDidChange(t)&&i),"ngModelChange"===n&&(i=!1!==(o.unit=t)&&i),i}),y.hb,y.y)),e.Bb(75,16384,null,0,o.Pb,[e.p],null,null),e.Sb(1024,null,M.c,(function(l){return[l]}),[o.Pb]),e.Bb(77,671744,null,0,M.h,[[8,null],[8,null],[8,null],[6,M.c]],{model:[0,"model"]},{update:"ngModelChange"}),e.Sb(2048,null,M.d,null,[M.h]),e.Bb(79,16384,null,0,M.e,[[4,M.d]],null,null),e.Bb(80,49152,null,0,o.mb,[e.j,e.p,e.F],null,null),(l()(),e.Cb(81,0,null,0,5,"ion-segment-button",[["class","settings-segment-button"],["mode","ios"],["value","metric"]],null,null,null,y.gb,y.z)),e.Bb(82,49152,null,0,o.nb,[e.j,e.p,e.F],{mode:[0,"mode"],value:[1,"value"]},null),(l()(),e.Cb(83,0,null,0,3,"ion-label",[],null,null,null,y.X,y.p)),e.Bb(84,49152,null,0,o.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(85,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(87,0,null,0,5,"ion-segment-button",[["class","settings-segment-button"],["mode","ios"],["value","imperial"]],null,null,null,y.gb,y.z)),e.Bb(88,49152,null,0,o.nb,[e.j,e.p,e.F],{mode:[0,"mode"],value:[1,"value"]},null),(l()(),e.Cb(89,0,null,0,3,"ion-label",[],null,null,null,y.X,y.p)),e.Bb(90,49152,null,0,o.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(91,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(93,0,null,0,8,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.languageClicked()&&e),e}),y.W,y.o)),e.Bb(94,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"]},null),(l()(),e.Cb(95,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(96,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(97,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(99,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,y.X,y.p)),e.Bb(100,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(101,0,["",""])),(l()(),e.rb(16777216,null,0,1,null,V)),e.Bb(103,16384,null,0,O.l,[e.X,e.T],{ngIf:[0,"ngIf"]},null),(l()(),e.Cb(104,0,null,0,8,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.musicPlayerClicked()&&e),e}),y.W,y.o)),e.Bb(105,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(106,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(107,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(108,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(110,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,y.X,y.p)),e.Bb(111,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(112,0,["",""])),(l()(),e.rb(16777216,null,0,1,null,L)),e.Bb(114,16384,null,0,O.l,[e.X,e.T],{ngIf:[0,"ngIf"]},null),(l()(),e.Cb(115,0,null,0,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,y.Y,y.r)),e.Bb(116,49152,null,0,o.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(117,0,null,0,3,"ion-label",[],null,null,null,y.X,y.p)),e.Bb(118,49152,null,0,o.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(119,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(121,0,null,0,8,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.heartRateDeviceClicked()&&e),e}),y.W,y.o)),e.Bb(122,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(123,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(124,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(125,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(127,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,y.X,y.p)),e.Bb(128,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(129,0,["",""])),(l()(),e.rb(16777216,null,0,1,null,I)),e.Bb(131,16384,null,0,O.l,[e.X,e.T],{ngIf:[0,"ngIf"]},null),(l()(),e.rb(16777216,null,0,1,null,A)),e.Bb(133,16384,null,0,O.l,[e.X,e.T],{ngIf:[0,"ngIf"]},null),(l()(),e.Cb(134,0,null,0,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,y.Y,y.r)),e.Bb(135,49152,null,0,o.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(136,0,null,0,3,"ion-label",[],null,null,null,y.X,y.p)),e.Bb(137,49152,null,0,o.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(138,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(140,0,null,0,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.feedbackClicked()&&e),e}),y.W,y.o)),e.Bb(141,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(142,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(143,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(144,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(146,0,null,0,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.tutorialClicked()&&e),e}),y.W,y.o)),e.Bb(147,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(148,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(149,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(150,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(152,0,null,0,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.videoTutorialClicked()&&e),e}),y.W,y.o)),e.Bb(153,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(154,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(155,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(156,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(158,0,null,0,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.userManualClicked()&&e),e}),y.W,y.o)),e.Bb(159,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(160,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(161,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(162,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(164,0,null,0,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.faqClicked()&&e),e}),y.W,y.o)),e.Bb(165,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(166,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(167,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(168,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(170,0,null,0,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,y.Y,y.r)),e.Bb(171,49152,null,0,o.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(172,0,null,0,3,"ion-label",[],null,null,null,y.X,y.p)),e.Bb(173,49152,null,0,o.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(174,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(176,0,null,0,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.privacyPolicyClicked()&&e),e}),y.W,y.o)),e.Bb(177,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(178,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(179,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(180,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(182,0,null,0,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.termsAndConditionsClicked()&&e),e}),y.W,y.o)),e.Bb(183,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(184,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(185,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(186,0,["",""])),e.Pb(131072,P.j,[P.k,e.j]),(l()(),e.Cb(188,0,null,0,5,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.copyrightClicked()&&e),e}),y.W,y.o)),e.Bb(189,49152,null,0,o.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"],disabled:[2,"disabled"]},null),(l()(),e.Cb(190,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,y.X,y.p)),e.Bb(191,49152,null,0,o.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(192,0,["",""])),e.Pb(131072,P.j,[P.k,e.j])],(function(l,n){var t=n.component;l(n,3,0,"ios"),l(n,7,0,""),l(n,24,0,null!=t.devInfo),l(n,26,0,null!=t.devInfo),l(n,28,0,null==t.devInfo),l(n,30,0,null==t.devInfo),l(n,38,0,"","",t.isLandscapeMode()),l(n,40,0,"settings-left-label"),l(n,43,0,"","",t.isLandscapeMode()),l(n,45,0,"settings-left-label"),l(n,49,0,"settings-right-label"),l(n,58,0,t.isLandscapeMode()),l(n,60,0,"settings-left-label"),l(n,64,0,"settings-right-label"),l(n,67,0,t.isLandscapeMode()),l(n,69,0,"settings-left-label"),l(n,73,0,"none"),l(n,77,0,t.unit),l(n,82,0,"ios","metric"),l(n,88,0,"ios","imperial"),l(n,94,0,"",""),l(n,96,0,"settings-left-label"),l(n,100,0,"settings-right-label"),l(n,103,0,t.isLandscapeMode()),l(n,105,0,"","",t.isLandscapeMode()),l(n,107,0,"settings-left-label"),l(n,111,0,"settings-right-label"),l(n,114,0,t.platformiOS),l(n,122,0,"","",t.isLandscapeMode()||1==t.heartRateDeviceConnecting),l(n,124,0,"settings-left-label"),l(n,128,0,"settings-right-label"),l(n,131,0,null!=t.coreTrainingMotionDataCollection),l(n,133,0,null!=t.coreTrainingMotionDataCollection),l(n,141,0,"","",t.isLandscapeMode()),l(n,143,0,"settings-left-label"),l(n,147,0,"","",t.isLandscapeMode()),l(n,149,0,"settings-left-label"),l(n,153,0,"","",t.isLandscapeMode()),l(n,155,0,"settings-left-label"),l(n,159,0,"","",t.isLandscapeMode()),l(n,161,0,"settings-left-label"),l(n,165,0,"","",t.isLandscapeMode()),l(n,167,0,"settings-left-label"),l(n,177,0,"","",t.isLandscapeMode()),l(n,179,0,"settings-left-label"),l(n,183,0,"","",t.isLandscapeMode()),l(n,185,0,"settings-left-label"),l(n,189,0,"","",t.isLandscapeMode()),l(n,191,0,"settings-left-label")}),(function(l,n){var t=n.component;l(n,11,0,e.Wb(n,11,0,e.Ob(n,12).transform("status.settings"))),l(n,21,0,e.Wb(n,21,0,e.Ob(n,22).transform("solos-air.solos-air"))),l(n,35,0,e.Wb(n,35,0,e.Ob(n,36).transform("profile.profile"))),l(n,41,0,t.userEmail),l(n,46,0,e.Wb(n,46,0,e.Ob(n,47).transform("status.strava"))),l(n,50,0,t.stravaConnected),l(n,55,0,e.Wb(n,55,0,e.Ob(n,56).transform("status.system"))),l(n,61,0,e.Wb(n,61,0,e.Ob(n,62).transform("status.version"))),l(n,65,0,t.appVers),l(n,70,0,e.Wb(n,70,0,e.Ob(n,71).transform("ai-coach.unit"))),l(n,74,0,e.Ob(n,79).ngClassUntouched,e.Ob(n,79).ngClassTouched,e.Ob(n,79).ngClassPristine,e.Ob(n,79).ngClassDirty,e.Ob(n,79).ngClassValid,e.Ob(n,79).ngClassInvalid,e.Ob(n,79).ngClassPending),l(n,85,0,e.Wb(n,85,0,e.Ob(n,86).transform("ai-coach.metric"))),l(n,91,0,e.Wb(n,91,0,e.Ob(n,92).transform("ai-coach.imperial"))),l(n,97,0,e.Wb(n,97,0,e.Ob(n,98).transform("status.language"))),l(n,101,0,t.languageSet),l(n,108,0,e.Wb(n,108,0,e.Ob(n,109).transform("music-player.music-player"))),l(n,112,0,t.selectedMusicPlayer),l(n,119,0,e.Wb(n,119,0,e.Ob(n,120).transform("ai-coach.devices"))),l(n,125,0,e.Wb(n,125,0,e.Ob(n,126).transform("ai-coach.heart-rate-device"))),l(n,129,0,t.heartRateDeviceName),l(n,138,0,e.Wb(n,138,0,e.Ob(n,139).transform("status.help"))),l(n,144,0,e.Wb(n,144,0,e.Ob(n,145).transform("status.feedback"))),l(n,150,0,e.Wb(n,150,0,e.Ob(n,151).transform("status.tutorial"))),l(n,156,0,e.Wb(n,156,0,e.Ob(n,157).transform("status.video-tutorial"))),l(n,162,0,e.Wb(n,162,0,e.Ob(n,163).transform("status.user-manual"))),l(n,168,0,e.Wb(n,168,0,e.Ob(n,169).transform("status.faq"))),l(n,174,0,e.Wb(n,174,0,e.Ob(n,175).transform("status.legal"))),l(n,180,0,e.Wb(n,180,0,e.Ob(n,181).transform("status.privacy-policy"))),l(n,186,0,e.Wb(n,186,0,e.Ob(n,187).transform("status.terms-and-conditions"))),l(n,192,0,e.Wb(n,192,0,e.Ob(n,193).transform("status.copyright")))}))}function T(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,1,"app-status",[],null,null,null,R,D)),e.Bb(1,114688,null,0,j,[o.Mb,o.Jb,e.F,o.f,s.a,P.k,a.l,u.k,r.c,c.a,b.c,d.b,p.a,h.a,m.a,f.d,C.a,v.b,k.a],null,null)],(function(l,n){l(n,1,0)}),null)}var J=e.yb("app-status",j,T,{},{},[]),N=t("iInd");t.d(n,"StatusPageModuleNgFactory",(function(){return z}));var z=e.zb(B,[],(function(l){return e.Lb([e.Mb(512,e.m,e.kb,[[8,[F.a,J]],[3,e.m],e.D]),e.Mb(4608,O.n,O.m,[e.z,[2,O.z]]),e.Mb(4608,M.j,M.j,[]),e.Mb(4608,o.c,o.c,[e.F,e.g]),e.Mb(4608,o.Ib,o.Ib,[o.c,e.m,e.w]),e.Mb(4608,o.Nb,o.Nb,[o.c,e.m,e.w]),e.Mb(4608,P.g,P.f,[]),e.Mb(4608,P.c,P.e,[]),e.Mb(4608,P.i,P.d,[]),e.Mb(4608,P.b,P.a,[]),e.Mb(4608,P.k,P.k,[P.l,P.g,P.c,P.i,P.b,P.m,P.n]),e.Mb(1073742336,O.b,O.b,[]),e.Mb(1073742336,M.i,M.i,[]),e.Mb(1073742336,M.b,M.b,[]),e.Mb(1073742336,o.Fb,o.Fb,[]),e.Mb(1073742336,P.h,P.h,[]),e.Mb(1073742336,N.o,N.o,[[2,N.t],[2,N.m]]),e.Mb(1073742336,B,B,[]),e.Mb(256,P.n,void 0,[]),e.Mb(256,P.m,void 0,[]),e.Mb(1024,N.k,(function(){return[[{path:"",component:j}]]}),[])])}))},gPPD:function(l,n,t){"use strict";t.d(n,"a",(function(){return c}));var e=t("mrSG"),i=t("ZZ/e"),o=t("nSqr"),s=t("OUMn"),a=t("+UoT"),u=t("8Y7J"),r=t("TSSN");let c=(()=>{class l{constructor(l,n,t,i,o){this.translate=l,this.appLauncher=n,this.popupCtrl=t,this.appAvailability=i,this.platform=o,this.askUserToInstallApp=(l,n)=>e.b(this,void 0,void 0,(function*(){const t=this.translate.instant("global.app-not-installed"),e=this.translate.instant("global.app-not-installed-install-now",{value:l}),i=this.translate.instant("global.no"),o=this.translate.instant("global.yes");yield this.popupCtrl.presentAlertController({header:t,message:e,buttons:[{text:i,role:"cancel"},{text:o,handler:n}]},!0)})),this.openiOSApp=(l,n,t)=>e.b(this,void 0,void 0,(function*(){let e=!1;try{(e=yield this.appAvailability.check(n))&&(yield this.appLauncher.launch({uri:n}))}catch(i){console.log(i)}finally{e||this.askUserToInstallApp(l,()=>{this.appLauncher.launch({uri:t})})}})),this.openAndroidApp=(l,n,t)=>e.b(this,void 0,void 0,(function*(){let e=!1;try{(e=yield this.appLauncher.canLaunch({packageName:n}))&&(yield this.appLauncher.launch({packageName:n}))}catch(i){console.log(i)}finally{e||this.askUserToInstallApp(l,()=>{this.appLauncher.launch({uri:t})})}})),this.openAppleMapApp=()=>e.b(this,void 0,void 0,(function*(){try{const l=this.translate.instant("global.map");this.platform.is("ios")&&(yield this.openiOSApp(l,"http://maps.apple.com/?address=",""))}catch(l){console.log(l.message)}})),this.openAppleMusicApp=()=>e.b(this,void 0,void 0,(function*(){try{const l=this.translate.instant("global.music");this.platform.is("ios")&&(yield this.openiOSApp(l,"https://music.apple.com",""))}catch(l){console.log(l.message)}})),this.openSpotifyApp=()=>e.b(this,void 0,void 0,(function*(){try{const l=this.translate.instant("global.spotify");this.platform.is("ios")?yield this.openiOSApp(l,"spotify:","https://apps.apple.com/app/spotify-new-music-and-podcasts/id324684580?ls=1&mt=8"):this.platform.is("android")&&(yield this.openAndroidApp(l,"com.spotify.music","https://play.google.com/store/apps/details?id=com.spotify.music"))}catch(l){console.log(l.message)}})),this.openZelloApp=()=>{try{const l=this.translate.instant("global.zello");this.platform.is("ios")?this.openiOSApp(l,"zello://","https://itunes.apple.com/app/zello-walkie-talkie/id508231856?ls=1&mt=8"):this.platform.is("android")&&this.openAndroidApp(l,"com.loudtalks","https://play.google.com/store/apps/details?id=com.loudtalks")}catch(l){console.log(l.message)}},this.openGoogleTranslateApp=()=>e.b(this,void 0,void 0,(function*(){try{const l=this.translate.instant("global.google-translate");this.platform.is("ios")?yield this.openiOSApp(l,"googletranslate://","https://itunes.apple.com/us/app/google-translate/id414706506?mt=8"):this.platform.is("android")&&(yield this.openAndroidApp(l,"com.google.android.apps.translate","https://play.google.com/store/apps/details?id=com.google.android.apps.translate"))}catch(l){console.log(l.message)}})),this.openGoogleMapApp=()=>e.b(this,void 0,void 0,(function*(){try{const l=this.translate.instant("global.google-map");this.platform.is("ios")?yield this.openiOSApp(l,"comgooglemaps://","https://itunes.apple.com/us/app/apple-store/id585027354?pt=9008&amp;ct=help-center-mg-promo-groupa-appicon-6291838&amp;mt=8"):this.platform.is("android")&&(yield this.openAndroidApp(l,"com.google.android.apps.maps","https://play.google.com/store/apps/details?id=com.google.android.apps.maps"))}catch(l){console.log(l.message)}})),this.openBrowser=l=>e.b(this,void 0,void 0,(function*(){try{if(this.platform.is("ios"))yield this.appLauncher.launch({uri:l});else if(this.platform.is("android")){const n="com.android.chrome",t="https://play.google.com/store/apps/details?id=com.android.chrome";(yield this.appLauncher.canLaunch({packageName:n}))?yield this.appLauncher.launch({uri:l,packageName:n}):yield this.askUserToInstallApp(n,()=>e.b(this,void 0,void 0,(function*(){try{yield this.appLauncher.launch({uri:t})}catch(l){console.log(l.message)}})))}}catch(n){console.log(n.message)}}))}}return l.ngInjectableDef=u.ac({factory:function(){return new l(u.bc(r.k),u.bc(o.a),u.bc(s.a),u.bc(a.a),u.bc(i.Mb))},token:l,providedIn:"root"}),l})()}}]);