package webd4201.sturchflintn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

public class LoginServlet extends HttpServlet
{

    /**
     * Post method for the Login Servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException
    {

        //CREATE A TEXT FILE
	   	/*String logFile = "./test_log.log";
	    File f = new File(logFile);
	    PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(f)), true);
	    System.setErr(printStream);
	    System.setOut(printStream);
	    System.out.println("Log started: " + new java.util.Date());
	    */
        try
        {
            // connect to database
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);
            HttpSession session = request.getSession(true);
            String id = new String();
            String password = new String();
            try
            {   // retrieve data from DB
                id = request.getParameter( "Login" ); //this is the name of the text input box on the form
                password = request.getParameter( "Password" ); //this is the name of the text input box on the form
                long validID = Long.parseLong(id);

                Student aStudent = Student.authenticate(validID, password); //if the ID exists, AND the password is correct
                // if the Student was found and retrieved from the db
                //put the found Student onto the session
                session.setAttribute("aStudent", aStudent);
                session.setAttribute("FName", aStudent.getFirstName());
                //empty out any errors if there were some
                session.setAttribute("errors", "");

                // redirect the user to a JSP
                response.sendRedirect("./dashboard.jsp");
                session.setAttribute("message", "Welcome Back " + session.getAttribute("FName") + "!");
            }catch( NotFoundException nfe) //incorrect login information
            {
                long validID = Long.parseLong(id);
                //sending errors to the page thru the session
                StringBuffer errorBuffer = new StringBuffer();
                errorBuffer.append("<strong>Your sign in information is not valid.<br/>");
                errorBuffer.append("Please try again.</strong>");
                if(Student.retrieve(validID) != null)
                {
                    session.setAttribute("id", validID);
                    errorBuffer.append("Invalid Password.</strong>");
                }
                else
                {
                    errorBuffer.append("Invalid login id.</strong>");
                    session.setAttribute("id", "");
                }
                session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./login.jsp");

                //for the first deliverable you will have to check if there was a problem
                //with just the password, or login id and password
                //this will require a special method e.g. public static boolean isExistingLogin(String arg);
            }
        }
        catch (Exception e) //not connected OR incorrect login information
        {
            HttpSession session = request.getSession(true);

            String id = new String();
            String password = new String();

            System.out.println(e);

            id = request.getParameter( "Login" ); //this is the name of the text input box on the form
            password = request.getParameter( "Password" ); //this is the name of the text input box on the form

            long validID = Long.parseLong(id);
            //sending errors to the page thru the session
            StringBuffer errorBuffer = new StringBuffer();
            errorBuffer.append("<strong>Your sign in information is not valid.<br/>");
            errorBuffer.append("Please try again.</strong>");
            try {
                if(Student.retrieve(validID) != null)
                {
                    session.setAttribute("id", validID);
                    errorBuffer.append("Invalid Password.</strong>");
                }
                else
                {
                    errorBuffer.append("Invalid login id.</strong>");
                    session.setAttribute("id", "");
                }
            } catch (Exception e1)
            {
                e1.printStackTrace();
            }
            session.setAttribute("errors", errorBuffer.toString());
            response.sendRedirect("./login.jsp");

            //for the first deliverable you will have to check if there was a problem
            //with just the password, or login id and password
            //this will require a special method e.g. public static boolean isExistingLogin(String arg);
        }
    }

    /**
     * doGet Method for Login Servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    /**
     * formatErrorPage Method for LoginServlet
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