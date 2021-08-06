
package aop.jdk;

public class UserManagerImpl implements UserManager {
    @Override
    public void addUser(String userId, String userName) {
        System.out.println("addUser(id:" + userId + ",name:" + userName + ")");
    }
}
