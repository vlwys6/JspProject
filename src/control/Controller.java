package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;


// @WebServlet("/Contoller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String, Object> commandMap = new HashMap<String, Object>();
    
    public Controller() {
        super();
    }

	
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("config");
		
		Properties pr = new Properties();
		FileInputStream f = null;
		try {
			 String configFilePath = config.getServletContext().getRealPath(props);
			 f = new FileInputStream(configFilePath);
			 pr.load(f);
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			if (f != null) try{ f.close(); }catch(IOException ex) { }
		}
		
		Iterator keyIter = pr.keySet().iterator();
		
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			
			try {
				Class commandClass = Class.forName(className);
				Object commandInstance = commandClass.getDeclaredConstructor().newInstance();
				commandMap.put(command, commandInstance);
			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
    public void requestPro(HttpServletRequest request, HttpServletResponse response)
                          throws ServletException,IOException{
    	String view = null;
    	CommandProcess com = null;
    	String command = request.getRequestURI();
    	
    	try {
    		   System.out.println(command);
    		   command = command.substring(request.getContextPath().length());   		
    		   com = (CommandProcess)commandMap.get(command);
    		   System.out.println(command);
    		   view = com.requestPro(request, response);
    		   System.out.println(view);
    	}catch(Throwable e) {
    		throw new ServletException(e);
    	}
    	//ajax String 를 포함하고있으면
    	if(command.contains("ajaxTest1")) {
			System.out.println("ajax String->"+command);  // /ch16/list.do
			// text 있다면 
			String result =  (String) request.getAttribute("result");
			System.out.println("controller result - > " + result);
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();//강제로 화면전송 pw.write안에 담은 내용을
//일반적인 경우
    	}
			else if(command.contains("ajaxTest2")) {
			System.out.println("ajax String - >"+ command);
			
			String result1 = (String)request.getAttribute("result1");
			System.out.println("controller result1 - > " + result1);
			PrintWriter pw1 = response.getWriter();
			pw1.write(result1);
			pw1.flush();
		
			
			
			
    		}
			else if(command.contains("ajaxTest3")) {
				System.out.println("ajax String - >"+ command);
				String result2 = (String)request.getAttribute("result2");
				System.out.println("controller result2 - > " + result2);
				PrintWriter pw2 = response.getWriter();
				pw2.write(result2);
				pw2.flush();
			
			
			}
			
			
			
			else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
    				dispatcher.forward(request, response);
    		}
    }
}
