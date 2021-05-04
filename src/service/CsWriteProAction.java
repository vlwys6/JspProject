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
import dao.CustomerBoardDao;

public class CsWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int result= 0;
		int maxSize = 1024*1024*8;
		String fileSave = "/img";
		ServletContext context = request.getSession().getServletContext();
		String realPath = context.getRealPath(fileSave);
		String fileName = "";
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize,"utf-8",new DefaultFileRenamePolicy());
		Enumeration en = multi.getFileNames();
		while(en.hasMoreElements()) {
			String fileName1 = (String)en.nextElement();
			fileName = multi.getFilesystemName(fileName1);
			File file = multi.getFile(fileName1);
		}
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		int type_num = Integer.parseInt(multi.getParameter("type_num"));
		String context1 = request.getContextPath();
		System.out.println(context1);
		try {
			if(fileName == null || fileName.equals("")) {
				Board board = new Board();
				board.setId(id);
				board.setBd_title(multi.getParameter("bd_title"));
				board.setBd_cont(multi.getParameter("bd_cont"));
				board.setType_num(type_num);
				CustomerBoardDao cd = CustomerBoardDao.getInstance();
				result = cd.insert(board);
				request.setAttribute("id", board.getId());
				request.setAttribute("result", result);
				request.setAttribute("bd_num", board.getBd_num());
				request.setAttribute("type_num", board.getType_num());
				request.setAttribute("context1", context1);
			}else {
				Board board = new Board();
				board.setId(id);
				board.setBd_title(multi.getParameter("bd_title"));
				board.setBd_cont(multi.getParameter("bd_cont"));
				board.setType_num(type_num);
				board.setBd_pic("img\\"+fileName);
				CustomerBoardDao cd = CustomerBoardDao.getInstance();
				result = cd.insert(board);
				request.setAttribute("id", board.getId());
				request.setAttribute("result", result);
				request.setAttribute("bd_num", board.getBd_num());
				request.setAttribute("type_num", board.getType_num());
				request.setAttribute("fileName", board.getBd_pic());
				request.setAttribute("context1", context1);
			}
		} catch (Exception e) {
			System.out.println("CsWriteProAction Exception->"+e.getMessage());
		}
		return "customer/csWritePro.jsp";
	}

}
