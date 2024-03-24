package cordova.plugins.qcc.GaiaLibrary.gaia;

/* loaded from: classes2.dex */
public final class GaiaUtils {
    private static final int BITS_IN_BYTE = 8;
    private static final int BYTES_IN_INT = 4;
    public static final int ERROR_DOWNLOAD_ABORT = 513;
    public static final int STATE_INSTALLING = 3;
    public static final int STATE_UPGRADE_COMPLETED = 4;

    public static String getHexadecimalStringFromInt(int i) {
        return String.format("%04X", Integer.valueOf(i & 65535));
    }

    public static String getHexadecimalStringFromBytes(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(String.format("0x%02x ", Byte.valueOf(b)));
        }
        return sb.toString();
    }

    public static int extractIntFromByteArray(byte[] bArr, int i, int i2, boolean z) {
        int i3 = 0;
        if ((i2 < 0) || (i2 > 4)) {
            throw new IndexOutOfBoundsException("Length must be between 0 and 4");
        }
        int i4 = (i2 - 1) * 8;
        if (z) {
            for (int i5 = (i2 + i) - 1; i5 >= i; i5--) {
                i3 |= (bArr[i5] & 255) << i4;
                i4 -= 8;
            }
        } else {
            for (int i6 = i; i6 < i + i2; i6++) {
                i3 |= (bArr[i6] & 255) << i4;
                i4 -= 8;
            }
        }
        return i3;
    }

    public static void copyIntIntoByteArray(int i, byte[] bArr, int i2, int i3, boolean z) {
        int i4 = 0;
        if ((i3 < 0) | (i3 > 4)) {
            throw new IndexOutOfBoundsException("Length must be between 0 and 4");
        }
        if (bArr.length < i2 + i3) {
            throw new IndexOutOfBoundsException("The targeted location must be contained in the target array.");
        }
        if (!z) {
            int i5 = (i3 - 1) * 8;
            while (i4 < i3) {
                bArr[i4 + i2] = (byte) (((255 << i5) & i) >> i5);
                i5 -= 8;
                i4++;
            }
            return;
        }
        int i6 = 0;
        for (int i7 = i3 - 1; i7 >= 0; i7--) {
            bArr[i6 + i2] = (byte) (((255 << i4) & i) >> i4);
            i4 += 8;
            i6++;
        }
    }

    public static String getGAIACommandToString(int i) {
        String str;
        if (i == 416) {
            str = "COMMAND_GET_MOUNTED_PARTITIONS";
        } else if (i != 417) {
            switch (i) {
                case 256:
                    str = "COMMAND_SET_RAW_CONFIGURATION(deprecated)";
                    break;
                case 257:
                    str = "COMMAND_SET_LED_CONFIGURATION";
                    break;
                case GAIA.COMMAND_SET_TONE_CONFIGURATION /* 258 */:
                    str = "COMMAND_SET_TONE_CONFIGURATION";
                    break;
                case GAIA.COMMAND_SET_DEFAULT_VOLUME /* 259 */:
                    str = "COMMAND_SET_DEFAULT_VOLUME";
                    break;
                case GAIA.COMMAND_FACTORY_DEFAULT_RESET /* 260 */:
                    str = "COMMAND_FACTORY_DEFAULT_RESET";
                    break;
                case GAIA.COMMAND_SET_VIBRATOR_CONFIGURATION /* 261 */:
                    str = "COMMAND_SET_VIBRATOR_CONFIGURATION";
                    break;
                case GAIA.COMMAND_SET_VOICE_PROMPT_CONFIGURATION /* 262 */:
                    str = "COMMAND_SET_VOICE_PROMPT_CONFIGURATION";
                    break;
                case 263:
                    str = "COMMAND_SET_FEATURE_CONFIGURATION";
                    break;
                case GAIA.COMMAND_SET_USER_EVENT_CONFIGURATION /* 264 */:
                    str = "COMMAND_SET_USER_EVENT_CONFIGURATION";
                    break;
                case GAIA.COMMAND_SET_TIMER_CONFIGURATION /* 265 */:
                    str = "COMMAND_SET_TIMER_CONFIGURATION";
                    break;
                case GAIA.COMMAND_SET_AUDIO_GAIN_CONFIGURATION /* 266 */:
                    str = "COMMAND_SET_AUDIO_GAIN_CONFIGURATION";
                    break;
                case GAIA.COMMAND_SET_VOLUME_CONFIGURATION /* 267 */:
                    str = "COMMAND_SET_VOLUME_CONFIGURATION";
                    break;
                case GAIA.COMMAND_SET_POWER_CONFIGURATION /* 268 */:
                    str = "COMMAND_SET_POWER_CONFIGURATION";
                    break;
                default:
                    switch (i) {
                        case GAIA.COMMAND_SET_USER_TONE_CONFIGURATION /* 270 */:
                            str = "COMMAND_SET_USER_TONE_CONFIGURATION";
                            break;
                        case 271:
                            str = "COMMAND_SET_DEVICE_NAME";
                            break;
                        case GAIA.COMMAND_SET_WLAN_CREDENTIALS /* 272 */:
                            str = "COMMAND_SET_WLAN_CREDENTIALS";
                            break;
                        case GAIA.COMMAND_SET_PEER_PERMITTED_ROUTING /* 273 */:
                            str = "COMMAND_SET_PEER_PERMITTED_ROUTING";
                            break;
                        case GAIA.COMMAND_SET_PERMITTED_NEXT_AUDIO_SOURCE /* 274 */:
                            str = "COMMAND_SET_PERMITTED_NEXT_AUDIO_SOURCE";
                            break;
                        default:
                            switch (i) {
                                case GAIA.COMMAND_SET_ONE_TOUCH_DIAL_STRING /* 278 */:
                                    str = "COMMAND_SET_ONE_TOUCH_DIAL_STRING";
                                    break;
                                case GAIA.COMMAND_SET_DFU_PARTITION /* 289 */:
                                    str = "COMMAND_SET_DFU_PARTITION";
                                    break;
                                case GAIA.COMMAND_GET_ONE_TOUCH_DIAL_STRING /* 406 */:
                                    str = "COMMAND_GET_ONE_TOUCH_DIAL_STRING";
                                    break;
                                case GAIA.COMMAND_ALERT_EVENT /* 528 */:
                                    str = "COMMAND_ALERT_EVENT";
                                    break;
                                case GAIA.COMMAND_ALERT_VOICE /* 529 */:
                                    str = "COMMAND_ALERT_VOICE";
                                    break;
                                case GAIA.COMMAND_SET_AUDIO_PROMPT_LANGUAGE /* 530 */:
                                    str = "COMMAND_SET_AUDIO_PROMPT_LANGUAGE";
                                    break;
                                case GAIA.COMMAND_START_SPEECH_RECOGNITION /* 531 */:
                                    str = "COMMAND_START_SPEECH_RECOGNITION";
                                    break;
                                case GAIA.COMMAND_SET_EQ_CONTROL /* 532 */:
                                    str = "COMMAND_SET_EQ_CONTROL";
                                    break;
                                case GAIA.COMMAND_SET_BASS_BOOST_CONTROL /* 533 */:
                                    str = "COMMAND_SET_BASS_BOOST_CONTROL";
                                    break;
                                case GAIA.COMMAND_SET_3D_ENHANCEMENT_CONTROL /* 534 */:
                                    str = "COMMAND_SET_3D_ENHANCEMENT_CONTROL";
                                    break;
                                case GAIA.COMMAND_SWITCH_EQ_CONTROL /* 535 */:
                                    str = "COMMAND_SWITCH_EQ_CONTROL";
                                    break;
                                case GAIA.COMMAND_TOGGLE_BASS_BOOST_CONTROL /* 536 */:
                                    str = "COMMAND_TOGGLE_BASS_BOOST_CONTROL";
                                    break;
                                case GAIA.COMMAND_TOGGLE_3D_ENHANCEMENT_CONTROL /* 537 */:
                                    str = "COMMAND_TOGGLE_3D_ENHANCEMENT_CONTROL";
                                    break;
                                case GAIA.COMMAND_SET_EQ_PARAMETER /* 538 */:
                                    str = "COMMAND_SET_EQ_PARAMETER";
                                    break;
                                case GAIA.COMMAND_SET_EQ_GROUP_PARAMETER /* 539 */:
                                    str = "COMMAND_SET_EQ_GROUP_PARAMETER";
                                    break;
                                case GAIA.COMMAND_DISPLAY_CONTROL /* 540 */:
                                    str = "COMMAND_DISPLAY_CONTROL";
                                    break;
                                case GAIA.COMMAND_ENTER_BLUETOOTH_PAIRING_MODE /* 541 */:
                                    str = "COMMAND_ENTER_BLUETOOTH_PAIRING_MODE";
                                    break;
                                case GAIA.COMMAND_SET_AUDIO_SOURCE /* 542 */:
                                    str = "COMMAND_SET_AUDIO_SOURCE";
                                    break;
                                case GAIA.COMMAND_AV_REMOTE_CONTROL /* 543 */:
                                    str = "COMMAND_AV_REMOTE_CONTROL";
                                    break;
                                case GAIA.COMMAND_SET_USER_EQ_CONTROL /* 544 */:
                                    str = "COMMAND_SET_USER_EQ_CONTROL";
                                    break;
                                case GAIA.COMMAND_TOGGLE_USER_EQ_CONTROL /* 545 */:
                                    str = "COMMAND_TOGGLE_USER_EQ_CONTROL";
                                    break;
                                case GAIA.COMMAND_SET_SPEAKER_EQ_CONTROL /* 546 */:
                                    str = "COMMAND_SET_SPEAKER_EQ_CONTROL";
                                    break;
                                case GAIA.COMMAND_TOGGLE_SPEAKER_EQ_CONTROL /* 547 */:
                                    str = "COMMAND_TOGGLE_SPEAKER_EQ_CONTROL";
                                    break;
                                case GAIA.COMMAND_SET_TWS_AUDIO_ROUTING /* 548 */:
                                    str = "COMMAND_SET_TWS_AUDIO_ROUTING";
                                    break;
                                case GAIA.COMMAND_SET_TWS_VOLUME /* 549 */:
                                    str = "COMMAND_SET_TWS_VOLUME";
                                    break;
                                case GAIA.COMMAND_TRIM_TWS_VOLUME /* 550 */:
                                    str = "COMMAND_TRIM_TWS_VOLUME";
                                    break;
                                case GAIA.COMMAND_SET_PEER_LINK_RESERVED /* 551 */:
                                    str = "COMMAND_SET_PEER_LINK_RESERVED";
                                    break;
                                case GAIA.COMMAND_SET_CODEC /* 576 */:
                                    str = "COMMAND_SET_CODEC";
                                    break;
                                case GAIA.COMMAND_GET_VOICE_PROMPT_CONTROL /* 650 */:
                                    str = "COMMAND_GET_VOICE_PROMPT_CONTROL";
                                    break;
                                case GAIA.COMMAND_GET_SPEECH_RECOGNITION_CONTROL /* 652 */:
                                    str = "COMMAND_GET_SPEECH_RECOGNITION_CONTROL";
                                    break;
                                case GAIA.COMMAND_GET_AUDIO_PROMPT_LANGUAGE /* 658 */:
                                    str = "COMMAND_GET_AUDIO_PROMPT_LANGUAGE";
                                    break;
                                case GAIA.COMMAND_GET_EQ_PARAMETER /* 666 */:
                                    str = "COMMAND_GET_EQ_PARAMETER";
                                    break;
                                case GAIA.COMMAND_GET_EQ_GROUP_PARAMETER /* 667 */:
                                    str = "COMMAND_GET_EQ_GROUP_PARAMETER";
                                    break;
                                case GAIA.COMMAND_GET_AUDIO_SOURCE /* 670 */:
                                    str = "COMMAND_GET_AUDIO_SOURCE";
                                    break;
                                case GAIA.COMMAND_GET_USER_EQ_CONTROL /* 672 */:
                                    str = "COMMAND_GET_USER_EQ_CONTROL";
                                    break;
                                case GAIA.COMMAND_GET_SPEAKER_EQ_CONTROL /* 674 */:
                                    str = "COMMAND_GET_SPEAKER_EQ_CONTROL";
                                    break;
                                case GAIA.COMMAND_GET_TWS_AUDIO_ROUTING /* 676 */:
                                    str = "COMMAND_GET_TWS_AUDIO_ROUTING";
                                    break;
                                case GAIA.COMMAND_GET_TWS_VOLUME /* 677 */:
                                    str = "COMMAND_GET_TWS_VOLUME";
                                    break;
                                case GAIA.COMMAND_GET_PEER_LINK_RESERVED /* 679 */:
                                    str = "COMMAND_GET_PEER_LINK_RESERVED";
                                    break;
                                case GAIA.COMMAND_GET_CODEC /* 704 */:
                                    str = "COMMAND_GET_CODEC";
                                    break;
                                case 768:
                                    str = "COMMAND_GET_API_VERSION";
                                    break;
                                case GAIA.COMMAND_GET_CURRENT_RSSI /* 769 */:
                                    str = "COMMAND_GET_CURRENT_RSSI";
                                    break;
                                case GAIA.COMMAND_GET_CURRENT_BATTERY_LEVEL /* 770 */:
                                    str = "COMMAND_GET_CURRENT_BATTERY_LEVEL";
                                    break;
                                case GAIA.COMMAND_GET_MODULE_ID /* 771 */:
                                    str = "COMMAND_GET_MODULE_ID";
                                    break;
                                case GAIA.COMMAND_GET_APPLICATION_VERSION /* 772 */:
                                    str = "COMMAND_GET_APPLICATION_VERSION";
                                    break;
                                case GAIA.COMMAND_GET_PIO_STATE /* 774 */:
                                    str = "COMMAND_GET_PIO_STATE";
                                    break;
                                case GAIA.COMMAND_READ_ADC /* 775 */:
                                    str = "COMMAND_READ_ADC";
                                    break;
                                case GAIA.COMMAND_GET_PEER_ADDRESS /* 778 */:
                                    str = "COMMAND_GET_PEER_ADDRESS";
                                    break;
                                case GAIA.COMMAND_GET_DFU_STATUS /* 784 */:
                                    str = "COMMAND_GET_DFU_STATUS(deprecated)";
                                    break;
                                case GAIA.COMMAND_GET_HOST_FEATURE_INFORMATION /* 800 */:
                                    str = "COMMAND_GET_HOST_FEATURE_INFORMATION";
                                    break;
                                case GAIA.COMMAND_AUTHENTICATE_REQUEST /* 1281 */:
                                    str = "COMMAND_AUTHENTICATE_REQUEST";
                                    break;
                                case GAIA.COMMAND_AUTHENTICATE_RESPONSE /* 1282 */:
                                    str = "COMMAND_AUTHENTICATE_RESPONSE";
                                    break;
                                case GAIA.COMMAND_SET_FEATURE /* 1283 */:
                                    str = "COMMAND_SET_FEATURE";
                                    break;
                                case GAIA.COMMAND_SET_SESSION_ENABLE /* 1284 */:
                                    str = "COMMAND_SET_SESSION_ENABLE";
                                    break;
                                case GAIA.COMMAND_GET_AUTH_BITMAPS /* 1408 */:
                                    str = "COMMAND_GET_AUTH_BITMAPS";
                                    break;
                                case GAIA.COMMAND_GET_FEATURE /* 1411 */:
                                    str = "COMMAND_GET_FEATURE";
                                    break;
                                case GAIA.COMMAND_GET_SESSION_ENABLE /* 1412 */:
                                    str = "COMMAND_GET_SESSION_ENABLE";
                                    break;
                                case GAIA.COMMAND_DATA_TRANSFER_SETUP /* 1537 */:
                                    str = "COMMAND_DATA_TRANSFER_SETUP";
                                    break;
                                case GAIA.COMMAND_DATA_TRANSFER_CLOSE /* 1538 */:
                                    str = "COMMAND_DATA_TRANSFER_CLOSE";
                                    break;
                                case GAIA.COMMAND_HOST_TO_DEVICE_DATA /* 1539 */:
                                    str = "COMMAND_HOST_TO_DEVICE_DATA";
                                    break;
                                case GAIA.COMMAND_DEVICE_TO_HOST_DATA /* 1540 */:
                                    str = "COMMAND_DEVICE_TO_HOST_DATA";
                                    break;
                                case GAIA.COMMAND_I2C_TRANSFER /* 1544 */:
                                    str = "COMMAND_I2C_TRANSFER";
                                    break;
                                case GAIA.COMMAND_GET_STORAGE_PARTITION_STATUS /* 1552 */:
                                    str = "COMMAND_GET_STORAGE_PARTITION_STATUS";
                                    break;
                                case GAIA.COMMAND_OPEN_STORAGE_PARTITION /* 1553 */:
                                    str = "COMMAND_OPEN_STORAGE_PARTITION";
                                    break;
                                case GAIA.COMMAND_OPEN_UART /* 1554 */:
                                    str = "COMMAND_OPEN_UART";
                                    break;
                                case GAIA.COMMAND_WRITE_STORAGE_PARTITION /* 1557 */:
                                    str = "COMMAND_WRITE_STORAGE_PARTITION";
                                    break;
                                case GAIA.COMMAND_WRITE_STREAM /* 1559 */:
                                    str = "COMMAND_WRITE_STREAM";
                                    break;
                                case GAIA.COMMAND_CLOSE_STORAGE_PARTITION /* 1560 */:
                                    str = "COMMAND_CLOSE_STORAGE_PARTITION";
                                    break;
                                case GAIA.COMMAND_MOUNT_STORAGE_PARTITION /* 1562 */:
                                    str = "COMMAND_MOUNT_STORAGE_PARTITION";
                                    break;
                                case GAIA.COMMAND_GET_FILE_STATUS /* 1568 */:
                                    str = "COMMAND_GET_FILE_STATUS";
                                    break;
                                case GAIA.COMMAND_OPEN_FILE /* 1569 */:
                                    str = "COMMAND_OPEN_FILE";
                                    break;
                                case GAIA.COMMAND_READ_FILE /* 1572 */:
                                    str = "COMMAND_READ_FILE";
                                    break;
                                case GAIA.COMMAND_CLOSE_FILE /* 1576 */:
                                    str = "COMMAND_CLOSE_FILE";
                                    break;
                                case GAIA.COMMAND_DFU_REQUEST /* 1584 */:
                                    str = "COMMAND_DFU_REQUEST";
                                    break;
                                case GAIA.COMMAND_DFU_BEGIN /* 1585 */:
                                    str = "COMMAND_DFU_BEGIN";
                                    break;
                                case GAIA.COMMAND_DFU_WRITE /* 1586 */:
                                    str = "COMMAND_DFU_WRITE";
                                    break;
                                case GAIA.COMMAND_DFU_COMMIT /* 1587 */:
                                    str = "COMMAND_DFU_COMMIT";
                                    break;
                                case GAIA.COMMAND_DFU_GET_RESULT /* 1588 */:
                                    str = "COMMAND_DFU_GET_RESULT";
                                    break;
                                case GAIA.COMMAND_VM_UPGRADE_CONNECT /* 1600 */:
                                    str = "COMMAND_VM_UPGRADE_CONNECT";
                                    break;
                                case GAIA.COMMAND_VM_UPGRADE_DISCONNECT /* 1601 */:
                                    str = "COMMAND_VM_UPGRADE_DISCONNECT";
                                    break;
                                case GAIA.COMMAND_VM_UPGRADE_CONTROL /* 1602 */:
                                    str = "COMMAND_VM_UPGRADE_CONTROL";
                                    break;
                                case GAIA.COMMAND_VM_UPGRADE_DATA /* 1603 */:
                                    str = "COMMAND_VM_UPGRADE_DATA";
                                    break;
                                case 1792:
                                    str = "COMMAND_NO_OPERATION";
                                    break;
                                case GAIA.COMMAND_GET_DEBUG_FLAGS /* 1793 */:
                                    str = "COMMAND_GET_DEBUG_FLAGS";
                                    break;
                                case GAIA.COMMAND_SET_DEBUG_FLAGS /* 1794 */:
                                    str = "COMMAND_SET_DEBUG_FLAGS";
                                    break;
                                case GAIA.COMMAND_RETRIEVE_PS_KEY /* 1808 */:
                                    str = "COMMAND_RETRIEVE_PS_KEY";
                                    break;
                                case GAIA.COMMAND_RETRIEVE_FULL_PS_KEY /* 1809 */:
                                    str = "COMMAND_RETRIEVE_FULL_PS_KEY";
                                    break;
                                case GAIA.COMMAND_STORE_PS_KEY /* 1810 */:
                                    str = "COMMAND_STORE_PS_KEY";
                                    break;
                                case GAIA.COMMAND_FLOOD_PS /* 1811 */:
                                    str = "COMMAND_FLOOD_PS";
                                    break;
                                case GAIA.COMMAND_STORE_FULL_PS_KEY /* 1812 */:
                                    str = "COMMAND_STORE_FULL_PS_KEY";
                                    break;
                                case GAIA.COMMAND_SEND_DEBUG_MESSAGE /* 1824 */:
                                    str = "COMMAND_SEND_DEBUG_MESSAGE";
                                    break;
                                case GAIA.COMMAND_SEND_APPLICATION_MESSAGE /* 1825 */:
                                    str = "COMMAND_SEND_APPLICATION_MESSAGE";
                                    break;
                                case GAIA.COMMAND_SEND_KALIMBA_MESSAGE /* 1826 */:
                                    str = "COMMAND_SEND_KALIMBA_MESSAGE";
                                    break;
                                case GAIA.COMMAND_GET_MEMORY_SLOTS /* 1840 */:
                                    str = "COMMAND_GET_MEMORY_SLOTS";
                                    break;
                                case GAIA.COMMAND_GET_DEBUG_VARIABLE /* 1856 */:
                                    str = "COMMAND_GET_DEBUG_VARIABLE";
                                    break;
                                case GAIA.COMMAND_SET_DEBUG_VARIABLE /* 1857 */:
                                    str = "COMMAND_SET_DEBUG_VARIABLE";
                                    break;
                                case GAIA.COMMAND_DELETE_PDL /* 1872 */:
                                    str = "COMMAND_DELETE_PDL";
                                    break;
                                case GAIA.COMMAND_SET_BLE_CONNECTION_PARAMETERS /* 1874 */:
                                    str = "COMMAND_SET_BLE_CONNECTION_PARAMETERS";
                                    break;
                                case 4096:
                                    str = "COMMAND_IVOR_START";
                                    break;
                                case 4097:
                                    str = "COMMAND_IVOR_VOICE_DATA_REQUEST";
                                    break;
                                case 4098:
                                    str = "COMMAND_IVOR_VOICE_DATA";
                                    break;
                                case 4099:
                                    str = "COMMAND_IVOR_VOICE_END";
                                    break;
                                case 4100:
                                    str = "COMMAND_IVOR_CANCEL";
                                    break;
                                case GAIA.COMMAND_IVOR_CHECK_VERSION /* 4101 */:
                                    str = "COMMAND_IVOR_CHECK_VERSION";
                                    break;
                                case GAIA.COMMAND_IVOR_ANSWER_START /* 4102 */:
                                    str = "COMMAND_IVOR_ANSWER_START";
                                    break;
                                case GAIA.COMMAND_IVOR_ANSWER_END /* 4103 */:
                                    str = "COMMAND_IVOR_ANSWER_END";
                                    break;
                                case GAIA.COMMAND_IVOR_PING /* 4336 */:
                                    str = "COMMAND_IVOR_PING";
                                    break;
                                case GAIA.COMMAND_REGISTER_NOTIFICATION /* 16385 */:
                                    str = "COMMAND_REGISTER_NOTIFICATION";
                                    break;
                                case 16386:
                                    str = "COMMAND_CANCEL_NOTIFICATION";
                                    break;
                                case GAIA.COMMAND_EVENT_NOTIFICATION /* 16387 */:
                                    str = "COMMAND_EVENT_NOTIFICATION";
                                    break;
                                case GAIA.COMMAND_GET_NOTIFICATION /* 16513 */:
                                    str = "COMMAND_GET_NOTIFICATION";
                                    break;
                                default:
                                    switch (i) {
                                        case GAIA.COMMAND_GET_CONFIGURATION_VERSION /* 384 */:
                                            str = "COMMAND_GET_CONFIGURATION_VERSION";
                                            break;
                                        case GAIA.COMMAND_GET_LED_CONFIGURATION /* 385 */:
                                            str = "COMMAND_GET_LED_CONFIGURATION";
                                            break;
                                        case GAIA.COMMAND_GET_TONE_CONFIGURATION /* 386 */:
                                            str = "COMMAND_GET_TONE_CONFIGURATION";
                                            break;
                                        case GAIA.COMMAND_GET_DEFAULT_VOLUME /* 387 */:
                                            str = "COMMAND_GET_DEFAULT_VOLUME";
                                            break;
                                        case GAIA.COMMAND_GET_CONFIGURATION_ID /* 388 */:
                                            str = "COMMAND_GET_CONFIGURATION_ID(deprecated)";
                                            break;
                                        case GAIA.COMMAND_GET_VIBRATOR_CONFIGURATION /* 389 */:
                                            str = "COMMAND_GET_VIBRATOR_CONFIGURATION";
                                            break;
                                        case GAIA.COMMAND_GET_VOICE_PROMPT_CONFIGURATION /* 390 */:
                                            str = "COMMAND_GET_VOICE_PROMPT_CONFIGURATION";
                                            break;
                                        case GAIA.COMMAND_GET_FEATURE_CONFIGURATION /* 391 */:
                                            str = "COMMAND_GET_FEATURE_CONFIGURATION";
                                            break;
                                        case GAIA.COMMAND_GET_USER_EVENT_CONFIGURATION /* 392 */:
                                            str = "COMMAND_GET_USER_EVENT_CONFIGURATION";
                                            break;
                                        case GAIA.COMMAND_GET_TIMER_CONFIGURATION /* 393 */:
                                            str = "COMMAND_GET_TIMER_CONFIGURATION";
                                            break;
                                        case GAIA.COMMAND_GET_AUDIO_GAIN_CONFIGURATION /* 394 */:
                                            str = "COMMAND_GET_AUDIO_GAIN_CONFIGURATION";
                                            break;
                                        case GAIA.COMMAND_GET_VOLUME_CONFIGURATION /* 395 */:
                                            str = "COMMAND_GET_VOLUME_CONFIGURATION";
                                            break;
                                        case GAIA.COMMAND_GET_POWER_CONFIGURATION /* 396 */:
                                            str = "COMMAND_GET_POWER_CONFIGURATION";
                                            break;
                                        default:
                                            switch (i) {
                                                case GAIA.COMMAND_GET_USER_TONE_CONFIGURATION /* 398 */:
                                                    str = "COMMAND_GET_USER_TONE_CONFIGURATION";
                                                    break;
                                                case GAIA.COMMAND_GET_DEVICE_NAME /* 399 */:
                                                    str = "COMMAND_GET_DEVICE_NAME";
                                                    break;
                                                case GAIA.COMMAND_GET_WLAN_CREDENTIALS /* 400 */:
                                                    str = "COMMAND_GET_WLAN_CREDENTIALS";
                                                    break;
                                                case 401:
                                                    str = "COMMAND_GET_PEER_PERMITTED_ROUTING";
                                                    break;
                                                case 402:
                                                    str = "COMMAND_GET_PERMITTED_NEXT_AUDIO_SOURCE";
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 513:
                                                            str = "COMMAND_CHANGE_VOLUME";
                                                            break;
                                                        case GAIA.COMMAND_DEVICE_RESET /* 514 */:
                                                            str = "COMMAND_DEVICE_RESET";
                                                            break;
                                                        case GAIA.COMMAND_SET_PIO_CONTROL /* 515 */:
                                                            str = "COMMAND_SET_PIO_CONTROL";
                                                            break;
                                                        case GAIA.COMMAND_SET_POWER_STATE /* 516 */:
                                                            str = "COMMAND_SET_POWER_STATE";
                                                            break;
                                                        case GAIA.COMMAND_SET_VOLUME_ORIENTATION /* 517 */:
                                                            str = "COMMAND_SET_VOLUME_ORIENTATION";
                                                            break;
                                                        case GAIA.COMMAND_SET_VIBRATOR_CONTROL /* 518 */:
                                                            str = "COMMAND_SET_VIBRATOR_CONTROL";
                                                            break;
                                                        case GAIA.COMMAND_SET_LED_CONTROL /* 519 */:
                                                            str = "COMMAND_SET_LED_CONTROL";
                                                            break;
                                                        case GAIA.COMMAND_FM_CONTROL /* 520 */:
                                                            str = "COMMAND_FM_CONTROL";
                                                            break;
                                                        case GAIA.COMMAND_PLAY_TONE /* 521 */:
                                                            str = "COMMAND_PLAY_TONE";
                                                            break;
                                                        case GAIA.COMMAND_SET_VOICE_PROMPT_CONTROL /* 522 */:
                                                            str = "COMMAND_SET_VOICE_PROMPT_CONTROL";
                                                            break;
                                                        case GAIA.COMMAND_CHANGE_AUDIO_PROMPT_LANGUAGE /* 523 */:
                                                            str = "COMMAND_CHANGE_AUDIO_PROMPT_LANGUAGE";
                                                            break;
                                                        case GAIA.COMMAND_SET_SPEECH_RECOGNITION_CONTROL /* 524 */:
                                                            str = "COMMAND_SET_SPEECH_RECOGNITION_CONTROL";
                                                            break;
                                                        case GAIA.COMMAND_ALERT_LEDS /* 525 */:
                                                            str = "COMMAND_ALERT_LEDS";
                                                            break;
                                                        case GAIA.COMMAND_ALERT_TONE /* 526 */:
                                                            str = "COMMAND_ALERT_TONE";
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case GAIA.COMMAND_TWS_PEER_START_ADVERTISING /* 554 */:
                                                                    str = "COMMAND_TWS_PEER_START_ADVERTISING";
                                                                    break;
                                                                case GAIA.COMMAND_FIND_MY_REMOTE /* 555 */:
                                                                    str = "COMMAND_FIND_MY_REMOTE";
                                                                    break;
                                                                case GAIA.COMMAND_SET_SUPPORTED_FEATURES /* 556 */:
                                                                    str = "COMMAND_SET_SUPPORTED_FEATURES";
                                                                    break;
                                                                case GAIA.COMMAND_DISCONNECT /* 557 */:
                                                                    str = "COMMAND_DISCONNECT";
                                                                    break;
                                                                default:
                                                                    switch (i) {
                                                                        case GAIA.COMMAND_GET_BOOT_MODE /* 642 */:
                                                                            str = "COMMAND_GET_BOOT_MODE";
                                                                            break;
                                                                        case GAIA.COMMAND_GET_PIO_CONTROL /* 643 */:
                                                                            str = "COMMAND_GET_PIO_CONTROL";
                                                                            break;
                                                                        case GAIA.COMMAND_GET_POWER_STATE /* 644 */:
                                                                            str = "COMMAND_GET_POWER_STATE";
                                                                            break;
                                                                        case GAIA.COMMAND_GET_VOLUME_ORIENTATION /* 645 */:
                                                                            str = "COMMAND_GET_VOLUME_ORIENTATION";
                                                                            break;
                                                                        case GAIA.COMMAND_GET_VIBRATOR_CONTROL /* 646 */:
                                                                            str = "COMMAND_GET_VIBRATOR_CONTROL";
                                                                            break;
                                                                        case GAIA.COMMAND_GET_LED_CONTROL /* 647 */:
                                                                            str = "COMMAND_GET_LED_CONTROL";
                                                                            break;
                                                                        default:
                                                                            switch (i) {
                                                                                case GAIA.COMMAND_GET_EQ_CONTROL /* 660 */:
                                                                                    str = "COMMAND_GET_EQ_CONTROL";
                                                                                    break;
                                                                                case GAIA.COMMAND_GET_BASS_BOOST_CONTROL /* 661 */:
                                                                                    str = "COMMAND_GET_BASS_BOOST_CONTROL";
                                                                                    break;
                                                                                case GAIA.COMMAND_GET_3D_ENHANCEMENT_CONTROL /* 662 */:
                                                                                    str = "COMMAND_GET_3D_ENHANCEMENT_CONTROL";
                                                                                    break;
                                                                                default:
                                                                                    str = "UNKNOWN";
                                                                                    break;
                                                                            }
                                                                    }
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
        } else {
            str = "COMMAND_GET_DFU_PARTITION";
        }
        return getHexadecimalStringFromInt(i) + " " + str;
    }
}
