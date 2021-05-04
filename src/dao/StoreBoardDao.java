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

public class StoreBoardDao {
   private static StoreBoardDao instance;
   private StoreBoardDao() {}
   public static StoreBoardDao getInstance() {
	   if(instance == null) {
		   instance = new StoreBoardDao();
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
		   System.out.println("StoreBoardDao getConnection 오류" + e.getMessage());
		   e.printStackTrace();
	   }
	   return conn;    
   }
   public int getTotalCnt(int condition , String searchkeyWord ,String searchValue) throws SQLException {
	   int tot = 0;
	   String SQL = "";
	   //검색추가 condition
		if (condition == 1) SQL = "SELECT COUNT(*) FROM BOARD WHERE TYPE_NUM = 300 ";
		else 				SQL = "select count(*) from board where "+searchValue+" like'%"+searchkeyWord+"%' and TYPE_NUM = 300";
		System.out.println("StoreBorad getTotalCnt SQL ->>"+SQL);
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   try {
		    conn = getConnection();
		   pstmt = conn.prepareStatement(SQL);
		    rs = pstmt.executeQuery();
		   if(rs.next())  tot = rs.getInt(1);
	   }catch(Exception e) {
		   System.out.println("StoreBoardDao getTotalCnt 에러 => "+ e.getMessage());
		   e.printStackTrace();
	   }finally {
		    if (rs !=null) rs.close();
		    if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
	   }
	   
	   return tot;
   }
   public Board select(int bd_num) throws SQLException {
	   String SQL = "SELECT * FROM BOARD WHERE BD_NUM = ? AND TYPE_NUM=300";
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Board board = new Board();
	   try {
		    conn = getConnection();
		   pstmt = conn.prepareStatement(SQL);
		    pstmt.setInt(1, bd_num);
		     rs  = pstmt.executeQuery();
		  if(rs.next()) {
			  board.setId(rs.getString(1));
			  board.setBd_num(rs.getInt(2));
			  board.setType_num(rs.getInt(3));
			  board.setBd_title(rs.getString(4));
			  board.setBd_cont(rs.getString(5));
			  board.setBd_video(rs.getString(6));
			  board.setBd_pic(rs.getString(7));
			  board.setBd_view(rs.getInt(8));
			  board.setBd_like(rs.getInt(9));
			  board.setBd_date(rs.getString(10));
			  board.setS_score(Math.round(rs.getDouble(11)*10)/10.0);
			  board.setS_cnt(rs.getInt(12));
			  board.setS_price(rs.getInt(13));
		  }
	   }catch(Exception e) {
		   System.out.println("StoreBoardDao select메소드 에러 =>"+e.getMessage());		   
		   e.printStackTrace();
	   }finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
	   
	   
	   return board;
   }
   
