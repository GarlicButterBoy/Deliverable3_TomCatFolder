package webd4201.sturchflintn;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

/**
 * Student class extends the user class and acts as the Student user type
 * @author Nick Sturch-Flint - 100303769
 * @version 1.0 (January 8th, 2021)
 * @since January 8th, 2021
 */
public class Student extends User
{
    //CONSTANTS
    /**
     * The Default Program Code for a default constructor
     */
    public static final String DEFAULT_PROGRAM_CODE = "UNDC";
    /**
     * The Default Program Description for a default constructor
     */
    public static final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";
    /**
     * The Default Year that the student is in, for a default constructor
     */
    public static final int DEFAULT_YEAR = 1;

    //VARIABLES
    /**
     * Private Data variable to store a Program Code
     */
    private String programCode;
    /**
     * Private Data variable to store a Program Description
     */
    private String programDescription;
    /**
     * Private Data variable to store the students current year
     */
    private int year;
    /**
     * Private Vector to store the marks the student has in each class
     */
    private Vector<Mark> marks;

    //CONSTRUCTORS
    /**
     * Parameterized Constructor that accepts a vector of marks
     * @param id                  User's ID
     * @param password            User's Password
     * @param firstName           User's First Name
     * @param lastName            User's Last Name
     * @param emailAddress        User's Email Address
     * @param lastAccess          User's Last Access Date
     * @param enrolDate           User's Enrolment Date
     * @param enabled             User's Enabled Status
     * @param type                User's Account Type
     * @param programCode         User's Program Code
     * @param programDescription  User's Program Description
     * @param year                User's Current Year
     * @param marks               User's Vector of Marks
     * @exception InvalidUserDataException throws an exception if any of the set methods fail
     */
    public Student(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type, String programCode, String programDescription, int year, Vector<Mark> marks)
            throws InvalidUserDataException
    {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        setProgramCode(programCode);
        setProgramDescription(programDescription);
        setYear(year);
        setMarks(marks);
    }
    /**
     *  Parameterized Constructor that doesn't need a vector of marks
     * @param id                  User's ID
     * @param password            User's Password
     * @param firstName           User's First Name
     * @param lastName            User's Last Name
     * @param emailAddress        User's Email Address
     * @param lastAccess          User's Last Access Date
     * @param enrolDate           User's Enrolment Date
     * @param enabled             User's Enabled Status
     * @param type                User's Account Type
     * @param programCode         User's Program Code
     * @param programDescription  User's Program Description
     * @param year                User's Current Year
     * @exception InvalidUserDataException throws an exception if any of the set methods fail
     */
    public Student(long id, String password, String firstName, String lastName, String emailAddress,
                   Date lastAccess, Date enrolDate, boolean enabled, char type, String programCode,
                   String programDescription, int year) throws InvalidUserDataException
    {
        this(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate,
                enabled, type, programCode, programDescription, year, new Vector<>());
        //System.out.println("ID: " + id);
    }
    /**
     * Default Constructor uses the parent class constructor then uses the student defaults to build a basic instance
     */
    public Student() throws InvalidUserDataException
    {
        this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS,
                new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE,  DEFAULT_PROGRAM_CODE,
                DEFAULT_PROGRAM_DESCRIPTION, DEFAULT_YEAR, new Vector<>());
    }

    //ACCESSORS
    //Getters
    /**
     * Method that returns the Program Code
     * @return programCode         In the String format
     */
    public String getProgramCode()
    {
        return programCode;
    }

    /**
     * Method that returns the Program Description
     * @return programDescription  In the String format
     */
    public String getProgramDescription()
    {
        return programDescription;
    }

    /**
     * Method that returns the Students current year
     * @return year               In the int format
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Method that returns a vector of marks
     * @return marks             As a vector
     */
    public Vector<Mark> getMarks()
    {
        return marks;
    }

    //Setters
    /**
     * Method that sets the Program Code
     * @param programCode        In the String Format
     */
    public void setProgramCode(String programCode)
    {
        this.programCode = programCode;
    }

    /**
     * Method that sets the Program Description
     * @param programDescription In the String format
     */
    public void setProgramDescription(String programDescription)
    {
        this.programDescription = programDescription;
    }

    /**
     * Method that sets the  Students current year
     * @param year              In the int format
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * Method that sets a vector of marks
     * @param marks            In a vector
     */
    public void setMarks(Vector<Mark> marks)
    {
        this.marks = marks;
    }

    //METHODS
    /**
     * An Override method that returns the user account type
     * @return   Student
     */
    @Override
    public String getTypeForDisplay()
    {
        return "Student";
    }

    /**
     * An Override method that returns the object in a string format
     * @return object as a string
     */
    @Override
    public String toString()
    {
        return  getTypeForDisplay() + "\n{\n" +
                getFirstName() + " " + getLastName() + "(" + getId() + ")" + "\n" +
                "Currently in " + getYear() + " year of " + getProgramDescription() + " " + getProgramCode() + "\n" +
                "Enrolled: " + getEnrolDate() +
                "\n}";
    }

    /**
     * Method that connects the DB
     * @param c     The connection String
     */
    public static void initialize(Connection c)
    {
        StudentDA.initialize(c);
    }

    /**
     * Method to terminate the connection to the DB
     */
    public static void terminate()
    {
        StudentDA.terminate();
    }

    /**
     * Method to retrieve a student from the DB
     * @param id        The id being searched for in the DB
     * @return Student  A record from the DB
     * @throws NotFoundException  throws an exception if the user cannot be found, or other implicit data errors
     */
    public static Student retrieve(long id) throws NotFoundException, SQLException, InvalidUserDataException, InvalidIdException, InvalidNameException, InvalidPasswordException
    {
        return StudentDA.retrieve(id);
    }

    /**
     * Creates a new Student Object
     * @return aStudent   Object containing the relevant data
     * @throws InvalidUserDataException  throws an exception if the user data entry is invalid
     */
    public boolean create() throws InvalidUserDataException, DuplicateException, InvalidIdException, InvalidNameException, InvalidPasswordException, SQLException, NoSuchAlgorithmException
    {
        return StudentDA.create(this);
    }

    /**
     * Updates an existing record
     * @return          number of rows affected
     * @throws NotFoundException    throws an exception if the user cannot be found
     */
    public  int update() throws NotFoundException, InvalidUserDataException, InvalidIdException, InvalidNameException, InvalidPasswordException, SQLException
    {
        return StudentDA.update(this);
    }

    /**
     * Deletes an existing record
     * @return          number of rows affected
     * @throws NotFoundException   throws an exception if the user cannot be found
     */
    public int delete() throws NotFoundException, InvalidUserDataException, InvalidIdException, InvalidNameException, InvalidPasswordException, SQLException
    {
        return  StudentDA.delete(this);
    }

    /**
     * Uses StudentDA authenticate to authenticate the users information is correct
     * @param studentNum         Student number as a long
     * @param password           password as a String
     * @return                   Student object if studentNum and password match
     * @throws NotFoundException
     */
    public static Student authenticate(long studentNum, String password) throws NotFoundException, InvalidIdException, SQLException, InvalidNameException, InvalidPasswordException, InvalidUserDataException
    {
        return StudentDA.authenticate(studentNum, password);
    }
}
