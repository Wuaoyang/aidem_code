package temporary;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;

import com.iwown.ble_module.iwown.bluetooth.LFBle;
import com.iwown.lib_common.BuildConfig;

import java.util.UUID;

/**
 * 作者：hzy on 2018/1/18 14:35
 * <p>
 * 邮箱：hezhiyuan@iwown.com
 */

public class BaseActionUtils {
    /**
     * notificationMonitor路径
     */
    public static final String NotificationMonitor="com.iwown.device_module.device_message_push.NotificationMonitor";
    /**
     * protobuf发送的最大字节数
     */
    public static final String PROTOBUF_MTU_INFO = "com.iwown.device_module.PROTOBUF_MTU_INFO";

    /**
     * protobuf发送数据的间隔时间
     */
    public static final String PROTOBUF_TIME_INFO = "com.iwown.device_module.PROTOBUF_TIME_INFO";

    /**
     * protobufCYC校验
     */
    public static final String PROTOBUF_CYC_CHECK = "com.iwown.device_module.PROTOBUF_CYC_CHECK_CODE";

    public static class FilePath{
        /** 61数据ble日志 */
        public static final String Mtk_Ble_61_Data_Log_Dir= BuildConfig.ROOT_PATH+"/61_data/";
        /**61数据睡眠*/
        public static final String Mtk_Ble_61_Sleep_Dir= BuildConfig.ROOT_PATH+"/sleep/";
        /**62数据ble日志*/
        public static final String Mtk_Ble_62_Data_Log_Dir = BuildConfig.ROOT_PATH+"/62_data/";

        public static final String Mtk_Ble_68_Data_Dir = BuildConfig.ROOT_PATH+"/68_data/";

        public static final String ProtoBuf_Ble_80_Sleep_Dir = BuildConfig.ROOT_PATH+"/protobuf/sleep/";

        public static final String ProtoBuf_Ble_90_DOWNLOAD_Dir = BuildConfig.ROOT_PATH+"/protobuf/download_file/";

        public static final String ECG_Data_Path = BuildConfig.ROOT_PATH+"/ecg/";

        public static final String RRI_Data_Path = BuildConfig.ROOT_PATH+"/rri/";

        public static final String RRI_SOURCE_DATA_PATH = BuildConfig.ROOT_PATH+"/rri_source/";

        public static final String Bp_Data_Path = BuildConfig.ROOT_PATH+"/bp/";
    }

    public static class OtherSetting{
        public static final String DIAL_SHOW_TIPS = "DIAL_SHOW_TIPS";
    }

    /**
     * 升级模式下的UUID
     */

    public static final UUID UPDATE_SERVICE_MAIN_ = UUID.fromString("00001530-0000-1000-8000-00805f9b34fb"); // 新协议手环主GATT服务
    public static final UUID UPDATE_SERVICE_MAIN_DFU = UUID.fromString("0000fe59-0000-1000-8000-00805f9b34fb"); // 新协议手环主GATT服务

    public static class SharedPreferencesAction{
        //class id 手环,手表
        public static final String APP_SDK_UPDATE_Types = "APP_SDK_UPDATE_Types";
        //model_class2
        public static final String APP_SDK_UPDATE_Content = "APP_SDK_UPDATE_Content";
        //model_class update time
        public static final String APP_SDK_UPDATE_Time = "APP_SDK_UPDATE_Time";
        //用户选择的SDK版本
        public static final String User_Sdk_type = "User_Sdk_type";
        //用户选择的SDK版本
        public static final String USER_SDK_TYPE_EARPHONE = "USER_SDK_TYPE_EARPHONE";

        public static final String EARPHONE = "EARPHONE";

        // PROTOBUF == 2 手环  1 手表
        public static final String PROTOBUF = "PROTOBUF";
        //耳机
        public static final String PROTOBUF_EARPHONE = "PROTOBUF_EARPHONE";

    }

