package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.StBuy;
import dao.StBuyDao;
import dao.StoreBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class SBuyWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			 request.setCharacterEncoding("utf-8");
			 HttpSession session = request.getSession();
			 String pageNum = request.getParameter("pageNum");
			 int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			 String board_id = request.getParameter("board_id");
			 int type_num = Integer.parseInt(request.getParameter("type_num"));
			 int s_cnt = Integer.parseInt(request.getParameter("s_cnt"));
			 String sbuy_id =  (String)session.getAttribute("id");
			 int s_price = Integer.parseInt(request.getParameter("s_price"));
			 int buy_payprice = s_cnt * s_price;
			 String buy_info = request.getParameter("buy_info");
			 String buy_address = request.getParameter("buy_address")+" "+request.getParameter("buy_detailaddr");
			 
			 System.out.println("SBuyWriteProAction buy_address->"+buy_address);
			 
			
			 
			//유저 정보 리스트
			UserInfoDao uid = UserInfoDao.getInstance();	
			String id = (String)session.getAttribute("id");
			UserInfo userinfo =  uid.select(id);
			request.setAttribute("userinfo", userinfo);
			
			//사진첨부관련 경로
			String context = request.getContextPath(); 
			request.setAttribute("context", context);
			 
			
			 StBuy stbuy = new StBuy();
			 StBuyDao stbd = StBuyDao.getInstance();
			 
			 stbuy.setBd_num(bd_num);
			 stbuy.setId(board_id);
			 stbuy.setType_num(type_num);
			 stbuy.setS_cnt(s_cnt);
			 stbuy.setSbuy_id(sbuy_id);
			 stbuy.setBuy_Info(buy_info);
			 stbuy.setBuy_payprice(buy_payprice);
			 stbuy.setBuy_address(buy_address);
			 
			 int result = stbd.insert(stbuy);
			 
			 request.setAttribute("pageNum", pageNum);
			 request.setAttribute("type_num", type_num);
			 request.setAttribute("bd_num", bd_num);
			 request.setAttribute("result", result);
			 request.setAttribute("stbuy", stbuy);
			 
			 
			 StoreBoardDao sbd = StoreBoardDao.getInstance(); 
			 Board storebd = sbd.select(bd_num);
			 request.setAttribute("storebd", storebd);
			
		
			 
		}catch(Exception e) {
			System.out.println("SBuyWriteProAction 에러=>"+e.getMessage());
			e.printStackTrace();
		}
		return "storeboard/sBuyWritePro.jsp";
	}

}
