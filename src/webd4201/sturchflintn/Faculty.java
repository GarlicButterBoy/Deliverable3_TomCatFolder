package webd4201.sturchflintn;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * Faculty class extends the user class and acts as the Faculty user type
 * @author Nick Sturch-Flint - 100303769
 * @version 1.0 (Janaury 8th, 2021)
 * @since January 8th, 2021
 */
public class Faculty extends User
{

    //CONSTANTS
    /**
     * The Default School Code for a default constructor
     */
    public static final String DEFAULT_SCHOOL_CODE = "SET";
    /**
     * The Default School Description for a default constructor
     */
    public static final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";
    /**
     * The Default Office for a default constructor
     */
    public static final String DEFAULT_OFFICE = "H-140";
    /**
     * The Default Phone Extension for a default constructor
     */
    public static final int DEFAULT_PHONE_EXTENSION = 1234;

    //VARIABLES
    /**
     * Private data member to hold a school code
     */
    private String schoolCode;
    /**
     * Private data member to hold the school description
     */
    private String schoolDescription;
    /**
     * Private data member to hold the office
     */
    private String office;
    /**
     * Private data member to hold phone extension
     */
    private int extension;

    //CONSTRUCTORS
    /**
     * Parameterized Constructor
     * @param id                 User's ID
     * @param password           User's Password
     * @param firstName          User's First Name
     * @param lastName           User's Last Name
     * @param emailAddress       User's Email Address
     * @param lastAccess         User's Last Time Logged In
     * @param enrolDate          User's Enrolment Date
     * @param enabled            User's Enabled Status
     * @param type               User's Account Type (Faculty)
     * @param schoolCode         User's School Code
     * @param schoolDescription  User's School Description
     * @param office             User's Office
     * @param extension          User's Phone Extension
     * @exception InvalidUserDataException throws an exception if any of the set methods fail
     */
    public Faculty(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type, String schoolCode, String schoolDescription, String office, int extension)
            throws InvalidUserDataException
    {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        setSchoolCode(schoolCode);
        setSchoolDescription(schoolDescription);
        setOffice(office);
        setExtension(extension);
    }

    /**
     * DEFAULT CONSTRUCTOR uses the parent class constructor then uses the faculty defaults to build a basic instance
     */
    public Faculty() throws InvalidUserDataException {
        super();
        setSchoolCode(DEFAULT_SCHOOL_CODE);
        setSchoolDescription(DEFAULT_SCHOOL_DESCRIPTION);
        setOffice(DEFAULT_OFFICE);
        setExtension(DEFAULT_PHONE_EXTENSION);
    }

    //ACCESSORS
    //Getters
    /**
     * Method to retrieve the Faculty members School Code
     * @return schoolCode           in the String format
     */
    public String getSchoolCode()
    {
        return schoolCode;
    }
    /**
     * Method to retrieve the Faculty members School Description
     * @return schoolDescription   in the String format
     */
    public String getSchoolDescription()
    {
        return schoolDescription;
    }
    /**
     * Method to retrieve the Faculty members Office code
     * @return office              in the String format
     */
    public String getOffice()
    {
        return office;
    }
    /**
     * Method to retrieve the Faculty members Phone Extension
     * @return extension          in the int format
     */
    public int getExtension()
    {
        return extension;
    }

    //Setters
    /**
     * Method to set the faculty members School Code
     * @param schoolCode            in the String format
     */
    public void setSchoolCode(String schoolCode)
    {
        this.schoolCode = schoolCode;
    }
    /**
     * Method to set the faculty members School Description
     * @param schoolDescription    in the String format
     */
    public void setSchoolDescription(String schoolDescription)
    {
        this.schoolDescription = schoolDescription;
    }
    /**
     * Method to set the faculty members Office code
     * @param office               in the String format
     */
    public void setOffice(String office)
    {
        this.office = office;
    }
    /**
     * Method to set the faculty members Phone Extension
     * @param extension            in the int format
     */
    public void setExtension(int extension)
    {
        this.extension = extension;
    }

    //METHODS
    /**
     * An Override method that returns the user account type
     * @return      Faculty
     */
    @Override
    public String getTypeForDisplay()
    {
        return "Faculty";
    }

    /**
     * An Override method that returns the object in a string format
     * @return object as a string
     */
    @Override
    public String toString()
    {
        return  getTypeForDisplay() + "\n{\n" +
                "Faculty ID    = " + getId() + "\n" +
                "Name          = " + getFirstName() + ' ' + getLastName() + "\n" +
                "Email Address = " + getEmailAddress() + '\n' +
                "Created On    = " + getEnrolDate() + "\n" +
                "Last Access   = " + getLastAccess() + "\n" +
                getSchoolDescription() + "\n" +
                "Office        = " + getOffice() + "\n" +
                CollegeInterface.phone_number + " " + getExtension() + "\n" +
                '}';
    }

    /**
     * Method that connects the DB
     * @param c     The connection String
     */
    public static void initialize(Connection c)
    {
        FacultyDA.initialize(c);
    }

    /**
     * Method to terminate the connection to the DB
     */
    public static void terminate()
    {
        FacultyDA.terminate();
    }

    /**
     * Method to retrieve a student from the DB
     * @param id        The id being searched for in the DB
     * @return Faculty  A record from the DB
     * @throws NotFoundException  throws an exception if the user cannot be found, or other implicit data errors
     */
    public static Faculty retrieve(long id) throws NotFoundException, SQLException, InvalidUserDataException, InvalidIdException, InvalidNameException, InvalidPasswordException {
        return FacultyDA.retrieve(id);
    }

    /**
     * Creates a new Student Object
     * @return aFaculty   Object containing the relevant data
     * @throws InvalidUserDataException  throws an exception if the user data entry is invalid
     */
    public boolean create() throws InvalidUserDataException, DuplicateException, InvalidIdException, InvalidNameException, InvalidPasswordException, SQLException, NoSuchAlgorithmException {
        return FacultyDA.create(this);
    }

    /**
     * Updates an existing record
     * @return          number of rows affected
     * @throws NotFoundException    throws an exception if the user cannot be found
     */
    public  int update() throws NotFoundException, InvalidUserDataException, InvalidIdException, InvalidNameException, InvalidPasswordException, SQLException {
        return FacultyDA.update(this);
    }

    /**
     * Deletes an existing record
     * @return          number of rows affected
     * @throws NotFoundException   throws an exception if the user cannot be found
     */
    public int delete() throws NotFoundException, InvalidUserDataException, InvalidIdException, InvalidNameException, InvalidPasswordException, SQLException {
        return  FacultyDA.delete(this);
    }
}
