package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentsDao;

public class PetCommDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			int type_num = Integer.parseInt(request.getParameter("type_num"));
			int comm_num = Integer.parseInt(request.getParameter("comm_num"));
			String id = request.getParameter("id");
			int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));	
			
			CommentsDao cd = CommentsDao.getInstance();
			int result = cd.delete(bd_num,type_num,comm_num);	 	
		
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("bd_num", bd_num);
			request.setAttribute("type_num", type_num);
			request.setAttribute("comm_num", comm_num);
			request.setAttribute("id", id); 
			request.setAttribute("commPageNum", commPageNum);
			request.setAttribute("result", result);
			

			System.out.println("pageNum " + pageNum);
			System.out.println("bd_num "+bd_num);
			System.out.println("type_num "+ type_num);
			System.out.println("comm_num "+comm_num);
			System.out.println("id "+id);
			System.out.println("commpageNum "+commPageNum);
			System.out.println("result "+result);
			
		} catch (Exception e) {
			System.out.println("PetCommDeleteProAction Exception->"+e.getMessage());
			e.printStackTrace();
		}
		return "petboard/petCommDeletePro.jsp";
	}

}
