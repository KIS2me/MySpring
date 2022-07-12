package IOC.bean.singleton;

/**
 * 获取单例对象的接口
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
