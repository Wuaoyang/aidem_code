package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据Base_Column_List生成批量插入时候的sql结构
 */
public class InitSql {


    /**
     * 根据Base_Column_List生成批量插入时候的sql结构
     * @param args
     */
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();


        // 根据情况替换，从自动生成的Base那边复制过来即可
        // 仅仅替换这里    Base_Column_List
        String s = "id,\n" +
                "                year,\n" +
                "                month,\n" +
                "                day,\n" +
                "                hour,\n" +
                "                min,\n" +
                "                second,\n" +
                "                device_id,\n" +
                "                type,\n" +
                "                status,\n" +
                "                start_seq,\n" +
                "                end_seq,\n" +
                "                create_time,\n" +
                "                update_time";



        // 生成两段sql标签，直接从控制台拷贝过去
        String[] split = s.split(",");
        for (String s1 : split) {
            sb.append("<if test=\"o.").append(lineToHump(s1)).append(" != null\">").append("\n" +
                    "        ").append("#{o.").append(lineToHump(s1)).append("}, ").append("\n").append("</if>").append("\n");
            sb1.append("<if test=\"list.get(0).").append(lineToHump(s1)).append(" != null\">").append("\n" +
                    "        ").append(s1.trim()).append(",\n").append("</if>").append("\n");
        }
        System.out.println("<!-- 批量插入 -->");
        System.out.println(sb1.toString());
        System.out.println("================================= 分隔符 =================================");
        System.out.println(sb.toString());
    }

    /**
     * 下划线转驼峰
     */
    private static String lineToHump(String str) {
        Pattern linePattern = Pattern.compile("_(\\w)");
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString().trim();
    }



}
