function _defineProperty(e,i,n){return i in e?Object.defineProperty(e,i,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[i]=n,e}function _classCallCheck(e,i){if(!(e instanceof i))throw new TypeError("Cannot call a class as a function")}function _defineProperties(e,i){for(var n=0;n<i.length;n++){var t=i[n];t.enumerable=t.enumerable||!1,t.configurable=!0,"value"in t&&(t.writable=!0),Object.defineProperty(e,t.key,t)}}function _createClass(e,i,n){return i&&_defineProperties(e.prototype,i),n&&_defineProperties(e,n),e}(window.webpackJsonp=window.webpackJsonp||[]).push([[123],{N3W9:function(e,i,n){"use strict";n.r(i),n.d(i,"ion_loading",(function(){return m}));var t=n("YrOr"),o=n("TJLY"),r=n("vTwt"),a=n("KSOA"),s=n("IvgT"),d=function(e,i){var n=new e,t=new e;t.addElement(i.querySelector("ion-backdrop"));var o=new e;return o.addElement(i.querySelector(".loading-wrapper")),t.fromTo("opacity",.01,.3),o.fromTo("opacity",.01,1).fromTo("scale",1.1,1),Promise.resolve(n.addElement(i).easing("ease-in-out").duration(200).add(t).add(o))},l=function(e,i){var n=new e,t=new e;t.addElement(i.querySelector("ion-backdrop"));var o=new e;return o.addElement(i.querySelector(".loading-wrapper")),t.fromTo("opacity",.3,0),o.fromTo("opacity",.99,0).fromTo("scale",1,.9),Promise.resolve(n.addElement(i).easing("ease-in-out").duration(200).add(t).add(o))},c=function(e,i){var n=new e,t=new e;t.addElement(i.querySelector("ion-backdrop"));var o=new e;return o.addElement(i.querySelector(".loading-wrapper")),t.fromTo("opacity",.01,.32),o.fromTo("opacity",.01,1).fromTo("scale",1.1,1),Promise.resolve(n.addElement(i).easing("ease-in-out").duration(200).add(t).add(o))},p=function(e,i){var n=new e,t=new e;t.addElement(i.querySelector("ion-backdrop"));var o=new e;return o.addElement(i.querySelector(".loading-wrapper")),t.fromTo("opacity",.32,0),o.fromTo("opacity",.99,0).fromTo("scale",1,.9),Promise.resolve(n.addElement(i).easing("ease-in-out").duration(200).add(t).add(o))},m=function(){function e(i){var n=this;_classCallCheck(this,e),Object(t.m)(this,i),this.presented=!1,this.mode=Object(t.e)(this),this.keyboardClose=!0,this.duration=0,this.backdropDismiss=!1,this.showBackdrop=!0,this.translucent=!1,this.animated=!0,this.onBackdropTap=function(){n.dismiss(void 0,r.a)},this.didPresent=Object(t.d)(this,"ionLoadingDidPresent",7),this.willPresent=Object(t.d)(this,"ionLoadingWillPresent",7),this.willDismiss=Object(t.d)(this,"ionLoadingWillDismiss",7),this.didDismiss=Object(t.d)(this,"ionLoadingDidDismiss",7)}return _createClass(e,[{key:"componentWillLoad",value:function(){if(void 0===this.spinner){var e=Object(t.e)(this);this.spinner=o.b.get("loadingSpinner",o.b.get("spinner","ios"===e?"lines":"crescent"))}}},{key:"present",value:function(){var e=this;return regeneratorRuntime.async((function(i){for(;;)switch(i.prev=i.next){case 0:return i.next=2,regeneratorRuntime.awrap(Object(r.e)(this,"loadingEnter",d,c,void 0));case 2:this.duration>0&&(this.durationTimeout=setTimeout((function(){return e.dismiss()}),this.duration+10));case 3:case"end":return i.stop()}}),null,this,null,Promise)}},{key:"dismiss",value:function(e,i){return this.durationTimeout&&clearTimeout(this.durationTimeout),Object(r.f)(this,e,i,"loadingLeave",l,p)}},{key:"onDidDismiss",value:function(){return Object(r.g)(this.el,"ionLoadingDidDismiss")}},{key:"onWillDismiss",value:function(){return Object(r.g)(this.el,"ionLoadingWillDismiss")}},{key:"render",value:function(){var e,i=this.message,n=this.spinner,o=Object(t.e)(this);return Object(t.i)(t.a,{onIonBackdropTap:this.onBackdropTap,style:{zIndex:"".concat(4e4+this.overlayIndex)},class:Object.assign({},Object(a.b)(this.cssClass),(e={},_defineProperty(e,o,!0),_defineProperty(e,"loading-translucent",this.translucent),e))},Object(t.i)("ion-backdrop",{visible:this.showBackdrop,tappable:this.backdropDismiss}),Object(t.i)("div",{class:"loading-wrapper",role:"dialog"},n&&Object(t.i)("div",{class:"loading-spinner"},Object(t.i)("ion-spinner",{name:n})),i&&Object(t.i)("div",{class:"loading-content",innerHTML:Object(s.a)(i)})))}},{key:"el",get:function(){return Object(t.f)(this)}}],[{key:"style",get:function(){return".sc-ion-loading-md-h{--min-width:auto;--width:auto;--min-height:auto;--height:auto;-moz-osx-font-smoothing:grayscale;-webkit-font-smoothing:antialiased;left:0;right:0;top:0;bottom:0;display:-ms-flexbox;display:flex;position:fixed;-ms-flex-align:center;align-items:center;-ms-flex-pack:center;justify-content:center;font-family:var(--ion-font-family,inherit);contain:strict;-ms-touch-action:none;touch-action:none;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;z-index:1000}.overlay-hidden.sc-ion-loading-md-h{display:none}.loading-wrapper.sc-ion-loading-md{display:-ms-flexbox;display:flex;-ms-flex-align:inherit;align-items:inherit;-ms-flex-pack:inherit;justify-content:inherit;width:var(--width);min-width:var(--min-width);max-width:var(--max-width);height:var(--height);min-height:var(--min-height);max-height:var(--max-height);background:var(--background);opacity:0;z-index:10}.spinner-bubbles.sc-ion-loading-md, .spinner-circles.sc-ion-loading-md, .spinner-crescent.sc-ion-loading-md, .spinner-dots.sc-ion-loading-md, .spinner-lines.sc-ion-loading-md, .spinner-lines-small.sc-ion-loading-md{color:var(--spinner-color)}.sc-ion-loading-md-h{--background:var(--ion-color-step-50,#f2f2f2);--max-width:280px;--max-height:90%;--spinner-color:var(--ion-color-primary,#3880ff);color:var(--ion-color-step-850,#262626);font-size:14px}.loading-wrapper.sc-ion-loading-md{border-radius:2px;padding-left:24px;padding-right:24px;padding-top:24px;padding-bottom:24px;-webkit-box-shadow:0 16px 20px rgba(0,0,0,.4);box-shadow:0 16px 20px rgba(0,0,0,.4)}@supports ((-webkit-margin-start:0) or (margin-inline-start:0)) or (-webkit-margin-start:0){.loading-wrapper.sc-ion-loading-md{padding-left:unset;padding-right:unset;-webkit-padding-start:24px;padding-inline-start:24px;-webkit-padding-end:24px;padding-inline-end:24px}}.loading-spinner.sc-ion-loading-md + .loading-content.sc-ion-loading-md{margin-left:16px}@supports ((-webkit-margin-start:0) or (margin-inline-start:0)) or (-webkit-margin-start:0){.loading-spinner.sc-ion-loading-md + .loading-content.sc-ion-loading-md{margin-left:unset;-webkit-margin-start:16px;margin-inline-start:16px}}"}}]),e}()}}]);