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
import dao.StBuy;
import dao.StoreBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class StoreContentFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			 int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			 int type_num = Integer.parseInt(request.getParameter("type_num"));
			 // 여기서 문제 type_num에서 걸려버림 그러면 뭔가가 잘못됫다는뜻
			 String pageNum = request.getParameter("pageNum");
			 int currentPage = Integer.parseInt(pageNum);
			 StoreBoardDao sbd = StoreBoardDao.getInstance();
			 String context = request.getContextPath();
			 sbd.viewCount(bd_num);
			 Board board = sbd.select(bd_num);
			 CommentsDao cd = CommentsDao.getInstance();
			 int commTotCnt = cd.getCommTotalCnt(bd_num, type_num);
			 System.out.println("commTotCnt =>"+commTotCnt);
			 System.out.println("type_num =>"+type_num);
			 System.out.println("bd_num =>"+bd_num);
			 String commPageNum = request.getParameter("commPageNum");
			 if(commPageNum == null || commPageNum.equals("")) {commPageNum = "1";}
			 int commCurrentPage = Integer.parseInt(commPageNum);
			 int commPageSize = 10 ,commBlockSize = 10;
			 int commStartRow = (commCurrentPage - 1) * commPageSize + 1;			 
			 int commEndRow = commStartRow + commPageSize - 1;
			 int commStartNum = commTotCnt - commStartRow + 1;
			 
			 List<Comments> commList = cd.commList(commStartRow, commEndRow, bd_num,type_num);
			 
			 HttpSession session = request.getSession();
			 String id = (String)session.getAttribute("id");
			 
			 int buyIfCount = sbd.buyInfoIf(bd_num,id);
			 request.setAttribute("buyIfCount",buyIfCount);
			 
			 StBuy stbuy = sbd.select(bd_num, id);
			 request.setAttribute("stbuy", stbuy);
			 
			 //유저 정보 리스트
			 UserInfoDao uid = UserInfoDao.getInstance();	
			 UserInfo userinfo =  uid.select(id);
			 request.setAttribute("userinfo", userinfo);
			

			 int commPageCnt = (int)Math.ceil((double)commTotCnt/commPageSize);
			 int commStartPage = (int)(commCurrentPage-1)/commBlockSize * commBlockSize + 1;
			 int commEndPage = commStartPage + commBlockSize - 1;
			 if(commEndPage > commPageCnt) commEndPage = commPageCnt;
			 request.setAttribute("bd_num", bd_num);
			 request.setAttribute("pageNum", pageNum);
			 request.setAttribute("currentPage", currentPage);
			 request.setAttribute("commPageNum", commPageNum);
			 request.setAttribute("type_num", type_num);
			 request.setAttribute("board", board);
			 request.setAttribute("commList", commList);
			 request.setAttribute("context", context);
			 request.setAttribute("commTotCnt", commTotCnt);
			 request.setAttribute("commCurrentPage", commCurrentPage);
			 request.setAttribute("commStartNum", commStartNum);
			 request.setAttribute("commBlockSize", commBlockSize);
			 request.setAttribute("commPageCnt", commPageCnt);
			 request.setAttribute("commStartPage", commStartPage);
			 request.setAttribute("commEndPage", commEndPage);
		}catch(Exception e) {
			System.out.println("StoreContentFormAction 에러 =>" +e.getMessage());
			e.printStackTrace();
		}
		
		
		return "storeboard/storeCont.jsp";
	}

}
