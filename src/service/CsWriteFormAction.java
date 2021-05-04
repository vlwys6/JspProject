package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserInfo;
import dao.UserInfoDao;

public class CsWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		try {
			UserInfoDao uid = UserInfoDao.getInstance();
			
			//유저 정보 리스트
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			UserInfo userinfo;
			userinfo = uid.select(id);
			
			
			request.setAttribute("userinfo", userinfo);
			
			//사진첨부관련 경로
			String context = request.getContextPath(); 
			request.setAttribute("context", context);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		return "customer/csWrite.jsp";
	}

}
