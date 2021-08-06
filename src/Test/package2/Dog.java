package Test.package2;

import Test.package1.Animal;

import java.util.Arrays;
import java.util.List;

/**
 * @author aidem
 * @date 2021-03-20
 * @description code
 */
public class Dog extends Animal {

    public static void main(String[] args) {

        String temp = "[设备系列,蓝牙名称,设备编号sn, CS200,CS20X,CS200X, CS201,CS20X,CS201X, CS201C,CS20X,CS201C, CS201G,CS20X,CS201G, CS202,CS20XT,CS202X, CS203,CS20XT,CS203X, CS253,CS253,CS253X, CS254,CS254,CS254X, CS255,CS255,CS255X, P1EM美国,n2,P1EMX0, P1C-E美国,ta+,P1CEX0, P1C,1C,P1CX00, H110,H110,H110X0, HB1,HB1,HB1X00, CR211,CR211,CR211X, H1,H1,H1X000, CS200韩国shings,watch2,CS200X]";
        String[] split = temp.substring(1, temp.length() - 1).split(",");
        List<String> list = Arrays.asList(split);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
