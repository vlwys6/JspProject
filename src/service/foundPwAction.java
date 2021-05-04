package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserInfoDao;

public class foundPwAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			System.out.println("foundPwAction id - >  "+ id);
			System.out.println("foundPwAction  name - > "+ name );
			
			UserInfoDao user = UserInfoDao.getInstance();
			String result = user.foundPw(id, name);
			
			System.out.println("foundPwAction  result - > " + result );
			request.setAttribute("result2", result);
			
			
			
			
		} catch (Exception e) {System.out.println("e.getMessage() " + e.getMessage());
			
		}
		
		return "ajax3";
	}

}
