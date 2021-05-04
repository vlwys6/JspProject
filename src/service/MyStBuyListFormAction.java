package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StBuy;
import dao.StBuyDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class MyStBuyListFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			 request.setCharacterEncoding("utf-8");
			 HttpSession session = request.getSession();
			 String id = (String) session.getAttribute("id");
			 
			 StBuyDao stbd = StBuyDao.getInstance();
			 List<StBuy> buyList = stbd.stbuyList(id);
			 
			 int totCnt = stbd.totCnt(id);
	         request.setAttribute("totCnt", totCnt);
			 
			//유저 정보 리스트
				UserInfoDao uid = UserInfoDao.getInstance();
				
				UserInfo userinfo =  uid.select(id);
				request.setAttribute("userinfo", userinfo);
				
				//사진첨부관련 경로
				String context = request.getContextPath(); 
				request.setAttribute("context", context);
			 
			 request.setAttribute("buyList", buyList);
		}catch(Exception e) {
			System.out.println("MyStBuyListFormAction 에러=>" +e.getMessage());
		   e.printStackTrace();	
		}		
		return "mypage/myStBuyListForm.jsp";
	}

}
