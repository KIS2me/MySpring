package IOC.factory;

import IOC.bean.singleton.DefaultSingletonBeanRegistry;
import IOC.bean.definition.BeanDefinition;

/**
 * 实现Bean工厂接口、并继承单例Bean注册类的抽象Bean工厂类
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    /**
     * 根据无参构造器得到bean
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    /**
     * 根据有参构造器得到bean
     * @param beanName
     * @param args
     * @return
     */
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    /**
     * 得到bean的具体实现，从单例Bean注册类中的bean容器得到bean
     * @param beanName
     * @param args
     * @return
     */
    protected Object doGetBean(String beanName, Object... args) { //Object[]
        Object bean = getSingleton(beanName);

        if(bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, args);
    }

    /**
     * 获取BeanDefinition实例
     * @param beanName
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * 通过BeanDefinition实例和bean的名字创造bean实例
     * @param beanName
     * @param beanDefinition
     * @return
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);
}
