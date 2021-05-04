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

public class StoreUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			 request.setCharacterEncoding("utf-8");
			 HttpSession session = request.getSession();
			 int maxSize = 5 * 1024 * 1024;
			 String fileSave = "/img";
			 ServletContext context = request.getSession().getServletContext();
			 String realPath1 = context.getRealPath(fileSave);
			 String filename = "";
			 MultipartRequest multi = new MultipartRequest(request,realPath1,maxSize,"utf-8",new DefaultFileRenamePolicy());
			 
			 String deletefile = multi.getParameter("deletefile");
			 
			 String realPath2 = context.getRealPath(deletefile);
			 
			 File deleteFile = new File(realPath2);
			 
			 if(deleteFile.exists()) {
				 
				 deleteFile.delete();
			 }
			 
			 Enumeration en = multi.getFileNames();
			 
			 while(en.hasMoreElements()) {
				 
				 String filename1 = (String)en.nextElement();
				 filename = multi.getFilesystemName(filename1);
			 }
			 
			 String board_id = (String) session.getAttribute("id");
			 String pageNum = multi.getParameter("pageNum");
			 int bd_num = Integer.parseInt(multi.getParameter("bd_num"));
			 int type_num = Integer.parseInt(multi.getParameter("type_num"));
			 Board board = new Board();
			 board.setId(board_id);
			 board.setBd_num(bd_num);
			 board.setBd_title(multi.getParameter("bd_title"));
			 board.setBd_cont(multi.getParameter("bd_cont"));
			 board.setBd_pic("img\\"+filename);
			 board.setS_cnt(Integer.parseInt(multi.getParameter("s_cnt")));
			 board.setS_price(Integer.parseInt(multi.getParameter("s_price")));
			 StoreBoardDao sbd = StoreBoardDao.getInstance();
			 
			 int result = sbd.update(board);
			 
			 request.setAttribute("board", board);
			 request.setAttribute("pageNum", pageNum);
			 request.setAttribute("bd_num", bd_num);
			 request.setAttribute("type_num", type_num);
			 request.setAttribute("result", result);
		}catch(Exception e) {
			System.out.println("StoreUpdateProAction 에러=>"+e.getMessage());
			e.printStackTrace();
		}
		return "storeboard/storeUpdatePro.jsp";
	}

}
