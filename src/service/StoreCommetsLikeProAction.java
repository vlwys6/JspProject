package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentsDao;

public class StoreCommetsLikeProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        try {
        	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			int type_num = Integer.parseInt(request.getParameter("type_num"));
			int comm_num = Integer.parseInt(request.getParameter("comm_num"));
			String board_id = request.getParameter("board_id");
			int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
			
			CommentsDao cd = CommentsDao.getInstance();
			cd.like(bd_num,type_num,comm_num,board_id);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("commPageNum", commPageNum);
			request.setAttribute("type_num", type_num);
			request.setAttribute("bd_num", bd_num);
        }catch(Exception e) {
        	System.out.println("StoreCommetsLikeProAction 에러=>"+e.getMessage());
        	e.printStackTrace();
        }
		
		return "storeboard/storeCommetsLikePro.jsp";
	}

}
