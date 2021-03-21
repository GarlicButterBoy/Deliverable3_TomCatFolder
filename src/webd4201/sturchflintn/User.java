package webd4201.sturchflintn;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * User class extends the college interface but also acts as the parent class to Faculty and Student user types
 * @author Nick Sturch-Flint - 100303769
 * @version 1.0 (January 8th, 2021)
 * @since January 8th, 2021
 */
public class User implements CollegeInterface {

    //CONSTANTS
    /**
     *The Default ID for a default constructor
     */
    protected static long DEFAULT_ID = 100123456;
    /**
     * The Default Password for a default constructor
     */
    protected static String DEFAULT_PASSWORD = "password";
    /**
     *The minimum password length allowed
     */
    public static final byte MINIMUM_PASSWORD_LENGTH = 8;
    /**
     * The maximum password length allowed
     */
    public static final byte MAXIMUM_PASSWORD_LENGTH = 40;
    /**
     * Default first name for a default constructor
     */
    protected static String DEFAULT_FIRST_NAME = "John";
    /**
     *Default last name for a default constructor
     */
    protected static String DEFAULT_LAST_NAME = "Doe";
    /**
     *Default email for a default constructor
     */
    protected static String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.com";
    /**
     *Default enabled status for a default constructor
     */
    protected static boolean DEFAULT_ENABLED_STATUS = true;
    /**
     *Default user type for a default constructor
     */
    protected static char DEFAULT_TYPE = 's';
    /**
     * The Minimum Allowed ID Number
     */
    public static final long MINIMUM_ID_NUMBER = 100000000L;
    /**
     * The Maximum allowed ID Number
     */
    public static final long MAXIMUM_ID_NUMBER = 999999999L;
    /**
     *ID must be 9 bytes, or characters, long
     */
    protected static byte ID_NUMBER_LENGTH = 9;
    /**
     *Updates date format to Canadian
     */
    protected final static DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);

    //VARIABLES
    /**
     * Private data member to hold id
     */
    private long id;
    /**
     *Private data member to hold password
     */
    private String password;
    /**
     *Private data member to hold first name
     */
    private String firstName;
    /**
     *Private data member to hold last name
     */
    private String lastName;
    /**
     *Private data member to hold email address
     */
    private String emailAddress;
    /**
     *Private data member to hold the last access
     */
    private Date lastAccess;
    /**
     *Private data member to holds the enrol date
     */
    private Date enrolDate;
    /**
     *Private data member to hold the enabled status
     */
    private boolean enabled;
    /**
     *Private data member to hold user type
     */
    private char type;

    //Accessors
    //Getters
    /**
     * Method to retrieve the ID
     * @return id in the long format
     */
    public long getId()
    {
        return id;
    }
    /**
     * Method to retrieve the password
     * @return password         in the String format
     */
    public String getPassword()
    {
        return password;
    }
    /**
     * Method to retrieve the first name
     * @return firstName        in the String format
     */
    public String getFirstName()
    {
        return firstName;
    }
    /**
     * Method to retrieve the last name
     * @return lastName         in the String format
     */
    public String getLastName()
    {
        return lastName;
    }
    /**
     * Method to retrieve the email address
     * @return emailAddress     in the String format
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }
    /**
     * Method to retrieve the last access date
     * @return lastAccess       in the Date format (Canadian)
     */
    public Date getLastAccess()
    {
        return lastAccess;
    }
    /**
     * Method to retrieve the enrolment date
     * @return enrolDate        in the Date format (Canadian)
     */
    public Date getEnrolDate()
    {
        return enrolDate;
    }
    /**
     * Method to retrieve the enabled status
     * @return enabled          in the boolean format
     */
    public boolean isEnabled()
    {
        return enabled;
    }
    /**
     * Method to retrieve the user type
     * @return type             as a char
     */
    public char getType()
    {
        return type;
    }

    //Setters
    /**
     * Method to set the id
     * @param id            must be between a minimum and maximum
     * @exception InvalidIdException  is thrown if anything fails setting the ID
     */
    public void setId(long id) throws InvalidIdException
    {

        if (verifyId(id))
        {
            this.id = id;
        }
        else
        {
            throw new InvalidIdException(id + " must be the appropriate length (9 character).");
        }

    }
    /**
     * Method to set the password
     * @param password      must be between a minimum and maximum length
     * @exception  InvalidPasswordException      is thrown if anything fails setting the password
     */
    public void setPassword(String password) throws InvalidPasswordException
    {
        if (password.length() >= MINIMUM_PASSWORD_LENGTH && password.length() <= MAXIMUM_PASSWORD_LENGTH)
        {
            this.password = password;
        }
        else
        {
            throw new InvalidPasswordException("The length of the password must be between " + MINIMUM_PASSWORD_LENGTH + " and " + MAXIMUM_PASSWORD_LENGTH + " characters long.");
        }

    }

    /**
     * Method to set the first name
     * @param firstName     must be longer than 0 characters and not be all numbers
     * @exception InvalidNameException      is thrown if anything fails setting the first name
     */
    public void setFirstName(String firstName) throws InvalidNameException
    {
        boolean flag = false;
        try
        {
            double test = Double.parseDouble(firstName);
        }
        catch (NumberFormatException ex)
        {
         flag = true;
        }

        if (!(firstName.isEmpty()) && flag)
        {
            this.firstName = firstName;
        }
        else
        {
            throw new InvalidNameException(firstName + " is not a valid name. Make sure there are no numbers or special characters.");
        }
    }

    /**
     * Method to set the last name
     * @param lastName     must be longer than 0 characters and not be all numbers
     * @exception InvalidNameException      is thrown if anything fails setting the last name
     */
    public void setLastName(String lastName) throws InvalidNameException
    {
        boolean flag = false;
        try
        {
            double test = Double.parseDouble(lastName);
        }
        catch (NumberFormatException ex)
        {
            flag = true;
        }

        if (!(lastName.isEmpty()) && flag)
        {
            this.lastName = lastName;
        }
        else
        {
            throw new InvalidNameException(lastName + " is not a valid name. Make sure there are no numbers or special characters.");
        }
    }

    /**
     * Method to set the email address
     * @param emailAddress  a string for the email address
     */
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    /**
     * Method to set the last access date
     * @param lastAccess  a date for the last access date
     */
    public void setLastAccess(Date lastAccess)
    {
        this.lastAccess = lastAccess;
    }

    /**
     * Method to set the enrolment date
     * @param enrolDate  a date for the enrolment day
     */
    public void setEnrolDate(Date enrolDate)
    {
        this.enrolDate = enrolDate;
    }

    /**
     * Method to set the enabled status
     * @param enabled  a boolean (true/false) for the enabled status of the user
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * Method to set the user type
     * @param type  a char that defines the type of user
     */
    public void setType(char type)
    {
        this.type = type;
    }

    //Constructors
    /**
     * Parameterized Constructor
     * @param id            Users ID
     * @param password      Users Password
     * @param firstName     User's First Name
     * @param lastName      User's Last Name
     * @param emailAddress  User's Email Address
     * @param lastAccess    User's Last Access Date
     * @param enrolDate     User's Enrolment Date
     * @param enabled       If User is Enabled
     * @param type          User's Account Type
     * @exception InvalidUserDataException throws an exception if any of the set methods fail
     */
    public User(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type) throws InvalidUserDataException
    {
        try
        {
            setId(id);
            setPassword(password);
            setFirstName(firstName);
            setLastName(lastName);
            setEmailAddress(emailAddress);
            setLastAccess(lastAccess);
            setEnrolDate(enrolDate);
            setEnabled(enabled);
            setType(type);
        }
        catch (Exception e)
        {
            throw new InvalidUserDataException(e.getMessage());
        }

    }

    /**
     * Default Constructor uses all of the default constants and the current date it is called
     */
    public User() throws InvalidUserDataException {
       this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), true, DEFAULT_TYPE);
    }

    //Methods
    /**
     * Returns the user type depending on the child class it is called.
     * @return String of 'User'
     */
    public String getTypeForDisplay()
    {
        return "User";
    }

    /**
     * Overrides the toString method to print out the class to a string.
     * @return a string that prints the relevant objects information using getters
     */
    @Override
    public String toString()
    {
        return  getTypeForDisplay() + "\n{\n" +
                "Student ID    = " + getId() + "\n" +
                "Name          = " + getFirstName() + ' ' + getLastName() + "\n" +
                "Email Address = " + getEmailAddress() + '\n' +
                "Created On    = " + getEnrolDate() + "\n" +
                "Last Access   = " + getLastAccess() + "\n" +
                '}';
    }

    /**
     * Prints the object.
     */
    public void dump()
    {
       System.out.println(toString());
    }

    /**
     * Returns a boolean depending on the id number after being compared to the min and max id numbers allowed.
     * @param id a long variable that represents the id
     * @return boolean
     */
    public static boolean verifyId(long id)
    {
        boolean flag = true;

        if (Long.toString(id).length() != ID_NUMBER_LENGTH)
        {
            flag = false;
        }

        return flag;
    }

    public static String hashPassword(String password)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA1");

            //Add password bytes to digest
            md.update(password.getBytes());

            //Get the hash's bytes
            byte[] bytes = md.digest();

            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(String.format("%02x", bytes[i]));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch ( NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
      //  System.out.println(generatedPassword);
        return generatedPassword;
    }



}
