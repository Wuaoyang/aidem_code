package com.aidem.cn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author aidem
 * @date 2021-04-15
 * @description code
 */
public class GetOffWork {

    private static final String lastTime = "2021-04-15 19:40:00";

    public static void main(String[] args) throws ParseException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse(lastTime);
        long lastLongTime = parse.getTime();
        Date date = new Date();
        long nowTime = date.getTime();
        long allTime = (lastLongTime - nowTime) / 1000;
        for (long i = 0; i < allTime; i++) {
            System.out.println("下班倒计时：" + (allTime - i) + "秒");
            Thread.sleep(1000);
        }
        System.out.println();

    }

}
