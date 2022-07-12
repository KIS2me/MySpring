package IOC.context;

import IOC.bean.reader.XmlBeanDefinitionReader;
import IOC.factory.DefaultAutowireBeanFactory;

/**
 * 应用上下文实现类
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    private String configLocation;

    public ClassPathXmlApplicationContext(String location) {
        this(location, new DefaultAutowireBeanFactory());
    }

    public ClassPathXmlApplicationContext(String location, DefaultAutowireBeanFactory beanFactory) {
        super(beanFactory);
        this.configLocation = location;
        refresh();
    }

    @Override
    public void refresh() {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(configLocation);
    }
}
