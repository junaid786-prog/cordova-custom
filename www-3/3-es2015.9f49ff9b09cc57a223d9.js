(window.webpackJsonp=window.webpackJsonp||[]).push([[3],{doUY:function(t,i,e){"use strict";e.d(i,"b",(function(){return T})),e.d(i,"a",(function(){return A})),e.d(i,"c",(function(){return f}));var s=e("mrSG"),a=e("ZZ/e"),n=e("R3bG"),r=e("DaA9"),o=e("8qa8"),h=e("yDgC"),c=e("Z18M"),l=e("AGLQ"),u=e("PEy0"),g=e("lvyt"),v=e("ppcj"),d=e("Zesz"),p=e("8Y7J"),y=e("TSSN");const m="tagPostureReminder",R="PostureMonitor-",S=".pm",T="evtMonitoringStateChanged",N="stateReasonAPIAction",A="evtMonitoringDataChanged",C=1e4,b=6e4;var I=function(t){return t[t.Idle=0]="Idle",t[t.Running=1]="Running",t[t.Paused=2]="Paused",t[t.Busy=3]="Busy",t}({}),M=function(t){return t[t.Start=0]="Start",t[t.Stop=1]="Stop",t[t.Pause=2]="Pause",t[t.Resume=3]="Resume",t}({});class P{constructor(t,i){this.type=t,this.para=i}}let f=(()=>{class t{constructor(t,i,e,a,c,l,u,d,p,y){this.events=t,this.translate=i,this.solosConnector=e,this.postureSettings=a,this.audioPlayback=c,this.backend=l,this.background=u,this.fileUtility=d,this.platform=p,this.us=y,this.state=I.Idle,this.isDisconnectPaused=!1,this.isSpeaking=!1,this.accumulatedTimeStamp=0,this.acceDataXArray=[],this.acceDataYArray=[],this.acceDataZArray=[],this.rollReference=null,this.pitchReference=null,this.curSeverityLevel=[!1,!1,!1,!1],this.severityLevelNormalCnt=0,this.severityLevelSlightCnt=0,this.severityLevelSeriousCnt=0,this.severityLevelSeverseCnt=0,this.numOfDataSample=0,this.uploadRecordsTimer=null,this.timerset=null,this.start=()=>{this.commandQueue.tryRun(new P(M.Start))},this.startInternal=()=>s.b(this,void 0,void 0,(function*(){if(this.state!=I.Idle)return!1;if(null==this.solosConnector.getConnectedDevice())return!1;this.setState(I.Busy);try{return this.rollReference=this.postureSettings.getRollReference(),this.pitchReference=this.postureSettings.getPitchReference(),this.isDisconnectPaused=!1,this.resetAlert(),this.lastTimeStamp=0,this.accumulatedTimeStamp=0,this.curSeverityLevel=[!1,!1,!1,!1],this.severityLevelNormalCnt=0,this.severityLevelSlightCnt=0,this.severityLevelSeriousCnt=0,this.severityLevelSeverseCnt=0,this.startTime=new Date,this.localRecordFilename=this.getNowLocalRecordFilename(),this.stopUploadRecords(),this.initMagAcceData(),this.timerset=new h.a,this.timerset.addTimer(this.calculatePostureData,1e3),this.timerset.addTimer(this.saveLocalRecord,C),yield this.solosConnector.startGAM(),this.events.subscribe(n.c,this.handleGAMBack),this.setMonitoring(!0),this.speakStartMonitor(),!0}catch(t){return console.log(JSON.stringify(t)),this.events.unsubscribe(n.c,this.handleGAMBack),yield this.solosConnector.stopGAM(),this.setState(I.Idle),!1}})),this.pause=()=>{this.commandQueue.tryRun(new P(M.Pause))},this.pauseInternal=()=>s.b(this,void 0,void 0,(function*(){if(this.state==I.Running){this.setState(I.Busy);try{this.events.unsubscribe(n.c,this.handleGAMBack),yield this.solosConnector.stopGAM()}catch(t){console.log(JSON.stringify(t))}finally{this.resetAlert(),this.speakPauseMonitor()}}})),this.resume=()=>{this.commandQueue.tryRun(new P(M.Resume))},this.resumeInternal=()=>s.b(this,void 0,void 0,(function*(){if(this.state==I.Paused&&null!=this.solosConnector.getConnectedDevice()){this.setState(I.Busy);try{this.lastTimeStamp=0,this.resetAlert(),this.initMagAcceData(),this.curSeverityLevel=[!1,!1,!1,!1],this.timerset=new h.a,this.timerset.addTimer(this.calculatePostureData,1e3),yield this.solosConnector.startGAM(),this.events.subscribe(n.c,this.handleGAMBack),this.speakResumeMonitor()}catch(t){console.log(JSON.stringify(t)),this.events.unsubscribe(n.c,this.handleGAMBack),yield this.solosConnector.stopGAM(),this.setState(I.Paused)}}})),this.stop=(t=N)=>{this.commandQueue.tryRun(new P(M.Stop,t))},this.isLandscapeMode=()=>this.us.isLandscapeMode(),this.stopInternal=t=>s.b(this,void 0,void 0,(function*(){if(this.state==I.Running||this.state==I.Paused){this.setState(I.Busy);try{this.timerset=null,this.events.unsubscribe(n.c,this.handleGAMBack),yield this.solosConnector.stopGAM()}catch(i){console.log(i)}finally{this.resetAlert(),this.saveLocalRecord().then(()=>{this.uploadRecords()}),this.speakStopMonitor(),this.setMonitoring(!1,t),this.curSeverityLevel=[!1,!1,!1,!1]}}})),this.setState=t=>{t!=this.state&&(this.state=t,this.events.publish(T,{state:t}),this.state!=I.Busy&&this.commandQueue.runPendingCommands())},this.isRunning=()=>this.state==I.Running||this.state==I.Paused||this.state==I.Busy,this.getPause=()=>this.state==I.Paused,this.isBusy=()=>this.state==I.Busy,this.getData=()=>({elapsedTime:this.accumulatedTimeStamp/1e3,curSeverityLevel:this.curSeverityLevel,severityLevelNormalCnt:this.severityLevelNormalCnt,severityLevelSlightCnt:this.severityLevelSlightCnt,severityLevelSeriousCnt:this.severityLevelSeriousCnt,severityLevelSeverseCnt:this.severityLevelSeverseCnt}),this.getNumOfDataSamples=()=>this.numOfDataSample,this.setMonitoring=(t,i=N)=>{this.events.publish(T,{on:t,reason:i})},this.evtSolosDisconnected=t=>{this.isLandscapeMode()?this.stop():this.getPause()||(this.isDisconnectPaused=!0,this.pause())},this.evtSolosConnected=t=>{this.isDisconnectPaused&&(this.resume(),this.isDisconnectPaused=!1)},this.handleGAMBack=t=>s.b(this,void 0,void 0,(function*(){const i=(new Date).getTime();if(0==this.lastTimeStamp)return void(this.lastTimeStamp=i);const e=i-this.lastTimeStamp;this.lastTimeStamp=i,this.accumulatedTimeStamp+=e,this.numOfDataSample++;const s=t.acceDataX,a=t.acceDataY;this.acceDataXArray.push(t.acceDataZ),this.acceDataYArray.push(-a),this.acceDataZArray.push(s),this.timerset.update(e)})),this.calculatePostureData=()=>s.b(this,void 0,void 0,(function*(){if(0==this.acceDataXArray.length)return;let t=0,i=0,e=0;for(let r=0;r<this.acceDataXArray.length;r++)t+=this.acceDataXArray[r],i+=this.acceDataYArray[r],e+=this.acceDataZArray[r];let s=t/this.acceDataXArray.length,a=i/this.acceDataYArray.length,n=e/this.acceDataZArray.length;this.initMagAcceData();let h=Math.atan(s/n),c=Math.atan(a/n);c*=180/Math.PI,h*=180/Math.PI;let l=this.postureSettings.getAlertLevelNum();const u=this.pitchReference-c;u<-30?(this.curSeverityLevel=[!1,!1,!1,!1],this.resetAlert()):u>=-30&&u<5?(this.severityLevelNormalCnt++,this.curSeverityLevel=[!0,!1,!1,!1],this.resetAlert()):u>=5&&u<15?(this.severityLevelSlightCnt++,this.curSeverityLevel=[!1,!0,!1,!1],l===r.e.Slight||this.resetAlert()):u>=15&&u<30?(this.severityLevelSeriousCnt++,this.curSeverityLevel=[!1,!1,!0,!1],l===r.e.Slight||l===r.e.Serious||this.resetAlert()):(this.severityLevelSeverseCnt++,this.curSeverityLevel=[!1,!1,!1,!0],l===r.e.Slight||l===r.e.Serious||l===r.e.Severe||this.resetAlert());const g=this.postureSettings.getRemindInterval();0!=g&&(Date.now()-this.alertTS)/1e3>=g&&(this.resetAlert(),"beep"===this.postureSettings.getReminderMethod()?this.audioPlayback.playMusic(!0,o.d,m):this.audioPlayback.speech(!0,this.translate.instant("monitoring.sit-properly"),m)),this.publishDataChangedEvent()})),this.resetAlert=()=>{this.alertTS=Date.now(),this.audioPlayback.flushPendingSpeech4tag(m),this.audioPlayback.flushPendingPlayMusic4tag(m)},this.initMagAcceData=()=>{this.acceDataXArray=[],this.acceDataYArray=[],this.acceDataZArray=[]},this.eventsSettingDidChanged=()=>{this.resetAlert()},this.eventsRemindIntervalChanged=()=>{this.resetAlert()},this.publishDataChangedEvent=()=>{this.events.publish(A)},this.speakStartMonitor=()=>{this.background.enable(),this.audioPlayback.speech(!0,this.translate.instant("monitoring.is-start"),"",()=>{this.setState(I.Running)})},this.speakStopMonitor=()=>{this.audioPlayback.speech(!0,this.translate.instant("monitoring.is-stop"),"",()=>{this.background.disable(),this.setState(I.Idle)})},this.speakResumeMonitor=()=>{this.audioPlayback.speech(!0,this.translate.instant("monitoring.is-resume"),"",()=>{this.setState(I.Running)})},this.speakPauseMonitor=()=>{this.audioPlayback.speech(!0,this.translate.instant("monitoring.is-pause"),"",()=>{this.setState(I.Paused)})},this.getNowLocalRecordFilename=()=>{const t=Object(g.c)(new Date);return R+t+S},this.saveLocalRecord=()=>s.b(this,void 0,void 0,(function*(){try{const t={startTime:this.startTime.toISOString(),elapsedTime:this.accumulatedTimeStamp/1e3,statNormal:this.severityLevelNormalCnt,statSlight:this.severityLevelSlightCnt,statSerious:this.severityLevelSeriousCnt,statSeverse:this.severityLevelSeverseCnt};yield this.fileUtility.saveTextFile(this.localRecordFilename,JSON.stringify(t))}catch(t){console.log(t.message)}})),this.uploadRecords=()=>s.b(this,void 0,void 0,(function*(){try{this.background.enable();const t=yield this.fileUtility.listFiles(S);for(const i of t)yield this.uploadRecord(i);this.background.disable(),(yield this.fileUtility.listFiles(S)).length&&null==this.uploadRecordsTimer&&(this.uploadRecordsTimer=setTimeout(()=>{this.uploadRecordsTimer=null,this.uploadRecords()},b))}catch(t){console.log(t)}})),this.stopUploadRecords=()=>{null!=this.uploadRecordsTimer&&(clearTimeout(this.uploadRecordsTimer),this.uploadRecordsTimer=null)},this.uploadRecord=t=>s.b(this,void 0,void 0,(function*(){try{yield this.fileUtility.checkFile(t);let i=yield this.fileUtility.readJSONFile(t);null!=i&&(yield this.backend.savePMonitor(i.elapsedTime,i.statNormal,i.statSlight,i.statSerious,i.statSeverse)),yield this.fileUtility.deleteFile(t)}catch(i){console.log(i.message)}})),this.runCommand=t=>s.b(this,void 0,void 0,(function*(){let i=!0;if(this.isBusy())i=!1;else if(t instanceof P){const i=t;switch(i.type){case M.Start:this.startInternal();break;case M.Pause:this.pauseInternal();break;case M.Resume:this.resumeInternal();break;case M.Stop:this.stopInternal(i.para)}}return i})),this.platform.ready().then(()=>{this.commandQueue=new v.a(this.runCommand),this.events.subscribe(n.k,this.evtSolosDisconnected),this.events.subscribe(n.j,this.evtSolosConnected),this.events.subscribe(r.a,this.eventsSettingDidChanged),this.events.subscribe(r.b,this.eventsRemindIntervalChanged),this.uploadRecords()})}}return t.ngInjectableDef=p.ac({factory:function(){return new t(p.bc(a.f),p.bc(y.k),p.bc(n.l),p.bc(r.d),p.bc(o.f),p.bc(c.c),p.bc(l.a),p.bc(u.a),p.bc(a.Mb),p.bc(d.a))},token:t,providedIn:"root"}),t})()},h5tO:function(t,i,e){"use strict";e.d(i,"a",(function(){return d})),e.d(i,"c",(function(){return p})),e.d(i,"b",(function(){return y}));var s=e("mrSG"),a=e("ZZ/e"),n=e("VjBM"),r=e("BHc3"),o=e("lGdP"),h=e("Wlh3"),c=e("doUY"),l=e("R3bG"),u=e("DaA9"),g=e("8Y7J"),v=e("TSSN"),d=function(t){return t.BASIC_TRAINING="basicTraining",t.POSTURE_TRAINING="postureTraining",t.CADENCE_TRAINING="cadenceTraining",t.INTERVAL_TRAINING="intervalTraining",t.STEP_COUNT_TRAINING="stepCountTraining",t.FAT_BURN="fatBurn",t.CORE_TRAINING="coreTraining",t.POSTURE_MONITORING="postureMonitor",t.POSTURE_EXERCISE="postureExercise",t.STRETCH="stretch",t.CALIBRATION="calibration",t.POSTURE_WARM_UP="postureWarmup",t.COMPASS_CALIBRATION="compassCalibration",t.FIRMWARE_UPDATE="firmwareUpdate",t}({});const p=[d.POSTURE_TRAINING,d.CADENCE_TRAINING,d.INTERVAL_TRAINING,d.FAT_BURN,d.STEP_COUNT_TRAINING];let y=(()=>{class t{constructor(t,i,e,a,r,c,l,u){this.coachActivity=t,this.coachTraining=i,this.coreTraining=e,this.translate=a,this.postureMonitoring=r,this.solosConnector=c,this.navCtrl=l,this.postureSettings=u,this.isBasicTrainingRunning=()=>this.coachActivity.isRunning()&&!this.coachTraining.isRunning(),this.isCoachTrainingRunning=t=>{let i=p.map(t=>t);const e={};if(e[i[0]]=this.coachTraining.getTrainingType()==n.b.Posture,e[i[1]]=this.coachTraining.getTrainingType()==n.b.Cadence,e[i[2]]=this.coachTraining.getTrainingType()==n.b.Interval,e[i[3]]=this.coachTraining.getTrainingType()==n.b.FastWalk,e[i[4]]=this.coachTraining.getTrainingType()==n.b.StepCount,void 0===t){for(const t in e)if(e[t])return!0;return!1}return!!e.hasOwnProperty(t)&&e[t]},this.isCoreTrainingRunning=()=>this.coreTraining.getCurrentState()!==h.d.Stop||this.coreTraining.isBusy(),this.isPostureMonitorRunning=()=>this.postureMonitoring.isRunning(),this.getNavPathByService=t=>p.includes(t)?this.getCoachTrainingNavPath(t):t===d.CORE_TRAINING?this.getCoreTrainingNavPath():t===d.POSTURE_MONITORING?"/posture-monitor":t===d.POSTURE_EXERCISE?"/exercise-util":t===d.STRETCH?"/stretch-util":"/ai-coach",this.getCoreTrainingNavPath=()=>this.isCoreTrainingRunning()?"/core-training":"/core-training-setting",this.getCoachTrainingNavPath=t=>{const i=(t=>t===p[0]?{start:"/posture-training",running:"/training-running/posture",stopping:"/training-result/posture"}:t===p[1]?{start:"/cadence-training",running:"/training-running/cadence",stopping:"/training-result/cadence"}:t===p[2]?{start:"/interval-training",running:"/training-running/interval",stopping:"/training-result/interval"}:t===p[3]?{start:"/fat-burn",running:"/training-running/fatburn",stopping:"/training-result/fatburn"}:{start:"/step-count",running:"/training-running/stepCount",stopping:"/training-result/stepCount"})(t);return this.isCoachTrainingRunning(t)?this.coachTraining.getState()==o.i.Stopping?i.stopping:i.running:i.start},this.getServiceStatus=t=>t==d.POSTURE_MONITORING?{service:t,running:this.isPostureMonitorRunning()}:t===d.BASIC_TRAINING?{service:t,running:this.isBasicTrainingRunning()}:t===d.CORE_TRAINING?{service:t,running:this.isCoreTrainingRunning()}:{service:t,running:this.isCoachTrainingRunning(t)},this.getActivityName=t=>this.activityNameMap.has(t)?this.translate.instant(this.activityNameMap.get(t)):"Unknown",this.isProximityOn=t=>!this.activityProximityMap.has(t)||!this.activityProximityMap.get(t)||this.solosConnector.isProximityOn(),this.isSolosCalibrated=t=>!this.activityCalibrationMap.has(t)||!this.activityCalibrationMap.get(t)||this.postureSettings.isRollPitchReferencesCalibrated(),this.getAnotherRunningService=(t,i)=>{const e=(t=t.filter(t=>t!==i)).map(t=>this.getServiceStatus(t)).filter(t=>t.running);return e.length?e[0].service:null},this.validate=(t,i,e)=>{let a={status:!1};a.header=this.translate.instant("global.warning"),a.message="",a.buttons=[{text:this.translate.instant("global.okay")}];const n=this.getAnotherRunningService(t,i);return null!=n?a.message=this.translate.instant("global.cant-start-bc-other-service-running",{v1:this.getActivityName(i),v2:this.getActivityName(n)}):null==this.solosConnector.getConnectedDevice()?(a.message=this.translate.instant("global.cant-start-airgo-not-connected",{value:this.getActivityName(i)}),a.buttons=[{text:this.translate.instant("global.no"),role:"cancel"},{text:this.translate.instant("global.yes"),handler:()=>s.b(this,void 0,void 0,(function*(){try{yield this.navCtrl.navigateRoot("/setup/0")}catch(t){console.log(t)}}))}]):this.isProximityOn(i)?this.isSolosCalibrated(i)?a.status=!0:(a.message=this.translate.instant("global.cant-start-not-calibrate",{value:this.getActivityName(i)}),a.buttons=[{text:this.translate.instant("calibration.no"),role:"cancel"},{text:this.translate.instant("calibration.yes"),handler:()=>{this.navCtrl.navigateForward("/calibration/0/"+encodeURIComponent(e))}}]):a.message=this.translate.instant("global.cant-start-airgo-not-wear",{value:this.getActivityName(i)}),a},this.activityNameMap=new Map,this.activityNameMap.set(d.BASIC_TRAINING,"ai-coach-util.basic-training"),this.activityNameMap.set(d.CADENCE_TRAINING,"cadence-training.title"),this.activityNameMap.set(d.CORE_TRAINING,"core-training.title"),this.activityNameMap.set(d.FAT_BURN,"fat-burn.title"),this.activityNameMap.set(d.INTERVAL_TRAINING,"interval-training.title"),this.activityNameMap.set(d.POSTURE_EXERCISE,"exercise.exercise"),this.activityNameMap.set(d.POSTURE_MONITORING,"monitoring.monitoring"),this.activityNameMap.set(d.POSTURE_TRAINING,"posture-training.title"),this.activityNameMap.set(d.STEP_COUNT_TRAINING,"step-count.title"),this.activityNameMap.set(d.STRETCH,"stretch.title"),this.activityNameMap.set(d.CALIBRATION,"calibration.title"),this.activityNameMap.set(d.POSTURE_WARM_UP,"warm-up.title"),this.activityNameMap.set(d.COMPASS_CALIBRATION,"compass-cal.header"),this.activityNameMap.set(d.FIRMWARE_UPDATE,"firmware.firmware-update"),this.activityCalibrationMap=new Map,this.activityCalibrationMap.set(d.BASIC_TRAINING,!0),this.activityCalibrationMap.set(d.CADENCE_TRAINING,!1),this.activityCalibrationMap.set(d.CORE_TRAINING,!0),this.activityCalibrationMap.set(d.FAT_BURN,!1),this.activityCalibrationMap.set(d.INTERVAL_TRAINING,!1),this.activityCalibrationMap.set(d.POSTURE_EXERCISE,!0),this.activityCalibrationMap.set(d.POSTURE_MONITORING,!0),this.activityCalibrationMap.set(d.POSTURE_TRAINING,!0),this.activityCalibrationMap.set(d.STEP_COUNT_TRAINING,!1),this.activityCalibrationMap.set(d.STRETCH,!0),this.activityCalibrationMap.set(d.CALIBRATION,!1),this.activityCalibrationMap.set(d.POSTURE_WARM_UP,!0),this.activityCalibrationMap.set(d.COMPASS_CALIBRATION,!1),this.activityCalibrationMap.set(d.FIRMWARE_UPDATE,!1),this.activityProximityMap=new Map,this.activityProximityMap.set(d.BASIC_TRAINING,!0),this.activityProximityMap.set(d.CADENCE_TRAINING,!0),this.activityProximityMap.set(d.CORE_TRAINING,!0),this.activityProximityMap.set(d.FAT_BURN,!0),this.activityProximityMap.set(d.INTERVAL_TRAINING,!0),this.activityProximityMap.set(d.POSTURE_EXERCISE,!0),this.activityProximityMap.set(d.POSTURE_MONITORING,!0),this.activityProximityMap.set(d.POSTURE_TRAINING,!0),this.activityProximityMap.set(d.STEP_COUNT_TRAINING,!0),this.activityProximityMap.set(d.STRETCH,!0),this.activityProximityMap.set(d.CALIBRATION,!0),this.activityProximityMap.set(d.POSTURE_WARM_UP,!0),this.activityProximityMap.set(d.COMPASS_CALIBRATION,!1),this.activityProximityMap.set(d.FIRMWARE_UPDATE,!1)}}return t.ngInjectableDef=g.ac({factory:function(){return new t(g.bc(r.b),g.bc(n.a),g.bc(h.c),g.bc(v.k),g.bc(c.c),g.bc(l.l),g.bc(a.Jb),g.bc(u.d))},token:t,providedIn:"root"}),t})()}}]);