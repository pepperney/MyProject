package cn.com.learn.dynamicProxy.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;



@SuppressWarnings("unused")
public class LearnReflection {

	public static void main(String[] args)throws Exception {
		Car sourceObject = new Car();
		Class<?> classType1 = Class.forName("cn.com.learn.dynamicProxy.reflect.Car");
		// 如果传入的是对象(例如Car对象)，则可以使用如下方法获得该对象所对应的 Class对象
		Class<?> classType2 = Car.class;
		Class<?> classType3 = new Car().getClass();
		 	
		Object car1 = classType2.newInstance();// newInstance只能调用无参数的构造方法生成对象
		Object car2 = classType2.getConstructor(new Class[] { String.class }).newInstance(new Object[] { "BMW" });// 调用指定的构造方法生成对象
		
		Field[] fields = classType2.getFields();// 获得该类的所有成员变量
		for (Field field : fields) {
			String fieldName = field.getName();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			// 获得get，set方法
			String methodNameofGet = "get" + firstLetter + fieldName.substring(1);
			String methodNameofSet = "set" + firstLetter + fieldName.substring(1);
			Method getMethod = classType2.getMethod("methodNameofGet",new Class[] {});
			Method setMethod = classType2.getMethod("methodNameofSet",new Class[] { field.getType() });
			Object value = getMethod.invoke(car2,new Object[]{});
			setMethod.invoke(car2, new Object[]{value});
		}

		Method[] methods = classType2.getDeclaredMethods();// 获得该类的所有方法	
		Method speedUp = classType2.getMethod("speedUp",new Class[] { int.class });// 获得某个特定的方法，需要方法名和方法参数(为一个Class类型的数组)
		Object result = speedUp.invoke(car2, new Object[] { 20 });
		System.out.println((Integer) result);
	}

}
