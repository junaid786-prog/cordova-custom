function _classCallCheck(n,t){if(!(n instanceof t))throw new TypeError("Cannot call a class as a function")}function _defineProperties(n,t){for(var l=0;l<t.length;l++){var e=t[l];e.enumerable=e.enumerable||!1,e.configurable=!0,"value"in e&&(e.writable=!0),Object.defineProperty(n,e.key,e)}}function _createClass(n,t,l){return t&&_defineProperties(n.prototype,t),l&&_defineProperties(n,l),n}(window.webpackJsonp=window.webpackJsonp||[]).push([[37],{QTWB:function(n,t,l){"use strict";l.r(t);var e=l("8Y7J"),u=function(){function n(t,l){var e=this;_classCallCheck(this,n),this.route=t,this.router=l,this.trainingResult=[],this.totalDurationInMS=0,this.calories=0,this.route.queryParams.subscribe((function(n){e.router.getCurrentNavigation().extras.state&&(e.starttime=e.router.getCurrentNavigation().extras.state.starttime,e.totalDurationInMS=e.router.getCurrentNavigation().extras.state.totalDurationInMS,e.calories=e.router.getCurrentNavigation().extras.state.calories,e.trainingResult=e.router.getCurrentNavigation().extras.state.trainingResult)}))}return _createClass(n,[{key:"ngOnInit",value:function(){}}]),n}(),r=function n(){_classCallCheck(this,n)},i=l("pMnS"),a=l("oBZk"),o=l("ZZ/e"),s=l("TSSN"),b=l("g/yO"),c=l("8ChU"),g=l("iInd"),f=e.Ab({encapsulation:0,styles:[[""]],data:{}});function p(n){return e.Xb(0,[(n()(),e.Cb(0,0,null,null,12,"ion-header",[],null,null,null,a.Q,a.i)),e.Bb(1,49152,null,0,o.D,[e.j,e.p,e.F],null,null),(n()(),e.Cb(2,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,a.ob,a.G)),e.Bb(3,49152,null,0,o.Db,[e.j,e.p,e.F],{mode:[0,"mode"]},null),(n()(),e.Cb(4,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,a.L,a.d)),e.Bb(5,49152,null,0,o.n,[e.j,e.p,e.F],null,null),(n()(),e.Cb(6,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(n,t,l){var u=!0;return"click"===t&&(u=!1!==e.Ob(n,8).onClick(l)&&u),u}),a.J,a.b)),e.Bb(7,49152,null,0,o.i,[e.j,e.p,e.F],{text:[0,"text"]},null),e.Bb(8,16384,null,0,o.j,[[2,o.jb],o.Jb],null,null),(n()(),e.Cb(9,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,a.mb,a.E)),e.Bb(10,49152,null,0,o.Bb,[e.j,e.p,e.F],null,null),(n()(),e.Vb(11,0,["",""])),e.Pb(131072,s.j,[s.k,e.j]),(n()(),e.Cb(13,0,null,null,3,"ion-content",[["class","app-background"]],null,null,null,a.O,a.g)),e.Bb(14,49152,null,0,o.w,[e.j,e.p,e.F],null,null),(n()(),e.Cb(15,0,null,0,1,"app-core-training-result",[],null,null,null,b.b,b.a)),e.Bb(16,114688,null,0,c.a,[s.k],{trainingResult:[0,"trainingResult"],calories:[1,"calories"],totalTimeInMS:[2,"totalTimeInMS"],starttime:[3,"starttime"]},null)],(function(n,t){var l=t.component;n(t,3,0,"ios"),n(t,7,0,""),n(t,16,0,l.trainingResult,l.calories,l.totalDurationInMS,l.starttime)}),(function(n,t){n(t,11,0,e.Wb(t,11,0,e.Ob(t,12).transform("core-training-result.core-training-result")))}))}var M=e.yb("app-core-training-result-container",u,(function(n){return e.Xb(0,[(n()(),e.Cb(0,0,null,null,1,"app-core-training-result-container",[],null,null,null,p,f)),e.Bb(1,114688,null,0,u,[g.a,g.m],null,null)],(function(n,t){n(t,1,0)}),null)}),{},{},[]),C=l("SVse"),m=l("s7LF"),h=l("hrfs"),d=l("j1ZV");l.d(t,"CoreTrainingResultContainerPageModuleNgFactory",(function(){return k}));var k=e.zb(r,[],(function(n){return e.Lb([e.Mb(512,e.m,e.kb,[[8,[i.a,M]],[3,e.m],e.D]),e.Mb(4608,C.n,C.m,[e.z,[2,C.z]]),e.Mb(4608,m.j,m.j,[]),e.Mb(4608,o.c,o.c,[e.F,e.g]),e.Mb(4608,o.Ib,o.Ib,[o.c,e.m,e.w]),e.Mb(4608,o.Nb,o.Nb,[o.c,e.m,e.w]),e.Mb(4608,s.g,s.f,[]),e.Mb(4608,s.c,s.e,[]),e.Mb(4608,s.i,s.d,[]),e.Mb(4608,s.b,s.a,[]),e.Mb(4608,s.k,s.k,[s.l,s.g,s.c,s.i,s.b,s.m,s.n]),e.Mb(1073742336,C.b,C.b,[]),e.Mb(1073742336,m.i,m.i,[]),e.Mb(1073742336,m.b,m.b,[]),e.Mb(1073742336,o.Fb,o.Fb,[]),e.Mb(1073742336,h.b,h.b,[]),e.Mb(1073742336,s.h,s.h,[]),e.Mb(1073742336,d.a,d.a,[]),e.Mb(1073742336,g.o,g.o,[[2,g.t],[2,g.m]]),e.Mb(1073742336,r,r,[]),e.Mb(256,s.n,void 0,[]),e.Mb(256,s.m,void 0,[]),e.Mb(1024,g.k,(function(){return[[{path:"",component:u}]]}),[])])}))},j1ZV:function(n,t,l){"use strict";l.d(t,"a",(function(){return e}));var e=function n(){_classCallCheck(this,n)}}}]);