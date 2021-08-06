package com.aidem.cn.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据Tsk查询结果后的映射，自动拼造一个ResultMap
 *
 * @author aidem
 * @date 2020/11/24 0024 12:30
 */
public class MappingConfig {

    // 第一步
    static String SET_STRING = "projectAllBugCard.setProjectName(resultSet.getString(\"name\"));\n" +
            "projectAllBugCard.setProject_id(resultSet.getLong(\"project_id\"));\n" +
            "projectAllBugCard.setUpdateTime(resultSet.getDate(\"update_time\"));\n" +
            "projectAllBugCard.setSortNum(resultSet.getFloat(\"sort_num\"));\n" +
            "projectAllBugCard.setProcessPerson(resultSet.getString(\"process_person\"));\n" +
            "projectAllBugCard.setPublishPerson(resultSet.getString(\"publish_person\"));\n" +
            "projectAllBugCard.setStatus(resultSet.getString(\"status\"));\n" +
            "projectAllBugCard.setDeadLine(resultSet.getDate(\"dead_line\"));\n" +
            "projectAllBugCard.setContent(resultSet.getString(\"content\"));\n" +
            "projectAllBugCard.setTitle(resultSet.getString(\"title\"));\n" +
            "projectAllBugCard.setListId(resultSet.getLong(\"list_id\"));\n" +
            "projectAllBugCard.setCardId(resultSet.getLong(\"card_id\"));\n" +
            "projectAllBugCard.setCounts(resultSet.getLong(\"counts\"));\n" +
            "projectAllBugCard.setList_title(resultSet.getString(\"list_title\"));\n" +
            "projectAllBugCard.setProgress(resultSet.getInt(\"progress\"));";

    static Map<String,String> JDBC_TYPE_MAP = new HashMap<String,String>(){{
        put("Long","LONGVARCHAR");
        put("String","VARCHAR");
        put("Float","REAL");
        put("Int","INTEGER");
        put("Date","DATE");
        put("Timestamp","DATE");
    }};

    // 第二步，去除前缀 比如输入tcp.setProjectName 则输入 tcp.set
    static String BEFORE = "projectAllBugCard.set";

    // 第三步，输入两个必要参数
    // 如 <resultMap id="getBugFeedBackFixedMap" type="org.iwown.oa.dal.model.TaskCardPure">
    static String RESULT_MAP_ID = "projectAllBugCardMap";
    static String RESULT_MAP_TYPE = "org.iwown.oa.dal.model.ProjectAllBugCard";

    // 静态量
    static String LEFT = "(";
    static String RIGHT = ")";

    public static void main(String[] args) {
        // 维护三个属性 构造<result property="projectName" jdbcType="LONGVARCHAR" column="projectName"/>
        List<String> propertyList = new ArrayList<>();
        List<String> jdbcTypeList = new ArrayList<>();
        List<String> columnList = new ArrayList<>();
        SET_STRING = SET_STRING.replace(BEFORE,"").replace("\n", "=").replace(";","").replace("  ","");
        String[] split = SET_STRING.split("=");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            // 第一个括号位置
            int length = s.length();
            int leftIndex = 0;
            for (int i1 = 0; i1 < length; i1++) {
                if (LEFT.equals(String.valueOf(s.charAt(i1)))){
                    leftIndex  = i1;
                    break;
                }
            }
            // 装填property
            String property = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1,leftIndex);
            propertyList.add(property.trim());
            // 装填jdbcType
            for (String key : JDBC_TYPE_MAP.keySet()) {
                if (s.contains(key)){
                    jdbcTypeList.add(JDBC_TYPE_MAP.get(key));
                    break;
                }
            }
            // 装填column
            String[] doubleQuoteList = s.split("\"");
            columnList.add(doubleQuoteList[1]);
        }
        System.out.println(" =============== DO COPY MAN!! =============== ");
        StringBuilder sb = new StringBuilder();
        sb.append("    <resultMap id=\"").append(RESULT_MAP_ID).append("\" ").append("type=\"").append(RESULT_MAP_TYPE).append("\">\n");
        for (int i = 0; i < propertyList.size(); i++) {
            sb.append("        <result property=\"").append(propertyList.get(i)).append("\" jdbcType=\"").append(jdbcTypeList.get(i)).append("\" column=\"").append(columnList.get(i)).append("\"/>\n");
        }
        sb.append("    </resultMap>");
        System.out.println(sb.toString());
    }
}
