import IOC.bean.definition.BeanDefinitionRegistry;
import IOC.bean.reader.XmlBeanDefinitionReader;
import IOC.context.ApplicationContext;
import IOC.context.ClassPathXmlApplicationContext;
import IOC.factory.BeanFactory;
import IOC.factory.DefaultAutowireBeanFactory;
import bean.UserService;
import org.junit.Test;

public class test1 {
    //测试xml文件配置
    @Test
    public void test1() {
        BeanFactory beanFactory = new DefaultAutowireBeanFactory();

        //读取配置文件并注册bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
        reader.loadBeanDefinitions("E:\\IDEA_workspace\\mySpring\\src\\test\\resources\\test1.xml");

        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    //测试上下文
    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("E:\\IDEA_workspace\\mySpring\\src\\test\\resources\\test1.xml");
        UserService userService = (UserService) context.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
