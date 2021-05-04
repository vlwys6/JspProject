package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Comments;
import dao.CommentsDao;

public class StoreCommetsWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String pageNum = request.getParameter("pageNum");
		
		String board_id = request.getParameter("board_id");
		
		int bd_num = Integer.parseInt(request.getParameter("bd_num")); 
		
		int type_num = Integer.parseInt(request.getParameter("type_num"));
		
		String comm_cont = request.getParameter("comm_cont");
		String comm_id = (String)session.getAttribute("id");
		CommentsDao ctd = CommentsDao.getInstance();
		Comments cs = new Comments();
		cs.setId(board_id); // 작성된 게시물의 회원이름
		cs.setBd_num(bd_num);
		cs.setType_num(type_num);
		cs.setComm_cont(comm_cont);
		cs.setComm_id(comm_id); //댓글작성자 이름
		System.out.println("board_id 체크 =>"+board_id);
		System.out.println("comm_id 체크 =>"+comm_id);
		System.out.println("bd_num 체크 =>"+ bd_num);
		System.out.println("type_num 체크 =>"+type_num);
		System.out.println("comm_cont 체크 =>"+comm_cont);
		int result = ctd.insert(cs);
		
		request.setAttribute("result", result);
		request.setAttribute("bd_num", bd_num);
		request.setAttribute("type_num", type_num);
		request.setAttribute("pageNum", pageNum);
		}catch(Exception e) {
			System.out.println("StoreCommetsWriteProAction 에러 =>"+e.getMessage());
			e.printStackTrace();
		}
		
		return "storeboard/storeCommetsWritePro.jsp";
	}

}
