package service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PetBoardDao;

public class PetDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			request.setCharacterEncoding("UTF-8");
			int maxSize = 30 * 1024 * 1024;
			String fileSave ="/img";
			ServletContext context = request.getSession().getServletContext();
			String realPath = context.getRealPath(fileSave);
			
			MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
			
			System.out.println("여기왔니?");
			int bd_num = Integer.parseInt(multi.getParameter("bd_num"));
			String pw = multi.getParameter("pw");
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id"); //자바에서 id값 가져와서 이제 jsp에도 동동떠다님
			
			System.out.println("PetDeleProAction bd_num-->" + bd_num);
			System.out.println("PetDeleProAction pw-->" + pw);
			
			PetBoardDao pbd = PetBoardDao.getInstance();
			int result = pbd.delete(bd_num, pw, id);
			request.setAttribute("result", result);
			request.setAttribute("bd_num", bd_num);
			
			
			//폴더에 저장된 기존파일삭제로직
			String deletefile = multi.getParameter("deletefile");
			String realPath2 = context.getRealPath(deletefile);
			File deleteFile = new File(realPath2);
			 
			if(deleteFile.exists()) {	 
				 deleteFile.delete();
			 }
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "petboard/petDeletePro.jsp";
	}

}
