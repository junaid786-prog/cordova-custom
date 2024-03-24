function _toConsumableArray(l){return _arrayWithoutHoles(l)||_iterableToArray(l)||_unsupportedIterableToArray(l)||_nonIterableSpread()}function _nonIterableSpread(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}function _unsupportedIterableToArray(l,n){if(l){if("string"==typeof l)return _arrayLikeToArray(l,n);var t=Object.prototype.toString.call(l).slice(8,-1);return"Object"===t&&l.constructor&&(t=l.constructor.name),"Map"===t||"Set"===t?Array.from(l):"Arguments"===t||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(t)?_arrayLikeToArray(l,n):void 0}}function _iterableToArray(l){if("undefined"!=typeof Symbol&&Symbol.iterator in Object(l))return Array.from(l)}function _arrayWithoutHoles(l){if(Array.isArray(l))return _arrayLikeToArray(l)}function _arrayLikeToArray(l,n){(null==n||n>l.length)&&(n=l.length);for(var t=0,e=new Array(n);t<n;t++)e[t]=l[t];return e}function _classCallCheck(l,n){if(!(l instanceof n))throw new TypeError("Cannot call a class as a function")}function _defineProperties(l,n){for(var t=0;t<n.length;t++){var e=n[t];e.enumerable=e.enumerable||!1,e.configurable=!0,"value"in e&&(e.writable=!0),Object.defineProperty(l,e.key,e)}}function _createClass(l,n,t){return n&&_defineProperties(l.prototype,n),t&&_defineProperties(l,t),l}(window.webpackJsonp=window.webpackJsonp||[]).push([[43],{OODR:function(l,n,t){"use strict";t.r(n);var e=t("8Y7J"),r=t("mrSG"),o=t("ZZ/e"),i=t("OUMn"),a=t("h5tO"),u=function(){function l(n,t,e){var o=this;_classCallCheck(this,l),this.navCtrl=n,this.popupCtrl=t,this.activityStateManager=e,this.isEasy=!0,this.isNormal=!1,this.isExpert=!1,this.ExerciseLevel="Easy",this.startExercise=function(){return r.b(o,void 0,void 0,regeneratorRuntime.mark((function l(){var n,t;return regeneratorRuntime.wrap((function(l){for(;;)switch(l.prev=l.next){case 0:if(l.prev=0,n=[].concat(_toConsumableArray(a.c),[a.a.BASIC_TRAINING,a.a.CORE_TRAINING,a.a.POSTURE_MONITORING]),!(t=this.activityStateManager.validate(n,a.a.STRETCH,"/stretch-util")).status){l.next=6;break}this.navCtrl.navigateForward("/stretch/"+this.ExerciseLevel+"/0"),l.next=8;break;case 6:return l.next=8,this.popupCtrl.presentAlertController({header:t.header,message:t.message,buttons:t.buttons},!0);case 8:l.next=13;break;case 10:l.prev=10,l.t0=l.catch(0),console.log(l.t0);case 13:case"end":return l.stop()}}),l,this,[[0,10]])})))}}return _createClass(l,[{key:"ngOnInit",value:function(){}},{key:"ionViewWillEnter",value:function(){}},{key:"ionViewWillLeave",value:function(){}},{key:"turnEasy",value:function(){this.ExerciseLevel="Easy",this.isEasy=!0,this.isNormal=!1,this.isExpert=!1}},{key:"turnNormal",value:function(){this.ExerciseLevel="Normal",this.isEasy=!1,this.isNormal=!0,this.isExpert=!1}},{key:"turnExpert",value:function(){this.ExerciseLevel="Expert",this.isEasy=!1,this.isNormal=!1,this.isExpert=!0}}]),l}(),s=function l(){_classCallCheck(this,l)},c=t("pMnS"),b=t("oBZk"),p=t("TSSN"),d=t("SVse"),g=e.Ab({encapsulation:0,styles:[['.intro-text-container[_ngcontent-%COMP%]{text-align:center;padding:1rem .5rem 1.5rem;position:relative;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;margin-bottom:1rem}.intro-text-container[_ngcontent-%COMP%]::before{content:"";background-image:url(stretch-exercise-banner.659bb088429690c3f2f6.png);background-size:cover;position:absolute;top:0;right:0;bottom:0;left:0;opacity:.5}.intro-text[_ngcontent-%COMP%]{color:var(--solos-color-a);position:relative}.choose-level-title[_ngcontent-%COMP%]{font-size:14px;text-align:center;color:var(--solos-color-a);text-transform:uppercase}.level-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:justify;justify-content:space-between;margin:10px 40px}.level-selection[_ngcontent-%COMP%]{flex-basis:30%;font-size:12px;text-align:center;padding:12px 0;color:var(--solos-color-a)}.active[_ngcontent-%COMP%]{background:var(--solos-color-d);border-color:var(--solos-color-b)}.inactive[_ngcontent-%COMP%]{background:0 0;border-color:var(--solos-color-a);border-style:solid;border-width:1px}.both-image-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;margin:0 40px}.image-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;width:50%;padding-top:62.5%;position:relative}.image-content[_ngcontent-%COMP%]{width:100%;height:100%;-o-object-fit:contain;object-fit:contain;border:0 transparent;position:absolute;top:0;bottom:0;left:0;right:0}.exercise-list-text[_ngcontent-%COMP%]{font-size:15px;color:var(--solos-color-a);text-align:center;text-transform:uppercase;margin-top:5px;margin-bottom:25px}.footer-buttons[_ngcontent-%COMP%]{display:-webkit-box;display:flex;padding:12px;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;background:var(--solos-color-b);border-width:1px 0 0;border-style:solid;border-color:var(--solos-color-a)}.start-button[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-align:center;align-items:center;width:60%}.footer-padding[_ngcontent-%COMP%]{height:calc(env(safe-area-inset-bottom));background:var(--solos-color-b)}']],data:{}});function f(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,12,"ion-header",[["style","background: var(--solos-color-b);"]],null,null,null,b.Q,b.i)),e.Bb(1,49152,null,0,o.D,[e.j,e.p,e.F],null,null),(l()(),e.Cb(2,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,b.ob,b.G)),e.Bb(3,49152,null,0,o.Db,[e.j,e.p,e.F],{mode:[0,"mode"]},null),(l()(),e.Cb(4,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,b.L,b.d)),e.Bb(5,49152,null,0,o.n,[e.j,e.p,e.F],null,null),(l()(),e.Cb(6,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(l,n,t){var r=!0;return"click"===n&&(r=!1!==e.Ob(l,8).onClick(t)&&r),r}),b.J,b.b)),e.Bb(7,49152,null,0,o.i,[e.j,e.p,e.F],{text:[0,"text"]},null),e.Bb(8,16384,null,0,o.j,[[2,o.jb],o.Jb],null,null),(l()(),e.Cb(9,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,b.mb,b.E)),e.Bb(10,49152,null,0,o.Bb,[e.j,e.p,e.F],null,null),(l()(),e.Vb(11,0,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(13,0,null,null,41,"ion-content",[["class","app-background"]],null,null,null,b.O,b.g)),e.Bb(14,49152,null,0,o.w,[e.j,e.p,e.F],null,null),(l()(),e.Cb(15,0,null,0,23,"div",[["class","main-container"]],null,null,null,null,null)),(l()(),e.Cb(16,0,null,null,3,"div",[["class","intro-text-container"]],null,null,null,null,null)),(l()(),e.Cb(17,0,null,null,2,"div",[["class","intro-text"]],null,null,null,null,null)),(l()(),e.Vb(18,null,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(20,0,null,null,2,"div",[["class","choose-level-title"]],null,null,null,null,null)),(l()(),e.Vb(21,null,[" "," "])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(23,0,null,null,15,"div",[["class","level-container"]],null,null,null,null,null)),(l()(),e.Cb(24,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.turnEasy()&&e),e}),null,null)),e.Sb(512,null,d.u,d.v,[e.x,e.y,e.p,e.L]),e.Bb(26,278528,null,0,d.j,[d.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),e.Vb(27,null,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(29,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.turnNormal()&&e),e}),null,null)),e.Sb(512,null,d.u,d.v,[e.x,e.y,e.p,e.L]),e.Bb(31,278528,null,0,d.j,[d.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),e.Vb(32,null,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(34,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.turnExpert()&&e),e}),null,null)),e.Sb(512,null,d.u,d.v,[e.x,e.y,e.p,e.L]),e.Bb(36,278528,null,0,d.j,[d.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),e.Vb(37,null,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(39,0,null,0,4,"div",[["class","both-image-container"]],null,null,null,null,null)),(l()(),e.Cb(40,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),e.Cb(41,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/oblique-stretch-left.png"]],null,null,null,null,null)),(l()(),e.Cb(42,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),e.Cb(43,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/oblique-stretch-right.png"]],null,null,null,null,null)),(l()(),e.Cb(44,0,null,0,2,"div",[["class","exercise-list-text"]],null,null,null,null,null)),(l()(),e.Vb(45,null,[" "," "])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(47,0,null,0,4,"div",[["class","both-image-container"]],null,null,null,null,null)),(l()(),e.Cb(48,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),e.Cb(49,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/twist-left.png"]],null,null,null,null,null)),(l()(),e.Cb(50,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),e.Cb(51,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/twist-right.png"]],null,null,null,null,null)),(l()(),e.Cb(52,0,null,0,2,"div",[["class","exercise-list-text"]],null,null,null,null,null)),(l()(),e.Vb(53,null,[" "," "])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(55,0,null,null,7,"ion-footer",[],null,null,null,b.P,b.h)),e.Bb(56,49152,null,0,o.B,[e.j,e.p,e.F],null,null),(l()(),e.Cb(57,0,null,0,4,"div",[["class","footer-buttons"]],null,null,null,null,null)),(l()(),e.Cb(58,0,null,null,3,"ion-button",[["class","start-button"],["color","solos-color-a"],["shape","round"]],null,[[null,"click"]],(function(l,n,t){var e=!0;return"click"===n&&(e=!1!==l.component.startExercise()&&e),e}),b.K,b.c)),e.Bb(59,49152,null,0,o.m,[e.j,e.p,e.F],{color:[0,"color"],shape:[1,"shape"]},null),(l()(),e.Vb(60,0,["",""])),e.Pb(131072,p.j,[p.k,e.j]),(l()(),e.Cb(62,0,null,0,0,"div",[["class","footer-padding"]],null,null,null,null,null))],(function(l,n){var t=n.component;l(n,3,0,"ios"),l(n,7,0,""),l(n,26,0,"level-selection",t.isEasy?"active":"inactive"),l(n,31,0,"level-selection",t.isNormal?"active":"inactive"),l(n,36,0,"level-selection",t.isExpert?"active":"inactive"),l(n,59,0,"solos-color-a","round")}),(function(l,n){l(n,11,0,e.Wb(n,11,0,e.Ob(n,12).transform("ai-care.stretch-exercise"))),l(n,18,0,e.Wb(n,18,0,e.Ob(n,19).transform("stretch.intro"))),l(n,21,0,e.Wb(n,21,0,e.Ob(n,22).transform("exercise.choose-level"))),l(n,27,0,e.Wb(n,27,0,e.Ob(n,28).transform("exercise.easy"))),l(n,32,0,e.Wb(n,32,0,e.Ob(n,33).transform("exercise.normal"))),l(n,37,0,e.Wb(n,37,0,e.Ob(n,38).transform("exercise.expert"))),l(n,45,0,e.Wb(n,45,0,e.Ob(n,46).transform("stretch.obliques-stretch"))),l(n,53,0,e.Wb(n,53,0,e.Ob(n,54).transform("stretch.waist-twist"))),l(n,60,0,e.Wb(n,60,0,e.Ob(n,61).transform("ai-coach.start")))}))}var v=e.yb("app-stretch-util",u,(function(l){return e.Xb(0,[(l()(),e.Cb(0,0,null,null,1,"app-stretch-util",[],null,null,null,f,g)),e.Bb(1,114688,null,0,u,[o.Jb,i.a,a.b],null,null)],(function(l,n){l(n,1,0)}),null)}),{},{},[]),m=t("s7LF"),h=t("hrfs"),x=t("j1ZV"),k=t("iInd");t.d(n,"StretchUtilPageModuleNgFactory",(function(){return y}));var y=e.zb(s,[],(function(l){return e.Lb([e.Mb(512,e.m,e.kb,[[8,[c.a,v]],[3,e.m],e.D]),e.Mb(4608,d.n,d.m,[e.z,[2,d.z]]),e.Mb(4608,m.j,m.j,[]),e.Mb(4608,o.c,o.c,[e.F,e.g]),e.Mb(4608,o.Ib,o.Ib,[o.c,e.m,e.w]),e.Mb(4608,o.Nb,o.Nb,[o.c,e.m,e.w]),e.Mb(4608,p.g,p.f,[]),e.Mb(4608,p.c,p.e,[]),e.Mb(4608,p.i,p.d,[]),e.Mb(4608,p.b,p.a,[]),e.Mb(4608,p.k,p.k,[p.l,p.g,p.c,p.i,p.b,p.m,p.n]),e.Mb(1073742336,d.b,d.b,[]),e.Mb(1073742336,m.i,m.i,[]),e.Mb(1073742336,m.b,m.b,[]),e.Mb(1073742336,o.Fb,o.Fb,[]),e.Mb(1073742336,h.b,h.b,[]),e.Mb(1073742336,p.h,p.h,[]),e.Mb(1073742336,x.a,x.a,[]),e.Mb(1073742336,k.o,k.o,[[2,k.t],[2,k.m]]),e.Mb(1073742336,s,s,[]),e.Mb(256,p.n,void 0,[]),e.Mb(256,p.m,void 0,[]),e.Mb(1024,k.k,(function(){return[[{path:"",component:u}]]}),[])])}))},j1ZV:function(l,n,t){"use strict";t.d(n,"a",(function(){return e}));var e=function l(){_classCallCheck(this,l)}}}]);