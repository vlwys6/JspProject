package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.CustomerBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class CsUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		int type_num = Integer.parseInt(request.getParameter("type_num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		try {
			CustomerBoardDao cd = CustomerBoardDao.getInstance();
			Board board = cd.select(bd_num);
			//유저 정보 리스트
			UserInfoDao uid = UserInfoDao.getInstance();
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			UserInfo userinfo =  uid.select(id);
			request.setAttribute("userinfo", userinfo);
			
			//사진첨부관련 경로
			String context = request.getContextPath(); 
			request.setAttribute("context", context);
			
			request.setAttribute("bd_num", bd_num);
			request.setAttribute("type_num", type_num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board", board);			
		} catch (Exception e) {
			System.out.println("CsUpdateFormAction Exception->"+e.getMessage());
		}
		return "customer/csUpdate.jsp";
	}

}
