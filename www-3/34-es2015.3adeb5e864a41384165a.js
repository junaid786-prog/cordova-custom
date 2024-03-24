(window.webpackJsonp=window.webpackJsonp||[]).push([[34],{"6gT1":function(l,n,t){"use strict";t.d(n,"b",(function(){return c})),t.d(n,"a",(function(){return o}));var e=t("mrSG"),i=(t("ZZ/e"),t("lIDq")),s=t("lvyt");t("Z18M"),t("OUMn");const u=16;var a=function(l){return l[l.CURRENT=0]="CURRENT",l[l.START=1]="START",l[l.STOP=2]="STOP",l}({});class c{constructor(l,n,t,i,s,c,o){this.route=l,this.router=n,this.coachSettings=t,this.backend=i,this.navCtrl=s,this.translate=c,this.popupCtrl=o,this.startDate="",this.startTime="",this.elapsedHH=0,this.elapsedmm=0,this.elapsedss=0,this.distance=0,this.avgPacingmm=-1,this.avgPacingss=-1,this.maxPacingmm=-1,this.maxPacingss=-1,this.minPacingmm=-1,this.minPacingss=-1,this.movingHH=0,this.movingmm=0,this.movingss=0,this.stepCount=0,this.calories=0,this.avgStride=-1,this.maxStride=-1,this.minStride=-1,this.avgCadence=-1,this.maxCadence=-1,this.minCadence=-1,this.leftBal=0,this.rightBal=0,this.avgHeartRate=-1,this.minHeartRate=-1,this.maxHeartRate=-1,this.pacingUnit="summary.per-km",this.distanceUnit="summary.km",this.strideUnit="summary.m",this.runningRoute=null,this.calculatedRoutes=[],this.markers=[],this.map=null,this.isImperial=!1,this.deleteActivity=()=>e.b(this,void 0,void 0,(function*(){if(null!=this.activity.activityId)try{yield this.popupCtrl.presentActionSheet({buttons:[{text:this.translate.instant("summary.delete-activity"),role:"destructive",handler:()=>{this.backend.removeActivity(this.activity.activityId).then(()=>{this.navCtrl.pop()}).catch(l=>{this.navCtrl.pop()})}},{text:this.translate.instant("global.cancel"),role:"cancel"}]},!0)}catch(l){console.log(l)}})),this.showRecycleBin=()=>null!=this.activity.activityId,this.openMap=()=>e.b(this,void 0,void 0,(function*(){if(null==this.map&&this.runningRoute.length>0)try{this.map=new google.maps.Map(this.mapCanvas.nativeElement,{center:{lat:this.runningRoute[0].lat,lng:this.runningRoute[0].lng},zoom:u,disableDefaultUI:!0}),this.updateRouteDisplay()}catch(l){console.log(l)}})),this.closeMap=()=>{null!=this.map&&(this.deleteRoute(),this.map=null)},this.deleteRoute=()=>{this.deleteMarkers();for(let l of this.calculatedRoutes)l.setMap(null);this.calculatedRoutes=[]},this.addMarker=(l,n)=>{let t;switch(n){case a.CURRENT:t="assets/imgs/blue-dot.png";break;case a.START:t="assets/imgs/green-dot.png";break;case a.STOP:default:t="assets/imgs/red-dot.png"}if(null!=this.map){let n=new google.maps.Marker({map:this.map,position:l,icon:{url:t}});this.markers.push(n)}},this.deleteMarkers=()=>{for(const l of this.markers)l.setMap(null);this.markers.length=0},this.drawPathOnMap=(l,n)=>{let t="#AA00FF";n||(t="#C0C0C0");const e=new google.maps.Polyline({path:l,geodesic:!0,strokeColor:t,strokeOpacity:1,strokeWeight:2});e.setMap(this.map),this.calculatedRoutes.push(e)},this.updateRouteDisplay=()=>{if(null!=this.map){let l=new google.maps.LatLngBounds,n=[],t=!1,e=null,i=null;for(let s of this.runningRoute)null==s.paused&&(s.paused=!1),null==e&&(e=s),i=s,n.push({lat:s.lat,lng:s.lng}),l.extend(new google.maps.LatLng(s.lat,s.lng)),t!=s.paused&&(this.drawPathOnMap(n,!t),n=[s],t=s.paused);n.length>1&&this.drawPathOnMap(n,!t),this.calculatedRoutes.length>0?(this.addMarker(e,a.START),this.addMarker(i,a.STOP),this.map.fitBounds(l)):this.addMarker(n[0],a.CURRENT)}},this.route.queryParams.subscribe(l=>{this.router.getCurrentNavigation().extras.state&&(this.activity=this.router.getCurrentNavigation().extras.state.activity)})}ngOnInit(){this.startDate=Object(s.b)(this.activity.startTime)+" "+Object(s.a)(this.activity.startTime),this.startTime=Object(s.d)(this.activity.startTime),this.coachSettings.getUnit()==i.d&&(this.pacingUnit="summary.per-mi",this.distanceUnit="summary.mile",this.strideUnit="summary.feet",this.isImperial=!0);let l=this.activity.elapsedTime;this.elapsedss=Math.floor(l%60),this.elapsedmm=Math.floor(l/60)%60,this.elapsedHH=Math.floor(l/3600),this.distance=this.activity.distance,this.isImperial&&(this.distance*=.62137);let n=this.activity.avgPacing,t=this.activity.maxPacing,e=this.activity.minPacing;this.isImperial&&(n/=.62137,t/=.62137,e/=.62137),this.avgPacingmm=Math.floor(n),this.avgPacingss=Math.floor(n%1*60),this.maxPacingmm=Math.floor(t),this.maxPacingss=Math.floor(t%1*60),this.minPacingmm=Math.floor(e),this.minPacingss=Math.floor(e%1*60);let u=this.activity.movingTime;this.movingss=Math.floor(u%60),this.movingmm=Math.floor(u/60)%60,this.movingHH=Math.floor(u/3600),this.stepCount=this.activity.stepCount,this.calories=this.activity.calories,this.avgStride=this.activity.avgStride,this.maxStride=this.activity.maxStride,this.minStride=this.activity.minStride,this.isImperial&&(this.avgStride*=3.2808,this.maxStride*=3.2808,this.minStride*=3.2808),this.avgCadence=this.activity.avgCadence,this.maxCadence=this.activity.maxCadence,this.minCadence=this.activity.minCadence,this.leftBal=this.activity.leftBalance,this.rightBal=this.activity.rightBalance,this.avgHeartRate=this.activity.avgHeartRate,this.minHeartRate=this.activity.minHeartRate,this.maxHeartRate=this.activity.maxHeartRate,this.runningRoute=this.activity.route}ionViewDidEnter(){this.openMap()}ionViewWillLeave(){this.closeMap()}}class o{constructor(){this.activityId=null,this.startTime=null,this.distance=0,this.elapsedTime=0,this.avgPacing=0,this.maxPacing=0,this.minPacing=0,this.movingTime=0,this.stepCount=0,this.calories=0,this.avgStride=0,this.maxStride=0,this.minStride=0,this.avgCadence=0,this.maxCadence=0,this.minCadence=0,this.leftBalance=0,this.rightBalance=0,this.avgHeartRate=0,this.minHeartRate=0,this.maxHeartRate=0,this.route=[]}}},RXbx:function(l,n,t){"use strict";t.r(n);var e=t("8Y7J"),i=t("6gT1");class s{}var u=t("pMnS"),a=t("oBZk"),c=t("ZZ/e"),o=t("SVse"),r=t("TSSN"),h=t("iInd"),m=t("lIDq"),b=t("Z18M"),g=t("OUMn"),d=e.Ab({encapsulation:0,styles:[["ion-item[_ngcontent-%COMP%]{--padding-end:0px;--inner-padding-end:0px}.main-body[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:start;justify-content:flex-start;-webkit-box-align:center;align-items:center;width:100%;height:100%}.act-title[_ngcontent-%COMP%]{width:100%;height:48px;padding:5px 15px;--color:var(--solos-color-a)}.date[_ngcontent-%COMP%]{font-size:16px}.time[_ngcontent-%COMP%]{font-size:16px;text-align:end}.metric-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:start;justify-content:flex-start;padding:0 5px;border-style:solid;border-bottom-color:var(--solos-color-b);border-width:0 0 1px}.metric-name[_ngcontent-%COMP%]{width:24%;margin:10px 0;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center}.metric-single[_ngcontent-%COMP%]{width:76%;margin:10px 0;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center}.metric-single-value[_ngcontent-%COMP%]{color:var(--solos-color-a);margin:0;font-size:32px;font-weight:500;line-height:32px;text-align:center}.metric-avg[_ngcontent-%COMP%]{width:38%;margin:10px 0;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center}.metric-max-min[_ngcontent-%COMP%]{width:38%;margin:10px 0 10px 20px;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:center;justify-content:center;-webkit-box-align:start;align-items:flex-start}.metric-max[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;margin:8px 0}.metric-max-value[_ngcontent-%COMP%]{color:var(--solos-color-a);margin:0;font-weight:300;font-size:16px;line-height:16px}.metric-max-text[_ngcontent-%COMP%]{color:var(--solos-color-c);font-size:24px}.metric-title[_ngcontent-%COMP%]{font-weight:300;color:var(--solos-color-c);font-size:12px;line-height:12px;margin:8px 0 0;text-align:center}.metric-icon[_ngcontent-%COMP%]{width:50px;height:34px}.metric-avg-value[_ngcontent-%COMP%]{color:var(--solos-color-a);margin:0 0 4px;font-size:26px;font-weight:500;line-height:28px;text-align:center}.metric-avg-text[_ngcontent-%COMP%]{text-align:center;color:var(--solos-color-c);margin:0;font-size:12px;font-weight:100;line-height:16px}.metric-reading[_ngcontent-%COMP%]{margin:10px 0;color:var(--solos-color-a);font-size:24px;font-weight:300}.map-container[_ngcontent-%COMP%]{width:100%;padding-top:133%;position:relative}.map[_ngcontent-%COMP%]{position:absolute;top:16px;left:16px;bottom:16px;right:16px}"]],data:{}});function v(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,5,"ion-buttons",[["slot","end"]],null,null,null,a.L,a.d)),e.Bb(1,49152,null,0,c.n,[e.j,e.p,e.F],null,null),(l()(),e.Cb(2,0,null,0,3,"ion-button",[],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.deleteActivity()&&e),e}),a.K,a.c)),e.Bb(3,49152,null,0,c.m,[e.j,e.p,e.F],null,null),(l()(),e.Cb(4,0,null,0,1,"ion-icon",[["color","solos-color-a"],["name","trash"],["slot","icon-only"]],null,null,null,a.R,a.j)),e.Bb(5,49152,null,0,c.E,[e.j,e.p,e.F],{color:[0,"color"],name:[1,"name"]},null)],(function(l,n){l(n,5,0,"solos-color-a","trash")}),null)}function p(l){return e.Xb(0,[e.Pb(0,o.e,[e.z]),e.Tb(671088640,1,{mapCanvas:0}),(l()(),e.Cb(2,0,null,null,14,"ion-header",[],null,null,null,a.Q,a.i)),e.Bb(3,49152,null,0,c.D,[e.j,e.p,e.F],null,null),(l()(),e.Cb(4,0,null,0,12,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,a.ob,a.G)),e.Bb(5,49152,null,0,c.Db,[e.j,e.p,e.F],{mode:[0,"mode"]},null),(l()(),e.Cb(6,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,a.L,a.d)),e.Bb(7,49152,null,0,c.n,[e.j,e.p,e.F],null,null),(l()(),e.Cb(8,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(l,n,t){var i=!0;return"click"===n&&(i=!1!==e.Ob(l,10).onClick(t)&&i),i}),a.J,a.b)),e.Bb(9,49152,null,0,c.i,[e.j,e.p,e.F],{text:[0,"text"]},null),e.Bb(10,16384,null,0,c.j,[[2,c.jb],c.Jb],null,null),(l()(),e.rb(16777216,null,0,1,null,v)),e.Bb(12,16384,null,0,o.l,[e.X,e.T],{ngIf:[0,"ngIf"]},null),(l()(),e.Cb(13,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,a.mb,a.E)),e.Bb(14,49152,null,0,c.Bb,[e.j,e.p,e.F],null,null),(l()(),e.Vb(15,0,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(17,0,null,null,225,"ion-content",[["class","settings-background "]],null,null,null,a.O,a.g)),e.Bb(18,49152,null,0,c.w,[e.j,e.p,e.F],null,null),(l()(),e.Cb(19,0,null,0,223,"ion-list",[["class","settings-body"]],null,null,null,a.Z,a.q)),e.Bb(20,49152,null,0,c.Q,[e.j,e.p,e.F],null,null),(l()(),e.Cb(21,0,null,0,7,"ion-item",[["class","act-title"],["no-padding",""]],null,null,null,a.W,a.o)),e.Bb(22,49152,null,0,c.J,[e.j,e.p,e.F],null,null),(l()(),e.Cb(23,0,null,0,2,"ion-label",[["class","date"],["slot","start"]],null,null,null,a.X,a.p)),e.Bb(24,49152,null,0,c.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(25,0,[" "," "])),(l()(),e.Cb(26,0,null,0,2,"ion-label",[["class","time"],["slot","end"]],null,null,null,a.X,a.p)),e.Bb(27,49152,null,0,c.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(28,0,[" "," "])),(l()(),e.Cb(29,0,null,0,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,a.Y,a.r)),e.Bb(30,49152,null,0,c.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(31,0,null,0,3,"ion-label",[],null,null,null,a.X,a.p)),e.Bb(32,49152,null,0,c.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(33,0,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(35,0,null,0,12,"div",[["class","metric-container"]],null,null,null,null,null)),(l()(),e.Cb(36,0,null,null,5,"div",[["class","metric-name"]],null,null,null,null,null)),(l()(),e.Cb(37,0,null,null,1,"ion-icon",[["class","metric-icon"],["src","assets/imgs/elapsed-on-icon.svg"]],null,null,null,a.R,a.j)),e.Bb(38,49152,null,0,c.E,[e.j,e.p,e.F],{src:[0,"src"]},null),(l()(),e.Cb(39,0,null,null,2,"p",[["class","metric-title"]],null,null,null,null,null)),(l()(),e.Vb(40,null,[" "," "])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(42,0,null,null,5,"div",[["class","metric-single"]],null,null,null,null,null)),(l()(),e.Cb(43,0,null,null,4,"h4",[["class","metric-single-value"]],null,null,null,null,null)),(l()(),e.Vb(44,null,["","",":",""])),e.Rb(45,2),e.Rb(46,2),e.Rb(47,2),(l()(),e.Cb(48,0,null,0,11,"div",[["class","metric-container"]],null,null,null,null,null)),(l()(),e.Cb(49,0,null,null,5,"div",[["class","metric-name"]],null,null,null,null,null)),(l()(),e.Cb(50,0,null,null,1,"ion-icon",[["class","metric-icon"],["src","assets/imgs/distance-on-icon.svg"]],null,null,null,a.R,a.j)),e.Bb(51,49152,null,0,c.E,[e.j,e.p,e.F],{src:[0,"src"]},null),(l()(),e.Cb(52,0,null,null,2,"p",[["class","metric-title"]],null,null,null,null,null)),(l()(),e.Vb(53,null,[" "," "])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(55,0,null,null,4,"div",[["class","metric-single"]],null,null,null,null,null)),(l()(),e.Cb(56,0,null,null,3,"h4",[["class","metric-single-value"]],null,null,null,null,null)),(l()(),e.Vb(57,null,["","",""])),e.Rb(58,2),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(60,0,null,0,28,"div",[["class","metric-container"]],null,null,null,null,null)),(l()(),e.Cb(61,0,null,null,5,"div",[["class","metric-name"]],null,null,null,null,null)),(l()(),e.Cb(62,0,null,null,1,"ion-icon",[["class","metric-icon"],["src","assets/imgs/pacing-on-icon.svg"]],null,null,null,a.R,a.j)),e.Bb(63,49152,null,0,c.E,[e.j,e.p,e.F],{src:[0,"src"]},null),(l()(),e.Cb(64,0,null,null,2,"p",[["class","metric-title"]],null,null,null,null,null)),(l()(),e.Vb(65,null,[" "," "])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(67,0,null,null,10,"div",[["class","metric-avg"]],null,null,null,null,null)),(l()(),e.Cb(68,0,null,null,6,"h4",[["class","metric-avg-value"]],null,null,null,null,null)),(l()(),e.Vb(69,null,["",":",""])),e.Rb(70,2),e.Rb(71,2),(l()(),e.Cb(72,0,null,null,2,"font",[["size","2"]],null,null,null,null,null)),(l()(),e.Vb(73,null,["\xa0",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(75,0,null,null,2,"p",[["class","metric-avg-text"]],null,null,null,null,null)),(l()(),e.Vb(76,null,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(78,0,null,null,10,"div",[["class","metric-avg"]],null,null,null,null,null)),(l()(),e.Cb(79,0,null,null,6,"h4",[["class","metric-avg-value"]],null,null,null,null,null)),(l()(),e.Vb(80,null,["",":",""])),e.Rb(81,2),e.Rb(82,2),(l()(),e.Cb(83,0,null,null,2,"font",[["size","2"]],null,null,null,null,null)),(l()(),e.Vb(84,null,["\xa0",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(86,0,null,null,2,"p",[["class","metric-avg-text"]],null,null,null,null,null)),(l()(),e.Vb(87,null,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(89,0,null,0,12,"div",[["class","metric-container"]],null,null,null,null,null)),(l()(),e.Cb(90,0,null,null,5,"div",[["class","metric-name"]],null,null,null,null,null)),(l()(),e.Cb(91,0,null,null,1,"ion-icon",[["class","metric-icon"],["src","assets/imgs/moving-on-icon.svg"]],null,null,null,a.R,a.j)),e.Bb(92,49152,null,0,c.E,[e.j,e.p,e.F],{src:[0,"src"]},null),(l()(),e.Cb(93,0,null,null,2,"p",[["class","metric-title"]],null,null,null,null,null)),(l()(),e.Vb(94,null,[" "," "])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(96,0,null,null,5,"div",[["class","metric-single"]],null,null,null,null,null)),(l()(),e.Cb(97,0,null,null,4,"h4",[["class","metric-single-value"]],null,null,null,null,null)),(l()(),e.Vb(98,null,["","",":",""])),e.Rb(99,2),e.Rb(100,2),e.Rb(101,2),(l()(),e.Cb(102,0,null,0,9,"div",[["class","metric-container"]],null,null,null,null,null)),(l()(),e.Cb(103,0,null,null,5,"div",[["class","metric-name"]],null,null,null,null,null)),(l()(),e.Cb(104,0,null,null,1,"ion-icon",[["class","metric-icon"],["src","assets/imgs/steps-on-icon.svg"]],null,null,null,a.R,a.j)),e.Bb(105,49152,null,0,c.E,[e.j,e.p,e.F],{src:[0,"src"]},null),(l()(),e.Cb(106,0,null,null,2,"p",[["class","metric-title"]],null,null,null,null,null)),(l()(),e.Vb(107,null,[" "," "])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(109,0,null,null,2,"div",[["class","metric-single"]],null,null,null,null,null)),(l()(),e.Cb(110,0,null,null,1,"h4",[["class","metric-single-value"]],null,null,null,null,null)),(l()(),e.Vb(111,null,["",""])),(l()(),e.Cb(112,0,null,0,32,"div",[["class","metric-container"]],null,null,null,null,null)),(l()(),e.Cb(113,0,null,null,5,"div",[["class","metric-name"]],null,null,null,null,null)),(l()(),e.Cb(114,0,null,null,1,"ion-icon",[["class","metric-icon"],["src","assets/imgs/heart-rate-on-icon.svg"]],null,null,null,a.R,a.j)),e.Bb(115,49152,null,0,c.E,[e.j,e.p,e.F],{src:[0,"src"]},null),(l()(),e.Cb(116,0,null,null,2,"p",[["class","metric-title"]],null,null,null,null,null)),(l()(),e.Vb(117,null,[" "," "])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(119,0,null,null,8,"div",[["class","metric-avg"]],null,null,null,null,null)),(l()(),e.Cb(120,0,null,null,4,"h4",[["class","metric-avg-value"]],null,null,null,null,null)),(l()(),e.Vb(121,null,["",""])),(l()(),e.Cb(122,0,null,null,2,"font",[["size","2"]],null,null,null,null,null)),(l()(),e.Vb(123,null,["\xa0",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(125,0,null,null,2,"p",[["class","metric-avg-text"]],null,null,null,null,null)),(l()(),e.Vb(126,null,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(128,0,null,null,16,"div",[["class","metric-max-min"]],null,null,null,null,null)),(l()(),e.Cb(129,0,null,null,7,"div",[["class","metric-max"]],null,null,null,null,null)),(l()(),e.Cb(130,0,null,null,1,"ion-icon",[["class","metric-max-text"],["mode","md"],["name","arrow-dropup"]],null,null,null,a.R,a.j)),e.Bb(131,49152,null,0,c.E,[e.j,e.p,e.F],{mode:[0,"mode"],name:[1,"name"]},null),(l()(),e.Cb(132,0,null,null,4,"p",[["class","metric-max-value"]],null,null,null,null,null)),(l()(),e.Vb(133,null,["",""])),(l()(),e.Cb(134,0,null,null,2,"font",[["size","1"]],null,null,null,null,null)),(l()(),e.Vb(135,null,["\xa0",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(137,0,null,null,7,"div",[["class","metric-max"]],null,null,null,null,null)),(l()(),e.Cb(138,0,null,null,1,"ion-icon",[["class","metric-max-text"],["mode","md"],["name","arrow-dropdown"]],null,null,null,a.R,a.j)),e.Bb(139,49152,null,0,c.E,[e.j,e.p,e.F],{mode:[0,"mode"],name:[1,"name"]},null),(l()(),e.Cb(140,0,null,null,4,"p",[["class","metric-max-value"]],null,null,null,null,null)),(l()(),e.Vb(141,null,["",""])),(l()(),e.Cb(142,0,null,null,2,"font",[["size","1"]],null,null,null,null,null)),(l()(),e.Vb(143,null,["\xa0",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(145,0,null,0,10,"div",[["class","metric-container"]],null,null,null,null,null)),(l()(),e.Cb(146,0,null,null,5,"div",[["class","metric-name"]],null,null,null,null,null)),(l()(),e.Cb(147,0,null,null,1,"ion-icon",[["class","metric-icon"],["src","assets/imgs/calories-on-icon.svg"]],null,null,null,a.R,a.j)),e.Bb(148,49152,null,0,c.E,[e.j,e.p,e.F],{src:[0,"src"]},null),(l()(),e.Cb(149,0,null,null,2,"p",[["class","metric-title"]],null,null,null,null,null)),(l()(),e.Vb(150,null,[" "," "])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(152,0,null,null,3,"div",[["class","metric-single"]],null,null,null,null,null)),(l()(),e.Cb(153,0,null,null,2,"h4",[["class","metric-single-value"]],null,null,null,null,null)),(l()(),e.Vb(154,null,["",""])),e.Rb(155,2),(l()(),e.Cb(156,0,null,0,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,a.Y,a.r)),e.Bb(157,49152,null,0,c.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(158,0,null,0,3,"ion-label",[],null,null,null,a.X,a.p)),e.Bb(159,49152,null,0,c.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(160,0,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(162,0,null,0,26,"div",[["class","metric-container"]],null,null,null,null,null)),(l()(),e.Cb(163,0,null,null,5,"div",[["class","metric-name"]],null,null,null,null,null)),(l()(),e.Cb(164,0,null,null,1,"ion-icon",[["class","metric-icon"],["src","assets/imgs/stride-on-icon.svg"]],null,null,null,a.R,a.j)),e.Bb(165,49152,null,0,c.E,[e.j,e.p,e.F],{src:[0,"src"]},null),(l()(),e.Cb(166,0,null,null,2,"p",[["class","metric-title"]],null,null,null,null,null)),(l()(),e.Vb(167,null,[" "," "])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(169,0,null,null,9,"div",[["class","metric-avg"]],null,null,null,null,null)),(l()(),e.Cb(170,0,null,null,5,"h4",[["class","metric-avg-value"]],null,null,null,null,null)),(l()(),e.Vb(171,null,["",""])),e.Rb(172,2),(l()(),e.Cb(173,0,null,null,2,"font",[["size","2"]],null,null,null,null,null)),(l()(),e.Vb(174,null,["\xa0\xa0",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(176,0,null,null,2,"p",[["class","metric-avg-text"]],null,null,null,null,null)),(l()(),e.Vb(177,null,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(179,0,null,null,9,"div",[["class","metric-avg"]],null,null,null,null,null)),(l()(),e.Cb(180,0,null,null,5,"h4",[["class","metric-avg-value"]],null,null,null,null,null)),(l()(),e.Vb(181,null,["",""])),e.Rb(182,2),(l()(),e.Cb(183,0,null,null,2,"font",[["size","2"]],null,null,null,null,null)),(l()(),e.Vb(184,null,["\xa0\xa0",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(186,0,null,null,2,"p",[["class","metric-avg-text"]],null,null,null,null,null)),(l()(),e.Vb(187,null,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(189,0,null,0,24,"div",[["class","metric-container"]],null,null,null,null,null)),(l()(),e.Cb(190,0,null,null,5,"div",[["class","metric-name"]],null,null,null,null,null)),(l()(),e.Cb(191,0,null,null,1,"ion-icon",[["class","metric-icon"],["src","assets/imgs/cadence-on-icon.svg"]],null,null,null,a.R,a.j)),e.Bb(192,49152,null,0,c.E,[e.j,e.p,e.F],{src:[0,"src"]},null),(l()(),e.Cb(193,0,null,null,2,"p",[["class","metric-title"]],null,null,null,null,null)),(l()(),e.Vb(194,null,[" "," "])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(196,0,null,null,8,"div",[["class","metric-avg"]],null,null,null,null,null)),(l()(),e.Cb(197,0,null,null,4,"h4",[["class","metric-avg-value"]],null,null,null,null,null)),(l()(),e.Vb(198,null,["",""])),e.Rb(199,2),(l()(),e.Cb(200,0,null,null,1,"font",[["size","2"]],null,null,null,null,null)),(l()(),e.Vb(-1,null,["\xa0\xa0spm"])),(l()(),e.Cb(202,0,null,null,2,"p",[["class","metric-avg-text"]],null,null,null,null,null)),(l()(),e.Vb(203,null,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(205,0,null,null,8,"div",[["class","metric-avg"]],null,null,null,null,null)),(l()(),e.Cb(206,0,null,null,4,"h4",[["class","metric-avg-value"]],null,null,null,null,null)),(l()(),e.Vb(207,null,["",""])),e.Rb(208,2),(l()(),e.Cb(209,0,null,null,1,"font",[["size","2"]],null,null,null,null,null)),(l()(),e.Vb(-1,null,["\xa0\xa0spm"])),(l()(),e.Cb(211,0,null,null,2,"p",[["class","metric-avg-text"]],null,null,null,null,null)),(l()(),e.Vb(212,null,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(214,0,null,0,20,"div",[["class","metric-container"]],null,null,null,null,null)),(l()(),e.Cb(215,0,null,null,5,"div",[["class","metric-name"]],null,null,null,null,null)),(l()(),e.Cb(216,0,null,null,1,"ion-icon",[["class","metric-icon"],["src","assets/imgs/left-balance-on-icon.svg"]],null,null,null,a.R,a.j)),e.Bb(217,49152,null,0,c.E,[e.j,e.p,e.F],{src:[0,"src"]},null),(l()(),e.Cb(218,0,null,null,2,"p",[["class","metric-title"]],null,null,null,null,null)),(l()(),e.Vb(219,null,[" "," "])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(221,0,null,null,6,"div",[["class","metric-avg"]],null,null,null,null,null)),(l()(),e.Cb(222,0,null,null,2,"h4",[["class","metric-avg-value"]],null,null,null,null,null)),(l()(),e.Vb(223,null,["","%"])),e.Rb(224,2),(l()(),e.Cb(225,0,null,null,2,"p",[["class","metric-avg-text"]],null,null,null,null,null)),(l()(),e.Vb(226,null,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(228,0,null,null,6,"div",[["class","metric-avg"]],null,null,null,null,null)),(l()(),e.Cb(229,0,null,null,2,"h4",[["class","metric-avg-value"]],null,null,null,null,null)),(l()(),e.Vb(230,null,["","%"])),e.Rb(231,2),(l()(),e.Cb(232,0,null,null,2,"p",[["class","metric-avg-text"]],null,null,null,null,null)),(l()(),e.Vb(233,null,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(235,0,null,0,5,"ion-list-header",[["class","settings-item-header"]],null,null,null,a.Y,a.r)),e.Bb(236,49152,null,0,c.R,[e.j,e.p,e.F],null,null),(l()(),e.Cb(237,0,null,0,3,"ion-label",[],null,null,null,a.X,a.p)),e.Bb(238,49152,null,0,c.P,[e.j,e.p,e.F],null,null),(l()(),e.Vb(239,0,["",""])),e.Pb(131072,r.j,[r.k,e.j]),(l()(),e.Cb(241,0,null,0,1,"div",[["class","map-container ion-padding"]],null,null,null,null,null)),(l()(),e.Cb(242,0,[[1,0],["mapCanvas",1]],null,0,"div",[["class","map"]],null,null,null,null,null))],(function(l,n){var t=n.component;l(n,5,0,"ios"),l(n,9,0,""),l(n,12,0,t.showRecycleBin()),l(n,38,0,"assets/imgs/elapsed-on-icon.svg"),l(n,51,0,"assets/imgs/distance-on-icon.svg"),l(n,63,0,"assets/imgs/pacing-on-icon.svg"),l(n,92,0,"assets/imgs/moving-on-icon.svg"),l(n,105,0,"assets/imgs/steps-on-icon.svg"),l(n,115,0,"assets/imgs/heart-rate-on-icon.svg"),l(n,131,0,"md","arrow-dropup"),l(n,139,0,"md","arrow-dropdown"),l(n,148,0,"assets/imgs/calories-on-icon.svg"),l(n,165,0,"assets/imgs/stride-on-icon.svg"),l(n,192,0,"assets/imgs/cadence-on-icon.svg"),l(n,217,0,"assets/imgs/left-balance-on-icon.svg")}),(function(l,n){var t=n.component;l(n,15,0,e.Wb(n,15,0,e.Ob(n,16).transform("summary.header"))),l(n,25,0,t.startDate),l(n,28,0,t.startTime),l(n,33,0,e.Wb(n,33,0,e.Ob(n,34).transform("summary.basic"))),l(n,40,0,e.Wb(n,40,0,e.Ob(n,41).transform("summary.elapsed-time")));var i=0==t.elapsedHH?"":e.Wb(n,44,0,l(n,45,0,e.Ob(n,0),t.elapsedHH,"2.0-0"))+":",s=e.Wb(n,44,1,l(n,46,0,e.Ob(n,0),t.elapsedmm,"2.0-0")),u=e.Wb(n,44,2,l(n,47,0,e.Ob(n,0),t.elapsedss,"2.0-0"));l(n,44,0,i,s,u),l(n,53,0,e.Wb(n,53,0,e.Ob(n,54).transform("summary.distance")));var a=e.Wb(n,57,0,l(n,58,0,e.Ob(n,0),t.distance,"1.2-2"));l(n,57,0,a,e.Wb(n,57,1,e.Ob(n,59).transform(t.distanceUnit))),l(n,65,0,e.Wb(n,65,0,e.Ob(n,66).transform("summary.pacing")));var c=t.avgPacingmm>0?e.Wb(n,69,0,l(n,70,0,e.Ob(n,0),t.avgPacingmm,"2.0-0")):"--",o=t.avgPacingmm>0?e.Wb(n,69,1,l(n,71,0,e.Ob(n,0),t.avgPacingss,"2.0-0")):"--";l(n,69,0,c,o),l(n,73,0,e.Wb(n,73,0,e.Ob(n,74).transform(t.pacingUnit))),l(n,76,0,e.Wb(n,76,0,e.Ob(n,77).transform("summary.average")));var r=t.minPacingmm>=0?e.Wb(n,80,0,l(n,81,0,e.Ob(n,0),t.minPacingmm,"2.0-0")):"--",h=t.minPacingmm>=0?e.Wb(n,80,1,l(n,82,0,e.Ob(n,0),t.minPacingss,"2.0-0")):"--";l(n,80,0,r,h),l(n,84,0,e.Wb(n,84,0,e.Ob(n,85).transform(t.pacingUnit))),l(n,87,0,e.Wb(n,87,0,e.Ob(n,88).transform("summary.best"))),l(n,94,0,e.Wb(n,94,0,e.Ob(n,95).transform("summary.moving-time")));var m=0==t.movingHH?"":e.Wb(n,98,0,l(n,99,0,e.Ob(n,0),t.movingHH,"2.0-0"))+":",b=e.Wb(n,98,1,l(n,100,0,e.Ob(n,0),t.movingmm,"2.0-0")),g=e.Wb(n,98,2,l(n,101,0,e.Ob(n,0),t.movingss,"2.0-0"));l(n,98,0,m,b,g),l(n,107,0,e.Wb(n,107,0,e.Ob(n,108).transform("summary.step-count"))),l(n,111,0,t.stepCount>=0?t.stepCount:"--"),l(n,117,0,e.Wb(n,117,0,e.Ob(n,118).transform("summary.heart-rate"))),l(n,121,0,t.avgHeartRate>0?t.avgHeartRate:"--"),l(n,123,0,e.Wb(n,123,0,e.Ob(n,124).transform("summary.heart-rate-unit"))),l(n,126,0,e.Wb(n,126,0,e.Ob(n,127).transform("summary.average"))),l(n,133,0,t.minHeartRate>0?t.minHeartRate:"--"),l(n,135,0,e.Wb(n,135,0,e.Ob(n,136).transform("summary.heart-rate-unit"))),l(n,141,0,t.maxHeartRate>0?t.maxHeartRate:"--"),l(n,143,0,e.Wb(n,143,0,e.Ob(n,144).transform("summary.heart-rate-unit"))),l(n,150,0,e.Wb(n,150,0,e.Ob(n,151).transform("summary.calories")));var d=t.calories>=0?e.Wb(n,154,0,l(n,155,0,e.Ob(n,0),t.calories,"1.0-0")):"--";l(n,154,0,d),l(n,160,0,e.Wb(n,160,0,e.Ob(n,161).transform("summary.advanced"))),l(n,167,0,e.Wb(n,167,0,e.Ob(n,168).transform("summary.stride")));var v=t.avgStride>=0?e.Wb(n,171,0,l(n,172,0,e.Ob(n,0),t.avgStride,"1.2-2")):"--";l(n,171,0,v),l(n,174,0,e.Wb(n,174,0,e.Ob(n,175).transform(t.strideUnit))),l(n,177,0,e.Wb(n,177,0,e.Ob(n,178).transform("summary.average")));var p=t.maxStride>=0?e.Wb(n,181,0,l(n,182,0,e.Ob(n,0),t.maxStride,"1.2-2")):"--";l(n,181,0,p),l(n,184,0,e.Wb(n,184,0,e.Ob(n,185).transform(t.strideUnit))),l(n,187,0,e.Wb(n,187,0,e.Ob(n,188).transform("summary.best"))),l(n,194,0,e.Wb(n,194,0,e.Ob(n,195).transform("summary.cadence")));var y=t.avgCadence>=0?e.Wb(n,198,0,l(n,199,0,e.Ob(n,0),t.avgCadence,"1.0-0")):"--";l(n,198,0,y),l(n,203,0,e.Wb(n,203,0,e.Ob(n,204).transform("summary.average")));var C=t.maxCadence>=0?e.Wb(n,207,0,l(n,208,0,e.Ob(n,0),t.maxCadence,"1.0-0")):"--";l(n,207,0,C),l(n,212,0,e.Wb(n,212,0,e.Ob(n,213).transform("summary.best"))),l(n,219,0,e.Wb(n,219,0,e.Ob(n,220).transform("summary.balance")));var f=t.leftBal>=0?e.Wb(n,223,0,l(n,224,0,e.Ob(n,0),100*t.leftBal,"1.0-0")):"--";l(n,223,0,f),l(n,226,0,e.Wb(n,226,0,e.Ob(n,227).transform("summary.left")));var x=t.rightBal>=0?e.Wb(n,230,0,l(n,231,0,e.Ob(n,0),100*t.rightBal,"1.0-0")):"--";l(n,230,0,x),l(n,233,0,e.Wb(n,233,0,e.Ob(n,234).transform("summary.right"))),l(n,239,0,e.Wb(n,239,0,e.Ob(n,240).transform("summary.map")))}))}function y(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,1,"app-summary",[],null,null,null,p,d)),e.Bb(1,114688,null,0,i.b,[h.a,h.m,m.a,b.c,c.Jb,r.k,g.a],null,null)],(function(l,n){l(n,1,0)}),null)}var C=e.yb("app-summary",i.b,y,{},{},[]),f=t("s7LF");t.d(n,"SummaryPageModuleNgFactory",(function(){return x}));var x=e.zb(s,[],(function(l){return e.Lb([e.Mb(512,e.m,e.kb,[[8,[u.a,C]],[3,e.m],e.D]),e.Mb(4608,o.n,o.m,[e.z,[2,o.z]]),e.Mb(4608,f.j,f.j,[]),e.Mb(4608,c.c,c.c,[e.F,e.g]),e.Mb(4608,c.Ib,c.Ib,[c.c,e.m,e.w]),e.Mb(4608,c.Nb,c.Nb,[c.c,e.m,e.w]),e.Mb(4608,r.g,r.f,[]),e.Mb(4608,r.c,r.e,[]),e.Mb(4608,r.i,r.d,[]),e.Mb(4608,r.b,r.a,[]),e.Mb(4608,r.k,r.k,[r.l,r.g,r.c,r.i,r.b,r.m,r.n]),e.Mb(1073742336,o.b,o.b,[]),e.Mb(1073742336,f.i,f.i,[]),e.Mb(1073742336,f.b,f.b,[]),e.Mb(1073742336,c.Fb,c.Fb,[]),e.Mb(1073742336,r.h,r.h,[]),e.Mb(1073742336,h.o,h.o,[[2,h.t],[2,h.m]]),e.Mb(1073742336,s,s,[]),e.Mb(256,r.n,void 0,[]),e.Mb(256,r.m,void 0,[]),e.Mb(1024,h.k,(function(){return[[{path:"",component:i.b}]]}),[])])}))},lIDq:function(l,n,t){"use strict";t.d(n,"e",(function(){return O})),t.d(n,"d",(function(){return P})),t.d(n,"c",(function(){return k})),t.d(n,"b",(function(){return T})),t.d(n,"a",(function(){return B}));var e=t("mrSG"),i=t("ZZ/e"),s=t("8Y7J"),u=t("xgBC");const a="CoachSettings",c="Unit",o="ReminderInterval",r="ReminderTypeHeartRate",h="ReminderTypePacing",m="ReminderTypeAveragePacing",b="ReminderTypeElapsedTime",g="ReminderTypeDistance",d="ReminderTypeMovingTime",v="ReminderTypeStepCount",p="ReminderTypeStride",y="ReminderTypeAverageStride",C="ReminderTypeCadence",f="ReminderTypeAverageCadence",x="ReminderTypeLeftBalance",R="ReminderTypeRightBalance",j="CoachAutoPause",O="Metric",P="Imperial",k="evtCoachUnitChanged",T="evtCoachIntervalChanged";let B=(()=>{class l{constructor(l,n,t){this.platform=l,this.storage=n,this.events=t,this.unit=O,this.reminderInterval=0,this.heartRate=!1,this.pacing=!1,this.avgPacing=!1,this.elapsedTime=!1,this.distance=!1,this.movingTime=!1,this.stepCount=!1,this.stride=!1,this.avgStride=!1,this.cadence=!1,this.avgCadence=!1,this.leftBalance=!1,this.rightBalance=!1,this.autoPause=!0,this.getKey=l=>a+"-"+l,this.setUnit=l=>e.b(this,void 0,void 0,(function*(){if((l==P||l==O)&&l!=this.unit){this.unit=l;try{yield this.storage.set(this.getKey(c),l),this.events.publish(k)}catch(n){console.log(n)}}})),this.getUnit=()=>this.unit,this.setReminderInterval=l=>e.b(this,void 0,void 0,(function*(){if(l>=0&&l!=this.reminderInterval){this.reminderInterval=l;try{yield this.storage.set(this.getKey(o),l),this.events.publish(T)}catch(n){console.log(n)}}})),this.getReminderInterval=()=>this.reminderInterval,this.setReminderHeartRateTypeOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.heartRate){this.heartRate=l;try{yield this.storage.set(this.getKey(r),l)}catch(n){console.log(n)}}})),this.getReminderHeartRateTypeOn=()=>this.heartRate,this.setReminderTypePacingOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.pacing){this.pacing=l;try{yield this.storage.set(this.getKey(h),l)}catch(n){console.log(n)}}})),this.getReminderTypePacingOn=()=>this.pacing,this.setReminderTypeAveragePacingOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.avgPacing){this.avgPacing=l;try{yield this.storage.set(this.getKey(m),l)}catch(n){console.log(n)}}})),this.getReminderTypeAveragePacingOn=()=>this.avgPacing,this.setReminderTypeElapsedTimeOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.elapsedTime){this.elapsedTime=l;try{yield this.storage.set(this.getKey(b),l)}catch(n){console.log(n)}}})),this.getReminderTypeElapsedTimeOn=()=>this.elapsedTime,this.setReminderTypeDistanceOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.distance){this.distance=l;try{yield this.storage.set(this.getKey(g),l)}catch(n){console.log(n)}}})),this.getReminderTypeDistanceOn=()=>this.distance,this.setReminderTypeMovingTimeOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.movingTime){this.movingTime=l;try{yield this.storage.set(this.getKey(d),l)}catch(n){console.log(n)}}})),this.getReminderTypeMovingTimeOn=()=>this.movingTime,this.setReminderTypeStepCountOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.stepCount){this.stepCount=l;try{yield this.storage.set(this.getKey(v),l)}catch(n){console.log(n)}}})),this.getReminderTypeStepCountOn=()=>this.stepCount,this.setReminderTypeStrideOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.stride){this.stride=l;try{yield this.storage.set(this.getKey(p),l)}catch(n){console.log(n)}}})),this.getReminderTypeStrideOn=()=>this.stride,this.setReminderTypeAverageStrideOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.avgStride){this.avgStride=l;try{yield this.storage.set(this.getKey(y),l)}catch(n){console.log(n)}}})),this.getReminderTypeAverageStrideOn=()=>this.avgStride,this.setReminderTypeCadenceOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.cadence){this.cadence=l;try{yield this.storage.set(this.getKey(C),l)}catch(n){console.log(n)}}})),this.getReminderTypeCadenceOn=()=>this.cadence,this.setReminderTypeAverageCadenceOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.avgCadence){this.avgCadence=l;try{yield this.storage.set(this.getKey(f),l)}catch(n){console.log(n)}}})),this.getReminderTypeAverageCadenceOn=()=>this.avgCadence,this.setReminderTypeLeftBalanceOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.leftBalance){this.leftBalance=l;try{yield this.storage.set(this.getKey(x),l)}catch(n){console.log(n)}}})),this.getReminderTypeLeftBalanceOn=()=>this.leftBalance,this.setReminderTypeRightBalanceOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.rightBalance){this.rightBalance=l;try{yield this.storage.set(this.getKey(R),l)}catch(n){console.log(n)}}})),this.getReminderTypeRightBalanceOn=()=>this.rightBalance,this.setCoachAutoPauseOn=l=>e.b(this,void 0,void 0,(function*(){if(l!=this.autoPause){this.autoPause=l;try{yield this.storage.set(this.getKey(j),l)}catch(n){console.log(n)}}})),this.getCoachAutoPauseOn=()=>this.autoPause,this.platform.ready().then(()=>{this.storage.get(this.getKey(c)).then(l=>{null!=l&&(this.unit=l)}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(o)).then(l=>{null!=l&&(this.reminderInterval=Number(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(r)).then(l=>{null!=l&&(this.heartRate=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(h)).then(l=>{null!=l&&(this.pacing=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(m)).then(l=>{null!=l&&(this.avgPacing=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(b)).then(l=>{null!=l&&(this.elapsedTime=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(g)).then(l=>{null!=l&&(this.distance=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(d)).then(l=>{null!=l&&(this.movingTime=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(v)).then(l=>{null!=l&&(this.stepCount=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(p)).then(l=>{null!=l&&(this.stride=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(y)).then(l=>{null!=l&&(this.avgStride=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(C)).then(l=>{null!=l&&(this.cadence=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(f)).then(l=>{null!=l&&(this.avgCadence=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(x)).then(l=>{null!=l&&(this.leftBalance=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(R)).then(l=>{null!=l&&(this.rightBalance=Boolean(l))}).catch(l=>{console.log(l)}),this.storage.get(this.getKey(j)).then(l=>{null!=l&&(this.autoPause=Boolean(l))}).catch(l=>{console.log(l)})})}}return l.ngInjectableDef=s.ac({factory:function(){return new l(s.bc(i.Mb),s.bc(u.b),s.bc(i.f))},token:l,providedIn:"root"}),l})()}}]);