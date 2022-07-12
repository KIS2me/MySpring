package AOP;

import AOP.pointcut.MethodMatcher;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 把代理、拦截、匹配的各项属性包装到此类中，方便在Proxy实现类进行使用
 */
public class AdvisedSupport {
    //方法增强的目标对象
    private TargetSource targetSource;
    //方法拦截器，即要进行的增强
    private MethodInterceptor methodInterceptor;
    //方法匹配器
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
