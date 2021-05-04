package service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.MyPet;
import dao.MyPetDao;

public class MyPetInsertProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			int result = 0;
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
			
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			
			MyPet mypet = new MyPet();
			mypet.setId(id);
			System.out.println("mypetinsertproaction id -> "+ mypet.getId());
			mypet.setP_name(multi.getParameter("p_name"));
			System.out.println("mypetinsertproaction p_name -> "+ mypet.getP_name());
			mypet.setP_age(multi.getParameter("p_age"));
			System.out.println("mypetinsertproaction p_age -> "+ mypet.getP_age());
			mypet.setP_hobby(multi.getParameter("p_hobby"));
			System.out.println("mypetinsertproaction p_hobb y-> "+ mypet.getP_hobby());
			mypet.setType_num(Integer.parseInt(multi.getParameter("type_num")));
			System.out.println("mypetinsertproaction Type_num -> "+ mypet.getType_num());
			mypet.setP_pic("img\\"+filename);
			System.out.println("mypetinsertproaction filename -> "+ mypet.getP_pic());
			
			MyPetDao pet = MyPetDao.getInstance(); // DB 활용
			result = pet.insert(mypet);
			
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			System.out.println("e.getMessage() -> " +e.getMessage());
		} 

		return "mypet/MyPetInsertPro.jsp";
	}

}
