(window.webpackJsonp=window.webpackJsonp||[]).push([[74],{lISr:function(n,l,t){"use strict";t.r(l);var o=t("8Y7J"),e=t("mrSG"),i=t("ZZ/e"),s=t("R3bG"),u=t("FjaT");const c="evtClassicBluetoothOnConnectResult",a="evtClassicBluetoothOnDeviceFound";let r=(()=>{class n{constructor(n,l){this.events=n,this.platform=l,this.foundDevices=new Map,this.startScan=()=>{this.platform.is("android")&&(this.foundDevices.clear(),cordova.plugins.ClassicBluetoothPlugin.startScan())},this.stopScan=()=>{this.platform.is("android")&&cordova.plugins.ClassicBluetoothPlugin.stopScan()},this.connect=(n,l)=>{this.platform.is("android")&&cordova.plugins.ClassicBluetoothPlugin.connect(n,l)},this.onConnectResult=n=>{this.events.publish(c,{result:n.result,name:n.name,uuid:n.uuid,macAddr:n.macAddr})},this.onDeviceFound=n=>{this.foundDevices.set(n.uuid,{name:n.name,macAddr:n.macAddr}),this.events.publish(a,{name:n.name,uuid:n.uuid,macAddr:n.macAddr})},this.getFoundDeviceMacAddr=n=>{const l=this.foundDevices.get(n);return null!=l?l.macAddr:null},window.addEventListener("classicBluetoothOnConnectResult",this.onConnectResult,!1),window.addEventListener("classicBluetoothOnDeviceFound",this.onDeviceFound,!1)}}return n.ngInjectableDef=o.ac({factory:function(){return new n(o.bc(i.f),o.bc(i.Mb))},token:n,providedIn:"root"}),n})();var b=t("OUMn"),d=t("Zesz");const h=6e4;var p=function(n){return n[n.WELCOME=0]="WELCOME",n[n.POWERON=1]="POWERON",n[n.PAIR_DEVICE=2]="PAIR_DEVICE",n[n.CONGRATULATION=3]="CONGRATULATION",n}({});class g{}class f{constructor(n,l,t,o,i,u,c,r,b,d,p,f){this.platform=n,this.zone=l,this.events=t,this.route=o,this.navCtrl=i,this.solosConnector=u,this.translate=c,this.openNativeSettings=r,this.storage=b,this.classicBT=d,this.popupCtrl=p,this.us=f,this.isListOpen=!1,this.imageUrl="assets/imgs/glasses.png",this.contentTitle="setup.welcome",this.contentText="setup.welcome-content",this.isBTOpen=!1,this.isNextBtn4BT=!1,this.isLastPage=!1,this.numOfPage=0,this.devInfos=[],this.subscribeAction=null,this.scanningBleDeviceTimeout=null,this.loadingPrompt=null,this.showLoading=!1,this.connectDevice=n=>e.b(this,void 0,void 0,(function*(){try{this.presentLoadingPrompt(),this.classicBT.connect(n.name,n.uuid)}catch(l){console.log("connectDevice failed : "+JSON.stringify(l))}})),this.skipSetup=()=>e.b(this,void 0,void 0,(function*(){yield this.popupCtrl.presentAlertController({header:this.translate.instant("setup.really-skip"),message:this.translate.instant("setup.really-skip-content"),buttons:[{text:this.translate.instant("global.cancel"),role:"cancel"},{text:this.translate.instant("global.okay"),handler:()=>e.b(this,void 0,void 0,(function*(){yield this.doneClicked()}))}]},!0)})),this.isLandscapeMode=()=>this.us.isLandscapeMode(),this.doneClicked=()=>e.b(this,void 0,void 0,(function*(){try{yield this.storage.set("Setup-Shown-Before",!0);const n=yield this.storage.get("Tutorial-Shown-Before");if(this.isLandscapeMode())return void(yield this.navCtrl.navigateRoot(""));console.log(this.isLandscapeMode()),1!=n?yield this.navCtrl.navigateRoot("/tutorial/root"):yield this.navCtrl.navigateRoot("/tabs")}catch(n){console.log(n)}})),this.solosBleConnected=n=>{this.zone.run(()=>e.b(this,void 0,void 0,(function*(){yield this.dismissLoadingPrompt(),this.nextClicked()})))},this.solosBleDisconnected=n=>{this.zone.run(()=>e.b(this,void 0,void 0,(function*(){this.ShowConnectDeviceError()})))},this.solosBtClassicDeviceFound=n=>{if(0==this.devInfos.filter(l=>l.uuid==n.uuid).length){let l=new g;l.name=n.name,l.uuid=n.uuid,l.macAddr=n.macAddr,this.zone.run(()=>{this.devInfos.push(l)})}},this.solosBtClassicConnected=n=>e.b(this,void 0,void 0,(function*(){try{this.presentLoadingPrompt()}catch(n){console.log(n)}})),this.startWaitingSolosConnection=()=>e.b(this,void 0,void 0,(function*(){try{this.events.subscribe(a,this.solosBtClassicDeviceFound),this.events.subscribe(s.h,this.solosBtClassicConnected),this.events.subscribe(s.j,this.solosBleConnected),this.events.subscribe(s.k,this.solosBleDisconnected),this.solosConnector.deviceConnected()?this.nextClicked():this.solosConnector.bluetoothClassicConnected()&&this.presentLoadingPrompt(),this.classicBT.startScan()}catch(n){console.log(JSON.stringify(n))}})),this.stopWaitingSolosConnection=()=>e.b(this,void 0,void 0,(function*(){try{this.events.unsubscribe(a,this.solosBtClassicDeviceFound),this.events.unsubscribe(s.h,this.solosBtClassicConnected),this.events.unsubscribe(s.j,this.solosBleConnected),this.events.unsubscribe(s.k,this.solosBleDisconnected),this.classicBT.stopScan(),yield this.dismissLoadingPrompt()}catch(n){console.log(JSON.stringify(n))}})),this.presentLoadingPrompt=()=>{this.zone.run(()=>e.b(this,void 0,void 0,(function*(){try{this.showLoading||(this.showLoading=!0,this.loadingPrompt=yield this.popupCtrl.presentLoadingController({spinner:"bubbles",translucent:!0}),this.scanningBleDeviceTimeout=setTimeout(()=>{this.ShowConnectDeviceError()},h))}catch(n){console.log("presentLoadingPrompt failed : "+JSON.stringify(n))}})))},this.dismissLoadingPrompt=()=>e.b(this,void 0,void 0,(function*(){try{this.showLoading&&(this.showLoading=!1,null!=this.scanningBleDeviceTimeout&&(clearTimeout(this.scanningBleDeviceTimeout),this.scanningBleDeviceTimeout=null),null!=this.loadingPrompt&&(yield this.loadingPrompt.dismiss(),this.loadingPrompt=null))}catch(n){console.log("dismissLoadingPrompt failed : "+JSON.stringify(n))}})),this.ShowConnectDeviceError=()=>{this.zone.run(()=>e.b(this,void 0,void 0,(function*(){try{yield this.dismissLoadingPrompt();let n="";this.platform.is("ios")?n=this.translate.instant("setup.ios-connect-device-error"):this.platform.is("android")&&(n=this.translate.instant("setup.android-connect-device-error")),yield this.popupCtrl.presentAlertController({header:this.translate.instant("global.error"),message:n,buttons:[{text:this.translate.instant("global.okay"),handler:()=>{this.classicBT.stopScan(),this.classicBT.startScan()}}]},!0)}catch(n){console.log(n)}})))}}ngOnInit(){switch(this.storage.get("Setup-Shown-Before").then(n=>{this.setupShown=n}),this.numOfPage=Number(this.route.snapshot.paramMap.get("page")),this.numOfPage){case p.WELCOME:this.contentTitle="setup.welcome",this.contentText="setup.welcome-content";break;case p.POWERON:this.contentTitle="setup.power-on",this.contentText="setup.power-on-content",this.imageUrl="assets/imgs/glasses-power-on.png";break;case p.PAIR_DEVICE:this.platform.is("ios")?(this.isNextBtn4BT=!0,this.imageUrl="assets/imgs/ios-bluetooth.png",this.contentText="setup.ios-pair-content"):(this.isListOpen=!0,this.contentText="setup.android-pair-content"),this.contentTitle="setup.pair";break;case p.CONGRATULATION:this.isLastPage=!0,this.contentTitle="setup.congratulation",this.contentText="setup.congratulation-content"}}ionViewDidEnter(){this.numOfPage==p.WELCOME||this.numOfPage==p.CONGRATULATION?this.subscribeAction=this.platform.backButton.subscribeWithPriority(8964,()=>{this.platform.is("android")&&(1!=this.setupShown?navigator.app.exitApp():this.skipSetup())}):this.numOfPage==p.PAIR_DEVICE&&this.startWaitingSolosConnection()}ionViewWillLeave(){this.numOfPage==p.WELCOME||this.numOfPage==p.CONGRATULATION?this.subscribeAction.unsubscribe():this.numOfPage==p.PAIR_DEVICE&&this.stopWaitingSolosConnection()}nextClicked(){this.navCtrl.navigateForward(`/setup/${this.numOfPage+1}`)}openBTSetting(){this.openNativeSettings.open("bluetooth")}}class m{}var v=t("pMnS"),C=t("oBZk"),k=t("TSSN"),x=t("SVse"),B=t("iInd"),O=t("xgBC"),P=o.Ab({encapsulation:0,styles:[[".body-div[_ngcontent-%COMP%], .image-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:start;justify-content:flex-start;-webkit-box-align:center;align-items:center;height:100%;width:100%}.image-content[_ngcontent-%COMP%]{height:100%;max-height:100%;margin:0 auto;-o-object-fit:contain;object-fit:contain;border:0 transparent}.list-container[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:start;justify-content:flex-start;-webkit-box-align:center;align-items:center;height:100%;width:100%}.list-content[_ngcontent-%COMP%]{height:100%;overflow-y:scroll;width:100%}.content-title[_ngcontent-%COMP%]{color:var(--solos-color-a);font-size:25px;margin:20px 40px 10px;-webkit-box-pack:center;justify-content:center}.content-text-container[_ngcontent-%COMP%]{-webkit-box-flex:1;flex-grow:1;display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;-webkit-box-pack:center;justify-content:center;margin:10px 40px}.content-text[_ngcontent-%COMP%]{color:var(--solos-color-a);font-size:14px;margin:0;white-space:pre-line;text-align:center;line-height:1.5}.action-button[_ngcontent-%COMP%]{margin-bottom:calc(env(safe-area-inset-bottom) + 10px);width:80%}.col[_ngcontent-%COMP%]{margin:3px 0 3px 3;-webkit-box-flex:1;flex:1;display:-webkit-box;display:flex;-webkit-box-pack:center;justify-content:center}"],[".main-container-landscape[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column}.list-container-landscape[_ngcontent-%COMP%]{height:200px;overflow-y:scroll;width:100%}@media (min-width:1025px) and (max-width:1280px){.main-container-landscape[_ngcontent-%COMP%]{display:-webkit-box;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;flex-direction:column;height:100%}.content-text[_ngcontent-%COMP%], .content-title[_ngcontent-%COMP%]{font-size:2em}.tablet-btn-style[_ngcontent-%COMP%]{height:2em;font-size:2em}.list-container-landscape[_ngcontent-%COMP%]{height:400px}}"]],data:{}});function w(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,3,"ion-back-button",[["color","solos-color-a"]],null,[[null,"click"]],(function(n,l,t){var e=!0;return"click"===l&&(e=!1!==o.Ob(n,3).onClick(t)&&e),e}),C.J,C.b)),o.Bb(1,49152,null,0,i.i,[o.j,o.p,o.F],{color:[0,"color"],text:[1,"text"]},null),o.Pb(131072,k.j,[k.k,o.j]),o.Bb(3,16384,null,0,i.j,[[2,i.jb],i.Jb],null,null)],(function(n,l){n(l,1,0,"solos-color-a",o.Wb(l,1,1,o.Ob(l,2).transform("setup.back")))}),null)}function T(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,3,"ion-button",[["color","solos-color-a"]],null,[[null,"click"]],(function(n,l,t){var o=!0;return"click"===l&&(o=!1!==n.component.skipSetup()&&o),o}),C.K,C.c)),o.Bb(1,49152,null,0,i.m,[o.j,o.p,o.F],{color:[0,"color"]},null),(n()(),o.Vb(2,0,[" "," "])),o.Pb(131072,k.j,[k.k,o.j])],(function(n,l){n(l,1,0,"solos-color-a")}),(function(n,l){n(l,2,0,o.Wb(l,2,0,o.Ob(l,3).transform("setup.skip")))}))}function j(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(n()(),o.Cb(1,0,null,null,0,"img",[["class","image-content"]],[[8,"src",4]],null,null,null,null))],null,(function(n,l){n(l,1,0,l.component.imageUrl)}))}function y(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,5,"div",[],null,null,null,null,null)),(n()(),o.Cb(1,0,null,null,4,"ion-item",[["class","settings-item"]],null,[[null,"click"]],(function(n,l,t){var o=!0;return"click"===l&&(o=!1!==n.component.connectDevice(n.context.$implicit)&&o),o}),C.W,C.o)),o.Bb(2,49152,null,0,i.J,[o.j,o.p,o.F],null,null),(n()(),o.Cb(3,0,null,0,2,"ion-label",[["color","settings-left-label"]],null,null,null,C.X,C.p)),o.Bb(4,49152,null,0,i.P,[o.j,o.p,o.F],{color:[0,"color"]},null),(n()(),o.Vb(5,0,["",""]))],(function(n,l){n(l,4,0,"settings-left-label")}),(function(n,l){n(l,5,0,l.context.$implicit.name)}))}function L(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,8,"div",[["class","list-container"]],null,null,null,null,null)),(n()(),o.Cb(1,0,null,null,7,"ion-list",[["class","settings-body list-content"]],null,null,null,C.Z,C.q)),o.Bb(2,49152,null,0,i.Q,[o.j,o.p,o.F],null,null),(n()(),o.rb(16777216,null,0,1,null,y)),o.Bb(4,278528,null,0,x.k,[o.X,o.T,o.x],{ngForOf:[0,"ngForOf"]},null),(n()(),o.Cb(5,0,null,0,3,"ion-item",[["class","settings-item"],["lines","none"]],null,null,null,C.W,C.o)),o.Bb(6,49152,null,0,i.J,[o.j,o.p,o.F],{lines:[0,"lines"]},null),(n()(),o.Cb(7,0,null,0,1,"ion-spinner",[["class","center"],["color","spinner"]],null,null,null,C.kb,C.C)),o.Bb(8,49152,null,0,i.tb,[o.j,o.p,o.F],{color:[0,"color"]},null)],(function(n,l){n(l,4,0,l.component.devInfos),n(l,6,0,"none"),n(l,8,0,"spinner")}),null)}function I(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,3,"ion-button",[["class","action-button"],["color","solos-color-d"],["shape","round"]],null,[[null,"click"]],(function(n,l,t){var o=!0;return"click"===l&&(o=!1!==n.component.nextClicked()&&o),o}),C.K,C.c)),o.Bb(1,49152,null,0,i.m,[o.j,o.p,o.F],{color:[0,"color"],shape:[1,"shape"]},null),(n()(),o.Vb(2,0,[" "," "])),o.Pb(131072,k.j,[k.k,o.j])],(function(n,l){n(l,1,0,"solos-color-d","round")}),(function(n,l){n(l,2,0,o.Wb(l,2,0,o.Ob(l,3).transform("setup.next")))}))}function M(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,3,"ion-button",[["class","action-button"],["color","solos-color-d"],["shape","round"]],null,[[null,"click"]],(function(n,l,t){var o=!0;return"click"===l&&(o=!1!==n.component.openBTSetting()&&o),o}),C.K,C.c)),o.Bb(1,49152,null,0,i.m,[o.j,o.p,o.F],{color:[0,"color"],shape:[1,"shape"]},null),(n()(),o.Vb(2,0,[" "," "])),o.Pb(131072,k.j,[k.k,o.j])],(function(n,l){n(l,1,0,"solos-color-d","round")}),(function(n,l){n(l,2,0,o.Wb(l,2,0,o.Ob(l,3).transform("setup.open-bt-btn")))}))}function F(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,3,"ion-button",[["class","action-button"],["color","solos-color-a"],["shape","round"]],null,[[null,"click"]],(function(n,l,t){var o=!0;return"click"===l&&(o=!1!==n.component.doneClicked()&&o),o}),C.K,C.c)),o.Bb(1,49152,null,0,i.m,[o.j,o.p,o.F],{color:[0,"color"],shape:[1,"shape"]},null),(n()(),o.Vb(2,0,[" "," "])),o.Pb(131072,k.j,[k.k,o.j])],(function(n,l){n(l,1,0,"solos-color-a","round")}),(function(n,l){n(l,2,0,o.Wb(l,2,0,o.Ob(l,3).transform("setup.done")))}))}function S(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,20,"ion-content",[["class","settings-background ion-padding-top"],["scrollY","false"]],null,null,null,C.O,C.g)),o.Bb(1,49152,null,0,i.w,[o.j,o.p,o.F],{scrollY:[0,"scrollY"]},null),(n()(),o.Cb(2,0,null,0,18,"div",[["class","body-div"]],null,null,null,null,null)),(n()(),o.Cb(3,0,null,null,2,"p",[["class","content-title"]],null,null,null,null,null)),(n()(),o.Vb(4,null,[" "," "])),o.Pb(131072,k.j,[k.k,o.j]),(n()(),o.Cb(6,0,null,null,4,"div",[["style","height: 50vh; width: 100%;"]],null,null,null,null,null)),(n()(),o.rb(16777216,null,null,1,null,j)),o.Bb(8,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null),(n()(),o.rb(16777216,null,null,1,null,L)),o.Bb(10,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null),(n()(),o.Cb(11,0,null,null,3,"div",[["class","content-text-container"]],null,null,null,null,null)),(n()(),o.Cb(12,0,null,null,2,"p",[["class","content-text"]],null,null,null,null,null)),(n()(),o.Vb(13,null,[" "," "])),o.Pb(131072,k.j,[k.k,o.j]),(n()(),o.rb(16777216,null,null,1,null,I)),o.Bb(16,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null),(n()(),o.rb(16777216,null,null,1,null,M)),o.Bb(18,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null),(n()(),o.rb(16777216,null,null,1,null,F)),o.Bb(20,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null)],(function(n,l){var t=l.component;n(l,1,0,"false"),n(l,8,0,!t.isListOpen),n(l,10,0,t.isListOpen),n(l,16,0,!t.isListOpen&&!t.isNextBtn4BT&&!t.isLastPage),n(l,18,0,t.isNextBtn4BT&&!t.isLastPage),n(l,20,0,t.isLastPage)}),(function(n,l){var t=l.component;n(l,4,0,o.Wb(l,4,0,o.Ob(l,5).transform(t.contentTitle))),n(l,13,0,o.Wb(l,13,0,o.Ob(l,14).transform(t.contentText)))}))}function D(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,1,"div",[["class","image-container"]],null,null,null,null,null)),(n()(),o.Cb(1,0,null,null,0,"img",[["class","image-content"],["style","height: 8em; width: 100%;"]],[[8,"src",4]],null,null,null,null))],null,(function(n,l){n(l,1,0,l.component.imageUrl)}))}function X(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,5,"div",[],null,null,null,null,null)),(n()(),o.Cb(1,0,null,null,4,"ion-item",[["class","settings-item"]],null,[[null,"click"]],(function(n,l,t){var o=!0;return"click"===l&&(o=!1!==n.component.connectDevice(n.context.$implicit)&&o),o}),C.W,C.o)),o.Bb(2,49152,null,0,i.J,[o.j,o.p,o.F],null,null),(n()(),o.Cb(3,0,null,0,2,"ion-label",[["color","settings-left-label"]],null,null,null,C.X,C.p)),o.Bb(4,49152,null,0,i.P,[o.j,o.p,o.F],{color:[0,"color"]},null),(n()(),o.Vb(5,0,["",""]))],(function(n,l){n(l,4,0,"settings-left-label")}),(function(n,l){n(l,5,0,l.context.$implicit.name)}))}function A(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,8,"div",[["class","list-container-landscape"]],null,null,null,null,null)),(n()(),o.Cb(1,0,null,null,7,"ion-list",[["class","settings-body list-content"]],null,null,null,C.Z,C.q)),o.Bb(2,49152,null,0,i.Q,[o.j,o.p,o.F],null,null),(n()(),o.rb(16777216,null,0,1,null,X)),o.Bb(4,278528,null,0,x.k,[o.X,o.T,o.x],{ngForOf:[0,"ngForOf"]},null),(n()(),o.Cb(5,0,null,0,3,"ion-item",[["class","settings-item"],["lines","none"]],null,null,null,C.W,C.o)),o.Bb(6,49152,null,0,i.J,[o.j,o.p,o.F],{lines:[0,"lines"]},null),(n()(),o.Cb(7,0,null,0,1,"ion-spinner",[["class","center"],["color","spinner"]],null,null,null,C.kb,C.C)),o.Bb(8,49152,null,0,i.tb,[o.j,o.p,o.F],{color:[0,"color"]},null)],(function(n,l){n(l,4,0,l.component.devInfos),n(l,6,0,"none"),n(l,8,0,"spinner")}),null)}function N(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,3,"ion-button",[["class","action-button tablet-btn-style "],["color","solos-color-d"],["shape","round"]],null,[[null,"click"]],(function(n,l,t){var o=!0;return"click"===l&&(o=!1!==n.component.nextClicked()&&o),o}),C.K,C.c)),o.Bb(1,49152,null,0,i.m,[o.j,o.p,o.F],{color:[0,"color"],shape:[1,"shape"]},null),(n()(),o.Vb(2,0,[" "," "])),o.Pb(131072,k.j,[k.k,o.j])],(function(n,l){n(l,1,0,"solos-color-d","round")}),(function(n,l){n(l,2,0,o.Wb(l,2,0,o.Ob(l,3).transform("setup.next")))}))}function W(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,3,"ion-button",[["class","action-button tablet-btn-style "],["color","solos-color-d"],["shape","round"]],null,[[null,"click"]],(function(n,l,t){var o=!0;return"click"===l&&(o=!1!==n.component.openBTSetting()&&o),o}),C.K,C.c)),o.Bb(1,49152,null,0,i.m,[o.j,o.p,o.F],{color:[0,"color"],shape:[1,"shape"]},null),(n()(),o.Vb(2,0,[" "," "])),o.Pb(131072,k.j,[k.k,o.j])],(function(n,l){n(l,1,0,"solos-color-d","round")}),(function(n,l){n(l,2,0,o.Wb(l,2,0,o.Ob(l,3).transform("setup.open-bt-btn")))}))}function E(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,3,"ion-button",[["class","action-button tablet-btn-style "],["color","solos-color-a"],["shape","round"]],null,[[null,"click"]],(function(n,l,t){var o=!0;return"click"===l&&(o=!1!==n.component.doneClicked()&&o),o}),C.K,C.c)),o.Bb(1,49152,null,0,i.m,[o.j,o.p,o.F],{color:[0,"color"],shape:[1,"shape"]},null),(n()(),o.Vb(2,0,[" "," "])),o.Pb(131072,k.j,[k.k,o.j])],(function(n,l){n(l,1,0,"solos-color-a","round")}),(function(n,l){n(l,2,0,o.Wb(l,2,0,o.Ob(l,3).transform("setup.done")))}))}function V(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,22,"ion-content",[["class","settings-background ion-padding-top"]],null,null,null,C.O,C.g)),o.Bb(1,49152,null,0,i.w,[o.j,o.p,o.F],null,null),(n()(),o.Cb(2,0,null,0,20,"div",[["class","main-container-landscape"]],null,null,null,null,null)),(n()(),o.Cb(3,0,null,null,3,"div",[["class","col"]],null,null,null,null,null)),(n()(),o.Cb(4,0,null,null,2,"p",[["class","content-title"]],null,null,null,null,null)),(n()(),o.Vb(5,null,[" "," "])),o.Pb(131072,k.j,[k.k,o.j]),(n()(),o.Cb(7,0,null,null,4,"div",[["class","col"]],null,null,null,null,null)),(n()(),o.rb(16777216,null,null,1,null,D)),o.Bb(9,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null),(n()(),o.rb(16777216,null,null,1,null,A)),o.Bb(11,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null),(n()(),o.Cb(12,0,null,null,3,"div",[["class","col"]],null,null,null,null,null)),(n()(),o.Cb(13,0,null,null,2,"p",[["class","content-text"]],null,null,null,null,null)),(n()(),o.Vb(14,null,[" "," "])),o.Pb(131072,k.j,[k.k,o.j]),(n()(),o.Cb(16,0,null,null,6,"div",[["class","col"]],null,null,null,null,null)),(n()(),o.rb(16777216,null,null,1,null,N)),o.Bb(18,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null),(n()(),o.rb(16777216,null,null,1,null,W)),o.Bb(20,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null),(n()(),o.rb(16777216,null,null,1,null,E)),o.Bb(22,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null)],(function(n,l){var t=l.component;n(l,9,0,!t.isListOpen),n(l,11,0,t.isListOpen),n(l,18,0,!t.isListOpen&&!t.isNextBtn4BT&&!t.isLastPage),n(l,20,0,t.isNextBtn4BT&&!t.isLastPage),n(l,22,0,t.isLastPage)}),(function(n,l){var t=l.component;n(l,5,0,o.Wb(l,5,0,o.Ob(l,6).transform(t.contentTitle))),n(l,14,0,o.Wb(l,14,0,o.Ob(l,15).transform(t.contentText)))}))}function _(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,15,"ion-header",[],null,null,null,C.Q,C.i)),o.Bb(1,49152,null,0,i.D,[o.j,o.p,o.F],null,null),(n()(),o.Cb(2,0,null,0,13,"ion-toolbar",[["class","settings-header-background"],["mode","ios"]],null,null,null,C.ob,C.G)),o.Bb(3,49152,null,0,i.Db,[o.j,o.p,o.F],{mode:[0,"mode"]},null),(n()(),o.Cb(4,0,null,0,3,"ion-buttons",[["slot","start"]],null,null,null,C.L,C.d)),o.Bb(5,49152,null,0,i.n,[o.j,o.p,o.F],null,null),(n()(),o.rb(16777216,null,0,1,null,w)),o.Bb(7,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null),(n()(),o.Cb(8,0,null,0,3,"ion-title",[["class","settings-header-title"]],null,null,null,C.mb,C.E)),o.Bb(9,49152,null,0,i.Bb,[o.j,o.p,o.F],null,null),(n()(),o.Vb(10,0,["",""])),o.Pb(131072,k.j,[k.k,o.j]),(n()(),o.Cb(12,0,null,0,3,"ion-buttons",[["slot","end"]],null,null,null,C.L,C.d)),o.Bb(13,49152,null,0,i.n,[o.j,o.p,o.F],null,null),(n()(),o.rb(16777216,null,0,1,null,T)),o.Bb(15,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null),(n()(),o.rb(16777216,null,null,1,null,S)),o.Bb(17,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null),(n()(),o.rb(16777216,null,null,1,null,V)),o.Bb(19,16384,null,0,x.l,[o.X,o.T],{ngIf:[0,"ngIf"]},null)],(function(n,l){var t=l.component;n(l,3,0,"ios"),n(l,7,0,!t.isLastPage),n(l,15,0,!t.isLastPage),n(l,17,0,!t.isLandscapeMode()),n(l,19,0,t.isLandscapeMode())}),(function(n,l){n(l,10,0,o.Wb(l,10,0,o.Ob(l,11).transform("setup.title")))}))}function R(n){return o.Xb(0,[(n()(),o.Cb(0,0,null,null,1,"app-setup",[],null,null,null,_,P)),o.Bb(1,114688,null,0,f,[i.Mb,o.F,i.f,B.a,i.Jb,s.l,k.k,u.a,O.b,r,b.a,d.a],null,null)],(function(n,l){n(l,1,0)}),null)}var J=o.yb("app-setup",f,R,{},{},[]),z=t("s7LF");t.d(l,"SetupPageModuleNgFactory",(function(){return U}));var U=o.zb(m,[],(function(n){return o.Lb([o.Mb(512,o.m,o.kb,[[8,[v.a,J]],[3,o.m],o.D]),o.Mb(4608,x.n,x.m,[o.z,[2,x.z]]),o.Mb(4608,z.j,z.j,[]),o.Mb(4608,i.c,i.c,[o.F,o.g]),o.Mb(4608,i.Ib,i.Ib,[i.c,o.m,o.w]),o.Mb(4608,i.Nb,i.Nb,[i.c,o.m,o.w]),o.Mb(1073742336,x.b,x.b,[]),o.Mb(1073742336,z.i,z.i,[]),o.Mb(1073742336,z.b,z.b,[]),o.Mb(1073742336,i.Fb,i.Fb,[]),o.Mb(1073742336,k.h,k.h,[]),o.Mb(1073742336,B.o,B.o,[[2,B.t],[2,B.m]]),o.Mb(1073742336,m,m,[]),o.Mb(1024,B.k,(function(){return[[{path:"",component:f}]]}),[])])}))}}]);