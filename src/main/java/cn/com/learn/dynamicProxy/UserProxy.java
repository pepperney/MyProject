package cn.com.learn.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理是实现aop的方式之一
 * 
 * 代理类--需要实现InvocationHandler接口，重写invoke方法
 * @Description 
 * @author niepei
 * @date 2017年2月8日 上午10:03:23 
 * @version V1.3.1
 */
public class UserProxy implements InvocationHandler {

	private Object targetObject;

	public UserProxy(Object targetObject) {
		this.targetObject = targetObject;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		UserImpl user = (UserImpl) targetObject;
		String username = user.getUsername();
		Object result = null;

		// 权限判断
		if (username != null && !"".equals(username)) {
			result = method.invoke(targetObject, args);
		}

		return result;
	}

	
	
	
	public static void main(String[] args) {
		
		System.out.println("Proxy.............");
		UserImpl targetObject = new UserImpl("pepper");
		UserProxy proxy = new UserProxy(targetObject);
		// 生成代理对象
		IUser object = (IUser) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),targetObject.getClass().getInterfaces(), proxy);
		object.addUser();

		
		System.out.println("NO Proxy.............");
		targetObject = new UserImpl();
		proxy = new UserProxy(targetObject);
		// 生成代理对象
		object = (IUser) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),targetObject.getClass().getInterfaces(), proxy);
		object.addUser();
	}

}
