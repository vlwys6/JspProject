package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.TipBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class TipUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				HttpSession session = request.getSession();
				String id = (String)session.getAttribute("id");
				//유저 정보 리스트
		         UserInfoDao uid = UserInfoDao.getInstance();
		         UserInfo userinfo =  uid.select(id);
		         request.setAttribute("userinfo", userinfo);
		       //사진첨부관련 경로
					String context = request.getContextPath(); 
					request.setAttribute("context", context);
				int bd_num = Integer.parseInt(request.getParameter("bd_num"));
				int pageNum = Integer.parseInt(request.getParameter("pageNum"));
				int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
				TipBoardDao tbd = TipBoardDao.getInstance();
				Board board = tbd.select(bd_num); 
				//request.setAttribute("id", id);
				request.setAttribute("bd_num", bd_num);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("commPageNum", commPageNum);
				request.setAttribute("board", board);
			} catch (Exception e) {
				System.out.println("TipUpdateAction Exception->"+e.getMessage());
			}
		return "tipboard/tipUpdate.jsp";
	}

}
