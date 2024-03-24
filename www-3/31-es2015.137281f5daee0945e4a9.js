(window.webpackJsonp=window.webpackJsonp||[]).push([[31],{"1a3b":function(l,n,t){"use strict";t.r(n);var i=t("8Y7J"),s=t("mrSG"),o=t("ZZ/e"),e=t("R3bG"),a=t("BHc3"),u=t("yM5c"),c=t("lIDq"),r=t("TA0h"),h=t("nJKE"),b=t("OUMn"),d=t("cPjO"),g=t("h5tO");const p=16;var m=function(l){return l[l.CURRENT=0]="CURRENT",l[l.START=1]="START",l[l.STOP=2]="STOP",l}({});class v{constructor(l,n,t,i,o,e,u,r,b,d,v,y){this.navCtrl=l,this.zone=n,this.events=t,this.translate=i,this.solosConnector=o,this.coachActivity=e,this.platform=u,this.popupCtrl=r,this.location=b,this.coachSettings=d,this.heartRateService=v,this.activityStateManager=y,this.heartRateDigit="--",this.pacingmm="--",this.pacingss="--",this.elapsedHH=0,this.elapsedmm=0,this.elapsedss=0,this.distanceDigit=0,this.movingHH=0,this.movingmm=0,this.movingss=0,this.stepCountDigit="--",this.strideDisplay="--",this.cadenceDisplay="--",this.L_ratio="--",this.R_ratio="--",this.M_ratio="--",this.pacingUnit="",this.distanceUnit="",this.strideUnit="",this.calories="--",this.busy=!1,this.heartRateDeviceConnected=!1,this.calculatedRoutes=[],this.markers=[],this.map=null,this.currentLocationTimer=null,this.viewDidEnter=!1,this.isCoachingRunning=()=>this.coachActivity.isRunning(),this.isCoachingStarted=()=>this.coachActivity.isRunning()&&!this.coachActivity.isPaused(),this.isCoachingStopped=()=>!this.coachActivity.isRunning(),this.isCoachingPaused=()=>this.coachActivity.isRunning()&&this.coachActivity.isPaused(),this.coachStateChanged=l=>{this.zone.run(()=>{this.busy=l.state==a.j.Busy})},this.tabViewWillEnter=()=>{this.busy=this.coachActivity.isBusy(),this.updateActivityRunTime(),this.updateMetricDataDisplay(),this.updateMetricUnit(),this.updateHeartRate()},this.updateMetricDataDisplay=()=>{this.zone.run(()=>{const l=this.coachActivity.getPacing();l>=0?(this.pacingmm=("0"+Math.floor(l).toString()).slice(-2),this.pacingss=("0"+Math.floor(l%1*60).toString()).slice(-2)):(this.pacingmm="--",this.pacingss="--");const n=this.coachActivity.getStepCount();this.stepCountDigit=n>=0?String(n):"--";const t=this.coachActivity.getCadence();this.cadenceDisplay=t>=0?t.toFixed(0):"--";const i=this.coachActivity.getMovingTime();let s=Math.floor(i/1e3);this.movingss=Math.floor(s%60),this.movingmm=Math.floor(s/60%60),this.movingHH=Math.floor(s/3600);const o=this.coachActivity.getStride();this.strideDisplay=o>=0?o.toFixed(1):"--";const e=this.coachActivity.getDistance();this.distanceDigit=e;const a=this.coachActivity.getLeftRatio();this.L_ratio=a>=0?Math.round(100*a).toFixed(0):"--";const u=this.coachActivity.getRightRatio();this.R_ratio=u>=0?Math.round(100*u).toFixed(0):"--";const c=this.coachActivity.getCalories();this.calories=c>=0?c.toFixed(0):"--"})},this.updateActivityRunTime=()=>{this.zone.run(()=>{const l=this.coachActivity.getRunTime();this.elapsedss=Math.floor(l/1e3%60),this.elapsedmm=Math.floor(l/1e3/60)%60,this.elapsedHH=Math.floor(l/1e3/3600)})},this.updateMetricUnit=()=>{this.zone.run(()=>{this.coachSettings.getUnit()==c.d?(this.pacingUnit=this.translate.instant("ai-coach.per-miles-text"),this.distanceUnit=this.translate.instant("ai-coach.miles-text"),this.strideUnit=this.translate.instant("ai-coach.feet-text")):(this.pacingUnit=this.translate.instant("ai-coach.per-km-text"),this.distanceUnit=this.translate.instant("ai-coach.km"),this.strideUnit=this.translate.instant("ai-coach.m")),this.updateActivityRunTime()})},this.startCoaching=()=>s.b(this,void 0,void 0,(function*(){try{const l=[...g.c,g.a.BASIC_TRAINING,g.a.CORE_TRAINING,g.a.POSTURE_MONITORING],n=this.activityStateManager.validate(l,g.a.BASIC_TRAINING,"/ai-coach");n.status?(this.coachActivity.start(),this.updateActivityRunTime(),this.updateMetricDataDisplay(),this.updateHeartRate()):yield this.popupCtrl.presentAlertController({header:n.header,message:n.message,buttons:n.buttons},!0)}catch(l){console.log(l.message)}})),this.stopCoaching=()=>s.b(this,void 0,void 0,(function*(){try{if(!this.coachActivity.isRunning())return;let l=[];if(this.coachActivity.isPaused()){if(null!=this.solosConnector.getConnectedDevice())return void(yield this.coachActivity.resume());l=[{text:this.translate.instant("ai-coach.end-activity"),role:"destructive",handler:()=>s.b(this,void 0,void 0,(function*(){try{yield this.coachActivity.stop()}catch(l){console.log(l)}}))},{text:this.translate.instant("ai-coach.close"),role:"cancel"}]}else l=[{text:this.translate.instant("ai-coach.end-activity"),role:"destructive",handler:()=>s.b(this,void 0,void 0,(function*(){try{yield this.coachActivity.stop()}catch(l){console.log(l)}}))},{text:this.translate.instant("ai-coach.pause-activity"),handler:()=>s.b(this,void 0,void 0,(function*(){try{yield this.coachActivity.pause()}catch(l){console.log(l)}}))},{text:this.translate.instant("ai-coach.close"),role:"cancel"}];yield this.popupCtrl.presentActionSheet({buttons:l},!0)}catch(l){console.log(l)}})),this.addMarker=(l,n)=>{let t;switch(n){case m.CURRENT:t="assets/imgs/blue-dot.png";break;case m.START:t="assets/imgs/green-dot.png";break;case m.STOP:default:t="assets/imgs/red-dot.png"}if(null!=this.map){let n=new google.maps.Marker({map:this.map,position:l,icon:{url:t}});this.markers.push(n)}},this.deleteMarkers=()=>{for(const l of this.markers)l.setMap(null);this.markers.length=0,this.hideCurrentLoc()},this.deleteRoute=()=>{this.deleteMarkers();for(let l of this.calculatedRoutes)l.setMap(null);this.calculatedRoutes=[]},this.drawPathOnMap=(l,n)=>{let t="#AA00FF";n||(t="#C0C0C0");const i=new google.maps.Polyline({path:l,geodesic:!0,strokeColor:t,strokeOpacity:1,strokeWeight:2});i.setMap(this.map),this.calculatedRoutes.push(i)},this.updateRouteDisplay=()=>{this.zone.run(()=>s.b(this,void 0,void 0,(function*(){if(null!=this.map){let l=[];if(this.platform.is("desktop")){let n=yield this.location.getCurrentPosition();void 0!==n&&(l.push(new h.b(n.coords.latitude,n.coords.longitude)),l.push(new h.b(n.coords.latitude+.01,n.coords.longitude+.01)))}else l=this.coachActivity.getCalculatedRoute();if(this.deleteRoute(),l.length>1){let n=new google.maps.LatLngBounds,t=[],i=!1,s=null,o=null;for(let e of l)null==s&&(s=e),o=e,t.push({lat:e.lat,lng:e.lng}),n.extend(new google.maps.LatLng(e.lat,e.lng)),i!=e.paused&&(this.drawPathOnMap(t,!i),t=[e],i=e.paused);t.length>1&&this.drawPathOnMap(t,!i),this.addMarker(s,m.START),this.addMarker(o,m.STOP),this.map.fitBounds(n)}else this.showCurrentLoc()}})))},this.hideCurrentLoc=()=>{null!=this.currentLocationTimer&&(clearTimeout(this.currentLocationTimer),this.currentLocationTimer=null)},this.showCurrentLoc=()=>s.b(this,void 0,void 0,(function*(){if(this.hideCurrentLoc(),this.map){const l=yield this.location.getCurrentPosition();if(void 0!==l){let n;n=this.platform.is("desktop")?{lat:l.coords.latitude+Math.random()/10,lng:l.coords.longitude+Math.random()/10}:{lat:l.coords.latitude,lng:l.coords.longitude},this.deleteMarkers(),this.addMarker(n,m.CURRENT),this.map.setCenter(n),this.map.setZoom(p)}this.currentLocationTimer=setTimeout(()=>{this.showCurrentLoc()},5e3)}})),this.openMap=()=>s.b(this,void 0,void 0,(function*(){if(null==this.map)try{let l=yield this.location.getCurrentPosition();void 0!==l&&(this.map=new google.maps.Map(this.mapCanvas.nativeElement,{center:{lat:l.coords.latitude,lng:l.coords.longitude},zoom:p,disableDefaultUI:!0})),this.updateRouteDisplay()}catch(l){console.log(l)}})),this.closeMap=()=>{null!=this.map&&(this.deleteRoute(),this.map=null)},this.openSummary=l=>{null!=l&&null!=l.activity&&this.zone.run(()=>{this.navCtrl.navigateForward("/summary",{state:{activity:l.activity}})})},this.coachSubPageDidChange=l=>{this.viewDidEnter&&("map"==l.detail.value?setTimeout(()=>this.openMap(),0):setTimeout(()=>this.closeMap(),0))},this.coachSettingsBtnClicked=()=>{this.navCtrl.navigateForward("/coach-settings")},this.onHeartRateChanged=l=>{this.zone.run(()=>{this.heartRateDigit=l.heartRate>0?l.heartRate:"--"})},this.updateHeartRate=()=>{const l=this.coachActivity.getHeartRate();this.heartRateDigit=l>0?String(l):"--"}}ngOnInit(){}ionViewWillEnter(){var l;this.busy=this.coachActivity.isBusy(),this.heartRateDeviceConnected=this.heartRateService.isConnected(),this.updateActivityRunTime(),this.updateMetricDataDisplay(),this.updateMetricUnit(),this.updateHeartRate(),this.bindedFunctions={},this.events.subscribe(r.a,l=this.tabViewWillEnter),this.bindedFunctions[r.a]=l,this.events.subscribe(a.h,l=this.updateRouteDisplay),this.bindedFunctions[a.h]=l,this.events.subscribe(a.f,l=this.updateMetricDataDisplay),this.bindedFunctions[a.f]=l,this.events.subscribe(a.g,l=this.updateActivityRunTime),this.bindedFunctions[a.g]=l,this.events.subscribe(c.c,l=this.updateMetricUnit),this.bindedFunctions[c.c]=l,this.events.subscribe(a.i,l=this.coachStateChanged),this.bindedFunctions[a.i]=l,this.events.subscribe(a.c,l=this.openSummary),this.bindedFunctions[a.c]=l,this.events.subscribe(a.e,l=this.onHeartRateChanged),this.bindedFunctions[a.e]=l}ionViewDidEnter(){this.viewDidEnter=!0}ionViewWillLeave(){this.viewDidEnter=!1;for(let l in this.bindedFunctions)this.events.unsubscribe(l,this.bindedFunctions[l]);this.bindedFunctions=null}ionViewDidLeave(){}}class y{}var C=t("pMnS"),f=t("SVse"),w=t("TSSN"),S=t("oBZk"),R=t("s7LF"),k=t("Opd2"),j=t("/rTQ"),M=t("v8bm"),x=t("gPPD"),B=i.Ab({encapsulation:0,styles:[[".footer-buttons[_ngcontent-%COMP%]{display:-webkit-box;display:flex;padding:10px;background:var(--solos-color-b);border-width:1px 0 0;border-style:solid;border-color:var(--solos-color-a)}.start-button[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-align:center;align-items:center;flex-basis:90%;margin-right:5px}.coach-button[_ngcontent-%COMP%]{flex-basis:10%;margin-left:5px;--border-width:1px;--border-style:solid;--border-color:var(--solos-color-d)}.metric-body[_ngcontent-%COMP%]{padding-top:5px;display:-webkit-box;display:flex;flex-wrap:wrap;-webkit-box-pack:center;justify-content:center}.metric-cell[_ngcontent-%COMP%]{width:45%;margin:10px 0;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center}.metric-title[_ngcontent-%COMP%]{font-size:14px;font-weight:300;margin:10px 0}.metric-icon[_ngcontent-%COMP%]{margin:5px 0;width:50px;height:38px}.metric-reading[_ngcontent-%COMP%]{margin:10px 0;color:var(--solos-color-a);font-size:24px;font-weight:300}.map-body[_ngcontent-%COMP%]{height:100%}.footer-padding[_ngcontent-%COMP%]{height:calc(env(safe-area-inset-bottom));background:var(--solos-color-b)}"]],data:{}});function F(l){return i.Xb(0,[(l()(),i.Cb(0,0,null,null,105,"div",[["class","metric-body"]],null,null,null,null,null)),(l()(),i.Cb(1,0,null,null,16,"div",[["class","metric-cell"]],null,null,null,null,null)),(l()(),i.Cb(2,0,null,null,5,"p",[["class","metric-title"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(4,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(5,{color:0}),(l()(),i.Vb(6,null,[" "," "])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(8,0,null,null,1,"ion-icon",[["class","metric-icon"]],null,null,null,S.R,S.j)),i.Bb(9,49152,null,0,o.E,[i.j,i.p,i.F],{src:[0,"src"]},null),(l()(),i.Cb(10,0,null,null,7,"p",[["class","metric-reading"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(12,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(13,{color:0}),(l()(),i.Vb(14,null,["","",""])),i.Rb(15,2),i.Rb(16,2),i.Rb(17,2),(l()(),i.Cb(18,0,null,null,14,"div",[["class","metric-cell"]],null,null,null,null,null)),(l()(),i.Cb(19,0,null,null,5,"p",[["class","metric-title"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(21,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(22,{color:0}),(l()(),i.Vb(23,null,[" "," "])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(25,0,null,null,1,"ion-icon",[["class","metric-icon"]],null,null,null,S.R,S.j)),i.Bb(26,49152,null,0,o.E,[i.j,i.p,i.F],{src:[0,"src"]},null),(l()(),i.Cb(27,0,null,null,5,"p",[["class","metric-reading"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(29,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(30,{color:0}),(l()(),i.Vb(31,null,["","",""])),i.Rb(32,2),(l()(),i.Cb(33,0,null,null,13,"div",[["class","metric-cell"]],null,null,null,null,null)),(l()(),i.Cb(34,0,null,null,5,"p",[["class","metric-title"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(36,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(37,{color:0}),(l()(),i.Vb(38,null,[" "," "])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(40,0,null,null,1,"ion-icon",[["class","metric-icon"]],null,null,null,S.R,S.j)),i.Bb(41,49152,null,0,o.E,[i.j,i.p,i.F],{src:[0,"src"]},null),(l()(),i.Cb(42,0,null,null,4,"p",[["class","metric-reading"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(44,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(45,{color:0}),(l()(),i.Vb(46,null,["",":","",""])),(l()(),i.Cb(47,0,null,null,16,"div",[["class","metric-cell"]],null,null,null,null,null)),(l()(),i.Cb(48,0,null,null,5,"p",[["class","metric-title"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(50,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(51,{color:0}),(l()(),i.Vb(52,null,[" "," "])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(54,0,null,null,1,"ion-icon",[["class","metric-icon"]],null,null,null,S.R,S.j)),i.Bb(55,49152,null,0,o.E,[i.j,i.p,i.F],{src:[0,"src"]},null),(l()(),i.Cb(56,0,null,null,7,"p",[["class","metric-reading"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(58,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(59,{color:0}),(l()(),i.Vb(60,null,["","",""])),i.Rb(61,2),i.Rb(62,2),i.Rb(63,2),(l()(),i.Cb(64,0,null,null,13,"div",[["class","metric-cell"]],null,null,null,null,null)),(l()(),i.Cb(65,0,null,null,5,"p",[["class","metric-title"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(67,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(68,{color:0}),(l()(),i.Vb(69,null,[" "," "])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(71,0,null,null,1,"ion-icon",[["class","metric-icon"]],null,null,null,S.R,S.j)),i.Bb(72,49152,null,0,o.E,[i.j,i.p,i.F],{src:[0,"src"]},null),(l()(),i.Cb(73,0,null,null,4,"p",[["class","metric-reading"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(75,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(76,{color:0}),(l()(),i.Vb(77,null,["",""])),(l()(),i.Cb(78,0,null,null,13,"div",[["class","metric-cell"]],null,null,null,null,null)),(l()(),i.Cb(79,0,null,null,5,"p",[["class","metric-title"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(81,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(82,{color:0}),(l()(),i.Vb(83,null,[" "," "])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(85,0,null,null,1,"ion-icon",[["class","metric-icon"]],null,null,null,S.R,S.j)),i.Bb(86,49152,null,0,o.E,[i.j,i.p,i.F],{src:[0,"src"]},null),(l()(),i.Cb(87,0,null,null,4,"p",[["class","metric-reading"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(89,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(90,{color:0}),(l()(),i.Vb(91,null,["","bpm"])),(l()(),i.Cb(92,0,null,null,13,"div",[["class","metric-cell"]],null,null,null,null,null)),(l()(),i.Cb(93,0,null,null,5,"p",[["class","metric-title"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(95,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(96,{color:0}),(l()(),i.Vb(97,null,[" "," "])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(99,0,null,null,1,"ion-icon",[["class","metric-icon"]],null,null,null,S.R,S.j)),i.Bb(100,49152,null,0,o.E,[i.j,i.p,i.F],{src:[0,"src"]},null),(l()(),i.Cb(101,0,null,null,4,"p",[["class","metric-reading"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(103,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(104,{color:0}),(l()(),i.Vb(105,null,["",""]))],(function(l,n){var t=n.component,i=l(n,5,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,4,0,i),l(n,9,0,t.isCoachingRunning()?"assets/imgs/elapsed-on-icon.svg":"assets/imgs/elapsed-off-icon.svg");var s=l(n,13,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,12,0,s);var o=l(n,22,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,21,0,o),l(n,26,0,t.isCoachingRunning()?"assets/imgs/distance-on-icon.svg":"assets/imgs/distance-off-icon.svg");var e=l(n,30,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,29,0,e);var a=l(n,37,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,36,0,a),l(n,41,0,t.isCoachingRunning()?"assets/imgs/pacing-on-icon.svg":"assets/imgs/pacing-off-icon.svg");var u=l(n,45,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,44,0,u);var c=l(n,51,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,50,0,c),l(n,55,0,t.isCoachingRunning()?"assets/imgs/moving-on-icon.svg":"assets/imgs/moving-off-icon.svg");var r=l(n,59,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,58,0,r);var h=l(n,68,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,67,0,h),l(n,72,0,t.isCoachingRunning()?"assets/imgs/steps-on-icon.svg":"assets/imgs/steps-off-icon.svg");var b=l(n,76,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,75,0,b);var d=l(n,82,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,81,0,d),l(n,86,0,t.isCoachingRunning()?"assets/imgs/heart-rate-on-icon.svg":"assets/imgs/heart-rate-off-icon.svg");var g=l(n,90,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,89,0,g);var p=l(n,96,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,95,0,p),l(n,100,0,t.isCoachingRunning()?"assets/imgs/calories-on-icon.svg":"assets/imgs/calories-off-icon.svg");var m=l(n,104,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,103,0,m)}),(function(l,n){var t=n.component;l(n,6,0,i.Wb(n,6,0,i.Ob(n,7).transform("ai-coach.elapsed-time")));var s=0==t.elapsedHH?"":i.Wb(n,14,0,l(n,15,0,i.Ob(n.parent,0),t.elapsedHH,"2.0-0"))+":",o=i.Wb(n,14,1,l(n,16,0,i.Ob(n.parent,0),t.elapsedmm,"2.0-0"))+":"+i.Wb(n,14,1,l(n,17,0,i.Ob(n.parent,0),t.elapsedss,"2.0-0"));l(n,14,0,s,o),l(n,23,0,i.Wb(n,23,0,i.Ob(n,24).transform("ai-coach.distance")));var e=i.Wb(n,31,0,l(n,32,0,i.Ob(n.parent,0),t.distanceDigit,"1.1-2"));l(n,31,0,e,t.distanceUnit),l(n,38,0,i.Wb(n,38,0,i.Ob(n,39).transform("ai-coach.pacing"))),l(n,46,0,t.pacingmm,t.pacingss,t.pacingUnit),l(n,52,0,i.Wb(n,52,0,i.Ob(n,53).transform("ai-coach.moving-time")));var a=0==t.movingHH?"":i.Wb(n,60,0,l(n,61,0,i.Ob(n.parent,0),t.movingHH,"2.0-0"))+":",u=i.Wb(n,60,1,l(n,62,0,i.Ob(n.parent,0),t.movingmm,"2.0-0"))+":"+i.Wb(n,60,1,l(n,63,0,i.Ob(n.parent,0),t.movingss,"2.0-0"));l(n,60,0,a,u),l(n,69,0,i.Wb(n,69,0,i.Ob(n,70).transform("ai-coach.step-count"))),l(n,77,0,t.stepCountDigit),l(n,83,0,i.Wb(n,83,0,i.Ob(n,84).transform("ai-coach.heart-rate"))),l(n,91,0,t.heartRateDigit),l(n,97,0,i.Wb(n,97,0,i.Ob(n,98).transform("ai-coach.calories"))),l(n,105,0,t.calories)}))}function P(l){return i.Xb(0,[(l()(),i.Cb(0,0,null,null,56,"div",[["class","metric-body"]],null,null,null,null,null)),(l()(),i.Cb(1,0,null,null,13,"div",[["class","metric-cell"]],null,null,null,null,null)),(l()(),i.Cb(2,0,null,null,5,"p",[["class","metric-title"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(4,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(5,{color:0}),(l()(),i.Vb(6,null,[" "," "])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(8,0,null,null,1,"ion-icon",[["class","metric-icon"]],null,null,null,S.R,S.j)),i.Bb(9,49152,null,0,o.E,[i.j,i.p,i.F],{src:[0,"src"]},null),(l()(),i.Cb(10,0,null,null,4,"p",[["class","metric-reading"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(12,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(13,{color:0}),(l()(),i.Vb(14,null,["","",""])),(l()(),i.Cb(15,0,null,null,13,"div",[["class","metric-cell"]],null,null,null,null,null)),(l()(),i.Cb(16,0,null,null,5,"p",[["class","metric-title"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(18,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(19,{color:0}),(l()(),i.Vb(20,null,[" "," "])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(22,0,null,null,1,"ion-icon",[["class","metric-icon"]],null,null,null,S.R,S.j)),i.Bb(23,49152,null,0,o.E,[i.j,i.p,i.F],{src:[0,"src"]},null),(l()(),i.Cb(24,0,null,null,4,"p",[["class","metric-reading"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(26,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(27,{color:0}),(l()(),i.Vb(28,null,["","spm"])),(l()(),i.Cb(29,0,null,null,13,"div",[["class","metric-cell"]],null,null,null,null,null)),(l()(),i.Cb(30,0,null,null,5,"p",[["class","metric-title"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(32,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(33,{color:0}),(l()(),i.Vb(34,null,[" "," "])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(36,0,null,null,1,"ion-icon",[["class","metric-icon"]],null,null,null,S.R,S.j)),i.Bb(37,49152,null,0,o.E,[i.j,i.p,i.F],{src:[0,"src"]},null),(l()(),i.Cb(38,0,null,null,4,"p",[["class","metric-reading"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(40,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(41,{color:0}),(l()(),i.Vb(42,null,["","%"])),(l()(),i.Cb(43,0,null,null,13,"div",[["class","metric-cell"]],null,null,null,null,null)),(l()(),i.Cb(44,0,null,null,5,"p",[["class","metric-title"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(46,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(47,{color:0}),(l()(),i.Vb(48,null,[" "," "])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(50,0,null,null,1,"ion-icon",[["class","metric-icon"]],null,null,null,S.R,S.j)),i.Bb(51,49152,null,0,o.E,[i.j,i.p,i.F],{src:[0,"src"]},null),(l()(),i.Cb(52,0,null,null,4,"p",[["class","metric-reading"]],null,null,null,null,null)),i.Sb(512,null,f.w,f.x,[i.p,i.y,i.L]),i.Bb(54,278528,null,0,f.o,[f.w],{ngStyle:[0,"ngStyle"]},null),i.Qb(55,{color:0}),(l()(),i.Vb(56,null,["","%"]))],(function(l,n){var t=n.component,i=l(n,5,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,4,0,i),l(n,9,0,t.isCoachingRunning()?"assets/imgs/stride-on-icon.svg":"assets/imgs/stride-off-icon.svg");var s=l(n,13,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,12,0,s);var o=l(n,19,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,18,0,o),l(n,23,0,t.isCoachingRunning()?"assets/imgs/cadence-on-icon.svg":"assets/imgs/cadence-off-icon.svg");var e=l(n,27,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,26,0,e);var a=l(n,33,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,32,0,a),l(n,37,0,t.isCoachingRunning()?"assets/imgs/left-balance-on-icon.svg":"assets/imgs/left-balance-off-icon.svg");var u=l(n,41,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,40,0,u);var c=l(n,47,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,46,0,c),l(n,51,0,t.isCoachingRunning()?"assets/imgs/right-balance-on-icon.svg":"assets/imgs/right-balance-off-icon.svg");var r=l(n,55,0,t.isCoachingRunning()?"var(--solos-color-a)":"var(--solos-color-c)");l(n,54,0,r)}),(function(l,n){var t=n.component;l(n,6,0,i.Wb(n,6,0,i.Ob(n,7).transform("ai-coach.stride"))),l(n,14,0,t.strideDisplay,t.strideUnit),l(n,20,0,i.Wb(n,20,0,i.Ob(n,21).transform("ai-coach.cadence"))),l(n,28,0,t.cadenceDisplay),l(n,34,0,i.Wb(n,34,0,i.Ob(n,35).transform("ai-coach.left-bal"))),l(n,42,0,t.L_ratio),l(n,48,0,i.Wb(n,48,0,i.Ob(n,49).transform("ai-coach.right-bal"))),l(n,56,0,t.R_ratio)}))}function L(l){return i.Xb(0,[(l()(),i.Cb(0,0,null,null,1,"div",[["class","map-body ion-padding"]],null,null,null,null,null)),(l()(),i.Cb(1,0,[[1,0],["mapCanvas",1]],null,0,"div",[["class","map-body"]],null,null,null,null,null))],null,null)}function O(l){return i.Xb(0,[(l()(),i.Cb(0,0,null,null,3,"ion-button",[["class","start-button"],["color","solos-color-a"],["shape","round"]],null,[[null,"click"]],(function(l,n,t){var i=!0;return"click"===n&&(i=!1!==l.component.startCoaching()&&i),i}),S.K,S.c)),i.Bb(1,49152,null,0,o.m,[i.j,i.p,i.F],{color:[0,"color"],disabled:[1,"disabled"],shape:[2,"shape"]},null),(l()(),i.Vb(2,0,["",""])),i.Pb(131072,w.j,[w.k,i.j])],(function(l,n){l(n,1,0,"solos-color-a",n.component.busy,"round")}),(function(l,n){l(n,2,0,i.Wb(n,2,0,i.Ob(n,3).transform("ai-coach.start")))}))}function A(l){return i.Xb(0,[(l()(),i.Cb(0,0,null,null,3,"ion-button",[["class","start-button"],["color","solos-color-d"],["shape","round"]],null,[[null,"click"]],(function(l,n,t){var i=!0;return"click"===n&&(i=!1!==l.component.stopCoaching()&&i),i}),S.K,S.c)),i.Bb(1,49152,null,0,o.m,[i.j,i.p,i.F],{color:[0,"color"],disabled:[1,"disabled"],shape:[2,"shape"]},null),(l()(),i.Vb(2,0,["",""])),i.Pb(131072,w.j,[w.k,i.j])],(function(l,n){l(n,1,0,"solos-color-d",n.component.busy,"round")}),(function(l,n){l(n,2,0,i.Wb(n,2,0,i.Ob(n,3).transform("ai-coach.stop")))}))}function T(l){return i.Xb(0,[(l()(),i.Cb(0,0,null,null,3,"ion-button",[["class","start-button"],["color","solos-color-e"],["shape","round"]],null,[[null,"click"]],(function(l,n,t){var i=!0;return"click"===n&&(i=!1!==l.component.stopCoaching()&&i),i}),S.K,S.c)),i.Bb(1,49152,null,0,o.m,[i.j,i.p,i.F],{color:[0,"color"],disabled:[1,"disabled"],shape:[2,"shape"]},null),(l()(),i.Vb(2,0,["",""])),i.Pb(131072,w.j,[w.k,i.j])],(function(l,n){l(n,1,0,"solos-color-e",n.component.busy,"round")}),(function(l,n){l(n,2,0,i.Wb(n,2,0,i.Ob(n,3).transform("ai-coach.resume")))}))}function D(l){return i.Xb(0,[i.Pb(0,f.e,[i.z]),i.Tb(671088640,1,{mapCanvas:0}),(l()(),i.Cb(2,0,null,null,34,"ion-header",[],null,null,null,S.Q,S.i)),i.Bb(3,49152,null,0,o.D,[i.j,i.p,i.F],null,null),(l()(),i.Cb(4,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,S.ob,S.G)),i.Bb(5,49152,null,0,o.Db,[i.j,i.p,i.F],{mode:[0,"mode"]},null),(l()(),i.Cb(6,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,S.L,S.d)),i.Bb(7,49152,null,0,o.n,[i.j,i.p,i.F],null,null),(l()(),i.Cb(8,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(l,n,t){var s=!0;return"click"===n&&(s=!1!==i.Ob(l,10).onClick(t)&&s),s}),S.J,S.b)),i.Bb(9,49152,null,0,o.i,[i.j,i.p,i.F],{text:[0,"text"]},null),i.Bb(10,16384,null,0,o.j,[[2,o.jb],o.Jb],null,null),(l()(),i.Cb(11,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,S.mb,S.E)),i.Bb(12,49152,null,0,o.Bb,[i.j,i.p,i.F],null,null),(l()(),i.Vb(13,0,["",""])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(15,0,null,0,21,"ion-segment",[],null,[[null,"ionChange"],[null,"ionBlur"]],(function(l,n,t){var s=!0,o=l.component;return"ionBlur"===n&&(s=!1!==i.Ob(l,18)._handleBlurEvent(t.target)&&s),"ionChange"===n&&(s=!1!==i.Ob(l,18)._handleChangeEvent(t.target)&&s),"ionChange"===n&&(s=!1!==o.coachSubPageDidChange(t)&&s),s}),S.hb,S.y)),i.Sb(5120,null,R.c,(function(l){return[l]}),[o.Pb]),i.Bb(17,49152,[["coachSubPage",4]],0,o.mb,[i.j,i.p,i.F],null,null),i.Bb(18,16384,null,0,o.Pb,[i.p],null,null),(l()(),i.Cb(19,0,null,0,5,"ion-segment-button",[["checked",""],["class","nested-tab-button"],["mode","md"],["value","basic"]],null,null,null,S.gb,S.z)),i.Bb(20,49152,null,0,o.nb,[i.j,i.p,i.F],{checked:[0,"checked"],mode:[1,"mode"],value:[2,"value"]},null),(l()(),i.Cb(21,0,null,0,3,"ion-label",[],null,null,null,S.X,S.p)),i.Bb(22,49152,null,0,o.P,[i.j,i.p,i.F],null,null),(l()(),i.Vb(23,0,["",""])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(25,0,null,0,5,"ion-segment-button",[["class","nested-tab-button"],["mode","md"],["value","advanced"]],null,null,null,S.gb,S.z)),i.Bb(26,49152,null,0,o.nb,[i.j,i.p,i.F],{mode:[0,"mode"],value:[1,"value"]},null),(l()(),i.Cb(27,0,null,0,3,"ion-label",[],null,null,null,S.X,S.p)),i.Bb(28,49152,null,0,o.P,[i.j,i.p,i.F],null,null),(l()(),i.Vb(29,0,["",""])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(31,0,null,0,5,"ion-segment-button",[["class","nested-tab-button"],["mode","md"],["value","map"]],null,null,null,S.gb,S.z)),i.Bb(32,49152,null,0,o.nb,[i.j,i.p,i.F],{mode:[0,"mode"],value:[1,"value"]},null),(l()(),i.Cb(33,0,null,0,3,"ion-label",[],null,null,null,S.X,S.p)),i.Bb(34,49152,null,0,o.P,[i.j,i.p,i.F],null,null),(l()(),i.Vb(35,0,["",""])),i.Pb(131072,w.j,[w.k,i.j]),(l()(),i.Cb(37,0,null,null,7,"ion-content",[["class","app-background"]],null,null,null,S.O,S.g)),i.Bb(38,49152,null,0,o.w,[i.j,i.p,i.F],null,null),(l()(),i.rb(16777216,null,0,1,null,F)),i.Bb(40,16384,null,0,f.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(l()(),i.rb(16777216,null,0,1,null,P)),i.Bb(42,16384,null,0,f.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(l()(),i.rb(16777216,null,0,1,null,L)),i.Bb(44,16384,null,0,f.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(l()(),i.Cb(45,0,null,null,16,"ion-footer",[],null,null,null,S.P,S.h)),i.Bb(46,49152,null,0,o.B,[i.j,i.p,i.F],null,null),(l()(),i.Cb(47,0,null,0,10,"div",[["class","footer-buttons"]],null,null,null,null,null)),(l()(),i.rb(16777216,null,null,1,null,O)),i.Bb(49,16384,null,0,f.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(l()(),i.rb(16777216,null,null,1,null,A)),i.Bb(51,16384,null,0,f.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(l()(),i.rb(16777216,null,null,1,null,T)),i.Bb(53,16384,null,0,f.l,[i.X,i.T],{ngIf:[0,"ngIf"]},null),(l()(),i.Cb(54,0,null,null,3,"ion-button",[["class","coach-button"],["color","solos-color-b-1"],["shape","round"]],null,[[null,"click"]],(function(l,n,t){var i=!0;return"click"===n&&(i=!1!==l.component.coachSettingsBtnClicked()&&i),i}),S.K,S.c)),i.Bb(55,49152,null,0,o.m,[i.j,i.p,i.F],{color:[0,"color"],shape:[1,"shape"]},null),(l()(),i.Cb(56,0,null,0,1,"ion-icon",[["name","settings"],["slot","icon-only"]],null,null,null,S.R,S.j)),i.Bb(57,49152,null,0,o.E,[i.j,i.p,i.F],{name:[0,"name"]},null),(l()(),i.Cb(58,0,null,0,2,"div",[],null,null,null,null,null)),(l()(),i.Cb(59,0,null,null,1,"app-music-bar",[],null,null,null,k.b,k.a)),i.Bb(60,245760,null,0,j.a,[o.Jb,o.f,M.k,i.F,x.a],null,null),(l()(),i.Cb(61,0,null,0,0,"div",[["class","footer-padding"]],null,null,null,null,null))],(function(l,n){var t=n.component;l(n,5,0,"ios"),l(n,9,0,""),l(n,20,0,"","md","basic"),l(n,26,0,"md","advanced"),l(n,32,0,"md","map"),l(n,40,0,"basic"==i.Ob(n,17).value),l(n,42,0,"advanced"==i.Ob(n,17).value),l(n,44,0,"map"==i.Ob(n,17).value),l(n,49,0,1==t.isCoachingStopped()),l(n,51,0,1==t.isCoachingStarted()),l(n,53,0,1==t.isCoachingPaused()),l(n,55,0,"solos-color-b-1","round"),l(n,57,0,"settings"),l(n,60,0)}),(function(l,n){l(n,13,0,i.Wb(n,13,0,i.Ob(n,14).transform("ai-coach-util.exercise"))),l(n,23,0,i.Wb(n,23,0,i.Ob(n,24).transform("ai-coach.basic"))),l(n,29,0,i.Wb(n,29,0,i.Ob(n,30).transform("ai-coach.advanced"))),l(n,35,0,i.Wb(n,35,0,i.Ob(n,36).transform("ai-coach.map")))}))}function V(l){return i.Xb(0,[(l()(),i.Cb(0,0,null,null,1,"app-ai-coach",[],null,null,null,D,B)),i.Bb(1,114688,null,0,v,[o.Jb,i.F,o.f,w.k,e.l,a.b,o.Mb,b.a,u.a,c.a,d.a,g.b],null,null)],(function(l,n){l(n,1,0)}),null)}var E=i.yb("app-ai-coach",v,V,{},{},[]),W=t("hrfs"),I=t("j1ZV"),H=t("iInd");t.d(n,"AiCoachPageModuleNgFactory",(function(){return z}));var z=i.zb(y,[],(function(l){return i.Lb([i.Mb(512,i.m,i.kb,[[8,[C.a,E]],[3,i.m],i.D]),i.Mb(4608,f.n,f.m,[i.z,[2,f.z]]),i.Mb(4608,R.j,R.j,[]),i.Mb(4608,o.c,o.c,[i.F,i.g]),i.Mb(4608,o.Ib,o.Ib,[o.c,i.m,i.w]),i.Mb(4608,o.Nb,o.Nb,[o.c,i.m,i.w]),i.Mb(4608,w.g,w.f,[]),i.Mb(4608,w.c,w.e,[]),i.Mb(4608,w.i,w.d,[]),i.Mb(4608,w.b,w.a,[]),i.Mb(4608,w.k,w.k,[w.l,w.g,w.c,w.i,w.b,w.m,w.n]),i.Mb(1073742336,f.b,f.b,[]),i.Mb(1073742336,R.i,R.i,[]),i.Mb(1073742336,R.b,R.b,[]),i.Mb(1073742336,o.Fb,o.Fb,[]),i.Mb(1073742336,W.b,W.b,[]),i.Mb(1073742336,w.h,w.h,[]),i.Mb(1073742336,I.a,I.a,[]),i.Mb(1073742336,H.o,H.o,[[2,H.t],[2,H.m]]),i.Mb(1073742336,y,y,[]),i.Mb(256,w.n,void 0,[]),i.Mb(256,w.m,void 0,[]),i.Mb(1024,H.k,(function(){return[[{path:"",component:v}]]}),[])])}))},"H/qo":function(l,n,t){"use strict";var i=/^[-!#$%&'*+\/0-9=?A-Z^_a-z{|}~](\.?[-!#$%&'*+\/0-9=?A-Z^_a-z`{|}~])*@[a-zA-Z0-9](-*\.?[a-zA-Z0-9])*\.[a-zA-Z](-?[a-zA-Z0-9])+$/;n.validate=function(l){if(!l)return!1;if(l.length>254)return!1;if(!i.test(l))return!1;var n=l.split("@");return!(n[0].length>64||n[1].split(".").some((function(l){return l.length>63})))}},j1ZV:function(l,n,t){"use strict";t.d(n,"a",(function(){return i}));class i{}},uxLX:function(l,n,t){"use strict";t.d(n,"c",(function(){return o})),t.d(n,"d",(function(){return e})),t.d(n,"a",(function(){return a})),t.d(n,"b",(function(){return u})),t.d(n,"e",(function(){return c}));var i=t("mrSG"),s=(t("ZZ/e"),t("H/qo"));t("Z18M"),t("OUMn");const o="evtProfilePageWillEnter",e="evtProfilePageWillLeave",a="evtProfilePageDidEnter",u="evtProfilePageDidLeave";class c{constructor(l,n,t,c,r,h,b){this.navCtrl=l,this.zone=n,this.events=t,this.translate=c,this.backend=r,this.platform=h,this.popupCtrl=b,this.title="profile.profile",this.logined=!1,this.keyboardShown=!1,this.emailInput="",this.passwordInput="",this.emailFocus=!1,this.pwdFocus=!1,this.tabViewWillEnter=()=>{this.events.publish(o)},this.tabViewWillLeave=()=>{this.events.publish(e)},this.tabViewDidEnter=()=>{this.events.publish(a)},this.tabViewDidLeave=()=>{this.events.publish(u)},this.loginOnClicked=()=>{setTimeout(()=>i.b(this,void 0,void 0,(function*(){const l=this.translate.instant("profile.login-error"),n=this.translate.instant("global.okay");let t=null;try{""===this.emailInput?yield this.popupCtrl.presentAlertController({header:l,message:this.translate.instant("profile.please-fill-email"),buttons:[{text:n}]},!0):s.validate(this.emailInput)?""===this.passwordInput?yield this.popupCtrl.presentAlertController({header:l,message:this.translate.instant("profile.please-fill-pwd"),buttons:[{text:n}]},!0):(t=yield this.popupCtrl.presentLoadingController({spinner:"bubbles",translucent:!0}),yield this.backend.signIn(this.emailInput,this.passwordInput),yield t.dismiss(),this.passwordInput="",this.navCtrl.navigateForward("/tabs",{state:{activePage:"profile"}})):yield this.popupCtrl.presentAlertController({header:l,message:this.translate.instant("profile.email-invalid"),buttons:[{text:n}]},!0)}catch(i){null!=t&&(yield t.dismiss()),yield this.popupCtrl.presentAlertController({header:l,message:i.message,buttons:[{text:n}]},!0)}})),100)},this.showTabs=()=>{if(this.platform.is("ios")||this.platform.is("android")){let l=document.querySelector("super-tabs-toolbar");null!=l&&(l.style.display="flex")}},this.hideTabs=()=>{if(this.platform.is("ios")||this.platform.is("android")){let l=document.querySelector("super-tabs-toolbar");null!=l&&(l.style.display="none")}},this.emailLostFocus=()=>{this.emailFocus=!1,this.emailFocus||this.pwdFocus||this.showTabs()},this.emailHasFocus=()=>{this.emailFocus=!0,this.hideTabs()},this.pwdLostFocus=()=>{this.pwdFocus=!1,this.emailFocus||this.pwdFocus||this.showTabs()},this.pwdHasFocus=()=>{this.pwdFocus=!0,this.hideTabs()}}ngOnInit(){}ionViewWillEnter(){this.events.publish(o),window.addEventListener("keyboardWillShow",this.keyboardShow.bind(this)),window.addEventListener("keyboardWillHide",this.keyboardHide.bind(this))}ionViewDidEnter(){this.events.publish(a)}ionViewWillLeave(){window.removeEventListener("keyboardWillShow",this.keyboardShow.bind(this)),window.removeEventListener("keyboardWillHide",this.keyboardHide.bind(this)),this.events.publish(e)}ionViewDidLeave(){this.events.publish(u)}goToForgotPasswordPage(){this.navCtrl.navigateForward("/forgot-password")}goToRegisterPage(){this.navCtrl.navigateForward("/register")}keyboardShow(l){this.zone.run(()=>{this.keyboardShown=!0})}keyboardHide(l){this.zone.run(()=>{this.keyboardShown=!1})}getLoginUser(){return this.backend.getLoginUser()}}}}]);