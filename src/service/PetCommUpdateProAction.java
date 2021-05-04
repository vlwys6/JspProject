package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Comments;
import dao.CommentsDao;

public class PetCommUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		int type_num = Integer.parseInt(request.getParameter("type_num"));
		int comm_num = Integer.parseInt(request.getParameter("comm_num"));
		int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
		String comm_cont = request.getParameter("comm_cont");
		
		try {
			//업데이트 하려고 넣은것
			Comments comments = new Comments();
			comments.setBd_num(bd_num);
			comments.setType_num(type_num);
			comments.setComm_cont(comm_cont);
			comments.setComm_num(comm_num);
			
			CommentsDao cd = CommentsDao.getInstance();
			int result = cd.update(comments);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
			request.setAttribute("bd_num", bd_num); 
			request.setAttribute("commPageNum", commPageNum);
			request.setAttribute("type_num", type_num);
			
		} catch (Exception e) {
			System.out.println("PetCommUpdateProAction Exception->"+e.getMessage());
		}
		return "petboard/petCommUpdatePro.jsp";
	}

}
