package git.qwhai.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflect {

    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();

        TestReflect get = new TestReflect();
        System.out.println("类名：\n" + get.getObjectName(demoClass));

        System.out.println("\n所有public变量：");
        get.printFields(get.getObjectName(demoClass));

        System.out.println("\n所有成员变量：");
        get.printDeclaredFields(get.getObjectName(demoClass));

        System.out.println("\n所有public方法：");
        get.printMethods(get.getObjectName(demoClass));

        System.out.println("\n所有成员方法：");
        get.printDeclaredMethods(get.getObjectName(demoClass));

        System.out.println("\n调用方法：");
        get.callMethod(demoClass, get.getObjectName(demoClass));

        System.out.println("\n测试生成一个不能new的对象");
        get.newPrivateClass("com.demo.reflect.PrivateClass");
    }

    /**
     * 获得一个对象所属的完整类的路径
     */
    private String getObjectName(Object o) {
        if (o == null) {
            return null;
        }

        return o.getClass().getName();
    }

    /**
     * 输出一个类的所有public变量
     */
    private void printFields(String clazzName) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            Field[] fields = clazz.getFields();
            for (Field field : fields) {
                System.out.println(field);
            }
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出一个类的所有成员变量
     */
    private void printDeclaredFields(String clazzName) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field);
            }
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出一个类的所有public方法
     */
    private void printMethods(String clazzName) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                System.out.println(method);
            }
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出一个类的所有成员方法
     */
    private void printDeclaredMethods(String clazzName) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method);
            }
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用类里面的成员函数
     */
    private void callMethod(Object o, String clazzName) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            Method method = clazz.getMethod("sayHello", String.class);
            method.invoke(o, TestReflect.class.getSimpleName());
        } catch(ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成一个不能new的对象
     */
    private void newPrivateClass(String clazzName) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            PrivateClass privateClass = (PrivateClass)constructor.newInstance();

            Method method = clazz.getDeclaredMethod("sayHello");
            method.setAccessible(true);
            method.invoke(privateClass);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
