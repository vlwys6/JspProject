package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.Comments;
import dao.CommentsDao;
import dao.CustomerBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class CsListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String comm_id = (String)session.getAttribute("id");
			String pageNum = request.getParameter("pageNum");
			int type_num = 400;
			if(pageNum == null || pageNum.equals("")) pageNum = "1";
			
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10,  blockSize = 10;
			CustomerBoardDao cbd = CustomerBoardDao.getInstance();
			int startRow = (currentPage-1)*pageSize+1;
			int endRow = startRow + pageSize-1;
			int totCnt = cbd.getTotalCnt();
			int startNum = totCnt-startRow+1;
			List<Board> list = cbd.list(startRow,endRow);
			
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize+1;
			int endPage = startPage + blockSize -1;
			
			if(endPage>pageCnt) endPage = pageCnt;
			
			//유저 정보 리스트
	         UserInfoDao uid = UserInfoDao.getInstance();
	         String id = (String)session.getAttribute("id");
	         UserInfo userinfo =  uid.select(id);
	         request.setAttribute("userinfo", userinfo);
	         
	       //사진첨부관련 경로
			String context = request.getContextPath(); 
			request.setAttribute("context", context);
			
			
			request.setAttribute("totCnt", totCnt); 
			request.setAttribute("list", list); 
			request.setAttribute("startNum", startNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
		} catch (Exception e) {
			System.out.println("CsListAction Exception->"+e.getMessage());
		}
		return "customer/csList.jsp";
	}

}
