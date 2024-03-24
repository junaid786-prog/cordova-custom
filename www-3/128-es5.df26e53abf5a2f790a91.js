function _createForOfIteratorHelper(e,t){var n;if("undefined"==typeof Symbol||null==e[Symbol.iterator]){if(Array.isArray(e)||(n=_unsupportedIterableToArray(e))||t&&e&&"number"==typeof e.length){n&&(e=n);var r=0,i=function(){};return{s:i,n:function(){return r>=e.length?{done:!0}:{done:!1,value:e[r++]}},e:function(e){throw e},f:i}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}var s,o=!0,a=!1;return{s:function(){n=e[Symbol.iterator]()},n:function(){var e=n.next();return o=e.done,e},e:function(e){a=!0,s=e},f:function(){try{o||null==n.return||n.return()}finally{if(a)throw s}}}}function _unsupportedIterableToArray(e,t){if(e){if("string"==typeof e)return _arrayLikeToArray(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?_arrayLikeToArray(e,t):void 0}}function _arrayLikeToArray(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function _classCallCheck(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function _defineProperties(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}function _createClass(e,t,n){return t&&_defineProperties(e.prototype,t),n&&_defineProperties(e,n),e}(window.webpackJsonp=window.webpackJsonp||[]).push([[128],{SUlj:function(e,t,n){"use strict";n.r(t),n.d(t,"ion_nav",(function(){return f})),n.d(t,"ion_nav_pop",(function(){return d})),n.d(t,"ion_nav_push",(function(){return p})),n.d(t,"ion_nav_set_root",(function(){return m}));var r=n("YrOr"),i=n("TJLY"),s=n("9Z7W"),o=n("EQwm"),a=n("1hGh"),u=n("cVTH"),c=1,l=function(){function e(t,n){_classCallCheck(this,e),this.component=t,this.params=n,this.state=c}return _createClass(e,[{key:"init",value:function(e){var t;return regeneratorRuntime.async((function(n){for(;;)switch(n.prev=n.next){case 0:if(this.state=2,this.element){n.next=5;break}return t=this.component,n.next=4,regeneratorRuntime.awrap(Object(a.a)(this.delegate,e,t,["ion-page","ion-page-invisible"],this.params));case 4:this.element=n.sent;case 5:case"end":return n.stop()}}),null,this,null,Promise)}},{key:"_destroy",value:function(){Object(o.b)(3!==this.state,"view state must be ATTACHED");var e=this.element;e&&(this.delegate?this.delegate.removeViewFromDom(e.parentElement,e):e.remove()),this.nav=void 0,this.state=3}}]),e}(),v=function(e,t,n){if(!e)return!1;if(e.component!==t)return!1;var r=e.params;if(r===n)return!0;if(!r&&!n)return!0;if(!r||!n)return!1;var i=Object.keys(r),s=Object.keys(n);if(i.length!==s.length)return!1;for(var o=0,a=i;o<a.length;o++){var u=a[o];if(r[u]!==n[u])return!1}return!0},h=function(e,t){return e?e instanceof l?e:new l(e,t):null},f=function(){function e(t){_classCallCheck(this,e),Object(r.m)(this,t),this.transInstr=[],this.useRouter=!1,this.isTransitioning=!1,this.destroyed=!1,this.views=[],this.animated=!0,this.ionNavWillLoad=Object(r.d)(this,"ionNavWillLoad",7),this.ionNavWillChange=Object(r.d)(this,"ionNavWillChange",3),this.ionNavDidChange=Object(r.d)(this,"ionNavDidChange",3)}return _createClass(e,[{key:"swipeGestureChanged",value:function(){this.gesture&&this.gesture.setDisabled(!0!==this.swipeGesture)}},{key:"rootChanged",value:function(){void 0!==this.root&&(this.useRouter||this.setRoot(this.root,this.rootParams))}},{key:"componentWillLoad",value:function(){if(this.useRouter=!!document.querySelector("ion-router")&&!this.el.closest("[no-router]"),void 0===this.swipeGesture){var e=Object(r.e)(this);this.swipeGesture=i.b.getBoolean("swipeBackEnabled","ios"===e)}this.ionNavWillLoad.emit()}},{key:"componentDidLoad",value:function(){return regeneratorRuntime.async((function(e){for(;;)switch(e.prev=e.next){case 0:return this.rootChanged(),e.next=3,regeneratorRuntime.awrap(Promise.all([n.e(0),n.e(9)]).then(n.bind(null,"h4wS")));case 3:this.gesture=e.sent.createSwipeBackGesture(this.el,this.canStart.bind(this),this.onStart.bind(this),this.onMove.bind(this),this.onEnd.bind(this)),this.swipeGestureChanged();case 5:case"end":return e.stop()}}),null,this,null,Promise)}},{key:"componentDidUnload",value:function(){var e,t=_createForOfIteratorHelper(this.views);try{for(t.s();!(e=t.n()).done;){var n=e.value;Object(u.b)(n.element,s.e),n._destroy()}}catch(r){t.e(r)}finally{t.f()}this.gesture&&(this.gesture.destroy(),this.gesture=void 0),this.transInstr.length=this.views.length=0,this.destroyed=!0}},{key:"push",value:function(e,t,n,r){return this.queueTrns({insertStart:-1,insertViews:[{page:e,params:t}],opts:n},r)}},{key:"insert",value:function(e,t,n,r,i){return this.queueTrns({insertStart:e,insertViews:[{page:t,params:n}],opts:r},i)}},{key:"insertPages",value:function(e,t,n,r){return this.queueTrns({insertStart:e,insertViews:t,opts:n},r)}},{key:"pop",value:function(e,t){return this.queueTrns({removeStart:-1,removeCount:1,opts:e},t)}},{key:"popTo",value:function(e,t,n){var r={removeStart:-1,removeCount:-1,opts:t};return"object"==typeof e&&e.component?(r.removeView=e,r.removeStart=1):"number"==typeof e&&(r.removeStart=e+1),this.queueTrns(r,n)}},{key:"popToRoot",value:function(e,t){return this.queueTrns({removeStart:1,removeCount:-1,opts:e},t)}},{key:"removeIndex",value:function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1,n=arguments.length>2?arguments[2]:void 0,r=arguments.length>3?arguments[3]:void 0;return this.queueTrns({removeStart:e,removeCount:t,opts:n},r)}},{key:"setRoot",value:function(e,t,n,r){return this.setPages([{page:e,params:t}],n,r)}},{key:"setPages",value:function(e,t,n){return null==t&&(t={}),!0!==t.animated&&(t.animated=!1),this.queueTrns({insertStart:0,insertViews:e,removeStart:0,removeCount:-1,opts:t},n)}},{key:"setRouteId",value:function(e,t,n){var r,i=this.getActiveSync();if(v(i,e,t))return Promise.resolve({changed:!1,element:i.element});var s,o=new Promise((function(e){return r=e})),a={updateURL:!1,viewIsReady:function(e){var t,n=new Promise((function(e){return t=e}));return r({changed:!0,element:e,markVisible:function(){return regeneratorRuntime.async((function(e){for(;;)switch(e.prev=e.next){case 0:return t(),e.next=3,regeneratorRuntime.awrap(s);case 3:case"end":return e.stop()}}),null,null,null,Promise)}}),n}};if("root"===n)s=this.setRoot(e,t,a);else{var u=this.views.find((function(n){return v(n,e,t)}));u?s=this.popTo(u,Object.assign({},a,{direction:"back"})):"forward"===n?s=this.push(e,t,a):"back"===n&&(s=this.setRoot(e,t,Object.assign({},a,{direction:"back",animated:!0})))}return o}},{key:"getRouteId",value:function(){var e;return regeneratorRuntime.async((function(t){for(;;)switch(t.prev=t.next){case 0:return e=this.getActiveSync(),t.abrupt("return",e?{id:e.element.tagName,params:e.params,element:e.element}:void 0);case 2:case"end":return t.stop()}}),null,this,null,Promise)}},{key:"getActive",value:function(){return Promise.resolve(this.getActiveSync())}},{key:"getByIndex",value:function(e){return Promise.resolve(this.views[e])}},{key:"canGoBack",value:function(e){return Promise.resolve(this.canGoBackSync(e))}},{key:"getPrevious",value:function(e){return Promise.resolve(this.getPreviousSync(e))}},{key:"getLength",value:function(){return this.views.length}},{key:"getActiveSync",value:function(){return this.views[this.views.length-1]}},{key:"canGoBackSync",value:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:this.getActiveSync();return!(!e||!this.getPreviousSync(e))}},{key:"getPreviousSync",value:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:this.getActiveSync();if(e){var t=this.views,n=t.indexOf(e);return n>0?t[n-1]:void 0}}},{key:"queueTrns",value:function(e,t){if(this.isTransitioning&&null!=e.opts&&e.opts.skipIfBusy)return Promise.resolve(!1);var n=new Promise((function(t,n){e.resolve=t,e.reject=n}));return e.done=t,e.insertViews&&0===e.insertViews.length&&(e.insertViews=void 0),this.transInstr.push(e),this.nextTrns(),n}},{key:"success",value:function(e,t){if(this.destroyed)this.fireError("nav controller was destroyed",t);else if(t.done&&t.done(e.hasCompleted,e.requiresTransition,e.enteringView,e.leavingView,e.direction),t.resolve(e.hasCompleted),!1!==t.opts.updateURL&&this.useRouter){var n=document.querySelector("ion-router");n&&n.navChanged("back"===e.direction?"back":"forward")}}},{key:"failed",value:function(e,t){this.destroyed?this.fireError("nav controller was destroyed",t):(this.transInstr.length=0,this.fireError(e,t))}},{key:"fireError",value:function(e,t){t.done&&t.done(!1,!1,e),t.reject&&!this.destroyed?t.reject(e):t.resolve(!1)}},{key:"nextTrns",value:function(){if(this.isTransitioning)return!1;var e=this.transInstr.shift();return!!e&&(this.runTransition(e),!0)}},{key:"runTransition",value:function(e){var t,n,r;return regeneratorRuntime.async((function(i){for(;;)switch(i.prev=i.next){case 0:if(i.prev=0,this.ionNavWillChange.emit(),this.isTransitioning=!0,this.prepareTI(e),t=this.getActiveSync(),n=this.getEnteringView(e,t),t||n){i.next=5;break}throw new Error("no views in the stack to be removed");case 5:if(i.t0=n&&n.state===c,!i.t0){i.next=9;break}return i.next=9,regeneratorRuntime.awrap(n.init(this.el));case 9:if(this.postViewInit(n,t,e),!e.enteringRequiresTransition&&!e.leavingRequiresTransition||n===t){i.next=16;break}return i.next=13,regeneratorRuntime.awrap(this.transition(n,t,e));case 13:i.t1=i.sent,i.next=17;break;case 16:i.t1={hasCompleted:!0,requiresTransition:!1};case 17:r=i.t1,this.success(r,e),this.ionNavDidChange.emit(),i.next=24;break;case 21:i.prev=21,i.t2=i.catch(0),this.failed(i.t2,e);case 24:this.isTransitioning=!1,this.nextTrns();case 25:case"end":return i.stop()}}),null,this,[[0,21]],Promise)}},{key:"prepareTI",value:function(e){var t=this.views.length;if(e.opts=e.opts||{},void 0===e.opts.delegate&&(e.opts.delegate=this.delegate),void 0!==e.removeView){Object(o.b)(void 0!==e.removeStart,"removeView needs removeStart"),Object(o.b)(void 0!==e.removeCount,"removeView needs removeCount");var n=this.views.indexOf(e.removeView);if(n<0)throw new Error("removeView was not found");e.removeStart+=n}void 0!==e.removeStart&&(e.removeStart<0&&(e.removeStart=t-1),e.removeCount<0&&(e.removeCount=t-e.removeStart),e.leavingRequiresTransition=e.removeCount>0&&e.removeStart+e.removeCount===t),e.insertViews&&((e.insertStart<0||e.insertStart>t)&&(e.insertStart=t),e.enteringRequiresTransition=e.insertStart===t);var r=e.insertViews;if(r){Object(o.b)(r.length>0,"length can not be zero");var i=r.map((function(e){return e instanceof l?e:"page"in e?h(e.page,e.params):h(e,void 0)})).filter((function(e){return null!==e}));if(0===i.length)throw new Error("invalid views to insert");var s,a=_createForOfIteratorHelper(i);try{for(a.s();!(s=a.n()).done;){var u=s.value;u.delegate=e.opts.delegate;var c=u.nav;if(c&&c!==this)throw new Error("inserted view was already inserted");if(3===u.state)throw new Error("inserted view was already destroyed")}}catch(v){a.e(v)}finally{a.f()}e.insertViews=i}}},{key:"getEnteringView",value:function(e,t){var n=e.insertViews;if(void 0!==n)return n[n.length-1];var r=e.removeStart;if(void 0!==r)for(var i=this.views,s=r+e.removeCount,o=i.length-1;o>=0;o--){var a=i[o];if((o<r||o>=s)&&a!==t)return a}}},{key:"postViewInit",value:function(e,t,n){Object(o.b)(t||e,"Both leavingView and enteringView are null"),Object(o.b)(n.resolve,"resolve must be valid"),Object(o.b)(n.reject,"reject must be valid");var r,i=n.opts,a=n.insertViews,c=n.removeStart,l=n.removeCount;if(void 0!==c&&void 0!==l){Object(o.b)(c>=0,"removeStart can not be negative"),Object(o.b)(l>=0,"removeCount can not be negative"),r=[];for(var v=0;v<l;v++){var h=this.views[v+c];h&&h!==e&&h!==t&&r.push(h)}i.direction=i.direction||"back"}var f=this.views.length+(void 0!==a?a.length:0)-(void 0!==l?l:0);if(Object(o.b)(f>=0,"final balance can not be negative"),0===f)throw console.warn("You can't remove all the pages in the navigation stack. nav.pop() is probably called too many times.",this,this.el),new Error("navigation stack needs at least one root page");if(a){var d,p=n.insertStart,m=_createForOfIteratorHelper(a);try{for(m.s();!(d=m.n()).done;){var g=d.value;this.insertViewAt(g,p),p++}}catch(S){m.e(S)}finally{m.f()}n.enteringRequiresTransition&&(i.direction=i.direction||"forward")}if(r&&r.length>0){var y,b=_createForOfIteratorHelper(r);try{for(b.s();!(y=b.n()).done;){var w=y.value;Object(u.b)(w.element,s.c),Object(u.b)(w.element,s.d),Object(u.b)(w.element,s.e)}}catch(S){b.e(S)}finally{b.f()}var k,O=_createForOfIteratorHelper(r);try{for(O.s();!(k=O.n()).done;){var C=k.value;this.destroyView(C)}}catch(S){O.e(S)}finally{O.f()}}}},{key:"transition",value:function(e,t,n){var s,o,a,c,l,v,h,f,d=this;return regeneratorRuntime.async((function(p){for(;;)switch(p.prev=p.next){case 0:return s=n.opts,o=s.progressAnimation?function(e){return d.sbAni=e}:void 0,a=Object(r.e)(this),c=e.element,l=t&&t.element,v=Object.assign({mode:a,showGoBack:this.canGoBackSync(e),baseEl:this.el,animationBuilder:this.animation||s.animationBuilder||i.b.get("navAnimation"),progressCallback:o,animated:this.animated&&i.b.getBoolean("animated",!0),enteringEl:c,leavingEl:l},s),p.next=8,regeneratorRuntime.awrap(Object(u.d)(v));case 8:return h=p.sent,f=h.hasCompleted,p.abrupt("return",this.transitionFinish(f,e,t,s));case 11:case"end":return p.stop()}}),null,this,null,Promise)}},{key:"transitionFinish",value:function(e,t,n,r){var i=e?t:n;return i&&this.cleanup(i),{hasCompleted:e,requiresTransition:!0,enteringView:t,leavingView:n,direction:r.direction}}},{key:"insertViewAt",value:function(e,t){var n=this.views,r=n.indexOf(e);r>-1?(Object(o.b)(e.nav===this,"view is not part of the nav"),n.splice(t,0,n.splice(r,1)[0])):(Object(o.b)(!e.nav,"nav is used"),e.nav=this,n.splice(t,0,e))}},{key:"removeView",value:function(e){Object(o.b)(2===e.state||3===e.state,"view state should be loaded or destroyed");var t=this.views,n=t.indexOf(e);Object(o.b)(n>-1,"view must be part of the stack"),n>=0&&t.splice(n,1)}},{key:"destroyView",value:function(e){e._destroy(),this.removeView(e)}},{key:"cleanup",value:function(e){if(!this.destroyed)for(var t=this.views,n=t.indexOf(e),r=t.length-1;r>=0;r--){var i=t[r],o=i.element;r>n?(Object(u.b)(o,s.e),this.destroyView(i)):r<n&&Object(u.c)(o,!0)}}},{key:"canStart",value:function(){return!!this.swipeGesture&&!this.isTransitioning&&0===this.transInstr.length&&this.canGoBackSync()}},{key:"onStart",value:function(){this.queueTrns({removeStart:-1,removeCount:1,opts:{direction:"back",progressAnimation:!0}},void 0)}},{key:"onMove",value:function(e){this.sbAni&&this.sbAni.progressStep(e)}},{key:"onEnd",value:function(e,t,n){this.sbAni&&this.sbAni.progressEnd(e,t,n)}},{key:"render",value:function(){return Object(r.i)("slot",null)}},{key:"el",get:function(){return Object(r.f)(this)}}],[{key:"watchers",get:function(){return{swipeGesture:["swipeGestureChanged"],root:["rootChanged"]}}},{key:"style",get:function(){return":host{left:0;right:0;top:0;bottom:0;position:absolute;contain:layout size style;overflow:hidden;z-index:0}"}}]),e}(),d=function(){function e(t){var n=this;_classCallCheck(this,e),Object(r.m)(this,t),this.pop=function(){var e=n.el.closest("ion-nav");e&&e.pop({skipIfBusy:!0})}}return _createClass(e,[{key:"render",value:function(){return Object(r.i)(r.a,{onClick:this.pop})}},{key:"el",get:function(){return Object(r.f)(this)}}]),e}(),p=function(){function e(t){var n=this;_classCallCheck(this,e),Object(r.m)(this,t),this.push=function(){var e=n.el.closest("ion-nav"),t=n.component;e&&void 0!==t&&e.push(t,n.componentProps,{skipIfBusy:!0})}}return _createClass(e,[{key:"render",value:function(){return Object(r.i)(r.a,{onClick:this.push})}},{key:"el",get:function(){return Object(r.f)(this)}}]),e}(),m=function(){function e(t){var n=this;_classCallCheck(this,e),Object(r.m)(this,t),this.setRoot=function(){var e=n.el.closest("ion-nav"),t=n.component;e&&void 0!==t&&e.setRoot(t,n.componentProps,{skipIfBusy:!0})}}return _createClass(e,[{key:"render",value:function(){return Object(r.i)(r.a,{onClick:this.setRoot})}},{key:"el",get:function(){return Object(r.f)(this)}}]),e}()}}]);