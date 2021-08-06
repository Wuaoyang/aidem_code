package temporary;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

public class ProtoBuf_80_data extends DataSupport {

    private long uid;
    /**
     * data
     */
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    /**
     * 时间戳(单位s)
     */
    private int time;
    /**
     * 排序用的
     */
    private int seq;
    /**
     * 设备名
     */
    private String data_from;
    /**
     * 睡眠数据
     */
    private String sleepData;
    private boolean charge;
    private boolean shutdown;
    /**
     * 健康
     */
    private int type;
    private int state;
    private float calorie;
    private int step;
    private float distance;
    /**
     * 心率
     */
    private int min_bpm;
    private int max_bpm;
    private int avg_bpm;

    /**
     * 血氧
     */
    private int avg_blood_oxygen;

    /**
     * 疲劳度
     */
    private float SDNN;
    private float RMSSD;
    private float PNN50;
    private float MEAN;
    private float fatigue;


    /**
     * 血压
     *
     */
    private int sbp;
    private int dbp;
    private int bpTime;//bp数据

    /***
     * 体温数据
     */
    /**环境温度**/
    private float envTemperature;
    /**体表温度**/
    private float bodyTemperature;
    /**预估温度**/
    private float estimateTemperature;
    /**腋下温度**/
    private float armpitTemperature;
    /**
     * 类型**/
    private int temperatureType;


    /**
     * 心情等级
     */
    private int moodLevel;

    /** 不进入数据库,仅用于计算*/
    @Column(ignore = true)
    public int endStep=0;
    @Column(ignore = true)
    public float endDis=0;
    @Column(ignore = true)
    public float endClo=0;
    @Column(ignore = true)
    public int endMin=0;

    public int getAvg_blood_oxygen() {
        return avg_blood_oxygen;
    }

    public void setAvg_blood_oxygen(int avg_blood_oxygen) {
        this.avg_blood_oxygen = avg_blood_oxygen;
    }

    /** 不进入数据库,仅用于计算*/





    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getCalorie() {
        return calorie;
    }

    public void setCalorie(float calorie) {
        this.calorie = calorie;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getMin_bpm() {
        return min_bpm;
    }

    public void setMin_bpm(int min_bpm) {
        this.min_bpm = min_bpm;
    }

    public int getMax_bpm() {
        return max_bpm;
    }

    public void setMax_bpm(int max_bpm) {
        this.max_bpm = max_bpm;
    }

    public int getAvg_bpm() {
        return avg_bpm;
    }

    public void setAvg_bpm(int avg_bpm) {
        this.avg_bpm = avg_bpm;
    }

    public float getSDNN() {
        return SDNN;
    }

    public void setSDNN(float SDNN) {
        this.SDNN = SDNN;
    }

    public float getRMSSD() {
        return RMSSD;
    }

    public void setRMSSD(float RMSSD) {
        this.RMSSD = RMSSD;
    }

    public float getPNN50() {
        return PNN50;
    }

    public void setPNN50(float PNN50) {
        this.PNN50 = PNN50;
    }

    public float getMEAN() {
        return MEAN;
    }

    public void setMEAN(float MEAN) {
        this.MEAN = MEAN;
    }

    public float getFatigue() {
        return fatigue;
    }

    public void setFatigue(float fatigue) {
        this.fatigue = fatigue;
    }

    public String getData_from() {
        return data_from;
    }

    public void setData_from(String data_from) {
        this.data_from = data_from;
    }

    public String getSleepData() {
        return sleepData;
    }

    public void setSleepData(String sleepData) {
        this.sleepData = sleepData;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getSbp() {
        return sbp;
    }

    public void setSbp(int sbp) {
        this.sbp = sbp;
    }

    public int getDbp() {
        return dbp;
    }

    public void setDbp(int dbp) {
        this.dbp = dbp;
    }

    public int getBpTime() {
        return bpTime;
    }

    public void setBpTime(int bpTime) {
        this.bpTime = bpTime;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMoodLevel() {
        return moodLevel;
    }

    public void setMoodLevel(int moodLevel) {
        this.moodLevel = moodLevel;
    }

    public float getEnvTemperature() {
        return envTemperature;
    }

    public void setEnvTemperature(float envTemperature) {
        this.envTemperature = envTemperature;
    }

    public float getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(float bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public float getEstimateTemperature() {
        return estimateTemperature;
    }

    public void setEstimateTemperature(float estimateTemperature) {
        this.estimateTemperature = estimateTemperature;
    }

    public float getArmpitTemperature() {
        return armpitTemperature;
    }

    public void setArmpitTemperature(float armpitTemperature) {
        this.armpitTemperature = armpitTemperature;
    }

    public int getTemperatureType() {
        return temperatureType;
    }

    public void setTemperatureType(int temperatureType) {
        this.temperatureType = temperatureType;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }else if(!(obj instanceof ProtoBuf_80_data)){
            return false;
        }else{
            return this.uid==((ProtoBuf_80_data) obj).getUid() &&
                    ((ProtoBuf_80_data) obj).getData_from()!=null &&
                    ((ProtoBuf_80_data) obj).getData_from().equals(this.data_from) &&
                    ((ProtoBuf_80_data) obj).getTime() == this.time;
        }
    }
}
