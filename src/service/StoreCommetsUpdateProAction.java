package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Comments;
import dao.CommentsDao;

public class StoreCommetsUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			 request.setCharacterEncoding("utf-8");
			 int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			 int type_num = Integer.parseInt(request.getParameter("type_num"));
			 int comm_num = Integer.parseInt(request.getParameter("comm_num"));
			 String pageNum = request.getParameter("pageNum");			 
			 String commPageNum = request.getParameter("commPageNum");
			 String comm_cont = request.getParameter("comm_cont");
			 Comments comments = new Comments();
			 comments.setBd_num(bd_num);
			 comments.setType_num(type_num);
			 comments.setComm_num(comm_num);
			 comments.setComm_cont(comm_cont);
			 System.out.println("댓글 업데이트 파라미터 comm_num =>"+comm_num);
			 System.out.println("댓글 업데이트 파라미터 type_num =>"+type_num);
			 System.out.println("댓글 업데이트 파라미터 comm_cont =>"+comm_cont);
			 System.out.println("댓글 업데이트 파라미터 bd_num =>"+bd_num);
			 CommentsDao ctd = CommentsDao.getInstance();
			 int result = ctd.update(comments);
			 
			 request.setAttribute("result", result);
			 request.setAttribute("bd_num", bd_num);
			 request.setAttribute("type_num", type_num);
			 request.setAttribute("comm_num", comm_num);
			 request.setAttribute("pageNum", pageNum);
			 request.setAttribute("commPageNum", commPageNum);
		}catch(Exception e) {
			System.out.println("StoreCommetsUpdateProAction 에러=>"+e.getMessage());			
			e.printStackTrace();
		}
		return "storeboard/storeCommetsUpdatePro.jsp";
	}

}