    public static class UserAction{
        //uid
        public static final String User_Uid="com.iwown.User_Uid";
        //use weight
        public static final String User_Weight="com.iwown.User_Weight";
        //target steps
        public static final String User_Target_Steps="com.iwown.User_Target_Steps";
        //用户顶部tab选择页面
        public static final String User_Page_Select="com.iwown.User_Page_Select";
        //用户设置的目标
        public static final String User_Step_Target="com.iwown.User_Step_Target";
        //用户设置卡路里目标
        public static final String User_Step_Calorie="com.iwown.User_Step_Calorie";
        //固件升级弹窗
        public static final String User_Firmware_Tag="com.iwown.User_Firmware_Tag";
        //上传历史日志时间
        public static final String User_Log_History_Time="com.iwown.User_Log_History_Time";
        //用户手机APP后台设置
        public static final String User_Phone_App_Background="com.iwown.User_Phone_App_Background";
        //用户网络请求远程设备设置时间限制
        public static final String User_Request_Remote_Setting_Time="com.iwown.User_Request_Remote_Setting_Time";
        //用户请求网络升级时间限制
        public static final String Bluetooth_Check_Firmware_Update_Time="com.iwown.Bluetooth_Check_Firmware_Update_Time";

        public static final String HEALTHY_LANGUAGE="com.iwown_healthy_language";
        /**给i3hr i6s7单独使用*/
        public static final String I6S7_BRACELET_KEY="com.iwown.I6S7_BRACELET_KEY";


    }


    public static class BleAction{
        //设备名(不能清空，存当前或上次连接的设备名)
        public static final String Bluetooth_Device_Name="com.iwown.Bluetooth_Device_Name";
        //设备mac(不能清空, 存储同上)
        public static final String Bluetooth_Device_Address="com.iwown.Bluetooth_Device_Address";

        //用绑定和重连，当前连接的手环
        public static final String Bluetooth_Device_Name_Current_Device="com.iwown.Bluetooth_Device_Name_Current_Device";
        //用绑定和重连，当前连接的手环
        public static final String Bluetooth_Device_Address_Current_Device="com.iwown.Bluetooth_Device_Address_Current_Device";
        public static final String Bluetooth_Device_Alias_Current_Device="com.iwown.Bluetooth_Device_Alias_Current_Device";

        //当前绑定的耳机信息
        public static final String Bluetooth_Device_Name_Current_EARPHONE="com.iwown.Bluetooth_Device_Name_Current_EARPHONE";
        public static final String Bluetooth_Device_Address_Current_EARPHONE="com.iwown.Bluetooth_Device_Address_Current_EARPHONE";
        public static final String Bluetooth_Device_Alias_Current_EARPHONE="com.iwown.Bluetooth_Device_Alias_Current_EARPHONE";

        //发送给手环心跳的时间
        public static final String Bluetooth_Sync_Heart_Beat_Time="com.iwown.Bluetooth_Sync_Heart_Beat_Time";
        //最后绑定的那支手环，用来判断切换手环
        public static final String Bluetooth_Last_Device_Address="com.iwown.Bluetooth_Last_Device_Address";
        //埃微公司新协议和老协议区分
        public static final String Bluetooth_Protocol_Type="com.iwown.Bluetooth_Protocol_Type";

        /** 知格的model号，用于是否获取gps */



    }

