package IOC.io;

/**
 * 资源加载器，由资源地址location，定位资源的接口
 */
public interface ResourceLoader {
    /**
     * 外界传入资源地址，即可获取资源
     * @param location 资源加载器
     * @return
     */
    Resource getResource(String location);
}
