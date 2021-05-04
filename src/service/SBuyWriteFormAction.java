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

public class SBuyWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	   try {
		    request.setCharacterEncoding("utf-8");
		    String pageNum = request.getParameter("pageNum");
		    int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		    int type_num = Integer.parseInt(request.getParameter("type_num"));
		    String context = request.getContextPath();
		    StoreBoardDao sbd = StoreBoardDao.getInstance();
		    
		    Board board = sbd.select(bd_num);
		    
			//유저 정보 리스트
			UserInfoDao uid = UserInfoDao.getInstance();
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			UserInfo userinfo =  uid.select(id);
			request.setAttribute("userinfo", userinfo);
	
		    
		    request.setAttribute("board", board);
		    request.setAttribute("context", context);
		    request.setAttribute("pageNum", pageNum);
		    request.setAttribute("bd_num", bd_num);
		    request.setAttribute("type_num", type_num);
	   }catch(Exception e) {
		   System.out.println("SBuyWriteFormAction 에러=>"+e.getMessage());
		   e.printStackTrace();
	   }
		
		return "storeboard/sBuyWrite.jsp";
	}

}
