package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreBoardDao;

public class StoreLikeAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			 request.setCharacterEncoding("utf-8");
			 int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			 int type_num = Integer.parseInt(request.getParameter("type_num"));
			 String pageNum = request.getParameter("pageNum");
			 
			 StoreBoardDao sbd = StoreBoardDao.getInstance();
			 sbd.like(bd_num);
			 
			 request.setAttribute("bd_num", bd_num);
			 request.setAttribute("type_num", type_num);
			 request.setAttribute("pageNum", pageNum);
			 
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "storeboard/storeLikePro.jsp";
	}

}
