package IOC.bean.definition;

/**
 * 注册并管理BeanDefinition的接口
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 判断容器中是否已经含有指定bean名称的beanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 根据bean名称返回beanDefinition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 返回容器中所有的bean名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
