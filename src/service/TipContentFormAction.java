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
import dao.TipBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class TipContentFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pageNum = request.getParameter("pageNum");		
		int bd_num = Integer.parseInt(request.getParameter("bd_num"));
		int type_num = Integer.parseInt(request.getParameter("type_num"));
		String context = request.getContextPath();
		
		
		try {
			//유저 정보 리스트
	         UserInfoDao uid = UserInfoDao.getInstance();
	         UserInfo userinfo =  uid.select(id);
	         request.setAttribute("userinfo", userinfo);
			//댓글 리스트
			String commPageNum = request.getParameter("commPageNum");
			if(commPageNum == null || commPageNum.equals("")) commPageNum = "1";
			int commCurrentPage = Integer.parseInt(commPageNum);
			int commPageSize = 6, commBlockSize = 5;
			CommentsDao cd = CommentsDao.getInstance();
			int commStartRow = (commCurrentPage-1)*commPageSize+1;
			int commEndRow = commStartRow + commPageSize -1;
			int commTotCnt = cd.getCommTotalCnt(bd_num,type_num);
			int commStartNum = commTotCnt - commStartRow +1;
			List<Comments> commList = cd.commList(commStartRow,commEndRow,bd_num,type_num);
			int commPageCnt = (int)Math.ceil((double)commTotCnt/commPageSize);
			int commStartPage =(int)(commCurrentPage-1)/commBlockSize*commBlockSize +1;
			int commEndPage = commStartPage + commBlockSize -1;
			if(commEndPage > commPageCnt) commEndPage = commPageCnt;
			//게시글 보여주기
			TipBoardDao bd = TipBoardDao.getInstance();
			bd.view(bd_num);  
			Board board = bd.select(bd_num);			
			request.setAttribute("pageNum", pageNum);
			//request.setAttribute("id", id);
			request.setAttribute("bd_num", bd_num);
			request.setAttribute("type_num", type_num);
			request.setAttribute("board", board);
			request.setAttribute("context", context);
			request.setAttribute("commTotCnt", commTotCnt); 
			request.setAttribute("commList", commList); 
			request.setAttribute("commStartNum", commStartNum);
			request.setAttribute("commPageNum", commPageNum);
			request.setAttribute("commCurrentPage", commCurrentPage);
			request.setAttribute("commPageSize", commPageSize);
			request.setAttribute("commBlockSize", commBlockSize);
			request.setAttribute("commPageCnt", commPageCnt);
			request.setAttribute("commStartPage", commStartPage);
			request.setAttribute("commEndPage", commEndPage);
		} catch (Exception e) {
			System.out.println("requestPro exception ->"+e.getMessage());
		}

		return "tipboard/tipCont.jsp";
	}

}
