package service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Board;
import dao.StoreBoardDao;

public class StoreWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			 int result = 0;
			 request.setCharacterEncoding("utf-8");
			 HttpSession session = request.getSession();
			 
			 int maxSize = 5 * 1024 * 1024;
			 String fileSave = "/img";
			 ServletContext context = request.getSession().getServletContext();
			 String realPath = context.getRealPath(fileSave);
			 
			 			 
			 // 위는 mvc2모델용 아래는 servlet에서 작성햇을때
			 //String realPath = getServletContext().getRealPath(fileSave);
			 String filename = "";
			 System.out.println("realPath 이미지 경로=>" + realPath);
			 MultipartRequest multi = new MultipartRequest(request,realPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
			 Enumeration en = multi.getFileNames();
			 
			 while(en.hasMoreElements()) {
				 
				 String filename1 = (String)en.nextElement();
				        filename  = multi.getFilesystemName(filename1);
				 String  original = multi.getOriginalFileName(filename1);
				 String type      = multi.getContentType(filename1);
			     File file        = multi.getFile(filename1); 
			 }
			 
			 
			 String id =(String) session.getAttribute("id");
			 String pageNum = multi.getParameter("pageNum");
			 Board board = new Board();
			 
			 board.setId(id);
			// board.setBd_num(Integer.parseInt(multi.getParameter("bd_num")));
			 board.setType_num(Integer.parseInt(multi.getParameter("type_num")));
			 board.setBd_title(multi.getParameter("bd_title"));
			 board.setBd_cont(multi.getParameter("bd_cont"));
			 board.setBd_pic("img\\"+filename);
			 board.setS_cnt(Integer.parseInt(multi.getParameter("s_cnt")));
			 board.setS_price(Integer.parseInt(multi.getParameter("s_price")));
			 StoreBoardDao sbd = StoreBoardDao.getInstance();
			 result = sbd.insert(board);
			 
			 request.setAttribute("result", result);
			 request.setAttribute("bd_num", board.getBd_num());
			 request.setAttribute("pageNum", pageNum);
		}catch(Exception e) {
			System.out.println("StoreWriteProAction 에러 =>"+e.getMessage());
			e.printStackTrace();
		}
		
		
		return "storeboard/storeWritePro.jsp";
	}

}
