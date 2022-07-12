package IOC.bean.definition;

/**
 * 用于处理注入bean类型属性（即属性为类对象的情况）的类，包装了bean的名字
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
