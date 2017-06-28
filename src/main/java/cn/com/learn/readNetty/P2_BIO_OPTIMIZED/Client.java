package cn.com.learn.readNetty.P2_BIO_OPTIMIZED;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static final Integer PORT = 10010; 
	
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket("127.0.0.1", PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("QUERY TIME　ORDER");
			System.out.println("--------------------------> the client send order to server succeed");
			String resp = in.readLine();
			System.out.println("<-------------------------- NOW IS " + resp);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				out = null;
				in.close();
				in = null;
				socket.close();
				socket = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
