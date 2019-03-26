package com.eyas.parent.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Created by yixuan on 2018/12/27.
 */
@Slf4j
public class ListUtil {

    /**
     * 将list进行拆分，可以用于批量更新
     *
     * @param strList     用于拆分的集合
     * @param splitNumber 拆个数
     * @return 拆分后的多个子集合
     */
    public static List<List<Long>> splitList(List<Long> strList, Integer splitNumber) {
        List<List<Long>> lists = new ArrayList<>();
        List<Long> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        if (EmptyUtil.isEmpty(strList)) {
            return lists;
        }
        for (Long str : strList) {
            i++;
            j++;
            list.add(str);
            if (i % splitNumber == 0 || j == strList.size()) {
                lists.add(list);
                list = new ArrayList<>();
                i = 0;
            }
        }
        return lists;
    }

    /**
     * list分组算法--不能使用。需要使用把getClass换成分组条件
     *
     * @param tList 需要分组的集合
     * @param <T>   泛型
     * @return 分组的条件的值作为map的key，分组结果作为Value
     */
    private static <T> Map<Object, List<T>> groupByToMap(List<T> tList) {
        return tList.stream().collect(Collectors.groupingBy(T::getClass));
    }
}
