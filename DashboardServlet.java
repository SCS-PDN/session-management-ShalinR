import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Intro to CS", "Dr. Smith"));
        courses.add(new Course("CS102", "Data Structures", "Prof. Lee"));
        courses.add(new Course("CS103", "Operating Systems", "Dr. Adams"));

        request.setAttribute("courses", courses);

        @SuppressWarnings("unchecked")
        List<Course> enrolled = (List<Course>) session.getAttribute("enrolledCourses");
        request.setAttribute("enrolledCourses", enrolled != null ? enrolled : new ArrayList<>());

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
