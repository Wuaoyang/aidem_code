package Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * xxxxxxxxxxxx
 *
 * @author aidem
 * @date 2020/11/24 0024 18:08
 */
public class Test {

    //时间 出生日期计算年龄
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dates = sdf.format(date);
        String birth = "1997-06-29 11:12:51";
        int age = Integer.valueOf(dates.substring(0, 4)) - Integer.valueOf(birth.substring(0, 4));
        if (dates.compareTo(birth) < 0)
            age--;
        System.out.println("age : " + age);

    }

}
