package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Comments;
import dao.CommentsDao;

public class CsCommWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String comm_id = (String)session.getAttribute("id");
		String id = request.getParameter("board_id");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int type_num = Integer.parseInt(request.getParameter("type_num"));
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		String comm_cont = request.getParameter("comm_cont");
		System.out.println("comm_id=>"+comm_id);
		String a = "admin1234";
		try {
			CommentsDao cd = CommentsDao.getInstance();
			if(comm_id.equals(a)) {
			int result1 = cd.update(bd_num);
			request.setAttribute("result1", result1);
			}
			
			Comments comments = new Comments();
			comments.setId(id);
			comments.setBd_num(bd_num);
			comments.setType_num(type_num);
			comments.setComm_cont(comm_cont);
			comments.setComm_id(comm_id); 
			int result = cd.insert(comments); 
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("id", comm_id);
			request.setAttribute("bd_num", bd_num);
			request.setAttribute("type_num", type_num);

		} catch (Exception e) {
			System.out.println("CsCommWriteProAction Exception->"+e.getMessage());
		}
		return "customer/csCommWritePro.jsp";
	}

}
