package service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Board;
import dao.CustomerBoardDao;

public class CsUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		int maxSize = 1024*1024*8;
		String fileSave = "/img";
		ServletContext context = request.getSession().getServletContext();
		String realPath = context.getRealPath(fileSave);
		String fileName = "";
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		String deletefile = multi.getParameter("deletefile");
		String realPath2 = context.getRealPath(deletefile);
		File deleteFile = new File(realPath2);
		if(deleteFile.exists()) {
			deleteFile.delete();
		}
		Enumeration en = multi.getFileNames();
		while(en.hasMoreElements()) {
			String fileName1 = (String)en.nextElement();
			fileName = multi.getFilesystemName(fileName1);
			File file = multi.getFile(fileName1);
		}
		int pageNum = Integer.parseInt(multi.getParameter("pageNum"));
		int bd_num = Integer.parseInt(multi.getParameter("bd_num"));
		int type_num = Integer.parseInt(multi.getParameter("type_num"));
		String bd_title = multi.getParameter("bd_title");
		String bd_cont = multi.getParameter("bd_cont");
		try {
			
			if(fileName== null || fileName.equals("")) {
				Board board = new Board();
				board.setBd_title(bd_title);
				board.setBd_cont(bd_cont);
				board.setBd_num(bd_num);
				CustomerBoardDao cd = CustomerBoardDao.getInstance();
				result = cd.update(board);
				request.setAttribute("pageNum", pageNum);
				//request.setAttribute("id", id);
				request.setAttribute("result", result);
				request.setAttribute("bd_num", bd_num);
				request.setAttribute("type_num", type_num);
				System.out.println("result->"+result);
				System.out.println("type_num->"+type_num);
				}else {
					Board board = new Board();
					board.setBd_title(bd_title);
					board.setBd_cont(bd_cont);
					board.setBd_num(bd_num);
					board.setBd_pic("img\\"+fileName);
					CustomerBoardDao cd = CustomerBoardDao.getInstance();
					result = cd.update(board);
					request.setAttribute("pageNum", pageNum);
					//request.setAttribute("id", id);
					request.setAttribute("result", result);
					request.setAttribute("bd_num", bd_num);
					request.setAttribute("type_num", type_num);
					request.setAttribute("fileName", board.getBd_pic());
					System.out.println("result->"+result);
					System.out.println("type_num->"+type_num);
				}
		} catch (Exception e) {
			System.out.println("CsUpdateProAction Exception->"+e.getMessage());
		}
		return "customer/csUpdatePro.jsp";
	}

}
