package com.eyas.parent.util;

import java.lang.reflect.Field;

/**
 * @author Created by yixuan on 2018/12/27.
 */
public class BeanUtil {

    /**
     * 拿到反射父类私有属性
     *
     * @param name name
     * @param cls  类
     * @return field
     */
    public static Field getPrivateField(String name, Class cls) {
        Field declaredField;
        try {
            declaredField = cls.getDeclaredField(name);
        } catch (NoSuchFieldException ex) {

            if (cls.getSuperclass() == null) {
                return null;
            } else {
                declaredField = getPrivateField(name, cls.getSuperclass());
            }
        }
        return declaredField;
    }
}
