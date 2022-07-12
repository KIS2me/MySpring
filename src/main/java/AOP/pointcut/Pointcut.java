package AOP.pointcut;

/**
 * 切入点接口
 */
public interface Pointcut {
    /**
     * 获取类匹配器对象
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * 获取方法匹配器对象
     * @return
     */
    MethodMatcher getMethodMatcher();
}