   public StBuy select(int bd_num, String id) throws SQLException {
	   String SQL = "SELECT s_score from stbuy WHERE BD_NUM = ? AND sbuy_id=?";
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   StBuy stbuy = new StBuy();
	   try {
		    conn = getConnection();
		   pstmt = conn.prepareStatement(SQL);
		    pstmt.setInt(1, bd_num);
		    pstmt.setString(2, id);
		     rs  = pstmt.executeQuery();
		  if(rs.next()) {
	
			  
			  stbuy.setS_score(Math.round(rs.getDouble(1)*10)/10.0);
		  }
	   }catch(Exception e) {
		   System.out.println("StoreBoardDao select메소드 에러 =>"+e.getMessage());		   
		   e.printStackTrace();
	   }finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
	   
	   
	   return stbuy;
   }
   public void viewCount(int bd_num) throws SQLException {
	   String SQL = "UPDATE BOARD SET BD_VIEW=BD_VIEW+1 WHERE BD_NUM = ? AND TYPE_NUM = 300";
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   
	   try {
		    conn = getConnection();
		   pstmt = conn.prepareStatement(SQL);
		   pstmt.setInt(1, bd_num);
		   pstmt.executeUpdate();
		   
	   }catch(Exception e) {
		   System.out.println("StoreBoardDao viewCount메소드 에러 =>"+e.getMessage());
		   e.printStackTrace();
	   }finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
   }
   public String getDate() throws SQLException {
	   String SQL = "SELECT TO_CHAR(SYSTIMESTAMP,'YYYY-MM-DD HH24:MI')FROM DUAL";
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   try {
		    conn = getConnection();
		   pstmt = conn.prepareStatement(SQL);
		   rs = pstmt.executeQuery();
		   if(rs.next()) {
			   
			   return rs.getString(1);
		   }
	   }catch(Exception e) {
		   e.printStackTrace();
	   }finally {
		   if (rs !=null) rs.close();
		    if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
	   }
	   
	   return "";
   }
   public int insert(Board board) throws SQLException {
	   int result = 0;
	   String SQL = "INSERT INTO BOARD(ID,BD_NUM,TYPE_NUM,BD_TITLE,BD_CONT,BD_PIC,"
	   		     +  "BD_VIEW,BD_LIKE,BD_DATE,S_SCORE,S_CNT,S_PRICE)"
	   		      + " VALUES(?,BD_NUM_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?)"; //12
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   try {
		    conn = getConnection();
		   pstmt = conn.prepareStatement(SQL);
		   pstmt.setString(1, board.getId());
		   pstmt.setInt(2, board.getType_num());
		   pstmt.setString(3,board.getBd_title());
		   pstmt.setString(4, board.getBd_cont());
		   pstmt.setString(5,board.getBd_pic());
		   pstmt.setInt(6, 0);
		   pstmt.setInt(7, 0);
		   pstmt.setString(8, getDate());
		   pstmt.setDouble(9,0);
		   pstmt.setInt(10, board.getS_cnt());
		   pstmt.setInt(11, board.getS_price());
		   
		   result = pstmt.executeUpdate();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }finally {
		   if(pstmt != null) pstmt.close();
		   if(conn != null) conn.close();
	   }
	   
	   return result;
   }
   public int delete(int bd_num, int type_num) throws SQLException {
	   int result = 0;
	   
	   String SQL1 = "SELECT * FROM STBUY WHERE BD_NUM = ? AND TYPE_NUM = ?";
	   String SQL2 = "SELECT * FROM COMMENTS WHERE BD_NUM = ? AND TYPE_NUM = ?";
	   String SQL3 = "DELETE FROM STBUY WHERE BD_NUM = ? AND TYPE_NUM = ?";
	   String SQL4 = "DELETE FROM COMMENTS WHERE BD_NUM = ? AND TYPE_NUM = ?";
	   String SQL5 = "DELETE FROM BOARD WHERE BD_NUM = ? AND TYPE_NUM = ?";
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   try {
		    conn = getConnection(); 
		    pstmt = conn.prepareStatement(SQL1);
		    pstmt.setInt(1, bd_num);
		    pstmt.setInt(2, type_num);
		    rs = pstmt.executeQuery();
		    if(rs.next()) {
		       rs.close();
		       pstmt.close();
		       pstmt = conn.prepareStatement(SQL3);
		       pstmt.setInt(1, bd_num);
		       pstmt.setInt(2, type_num);
		       pstmt.executeUpdate();
		    }
		    rs.close();
		    pstmt.close();
		    pstmt = conn.prepareStatement(SQL2);
		    pstmt.setInt(1, bd_num);
		    pstmt.setInt(2, type_num);
		    rs = pstmt.executeQuery();
		    if(rs.next()) {
		    	rs.close();
		        pstmt.close();
		        pstmt = conn.prepareStatement(SQL4);
		        pstmt.setInt(1, bd_num);
			    pstmt.setInt(2, type_num);
			    pstmt.executeUpdate();			    
		    }
		    rs.close();
		    pstmt.close();
		    pstmt = conn.prepareStatement(SQL5);
		    pstmt.setInt(1, bd_num);
		    pstmt.setInt(2, type_num);
		    result = pstmt.executeUpdate();
	   }catch(Exception e) {
		   System.out.println("StoreBoardDao delete 에러 =>"+e.getMessage());
		   e.printStackTrace();
	   }finally {
		   if(pstmt != null) pstmt.close();
		   if(conn != null) conn.close();
	   }
	   
	   return result;
   }
     
   
   public List<Board> list(int startRow,int endRow , String searchkeyWord , String searchValue) throws SQLException{
	   List<Board> list = new ArrayList<Board>();
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   //검색용추가
	   if (searchkeyWord != null) {
		   String SQL = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM ( SELECT b.* , (SELECT count(*) "
	   		       + "FROM comments c WHERE b.id =  c.id AND b.bd_num = c.bd_num ) comments_count "
	   		       + "FROM  BOARD b WHERE "+searchValue+" like '%"+searchkeyWord+"%' AND b.TYPE_NUM = 300 ORDER BY BD_NUM DESC) A)"
	   		       + "WHERE RN BETWEEN ? AND ?";
		   System.out.println("Store List<Board> list sql->"+SQL);
		   	conn = getConnection();
		    pstmt = conn.prepareStatement(SQL);
		    pstmt.setInt(1, startRow);
		    pstmt.setInt(2, endRow);
	   }else {
		   String SQL = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM ( SELECT b.* , (SELECT count(*) "
	   		       + "FROM comments c WHERE b.id =  c.id AND b.bd_num = c.bd_num ) comments_count "
	   		       + "FROM  BOARD b WHERE b.TYPE_NUM = 300 ORDER BY BD_NUM DESC) A) WHERE RN BETWEEN ? AND ?";
		   System.out.println("Store List<Board> list sql->"+SQL);
		   	conn = getConnection();
		    pstmt = conn.prepareStatement(SQL);
		    pstmt.setInt(1, startRow);
		    pstmt.setInt(2, endRow);
	   }
	   try {
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
		    		board.setS_score(Math.round(rs.getDouble(12)*10)/10.0);
		    		board.setS_cnt(rs.getInt(13));
		    		board.setS_price(rs.getInt(14));
		    		board.setComments_count(rs.getInt(16));
		    		list.add(board);
		    	}while(rs.next());
		    	
		    }
	   }catch(Exception e) {
		   System.out.println("StoreBoardDao list메소드에서 에러 =>" + e.getMessage());
		   e.printStackTrace();
	   }finally {
		    if (rs !=null) rs.close();
		    if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
	   }
	   
	   return list;
   }
   public void like(int bd_num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "update board set bd_like = bd_like+1 where bd_num=? and type_num=300";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bd_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("like Exception->"+e.getMessage());
		}finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
   public int update(Board board) throws SQLException {
	   int result = 0;
	   String SQL = "UPDATE BOARD SET BD_TITLE = ? , BD_CONT = ? , BD_PIC = ? , S_CNT = ? , "
	   		      + "S_PRICE = ? WHERE BD_NUM = ? AND TYPE_NUM = 300";
	   Connection conn = null;
	  PreparedStatement pstmt = null;
	  
	   try {
		    conn = getConnection();
		    pstmt = conn.prepareStatement(SQL);
		    pstmt.setString(1,board.getBd_title());
		    pstmt.setString(2, board.getBd_cont());
		    pstmt.setString(3, board.getBd_pic());
		    pstmt.setInt(4, board.getS_cnt());
		    pstmt.setInt(5,board.getS_price());
		    pstmt.setInt(6, board.getBd_num());
		    result = pstmt.executeUpdate();
	   }catch(Exception e) {
		   System.out.println("StoreBoardDao update 에러 =>"+e.getMessage());
		   e.printStackTrace();
	   }finally {
		   if(pstmt != null) pstmt.close();
		   if(conn != null) conn.close();
	   }
	   
	   return result;
   }
   public List<Board> bestStoreList() throws SQLException{
	   List<Board> bestStoreList = new ArrayList<Board>();
	   String SQL = "SELECT * FROM "
				+ "(SELECT ROWNUM RN, A.* FROM "
				+ "( SELECT b.* , (SELECT count(*) FROM comments c WHERE b.id =  c.id AND b.bd_num = c.bd_num ) "
				+ " comments_count FROM BOARD b WHERE b.TYPE_NUM = 300 "
				+ "ORDER BY S_SCORE DESC) A) WHERE RN BETWEEN 1 AND 8";
	   
	   
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   try {
		    conn = getConnection();
		   pstmt = conn.prepareStatement(SQL);
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
				   board.setS_score(Math.round(rs.getDouble(12)*10)/10.0);
				   board.setS_cnt(rs.getInt(13));
				   board.setS_price(rs.getInt(14));
				   board.setComments_count(rs.getInt(16));
				   bestStoreList.add(board);
			   }while(rs.next());
		   }
	   }catch(Exception e) {
		   System.out.println("List<Board>bestStoreList 에러 =>"+e.getMessage());
		   e.printStackTrace();
	   }finally {
		    if (rs !=null) rs.close();
		    if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
	   }
	   
	   return bestStoreList;
   }
   
   public int buyInfoIf(int bd_num,String id) throws SQLException {
	   int buyIfCount = 0;
	   String SQL = "SELECT * FROM STBUY WHERE BD_NUM = ? AND TYPE_NUM = 300 AND SBUY_ID = ?";
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
	   try {
		    conn = getConnection();
		   pstmt = conn.prepareStatement(SQL);
		   pstmt.setString(2, id);
		   pstmt.setInt(1, bd_num);
		   rs = pstmt.executeQuery();
		   if(rs.next()) {
			   buyIfCount = 1;
		   }else {
			   buyIfCount = 0;
		   }
	   }catch(Exception e) {
		   System.out.println("StoreBoardDao buyInfoIf 에러 =>"+e.getMessage());
		   e.printStackTrace();
	   }finally {
		   if (rs !=null) rs.close();
		   if (pstmt != null) pstmt.close();
		if (conn !=null) conn.close();
	   }
	   return buyIfCount;
   }
   
   
   
}
