package AOP.proxy;

import AOP.AdvisedSupport;
import AOP.ReflectiveMethodInvocation;
import org.aopalliance.intercept.MethodInterceptor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 获取代理类的具体实现
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private final AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    /**
     * 返回指定接口的代理类对象
     * @return
     */
    @Override
    public Object getProxy() {
        /*
         *  Proxy.newProxyInstance： 返回一个指定接口的代理类对象
         * 方法参数：
         *    ClassLoader loader - 定义代理类的类加载器
         *    Class<?>[] interfaces - 代理类要实现的接口列表
         *    InvocationHandler h - 指派方法调用的调用处理程序
         */
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                advisedSupport.getTargetSource().getTargetInterfaces(), this);
    }

    /**
     * 根据原方法是否匹配，执行不同增强策略
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //若原方法匹配，就执行增强
        if(advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTarget().getClass())) {
            MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            //反射调用
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args));
        }

        //否则执行原方法
        return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
    }
}
