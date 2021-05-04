package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.MyPet;
import dao.MyPetDao;
import dao.UserBoardDao;
import dao.UserInfo;
import dao.UserInfoDao;

public class MyPetListFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
		
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		 String board_id = request.getParameter("board_id");
         String comm_id = request.getParameter("comm_id");
         System.out.println("id->"+id);
         System.out.println("board_id->"+board_id);
         System.out.println("comm_id->"+comm_id);
		//구도회씨가 만진거
		//String id = request.getParameter("id");
		System.out.println("mypetlistform action 에 세션에있는 id 값 -> "+ id);
		String context = request.getContextPath();
		request.setAttribute("context", context);
		//반려 정보 가져오기 
		try {
			
			 UserBoardDao ubd = UserBoardDao.getInstance();
		    if(board_id.equals("") && comm_id.equals("")) {
	        	   System.out.println("mypetlist 1시작");
	               UserInfoDao uid = UserInfoDao.getInstance(); //dao
	     			UserInfo userInfo = uid.select(id); //dto
	     			request.setAttribute("userInfo", userInfo);
	     			
	     			
	     			request.setAttribute("board_id", board_id);
	     			request.setAttribute("comm_id", comm_id);
	     			MyPetDao mypet = MyPetDao.getInstance();
	      			List<MyPet> pet_list = mypet.getMyPets(id); 
	    			request.setAttribute("pet_list", pet_list);
	     			System.out.println("board_id -> "+board_id + "comm_id -> " + comm_id);
	            }else if(id.equals(board_id) || id.equals(comm_id)) {
	            	System.out.println("mypetlist 2시작");
	               
	               UserInfoDao uid = UserInfoDao.getInstance(); //dao
	     			UserInfo userInfo = uid.select(id); //dto
	     			request.setAttribute("userInfo", userInfo);
	     			
	     			MyPetDao mypet = MyPetDao.getInstance();
	      			List<MyPet> pet_list = mypet.getMyPets(id);
	      			request.setAttribute("pet_list", pet_list);
	      			System.out.println("board_id -> "+board_id + "comm_id -> " + comm_id);
	    			
	               //내 아이디
	            }else if(!id.equals(board_id) && comm_id.equals("")) {
	            	System.out.println("mypetlist 3시작");
	               UserInfoDao uid = UserInfoDao.getInstance(); //dao
	               UserInfo userInfo = uid.select(id); //dto
	               request.setAttribute("userInfo", userInfo);
	               UserInfo userBoardInfo = uid.select(board_id);
	               System.out.println("userBoardInfo - > " + userBoardInfo.getId());
	               request.setAttribute("userBoardInfo", userBoardInfo);
	               
	               request.setAttribute("comm_id", comm_id);
	               MyPetDao mypet = MyPetDao.getInstance();
	     			List<MyPet> pet_list_board = mypet.getMyPets(board_id);
	     			 request.setAttribute("pet_list_board", pet_list_board);
	     			System.out.println("board_id -> "+board_id + "comm_id -> " + comm_id);
	   			
	               
	               
	               //게시판
	            }else if(!id.equals(comm_id) && board_id.equals("")) {
	            	System.out.println("mypetlist 4시작");
	               UserInfoDao uid = UserInfoDao.getInstance(); //dao
	               UserInfo userInfo = uid.select(id); //dto
	               
	               MyPetDao mypet = MyPetDao.getInstance();
	               List<MyPet> pet_list_comm = mypet.getMyPets(comm_id);
	               
	               request.setAttribute("board_id", board_id);
	               request.setAttribute("userInfo", userInfo);
	               UserInfo userCommInfo = uid.select(comm_id);
	               System.out.println("userCommInfo - > " + userCommInfo.getId());
	               request.setAttribute("userCommInfo", userCommInfo);
	               request.setAttribute("pet_list_comm", pet_list_comm);
	               System.out.println("board_id -> "+board_id + "comm_id -> " + comm_id);
	               
	               //댓글
	               
	               
	               
	     			 
	   			
	            }
	       	
			
		} catch (Exception e) {
			System.out.println("MyPetListFormAction Exception->"+e.getMessage());
		}
		
		
		return "mypet/mypetList.jsp";
	}

}
