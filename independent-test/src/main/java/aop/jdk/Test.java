package aop.jdk;

/**
 * @author aidem
 * @date 2021-07-08
 * @description code
 */
public class Test {

    public static void main(String[] args) {
        UserManager um = new UserManagerImpl();
        TimeHandler time = new TimeHandler(um);
        um = ProxyUtil.proxyOne(um.getClass().getClassLoader(), um.getClass().getInterfaces(), time);
        um.addUser("1111", "张三");
    }
}
