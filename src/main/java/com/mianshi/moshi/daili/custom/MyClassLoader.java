package com.mianshi.moshi.daili.custom;

import java.io.*;

/**
 * 自定义类加载器
 * bootstrap classLoader jdk/jre/目录下的jar包加载
 *
 * ext classLoader jdk/ext/目录下的jar包加载
 *
 * app classLoader 我们应用的classLoader
 *
 */
public class MyClassLoader extends ClassLoader{

    private File classPathFiles;

    public MyClassLoader() {
        String classPath = MyClassLoader.class.getResource("").getPath();

        this.classPathFiles = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String className = MyClassLoader.class.getPackage().getName() + "." +name;

        if(className != null){
            File classFile = new File(classPathFiles, name.replace("\\.","/") + ".class");
            FileInputStream fis = null;
            ByteArrayOutputStream bos = null;
            if(classFile.exists()){
                try {
                    fis = new FileInputStream(classFile);

                    byte[] bytes = new byte[4096];
                    bos = new ByteArrayOutputStream();
                    int len;
                    while ((len = fis.read(bytes)) != -1){
                        bos.write(bytes,0 ,len);
                    }

                    // 调用jvm的定义class方法，该方法会调用native方法
                    return defineClass(className,bos.toByteArray(),0,bos.size());
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    if(fis != null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(bos != null){
                        try {
                            bos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
