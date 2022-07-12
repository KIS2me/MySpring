package myMthod;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("在方法" + invocation.getMethod().getName() + "开始前");
        Object obj = invocation.proceed();
        System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
        System.out.println("在方法" + invocation.getMethod().getName() + "结束后");
        return obj;
    }
}
