
package cn.com.learn.designPattern.P05__Prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * 该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象
 * 
 * 要实现深复制，需要采用流的形式读入当前对象的二进制输入，再写出二进制数据对应的对象。
 * 
 * @author pepper
 *
 */
public class Prototype implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	private String string;
	

	public String getString() {

		return string;
	}

	public void setString(String string) {

		this.string = string;
	}

	public Object myClone() throws CloneNotSupportedException {

		Prototype prototype = (Prototype) super.clone();
		return prototype;

	}

	/* 浅复制 */
	public Object shallowClone() throws CloneNotSupportedException {

		Prototype prototype = (Prototype) super.clone();
		return prototype;
	}

	/* 深复制 */
	public Object deepClone() throws IOException, ClassNotFoundException {

		/* 写入当前对象的二进制流 */
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);

		/* 读出二进制流产生的新对象 */
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}

}