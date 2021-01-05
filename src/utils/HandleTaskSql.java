package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 更改createPreparedStatement为xml对应sql
 *
 * @author aidem
 * @date 2020/11/24 0024 11:22
 */
public class HandleTaskSql {

    private static String SQL = "select * from need_card where dead_line < ? and status != '已完成' and status != '挂起' and status != '未接收' and status != '已关闭'";

    private static String SET_STARING = "ps.setString(1, dateStr);";

    public static void main(String[] args) {
        SQL = SQL.replace("<","&lt;").replace("+","").replace("\"","");
        SET_STARING = SET_STARING.replace("ps.setString(", "").replace(");\n", "=").replace(");","");
        String[] split = SET_STARING.split("=");
        List<String> list = new ArrayList<>();
        if (split.length != 1 || !"".equals(split[0])){
            for (int i = 0; i < split.length; i++) {
                list.add(split[i].split(",")[1]);
            }
        }
        for (int i = 0; i < SQL.length(); i++) {
            String s = String.valueOf(SQL.charAt(i));
            if ("?".equals(s)){
                SQL = SQL.substring(0,i) + "#{" + list.get(0).replace(";","").trim() + "}" + SQL.substring(i+1);
                list.remove(0);
            }
        }
        System.out.println("do Copy Now!!!!! Man ");
        System.out.println(SQL);
    }




}
