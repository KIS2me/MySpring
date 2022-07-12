package AOP.pointcut;

/**
 * 类匹配器
 */
public interface ClassFilter {
    /**
     * 检查指定类是否匹配
     * @param clazz
     * @return
     */
    boolean matches(Class<?> clazz);
}
