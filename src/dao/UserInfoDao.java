package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserInfoDao {

	private static UserInfoDao instance;
	private UserInfoDao() {}
	public static UserInfoDao getInstance() {
		if (instance == null) {	
			instance = new UserInfoDao();		
			}
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) { 
			System.out.println(e.getMessage());	
			}
		return conn;
	}
	
	
	public int insert(UserInfo userinfo) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		int result = 0;			
		String sql="INSERT INTO userinfo VALUES(?,?,?,?,?,?,?,?,?)";
		

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println("UserInfo insert userinfo.getId()->"+userinfo.getId());
			System.out.println("UserInfo insert userinfo.getPw()->"+userinfo.getPw());
			System.out.println("UserInfo insert userinfo.getName()->"+userinfo.getName());
			System.out.println("UserInfo insert userinfo.getNick()->"+userinfo.getNick());
			System.out.println("UserInfo insert userinfo.getGender()->"+userinfo.getGender());
			System.out.println("UserInfo insert userinfo.getEmail()->"+userinfo.getEmail());
			System.out.println("UserInfo insert userinfo.getUserpic()->"+userinfo.getUserpic());

			pstmt.setString(1, userinfo.getId());
			pstmt.setString(2, userinfo.getPw());
			pstmt.setString(3, userinfo.getName());
			pstmt.setString(4, userinfo.getNick());
			pstmt.setString(5, userinfo.getGender());
			pstmt.setString(6, userinfo.getEmail());
			pstmt.setNull(7, java.sql.Types.VARCHAR);
			pstmt.setString(8, userinfo.getUserpic());
			pstmt.setNull(9, java.sql.Types.INTEGER);
			
			
			result = pstmt.executeUpdate();
			
			System.out.println("UserInfoDao insert result->"+result);

		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}

	  public int selectId(String id, String pw) throws SQLException {
	  
	  	String SQL = "SELECT pw FROM USERINFO WHERE ID = ?";
	  	Connection conn = null;
	  	PreparedStatement pstmt = null;
	  	ResultSet rs = null;
	  	try {
	  		 conn = getConnection();
	  		 pstmt = conn.prepareStatement(SQL);
	  		 pstmt.setString(1, id);
	  		 System.out.println("userinfo dao selectId -> " +id);
	  		 rs = pstmt.executeQuery();
	  		 System.out.println("userinfo selectid getstring equals " + pw);
	  		
	  		 if(rs.next()) {
	  			
	  			 if(rs.getString("pw").equals(pw)) {
	  				  return 1;
	  			 }else {
	  				 return  0;
	  			 }
	  			 
	  		 }
	  	}catch(Exception e) {
	  		System.out.println("selectId 메소드에서 에러 : " + e.getMessage());
	  		e.printStackTrace();
	  	}finally {
	  		if(rs != null) rs.close();
	  		if(pstmt != null) pstmt.close();
	  		if(conn != null) conn.close();
	  	}
	  	
	  	
	  	return -1;
	  }
	  public UserInfo select(String id) throws SQLException {
		 
		  Connection conn = null;
		  String sql = "select * from userinfo where id = ?";
		  PreparedStatement pstmt =null;
		  ResultSet rs = null;
		  UserInfo user = null;
			System.out.println("UserInfoDao select id->"+id);
			System.out.println("UserInfoDao select sql->"+sql);
				  try {
						conn  = getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, id);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							user = new UserInfo();
							user.setId(rs.getString(1));
							System.out.println("UserInfo select user.getId()->"+user.getId());


							user.setPw(rs.getString(2));
							System.out.println("UserInfo select user.getPw()->"+user.getPw());
							user.setName(rs.getString(3));
							System.out.println("UserInfo select user.getName()->"+user.getName());
							user.setNick(rs.getString(4));
							System.out.println("UserInfo select user.getNick()->"+user.getNick());
							user.setGender(rs.getString(5));
							System.out.println("UserInfo select user.getGender()->"+user.getGender());
					    	user.setEmail(rs.getString(6));
					     	System.out.println("UserInfo select user.getEmail()->"+user.getEmail());
							
							user.setUserpic(rs.getString(8));
							System.out.println("UserInfo select user.getUserpic()->"+user.getUserpic());
							
						}
				} catch (Exception e) {System.out.println("userinfodat -> select -> error : " +e.getMessage());
					// TODO: handle exception
				}finally {
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				}  
		  return user;
	  }
	  
	public int myupdate(UserInfo userinfo) throws SQLException {
		
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		int result = 0;			
		String sql="update UserInfo set pw=?,name=?,nick=?, email=? ,userpic=? where id=?";
		
		//userinfo 에담겨져있는 파라미터를 가져옴
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			
			System.out.println("UserInfo myupdate userinfo.getId()->"+userinfo.getId());
			System.out.println("UserInfo myupdate userinfo.getPw()->"+userinfo.getPw());
			System.out.println("UserInfo myupdate userinfo.getName()->"+userinfo.getName());
			System.out.println("UserInfo myupdate userinfo.getNick()->"+userinfo.getNick());
			System.out.println("UserInfo myupdate userinfo.getEmail()->"+userinfo.getEmail());
			System.out.println("UserInfo myupdate userinfo.userpic()->"+userinfo.getUserpic());
			
			pstmt.setString(6, userinfo.getId());
			pstmt.setString(1, userinfo.getPw());
			pstmt.setString(2, userinfo.getName());
			pstmt.setString(3, userinfo.getNick());
			pstmt.setString(4, userinfo.getEmail());
			pstmt.setString(5, userinfo.getUserpic());
			System.out.println("UserInfoDao update sql -> " + sql);
			result = pstmt.executeUpdate();
			
			System.out.println("UserInfoDao myupdate result->"+result);

		} catch (Exception e) {
			
		}finally {if (pstmt != null) pstmt.close();
		if (conn !=null) conn.close();
		
			
		}return result;
		
	}
	public String duplicateIdCheck(String id) throws SQLException {
		
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs= null;			
		String x= "0";
		
		
		
		try {
			String sql="select id from userinfo where id=?";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println("userinfo check get id - > " + id);
			
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
		
			if(rs.next()) {
			
				System.out.println("rs의 setString " + rs.getString(1));
				x="1";
			    System.out.println("rs.next - > x =" + x);
			 }//해당아이디 존재
			else {x = "0"; 
			System.out.println("rs.next - > x = " + x) ;
		
			 
			System.out.println("rs의 getString " + rs.getString(1));
			
			
			
			}
		} catch (Exception e) {
			System.out.println("UserInfoDao duplicateIdCheck 에러=>"+e.getMessage());
			e.printStackTrace();
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return x;
		
	
	}
	public String foundId(String pname , String name , String email) throws SQLException {
		
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs= null;		
		String result = "0";
		try {
			String sql = "select u.id "
				+	"from userinfo u , mypet m "
				+	"where u.id=m.id  and  m.P_NAME = ? and u.NAME = ? and u.EMAIL = ?";
			conn = getConnection();
			System.out.println("sql - >" + sql );
			pstmt = conn.prepareStatement(sql);
			System.out.println("userinfo founId p_name -> " + pname + " name - > " + name +" email -> "+ email);
			
			pstmt.setString(1, pname);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				
				
				result = rs.getString(1);
				System.out.println("userinfodao founid rs.next() result - > " + result);
								
				
			}else {
				
			result = "0";
			System.out.println("userinfodao founid rs.next() else result - > " + result);
			}

			
		} catch (Exception e) {
			System.out.println("UserInfoDao foundId 에러=>"+e.getMessage());
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		
		
		return result;
		
	}
	
	public String foundPw(String id ,String name) throws SQLException {
		
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs= null;		
		String result = "0";
		
		try {
			String sql = "select pw "
				+	"from userinfo "
				+	"where id = ? and NAME = ? ";
			conn = getConnection();
			System.out.println("sql - >" + sql );
			pstmt = conn.prepareStatement(sql);
			System.out.println("userinfo founpw id -> " + id + " name - > " + name );
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				
				
				result = rs.getString(1);
				System.out.println("userinfodao founid rs.next() result - > " + result);
								
				
			}else {
				
			result = "0";
			System.out.println("userinfodao founid rs.next() else result - > " + result);
			}

			
		} catch (Exception e) {System.out.println("e.getMessage()" + e.getMessage());
			// TODO: handle exception
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		
		
		
		return result;
		
	}
	
	
	
	  
	  }
