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
        String s = "detail_id, document_id, project_id, module_id, module_title, column_title_1, column_title_2,\n" +
                "    column_title_3, column_title_4, column_title_5, column_title_6, column_title_7, column_title_8,\n" +
                "    update_time, user_name, sid";
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