    public static class FirmwareAction{
        //设备信息key
        public static final String Firmware_Information="com.iwown.Firmware_Information";
        //手环电池电量
        public static final String Firmware_Battery="com.iwown.Firmware_Battery";
        //手环支持的运动类型
        public static final String Firmware_Support_Sport="com.iwown.Firmware_Support_Sport";
        //手环设置
        public static final String Firmware_Settings_Default="com.iwown.Firmware_Settings_Default";
        //手环数据同步设置
        public static final String Firmware_Last_Sync_Setting_Time="com.iwown.Firmware_Last_Sync_Setting_Time";
        //扫描刷新界面时间
        public static final String Firmware_Scan_Refresh_Time="com.iwown.Firmware_Scan_Refresh_Time";
        //是否支持新协议，心跳协议
        public static final String Firmware_New_Protocol="com.iwown.Firmware_New_Protocol";
        //0x29数据保存
        public static final String Firmware_Curr_0x29_Data="com.iwown.Firmware_Curr_0x29_Data";
        //最后更新epo的时间
        public static final String Firmware_Last_Epo_Time="com.iwown.Firmware_Last_Epo_Time";
        //电话推送
        public static final String Firmware_Call_Message_Push="com.iwown.call_message_push";
        //消息推送
        public static final String Firmware_Sms_Message_Push="com.iwown.Firmware_Sms_Message_Push";
        //iv,mtk zg手环震动模式和次数
        public static final String Firmware_Vibration_Mode="com.iwown.Firmware_Vibration_Mode";
        //久座提醒开关key
        public static final String Firmware_Long_Sit="com.iwown.Firmware_Long_Sit";
        //添加运动相关选中集合
        public static final String Firmware_Support_Sports_Status="com.iwown.Firmware_Support_Sports_Status";
        //添加运动相关未选中集合
        public static final String Firmware_Support_Sports_Status_UnChecked="com.iwown.Firmware_Support_Sports_Status_UnChecked";
        //消息推送开关
        public static final String Firmware_Message_Push_Switch_Statue="com.iwown.Firmware_Message_Push_Switch_Statue";
        //自动心率
        public static final String Firmware_Message_Auto_Heart_Rate="com.iwown.Firmware_Message_Auto_Heart_Rate";
        //心率预警
        public static final String Firmware_Message_Auto_Heart_Guidance="com.iwown.Firmware_Message_Auto_Heart_Guidance";
        //本地其它一些小设置一起保存
        public static final String Firmware_Message_Device_Setting_Other="com.iwown.Firmware_Message_Device_Setting_Other";
        // 自拍神器
        public final static String ACTION_SELFIE_DATA = "com.kunekt.healthy.ACTION_SELFIE_DATA";
        //固件升级时服务器返回的具体
        public static final String Firmware_Update_Server_Resp="com.iwown.Firmware_Update_Server_Resp";
        //固件升级时保存的mac地址
        public static final String Firmware_Update_Device_Mac="com.iwown.Firmware_Update_Device_Mac";
        //手环偏好设置上传更新时间
        public static final String Firmware_Device_Pref_Upload_Time="com.iwown.Firmware_Device_Pref_Upload_Time";
        //是否支持08协议
        public static final String Firmware_Can_Support_08="com.iwown.Firmware_Can_Support_08";
        //保存天气
        public static final String Firmware_Weather_Int="com.iwown.Firmware_Weather_Int";
        //保存温度
        public static final String Firmware_Temperature_Int="com.iwown.Firmware_Temperature_Int";
        public static final String Firmware_pm25="com.iwown.pm25";
        //mtk 手环出厂时间
        public static final String Firmware_Factory_Version_Time="com.iwown.Firmware_Factory_Version_Time";
        //mtk 手环出厂硬件信息
        public static final String Firmware_Factory_Version_Info="com.iwown.Firmware_Factory_Version_Info";
        //给更新天气时间
        public static final String Firmware_Weather_Update="com.iwown.Firmware_Weather_Update";
        //下发所以指令控制
        public static final String Firmware_Command_To_Device="com.iwown.Firmware_Command_To_Device";
        //消息推送触发重连时间限制
        public static final String Firmware_Notification_Reconnect_Time="com.iwown.Firmware_Notification_Reconnect_Time";
        //class model 请求国家
        public static final String Firmware_class_model_country="com.iwown.class_model_country";

        //正在请求固件还未返回
        public static final String Firmware_post_version="com.iwown.Firmware_post_version";
        //最后更新agps的时间
        public static final String Firmware_Last_agps_Time="com.iwown.Firmware_Last_agps_Time";
        /**解绑绑定手环按钮****/
        public static final String UNBind_Device_Button="com.iwown.unBind_Device_Button";
        /**健康配置血压****/
        public static final String HealthConfig_blood="com.iwown.healthConfig_blood";
        /**记录下获取model的值**/
        public static final String GetModel_TimeOver="com.iwown.GetModel_TimeOver";
        /**记录下获取电池timeout时间的值**/
        public static final String GetBattery_TimeOut="com.iwown.GetBattery_TimeOut";
    }

    public static class EarphoneSetting{
        public static final String EAR_PHONE_SETTING ="EAR_PHONE_SETTING";
        public static final int BAND_TYPE = 0;
        public static final int EAR_PHONE_TYPR = 1;

    }

    public static class UrlTag{
        public static final String DEVICE_CONFIG = "DEVICE_CONFIG";
    }

    public static class EarphoneTab{
        public static final String EarphoneSelectTab ="EarphoneSelectTab";
    }

    public static class WifiScaleAction{
        //体重秤设置
        public final static String Wifi_Scale_Setting_Action = "com.kunekt.healthy.Wifi_Scale_Setting_Action";
    }

