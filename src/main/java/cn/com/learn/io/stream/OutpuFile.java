package cn.com.learn.io.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;

public class OutpuFile {

	
	public static String FILE = "C:/Users/XiaoShou/Desktop/output.txt";
	
	/**
	 * 将文本写入文件
	 * @Description 
	 * @author niepei
	 * @throws IOException
	 */
	public static void basicFileOutPut() throws IOException {
		BufferedReader in = new BufferedReader(new StringReader(InputFile.read(InputFile.PATH + "OutpuFile.java")));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE)));
		PrintWriter out = new PrintWriter(FILE);/*本行代码是上一行代码的简写形式，javaSE5新增了这个构造方法替代我们层层的使用装饰器*/
		int lineCount = 1;
		String s;
		while((s=in.readLine())!=null){
			out.println(lineCount++ +" : " + s);
		}
		out.close();
		System.out.println(InputFile.read(FILE));
		
	}
	
	
	
	/**
	 * 数据的存储与恢复
	 * @Description 
	 * @author niepei
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void storeAndRecoveryData() throws IOException{
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(FILE)));
		out.writeUTF("pi is ");
		out.writeDouble(3.14159265357);
		out.writeUTF("Square root of 2 is ");
		out.writeDouble(1.414);
		out.close();
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(FILE)));
		System.out.print(in.readUTF());
		System.out.println(in.readDouble());
		System.out.print(in.readUTF());
		System.out.println(in.readDouble());
		
	}
	
	 
	
	/**
	 * 标准io，System.in,System.ou.System.err这三个都是PrintStream对象<字节流>
	 * @Description 
	 * @author niepei
	 * @throws IOException
	 */
	public static void standardIO() throws IOException{
		
		//可以将System.out转换为PrintWriter，true代表开启自动清空功能
		PrintWriter out = new PrintWriter(System.out,true);
		out.println("hello world!");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while((s=in.readLine())!=null){
			System.out.println(s);
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		/*basicFileOutPut();*/
		
		/*storeAndRecoveryData();*/

	}

}
