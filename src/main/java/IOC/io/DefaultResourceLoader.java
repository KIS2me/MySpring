package IOC.io;

/**
 * 定位资源的具体实现类
 */
public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        return new FileSystemResource(location);
    }
}
