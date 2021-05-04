package service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dao.Board;
import dao.Comments;
import dao.StoreBoardDao;

public class StoreDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			 request.setCharacterEncoding("utf-8");
			 String pageNum = request.getParameter("pageNum");
		     int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		     int type_num = Integer.parseInt(request.getParameter("type_num"));
		     ServletContext context = request.getSession().getServletContext();
		     String deletefile = request.getParameter("deletefile");
		     String realPath = context.getRealPath(deletefile);
		     
		     File deleteFile = new File(realPath);
		     
		     if(deleteFile.exists()) {
		    	 
		    	 deleteFile.delete();
		     }
		     
		     StoreBoardDao sbd = StoreBoardDao.getInstance();
		     
		     int result = sbd.delete(bd_num,type_num);
		     
		     request.setAttribute("pageNum", pageNum);
		     request.setAttribute("bd_num", bd_num);
		     request.setAttribute("type_num", type_num);
		     request.setAttribute("result", result);
		     
		}catch(Exception e) {
			System.out.println("StoreDeletePro 에러 =>" +e.getMessage());
			e.printStackTrace();
		}
		
		return "storeboard/storeDeletePro.jsp";
	}

}
