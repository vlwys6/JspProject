package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



public class MyPetDao {

   private static MyPetDao instance;
   private MyPetDao() {}
   public static MyPetDao getInstance() {
      if (instance == null) {   
         instance = new MyPetDao();      
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
   public int insert(MyPet mypet) throws SQLException {
      Connection conn = null;
      PreparedStatement pstmt =null;
      int result =0;
      String sql = "INSERT INTO mypet VALUES(?,p_num_seq.nextval,?,?,?,?,?)";
      
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, mypet.getId());
         pstmt.setInt(2, mypet.getType_num());
         pstmt.setString(3, mypet.getP_name());
         pstmt.setString(4, mypet.getP_age());
         pstmt.setString(5, mypet.getP_hobby());
         pstmt.setString(6, mypet.getP_pic());
         
         
         
         result = pstmt.executeUpdate();
         System.out.println("MyPetDao insert result->"+result);

         
      } catch (Exception e) {
         System.out.println("insert Exception->"+e.getMessage());
      }finally {
         if (pstmt != null) pstmt.close();
         if (conn !=null) conn.close();
      }
            return result;
   }
   
   
   //반려동물 조회
   public ArrayList<MyPet> getMyPets(String id) throws SQLException{
      ArrayList<MyPet> list = new ArrayList<MyPet>();
           Connection conn = null;
           PreparedStatement pstmt = null;
           ResultSet rs = null;
           
           
           
           
           try {
              String sql = "select *  from mypet where id= ? ";
              System.out.println("mypet arraylist sql -> " + sql);
              conn = getConnection();
              pstmt = conn.prepareStatement(sql);
              pstmt.setString(1, id);
              rs = pstmt.executeQuery();
              
              while(rs.next()) {
                 MyPet mypet = new MyPet();
                 
                 mypet .setP_age(rs.getString("p_age"));
                 System.out.println("mypet 에 있는 p_age 값 - > "+ mypet .getP_age());
                 mypet .setP_name(rs.getString("p_name"));
                 System.out.println("mypet  에 있는 p_name 값 - > "+ mypet .getP_name());
                 mypet .setP_hobby(rs.getString("p_hobby"));
                 System.out.println("mypet  에 있는 p_hobby 값 - > "+ mypet .getP_hobby());
                 mypet .setP_pic(rs.getString("p_pic"));
                 System.out.println("mypet  에 있는 p_pic 값 - > "+ mypet .getP_pic());
                 mypet .setType_num(rs.getInt("type_num"));
                 System.out.println("mypet  에 있는 type_num 값 - > "+ mypet .getType_num());
                 
                 list.add(mypet);
              }
              
              
            
         } catch (Exception e) { System.out.println("mypetdao arraylist 메소드 에러 =>"+e.getMessage());         
              e.printStackTrace();
              
              
         }finally {
            if (rs !=null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn !=null) conn.close();
         }
           
           
           
           
           return list;
           
      

    
   

   }
}