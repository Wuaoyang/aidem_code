package temporary;

import androidx.annotation.NonNull;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

public class TB_rri_index_table extends DataSupport implements Comparable<TB_rri_index_table> {
    @Column(ignore = true)
    public static final int ISOK = 1;
    @Column(ignore = true)
    public static final int ISNOTOK = 0;

    private int id;
    private long uid;
    private String dataFrom;
    private int start_seq;
    private int end_seq;
    private String date;

    private String data_ymd;

    private int syncType;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }

    public int getStart_seq() {
        return start_seq;
    }

    public void setStart_seq(int start_seq) {
        this.start_seq = start_seq;
    }

    public int getEnd_seq() {
        return end_seq;
    }

    public void setEnd_seq(int end_seq) {
        this.end_seq = end_seq;
    }


    public String getData_ymd() {
        return data_ymd;
    }

    public void setData_ymd(String data_ymd) {
        this.data_ymd = data_ymd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSyncType() {
        return syncType;
    }

    public void setSyncType(int syncType) {
        this.syncType = syncType;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(@NonNull TB_rri_index_table tb_rri_index_table) {
        if(this.start_seq > tb_rri_index_table.getStart_seq()){
            return 1;
        }
        return -1;
    }
}
