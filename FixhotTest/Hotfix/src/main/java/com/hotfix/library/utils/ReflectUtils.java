package com.hotfix.library.utils;

import java.lang.reflect.Field;

import dalvik.system.PathClassLoader;

/**
 * @author sunxiaoyun
 * @description $
 * @time 19/6/4
 */
class ReflectUtils {
    /**
     * 通过反射获取某对象,并设置私有可访问
     *
     * @param obj   改属性所属类的对象
     * @param clazz 该属性所属类
     * @param field 属性名
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private static Object getField(Object obj, Class<?> clazz, String field)
            throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        Field localField = clazz.getDeclaredField(field);
        localField.setAccessible(true);
        return localField.get(obj);
    }

    /**
     * 给某属性赋值,并设置私有可访问
     *
     * @param obj   改属性所属类的对象
     * @param clazz 该属性所属类
     * @param value 值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static void setField(Object obj, Class<?> clazz, Object value)
            throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        Field localField = clazz.getDeclaredField("dexElements");
        localField.setAccessible(true);
        localField.set(obj, value);
    }

    /**
     * 通过反射获取baseDexClassLoader对象中的pathList对象
     *
     * @param baseDexClassLoader
     * @return pathList对象
     * @throws NoSuchFieldError
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     */
    public static Object getPathList(Object baseDexClassLoader) throws  IllegalAccessException, IllegalArgumentException, ClassNotFoundException, NoSuchFieldException {
        return getField(baseDexClassLoader, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }

    /**
     * 通过反射获取baseDexClassLoader对象中的pathList对象,在获取dexElemenls
     *
     * @param paramObject
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object getDexElements(Object paramObject) throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        return getField(paramObject, paramObject.getClass(), "dexElements");
    }
}
