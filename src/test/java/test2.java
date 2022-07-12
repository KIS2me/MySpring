import AOP.AdvisedSupport;
import AOP.pointcut.AspectJExpressionPointcut;
import AOP.TargetSource;
import AOP.proxy.JdkDynamicAopProxy;
import bean.UserService;
import bean.UserService2;
import bean.UserServiceInterface;
import myMthod.UserServiceInterceptor;
import org.junit.Test;

import java.lang.reflect.Method;

public class test2 {
    //测试AspectJ匹配切面
    @Test
    public void test_AspectJ() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* bean.UserService.*(..))");//test.java后的目录
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    //测试Jdk动态代理实现AOP
    @Test
    public void test_Proxy() {
        UserServiceInterface userService2 = new UserService2();

        //组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        //传入代理对象
        advisedSupport.setTargetSource(new TargetSource(userService2));
        //设置方法拦截器(即设置要进行的增强)
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        //设置方法匹配器
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* bean.UserServiceInterface.*(..))"));
        //生成代理对象
        UserServiceInterface proxy = (UserServiceInterface) new JdkDynamicAopProxy(advisedSupport).getProxy();

        System.out.println(proxy.queryUserInfo());
        System.out.println();
        System.out.println(proxy.register("TX"));
    }
}
