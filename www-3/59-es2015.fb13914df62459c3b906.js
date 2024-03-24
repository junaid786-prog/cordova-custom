(window.webpackJsonp=window.webpackJsonp||[]).push([[59],{w3vW:function(n,e,l){"use strict";l.r(e);var t=l("8Y7J"),i=l("mrSG");class o{constructor(n){this.translate=n,this.getFormattedReps=n=>n>1?n+" "+this.translate.instant("core-training.reps"):n+" "+this.translate.instant("core-training.rep"),this.getFormattedDuration=n=>{let e=n;return e>1?e+" "+this.translate.instant("core-training.seconds"):e+" "+this.translate.instant("core-training.second")},this.workoutRestTimeMin=()=>Math.floor(this.workoutRestTime/60),this.workoutRestTimeSec=()=>Math.floor(this.workoutRestTime%60)}ngOnInit(){}ngOnDestroy(){}}var r=l("OUMn"),s=l("8cXU"),a=l("ZZ/e"),u=l("h5tO"),c=l("tdY7"),b=l("Z18M"),g=l("UJ+M"),d=l("7JKF"),m=l("0/40"),p=l("nhhH");const h="CoreTraining",x="Level",v="CustomProgramme";class f{constructor(n,e,l,t,o,r,a,c,b){this.navCtrl=n,this.popupController=e,this.translate=l,this.zone=t,this.storage=o,this.activityStateManager=r,this.coreExerciseProgram=a,this.mobileBackend=c,this.preferences=b,this.currentTrainingProgramme=Object.assign({},s.a.getInstance().getTrainingProgramme(s.d.EASY)),this.currentTrainingLevel=s.d.EASY,this.hasLoadedLastProgramme=!1,this.planAllowed=!1,this.onClickUpdateWorkoutsSetup=n=>{this.showWorkoutSettingPopoutController(this.currentTrainingProgramme.workoutList[n],e=>{const l=[...this.currentTrainingProgramme.workoutList];try{this.zone.run(()=>i.b(this,void 0,void 0,(function*(){let t=!1;for(let i in e)l[n][i]!==e[i]&&(t=!0);t&&(l[n]=Object.assign({},e),this.currentTrainingProgramme.workoutList=l,this.setLevelToCustom(!1))})))}catch(t){console.log(t)}})},this.onClickRemoveWorkout=n=>i.b(this,void 0,void 0,(function*(){let e=[];e=[{text:this.translate.instant("global.no"),role:"cancel"},{text:this.translate.instant("global.yes"),handler:()=>i.b(this,void 0,void 0,(function*(){try{this.zone.run(()=>i.b(this,void 0,void 0,(function*(){const e=this.currentTrainingProgramme.workoutList.filter((e,l)=>l!=n);this.setLevelToCustom(!1),this.currentTrainingProgramme.workoutList=e})))}catch(e){console.log(e)}}))}],yield this.popupController.presentAlertController({header:this.translate.instant("core-training.remove-exercise"),message:this.translate.instant("core-training.confirm-remove-exercise"),buttons:e})})),this.onClickNumberOfSets=()=>{this.showNumOfSetPopoutController(this.currentTrainingProgramme.numOfSets,n=>i.b(this,void 0,void 0,(function*(){this.zone.run(()=>i.b(this,void 0,void 0,(function*(){this.currentTrainingProgramme.numOfSets!==n&&(this.currentTrainingProgramme.numOfSets=n,this.setLevelToCustom(!1))})))})))},this.onClickRestTimeBetweenSet=()=>{this.showRestTimeController(this.currentTrainingProgramme.restTimeBetweenSetInSecond,n=>i.b(this,void 0,void 0,(function*(){this.zone.run(()=>i.b(this,void 0,void 0,(function*(){this.currentTrainingProgramme.numOfSets!==n&&(this.currentTrainingProgramme.restTimeBetweenSetInSecond=n,this.setLevelToCustom(!1))})))})))},this.onClickAddWorkout=()=>{let n={name:s.b.SQUAT,reps:0,icon:s.a.getIconByExerciseName(name),measurement:s.a.getWorkoutMeasurementMappings(s.b.SQUAT)};this.showWorkoutSettingPopoutController(n,n=>i.b(this,void 0,void 0,(function*(){this.zone.run(()=>i.b(this,void 0,void 0,(function*(){const e=[...this.currentTrainingProgramme.workoutList,n];this.currentTrainingProgramme.workoutList=e,this.setLevelToCustom(!1)})))})))},this.onClickRestTimeBetweenExercise=()=>{this.showRestTimeController(this.currentTrainingProgramme.restTimeBetweenExerciseInSecond,n=>i.b(this,void 0,void 0,(function*(){this.zone.run(()=>i.b(this,void 0,void 0,(function*(){this.currentTrainingProgramme.restTimeBetweenExerciseInSecond!==n&&(this.currentTrainingProgramme.restTimeBetweenExerciseInSecond=n,this.setLevelToCustom(!1))})))})))},this.onClickStartTraining=()=>i.b(this,void 0,void 0,(function*(){try{const n=[...u.c,u.a.BASIC_TRAINING,u.a.CORE_TRAINING,u.a.POSTURE_MONITORING],e=this.activityStateManager.validate(n,u.a.CORE_TRAINING,"/core-training-setting");e.status?null==(yield this.preferences.getCoreTrainingMotionDataCollection())?yield this.popupController.presentAlertController({header:this.translate.instant("core-training.motion-data-collection"),message:this.translate.instant("core-training.motion-data-collection-detail"),buttons:[{text:this.translate.instant("global.no"),role:"cancel",handler:()=>i.b(this,void 0,void 0,(function*(){yield this.preferences.setCoreTrainingMotionDataCollection(!1),this.startCoreTraining()}))},{text:this.translate.instant("global.yes"),handler:()=>i.b(this,void 0,void 0,(function*(){yield this.preferences.setCoreTrainingMotionDataCollection(!0),this.startCoreTraining()}))}]}):this.startCoreTraining():yield this.popupController.presentAlertController({header:e.header,message:e.message,buttons:e.buttons},!0)}catch(n){console.log(n)}})),this.startCoreTraining=()=>{this.zone.run(()=>{const n={state:{data:Object.assign({},this.currentTrainingProgramme)}};this.navCtrl.navigateForward("/core-training",n)})},this.showNumOfSetPopoutController=(n,e)=>{let l=[];l.push({name:"numOfSet",options:this.popupController.getColumnOptions(Array.from({length:20},(n,e)=>e).map(n=>n+1)),selectedIndex:this.currentTrainingProgramme.numOfSets-1});const t=[this.translate.instant("core-training.unit-set")];l.push({name:"unit",options:this.popupController.getColumnOptions(t),selectedIndex:0}),this.popupController.presentNativePickerController(l,n=>i.b(this,void 0,void 0,(function*(){try{e(Number(n[0].description))}catch(l){yield this.presentAlert(l)}})))},this.showRestTimeController=(n,e)=>{let l=[];l.push({name:"minValue",options:this.popupController.getColumnOptions(Array.from({length:6},(n,e)=>e).map(n=>n)),selectedIndex:Math.floor(n/60)});let t=[this.translate.instant("core-training.mins")];l.push({name:"minUnit",options:this.popupController.getColumnOptions(t),selectedIndex:0}),l.push({name:"secValue",options:this.popupController.getColumnOptions(Array.from({length:60},(n,e)=>e).map(n=>n)),selectedIndex:n%60}),t=[this.translate.instant("core-training.seconds")],l.push({name:"secUnit",options:this.popupController.getColumnOptions(t),selectedIndex:0}),this.popupController.presentNativePickerController(l,n=>i.b(this,void 0,void 0,(function*(){try{const l=60*Number(n[0].description)+Number(n[2].description);e(l)}catch(l){yield this.presentAlert(l)}})))},this.showWorkoutSettingPopoutController=(n,e)=>{let l=[];const t=Object.values(s.b).filter(n=>n!==s.b.REST&&n!==s.b.UNKNWON).map(n=>this.translate.instant(n)),o=this.translate.instant(n.name);l.push({name:"exercise",options:this.popupController.getColumnOptions(t),selectedIndex:this.popupController.getSelectedIndex(t,o)}),l.push({name:"repsValue",options:this.popupController.getColumnOptions(Array.from({length:300},(n,e)=>e).map(n=>n+1)),selectedIndex:n.reps-1});let r=[this.translate.instant("core-training.reps"),this.translate.instant("core-training.seconds")];l.push({name:"repsUnit",options:this.popupController.getColumnOptions(r),selectedIndex:n.measurement-1}),this.popupController.presentNativePickerController(l,l=>i.b(this,void 0,void 0,(function*(){try{const o=l[0].description,a=Number(l[1].description),u=l[2].description,c=Object.assign({},n);c.name=Object.values(s.b)[t.indexOf(o)],c.icon=s.a.getIconByExerciseName(c.name),c.reps=a,c.measurement=u===r[0]?s.e.COUNT:s.e.DURATION,c.name===s.b.PLANK&&c.measurement==s.e.COUNT?this.zone.run(()=>i.b(this,void 0,void 0,(function*(){yield this.presentAlertMessage(this.translate.instant("core-training.alert-plank-set-count"))}))):e(c)}catch(o){yield this.presentAlert(o)}})))},this.presentAlertMessage=n=>i.b(this,void 0,void 0,(function*(){try{yield this.popupController.presentAlertController({header:this.translate.instant("global.error"),message:n,buttons:[{text:this.translate.instant("global.okay")}]},!0)}catch(e){console.log(e)}})),this.presentAlert=n=>i.b(this,void 0,void 0,(function*(){this.presentAlertMessage(n.message)})),this.setLevelToEasy=()=>i.b(this,void 0,void 0,(function*(){yield this.saveTrainingLevel(s.d.EASY),this.currentTrainingProgramme=Object.assign({},s.a.getInstance().getTrainingProgramme(s.d.EASY))})),this.setLevelToNormal=()=>i.b(this,void 0,void 0,(function*(){yield this.saveTrainingLevel(s.d.NORMAL),this.currentTrainingProgramme=Object.assign({},s.a.getInstance().getTrainingProgramme(s.d.NORMAL))})),this.setLevelToExpert=()=>i.b(this,void 0,void 0,(function*(){yield this.saveTrainingLevel(s.d.EXPERT),this.currentTrainingProgramme=Object.assign({},s.a.getInstance().getTrainingProgramme(s.d.EXPERT))})),this.setLevelToCustom=n=>i.b(this,void 0,void 0,(function*(){yield this.saveTrainingLevel(s.d.CUSTOM),n?(yield this.loadCustomProgramme())||(this.currentTrainingProgramme=Object.assign({},s.a.getInstance().getTrainingProgramme(s.d.CUSTOM))):this.saveCustomProgramme()})),this.isEasy=()=>this.currentTrainingLevel==s.d.EASY,this.isNormal=()=>this.currentTrainingLevel==s.d.NORMAL,this.isExpert=()=>this.currentTrainingLevel==s.d.EXPERT,this.isCustom=()=>this.currentTrainingLevel==s.d.CUSTOM,this.loadTrainingLevel=()=>i.b(this,void 0,void 0,(function*(){try{const n=yield this.storage.get(this.getKey(x));this.currentTrainingLevel=null!=n?n:s.d.EASY}catch(n){console.error("loadTrainingLevel exception "+n)}})),this.saveTrainingLevel=n=>i.b(this,void 0,void 0,(function*(){try{this.currentTrainingLevel=n,yield this.storage.set(this.getKey(x),this.currentTrainingLevel)}catch(e){console.error("saveTrainingLevel exception "+e)}})),this.loadCustomProgramme=()=>i.b(this,void 0,void 0,(function*(){try{let n=yield this.storage.get(this.getKey(v));if(null!=n)return this.currentTrainingProgramme=n,!0}catch(n){console.log(n)}return!1})),this.saveCustomProgramme=()=>i.b(this,void 0,void 0,(function*(){try{yield this.storage.set(this.getKey(v),this.currentTrainingProgramme)}catch(n){console.log(n)}})),this.isMeasuredInCount=n=>n===s.e.COUNT,this.getKey=n=>h+"-"+n,this.restTimeBetweenSetMin=()=>Math.floor(this.currentTrainingProgramme.restTimeBetweenSetInSecond/60),this.restTimeBetweenSetSec=()=>Math.floor(this.currentTrainingProgramme.restTimeBetweenSetInSecond%60),this.restTimeBetweenExerciseMin=()=>Math.floor(this.currentTrainingProgramme.restTimeBetweenExerciseInSecond/60),this.restTimeBetweenExerciseSec=()=>Math.floor(this.currentTrainingProgramme.restTimeBetweenExerciseInSecond%60),this.onClickNavToFourWeeksProgramSetting=()=>{this.currentPlan!==this.translate.instant("global.off")?this.navCtrl.navigateForward("/core-training-plan"):this.navCtrl.navigateForward("/core-training-plan-settings",{state:{level:this.currentTrainingLevel}})}}ngOnInit(){return i.b(this,void 0,void 0,(function*(){yield this.loadTrainingLevel(),this.currentTrainingLevel==s.d.EASY?yield this.setLevelToEasy():this.currentTrainingLevel==s.d.NORMAL?yield this.setLevelToNormal():this.currentTrainingLevel==s.d.EXPERT?yield this.setLevelToExpert():this.currentTrainingLevel==s.d.CUSTOM&&(yield this.setLevelToCustom(!0))}))}ionViewWillEnter(){return i.b(this,void 0,void 0,(function*(){try{this.planAllowed=null!==this.mobileBackend.getLoginUser(),this.currentPlan=this.translate.instant("global.off");const n=yield this.coreExerciseProgram.getProgramSettings();n instanceof g.a?this.currentPlan=this.translate.instant("global.1-week"):n instanceof d.a?this.currentPlan=this.translate.instant("global.2-week"):n instanceof m.a&&(this.currentPlan=this.translate.instant("global.4-week"))}catch(n){console.error(n)}}))}ionViewWillLeave(){}}class C{}var k=l("pMnS"),w=l("oBZk"),T=l("TSSN"),P=l("SVse"),O=t.Ab({encapsulation:0,styles:[[".exercise-option[_ngcontent-%COMP%]{color:var(--solos-color-a);display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row}.list-index-container[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1;width:100px;height:100px;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;margin-right:.5rem}.circle-container[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1;width:2.2rem;height:2.2rem;align-self:center}.circle[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;-webkit-box-pack:center;justify-content:center;border-style:solid;border-width:2px;border-radius:50%;height:100%;width:100%;font-size:1em}.stage-num[_ngcontent-%COMP%]{align-self:center;font-size:1.2rem}.line[_ngcontent-%COMP%]{-webkit-box-flex:3;flex:3;border-left:2px solid var(--solos-color-a);height:100%;align-self:center}.exercise-item[_ngcontent-%COMP%]{-webkit-box-flex:5;flex:5}.exercise-content[_ngcontent-%COMP%]{-webkit-box-flex:6;flex:6;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;color:#fff;height:100%;width:100%}.exercise-repetition[_ngcontent-%COMP%], .exercise-rest-time[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;font-size:1rem}.exercise-repetition[_ngcontent-%COMP%]{-webkit-box-flex:2;flex:2}.exercise-rest-time[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1;color:#8e8e93;font-size:1rem}.exercise-repetition-item[_ngcontent-%COMP%], .exercise-rest-time-item[_ngcontent-%COMP%]{-webkit-box-flex:2;flex:2;font-size:1rem;margin-bottom:10px;align-self:center}.reps-value[_ngcontent-%COMP%]{font-size:.9rem}.large-icon[_ngcontent-%COMP%]{font-size:2.5rem}.icon[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1;align-self:center;font-size:1.1rem;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center}.rest-time-content[_ngcontent-%COMP%]{-webkit-box-flex:4;flex:4;font-size:1rem;align-self:center}.remove-btn[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1;font-size:1.2rem;padding-bottom:5px;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center}.btn-icon[_ngcontent-%COMP%]{align-self:center}"]],data:{}});function y(n){return t.Xb(0,[(n()(),t.Cb(0,0,null,null,1,"div",[["class","exercise-repetition-item reps-value"]],null,null,null,null,null)),(n()(),t.Vb(1,null,[" / "," "]))],null,(function(n,e){var l=e.component;n(e,1,0,l.getFormattedReps(l.workoutReps))}))}function M(n){return t.Xb(0,[(n()(),t.Cb(0,0,null,null,1,"div",[["class","exercise-repetition-item reps-value"]],null,null,null,null,null)),(n()(),t.Vb(1,null,[" / "," "]))],null,(function(n,e){var l=e.component;n(e,1,0,l.getFormattedDuration(l.workoutReps))}))}function j(n){return t.Xb(0,[t.Pb(0,P.e,[t.z]),(n()(),t.Cb(1,0,null,null,32,"div",[["class","exercise-option"]],null,null,null,null,null)),(n()(),t.Cb(2,0,null,null,5,"div",[["class","list-index-container"]],null,null,null,null,null)),(n()(),t.Cb(3,0,null,null,3,"div",[["class","circle-container"]],null,null,null,null,null)),(n()(),t.Cb(4,0,null,null,2,"div",[["class","circle"]],null,null,null,null,null)),(n()(),t.Cb(5,0,null,null,1,"span",[["class","stage-num"]],null,null,null,null,null)),(n()(),t.Vb(6,null,["",""])),(n()(),t.Cb(7,0,null,null,0,"div",[["class","line"]],null,null,null,null,null)),(n()(),t.Cb(8,0,null,null,22,"ion-item",[["class","exercise-item ion-no-padding"],["lines","none"]],null,[[null,"click"]],(function(n,e,l){var t=!0,i=n.component;return"click"===e&&(t=!1!==i.onClickUpdateWorkoutsSetup(i.index)&&t),t}),w.W,w.o)),t.Bb(9,49152,null,0,a.J,[t.j,t.p,t.F],{lines:[0,"lines"]},null),(n()(),t.Cb(10,0,null,0,20,"div",[["class","exercise-content"]],null,null,null,null,null)),(n()(),t.Cb(11,0,null,null,10,"div",[["class","exercise-repetition"]],null,null,null,null,null)),(n()(),t.Cb(12,0,null,null,2,"div",[["class","exercise-repetition-item icon"]],null,null,null,null,null)),(n()(),t.Cb(13,0,null,null,1,"ion-icon",[["class","button-icon large-icon"]],null,null,null,w.R,w.j)),t.Bb(14,49152,null,0,a.E,[t.j,t.p,t.F],{src:[0,"src"]},null),(n()(),t.Cb(15,0,null,null,2,"div",[["class","exercise-repetition-item"]],null,null,null,null,null)),(n()(),t.Vb(16,null,[" "," "])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.rb(16777216,null,null,1,null,y)),t.Bb(19,16384,null,0,P.l,[t.X,t.T],{ngIf:[0,"ngIf"]},null),(n()(),t.rb(16777216,null,null,1,null,M)),t.Bb(21,16384,null,0,P.l,[t.X,t.T],{ngIf:[0,"ngIf"]},null),(n()(),t.Cb(22,0,null,null,8,"div",[["class","exercise-rest-time"]],null,null,null,null,null)),(n()(),t.Cb(23,0,null,null,2,"div",[["class","exercise-rest-time-item icon"]],null,null,null,null,null)),(n()(),t.Cb(24,0,null,null,1,"ion-icon",[["class","button-icon"],["src","assets/imgs/stop-watch.svg"]],null,null,null,w.R,w.j)),t.Bb(25,49152,null,0,a.E,[t.j,t.p,t.F],{src:[0,"src"]},null),(n()(),t.Cb(26,0,null,null,4,"div",[["class","exercise-rest-time-item rest-time-content"]],null,null,null,null,null)),(n()(),t.Vb(27,null,[" "," "," : "," "])),t.Pb(131072,T.j,[T.k,t.j]),t.Rb(29,2),t.Rb(30,2),(n()(),t.Cb(31,0,null,null,2,"div",[["class","remove-btn"]],null,[[null,"click"]],(function(n,e,l){var t=!0,i=n.component;return"click"===e&&(t=!1!==i.onClickRemoveWorkout(i.index)&&t),t}),null,null)),(n()(),t.Cb(32,0,null,null,1,"ion-icon",[["class","button-icon btn-icon"],["src","assets/imgs/bin-01.svg"]],null,null,null,w.R,w.j)),t.Bb(33,49152,null,0,a.E,[t.j,t.p,t.F],{src:[0,"src"]},null)],(function(n,e){var l=e.component;n(e,9,0,"none"),n(e,14,0,t.Gb(1,"",l.workoutIconSrc,"")),n(e,19,0,l.isMeasuredInCount),n(e,21,0,!l.isMeasuredInCount),n(e,25,0,"assets/imgs/stop-watch.svg"),n(e,33,0,"assets/imgs/bin-01.svg")}),(function(n,e){var l=e.component;n(e,6,0,l.index+1),n(e,16,0,t.Wb(e,16,0,t.Ob(e,17).transform(l.workoutName)));var i=t.Wb(e,27,0,t.Ob(e,28).transform("core-training.rest-time")),o=t.Wb(e,27,1,n(e,29,0,t.Ob(e,0),l.workoutRestTimeMin(),"2.0-0")),r=t.Wb(e,27,2,n(e,30,0,t.Ob(e,0),l.workoutRestTimeSec(),"2.0-0"));n(e,27,0,i,o,r)}))}var S=l("xgBC"),B=t.Ab({encapsulation:0,styles:[['.main-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column}.intro-text-container[_ngcontent-%COMP%]{text-align:center;padding:1rem .5rem 1.5rem;position:relative;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;margin-bottom:1rem}.intro-text-container[_ngcontent-%COMP%]::before{content:"";background-image:url(core-training-banner.691f444e61865e61a750.png);background-size:cover;position:absolute;top:0;right:0;bottom:0;left:0;opacity:.5}.intro-text[_ngcontent-%COMP%]{color:var(--solos-color-a);position:relative}.choose-level-title[_ngcontent-%COMP%]{font-size:1rem;margin:0 10px;text-align:center;color:var(--solos-color-a);text-transform:uppercase}.level-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:justify;justify-content:space-between;margin:20px}.level-selection[_ngcontent-%COMP%]{flex-basis:23%;font-size:.8rem;text-align:center;padding:12px 0;color:var(--solos-color-a)}.active[_ngcontent-%COMP%]{background:var(--solos-color-d);border-color:var(--solos-color-b)}.inactive[_ngcontent-%COMP%]{background:0 0;border-color:var(--solos-color-a);border-style:solid;border-width:1px}.start-button[_ngcontent-%COMP%]{width:80%}.large-font[_ngcontent-%COMP%]{font-size:1.1rem}.repetition-global-setting[_ngcontent-%COMP%], .rest-time-global-setting[_ngcontent-%COMP%]{font-size:.8rem}.exercise-set-title-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center;color:var(--solos-color-a);border-width:1px 0;border-style:solid;margin:5px 15px 0;padding:5px;font-size:1rem}.selected-exercise-list[_ngcontent-%COMP%]{font-size:.8rem;background:var(--solos-color-b);display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;margin:10px}.add-exercise-item[_ngcontent-%COMP%]{-webkit-box-flex:5;flex:5}.add-exercise-btn[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;color:#f34d24}.list-index-container[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1;width:100px;height:0;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;margin-right:1em}.circle-container[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1;width:2.2rem;height:2.2rem;align-self:center}.circle[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:horizontal;-webkit-box-direction:normal;flex-direction:row;-webkit-box-pack:center;justify-content:center;border-style:solid;border-width:2px;border-radius:50%;height:100%;width:100%;font-size:1em}.stage-num[_ngcontent-%COMP%]{align-self:center;font-size:1.2rem}.add-exercise-btn-title[_ngcontent-%COMP%]{-webkit-box-flex:6;flex:6;height:50px;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;font-weight:bolder;font-size:1.8rem;color:var(--solos-color-a)}.tmp-container[_ngcontent-%COMP%]{-webkit-box-flex:1;flex:1}.footer-buttons[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-align:center;align-items:center;-webkit-box-pack:center;justify-content:center;padding:10px;background:var(--solos-color-b);border-width:1px 0 0;border-style:solid;border-color:var(--solos-color-a)}.footer-padding[_ngcontent-%COMP%]{height:calc(env(safe-area-inset-bottom));background:var(--solos-color-b)}.md.spm[_ngcontent-%COMP%]{margin-left:20px}.ios.spm[_ngcontent-%COMP%]{margin-left:15px}']],data:{}});function L(n){return t.Xb(0,[(n()(),t.Cb(0,0,null,null,8,"ion-item",[["button",""],["class","large-font settings-item"],["detail",""],["lines","none"]],null,[[null,"click"]],(function(n,e,l){var t=!0;return"click"===e&&(t=!1!==n.component.onClickNavToFourWeeksProgramSetting()&&t),t}),w.W,w.o)),t.Bb(1,49152,null,0,a.J,[t.j,t.p,t.F],{button:[0,"button"],detail:[1,"detail"],lines:[2,"lines"]},null),(n()(),t.Cb(2,0,null,0,3,"ion-label",[["color","settings-left-label ion-text-wrap"]],null,null,null,w.X,w.p)),t.Bb(3,49152,null,0,a.P,[t.j,t.p,t.F],{color:[0,"color"]},null),(n()(),t.Vb(4,0,["",""])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(6,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,w.X,w.p)),t.Bb(7,49152,null,0,a.P,[t.j,t.p,t.F],{color:[0,"color"]},null),(n()(),t.Vb(8,0,["",""]))],(function(n,e){n(e,1,0,"","","none"),n(e,3,0,"settings-left-label ion-text-wrap"),n(e,7,0,"settings-right-label")}),(function(n,e){var l=e.component;n(e,4,0,t.Wb(e,4,0,t.Ob(e,5).transform("global.plan"))),n(e,8,0,l.currentPlan)}))}function I(n){return t.Xb(0,[(n()(),t.Cb(0,0,null,null,1,"app-core-training-option",[],null,null,null,j,O)),t.Bb(1,245760,null,0,o,[T.k],{index:[0,"index"],workoutName:[1,"workoutName"],workoutIconSrc:[2,"workoutIconSrc"],workoutReps:[3,"workoutReps"],workoutRestTime:[4,"workoutRestTime"],isMeasuredInCount:[5,"isMeasuredInCount"],onClickUpdateWorkoutsSetup:[6,"onClickUpdateWorkoutsSetup"],onClickRemoveWorkout:[7,"onClickRemoveWorkout"]},null)],(function(n,e){var l=e.component;n(e,1,0,e.context.index,e.context.$implicit.name,e.context.$implicit.icon,e.context.$implicit.reps,l.currentTrainingProgramme.restTimeBetweenExerciseInSecond,l.isMeasuredInCount(e.context.$implicit.measurement),l.onClickUpdateWorkoutsSetup,l.onClickRemoveWorkout)}),null)}function R(n){return t.Xb(0,[t.Pb(0,P.e,[t.z]),t.Tb(671088640,1,{coreTrainingOption:0}),(n()(),t.Cb(2,0,null,null,12,"ion-header",[["style","background: var(--solos-color-b);"]],null,null,null,w.Q,w.i)),t.Bb(3,49152,null,0,a.D,[t.j,t.p,t.F],null,null),(n()(),t.Cb(4,0,null,0,10,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,w.ob,w.G)),t.Bb(5,49152,null,0,a.Db,[t.j,t.p,t.F],{mode:[0,"mode"]},null),(n()(),t.Cb(6,0,null,0,4,"ion-buttons",[["slot","start"]],null,null,null,w.L,w.d)),t.Bb(7,49152,null,0,a.n,[t.j,t.p,t.F],null,null),(n()(),t.Cb(8,0,null,0,2,"ion-back-button",[["class","settings-back-button"],["text",""]],null,[[null,"click"]],(function(n,e,l){var i=!0;return"click"===e&&(i=!1!==t.Ob(n,10).onClick(l)&&i),i}),w.J,w.b)),t.Bb(9,49152,null,0,a.i,[t.j,t.p,t.F],{text:[0,"text"]},null),t.Bb(10,16384,null,0,a.j,[[2,a.jb],a.Jb],null,null),(n()(),t.Cb(11,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,w.mb,w.E)),t.Bb(12,49152,null,0,a.Bb,[t.j,t.p,t.F],null,null),(n()(),t.Vb(13,0,["",""])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(15,0,null,null,84,"ion-content",[["class","app-background"]],null,null,null,w.O,w.g)),t.Bb(16,49152,null,0,a.w,[t.j,t.p,t.F],null,null),(n()(),t.Cb(17,0,null,0,82,"div",[["class","main-container"]],null,null,null,null,null)),(n()(),t.Cb(18,0,null,null,3,"div",[["class","intro-text-container"]],null,null,null,null,null)),(n()(),t.Cb(19,0,null,null,2,"div",[["class","intro-text"]],null,null,null,null,null)),(n()(),t.Vb(20,null,["",""])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(22,0,null,null,2,"div",[["class","choose-level-title"]],null,null,null,null,null)),(n()(),t.Vb(23,null,[" "," "])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(25,0,null,null,20,"div",[["class","level-container"]],null,null,null,null,null)),(n()(),t.Cb(26,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(n,e,l){var t=!0;return"click"===e&&(t=!1!==n.component.setLevelToEasy()&&t),t}),null,null)),t.Sb(512,null,P.u,P.v,[t.x,t.y,t.p,t.L]),t.Bb(28,278528,null,0,P.j,[P.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(n()(),t.Vb(29,null,[" "," "])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(31,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(n,e,l){var t=!0;return"click"===e&&(t=!1!==n.component.setLevelToNormal()&&t),t}),null,null)),t.Sb(512,null,P.u,P.v,[t.x,t.y,t.p,t.L]),t.Bb(33,278528,null,0,P.j,[P.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(n()(),t.Vb(34,null,[" "," "])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(36,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(n,e,l){var t=!0;return"click"===e&&(t=!1!==n.component.setLevelToExpert()&&t),t}),null,null)),t.Sb(512,null,P.u,P.v,[t.x,t.y,t.p,t.L]),t.Bb(38,278528,null,0,P.j,[P.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(n()(),t.Vb(39,null,[" "," "])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(41,0,null,null,4,"div",[["class","level-selection"]],null,[[null,"click"]],(function(n,e,l){var t=!0;return"click"===e&&(t=!1!==n.component.setLevelToCustom(!0)&&t),t}),null,null)),t.Sb(512,null,P.u,P.v,[t.x,t.y,t.p,t.L]),t.Bb(43,278528,null,0,P.j,[P.u],{klass:[0,"klass"],ngClass:[1,"ngClass"]},null),(n()(),t.Vb(44,null,[" "," "])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.rb(16777216,null,null,1,null,L)),t.Bb(47,16384,null,0,P.l,[t.X,t.T],{ngIf:[0,"ngIf"]},null),(n()(),t.Cb(48,0,null,null,8,"ion-item",[["button",""],["class","large-font settings-item"],["detail",""],["lines","none"]],null,[[null,"click"]],(function(n,e,l){var t=!0;return"click"===e&&(t=!1!==n.component.onClickNumberOfSets()&&t),t}),w.W,w.o)),t.Bb(49,49152,null,0,a.J,[t.j,t.p,t.F],{button:[0,"button"],detail:[1,"detail"],lines:[2,"lines"]},null),(n()(),t.Cb(50,0,null,0,3,"ion-label",[["color","settings-left-label ion-text-wrap"]],null,null,null,w.X,w.p)),t.Bb(51,49152,null,0,a.P,[t.j,t.p,t.F],{color:[0,"color"]},null),(n()(),t.Vb(52,0,["",""])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(54,0,null,0,2,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,w.X,w.p)),t.Bb(55,49152,null,0,a.P,[t.j,t.p,t.F],{color:[0,"color"]},null),(n()(),t.Vb(56,0,["",""])),(n()(),t.Cb(57,0,null,null,10,"ion-item",[["button",""],["class","large-font settings-item"],["detail",""],["lines","none"]],null,[[null,"click"]],(function(n,e,l){var t=!0;return"click"===e&&(t=!1!==n.component.onClickRestTimeBetweenExercise()&&t),t}),w.W,w.o)),t.Bb(58,49152,null,0,a.J,[t.j,t.p,t.F],{button:[0,"button"],detail:[1,"detail"],lines:[2,"lines"]},null),(n()(),t.Cb(59,0,null,0,3,"ion-label",[["color","settings-left-label ion-text-wrap"]],null,null,null,w.X,w.p)),t.Bb(60,49152,null,0,a.P,[t.j,t.p,t.F],{color:[0,"color"]},null),(n()(),t.Vb(61,0,["",""])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(63,0,null,0,4,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,w.X,w.p)),t.Bb(64,49152,null,0,a.P,[t.j,t.p,t.F],{color:[0,"color"]},null),(n()(),t.Vb(65,0,[""," : "," "])),t.Rb(66,2),t.Rb(67,2),(n()(),t.Cb(68,0,null,null,10,"ion-item",[["button",""],["class","large-font settings-item"],["detail",""],["lines","none"]],null,[[null,"click"]],(function(n,e,l){var t=!0;return"click"===e&&(t=!1!==n.component.onClickRestTimeBetweenSet()&&t),t}),w.W,w.o)),t.Bb(69,49152,null,0,a.J,[t.j,t.p,t.F],{button:[0,"button"],detail:[1,"detail"],lines:[2,"lines"]},null),(n()(),t.Cb(70,0,null,0,3,"ion-label",[["color","settings-left-label ion-text-wrap"]],null,null,null,w.X,w.p)),t.Bb(71,49152,null,0,a.P,[t.j,t.p,t.F],{color:[0,"color"]},null),(n()(),t.Vb(72,0,["",""])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(74,0,null,0,4,"ion-label",[["class","ion-text-right"],["color","settings-right-label"]],null,null,null,w.X,w.p)),t.Bb(75,49152,null,0,a.P,[t.j,t.p,t.F],{color:[0,"color"]},null),(n()(),t.Vb(76,0,[""," : "," "])),t.Rb(77,2),t.Rb(78,2),(n()(),t.Cb(79,0,null,null,4,"div",[["class","exercise-set-title-container"]],null,null,null,null,null)),(n()(),t.Cb(80,0,null,null,3,"ion-label",[["class","exercise-set-title"]],null,null,null,w.X,w.p)),t.Bb(81,49152,null,0,a.P,[t.j,t.p,t.F],null,null),(n()(),t.Vb(82,0,["",""])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(84,0,null,null,15,"div",[["class","selected-exercise-list"]],null,null,null,null,null)),(n()(),t.rb(16777216,null,null,1,null,I)),t.Bb(86,278528,null,0,P.k,[t.X,t.T,t.x],{ngForOf:[0,"ngForOf"]},null),(n()(),t.Cb(87,0,null,null,12,"div",[["class","add-exercise-btn"]],null,[[null,"click"]],(function(n,e,l){var t=!0;return"click"===e&&(t=!1!==n.component.onClickAddWorkout()&&t),t}),null,null)),(n()(),t.Cb(88,0,null,null,4,"div",[["class","list-index-container"]],null,null,null,null,null)),(n()(),t.Cb(89,0,null,null,3,"div",[["class","circle-container"]],null,null,null,null,null)),(n()(),t.Cb(90,0,null,null,2,"div",[["class","circle"]],null,null,null,null,null)),(n()(),t.Cb(91,0,null,null,1,"span",[["class","stage-num"]],null,null,null,null,null)),(n()(),t.Vb(-1,null,["+"])),(n()(),t.Cb(93,0,null,null,5,"ion-item",[["class","add-exercise-item ion-no-padding"],["lines","none"]],null,null,null,w.W,w.o)),t.Bb(94,49152,null,0,a.J,[t.j,t.p,t.F],{lines:[0,"lines"]},null),(n()(),t.Cb(95,0,null,0,3,"div",[["class","add-exercise-btn-title"]],null,null,null,null,null)),(n()(),t.Cb(96,0,null,null,2,"span",[],null,null,null,null,null)),(n()(),t.Vb(97,null,["",""])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(99,0,null,null,0,"div",[["class","tmp-container"]],null,null,null,null,null)),(n()(),t.Cb(100,0,null,null,7,"ion-footer",[],null,null,null,w.P,w.h)),t.Bb(101,49152,null,0,a.B,[t.j,t.p,t.F],null,null),(n()(),t.Cb(102,0,null,0,4,"div",[["class","footer-buttons"]],null,null,null,null,null)),(n()(),t.Cb(103,0,null,null,3,"ion-button",[["class","start-button"],["color","solos-color-a"],["expand","block"],["shape","round"]],null,[[null,"click"]],(function(n,e,l){var t=!0;return"click"===e&&(t=!1!==n.component.onClickStartTraining()&&t),t}),w.K,w.c)),t.Bb(104,49152,null,0,a.m,[t.j,t.p,t.F],{color:[0,"color"],expand:[1,"expand"],shape:[2,"shape"]},null),(n()(),t.Vb(105,0,[" "," "])),t.Pb(131072,T.j,[T.k,t.j]),(n()(),t.Cb(107,0,null,0,0,"div",[["class","footer-padding"]],null,null,null,null,null))],(function(n,e){var l=e.component;n(e,5,0,"ios"),n(e,9,0,""),n(e,28,0,"level-selection",l.isEasy()?"active":"inactive"),n(e,33,0,"level-selection",l.isNormal()?"active":"inactive"),n(e,38,0,"level-selection",l.isExpert()?"active":"inactive"),n(e,43,0,"level-selection",l.isCustom()?"active":"inactive"),n(e,47,0,l.planAllowed),n(e,49,0,"","","none"),n(e,51,0,"settings-left-label ion-text-wrap"),n(e,55,0,"settings-right-label"),n(e,58,0,"","","none"),n(e,60,0,"settings-left-label ion-text-wrap"),n(e,64,0,"settings-right-label"),n(e,69,0,"","","none"),n(e,71,0,"settings-left-label ion-text-wrap"),n(e,75,0,"settings-right-label"),n(e,86,0,l.currentTrainingProgramme.workoutList),n(e,94,0,"none"),n(e,104,0,"solos-color-a","block","round")}),(function(n,e){var l=e.component;n(e,13,0,t.Wb(e,13,0,t.Ob(e,14).transform("core-training.title"))),n(e,20,0,t.Wb(e,20,0,t.Ob(e,21).transform("core-training.intro"))),n(e,23,0,t.Wb(e,23,0,t.Ob(e,24).transform("training.choose-level"))),n(e,29,0,t.Wb(e,29,0,t.Ob(e,30).transform("training.easy"))),n(e,34,0,t.Wb(e,34,0,t.Ob(e,35).transform("training.normal"))),n(e,39,0,t.Wb(e,39,0,t.Ob(e,40).transform("training.expert"))),n(e,44,0,t.Wb(e,44,0,t.Ob(e,45).transform("training.custom"))),n(e,52,0,t.Wb(e,52,0,t.Ob(e,53).transform("core-training.number-of-set"))),n(e,56,0,l.currentTrainingProgramme.numOfSets),n(e,61,0,t.Wb(e,61,0,t.Ob(e,62).transform("core-training.rest-time-between-exercise")));var i=t.Wb(e,65,0,n(e,66,0,t.Ob(e,0),l.restTimeBetweenExerciseMin(),"2.0-0")),o=t.Wb(e,65,1,n(e,67,0,t.Ob(e,0),l.restTimeBetweenExerciseSec(),"2.0-0"));n(e,65,0,i,o),n(e,72,0,t.Wb(e,72,0,t.Ob(e,73).transform("core-training.rest-time-between-set")));var r=t.Wb(e,76,0,n(e,77,0,t.Ob(e,0),l.restTimeBetweenSetMin(),"2.0-0")),s=t.Wb(e,76,1,n(e,78,0,t.Ob(e,0),l.restTimeBetweenSetSec(),"2.0-0"));n(e,76,0,r,s),n(e,82,0,t.Wb(e,82,0,t.Ob(e,83).transform("core-training.training"))),n(e,97,0,t.Wb(e,97,0,t.Ob(e,98).transform("core-training.add-exercise"))),n(e,105,0,t.Wb(e,105,0,t.Ob(e,106).transform("training.start")))}))}function _(n){return t.Xb(0,[(n()(),t.Cb(0,0,null,null,1,"app-core-training-setting",[],null,null,null,R,B)),t.Bb(1,114688,null,0,f,[a.Jb,r.a,T.k,t.F,S.b,u.b,c.a,b.c,p.a],null,null)],(function(n,e){n(e,1,0)}),null)}var E=t.yb("app-core-training-setting",f,_,{},{},[]),W=l("s7LF"),N=l("hrfs"),F=l("j1ZV"),A=l("iInd");l.d(e,"CoreTrainingSettingPageModuleNgFactory",(function(){return z}));var z=t.zb(C,[],(function(n){return t.Lb([t.Mb(512,t.m,t.kb,[[8,[k.a,E]],[3,t.m],t.D]),t.Mb(4608,P.n,P.m,[t.z,[2,P.z]]),t.Mb(4608,W.j,W.j,[]),t.Mb(4608,a.c,a.c,[t.F,t.g]),t.Mb(4608,a.Ib,a.Ib,[a.c,t.m,t.w]),t.Mb(4608,a.Nb,a.Nb,[a.c,t.m,t.w]),t.Mb(4608,T.g,T.f,[]),t.Mb(4608,T.c,T.e,[]),t.Mb(4608,T.i,T.d,[]),t.Mb(4608,T.b,T.a,[]),t.Mb(4608,T.k,T.k,[T.l,T.g,T.c,T.i,T.b,T.m,T.n]),t.Mb(1073742336,P.b,P.b,[]),t.Mb(1073742336,W.i,W.i,[]),t.Mb(1073742336,W.b,W.b,[]),t.Mb(1073742336,a.Fb,a.Fb,[]),t.Mb(1073742336,N.b,N.b,[]),t.Mb(1073742336,T.h,T.h,[]),t.Mb(1073742336,F.a,F.a,[]),t.Mb(1073742336,A.o,A.o,[[2,A.t],[2,A.m]]),t.Mb(1073742336,C,C,[]),t.Mb(256,T.n,void 0,[]),t.Mb(256,T.m,void 0,[]),t.Mb(1024,A.k,(function(){return[[{path:"",component:f}]]}),[])])}))}}]);