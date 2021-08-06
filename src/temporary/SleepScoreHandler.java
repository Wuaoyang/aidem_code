package temporary;

/**
 * Created by Daemon on 2018/3/16 11:38.
 */

public class SleepScoreHandler {

    /**
     * 睡眠得分
     * 入睡时间早于晚上00:00的计算公式：20+60*（1-|y-480|/480）+20*（1-|z-25|/25）
     * 入睡时间晚于晚上00:00的计算公式：20*（1-x/480）+60*（1-|y-480|/480）+20*（1-|z-25|/25
     * x是指距离入睡时间00:00的分钟差，y是指实际睡眠时长分钟数，z是指深睡百分比
     *
     * @param total_min      睡眠时间 包括清醒
     * @param deep_min
     * @param startTime_unix
     * @return
     */
    public static int calSleepScore(int total_min, int deep_min, long startTime_unix) {
        int score = 0;

        DateUtil dateUtil1 = new DateUtil(startTime_unix, true);
        if (dateUtil1.getHour() >= 18 && dateUtil1.getHour() <= 23) {
            int y = total_min;
            int z = (int) (1.0f * deep_min / total_min * 100);
            score = (int) (20 + 60 * (1 - Math.abs(y - 480) / 480f) + 20 * (1 - Math.abs(z - 25) / 25f));
        } else {
            //晚于0点
            DateUtil dateUtil_Z = new DateUtil(dateUtil1.getUnixTimestamp(), true);
            dateUtil_Z.setHour(0);
            dateUtil_Z.setMinute(0);
            dateUtil_Z.setSecond(0);
            long l = dateUtil1.getUnixTimestamp() - dateUtil_Z.getUnixTimestamp();
            int x = (int) (l / 60);
            int y = total_min;
            int z = (int) (1.0f * deep_min / total_min * 100);
            score = (int) (20 * (1 - x / 480) + 60 * (1 - Math.abs(y - 480) / 480f) + 20 * (1 - Math.abs(z - 25) / 25f));
        }


        if (score < 0) {
            score = 0;
        }
        return score;
    }
}
