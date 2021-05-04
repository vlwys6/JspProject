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

public class StBuyDao {
  private static StBuyDao instance;
  private StBuyDao() {}
  public static StBuyDao getInstance() {
	  if(instance == null) {
		  instance = new StBuyDao();
	  }	  
	  return instance;
  }
  public Connection getConnection() {
	  Connection conn = null;
	  try {
		   Context ctx = new InitialContext();
		  DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
		  conn = ds.getConnection();
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  return conn;
  }
  public int insert(StBuy stbuy) throws SQLException {
	  int result = 0;
	  String SQL = "INSERT INTO STBUY(ID,BD_NUM,SBUY_NUM,TYPE_NUM,S_CNT,S_SCORE,BUY_CHK,BUY_INFO,SBUY_ID,BUY_PAYPRICE,BUY_DATE,BUY_ADDRESS) "
	  		      + "VALUES(?,?,SBUY_NUM_SEQ.NEXTVAL,?,?,?,?,?,?,?,TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI'),?)";
	  System.out.println("StBuyDao insert SQL=>"+SQL);
	  System.out.println("StBuyDao insert  stbuy.getBuy_address()=>"+ stbuy.getBuy_address());
     Connection conn = null;
      PreparedStatement pstmt = null;
      try {
    	   conn = getConnection();
    	  pstmt = conn.prepareStatement(SQL);
    	  pstmt.setString(1, stbuy.getId());
    	  pstmt.setInt(2, stbuy.getBd_num());
    	  pstmt.setInt(3,stbuy.getType_num());
    	  pstmt.setInt(4,stbuy.getS_cnt());
    	  pstmt.setDouble(5, 0);
    	  pstmt.setString(6,"n");
    	  pstmt.setString(7, stbuy.getBuy_Info());  	  
    	  pstmt.setString(8,stbuy.getSbuy_id());
    	  pstmt.setInt(9, stbuy.getBuy_payprice());
    	  pstmt.setString(10, stbuy.getBuy_address());
    	  result = pstmt.executeUpdate();
    	  
      }catch(Exception e) {
    	  System.out.println("StBuyDao insert 에러 =>"+e.getMessage());
    	  e.printStackTrace();
      }finally {
		    if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
      }
	  return result;
  }
  public List<StBuy> stbuyList(String id) throws SQLException{
	  List<StBuy> buyList = new ArrayList<StBuy>();
	  Connection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  
	  
      String SQL1 = "SELECT * FROM STBUY order by buy_date desc";
	  
	  String SQL2 = "SELECT * FROM STBUY WHERE SBUY_ID = ? order by buy_date desc";
	  
	  try {
		   conn = getConnection();
		  if(id.equals("admin1234")) {
			  pstmt = conn.prepareStatement(SQL1);
		  }else {
			  pstmt = conn.prepareStatement(SQL2);
			  pstmt.setString(1, id);
		  }
		   rs = pstmt.executeQuery();
		   if(rs.next()) {
			   do {
				   StBuy stbuy = new StBuy();
				   stbuy.setId(rs.getString(1));
				   stbuy.setBd_num(rs.getInt(2));
				   stbuy.setSbuy_num(rs.getInt(3));
				   stbuy.setType_num(rs.getInt(4));
				   stbuy.setS_cnt(rs.getInt(5));
				   stbuy.setS_score(rs.getDouble(6));
				   stbuy.setBuy_chk(rs.getString(7));
				   stbuy.setBuy_Info(rs.getString(8));
				   stbuy.setSbuy_id(rs.getString(9));
				   stbuy.setBuy_payprice(rs.getInt(10));
				   stbuy.setBuy_date(rs.getString(11));
				   stbuy.setBuy_address(rs.getString(12));
				   buyList.add(stbuy);
			   }while(rs.next());
		   }
	  }catch(Exception e) {
		  System.out.println("StBuyDao stbuyList 에러=>"+e.getMessage());
		  e.printStackTrace();
	  }finally {
		  if (rs !=null) rs.close();
		  if (pstmt != null) pstmt.close();
	      if (conn !=null) conn.close();
	  }
	  return buyList;
  }
  public int updateScore(double s_score,int sbuy_num,int bd_num,String sbuy_id) throws SQLException {
	  int result = 0;
	  Double avgS_score = null;
	  String SQL1 = "UPDATE STBUY SET S_SCORE = ? WHERE SBUY_NUM = ? AND BD_NUM = ? AND SBUY_ID = ?";
	  String SQL2 = "SELECT BD_NUM ,AVG(S_SCORE) FROM STBUY GROUP BY BD_NUM HAVING BD_NUM = ?";
	  String SQL3 = "UPDATE BOARD SET S_SCORE = ? WHERE BD_NUM = ? AND TYPE_NUM = 300";
	  Connection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  try {
		   conn = getConnection();
		  pstmt = conn.prepareStatement(SQL1);
		  pstmt.setDouble(1, s_score);
		  pstmt.setInt(2, sbuy_num);
		  pstmt.setInt(3, bd_num);
		  pstmt.setString(4, sbuy_id);
		  result = pstmt.executeUpdate();
		  pstmt.close();
		  pstmt = conn.prepareStatement(SQL2);
		  pstmt.setInt(1, bd_num);
		  rs = pstmt.executeQuery();
		  if(rs.next()) {
			  avgS_score = rs.getDouble(2);
		  }
		  rs.close();
		  pstmt.close();
		  pstmt = conn.prepareStatement(SQL3);
		  pstmt.setDouble(1,avgS_score);
		  pstmt.setInt(2, bd_num);
		  pstmt.executeUpdate();
	  }catch(Exception e) {
		  System.out.println("StBuyDao updateScore 에러=>"+e.getMessage());
		  e.printStackTrace();
	  }finally {
		  if (pstmt != null) pstmt.close();
		  if (conn !=null) conn.close();
	  }
	  return result;
  }
  
  public int totCnt(String id) throws SQLException {
	     int result = 0;
	     Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     String sql1 = "select count(*) from stbuy";
	     String sql2 = "select count(*) from stbuy where sbuy_id=?";
	     String a = "admin1234";
	     try {
	      conn = getConnection();
	      if(id.equals(a)) {
	    	  pstmt = conn.prepareStatement(sql1);
	    	  rs = pstmt.executeQuery();
	    	  if(rs.next()) result = rs.getInt(1);
	    	  pstmt.close();
	    	  rs.close();
	      }else {
	      pstmt = conn.prepareStatement(sql2);
	      pstmt.setString(1, id);
	      rs = pstmt.executeQuery();
	      if(rs.next()) result = rs.getInt(1);
	      }
	   } catch (Exception e) {
	      System.out.println("totCnt Exception->"+e.getMessage());
	   }finally {
	       if (rs !=null) rs.close();
	        if (pstmt != null) pstmt.close();
	         if (conn !=null) conn.close();
	   }
	     return result;
	  }
}
