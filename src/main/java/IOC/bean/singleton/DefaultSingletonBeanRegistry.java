package IOC.bean.singleton;

import java.util.HashMap;

/**
 *
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    HashMap<String, Object> singletonMap = new HashMap<>();

    /**
     * 根据bean的名字得到单例对象
     * @param beanName
     * @return
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }

    /**
     * 将单例存入容器singletonMap中
     * @param beanName
     * @param bean
     */
    protected void addSingleton(String beanName, Object bean) {
        singletonMap.put(beanName, bean);
    }
}
