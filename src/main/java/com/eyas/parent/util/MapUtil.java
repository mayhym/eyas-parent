package com.eyas.parent.util;

import com.eyas.parent.exception.EyasRuntimeException;
import org.springframework.cglib.beans.BeanMap;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Created by yixuan on 2018/12/27.
 */
public class MapUtil {
    /**
     * 将map里面的值(泛型list)，转化成集合
     *
     * @param map 需要转换的map
     * @param <T> 泛型
     * @return 转化结果
     */
    public static <T> List<List<T>> mapValueToList(Map<Object, List<T>> map) {
        List<List<T>> tListResult = new ArrayList<>();
        map.forEach((o, o2) -> tListResult.add(o2));
        return tListResult;
    }

    public static <T> T mapToBean(Map<String,Object> map, T bean){
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将对象装换为map
     * @param bean bean
     * @return map
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param clazz 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException
     *             如果分析类属性失败
     * @throws IllegalAccessException
     *             如果实例化 JavaBean 失败
     * @throws InstantiationException
     *             如果实例化 JavaBean 失败
     */
    public static <T> T map2ToBean(Class<T> clazz, Map map) throws IntrospectionException, IllegalAccessException, InstantiationException {
        // 获取类属性
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        T obj = clazz.newInstance();
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;
                Field privateField = BeanUtil.getPrivateField(propertyName, clazz);
                if (privateField == null) {
                    String type = privateField.getGenericType().toString();
                    if ("class java.lang.String".equals(type)) {
                        privateField.set(obj, value);
                    } else if ("class java.lang.Boolean".equals(type)) {
                        privateField.set(obj, Boolean.parseBoolean(String.valueOf(value)));
                    } else if ("class java.lang.Long".equals(type)) {
                        privateField.set(obj, Long.parseLong(String.valueOf(value)));
                    } else if ("class java.lang.Integer".equals(type)) {
                        privateField.set(obj, Integer.parseInt(String.valueOf(value)));
                    } else if ("class java.lang.Double".equals(type)) {
                        privateField.set(obj, Double.parseDouble(String.valueOf(value)));
                    } else if ("class java.lang.Float".equals(type)) {
                        privateField.set(obj, Float.parseFloat(String.valueOf(value)));
                    } else if ("class java.math.BigDecimal".equals(type)) {
                        privateField.set(obj, new BigDecimal(String.valueOf(value)));
                    }//可继续追加类型
                }
            }
        }
        return obj;
    }

}
