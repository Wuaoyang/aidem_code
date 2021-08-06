package temporary;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.SystemClock;
import com.iwown.app.nativeinvoke.NativeInvoker;
import com.iwown.app.nativeinvoke.SA_SleepBufInfo;
import com.iwown.app.nativeinvoke.SA_SleepDataInfo;
import com.iwown.awlog.Author;
import com.iwown.awlog.AwLog;
import com.iwown.ble_module.utils.JsonTool;
import com.iwown.data_link.af.AfDateInfo;
import com.iwown.data_link.eventbus.HealthDataEventBus;
import com.iwown.data_link.sleep_data.SleepScoreHandler;
import com.iwown.data_link.user_pre.UserConfig;
import com.iwown.device_module.common.BaseActionUtils;
import com.iwown.device_module.common.Bluetooth.receiver.iv.bean.SleepSegment;
import com.iwown.device_module.common.Bluetooth.receiver.mtk.dao.Mtk_DeviceBaseInfoSqlUtil;
import com.iwown.device_module.common.Bluetooth.sync.proto.ComplexPropertyPreFilter;
import com.iwown.device_module.common.network.NetFactory;
import com.iwown.device_module.common.network.callback.MyCallback;
import com.iwown.device_module.common.network.data.resp.UpSDFileCode;
import com.iwown.device_module.common.sql.File_protobuf_80data;
import com.iwown.device_module.common.sql.ProtoBuf_80_data;
import com.iwown.device_module.common.sql.ProtoBuf_index_80;
import com.iwown.device_module.common.sql.TB_http_manage;
import com.iwown.device_module.common.sql.TB_rri_data;
import com.iwown.device_module.common.sql.TB_rri_index_table;
import com.iwown.device_module.common.sql.sleep.TB_SLEEP_Final_DATA;
import com.iwown.device_module.common.utils.ContextUtil;
import com.iwown.device_module.common.utils.JsonUtils;
import com.iwown.device_module.common.utils.PrefUtil;
import com.iwown.device_module.device_setting.configure.WristbandModel;
import com.iwown.lib_common.date.DateUtil;
import com.iwown.lib_common.file.FileUtils;
import com.iwown.lib_common.log.L;
import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static com.iwown.lib_common.file.FileIOUtils.write2SDFromString;

/**
 * @author 睡眠数据入表
 */
public class ProtoBufSleepSqlUtils {

    private static String dataFrom = "";
    private static String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final int UPLOAD_TIME = 2 * 3600;
    private static final int DELAY = 500;


    public static void dispSleepData() {
        dataFrom = ContextUtil.getDeviceNameNoClear();
        L.file("开始计算睡眠数据", L.Type_Operate);
        List<ProtoBuf_index_80> protoBuf_index_80s = DataSupport.where("uid=? and data_from=? and indexType=0", ContextUtil.getLUID() + "", dataFrom).find(ProtoBuf_index_80.class);
        Set<SleepBean> hashSet = new HashSet<>();
        for (ProtoBuf_index_80 index_80 : protoBuf_index_80s) {
            SleepBean bean = new SleepBean(index_80.getYear(), index_80.getMonth(), index_80.getDay());
            hashSet.add(bean);
        }
        disAndWriteData(hashSet);

    }


