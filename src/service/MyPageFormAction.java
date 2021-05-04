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

public class MyPageFormAction implements CommandProcess {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
         
      try {
          UserBoardDao ubd = UserBoardDao.getInstance();
          String pageNum = request.getParameter("pageNum");//처음엔 null로 가져옴
          if(pageNum == null || pageNum.equals("")) pageNum = "1";
          int startRow = 1;
          int endRow = 10;
          String board_id = request.getParameter("board_id");
          String comm_id = request.getParameter("comm_id");
          
          
          HttpSession session = request.getSession();
          String id = (String) session.getAttribute("id");
          System.out.println("id->"+id);
          System.out.println("board_id->"+board_id);
          System.out.println("comm_id->"+comm_id);
     //반려정보들 보내기
      			
    		
    		
         
           if(board_id == null && comm_id == null) {
        	   System.out.println("1시작");
               List<Board> userBoardList = ubd.list(startRow,endRow,id); 
               request.setAttribute("userBoardList",userBoardList);
               UserInfoDao uid = UserInfoDao.getInstance(); //dao
     			UserInfo userInfo = uid.select(id); //dto
     			request.setAttribute("userInfo", userInfo);
     			
     			MyPetDao mypet = MyPetDao.getInstance();
      			List<MyPet> pet_list = mypet.getMyPets(id); 
    			request.setAttribute("pet_list", pet_list);
     			
            }else if(id.equals(board_id) || id.equals(comm_id)) {
            	System.out.println("2시작");
               List<Board> userBoardList = ubd.list(startRow,endRow,id);
               
               request.setAttribute("userBoardList",userBoardList);
               UserInfoDao uid = UserInfoDao.getInstance(); //dao
     			UserInfo userInfo = uid.select(id); //dto
     			request.setAttribute("userInfo", userInfo);
     			
     			MyPetDao mypet = MyPetDao.getInstance();
      			List<MyPet> pet_list = mypet.getMyPets(id);
      			request.setAttribute("pet_list", pet_list);
    			
               //내 아이디
            }else if(!id.equals(board_id) && comm_id == null) {
            	System.out.println("3시작");
               List<Board> userBoardList = ubd.list(startRow,endRow,board_id); 
               request.setAttribute("userBoardList",userBoardList);
               UserInfoDao uid = UserInfoDao.getInstance(); //dao
               UserInfo userInfo = uid.select(id); //dto
               request.setAttribute("userInfo", userInfo);
               UserInfo userBoardInfo = uid.select(board_id);
               System.out.println("userBoardInfo - > " + userBoardInfo.getId());
               request.setAttribute("userBoardInfo", userBoardInfo);
               
               MyPetDao mypet = MyPetDao.getInstance();
     			List<MyPet> pet_list_board = mypet.getMyPets(board_id);
     			request.setAttribute("pet_list_board", pet_list_board);
   			
               
               
               //게시판
            }else if(!id.equals(comm_id) && board_id == null) {
            	System.out.println("4시작");
               List<Board> userBoardList = ubd.list(startRow,endRow,comm_id); 
               request.setAttribute("userBoardList",userBoardList);
               UserInfoDao uid = UserInfoDao.getInstance(); //dao
               UserInfo userInfo = uid.select(id); //dto
               
               MyPetDao mypet = MyPetDao.getInstance();
               List<MyPet> pet_list_comm = mypet.getMyPets(comm_id);
               
               request.setAttribute("userInfo", userInfo);
               UserInfo userCommInfo = uid.select(comm_id);
               System.out.println("userCommInfo - > " + userCommInfo.getId());
               request.setAttribute("userCommInfo", userCommInfo);
               request.setAttribute("pet_list_comm", pet_list_comm);
               
               
               //댓글
               
               
               
     			 
   			
            }
       	
        
          request.setAttribute("pageNum", pageNum);
          request.setAttribute("board_id", board_id);
          request.setAttribute("comm_id", comm_id);
      } catch (Exception e) {
         System.out.println("MyPageFormAction 에러 =>"+e.getMessage());
         e.printStackTrace();
      }
      return "mypage/myPage.jsp";
   }
}