package IOC.factory;

import IOC.bean.definition.BeanDefinition;
import IOC.bean.definition.BeanReference;
import IOC.instantiate.InstantiationStrategy;
import IOC.instantiate.SimpleInstantiationStrategy;
import IOC.property.PropertyValue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public abstract class AbstractAutowireBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    /**
     * 根据构造方法的参数，通过BeanDefinition实例和bean的名字创造bean实例
     * @param beanName
     * @param beanDefinition
     * @return
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            //创造新的bean
            bean = createBeanInstance(beanName, beanDefinition, args);
            //为bean填入属性
            fillPropertyValues(beanDefinition, bean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将创建的bean加入单例容器中
        addSingleton(beanName ,bean);
        return bean;
    }

    /**
     * 根据入参信息args，执行不同的实例化策略，调用不同的构造器获得实例
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     */
    public Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor constructorToUse = null;
        Class clazz = beanDefinition.getBeanClass();

        //通过比较class对象的每个构造器的参数长度是否与args长度匹配，找出正确的构造器
        Constructor[] ctors = clazz.getDeclaredConstructors();
        for(Constructor ctor : ctors) {
            if(args != null && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
            }
        }

        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 为bean填充属性
     * @param beanDefinition
     * @param bean
     */
    public void fillPropertyValues(BeanDefinition beanDefinition, Object bean) {
        try {
            for(PropertyValue pv : beanDefinition.getPropertyValues().getPropertyValues()) {
                String name = pv.getName();
                Object value = pv.getValue();

                //当value是BeanReference的一个对象时，说明value是一个bean类型属性
                if(value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    //递归得到此value表示的bean
                    value = getBean(beanReference.getBeanName());
                }

                //通过反射，根据属性名获取属性类型对象declaredField
                Field declaredField = bean.getClass().getDeclaredField(name);
                //令属性可访问
                declaredField.setAccessible(true);
                //设置属性
                declaredField.set(bean, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //私有变量通过方法传递
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
