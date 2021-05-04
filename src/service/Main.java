package service;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.BoardUserInfo;
import dao.BoardUserInfoDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class Main implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	try {
				String pageNum = request.getParameter("pageNum");
		        if (pageNum==null || pageNum.equals("")) {    pageNum = "1";    }
		        request.setAttribute("pageNum", pageNum);
		        
				BoardUserInfoDao buid = BoardUserInfoDao.getInstance();
				
				
				//반려베스트게시글 리스트
				List<BoardUserInfo> bestListPet = buid.bestListPet();
				
				request.setAttribute("bestListPet", bestListPet);
				System.out.println("bestListPet -->"+bestListPet);
				
				
				
				//팁베스트게시글 리스트
				List<BoardUserInfo> bestListTip = buid.bestListTip();
				
				request.setAttribute("bestListTip", bestListTip);
				System.out.println("bestListTip -->"+bestListTip);
				
				
				
				//스토어베스트게시글 리스트
				List<BoardUserInfo> bestListStore = buid.bestListStore();
				
				request.setAttribute("bestListStore", bestListStore);
				System.out.println("bestListStore -->"+bestListStore);
		
				
				
				//유저 정보 리스트
				UserInfoDao uid = UserInfoDao.getInstance();
				HttpSession session = request.getSession();
				String id = (String)session.getAttribute("id");
				UserInfo userinfo =  uid.select(id);
				request.setAttribute("userinfo", userinfo);
				
				//사진첨부관련 경로
				String context = request.getContextPath(); 
				request.setAttribute("context", context);
				
		
	
		    } catch (Exception e) {
				System.out.println("e.getMessage()"+e.getMessage());
			}
		
			return "main.jsp";
		}

}
