package service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.TipBoardDao;

public class TipDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int maxSize = 1024*1024*8;
		String fileSave = "/img";
		ServletContext context = request.getSession().getServletContext();
		String realPath = context.getRealPath(fileSave);		
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize,"utf-8", new DefaultFileRenamePolicy());
		String deletefile = multi.getParameter("deletefile");
		String realPath2 = context.getRealPath(deletefile);
		System.out.println(realPath2);
		File deleteFile = new File(realPath2);
		if(deleteFile.exists()) {
			deleteFile.delete();
		}
		try {
			int bd_num = Integer.parseInt(multi.getParameter("bd_num"));
			int pageNum = Integer.parseInt(multi.getParameter("pageNum"));
			TipBoardDao tbd = TipBoardDao.getInstance();
			int result = tbd.delete(bd_num);
			request.setAttribute("bd_num", bd_num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println("TipDeleteProAction Exception->"+e.getMessage());
		}
		return "tipboard/tipDeletePro.jsp";
	}

}
