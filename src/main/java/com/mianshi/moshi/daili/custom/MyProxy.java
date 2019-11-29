package com.mianshi.moshi.daili.custom;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyProxy {

    private static final String Ln = "\r\n";

    public static Object newProxyInstance(MyClassLoader loader,
                                          Class<?>[] interfaces,
                                          MyInvocationHandler h)
             {

        try {
            // 1. 动态生成一个 .java的源文件
            String code = generateCode(new Class[]{Service.class});

            // 2. 把生成的这个.java源文件保存在磁盘上
            String filePath = MyProxy.class.getResource("").getPath();
            File file = new File(filePath+"$Proxy0.java");
            FileWriter fw = new FileWriter(file);
            fw.write(code);
            fw.flush();
            fw.close();

            // 3.把这个.java源文件编译成.class文件
            // 创建一个java编译器对象
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            // 获取java源代码文件管理器
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
            // 获取java源文件的一个迭代器对象
            Iterable iterable = manager.getJavaFileObjects(file);
            // 获取一个编译的任务
            JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null,null,null,iterable);
            // 执行编译
            task.call();
            // 关闭文件管理器
            manager.close();

            // 4.把编译后的.class文件加载到jvm内存中
            Class clazz = loader.findClass("$Proxy0");

            // 5.根据加载到jvm中的.class字节码文件生成Class类，然后创建Class类的对象
            Constructor constructor = clazz.getConstructor(ProxyService.class);
            return constructor.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    private static String generateCode(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("package com.mianshi.moshi.daili.custom;" + Ln);
        sb.append("import java.lang.reflect.Method;" + Ln);
        sb.append("public class $Proxy0 implements "+ interfaces[0].getName() + "{" + Ln);
        sb.append("public ProxyService h;"+ Ln);
        sb.append("public $Proxy0(ProxyService h) {" + Ln);
        sb.append("this.h = h;" + Ln);
        sb.append("}"+ Ln);

        for (Method m : interfaces[0].getMethods()) {
            sb.append("public "+ m.getReturnType().getName() + " "+ m.getName() + "() {" + Ln);
            sb.append("try{" + Ln);
            sb.append("Method m=" + interfaces[0].getName() + ".class.getMethod(\""+ m.getName() + "\",new Class[]{});" + Ln);
            sb.append("this.h.invoke(this,m,null);"+ Ln);
            sb.append("}catch(Throwable e){" +Ln);
            sb.append("e.printStackTrace();" +Ln);
            sb.append("}" +Ln);
            sb.append("return \"ssss\";" +Ln);
            sb.append("}" +Ln);
        }
        sb.append("}" +Ln);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateCode(new Class[]{Service.class}));
    }
}
