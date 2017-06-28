package cn.com.learn.io.stream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class InputFile {

	public static String PATH = "D:/portalworkspace/Base/src/cn/com/io/inputStreamAndOutputStream/"; 
	
	/**
	 * 缓冲输入文件
	 * @Description 
	 * @author niepei
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static String read(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while((s=in.readLine())!=null){
			sb.append(s+"\n");//readLine()会清除掉换行符，所以这里需要手动添加
		}
		in.close();
		return sb.toString();
	}
	
	
	/**
	 * 从内存输入
	 * @Description 
	 * @author niepei
	 * @throws IOException
	 */
	public static void memoryInput() throws IOException{
		//使用read()方法返回的String来创建一个StringReader对象
		StringReader in = new StringReader(InputFile.read(PATH+"InputFile.java"));
		int c;
		//使用read()方法一次读取一个字符发送给控制台
		while((c=in.read())!=-1){
			//read()方法返回的是int，为了正常显示需要转为char
			System.out.print((char)c);
		}
	} 
	
	/**
	 * 格式化的从内存输入
	 * @Description 
	 * @author niepei
	 * @throws IOException
	 */
	public static void memoryInputFormat() throws IOException {
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(read(PATH + "InputFile.java").getBytes()));
		while (in.available() != 0) {
			System.out.print((char) in.readByte());
		}

	}
	
	public static void main(String[] args) throws IOException {
		/*System.out.println(read(PATH+"InputFile.java"));*/
 
		/*memoryInput();*/
		
		memoryInputFormat();
	}

}
