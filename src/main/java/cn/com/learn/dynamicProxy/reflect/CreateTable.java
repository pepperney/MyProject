package cn.com.learn.dynamicProxy.reflect;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


@SuppressWarnings( { "unused" })
public class CreateTable {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/education";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	
	
	public static String generateSql(String className) throws Exception {
		
		Class<?> classType = Class.forName(className);//根据类的全路径名称获得该类的类型	
		Object object = classType.newInstance();// 生成对象
		Field[] fields = classType.getDeclaredFields();// 获得该类的所有成员变量
		
		StringBuffer sb = new StringBuffer("");
		for (Field field : fields) {
			String fieldName = field.getName();
			String fieldType = field.getType().getName();
			if(fieldType == "int"){
				fieldType = "INT(11)";
			}else if(fieldType == "java.lang.String"){
				fieldType = "VARCHAR(255)";
			} 
			sb.append(fieldName + "  " + fieldType + ",");
		}
		String str = sb.toString();
		str = str.substring(0, str.length()-1);

		String tableName = classType.getName().toLowerCase();
		while (tableName.indexOf(".") > 0) {
			tableName = tableName.substring(tableName.indexOf(".") + 1);
		}
		
		String sql = "CREATE TABLE " + "t_base_" + tableName + "(" + str + ")";
		return sql;
	}

	public static void main(String[] args) throws Exception {
		
		Class.forName(DRIVER);// 加载驱动
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 获取数据连接
		Statement statement = conn.createStatement();// 创建Statement	
		
		String sql_1 = CreateTable.generateSql("cn.mybatis.Car");
		System.out.println("===>>" +sql_1);
		statement.execute(sql_1);
		
		String sql_2 = "insert into t_base_car VALUES('BMW','黑色',80)";
		System.out.println("===>>" + sql_2);
		statement.execute(sql_2);
		
		String sql_3 = "select * FROM t_base_car";
		System.out.println("===>>" +sql_3);
		ResultSet rs = statement.executeQuery(sql_3);
		while (rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3));
		}
		
		statement.close();
		conn.close();	

	}
}
