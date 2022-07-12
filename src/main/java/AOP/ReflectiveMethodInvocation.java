package AOP;

import org.aopalliance.intercept.MethodInvocation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * 实现被增强方法调用的类
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    //目标对象
    protected final Object target;
    //被增强方法
    protected final Method method;
    //方法参数
    protected final Object[] args;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    /**
     * 实际以反射方式调用原方法的方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object proceed() throws Throwable {
        //invoke(Object obj, Object... args)：对带有指定参数的指定对象调用由此 Method 对象表示的底层方法
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
