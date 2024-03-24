var exec = require('cordova/exec');

  module.exports = {
    init: function (successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "init",
        []
      )
    },
    startScan: function (successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "startScan",
        []
      )
    },
    stopScan: function (successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "stopScan",
        []
      )
    },
    connect: function (uuid, timeoutMS, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "connect",
        [uuid, timeoutMS]
      )
    },
    disconnect: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "disconnect",
        [uuid]
      )
    },
    upgradeFirmware: function (uuid, data, name, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "ugpradeFirmware",
        [uuid, data, name]
      )
    },
    abortFirmwareUpgrade: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "abortFirmwareUpgrade",
        [uuid]
      )
    },
    switchFirmware: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "switchFirmware",
        [uuid]
      )
    },
    getFirmwareVer: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "getFirmwareVer",
        [uuid]
      )
    },
    initSensors: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "initSensors",
        [uuid]
      )
    },
    enableGestureEvent: function (uuid, enable, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "enableGestureEvent",
        [uuid, enable]
      )
    },
    enableGAMSensors: function (uuid, enable, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "enableGAMSensors",
        [uuid, enable]
      )
    },
    enableGAMEvents: function (uuid, enable, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "enableGAMEvents",
        [uuid, enable]
      )
    },
    setGAMEventInterval: function (uuid, intervalMS, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setGAMEventInterval",
        [uuid, intervalMS]
      )
    },
    enableProximitySensor: function (uuid, enable, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "enableProximitySensor",
        [uuid, enable]
      )
    },
    readProximity: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "readProximity",
        [uuid]
      )
    },
    resetStepCountSensor: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "resetStepCountSensor",
        [uuid]
      )
    },
    enableStepCountSensor: function (uuid, enable, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "enableStepCountSensor",
        [uuid, enable]
      )
    },
    enableStepCountEvent: function (uuid, enable, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "enableStepCountEvent",
        [uuid, enable]
      )
    },
    getCalibrationData: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "getCalibrationData",
        [uuid]
      )
    },
    setMagnetCalibrationData: function (uuid, max_x, min_x, max_y, min_y, max_z, min_z, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setMagnetCalibrationData",
        [uuid, max_x, min_x, max_y, min_y, max_z, min_z]
      )
    },
    setLanguage: function (uuid, langId, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setLanguage",
        [uuid, langId]
      )
    },
    setTapSensitivity: function (uuid, value, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setTapSensitivity",
        [uuid, value]
      )
    },
    getTapSensitivity: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "getTapSensitivity",
        [uuid]
      )
    },
    readBattery: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "readBattery",
        [uuid]
      )
    },
    setPowerOn: function (uuid, on, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setPowerOn",
        [uuid, on]
      )
    },
    readI2C: function (uuid, devAddr, regAddr, bytes2Read, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "readI2C",
        [uuid, devAddr, regAddr, bytes2Read]
      )
    },
    writeI2C: function (uuid, devAddr, regAddr, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "writeI2C",
        [uuid, devAddr, regAddr]
      )
    },
    readAutoPowerOffMode: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "readAutoPowerOffMode",
        [uuid]
      )
    },

    writeAutoPowerOffMode: function (uuid, on, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "writeAutoPowerOffMode",
        [uuid, on]
      )
    },

    readAutoPowerOffTimeout: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "readAutoPowerOffTimeout",
        [uuid]
      )
    },

    writeAutoPowerOffTimeout: function (uuid, timeInMin, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "writeAutoPowerOffTimeout",
        [uuid, timeInMin]
      )
    },
    readOnHeadVoicePromptVolume: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "readOnHeadVoicePromptVolume",
        [uuid]
      )
    },

    writeOnHeadVoicePromptVolume: function (uuid, volume, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "writeOnHeadVoicePromptVolume",
        [uuid, volume]
      )
    },
    readOffHeadVoicePromptVolume: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "readOffHeadVoicePromptVolume",
        [uuid]
      )
    },

    writeOffHeadVoicePromptVolume: function (uuid, volume, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "writeOffHeadVoicePromptVolume",
        [uuid, volume]
      )
    },
    readAutoPowerOnUponUnplugCharger: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "readAutoPowerOnUponUnplugCharger",
        [uuid]
      )
    },

    writeAutoPowerOnUponUnplugCharger: function (uuid, yes, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "writeAutoPowerOnUponUnplugCharger",
        [uuid, yes]
      )
    },
    readLostLinkAlertDuration: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "readLostLinkAlertDuration",
        [uuid]
      )
    },

    writeLostLinkAlertDuration: function (uuid, duration, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "writeLostLinkAlertDuration",
        [uuid, duration]
      )
    },
    getUserEqualizerOn: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "getUserEqualizerOn",
        [uuid]
      )
    },
    setUserEqualizerOn: function (uuid, on, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setUserEqualizerOn",
        [uuid, on]
      )
    },
    getEqualizerMasterGain: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "getEqualizerMasterGain",
        [uuid]
      )
    },
    setEqualizerMasterGain: function (uuid, gain, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setEqualizerMasterGain",
        [uuid, gain]
      )
    },
    getEqualizerGain: function (uuid, band, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "getEqualizerGain",
        [uuid, band]
      )
    },
    setEqualizerGain: function (uuid, band, gain, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setEqualizerGain",
        [uuid, band, gain]
      )
    },
    getEqualizerFrequency: function (uuid, band, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "getEqualizerFrequency",
        [uuid, band]
      )
    },
    setEqualizerFrequency: function (uuid, band, frequency, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setEqualizerFrequency",
        [uuid, band, frequency]
      )
    },
    getEqualizerQFactor: function (uuid, band, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "getEqualizerQFactor",
        [uuid, band]
      )
    },
    setEqualizerQFactor: function (uuid, band, q, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setEqualizerQFactor",
        [uuid, band, q]
      )
    },
    getEqualizerFilterType: function (uuid, band, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "getEqualizerFilterType",
        [uuid, band]
      )
    },
    setEqualizerFilterType: function (uuid, band, filterType, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setEqualizerFilterType",
        [uuid, band, filterType]
      )
    },
    getMultipoint: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "getMultipoint",
        [uuid]
      )
    },
    setMultipoint: function (uuid, on, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "setMultipoint",
        [uuid, on]
      )
    },
    resetDevice: function (uuid, successCallback, errorCallback) {
      return exec(
        successCallback,
        errorCallback,
        "GAIA_QCC_Plugin",
        "resetDevice",
        [uuid]
      )
    },


    Events: {
      onBluetoothOn: function (args) {
        cordova.fireWindowEvent('onGaiaQccBluetoothOn');
      },
      onBluetoothOff: function (args) {
        cordova.fireWindowEvent('onGaiaQccBluetoothOff');
      },
      onDeviceScanned: function (args) {
        cordova.fireWindowEvent('onGaiaQccDeviceScanned', { name: args.name, uuid: args.uuid });
      },
      onDeviceConnected: function (args) {
        cordova.fireWindowEvent('onGaiaQccDeviceConnected', { name: args.name, uuid: args.uuid });
      },
      onDeviceDisconnected: function (args) {
        cordova.fireWindowEvent('onGaiaQccDeviceDisconnected', { name: args.name, uuid: args.uuid });
      },
      onGestureChanged: function (args) {
        cordova.fireWindowEvent('onGaiaQccGestureChanged', { uuid: args.uuid, gestureType: args.gestureType });
      },
      onGAMDataAvailable: function (args) {
        cordova.fireWindowEvent('onGaiaQccGAMDataAvailable', {
          uuid: args.uuid, timestampMS: args.timestampMS,
          gyroX: args.gyroX, gyroY: args.gyroY, gyroZ: args.gyroZ,
          acceX: args.acceX, acceY: args.acceY, acceZ: args.acceZ,
          magnX: args.magnX, magnY: args.magnY, magnZ: args.magnZ
        });
      },
      onStepCountAvailable: function (args) {
        cordova.fireWindowEvent('onGaiaQccStepCountAvailable', { uuid: args.uuid, timestampMS: args.timestampMS, stepCount: args.stepCount });
      },
      onProximityValueAvailable: function (args) {
        cordova.fireWindowEvent('onGaiaQccProximityValueAvailable', { uuid: args.uuid, value: args.value });
      },
      onBatteryValueAvailable: function (args) {
        cordova.fireWindowEvent('onGaiaQccBatteryValueAvailable', { uuid: args.uuid, mV: args.mV, chargingState: args.chargingState });
      },
      readProximity: function (args) {
        cordova.fireWindowEvent('onGaiaQccProximityValueAvailable', { uuid: args.uuid, level: args.value });
      }
    }

  };
