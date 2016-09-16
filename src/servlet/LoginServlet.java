package servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet  extends HttpServlet {  
    //重写doGet方法  
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {  
    	response.setContentType("text/html;charset=utf-8");
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");     
        String password = request.getParameter("password"); 
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        
        boolean userNisRight = username.equals("赵艳梅");
        boolean pass = password.equals("霍少峰");
        System.out.println("userNisRight="+userNisRight+", pass="+pass);
        if(username.equals("赵艳梅") && password.equals("霍少峰")) {
        	out.println("login successful!");
        } else {
        	out.println("login failed.username="+username+", password="+password);
        }
        out.flush();
        out.close();
          
        //服务器端打印信息  
        //System.out.println("username=" + username);  
        //System.out.println("password=" + password);  
        //设置编码格式  
//        response.setContentType("text/html;charset=GB18030");  
//          
//        //返回html页面  
//        response.getWriter().println("<html>");  
//        response.getWriter().println("<head>");     
//        response.getWriter().println("<title>登录信息</title>");      
//        response.getWriter().println("</head>");    
//        response.getWriter().println("<body>");     
//        response.getWriter().println("欢迎【" + username + "】用户登录成功！！！");    
//        response.getWriter().println("</body>");    
//        response.getWriter().println("</html>");  
        
        
//        DataOutputStream output=new DataOutputStream(response.getOutputStream());
//        output.writeUTF("serviceData:success");
//        output.flush();
//        output.close();
        }                     
    //重写doPost方法  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {  
//        doGet(request, response);     
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        
        String username = request.getParameter("username");     
        String password = request.getParameter("password"); 
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        
        boolean userNisRight = username.equals("赵艳梅");
        boolean pass = password.equals("霍少峰");
        System.out.println("userNisRight="+userNisRight+", pass="+pass);
        
        PrintWriter out = response.getWriter();
        if(username.equals("美羊羊") && password.equals("二霍")) {
        	out.println("success: 你们是一对");
        } else {
        	out.println("false:你们不合适");
        }
        out.flush();
        out.close();
    }       
}  