    public static class KeyCodeAction{
        //自拍
        public static final String Action_Seleie_Data="com.iwown.Action_Seleie_Data";
        public static final String Action_Phone_Statue_Out="com.iwown.Action_Phone_Statue_Out";
        //静音
        public static final String Action_Phone_Mute="com.iwown.Action_Phone_Mute";
        //响铃
        public static final String Action_Phone_Ring="com.iwown.Action_Phone_Ring";
        //播放
        public static final String Action_Voice_Start="com.iwown.Action_voice_start";
    }

    public static class SDK{
        //埃微
        public static final int  Sdk_Type_Iv=1;
        //知格
        public static final int  Sdk_Type_Zg=3;
        //mtk
        public static final int  Sdk_Type_Mtk=2;
        //protobuf新协议
        public static final int Sdk_Type_ProtoBuf =4;
    }

    public static class DeviceType{
        //action
        public static final String  TYPE_ACTION="TYPE_ACTION";
        //手环
        public static final int  TYPE_B=1;
        //手表
        public static final int  TYPE_W=2;
    }

    //日志上传
    public class LogKeyStr {
        //日志名称关键字
        public static final String BLE_LOG_KEY = "operate";
        public static final String NOTIFY_LOG_KEY = "notify";
        public static final String WRITE_LOG_KEY = "write";
    }

    /**
     * 心率预警区间
     */
    public static class HEART_REMIND_TYPE {
        public static final int NO_SELECTED = -1;
        public static final int WARM_UP = 0;
        public static final int FAT_BURNNING = 1;
        public static final int AEROBIC = 2;
        public static final int ANAEROBIC = 3;

    }

    public static class DEV_PLATFORM {
//        1-TI ,2-nordic平台 ,3-dialog平台

        public static final int TI = 1;
        public static final int NORDIC = 2;
        public static final int DIALOG = 3;
        public static final int MTK = 4;
    }

    /***
     * 语言代号
     */
    public class LanguageType{
//        英中意日法 德葡西俄韩 阿越泰土瑞 简(bit0：英语1：中文简体，2：意大利语，3：日语，
// 4：法语，5：德语，6：葡萄牙语，7：西班牙语，8：俄语，9：韩语，10：阿拉伯语，11：越南语，
// 12：泰语，13：土尔其语，14：瑞典语，18：简化版 示例:101(2)表示有英语和意大利语)

        //发给手环
        public final static int ENGLISH=0;
        public final static int CHINESE=1;
        public final static int ITALIAN=2;
        public final static int JAPAN=3;
        public final static int FRENCH=4;
        public final static int GERMAN=5;
        public final static int PORTUGUESE=6;
        public final static int SPANISH=7;
        public final static int RUSSIAN=8;
        public final static int KOREAN=9;
        public final static int ARABIC=10;
        public final static int VIETNAMESE=11;

        public final static int Poland=15;
        public final static int Romania=16;

        public final static int Sweden=14;

        public final static int Thai=12;
        public final static int Turkey=13;
        public final static int BASIC=255;
        public final static int Chinese_Tc=22;


        //发给手环
        public final static int ENGLISH_p1=0;
        public final static int CHINESE_p1=1;
        public final static int ITALIAN_p1=2;
        public final static int JAPAN_p1=3;
        public final static int FRENCH_p1=4;
        public final static int GERMAN_p1=5;
        public final static int PORTUGUESE_p1=6;
        public final static int SPANISH_p1=7;
        public final static int RUSSIAN_p1=8;
        public final static int KOREAN_p1=9;
        public final static int ARABIC_p1=10;
        public final static int VIETNAMESE_p1=11;
        public final static int Poland_p1=12;
        public final static int Romania_p1=13;
        public final static int Sweden_p1=14;




    }

    /**
     * 动态mode适配
     */
    public static class SETTING_INDEXS {

