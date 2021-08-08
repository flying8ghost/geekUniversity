package com.geekbang.java.jvm.c02;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * *.xlass类文件加载器
 */
public class XClassLoader extends ClassLoader {

    private final String classFilePath;

    public XClassLoader(String classFilePath) {
        this.classFilePath = classFilePath;
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = this.decode(this.readFile(this.classFilePath));
        return this.defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 读取类文件
     *
     * @param file
     * @return
     */
    private byte[] readFile(String file) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) != -1) {
                bos.write(bytes, 0, length);
            }

            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("类文件加载失败. file:" + file);
    }

    /**
     * 解码
     *
     * @param sourceBytes
     * @return
     */
    private byte[] decode(byte[] sourceBytes) {
        byte[] decodeBytes = new byte[sourceBytes.length];
        for (int i = 0; i < sourceBytes.length; i++) {
            //解码
            decodeBytes[i] = (byte) (255 - sourceBytes[i]);
        }
        return decodeBytes;
    }

    public static void main(String[] args) throws Exception {
        String classFilePath = XClassLoader.class.getResource("Hello.xlass").getFile();
        Class<?> aClass = new XClassLoader(classFilePath).findClass("Hello");
//        Object aInstance = aClass.newInstance(); //jdk8
        Object aInstance = aClass.getDeclaredConstructor().newInstance(); //jdk8+
        Method aMethod = aClass.getMethod("hello");
        aMethod.invoke(aInstance);
    }
}
