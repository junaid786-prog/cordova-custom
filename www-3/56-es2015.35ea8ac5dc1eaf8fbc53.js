(window.webpackJsonp=window.webpackJsonp||[]).push([[56],{l9Q2:function(l,n,t){"use strict";t.r(n);var i=t("8Y7J"),e=t("mrSG"),o=t("ZZ/e"),r=t("Z18M"),s=t("lvyt"),u=t("8cXU"),a=t("07SE");class c{constructor(l,n,t,i,o){this.zone=l,this.route=n,this.router=t,this.backend=i,this.navCtrl=o,this.coreTrainingList=[],this.totalRecords=0,this.lastTotalRecords=-1,this.busyLoadData=!1,this.getTotalCoreTrainingCount=()=>e.b(this,void 0,void 0,(function*(){try{this.totalRecords=yield this.backend.getTotalCoreExercise4Dates(this.fromDate,this.toDate)}catch(l){console.log(l)}})),this.loadData=()=>e.b(this,void 0,void 0,(function*(){if(!this.busyLoadData){this.busyLoadData=!0;try{yield this.getTotalCoreTrainingCount(),this.totalRecords!=this.lastTotalRecords&&(this.lastTotalRecords=this.totalRecords,this.infiniteScroll.disabled=!1,this.coreTrainingList=[],yield this.loadMore())}catch(l){console.log(l)}this.busyLoadData=!1}})),this.loadMore=(l=null)=>e.b(this,void 0,void 0,(function*(){this.coreTrainingList.length>0?yield this.getCoreTrainingRecords(this.fromDate,this.coreTrainingList[this.coreTrainingList.length-1].endTime,l):yield this.getCoreTrainingRecords(this.fromDate,this.toDate,l)})),this.getCoreTrainingRecords=(l,n,t=null)=>e.b(this,void 0,void 0,(function*(){try{let t=yield this.backend.queryCoreExercises4Dates(l,n);if(t.length>0){for(let l of t){const n=l.id,t=l.getStartTime(),i=l.getEndTime(),e=Object(s.b)(t)+" "+Object(s.a)(t),o=Object(s.d)(t),r=l.getTotalDurInMS()/1e3,u=Math.floor(r%60),a=Math.floor(r/60)%60,c=Math.floor(r/3600),d=l.getCalories(),h=l.getTotalSets();this.zone.run(()=>{this.coreTrainingList.push(new b(n,e,o,c,a,u,d,h,i))})}this.infiniteScroll.complete(),10!=t.length&&(this.infiniteScroll.disabled=!0)}else this.infiniteScroll.complete(),this.infiniteScroll.disabled=!0;this.virtualScroll.checkEnd()}catch(t){alert(t.message)}})),this.openCoreExerciseResult=l=>e.b(this,void 0,void 0,(function*(){try{const n=yield this.backend.queryCoreExercise(l),t=n.getResult();let i=[];const e=[a.b.Hops,a.b.Lunges,a.b.Planks,a.b.Pushups,a.b.Situps,a.b.Squats,a.b.Jog],o=[u.b.HOPS,u.b.LUNGE,u.b.PLANK,u.b.PUSH_UP,u.b.SIT_UP,u.b.SQUAT,u.b.JOG];for(const l in e){const n=e[l];let r={};r.name=o[l],r.durationInMS=t.filter(l=>l.type==n).reduce((l,n)=>n.time+l,0),r.reps=t.filter(l=>l.type==n).reduce((l,n)=>n.cnt+l,0),0===r.durationInMS&&0===r.reps||i.push(r)}let r={state:{starttime:n.getStartTime(),totalDurationInMS:n.getTotalDurInMS(),calories:n.getCalories(),trainingResult:i}};this.navCtrl.navigateForward("/core-training-result-container",r)}catch(n){alert(n.message)}})),this.route.queryParams.subscribe(l=>{this.router.getCurrentNavigation().extras.state&&(this.fromDate=this.router.getCurrentNavigation().extras.state.fromDate,this.toDate=this.router.getCurrentNavigation().extras.state.toDate)})}ngOnInit(){}ionViewWillEnter(){this.loadData()}ionViewDidEnter(){}ionViewWillLeave(){}ionViewDidLeave(){}}class b{constructor(l,n,t,i,e,o,r,s,u){this.objectId=l,this.date=n,this.time=t,this.elapsedHH=i,this.elapsedmm=e,this.elapsedss=o,this.calories=r,this.sets=s,this.endTime=u}}class d{}var h=t("pMnS"),p=t("oBZk"),g=t("TSSN"),m=t("SVse"),f=t("iInd"),x=i.Ab({encapsulation:0,styles:[["ion-item[_ngcontent-%COMP%]{--padding-end:0px;--inner-padding-end:0px}.container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center}.total-activities[_ngcontent-%COMP%]{color:var(--solos-color-a);font-size:16px;margin:10px;text-transform:uppercase}.card[_ngcontent-%COMP%]{width:90%;--background:var(--solos-color-b);padding-bottom:10px;margin-left:auto;margin-right:auto;border-radius:0;border:1px solid var(--solos-color-a)}.card-header[_ngcontent-%COMP%]{padding:0 15px;--min-height:0;--border-color:var(--solos-color-d);--border-width:0px 0px 1px 0px}.date[_ngcontent-%COMP%]{font-size:14px;color:var(--solos-color-a)}.time[_ngcontent-%COMP%]{font-size:14px;color:var(--solos-color-a);text-align:end}.card-body[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:justify;justify-content:space-between;padding:0 15px}.card-column[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column}.card-reading[_ngcontent-%COMP%]{color:var(--solos-color-a);margin:10px 0 5px;font-weight:700}.card-unit[_ngcontent-%COMP%]{text-align:center;color:var(--solos-color-c);margin:0;font-weight:100}"]],data:{}});function C(l){return i.Xb(0,[(l()(),i.Cb(0,0,null,null,31,"ion-card",[["class","card"]],null,[[null,"click"]],(function(l,n,t){var i=!0;return"click"===n&&(i=!1!==l.component.openCoreExerciseResult(l.context.$implicit.objectId)&&i),i}),p.M,p.e)),i.Bb(1,49152,null,0,o.o,[i.j,i.p,i.F],null,null),(l()(),i.Cb(2,0,null,0,7,"ion-item",[["class","card-header"],["no-padding",""]],null,null,null,p.W,p.o)),i.Bb(3,49152,null,0,o.J,[i.j,i.p,i.F],null,null),(l()(),i.Cb(4,0,null,0,2,"ion-label",[["class","date"]],null,null,null,p.X,p.p)),i.Bb(5,49152,null,0,o.P,[i.j,i.p,i.F],null,null),(l()(),i.Vb(6,0,[" "," "])),(l()(),i.Cb(7,0,null,0,2,"ion-label",[["class","time"]],null,null,null,p.X,p.p)),i.Bb(8,49152,null,0,o.P,[i.j,i.p,i.F],null,null),(l()(),i.Vb(9,0,[" "," "])),(l()(),i.Cb(10,0,null,0,21,"div",[["class","card-body"]],null,null,null,null,null)),(l()(),i.Cb(11,0,null,null,5,"div",[["class","card-column"]],null,null,null,null,null)),(l()(),i.Cb(12,0,null,null,1,"h3",[["class","card-reading"]],null,null,null,null,null)),(l()(),i.Vb(13,null,["",""])),(l()(),i.Cb(14,0,null,null,2,"p",[["class","card-unit"]],null,null,null,null,null)),(l()(),i.Vb(15,null,["",""])),i.Pb(131072,g.j,[g.k,i.j]),(l()(),i.Cb(17,0,null,null,8,"div",[["class","card-column"]],null,null,null,null,null)),(l()(),i.Cb(18,0,null,null,4,"h3",[["class","card-reading"]],null,null,null,null,null)),(l()(),i.Vb(19,null,["","",":",""])),i.Rb(20,2),i.Rb(21,2),i.Rb(22,2),(l()(),i.Cb(23,0,null,null,2,"p",[["class","card-unit"]],null,null,null,null,null)),(l()(),i.Vb(24,null,["",""])),i.Pb(131072,g.j,[g.k,i.j]),(l()(),i.Cb(26,0,null,null,5,"div",[["class","card-column"]],null,null,null,null,null)),(l()(),i.Cb(27,0,null,null,1,"h3",[["class","card-reading"]],null,null,null,null,null)),(l()(),i.Vb(28,null,["",""])),(l()(),i.Cb(29,0,null,null,2,"p",[["class","card-unit"]],null,null,null,null,null)),(l()(),i.Vb(30,null,["",""])),i.Pb(131072,g.j,[g.k,i.j])],null,(function(l,n){l(n,6,0,n.context.$implicit.date),l(n,9,0,n.context.$implicit.time),l(n,13,0,n.context.$implicit.calories),l(n,15,0,i.Wb(n,15,0,i.Ob(n,16).transform("core-training-history.calories")));var t=0==n.context.$implicit.elapsedHH?"":i.Wb(n,19,0,l(n,20,0,i.Ob(n.parent,0),n.context.$implicit.elapsedHH,"2.0-0"))+":",e=i.Wb(n,19,1,l(n,21,0,i.Ob(n.parent,0),n.context.$implicit.elapsedmm,"2.0-0")),o=i.Wb(n,19,2,l(n,22,0,i.Ob(n.parent,0),n.context.$implicit.elapsedss,"2.0-0"));l(n,19,0,t,e,o),l(n,24,0,i.Wb(n,24,0,i.Ob(n,25).transform("core-training-history.time"))),l(n,28,0,n.context.$implicit.sets),l(n,30,0,i.Wb(n,30,0,i.Ob(n,31).transform("core-training-history.set")))}))}function v(l){return i.Xb(0,[i.Pb(0,m.e,[i.z]),i.Tb(671088640,1,{virtualScroll:0}),i.Tb(671088640,2,{infiniteScroll:0}),(l()(),i.Cb(3,0,null,null,12,"ion-header",[],null,null,null,p.Q,p.i)),i.Bb(4,49152,null,0,o.D,[i.j,i.p,i.F],null,null),(l()(),i.Cb(5,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,p.ob,p.G)),i.Bb(6,49152,null,0,o.Db,[i.j,i.p,i.F],{mode:[0,"mode"]},null),(l()(),i.Cb(7,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,p.L,p.d)),i.Bb(8,49152,null,0,o.n,[i.j,i.p,i.F],null,null),(l()(),i.Cb(9,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==i.Ob(l,11).onClick(t)&&e),e}),p.J,p.b)),i.Bb(10,49152,null,0,o.i,[i.j,i.p,i.F],{text:[0,"text"]},null),i.Bb(11,16384,null,0,o.j,[[2,o.jb],o.Jb],null,null),(l()(),i.Cb(12,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,p.mb,p.E)),i.Bb(13,49152,null,0,o.Bb,[i.j,i.p,i.F],null,null),(l()(),i.Vb(14,0,["",""])),i.Pb(131072,g.j,[g.k,i.j]),(l()(),i.Cb(16,0,null,null,16,"ion-content",[["class","settings-background"]],null,null,null,p.O,p.g)),i.Bb(17,49152,null,0,o.w,[i.j,i.p,i.F],null,null),(l()(),i.Cb(18,0,null,0,14,"div",[["class","container"]],null,null,null,null,null)),(l()(),i.Cb(19,0,null,null,2,"div",[["class","total-activities"]],null,null,null,null,null)),(l()(),i.Vb(20,null,[" "," : "," "])),i.Pb(131072,g.j,[g.k,i.j]),(l()(),i.Cb(22,0,null,null,6,"ion-virtual-scroll",[["approxItemHeight","320px"]],null,null,null,p.pb,p.H)),i.Bb(23,835584,[[1,4],["virtualScroll",4]],3,o.Eb,[i.F,i.x,i.p],{approxItemHeight:[0,"approxItemHeight"],items:[1,"items"]},null),i.Tb(335544320,3,{itmTmp:0}),i.Tb(335544320,4,{hdrTmp:0}),i.Tb(335544320,5,{ftrTmp:0}),(l()(),i.rb(16777216,null,0,1,null,C)),i.Bb(28,16384,[[3,4]],0,o.Rb,[i.T,i.X],null,null),(l()(),i.Cb(29,0,null,null,3,"ion-infinite-scroll",[["threshold","100px"]],null,[[null,"ionInfinite"]],(function(l,n,t){var i=!0;return"ionInfinite"===n&&(i=!1!==l.component.loadMore(t)&&i),i}),p.U,p.l)),i.Bb(30,49152,[[2,4],["infiniteScroll",4]],0,o.G,[i.j,i.p,i.F],{threshold:[0,"threshold"]},null),(l()(),i.Cb(31,0,null,0,1,"ion-infinite-scroll-content",[["loadingSpinner","bubbles"]],null,null,null,p.T,p.m)),i.Bb(32,49152,null,0,o.H,[i.j,i.p,i.F],{loadingSpinner:[0,"loadingSpinner"]},null)],(function(l,n){var t=n.component;l(n,6,0,"ios"),l(n,10,0,""),l(n,23,0,"320px",t.coreTrainingList),l(n,30,0,"100px"),l(n,32,0,"bubbles")}),(function(l,n){var t=n.component;l(n,14,0,i.Wb(n,14,0,i.Ob(n,15).transform("core-training-history.core-training-history"))),l(n,20,0,i.Wb(n,20,0,i.Ob(n,21).transform("core-training-history.records")),t.totalRecords)}))}function M(l){return i.Xb(0,[(l()(),i.Cb(0,0,null,null,1,"app-core-training-history",[],null,null,null,v,x)),i.Bb(1,114688,null,0,c,[i.F,f.a,f.m,r.c,o.Jb],null,null)],(function(l,n){l(n,1,0)}),null)}var y=i.yb("app-core-training-history",c,M,{},{},[]),k=t("s7LF");t.d(n,"CoreTrainingHistoryPageModuleNgFactory",(function(){return T}));var T=i.zb(d,[],(function(l){return i.Lb([i.Mb(512,i.m,i.kb,[[8,[h.a,y]],[3,i.m],i.D]),i.Mb(4608,m.n,m.m,[i.z,[2,m.z]]),i.Mb(4608,k.j,k.j,[]),i.Mb(4608,o.c,o.c,[i.F,i.g]),i.Mb(4608,o.Ib,o.Ib,[o.c,i.m,i.w]),i.Mb(4608,o.Nb,o.Nb,[o.c,i.m,i.w]),i.Mb(4608,g.g,g.f,[]),i.Mb(4608,g.c,g.e,[]),i.Mb(4608,g.i,g.d,[]),i.Mb(4608,g.b,g.a,[]),i.Mb(4608,g.k,g.k,[g.l,g.g,g.c,g.i,g.b,g.m,g.n]),i.Mb(1073742336,m.b,m.b,[]),i.Mb(1073742336,k.i,k.i,[]),i.Mb(1073742336,k.b,k.b,[]),i.Mb(1073742336,o.Fb,o.Fb,[]),i.Mb(1073742336,f.o,f.o,[[2,f.t],[2,f.m]]),i.Mb(1073742336,g.h,g.h,[]),i.Mb(1073742336,d,d,[]),i.Mb(1024,f.k,(function(){return[[{path:"",component:c}]]}),[]),i.Mb(256,g.n,void 0,[]),i.Mb(256,g.m,void 0,[])])}))}}]);