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

public class BoardUserInfoDao {
	
	//Singleton작성
			private static BoardUserInfoDao instance;
			private BoardUserInfoDao () {}
			public static BoardUserInfoDao getInstance() {
				if (instance == null ) {
					instance = new BoardUserInfoDao();
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
			 	
			//베스트리스트 반려동물자랑
			public List<BoardUserInfo> bestListPet() throws SQLException {
				
				List<BoardUserInfo> bestListPet = new ArrayList<BoardUserInfo>();
				
				Connection conn =null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql ="SELECT b.* ,u.*, (SELECT count(*) "
						+ " FROM comments c "
						+ " WHERE b.id =  c.id AND b.bd_num = c.bd_num ) comments_count "
						+ "FROM board b ,userinfo u "
						+ "WHERE b.id = u.id and b.TYPE_NUM = 100 "
						+ "ORDER BY BD_like DESC";
				
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						do {
						
						BoardUserInfo bui = new BoardUserInfo();
						
						   
		                  bui.setId(rs.getString(1));
		                  bui.setBd_num(rs.getInt(2));
		                  bui.setType_num(rs.getInt(3));
		                  bui.setBd_title(rs.getString(4));
		                  bui.setBd_cont(rs.getString(5));
		                  bui.setBd_video(rs.getString(6));
		                  bui.setBd_pic(rs.getString(7));
		                  bui.setBd_view(rs.getInt(8));
		                  bui.setBd_like(rs.getInt(9));
		                  bui.setBd_date(rs.getString(10));
		                  bui.setS_score(rs.getInt(11));
		                  bui.setS_cnt(rs.getInt(12));
		                  bui.setS_price(rs.getInt(13));
		                  bui.setBd_yn(rs.getString(14));
		                  bui.setPw(rs.getString(16));
		                  bui.setName(rs.getString(17));
		                  bui.setNick(rs.getString(18));
		                  bui.setGender(rs.getString(19));
		                  bui.setEmail(rs.getString(20));
		                    bui.setUserpic(rs.getString(22));
		                    bui.setPoint(rs.getInt(23));
		                    bui.setComments_count(rs.getInt(24));
				     	bestListPet.add(bui); //담아주기 그릇
						
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
					
				return bestListPet;
			}
			
			//베스트리스트 팁앤노하우
			public List<BoardUserInfo> bestListTip() throws SQLException {
				
				List<BoardUserInfo> bestListTip = new ArrayList<BoardUserInfo>();
				
				Connection conn =null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql ="SELECT b.* ,u.*, (SELECT count(*) "
						+ "                 FROM comments c "
						+ "                 WHERE b.id =  c.id AND b.bd_num = c.bd_num ) comments_count "
						+ "FROM board b ,userinfo u "
						+ "WHERE b.id = u.id and b.TYPE_NUM = 200 "
						+ "ORDER BY BD_like DESC";
				
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						do {
						
						BoardUserInfo bui = new BoardUserInfo();
						
						   
		                  bui.setId(rs.getString(1));
		                  bui.setBd_num(rs.getInt(2));
		                  bui.setType_num(rs.getInt(3));
		                  bui.setBd_title(rs.getString(4));
		                  bui.setBd_cont(rs.getString(5));
		                  bui.setBd_video(rs.getString(6));
		                  bui.setBd_pic(rs.getString(7));
		                  bui.setBd_view(rs.getInt(8));
		                  bui.setBd_like(rs.getInt(9));
		                  bui.setBd_date(rs.getString(10));
		                  bui.setS_score(rs.getInt(11));
		                  bui.setS_cnt(rs.getInt(12));
		                  bui.setS_price(rs.getInt(13));
		                  bui.setBd_yn(rs.getString(14));
		                  bui.setPw(rs.getString(16));
		                  bui.setName(rs.getString(17));
		                  bui.setNick(rs.getString(18));
		                  bui.setGender(rs.getString(19));
		                  bui.setEmail(rs.getString(20));
		                  bui.setUserpic(rs.getString(22));
		                  bui.setPoint(rs.getInt(23));
		                  bui.setComments_count(rs.getInt(24));
				     	bestListTip.add(bui); //담아주기 그릇
						
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
					
				return bestListTip;
			}
			
			//베스트리스트 스토어
			public List<BoardUserInfo> bestListStore() throws SQLException {
				
				List<BoardUserInfo> bestListStore = new ArrayList<BoardUserInfo>();
				
				Connection conn =null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql ="SELECT b.* ,u.*, (SELECT count(*) "
						+ "                 FROM comments c "
						+ "                 WHERE b.id =  c.id AND b.bd_num = c.bd_num ) comments_count "
						+ "FROM board b ,userinfo u "
						+ "WHERE b.id = u.id and b.TYPE_NUM = 300 "
						+ "ORDER BY s_score DESC";
				
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						do {
						
						BoardUserInfo bui = new BoardUserInfo();
						
						 bui.setId(rs.getString(1));
		                  bui.setBd_num(rs.getInt(2));
		                  bui.setType_num(rs.getInt(3));
		                  bui.setBd_title(rs.getString(4));
		                  bui.setBd_cont(rs.getString(5));
		                  bui.setBd_video(rs.getString(6));
		                  bui.setBd_pic(rs.getString(7));
		                  bui.setBd_view(rs.getInt(8));
		                  bui.setBd_like(rs.getInt(9));
		                  bui.setBd_date(rs.getString(10));
		                  bui.setS_score(Math.round(rs.getDouble(11)*10)/10.0);
		                  bui.setS_cnt(rs.getInt(12));
		                  bui.setS_price(rs.getInt(13));
		                  bui.setBd_yn(rs.getString(14));
		                  bui.setPw(rs.getString(16));
		                  bui.setName(rs.getString(17));
		                  bui.setNick(rs.getString(18));
		                  bui.setGender(rs.getString(19));
		                  bui.setEmail(rs.getString(20));
		                  bui.setUserpic(rs.getString(22));
		                  bui.setPoint(rs.getInt(23));
		                  bui.setComments_count(rs.getInt(24));
				     	bestListStore.add(bui); //담아주기 그릇
						
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
					
				return bestListStore;
			}
}
