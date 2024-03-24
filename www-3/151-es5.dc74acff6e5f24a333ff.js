function _defineProperty(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function _classCallCheck(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function _defineProperties(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}function _createClass(e,t,n){return t&&_defineProperties(e.prototype,t),n&&_defineProperties(e,n),e}(window.webpackJsonp=window.webpackJsonp||[]).push([[151],{nI0H:function(e,t,n){"use strict";n.r(t),n.d(t,"ion_spinner",(function(){return o}));var r=n("YrOr"),i=n("TJLY"),s=n("KSOA"),a={lines:{dur:1e3,lines:12,fn:function(e,t,n){return{y1:17,y2:29,style:{transform:"rotate(".concat(30*t+(t<6?180:-180),"deg)"),"animation-delay":"".concat(e*t/n-e,"ms")}}}},"lines-small":{dur:1e3,lines:12,fn:function(e,t,n){return{y1:12,y2:20,style:{transform:"rotate(".concat(30*t+(t<6?180:-180),"deg)"),"animation-delay":"".concat(e*t/n-e,"ms")}}}},bubbles:{dur:1e3,circles:9,fn:function(e,t,n){var r="".concat(e*t/n-e,"ms"),i=2*Math.PI*t/n;return{r:5,style:{top:"".concat(9*Math.sin(i),"px"),left:"".concat(9*Math.cos(i),"px"),"animation-delay":r}}}},circles:{dur:1e3,circles:8,fn:function(e,t,n){var r=t/n,i="".concat(e*r-e,"ms"),s=2*Math.PI*r;return{r:5,style:{top:"".concat(9*Math.sin(s),"px"),left:"".concat(9*Math.cos(s),"px"),"animation-delay":i}}}},crescent:{dur:750,circles:1,fn:function(){return{r:26,style:{}}}},dots:{dur:750,circles:3,fn:function(e,t){return{r:6,style:{left:"".concat(9-9*t,"px"),"animation-delay":-110*t+"ms"}}}}},o=function(){function e(t){_classCallCheck(this,e),Object(r.m)(this,t),this.paused=!1}return _createClass(e,[{key:"getName",value:function(){var e=this.name||i.b.get("spinner"),t=Object(r.e)(this);return e||("ios"===t?"lines":"crescent")}},{key:"render",value:function(){var e,t=Object(r.e)(this),n=this.getName(),o=a[n]||a.lines,f="number"==typeof this.duration&&this.duration>10?this.duration:o.dur,u=[];if(void 0!==o.circles)for(var p=0;p<o.circles;p++)u.push(c(o,f,p,o.circles));else if(void 0!==o.lines)for(var m=0;m<o.lines;m++)u.push(l(o,f,m,o.lines));return Object(r.i)(r.a,{class:Object.assign({},Object(s.a)(this.color),(e={},_defineProperty(e,t,!0),_defineProperty(e,"spinner-".concat(n),!0),_defineProperty(e,"spinner-paused",!!this.paused||i.b.getBoolean("_testing")),e))},u)}}],[{key:"style",get:function(){return":host{display:inline-block;position:relative;width:28px;height:28px;color:var(--color);-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}:host(.ion-color){color:var(--ion-color-base)}svg{left:0;top:0;-webkit-transform-origin:center;transform-origin:center;position:absolute;width:100%;height:100%;-webkit-transform:translateZ(0);transform:translateZ(0)}:host-context([dir=rtl]) svg,[dir=rtl] svg{left:unset;right:unset;right:0;-webkit-transform-origin:calc(100% - center);transform-origin:calc(100% - center)}:host(.spinner-lines) line,:host(.spinner-lines-small) line{stroke-width:4px;stroke-linecap:round;stroke:currentColor}:host(.spinner-lines) svg,:host(.spinner-lines-small) svg{-webkit-animation:spinner-fade-out 1s linear infinite;animation:spinner-fade-out 1s linear infinite}:host(.spinner-bubbles) svg{-webkit-animation:spinner-scale-out 1s linear infinite;animation:spinner-scale-out 1s linear infinite;fill:currentColor}:host(.spinner-circles) svg{-webkit-animation:spinner-fade-out 1s linear infinite;animation:spinner-fade-out 1s linear infinite;fill:currentColor}:host(.spinner-crescent) circle{fill:transparent;stroke-width:4px;stroke-dasharray:128px;stroke-dashoffset:82px;stroke:currentColor}:host(.spinner-crescent) svg{-webkit-animation:spinner-rotate 1s linear infinite;animation:spinner-rotate 1s linear infinite}:host(.spinner-dots) circle{stroke-width:0;fill:currentColor}:host(.spinner-dots) svg{-webkit-animation:spinner-dots 1s linear infinite;animation:spinner-dots 1s linear infinite}:host(.spinner-paused) svg{-webkit-animation-play-state:paused;animation-play-state:paused}@-webkit-keyframes spinner-fade-out{0%{opacity:1}to{opacity:0}}@keyframes spinner-fade-out{0%{opacity:1}to{opacity:0}}@-webkit-keyframes spinner-scale-out{0%{-webkit-transform:scale(1);transform:scale(1)}to{-webkit-transform:scale(0);transform:scale(0)}}@keyframes spinner-scale-out{0%{-webkit-transform:scale(1);transform:scale(1)}to{-webkit-transform:scale(0);transform:scale(0)}}@-webkit-keyframes spinner-rotate{0%{-webkit-transform:rotate(0deg);transform:rotate(0deg)}to{-webkit-transform:rotate(1turn);transform:rotate(1turn)}}@keyframes spinner-rotate{0%{-webkit-transform:rotate(0deg);transform:rotate(0deg)}to{-webkit-transform:rotate(1turn);transform:rotate(1turn)}}@-webkit-keyframes spinner-dots{0%{-webkit-transform:scale(1);transform:scale(1);opacity:.9}50%{-webkit-transform:scale(.4);transform:scale(.4);opacity:.3}to{-webkit-transform:scale(1);transform:scale(1);opacity:.9}}@keyframes spinner-dots{0%{-webkit-transform:scale(1);transform:scale(1);opacity:.9}50%{-webkit-transform:scale(.4);transform:scale(.4);opacity:.3}to{-webkit-transform:scale(1);transform:scale(1);opacity:.9}}"}}]),e}(),c=function(e,t,n,i){var s=e.fn(t,n,i);return s.style["animation-duration"]="".concat(t,"ms"),Object(r.i)("svg",{viewBox:"0 0 64 64",style:s.style},Object(r.i)("circle",{transform:"translate(32,32)",r:s.r}))},l=function(e,t,n,i){var s=e.fn(t,n,i);return s.style["animation-duration"]="".concat(t,"ms"),Object(r.i)("svg",{viewBox:"0 0 64 64",style:s.style},Object(r.i)("line",{transform:"translate(32,32)",y1:s.y1,y2:s.y2}))}}}]);