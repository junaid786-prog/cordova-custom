(window.webpackJsonp=window.webpackJsonp||[]).push([[84],{"XRr/":function(t,e,i){"use strict";i.r(e),i.d(e,"super_tab",(function(){return o})),i.d(e,"super_tabs",(function(){return a})),i.d(e,"super_tabs_container",(function(){return h}));var n=i("ALya"),s=i("hMaD");const o=class{constructor(t){Object(n.h)(this,t)}componentDidLoad(){this.checkIonContent()}componentDidUpdate(){this.checkIonContent()}checkIonContent(){if("boolean"!=typeof this.noScroll){const t=this.el.querySelector("ion-content");t&&t.parentElement===this.el&&(this.noScroll=!0)}}async getRootScrollableEl(){if(!this.noScroll&&this.el.scrollHeight>this.el.clientHeight)return this.el;const t=this.el.querySelector("ion-content");return t?t.getScrollElement():this.noScroll?null:this.el}render(){return Object(n.g)("slot",null)}get el(){return Object(n.f)(this)}static get style(){return":host{height:var(--super-tab-height,100%);position:relative;display:block;overflow-x:hidden;overflow-y:auto;contain:size style;z-index:1;-ms-flex-negative:0;flex-shrink:0;-ms-flex-positive:0;flex-grow:0;width:var(--super-tab-width,100vw);-webkit-transform:translateZ(0);transform:translateZ(0);-webkit-box-sizing:border-box;box-sizing:border-box;-ms-flex-order:-1;order:-1;-webkit-user-select:none;-webkit-touch-callout:none;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0);-webkit-font-smoothing:antialiased}:host[noScroll]{overflow-y:hidden}ion-content,ion-nav{height:100%;max-height:100%;position:absolute}ion-content>.ion-page,ion-nav>.ion-page{position:absolute}"}},a=class{constructor(t){Object(n.h)(this,t),this.activeTabIndex=0,this._config=s.a,this.initAttempts=0,this.initPromise=new Promise(t=>{this.initPromiseResolve=t}),this.tabChange=Object(n.d)(this,"tabChange",7)}async setConfig(t){this.debug("setConfig called with: ",t),this._config=Object.assign(Object.assign({},s.a),t)}propagateConfig(){this.container&&(this.container.config=this._config),this.toolbar&&(this.toolbar.config=this._config)}async selectTab(t,e=!0){this.debug("selectTab",t,e),await this.initPromise;const i=this.activeTabIndex;this.container&&await this.container.setActiveTabIndex(t,!0,e),this.toolbar&&await this.toolbar.setActiveTab(t,!0,e),this.emitTabChangeEvent(t,i),this.activeTabIndex=i}async onConfigChange(t){await this.setConfig(t)}onWindowResize(){this.debug("onWindowResize"),this.toolbar&&this.toolbar.setSelectedTab(this.activeTabIndex),this.container.reindexTabs()}async componentWillLoad(){this.debug("componentWillLoad"),this.config&&await this.setConfig(this.config)}componentDidLoad(){this.debug("componentDidLoad"),this.indexChildren(),this.container&&this.container.setActiveTabIndex(this.activeTabIndex),this.toolbar&&this.toolbar.setActiveTab(this.activeTabIndex),this.el.shadowRoot.addEventListener("slotchange",this.onSlotchange.bind(this)),requestAnimationFrame(()=>{this.initComponent()})}initComponent(){!this.container&&++this.initAttempts<1e3?requestAnimationFrame(()=>{this.initComponent()}):(this.debug(`failed to init ${this.initAttempts} times`),this.container&&this.container.moveContainerByIndex(this.activeTabIndex,!1),this.toolbar&&this.toolbar.setSelectedTab(this.activeTabIndex,!1),this.propagateConfig(),this.setupEventListeners(),this.initPromiseResolve())}async setupEventListeners(){this.container?(await this.container.componentOnReady(),this.el.addEventListener("selectedTabIndexChange",this.onContainerSelectedTabChange.bind(this)),this.el.addEventListener("activeTabIndexChange",this.onContainerActiveTabChange.bind(this))):this.debug("setupEventListeners: container does not exist"),this.toolbar?(await this.toolbar.componentOnReady(),this.el.addEventListener("buttonClick",this.onToolbarButtonClick.bind(this))):this.debug("setupEventListeners: toolbar does not exist")}async onContainerSelectedTabChange(t){this.toolbar&&await this.toolbar.setSelectedTab(t.detail)}emitTabChangeEvent(t,e){"number"!=typeof t||t<0||(("number"!=typeof e||e<0)&&(e=this.activeTabIndex),this.tabChange.emit({changed:t!==e,index:t}))}onContainerActiveTabChange(t){this.debug("onContainerActiveTabChange",t);const e=t.detail;this.emitTabChangeEvent(e),this.activeTabIndex=e,this.toolbar&&this.toolbar.setActiveTab(e,!0,!0)}onToolbarButtonClick(t){this.debug("onToolbarButtonClick",t);const{index:e}=t.detail;this.container&&this.container.setActiveTabIndex(e,!0,!0),this.emitTabChangeEvent(e),this.activeTabIndex=e}indexChildren(){this.debug("indexChildren");const t=this.el.querySelector("super-tabs-container"),e=this.el.querySelector("super-tabs-toolbar");t&&this.container!==t&&(this.container=t),e&&this.toolbar!==e&&(this.toolbar=e),this.propagateConfig()}async onSlotchange(){this.indexChildren(),this.selectTab(this.activeTabIndex)}debug(...t){Object(s.e)(this._config,"tabs",t)}render(){return Object(n.g)(n.a,null,Object(n.g)("slot",{name:"top"}),Object(n.g)("slot",null),Object(n.g)("slot",{name:"bottom"}))}get el(){return Object(n.f)(this)}static get watchers(){return{config:["onConfigChange"]}}static get style(){return":host{height:100%;max-height:100%;display:-ms-flexbox;display:flex;-ms-flex-direction:column;flex-direction:column;overflow:hidden;z-index:1;position:relative;contain:layout size style;-ms-flex-order:-1;order:-1;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;-webkit-touch-callout:none;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0);-webkit-font-smoothing:antialiased;-ms-touch-action:none;touch-action:none;-webkit-box-sizing:border-box;box-sizing:border-box;margin:0;padding:0}"}},h=class{constructor(t){Object(n.h)(this,t),this.swipeEnabled=!0,this.autoScrollTop=!1,this.tabs=[],this.isDragging=!1,this.leftThreshold=0,this.rightThreshold=0,this.scrollWidth=0,this.clientWidth=0,this.activeTabIndexChange=Object(n.d)(this,"activeTabIndexChange",7),this.selectedTabIndexChange=Object(n.d)(this,"selectedTabIndexChange",7),this.queue=Object(n.e)(this,"queue")}async componentDidLoad(){this.debug("componentDidLoad"),await this.indexTabs(),this.slot=this.el.shadowRoot.querySelector("slot"),this.slot.addEventListener("slotchange",this.onSlotChange.bind(this))}async onSlotChange(){this.debug("onSlotChange",this.tabs.length)}async componentWillUpdate(){await this.indexTabs()}async reindexTabs(){await this.indexTabs()}moveContainerByIndex(t,e){const i=this.indexToPosition(t);return 0===i&&t>0?Promise.resolve():this.moveContainer(i,e)}moveContainer(t,e){return Object(s.h)(this.el,t,0,e?this.config.transitionDuration:0),Promise.resolve()}async setActiveTabIndex(t,e=!0,i=!0){if(this.debug("setActiveTabIndex",t),this._activeTabIndex===t){if(!this.autoScrollTop)return;await this.scrollToTop()}e&&await this.moveContainerByIndex(t,i),await this.updateActiveTabIndex(t,!1)}async scrollToTop(){if(void 0===this._activeTabIndex||void 0===this.tabs)return void this.debug("activeTabIndex or tabs was undefined");const t=this.tabs[this._activeTabIndex];this.queue.read(()=>{t?t.getRootScrollableEl().then(t=>{t&&Object(s.h)(t,0,0,this.config.transitionDuration)}):this.debug("Current active tab was undefined in scrollToTop")})}updateActiveTabIndex(t,e=!0){this.debug("updateActiveTabIndex",t,e,this._activeTabIndex),this._activeTabIndex=t,e&&this.activeTabIndexChange.emit(this._activeTabIndex)}updateSelectedTabIndex(t){t!==this._selectedTabIndex&&(this._selectedTabIndex=t,this.selectedTabIndexChange.emit(this._selectedTabIndex))}async onTouchStart(t){if(!this.swipeEnabled)return;if(this.config.avoidElements){let e=!1,i=t.target;if(i)do{if("function"==typeof i.getAttribute&&i.getAttribute("avoid-super-tabs"))return;i=i.parentElement}while(i&&!e)}const e=Object(s.g)(t);e.x<this.leftThreshold||e.x>this.clientWidth-this.rightThreshold||(this.config.shortSwipeDuration>0&&(this.initialTimestamp=Object(s.c)()),this.initialCoords=e,this.lastPosX=e.x)}async onClick(t){this.isDragging&&(t.stopImmediatePropagation(),t.preventDefault())}async onTouchMove(t){if(!this.swipeEnabled||!this.initialCoords||"number"!=typeof this.lastPosX)return;const e=Object(s.g)(t);if(!this.isDragging){if(!Object(s.d)(e,this.initialCoords,this.config))return void(Math.abs(e.y-this.initialCoords.y)>100&&(this.initialCoords=void 0,this.lastPosX=void 0));this.isDragging=!0}if(!this.isDragging)return;this.config.allowElementScroll||t.stopImmediatePropagation();const i=this.lastPosX-e.x;if(0===i)return;const n=Object(s.f)(this.el),o=Object(s.b)(this.el,i);o!==n&&(this.updateSelectedTabIndex(this.positionToIndex(o)),this.lastPosX=e.x,this.moveContainer(o,!1))}async onTouchEnd(t){if(!this.swipeEnabled||!this.isDragging)return;const e=Object(s.g)(t),i=Object(s.c)()-this.initialTimestamp,n=this.config.shortSwipeDuration>0&&i<=this.config.shortSwipeDuration,o=e.x-this.initialCoords.x;let a=this.calcSelectedTab();const h=Math.round(a);n&&h===this._activeTabIndex&&(a+=o>0?-1:1),a=this.normalizeSelectedTab(a),this.updateActiveTabIndex(a),this.moveContainerByIndex(a,!0),this.isDragging=!1,this.initialCoords=void 0,this.lastPosX=void 0}async indexTabs(){if(this.scrollWidth=this.el.scrollWidth,this.clientWidth=this.el.clientWidth,this.debug("indexTab",this.scrollWidth,this.clientWidth),0===this.scrollWidth||0===this.clientWidth)return void requestAnimationFrame(()=>{this.indexTabs()});const t=Array.from(this.el.querySelectorAll("super-tab"));if(await Promise.all(t.map(t=>t.componentOnReady())),this.tabs=t,this.ready&&"number"==typeof this._activeTabIndex&&this.moveContainerByIndex(this._activeTabIndex,!0),this.config)switch(this.config.sideMenu){case"both":this.rightThreshold=this.leftThreshold=this.config.sideMenuThreshold||0;break;case"left":this.leftThreshold=this.config.sideMenuThreshold||0;break;case"right":this.rightThreshold=this.config.sideMenuThreshold||0}void 0!==this._activeTabIndex&&this.moveContainerByIndex(this._activeTabIndex,!1).then(()=>{this.ready=!0})}calcSelectedTab(){const t=Math.max(0,Math.min(this.scrollWidth-this.clientWidth,Object(s.f)(this.el)));return this.positionToIndex(t)}positionToIndex(t){return t/this.clientWidth}indexToPosition(t){return this.debug("indexToPosition",t,this.clientWidth),t*this.clientWidth}normalizeSelectedTab(t){const e=this.clientWidth;return Math.max(0,Math.min(this.scrollWidth-e,e*Math.round(t)))/e}debug(...t){Object(s.e)(this.config,"container",t)}render(){return Object(n.g)("slot",null)}get el(){return Object(n.f)(this)}static get style(){return":host{display:-ms-flexbox;display:flex;-ms-flex-flow:row nowrap;flex-flow:row nowrap;min-width:100%;-ms-flex:1 1 auto;flex:1 1 auto;position:relative;-webkit-box-sizing:border-box;box-sizing:border-box;width:var(--super-tab-width,100vw);height:var(--super-tab-height,100%);overflow:hidden;-webkit-transform:translateZ(0);transform:translateZ(0);-ms-touch-action:pan-y;touch-action:pan-y;-moz-user-select:none;-ms-user-select:none;user-select:none;will-change:scroll-position;-ms-flex-order:-1;order:-1;-webkit-user-select:none;-webkit-touch-callout:none;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0);-webkit-font-smoothing:antialiased}"}}}}]);