package com.zjz.code.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author zjz
 * @Date 2021/1/17 12:21
 */
public class BeanUtil {

    /**
     * 对象之间的拷贝
     *
     * @author HCY
     * @since 2020/12/11 下午 08:37
     * @param source: 来源
     * @param classType: 接收
     * @return E
     */
    public static <T, E> E copy(T source, Class<E> classType) {

        if (source == null) {
            return null;
        }
        E targetInstance = null;
        try {
            targetInstance = classType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        BeanUtils.copyProperties(source, targetInstance);
        return targetInstance;
    }

    /**
     * 集合之间对象的拷贝
     *
     * @author HCY
     * @since 2020/12/11 下午 08:38
     * @param sourceList: 来源
     * @param classType: 接收
     * @return java.util.List<E>
     */
    public static <T, E> List<E> batchCopy(List<T> sourceList, Class<E> classType) {
        if (sourceList == null) {
            return null;
        }
        List<E> result = new ArrayList<E>();
        int size = sourceList.size();
        for (int i = 0; i < size; i++) {
            result.add(copy(sourceList.get(i), classType));
        }
        return result;
    }
}
