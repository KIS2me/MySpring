package IOC.instantiate;

import IOC.bean.definition.BeanDefinition;
import java.lang.reflect.Constructor;

/**
 * 实例化策略接口，将构造器含参与否生成的不同单例的策略，集成到此接口
 */
public interface InstantiationStrategy {
    /**
     * 实例化方法
     * @param beanDefinition
     * @param beanName
     * @param constructor 为了拿到符合参数信息相对应的构造函数
     * @param args 具体的参数信息
     * @return
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args);
}
