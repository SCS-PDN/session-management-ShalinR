import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final HashMap<String, String> users = new HashMap<>();

    public void init() throws ServletException {
        users.put("student1", "pass1");
        users.put("student2", "pass2");
        users.put("admin", "admin123");
    }
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
		String username=request.getParameter('username');
		String password= request.getParameter('password');
		
		
        if (users.containsKey(username) && users.get(username).equals(password)) {

     
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(60 * 60); 
        response.addCookie(cookie);
        response.sendRedirect("DashboardServlet");
        }else {
        	response.sendRedirect('login.html')
        }
    }
}