        public static final int All_Of_Them=9999;
//        ype :0-led 1-手势识别 2-公英制单位 3-24小时制度 4-自动睡眠 5-背光时间 6-黑白背景切换 7-语言 8-断连提醒 9-日期格式 10-翻腕亮屏时间 11-自动心率 12-震动设置 13-心率开关标志 14-天气 15-闹钟 16-日程 17-添加运动 18-智拍 19-久坐 20-固件升级 21-心率功能
//        valueInt : 1 开| 2关，1-英制，2-国际制, 1-12小时|2-24小时，2-黑|1-白 , bit0-bitx==》英中意日法 德葡西俄韩 阿越简(bit0：英语1：中文简体，2：意大利语，3：日语，4：法语，5：德语，6：葡萄牙语，7：西班牙语，8：俄语，9：韩语，10：阿拉伯语，11：越南语，18：简化版 示例:101(2)表示有英语和意大利语)
//        valueStr : 字符串型默认值,用于数字很难处理的情况，比方说时间段（18:00-06:00）

        public static final int LED = 0;
        public static final int GESTURE = 1;
        public static final int UNIT_MEASUREMENT = 2;
        public static final int HOUR_24 = 3;
        public static final int AUTO_SLEEP = 4;
        public static final int BACK_LIGHT_TIME = 5;
        public static final int BACK_COLOR = 6;
        public static final int LANGUAGE = 7;
        public static final int DISCONNECT_TIP = 8;
        public static final int DATE_FORMAT = 9;
        public static final int WRIST_TURNING = 10;
        public static final int AUTO_HEART_RATE = 11;
        public static final int VIBRATION = 12;
        public static final int HEART_GUIDE = 13;
        public static final int WEATHER = 14;
        public static final int ALARM = 15;
        public static final int SCHEDULE = 16;
        public static final int ADD_SPORT = 17;
        public static final int CAMERA = 18;
        public static final int SEDENTARY = 19;
        public static final int FW_UPGRADE = 20;
        public static final int SUPPORT_HEART = 21;
        public static final int SHOULD_READ_0X18 = 22;
        public static final int SUPPORT_SOPORT_COUNT = 23;
        public static final int SMART_TRACK = 24;
        public static final int Call_PUSH_SWITCH = 25;
        public static final int AUTO_HEART_SWITCH_TIME = 26;
        public static final int WEARING_MANNER = 27;
        public static final int HRV_P1 = 28;
        public static final int Message_Push_Switch = 30;

        public static final int Double_Touch_Switch = 32;
        public static final int Wearable_Recognize = 33;
        public static final int Standard_Heart = 34;
        public static final int Welcome_Page = 35;
        public static final int Device_Guide = 36;
        public static final int HAS_AGPS = 37;

        public static final int BLOOD_PRESSURE = 38;
        /**勿扰*/
        public static final int NO_DISTURB = 39;

        public static final int SUPPORT_ECG = 40;

        /**24小时房颤*/
        public static final int AF_24 = 41;
        /**血氧*/
        public static final int BLOOD_OXYGEN = 43;
        /**设置表盘*/
        public static final int DIAL_SET = 44;
        /**压力值*/
        public static final int STRESS = 45;
        /**翻腕灵敏度*/
        public static final int GESTURE_SENSIBILITY = 46;
        /**亮屏时间*/
        public static final int BRIGHT_SCREEN_LIGHT = 48;
        /**体温*/
        public static final int BRIGHT_TEMBERATURE = 49;
        /** APG血管健康 */
        public static final int APG = 50;
        /** 步数阈值(0-5) */
        public static final int STEP_LEVEL = 51;
    }

    /**
     * 消息推送
     */
    public class PushAppPackName {
        public static final String FACEBOOK  = "com.facebook.orca";
        public static final String TWITTER  = "com.twitter.android";
        public static final String WHATSAPP  = "com.whatsapp";
        public static final String SKYPE1  = "com.skype.rover";
        public static final String SKYPE2  = "com.skype.raider";

        public static final String LINE  = "jp.naver.line.android";
        public static final String KAKAOTALK  = "com.kakao.talk";
        public static final String GMAIL  = "com.google.android.gm";
        public static final String QQ  = "com.tencent.mobileqq";
        public static final String WECHAT  = "com.tencent.mm";
        public static final String SINA = "com.sina.weibo";

        /**
         * 推送默认开关
         */
        public static final String Push_Default = "com.iwown.MsgPushActivity.Push_Default";
        /**
         * 推送第一次
         */
        public static final String PUSH_FIRST = "com.iwown.MsgPushActivity.PUSH_FIRST";
    }

