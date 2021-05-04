package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.TipBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class TipListFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			//유저 정보 리스트
	         UserInfoDao uid = UserInfoDao.getInstance();
	         UserInfo userinfo =  uid.select(id);
	         request.setAttribute("userinfo", userinfo);
	       //사진첨부관련 경로
				String context = request.getContextPath(); 
				request.setAttribute("context", context);
			String pageNum = request.getParameter("pageNum");//처음엔 null로 가져옴
			String searchkeyWord = request.getParameter("searchkeyWord");
			String searchValue = request.getParameter("searchValue");
			System.out.println("팁리스트프롬액션키워드 ->"+searchkeyWord);
			System.out.println("팁리스트프롬액션벨류 ->"+searchValue);
			
			
			if(pageNum == null || pageNum.equals("")) pageNum="1";
			//현재페이지
			int currentPage = Integer.parseInt(pageNum);
			// 게시물 한번에 보여주는수 / 밑에 페이지 넘버
			int pageSize = 10, blockSize = 10;
			TipBoardDao bd = TipBoardDao.getInstance();
			int startRow = (currentPage-1)*pageSize+1;  // 초기값:1
			int endRow = startRow + pageSize-1;			// 초기값:10
			int condition = 0;
			if (searchkeyWord==null) {
				condition = 1; //키값이 있으면 totalcnt 에 sql 바뀜
				}
			int totCnt = bd.getTotalCnt(condition , searchkeyWord , searchValue); //토탈 게시물 수 
			int startNum = totCnt - startRow+1;  //38 ->최신글을 가장 먼저 띄워주기위한 변수
			List<Board> list = bd.list(startRow,endRow,searchkeyWord,searchValue);
			// 페이지 수               올림 (반올림이나 내림을 하면 남은 게시물이 올라오지를 못함)
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize); 
			int startPage = (int)(currentPage-1)/blockSize*blockSize+1; //초기값: 1
			int endPage = startPage + blockSize - 1;                    //초기값: 10
			//쓸데없는 페이지 번호를 안나타나게 하기위한 로직
			if(endPage > pageCnt) endPage = pageCnt;
			
			// 상단 고정
			List<Board> list2 = bd.list2();

			request.setAttribute("searchkeyWord", searchkeyWord);
			request.setAttribute("searchValue", searchValue);
			request.setAttribute("totCnt", totCnt); 
			request.setAttribute("list", list); 
			request.setAttribute("list2", list2); 
			request.setAttribute("startNum", startNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			System.out.println("==========================================");
			System.out.println("pageNum -> "+pageNum);
			System.out.println("currentPage -> "+currentPage);
			System.out.println("pageSize -> "+pageSize);
			System.out.println("blockSize ->" + blockSize);
			System.out.println("pageCnt -> "+pageCnt);
			System.out.println("startPage -> "+startPage);
			System.out.println("endPage -> "+endPage);
			System.out.println("id -> "+id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "tipboard/tipList.jsp";
	}

}
