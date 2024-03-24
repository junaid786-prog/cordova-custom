function _toConsumableArray(n){return _arrayWithoutHoles(n)||_iterableToArray(n)||_unsupportedIterableToArray(n)||_nonIterableSpread()}function _nonIterableSpread(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}function _unsupportedIterableToArray(n,t){if(n){if("string"==typeof n)return _arrayLikeToArray(n,t);var e=Object.prototype.toString.call(n).slice(8,-1);return"Object"===e&&n.constructor&&(e=n.constructor.name),"Map"===e||"Set"===e?Array.from(n):"Arguments"===e||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(e)?_arrayLikeToArray(n,t):void 0}}function _iterableToArray(n){if("undefined"!=typeof Symbol&&Symbol.iterator in Object(n))return Array.from(n)}function _arrayWithoutHoles(n){if(Array.isArray(n))return _arrayLikeToArray(n)}function _arrayLikeToArray(n,t){(null==t||t>n.length)&&(t=n.length);for(var e=0,l=new Array(t);e<t;e++)l[e]=n[e];return l}function _classCallCheck(n,t){if(!(n instanceof t))throw new TypeError("Cannot call a class as a function")}function _defineProperties(n,t){for(var e=0;e<t.length;e++){var l=t[e];l.enumerable=l.enumerable||!1,l.configurable=!0,"value"in l&&(l.writable=!0),Object.defineProperty(n,l.key,l)}}function _createClass(n,t,e){return t&&_defineProperties(n.prototype,t),e&&_defineProperties(n,e),n}(window.webpackJsonp=window.webpackJsonp||[]).push([[28],{"/rTQ":function(n,t,e){"use strict";e.d(t,"a",(function(){return i})),e("ZZ/e");var l=e("v8bm");e("gPPD");var o="assets/imgs/spotify-icon.svg",r="assets/imgs/iTunes-icon.svg",i=function(){function n(t,e,i,a,s){var u=this;_classCallCheck(this,n),this.navCtrl=t,this.events=e,this.musicPlayerService=i,this.zone=a,this.appLauncherService=s,this.iconPath=o,this.isMusicConnected=!1,this.processBarValue=0,this.currentArtist="",this.currentTrack="",this.isPrevSupport=!0,this.duration=0,this.goToMusicSelectPage=function(){u.isMusicConnected||u.navCtrl.navigateForward("/music-player-selection")},this.openMusicPlayer=function(){var n=u.musicPlayerService.getPlayerType();n==l.n?u.appLauncherService.openSpotifyApp():n==l.l&&u.appLauncherService.openAppleMusicApp()},this.skipBackwardPressed=function(){u.musicPlayerService.prevTrack()},this.playPausePressed=function(){u.isPlaying?u.musicPlayerService.pause():u.musicPlayerService.play()},this.skipForwardPressed=function(){u.musicPlayerService.nextTrack()},this.updatePlayerTypeUI=function(){u.zone.run((function(){var n=u.musicPlayerService.getPlayerType();n==l.l?(u.isMusicConnected=!0,u.iconPath=r):n==l.n?(u.isMusicConnected=!0,u.iconPath=o):u.isMusicConnected=!1,u.currentArtist="",u.currentTrack="",u.processBarValue=0,u.isPlaying=!1,u.isPrevSupport=!0,u.musicPlayerService.getTrackInfo()}))},this.onPlayerTypeChanged=function(n){u.updatePlayerTypeUI()},this.playerOnPlay=function(n){u.zone.run((function(){u.isPlaying=!0}))},this.playerOnPause=function(n){u.zone.run((function(){u.isPlaying=!1}))},this.onTrackChanged=function(n){u.zone.run((function(){u.currentTrack=n.title,u.currentArtist=n.artist,u.duration=n.duration,u.currentTrack!=n.title&&(u.processBarValue=0)}))},this.onPositionChanged=function(n){u.zone.run((function(){u.processBarValue=Math.floor(n.position/u.duration*100)}))},this.onArtWorkChanged=function(n){u.zone.run((function(){if(null!=n.url)u.iconPath=n.url;else{var t=u.musicPlayerService.getPlayerType();u.iconPath=t==l.n?o:r}}))},this.onPrevTrackEnabled=function(n){u.zone.run((function(){u.isPrevSupport=n}))},this.onSpotifyNotInstalled=function(){u.appLauncherService.openSpotifyApp()}}return _createClass(n,[{key:"ngOnInit",value:function(){var n;this.bindedFunctions={},this.events.subscribe(l.e,n=this.playerOnPlay),this.bindedFunctions[l.e]=n,this.events.subscribe(l.d,n=this.playerOnPause),this.bindedFunctions[l.d]=n,this.events.subscribe(l.i,n=this.onTrackChanged),this.bindedFunctions[l.i]=n,this.events.subscribe(l.f,n=this.onPositionChanged),this.bindedFunctions[l.f]=n,this.events.subscribe(l.j,n=this.onPlayerTypeChanged),this.bindedFunctions[l.j]=n,this.events.subscribe(l.c,n=this.onArtWorkChanged),this.bindedFunctions[l.c]=n,this.events.subscribe(l.g,n=this.onPrevTrackEnabled),this.bindedFunctions[l.g]=n,this.events.subscribe(l.h,n=this.onSpotifyNotInstalled),this.bindedFunctions[l.h]=n,this.musicPlayerService.ready()&&this.updatePlayerTypeUI()}},{key:"ngOnDestroy",value:function(){for(var n in this.bindedFunctions)this.events.unsubscribe(n,this.bindedFunctions[n]);this.bindedFunctions=null}}]),n}()},Es4I:function(n,t,e){"use strict";e.r(t);var l=e("8Y7J"),o=e("mrSG"),r=e("ZZ/e"),i=e("VjBM"),a=e("OUMn"),s=e("J5fJ"),u=e("3Cwc"),c=e("Z18M"),p=e("Zesz"),b=e("h5tO"),d=function(){function n(t,e,l,r,a,u,c){var p=this;_classCallCheck(this,n),this.navCtrl=t,this.coachTraining=e,this.popupCtrl=l,this.trainingSettings=r,this.backend=a,this.us=u,this.activityStateManager=c,this.isLandscapeMode=function(){return p.us.isLandscapeMode()},this.startTraining=function(){return o.b(p,void 0,void 0,regeneratorRuntime.mark((function n(){var t,e,l,o,r;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,t=[].concat(_toConsumableArray(b.c),[b.a.BASIC_TRAINING,b.a.CORE_TRAINING,b.a.POSTURE_MONITORING]),!(e=this.activityStateManager.validate(t,b.a.STEP_COUNT_TRAINING,"/step-count-settings")).status){n.next=21;break}return l=this.trainingSettings.getTargetStepCount(),o=0,n.prev=5,n.next=8,this.backend.queryTodayActivityStats();case 8:o=n.sent.getStats().reduce((function(n,t){return n+(t.getStepCount()>=0?t.getStepCount():0)}),0),n.next=14;break;case 11:n.prev=11,n.t0=n.catch(5),console.log(n.t0);case 14:if(r=new s.a(0,o,l),n.t1=this.coachTraining.start(i.b.StepCount,r),!n.t1){n.next=19;break}return n.next=19,this.navCtrl.navigateForward("/training-running/stepCount");case 19:n.next=23;break;case 21:return n.next=23,this.popupCtrl.presentAlertController({header:e.header,message:e.message,buttons:e.buttons},!0);case 23:n.next=28;break;case 25:n.prev=25,n.t2=n.catch(0),console.log(n.t2);case 28:case"end":return n.stop()}}),n,this,[[0,25],[5,11]])})))},this.settingsBtnClicked=function(){p.navCtrl.navigateForward("/step-count-settings")}}return _createClass(n,[{key:"ngOnInit",value:function(){}},{key:"ionViewWillEnter",value:function(){}},{key:"ionViewDidEnter",value:function(){}},{key:"ionViewWillLeave",value:function(){}}]),n}(),h=function n(){_classCallCheck(this,n)},f=e("pMnS"),g=e("TSSN"),m=e("Opd2"),v=e("/rTQ"),k=e("v8bm"),y=e("gPPD"),x=e("oBZk"),C=e("SVse"),w=l.Ab({encapsulation:0,styles:[['.footer-buttons[_ngcontent-%COMP%]{display:-webkit-box;display:flex;padding:10px;background:var(--solos-color-b);border-width:1px 0 0;border-style:solid;border-color:var(--solos-color-a)}.intro-text-container[_ngcontent-%COMP%]{text-align:center;padding:1rem .5rem 1.5rem;position:relative;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;margin-bottom:1rem}.intro-text-container[_ngcontent-%COMP%]::before{content:"";background-image:url(step-count-banner.524deb0d0be50e61b947.png);background-size:cover;position:absolute;top:0;right:0;bottom:0;left:0;opacity:.5}.intro-text[_ngcontent-%COMP%]{color:var(--solos-color-a);position:relative}.start-button[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-align:center;align-items:center;flex-basis:90%;margin-right:5px}.settings-button[_ngcontent-%COMP%]{flex-basis:10%;margin-left:5px;--border-width:1px;--border-style:solid;--border-color:var(--solos-color-d)}.footer-padding[_ngcontent-%COMP%]{height:calc(env(safe-area-inset-bottom));background:var(--solos-color-b)}.detail-container-portrait[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center}.item[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-flex:1;flex:1}.item-title[_ngcontent-%COMP%]{color:var(--solos-color-a);text-align:center;text-transform:uppercase;margin:40px 0 0;font-size:26px}.item-value[_ngcontent-%COMP%]{color:var(--solos-color-d);text-align:center;font-size:55px;margin:20px 0 0}'],[".detail-container-landscape[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center}"]],data:{}});function P(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,10,"div",[["class","detail-container-portrait"]],null,null,null,null,null)),(n()(),l.Cb(1,0,null,null,2,"div",[["class","item-title"]],null,null,null,null,null)),(n()(),l.Vb(2,null,[" "," "])),l.Pb(131072,g.j,[g.k,l.j]),(n()(),l.Cb(4,0,null,null,1,"div",[["class","item-value"]],null,null,null,null,null)),(n()(),l.Vb(-1,null,[" 00:00 "])),(n()(),l.Cb(6,0,null,null,2,"div",[["class","item-title"]],null,null,null,null,null)),(n()(),l.Vb(7,null,[" "," "])),l.Pb(131072,g.j,[g.k,l.j]),(n()(),l.Cb(9,0,null,null,1,"div",[["class","item-value"]],null,null,null,null,null)),(n()(),l.Vb(-1,null,[" 0 "]))],null,(function(n,t){n(t,2,0,l.Wb(t,2,0,l.Ob(t,3).transform("step-count.time"))),n(t,7,0,l.Wb(t,7,0,l.Ob(t,8).transform("step-count.step-count")))}))}function M(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,12,"div",[["class","detail-container-landscape"]],null,null,null,null,null)),(n()(),l.Cb(1,0,null,null,5,"div",[["class","item"]],null,null,null,null,null)),(n()(),l.Cb(2,0,null,null,2,"div",[["class","item-title"]],null,null,null,null,null)),(n()(),l.Vb(3,null,[" "," "])),l.Pb(131072,g.j,[g.k,l.j]),(n()(),l.Cb(5,0,null,null,1,"div",[["class","item-value"]],null,null,null,null,null)),(n()(),l.Vb(-1,null,[" 00:00 "])),(n()(),l.Cb(7,0,null,null,5,"div",[["class","item"]],null,null,null,null,null)),(n()(),l.Cb(8,0,null,null,2,"div",[["class","item-title"]],null,null,null,null,null)),(n()(),l.Vb(9,null,[" "," "])),l.Pb(131072,g.j,[g.k,l.j]),(n()(),l.Cb(11,0,null,null,1,"div",[["class","item-value"]],null,null,null,null,null)),(n()(),l.Vb(-1,null,[" 0 "]))],null,(function(n,t){n(t,3,0,l.Wb(t,3,0,l.Ob(t,4).transform("step-count.time"))),n(t,9,0,l.Wb(t,9,0,l.Ob(t,10).transform("step-count.step-count")))}))}function S(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,1,"app-music-bar",[],null,null,null,m.b,m.a)),l.Bb(1,245760,null,0,v.a,[r.Jb,r.f,k.k,l.F,y.a],null,null)],(function(n,t){n(t,1,0)}),null)}function A(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,12,"ion-header",[["style","background: var(--solos-color-b);"]],null,null,null,x.Q,x.i)),l.Bb(1,49152,null,0,r.D,[l.j,l.p,l.F],null,null),(n()(),l.Cb(2,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,x.ob,x.G)),l.Bb(3,49152,null,0,r.Db,[l.j,l.p,l.F],{mode:[0,"mode"]},null),(n()(),l.Cb(4,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,x.L,x.d)),l.Bb(5,49152,null,0,r.n,[l.j,l.p,l.F],null,null),(n()(),l.Cb(6,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(n,t,e){var o=!0;return"click"===t&&(o=!1!==l.Ob(n,8).onClick(e)&&o),o}),x.J,x.b)),l.Bb(7,49152,null,0,r.i,[l.j,l.p,l.F],{text:[0,"text"]},null),l.Bb(8,16384,null,0,r.j,[[2,r.jb],r.Jb],null,null),(n()(),l.Cb(9,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,x.mb,x.E)),l.Bb(10,49152,null,0,r.Bb,[l.j,l.p,l.F],null,null),(n()(),l.Vb(11,0,["",""])),l.Pb(131072,g.j,[g.k,l.j]),(n()(),l.Cb(13,0,null,null,10,"ion-content",[["class","app-background"]],null,null,null,x.O,x.g)),l.Bb(14,49152,null,0,r.w,[l.j,l.p,l.F],null,null),(n()(),l.Cb(15,0,null,0,8,"div",[["class","main-container"]],null,null,null,null,null)),(n()(),l.Cb(16,0,null,null,3,"div",[["class","intro-text-container"]],null,null,null,null,null)),(n()(),l.Cb(17,0,null,null,2,"div",[["class","intro-text"]],null,null,null,null,null)),(n()(),l.Vb(18,null,["",""])),l.Pb(131072,g.j,[g.k,l.j]),(n()(),l.rb(16777216,null,null,1,null,P)),l.Bb(21,16384,null,0,C.l,[l.X,l.T],{ngIf:[0,"ngIf"]},null),(n()(),l.rb(16777216,null,null,1,null,M)),l.Bb(23,16384,null,0,C.l,[l.X,l.T],{ngIf:[0,"ngIf"]},null),(n()(),l.Cb(24,0,null,null,14,"ion-footer",[],null,null,null,x.P,x.h)),l.Bb(25,49152,null,0,r.B,[l.j,l.p,l.F],null,null),(n()(),l.Cb(26,0,null,0,8,"div",[["class","footer-buttons"]],null,null,null,null,null)),(n()(),l.Cb(27,0,null,null,3,"ion-button",[["class","start-button"],["color","solos-color-a"],["shape","round"]],null,[[null,"click"]],(function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.startTraining()&&l),l}),x.K,x.c)),l.Bb(28,49152,null,0,r.m,[l.j,l.p,l.F],{color:[0,"color"],shape:[1,"shape"]},null),(n()(),l.Vb(29,0,["",""])),l.Pb(131072,g.j,[g.k,l.j]),(n()(),l.Cb(31,0,null,null,3,"ion-button",[["class","settings-button"],["color","solos-color-b-1"],["shape","round"]],null,[[null,"click"]],(function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.settingsBtnClicked()&&l),l}),x.K,x.c)),l.Bb(32,49152,null,0,r.m,[l.j,l.p,l.F],{color:[0,"color"],shape:[1,"shape"]},null),(n()(),l.Cb(33,0,null,0,1,"ion-icon",[["name","settings"],["slot","icon-only"]],null,null,null,x.R,x.j)),l.Bb(34,49152,null,0,r.E,[l.j,l.p,l.F],{name:[0,"name"]},null),(n()(),l.Cb(35,0,null,0,2,"div",[],null,null,null,null,null)),(n()(),l.rb(16777216,null,null,1,null,S)),l.Bb(37,16384,null,0,C.l,[l.X,l.T],{ngIf:[0,"ngIf"]},null),(n()(),l.Cb(38,0,null,0,0,"div",[["class","footer-padding"]],null,null,null,null,null))],(function(n,t){var e=t.component;n(t,3,0,"ios"),n(t,7,0,""),n(t,21,0,!e.isLandscapeMode()),n(t,23,0,e.isLandscapeMode()),n(t,28,0,"solos-color-a","round"),n(t,32,0,"solos-color-b-1","round"),n(t,34,0,"settings"),n(t,37,0,!e.isLandscapeMode())}),(function(n,t){n(t,11,0,l.Wb(t,11,0,l.Ob(t,12).transform("step-count.title"))),n(t,18,0,l.Wb(t,18,0,l.Ob(t,19).transform("step-count.intro"))),n(t,29,0,l.Wb(t,29,0,l.Ob(t,30).transform("ai-coach.start")))}))}var T=l.yb("app-step-count",d,(function(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,1,"app-step-count",[],null,null,null,A,w)),l.Bb(1,114688,null,0,d,[r.Jb,i.a,a.a,u.e,c.c,p.a,b.b],null,null)],(function(n,t){n(t,1,0)}),null)}),{},{},[]),j=e("s7LF"),O=e("hrfs"),_=e("j1ZV"),I=e("iInd");e.d(t,"StepCountPageModuleNgFactory",(function(){return B}));var B=l.zb(h,[],(function(n){return l.Lb([l.Mb(512,l.m,l.kb,[[8,[f.a,T]],[3,l.m],l.D]),l.Mb(4608,C.n,C.m,[l.z,[2,C.z]]),l.Mb(4608,j.j,j.j,[]),l.Mb(4608,r.c,r.c,[l.F,l.g]),l.Mb(4608,r.Ib,r.Ib,[r.c,l.m,l.w]),l.Mb(4608,r.Nb,r.Nb,[r.c,l.m,l.w]),l.Mb(4608,g.g,g.f,[]),l.Mb(4608,g.c,g.e,[]),l.Mb(4608,g.i,g.d,[]),l.Mb(4608,g.b,g.a,[]),l.Mb(4608,g.k,g.k,[g.l,g.g,g.c,g.i,g.b,g.m,g.n]),l.Mb(1073742336,C.b,C.b,[]),l.Mb(1073742336,j.i,j.i,[]),l.Mb(1073742336,j.b,j.b,[]),l.Mb(1073742336,r.Fb,r.Fb,[]),l.Mb(1073742336,O.b,O.b,[]),l.Mb(1073742336,g.h,g.h,[]),l.Mb(1073742336,_.a,_.a,[]),l.Mb(1073742336,I.o,I.o,[[2,I.t],[2,I.m]]),l.Mb(1073742336,h,h,[]),l.Mb(256,g.n,void 0,[]),l.Mb(256,g.m,void 0,[]),l.Mb(1024,I.k,(function(){return[[{path:"",component:d}]]}),[])])}))},Opd2:function(n,t,e){"use strict";var l=e("8Y7J"),o=e("SVse"),r=e("oBZk"),i=e("ZZ/e"),a=e("TSSN");e("/rTQ"),e("v8bm"),e("gPPD"),e.d(t,"a",(function(){return s})),e.d(t,"b",(function(){return h}));var s=l.Ab({encapsulation:0,styles:[[".buttons-last-slot[_ngcontent-%COMP%]{margin-right:0}.song-name[_ngcontent-%COMP%]{white-space:nowrap;overflow:hidden;text-overflow:ellipsis;margin-top:auto;margin-bottom:auto;margin-left:10px;color:var(--solos-color-a);font-size:14px;font-weight:300;line-height:16px}.song-icon[_ngcontent-%COMP%]{margin-top:auto;margin-bottom:auto;width:28px;height:28px;-o-object-fit:contain;object-fit:contain}.no-song[_ngcontent-%COMP%]{overflow:hidden;margin-top:auto;margin-bottom:auto;margin-left:10px;height:16px;color:var(--solos-color-a);font-size:14px;font-weight:300;line-height:16px}.no-song-icon[_ngcontent-%COMP%]{background:var(--solos-color-c);color:var(--solos-color-b);display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;border-radius:20%;margin-top:auto;margin-bottom:auto;width:22px;height:22px;padding:3px;min-width:22px}.progress[_ngcontent-%COMP%]{display:-webkit-box;display:flex;left:0;right:0;top:0;position:absolute;height:6px;border-style:solid;border-color:var(--solos-color-a);border-width:1px}.left-panel[_ngcontent-%COMP%]{display:-webkit-box;display:flex;margin-left:4px;overflow:hidden}.full-width[_ngcontent-%COMP%]{width:100%}.progress-inside[_ngcontent-%COMP%]{background:var(--solos-color-a);border-style:solid;border-color:var(--solos-color-b);border-width:1px}"]],data:{}});function u(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,4,"div",[["class","progress"]],null,null,null,null,null)),(n()(),l.Cb(1,0,null,null,3,"div",[["class","progress-inside"]],null,null,null,null,null)),l.Sb(512,null,o.w,o.x,[l.p,l.y,l.L]),l.Bb(3,278528,null,0,o.o,[o.w],{ngStyle:[0,"ngStyle"]},null),l.Qb(4,{"flex-basis":0})],(function(n,t){var e=n(t,4,0,t.component.processBarValue+"%");n(t,3,0,e)}),null)}function c(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,6,"div",[["class","left-panel"],["slot","start"],["style","margin-top: 4px;"]],null,[[null,"click"]],(function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.openMusicPlayer()&&l),l}),null,null)),(n()(),l.Cb(1,0,null,null,1,"ion-img",[["class","song-icon"]],null,null,null,r.S,r.k)),l.Bb(2,49152,null,0,i.F,[l.j,l.p,l.F],{src:[0,"src"]},null),(n()(),l.Cb(3,0,null,null,3,"p",[["class","song-name"]],null,null,null,null,null)),(n()(),l.Vb(4,null,[" ",""])),(n()(),l.Cb(5,0,null,null,0,"br",[],null,null,null,null,null)),(n()(),l.Vb(6,null,[" "," "]))],(function(n,t){n(t,2,0,t.component.iconPath)}),(function(n,t){var e=t.component;n(t,4,0,e.currentTrack),n(t,6,0,e.currentArtist)}))}function p(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,13,"ion-buttons",[["slot","end"],["style","margin-top: 4px;"]],null,null,null,r.L,r.d)),l.Bb(1,49152,null,0,i.n,[l.j,l.p,l.F],null,null),(n()(),l.Cb(2,0,null,0,3,"ion-button",[["clear",""],["icon-only",""],["ion-button",""]],null,[[null,"click"]],(function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.skipBackwardPressed()&&l),l}),r.K,r.c)),l.Bb(3,49152,null,0,i.m,[l.j,l.p,l.F],{disabled:[0,"disabled"]},null),(n()(),l.Cb(4,0,null,0,1,"ion-icon",[["color","solos-color-a"],["name","skip-backward"]],null,null,null,r.R,r.j)),l.Bb(5,49152,null,0,i.E,[l.j,l.p,l.F],{color:[0,"color"],name:[1,"name"]},null),(n()(),l.Cb(6,0,null,0,3,"ion-button",[["clear",""],["icon-only",""],["ion-button",""]],null,[[null,"click"]],(function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.playPausePressed()&&l),l}),r.K,r.c)),l.Bb(7,49152,null,0,i.m,[l.j,l.p,l.F],null,null),(n()(),l.Cb(8,0,null,0,1,"ion-icon",[["color","solos-color-a"]],null,null,null,r.R,r.j)),l.Bb(9,49152,null,0,i.E,[l.j,l.p,l.F],{color:[0,"color"],name:[1,"name"]},null),(n()(),l.Cb(10,0,null,0,3,"ion-button",[["clear",""],["icon-only",""],["ion-button",""]],null,[[null,"click"]],(function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.skipForwardPressed()&&l),l}),r.K,r.c)),l.Bb(11,49152,null,0,i.m,[l.j,l.p,l.F],null,null),(n()(),l.Cb(12,0,null,0,1,"ion-icon",[["color","solos-color-a"],["name","skip-forward"]],null,null,null,r.R,r.j)),l.Bb(13,49152,null,0,i.E,[l.j,l.p,l.F],{color:[0,"color"],name:[1,"name"]},null)],(function(n,t){var e=t.component;n(t,3,0,!e.isPrevSupport),n(t,5,0,"solos-color-a","skip-backward"),n(t,9,0,"solos-color-a",e.isPlaying?"pause":"play"),n(t,13,0,"solos-color-a","skip-forward")}),null)}function b(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,1,"ion-icon",[["class","no-song-icon"],["name","musical-note"]],null,null,null,r.R,r.j)),l.Bb(1,49152,null,0,i.E,[l.j,l.p,l.F],{name:[0,"name"]},null)],(function(n,t){n(t,1,0,"musical-note")}),null)}function d(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,5,"div",[["class","left-panel full-width"],["slot","start"]],null,[[null,"click"]],(function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.goToMusicSelectPage()&&l),l}),null,null)),(n()(),l.rb(16777216,null,null,1,null,b)),l.Bb(2,16384,null,0,o.l,[l.X,l.T],{ngIf:[0,"ngIf"]},null),(n()(),l.Cb(3,0,null,null,2,"p",[["class","no-song"]],null,null,null,null,null)),(n()(),l.Vb(4,null,[" "," "])),l.Pb(131072,a.j,[a.k,l.j])],(function(n,t){n(t,2,0,!t.component.isMusicConnected)}),(function(n,t){n(t,4,0,l.Wb(t,4,0,l.Ob(t,5).transform("music-player.music-off")))}))}function h(n){return l.Xb(0,[(n()(),l.Cb(0,0,null,null,9,"ion-toolbar",[["class","settings-background"],["style","padding:0 16px;"]],null,null,null,r.ob,r.G)),l.Bb(1,49152,null,0,i.Db,[l.j,l.p,l.F],null,null),(n()(),l.rb(16777216,null,0,1,null,u)),l.Bb(3,16384,null,0,o.l,[l.X,l.T],{ngIf:[0,"ngIf"]},null),(n()(),l.rb(16777216,null,0,1,null,c)),l.Bb(5,16384,null,0,o.l,[l.X,l.T],{ngIf:[0,"ngIf"]},null),(n()(),l.rb(16777216,null,0,1,null,p)),l.Bb(7,16384,null,0,o.l,[l.X,l.T],{ngIf:[0,"ngIf"]},null),(n()(),l.rb(16777216,null,0,1,null,d)),l.Bb(9,16384,null,0,o.l,[l.X,l.T],{ngIf:[0,"ngIf"]},null)],(function(n,t){var e=t.component;n(t,3,0,e.isMusicConnected),n(t,5,0,e.isMusicConnected),n(t,7,0,e.isMusicConnected),n(t,9,0,!e.isMusicConnected)}),null)}},gPPD:function(n,t,e){"use strict";e.d(t,"a",(function(){return c}));var l=e("mrSG"),o=e("ZZ/e"),r=e("nSqr"),i=e("OUMn"),a=e("+UoT"),s=e("8Y7J"),u=e("TSSN"),c=function(){var n=function n(t,e,o,r,i){var a=this;_classCallCheck(this,n),this.translate=t,this.appLauncher=e,this.popupCtrl=o,this.appAvailability=r,this.platform=i,this.askUserToInstallApp=function(n,t){return l.b(a,void 0,void 0,regeneratorRuntime.mark((function e(){var l,o,r,i;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return l=this.translate.instant("global.app-not-installed"),o=this.translate.instant("global.app-not-installed-install-now",{value:n}),r=this.translate.instant("global.no"),i=this.translate.instant("global.yes"),e.next=3,this.popupCtrl.presentAlertController({header:l,message:o,buttons:[{text:r,role:"cancel"},{text:i,handler:t}]},!0);case 3:case"end":return e.stop()}}),e,this)})))},this.openiOSApp=function(n,t,e){return l.b(a,void 0,void 0,regeneratorRuntime.mark((function l(){var o,r=this;return regeneratorRuntime.wrap((function(l){for(;;)switch(l.prev=l.next){case 0:return o=!1,l.prev=1,l.next=4,this.appAvailability.check(t);case 4:if(l.t0=o=l.sent,!l.t0){l.next=8;break}return l.next=8,this.appLauncher.launch({uri:t});case 8:l.next=13;break;case 10:l.prev=10,l.t1=l.catch(1),console.log(l.t1);case 13:return l.prev=13,o||this.askUserToInstallApp(n,(function(){r.appLauncher.launch({uri:e})})),l.finish(13);case 16:case"end":return l.stop()}}),l,this,[[1,10,13,16]])})))},this.openAndroidApp=function(n,t,e){return l.b(a,void 0,void 0,regeneratorRuntime.mark((function l(){var o,r=this;return regeneratorRuntime.wrap((function(l){for(;;)switch(l.prev=l.next){case 0:return o=!1,l.prev=1,l.next=4,this.appLauncher.canLaunch({packageName:t});case 4:if(l.t0=o=l.sent,!l.t0){l.next=8;break}return l.next=8,this.appLauncher.launch({packageName:t});case 8:l.next=13;break;case 10:l.prev=10,l.t1=l.catch(1),console.log(l.t1);case 13:return l.prev=13,o||this.askUserToInstallApp(n,(function(){r.appLauncher.launch({uri:e})})),l.finish(13);case 16:case"end":return l.stop()}}),l,this,[[1,10,13,16]])})))},this.openAppleMapApp=function(){return l.b(a,void 0,void 0,regeneratorRuntime.mark((function n(){var t;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,t=this.translate.instant("global.map"),n.t0=this.platform.is("ios"),!n.t0){n.next=6;break}return n.next=6,this.openiOSApp(t,"http://maps.apple.com/?address=","");case 6:n.next=11;break;case 8:n.prev=8,n.t1=n.catch(0),console.log(n.t1.message);case 11:case"end":return n.stop()}}),n,this,[[0,8]])})))},this.openAppleMusicApp=function(){return l.b(a,void 0,void 0,regeneratorRuntime.mark((function n(){var t;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,t=this.translate.instant("global.music"),n.t0=this.platform.is("ios"),!n.t0){n.next=6;break}return n.next=6,this.openiOSApp(t,"https://music.apple.com","");case 6:n.next=11;break;case 8:n.prev=8,n.t1=n.catch(0),console.log(n.t1.message);case 11:case"end":return n.stop()}}),n,this,[[0,8]])})))},this.openSpotifyApp=function(){return l.b(a,void 0,void 0,regeneratorRuntime.mark((function n(){var t;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,t=this.translate.instant("global.spotify"),!this.platform.is("ios")){n.next=7;break}return n.next=5,this.openiOSApp(t,"spotify:","https://apps.apple.com/app/spotify-new-music-and-podcasts/id324684580?ls=1&mt=8");case 5:n.next=11;break;case 7:if(n.t0=this.platform.is("android"),!n.t0){n.next=11;break}return n.next=11,this.openAndroidApp(t,"com.spotify.music","https://play.google.com/store/apps/details?id=com.spotify.music");case 11:n.next=16;break;case 13:n.prev=13,n.t1=n.catch(0),console.log(n.t1.message);case 16:case"end":return n.stop()}}),n,this,[[0,13]])})))},this.openZelloApp=function(){try{var n=a.translate.instant("global.zello");a.platform.is("ios")?a.openiOSApp(n,"zello://","https://itunes.apple.com/app/zello-walkie-talkie/id508231856?ls=1&mt=8"):a.platform.is("android")&&a.openAndroidApp(n,"com.loudtalks","https://play.google.com/store/apps/details?id=com.loudtalks")}catch(t){console.log(t.message)}},this.openGoogleTranslateApp=function(){return l.b(a,void 0,void 0,regeneratorRuntime.mark((function n(){var t;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,t=this.translate.instant("global.google-translate"),!this.platform.is("ios")){n.next=7;break}return n.next=5,this.openiOSApp(t,"googletranslate://","https://itunes.apple.com/us/app/google-translate/id414706506?mt=8");case 5:n.next=11;break;case 7:if(n.t0=this.platform.is("android"),!n.t0){n.next=11;break}return n.next=11,this.openAndroidApp(t,"com.google.android.apps.translate","https://play.google.com/store/apps/details?id=com.google.android.apps.translate");case 11:n.next=16;break;case 13:n.prev=13,n.t1=n.catch(0),console.log(n.t1.message);case 16:case"end":return n.stop()}}),n,this,[[0,13]])})))},this.openGoogleMapApp=function(){return l.b(a,void 0,void 0,regeneratorRuntime.mark((function n(){var t;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:if(n.prev=0,t=this.translate.instant("global.google-map"),!this.platform.is("ios")){n.next=7;break}return n.next=5,this.openiOSApp(t,"comgooglemaps://","https://itunes.apple.com/us/app/apple-store/id585027354?pt=9008&amp;ct=help-center-mg-promo-groupa-appicon-6291838&amp;mt=8");case 5:n.next=11;break;case 7:if(n.t0=this.platform.is("android"),!n.t0){n.next=11;break}return n.next=11,this.openAndroidApp(t,"com.google.android.apps.maps","https://play.google.com/store/apps/details?id=com.google.android.apps.maps");case 11:n.next=16;break;case 13:n.prev=13,n.t1=n.catch(0),console.log(n.t1.message);case 16:case"end":return n.stop()}}),n,this,[[0,13]])})))},this.openBrowser=function(n){return l.b(a,void 0,void 0,regeneratorRuntime.mark((function t(){var e,o,r=this;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(t.prev=0,!this.platform.is("ios")){t.next=6;break}return t.next=4,this.appLauncher.launch({uri:n});case 4:t.next=17;break;case 6:if(!this.platform.is("android")){t.next=17;break}return e="com.android.chrome",o="https://play.google.com/store/apps/details?id=com.android.chrome",t.next=10,this.appLauncher.canLaunch({packageName:e});case 10:if(!t.sent){t.next=15;break}return t.next=13,this.appLauncher.launch({uri:n,packageName:e});case 13:t.next=17;break;case 15:return t.next=17,this.askUserToInstallApp(e,(function(){return l.b(r,void 0,void 0,regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:return n.prev=0,n.next=3,this.appLauncher.launch({uri:o});case 3:n.next=8;break;case 5:n.prev=5,n.t0=n.catch(0),console.log(n.t0.message);case 8:case"end":return n.stop()}}),n,this,[[0,5]])})))}));case 17:t.next=22;break;case 19:t.prev=19,t.t0=t.catch(0),console.log(t.t0.message);case 22:case"end":return t.stop()}}),t,this,[[0,19]])})))}};return n.ngInjectableDef=s.ac({factory:function(){return new n(s.bc(u.k),s.bc(r.a),s.bc(i.a),s.bc(a.a),s.bc(o.Mb))},token:n,providedIn:"root"}),n}()},j1ZV:function(n,t,e){"use strict";e.d(t,"a",(function(){return l}));var l=function n(){_classCallCheck(this,n)}}}]);