
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserBoardDao {
  private static UserBoardDao instance;
  private UserBoardDao() {}
  public static UserBoardDao getInstance() {
	  if(instance == null) {
		  instance = new UserBoardDao();
	  }	  
	  return instance;
  }
  
  public Connection getConnection() {
	  Connection conn = null;
	  try {
		   Context ctx = new InitialContext();
		  DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
		  conn = ds.getConnection();
	  }catch(Exception e) {
		  e.printStackTrace();
	  }	  
	  return conn; 
  }
  public int getTotalCnt(String id) {
	  int tot = 0;
	  String SQL = "SELECT COUNT(*) FROM BOARD WHERE ID = ?";
	  Connection conn = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 try {
		  conn = getConnection();
		 pstmt = conn.prepareStatement(SQL);
		 pstmt.setString(1, id);
		 rs = pstmt.executeQuery();
		 if(rs.next()) {
			 tot = rs.getInt(1);
		 }
	 }catch(Exception e) {
		 System.out.println("UserBoardDao getTotalCnt 에러=>"+e.getMessage());
		 e.printStackTrace();
	 }
	  return tot;
  }
  public List<Board> list(int startRow,int endRow,String id) throws SQLException{
	  List<Board> userBoardList = new ArrayList<Board>();
	  Connection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  String SQL = "SELECT * FROM(SELECT ROWNUM RN, A.* FROM (SELECT B.*, (SELECT COUNT(*) "
	  		     + "FROM COMMENTS C WHERE B.ID = C.ID AND B.BD_NUM = C.BD_NUM) COMMENTS_COUNT "
	  		     + "FROM BOARD B WHERE B.ID = ? ORDER BY BD_NUM) A) WHERE RN BETWEEN ? AND ?";
	  try {
		   conn = getConnection();
		   pstmt = conn.prepareStatement(SQL);
		   pstmt.setString(1, id);
		   pstmt.setInt(2, startRow);
		   pstmt.setInt(3, endRow);
		   rs = pstmt.executeQuery();
		   if(rs.next()) {
			   do {
				   Board board = new Board();
				   board.setId(rs.getString(2));
		    		board.setBd_num(rs.getInt(3));
		    		board.setType_num(rs.getInt(4));
		    		board.setBd_title(rs.getString(5));
		    		board.setBd_cont(rs.getString(6));
		    		board.setBd_video(rs.getString(7));
		    		board.setBd_pic(rs.getString(8));
		    		board.setBd_view(rs.getInt(9));
		    		board.setBd_like(rs.getInt(10));
		    		board.setBd_date(rs.getString(11));
		    		board.setS_score(rs.getDouble(12));
		    		board.setS_cnt(rs.getInt(13));
		    		board.setS_price(rs.getInt(14));
		    		board.setComments_count(rs.getInt(16));
		    		userBoardList.add(board);
			   }while(rs.next());			   
		   }
	  }catch(Exception e) {
		  System.out.println("UserBoardDao List 에러 =>" +e.getMessage());
		  e.printStackTrace();
	  }finally {
		  if(rs != null) rs.close();
		  if(pstmt != null) pstmt.close();
		  if(conn != null) conn.close();
	  }
	  
	  return userBoardList;
  }
}