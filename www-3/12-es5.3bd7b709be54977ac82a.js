(window.webpackJsonp=window.webpackJsonp||[]).push([[12],{"U/P7":function(n,e,t){"use strict";t.r(e),t.d(e,"startHardwareBackButton",(function(){return r}));var r=function(){var n=document,e=!1;n.addEventListener("backbutton",(function(){if(!e){var t=[],r=new CustomEvent("ionBackButton",{bubbles:!1,detail:{register:function(n,e){t.push({priority:n,handler:e})}}});if(n.dispatchEvent(r),t.length>0){var o,i=Number.MIN_SAFE_INTEGER;t.forEach((function(n){var e=n.priority,t=n.handler;e>=i&&(i=e,o=t)})),e=!0,a(o).then((function(){return e=!1}))}}}))},a=function(n){var e;return regeneratorRuntime.async((function(t){for(;;)switch(t.prev=t.next){case 0:if(t.prev=0,!n){t.next=7;break}if(e=n(),t.t0=null!=e,!t.t0){t.next=7;break}return t.next=7,regeneratorRuntime.awrap(e);case 7:t.next=12;break;case 9:t.prev=9,t.t1=t.catch(0),console.error(t.t1);case 12:case"end":return t.stop()}}),null,null,[[0,9]],Promise)}}}]);