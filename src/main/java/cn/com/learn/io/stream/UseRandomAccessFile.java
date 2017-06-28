package cn.com.learn.io.stream;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 随机读取文件：类RandomAccess可以随机的读取一个文件中指定位置的数据。因为在文件中，所有得内容都是按照字节存放的，都有固定的保存位置
 * 实例化此类的时候需要传递File类，以及文件的打开模式，r：读 w：写 rw：读写，使用rw模式时如果文件不存在，则会自动创建
 * @Description 
 * @author niepei
 * @date 2016年12月30日 上午10:51:26 
 * @version V1.3.1
 */
public class UseRandomAccessFile {

	public static void store() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(OutpuFile.FILE, "rw");
		for (int i = 0; i < 7; i++) {
			raf.writeDouble(i * 1.414);
		}
		raf.writeUTF("----------> the end of file");
		raf.close();
	}
	
	public static void display() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(OutpuFile.FILE, "r");
		for (int i = 0; i < 7; i++) {
			System.out.println("value " + i + " : " + raf.readDouble());
		}
		System.out.println(raf.readUTF());
		raf.close();
	}

	
	
	public static void main(String[] args) throws IOException {
		store();
		display();
	}

}
