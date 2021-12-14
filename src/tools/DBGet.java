package tools;

import java.sql.*;

public class DBGet {
	/* String sDBDriver = "com.mysql.jdbc.Driver";
	 String sConnStr = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf-8";
	 String username = "root";
	 String password = "990428";*/

	String sDBDriver = "com.mysql.jdbc.Driver";
	String sConnStr = "jdbc:mysql://39.108.102.215:3307/demo?useUnicode=true&characterEncoding=utf-8";
	String username = "root";
	String password = "admin";
	 /*String sDBDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	 String sConnStr = "jdbc:sqlserver://localhost:1433;DatabaseName=msi";
	 String username = "sa";
	 String password = "0318";*/
	 public Connection getConnection(){
		Connection conn = null;
		//装载驱动程序
		try{
			Class.forName(sDBDriver);
			//建立数据库连接
			conn = DriverManager.getConnection(sConnStr,username,password);
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnection(Connection conn){
		if(conn!=null)
		try{
				conn.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
}
