package cordova.plugins.qcc.GaiaLibrary.gaia;

/* loaded from: classes2.dex */
public class CommandSpecification {
    public static final int DSP_REG_SENSOR_CALIBRATION_GA = 6291650;
    public static final int DSP_REG_SENSOR_CALIBRATION_MAGNET = 6291648;
    public static final int DSP_REG_WHISPER = 6291586;
    public static final int DSP_REG_WHISPER_DOWNLINK = 6291588;
    public static final int DSP_REG_WHISPER_EQ = 6291590;
    public static final int DSP_REG_WHISPER_FEATURE = 6291592;
    public static final int DSP_REG_WHISPER_HEARABLE = 6292482;
    public static final int GAIA_SOLOS_COMMANDS = 648;
    public static final byte GAIA_SOLOS_COMMANDS_DELETE_WHOLE_DAY_POSTURE_MONITOR_REPORT = 89;
    public static final byte GAIA_SOLOS_COMMANDS_DELETE_WHOLE_DAY_STEP_COUNT_REPORT = 87;
    public static final byte GAIA_SOLOS_COMMANDS_DOWNLOAD_WHOLE_DAY_POSTURE_MONITOR_REPORT = 88;
    public static final byte GAIA_SOLOS_COMMANDS_DOWNLOAD_WHOLE_DAY_POSTURE_MONITOR_REPORT_2 = 90;
    public static final byte GAIA_SOLOS_COMMANDS_DOWNLOAD_WHOLE_DAY_STEP_COUNT_REPORT = 86;
    public static final byte GAIA_SOLOS_COMMANDS_FACTORY_RESET = 64;
    public static final byte GAIA_SOLOS_COMMANDS_GET_AV_EVENTS = 40;
    public static final byte GAIA_SOLOS_COMMANDS_GET_AV_INFO = 41;
    public static final byte GAIA_SOLOS_COMMANDS_GET_DSP_CONFIG = 51;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_AMBIENT_NOISE_DEBUG_EVENT_ENABLE = 38;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_EVENT_9AXIS = 16;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_EVENT_AV_INFO = 37;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_EVENT_GESTURE = 18;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_EVENT_INTERVAL = 35;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_EVENT_MOTION = 19;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_EVENT_STATUS = 33;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_EVENT_STEP_COUNTS = 17;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_UART_DATA_EVENT = 48;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_WHOLE_DAY_POSTURE_MONITOR = 33;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_WHOLE_DAY_POSTURE_MONITOR_2 = 34;
    public static final byte GAIA_SOLOS_COMMANDS_GET_REPORT_WHOLE_DAY_STEP_COUNT = 32;
    public static final byte GAIA_SOLOS_COMMANDS_GET_WHOLE_DAY_MONITOR = 81;
    public static final byte GAIA_SOLOS_COMMANDS_GET_WHOLE_DAY_MONITOR_REPORT = 83;
    public static final byte GAIA_SOLOS_COMMANDS_GET_WHOLE_DAY_POSTURE_MONITOR_CONFIG = 85;
    public static final byte GAIA_SOLOS_COMMANDS_READ_9AXIS = 18;
    public static final byte GAIA_SOLOS_COMMANDS_READ_CONFIG_PARAM = 49;
    public static final byte GAIA_SOLOS_COMMANDS_READ_FW_VER = 0;
    public static final byte GAIA_SOLOS_COMMANDS_READ_I2C = 2;
    public static final byte GAIA_SOLOS_COMMANDS_READ_PROXIMITY = 17;
    public static final byte GAIA_SOLOS_COMMANDS_READ_VOICE_PROMPT_LANG = 4;
    public static final byte GAIA_SOLOS_COMMANDS_REBOOT_DEVICE = 8;
    public static final byte GAIA_SOLOS_COMMANDS_SENSORS_RESET = 16;
    public static final byte GAIA_SOLOS_COMMANDS_SET_ASR_MODE = 22;
    public static final byte GAIA_SOLOS_COMMANDS_SET_AV_EVENTS = 39;
    public static final byte GAIA_SOLOS_COMMANDS_SET_CONFIG_PARAM = 48;
    public static final byte GAIA_SOLOS_COMMANDS_SET_DSP_CONFIG = 50;
    public static final byte GAIA_SOLOS_COMMANDS_SET_LED = 7;
    public static final byte GAIA_SOLOS_COMMANDS_SET_REPORT_AMBIENT_NOISE_DEBUG_EVENT_ENABLE = 37;
    public static final byte GAIA_SOLOS_COMMANDS_SET_REPORT_ENABLE = 32;
    public static final byte GAIA_SOLOS_COMMANDS_SET_REPORT_EVENT_INTERVAL = 34;
    public static final byte GAIA_SOLOS_COMMANDS_SET_RTC = 5;
    public static final byte GAIA_SOLOS_COMMANDS_SET_VOICE_PROMPT_LANG = 3;
    public static final byte GAIA_SOLOS_COMMANDS_SET_VOLATILE_CONFIG_TIMEOUT = 52;
    public static final byte GAIA_SOLOS_COMMANDS_SET_WHOLE_DAY_MONITOR = 80;
    public static final byte GAIA_SOLOS_COMMANDS_SET_WHOLE_DAY_MONITOR_REPORT_EVENT = 82;
    public static final byte GAIA_SOLOS_COMMANDS_SET_WHOLE_DAY_POSTURE_MONITOR_CONFIG = 84;
    public static final byte GAIA_SOLOS_COMMANDS_UART_ENABLE = 36;
    public static final byte GAIA_SOLOS_COMMANDS_WRITE_I2C = 1;
    public static final byte GAIA_SOLOS_COMMANDS_WRITE_UART = 6;
    public static final int GAIA_SOLOS_EVENTS = 49800;
    public static final int GA_DATA_LENGTH_IN_SPEC = 12;
    public static byte I2C_READ_DATA_SIZE = 1;
    public static byte I2C_READ_DEVICE_ADDR = 114;
    public static byte I2C_READ_REG_ADDR = -110;
    public static byte I2C_WRITE_DATA_SIZE = 1;
    public static byte I2C_WRITE_DEVICE_ADDR = -44;
    public static byte I2C_WRITE_REG_ADDR = 89;
    public static final int MAGNET_DATA_LENGTH_IN_SPEC = 6;
    public static final byte MEDIA_ARTIST = 2;
    public static final byte MEDIA_CURRENT_PLAY_TIME = 8;
    public static final byte MEDIA_NEXT = 75;
    public static final byte MEDIA_PAUSE = 70;
    public static final byte MEDIA_PLAY = 68;
    public static final byte MEDIA_PLAY_STATUS = Byte.MIN_VALUE;
    public static final byte MEDIA_PLAY_TIME = 4;
    public static final byte MEDIA_PREV = 76;
    public static final byte MEDIA_TITLE = 1;
    public static final byte MEDIA_VOL_DOWN = 66;
    public static final byte MEDIA_VOL_UP = 65;
    public static final int PARAM_CATEGORY_FACTORY = 5;
    public static final int PARAM_CATEGORY_INPUT = 4;
    public static final int PARAM_CATEGORY_MONITOR = 6;
    public static final int PARAM_CATEGORY_SENSORS = 3;
    public static final int PARAM_CATEGORY_SYSTEM = 2;
    public static final byte PARAM_CATEGORY_WHISPER_RT = 16;
    public static final byte PARAM_CATEGORY_WHISPER_RT2 = 17;
    public static final byte PARAM_CONFIGURABLE_STORAGE = 1;
    public static final byte PARAM_FACTORY_STORAGE = 2;
    public static final int PARAM_ID_AUTO_POWER_OFF_MODE = 1;
    public static final byte PARAM_ID_FACTORY_MODEL_NUM = 1;
    public static final byte PARAM_ID_FACTORY_SERIAL_NUM = 0;
    public static final int PARAM_ID_LOST_LINK_ALERT_DURATION = 6;
    public static final int PARAM_ID_OFF_HEAD_VOICE_PROMPT_VOLUME = 4;
    public static final int PARAM_ID_ON_HEAD_VOICE_PROMPT_VOLUME = 3;
    public static final int PARAM_ID_READ_AUTO_POWER_OFF_TIMEOUT = 0;
    public static final int PARAM_ID_SENSORS_TAP_SENSITIVITY = 2;
    public static final int PARAM_ID_SYSTEM_CONFIG = 5;
    public static final short PARAM_ID_SYSTEM_CONFIG_AMBIENT_NOISE_DETECTION = 32;
    public static final short PARAM_ID_SYSTEM_CONFIG_AUTO_POWER_ON_UNPLUG_CHARGER = 1;
    public static final short PARAM_ID_SYSTEM_CONFIG_HAR = 16;
    public static final short PARAM_ID_SYSTEM_CONFIG_LOST_LINK_MULTIPLE_DEVICE = 4;
    public static final short PARAM_ID_SYSTEM_CONFIG_MULTIPOINT = 2;
    public static final short PARAM_ID_SYSTEM_CONFIG_SMART_POWER_OFF = 128;
    public static final short PARAM_ID_SYSTEM_CONFIG_VA_TONE = 8;
    public static final byte PARAM_ID_WHISPER_RT2_AGC_ENABLE = 11;
    public static final byte PARAM_ID_WHISPER_RT2_AGC_POWER = 13;
    public static final byte PARAM_ID_WHISPER_RT2_BAND_10_GAIN = 9;
    public static final byte PARAM_ID_WHISPER_RT2_BAND_1_GAIN = 0;
    public static final byte PARAM_ID_WHISPER_RT2_BAND_2_GAIN = 1;
    public static final byte PARAM_ID_WHISPER_RT2_BAND_3_GAIN = 2;
    public static final byte PARAM_ID_WHISPER_RT2_BAND_4_GAIN = 3;
    public static final byte PARAM_ID_WHISPER_RT2_BAND_5_GAIN = 4;
    public static final byte PARAM_ID_WHISPER_RT2_BAND_6_GAIN = 5;
    public static final byte PARAM_ID_WHISPER_RT2_BAND_7_GAIN = 6;
    public static final byte PARAM_ID_WHISPER_RT2_BAND_8_GAIN = 7;
    public static final byte PARAM_ID_WHISPER_RT2_BAND_9_GAIN = 8;
    public static final byte PARAM_ID_WHISPER_RT2_MASTER_GAIN = 10;
    public static final byte PARAM_ID_WHISPER_RT2_OUTFLAG = 12;
    public static final byte PARAM_ID_WHISPER_RT2_OUTPUT_GAIN = 14;
    public static final byte PARAM_ID_WHISPER_RT_BAND_10_GAIN = 23;
    public static final byte PARAM_ID_WHISPER_RT_BAND_1_GAIN = 14;
    public static final byte PARAM_ID_WHISPER_RT_BAND_2_GAIN = 15;
    public static final byte PARAM_ID_WHISPER_RT_BAND_3_GAIN = 16;
    public static final byte PARAM_ID_WHISPER_RT_BAND_4_GAIN = 17;
    public static final byte PARAM_ID_WHISPER_RT_BAND_5_GAIN = 18;
    public static final byte PARAM_ID_WHISPER_RT_BAND_6_GAIN = 19;
    public static final byte PARAM_ID_WHISPER_RT_BAND_7_GAIN = 20;
    public static final byte PARAM_ID_WHISPER_RT_BAND_8_GAIN = 21;
    public static final byte PARAM_ID_WHISPER_RT_BAND_9_GAIN = 22;
    public static final byte PARAM_ID_WHISPER_RT_OUTFLAG = 3;
    public static final byte PARAM_ID_WHISPER_RT_OUTFLAG2 = 13;
    public static final byte PARAM_ID_WHISPER_RT_OUTGAIN = 2;
    public static final byte PARAM_INSTANT_CONFIG_STORAGE = 0;

    /* loaded from: classes2.dex */
    public enum AlertMethod {
        NONE,
        BEEP,
        VOICE
    }

    /* loaded from: classes2.dex */
    public enum GaiaSolosResponseStatus {
        SUCCESS,
        FAILED,
        INVALID_PAYLOAD_LENGTH
    }

    /* loaded from: classes2.dex */
    public enum SolosGaiaLanguageCode {
        ZH_CN,
        ENG
    }
}
