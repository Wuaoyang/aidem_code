package utils;

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
    static String SET_STRING = "projectBugCardVo.setCardId(resultSet.getString(\"cardId\"));\n" +
            "                        projectBugCardVo.setContent(resultSet.getString(\"content\"));\n" +
            "                        projectBugCardVo.setDeadLine(resultSet.getString(\"deadLine\"));\n" +
            "                        projectBugCardVo.setProcess_person(resultSet.getString(\"process_person\"));\n" +
            "                        projectBugCardVo.setProjectId(resultSet.getString(\"project_id\"));\n" +
            "                        projectBugCardVo.setProjectName(resultSet.getString(\"projectName\"));\n" +
            "                        projectBugCardVo.setCardId(resultSet.getString(\"cardId\"));\n" +
            "                        projectBugCardVo.setStatus(resultSet.getString(\"status\"));\n" +
            "                        projectBugCardVo.setSubmitter(resultSet.getString(\"submitter\"));\n" +
            "                        projectBugCardVo.setTitle(resultSet.getString(\"title\"));";

    static Map<String,String> JDBC_TYPE_MAP = new HashMap<String,String>(){{
        put("Long","LONGVARCHAR");
        put("String","VARCHAR");
        put("Float","REAL");
        put("Int","INTEGER");
        put("Date","DATE");
        put("Timestamp","DATE");
    }};

    // 第二步，去除前缀 比如输入tcp.setProjectName 则输入 tcp.set
    static String BEFORE = "projectBugCardVo.set";

    // 第三步，输入两个必要参数
    // 如 <resultMap id="getTaskCardBetweenDayMap" type="org.iwown.oa.dal.model.TaskCardPure">
    static String RESULT_MAP_ID = "projectBugCardVoMap";
    static String RESULT_MAP_TYPE = "org.iwown.oa.dal.model.ProjectBugCardVo";

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
