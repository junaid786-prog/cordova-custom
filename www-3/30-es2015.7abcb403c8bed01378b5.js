(window.webpackJsonp=window.webpackJsonp||[]).push([[30],{"/rTQ":function(t,e,n){"use strict";n.d(e,"a",(function(){return l})),n("ZZ/e");var i=n("v8bm");n("gPPD");const s="assets/imgs/spotify-icon.svg",o="assets/imgs/iTunes-icon.svg";class l{constructor(t,e,n,l,a){this.navCtrl=t,this.events=e,this.musicPlayerService=n,this.zone=l,this.appLauncherService=a,this.iconPath=s,this.isMusicConnected=!1,this.processBarValue=0,this.currentArtist="",this.currentTrack="",this.isPrevSupport=!0,this.duration=0,this.goToMusicSelectPage=()=>{this.isMusicConnected||this.navCtrl.navigateForward("/music-player-selection")},this.openMusicPlayer=()=>{const t=this.musicPlayerService.getPlayerType();t==i.n?this.appLauncherService.openSpotifyApp():t==i.l&&this.appLauncherService.openAppleMusicApp()},this.skipBackwardPressed=()=>{this.musicPlayerService.prevTrack()},this.playPausePressed=()=>{this.isPlaying?this.musicPlayerService.pause():this.musicPlayerService.play()},this.skipForwardPressed=()=>{this.musicPlayerService.nextTrack()},this.updatePlayerTypeUI=()=>{this.zone.run(()=>{const t=this.musicPlayerService.getPlayerType();t==i.l?(this.isMusicConnected=!0,this.iconPath=o):t==i.n?(this.isMusicConnected=!0,this.iconPath=s):this.isMusicConnected=!1,this.currentArtist="",this.currentTrack="",this.processBarValue=0,this.isPlaying=!1,this.isPrevSupport=!0,this.musicPlayerService.getTrackInfo()})},this.onPlayerTypeChanged=t=>{this.updatePlayerTypeUI()},this.playerOnPlay=t=>{this.zone.run(()=>{this.isPlaying=!0})},this.playerOnPause=t=>{this.zone.run(()=>{this.isPlaying=!1})},this.onTrackChanged=t=>{this.zone.run(()=>{this.currentTrack=t.title,this.currentArtist=t.artist,this.duration=t.duration,this.currentTrack!=t.title&&(this.processBarValue=0)})},this.onPositionChanged=t=>{this.zone.run(()=>{this.processBarValue=Math.floor(t.position/this.duration*100)})},this.onArtWorkChanged=t=>{this.zone.run(()=>{if(null!=t.url)this.iconPath=t.url;else{const t=this.musicPlayerService.getPlayerType();this.iconPath=t==i.n?s:o}})},this.onPrevTrackEnabled=t=>{this.zone.run(()=>{this.isPrevSupport=t})},this.onSpotifyNotInstalled=()=>{this.appLauncherService.openSpotifyApp()}}ngOnInit(){let t;this.bindedFunctions={},this.events.subscribe(i.e,t=this.playerOnPlay),this.bindedFunctions[i.e]=t,this.events.subscribe(i.d,t=this.playerOnPause),this.bindedFunctions[i.d]=t,this.events.subscribe(i.i,t=this.onTrackChanged),this.bindedFunctions[i.i]=t,this.events.subscribe(i.f,t=this.onPositionChanged),this.bindedFunctions[i.f]=t,this.events.subscribe(i.j,t=this.onPlayerTypeChanged),this.bindedFunctions[i.j]=t,this.events.subscribe(i.c,t=this.onArtWorkChanged),this.bindedFunctions[i.c]=t,this.events.subscribe(i.g,t=this.onPrevTrackEnabled),this.bindedFunctions[i.g]=t,this.events.subscribe(i.h,t=this.onSpotifyNotInstalled),this.bindedFunctions[i.h]=t,this.musicPlayerService.ready()&&this.updatePlayerTypeUI()}ngOnDestroy(){for(let t in this.bindedFunctions)this.events.unsubscribe(t,this.bindedFunctions[t]);this.bindedFunctions=null}}},Opd2:function(t,e,n){"use strict";var i=n("8Y7J"),s=n("SVse"),o=n("oBZk"),l=n("ZZ/e"),a=n("TSSN");n("/rTQ"),n("v8bm"),n("gPPD"),n.d(e,"a",(function(){return c})),n.d(e,"b",(function(){return g}));var c=i.Ab({encapsulation:0,styles:[[".buttons-last-slot[_ngcontent-%COMP%]{margin-right:0}.song-name[_ngcontent-%COMP%]{white-space:nowrap;overflow:hidden;text-overflow:ellipsis;margin-top:auto;margin-bottom:auto;margin-left:10px;color:var(--solos-color-a);font-size:14px;font-weight:300;line-height:16px}.song-icon[_ngcontent-%COMP%]{margin-top:auto;margin-bottom:auto;width:28px;height:28px;-o-object-fit:contain;object-fit:contain}.no-song[_ngcontent-%COMP%]{overflow:hidden;margin-top:auto;margin-bottom:auto;margin-left:10px;height:16px;color:var(--solos-color-a);font-size:14px;font-weight:300;line-height:16px}.no-song-icon[_ngcontent-%COMP%]{background:var(--solos-color-c);color:var(--solos-color-b);display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;border-radius:20%;margin-top:auto;margin-bottom:auto;width:22px;height:22px;padding:3px;min-width:22px}.progress[_ngcontent-%COMP%]{display:-webkit-box;display:flex;left:0;right:0;top:0;position:absolute;height:6px;border-style:solid;border-color:var(--solos-color-a);border-width:1px}.left-panel[_ngcontent-%COMP%]{display:-webkit-box;display:flex;margin-left:4px;overflow:hidden}.full-width[_ngcontent-%COMP%]{width:100%}.progress-inside[_ngcontent-%COMP%]{background:var(--solos-color-a);border-style:solid;border-color:var(--solos-color-b);border-width:1px}"]],data:{}});function r(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,4,"div",[["class","progress"]],null,null,null,null,null)),(t()(),i.Cb(1,0,null,null,3,"div",[["class","progress-inside"]],null,null,null,null,null)),i.Sb(512,null,s.w,s.x,[i.p,i.y,i.L]),i.Bb(3,278528,null,0,s.o,[s.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(4,{"flex-basis":0})],(function(t,e){var n=t(e,4,0,e.component.processBarValue+"%");t(e,3,0,n)}),null)}function h(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,6,"div",[["class","left-panel"],["slot","start"],["style","margin-top: 4px;"]],null,[[null,"click"]],(function(t,e,n){var i=!0;return"click"===e&&(i=!1!==t.component.openMusicPlayer()&&i),i}),null,null)),(t()(),i.Cb(1,0,null,null,1,"ion-img",[["class","song-icon"]],null,null,null,o.S,o.k)),i.Bb(2,49152,null,0,l.F,[i.j,i.p,i.F],{src:[0,"src"]},null),(t()(),i.Cb(3,0,null,null,3,"p",[["class","song-name"]],null,null,null,null,null)),(t()(),i.Vb(4,null,[" ",""])),(t()(),i.Cb(5,0,null,null,0,"br",[],null,null,null,null,null)),(t()(),i.Vb(6,null,[" "," "]))],(function(t,e){t(e,2,0,e.component.iconPath)}),(function(t,e){var n=e.component;t(e,4,0,n.currentTrack),t(e,6,0,n.currentArtist)}))}function u(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,13,"ion-buttons",[["slot","end"],["style","margin-top: 4px;"]],null,null,null,o.L,o.d)),i.Bb(1,49152,null,0,l.n,[i.j,i.p,i.F],null,null),(t()(),i.Cb(2,0,null,0,3,"ion-button",[["clear",""],["icon-only",""],["ion-button",""]],null,[[null,"click"]],(function(t,e,n){var i=!0;return"click"===e&&(i=!1!==t.component.skipBackwardPressed()&&i),i}),o.K,o.c)),i.Bb(3,49152,null,0,l.m,[i.j,i.p,i.F],{disabled:[0,"disabled"]},null),(t()(),i.Cb(4,0,null,0,1,"ion-icon",[["color","solos-color-a"],["name","skip-backward"]],null,null,null,o.R,o.j)),i.Bb(5,49152,null,0,l.E,[i.j,i.p,i.F],{color:[0,"color"],name:[1,"name"]},null),(t()(),i.Cb(6,0,null,0,3,"ion-button",[["clear",""],["icon-only",""],["ion-button",""]],null,[[null,"click"]],(function(t,e,n){var i=!0;return"click"===e&&(i=!1!==t.component.playPausePressed()&&i),i}),o.K,o.c)),i.Bb(7,49152,null,0,l.m,[i.j,i.p,i.F],null,null),(t()(),i.Cb(8,0,null,0,1,"ion-icon",[["color","solos-color-a"]],null,null,null,o.R,o.j)),i.Bb(9,49152,null,0,l.E,[i.j,i.p,i.F],{color:[0,"color"],name:[1,"name"]},null),(t()(),i.Cb(10,0,null,0,3,"ion-button",[["clear",""],["icon-only",""],["ion-button",""]],null,[[null,"click"]],(function(t,e,n){var i=!0;return"click"===e&&(i=!1!==t.component.skipForwardPressed()&&i),i}),o.K,o.c)),i.Bb(11,49152,null,0,l.m,[i.j,i.p,i.F],null,null),(t()(),i.Cb(12,0,null,0,1,"ion-icon",[["color","solos-color-a"],["name","skip-forward"]],null,null,null,o.R,o.j)),i.Bb(13,49152,null,0,l.E,[i.j,i.p,i.F],{color:[0,"color"],name:[1,"name"]},null)],(function(t,e){var n=e.component;t(e,3,0,!n.isPrevSupport),t(e,5,0,"solos-color-a","skip-backward"),t(e,9,0,"solos-color-a",n.isPlaying?"pause":"play"),t(e,13,0,"solos-color-a","skip-forward")}),null)}function p(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,1,"ion-icon",[["class","no-song-icon"],["name","musical-note"]],null,null,null,o.R,o.j)),i.Bb(1,49152,null,0,l.E,[i.j,i.p,i.F],{name:[0,"name"]},null)],(function(t,e){t(e,1,0,"musical-note")}),null)}function d(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,5,"div",[["class","left-panel full-width"],["slot","start"]],null,[[null,"click"]],(function(t,e,n){var i=!0;return"click"===e&&(i=!1!==t.component.goToMusicSelectPage()&&i),i}),null,null)),(t()(),i.rb(16777216,null,null,1,null,p)),i.Bb(2,16384,null,0,s.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(t()(),i.Cb(3,0,null,null,2,"p",[["class","no-song"]],null,null,null,null,null)),(t()(),i.Vb(4,null,[" "," "])),i.Pb(131072,a.j,[a.k,i.j])],(function(t,e){t(e,2,0,!e.component.isMusicConnected)}),(function(t,e){t(e,4,0,i.Wb(e,4,0,i.Ob(e,5).transform("music-player.music-off")))}))}function g(t){return i.Xb(0,[(t()(),i.Cb(0,0,null,null,9,"ion-toolbar",[["class","settings-background"],["style","padding:0 16px;"]],null,null,null,o.ob,o.G)),i.Bb(1,49152,null,0,l.Db,[i.j,i.p,i.F],null,null),(t()(),i.rb(16777216,null,0,1,null,r)),i.Bb(3,16384,null,0,s.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(t()(),i.rb(16777216,null,0,1,null,h)),i.Bb(5,16384,null,0,s.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(t()(),i.rb(16777216,null,0,1,null,u)),i.Bb(7,16384,null,0,s.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(t()(),i.rb(16777216,null,0,1,null,d)),i.Bb(9,16384,null,0,s.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null)],(function(t,e){var n=e.component;t(e,3,0,n.isMusicConnected),t(e,5,0,n.isMusicConnected),t(e,7,0,n.isMusicConnected),t(e,9,0,!n.isMusicConnected)}),null)}},gPPD:function(t,e,n){"use strict";n.d(e,"a",(function(){return h}));var i=n("mrSG"),s=n("ZZ/e"),o=n("nSqr"),l=n("OUMn"),a=n("+UoT"),c=n("8Y7J"),r=n("TSSN");let h=(()=>{class t{constructor(t,e,n,s,o){this.translate=t,this.appLauncher=e,this.popupCtrl=n,this.appAvailability=s,this.platform=o,this.askUserToInstallApp=(t,e)=>i.b(this,void 0,void 0,(function*(){const n=this.translate.instant("global.app-not-installed"),i=this.translate.instant("global.app-not-installed-install-now",{value:t}),s=this.translate.instant("global.no"),o=this.translate.instant("global.yes");yield this.popupCtrl.presentAlertController({header:n,message:i,buttons:[{text:s,role:"cancel"},{text:o,handler:e}]},!0)})),this.openiOSApp=(t,e,n)=>i.b(this,void 0,void 0,(function*(){let i=!1;try{(i=yield this.appAvailability.check(e))&&(yield this.appLauncher.launch({uri:e}))}catch(s){console.log(s)}finally{i||this.askUserToInstallApp(t,()=>{this.appLauncher.launch({uri:n})})}})),this.openAndroidApp=(t,e,n)=>i.b(this,void 0,void 0,(function*(){let i=!1;try{(i=yield this.appLauncher.canLaunch({packageName:e}))&&(yield this.appLauncher.launch({packageName:e}))}catch(s){console.log(s)}finally{i||this.askUserToInstallApp(t,()=>{this.appLauncher.launch({uri:n})})}})),this.openAppleMapApp=()=>i.b(this,void 0,void 0,(function*(){try{const t=this.translate.instant("global.map");this.platform.is("ios")&&(yield this.openiOSApp(t,"http://maps.apple.com/?address=",""))}catch(t){console.log(t.message)}})),this.openAppleMusicApp=()=>i.b(this,void 0,void 0,(function*(){try{const t=this.translate.instant("global.music");this.platform.is("ios")&&(yield this.openiOSApp(t,"https://music.apple.com",""))}catch(t){console.log(t.message)}})),this.openSpotifyApp=()=>i.b(this,void 0,void 0,(function*(){try{const t=this.translate.instant("global.spotify");this.platform.is("ios")?yield this.openiOSApp(t,"spotify:","https://apps.apple.com/app/spotify-new-music-and-podcasts/id324684580?ls=1&mt=8"):this.platform.is("android")&&(yield this.openAndroidApp(t,"com.spotify.music","https://play.google.com/store/apps/details?id=com.spotify.music"))}catch(t){console.log(t.message)}})),this.openZelloApp=()=>{try{const t=this.translate.instant("global.zello");this.platform.is("ios")?this.openiOSApp(t,"zello://","https://itunes.apple.com/app/zello-walkie-talkie/id508231856?ls=1&mt=8"):this.platform.is("android")&&this.openAndroidApp(t,"com.loudtalks","https://play.google.com/store/apps/details?id=com.loudtalks")}catch(t){console.log(t.message)}},this.openGoogleTranslateApp=()=>i.b(this,void 0,void 0,(function*(){try{const t=this.translate.instant("global.google-translate");this.platform.is("ios")?yield this.openiOSApp(t,"googletranslate://","https://itunes.apple.com/us/app/google-translate/id414706506?mt=8"):this.platform.is("android")&&(yield this.openAndroidApp(t,"com.google.android.apps.translate","https://play.google.com/store/apps/details?id=com.google.android.apps.translate"))}catch(t){console.log(t.message)}})),this.openGoogleMapApp=()=>i.b(this,void 0,void 0,(function*(){try{const t=this.translate.instant("global.google-map");this.platform.is("ios")?yield this.openiOSApp(t,"comgooglemaps://","https://itunes.apple.com/us/app/apple-store/id585027354?pt=9008&amp;ct=help-center-mg-promo-groupa-appicon-6291838&amp;mt=8"):this.platform.is("android")&&(yield this.openAndroidApp(t,"com.google.android.apps.maps","https://play.google.com/store/apps/details?id=com.google.android.apps.maps"))}catch(t){console.log(t.message)}})),this.openBrowser=t=>i.b(this,void 0,void 0,(function*(){try{if(this.platform.is("ios"))yield this.appLauncher.launch({uri:t});else if(this.platform.is("android")){const e="com.android.chrome",n="https://play.google.com/store/apps/details?id=com.android.chrome";(yield this.appLauncher.canLaunch({packageName:e}))?yield this.appLauncher.launch({uri:t,packageName:e}):yield this.askUserToInstallApp(e,()=>i.b(this,void 0,void 0,(function*(){try{yield this.appLauncher.launch({uri:n})}catch(t){console.log(t.message)}})))}}catch(e){console.log(e.message)}}))}}return t.ngInjectableDef=c.ac({factory:function(){return new t(c.bc(r.k),c.bc(o.a),c.bc(l.a),c.bc(a.a),c.bc(s.Mb))},token:t,providedIn:"root"}),t})()},lIDq:function(t,e,n){"use strict";n.d(e,"e",(function(){return S})),n.d(e,"d",(function(){return M})),n.d(e,"c",(function(){return B})),n.d(e,"b",(function(){return R})),n.d(e,"a",(function(){return A}));var i=n("mrSG"),s=n("ZZ/e"),o=n("8Y7J"),l=n("xgBC");const a="CoachSettings",c="Unit",r="ReminderInterval",h="ReminderTypeHeartRate",u="ReminderTypePacing",p="ReminderTypeAveragePacing",d="ReminderTypeElapsedTime",g="ReminderTypeDistance",b="ReminderTypeMovingTime",y="ReminderTypeStepCount",m="ReminderTypeStride",v="ReminderTypeAverageStride",f="ReminderTypeCadence",T="ReminderTypeAverageCadence",P="ReminderTypeLeftBalance",C="ReminderTypeRightBalance",k="CoachAutoPause",S="Metric",M="Imperial",B="evtCoachUnitChanged",R="evtCoachIntervalChanged";let A=(()=>{class t{constructor(t,e,n){this.platform=t,this.storage=e,this.events=n,this.unit=S,this.reminderInterval=0,this.heartRate=!1,this.pacing=!1,this.avgPacing=!1,this.elapsedTime=!1,this.distance=!1,this.movingTime=!1,this.stepCount=!1,this.stride=!1,this.avgStride=!1,this.cadence=!1,this.avgCadence=!1,this.leftBalance=!1,this.rightBalance=!1,this.autoPause=!0,this.getKey=t=>a+"-"+t,this.setUnit=t=>i.b(this,void 0,void 0,(function*(){if((t==M||t==S)&&t!=this.unit){this.unit=t;try{yield this.storage.set(this.getKey(c),t),this.events.publish(B)}catch(e){console.log(e)}}})),this.getUnit=()=>this.unit,this.setReminderInterval=t=>i.b(this,void 0,void 0,(function*(){if(t>=0&&t!=this.reminderInterval){this.reminderInterval=t;try{yield this.storage.set(this.getKey(r),t),this.events.publish(R)}catch(e){console.log(e)}}})),this.getReminderInterval=()=>this.reminderInterval,this.setReminderHeartRateTypeOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.heartRate){this.heartRate=t;try{yield this.storage.set(this.getKey(h),t)}catch(e){console.log(e)}}})),this.getReminderHeartRateTypeOn=()=>this.heartRate,this.setReminderTypePacingOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.pacing){this.pacing=t;try{yield this.storage.set(this.getKey(u),t)}catch(e){console.log(e)}}})),this.getReminderTypePacingOn=()=>this.pacing,this.setReminderTypeAveragePacingOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.avgPacing){this.avgPacing=t;try{yield this.storage.set(this.getKey(p),t)}catch(e){console.log(e)}}})),this.getReminderTypeAveragePacingOn=()=>this.avgPacing,this.setReminderTypeElapsedTimeOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.elapsedTime){this.elapsedTime=t;try{yield this.storage.set(this.getKey(d),t)}catch(e){console.log(e)}}})),this.getReminderTypeElapsedTimeOn=()=>this.elapsedTime,this.setReminderTypeDistanceOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.distance){this.distance=t;try{yield this.storage.set(this.getKey(g),t)}catch(e){console.log(e)}}})),this.getReminderTypeDistanceOn=()=>this.distance,this.setReminderTypeMovingTimeOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.movingTime){this.movingTime=t;try{yield this.storage.set(this.getKey(b),t)}catch(e){console.log(e)}}})),this.getReminderTypeMovingTimeOn=()=>this.movingTime,this.setReminderTypeStepCountOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.stepCount){this.stepCount=t;try{yield this.storage.set(this.getKey(y),t)}catch(e){console.log(e)}}})),this.getReminderTypeStepCountOn=()=>this.stepCount,this.setReminderTypeStrideOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.stride){this.stride=t;try{yield this.storage.set(this.getKey(m),t)}catch(e){console.log(e)}}})),this.getReminderTypeStrideOn=()=>this.stride,this.setReminderTypeAverageStrideOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.avgStride){this.avgStride=t;try{yield this.storage.set(this.getKey(v),t)}catch(e){console.log(e)}}})),this.getReminderTypeAverageStrideOn=()=>this.avgStride,this.setReminderTypeCadenceOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.cadence){this.cadence=t;try{yield this.storage.set(this.getKey(f),t)}catch(e){console.log(e)}}})),this.getReminderTypeCadenceOn=()=>this.cadence,this.setReminderTypeAverageCadenceOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.avgCadence){this.avgCadence=t;try{yield this.storage.set(this.getKey(T),t)}catch(e){console.log(e)}}})),this.getReminderTypeAverageCadenceOn=()=>this.avgCadence,this.setReminderTypeLeftBalanceOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.leftBalance){this.leftBalance=t;try{yield this.storage.set(this.getKey(P),t)}catch(e){console.log(e)}}})),this.getReminderTypeLeftBalanceOn=()=>this.leftBalance,this.setReminderTypeRightBalanceOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.rightBalance){this.rightBalance=t;try{yield this.storage.set(this.getKey(C),t)}catch(e){console.log(e)}}})),this.getReminderTypeRightBalanceOn=()=>this.rightBalance,this.setCoachAutoPauseOn=t=>i.b(this,void 0,void 0,(function*(){if(t!=this.autoPause){this.autoPause=t;try{yield this.storage.set(this.getKey(k),t)}catch(e){console.log(e)}}})),this.getCoachAutoPauseOn=()=>this.autoPause,this.platform.ready().then(()=>{this.storage.get(this.getKey(c)).then(t=>{null!=t&&(this.unit=t)}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(r)).then(t=>{null!=t&&(this.reminderInterval=Number(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(h)).then(t=>{null!=t&&(this.heartRate=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(u)).then(t=>{null!=t&&(this.pacing=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(p)).then(t=>{null!=t&&(this.avgPacing=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(d)).then(t=>{null!=t&&(this.elapsedTime=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(g)).then(t=>{null!=t&&(this.distance=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(b)).then(t=>{null!=t&&(this.movingTime=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(y)).then(t=>{null!=t&&(this.stepCount=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(m)).then(t=>{null!=t&&(this.stride=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(v)).then(t=>{null!=t&&(this.avgStride=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(f)).then(t=>{null!=t&&(this.cadence=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(T)).then(t=>{null!=t&&(this.avgCadence=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(P)).then(t=>{null!=t&&(this.leftBalance=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(C)).then(t=>{null!=t&&(this.rightBalance=Boolean(t))}).catch(t=>{console.log(t)}),this.storage.get(this.getKey(k)).then(t=>{null!=t&&(this.autoPause=Boolean(t))}).catch(t=>{console.log(t)})})}}return t.ngInjectableDef=o.ac({factory:function(){return new t(o.bc(s.Mb),o.bc(l.b),o.bc(s.f))},token:t,providedIn:"root"}),t})()},y8fh:function(t,e,n){"use strict";n.r(e),n.d(e,"ProfilePageModuleNgFactory",(function(){return b}));var i=n("8Y7J"),s=n("723k"),o=n("pMnS"),l=n("Bm+Y"),a=n("SVse"),c=n("s7LF"),r=n("ZZ/e"),h=n("TSSN"),u=n("hrfs"),p=n("j1ZV"),d=n("iInd"),g=n("uxLX"),b=i.zb(s.a,[],(function(t){return i.Lb([i.Mb(512,i.m,i.kb,[[8,[o.a,l.a]],[3,i.m],i.D]),i.Mb(4608,a.n,a.m,[i.z,[2,a.z]]),i.Mb(4608,c.j,c.j,[]),i.Mb(4608,r.c,r.c,[i.F,i.g]),i.Mb(4608,r.Ib,r.Ib,[r.c,i.m,i.w]),i.Mb(4608,r.Nb,r.Nb,[r.c,i.m,i.w]),i.Mb(4608,h.g,h.f,[]),i.Mb(4608,h.c,h.e,[]),i.Mb(4608,h.i,h.d,[]),i.Mb(4608,h.b,h.a,[]),i.Mb(4608,h.k,h.k,[h.l,h.g,h.c,h.i,h.b,h.m,h.n]),i.Mb(1073742336,a.b,a.b,[]),i.Mb(1073742336,c.i,c.i,[]),i.Mb(1073742336,c.b,c.b,[]),i.Mb(1073742336,r.Fb,r.Fb,[]),i.Mb(1073742336,u.b,u.b,[]),i.Mb(1073742336,h.h,h.h,[]),i.Mb(1073742336,p.a,p.a,[]),i.Mb(1073742336,d.o,d.o,[[2,d.t],[2,d.m]]),i.Mb(1073742336,s.a,s.a,[]),i.Mb(256,h.n,void 0,[]),i.Mb(256,h.m,void 0,[]),i.Mb(1024,d.k,(function(){return[[{path:"",component:g.e}]]}),[])])}))}}]);