package com.aidem.cn;

import com.aidem.cn.config.MqttConfig;
import com.aidem.cn.dto.HisDataOuterClass;
import com.aidem.cn.dto.HisHealthData;
import com.aidem.cn.dto.RealtimeData;
import com.aidem.cn.mqttProcess.MqttProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author aidem
 * @date 2021-01-05
 * @description code
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MainMultTest {

    @Autowired
    private MqttProducer mqttProducer;
    @Autowired
    private MqttConfig mqttConfig;

    /**
     * 模拟固件发送的 indexTable 数据
     */
    @Test
    public void test() {
        // 前15位 设备号
        String deviceId = "352099001761481";
        byte[] byteone = deviceId.getBytes();
        // 固定DT
        byte[] bytetwo = new byte[]{68, 84};
        // 构造消息体
        HisDataOuterClass.HisNotification.Builder builder = HisDataOuterClass.HisNotification.newBuilder();
        HisDataOuterClass.HisIndexTable.Builder indexBuilder = HisDataOuterClass.HisIndexTable.newBuilder();
        indexBuilder.addIndex(buildIndex(0, 13, 1625279039));
        indexBuilder.addIndex(buildIndex(0, 12, 1609915173));
        builder.setIndexTable(indexBuilder);
        builder.setType(HisDataOuterClass.HisDataType.HEALTH_DATA);
        // indexTable
        byte[] bytethree = builder.build().toByteArray();
        // len + crt + opt
        int length = bytethree.length;
        byte[] bytefore = new byte[]{(byte) length, 0, 105, -82, -128, 0};
        byte[] resultBytes = doConcat(doConcat(doConcat(byteone, bytetwo), bytefore), bytethree);
        mqttProducer.publish(mqttConfig.getUploadTopic(), null, resultBytes);
    }

    /**
     * 构造index
     *
     * @param start
     * @param end
     * @param second
     * @return
     */
    private HisDataOuterClass.HisIndex.Builder buildIndex(int start, int end, int second) {
        HisDataOuterClass.HisIndex.Builder builder1 = HisDataOuterClass.HisIndex.newBuilder();
        builder1.setStartSeq(start);
        builder1.setEndSeq(end);
        RealtimeData.RtData.Builder builder2 = RealtimeData.RtData.newBuilder();
        RealtimeData.RtTime.Builder builder3 = RealtimeData.RtTime.newBuilder();
        builder3.setSeconds(second);
        builder2.setTime(builder3);
        builder1.setTime(builder2.getTime());
        return builder1;
    }

// ====================================================================================================

    /**
     * 模拟固件发送的health indexTable 数据
     */
    @Test
    public void sendHealthIndextable() {
//        String deviceId = "352099001761491";
        String deviceId = "352099001761565";
        byte[] byteone = deviceId.getBytes();
        // 固定DT
        byte[] bytetwo = new byte[]{68, 84};
        HisDataOuterClass.HisNotification.Builder hisData = HisDataOuterClass.HisNotification.newBuilder();
        HisDataOuterClass.HisIndexTable.Builder hisindexTable = HisDataOuterClass.HisIndexTable.newBuilder();
        HisDataOuterClass.HisIndex.Builder hisIndex = HisDataOuterClass.HisIndex.newBuilder();
        RealtimeData.RtTime.Builder rttime = RealtimeData.RtTime.newBuilder();
        rttime.setSeconds(1625452671);
        hisIndex.setTime(rttime);
        hisIndex.setStartSeq(0);
        hisIndex.setEndSeq(1);
        hisindexTable.addIndex(hisIndex);
        hisData.setType(HisDataOuterClass.HisDataType.HEALTH_DATA);
        hisData.setIndexTable(hisindexTable);
        // details
        byte[] bytethree = hisData.build().toByteArray();
        // len + crt + opt
        int length = bytethree.length;
        byte[] bytefore = new byte[]{(byte) length, 0, 105, -82, -128, 0};
        byte[] resultBytes = doConcat(doConcat(doConcat(byteone, bytetwo), bytefore), bytethree);
        mqttProducer.publish(mqttConfig.getUploadTopic(), null, resultBytes);
    }

    /**
     * 模拟固件发送的 health details 数据
     */
    @Test
    public void sendHealthDetails() {
        // 前15位 设备号
        String deviceId = "352099001761565";
        byte[] byteone = deviceId.getBytes();
        // 固定DT
        byte[] bytetwo = new byte[]{68, 84};
        // 构造消息体
        HisDataOuterClass.HisNotification.Builder builder = HisDataOuterClass.HisNotification.newBuilder();
        HisDataOuterClass.HisData.Builder builder1 = HisDataOuterClass.HisData.newBuilder();
        // ==================================== seq ：0 ====================================
        // ---------------- HisDataHealth --------------
        HisHealthData.HisDataHealth.Builder builder2 = HisHealthData.HisDataHealth.newBuilder();
        RealtimeData.DateTime.Builder builder3 = RealtimeData.DateTime.newBuilder();
        RealtimeData.RtTime.Builder builder4 = RealtimeData.RtTime.newBuilder();
        builder4.setSeconds(1625452671);
        builder3.setDateTime(builder4);
        builder3.setTimeZone(0);
        // timeStamp
        builder2.setTimeStamp(builder3);
        // HisHealthSleep - sleepData
        HisHealthData.HisHealthSleep.Builder builder5 = HisHealthData.HisHealthSleep.newBuilder();
        builder5.addSleepData(10);
        builder5.setShutDown(true);
        builder5.setCharge(true);
        builder2.setSleepData(builder5);
        //  HisHealthPedo - pedoData
        HisHealthData.HisHealthPedo.Builder builder6 = HisHealthData.HisHealthPedo.newBuilder();
        builder6.setType(0);
        builder6.setState(1);
        builder6.setCalorie(2);
        builder6.setStep(3);
        builder6.setDistance(4);
        builder2.setPedoData(builder6);
        // HisHealthHr - hr_data
        HisHealthData.HisHealthHr.Builder builder7 = HisHealthData.HisHealthHr.newBuilder();
        builder7.setMinBpm(10);
        builder7.setMaxBpm(30);
        builder7.setAvgBpm(20);
        builder2.setHrData(builder7);
        // HisHealthHrv - hrv_data
        HisHealthData.HisHealthHrv.Builder builder8 = HisHealthData.HisHealthHrv.newBuilder();
        builder8.setSDNN(123.11f);
        builder8.setRMSSD(123.12f);
        builder8.setPNN50(123.13f);
        builder8.setMEAN(123.14f);
        builder8.setFatigue(123.15f);
        builder2.setHrvData(builder8);
        // HisHealthBp - bp_data
        HisHealthData.HisHealthBp.Builder builder9 = HisHealthData.HisHealthBp.newBuilder();
        builder9.setSbp(15);
        builder9.setDbp(156);
        builder9.setTime(1561);
        builder2.setBpData(builder9);
        // HisHealthAf - af_data
        HisHealthData.HisHealthAf.Builder builder10 = HisHealthData.HisHealthAf.newBuilder();
        builder10.setAf(156556);
        builder10.setManual(true);
        builder2.setAfData(builder10);
        // HisHealthMdt - mdt_data
        HisHealthData.HisHealthMdt.Builder builder11 = HisHealthData.HisHealthMdt.newBuilder();
        builder11.setSDNN(516.1f);
        builder11.setRMSSD(516.2f);
        builder11.setPNN50(515.1f);
        builder11.setMEAN(5126.1f);
        builder11.setRESULT(51226.1f);
        builder11.setRELAX(5125.15f);
        builder11.setStatus(HisHealthData.HisMdtState.RELAX);
        builder2.setMdtData(builder11);
        // HisHealthMood - mood_data
        HisHealthData.HisHealthMood.Builder builder12 = HisHealthData.HisHealthMood.newBuilder();
        builder12.setMoodLevel(15656);
        builder2.setMoodData(builder12);
        // HisHealthBreath - breath_data
        HisHealthData.HisHealthBreath.Builder builder13 = HisHealthData.HisHealthBreath.newBuilder();
        builder13.setBreathrate(5513);
        builder2.setBreathData(builder13);
        // HisHealthBioz - bioz_data
        HisHealthData.HisHealthBioz.Builder builder14 = HisHealthData.HisHealthBioz.newBuilder();
        builder14.setR(55);
        builder14.setFat(56);
        builder14.setBmi(57);
        builder14.setType(58);
        builder2.setBiozData(builder14);
        // HisHealthBOxy   bxoy_data
        HisHealthData.HisHealthBOxy.Builder builder15 = HisHealthData.HisHealthBOxy.newBuilder();
        builder15.setMinOxy(5513);
        builder15.setMaxOxy(5518);
        //  todo ?  agv?
        builder15.setAgvOxy(5519);
        builder2.setBxoyData(builder15);
        // HisHealthTemp  temperature_data
        HisHealthData.HisHealthTemp.Builder builder16 = HisHealthData.HisHealthTemp.newBuilder();
        builder16.setType(HisHealthData.TPAMeasureType.TPA_MEASURE_TYPE_AUTO);
        builder16.setEviBody(556);
        builder16.setEstiArm(849);
        builder2.setTemperatureData(builder16);
        builder1.setSeq(0);
        builder1.setHealth(builder2.build());
        builder.setType(HisDataOuterClass.HisDataType.HEALTH_DATA);
        builder.setHisData(builder1);
        // details
        byte[] bytethree = builder.build().toByteArray();
        // len + crt + opt
        int length = bytethree.length;
        byte[] bytefore = new byte[]{(byte) length, 0, 105, -82, -128, 0};
        byte[] resultBytes = doConcat(doConcat(doConcat(byteone, bytetwo), bytefore), bytethree);
        mqttProducer.publish(mqttConfig.getUploadTopic(), null, resultBytes);
    }

    /**
     * 合并两个数组的方法
     */
    private byte[] doConcat(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

}
