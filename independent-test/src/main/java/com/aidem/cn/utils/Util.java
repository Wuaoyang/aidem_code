package com.aidem.cn.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Util {
    // todo
//    private static String SDPATH = Environment.getExternalStorageDirectory() + "/";
    private static String SDPATH = "/";

    public static String ascii2String(byte[] bytes2) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes2.length; i++) {
            if (ascii2Char(bytes2[i]) != 0) {
                sb.append((char) ascii2Char(bytes2[i]));
            }
        }
        return sb.toString();
    }

    public static char ascii2Char(int ASCII) {
        return (char) ASCII;
    }

    /**
     * 在SD卡上创建目录
     *
     * @param dirName
     */
    public static File creatSDDir(String dirName) {
        File dir = new File(SDPATH + dirName);
        dir.mkdirs();
        return dir;
    }

    /**
     * 在SD卡上创建文件
     *
     * @throws IOException
     */
    public static File creatSDFile(String fileName) throws IOException {
        File file = new File(SDPATH + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public static File write2SDFromString(String filePath, String string) {
        File file = null;
        FileWriter output = null;
        try {
            file = creatSDFile(filePath);
            output = new FileWriter(file, true);
            output.write("\r\n");
            output.write(string);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public static File write2SDFromString(String path, String fileName, String string) {
        File file = null;
        FileWriter output = null;
        try {
            creatSDDir(path);
            file = creatSDFile(path + fileName);
            output = new FileWriter(file, true);
            output.write(string);
            output.write("\r\n");
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public static File write2SDFromInput(String path, String fileName, InputStream input) {
        File file = null;
        OutputStream output = null;
        try {
            creatSDDir(path);
            file = creatSDFile(path + fileName);
            output = new FileOutputStream(file, true);
            byte buffer[] = new byte[128];
            while ((input.read(buffer)) != -1) {
                output.write(buffer);
            }
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public static File wirteData2File(String path, String fileName, InputStream input) {
        File file = null;
        OutputStream output = null;
        try {
            File file1 = creatSDDir(path);
            file = new File(file1, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            output = new FileOutputStream(file, true);
            byte buffer[] = new byte[128];
            while ((input.read(buffer)) != -1) {
                output.write(buffer);
            }
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static InputStream StringTOInputStream(String in) throws Exception {

        ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("utf-8"));
        return is;
    }

    /**
     * 合并两数组
     *
     * @param a
     * @param b
     * @return
     */
    public static byte[] concat(byte[] a, byte[] b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } else {
            byte[] c = new byte[a.length + b.length];
            System.arraycopy(a, 0, c, 0, a.length);
            System.arraycopy(b, 0, c, a.length, b.length);
            return c;
        }
    }

    public static String bytesToString(byte[] bytes) {
        return bytesToString(bytes, true);
    }

    public static String bytesToString(byte[] bytes, boolean needSpace) {
        if (bytes == null) return null;
        StringBuilder stringBuilder = new StringBuilder(bytes.length);
        String format;
        if (needSpace) {
            format = "%02X ";
        } else {
            format = "%02X";
        }

        for (byte byteChar : bytes)
            stringBuilder.append(String.format(format, byteChar));
        return stringBuilder.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if ((hexString == null) || (hexString.equals(""))) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; ++i) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 判断当前应用程序处于前台还是后台
     * todo
     */
//    public static boolean isApplicationBroughtToBackground(final Context context) {
//        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
//        if (!tasks.isEmpty()) {
//            ComponentName topActivity = tasks.get(0).topActivity;
//            if (!topActivity.getPackageName().equals(context.getPackageName())) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public static boolean hasLollipop() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
//    }

    /**
     * 判断有些手机显示为null的情况
     *
     * @param scanReord
     * @return
     */
    public static String isDevNameNULl(byte[] scanReord) {
        String name = "Device-XXXX";
        try {
            for (int i = 0; i < scanReord.length; i++) {
                int len = scanReord[i]; // 长度
                if (len == 0) {
                    break;
                }
                int type = scanReord[i + 1];
                byte[] bytes = new byte[len - 1];
                for (int j = 0; j < len - 1; j++) {
                    bytes[j] = scanReord[i + 2 + j];
                }
                byte[] bytes2 = new byte[len - 1];
                int idx = 0;
                do {
                    if (len - 1 > 0 && (len - 1) % 2 == 0) {
                        bytes2[idx] = bytes[idx + 1];
                        bytes2[idx + 1] = bytes[idx];
                        idx += 2;
                    } else {
                        bytes2[idx] = bytes[idx];
                        idx += 1;
                    }

                } while (idx < bytes.length);

                i = i + len;
                if (type == 0x09) {
                    return Util.ascii2String(bytes2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * 获取数据的头文件
     *
     * @param grp
     * @param cmd
     * @return
     */
    public static byte form_Header(int grp, int cmd) {
        return (byte) ((((byte) grp & 0x0f) << 4) | ((byte) cmd & 0x0f));
    }

    /**
     *日期格式转为时间戳
     * @return
     */
    public static long date2TimeStamp(int year,int month,int day,int hour,int min){
        try {
            String date_str = year + "-" + month + "-" + day +" " + hour +":"+ min +":00";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return Long.parseLong(String.valueOf(sdf.parse(date_str).getTime()/1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *日期格式转为时间戳
     * @return
     */
    public static long date2TimeStamp(int year,int month,int day,int hour,int min,int sec){
        try {
            String date_str = year + "-" + month + "-" + day +" " + hour +":"+ min +":"+sec;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return Long.parseLong(String.valueOf(sdf.parse(date_str).getTime()/1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public static File write2SDFromString_1(String path, String fileName, String string) {
        File file = null;
        FileWriter output = null;
        try {
            creatSDDir(path);
            file = creatSDFile(path + fileName);
            output = new FileWriter(file, true);
            output.write(string);
            output.write("\r\n");
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    //int转byte
    public static byte int2byte(int integer){
        return (byte) (0xff & integer);
    }

    /**
     * byte to int
     * @param bytes
     * @return
     */
    public static int bytesToInt(byte[] bytes) {
        int addr = 0;
        if (bytes.length == 1) {
            addr = bytes[0] & 0xFF;
        } else if (bytes.length == 4) {
            addr = bytes[0] & 0xFF;
            addr |= ((bytes[1] << 8) & 0xFF00);
            addr |= ((bytes[2] << 16) & 0xFF0000);
            addr |= ((bytes[3] << 24) & 0xFF000000);
        } else if (bytes.length == 2) {
            addr = bytes[0] & 0xFF;
            addr |= ((bytes[1] << 8) & 0xFF00);
        } else if (bytes.length == 3) {
            addr = bytes[0] & 0xFF;
            addr |= ((bytes[1] << 8) & 0xFF00);
            addr |= ((bytes[2] << 16) & 0xFF0000);
        }
        return addr;
    }

    public static int byteToInt(byte bytes){
        return  bytes & 0xFF;
    }

    /**
     * int转hexString 不足两位则补0
     */
    public static String intToHexString(int number){
        String numString = Integer.toHexString(number);
        return numString.length() < 2 ? "0" + numString : numString;
    }

//    public static String getBrand(){
//        return Build.BRAND;
//    }

    /**
     * CRC16校验
     * @param bytes
     * @return
     */
    public static int crc16Modem(byte[] bytes) {
        int crc = 0x00;          // initial value
        int polynomial = 0x1021;
        for (byte index : bytes) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((index >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
            }
        }
        crc &= 0xffff;
        return crc;

    }

    /**
     * crc32
     * @param bytes
     * @return
     */
    public static long CRC_32(byte[] bytes) {
        long resultCrcValue = 0x00000000ffffffffL;
        for (int i = 0; i < bytes.length; i++) {
            int index = (int) ((resultCrcValue ^ bytes[i]) & 0xff);
            resultCrcValue = crc32Table[index] ^ (resultCrcValue >> 8);
        }
        resultCrcValue = resultCrcValue ^ 0x00000000ffffffffL;
        return resultCrcValue;
    }

    private static long[] crc32Table = new long[256];

    static {
        long crcValue;
        for (int i = 0; i < 256; i++) {
            crcValue = i;
            for (int j = 0; j < 8; j++) {
                if ((crcValue & 1) == 1) {
                    crcValue = crcValue >> 1;
                    crcValue = 0x00000000edb88320L ^ crcValue;
                } else {
                    crcValue = crcValue >> 1;
                }
            }
            crc32Table[i] = crcValue;
        }
    }

//    public static int bytesToInt(byte[] bytes) {
//        if (bytes == null) return 0;
//        if (bytes.length != 2) return 0;
//        int value = (bytes[1] & 0x00ff) << 8 | (bytes[0] & 0xff);
//        return value;
//    }

    public static byte[] getMergeHeadLenBytes(int typeH, int typeL, byte[] bytes) {
        int cyc = crc16Modem(bytes);
        return writeWristBandData(cyc, typeH, typeL, bytes);
    }

    /**
     * 数据拼接
     *
     * @return
     */
    private static byte[] writeWristBandData(int cyc, int typeH, int typeL, byte[] data) {
        byte[] commonData = new byte[8];
        commonData[0] = (byte) 0x44;
        commonData[1] = (byte) 0x54;
        commonData[2] = (byte) (data.length & 0xff);
        commonData[3] = (byte) ((data.length & 0xff00) >> 8);
        commonData[4] = (byte) (cyc & 0xff);
        commonData[5] = (byte) ((cyc & 0xff00) >> 8);
        commonData[6] = (byte) typeL;
        commonData[7] = (byte) typeH;
        return concat(commonData, data);
    }

    public static float getTimeZone() {
        return TimeZone.getDefault().getOffset(System.currentTimeMillis()) / (3600 * 1000f);
    }

    public static byte[] fileToByteStr(String path){
        byte[] data = null;
        try {
            File file = new File(path);
            if(!file.exists() || !file.isFile()){
                return data;
            }
            InputStream in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        return data;
    }
}
