package IOC.factory;

import IOC.bean.definition.BeanDefinition;
import IOC.bean.definition.BeanDefinitionRegistry;

import java.util.HashMap;

/**
 * 含有BeanDefinition容器、对BeanDefinition进行管理的Bean自动装配工厂类
 */
public class DefaultAutowireBeanFactory extends AbstractAutowireBeanFactory implements BeanDefinitionRegistry {
    /**
     * BeanDefinition容器
     */
    HashMap<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 将beanDefinition注册入BeanDefinition容器
     * @param beanName
     * @param beanDefinition
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 获取BeanDefinition实例
     * @param beanName
     * @return
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null) {
            System.out.println("No bean named" + beanName + "is defined");
        }
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