    /**
     * 每次迭代延迟1秒
     *
     * @param hashSet
     */
    @SuppressLint("CheckResult")
    private static void disAndWriteData(Set<SleepBean> hashSet) {
        if (hashSet == null || hashSet.size() == 0) {
            return;
        }
        Observable.zip(Observable.fromIterable(hashSet), Observable.interval(500, TimeUnit.MILLISECONDS),
                (sleepBean, aLong) -> sleepBean)
                .map(sleepBean -> {
                    DateUtil dateUtil = new DateUtil(sleepBean.getYear(), sleepBean.getMonth(), sleepBean.getDay());
                    TB_SLEEP_Final_DATA tb_sleep_final_data = DataSupport.where("uid=? and data_from=? and date=?",
                            ContextUtil.getUID(), dataFrom, dateUtil.getSyyyyMMddDate())
                            .findFirst(TB_SLEEP_Final_DATA.class);
                    if (tb_sleep_final_data != null) {
                        L.file("本地睡眠信息:" + tb_sleep_final_data.toString(), L.Type_Operate);
                    } else {
                        L.file("本地睡眠信息为空 " + dataFrom, L.Type_Operate);
                    }
                    List<ProtoBuf_80_data> index80s = new ArrayList<>();
                    if (tb_sleep_final_data == null || dateUtil.isToday()) {
                        index80s = DataSupport.where("uid= ? and year=? and month=? and day=? and data_from=?",
                                ContextUtil.getLUID() + "", dateUtil.getYear() + "", dateUtil.getMonth() + "", dateUtil.getDay() + "", dataFrom).
                                order("seq asc").find(ProtoBuf_80_data.class);
                        return index80s;
                    }

                    return index80s;
                })
                .filter(index80s -> index80s != null && index80s.size() > 0)
                .map(index80s -> {
                    //解析某一天的数据成json
                    Thread.sleep(2000);
                    String s = filterJson(index80s);
                    DateUtil dateUtil = new DateUtil(index80s.get(0).getYear(), index80s.get(0).getMonth(), index80s.get(0).getDay());
                    AwLog.i(Author.YanXi, "计算" + dateUtil.getSyyyyMMddDate() + "的睡眠");
                    //writeSleep(data, s, dataFrom);
                    String[] twoDaysPath = getTwoDaysPath(dateUtil);
                    String[] pathAndName = getPathAndName(dateUtil);
                    File file = write2SDFromString(pathAndName[0], pathAndName[1], s, false);

                    /**
                     * 计算睡眠数据
                     */
                    SA_SleepBufInfo sleep = getSleep(twoDaysPath[0], twoDaysPath[1], dateUtil, dataFrom);
                    int upHour = 9;
                    if (sleep != null && sleep.outSleepTime != null) {
                        upHour = sleep.outSleepTime.hour;
                    }
                    //上传睡眠数据
//                    uploadSleepFile(UserConfig.getInstance().getNewUID(),dateUtil,dataFrom,file,upHour);
//                        SystemClock.sleep(DELAY);
                    String s1 = JsonUtils.toJson(sleep);
                    L.file(s1, L.Type_Operate);
                    AwLog.i(Author.YanXi, s1);
                    return s1;
                })
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    /**
     * @param dateUtil
     * @return string[] 0 昨天 1 今天
     */
    public static String[] getTwoDaysPath(DateUtil dateUtil) {
        String[] path = new String[2];
        DateUtil yesDateUtil = new DateUtil(dateUtil.getUnixTimestamp(), true);
        yesDateUtil.addDay(-1);
        String rootPath = BaseActionUtils.FilePath.ProtoBuf_Ble_80_Sleep_Dir + dateUtil.getSyyyyMMddDate() + "/";
        String fileName = "uid-" + ContextUtil.getLUID() + "-date-" + dateUtil.getSyyyyMMddDate() + "-source-" + dataFrom + ".json";
        String yesRootPath = BaseActionUtils.FilePath.ProtoBuf_Ble_80_Sleep_Dir + yesDateUtil.getSyyyyMMddDate() + "/";
        String yesFileName = "uid-" + ContextUtil.getLUID() + "-date-" + yesDateUtil.getSyyyyMMddDate() + "-source-" + dataFrom + ".json";
        path[0] = yesRootPath + yesFileName;
        path[1] = rootPath + fileName;
        return path;
    }

    private static String[] getPathAndName(DateUtil dateUtil) {
        String[] path = new String[2];
        String rootPath = BaseActionUtils.FilePath.ProtoBuf_Ble_80_Sleep_Dir + dateUtil.getSyyyyMMddDate() + "/";
        String fileName = "uid-" + ContextUtil.getLUID() + "-date-" + dateUtil.getSyyyyMMddDate() + "-source-" + dataFrom + ".json";
        path[0] = rootPath;
        path[1] = fileName;
        return path;
    }

    //将数据过滤成json
    private static String filterJson(List<ProtoBuf_80_data> data) {
        //保存到本地
        List<File_protobuf_80data> protobufLists = new ArrayList<>();
        for (ProtoBuf_80_data index : data) {
            File_protobuf_80data file_protobuf_80data = new File_protobuf_80data();

            File_protobuf_80data.Sleep sleep = new File_protobuf_80data.Sleep();
            sleep.setA(JsonTool.fromJson(index.getSleepData(), int[].class));
            if (index.isShutdown()) {
                sleep.setS(1);
            } else {
                sleep.setS(0);
            }
            if (index.isCharge()) {
                sleep.setC(1);
            } else {
                sleep.setC(0);
            }


            File_protobuf_80data.HeartRate heartRate = new File_protobuf_80data.HeartRate();
            heartRate.setX(index.getMax_bpm());
            heartRate.setN(index.getMin_bpm());
            heartRate.setA(index.getAvg_bpm());

            File_protobuf_80data.HRV hrv = new File_protobuf_80data.HRV();
            hrv.setS(index.getSDNN());
            hrv.setR(index.getRMSSD());
            hrv.setP(index.getPNN50());
            hrv.setM(index.getMEAN());
            hrv.setF(index.getFatigue());

            File_protobuf_80data.Pedo pedo = new File_protobuf_80data.Pedo();
            pedo.setS(index.getStep());
            pedo.setD((int) index.getDistance());
            pedo.setC(index.getCalorie());
            pedo.setT(index.getType());
            pedo.setA(index.getState());

            file_protobuf_80data.setQ(index.getSeq());
            file_protobuf_80data.setT(file_protobuf_80data.parseTime(index.getHour(), index.getMinute()));
            file_protobuf_80data.setE(sleep);
            file_protobuf_80data.setP(pedo);
            file_protobuf_80data.setH(heartRate);
            file_protobuf_80data.setV(hrv);

            protobufLists.add(file_protobuf_80data);

        }


        //解析
        ComplexPropertyPreFilter filter = new ComplexPropertyPreFilter();
        Map<Class<?>, String[]> includes = new HashMap<>();
        Map<Class<?>, String[]> excludes = new HashMap<>();
        excludes.put(File_protobuf_80data.Pedo.class, new String[]{"t", "a", "c", "s", "d"});
        excludes.put(File_protobuf_80data.HeartRate.class, new String[]{"n", "x", "a"});
        excludes.put(File_protobuf_80data.HRV.class, new String[]{"s", "r", "p", "m", "f"});
        excludes.put(File_protobuf_80data.Sleep.class, new String[]{"a", "c", "s"});
        includes.put(File_protobuf_80data.class, new String[]{"Q", "T", "E", "H", "P", "V"});
        filter.setExcludes(excludes);
        filter.setIncludes(includes);
        return JsonTool.toJson(protobufLists, filter);
    }

    @SuppressLint("CheckResult")
    public static void dispSleepData(int year, int month, int day) {
        //解析本地睡眠表上传睡眠数据
        //查询数据库
        /**
         * 通过年月日计算睡眠数据
         */
        dataFrom = ContextUtil.getDeviceNameNoClear();

        Observable.create(new ObservableOnSubscribe<SleepProtoData>() {
            @Override
            public void subscribe(ObservableEmitter<SleepProtoData> emitter) throws Exception {
                List<ProtoBuf_80_data> index80s = DataSupport.where("uid= ? and year=? and month=? and day=? and data_from=?",
                        ContextUtil.getLUID() + "", year + "", month + "", day + "", dataFrom + "")
                        .order("seq asc").find(ProtoBuf_80_data.class);

                DateUtil dateUtil = new DateUtil(year, month, day);
                dateUtil.addDay(-1);
                //昨天的数据
                List<ProtoBuf_80_data> yes80 = DataSupport.where("uid= ? and year=? and month=? and day=? and data_from=?",
                        ContextUtil.getLUID() + "", dateUtil.getYear() + "", dateUtil.getMonth() + "", dateUtil.getDay() + "", dataFrom + "")
                        .order("seq asc").find(ProtoBuf_80_data.class);

                SleepProtoData sleepProtoData = new SleepProtoData();
                sleepProtoData.setTodayList(index80s);
                sleepProtoData.setYesterdayList(yes80);
                sleepProtoData.setYesDate(dateUtil);
                emitter.onNext(sleepProtoData);
            }
        })
                .filter(new Predicate<SleepProtoData>() {
                    @Override
                    public boolean test(SleepProtoData sleepProtoData) throws Exception {
                        return sleepProtoData.hasData();
                    }
                })
                .map(new Function<SleepProtoData, String>() {
                    @Override
                    public String apply(SleepProtoData sleepProtoData) throws Exception {
                        String s = filterJson(sleepProtoData.getTodayList());
                        DateUtil dateUtil = new DateUtil(year, month, day);
                        String[] twoDaysPath = getTwoDaysPath(dateUtil);
                        String[] pathAndName = getPathAndName(dateUtil);
                        //写睡眠数据到本地
                        write2SDFromString(pathAndName[0], pathAndName[1], s, false);
                        if (sleepProtoData.getYesterdayList() != null && sleepProtoData.getYesterdayList().size() > 0) {
                            String yesS = filterJson(sleepProtoData.getYesterdayList());
                            String[] yesPathAndName = getPathAndName(sleepProtoData.getYesDate());
                            write2SDFromString(yesPathAndName[0], yesPathAndName[1], yesS, false);
                        }
                        /**
                         * 计算睡眠数据
                         */
                        SA_SleepBufInfo sleep = getSleep(twoDaysPath[0], twoDaysPath[1], dateUtil, dataFrom);
                        String s1 = JsonTool.toJson(sleep);
                        L.file(s1, L.Type_Operate);
                        return s1;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                    }
                });

    }


    /**
     * 将睡眠数据入本地表
     *
     * @param f1SleepData
     * @param date
     */
    public static void localSleepDataToSleepFinal(SA_SleepBufInfo f1SleepData, String date) {
        L.file("==========SleepFinalLocal==========" + JsonTool.toJson(f1SleepData), L.Type_Operate);
        if (null != f1SleepData.sleepdata) {
            if (f1SleepData.datastatus != 0 && f1SleepData.datastatus != 1) {
                return;
            }
            List<SleepSegment> segList = new ArrayList<>();
            SA_SleepDataInfo[] sleepData = f1SleepData.sleepdata;
            if (sleepData.length <= 0) {
                return;
            }
            int totalDeep = 0;
            int totalLight = 0;
            int totalWakeUp = 0;
            int totalEye = 0;
            int lastEndTime = -1;
            int lastHour = -1;
            SleepSegment tampSeg = new SleepSegment();
            for (int i = 0; i < sleepData.length; i++) {
                SA_SleepDataInfo bean = sleepData[i];
                SleepSegment segment = new SleepSegment();
                int start = bean.startTime.hour * 60 + bean.startTime.minute;
                int end = bean.stopTime.hour * 60 + bean.stopTime.minute;
//                if(end < lastEndTime && lastHour>=0){
//                    //时间倒退问题,有误数据
//                    continue;
//                }
                lastEndTime = end;
                lastHour = bean.stopTime.hour;
                int activity = 0;
                if (start <= end) {
                    activity = end - start;
                } else {
                    activity = end + 1440 - start;
                }

                if (i == 0) {
                    segment.setSt(0);
                    segment.setEt(activity);
                    segment.setType(bean.sleepMode);
                    tampSeg = segment;
                    segList.add(0, segment);
                } else if (i > 0) {
                    segment.setSt(tampSeg.getEt());
                    segment.setEt(tampSeg.getEt() + activity);
                    segment.setType(bean.sleepMode);
                    segList.add(segment);
                    tampSeg = segment;
                }

                //睡眠类型
                int sleepType = bean.sleepMode;
                if (sleepType == 3) {
                    totalDeep += activity;
                } else if (sleepType == 4) {
                    totalLight += activity;
                } else if (sleepType == 6) {
                    totalWakeUp += activity;
                } else if (sleepType == 7) {
                    totalEye += activity;
                }

            }
            DateUtil startDateUtil = new DateUtil(f1SleepData.inSleepTime.year + 2000, f1SleepData.inSleepTime.month, f1SleepData.inSleepTime.day, f1SleepData.inSleepTime.hour, f1SleepData.inSleepTime.minute);
            DateUtil endDateUtil = new DateUtil(f1SleepData.outSleepTime.year + 2000, f1SleepData.outSleepTime.month, f1SleepData.outSleepTime.day, f1SleepData.outSleepTime.hour, f1SleepData.outSleepTime.minute);

            TB_SLEEP_Final_DATA sleepDataByDate1 = Mtk_DeviceBaseInfoSqlUtil.getSleepDataByDate1(ContextUtil.app, date);
            if (sleepDataByDate1 == null) {
                sleepDataByDate1 = new TB_SLEEP_Final_DATA();
                sleepDataByDate1.setToDefault("_uploaded");
                sleepDataByDate1.setUid(ContextUtil.getLUID());
                sleepDataByDate1.setData_from(dataFrom);
                sleepDataByDate1.setDate(date);
            }

            try {
                if (totalEye == 0 && sleepDataByDate1.getEye_move_time() != 0) {
                    return;
                }
                ` `
                if (totalLight > 13 * 60 || totalDeep > 10 * 60 || totalWakeUp > 6 * 60) {
                    return;
                }

                int score = SleepScoreHandler.calSleepScore((totalDeep + totalLight + totalWakeUp), totalDeep, startDateUtil.getUnixTimestamp());
                sleepDataByDate1.setYear(startDateUtil.getYear());
                sleepDataByDate1.setMonth(startDateUtil.getMonth());
                sleepDataByDate1.setStart_time(startDateUtil.getUnixTimestamp());
                sleepDataByDate1.setEnd_time(endDateUtil.getUnixTimestamp());
                sleepDataByDate1.setDeepSleepTime(totalDeep);
                sleepDataByDate1.setEye_move_time(totalEye);
                sleepDataByDate1.setLightSleepTime(totalLight);
                sleepDataByDate1.setScore(score);
                sleepDataByDate1.setSleep_segment(JsonUtils.toJson(segList));
                sleepDataByDate1.saveAsync().listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        AwLog.e(Author.YanXi, "保存成功: " + success + " == " + endDateUtil.isToday());
                        if (endDateUtil.isToday()) {
                            HealthDataEventBus.updateHealthSleepEvent();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static TB_SLEEP_Final_DATA getSleepDataByDate(String date) {
        return DataSupport.where("date =? and data_from=?",
                date, dataFrom).findFirst(TB_SLEEP_Final_DATA.class);
    }

    private static SA_SleepBufInfo getSleep(String yesDatePath, String toDatePath, DateUtil dateUtil, final String dataFrom) {
        try {
            SA_SleepBufInfo retData = new SA_SleepBufInfo();
            if (FileUtils.checkFileExists(toDatePath)) {
                NativeInvoker jni = new NativeInvoker();
                DateUtil yesDateUtil = new DateUtil(dateUtil.getUnixTimestamp(), true);
                yesDateUtil.addDay(-1);
                double[] rriData = getRriData(ContextUtil.getLUID(), dateUtil.getSyyyyMMddDate(), dataFrom);
                double[] yesRriData = getRriData(ContextUtil.getLUID(), yesDateUtil.getSyyyyMMddDate(), dataFrom);
//                AwLog.e(Author.YanXi,"rridata size:" + rriData.length + "-----yesRriData size:" + yesRriData.length);
                AwLog.i(Author.YanXi, "rridata uid" + ContextUtil.getLUID() + "---dafrom:-> " + dataFrom);
                int status = jni.calculateSleepFileWithAF(rootPath + yesDatePath, rootPath + toDatePath, dateUtil.getSyyyyMMddDate(), 1, yesRriData, rriData, retData);
                retData.datastatus = status;
                L.file("rridata:" + rriData.length, L.Type_Operate);
                L.file("yes rridata:" + yesRriData.length, L.Type_Operate);
                /**
                 * 本地入表
                 */
                localSleepDataToSleepFinal(retData, dateUtil.getSyyyyMMddDate());
                return retData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static List<ProtoBuf_index_80> sort(List<ProtoBuf_index_80> index80s) {
        //排序
        Collections.sort(index80s, new Comparator<ProtoBuf_index_80>() {
            @Override
            public int compare(ProtoBuf_index_80 index1, ProtoBuf_index_80 index2) {
                int i = index1.getYear() * 380 + index1.getMonth() * 31 + index1.getDay();
                int i2 = index2.getYear() * 380 + index2.getMonth() * 31 + index2.getDay();
                if (i > i2) {
                    return 1;
                } else if (i == i2) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return index80s;
    }

    private static double[] getRriData(long uid, String date, String dataFrom) {
        List<Integer> list = new ArrayList<>();
        List<Double> list2 = new ArrayList<>();
        //查询数据库
        String seqMsg = "uid=? and data_from=? and date=?";
        List<TB_rri_data> tbRriData = DataSupport.where(seqMsg, uid + "", dataFrom, date).order("seq asc").find(TB_rri_data.class);

        if (tbRriData == null || tbRriData.size() == 0) {
            return new double[0];
        }
        for (TB_rri_data rriData : tbRriData) {
            List<Integer> listJson = JsonUtils.getListJson(rriData.getRawData(), Integer.class);
            list.addAll(listJson);
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list2.add(Double.valueOf(list.get(0)));
            } else {
                int last = list.get(i - 1);
                int current = list.get(i);
                if (last == -1 && current == -1) {
                } else {
                    list2.add((double) current);
                }
            }
        }
        double[] rri = new double[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            rri[i] = list2.get(i);
        }
        return rri;
    }


    private static void uploadSleepFile(long uid, DateUtil dateUtil, String source, File file, int upHour) {
        long tbId = 0;
        if (dateUtil.isToday()) {
            //时间未到不上传文件，浪费流量
            if (dateUtil.getHour() < upHour) {
                return;
            }
            TB_http_manage http_manage = DataSupport.where("uid=? and data_from=? and date=? and type=?"
                    , String.valueOf(uid), source, dateUtil.getSyyyyMMddDate(), WristbandModel.HttpType.PRO_SLEEP_UP).findFirst(TB_http_manage.class);
            if (http_manage != null) {
                tbId = http_manage.getId();
                if (dateUtil.getHour() > 13) {
                    return;
                }
                if (System.currentTimeMillis() / 1000 - http_manage.getUp_time() < UPLOAD_TIME) {
                    return;
                }
            }
        }
        final long finalTbId = tbId;
        NetFactory.getInstance().getClient(new MyCallback<UpSDFileCode>() {
            @Override
            public void onSuccess(UpSDFileCode o) {
                if (dateUtil.isToday()) {
                    if (finalTbId != 0) {
                        TB_http_manage tb_http_manage = DataSupport.find(TB_http_manage.class, finalTbId);
                        if (tb_http_manage != null) {
                            tb_http_manage.setUp_time(System.currentTimeMillis() / 1000);
                        }
                    } else {
                        TB_http_manage httpManage = new TB_http_manage();
                        httpManage.setUp_time(System.currentTimeMillis() / 1000);
                        httpManage.setData_from(source);
                        httpManage.setDate(dateUtil.getSyyyyMMddDate());
                        httpManage.setType(WristbandModel.HttpType.PRO_SLEEP_UP);
                        httpManage.setUid(uid);
                        httpManage.setUpload(1);
                        httpManage.save();
                    }
                }
            }

            @Override
            public void onFail(Throwable e) {
            }
        }).uploadSleepJson(uid, dateUtil.getSyyyyMMddDate(), source, file);
    }


}
