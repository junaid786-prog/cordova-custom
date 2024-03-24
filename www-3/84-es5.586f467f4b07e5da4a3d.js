function _classCallCheck(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function _defineProperties(e,t){for(var n=0;n<t.length;n++){var i=t[n];i.enumerable=i.enumerable||!1,i.configurable=!0,"value"in i&&(i.writable=!0),Object.defineProperty(e,i.key,i)}}function _createClass(e,t,n){return t&&_defineProperties(e.prototype,t),n&&_defineProperties(e,n),e}(window.webpackJsonp=window.webpackJsonp||[]).push([[84],{"XRr/":function(e,t,n){"use strict";n.r(t),n.d(t,"super_tab",(function(){return s})),n.d(t,"super_tabs",(function(){return a})),n.d(t,"super_tabs_container",(function(){return o}));var i=n("ALya"),r=n("hMaD"),s=function(){function e(t){_classCallCheck(this,e),Object(i.h)(this,t)}return _createClass(e,[{key:"componentDidLoad",value:function(){this.checkIonContent()}},{key:"componentDidUpdate",value:function(){this.checkIonContent()}},{key:"checkIonContent",value:function(){if("boolean"!=typeof this.noScroll){var e=this.el.querySelector("ion-content");e&&e.parentElement===this.el&&(this.noScroll=!0)}}},{key:"getRootScrollableEl",value:function(){var e;return regeneratorRuntime.async((function(t){for(;;)switch(t.prev=t.next){case 0:if(this.noScroll||!(this.el.scrollHeight>this.el.clientHeight)){t.next=2;break}return t.abrupt("return",this.el);case 2:return e=this.el.querySelector("ion-content"),t.abrupt("return",e?e.getScrollElement():this.noScroll?null:this.el);case 4:case"end":return t.stop()}}),null,this,null,Promise)}},{key:"render",value:function(){return Object(i.g)("slot",null)}},{key:"el",get:function(){return Object(i.f)(this)}}],[{key:"style",get:function(){return":host{height:var(--super-tab-height,100%);position:relative;display:block;overflow-x:hidden;overflow-y:auto;contain:size style;z-index:1;-ms-flex-negative:0;flex-shrink:0;-ms-flex-positive:0;flex-grow:0;width:var(--super-tab-width,100vw);-webkit-transform:translateZ(0);transform:translateZ(0);-webkit-box-sizing:border-box;box-sizing:border-box;-ms-flex-order:-1;order:-1;-webkit-user-select:none;-webkit-touch-callout:none;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0);-webkit-font-smoothing:antialiased}:host[noScroll]{overflow-y:hidden}ion-content,ion-nav{height:100%;max-height:100%;position:absolute}ion-content>.ion-page,ion-nav>.ion-page{position:absolute}"}}]),e}(),a=function(){function e(t){var n=this;_classCallCheck(this,e),Object(i.h)(this,t),this.activeTabIndex=0,this._config=r.a,this.initAttempts=0,this.initPromise=new Promise((function(e){n.initPromiseResolve=e})),this.tabChange=Object(i.d)(this,"tabChange",7)}return _createClass(e,[{key:"setConfig",value:function(e){return regeneratorRuntime.async((function(t){for(;;)switch(t.prev=t.next){case 0:this.debug("setConfig called with: ",e),this._config=Object.assign(Object.assign({},r.a),e);case 1:case"end":return t.stop()}}),null,this,null,Promise)}},{key:"propagateConfig",value:function(){this.container&&(this.container.config=this._config),this.toolbar&&(this.toolbar.config=this._config)}},{key:"selectTab",value:function(e){var t,n,i=arguments;return regeneratorRuntime.async((function(r){for(;;)switch(r.prev=r.next){case 0:return t=!(i.length>1&&void 0!==i[1])||i[1],this.debug("selectTab",e,t),r.next=4,regeneratorRuntime.awrap(this.initPromise);case 4:if(n=this.activeTabIndex,r.t0=this.container,!r.t0){r.next=9;break}return r.next=9,regeneratorRuntime.awrap(this.container.setActiveTabIndex(e,!0,t));case 9:if(r.t1=this.toolbar,!r.t1){r.next=13;break}return r.next=13,regeneratorRuntime.awrap(this.toolbar.setActiveTab(e,!0,t));case 13:this.emitTabChangeEvent(e,n),this.activeTabIndex=n;case 15:case"end":return r.stop()}}),null,this,null,Promise)}},{key:"onConfigChange",value:function(e){return regeneratorRuntime.async((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,regeneratorRuntime.awrap(this.setConfig(e));case 2:case"end":return t.stop()}}),null,this,null,Promise)}},{key:"onWindowResize",value:function(){this.debug("onWindowResize"),this.toolbar&&this.toolbar.setSelectedTab(this.activeTabIndex),this.container.reindexTabs()}},{key:"componentWillLoad",value:function(){return regeneratorRuntime.async((function(e){for(;;)switch(e.prev=e.next){case 0:if(this.debug("componentWillLoad"),e.t0=this.config,!e.t0){e.next=5;break}return e.next=5,regeneratorRuntime.awrap(this.setConfig(this.config));case 5:case"end":return e.stop()}}),null,this,null,Promise)}},{key:"componentDidLoad",value:function(){var e=this;this.debug("componentDidLoad"),this.indexChildren(),this.container&&this.container.setActiveTabIndex(this.activeTabIndex),this.toolbar&&this.toolbar.setActiveTab(this.activeTabIndex),this.el.shadowRoot.addEventListener("slotchange",this.onSlotchange.bind(this)),requestAnimationFrame((function(){e.initComponent()}))}},{key:"initComponent",value:function(){var e=this;!this.container&&++this.initAttempts<1e3?requestAnimationFrame((function(){e.initComponent()})):(this.debug("failed to init ".concat(this.initAttempts," times")),this.container&&this.container.moveContainerByIndex(this.activeTabIndex,!1),this.toolbar&&this.toolbar.setSelectedTab(this.activeTabIndex,!1),this.propagateConfig(),this.setupEventListeners(),this.initPromiseResolve())}},{key:"setupEventListeners",value:function(){return regeneratorRuntime.async((function(e){for(;;)switch(e.prev=e.next){case 0:if(!this.container){e.next=7;break}return e.next=3,regeneratorRuntime.awrap(this.container.componentOnReady());case 3:this.el.addEventListener("selectedTabIndexChange",this.onContainerSelectedTabChange.bind(this)),this.el.addEventListener("activeTabIndexChange",this.onContainerActiveTabChange.bind(this)),e.next=8;break;case 7:this.debug("setupEventListeners: container does not exist");case 8:if(!this.toolbar){e.next=14;break}return e.next=11,regeneratorRuntime.awrap(this.toolbar.componentOnReady());case 11:this.el.addEventListener("buttonClick",this.onToolbarButtonClick.bind(this)),e.next=15;break;case 14:this.debug("setupEventListeners: toolbar does not exist");case 15:case"end":return e.stop()}}),null,this,null,Promise)}},{key:"onContainerSelectedTabChange",value:function(e){return regeneratorRuntime.async((function(t){for(;;)switch(t.prev=t.next){case 0:if(t.t0=this.toolbar,!t.t0){t.next=4;break}return t.next=4,regeneratorRuntime.awrap(this.toolbar.setSelectedTab(e.detail));case 4:case"end":return t.stop()}}),null,this,null,Promise)}},{key:"emitTabChangeEvent",value:function(e,t){"number"!=typeof e||e<0||(("number"!=typeof t||t<0)&&(t=this.activeTabIndex),this.tabChange.emit({changed:e!==t,index:e}))}},{key:"onContainerActiveTabChange",value:function(e){this.debug("onContainerActiveTabChange",e);var t=e.detail;this.emitTabChangeEvent(t),this.activeTabIndex=t,this.toolbar&&this.toolbar.setActiveTab(t,!0,!0)}},{key:"onToolbarButtonClick",value:function(e){this.debug("onToolbarButtonClick",e);var t=e.detail.index;this.container&&this.container.setActiveTabIndex(t,!0,!0),this.emitTabChangeEvent(t),this.activeTabIndex=t}},{key:"indexChildren",value:function(){this.debug("indexChildren");var e=this.el.querySelector("super-tabs-container"),t=this.el.querySelector("super-tabs-toolbar");e&&this.container!==e&&(this.container=e),t&&this.toolbar!==t&&(this.toolbar=t),this.propagateConfig()}},{key:"onSlotchange",value:function(){return regeneratorRuntime.async((function(e){for(;;)switch(e.prev=e.next){case 0:this.indexChildren(),this.selectTab(this.activeTabIndex);case 1:case"end":return e.stop()}}),null,this,null,Promise)}},{key:"debug",value:function(){for(var e=arguments.length,t=new Array(e),n=0;n<e;n++)t[n]=arguments[n];Object(r.e)(this._config,"tabs",t)}},{key:"render",value:function(){return Object(i.g)(i.a,null,Object(i.g)("slot",{name:"top"}),Object(i.g)("slot",null),Object(i.g)("slot",{name:"bottom"}))}},{key:"el",get:function(){return Object(i.f)(this)}}],[{key:"watchers",get:function(){return{config:["onConfigChange"]}}},{key:"style",get:function(){return":host{height:100%;max-height:100%;display:-ms-flexbox;display:flex;-ms-flex-direction:column;flex-direction:column;overflow:hidden;z-index:1;position:relative;contain:layout size style;-ms-flex-order:-1;order:-1;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;-webkit-touch-callout:none;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0);-webkit-font-smoothing:antialiased;-ms-touch-action:none;touch-action:none;-webkit-box-sizing:border-box;box-sizing:border-box;margin:0;padding:0}"}}]),e}(),o=function(){function e(t){_classCallCheck(this,e),Object(i.h)(this,t),this.swipeEnabled=!0,this.autoScrollTop=!1,this.tabs=[],this.isDragging=!1,this.leftThreshold=0,this.rightThreshold=0,this.scrollWidth=0,this.clientWidth=0,this.activeTabIndexChange=Object(i.d)(this,"activeTabIndexChange",7),this.selectedTabIndexChange=Object(i.d)(this,"selectedTabIndexChange",7),this.queue=Object(i.e)(this,"queue")}return _createClass(e,[{key:"componentDidLoad",value:function(){return regeneratorRuntime.async((function(e){for(;;)switch(e.prev=e.next){case 0:return this.debug("componentDidLoad"),e.next=3,regeneratorRuntime.awrap(this.indexTabs());case 3:this.slot=this.el.shadowRoot.querySelector("slot"),this.slot.addEventListener("slotchange",this.onSlotChange.bind(this));case 5:case"end":return e.stop()}}),null,this,null,Promise)}},{key:"onSlotChange",value:function(){return regeneratorRuntime.async((function(e){for(;;)switch(e.prev=e.next){case 0:this.debug("onSlotChange",this.tabs.length);case 1:case"end":return e.stop()}}),null,this,null,Promise)}},{key:"componentWillUpdate",value:function(){return regeneratorRuntime.async((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,regeneratorRuntime.awrap(this.indexTabs());case 2:case"end":return e.stop()}}),null,this,null,Promise)}},{key:"reindexTabs",value:function(){return regeneratorRuntime.async((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,regeneratorRuntime.awrap(this.indexTabs());case 2:case"end":return e.stop()}}),null,this,null,Promise)}},{key:"moveContainerByIndex",value:function(e,t){var n=this.indexToPosition(e);return 0===n&&e>0?Promise.resolve():this.moveContainer(n,t)}},{key:"moveContainer",value:function(e,t){return Object(r.h)(this.el,e,0,t?this.config.transitionDuration:0),Promise.resolve()}},{key:"setActiveTabIndex",value:function(e){var t,n,i=arguments;return regeneratorRuntime.async((function(r){for(;;)switch(r.prev=r.next){case 0:if(t=!(i.length>1&&void 0!==i[1])||i[1],n=!(i.length>2&&void 0!==i[2])||i[2],this.debug("setActiveTabIndex",e),this._activeTabIndex!==e){r.next=7;break}if(this.autoScrollTop){r.next=5;break}return r.abrupt("return");case 5:return r.next=7,regeneratorRuntime.awrap(this.scrollToTop());case 7:if(r.t0=t,!r.t0){r.next=11;break}return r.next=11,regeneratorRuntime.awrap(this.moveContainerByIndex(e,n));case 11:return r.next=13,regeneratorRuntime.awrap(this.updateActiveTabIndex(e,!1));case 13:case"end":return r.stop()}}),null,this,null,Promise)}},{key:"scrollToTop",value:function(){var e,t=this;return regeneratorRuntime.async((function(n){for(;;)switch(n.prev=n.next){case 0:if(void 0!==this._activeTabIndex&&void 0!==this.tabs){n.next=2;break}return n.abrupt("return",void this.debug("activeTabIndex or tabs was undefined"));case 2:e=this.tabs[this._activeTabIndex],this.queue.read((function(){e?e.getRootScrollableEl().then((function(e){e&&Object(r.h)(e,0,0,t.config.transitionDuration)})):t.debug("Current active tab was undefined in scrollToTop")}));case 4:case"end":return n.stop()}}),null,this,null,Promise)}},{key:"updateActiveTabIndex",value:function(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];this.debug("updateActiveTabIndex",e,t,this._activeTabIndex),this._activeTabIndex=e,t&&this.activeTabIndexChange.emit(this._activeTabIndex)}},{key:"updateSelectedTabIndex",value:function(e){e!==this._selectedTabIndex&&(this._selectedTabIndex=e,this.selectedTabIndexChange.emit(this._selectedTabIndex))}},{key:"onTouchStart",value:function(e){var t,n,i;return regeneratorRuntime.async((function(s){for(;;)switch(s.prev=s.next){case 0:if(this.swipeEnabled){s.next=2;break}return s.abrupt("return");case 2:if(!this.config.avoidElements){s.next=9;break}if(t=!1,!(n=e.target)){s.next=9;break}case 5:if("function"!=typeof n.getAttribute||!n.getAttribute("avoid-super-tabs")){s.next=7;break}return s.abrupt("return");case 7:n=n.parentElement;case 8:if(n&&!t){s.next=5;break}case 9:(i=Object(r.g)(e)).x<this.leftThreshold||i.x>this.clientWidth-this.rightThreshold||(this.config.shortSwipeDuration>0&&(this.initialTimestamp=Object(r.c)()),this.initialCoords=i,this.lastPosX=i.x);case 11:case"end":return s.stop()}}),null,this,null,Promise)}},{key:"onClick",value:function(e){return regeneratorRuntime.async((function(t){for(;;)switch(t.prev=t.next){case 0:this.isDragging&&(e.stopImmediatePropagation(),e.preventDefault());case 1:case"end":return t.stop()}}),null,this,null,Promise)}},{key:"onTouchMove",value:function(e){var t,n,i,s;return regeneratorRuntime.async((function(a){for(;;)switch(a.prev=a.next){case 0:if(this.swipeEnabled&&this.initialCoords&&"number"==typeof this.lastPosX){a.next=2;break}return a.abrupt("return");case 2:if(t=Object(r.g)(e),this.isDragging){a.next=7;break}if(Object(r.d)(t,this.initialCoords,this.config)){a.next=6;break}return a.abrupt("return",void(Math.abs(t.y-this.initialCoords.y)>100&&(this.initialCoords=void 0,this.lastPosX=void 0)));case 6:this.isDragging=!0;case 7:if(this.isDragging){a.next=9;break}return a.abrupt("return");case 9:if(this.config.allowElementScroll||e.stopImmediatePropagation(),0!==(n=this.lastPosX-t.x)){a.next=13;break}return a.abrupt("return");case 13:i=Object(r.f)(this.el),(s=Object(r.b)(this.el,n))!==i&&(this.updateSelectedTabIndex(this.positionToIndex(s)),this.lastPosX=t.x,this.moveContainer(s,!1));case 15:case"end":return a.stop()}}),null,this,null,Promise)}},{key:"onTouchEnd",value:function(e){var t,n,i,s,a,o;return regeneratorRuntime.async((function(c){for(;;)switch(c.prev=c.next){case 0:if(this.swipeEnabled&&this.isDragging){c.next=2;break}return c.abrupt("return");case 2:t=Object(r.g)(e),n=Object(r.c)()-this.initialTimestamp,i=this.config.shortSwipeDuration>0&&n<=this.config.shortSwipeDuration,s=t.x-this.initialCoords.x,a=this.calcSelectedTab(),o=Math.round(a),i&&o===this._activeTabIndex&&(a+=s>0?-1:1),a=this.normalizeSelectedTab(a),this.updateActiveTabIndex(a),this.moveContainerByIndex(a,!0),this.isDragging=!1,this.initialCoords=void 0,this.lastPosX=void 0;case 6:case"end":return c.stop()}}),null,this,null,Promise)}},{key:"indexTabs",value:function(){var e,t=this;return regeneratorRuntime.async((function(n){for(;;)switch(n.prev=n.next){case 0:if(this.scrollWidth=this.el.scrollWidth,this.clientWidth=this.el.clientWidth,this.debug("indexTab",this.scrollWidth,this.clientWidth),0!==this.scrollWidth&&0!==this.clientWidth){n.next=2;break}return n.abrupt("return",void requestAnimationFrame((function(){t.indexTabs()})));case 2:return e=Array.from(this.el.querySelectorAll("super-tab")),n.next=5,regeneratorRuntime.awrap(Promise.all(e.map((function(e){return e.componentOnReady()}))));case 5:if(this.tabs=e,this.ready&&"number"==typeof this._activeTabIndex&&this.moveContainerByIndex(this._activeTabIndex,!0),!this.config){n.next=16;break}n.t0=this.config.sideMenu,n.next="both"===n.t0?11:"left"===n.t0?13:"right"===n.t0?15:16;break;case 11:return this.rightThreshold=this.leftThreshold=this.config.sideMenuThreshold||0,n.abrupt("break",16);case 13:return this.leftThreshold=this.config.sideMenuThreshold||0,n.abrupt("break",16);case 15:this.rightThreshold=this.config.sideMenuThreshold||0;case 16:void 0!==this._activeTabIndex&&this.moveContainerByIndex(this._activeTabIndex,!1).then((function(){t.ready=!0}));case 17:case"end":return n.stop()}}),null,this,null,Promise)}},{key:"calcSelectedTab",value:function(){var e=Math.max(0,Math.min(this.scrollWidth-this.clientWidth,Object(r.f)(this.el)));return this.positionToIndex(e)}},{key:"positionToIndex",value:function(e){return e/this.clientWidth}},{key:"indexToPosition",value:function(e){return this.debug("indexToPosition",e,this.clientWidth),e*this.clientWidth}},{key:"normalizeSelectedTab",value:function(e){var t=this.clientWidth;return Math.max(0,Math.min(this.scrollWidth-t,t*Math.round(e)))/t}},{key:"debug",value:function(){for(var e=arguments.length,t=new Array(e),n=0;n<e;n++)t[n]=arguments[n];Object(r.e)(this.config,"container",t)}},{key:"render",value:function(){return Object(i.g)("slot",null)}},{key:"el",get:function(){return Object(i.f)(this)}}],[{key:"style",get:function(){return":host{display:-ms-flexbox;display:flex;-ms-flex-flow:row nowrap;flex-flow:row nowrap;min-width:100%;-ms-flex:1 1 auto;flex:1 1 auto;position:relative;-webkit-box-sizing:border-box;box-sizing:border-box;width:var(--super-tab-width,100vw);height:var(--super-tab-height,100%);overflow:hidden;-webkit-transform:translateZ(0);transform:translateZ(0);-ms-touch-action:pan-y;touch-action:pan-y;-moz-user-select:none;-ms-user-select:none;user-select:none;will-change:scroll-position;-ms-flex-order:-1;order:-1;-webkit-user-select:none;-webkit-touch-callout:none;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0);-webkit-font-smoothing:antialiased}"}}]),e}()}}]);