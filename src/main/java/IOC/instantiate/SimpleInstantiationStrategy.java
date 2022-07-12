package IOC.instantiate;

import IOC.bean.definition.BeanDefinition;
import java.lang.reflect.Constructor;

/**
 * 实例化策略的具体实现类
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    /**
     * 实例化策略的具体实现方法
     * @param beanDefinition
     * @param beanName
     * @param constructor 为了拿到符合参数信息相对应的构造函数
     * @param args 具体的参数信息
     * @return
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) {
        Class clazz = beanDefinition.getBeanClass();
        Object obj = null;

        try {
            if(constructor == null) {
                obj = clazz.getDeclaredConstructor().newInstance();
            }else {
                //constructor.getParameterTypes()：返回此构造器的形参类型数组
                //clazz.getDeclaredConstructor(Class<?>... parameterTypes)：根据形参类型数组返回此class对象的指定构造方法
                obj = clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}
