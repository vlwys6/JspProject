package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.StoreBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class StoreWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			 int bd_num = 0;
			 String pageNum = request.getParameter("pageNum");
			 
				//유저 정보 리스트
				UserInfoDao uid = UserInfoDao.getInstance();
				HttpSession session = request.getSession();
				String id = (String)session.getAttribute("id");
				UserInfo userinfo =  uid.select(id);
				request.setAttribute("userinfo", userinfo);
				
				//사진첨부관련 경로
				String context = request.getContextPath(); 
				request.setAttribute("context", context);
				
			 
			 
			 if(pageNum == null) { pageNum = "1";}
			 if(request.getParameter("bd_num") != null) {
				 bd_num = Integer.parseInt(request.getParameter("bd_num"));
				 StoreBoardDao sbd = StoreBoardDao.getInstance();
				 Board board = sbd.select(bd_num);
 
			} request.setAttribute("bd_num", bd_num);
			 request.setAttribute("pageNum", pageNum);
			 
			 
		}catch(Exception e) {
			System.out.println("StoreWriteFormAction 에러 =>"+ e.getMessage());
			e.printStackTrace();
		}
		
		
		
		return "storeboard/storeWrite.jsp";
	}

}
