package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Comments;
import dao.CommentsDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class StoreCommetsUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			  request.setCharacterEncoding("utf-8");
			  int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			  String pageNum = request.getParameter("pageNum");
			  String commPageNum = request.getParameter("commPageNum");
			  int type_num = Integer.parseInt(request.getParameter("type_num"));
			  int comm_num = Integer.parseInt(request.getParameter("comm_num"));
			  HttpSession session = request.getSession();
			  String comm_id = (String)session.getAttribute("id");
			  CommentsDao ctd = CommentsDao.getInstance();
			  Comments comments = ctd.select(type_num, comm_num, comm_id);
			  
				//유저 정보 리스트
				UserInfoDao uid = UserInfoDao.getInstance();
				
				String id = (String)session.getAttribute("id");
				UserInfo userinfo =  uid.select(id);
				request.setAttribute("userinfo", userinfo);
				
				//사진첨부관련 경로
				String context = request.getContextPath(); 
				request.setAttribute("context", context);
				
			  
			  request.setAttribute("comments", comments);
			  request.setAttribute("bd_num", bd_num);
			  request.setAttribute("type_num", type_num);
			  request.setAttribute("comm_num", comm_num);
			  request.setAttribute("pageNum", pageNum);
			  request.setAttribute("commPageNum", commPageNum);
		}catch(Exception e) {
			System.out.println("StoreCommetsUpdateFormAction 에러=>"+e.getMessage());
			
			e.printStackTrace();
		}
		return "storeboard/storeCommetsUpdateForm.jsp";
	}

}
