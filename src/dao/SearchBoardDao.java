package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SearchBoardDao {
   private static SearchBoardDao instance;

   private SearchBoardDao() {
   }

   public static SearchBoardDao getInstance() {
      if (instance == null) {
         instance = new SearchBoardDao();
      }
      return instance;
   }

   private Connection getConnection() {
      Connection conn = null;
      try {
         Context ctx = new InitialContext();
         DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
         conn = ds.getConnection();
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
      return conn;
   }

   public int getTotalCnt(int condition, String searchkeyWord, String searchValue) throws SQLException {
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      int tot = 0;
      String sql = "";
      if (condition == 1)
         sql = "select count(*) from board";
      else
         sql = "select count(*) from board where bd_title like'%" + searchkeyWord + "%'";
      System.out.println("SearchBorad getTotalCnt sql ->>" + sql);
      try {
         conn = getConnection();
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         if (rs.next())
            tot = rs.getInt(1);

      } catch (Exception e) {
         System.out.println(e.getMessage());
      } finally {
         if (rs != null)
            rs.close();
         if (stmt != null)
            stmt.close();
         if (conn != null)
            conn.close();
      }

      return tot;
   }

   public List<Board> list(int startRow,int endRow , String searchkeyWord , String searchValue) throws SQLException{
      List<Board> list = new ArrayList<Board>();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      if (searchkeyWord != null) {
         String sql = "SELECT * FROM "
               + "(SELECT ROWNUM RN, A.* FROM "
               + "( SELECT b.* , (SELECT count(*) FROM comments c WHERE b.id =  c.id AND b.bd_num = c.bd_num ) "
               + "comments_count FROM BOARD b WHERE bd_title like '%"+searchkeyWord+"%' and b.type_num != 400"
               + "ORDER BY BD_NUM DESC) A) WHERE RN BETWEEN ? AND ?";
         System.out.println("Search List<Board> list sql->"+sql);
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);
      }else {
         String sql = "SELECT * FROM "
               + "(SELECT ROWNUM RN, A.* FROM "
               + "( SELECT b.* , (SELECT count(*) FROM comments c WHERE b.id =  c.id AND b.bd_num = c.bd_num )"
               + " comments_count FROM BOARD b"
               + "ORDER BY BD_NUM DESC) A) WHERE RN BETWEEN ? AND ?";
         System.out.println("Search List<Board> list sql->"+sql);
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
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
               board.setBd_date(rs.getString(11).substring(0, 14));
               board.setS_score(rs.getDouble(12));
               board.setS_cnt(rs.getInt(13));
               board.setS_price(rs.getInt(14));
               board.setComments_count(rs.getInt(16));
               list.add(board);
            }while(rs.next());
         }
      } catch (Exception e) {
         System.out.println("List<Board> list exception ->"+e.getMessage());
      }finally {
         if(rs != null) rs.close();
         if(pstmt != null) pstmt.close();
         if(conn != null) conn.close();
      }
      return list;   
   }

   // 추천수
   public void like(int bd_num) throws SQLException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      String sql = "update board set bd_like = bd_like+1 where bd_num=?";
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bd_num);
         pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("like Exception->" + e.getMessage());
      } finally {
         if (pstmt != null)
            pstmt.close();
         if (conn != null)
            conn.close();
      }
   }

   public int insert(Board board) throws SQLException {
      int result = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      String sql = "insert into board values(?,bd_num_seq.nextval,?,?,?,?,?,?,?,to_char(sysdate, 'yy/mm/dd hh24:mi:ss'),?,?,?,?)";
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, board.getId());
         pstmt.setInt(2, board.getType_num());
         pstmt.setString(3, board.getBd_title());
         pstmt.setString(4, board.getBd_cont());
         pstmt.setString(5, board.getBd_video());
         pstmt.setString(6, board.getBd_pic());
         pstmt.setInt(7, board.getBd_view());
         pstmt.setInt(8, board.getBd_like());
         pstmt.setDouble(9, board.getS_score());
         pstmt.setInt(10, board.getS_cnt());
         pstmt.setDouble(11, board.getS_price());
         pstmt.setString(12, board.getBd_yn());
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("insert exception ->" + e.getMessage());
      } finally {
         if (pstmt != null)
            pstmt.close();
         if (conn != null)
            conn.close();
      }
      return result;
   }

   // 조회수
   public void view(int bd_num) throws SQLException {
      Connection conn = null;
      PreparedStatement pstmt = null;
      String sql = "update board set bd_view = bd_view+1 where bd_num=?";
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bd_num);
         pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("view exception->" + e.getMessage());
      } finally {
         if (pstmt != null)
            pstmt.close();
         if (conn != null)
            conn.close();
      }
   }

   public Board select(int bd_num) throws SQLException {
      Board board = new Board();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "select * from board where bd_num=?";
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bd_num);
         rs = pstmt.executeQuery();
         if (rs.next()) {
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
            board.setS_score(rs.getDouble(11));
            board.setS_cnt(rs.getInt(12));
            board.setS_price(rs.getInt(13));
         }
      } catch (Exception e) {
         System.out.println("select Exception->" + e.getMessage());
      } finally {
         if (rs != null)
            rs.close();
         if (pstmt != null)
            pstmt.close();
         if (conn != null)
            conn.close();
      }
      return board;
   }

   // 게시물 수정
    public int update(Board board) throws SQLException {
         int result = 0;
         String SQL = "UPDATE BOARD SET BD_TITLE = ? , BD_CONT = ? , BD_PIC = ? , S_CNT = ? , "
                     + "S_PRICE = ? WHERE BD_NUM = ?";
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
            System.out.println("SearchBoardDao update 에러 =>"+e.getMessage());
            e.printStackTrace();
         }finally {
        	 if(pstmt != null) pstmt.close();
        	 if(conn != null) conn.close();
         }
         
         return result;
      }

   // 삭제
   public int delete(int bd_num) throws SQLException {
      int result = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "select * from stbuy where bd_num=?";
      String sql2 = "select * from comments where bd_num=?";
      String sql3 = "delete from stbuy where bd_num=?";
      String sql4 = "delete from comments where bd_num=?";
      String sql5 = "delete from board where bd_num=?";
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bd_num);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            rs.close();
            pstmt.close();
            pstmt = conn.prepareStatement(sql3);
            pstmt.setInt(1, bd_num);
            pstmt.executeUpdate();
         }
         rs.close();
         pstmt.close();
         pstmt = conn.prepareStatement(sql2);
         pstmt.setInt(1, bd_num);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            rs.close();
            pstmt.close();
            pstmt = conn.prepareStatement(sql4);
            pstmt.setInt(1, bd_num);
            pstmt.executeUpdate();
         }
         rs.close();
         pstmt.close();
         pstmt = conn.prepareStatement(sql5);
         pstmt.setInt(1, bd_num);
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("delete Exception->" + e.getMessage());
      } finally {
         if (rs != null)
            rs.close();
         if (pstmt != null)
            pstmt.close();
         if (conn != null)
            conn.close();
      }
      return result;
   }
}