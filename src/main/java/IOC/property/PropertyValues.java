package IOC.property;

import java.util.ArrayList;
import java.util.List;

/**
 * 多个属性的包装类
 */
public class PropertyValues {
    /**
     * 用于接收同一个类多个属性值的集合
     */
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 向属性集合中加入新的属性的方法
     * @param pv
     */
    public void addPropertyValue(PropertyValue pv) {
        propertyValueList.add(pv);
    }

    /**
     * 根据属性名返回集合中存储的PropertyValue对象
     * @param propertyName
     * @return PropertyValue
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for(PropertyValue pv : propertyValueList) {
            if(pv.getName().equals(propertyName)) {
                return pv;
            }
        }

        return null;
    }

    /**
     * 返回集合中所有的PropertyValue对象
     * @return PropertyValue[]
     */
    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }
}
