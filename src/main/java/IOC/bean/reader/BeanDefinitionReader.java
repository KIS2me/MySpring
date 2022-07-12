package IOC.bean.reader;

import IOC.bean.definition.BeanDefinitionRegistry;
import IOC.io.Resource;
import IOC.io.ResourceLoader;

/**
 * Bean定义类的读取接口
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String location);
}
