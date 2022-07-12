package IOC.bean.reader;

import IOC.bean.definition.BeanDefinition;
import IOC.bean.definition.BeanDefinitionRegistry;
import IOC.bean.definition.BeanReference;
import IOC.io.Resource;
import IOC.property.PropertyValue;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 根据Resource对象得到XML文件输入流，并执行解析XML文件，并注册bean的方法
     * @param resources
     */
    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for(Resource resource : resources) {
            InputStream inputStream = null;
            try {
                //由Resource得到输入流
                inputStream = resource.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            doLoadBeanDefinitions(inputStream);
        }
    }

    /**
     * 根据文件位置得到XML文件输入流，并执行解析XML文件，并注册bean的方法
     * @param location
     */
    @Override
    public void loadBeanDefinitions(String location) {
        InputStream inputStream = null;
        try {
            //由文件位置得到输入流
            inputStream = getResourceLoader().getResource(location).getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        doLoadBeanDefinitions(inputStream);
    }

    /**
     * 解析XML文件，并注册bean的方法
     * @param inputStream
     */
    public void doLoadBeanDefinitions(InputStream inputStream) {
        //解析xml
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        //解析
        for(int i = 0; i < childNodes.getLength(); i ++) {
            Node node = childNodes.item(i);

            //判断是否为元素
            if(!(node instanceof Element)) {
                continue;
            }
            //判断是否为bean
            if(!"bean".equals(node.getNodeName())) {
                continue;
            }

            //解析标签bean
            Element bean = (Element) node;
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            //获取Class对象，方便获取类中的名称
            Class clazz = null;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //若有id，beanName设为id，否则才是name，优先级id>name
            String beanName = StrUtil.isEmpty(id) ? name : id;
            if(StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            //定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            //注入属性
            processProperty(beanDefinition, bean);

            if(getRegistry().containsBeanDefinition(beanName)) {
                try {
                    throw new Exception("Bean named[" + beanName + "] is already registered");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //注册BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    protected void processProperty(BeanDefinition beanDefinition, Element bean) {
        //读取属性并填充
        for(int j = 0; j < bean.getChildNodes().getLength(); j ++) {
            if(!(bean.getChildNodes().item(j) instanceof Element)) {
                continue;
            }
            if(!"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                continue;
            }

            //解析标签property
            Element property = (Element) bean.getChildNodes().item(j);
            String name = property.getAttribute("name");
            String value = property.getAttribute("value");
            String ref = property.getAttribute("ref");
            Object v = StrUtil.isNotEmpty(ref) ? new BeanReference(ref) : value;
            //注入属性信息
            beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,v));
        }
    }
}