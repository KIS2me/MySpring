package IOC.factory;

/**
 * Bean工厂接口
 */
public interface BeanFactory {
    Object getBean(String beanName);

    Object getBean(String beanName, Object... args);
}
