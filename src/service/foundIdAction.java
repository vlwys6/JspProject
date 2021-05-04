package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserInfoDao;

public class foundIdAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			String pname = request.getParameter("pname");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
				System.out.println("AjaxAction2 - > pname"+ pname);
				System.out.println("AjaxAction2 - > name "+ name );
				System.out.println("AjaxAction2 - > email"+ email);
			UserInfoDao user = UserInfoDao.getInstance();
			String result = user.foundId(pname, name, email);
			System.out.println("ajaxAction02 result - >" + result);
			//ajax 컨트롤러로보내기 
			request.setAttribute("result1", result);
			
			
			
				
			
			
			
		} catch (Exception e) { System.out.println(e.getMessage());
			// TODO: handle exception
		}
		
		
		
		return "ajax2";
	}

}
