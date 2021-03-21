package webd4201.sturchflintn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet
{
    /**
     * Post Method for the LogoutServlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            // connect to database
            //Connection c = DatabaseConnect.initialize();
            HttpSession session = request.getSession(true);


            //Remove/Invalidate any stored data in the session
            //session.invalidate();
            session.removeAttribute("aStudent");
            session.removeAttribute("id");
            session.removeAttribute("FName");
            //Update the message and redirect to the login page
            session.setAttribute("message", "You have successfully logged out, we'll see you later");
            response.sendRedirect("./login.jsp");
        }
        catch (Exception e) //not connected
        {
            System.out.println(e);
            String line1="<h2>A network error has occurred!</h2>";
            String line2="<p>Please notify your system " +
                    "administrator and check log. "+e.toString()+"</p>";
            formatErrorPage(line1, line2,response);
        }
    }

    /**
     * Get Method for the Logout Servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    /**
     * formatErrorPage method for the LogoutServlet
     * @param first
     * @param second
     * @param response
     * @throws IOException
     */
    public void formatErrorPage( String first, String second,
                                 HttpServletResponse response) throws IOException
    {
        PrintWriter output = response.getWriter();
        response.setContentType( "text/html" );
        output.println(first);
        output.println(second);
        output.close();
    }
}
