package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserInfo;
import dao.UserInfoDao;


public class MyInfoUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		try {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id"); //세션에있는 id 가져옴
			
			
	
			System.out.println("MyInfoUpdateFormAction id->"+id);
		    UserInfoDao uid = UserInfoDao.getInstance(); //dao
			UserInfo userInfo = uid.select(id); //dto
			System.out.println("MyInfoUpdateFormAction userInfo.getName()->"+userInfo.getUserpic());
			request.setAttribute("userInfo", userInfo);
			
			String context = request.getContextPath();
			System.out.println("context Path : " + context ); 
			
			request.setAttribute("context", context);
			
			
			
			
		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
		
		
		return "mypage/myInfoUpdateForm.jsp";
	}

}

