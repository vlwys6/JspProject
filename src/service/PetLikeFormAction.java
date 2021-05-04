package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.PetBoardDao;

public class PetLikeFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		int type_num = Integer.parseInt(request.getParameter("type_num"));
		try {
			PetBoardDao pbd = PetBoardDao.getInstance();
			
			//추천수 올리기
			pbd.bd_like(bd_num);
			
			
			request.setAttribute("bd_num", bd_num);
			request.setAttribute("type_num", type_num);
			
		} catch (Exception e) {
			System.out.println("PetLikeFormAction Exception->"+e.getMessage());
		}
		
		
		
		
		return "petboard/petLike.jsp";
	}

}
