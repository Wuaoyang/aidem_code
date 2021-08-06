package aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyUtil {
    @SuppressWarnings("unchecked")
    public static <T> T proxyOne(ClassLoader loader, Class<?>[] clz, InvocationHandler handler) {
        return (T) Proxy.newProxyInstance(loader, clz, handler);
    }
}
