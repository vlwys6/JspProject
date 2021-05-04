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

public class PetBoardDao {
	
	//Singleton작성
		private static PetBoardDao instance;
		private PetBoardDao () {}
		public static PetBoardDao getInstance() {
			if (instance == null ) {
				instance = new PetBoardDao();
			} return instance;
		}
		
		private Connection getConnection() {
			Connection conn = null;
			try {
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
				conn = ds.getConnection();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			} return conn;
		}
		
		public int getTotalCnt(int condition , String searchkeyWord ,String searchValue) throws SQLException {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			int tot = 0;
			String sql = "";
			if (condition == 1) sql = "select count(*) from board where type_num = 100";
			else 				sql = "select count(*) from board where "+searchValue+" like'%"+searchkeyWord+"%' and type_num=100";
			System.out.println("TipBorad getTotalCnt sql ->>"+sql);
			try {
				conn = getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next()) tot = rs.getInt(1);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}
			
			return tot;
		}
		
		public List<Board> list(int startRow,int endRow , String searchkeyWord , String searchValue) throws SQLException {
						//리스트에 저~장~★
			List<Board> list = new ArrayList<Board>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			if (searchkeyWord != null) {
	            String sql = "SELECT * FROM "
	                  + "(SELECT ROWNUM RN, A.* FROM "
	                  + "( SELECT b.* , (SELECT count(*) FROM comments c WHERE b.id =  c.id AND b.bd_num = c.bd_num ) "
	                  + "comments_count FROM BOARD b WHERE "+searchValue+" like '%"+searchkeyWord+"%' and b.TYPE_NUM = 100"
	                  + "ORDER BY BD_NUM DESC) A) WHERE RN BETWEEN ? AND ?";
	            System.out.println("Pet List<Board> list sql->"+sql);
	            conn = getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, startRow);
	            pstmt.setInt(2, endRow);
	         }else {
	            String sql = "SELECT * FROM "
	                  + "(SELECT ROWNUM RN, A.* FROM "
	                  + "( SELECT b.* , (SELECT count(*) FROM comments c WHERE b.id =  c.id AND b.bd_num = c.bd_num )"
	                  + " comments_count FROM BOARD b WHERE b.TYPE_NUM = 100"
	                  + "ORDER BY BD_NUM DESC) A) WHERE RN BETWEEN ? AND ?";
	            System.out.println("Pet List<Board> list sql->"+sql);
	            conn = getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, startRow);
	            pstmt.setInt(2, endRow);
	         }
			
			try {
				rs = pstmt.executeQuery();
				
				// true or false 가지고 포인터로 이동
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
						board.setComments_count(rs.getInt(16));
						
						list.add(board); //담아주기 그릇
						
					}while(rs.next()); //다음에 값이 있으면 do로 반복
				}
			} catch (Exception e) {
				System.out.println(e.getMessage()); // 일반 syso 처럼 나오게하는거
				e.printStackTrace(); // 빨간글씨로 나와서 구분이된다.
			}finally {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn !=null) conn.close();
			} return list;
			
		}
		
		//베스트리스트
		public List<Board> bestList() throws SQLException {
			
			List<Board> bestList = new ArrayList<Board>();
			
			Connection conn =null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql ="SELECT * FROM "
					+ "(SELECT ROWNUM RN, A.* FROM "
					+ "( SELECT b.* , (SELECT count(*) FROM comments c WHERE b.id =  c.id AND b.bd_num = c.bd_num ) "
					+ " comments_count FROM BOARD b WHERE b.TYPE_NUM = 100 "
					+ "ORDER BY BD_like DESC) A) WHERE RN BETWEEN 1 AND 8";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					do {Board board = new Board();
					
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
					board.setComments_count(rs.getInt(16));
					
					bestList.add(board); //담아주기 그릇
					
				}while(rs.next()); //다음에 값이 있으면 do로 반복
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
						
			} finally {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn !=null) conn.close();
			}
				
			return bestList;
		}
		
		
		
		
		
		
		public int insert(Board board) throws SQLException {
			int result =0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "insert into board values(?,bd_num_seq.NEXTVAL,?,?,?,?,?,?,?,to_char(sysdate,'yy/mm/dd hh24:mi'),?,?,?,?)";
			
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
			System.out.println("PetBoardDao insert result --> "+ result);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				if (pstmt != null) pstmt.close();
				if (conn !=null) conn.close();
			}

			return result;
		}
		
		
		public int update(Board board) throws SQLException {
			int result =0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "update board set bd_title=?, bd_cont=?, bd_pic=? where bd_num=?";
			
			try {
				
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBd_title());
			pstmt.setString(2, board.getBd_cont());
			pstmt.setString(3, board.getBd_pic());
			pstmt.setInt(4, board.getBd_num());
			
			result = pstmt.executeUpdate();
			System.out.println("PetBoardDao insert result --> "+ result);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				if (pstmt != null) pstmt.close();
				if (conn !=null) conn.close();
			}

			return result;
		}
		
		
		public List<Board> listAll() throws SQLException {
			List<Board> list = new ArrayList<Board>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select * from board order by bd_num desc";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					do {
						Board board = new Board();
						
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
						
						list.add(board);
						
					}while(rs.next());
				}
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} finally {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			}
			
			
			return list;
		}
		
		//조회수
		public void bd_view(int bd_num) throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "update board set bd_view=bd_view+1 where bd_num=?";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,bd_num);
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}finally {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			
		}
		public void bd_like(int bd_num) throws SQLException {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "update board set bd_like = bd_like+1 where bd_num=?";
			
			try {
				conn= getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bd_num);
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}finally {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			
			
		}
		public Board select(int bd_num) throws SQLException {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			String sql = "select * from board where bd_num ="+bd_num;
			
			Board board = new Board();
			try {
				conn = getConnection();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
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
					board.setS_score(rs.getInt(11));
					board.setS_cnt(rs.getInt(12));
					board.setS_price(rs.getInt(13));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}finally {
				if (rs !=null)    rs.close();
				if (stmt != null) stmt.close();
				if (conn !=null)  conn.close();
			}
			
			return board;
		}
		
		public int delete(int bd_num, String pw, String id) throws SQLException {
			
			int result = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "delete from comments where bd_num=?";
			String sql1 = "delete from stbuy where bd_num=?";
			String sql2 = "select pw from userinfo where id=?";
			String sql3 = "delete from board where bd_num=?";
			
			try {
				String dbPw= "";
				conn = getConnection();
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1, bd_num);
				result = pstmt.executeUpdate(); //스토어 삭제
				pstmt.close();
				
				pstmt= conn.prepareStatement(sql1);
				pstmt.setInt(1, bd_num);
				result = pstmt.executeUpdate(); //댓글 전부삭제
				pstmt.close();
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dbPw = rs.getString(1);
					if(dbPw.equals(pw)) {
						rs.close();
						pstmt.close();
						pstmt = conn.prepareStatement(sql3);
						pstmt.setInt(1, bd_num);
						result = pstmt.executeUpdate(); //삭제
					} else result= 0; // 비번이 틀리면
				} else result = -1; //오류시
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}finally {
				if (pstmt!=null) pstmt.close();
				if (rs!=null) rs.close();
				if (conn!=null) conn.close();
			}
	
			return result;
		}
		
}
