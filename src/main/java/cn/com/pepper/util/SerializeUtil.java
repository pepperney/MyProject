package cn.com.pepper.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import cn.com.pepper.exception.MyException;

/**
 * 序列化与反序列化工具类
 * @Description 
 * @author niepei
 * @date 2017年6月26日 下午5:12:13 
 * @version V1.3.1
 */
public class SerializeUtil {

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deSerialize(byte[] bytes) {

		Object result = null;

		if (bytes == null || bytes.length == 0) {
			return null;
		}
		try {
			ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);

			ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);

			result = objectInputStream.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] serialize(Object object) {

		byte[] result = null;

		if (object == null) {
			return null;
		}

		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);

		if (object instanceof Serializable) {
			try {
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
				objectOutputStream.writeObject(object);
				objectOutputStream.flush();
				result = byteStream.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			throw new MyException(object.getClass() + " must implements java.io.Serializable");
		}

		return result;
	}

}
