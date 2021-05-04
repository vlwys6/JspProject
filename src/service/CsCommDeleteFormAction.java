package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserInfo;
import dao.UserInfoDao;

public class CsCommDeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		int type_num = Integer.parseInt(request.getParameter("type_num"));
		int comm_num = Integer.parseInt(request.getParameter("comm_num"));
		
		try {
			//유저 정보 리스트
	        UserInfoDao uid = UserInfoDao.getInstance();
	        UserInfo userinfo;
			userinfo = uid.select(id);
			 request.setAttribute("userinfo", userinfo);
			//사진첨부관련 경로
				String context = request.getContextPath(); 
				request.setAttribute("context", context);
		} catch (Exception e) {
			System.out.println("CsCommDeleteFormAction Exception->"+e.getMessage());
		}
       
		//String comm_id = request.getParameter("comm_id");

		
		//request.setAttribute("id", id); 
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("type_num", type_num);
		//request.setAttribute("comm_id", comm_id);
		request.setAttribute("comm_num", comm_num);
		request.setAttribute("bd_num", bd_num);
		return "customer/csCommDelete.jsp";
	}

}
