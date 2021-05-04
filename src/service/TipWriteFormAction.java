package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserInfo;
import dao.UserInfoDao;

public class TipWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int bd_num = 0;
			//유저 정보 리스트
	         UserInfoDao uid = UserInfoDao.getInstance();
	         HttpSession session = request.getSession();
	         String id = (String)session.getAttribute("id");
	         UserInfo userinfo =  uid.select(id);
	       //사진첨부관련 경로
				String context = request.getContextPath(); 
				request.setAttribute("context", context);
	         request.setAttribute("userinfo", userinfo);
			String pageNum = request.getParameter("pageNum");
			System.out.println("TipWriteAction start");
			
			request.setAttribute("bd_num", bd_num);
			request.setAttribute("id", id);
			request.setAttribute("pageNum", pageNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "tipboard/tipWrite.jsp";
	}

}
