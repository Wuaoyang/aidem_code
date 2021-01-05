package com.aidem.cn;

import com.aidem.cn.config.MqttConfig;
import com.aidem.cn.dto.HisDataOuterClass;
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
public class MainTest {

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
        HisDataOuterClass.HisIndex.Builder builder1 = HisDataOuterClass.HisIndex.newBuilder();
        builder1.setStartSeq(0);
        builder1.setEndSeq(12);
        RealtimeData.RtData.Builder builder2 = RealtimeData.RtData.newBuilder();
        RealtimeData.RtTime.Builder builder3 = RealtimeData.RtTime.newBuilder();
        builder3.setSeconds(1609828773);
        builder2.setTime(builder3);
        builder1.setTime(builder2.getTime());
        HisDataOuterClass.HisIndexTable.Builder indexBuilder = HisDataOuterClass.HisIndexTable.newBuilder();
        indexBuilder.addIndex(builder1);
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
     * 合并两个数组的方法
     */
    private byte[] doConcat(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

}
