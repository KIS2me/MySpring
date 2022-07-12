package IOC.bean.reader;

import IOC.bean.definition.BeanDefinitionRegistry;
import IOC.io.DefaultResourceLoader;
import IOC.io.ResourceLoader;

/**
 * Bean定义类的读取的抽象类
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry beanDefinitionRegistry;
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.beanDefinitionRegistry = registry;
        this.resourceLoader = new DefaultResourceLoader();
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader loader) {
        this.beanDefinitionRegistry = registry;
        this.resourceLoader = loader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return beanDefinitionRegistry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
