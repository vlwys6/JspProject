package service;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.UserInfoDao;

public class LoginProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
		     String id = request.getParameter("id"); 
		     String pw = request.getParameter("pw");
		     
		     UserInfoDao userinfo = UserInfoDao.getInstance();
		     System.out.println("loginproaction id -> " + id );
		     System.out.println("loginproaction pw -> " + pw );
		     int result = userinfo.selectId(id,pw);
		     
		    if (result ==1) {
		    	HttpSession session = request.getSession();
		    	session.setAttribute("id", id);
		    	
		    }
		    
		    	request.setAttribute("result", result);
		    
		     
		     
		}catch(Exception e) {
			System.out.println("LoginProAction 쪽에서 에러 : " + e.getMessage());
			e.printStackTrace();
		}
		return "user/loginPro.jsp";
	}

}
