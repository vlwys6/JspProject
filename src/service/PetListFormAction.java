package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.PetBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;


public class PetListFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PetBoardDao  pbd = PetBoardDao.getInstance();
		
		try {
			String searchkeyWord = request.getParameter("searchkeyWord");
			String searchValue = request.getParameter("searchValue");
			System.out.println("펫리스트프롬액션키워드 ->"+searchkeyWord);
			System.out.println("펫리스트프롬액션벨류 ->"+searchValue);
			
			String pageNum = request.getParameter("pageNum");	
			if (pageNum==null || pageNum.equals("")) {	pageNum = "1";	}
			int currentPage = Integer.parseInt(pageNum);	     // currentPage  2
			int pageSize  = 8, blockSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1;   // 초기값 1 , currentPage  2 --> 11
			int endRow =  startRow + pageSize - 1;             // 초기값 10, currentPage  2 --> 20
			int condition = 0;
			if (searchkeyWord==null) {
				condition = 1; //키값이 있으면 totalcnt 에 sql 바뀜
				}
			int totCnt = pbd.getTotalCnt(condition , searchkeyWord , searchValue);
			int startNum = totCnt - startRow + 1;   // 38
			List<Board> list = pbd.list(startRow,endRow,searchkeyWord,searchValue); //일반게시글 리스트
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;  // 초기값 1 , currentPage  2 --> 2
			int endPage = startPage + blockSize -1;	                       // 초기값 10, currentPage  2 --> 11
			if (endPage > pageCnt) endPage = pageCnt;	
			
			
			//유저 정보 리스트
			UserInfoDao uid = UserInfoDao.getInstance();
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			UserInfo userinfo =  uid.select(id);
			request.setAttribute("userinfo", userinfo);
			
			
			//베스트게시글 리스트
			List<Board> bestList = pbd.bestList();
			request.setAttribute("bestList", bestList);
			
	
			//검색
			request.setAttribute("searchkeyWord", searchkeyWord);
			request.setAttribute("searchValue", searchValue);
			//사진첨부관련 경로
			String context = request.getContextPath(); 
			request.setAttribute("context", context);
		
			request.setAttribute("totCnt",   totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list",     list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			System.out.println("-----------------------------------------------"); 
			System.out.println("startNum-->" + startNum);        // /och16/list.do
			System.out.println("totCnt-->" + totCnt);            // /och16/list.do
			System.out.println("currentPage-->" + currentPage);  // /och16/list.do
			System.out.println("blockSize-->" + blockSize);      // /och16/list.do
			System.out.println("pageSize-->" + pageSize);        // /och16/list.do
			System.out.println("pageCnt-->" + pageCnt);          // /och16/list.do
			System.out.println("startPage-->" + startPage);      // /och16/list.do
			System.out.println("endPage-->" + endPage);          // /och16/list.do

	    } catch (Exception e) {
			System.out.println("e.getMessage()"+e.getMessage());
		}
	
		
		
		return "petboard/petList.jsp";
	}

}
