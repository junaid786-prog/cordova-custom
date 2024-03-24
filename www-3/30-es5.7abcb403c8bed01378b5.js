function _classCallCheck(e,n){if(!(e instanceof n))throw new TypeError("Cannot call a class as a function")}function _defineProperties(e,n){for(var t=0;t<n.length;t++){var r=n[t];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}function _createClass(e,n,t){return n&&_defineProperties(e.prototype,n),t&&_defineProperties(e,t),e}(window.webpackJsonp=window.webpackJsonp||[]).push([[30],{"/rTQ":function(e,n,t){"use strict";t.d(n,"a",(function(){return a})),t("ZZ/e");var r=t("v8bm");t("gPPD");var i="assets/imgs/spotify-icon.svg",o="assets/imgs/iTunes-icon.svg",a=function(){function e(n,t,a,s,c){var l=this;_classCallCheck(this,e),this.navCtrl=n,this.events=t,this.musicPlayerService=a,this.zone=s,this.appLauncherService=c,this.iconPath=i,this.isMusicConnected=!1,this.processBarValue=0,this.currentArtist="",this.currentTrack="",this.isPrevSupport=!0,this.duration=0,this.goToMusicSelectPage=function(){l.isMusicConnected||l.navCtrl.navigateForward("/music-player-selection")},this.openMusicPlayer=function(){var e=l.musicPlayerService.getPlayerType();e==r.n?l.appLauncherService.openSpotifyApp():e==r.l&&l.appLauncherService.openAppleMusicApp()},this.skipBackwardPressed=function(){l.musicPlayerService.prevTrack()},this.playPausePressed=function(){l.isPlaying?l.musicPlayerService.pause():l.musicPlayerService.play()},this.skipForwardPressed=function(){l.musicPlayerService.nextTrack()},this.updatePlayerTypeUI=function(){l.zone.run((function(){var e=l.musicPlayerService.getPlayerType();e==r.l?(l.isMusicConnected=!0,l.iconPath=o):e==r.n?(l.isMusicConnected=!0,l.iconPath=i):l.isMusicConnected=!1,l.currentArtist="",l.currentTrack="",l.processBarValue=0,l.isPlaying=!1,l.isPrevSupport=!0,l.musicPlayerService.getTrackInfo()}))},this.onPlayerTypeChanged=function(e){l.updatePlayerTypeUI()},this.playerOnPlay=function(e){l.zone.run((function(){l.isPlaying=!0}))},this.playerOnPause=function(e){l.zone.run((function(){l.isPlaying=!1}))},this.onTrackChanged=function(e){l.zone.run((function(){l.currentTrack=e.title,l.currentArtist=e.artist,l.duration=e.duration,l.currentTrack!=e.title&&(l.processBarValue=0)}))},this.onPositionChanged=function(e){l.zone.run((function(){l.processBarValue=Math.floor(e.position/l.duration*100)}))},this.onArtWorkChanged=function(e){l.zone.run((function(){if(null!=e.url)l.iconPath=e.url;else{var n=l.musicPlayerService.getPlayerType();l.iconPath=n==r.n?i:o}}))},this.onPrevTrackEnabled=function(e){l.zone.run((function(){l.isPrevSupport=e}))},this.onSpotifyNotInstalled=function(){l.appLauncherService.openSpotifyApp()}}return _createClass(e,[{key:"ngOnInit",value:function(){var e;this.bindedFunctions={},this.events.subscribe(r.e,e=this.playerOnPlay),this.bindedFunctions[r.e]=e,this.events.subscribe(r.d,e=this.playerOnPause),this.bindedFunctions[r.d]=e,this.events.subscribe(r.i,e=this.onTrackChanged),this.bindedFunctions[r.i]=e,this.events.subscribe(r.f,e=this.onPositionChanged),this.bindedFunctions[r.f]=e,this.events.subscribe(r.j,e=this.onPlayerTypeChanged),this.bindedFunctions[r.j]=e,this.events.subscribe(r.c,e=this.onArtWorkChanged),this.bindedFunctions[r.c]=e,this.events.subscribe(r.g,e=this.onPrevTrackEnabled),this.bindedFunctions[r.g]=e,this.events.subscribe(r.h,e=this.onSpotifyNotInstalled),this.bindedFunctions[r.h]=e,this.musicPlayerService.ready()&&this.updatePlayerTypeUI()}},{key:"ngOnDestroy",value:function(){for(var e in this.bindedFunctions)this.events.unsubscribe(e,this.bindedFunctions[e]);this.bindedFunctions=null}}]),e}()},Opd2:function(e,n,t){"use strict";var r=t("8Y7J"),i=t("SVse"),o=t("oBZk"),a=t("ZZ/e"),s=t("TSSN");t("/rTQ"),t("v8bm"),t("gPPD"),t.d(n,"a",(function(){return c})),t.d(n,"b",(function(){return d}));var c=r.Ab({encapsulation:0,styles:[[".buttons-last-slot[_ngcontent-%COMP%]{margin-right:0}.song-name[_ngcontent-%COMP%]{white-space:nowrap;overflow:hidden;text-overflow:ellipsis;margin-top:auto;margin-bottom:auto;margin-left:10px;color:var(--solos-color-a);font-size:14px;font-weight:300;line-height:16px}.song-icon[_ngcontent-%COMP%]{margin-top:auto;margin-bottom:auto;width:28px;height:28px;-o-object-fit:contain;object-fit:contain}.no-song[_ngcontent-%COMP%]{overflow:hidden;margin-top:auto;margin-bottom:auto;margin-left:10px;height:16px;color:var(--solos-color-a);font-size:14px;font-weight:300;line-height:16px}.no-song-icon[_ngcontent-%COMP%]{background:var(--solos-color-c);color:var(--solos-color-b);display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;border-radius:20%;margin-top:auto;margin-bottom:auto;width:22px;height:22px;padding:3px;min-width:22px}.progress[_ngcontent-%COMP%]{display:-webkit-box;display:flex;left:0;right:0;top:0;position:absolute;height:6px;border-style:solid;border-color:var(--solos-color-a);border-width:1px}.left-panel[_ngcontent-%COMP%]{display:-webkit-box;display:flex;margin-left:4px;overflow:hidden}.full-width[_ngcontent-%COMP%]{width:100%}.progress-inside[_ngcontent-%COMP%]{background:var(--solos-color-a);border-style:solid;border-color:var(--solos-color-b);border-width:1px}"]],data:{}});function l(e){return r.Xb(0,[(e()(),r.Cb(0,0,null,null,4,"div",[["class","progress"]],null,null,null,null,null)),(e()(),r.Cb(1,0,null,null,3,"div",[["class","progress-inside"]],null,null,null,null,null)),r.Sb(512,null,i.w,i.x,[r.p,r.y,r.L]),r.Bb(3,278528,null,0,i.o,[i.w],{ngStyle:[0,"ngStyle"]},null),r.Qb(4,{"flex-basis":0})],(function(e,n){var t=e(n,4,0,n.component.processBarValue+"%");e(n,3,0,t)}),null)}function u(e){return r.Xb(0,[(e()(),r.Cb(0,0,null,null,6,"div",[["class","left-panel"],["slot","start"],["style","margin-top: 4px;"]],null,[[null,"click"]],(function(e,n,t){var r=!0;return"click"===n&&(r=!1!==e.component.openMusicPlayer()&&r),r}),null,null)),(e()(),r.Cb(1,0,null,null,1,"ion-img",[["class","song-icon"]],null,null,null,o.S,o.k)),r.Bb(2,49152,null,0,a.F,[r.j,r.p,r.F],{src:[0,"src"]},null),(e()(),r.Cb(3,0,null,null,3,"p",[["class","song-name"]],null,null,null,null,null)),(e()(),r.Vb(4,null,[" ",""])),(e()(),r.Cb(5,0,null,null,0,"br",[],null,null,null,null,null)),(e()(),r.Vb(6,null,[" "," "]))],(function(e,n){e(n,2,0,n.component.iconPath)}),(function(e,n){var t=n.component;e(n,4,0,t.currentTrack),e(n,6,0,t.currentArtist)}))}function p(e){return r.Xb(0,[(e()(),r.Cb(0,0,null,null,13,"ion-buttons",[["slot","end"],["style","margin-top: 4px;"]],null,null,null,o.L,o.d)),r.Bb(1,49152,null,0,a.n,[r.j,r.p,r.F],null,null),(e()(),r.Cb(2,0,null,0,3,"ion-button",[["clear",""],["icon-only",""],["ion-button",""]],null,[[null,"click"]],(function(e,n,t){var r=!0;return"click"===n&&(r=!1!==e.component.skipBackwardPressed()&&r),r}),o.K,o.c)),r.Bb(3,49152,null,0,a.m,[r.j,r.p,r.F],{disabled:[0,"disabled"]},null),(e()(),r.Cb(4,0,null,0,1,"ion-icon",[["color","solos-color-a"],["name","skip-backward"]],null,null,null,o.R,o.j)),r.Bb(5,49152,null,0,a.E,[r.j,r.p,r.F],{color:[0,"color"],name:[1,"name"]},null),(e()(),r.Cb(6,0,null,0,3,"ion-button",[["clear",""],["icon-only",""],["ion-button",""]],null,[[null,"click"]],(function(e,n,t){var r=!0;return"click"===n&&(r=!1!==e.component.playPausePressed()&&r),r}),o.K,o.c)),r.Bb(7,49152,null,0,a.m,[r.j,r.p,r.F],null,null),(e()(),r.Cb(8,0,null,0,1,"ion-icon",[["color","solos-color-a"]],null,null,null,o.R,o.j)),r.Bb(9,49152,null,0,a.E,[r.j,r.p,r.F],{color:[0,"color"],name:[1,"name"]},null),(e()(),r.Cb(10,0,null,0,3,"ion-button",[["clear",""],["icon-only",""],["ion-button",""]],null,[[null,"click"]],(function(e,n,t){var r=!0;return"click"===n&&(r=!1!==e.component.skipForwardPressed()&&r),r}),o.K,o.c)),r.Bb(11,49152,null,0,a.m,[r.j,r.p,r.F],null,null),(e()(),r.Cb(12,0,null,0,1,"ion-icon",[["color","solos-color-a"],["name","skip-forward"]],null,null,null,o.R,o.j)),r.Bb(13,49152,null,0,a.E,[r.j,r.p,r.F],{color:[0,"color"],name:[1,"name"]},null)],(function(e,n){var t=n.component;e(n,3,0,!t.isPrevSupport),e(n,5,0,"solos-color-a","skip-backward"),e(n,9,0,"solos-color-a",t.isPlaying?"pause":"play"),e(n,13,0,"solos-color-a","skip-forward")}),null)}function h(e){return r.Xb(0,[(e()(),r.Cb(0,0,null,null,1,"ion-icon",[["class","no-song-icon"],["name","musical-note"]],null,null,null,o.R,o.j)),r.Bb(1,49152,null,0,a.E,[r.j,r.p,r.F],{name:[0,"name"]},null)],(function(e,n){e(n,1,0,"musical-note")}),null)}function g(e){return r.Xb(0,[(e()(),r.Cb(0,0,null,null,5,"div",[["class","left-panel full-width"],["slot","start"]],null,[[null,"click"]],(function(e,n,t){var r=!0;return"click"===n&&(r=!1!==e.component.goToMusicSelectPage()&&r),r}),null,null)),(e()(),r.rb(16777216,null,null,1,null,h)),r.Bb(2,16384,null,0,i.l,[r.X,r.T],{ngIf:[0,"ngIf"]},null),(e()(),r.Cb(3,0,null,null,2,"p",[["class","no-song"]],null,null,null,null,null)),(e()(),r.Vb(4,null,[" "," "])),r.Pb(131072,s.j,[s.k,r.j])],(function(e,n){e(n,2,0,!n.component.isMusicConnected)}),(function(e,n){e(n,4,0,r.Wb(n,4,0,r.Ob(n,5).transform("music-player.music-off")))}))}function d(e){return r.Xb(0,[(e()(),r.Cb(0,0,null,null,9,"ion-toolbar",[["class","settings-background"],["style","padding:0 16px;"]],null,null,null,o.ob,o.G)),r.Bb(1,49152,null,0,a.Db,[r.j,r.p,r.F],null,null),(e()(),r.rb(16777216,null,0,1,null,l)),r.Bb(3,16384,null,0,i.l,[r.X,r.T],{ngIf:[0,"ngIf"]},null),(e()(),r.rb(16777216,null,0,1,null,u)),r.Bb(5,16384,null,0,i.l,[r.X,r.T],{ngIf:[0,"ngIf"]},null),(e()(),r.rb(16777216,null,0,1,null,p)),r.Bb(7,16384,null,0,i.l,[r.X,r.T],{ngIf:[0,"ngIf"]},null),(e()(),r.rb(16777216,null,0,1,null,g)),r.Bb(9,16384,null,0,i.l,[r.X,r.T],{ngIf:[0,"ngIf"]},null)],(function(e,n){var t=n.component;e(n,3,0,t.isMusicConnected),e(n,5,0,t.isMusicConnected),e(n,7,0,t.isMusicConnected),e(n,9,0,!t.isMusicConnected)}),null)}},gPPD:function(e,n,t){"use strict";t.d(n,"a",(function(){return u}));var r=t("mrSG"),i=t("ZZ/e"),o=t("nSqr"),a=t("OUMn"),s=t("+UoT"),c=t("8Y7J"),l=t("TSSN"),u=function(){var e=function e(n,t,i,o,a){var s=this;_classCallCheck(this,e),this.translate=n,this.appLauncher=t,this.popupCtrl=i,this.appAvailability=o,this.platform=a,this.askUserToInstallApp=function(e,n){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function t(){var r,i,o,a;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return r=this.translate.instant("global.app-not-installed"),i=this.translate.instant("global.app-not-installed-install-now",{value:e}),o=this.translate.instant("global.no"),a=this.translate.instant("global.yes"),t.next=3,this.popupCtrl.presentAlertController({header:r,message:i,buttons:[{text:o,role:"cancel"},{text:a,handler:n}]},!0);case 3:case"end":return t.stop()}}),t,this)})))},this.openiOSApp=function(e,n,t){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function r(){var i,o=this;return regeneratorRuntime.wrap((function(r){for(;;)switch(r.prev=r.next){case 0:return i=!1,r.prev=1,r.next=4,this.appAvailability.check(n);case 4:if(r.t0=i=r.sent,!r.t0){r.next=8;break}return r.next=8,this.appLauncher.launch({uri:n});case 8:r.next=13;break;case 10:r.prev=10,r.t1=r.catch(1),console.log(r.t1);case 13:return r.prev=13,i||this.askUserToInstallApp(e,(function(){o.appLauncher.launch({uri:t})})),r.finish(13);case 16:case"end":return r.stop()}}),r,this,[[1,10,13,16]])})))},this.openAndroidApp=function(e,n,t){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function r(){var i,o=this;return regeneratorRuntime.wrap((function(r){for(;;)switch(r.prev=r.next){case 0:return i=!1,r.prev=1,r.next=4,this.appLauncher.canLaunch({packageName:n});case 4:if(r.t0=i=r.sent,!r.t0){r.next=8;break}return r.next=8,this.appLauncher.launch({packageName:n});case 8:r.next=13;break;case 10:r.prev=10,r.t1=r.catch(1),console.log(r.t1);case 13:return r.prev=13,i||this.askUserToInstallApp(e,(function(){o.appLauncher.launch({uri:t})})),r.finish(13);case 16:case"end":return r.stop()}}),r,this,[[1,10,13,16]])})))},this.openAppleMapApp=function(){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function e(){var n;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,n=this.translate.instant("global.map"),e.t0=this.platform.is("ios"),!e.t0){e.next=6;break}return e.next=6,this.openiOSApp(n,"http://maps.apple.com/?address=","");case 6:e.next=11;break;case 8:e.prev=8,e.t1=e.catch(0),console.log(e.t1.message);case 11:case"end":return e.stop()}}),e,this,[[0,8]])})))},this.openAppleMusicApp=function(){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function e(){var n;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,n=this.translate.instant("global.music"),e.t0=this.platform.is("ios"),!e.t0){e.next=6;break}return e.next=6,this.openiOSApp(n,"https://music.apple.com","");case 6:e.next=11;break;case 8:e.prev=8,e.t1=e.catch(0),console.log(e.t1.message);case 11:case"end":return e.stop()}}),e,this,[[0,8]])})))},this.openSpotifyApp=function(){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function e(){var n;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,n=this.translate.instant("global.spotify"),!this.platform.is("ios")){e.next=7;break}return e.next=5,this.openiOSApp(n,"spotify:","https://apps.apple.com/app/spotify-new-music-and-podcasts/id324684580?ls=1&mt=8");case 5:e.next=11;break;case 7:if(e.t0=this.platform.is("android"),!e.t0){e.next=11;break}return e.next=11,this.openAndroidApp(n,"com.spotify.music","https://play.google.com/store/apps/details?id=com.spotify.music");case 11:e.next=16;break;case 13:e.prev=13,e.t1=e.catch(0),console.log(e.t1.message);case 16:case"end":return e.stop()}}),e,this,[[0,13]])})))},this.openZelloApp=function(){try{var e=s.translate.instant("global.zello");s.platform.is("ios")?s.openiOSApp(e,"zello://","https://itunes.apple.com/app/zello-walkie-talkie/id508231856?ls=1&mt=8"):s.platform.is("android")&&s.openAndroidApp(e,"com.loudtalks","https://play.google.com/store/apps/details?id=com.loudtalks")}catch(n){console.log(n.message)}},this.openGoogleTranslateApp=function(){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function e(){var n;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,n=this.translate.instant("global.google-translate"),!this.platform.is("ios")){e.next=7;break}return e.next=5,this.openiOSApp(n,"googletranslate://","https://itunes.apple.com/us/app/google-translate/id414706506?mt=8");case 5:e.next=11;break;case 7:if(e.t0=this.platform.is("android"),!e.t0){e.next=11;break}return e.next=11,this.openAndroidApp(n,"com.google.android.apps.translate","https://play.google.com/store/apps/details?id=com.google.android.apps.translate");case 11:e.next=16;break;case 13:e.prev=13,e.t1=e.catch(0),console.log(e.t1.message);case 16:case"end":return e.stop()}}),e,this,[[0,13]])})))},this.openGoogleMapApp=function(){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function e(){var n;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(e.prev=0,n=this.translate.instant("global.google-map"),!this.platform.is("ios")){e.next=7;break}return e.next=5,this.openiOSApp(n,"comgooglemaps://","https://itunes.apple.com/us/app/apple-store/id585027354?pt=9008&amp;ct=help-center-mg-promo-groupa-appicon-6291838&amp;mt=8");case 5:e.next=11;break;case 7:if(e.t0=this.platform.is("android"),!e.t0){e.next=11;break}return e.next=11,this.openAndroidApp(n,"com.google.android.apps.maps","https://play.google.com/store/apps/details?id=com.google.android.apps.maps");case 11:e.next=16;break;case 13:e.prev=13,e.t1=e.catch(0),console.log(e.t1.message);case 16:case"end":return e.stop()}}),e,this,[[0,13]])})))},this.openBrowser=function(e){return r.b(s,void 0,void 0,regeneratorRuntime.mark((function n(){var t,i,o=this;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,!this.platform.is("ios")){n.next=6;break}return n.next=4,this.appLauncher.launch({uri:e});case 4:n.next=17;break;case 6:if(!this.platform.is("android")){n.next=17;break}return t="com.android.chrome",i="https://play.google.com/store/apps/details?id=com.android.chrome",n.next=10,this.appLauncher.canLaunch({packageName:t});case 10:if(!n.sent){n.next=15;break}return n.next=13,this.appLauncher.launch({uri:e,packageName:t});case 13:n.next=17;break;case 15:return n.next=17,this.askUserToInstallApp(t,(function(){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,this.appLauncher.launch({uri:i});case 3:e.next=8;break;case 5:e.prev=5,e.t0=e.catch(0),console.log(e.t0.message);case 8:case"end":return e.stop()}}),e,this,[[0,5]])})))}));case 17:n.next=22;break;case 19:n.prev=19,n.t0=n.catch(0),console.log(n.t0.message);case 22:case"end":return n.stop()}}),n,this,[[0,19]])})))}};return e.ngInjectableDef=c.ac({factory:function(){return new e(c.bc(l.k),c.bc(o.a),c.bc(a.a),c.bc(s.a),c.bc(i.Mb))},token:e,providedIn:"root"}),e}()},lIDq:function(e,n,t){"use strict";t.d(n,"e",(function(){return s})),t.d(n,"d",(function(){return c})),t.d(n,"c",(function(){return l})),t.d(n,"b",(function(){return u})),t.d(n,"a",(function(){return p}));var r=t("mrSG"),i=t("ZZ/e"),o=t("8Y7J"),a=t("xgBC"),s="Metric",c="Imperial",l="evtCoachUnitChanged",u="evtCoachIntervalChanged",p=function(){var e=function e(n,t,i){var o=this;_classCallCheck(this,e),this.platform=n,this.storage=t,this.events=i,this.unit=s,this.reminderInterval=0,this.heartRate=!1,this.pacing=!1,this.avgPacing=!1,this.elapsedTime=!1,this.distance=!1,this.movingTime=!1,this.stepCount=!1,this.stride=!1,this.avgStride=!1,this.cadence=!1,this.avgCadence=!1,this.leftBalance=!1,this.rightBalance=!1,this.autoPause=!0,this.getKey=function(e){return"CoachSettings-"+e},this.setUnit=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e!=c&&e!=s||e==this.unit){n.next=11;break}return this.unit=e,n.prev=2,n.next=5,this.storage.set(this.getKey("Unit"),e);case 5:this.events.publish(l),n.next=11;break;case 8:n.prev=8,n.t0=n.catch(2),console.log(n.t0);case 11:case"end":return n.stop()}}),n,this,[[2,8]])})))},this.getUnit=function(){return o.unit},this.setReminderInterval=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(!(e>=0&&e!=this.reminderInterval)){n.next=11;break}return this.reminderInterval=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderInterval"),e);case 5:this.events.publish(u),n.next=11;break;case 8:n.prev=8,n.t0=n.catch(2),console.log(n.t0);case 11:case"end":return n.stop()}}),n,this,[[2,8]])})))},this.getReminderInterval=function(){return o.reminderInterval},this.setReminderHeartRateTypeOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.heartRate){n.next=10;break}return this.heartRate=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeHeartRate"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderHeartRateTypeOn=function(){return o.heartRate},this.setReminderTypePacingOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.pacing){n.next=10;break}return this.pacing=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypePacing"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypePacingOn=function(){return o.pacing},this.setReminderTypeAveragePacingOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.avgPacing){n.next=10;break}return this.avgPacing=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeAveragePacing"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypeAveragePacingOn=function(){return o.avgPacing},this.setReminderTypeElapsedTimeOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.elapsedTime){n.next=10;break}return this.elapsedTime=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeElapsedTime"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypeElapsedTimeOn=function(){return o.elapsedTime},this.setReminderTypeDistanceOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.distance){n.next=10;break}return this.distance=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeDistance"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypeDistanceOn=function(){return o.distance},this.setReminderTypeMovingTimeOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.movingTime){n.next=10;break}return this.movingTime=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeMovingTime"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypeMovingTimeOn=function(){return o.movingTime},this.setReminderTypeStepCountOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.stepCount){n.next=10;break}return this.stepCount=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeStepCount"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypeStepCountOn=function(){return o.stepCount},this.setReminderTypeStrideOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.stride){n.next=10;break}return this.stride=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeStride"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypeStrideOn=function(){return o.stride},this.setReminderTypeAverageStrideOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.avgStride){n.next=10;break}return this.avgStride=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeAverageStride"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypeAverageStrideOn=function(){return o.avgStride},this.setReminderTypeCadenceOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.cadence){n.next=10;break}return this.cadence=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeCadence"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypeCadenceOn=function(){return o.cadence},this.setReminderTypeAverageCadenceOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.avgCadence){n.next=10;break}return this.avgCadence=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeAverageCadence"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypeAverageCadenceOn=function(){return o.avgCadence},this.setReminderTypeLeftBalanceOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.leftBalance){n.next=10;break}return this.leftBalance=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeLeftBalance"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypeLeftBalanceOn=function(){return o.leftBalance},this.setReminderTypeRightBalanceOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.rightBalance){n.next=10;break}return this.rightBalance=e,n.prev=2,n.next=5,this.storage.set(this.getKey("ReminderTypeRightBalance"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getReminderTypeRightBalanceOn=function(){return o.rightBalance},this.setCoachAutoPauseOn=function(e){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(e==this.autoPause){n.next=10;break}return this.autoPause=e,n.prev=2,n.next=5,this.storage.set(this.getKey("CoachAutoPause"),e);case 5:n.next=10;break;case 7:n.prev=7,n.t0=n.catch(2),console.log(n.t0);case 10:case"end":return n.stop()}}),n,this,[[2,7]])})))},this.getCoachAutoPauseOn=function(){return o.autoPause},this.platform.ready().then((function(){o.storage.get(o.getKey("Unit")).then((function(e){null!=e&&(o.unit=e)})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderInterval")).then((function(e){null!=e&&(o.reminderInterval=Number(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeHeartRate")).then((function(e){null!=e&&(o.heartRate=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypePacing")).then((function(e){null!=e&&(o.pacing=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeAveragePacing")).then((function(e){null!=e&&(o.avgPacing=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeElapsedTime")).then((function(e){null!=e&&(o.elapsedTime=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeDistance")).then((function(e){null!=e&&(o.distance=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeMovingTime")).then((function(e){null!=e&&(o.movingTime=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeStepCount")).then((function(e){null!=e&&(o.stepCount=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeStride")).then((function(e){null!=e&&(o.stride=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeAverageStride")).then((function(e){null!=e&&(o.avgStride=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeCadence")).then((function(e){null!=e&&(o.cadence=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeAverageCadence")).then((function(e){null!=e&&(o.avgCadence=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeLeftBalance")).then((function(e){null!=e&&(o.leftBalance=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("ReminderTypeRightBalance")).then((function(e){null!=e&&(o.rightBalance=Boolean(e))})).catch((function(e){console.log(e)})),o.storage.get(o.getKey("CoachAutoPause")).then((function(e){null!=e&&(o.autoPause=Boolean(e))})).catch((function(e){console.log(e)}))}))};return e.ngInjectableDef=o.ac({factory:function(){return new e(o.bc(i.Mb),o.bc(a.b),o.bc(i.f))},token:e,providedIn:"root"}),e}()},y8fh:function(e,n,t){"use strict";t.r(n),t.d(n,"ProfilePageModuleNgFactory",(function(){return f}));var r=t("8Y7J"),i=t("723k"),o=t("pMnS"),a=t("Bm+Y"),s=t("SVse"),c=t("s7LF"),l=t("ZZ/e"),u=t("TSSN"),p=t("hrfs"),h=t("j1ZV"),g=t("iInd"),d=t("uxLX"),f=r.zb(i.a,[],(function(e){return r.Lb([r.Mb(512,r.m,r.kb,[[8,[o.a,a.a]],[3,r.m],r.D]),r.Mb(4608,s.n,s.m,[r.z,[2,s.z]]),r.Mb(4608,c.j,c.j,[]),r.Mb(4608,l.c,l.c,[r.F,r.g]),r.Mb(4608,l.Ib,l.Ib,[l.c,r.m,r.w]),r.Mb(4608,l.Nb,l.Nb,[l.c,r.m,r.w]),r.Mb(4608,u.g,u.f,[]),r.Mb(4608,u.c,u.e,[]),r.Mb(4608,u.i,u.d,[]),r.Mb(4608,u.b,u.a,[]),r.Mb(4608,u.k,u.k,[u.l,u.g,u.c,u.i,u.b,u.m,u.n]),r.Mb(1073742336,s.b,s.b,[]),r.Mb(1073742336,c.i,c.i,[]),r.Mb(1073742336,c.b,c.b,[]),r.Mb(1073742336,l.Fb,l.Fb,[]),r.Mb(1073742336,p.b,p.b,[]),r.Mb(1073742336,u.h,u.h,[]),r.Mb(1073742336,h.a,h.a,[]),r.Mb(1073742336,g.o,g.o,[[2,g.t],[2,g.m]]),r.Mb(1073742336,i.a,i.a,[]),r.Mb(256,u.n,void 0,[]),r.Mb(256,u.m,void 0,[]),r.Mb(1024,g.k,(function(){return[[{path:"",component:d.e}]]}),[])])}))}}]);