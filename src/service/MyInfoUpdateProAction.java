package service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.UserInfo;
import dao.UserInfoDao;

public class MyInfoUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			
			request.setCharacterEncoding("utf-8");
			
			int maxSize = 5 * 1024 * 1024; 
			String fileSave = "/img";
			ServletContext context = request.getSession().getServletContext();
			String realPath = context.getRealPath(fileSave);
			String filename = "";
			System.out.println("realPath 이미지 경로=>" + realPath);
			MultipartRequest multi = new MultipartRequest(request,realPath,	maxSize,"utf-8", new DefaultFileRenamePolicy());
			System.out.println("multi -> "+ multi);
		
			
			Enumeration en = multi.getFileNames(); //여러개의 파일을 올릴경우를 대비해서 Enumeration 파일객체맡기
			System.out.println("en -> "+ en);
			
			
			while(en.hasMoreElements()) {
				//input 태그의 속성이 file인 태그의 name 속성값 :파라미터이름m
				String filename1 = (String)en.nextElement(); 
				//서버에 저장된 파일 이름 
				filename = multi.getFilesystemName(filename1); 
				//전송전 원래의 파일 이름 
				String original = multi.getOriginalFileName(filename1);
				//전송된 파일의 내용 타입 
				String type = multi.getContentType(filename1); 
				//전송된 파일속성이 file인 태그의 name 속성값을 이용해 파일객체생성 
				File file = multi.getFile(filename1); //file -> java.io로 잡기
				System.out.println("real Path : " + realPath ); 
				System.out.println("파라메터 이름 : " + filename1 ); 
				System.out.println("실제 파일 이름 : " + original ); 
				System.out.println("저장된 파일 이름 : " + filename ); 
				System.out.println("파일 타입 : " + type); 
				if(file!=null){ 
					System.out.println("크기 : " + file.length() );
				}
	}
			
			
			String id = multi.getParameter("id");
			String pw = multi.getParameter("pw");
			String name = multi.getParameter("name");
			String nick = multi.getParameter("nick");
			String email = multi.getParameter("email");
			String userpic = multi.getParameter("userpic");

			
			
			
			UserInfo userinfo = new UserInfo();
			userinfo.setId(id);
			userinfo.setPw(pw);
			userinfo.setName(name);
			userinfo.setNick(nick);
			userinfo.setEmail(email);
			userinfo.setUserpic("img\\"+filename);
			System.out.println("myinfoupdate proaction userpic - > " + filename);
			
	
			UserInfoDao ui = UserInfoDao.getInstance();//DB
			
			
			int result = ui.myupdate(userinfo);
			
			request.setAttribute("id", id);
			request.setAttribute("pw", pw);
			request.setAttribute("name", name);
			request.setAttribute("nick", nick);
			request.setAttribute("email", email);
			request.setAttribute("userpic", userpic);
			
			
			
		        System.out.println("myinfoupdateproaction result 값 - > " + result);
		        request.setAttribute("result", result);
		        
		} catch (Exception e) {System.out.println("myinfoupdateproaction e.getMessage() ->"+e.getMessage());
			// TODO: handle exception
		}
		
		return "mypage/myInfoUpdatePro.jsp";
	}

}
