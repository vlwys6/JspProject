package service;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
	
		} catch (Exception e) { System.out.println("joinFormAction 쪽에서 에러 : " + e.getMessage());
	
		}
		return "user/join.jsp";
	}

}
