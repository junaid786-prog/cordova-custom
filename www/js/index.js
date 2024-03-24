/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

// Wait for the deviceready event before using any of Cordova's device APIs.
// See https://cordova.apache.org/docs/en/latest/cordova/events/events.html#deviceready
document.addEventListener('deviceready', onDeviceReady, false);
var GAIA_QCC_Plugin = {};
var deviceId = "eeae145e-b39b-31af-9ed5-00e08e439d57";
function onDeviceReady() {
    // Cordova is now initialized. Have fun!

    console.log('Running cordova-' + cordova.platformId + '@' + cordova.version);
    document.getElementById('deviceready').classList.add('ready');
    document.getElementById('info-dev').innerHTML = device.cordova;

    window.addEventListener("onDeviceScanned", onDeviceFound)
    window.addEventListener("classicBluetoothOnDeviceFound", onClassicBluetoothDeviceFound);
    window.addEventListener("onGaiaQccDeviceScanned", onGaiaQccDeviceScanned);
    window.addEventListener("onGaiaQccBatteryValueAvailable", onGaiaQccBatteryValueAvailable);

    window.addEventListener("onGaiaQccBluetoothOn", onGaiaQccBluetoothOn);
    window.addEventListener("onGaiaQccBluetoothOff", onGaiaQccBluetoothOff);
    window.addEventListener("onGaiaQccDeviceScanned", onGaiaQccDeviceScanned);
    window.addEventListener("onGaiaQccDeviceConnected", onGaiaQccDeviceConnected);
    window.addEventListener("onGaiaQccDeviceDisconnected", onGaiaQccDeviceDisconnected);


    GAIA_QCC_Plugin = window.cordova.plugins.GAIA_QCC_Plugin;


    setTimeout(initPlugin, 3000)



    document.getElementById('connect-btn').onclick = () => {
        console.log("CONNECTING HERE");

        GAIA_QCC_Plugin.connect(deviceId, 3000, onResult, errorM)
    }

    document.getElementById('start-scan').onclick = () => {
        console.log("::startScanning")
        GAIA_QCC_Plugin.startScan(onResult, errorM)
    }
    document.getElementById('stop-scan').onclick = () => {
        console.log("::stopScanning")
        GAIA_QCC_Plugin.stopScan(onResult, errorM)
    }
    document.getElementById('init-sensor').onclick = () => {
        console.log("::init sensors")
        GAIA_QCC_Plugin.initSensors(deviceId, onResult, errorM);
        GAIA_QCC_Plugin.enableGAMSensors(deviceId, true, onResult, errorM);
    }

    document.getElementById('read-battery').onclick = () => {
            console.log("::read battery")
            GAIA_QCC_Plugin.readBattery(deviceId, onResult, errorM);


            GAIA_QCC_Plugin.enableStepCountSensor(deviceId, true, onResult, errorM);
            GAIA_QCC_Plugin.enableStepCountEvent(deviceId, true, onResult, errorM)
            GAIA_QCC_Plugin.enableGestureEvent(deviceId, true, onResult, errorM);
    }




    console.log("HELLO");
}

function initPlugin(){
    GAIA_QCC_Plugin.init(success, errorM)
}
function stop(){
    Cordova.exec(
              success,
              errorM,
              "GAIA_QCC_Plugin",
              "stopScan",
              []
        )
}
function success(msg) {
    alert("Hello: " + msg)
    console.log(msg)
}
function errorM(msg) {
    alert("Error: " + msg)
}

function onDeviceFound(args){
    console.log("received::" );
    console.log({
        args
    });
}

function onResult(args){
    console.log("result::" , args?.length);
    console.log(JSON.stringify(args))
    alert("RESULT" + args)
}

function onEvent(res) {
    console.log("Results from res::", res?.length, JSON.stringify(res))
}



function stopSensors(){
    GAIA_QCC_Plugin.enableGAMSensors(deviceId, false, onResult, errorM);
    GAIA_QCC_Plugin.enableStepCountSensor(deviceId, false, onResult, errorM);
    GAIA_QCC_Plugin.enableStepCountEvent(deviceId, false, onResult, errorM)
    GAIA_QCC_Plugin.enableGestureEvent(deviceId, false, onResult, errorM);

    window.removeEventListener("onGaiaQccGestureChanged", remove);
    window.removeEventListener("onGaiaQccStepCountAvailable", remove);
    window.removeEventListener("onGaiaQccProximityValueAvailable", remove);
    window.removeEventListener("onGaiaQccBatteryValueAvailable", remove);
    window.removeEventListener("onGaiaQccGAMDataAvailable", remove);

}
function onClassicBluetoothDeviceFound(event) {
    console.log("Event Tag: classicBluetoothOnDeviceFound");
    console.log("Event Value: " + JSON.stringify(event));
}

function onGaiaQccGestureChanged(event) {
    console.log("Event Tag: onGaiaQccGestureChanged");
    console.log("Event Value: " + JSON.stringify(event));
}

function onGaiaQccDeviceScanned(event) {
    console.log("Event Tag: onGaiaQccDeviceScanned");
    console.log("Event Value: " + JSON.stringify(event));
}

function onGaiaQccStepCountAvailable(event) {
    console.log("Event Tag: onGaiaQccStepCountAvailable");
    console.log("Event Value: " + JSON.stringify(event));
}

function onGaiaQccProximityValueAvailable(event) {
    console.log("Event Tag: onGaiaQccProximityValueAvailable");
    console.log("Event Value: " + JSON.stringify(event));
}

function onGaiaQccBatteryValueAvailable(event) {
    console.log("Event Tag: onGaiaQccBatteryValueAvailable");
    console.log("Event Value: " + JSON.stringify(event));
}

function onGaiaQccBluetoothOn(event) {
    console.log("Event Tag: onGaiaQccBluetoothOn");
    console.log("Event Value: " + JSON.stringify(event));
}

function onGaiaQccBluetoothOff(event) {
    console.log("Event Tag: onGaiaQccBluetoothOff");
    console.log("Event Value: " + JSON.stringify(event));
}

function onGaiaQccDeviceScanned(event) {
    console.log("Event Tag: onGaiaQccDeviceScanned");
    console.log("Event Value: " + JSON.stringify(event));
}

function onGaiaQccDeviceConnected(event) {
    console.log("Event Tag: onGaiaQccDeviceConnected");
    console.log("Event Value: " + JSON.stringify(event));
}

function onGaiaQccDeviceDisconnected(event) {
    console.log("Event Tag: onGaiaQccDeviceDisconnected");
    console.log("Event Value: " + JSON.stringify(event));
}

function onGaiaQccGAMDataAvailable(event) {
    console.log("Event Tag: onGaiaQccGAMDataAvailable");
    console.log("Event Value: " + JSON.stringify(event));
}

function remove(){
    console.log("removed")
}

function registerListeners(){
    window.addEventListener("onGaiaQccGAMDataAvailable", onGaiaQccGAMDataAvailable);
    window.addEventListener("onGaiaQccStepCountAvailable", onGaiaQccStepCountAvailable);
    window.addEventListener("onGaiaQccProximityValueAvailable", onGaiaQccProximityValueAvailable);
    window.addEventListener("onGaiaQccGestureChanged", onGaiaQccGestureChanged);
}