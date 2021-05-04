package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Comments;
import dao.CommentsDao;

public class PetCommWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String id = request.getParameter("board_id");
			System.out.println("board_id-->"+id);
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			System.out.println("pageNum-->"+pageNum);
			int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
			System.out.println("commPageNum-->"+commPageNum);
			int type_num = Integer.parseInt(request.getParameter("type_num"));
			System.out.println("type_num-->"+type_num);
			int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			System.out.println("bd_num-->"+bd_num);
			String comm_id = request.getParameter("comm_id");
			System.out.println("comm_id-->"+comm_id);
			String comm_cont = request.getParameter("comm_cont");
			System.out.println("comm_cont-->"+comm_cont);
			try {
				CommentsDao cd = CommentsDao.getInstance();
				Comments comments = new Comments();
				comments.setId(id);
				comments.setBd_num(bd_num);
				comments.setType_num(type_num);
				comments.setComm_cont(comm_cont);
				comments.setComm_id(comm_id); 
				int result = cd.insert(comments); 
				request.setAttribute("result", result);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("commPageNum", commPageNum);
				request.setAttribute("id", comm_id);
				request.setAttribute("bd_num", bd_num);
				request.setAttribute("type_num", type_num);
			} catch (Exception e) {
				System.out.println("PetCommWriteProAction Exception->"+e.getMessage());
			}

		return "petboard/petCommWritePro.jsp";
	}

}
