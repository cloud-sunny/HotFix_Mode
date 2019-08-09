package com.hotfix.library.utils;

import java.lang.reflect.Array;

/**
 * @author sunxiaoyun
 * @description $
 * @time 19/6/4
 */
class ArrayUtils {
    public static Object combineArray(Object arrayLhs, Object arrayRhs) {
        //获取一个数组的class对象,通过Array.newInstence()可以反射生成对象数组
        Class<?> localClass = arrayLhs.getClass().getComponentType();
        // 前数组长度
        int i = Array.getLength(arrayLhs);
        //新数组长度=前数组长度+后数组长度
        int j = i + Array.getLength(arrayRhs);
        // 生成对象数组
        Object result = Array.newInstance(localClass, j);
        for (int k = 0; k < j; ++k ){
            if (k < i) {
                //从0开始变量,如果前数组有值,添加到新数组的第一个位置
                Array.set(result, k, Array.get(arrayLhs, k));
            } else {
                //添加完前数组,可添加后数组,合并完成
                Array.set(result, k, Array.get(arrayRhs, k - i));
            }
        }
        return result;
    }

}
