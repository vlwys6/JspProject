package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Comments;
import dao.CommentsDao;

public class TipCommUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String comm_id = (String)session.getAttribute("id");
		int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		int type_num = Integer.parseInt(request.getParameter("type_num"));
		int comm_num = Integer.parseInt(request.getParameter("comm_num"));
		
		String comm_cont = request.getParameter("comm_cont");
		//String comm_id = request.getParameter("comm_id");
		try {
			Comments comments = new Comments();
			comments.setBd_num(bd_num);
			//comments.setComm_id(comm_id);
			comments.setType_num(type_num);
			comments.setComm_cont(comm_cont);
			comments.setComm_num(comm_num);
			CommentsDao cd = CommentsDao.getInstance();
			int result = cd.update(comments);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("commPageNum", commPageNum);
			request.setAttribute("result", result);
			request.setAttribute("bd_num", bd_num); 
			request.setAttribute("type_num", type_num); 
			//request.setAttribute("id", comm_id);
		} catch (Exception e) {
			System.out.println("TipCommUpdateProAction Exception->"+e.getMessage());
		}
		
		return "tipboard/tipCommUpdatePro.jsp";
	}

}
