package AOP;

/**
 * 方法增强的目标对象
 */
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    /**
     * 获得目标类对象的所有接口
     * @return
     */
    public Class<?>[] getTargetInterfaces() {
        return this.target.getClass().getInterfaces();
    }
}
