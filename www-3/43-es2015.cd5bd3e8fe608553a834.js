(window.webpackJsonp=window.webpackJsonp||[]).push([[43],{OODR:function(l,n,t){"use strict";t.r(n);var e=t("8Y7J"),o=t("mrSG"),s=t("ZZ/e"),i=t("OUMn"),u=t("h5tO");class r{constructor(l,n,t){this.navCtrl=l,this.popupCtrl=n,this.activityStateManager=t,this.isEasy=!0,this.isNormal=!1,this.isExpert=!1,this.ExerciseLevel="Easy",this.startExercise=()=>o.b(this,void 0,void 0,(function*(){try{const l=[...u.c,u.a.BASIC_TRAINING,u.a.CORE_TRAINING,u.a.POSTURE_MONITORING],n=this.activityStateManager.validate(l,u.a.STRETCH,"/stretch-util");n.status?this.navCtrl.navigateForward("/stretch/"+this.ExerciseLevel+"/0"):yield this.popupCtrl.presentAlertController({header:n.header,message:n.message,buttons:n.buttons},!0)}catch(l){console.log(l)}}))}ngOnInit(){}ionViewWillEnter(){}ionViewWillLeave(){}turnEasy(){this.ExerciseLevel="Easy",this.isEasy=!0,this.isNormal=!1,this.isExpert=!1}turnNormal(){this.ExerciseLevel="Normal",this.isEasy=!1,this.isNormal=!0,this.isExpert=!1}turnExpert(){this.ExerciseLevel="Expert",this.isEasy=!1,this.isNormal=!1,this.isExpert=!0}}class a{}var c=t("pMnS"),b=t("oBZk"),p=t("TSSN"),g=t("SVse"),d=e.Ab({encapsulation:0,styles:[['.intro-text-container[_ngcontent-%COMP%]{text-align:center;padding:1rem .5rem 1.5rem;position:relative;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;margin-bottom:1rem}.intro-text-container[_ngcontent-%COMP%]::before{content:"";background-image:url(stretch-exercise-banner.659bb088429690c3f2f6.png);background-size:cover;position:absolute;top:0;right:0;bottom:0;left:0;opacity:.5}.intro-text[_ngcontent-%COMP%]{color:var(--solos-color-a);position:relative}.choose-level-title[_ngcontent-%COMP%]{font-size:14px;text-align:center;color:var(--solos-color-a);text-transform:uppercase}.level-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:justify;justify-content:space-between;margin:10px 40px}.level-selection[_ngcontent-%COMP%]{flex-basis:30%;font-size:12px;text-align:center;padding:12px 0;color:var(--solos-color-a)}.active[_ngcontent-%COMP%]{background:var(--solos-color-d);border-color:var(--solos-color-b)}.inactive[_ngcontent-%COMP%]{background:0 0;border-color:var(--solos-color-a);border-style:solid;border-width:1px}.both-image-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;margin:0 40px}.image-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;width:50%;padding-top:62.5%;position:relative}.image-content[_ngcontent-%COMP%]{width:100%;height:100%;-o-object-fit:contain;object-fit:contain;border:0 transparent;position:absolute;top:0;bottom:0;left:0;right:0}.exercise-list-text[_ngcontent-%COMP%]{font-size:15px;color:var(--solos-color-a);text-align:center;text-transform:uppercase;margin-top:5px;margin-bottom:25px}.footer-buttons[_ngcontent-%COMP%]{display:-webkit-box;display:flex;padding:12px;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;background:var(--solos-color-b);border-width:1px 0 0;border-style:solid;border-color:var(--solos-color-a)}.start-button[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-align:center;align-items:center;width:60%}.footer-padding[_ngcontent-%COMP%]{height:calc(env(safe-area-inset-bottom));background:var(--solos-color-b)}']],data:{}});function m(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,12,"ion-header",[["style","background: var(--solos-color-b);"]],null,null,null,b.Q,b.i)),e.Bb(1,49152,null,0,s.D,[e.j,e.p,e.F],null,null),(l()(),e.Cb(2,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,b.ob,b.G)),e.Bb(3,49152,null,0,s.Db,[e.j,e.p,e.F],{mode:[0,"mode"]},null),(l()(),e.Cb(4,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,b.L,b.d)),e.Bb(5,49152,null,0,s.n,[e.j,e.p,e.F],null,null),(l()(),e.Cb(6,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(l,n,t){var o=!0;return"click"===n&&(o=!1!==e.Ob(l,8).onClick(t)&&o),o}),b.J,b.b)),e.Bb(7,49152,null,0,s.i,[e.j,e.p,e.F],{text:[0,"text"]},null),e.Bb(8,16384,null,0,s.j,[[2,s.jb],s.Jb],null,null),(l()(),e.Cb(9,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,b.mb,b.E)),e.Bb(10,49152,null,0,s.Bb,[e.j,e.p,e.F],null,null),(l()(),e.Vb(11,0,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(13,0,null,null,41,"ion-content",[["class","app-background"]],null,null,null,b.O,b.g)),e.Bb(14,49152,null,0,s.w,[e.j,e.p,e.F],null,null),(l()(),e.Cb(15,0,null,0,23,"div",[["class","main-container"]],null,null,null,null,null)),(l()(),e.Cb(16,0,null,null,3,"div",[["class","intro-text-container"]],null,null,null,null,null)),(l()(),e.Cb(17,0,null,null,2,"div",[["class","intro-text"]],null,null,null,null,null)),(l()(),e.Vb(18,null,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(20,0,null,null,2,"div",[["class","choose-level-title"]],null,null,null,null,null)),(l()(),e.Vb(21,null,[" "," "])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(23,0,null,null,15,"div",[["class","level-container"]],null,null,null,null,null)),(l()(),e.Cb(24,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.turnEasy()&&e),e}),null,null)),e.Sb(512,null,g.u,g.v,[e.x,e.y,e.p,e.L]),e.Bb(26,278528,null,0,g.j,[g.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),e.Vb(27,null,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(29,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.turnNormal()&&e),e}),null,null)),e.Sb(512,null,g.u,g.v,[e.x,e.y,e.p,e.L]),e.Bb(31,278528,null,0,g.j,[g.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),e.Vb(32,null,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(34,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.turnExpert()&&e),e}),null,null)),e.Sb(512,null,g.u,g.v,[e.x,e.y,e.p,e.L]),e.Bb(36,278528,null,0,g.j,[g.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),e.Vb(37,null,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(39,0,null,0,4,"div",[["class","both-image-container"]],null,null,null,null,null)),(l()(),e.Cb(40,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),e.Cb(41,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/oblique-stretch-left.png"]],null,null,null,null,null)),(l()(),e.Cb(42,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),e.Cb(43,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/oblique-stretch-right.png"]],null,null,null,null,null)),(l()(),e.Cb(44,0,null,0,2,"div",[["class","exercise-list-text"]],null,null,null,null,null)),(l()(),e.Vb(45,null,[" "," "])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(47,0,null,0,4,"div",[["class","both-image-container"]],null,null,null,null,null)),(l()(),e.Cb(48,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),e.Cb(49,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/twist-left.png"]],null,null,null,null,null)),(l()(),e.Cb(50,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),e.Cb(51,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/twist-right.png"]],null,null,null,null,null)),(l()(),e.Cb(52,0,null,0,2,"div",[["class","exercise-list-text"]],null,null,null,null,null)),(l()(),e.Vb(53,null,[" "," "])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(55,0,null,null,7,"ion-footer",[],null,null,null,b.P,b.h)),e.Bb(56,49152,null,0,s.B,[e.j,e.p,e.F],null,null),(l()(),e.Cb(57,0,null,0,4,"div",[["class","footer-buttons"]],null,null,null,null,null)),(l()(),e.Cb(58,0,null,null,3,"ion-button",[["class","start-button"],["color","solos-color-a"],["shape","round"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.startExercise()&&e),e}),b.K,b.c)),e.Bb(59,49152,null,0,s.m,[e.j,e.p,e.F],{color:[0,"color"],shape:[1,"shape"]},null),(l()(),e.Vb(60,0,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(62,0,null,0,0,"div",[["class","footer-padding"]],null,null,null,null,null))],(function(l,n){var t=n.component;l(n,3,0,"ios"),l(n,7,0,""),l(n,26,0,"level-selection",t.isEasy?"active":"inactive"),l(n,31,0,"level-selection",t.isNormal?"active":"inactive"),l(n,36,0,"level-selection",t.isExpert?"active":"inactive"),l(n,59,0,"solos-color-a","round")}),(function(l,n){l(n,11,0,e.Wb(n,11,0,e.Ob(n,12).transform("ai-care.stretch-exercise"))),l(n,18,0,e.Wb(n,18,0,e.Ob(n,19).transform("stretch.intro"))),l(n,21,0,e.Wb(n,21,0,e.Ob(n,22).transform("exercise.choose-level"))),l(n,27,0,e.Wb(n,27,0,e.Ob(n,28).transform("exercise.easy"))),l(n,32,0,e.Wb(n,32,0,e.Ob(n,33).transform("exercise.normal"))),l(n,37,0,e.Wb(n,37,0,e.Ob(n,38).transform("exercise.expert"))),l(n,45,0,e.Wb(n,45,0,e.Ob(n,46).transform("stretch.obliques-stretch"))),l(n,53,0,e.Wb(n,53,0,e.Ob(n,54).transform("stretch.waist-twist"))),l(n,60,0,e.Wb(n,60,0,e.Ob(n,61).transform("ai-coach.start")))}))}function v(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,1,"app-stretch-util",[],null,null,null,m,d)),e.Bb(1,114688,null,0,r,[s.Jb,i.a,u.b],null,null)],(function(l,n){l(n,1,0)}),null)}var x=e.yb("app-stretch-util",r,v,{},{},[]),h=t("s7LF"),k=t("hrfs"),f=t("j1ZV"),C=t("iInd");t.d(n,"StretchUtilPageModuleNgFactory",(function(){return M}));var M=e.zb(a,[],(function(l){return e.Lb([e.Mb(512,e.m,e.kb,[[8,[c.a,x]],[3,e.m],e.D]),e.Mb(4608,g.n,g.m,[e.z,[2,g.z]]),e.Mb(4608,h.j,h.j,[]),e.Mb(4608,s.c,s.c,[e.F,e.g]),e.Mb(4608,s.Ib,s.Ib,[s.c,e.m,e.w]),e.Mb(4608,s.Nb,s.Nb,[s.c,e.m,e.w]),e.Mb(4608,p.g,p.f,[]),e.Mb(4608,p.c,p.e,[]),e.Mb(4608,p.i,p.d,[]),e.Mb(4608,p.b,p.a,[]),e.Mb(4608,p.k,p.k,[p.l,p.g,p.c,p.i,p.b,p.m,p.n]),e.Mb(1073742336,g.b,g.b,[]),e.Mb(1073742336,h.i,h.i,[]),e.Mb(1073742336,h.b,h.b,[]),e.Mb(1073742336,s.Fb,s.Fb,[]),e.Mb(1073742336,k.b,k.b,[]),e.Mb(1073742336,p.h,p.h,[]),e.Mb(1073742336,f.a,f.a,[]),e.Mb(1073742336,C.o,C.o,[[2,C.t],[2,C.m]]),e.Mb(1073742336,a,a,[]),e.Mb(256,p.n,void 0,[]),e.Mb(256,p.m,void 0,[]),e.Mb(1024,C.k,(function(){return[[{path:"",component:r}]]}),[])])}))},j1ZV:function(l,n,t){"use strict";t.d(n,"a",(function(){return e}));class e{}}}]);