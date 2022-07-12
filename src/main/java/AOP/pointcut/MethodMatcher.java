package AOP.pointcut;

import java.lang.reflect.Method;

/**
 * 方法匹配器
 */
public interface MethodMatcher {
    /**
     * 检查指定方法是否匹配
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class<?> targetClass);
}
