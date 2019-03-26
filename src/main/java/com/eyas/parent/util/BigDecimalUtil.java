package com.eyas.parent.util;


import com.eyas.parent.enumeration.ErrCodeEnum;
import com.eyas.parent.exception.EyasRuntimeException;

import java.math.BigDecimal;

/**
 * @author Created by yixuan on 2019/3/19.
 */
public class BigDecimalUtil {

    /**
     * bigDecimal转换-其他类型自己添加
     *
     * @param num 被转对象-string-integer-bigdecimal-其他自己加
     * @return BigDecimal
     */
    public static BigDecimal bigDecimalTransformation(Object num) {
        EmptyUtil.dealEmptyData(num, "进行BigDecimal转换数据不能为空!");
        BigDecimal bigDecimal = null;
        if (num instanceof Integer) {
            Integer numNew = (Integer) num;
            bigDecimal = new BigDecimal(numNew);
        } else if (num instanceof String) {
            String numNew = (String) num;
            bigDecimal = new BigDecimal(numNew);
        } else if (num instanceof BigDecimal) {
            bigDecimal = (BigDecimal) num;
        }
        return bigDecimal;
    }

    /**
     * 加法
     *
     * @param numA 加数
     * @param numB 被加数
     * @return 和值
     */
    public static BigDecimal bigDecimalAdd(Object numA, Object numB) {
        BigDecimal bigNumA = BigDecimalUtil.bigDecimalTransformation(numA);
        BigDecimal bigNumB = BigDecimalUtil.bigDecimalTransformation(numB);
        return bigNumA.add(bigNumB);
    }

    /**
     * 减法
     *
     * @param numA 减数
     * @param numB 被减数
     * @return 差值
     */
    public static BigDecimal bigDecimalSubtract(Object numA, Object numB) {
        BigDecimal bigNumA = BigDecimalUtil.bigDecimalTransformation(numA);
        BigDecimal bigNumB = BigDecimalUtil.bigDecimalTransformation(numB);
        return bigNumA.subtract(bigNumB);
    }

    /**
     * 乘法
     *
     * @param numA 乘数
     * @param numB 被乘数
     * @return 积值
     */
    public static BigDecimal bigDecimalMultiply(Object numA, Object numB) {
        BigDecimal bigNumA = BigDecimalUtil.bigDecimalTransformation(numA);
        BigDecimal bigNumB = BigDecimalUtil.bigDecimalTransformation(numB);
        return bigNumA.multiply(bigNumB);
    }

    /**
     * 除法
     *
     * @param numA 除数
     * @param numB 被除数
     * @return 除值
     */
    public static BigDecimal bigDecimalDivide(Object numA, Object numB) {
        BigDecimal bigNumA = BigDecimalUtil.bigDecimalTransformation(numA);
        BigDecimal bigNumB = BigDecimalUtil.bigDecimalTransformation(numB);
        if (BigDecimalUtil.compareZeroEqual(bigNumB)) {
            throw new EyasRuntimeException(ErrCodeEnum.MATH_ZERO.getErrCode(), "bigDecimal除法运算分母不能为0");
        }
        return bigNumA.divide(bigNumB, BigDecimal.ROUND_CEILING);
    }

    /**
     * 等于0
     *
     * @param num 值
     * @return true
     */
    public static boolean compareZeroEqual(Object num) {
        return BigDecimalUtil.bigDecimalSubtract(num, 0).equals(BigDecimal.ZERO);
    }

    /**
     * 大于0
     *
     * @param num 值
     * @return true
     */
    public static boolean compareZeroGreater(Object num) {
        int r = BigDecimalUtil.bigDecimalTransformation(num).compareTo(BigDecimal.ZERO);
        return 0 < r;
    }

    /**
     * 小于0
     *
     * @param num 值
     * @return true
     */
    public static boolean compareZeroSmaller(Object num) {
        int r = BigDecimalUtil.bigDecimalTransformation(num).compareTo(BigDecimal.ZERO);
        return 0 > r;
    }
}
