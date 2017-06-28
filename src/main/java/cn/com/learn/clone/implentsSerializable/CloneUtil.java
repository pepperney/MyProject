package cn.com.learn.clone.implentsSerializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CloneUtil {
	
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T object) throws Exception{
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream  out = new ObjectOutputStream(bout);
		out.writeObject(object);
		ObjectInputStream  in = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
		return (T)in.readObject();
	}
	
	

	
	
}
