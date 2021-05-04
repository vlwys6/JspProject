package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentsDao;

public class StoreCommetsDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String pageNum = request.getParameter("pageNum");
		int type_num = Integer.parseInt(request.getParameter("type_num"));
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		int comm_num = Integer.parseInt(request.getParameter("comm_num"));
		String comm_id = (String)session.getAttribute("id");
		int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
		// 파라미터받는 값이 잘못된것같다. 
		// foreach안에 있는 값이여서 힘든건가?
		// 해결한 방법은 foreach안에있는 form문을 a태그로 바꿔서 해보니 잘된다.
		// 근데 이렇게 되면 수정할때는 어떻게 해야하지?
		CommentsDao ctd = CommentsDao.getInstance();
		int result = ctd.delete(bd_num,type_num,comm_num);
		System.out.println("comm_num 체크 =>"+comm_num);
		System.out.println("bd_num 체크 =>"+ bd_num);
		System.out.println("type_num 체크 =>"+type_num);
		System.out.println("pageNum 체크 =>"+pageNum);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("type_num", type_num);
		request.setAttribute("bd_num", bd_num);
		request.setAttribute("comm_num", comm_num);
		request.setAttribute("result", result);
		}catch(Exception e) {
			
			System.out.println("StoreCommetsDeleteProAction 에러 =>"+e.getMessage());
			e.printStackTrace();
			
		}
		
		return "storeboard/storeCommetsDeletePro.jsp";
	}

}
