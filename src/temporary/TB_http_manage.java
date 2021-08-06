package temporary;

import org.litepal.crud.DataSupport;

/**
 * Created by nokey on 2017/10/12.
 */

public class TB_http_manage extends DataSupport {
    private long uid;
    private String type;
    private String date;
    //0http操作未成功，1成功
    private int upload;
    private long up_time;
    private String data_from;
    private int year;
    private int month;
    private int day;
    private long date_time;

    public long getDate_time() {
        return date_time;
    }

    public void setDate_time(long date_time) {
        this.date_time = date_time;
    }

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

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUpload() {
        return upload;
    }

    public void setUpload(int upload) {
        this.upload = upload;
    }

    public long getUp_time() {
        return up_time;
    }

    public void setUp_time(long up_time) {
        this.up_time = up_time;
    }

    public String getData_from() {
        return data_from;
    }

    public void setData_from(String data_from) {
        this.data_from = data_from;
    }

    public long getId(){
        return getBaseObjId();
    }
}
