package servlet;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 314719472293387358L;
	private boolean flag = false;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//登陆成功标志
		String LOGIN_FLAG="";
		//获得客户端提交用户名密码
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//调用UserDAO中isLogin方法判断数据中用户名密码是否正确
		boolean flag=true;
		try {
			DataOutputStream output=new DataOutputStream(resp.getOutputStream());
			if (flag) {
				LOGIN_FLAG="success";
				output.writeUTF("服务器端数据:"+LOGIN_FLAG);
			    System.out.println(LOGIN_FLAG);
				output.writeInt(1);
				  output.close(); 
			}else{
				 //登录失败  
				LOGIN_FLAG="failure";
				System.out.println(LOGIN_FLAG);
				output.writeUTF("服务器端数据:"+LOGIN_FLAG);
				  output.close(); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
//		String LOGIN_FLAG = "";
//		String ip = req.getParameter("ip");
//		String port = req.getParameter("port");
//		String username = req.getParameter("username");
//		String pwd = req.getParameter("password");
//		System.out.println("ip="+ip+", port="+port+", username="+username+", password="+pwd);
//		if(ip.equals("192.168.1.99") && port.equals("8000")
//				&& username.equals("admin") && pwd.equals("rMS?f3RuQC")) {
//					flag = true;
//		}
//				
//		DataOutputStream output = new DataOutputStream(resp.getOutputStream());
//		if(flag) {
//			LOGIN_FLAG = "success";
//		} else {
//			LOGIN_FLAG = "failure";
//		}
//		System.out.println(LOGIN_FLAG);
//
//		output.writeUTF("服务器端数据："+LOGIN_FLAG);
//		output.close();
	}
	
}
