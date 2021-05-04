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
import dao.PetBoardDao;

public class PetUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int maxSize = 30 * 1024 * 1024;
		String fileSave ="/img";
		ServletContext context = request.getSession().getServletContext();
		System.out.println("PetWriteProAction context->"+context);
		String realPath = context.getRealPath(fileSave);
		System.out.println("PetWriteProAction realPath->"+realPath);
		String filename = null;
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		//폴더에 저장된 기존파일삭제로직
		String deletefile = multi.getParameter("deletefile");
		String realPath2 = context.getRealPath(deletefile);
		System.out.println("realPath2-->"+ realPath2);
		File deleteFile = new File(realPath2);
		 
		if(deleteFile.exists()) {	 
			 deleteFile.delete();
		 }
		
		//Enumeration 순서를 가지고 있는 배열의 한 종류
		//getFileNames() 메소드는 폼요소중 input태그에서 file속성으로 지정된 태그의 name(파라미터)을 Enumeration 객체타입으로 반환.
		Enumeration en = multi.getFileNames();
		while(en.hasMoreElements()) {
			String filename1 = (String)en.nextElement();
			filename = multi.getFilesystemName(filename1);
			File file = multi.getFile(filename1);
		}	
			int bd_num = Integer.parseInt(multi.getParameter("bd_num"));
			String context1 = request.getContextPath();
			System.out.println(context1);
		
		try {
			if(filename == null || filename == "") {
				Board board = new Board();
				board.setBd_title(multi.getParameter("bd_title"));
				board.setBd_cont(multi.getParameter("bd_cont"));
				board.setBd_num(bd_num);
				
				PetBoardDao pbd = PetBoardDao.getInstance();
				int result = pbd.update(board);
				request.setAttribute("result", result);
			} else {
				Board board = new Board();
				
				board.setBd_title(multi.getParameter("bd_title"));
				board.setBd_cont(multi.getParameter("bd_cont"));
				board.setBd_num(bd_num);
				board.setBd_pic("img\\"+filename);
	
				PetBoardDao pbd = PetBoardDao.getInstance();
				int result = pbd.update(board);
				request.setAttribute("result", result); 
				System.out.println("PetWriteProAction result->"+result);
			
			
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "petboard/petUpdatePro.jsp";
	}

}
