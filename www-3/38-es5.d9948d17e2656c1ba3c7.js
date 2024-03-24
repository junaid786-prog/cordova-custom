function _toConsumableArray(l){return _arrayWithoutHoles(l)||_iterableToArray(l)||_unsupportedIterableToArray(l)||_nonIterableSpread()}function _nonIterableSpread(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}function _unsupportedIterableToArray(l,n){if(l){if("string"==typeof l)return _arrayLikeToArray(l,n);var e=Object.prototype.toString.call(l).slice(8,-1);return"Object"===e&&l.constructor&&(e=l.constructor.name),"Map"===e||"Set"===e?Array.from(l):"Arguments"===e||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(e)?_arrayLikeToArray(l,n):void 0}}function _iterableToArray(l){if("undefined"!=typeof Symbol&&Symbol.iterator in Object(l))return Array.from(l)}function _arrayWithoutHoles(l){if(Array.isArray(l))return _arrayLikeToArray(l)}function _arrayLikeToArray(l,n){(null==n||n>l.length)&&(n=l.length);for(var e=0,t=new Array(n);e<n;e++)t[e]=l[e];return t}function _defineProperties(l,n){for(var e=0;e<n.length;e++){var t=n[e];t.enumerable=t.enumerable||!1,t.configurable=!0,"value"in t&&(t.writable=!0),Object.defineProperty(l,t.key,t)}}function _createClass(l,n,e){return n&&_defineProperties(l.prototype,n),e&&_defineProperties(l,e),l}function _classCallCheck(l,n){if(!(l instanceof n))throw new TypeError("Cannot call a class as a function")}(window.webpackJsonp=window.webpackJsonp||[]).push([[38],{j1ZV:function(l,n,e){"use strict";e.d(n,"a",(function(){return t}));var t=function l(){_classCallCheck(this,l)}},uBLN:function(l,n,e){"use strict";e.r(n);var t=e("8Y7J"),u=e("mrSG"),i=e("ZZ/e"),s=e("R3bG"),a=e("OUMn"),o=e("Zesz"),r=e("h5tO"),c=function(){function l(n,e,t,i,s,a){var o=this;_classCallCheck(this,l),this.navCtrl=n,this.events=e,this.solosConnector=t,this.popupCtrl=i,this.us=s,this.activityStateManager=a,this.isActive=!1,this.isEasy=!0,this.isNormal=!1,this.isExpert=!1,this.ExerciseLevel="Easy",this.isLandscapeMode=function(){return o.us.isLandscapeMode()},this.startExercise=function(){return u.b(o,void 0,void 0,regeneratorRuntime.mark((function l(){var n,e;return regeneratorRuntime.wrap((function(l){for(;;)switch(l.prev=l.next){case 0:if(l.prev=0,n=[].concat(_toConsumableArray(r.c),[r.a.BASIC_TRAINING,r.a.CORE_TRAINING,r.a.POSTURE_MONITORING]),!(e=this.activityStateManager.validate(n,r.a.POSTURE_EXERCISE,"/exercise-util")).status){l.next=6;break}this.navCtrl.navigateForward("/exercise/"+this.ExerciseLevel+"/0"),l.next=8;break;case 6:return l.next=8,this.popupCtrl.presentAlertController({header:e.header,message:e.message,buttons:e.buttons},!0);case 8:l.next=13;break;case 10:l.prev=10,l.t0=l.catch(0),console.log(l.t0.message);case 13:case"end":return l.stop()}}),l,this,[[0,10]])})))},this.warmUp=function(){return u.b(o,void 0,void 0,regeneratorRuntime.mark((function l(){var n,e;return regeneratorRuntime.wrap((function(l){for(;;)switch(l.prev=l.next){case 0:if(l.prev=0,n=[].concat(_toConsumableArray(r.c),[r.a.BASIC_TRAINING,r.a.CORE_TRAINING,r.a.POSTURE_MONITORING]),!(e=this.activityStateManager.validate(n,r.a.POSTURE_WARM_UP,"/exercise-util")).status){l.next=6;break}this.navCtrl.navigateForward("/show3d"),l.next=8;break;case 6:return l.next=8,this.popupCtrl.presentAlertController({header:e.header,message:e.message,buttons:e.buttons},!0);case 8:l.next=13;break;case 10:l.prev=10,l.t0=l.catch(0),console.log(l.t0.message);case 13:case"end":return l.stop()}}),l,this,[[0,10]])})))}}return _createClass(l,[{key:"ngOnInit",value:function(){}},{key:"ionViewWillEnter",value:function(){var l;this.bindedFunctions={},l=this.evtSolosConnected.bind(this),this.events.subscribe(s.j,l),this.bindedFunctions[s.j]=l,l=this.evtSolosDisconnected.bind(this),this.events.subscribe(s.k,l),this.bindedFunctions[s.k]=l}},{key:"ionViewWillLeave",value:function(){for(var l in this.bindedFunctions)this.events.unsubscribe(l,this.bindedFunctions[l]);this.bindedFunctions=null}},{key:"evtSolosConnected",value:function(l){}},{key:"evtSolosDisconnected",value:function(l){this.isLandscapeMode()&&this.navCtrl.navigateBack("")}},{key:"turnEasy",value:function(){this.ExerciseLevel="Easy",this.isEasy=!0,this.isNormal=!1,this.isExpert=!1}},{key:"turnNormal",value:function(){this.ExerciseLevel="Normal",this.isEasy=!1,this.isNormal=!0,this.isExpert=!1}},{key:"turnExpert",value:function(){this.ExerciseLevel="Expert",this.isEasy=!1,this.isNormal=!1,this.isExpert=!0}}]),l}(),b=function l(){_classCallCheck(this,l)},d=e("pMnS"),p=e("TSSN"),g=e("SVse"),m=e("oBZk"),x=t.Ab({encapsulation:0,styles:[['.intro-text-container[_ngcontent-%COMP%]{text-align:center;padding:1rem .5rem 1.5rem;position:relative;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;margin-bottom:1rem}.intro-text-container[_ngcontent-%COMP%]::before{content:"";background-image:url(posture-exercise-banner.b09c24c968b708e006ce.png);background-size:cover;position:absolute;top:0;right:0;bottom:0;left:0;opacity:.5}.intro-text[_ngcontent-%COMP%]{color:var(--solos-color-a);position:relative}.choose-level-title[_ngcontent-%COMP%]{font-size:14px;text-align:center;color:var(--solos-color-a);text-transform:uppercase}.level-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:justify;justify-content:space-between;margin:10px 40px}.level-selection[_ngcontent-%COMP%]{flex-basis:30%;font-size:12px;text-align:center;padding:12px 0;color:var(--solos-color-a)}.active[_ngcontent-%COMP%]{background:var(--solos-color-d);border-color:var(--solos-color-b)}.inactive[_ngcontent-%COMP%]{background:0 0;border-color:var(--solos-color-a);border-style:solid;border-width:1px}.both-image-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;margin:0 40px}.image-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;-webkit-box-align:center;align-items:center;width:50%;padding-top:50%;position:relative}.image-content[_ngcontent-%COMP%]{width:100%;height:100%;-o-object-fit:contain;object-fit:contain;border:0 transparent;position:absolute;top:0;bottom:0;left:0;right:0}.exercise-list-text[_ngcontent-%COMP%]{font-size:15px;color:var(--solos-color-a);text-align:center;text-transform:uppercase;margin-top:5px;margin-bottom:25px}.footer-buttons[_ngcontent-%COMP%]{display:-webkit-box;display:flex;padding:12px;-webkit-box-align:center;align-items:center;background:var(--solos-color-b);border-width:1px 0 0;border-style:solid;border-color:var(--solos-color-a)}.start-button[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-align:center;align-items:center;flex-basis:90%;margin-right:5px}.coach-button[_ngcontent-%COMP%]{flex-basis:10%;margin-left:5px;--border-width:1px;--border-style:solid;--border-color:var(--solos-color-a)}.footer-padding[_ngcontent-%COMP%]{height:calc(env(safe-area-inset-bottom));background:var(--solos-color-b)}'],[".landscape-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;height:100%}.main-container[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1}.content-container-landscape[_ngcontent-%COMP%]{-webkit-box-flex:3;flex:3;display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row}.choose-level-title-landscape[_ngcontent-%COMP%]{font-size:1em;text-align:center;color:var(--solos-color-a);text-transform:uppercase}.level-container-landscape[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:justify;justify-content:space-between;margin:10px 40px}.level-selection-landscape[_ngcontent-%COMP%]{flex-basis:30%;font-size:1em;text-align:center;padding:12px 0;color:var(--solos-color-a)}.demo-img-container-landscape[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column}.both-image-container-landscape[_ngcontent-%COMP%]{-webkit-box-flex:5;flex:5;display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;margin:0 40px}.exercise-list-text-landscape[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1;font-size:1em;color:var(--solos-color-a);text-align:center;text-transform:uppercase;margin-top:5px;margin-bottom:25px}@media (min-width:1025px) and (max-width:1280px){.tablet-btn-style[_ngcontent-%COMP%]{height:2em;font-size:2em}.choose-level-title-landscape[_ngcontent-%COMP%]{font-size:2em;text-align:center;color:var(--solos-color-a);text-transform:uppercase}.level-selection-landscape[_ngcontent-%COMP%]{flex-basis:30%;font-size:2em;text-align:center;padding:12px 0;color:var(--solos-color-a)}.exercise-list-text-landscape[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1;font-size:2em;color:var(--solos-color-a);text-align:center;text-transform:uppercase;margin-top:5px;margin-bottom:25px}}"]],data:{}});function v(l){return t.Xb(0,[(l()(),t.Cb(0,0,null,null,48,"div",[],null,null,null,null,null)),(l()(),t.Cb(1,0,null,null,23,"div",[["class","main-container"]],null,null,null,null,null)),(l()(),t.Cb(2,0,null,null,3,"div",[["class","intro-text-container"]],null,null,null,null,null)),(l()(),t.Cb(3,0,null,null,2,"div",[["class","intro-text"]],null,null,null,null,null)),(l()(),t.Vb(4,null,["",""])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(6,0,null,null,2,"div",[["class","choose-level-title"]],null,null,null,null,null)),(l()(),t.Vb(7,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(9,0,null,null,15,"div",[["class","level-container"]],null,null,null,null,null)),(l()(),t.Cb(10,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,e){var t=!0;return"click"===n&&(t=!1!==l.component.turnEasy()&&t),t}),null,null)),t.Sb(512,null,g.u,g.v,[t.x,t.y,t.p,t.L]),t.Bb(12,278528,null,0,g.j,[g.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),t.Vb(13,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(15,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,e){var t=!0;return"click"===n&&(t=!1!==l.component.turnNormal()&&t),t}),null,null)),t.Sb(512,null,g.u,g.v,[t.x,t.y,t.p,t.L]),t.Bb(17,278528,null,0,g.j,[g.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),t.Vb(18,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(20,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(l,n,e){var t=!0;return"click"===n&&(t=!1!==l.component.turnExpert()&&t),t}),null,null)),t.Sb(512,null,g.u,g.v,[t.x,t.y,t.p,t.L]),t.Bb(22,278528,null,0,g.j,[g.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),t.Vb(23,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(25,0,null,null,4,"div",[["class","both-image-container"]],null,null,null,null,null)),(l()(),t.Cb(26,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(27,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_nod_up.png"]],null,null,null,null,null)),(l()(),t.Cb(28,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(29,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_nod_down.png"]],null,null,null,null,null)),(l()(),t.Cb(30,0,null,null,2,"div",[["class","exercise-list-text"]],null,null,null,null,null)),(l()(),t.Vb(31,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(33,0,null,null,4,"div",[["class","both-image-container"]],null,null,null,null,null)),(l()(),t.Cb(34,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(35,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_turn_left.png"]],null,null,null,null,null)),(l()(),t.Cb(36,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(37,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_turn_right.png"]],null,null,null,null,null)),(l()(),t.Cb(38,0,null,null,2,"div",[["class","exercise-list-text"]],null,null,null,null,null)),(l()(),t.Vb(39,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(41,0,null,null,4,"div",[["class","both-image-container"]],null,null,null,null,null)),(l()(),t.Cb(42,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(43,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_tilt_left.png"]],null,null,null,null,null)),(l()(),t.Cb(44,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(45,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_tilt_right.png"]],null,null,null,null,null)),(l()(),t.Cb(46,0,null,null,2,"div",[["class","exercise-list-text"]],null,null,null,null,null)),(l()(),t.Vb(47,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j])],(function(l,n){var e=n.component;l(n,12,0,"level-selection",e.isEasy?"active":"inactive"),l(n,17,0,"level-selection",e.isNormal?"active":"inactive"),l(n,22,0,"level-selection",e.isExpert?"active":"inactive")}),(function(l,n){l(n,4,0,t.Wb(n,4,0,t.Ob(n,5).transform("exercise.intro"))),l(n,7,0,t.Wb(n,7,0,t.Ob(n,8).transform("exercise.choose-level"))),l(n,13,0,t.Wb(n,13,0,t.Ob(n,14).transform("exercise.easy"))),l(n,18,0,t.Wb(n,18,0,t.Ob(n,19).transform("exercise.normal"))),l(n,23,0,t.Wb(n,23,0,t.Ob(n,24).transform("exercise.expert"))),l(n,31,0,t.Wb(n,31,0,t.Ob(n,32).transform("exercise.head-nod-up-down"))),l(n,39,0,t.Wb(n,39,0,t.Ob(n,40).transform("exercise.head-turn-left-right"))),l(n,47,0,t.Wb(n,47,0,t.Ob(n,48).transform("exercise.head-tilt-left-right")))}))}function f(l){return t.Xb(0,[(l()(),t.Cb(0,0,null,null,52,"div",[["class","landscape-container"]],null,null,null,null,null)),(l()(),t.Cb(1,0,null,null,23,"div",[["class","main-container-landscape"]],null,null,null,null,null)),(l()(),t.Cb(2,0,null,null,3,"div",[["class","intro-text-container"]],null,null,null,null,null)),(l()(),t.Cb(3,0,null,null,2,"div",[["class","intro-text"]],null,null,null,null,null)),(l()(),t.Vb(4,null,["",""])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(6,0,null,null,2,"div",[["class","choose-level-title-landscape"]],null,null,null,null,null)),(l()(),t.Vb(7,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(9,0,null,null,15,"div",[["class","level-container-landscape"]],null,null,null,null,null)),(l()(),t.Cb(10,0,null,null,4,"div",[["class","level-selection-landscape"]],null,[[null,"click"]],(function(l,n,e){var t=!0;return"click"===n&&(t=!1!==l.component.turnEasy()&&t),t}),null,null)),t.Sb(512,null,g.u,g.v,[t.x,t.y,t.p,t.L]),t.Bb(12,278528,null,0,g.j,[g.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),t.Vb(13,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(15,0,null,null,4,"div",[["class","level-selection-landscape"]],null,[[null,"click"]],(function(l,n,e){var t=!0;return"click"===n&&(t=!1!==l.component.turnNormal()&&t),t}),null,null)),t.Sb(512,null,g.u,g.v,[t.x,t.y,t.p,t.L]),t.Bb(17,278528,null,0,g.j,[g.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),t.Vb(18,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(20,0,null,null,4,"div",[["class","level-selection-landscape"]],null,[[null,"click"]],(function(l,n,e){var t=!0;return"click"===n&&(t=!1!==l.component.turnExpert()&&t),t}),null,null)),t.Sb(512,null,g.u,g.v,[t.x,t.y,t.p,t.L]),t.Bb(22,278528,null,0,g.j,[g.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(l()(),t.Vb(23,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(25,0,null,null,27,"div",[["class","content-container-landscape"]],null,null,null,null,null)),(l()(),t.Cb(26,0,null,null,8,"div",[["class","demo-img-container-landscape"]],null,null,null,null,null)),(l()(),t.Cb(27,0,null,null,4,"div",[["class","both-image-container-landscape"]],null,null,null,null,null)),(l()(),t.Cb(28,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(29,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_nod_up.png"]],null,null,null,null,null)),(l()(),t.Cb(30,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(31,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_nod_down.png"]],null,null,null,null,null)),(l()(),t.Cb(32,0,null,null,2,"div",[["class","exercise-list-text-landscape"]],null,null,null,null,null)),(l()(),t.Vb(33,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(35,0,null,null,8,"div",[["class","demo-img-container-landscape"]],null,null,null,null,null)),(l()(),t.Cb(36,0,null,null,4,"div",[["class","both-image-container-landscape"]],null,null,null,null,null)),(l()(),t.Cb(37,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(38,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_turn_left.png"]],null,null,null,null,null)),(l()(),t.Cb(39,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(40,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_turn_right.png"]],null,null,null,null,null)),(l()(),t.Cb(41,0,null,null,2,"div",[["class","exercise-list-text-landscape"]],null,null,null,null,null)),(l()(),t.Vb(42,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(44,0,null,null,8,"div",[["class","demo-img-container-landscape"]],null,null,null,null,null)),(l()(),t.Cb(45,0,null,null,4,"div",[["class","both-image-container-landscape"]],null,null,null,null,null)),(l()(),t.Cb(46,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(47,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_tilt_left.png"]],null,null,null,null,null)),(l()(),t.Cb(48,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(l()(),t.Cb(49,0,null,null,0,"img",[["class","image-content"],["src","assets/imgs/head_tilt_right.png"]],null,null,null,null,null)),(l()(),t.Cb(50,0,null,null,2,"div",[["class","exercise-list-text-landscape"]],null,null,null,null,null)),(l()(),t.Vb(51,null,[" "," "])),t.Pb(131072,p.j,[p.k,t.j])],(function(l,n){var e=n.component;l(n,12,0,"level-selection-landscape",e.isEasy?"active":"inactive"),l(n,17,0,"level-selection-landscape",e.isNormal?"active":"inactive"),l(n,22,0,"level-selection-landscape",e.isExpert?"active":"inactive")}),(function(l,n){l(n,4,0,t.Wb(n,4,0,t.Ob(n,5).transform("exercise.intro"))),l(n,7,0,t.Wb(n,7,0,t.Ob(n,8).transform("exercise.choose-level"))),l(n,13,0,t.Wb(n,13,0,t.Ob(n,14).transform("exercise.easy"))),l(n,18,0,t.Wb(n,18,0,t.Ob(n,19).transform("exercise.normal"))),l(n,23,0,t.Wb(n,23,0,t.Ob(n,24).transform("exercise.expert"))),l(n,33,0,t.Wb(n,33,0,t.Ob(n,34).transform("exercise.head-nod-up-down"))),l(n,42,0,t.Wb(n,42,0,t.Ob(n,43).transform("exercise.head-turn-left-right"))),l(n,51,0,t.Wb(n,51,0,t.Ob(n,52).transform("exercise.head-tilt-left-right")))}))}function h(l){return t.Xb(0,[(l()(),t.Cb(0,0,null,null,12,"ion-header",[["style","background: var(--solos-color-b);"]],null,null,null,m.Q,m.i)),t.Bb(1,49152,null,0,i.D,[t.j,t.p,t.F],null,null),(l()(),t.Cb(2,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,m.ob,m.G)),t.Bb(3,49152,null,0,i.Db,[t.j,t.p,t.F],{mode:[0,"mode"]},null),(l()(),t.Cb(4,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,m.L,m.d)),t.Bb(5,49152,null,0,i.n,[t.j,t.p,t.F],null,null),(l()(),t.Cb(6,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(l,n,e){var u=!0;return"click"===n&&(u=!1!==t.Ob(l,8).onClick(e)&&u),u}),m.J,m.b)),t.Bb(7,49152,null,0,i.i,[t.j,t.p,t.F],{text:[0,"text"]},null),t.Bb(8,16384,null,0,i.j,[[2,i.jb],i.Jb],null,null),(l()(),t.Cb(9,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,m.mb,m.E)),t.Bb(10,49152,null,0,i.Bb,[t.j,t.p,t.F],null,null),(l()(),t.Vb(11,0,["",""])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(13,0,null,null,5,"ion-content",[["class","app-background"]],null,null,null,m.O,m.g)),t.Bb(14,49152,null,0,i.w,[t.j,t.p,t.F],null,null),(l()(),t.rb(16777216,null,0,1,null,v)),t.Bb(16,16384,null,0,g.l,[t.X,t.T],{ngIf:[0,"ngIf"]},null),(l()(),t.rb(16777216,null,0,1,null,f)),t.Bb(18,16384,null,0,g.l,[t.X,t.T],{ngIf:[0,"ngIf"]},null),(l()(),t.Cb(19,0,null,null,11,"ion-footer",[],null,null,null,m.P,m.h)),t.Bb(20,49152,null,0,i.B,[t.j,t.p,t.F],null,null),(l()(),t.Cb(21,0,null,0,8,"div",[["class","footer-buttons"]],null,null,null,null,null)),(l()(),t.Cb(22,0,null,null,3,"ion-button",[["class","start-button tablet-btn-style"],["color","solos-color-a"],["shape","round"]],null,[[null,"click"]],(function(l,n,e){var t=!0;return"click"===n&&(t=!1!==l.component.startExercise()&&t),t}),m.K,m.c)),t.Bb(23,49152,null,0,i.m,[t.j,t.p,t.F],{color:[0,"color"],shape:[1,"shape"]},null),(l()(),t.Vb(24,0,[" ",""])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(26,0,null,null,3,"ion-button",[["class","coach-button tablet-btn-style"],["color","solos-color-b"],["shape","round"]],null,[[null,"click"]],(function(l,n,e){var t=!0;return"click"===n&&(t=!1!==l.component.warmUp()&&t),t}),m.K,m.c)),t.Bb(27,49152,null,0,i.m,[t.j,t.p,t.F],{color:[0,"color"],shape:[1,"shape"]},null),(l()(),t.Vb(28,0,[" ",""])),t.Pb(131072,p.j,[p.k,t.j]),(l()(),t.Cb(30,0,null,0,0,"div",[["class","footer-padding"]],null,null,null,null,null))],(function(l,n){var e=n.component;l(n,3,0,"ios"),l(n,7,0,""),l(n,16,0,!e.isLandscapeMode()),l(n,18,0,e.isLandscapeMode()),l(n,23,0,"solos-color-a","round"),l(n,27,0,"solos-color-b","round")}),(function(l,n){l(n,11,0,t.Wb(n,11,0,t.Ob(n,12).transform("ai-care.posture-exercise"))),l(n,24,0,t.Wb(n,24,0,t.Ob(n,25).transform("ai-coach.start"))),l(n,28,0,t.Wb(n,28,0,t.Ob(n,29).transform("exercise.warm-up")))}))}var C=t.yb("app-exercise-util",c,(function(l){return t.Xb(0,[(l()(),t.Cb(0,0,null,null,1,"app-exercise-util",[],null,null,null,h,x)),t.Bb(1,114688,null,0,c,[i.Jb,i.f,s.l,a.a,o.a,r.b],null,null)],(function(l,n){l(n,1,0)}),null)}),{},{},[]),k=e("s7LF"),y=e("hrfs"),_=e("j1ZV"),w=e("iInd");e.d(n,"ExerciseUtilPageModuleNgFactory",(function(){return j}));var j=t.zb(b,[],(function(l){return t.Lb([t.Mb(512,t.m,t.kb,[[8,[d.a,C]],[3,t.m],t.D]),t.Mb(4608,g.n,g.m,[t.z,[2,g.z]]),t.Mb(4608,k.j,k.j,[]),t.Mb(4608,i.c,i.c,[t.F,t.g]),t.Mb(4608,i.Ib,i.Ib,[i.c,t.m,t.w]),t.Mb(4608,i.Nb,i.Nb,[i.c,t.m,t.w]),t.Mb(4608,p.g,p.f,[]),t.Mb(4608,p.c,p.e,[]),t.Mb(4608,p.i,p.d,[]),t.Mb(4608,p.b,p.a,[]),t.Mb(4608,p.k,p.k,[p.l,p.g,p.c,p.i,p.b,p.m,p.n]),t.Mb(1073742336,g.b,g.b,[]),t.Mb(1073742336,k.i,k.i,[]),t.Mb(1073742336,k.b,k.b,[]),t.Mb(1073742336,i.Fb,i.Fb,[]),t.Mb(1073742336,y.b,y.b,[]),t.Mb(1073742336,p.h,p.h,[]),t.Mb(1073742336,_.a,_.a,[]),t.Mb(1073742336,w.o,w.o,[[2,w.t],[2,w.m]]),t.Mb(1073742336,b,b,[]),t.Mb(256,p.n,void 0,[]),t.Mb(256,p.m,void 0,[]),t.Mb(1024,w.k,(function(){return[[{path:"",component:c}]]}),[])])}))}}]);