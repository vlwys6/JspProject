package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StBuy;
import dao.StBuyDao;

public class MyStBuyScoreUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			 request.setCharacterEncoding("utf-8");
			 
			 HttpSession session = request.getSession();
			 String sbuy_id = (String)session.getAttribute("id");
			 int sbuy_num = Integer.parseInt(request.getParameter("sbuy_num"));
			 double s_score = Double.parseDouble(request.getParameter("s_score"));
			 int bd_num = Integer.parseInt(request.getParameter("bd_num"));
			 StBuyDao stbd = StBuyDao.getInstance();			 
			 
			 int result = stbd.updateScore(s_score,sbuy_num,bd_num,sbuy_id);
			 
			 request.setAttribute("result", result);
			 
		}catch(Exception e) {
			System.out.println("MyStBuyScoreUpdateProAction 에러 =>"+e.getMessage());
			e.printStackTrace();
		}
		return "mypage/myStBuyScoreUpdatePro.jsp";
	}

}
