package servlet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import entity.User;

public class DBHelper {
	private volatile static DBHelper dbHelper = null;
	public static final String url = "jdbc:mysql://120.27.96.78:3306/iot_bupt_zhym";
	public static final String user = "root";
	public static final String password = "123";
	static Connection conn;
	
	// 对数据库使用单例模式 DCL
	public static DBHelper getDBHelper () {
		if(dbHelper == null) {
			synchronized(DBHelper.class) {
				if(dbHelper == null) {
					dbHelper = new DBHelper();
				}
			}
		}
		return dbHelper;
	}
	
	private DBHelper() {
		try {
			Class.forName("com.mysql.jdbc.Driver");			//加载mysql数据库
			
			conn = (Connection) DriverManager.getConnection(url, user, password);	//连接mysql数据库				

		} catch (ClassNotFoundException e) {
			System.out.println("找不到MySQL驱动");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		
	}
	
	public int add(User u) {
		String un = u.getUsername();
		String pd = u.getPassword();
		String sql = "insert into user (username, password) values ('"+u.getUsername()+"','"+u.getPassword()+"')";
		int res = -1;
		// 创建一个Statement对象
		try {		
			conn = (Connection) DriverManager.getConnection(url, user, password);								
			Statement stmt = (Statement) conn.createStatement();				
			res = stmt.executeUpdate(sql);
			System.out.println("插入数据成功！res="+res);
			stmt.close();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return res;
	}
	
	public List<User> queryAll() {
		List<User> res = new ArrayList<User>();
		String sql = "select * from user";
		
		try {
		    conn = (Connection) DriverManager.getConnection(url, user, password);					
			Statement stmt = (Statement) conn.createStatement();			
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("id="+"\t"+"username="+"\t"+"password="+"\t");
			while(rs.next()) {
				User user = new User(rs.getString(2), rs.getString(3));
				res.add(user);
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.println();
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	//下面这个方法分权限：只有管理员才可以在客户端删除其它用户
	public boolean deleteUser(String un) {
		Boolean succ = false;
		String sql = "delete * from user where username = '"+un+"'";
		
		try {
			conn = (Connection) DriverManager.getConnection(url, user, password);
			PreparedStatement pStmt = (PreparedStatement) conn.prepareStatement(sql);
			pStmt.execute();
			if(pStmt.getUpdateCount()>0) {
				succ = true;
			}
			
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return succ;
	}
	
	public Boolean checkExist(String un) {
		Boolean exist = false;
		String sql = "select 1 from user where username = '"+un+"' limit 1";
		try {
			conn = (Connection) DriverManager.getConnection(url, user, password);
			Statement stmt = (Statement) conn.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			if(res.next()) {
				exist = true;
			} 
			
			res.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}
	
}
