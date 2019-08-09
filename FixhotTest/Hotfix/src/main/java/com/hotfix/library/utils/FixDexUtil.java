package com.hotfix.library.utils;

import android.content.Context;

import com.hotfix.library.utils.ArrayUtils;
import com.hotfix.library.utils.Constants;

import java.io.File;
import java.util.HashSet;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * @author sunxiaoyun
 * @description $
 * @time 19/6/3
 */
public class FixDexUtil {
    //classes2.dex 和 classes3.dex 同时修复
    private static HashSet<File> loadedDex = new HashSet<>();


    static {
        //修复之前清理集合
        loadedDex.clear();
    }

    /**
     * 修复加载
     *
     * @param context
     */
    public static void loadFixedDex(Context context) {
        File fileDir = context.getDir(Constants.DEX_DIR, Context.MODE_PRIVATE);
        //循环目录中私有文件
        File[] fileList = fileDir.listFiles();
        for (File file : fileList) {

            if (file.getName().endsWith(Constants.DEX_SUFFIX) && !"classes.dex".equals(file.getName())) {
                //找到修复包加入集合
                loadedDex.add(file);
            }
        }

        //模拟类加载器
        createDexClassLoader(context, fileDir);
    }

    private static void createDexClassLoader(Context context, File fileDir) {
        //创建解压目录
        String optimizeDir = fileDir.getAbsolutePath() + File.separator + "opt_dex";
        //创建目录
        File fopt = new File(optimizeDir);
        if (!fopt.exists()) {
            //创建多级目录
            fopt.mkdirs();
        }

        for (File dex : loadedDex) {
            //自有类加载器
            DexClassLoader classLoader = new DexClassLoader(dex.getAbsolutePath(), optimizeDir, null, context.getClassLoader());
            //每循环一次修复一次
            hotfix(classLoader, context);
        }

    }

    private static void hotfix(DexClassLoader classLoader, Context context) {
        //获取系统的pathclassloader
        PathClassLoader pathLoader = (PathClassLoader) context.getClassLoader();

        try {
            //获取自有的dexElements数组
            Object myElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(classLoader));
            //获取系统的dexElements数组
            Object systemElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(pathLoader));
            //合并并生产新的dexElements
            Object dexElements = ArrayUtils.combineArray(myElements, systemElements);
            //获取系统的pathList
            Object systemPathList = ReflectUtils.getPathList(pathLoader);
            //通过反射,将新的dexElements数组赋值给系统的pathList;
            ReflectUtils.setField(systemPathList, systemPathList.getClass(), dexElements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
