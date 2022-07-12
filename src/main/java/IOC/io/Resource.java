package IOC.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 管理资源加载流的接口
 */
public interface Resource {
    /**
     * 获取InputStream流的方法
     * @return InputStream
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
