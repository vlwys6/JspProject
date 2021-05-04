package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentsDao;

public class CsCommDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String comm_id = (String)session.getAttribute("id");
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			int type_num = Integer.parseInt(request.getParameter("type_num"));
			int comm_num = Integer.parseInt(request.getParameter("comm_num"));
			//String comm_id = request.getParameter("comm_id");
		
		CommentsDao cd = CommentsDao.getInstance();
		int result = cd.delete(bd_num,type_num,comm_num);	 	
		request.setAttribute("result", result);
		//request.setAttribute("id", id); 
		request.setAttribute("pageNum", pageNum);

		request.setAttribute("type_num", type_num);
		//request.setAttribute("comm_id", comm_id);
		request.setAttribute("comm_num", comm_num);
		request.setAttribute("bd_num", bd_num);
		System.out.println("id "+comm_id);
		System.out.println("pagenum " + pageNum);

		System.out.println("type_num "+ type_num);
		//System.out.println("comm_id "+comm_id);
		System.out.println("comm_num "+comm_num);
		System.out.println("bd_num "+bd_num);
		System.out.println("result "+result);
	} catch (Exception e) {
		System.out.println("TipCommDeleteProAction Exception->"+e.getMessage());
	}
		return "customer/csCommDeletePro.jsp";
	}

}
