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

public class TipCommUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String comm_id = (String)session.getAttribute("id");
		int commPageNum = Integer.parseInt(request.getParameter("commPageNum"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		int type_num = Integer.parseInt(request.getParameter("type_num"));
		int comm_num = Integer.parseInt(request.getParameter("comm_num"));
		System.out.println(pageNum);
		System.out.println(bd_num);
		System.out.println(type_num);
		System.out.println(comm_num);
		System.out.println(comm_id);		
		try{	
			//유저 정보 리스트
	         UserInfoDao uid = UserInfoDao.getInstance();
	         String id = (String)session.getAttribute("id");
	         UserInfo userinfo =  uid.select(id);
	         request.setAttribute("userinfo", userinfo);
	       //사진첨부관련 경로
				String context = request.getContextPath(); 
				request.setAttribute("context", context);
		CommentsDao cd = CommentsDao.getInstance();
		Comments comments = cd.select(type_num,comm_num,comm_id); 	
		request.setAttribute("comments", comments);
		//request.setAttribute("id", id);
		request.setAttribute("bd_num", bd_num);
		request.setAttribute("type_num", type_num);
		request.setAttribute("comm_num", comm_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("commPageNum", commPageNum);
		}catch (Exception e) {
			System.out.println("TipCommUpdateFormAction Exception->"+e.getMessage());
		}
		return "tipboard/tipCommUpdate.jsp";
		
	}

}
