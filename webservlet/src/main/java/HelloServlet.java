import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet implements GreetingService {
    private static final long serialVersionUID = 1L;

    public HelloServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        // Get the session, create one if it doesn't exist
        HttpSession session = request.getSession();
        
        // Retrieve the name from the session or request
        String name = (String) session.getAttribute("username");
        if (name == null) {
            name = request.getParameter("t1");
            // Store the name in the session
            if (name != null) {
                session.setAttribute("username", name);
            } else {
                name = "Guest"; // Default value if no name is provided
            }
        }

        // Use the interface method to generate the greeting
        String greeting = generateGreeting(name);
        
        pw.println("<h1>" + greeting + "</h1>");
        pw.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String generateGreeting(String name) {
        return "Welcome to our page, " + name;
    }
}
