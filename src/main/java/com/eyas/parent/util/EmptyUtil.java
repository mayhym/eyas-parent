package com.eyas.parent.util;

import com.eyas.parent.dto.EyasResult;
import com.eyas.parent.enumeration.ErrCodeEnum;
import com.eyas.parent.exception.ParentRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Created by yixuan on 2018/9/29.
 */
@Slf4j
public final class EmptyUtil {
    /**
     * 判断对象是否为空
     *
     * @param obj 对象
     * @return {@code true}: 为空<br>{@code false}: 不为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String && obj.toString().length() == 0) {
            return true;
        } else if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        } else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        } else if (obj instanceof StringBuffer && ((StringBuffer) obj).length() == 0) {
            return true;
        } else if (obj instanceof StringBuilder && ((StringBuilder) obj).length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断对象是否非空
     *
     * @param obj 对象
     * @return {@code true}: 非空<br>{@code false}: 空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 非空校验并打印错误日志
     *
     * @param o            校验对象
     * @param checkMessage 打印的信息
     * @return @return {@code true}: 为空<br>{@code false}: 不为空
     */
    public static boolean checkEmpty(Object o, String checkMessage) {
        if (EmptyUtil.isEmpty(o)) {
            log.error(checkMessage);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 非空校验数据异常，抛出异常；
     * 校验成功不返回结果；
     *
     * @param o      检验对象
     * @param errMsg 报错日志信息
     */
    public static void dealEmptyData(Object o, String errMsg) {
        if (EmptyUtil.checkEmpty(o, errMsg)) {
            throw new ParentRuntimeException(ErrCodeEnum.NULL_PARAM_ERROR, errMsg);
        } else {
        }
    }

    /**
     * 非空校验数据异常，抛出异常；
     * 校验成功返回结果；
     *
     * @param t      检验对象
     * @param errMsg 报错日志信息
     * @return object
     */
    public static <T> T dealEmptyDataReturn(T t, String errMsg) {
        EmptyUtil.dealEmptyData(t, errMsg);
        return t;
    }

    /**
     * 非空校验数据异常，抛出异常；
     * 校验成功返回结果；
     *
     * @param o      检验对象
     * @param errMsg 报错日志信息
     * @return sntResult
     */
    public static EyasResult dealEmptyDataResult(Object o, String errMsg) {
        EmptyUtil.dealEmptyData(o, errMsg);
        return EyasResult.ok(o);
    }
}
