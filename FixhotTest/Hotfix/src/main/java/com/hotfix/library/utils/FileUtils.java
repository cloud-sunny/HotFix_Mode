package com.hotfix.library.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author sunxiaoyun
 * @description $
 * @time 19/6/3
 */
public class FileUtils {
    /**
     * 复制文件
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws IOException io异常
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        //新建文件输入流并对它进行缓存
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);
        //新建文件输出流并对它进行缓存
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);
        // 缓存数组
        byte[] b = new byte[1024 * 5];
        int len;

        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        //刷新此缓存的输出流
        outBuff.flush();
        //关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }
}
