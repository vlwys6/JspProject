package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TipBoardDao;

public class TipLikeAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			int type_num = Integer.parseInt(request.getParameter("type_num"));
			TipBoardDao tbd = TipBoardDao.getInstance();
			tbd.like(bd_num);
			request.setAttribute("bd_num", bd_num);
			request.setAttribute("type_num", type_num);
			request.setAttribute("pageNum", pageNum);		
		} catch (Exception e) {
			System.out.println("TipLikeAction Exception ->"+e.getMessage());
		}
		return "tipboard/tipLikePro.jsp";
	}

}
