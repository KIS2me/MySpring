package IOC.context;

import IOC.factory.DefaultAutowireBeanFactory;

/**
 * 应用上下文抽象类实现
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected DefaultAutowireBeanFactory beanFactory;

    public AbstractApplicationContext(DefaultAutowireBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return beanFactory.getBean(beanName, args);
    }

    public abstract void refresh();
}
