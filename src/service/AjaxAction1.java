package service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.Board;
import dao.UserInfo;
import dao.UserInfoDao;
public class AjaxAction1 implements CommandProcess {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
	        request.setCharacterEncoding("utf-8"); 
			response.setContentType("text/html;charset=utf-8");
	        // 본인 필요 DB Text가져 옴 (DAO 연결)
	    	String id = request.getParameter("id");
	    	
	    			System.out.println("AjaxAction1 - > id - > " + id);
	    			
	    	UserInfoDao user = UserInfoDao.getInstance();
			String result = user.duplicateIdCheck(id);
			System.out.println("ajaxAction1 result - > "+ result);
			request.setAttribute("result", result);
			

		} catch(Exception e) { System.out.println(e.getMessage()); }
		//request.setAttribute("retStr", "retStr");
       return "ajax1";
	}
}