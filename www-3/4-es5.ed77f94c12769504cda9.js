function _defineProperties(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function _createClass(e,t,r){return t&&_defineProperties(e.prototype,t),r&&_defineProperties(e,r),e}function _classCallCheck(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function _inherits(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function");e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,writable:!0,configurable:!0}}),t&&_setPrototypeOf(e,t)}function _setPrototypeOf(e,t){return(_setPrototypeOf=Object.setPrototypeOf||function(e,t){return e.__proto__=t,e})(e,t)}function _createSuper(e){var t=_isNativeReflectConstruct();return function(){var r,n=_getPrototypeOf(e);if(t){var a=_getPrototypeOf(this).constructor;r=Reflect.construct(n,arguments,a)}else r=n.apply(this,arguments);return _possibleConstructorReturn(this,r)}}function _possibleConstructorReturn(e,t){return!t||"object"!=typeof t&&"function"!=typeof t?_assertThisInitialized(e):t}function _assertThisInitialized(e){if(void 0===e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return e}function _isNativeReflectConstruct(){if("undefined"==typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"==typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],(function(){}))),!0}catch(e){return!1}}function _getPrototypeOf(e){return(_getPrototypeOf=Object.setPrototypeOf?Object.getPrototypeOf:function(e){return e.__proto__||Object.getPrototypeOf(e)})(e)}(window.webpackJsonp=window.webpackJsonp||[]).push([[4],{"0/40":function(e,t,r){"use strict";r.d(t,"a",(function(){return o}));var n=r("qX8T"),a=r("bqTn"),o=function(e){_inherits(r,e);var t=_createSuper(r);function r(){var e,n=arguments.length>0&&void 0!==arguments[0]?arguments[0]:null,o=arguments.length>1&&void 0!==arguments[1]?arguments[1]:null,u=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;return _classCallCheck(this,r),(e=t.call(this,n,n,o,u)).allTrainingDays=function(){for(var t=[],r=new Date(e.fromDate),n=0;n<28;n++)t.push(new Date(r)),r.setDate(r.getDate()+1);return t},null!==e.toDate&&(e.toDate=new Date,e.toDate.setDate(n.getDate()+27),e.toDate.setHours(23,59,59,0),e.type=a.c.fourWeeksProgram),e}return r}(n.a)},"7JKF":function(e,t,r){"use strict";r.d(t,"a",(function(){return o}));var n=r("qX8T"),a=r("bqTn"),o=function(e){_inherits(r,e);var t=_createSuper(r);function r(){var e,n=arguments.length>0&&void 0!==arguments[0]?arguments[0]:null,o=arguments.length>1&&void 0!==arguments[1]?arguments[1]:null,u=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;return _classCallCheck(this,r),(e=t.call(this,n,n,o,u)).allTrainingDays=function(){for(var t=[],r=new Date(e.fromDate),n=0;n<14;n++)t.push(new Date(r)),r.setDate(r.getDate()+1);return t},null!==e.toDate&&(e.toDate=new Date,e.toDate.setDate(n.getDate()+13),e.toDate.setHours(23,59,59,0),e.type=a.c.twoWeeksProgram),e}return r}(n.a)},"UJ+M":function(e,t,r){"use strict";r.d(t,"a",(function(){return o}));var n=r("qX8T"),a=r("bqTn"),o=function(e){_inherits(r,e);var t=_createSuper(r);function r(){var e,n=arguments.length>0&&void 0!==arguments[0]?arguments[0]:null,o=arguments.length>1&&void 0!==arguments[1]?arguments[1]:null,u=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;return _classCallCheck(this,r),(e=t.call(this,n,n,o,u)).allTrainingDays=function(){for(var t=[],r=new Date(e.fromDate),n=0;n<7;n++)t.push(new Date(r)),r.setDate(r.getDate()+1);return t},null!==e.toDate&&(e.toDate=new Date,e.toDate.setDate(n.getDate()+6),e.toDate.setHours(23,59,59,0),e.type=a.c.oneWeekProgram),e}return r}(n.a)},bqTn:function(e,t,r){"use strict";r.d(t,"d",(function(){return n})),r.d(t,"e",(function(){return a})),r.d(t,"b",(function(){return o})),r.d(t,"a",(function(){return u})),r.d(t,"f",(function(){return i})),r.d(t,"c",(function(){return s})),r.d(t,"g",(function(){return c}));var n="CoreExerciseProgram",a="LocalNotification",o=function(e){return e[e.Scheduled=0]="Scheduled",e[e.Unscheduled=1]="Unscheduled",e[e.Completed=2]="Completed",e[e.ScheduledCompleted=3]="ScheduledCompleted",e[e.Failed=4]="Failed",e}({}),u=function(e){return e.None="NONE",e.Normal="NORMAL",e.Destoryed="DESTROYED",e}({}),i=function(e){return e.None="NONE",e.Time="TIME",e.Calories="CALORIES",e}({}),s=function(e){return e.oneWeekProgram="oneWeekProgram",e.twoWeeksProgram="twoWeeksProgram",e.fourWeeksProgram="fourWeeksProgram",e}({}),c=function e(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:i.None,r=arguments.length>1&&void 0!==arguments[1]?arguments[1]:0;_classCallCheck(this,e),this.targetType=t,this.value=r}},j1ZV:function(e,t,r){"use strict";r.d(t,"a",(function(){return n}));var n=function e(){_classCallCheck(this,e)}},qX8T:function(e,t,r){"use strict";var n,a=r("bqTn"),o=((n=function(){function e(t,r,n){var o=this;_classCallCheck(this,e),this.getFromDate=function(){return o.fromDate},this.setFromDate=function(e){o.fromDate=new Date(e),o.fromDate.setHours(0,0,0,0)},this.getToDate=function(){return o.toDate},this.setToDate=function(e){o.toDate=new Date(e),o.toDate.setHours(23,59,59,0)},this.getTarget=function(){return o.target},this.setTarget=function(e){o.target=e},this.setProgramState=function(e){o.programState=e},this.getProgramState=function(){return o.programState},this.hasTargetFulfilled=function(e,t){switch(t.targetType){case a.f.None:return 1===e.completion;case a.f.Time:return e.totalDurInMS>=t.value;case a.f.Calories:return e.calories>=t.value;default:return!1}},this.fromDate=t,this.toDate=r,this.target=n,this.programState=a.a.Normal}return _createClass(e,[{key:"progressCalculate",value:function(e){var t=this.calculateDayStatus(e),r=t.filter((function(e){return e.status===a.b.ScheduledCompleted})),n=this.getScheduledTrainingDays().length;return{progress:t,completion:r.length/n}}},{key:"calculateDayStatus",value:function(e){return null}}]),e}()).str2ProgramState=function(e){return e===a.a.Normal?a.a.Normal:e===a.a.Destoryed?a.a.Destoryed:a.a.None},n);r.d(t,"a",(function(){return u}));var u=function(e){_inherits(r,e);var t=_createSuper(r);function r(){var e,n=arguments.length>0&&void 0!==arguments[0]?arguments[0]:null,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:null,o=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null,u=arguments.length>3&&void 0!==arguments[3]?arguments[3]:null;return _classCallCheck(this,r),(e=t.call(this,n,a,o)).setWeekDayEnable=function(t,r){e.weekDayEnable[t]=r,e.totalDayEnabledWeekly++},e.allTrainingDays=function(){return[]},e.getWeekDayEnable=function(){return e.weekDayEnable},e.getTotalDayEnabledWeekly=function(){return e.totalDayEnabledWeekly},e.weekDayEnable=u,null!==e.weekDayEnable&&(e.totalDayEnabledWeekly=e.weekDayEnable.filter((function(e){return e})).length),e}return _createClass(r,[{key:"getPackedSettings",value:function(){return{type:this.type,fromDate:this.fromDate,toDate:this.toDate,target:this.target,trainingTarget:this.target,programState:this.programState,settings:{everySun:this.weekDayEnable[0],everyMon:this.weekDayEnable[1],everyTue:this.weekDayEnable[2],everyWed:this.weekDayEnable[3],everyThu:this.weekDayEnable[4],everyFri:this.weekDayEnable[5],everySat:this.weekDayEnable[6]}}}},{key:"unpackSettings",value:function(e){this.fromDate=e.getFromDate(),this.toDate=e.getToDate(),this.programState=o.str2ProgramState(e.getProgramState()),this.target=e.getTrainingTarget();var t=e.getSettings();this.weekDayEnable=Object.keys(t).map((function(e){return t[e]})),this.totalDayEnabledWeekly=this.weekDayEnable.filter((function(e){return e})).length}},{key:"calculateDayStatus",value:function(e){var t=this,r=this.allTrainingDays().map((function(e){return{date:e,status:t.isTodayScheduled(e)?a.b.Scheduled:a.b.Unscheduled}}));e.forEach((function(e){var n=r.map((function(e){return e.date.getTime()})).indexOf(e.startTime.getTime());-1!==n&&t.hasTargetFulfilled(e,t.target)&&(r[n].status=t.isTodayScheduled(e.startTime)?a.b.ScheduledCompleted:a.b.Completed)}));var n=new Date;n.setHours(0,0,0,0);for(var o=r.map((function(e){return e.date.getTime()})).indexOf(n.getTime()),u=null,i=0;i<=o;i++)r[i].status===a.b.Scheduled?u=r[i]:r[i].status===a.b.ScheduledCompleted?u=null:r[i].status===a.b.Completed&&null!==u&&(u.status=a.b.Unscheduled,r[i].status=a.b.ScheduledCompleted,u=null);if(r[o].status===a.b.Unscheduled)for(var s=o-1;s>=0;s--){if(r[s].status===a.b.Scheduled){r[s].status=a.b.Unscheduled,r[o].status=a.b.Scheduled;break}if(r[s].status!==a.b.Unscheduled)break}for(var c=null,l=0;l<o;l++)r[l].status===a.b.Completed?c=r[l]:r[l].status===a.b.ScheduledCompleted?c=null:r[l].status===a.b.Scheduled&&null!==c&&(c.status=a.b.ScheduledCompleted,r[l].status=a.b.Unscheduled,c=null);return r.filter((function(e){return e.status===a.b.Scheduled&&e.date.getTime()<n.getTime()})).forEach((function(e){e.status=a.b.Failed})),r}},{key:"isTodayScheduled",value:function(e){return this.weekDayEnable[e.getDay()]}},{key:"getScheduledTrainingDays",value:function(){var e=this;return this.allTrainingDays().filter((function(t){return e.weekDayEnable[t.getDay()]}))}}]),r}(o)},tdY7:function(e,t,r){"use strict";var n,a=r("mrSG"),o=r("Z18M"),u=r("UJ+M"),i=r("7JKF"),s=r("0/40"),c=r("bqTn"),l=((n=function e(){_classCallCheck(this,e)}).createProgramSetting=function(e){return e===c.c.oneWeekProgram?new u.a:e===c.c.twoWeeksProgram?new i.a:e===c.c.fourWeeksProgram?new s.a:null},n),f=function(){return function(){var e=new Date("October 3, 2020 00:00:00"),t=new Date("September 30, 2020 00:00:00"),r=new Date(t);r.setDate(r.getDate()+27);var n=new Date(e);n.setHours(0,0,0,0);var a=new Date(e);a.setHours(8,2,0,0);var o=a.getTime()-n.getTime();return{today:e,fromDate:t,toDate:r,coreExercise:[{startTime:new Date("October 1, 2020 00:00:00"),endTime:a,totalDurInMS:o,calories:100,completion:1},{startTime:new Date("October 2, 2020 00:00:00"),endTime:a,totalDurInMS:o,calories:100,completion:1}]}}()},d={todayTestData:f().today,toDateTestData:f().toDate,fromDateTestData:f().fromDate,coreExerciseTestData:f().coreExercise},g=r("8Y7J"),h=r("xgBC"),D=r("TSSN");r.d(t,"a",(function(){return b}));var p,m=new s.a(d.fromDateTestData,new c.g,[!1,!1,!1,!0,!0,!1,!1]),b=((p=function e(t,r,n){var o=this;_classCallCheck(this,e),this.backend=t,this.storage=r,this.translate=n,this.debug=!1,this.getKey=function(e){return c.d+"-"+e},this.setNextReminderNotification=function(){return a.b(o,void 0,void 0,regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:case"end":return e.stop()}}),e)})))},this.addProgram=function(e){return a.b(o,void 0,void 0,regeneratorRuntime.mark((function t(){var r;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,this.backend.getCoreExercisePlanSettings(new Date,new Date);case 2:if(t.t0=t.sent.length,!(t.t0>0)){t.next=5;break}return t.abrupt("return",!1);case 5:return r=e.getPackedSettings(),t.prev=6,t.next=9,this.backend.saveCoreExercisePlanSettings(r.type,r.fromDate,r.toDate,r.settings,r.trainingTarget,r.programState);case 9:return t.abrupt("return",!0);case 12:return t.prev=12,t.t1=t.catch(6),t.abrupt("return",!1);case 15:case"end":return t.stop()}}),t,this,[[6,12]])})))},this.deleteProgram=function(){return a.b(o,void 0,void 0,regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,this.backend.getCoreExercisePlanSettings(new Date,new Date);case 2:if(0!==(t=e.sent).length){e.next=5;break}return e.abrupt("return",!1);case 5:return e.prev=5,e.next=8,this.backend.removeCoreExercisePlanSettings(t[0].id);case 8:return e.abrupt("return",!0);case 11:return e.prev=11,e.t0=e.catch(5),e.abrupt("return",(console.log(e.t0),!1));case 14:case"end":return e.stop()}}),e,this,[[5,11]])})))},this.getProgramSettings=function(){return a.b(o,void 0,void 0,regeneratorRuntime.mark((function e(){var t,r;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,this.backend.getCoreExercisePlanSettings(new Date,new Date);case 2:if(0!==(t=e.sent).length){e.next=5;break}return e.abrupt("return",null);case 5:return r=l.createProgramSetting(t[0].getType()),e.abrupt("return",(r.unpackSettings(t[0]),r));case 7:case"end":return e.stop()}}),e,this)})))},this.getProgramProgress=function(e,t){return a.b(o,void 0,void 0,regeneratorRuntime.mark((function r(){var n,a,o,u;return regeneratorRuntime.wrap((function(r){for(;;)switch(r.prev=r.next){case 0:return r.next=2,this.backend.getCoreExercisePlanSettings(e,t);case 2:if(0!==(a=r.sent).length){r.next=5;break}return r.abrupt("return",null);case 5:return this.debug?n=m:(n=l.createProgramSetting(a[0].getType())).unpackSettings(a[0]),r.t0=function(e,t){return e.reduce((function(e,t){var r=t.startTime;return e[r]=e[r]||[],e[r].push(t),e}),{})},r.next=9,this.backend.queryCoreExercises4Dates(e,t,1e3);case 9:return r.t1=r.sent.map((function(e){var t=e.getStartTime();return t.setHours(0,0,0,0),{startTime:t,endTime:e.getEndTime(),totalDurInMS:e.getTotalDurInMS(),calories:e.getCalories(),completion:e.getCompletion(),result:e.getResult()}})),o=(0,r.t0)(r.t1),u=Object.keys(o).map((function(e){return{startTime:new Date(e),endTime:o[e].map((function(e){return e.endTime}))[o[e].length-1],totalDurInMS:o[e].reduce((function(e,t){return e+t.totalDurInMS}),0),calories:o[e].reduce((function(e,t){return e+t.calories}),0),completion:o[e].reduce((function(e,t){return 1===e||1===t.completion?1:0}),0)}})),r.abrupt("return",n.progressCalculate(this.debug?d.coreExerciseTestData:u));case 13:case"end":return r.stop()}}),r,this)})))},this.getShowTodayReminder=function(){return a.b(o,void 0,void 0,regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,this.storage.get(this.getKey(c.e));case 2:return t=e.sent,e.abrupt("return",void 0===t||t<new Date);case 4:case"end":return e.stop()}}),e,this)})))}}).ngInjectableDef=g.ac({factory:function(){return new p(g.bc(o.c),g.bc(h.b),g.bc(D.k))},token:p,providedIn:"root"}),p)}}]);