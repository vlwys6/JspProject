package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.Comments;
import dao.StoreBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class StoreListFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StoreBoardDao sbd = StoreBoardDao.getInstance(); 
		try {
			String searchkeyWord = request.getParameter("searchkeyWord");
			String searchValue = request.getParameter("searchValue");
			System.out.println("스토어리스트프롬액션키워드 ->"+searchkeyWord);
			System.out.println("스토어리스트프롬액션벨류 ->"+searchValue);
			
			
			 request.setCharacterEncoding("utf-8");
			 int condition = 0;
				if (searchkeyWord==null) {
					condition = 1; //키값이 있으면 totalcnt 에 sql 바뀜
					}
			 int totCnt = sbd.getTotalCnt(condition , searchkeyWord , searchValue);
			 String pageNum = request.getParameter("pageNum");
			 if(pageNum == null || pageNum.equals("")) { pageNum = "1"; }
			 int currentPage = Integer.parseInt(pageNum);
			 int pageSize = 8, blockSize = 10;
			 
			 int startRow = (currentPage - 1) * pageSize + 1;			 
			 int endRow = startRow + pageSize - 1;
			 int startNum = totCnt - startRow + 1;
			 
			 String context = request.getContextPath();
			 
			 
			 
			 List<Board> list = sbd.list(startRow,endRow,searchkeyWord,searchValue);
			 int pageCnt = (int)Math.ceil((double)totCnt/pageSize); 
			 int startPage = (int)(currentPage-1)/blockSize * blockSize + 1;
			 int endPage = startPage + blockSize -1;
			 if(endPage > pageCnt) endPage = pageCnt;
			 
			 
			 //유저 정보 리스트
			UserInfoDao uid = UserInfoDao.getInstance();
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			UserInfo userinfo =  uid.select(id);
			request.setAttribute("userinfo", userinfo);
	
			 
			 
			 
			 List<Board> bestStoreList = sbd.bestStoreList();
			 
		  	 request.setAttribute("searchkeyWord", searchkeyWord);
			 request.setAttribute("searchValue", searchValue);
			 request.setAttribute("bestStoreList", bestStoreList);
			 request.setAttribute("totCnt", totCnt);
			 request.setAttribute("pageNum", pageNum);
			 request.setAttribute("currentPage", currentPage);
			 request.setAttribute("startNum", startNum);
			 
			 request.setAttribute("list", list);
			 request.setAttribute("blockSize", blockSize);
			 request.setAttribute("pageCnt", pageCnt);
			 request.setAttribute("startPage", startPage);
			 request.setAttribute("endPage", endPage);
			 
			 request.setAttribute("context", context);
			 
		}catch(Exception e) {
			System.out.println("StoreListFormAction 에러 =>"+e.getMessage());
			e.printStackTrace();
		}
		
		return "storeboard/storeList.jsp";
	}

}
