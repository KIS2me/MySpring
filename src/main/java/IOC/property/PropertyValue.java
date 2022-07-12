package IOC.property;

/**
 * 单个属性的包装类
 */
public class PropertyValue {
    //将变量定义为final后，便不可用set传值
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
