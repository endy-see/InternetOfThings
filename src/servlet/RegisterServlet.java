package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Constants;
import entity.User;

public class RegisterServlet extends HttpServlet implements Constants{
	//重写doGet方法  
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {  
    	response.setContentType("text/html;charset=utf-8");
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("user");     
        String pwd = request.getParameter("pwd"); 
        if(user!=null && pwd!=null) {
        	//注册前先查询该用户名是否已被占用
        	DBHelper helper = DBHelper.getDBHelper();
        	if(helper.checkExist(user)) {
        		out.print(ERR_ALREADY_EXIST);
        		return;
        	}
        	User u = new User(user, pwd);
        	int res = helper.add(u);
        	if(res == 1) {
        		out.print(SUCCESS);
        	} else {
            	out.print(ADD_FAILED);        		
        	}
        } else {
        	out.print(PARAMS_ERR);        		
        }
        out.flush();
        out.close();
    }                     
}
