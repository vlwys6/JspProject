package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserInfo;
import dao.UserInfoDao;

public class PetCommDeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		
		try {
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			int type_num = Integer.parseInt(request.getParameter("type_num"));
			int comm_num = Integer.parseInt(request.getParameter("comm_num"));
			
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			
			String board_id = request.getParameter("board_id");
			int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
			
			
			//유저 정보 리스트
			UserInfoDao uid = UserInfoDao.getInstance();
			
			UserInfo userinfo= uid.select(id);
			request.setAttribute("userinfo", userinfo);
			
			//사진첨부관련 경로
			String context = request.getContextPath(); 
			request.setAttribute("context", context);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("bd_num", bd_num);
			request.setAttribute("type_num", type_num);
			request.setAttribute("comm_num", comm_num);
			request.setAttribute("id", id); 
			request.setAttribute("board_id", board_id);
			request.setAttribute("commPageNum", commPageNum);
		
			System.out.println("pagenum " + pageNum);
			System.out.println("bd_num "+bd_num);
			System.out.println("type_num "+ type_num);
			System.out.println("comm_num "+comm_num);
			System.out.println("id "+id);
			System.out.println("board_id "+board_id);
			System.out.println("commpagenum "+commPageNum);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
	
		
		return "petboard/petCommDelete.jsp";
	}

}
