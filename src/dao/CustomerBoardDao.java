package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CustomerBoardDao {
	private static CustomerBoardDao instance;
	private CustomerBoardDao() {}
	public static CustomerBoardDao getInstance() {
		if(instance == null ) {
			instance = new CustomerBoardDao();
		}
		return instance;
	}
	public  Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println("CustomerBoardDao getConnection Exception->"+e.getMessage());
		}
		return conn;
	}
	public int getTotalCnt() throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from board where type_num=400";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("getTotalCnt Exception->"+e.getMessage());
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}
	public List<Board> list(int startRow,int endRow) throws SQLException{
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "
				+ "(SELECT ROWNUM RN, A.* FROM "
				+ "( SELECT b.* , (SELECT count(*) FROM comments c WHERE b.id =  c.id AND b.bd_num = c.bd_num )"
				+ " comments_count FROM BOARD b WHERE b.TYPE_NUM = 400 "
				+ "ORDER BY BD_NUM DESC) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
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
					board.setBd_yn(rs.getString(15));
					board.setComments_count(rs.getInt(16));
					list.add(board);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("list Exception->"+e.getMessage());
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return list;
	}
	public int insert(Board board) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into board values(?,bd_num_seq.nextval,?,?,?,?,?,?,?,to_char(sysdate, 'yy/mm/dd hh24:mi:ss'),?,?,?,'N')";
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
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert Exception->"+e.getMessage());
		}finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}
	public void view(int bd_num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update board set bd_view = bd_view +1 where bd_num=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bd_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("view Exception->"+e.getMessage());
		}finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	public Board select(int bd_num) throws SQLException {
		Board board = new Board();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where bd_num=? and type_num=400";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bd_num);
			rs = pstmt.executeQuery();
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
				board.setS_score(rs.getDouble(11));
				board.setS_cnt(rs.getInt(12));
				board.setS_price(rs.getInt(13));
			}
		} catch (Exception e) {
			System.out.println("select Exception->"+e.getMessage());
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return board;
	}
	public int update(Board board) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql ="update board set bd_title=?,bd_cont=?,bd_pic=? where bd_num=? and type_num=400";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBd_title());
			pstmt.setString(2, board.getBd_cont());
			pstmt.setString(3, board.getBd_pic());
			pstmt.setInt(4, board.getBd_num());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("update Exception->"+e.getMessage());
		}finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}
	public int delete(int bd_num) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from stbuy where bd_num=? and type_num=400";
		String sql2 = "select * from comments where bd_num=? and type_num=400";
		String sql3 = "delete from stbuy where bd_num=? and type_num=400";
		String sql4 = "delete from comments where bd_num=? and type_num=400";
		String sql5 = "delete from board where bd_num=? and type_num=400";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bd_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
			if(rs.next()) {
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
			result =pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("delete Exception->"+e.getMessage());
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}
}
