package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据JPA写法，自动拼接上@Param("...")
 *
 * @author aidem
 * @date 2020/11/25 0025 10:02
 */
public class InitMybatisParams {

    private static String SQL = "List<TaskCard> getSendweekFinishTask(String dateStart, String dateEnd, String publistPerson);";

    /**
     * 静态量
     */
    private static String LEFT = "(";
    private static String RIGHT = ")";


    public static void main(String[] args) {

        int length = SQL.length();
        int leftIndex = 0;
        for (int i = 0; i < length; i++) {
            if (LEFT.equals(String.valueOf(SQL.charAt(i)))) {
                leftIndex = i + 1;
                break;
            }
        }
        String new_sql = SQL.substring(leftIndex);
        String[] split = new_sql.split(",");
        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            String[] s1List = s.split(" ");
            String s1;
            if (i == split.length -1) {
                s1 = s1List[s1List.length - 1].substring(0, s1List[s1List.length - 1].length() - 2);
            } else {
                s1 = s1List[s1List.length - 1];
            }
            if (i == 0){
                tempList.add("@Param(\"" + s1 + "\") " + s);
            } else {
                tempList.add("@Param(\"" + s1 + "\")" + s);
            }
        }
        SQL = SQL.substring(0, leftIndex);
        for (String s : tempList) {
            SQL += s + ", ";
        }
        System.out.println("do Copy Now!!!!! Man ");
        System.out.println(SQL.substring(0, SQL.length() - 2));
    }

}
