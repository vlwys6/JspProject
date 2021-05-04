package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.PetBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class PetUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		try {
			request.setCharacterEncoding("utf-8");
			int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			String context = request.getContextPath();
			

			PetBoardDao pbd = PetBoardDao.getInstance();
			Board board = pbd.select(bd_num);
			
			//유저 정보 리스트
			UserInfoDao uid = UserInfoDao.getInstance();
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			UserInfo userinfo =  uid.select(id);
			request.setAttribute("userinfo", userinfo);
			
			request.setAttribute("board", board);
			request.setAttribute("context", context);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	
		
		return "petboard/petUpdate.jsp";
	}

}
