function _defineProperties(t,n){for(var l=0;l<n.length;l++){var e=n[l];e.enumerable=e.enumerable||!1,e.configurable=!0,"value"in e&&(e.writable=!0),Object.defineProperty(t,e.key,e)}}function _createClass(t,n,l){return n&&_defineProperties(t.prototype,n),l&&_defineProperties(t,l),t}function _classCallCheck(t,n){if(!(t instanceof n))throw new TypeError("Cannot call a class as a function")}(window.webpackJsonp=window.webpackJsonp||[]).push([[44],{"/rCV":function(t,n,l){"use strict";l.r(n);var e=l("8Y7J"),i=l("mrSG"),a=l("ZZ/e"),s=l("VjBM"),r=l("lIDq"),o=l("Zesz"),u=function t(n,l){_classCallCheck(this,t),this.title=n,this.value=l},c=function(){function t(n,l,e,a,s,o,c,b){var p=this;_classCallCheck(this,t),this.navCtrl=n,this.platform=l,this.coachTraining=e,this.translate=a,this.decimalPipe=s,this.route=o,this.coachSettings=c,this.us=b,this.displayItems=[],this.pageTitle="",this.subscribeAction=null,this.isLandscapeMode=function(){return p.us.isLandscapeMode()},this.backToRoot=function(){return i.b(p,void 0,void 0,regeneratorRuntime.mark((function t(){var n,l;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:"stepCount"==this.type?(n={state:{activePage:"ai-care"}},this.isLandscapeMode()?this.navCtrl.navigateBack("",n):this.navCtrl.navigateBack("/tabs",n)):(l={state:{activePage:"ai-coach-util"}},this.isLandscapeMode()?this.navCtrl.navigateBack("",l):this.navCtrl.navigateBack("/tabs",l));case 1:case"end":return t.stop()}}),t,this)})))},this.handleMetricsData=function(t){if(null!=t){var n=t.movingTime,l=Math.floor(n/1e3/60)%60,e=Math.floor(n/1e3%60),i=Math.floor(n/1e3/3600),a=t.distance,s=p.translate.instant("training.total-time"),o=(0==i?"":i.toFixed(0)+":")+p.decimalPipe.transform(l,"2.0-0")+":"+p.decimalPipe.transform(e,"2.0-0"),c=new u(s,o),b=p.translate.instant("training.total-distance"),m=p.decimalPipe.transform(a,"1.2-2")+" "+p.distanceUnit,d=new u(b,m);if("posture"==p.type){var f=t.correctPostureCnt,h=t.incorrectPostureCnt,g=f+h?100*f/(f+h):0,v=p.translate.instant("posture-training.correct-posture"),C=p.decimalPipe.transform(g,"1.0-0")+"%",k=new u(v,C);p.displayItems.push(c,d,k)}else if("cadence"==p.type){var w=t.avgCadence,M=100*t.avgCadence/t.targetCadence,y=p.translate.instant("cadence-training.avg-cadence"),x=p.decimalPipe.transform(w,"1.0-0")+" spm",P=new u(y,x),T=p.translate.instant("cadence-training.achievement"),I=p.decimalPipe.transform(M,"1.0-0")+"%",B=new u(T,I);p.displayItems.push(c,d,P,B)}else if("interval"==p.type)if("distance"==t.type){var F=Number(t.runDistance),_=Number(t.restTime),j=p.translate.instant("interval-training.total-run-distance"),O=p.decimalPipe.transform(F,"1.2-2")+" ";p.coachSettings.getUnit()==r.d?O+=p.translate.instant("ai-coach.miles-text"):O+=p.translate.instant("ai-coach.km");var V=p.translate.instant("interval-training.total-rest-time"),z=p.decimalPipe.transform(Math.floor(_/60),"2.0-0")+":"+p.decimalPipe.transform(Math.floor(_%60),"2.0-0"),L=new u(j,O),S=new u(V,z);p.displayItems.push(c,d,L,S)}else{var X=Number(t.runTime),D=Number(t.restTime),N=p.translate.instant("interval-training.total-run-time"),R=p.decimalPipe.transform(Math.floor(X/60),"2.0-0")+":"+p.decimalPipe.transform(Math.floor(X%60),"2.0-0"),Z=p.translate.instant("interval-training.total-rest-time"),A=p.decimalPipe.transform(Math.floor(D/60),"2.0-0")+":"+p.decimalPipe.transform(Math.floor(D%60),"2.0-0"),E=new u(N,R),J=new u(Z,A);p.displayItems.push(c,d,E,J)}else if("stepCount"==p.type){var U=t.targetStepCount,W=t.accuStepCount,$=Number(t.stepCount);$<0&&($=0);var G=100*(W+$)/U,q=p.translate.instant("step-count.step-count"),K=p.decimalPipe.transform($,"1.0-0"),Q=new u(q,K),Y=p.translate.instant("step-count.daily-goal-achievement"),H=p.decimalPipe.transform(G,"1.0-0")+"%",tt=new u(Y,H);p.displayItems.push(c,Q,tt)}else if("fatburn"==p.type){p.displayItems.push(c,d);var nt=t.stepCount>=0?t.stepCount:0,lt=p.translate.instant("ai-coach.step-count"),et=p.decimalPipe.transform(nt,"1.0-0");if(p.displayItems.push(new u(lt,et)),t.calories>0){var it=p.translate.instant("ai-coach.calories"),at=p.decimalPipe.transform(t.calories,"1.0-0");p.displayItems.push(new u(it,at))}var st=p.translate.instant("fat-burn.fast-walk-avg-spm"),rt=p.decimalPipe.transform(t.fastWalkAvgCadence,"1.0-0")+" "+p.translate.instant("training.spm");p.displayItems.push(new u(st,rt))}}},this.type=this.route.snapshot.paramMap.get("type"),"posture"==this.type?this.pageTitle=this.translate.instant("posture-training.title"):"cadence"==this.type?this.pageTitle=this.translate.instant("cadence-training.title"):"interval"==this.type?this.pageTitle=this.translate.instant("interval-training.title"):"stepCount"==this.type?this.pageTitle=this.translate.instant("step-count.title"):"fatburn"==this.type&&(this.pageTitle=this.translate.instant("fat-burn.title"))}return _createClass(t,[{key:"ngOnInit",value:function(){}},{key:"ionViewWillEnter",value:function(){var t=this;this.distanceUnit=this.coachSettings.getUnit()==r.d?this.translate.instant("ai-coach.miles-text"):this.translate.instant("ai-coach.km"),this.handleMetricsData(this.coachTraining.getCoachMetricData()),this.subscribeAction=this.platform.backButton.subscribeWithPriority(8964,(function(){t.backToRoot()}))}},{key:"ionViewDidEnter",value:function(){}},{key:"ionViewWillLeave",value:function(){this.subscribeAction.unsubscribe()}}]),t}(),b=function t(){_classCallCheck(this,t)},p=l("pMnS"),m=l("SVse"),d=l("oBZk"),f=l("TSSN"),h=l("iInd"),g=e.Ab({encapsulation:0,styles:[[".detail-container-portrait[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center}.item[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-flex:1;flex:1}.item-title[_ngcontent-%COMP%]{color:var(--solos-color-a);text-transform:uppercase;text-align:center;margin:40px 0 0;font-size:18px}.item-value[_ngcontent-%COMP%]{color:var(--solos-color-d);text-align:center;font-size:37px;margin:20px 0 0}"],[".detail-container-landscape[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;flex-wrap:wrap}@media (min-width:1025px) and (max-width:1280px){.item-title[_ngcontent-%COMP%], .item-value[_ngcontent-%COMP%]{font-size:6em}}"]],data:{}});function v(t){return e.Xb(0,[(t()(),e.Cb(0,0,null,null,4,"div",[],null,null,null,null,null)),(t()(),e.Cb(1,0,null,null,1,"div",[["class","item-title"]],null,null,null,null,null)),(t()(),e.Vb(2,null,[" "," "])),(t()(),e.Cb(3,0,null,null,1,"div",[["class","item-value"]],null,null,null,null,null)),(t()(),e.Vb(4,null,[" "," "]))],null,(function(t,n){t(n,2,0,n.context.$implicit.title),t(n,4,0,n.context.$implicit.value)}))}function C(t){return e.Xb(0,[(t()(),e.Cb(0,0,null,null,2,"div",[["class","detail-container-portrait"]],null,null,null,null,null)),(t()(),e.rb(16777216,null,null,1,null,v)),e.Bb(2,278528,null,0,m.k,[e.X,e.T,e.x],{ngForOf:[0,"ngForOf"]},null)],(function(t,n){t(n,2,0,n.component.displayItems)}),null)}function k(t){return e.Xb(0,[(t()(),e.Cb(0,0,null,null,4,"div",[["class","item"]],null,null,null,null,null)),(t()(),e.Cb(1,0,null,null,1,"div",[["class","item-title"]],null,null,null,null,null)),(t()(),e.Vb(2,null,[" "," "])),(t()(),e.Cb(3,0,null,null,1,"div",[["class","item-value"]],null,null,null,null,null)),(t()(),e.Vb(4,null,[" "," "]))],null,(function(t,n){t(n,2,0,n.context.$implicit.title),t(n,4,0,n.context.$implicit.value)}))}function w(t){return e.Xb(0,[(t()(),e.Cb(0,0,null,null,2,"div",[["class","detail-container-landscape"]],null,null,null,null,null)),(t()(),e.rb(16777216,null,null,1,null,k)),e.Bb(2,278528,null,0,m.k,[e.X,e.T,e.x],{ngForOf:[0,"ngForOf"]},null)],(function(t,n){t(n,2,0,n.component.displayItems)}),null)}function M(t){return e.Xb(0,[(t()(),e.Cb(0,0,null,null,12,"ion-header",[["style","background: var(--solos-color-b);"]],null,null,null,d.Q,d.i)),e.Bb(1,49152,null,0,a.D,[e.j,e.p,e.F],null,null),(t()(),e.Cb(2,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,d.ob,d.G)),e.Bb(3,49152,null,0,a.Db,[e.j,e.p,e.F],{mode:[0,"mode"]},null),(t()(),e.Cb(4,0,null,0,5,"ion-buttons",[["slot","start"]],null,null,null,d.L,d.d)),e.Bb(5,49152,null,0,a.n,[e.j,e.p,e.F],null,null),(t()(),e.Cb(6,0,null,0,3,"ion-button",[["color","solos-color-d"]],null,[[null,"click"]],(function(t,n,l){var e=!0;return"click"===n&&(e=!1!==t.component.backToRoot()&&e),e}),d.K,d.c)),e.Bb(7,49152,null,0,a.m,[e.j,e.p,e.F],{color:[0,"color"]},null),(t()(),e.Cb(8,0,null,0,1,"ion-icon",[["name","arrow-back"],["size","large"],["slot","start"]],null,null,null,d.R,d.j)),e.Bb(9,49152,null,0,a.E,[e.j,e.p,e.F],{name:[0,"name"],size:[1,"size"]},null),(t()(),e.Cb(10,0,null,0,2,"ion-title",[["class","settings-header-title"]],null,null,null,d.mb,d.E)),e.Bb(11,49152,null,0,a.Bb,[e.j,e.p,e.F],null,null),(t()(),e.Vb(12,0,["",""])),(t()(),e.Cb(13,0,null,null,5,"ion-content",[["class","app-background"]],null,null,null,d.O,d.g)),e.Bb(14,49152,null,0,a.w,[e.j,e.p,e.F],null,null),(t()(),e.rb(16777216,null,0,1,null,C)),e.Bb(16,16384,null,0,m.l,[e.X,e.T],{ngIf:[0,"ngIf"]},null),(t()(),e.rb(16777216,null,0,1,null,w)),e.Bb(18,16384,null,0,m.l,[e.X,e.T],{ngIf:[0,"ngIf"]},null)],(function(t,n){var l=n.component;t(n,3,0,"ios"),t(n,7,0,"solos-color-d"),t(n,9,0,"arrow-back","large"),t(n,16,0,!l.isLandscapeMode()),t(n,18,0,l.isLandscapeMode())}),(function(t,n){t(n,12,0,n.component.pageTitle)}))}var y=e.yb("app-training-result",c,(function(t){return e.Xb(0,[(t()(),e.Cb(0,0,null,null,1,"app-training-result",[],null,null,null,M,g)),e.Bb(1,114688,null,0,c,[a.Jb,a.Mb,s.a,f.k,m.e,h.a,r.a,o.a],null,null)],(function(t,n){t(n,1,0)}),null)}),{},{},[]),x=l("s7LF"),P=l("hrfs"),T=l("j1ZV");l.d(n,"TrainingResultPageModuleNgFactory",(function(){return I}));var I=e.zb(b,[],(function(t){return e.Lb([e.Mb(512,e.m,e.kb,[[8,[p.a,y]],[3,e.m],e.D]),e.Mb(4608,m.n,m.m,[e.z,[2,m.z]]),e.Mb(4608,x.j,x.j,[]),e.Mb(4608,a.c,a.c,[e.F,e.g]),e.Mb(4608,a.Ib,a.Ib,[a.c,e.m,e.w]),e.Mb(4608,a.Nb,a.Nb,[a.c,e.m,e.w]),e.Mb(4608,f.g,f.f,[]),e.Mb(4608,f.c,f.e,[]),e.Mb(4608,f.i,f.d,[]),e.Mb(4608,f.b,f.a,[]),e.Mb(4608,f.k,f.k,[f.l,f.g,f.c,f.i,f.b,f.m,f.n]),e.Mb(1073742336,m.b,m.b,[]),e.Mb(1073742336,x.i,x.i,[]),e.Mb(1073742336,x.b,x.b,[]),e.Mb(1073742336,a.Fb,a.Fb,[]),e.Mb(1073742336,P.b,P.b,[]),e.Mb(1073742336,f.h,f.h,[]),e.Mb(1073742336,T.a,T.a,[]),e.Mb(1073742336,h.o,h.o,[[2,h.t],[2,h.m]]),e.Mb(1073742336,b,b,[]),e.Mb(256,f.n,void 0,[]),e.Mb(256,f.m,void 0,[]),e.Mb(1024,h.k,(function(){return[[{path:"",component:c}]]}),[])])}))},j1ZV:function(t,n,l){"use strict";l.d(n,"a",(function(){return e}));var e=function t(){_classCallCheck(this,t)}}}]);