    /**
     * app bluetooth operation
     */
    public static final String ON_SCAN_RESULT = "com.iwown.app.ON_SCAN_RESULT";
    public static final String ON_DATA_ARRIVED = "com.iwown.app.ON_DATA_ARRIVED";
    public static final String ON_BLUETOOTH_INIT = "com.iwown.app.ON_BLUETOOTH_INIT";
    public static final String ON_CONNECT_STATUE = "com.iwown.app.ON_CONNECT_STATUE";
    public static final String ON_DISCOVER_SERVICE = "com.iwown.app.ON_DISCOVER_SERVICE";
    public static final String ON_DISCOVER_CHARACTER = "com.iwown.app.ON_DISCOVER_CHARACTER";
    public static final String ON_COMMON_SEND = "com.iwown.app.ON_COMMON_SEND";
    public static final String ON_CHARACTERISTIC_CHANGE = "com.iwown.app.ON_CHARACTERISTIC_CHANGE";
    public static final String ON_BLUETOOTH_ERROR = "com.iwown.app.ON_BLUETOOTH_ERROR";
    public static final String BLE_SDK_TYPE = "com.iwown.app.BLE_SDK_TYPE";
    public static final String BLE_DATA_TYPE = "com.iwown.app.BLE_DATA_TYPE";
    public static final String BLE_ARRIVED_DATA = "com.iwown.app.BLE_ARRIVED_DATA";
    public static final String BLE_SCAN_RESULT_DEVICE = "com.iwown.app.BLE_SCAN_RESULT_DEVICE";
    public static final String BLE_CONNECT_STATUE = "com.iwown.app.BLE_CONNECT_STAUE";
    public static final String BLE_SERVICE_UUID = "com.iwown.app.BLE_SERVICE_UUID";
    public static final String BLE_CHARACTER_UUID = "com.iwown.app.CHARACTER_UUID";
    public static final String BLE_COMMON_SEND = "com.iwown.app.BLE_COMMON_SEND";
    public static final String BLE_BLUETOOTH_ADDRESS = "com.iwown.app.BLE_BLUETOOTH_ADDRESS";
    public static final String BLE_PRE_CONNECT= "com.iwown.app.BLE_PRE_CONEECT";
    public static final String BLE_NO_CALLBACK= "com.iwown.app.BLE_NO_CALLBACK";


    public static IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ON_SCAN_RESULT);
        intentFilter.addAction(ON_DATA_ARRIVED);
        intentFilter.addAction(ON_BLUETOOTH_INIT);
        intentFilter.addAction(ON_CONNECT_STATUE);
        intentFilter.addAction(ON_DISCOVER_SERVICE);
        intentFilter.addAction(ON_DISCOVER_CHARACTER);
        intentFilter.addAction(ON_COMMON_SEND);
        intentFilter.addAction(ON_CHARACTERISTIC_CHANGE);
        intentFilter.addAction(ON_BLUETOOTH_ERROR);
        intentFilter.addAction(BLE_DATA_TYPE);
        intentFilter.addAction(BLE_ARRIVED_DATA);
        intentFilter.addAction(BLE_SCAN_RESULT_DEVICE);
        intentFilter.addAction(BLE_CONNECT_STATUE);
        intentFilter.addAction(BLE_SERVICE_UUID);
        intentFilter.addAction(BLE_CHARACTER_UUID);
        intentFilter.addAction(BLE_COMMON_SEND);
        intentFilter.addAction(BLE_SDK_TYPE);
        intentFilter.addAction(BLE_BLUETOOTH_ADDRESS);
        intentFilter.addAction(BLE_PRE_CONNECT);
        intentFilter.addAction(BLE_NO_CALLBACK);

        return intentFilter;
    }

    public static IntentFilter getLFIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LFBle.ACTION_LF_CONNECT);
        intentFilter.addAction(LFBle.ACTION_LF_WEIGHT);
        intentFilter.addAction(LFBle.ACTION_LF_PRO_WEIGHT);
        return intentFilter;
    }

    public static IntentFilter getDeviceIntentFilter(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        return intentFilter;
    }

    public static IntentFilter getCallIntentFilter(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        intentFilter.addAction(KeyCodeAction.Action_Phone_Statue_Out);
        intentFilter.addAction("com.iwown.ACTION_PHONE_NORINGING");
        intentFilter.addAction("com.iwown.ACTION_PHONE_ANSWER_RING");
        return intentFilter;
    }
}
