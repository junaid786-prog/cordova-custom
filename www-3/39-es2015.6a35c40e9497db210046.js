(window.webpackJsonp=window.webpackJsonp||[]).push([[39],{QErx:function(l,n,t){"use strict";t.r(n);var e=t("8Y7J"),i=t("mrSG"),s=t("ZZ/e"),o=t("3Cwc"),a=t("OUMn"),u=t("VjBM"),r=t("4SHv"),c=t("8qa8"),b=t("Z18M"),d=t("LRfq"),h=t("h5tO");const g=3e3,p=130,m=150,C=140,v=125,f=145,k=135,O=120,w=140,T=125,y=90,j=110,B=95,W=10,M=20,P=5,S=.85,x=1.1;class F{constructor(l,n,t,e,s,a,r,b,F,D){this.navCtrl=l,this.trainingSettings=n,this.translate=t,this.popupController=e,this.coachTraining=s,this.strava=a,this.zone=r,this.audioPlayer=b,this.backend=F,this.activityStateManager=D,this.isEasy=!0,this.isNormal=!1,this.isExpert=!1,this.isCustom=!1,this.warmUpTimeTxt="",this.warmUpTime=0,this.warmUpCadenceTxt="",this.warmUpCadence=0,this.fastWalkTimeTxt="",this.fastWalkTime=0,this.fastWalkCadenceTxt="",this.fastWalkCadence=0,this.coolDownTimeTxt="",this.coolDownTime=0,this.coolDownCadenceTxt="",this.coolDownCadence=0,this.intervalTxt="",this.interval=0,this.isMovingTimeOn=!1,this.isDistanceOn=!1,this.isAutoPause=!1,this.isBGMOn=!1,this.isUploadStrava=!1,this.isStravaLoggedin=!1,this.beepPlayerTimout=null,this.viewDidEnter=!1,this.warmUpCadenceOpts=[],this.warmUpTimesOpts=[],this.fastWalkCadenceOpts=[],this.fastWalkTimeOpts=[],this.coolDownCadenceOpts=[],this.coolDownTimeOpts=[],this.customTimeOpts=[1,5,10,15,20,25,30,35,40,45,50,55,60],this.setupLevelOptions=()=>{let l=this.getUserAge();0==l&&(l=40),l<30?(this.warmUpCadenceOpts=[Math.round(p*S),p,Math.round(p*x)],this.warmUpTimesOpts=[W,W,W],this.fastWalkCadenceOpts=[Math.round(m*S),m,Math.round(m*x)],this.fastWalkTimeOpts=[Math.round(M),M,Math.round(M)],this.coolDownCadenceOpts=[Math.round(C*S),C,Math.round(C*x)],this.coolDownTimeOpts=[P,P,P]):l<45?(this.warmUpCadenceOpts=[Math.round(v*S),v,Math.round(v*x)],this.warmUpTimesOpts=[W,W,W],this.fastWalkCadenceOpts=[Math.round(f*S),f,Math.round(f*x)],this.fastWalkTimeOpts=[Math.round(M),M,Math.round(M)],this.coolDownCadenceOpts=[Math.round(k*S),k,Math.round(k*x)],this.coolDownTimeOpts=[P,P,P]):l<60?(this.warmUpCadenceOpts=[Math.round(O*S),O,Math.round(O*x)],this.warmUpTimesOpts=[W,W,W],this.fastWalkCadenceOpts=[Math.round(w*S),w,Math.round(w*x)],this.fastWalkTimeOpts=[Math.round(M),M,Math.round(M)],this.coolDownCadenceOpts=[Math.round(T*S),T,Math.round(T*x)],this.coolDownTimeOpts=[P,P,P]):(this.warmUpCadenceOpts=[Math.round(y*S),y,Math.round(y*x)],this.warmUpTimesOpts=[W,W,W],this.fastWalkCadenceOpts=[Math.round(j*S),j,Math.round(j*x)],this.fastWalkTimeOpts=[Math.round(M),M,Math.round(M)],this.coolDownCadenceOpts=[Math.round(B*S),B,Math.round(B*x)],this.coolDownTimeOpts=[P,P,P])},this.getUserAge=()=>{const l=this.backend.getLoginUser();return null!=l?this.backend.getAge(l):0},this.setLevelSelected=l=>i.b(this,void 0,void 0,(function*(){try{let n,t,e,i,s,a;switch(this.isEasy=!1,this.isNormal=!1,this.isExpert=!1,this.isCustom=!1,l){case o.d.Easy:this.isEasy=!0,n=this.warmUpTimesOpts[0],t=this.warmUpCadenceOpts[0],e=this.fastWalkTimeOpts[0],i=this.fastWalkCadenceOpts[0],s=this.coolDownTimeOpts[0],a=this.coolDownCadenceOpts[0];break;case o.d.Normal:this.isNormal=!0,n=this.warmUpTimesOpts[1],t=this.warmUpCadenceOpts[1],e=this.fastWalkTimeOpts[1],i=this.fastWalkCadenceOpts[1],s=this.coolDownTimeOpts[1],a=this.coolDownCadenceOpts[1];break;case o.d.Expert:this.isExpert=!0,n=this.warmUpTimesOpts[2],t=this.warmUpCadenceOpts[2],e=this.fastWalkTimeOpts[2],i=this.fastWalkCadenceOpts[2],s=this.coolDownTimeOpts[2],a=this.coolDownCadenceOpts[2];break;case o.d.Custom:this.isCustom=!0,n=this.trainingSettings.getFastWalkWarmUpTime(),t=this.trainingSettings.getFastWalkWarmUpCadence(),e=this.trainingSettings.getFastWalkFastWalkTime(),i=this.trainingSettings.getFastWalkFastWalkCadence(),s=this.trainingSettings.getFastWalkCoolDownTime(),a=this.trainingSettings.getFastWalkCoolDownCadence(),0!=n&&0!=t&&0!=e&&0!=i&&0!=s&&0!=a||(n=this.warmUpTimesOpts[2],t=this.warmUpCadenceOpts[2],e=this.fastWalkTimeOpts[2],i=this.fastWalkCadenceOpts[2],s=this.coolDownTimeOpts[2],a=this.coolDownCadenceOpts[2])}yield this.trainingSettings.setFastWalkLevelSelected(l),yield this.setWarmUpTime(n,l==o.d.Custom),yield this.setWarmUpCadence(t,l==o.d.Custom),yield this.setFastWalkTime(e,l==o.d.Custom),yield this.setFastWalkCadence(i,l==o.d.Custom),yield this.setCoolDownTime(s,l==o.d.Custom),yield this.setCoolDownCadence(a,l==o.d.Custom)}catch(n){console.log(n)}})),this.turnEasy=()=>{try{this.setLevelSelected(o.d.Easy)}catch(l){console.log(l)}},this.turnNormal=()=>{try{this.setLevelSelected(o.d.Normal)}catch(l){console.log(l)}},this.turnExpert=()=>{try{this.setLevelSelected(o.d.Expert)}catch(l){console.log(l)}},this.turnCustom=()=>{try{this.setLevelSelected(o.d.Custom)}catch(l){console.log(l)}},this.showLevelPopupController=(l,n,t)=>{let e=[];e.push({name:"time",options:this.popupController.getColumnOptions(this.customTimeOpts),selectedIndex:this.popupController.getSelectedIndex(this.customTimeOpts,String(l))}),e.push({name:"timeunit",options:this.popupController.getColumnOptions([this.mins]),selectedIndex:0});const s=Array.from(new Array(190),(l,n)=>n+30);e.push({name:"spm",options:this.popupController.getColumnOptions(s),selectedIndex:this.popupController.getSelectedIndex(s,String(n))}),e.push({name:"spmunit",options:this.popupController.getColumnOptions([this.spm]),selectedIndex:0}),this.popupController.presentNativePickerController(e,l=>i.b(this,void 0,void 0,(function*(){try{const n=Number(l[0].description),e=Number(l[2].description);t(n,e)}catch(n){yield this.presentAlert(n)}})))},this.warmUpClicked=()=>{this.showLevelPopupController(this.warmUpTime,this.warmUpCadence,(l,n)=>i.b(this,void 0,void 0,(function*(){try{yield this.setWarmUpTime(l),yield this.setWarmUpCadence(n),yield this.setLevelSelected(o.d.Custom)}catch(t){this.presentAlert(t)}})))},this.fastWalkClicked=()=>{this.showLevelPopupController(this.fastWalkTime,this.fastWalkCadence,(l,n)=>i.b(this,void 0,void 0,(function*(){try{yield this.setFastWalkTime(l),yield this.setFastWalkCadence(n),yield this.setLevelSelected(o.d.Custom)}catch(t){this.presentAlert(t)}})))},this.coolDownClicked=()=>{this.showLevelPopupController(this.coolDownTime,this.coolDownCadence,(l,n)=>i.b(this,void 0,void 0,(function*(){try{yield this.setCoolDownTime(l),yield this.setCoolDownCadence(n),yield this.setLevelSelected(o.d.Custom)}catch(t){this.presentAlert(t)}})))},this.setWarmUpTime=(l,n=!0)=>i.b(this,void 0,void 0,(function*(){try{this.zone.run(()=>i.b(this,void 0,void 0,(function*(){try{this.warmUpTime=l,this.warmUpTimeTxt=1!=this.warmUpTime?String(this.warmUpTime)+" "+this.mins:String(this.warmUpTime)+" "+this.min}catch(n){console.log(n)}}))),n&&(yield this.trainingSettings.setFastWalkWarmUpTime(this.warmUpTime))}catch(t){console.log(t)}})),this.setFastWalkTime=(l,n=!0)=>i.b(this,void 0,void 0,(function*(){try{this.zone.run(()=>i.b(this,void 0,void 0,(function*(){try{this.fastWalkTime=l,this.fastWalkTimeTxt=1!=this.fastWalkTime?String(this.fastWalkTime)+" "+this.mins:String(this.fastWalkTime)+" "+this.min}catch(n){console.log(n)}}))),n&&(yield this.trainingSettings.setFastWalkFastWalkTime(this.fastWalkTime))}catch(t){console.log(t)}})),this.setCoolDownTime=(l,n=!0)=>i.b(this,void 0,void 0,(function*(){try{this.zone.run(()=>i.b(this,void 0,void 0,(function*(){try{this.coolDownTime=l,this.coolDownTimeTxt=1!=this.coolDownTime?String(this.coolDownTime)+" "+this.mins:String(this.coolDownTime)+" "+this.min}catch(n){console.log(n)}}))),n&&(yield this.trainingSettings.setFastWalkCoolDownTime(this.coolDownTime))}catch(t){console.log(t)}})),this.setWarmUpCadence=(l,n=!0)=>i.b(this,void 0,void 0,(function*(){try{this.zone.run(()=>i.b(this,void 0,void 0,(function*(){try{this.warmUpCadence=l,this.warmUpCadenceTxt=String(this.warmUpCadence)}catch(n){console.log(n)}}))),n&&(yield this.trainingSettings.setFastWalkWarmUpCadence(this.warmUpCadence))}catch(t){console.log(t)}})),this.setFastWalkCadence=(l,n=!0)=>i.b(this,void 0,void 0,(function*(){try{this.zone.run(()=>i.b(this,void 0,void 0,(function*(){try{this.fastWalkCadence=l,this.fastWalkCadenceTxt=String(this.fastWalkCadence)}catch(n){console.log(n)}}))),n&&(yield this.trainingSettings.setFastWalkFastWalkCadence(this.fastWalkCadence))}catch(t){console.log(t)}})),this.setCoolDownCadence=(l,n=!0)=>i.b(this,void 0,void 0,(function*(){try{this.zone.run(()=>i.b(this,void 0,void 0,(function*(){try{this.coolDownCadence=l,this.coolDownCadenceTxt=String(this.coolDownCadence)}catch(n){console.log(n)}}))),n&&(yield this.trainingSettings.setFastWalkCoolDownCadence(this.coolDownCadence))}catch(t){console.log(t)}})),this.clearPlayBeepSoundTimeout=()=>{null!=this.beepPlayerTimout&&(clearTimeout(this.beepPlayerTimout),this.beepPlayerTimout=null)},this.playBeepSound=()=>{this.clearPlayBeepSoundTimeout(),this.audioPlayer.setVolume(c.e,this.beepVolume),this.audioPlayer.playMusic(!1,c.e,"",this.warmUpCadence),this.beepPlayerTimout=setTimeout(()=>{this.stopBeepSound()},g)},this.stopBeepSound=()=>{null!=this.beepPlayerTimout&&(this.clearPlayBeepSoundTimeout(),this.audioPlayer.stopMusic(c.e))},this.beepVolumeDidChange=l=>i.b(this,void 0,void 0,(function*(){try{if(!this.viewDidEnter)return;yield this.trainingSettings.setBeepVolume(this.beepVolume),this.playBeepSound()}catch(l){console.log(l)}})),this.autoPauseDidChange=l=>i.b(this,void 0,void 0,(function*(){try{if(!this.viewDidEnter)return;yield this.trainingSettings.setAutoPauseOn(this.isAutoPause)}catch(l){console.log(l)}})),this.intervalClicked=()=>i.b(this,void 0,void 0,(function*(){const l=Array.from(new Array(61),(l,n)=>n);let n=[];n.push({name:"tens",options:this.popupController.getColumnOptions(l),selectedIndex:this.popupController.getSelectedIndex(l,String(Math.floor(this.interval)))});const t=[this.translate.instant("training.mins")];n.push({name:"unit",options:this.popupController.getColumnOptions(t),selectedIndex:0}),this.popupController.presentNativePickerController(n,l=>i.b(this,void 0,void 0,(function*(){try{const n=Number(l[0].description);n>60?this.presentAlert({message:this.translate.instant("traininig.should-below-60")}):yield this.setInterval(n)}catch(n){yield this.presentAlert(n)}})))})),this.setInterval=l=>i.b(this,void 0,void 0,(function*(){try{this.zone.run(()=>i.b(this,void 0,void 0,(function*(){try{this.interval=l,this.intervalTxt=1!=this.interval?String(this.interval)+" "+this.mins:String(this.interval)+" "+this.min}catch(n){console.log(n)}}))),yield this.trainingSettings.setReminderInterval(l)}catch(n){console.log(n)}})),this.movingTimeDidChange=l=>i.b(this,void 0,void 0,(function*(){try{if(!this.viewDidEnter)return;yield this.trainingSettings.setReminderTypeMovingTimeOn(this.isMovingTimeOn)}catch(l){console.log(l)}})),this.distanceDidChange=l=>i.b(this,void 0,void 0,(function*(){try{if(!this.viewDidEnter)return;yield this.trainingSettings.setReminderTypeDistanceOn(this.isDistanceOn)}catch(l){console.log(l)}})),this.backgroundMusicDidChange=l=>i.b(this,void 0,void 0,(function*(){try{if(!this.viewDidEnter)return;yield this.trainingSettings.setBackdroundMusic(this.isBGMOn)}catch(l){console.log(l)}})),this.uploadStravaDidChange=l=>i.b(this,void 0,void 0,(function*(){try{if(!this.viewDidEnter)return;yield this.trainingSettings.setUploadStrava(this.isUploadStrava)}catch(l){console.log(l)}})),this.startTraining=()=>i.b(this,void 0,void 0,(function*(){try{const l=[...h.c,h.a.BASIC_TRAINING,h.a.CORE_TRAINING,h.a.POSTURE_MONITORING],n=this.activityStateManager.validate(l,h.a.FAT_BURN,"/fat-burn");if(n.status){this.stopBeepSound();const l=new d.b(60*(this.warmUpTime+this.fastWalkTime+this.coolDownTime),60*this.warmUpTime,this.warmUpCadence,60*this.fastWalkTime,this.fastWalkCadence,60*this.coolDownTime,this.coolDownCadence);this.coachTraining.start(u.b.FastWalk,l)&&(yield this.navCtrl.navigateForward("/training-running/fatburn"))}else yield this.popupController.presentAlertController({header:n.header,message:n.message,buttons:n.buttons},!0)}catch(l){console.log(l)}})),this.presentAlert=l=>i.b(this,void 0,void 0,(function*(){try{yield this.popupController.presentAlertController({header:this.translate.instant("global.error"),message:l.message,buttons:[{text:this.translate.instant("global.okay")}]},!0)}catch(l){console.log(l)}}))}ngOnInit(){}ionViewWillEnter(){this.setupLevelOptions(),this.min=this.translate.instant("training.min"),this.mins=this.translate.instant("training.mins"),this.spm=this.translate.instant("training.spm");const l=this.trainingSettings.getReminderInterval();this.setInterval(l),this.isMovingTimeOn=this.trainingSettings.getReminderTypeMovingTimeOn(),this.isDistanceOn=this.trainingSettings.getReminderTypeDistanceOn(),this.isAutoPause=this.trainingSettings.getAutoPauseOn(),this.isBGMOn=this.trainingSettings.getBackdroundMusic(),this.isStravaLoggedin=this.strava.isAuthorized(),this.isUploadStrava=this.trainingSettings.getUploadStrava();const n=this.trainingSettings.getFastWalkLevelSelected();this.setLevelSelected(n),this.beepVolume=this.trainingSettings.getBeepVolume()}ionViewDidEnter(){this.viewDidEnter=!0}ionViewWillLeave(){this.stopBeepSound()}}class D{}var U=t("pMnS"),V=t("oBZk"),E=t("TSSN"),I=t("SVse"),L=t("s7LF"),A=e.Ab({encapsulation:0,styles:[['.main-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column}.intro-text-container[_ngcontent-%COMP%]{text-align:center;padding:1rem .5rem 1.5rem;position:relative;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;margin-bottom:1rem}.intro-text-container[_ngcontent-%COMP%]::before{content:"";background-image:url(fat-burn-banner.a8545357f16509c819d5.png);background-size:cover;position:absolute;top:0;right:0;bottom:0;left:0;opacity:.5}.intro-text[_ngcontent-%COMP%]{color:var(--solos-color-a);position:relative}.choose-level-title[_ngcontent-%COMP%]{font-size:14px;margin:0 10px;text-align:center;color:var(--solos-color-a);text-transform:uppercase}.level-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:justify;justify-content:space-between;margin:20px}.level-selection[_ngcontent-%COMP%]{flex-basis:23%;font-size:12px;text-align:center;padding:12px 0;color:var(--solos-color-a)}.active[_ngcontent-%COMP%]{background:var(--solos-color-d);border-color:var(--solos-color-b)}.inactive[_ngcontent-%COMP%]{background:0 0;border-color:var(--solos-color-a);border-style:solid;border-width:1px}.start-button[_ngcontent-%COMP%]{width:80%}.footer-buttons[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-align:center;align-items:center;-webkit-box-pack:center;justify-content:center;padding:10px;background:var(--solos-color-b);border-width:1px 0 0;border-style:solid;border-color:var(--solos-color-a)}.footer-padding[_ngcontent-%COMP%]{height:calc(env(safe-area-inset-bottom));background:var(--solos-color-b)}.volume[_ngcontent-%COMP%]{--bar-background-active:var(--solos-color-d);--bar-background:var(--solos-color-c)}.md.spm[_ngcontent-%COMP%]{margin-left:20px}.ios.spm[_ngcontent-%COMP%]{margin-left:15px}']],data:{}});function _(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,12,"ion-header",[["style","background: var(--solos-color-b);"]],null,null,null,V.Q,V.i)),e.Bb(1,49152,null,0,s.D,[e.j,e.p,e.F],null,null),(l()(),e.Cb(2,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,V.ob,V.G)),e.Bb(3,49152,null,0,s.Db,[e.j,e.p,e.F],{mode:[0,"mode"]},null),(l()(),e.Cb(4,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,V.L,V.d)),e.Bb(5,49152,null,0,s.n,[e.j,e.p,e.F],null,null),(l()(),e.Cb(6,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(l,n,t){var i=!0;return"click"===n&&(i=!1!==e.Ob(l,8).onClick(t)&&i),i}),V.J,V.b)),e.Bb(7,49152,null,0,s.i,[e.j,e.p,e.F],{text:[0,"text"]},null),e.Bb(8,16384,null,0,s.j,[[2,s.jb],s.Jb],null,null),(l()(),e.Cb(9,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,V.mb,V.E)),e.Bb(10,49152,null,0,s.Bb,[e.j,e.p,e.F],null,null),(l()(),e.Vb(11,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(13,0,null,null,188,"ion-content",[["class","app-background"]],null,null,null,V.O,V.g)),e.Bb(14,49152,null,0,s.w,[e.j,e.p,e.F],null,null),(l()(),e.Cb(15,0,null,0,186,"div",[["class","main-container"]],null,null,null,null,null)),(l()(),e.Cb(16,0,null,null,3,"div",[["class","intro-text-container"]],null,null,null,null,null)),(l()(),e.Cb(17,0,null,null,2,"div",[["class","intro-text"]],null,null,null,null,null)),(l()(),e.Vb(18,null,[" ",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(20,0,null,null,2,"div",[["class","choose-level-title"]],null,null,null,null,null)),(l()(),e.Vb(21,null,[" "," "])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(23,0,null,null,20,"div",[["class","level-container"]],null,null,null,null,null)),(l()(),e.Cb(24,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.turnEasy()&&e),e}),null,null)),e.Sb(512,null,I.u,I.v,[e.x,e.y,e.p,e.L]),e.Bb(26,278528,null,0,I.j,[I.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),e.Vb(27,null,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(29,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.turnNormal()&&e),e}),null,null)),e.Sb(512,null,I.u,I.v,[e.x,e.y,e.p,e.L]),e.Bb(31,278528,null,0,I.j,[I.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),e.Vb(32,null,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(34,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.turnExpert()&&e),e}),null,null)),e.Sb(512,null,I.u,I.v,[e.x,e.y,e.p,e.L]),e.Bb(36,278528,null,0,I.j,[I.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),e.Vb(37,null,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(39,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.turnCustom()&&e),e}),null,null)),e.Sb(512,null,I.u,I.v,[e.x,e.y,e.p,e.L]),e.Bb(41,278528,null,0,I.j,[I.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),e.Vb(42,null,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(44,0,null,null,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,V.Y,V.r)),e.Bb(45,49152,null,0,s.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(46,0,null,0,3,"ion-label",[],null,null,null,V.X,V.p)),e.Bb(47,49152,null,0,s.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(48,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(50,0,null,null,15,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.warmUpClicked()&&e),e}),V.W,V.o)),e.Bb(51,49152,null,0,s.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"]},null),(l()(),e.Cb(52,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,V.X,V.p)),e.Bb(53,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(54,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(56,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,V.X,V.p)),e.Bb(57,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(58,0,["",""])),(l()(),e.Cb(59,0,null,0,3,"ion-label",[["class","spm"],["color","settings-left-label"]],null,null,null,V.X,V.p)),e.Bb(60,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(61,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(63,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,V.X,V.p)),e.Bb(64,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(65,0,["",""])),(l()(),e.Cb(66,0,null,null,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,V.Y,V.r)),e.Bb(67,49152,null,0,s.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(68,0,null,0,3,"ion-label",[],null,null,null,V.X,V.p)),e.Bb(69,49152,null,0,s.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(70,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(72,0,null,null,15,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.fastWalkClicked()&&e),e}),V.W,V.o)),e.Bb(73,49152,null,0,s.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"]},null),(l()(),e.Cb(74,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,V.X,V.p)),e.Bb(75,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(76,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(78,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,V.X,V.p)),e.Bb(79,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(80,0,["",""])),(l()(),e.Cb(81,0,null,0,3,"ion-label",[["class","spm"],["color","settings-left-label"]],null,null,null,V.X,V.p)),e.Bb(82,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(83,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(85,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,V.X,V.p)),e.Bb(86,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(87,0,["",""])),(l()(),e.Cb(88,0,null,null,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,V.Y,V.r)),e.Bb(89,49152,null,0,s.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(90,0,null,0,3,"ion-label",[],null,null,null,V.X,V.p)),e.Bb(91,49152,null,0,s.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(92,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(94,0,null,null,15,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.coolDownClicked()&&e),e}),V.W,V.o)),e.Bb(95,49152,null,0,s.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"]},null),(l()(),e.Cb(96,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,V.X,V.p)),e.Bb(97,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(98,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(100,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,V.X,V.p)),e.Bb(101,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(102,0,["",""])),(l()(),e.Cb(103,0,null,0,3,"ion-label",[["class","spm"],["color","settings-left-label"]],null,null,null,V.X,V.p)),e.Bb(104,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(105,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(107,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,V.X,V.p)),e.Bb(108,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(109,0,["",""])),(l()(),e.Cb(110,0,null,null,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,V.Y,V.r)),e.Bb(111,49152,null,0,s.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(112,0,null,0,3,"ion-label",[],null,null,null,V.X,V.p)),e.Bb(113,49152,null,0,s.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(114,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(116,0,null,null,12,"ion-item",[["class","settings-item"]],null,null,null,V.W,V.o)),e.Bb(117,49152,null,0,s.J,[e.j,e.p,e.F],null,null),(l()(),e.Cb(118,0,null,0,10,"ion-range",[["class","volume"],["max","10"],["min","0"],["mode","ios"],["step","1"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(l,n,t){var i=!0,s=l.component;return"ionBlur"===n&&(i=!1!==e.Ob(l,119)._handleBlurEvent(t.target)&&i),"ionChange"===n&&(i=!1!==e.Ob(l,119)._handleChangeEvent(t.target)&&i),"ionChange"===n&&(i=!1!==s.beepVolumeDidChange(t)&&i),"ngModelChange"===n&&(i=!1!==(s.beepVolume=t)&&i),i}),V.eb,V.w)),e.Bb(119,16384,null,0,s.Pb,[e.p],null,null),e.Sb(1024,null,L.c,(function(l){return[l]}),[s.Pb]),e.Bb(121,671744,null,0,L.h,[[8,null],[8,null],[8,null],[6,L.c]],{model:[0,"model"]},{update:"ngModelChange"}),e.Sb(2048,null,L.d,null,[L.h]),e.Bb(123,16384,null,0,L.e,[[4,L.d]],null,null),e.Bb(124,49152,null,0,s.db,[e.j,e.p,e.F],{max:[0,"max"],min:[1,"min"],mode:[2,"mode"],step:[3,"step"]},null),(l()(),e.Cb(125,0,null,0,1,"ion-icon",[["color","solos-color-a"],["name","volume-low"],["slot","start"]],null,null,null,V.R,V.j)),e.Bb(126,49152,null,0,s.E,[e.j,e.p,e.F],{color:[0,"color"],name:[1,"name"]},null),(l()(),e.Cb(127,0,null,0,1,"ion-icon",[["color","solos-color-a"],["name","volume-high"],["slot","end"]],null,null,null,V.R,V.j)),e.Bb(128,49152,null,0,s.E,[e.j,e.p,e.F],{color:[0,"color"],name:[1,"name"]},null),(l()(),e.Cb(129,0,null,null,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,V.Y,V.r)),e.Bb(130,49152,null,0,s.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(131,0,null,0,3,"ion-label",[],null,null,null,V.X,V.p)),e.Bb(132,49152,null,0,s.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(133,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(135,0,null,null,8,"ion-item",[["button",""],["class","settings-item"],["detail",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.intervalClicked()&&e),e}),V.W,V.o)),e.Bb(136,49152,null,0,s.J,[e.j,e.p,e.F],{button:[0,"button"],detail:[1,"detail"]},null),(l()(),e.Cb(137,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,V.X,V.p)),e.Bb(138,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(139,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(141,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,V.X,V.p)),e.Bb(142,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(143,0,["",""])),(l()(),e.Cb(144,0,null,null,12,"ion-item",[["class","settings-item"]],null,null,null,V.W,V.o)),e.Bb(145,49152,null,0,s.J,[e.j,e.p,e.F],null,null),(l()(),e.Cb(146,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,V.X,V.p)),e.Bb(147,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(148,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(150,0,null,0,6,"ion-toggle",[["class","item-toggle"],["color","solos-color-d"],["mode","md"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(l,n,t){var i=!0,s=l.component;return"ionBlur"===n&&(i=!1!==e.Ob(l,151)._handleBlurEvent(t.target)&&i),"ionChange"===n&&(i=!1!==e.Ob(l,151)._handleIonChange(t.target)&&i),"ionChange"===n&&(i=!1!==s.movingTimeDidChange(t)&&i),"ngModelChange"===n&&(i=!1!==(s.isMovingTimeOn=t)&&i),i}),V.nb,V.F)),e.Bb(151,16384,null,0,s.d,[e.p],null,null),e.Sb(1024,null,L.c,(function(l){return[l]}),[s.d]),e.Bb(153,671744,null,0,L.h,[[8,null],[8,null],[8,null],[6,L.c]],{model:[0,"model"]},{update:"ngModelChange"}),e.Sb(2048,null,L.d,null,[L.h]),e.Bb(155,16384,null,0,L.e,[[4,L.d]],null,null),e.Bb(156,49152,null,0,s.Cb,[e.j,e.p,e.F],{color:[0,"color"],mode:[1,"mode"]},null),(l()(),e.Cb(157,0,null,null,12,"ion-item",[["class","settings-item"]],null,null,null,V.W,V.o)),e.Bb(158,49152,null,0,s.J,[e.j,e.p,e.F],null,null),(l()(),e.Cb(159,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,V.X,V.p)),e.Bb(160,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(161,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(163,0,null,0,6,"ion-toggle",[["class","item-toggle"],["color","solos-color-d"],["mode","md"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(l,n,t){var i=!0,s=l.component;return"ionBlur"===n&&(i=!1!==e.Ob(l,164)._handleBlurEvent(t.target)&&i),"ionChange"===n&&(i=!1!==e.Ob(l,164)._handleIonChange(t.target)&&i),"ionChange"===n&&(i=!1!==s.distanceDidChange(t)&&i),"ngModelChange"===n&&(i=!1!==(s.isDistanceOn=t)&&i),i}),V.nb,V.F)),e.Bb(164,16384,null,0,s.d,[e.p],null,null),e.Sb(1024,null,L.c,(function(l){return[l]}),[s.d]),e.Bb(166,671744,null,0,L.h,[[8,null],[8,null],[8,null],[6,L.c]],{model:[0,"model"]},{update:"ngModelChange"}),e.Sb(2048,null,L.d,null,[L.h]),e.Bb(168,16384,null,0,L.e,[[4,L.d]],null,null),e.Bb(169,49152,null,0,s.Cb,[e.j,e.p,e.F],{color:[0,"color"],mode:[1,"mode"]},null),(l()(),e.Cb(170,0,null,null,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,V.Y,V.r)),e.Bb(171,49152,null,0,s.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(172,0,null,0,3,"ion-label",[],null,null,null,V.X,V.p)),e.Bb(173,49152,null,0,s.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(174,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(176,0,null,null,12,"ion-item",[["class","settings-item"]],null,null,null,V.W,V.o)),e.Bb(177,49152,null,0,s.J,[e.j,e.p,e.F],null,null),(l()(),e.Cb(178,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,V.X,V.p)),e.Bb(179,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(180,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(182,0,null,0,6,"ion-toggle",[["class","item-toggle"],["color","solos-color-d"],["mode","md"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(l,n,t){var i=!0,s=l.component;return"ionBlur"===n&&(i=!1!==e.Ob(l,183)._handleBlurEvent(t.target)&&i),"ionChange"===n&&(i=!1!==e.Ob(l,183)._handleIonChange(t.target)&&i),"ionChange"===n&&(i=!1!==s.autoPauseDidChange(t)&&i),"ngModelChange"===n&&(i=!1!==(s.isAutoPause=t)&&i),i}),V.nb,V.F)),e.Bb(183,16384,null,0,s.d,[e.p],null,null),e.Sb(1024,null,L.c,(function(l){return[l]}),[s.d]),e.Bb(185,671744,null,0,L.h,[[8,null],[8,null],[8,null],[6,L.c]],{model:[0,"model"]},{update:"ngModelChange"}),e.Sb(2048,null,L.d,null,[L.h]),e.Bb(187,16384,null,0,L.e,[[4,L.d]],null,null),e.Bb(188,49152,null,0,s.Cb,[e.j,e.p,e.F],{color:[0,"color"],mode:[1,"mode"]},null),(l()(),e.Cb(189,0,null,null,12,"ion-item",[["class","settings-item"]],null,null,null,V.W,V.o)),e.Bb(190,49152,null,0,s.J,[e.j,e.p,e.F],null,null),(l()(),e.Cb(191,0,null,0,3,"ion-label",[["color","settings-left-label"]],null,null,null,V.X,V.p)),e.Bb(192,49152,null,0,s.P,[e.j,e.p,e.F],{color:[0,"color"]},null),(l()(),e.Vb(193,0,["",""])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(195,0,null,0,6,"ion-toggle",[["class","item-toggle"],["color","solos-color-d"],["mode","md"]],[[2,"ng-untouched",null],[2,"ng-touched",null],[2,"ng-pristine",null],[2,"ng-dirty",null],[2,"ng-valid",null],[2,"ng-invalid",null],[2,"ng-pending",null]],[[null,"ionChange"],[null,"ngModelChange"],[null,"ionBlur"]],(function(l,n,t){var i=!0,s=l.component;return"ionBlur"===n&&(i=!1!==e.Ob(l,196)._handleBlurEvent(t.target)&&i),"ionChange"===n&&(i=!1!==e.Ob(l,196)._handleIonChange(t.target)&&i),"ionChange"===n&&(i=!1!==s.uploadStravaDidChange(t)&&i),"ngModelChange"===n&&(i=!1!==(s.isUploadStrava=t)&&i),i}),V.nb,V.F)),e.Bb(196,16384,null,0,s.d,[e.p],null,null),e.Sb(1024,null,L.c,(function(l){return[l]}),[s.d]),e.Bb(198,671744,null,0,L.h,[[8,null],[8,null],[8,null],[6,L.c]],{isDisabled:[0,"isDisabled"],model:[1,"model"]},{update:"ngModelChange"}),e.Sb(2048,null,L.d,null,[L.h]),e.Bb(200,16384,null,0,L.e,[[4,L.d]],null,null),e.Bb(201,49152,null,0,s.Cb,[e.j,e.p,e.F],{color:[0,"color"],disabled:[1,"disabled"],mode:[2,"mode"]},null),(l()(),e.Cb(202,0,null,null,7,"ion-footer",[],null,null,null,V.P,V.h)),e.Bb(203,49152,null,0,s.B,[e.j,e.p,e.F],null,null),(l()(),e.Cb(204,0,null,0,4,"div",[["class","footer-buttons"]],null,null,null,null,null)),(l()(),e.Cb(205,0,null,null,3,"ion-button",[["class","start-button"],["color","solos-color-a"],["expand","block"],["shape","round"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.startTraining()&&e),e}),V.K,V.c)),e.Bb(206,49152,null,0,s.m,[e.j,e.p,e.F],{color:[0,"color"],expand:[1,"expand"],shape:[2,"shape"]},null),(l()(),e.Vb(207,0,[" "," "])),e.Pb(131072,E.j,[E.k,e.j]),(l()(),e.Cb(209,0,null,0,0,"div",[["class","footer-padding"]],null,null,null,null,null))],(function(l,n){var t=n.component;l(n,3,0,"ios"),l(n,7,0,""),l(n,26,0,"level-selection",t.isEasy?"active":"inactive"),l(n,31,0,"level-selection",t.isNormal?"active":"inactive"),l(n,36,0,"level-selection",t.isExpert?"active":"inactive"),l(n,41,0,"level-selection",t.isCustom?"active":"inactive"),l(n,51,0,"",""),l(n,53,0,"settings-left-label"),l(n,57,0,"settings-right-label"),l(n,60,0,"settings-left-label"),l(n,64,0,"settings-right-label"),l(n,73,0,"",""),l(n,75,0,"settings-left-label"),l(n,79,0,"settings-right-label"),l(n,82,0,"settings-left-label"),l(n,86,0,"settings-right-label"),l(n,95,0,"",""),l(n,97,0,"settings-left-label"),l(n,101,0,"settings-right-label"),l(n,104,0,"settings-left-label"),l(n,108,0,"settings-right-label"),l(n,121,0,t.beepVolume),l(n,124,0,"10","0","ios","1"),l(n,126,0,"solos-color-a","volume-low"),l(n,128,0,"solos-color-a","volume-high"),l(n,136,0,"",""),l(n,138,0,"settings-left-label"),l(n,142,0,"settings-right-label"),l(n,147,0,"settings-left-label"),l(n,153,0,t.isMovingTimeOn),l(n,156,0,"solos-color-d","md"),l(n,160,0,"settings-left-label"),l(n,166,0,t.isDistanceOn),l(n,169,0,"solos-color-d","md"),l(n,179,0,"settings-left-label"),l(n,185,0,t.isAutoPause),l(n,188,0,"solos-color-d","md"),l(n,192,0,"settings-left-label"),l(n,198,0,!t.isStravaLoggedin,t.isUploadStrava),l(n,201,0,"solos-color-d",!t.isStravaLoggedin,"md"),l(n,206,0,"solos-color-a","block","round")}),(function(l,n){var t=n.component;l(n,11,0,e.Wb(n,11,0,e.Ob(n,12).transform("fat-burn.title"))),l(n,18,0,e.Wb(n,18,0,e.Ob(n,19).transform("fat-burn.intro"))),l(n,21,0,e.Wb(n,21,0,e.Ob(n,22).transform("training.choose-level"))),l(n,27,0,e.Wb(n,27,0,e.Ob(n,28).transform("training.easy"))),l(n,32,0,e.Wb(n,32,0,e.Ob(n,33).transform("training.normal"))),l(n,37,0,e.Wb(n,37,0,e.Ob(n,38).transform("training.expert"))),l(n,42,0,e.Wb(n,42,0,e.Ob(n,43).transform("training.custom"))),l(n,48,0,e.Wb(n,48,0,e.Ob(n,49).transform("fat-burn.warm-up"))),l(n,54,0,e.Wb(n,54,0,e.Ob(n,55).transform("training.time"))),l(n,58,0,t.warmUpTimeTxt),l(n,61,0,e.Wb(n,61,0,e.Ob(n,62).transform("training.spm"))),l(n,65,0,t.warmUpCadenceTxt),l(n,70,0,e.Wb(n,70,0,e.Ob(n,71).transform("fat-burn.fast-walk"))),l(n,76,0,e.Wb(n,76,0,e.Ob(n,77).transform("training.time"))),l(n,80,0,t.fastWalkTimeTxt),l(n,83,0,e.Wb(n,83,0,e.Ob(n,84).transform("training.spm"))),l(n,87,0,t.fastWalkCadenceTxt),l(n,92,0,e.Wb(n,92,0,e.Ob(n,93).transform("fat-burn.cool-down"))),l(n,98,0,e.Wb(n,98,0,e.Ob(n,99).transform("training.time"))),l(n,102,0,t.coolDownTimeTxt),l(n,105,0,e.Wb(n,105,0,e.Ob(n,106).transform("training.spm"))),l(n,109,0,t.coolDownCadenceTxt),l(n,114,0,e.Wb(n,114,0,e.Ob(n,115).transform("training.beep"))),l(n,118,0,e.Ob(n,123).ngClassUntouched,e.Ob(n,123).ngClassTouched,e.Ob(n,123).ngClassPristine,e.Ob(n,123).ngClassDirty,e.Ob(n,123).ngClassValid,e.Ob(n,123).ngClassInvalid,e.Ob(n,123).ngClassPending),l(n,133,0,e.Wb(n,133,0,e.Ob(n,134).transform("training.reminder-settings"))),l(n,139,0,e.Wb(n,139,0,e.Ob(n,140).transform("training.reminder-interval"))),l(n,143,0,t.intervalTxt),l(n,148,0,e.Wb(n,148,0,e.Ob(n,149).transform("training.moving-time"))),l(n,150,0,e.Ob(n,155).ngClassUntouched,e.Ob(n,155).ngClassTouched,e.Ob(n,155).ngClassPristine,e.Ob(n,155).ngClassDirty,e.Ob(n,155).ngClassValid,e.Ob(n,155).ngClassInvalid,e.Ob(n,155).ngClassPending),l(n,161,0,e.Wb(n,161,0,e.Ob(n,162).transform("training.distance"))),l(n,163,0,e.Ob(n,168).ngClassUntouched,e.Ob(n,168).ngClassTouched,e.Ob(n,168).ngClassPristine,e.Ob(n,168).ngClassDirty,e.Ob(n,168).ngClassValid,e.Ob(n,168).ngClassInvalid,e.Ob(n,168).ngClassPending),l(n,174,0,e.Wb(n,174,0,e.Ob(n,175).transform("training.advanced-settings"))),l(n,180,0,e.Wb(n,180,0,e.Ob(n,181).transform("training.auto-pause"))),l(n,182,0,e.Ob(n,187).ngClassUntouched,e.Ob(n,187).ngClassTouched,e.Ob(n,187).ngClassPristine,e.Ob(n,187).ngClassDirty,e.Ob(n,187).ngClassValid,e.Ob(n,187).ngClassInvalid,e.Ob(n,187).ngClassPending),l(n,193,0,e.Wb(n,193,0,e.Ob(n,194).transform("training.upload-strava"))),l(n,195,0,e.Ob(n,200).ngClassUntouched,e.Ob(n,200).ngClassTouched,e.Ob(n,200).ngClassPristine,e.Ob(n,200).ngClassDirty,e.Ob(n,200).ngClassValid,e.Ob(n,200).ngClassInvalid,e.Ob(n,200).ngClassPending),l(n,207,0,e.Wb(n,207,0,e.Ob(n,208).transform("training.start")))}))}function X(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,1,"app-fat-burn",[],null,null,null,_,A)),e.Bb(1,114688,null,0,F,[s.Jb,o.e,E.k,a.a,u.a,r.c,e.F,c.f,b.c,h.b],null,null)],(function(l,n){l(n,1,0)}),null)}var N=e.yb("app-fat-burn",F,X,{},{},[]),R=t("hrfs"),z=t("j1ZV"),J=t("iInd");t.d(n,"FatBurnPageModuleNgFactory",(function(){return G}));var G=e.zb(D,[],(function(l){return e.Lb([e.Mb(512,e.m,e.kb,[[8,[U.a,N]],[3,e.m],e.D]),e.Mb(4608,I.n,I.m,[e.z,[2,I.z]]),e.Mb(4608,L.j,L.j,[]),e.Mb(4608,s.c,s.c,[e.F,e.g]),e.Mb(4608,s.Ib,s.Ib,[s.c,e.m,e.w]),e.Mb(4608,s.Nb,s.Nb,[s.c,e.m,e.w]),e.Mb(4608,E.g,E.f,[]),e.Mb(4608,E.c,E.e,[]),e.Mb(4608,E.i,E.d,[]),e.Mb(4608,E.b,E.a,[]),e.Mb(4608,E.k,E.k,[E.l,E.g,E.c,E.i,E.b,E.m,E.n]),e.Mb(1073742336,I.b,I.b,[]),e.Mb(1073742336,L.i,L.i,[]),e.Mb(1073742336,L.b,L.b,[]),e.Mb(1073742336,s.Fb,s.Fb,[]),e.Mb(1073742336,R.b,R.b,[]),e.Mb(1073742336,E.h,E.h,[]),e.Mb(1073742336,z.a,z.a,[]),e.Mb(1073742336,J.o,J.o,[[2,J.t],[2,J.m]]),e.Mb(1073742336,D,D,[]),e.Mb(256,E.n,void 0,[]),e.Mb(256,E.m,void 0,[]),e.Mb(1024,J.k,(function(){return[[{path:"",component:F}]]}),[])])}))},j1ZV:function(l,n,t){"use strict";t.d(n,"a",(function(){return e}));class e{}}}]);