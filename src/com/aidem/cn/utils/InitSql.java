package com.aidem.cn.utils;

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
                "        device_id,\n" +
                "        start_time,\n" +
                "        end_time,\n" +
                "        type,\n" +
                "        calorie,\n" +
                "        step,\n" +
                "        distance,\n" +
                "        activity,\n" +
                "        create_time,\n" +
                "        update_time";



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

    String s = "<!-- 批量插入 -->\n" +
            "  <sql id=\"beforeBatchInsertSql\">\n" +
            "    <trim suffixOverrides=\",\">\n" +
            "      <if test=\"list.get(0).detailId != null\">\n" +
            "        detail_id,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).documentId != null\">\n" +
            "        document_id,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).projectId != null\">\n" +
            "        project_id,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).moduleId != null\">\n" +
            "        module_id,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).moduleTitle != null\">\n" +
            "        module_title,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).columnTitle1 != null\">\n" +
            "        column_title_1,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).columnTitle2 != null\">\n" +
            "        column_title_2,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).columnTitle3 != null\">\n" +
            "        column_title_3,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).columnTitle4 != null\">\n" +
            "        column_title_4,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).columnTitle5 != null\">\n" +
            "        column_title_5,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).columnTitle6 != null\">\n" +
            "        column_title_6,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).columnTitle7 != null\">\n" +
            "        column_title_7,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).columnTitle8 != null\">\n" +
            "        column_title_8,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).updateTime != null\">\n" +
            "        update_time,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).userName != null\">\n" +
            "        user_name,\n" +
            "      </if>\n" +
            "      <if test=\"list.get(0).sid != null\">\n" +
            "        sid,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "  </sql>\n" +
            "  <sql id=\"batchInsertSql\">\n" +
            "    <trim suffixOverrides=\",\">\n" +
            "      <if test=\"o.detailId != null\">\n" +
            "        #{o.detailId},\n" +
            "      </if>\n" +
            "      <if test=\"o.documentId != null\">\n" +
            "        #{o.documentId},\n" +
            "      </if>\n" +
            "      <if test=\"o.projectId != null\">\n" +
            "        #{o.projectId},\n" +
            "      </if>\n" +
            "      <if test=\"o.moduleId != null\">\n" +
            "        #{o.moduleId},\n" +
            "      </if>\n" +
            "      <if test=\"o.moduleTitle != null\">\n" +
            "        #{o.moduleTitle},\n" +
            "      </if>\n" +
            "      <if test=\"o.columnTitle1 != null\">\n" +
            "        #{o.columnTitle1},\n" +
            "      </if>\n" +
            "      <if test=\"o.columnTitle2 != null\">\n" +
            "        #{o.columnTitle2},\n" +
            "      </if>\n" +
            "      <if test=\"o.columnTitle3 != null\">\n" +
            "        #{o.columnTitle3},\n" +
            "      </if>\n" +
            "      <if test=\"o.columnTitle4 != null\">\n" +
            "        #{o.columnTitle4},\n" +
            "      </if>\n" +
            "      <if test=\"o.columnTitle5 != null\">\n" +
            "        #{o.columnTitle5},\n" +
            "      </if>\n" +
            "      <if test=\"o.columnTitle6 != null\">\n" +
            "        #{o.columnTitle6},\n" +
            "      </if>\n" +
            "      <if test=\"o.columnTitle7 != null\">\n" +
            "        #{o.columnTitle7},\n" +
            "      </if>\n" +
            "      <if test=\"o.columnTitle8 != null\">\n" +
            "        #{o.columnTitle8},\n" +
            "      </if>\n" +
            "      <if test=\"o.updateTime != null\">\n" +
            "        #{o.updateTime},\n" +
            "      </if>\n" +
            "      <if test=\"o.userName != null\">\n" +
            "        #{o.userName},\n" +
            "      </if>\n" +
            "      <if test=\"o.sid != null\">\n" +
            "        #{o.sid},\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "  </sql>\n" +
            "  <insert id=\"batchInsert\">\n" +
            "    insert into document_module_detail\n" +
            "    (\n" +
            "      <include refid=\"beforeBatchInsertSql\"></include>\n" +
            "    )\n" +
            "    <foreach collection=\"list\" open=\"values \" item=\"o\" separator=\",\">\n" +
            "      (\n" +
            "        <include refid=\"batchInsertSql\"></include>\n" +
            "      )\n" +
            "    </foreach>\n" +
            "  </insert>";



}
