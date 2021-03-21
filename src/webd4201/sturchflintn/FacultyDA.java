/**
 * FacultyDA - Used to interact with the Database for Faculty user types
 * @author Nick Sturch-Flint
 * @version 1.1.1 (Feb 16, 2021)
 * @since 1.1.1
 */
package webd4201.sturchflintn;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class FacultyDA
{
    static Vector<Student> faculty = new Vector<>();	// contains faculty references
    static Faculty aFaculty;

    // declare variables for the database connection
    static Connection aConnection;
    static Statement aStatement;

    // declare static variables for all student instance attribute values
    /**
     * Private data member to hold id
     */
    private static long id;
    /**
     *Private data member to hold password
     */
    private static String password;
    /**
     *Private data member to hold first name
     */
    private static String firstName;
    /**
     *Private data member to hold last name
     */
    private static String lastName;
    /**
     *Private data member to hold email address
     */
    private static String emailAddress;
    /**
     *Private data member to hold the last access
     */
    private static Date lastAccess;
    /**
     *Private data member to holds the enrol date
     */
    private static Date enrolDate;
    /**
     *Private data member to hold the enabled status
     */
    private static boolean enabled;
    /**
     *Private data member to hold user type
     */
    private static char type;
    /**
     * Private Data member to hold the faculty School Code
     */
    private static String school_code;
    /**
     * Private data member to hold the faculty School Description
     */
    private static String school_description;
    /**
     * Private Data member to hold the faculty office
     */
    private static String office;
    /**
     * Private data member to hold the faculty phone extension
     */
    private static int phone_extension;

    //Class Constant
    /**
     * Updates the date format to be the same everywhere
     */
    private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");

    // establish the database connection
    /**
     * This method will create a statement to be used in DB connection
     * @param c   A string that holds the DB connection
     */
    public static void initialize(Connection c)
    {
        try {
            aConnection=c;
            aStatement=aConnection.createStatement();
        }
        catch (SQLException e)
        { System.out.println(e.getMessage());	}
    }

    // close the database connection
    /**
     * This method will terminate (close) a statement that is used in DB connection
     */
    public static void terminate()
    {
        try
        { 	// close the statement
            aStatement.close();
        }
        catch (SQLException e)
        { System.out.println(e.getMessage());	}
    }

    /**
     * This method checks the id against all in the DB
     * @param id      Checks the id of the potential new user against all existing users
     * @return Faculty  Object form of the data collected for a student
     * @throws NotFoundException    throws an exception if the user cannot be found
     */
    public static Faculty retrieve(long id) throws NotFoundException, SQLException, InvalidIdException, InvalidNameException, InvalidPasswordException, InvalidUserDataException
    { // retrieve Customer and Boat data

        aFaculty = new Faculty();
        // define the SQL query statement using the phone number key
        PreparedStatement sqlQuery = aConnection.prepareStatement("SELECT users.id, password, first_name, last_name, email_address, last_access, enrol_date, " +
                "enabled, type, school_code, school_description, office, phone_extension FROM users, faculty WHERE users.id = faculty.id AND faculty.id = ?;");
        //System.out.println(sqlQuery);
        sqlQuery.setLong(1, id);
        // execute the SQL query statement
        try
        {
            ResultSet rs = sqlQuery.executeQuery();
            // next method sets cursor & returns true if there is data
            boolean gotIt = rs.next();
            if (gotIt)
            {	// extract the data
                id = rs.getLong("id");
                password = rs.getString("password");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                emailAddress = rs.getString("email_address");
                lastAccess = rs.getDate("last_access");
                enrolDate = rs.getDate("enrol_date");
                enabled = rs.getBoolean("enabled");
                type = 's';
                school_code = rs.getString("school_code");
                school_description = rs.getString("school_description");
                office = rs.getString("office");
                phone_extension = rs.getInt("phone_extension");

                // create Faculty
                try
                {

                    aFaculty = new Faculty(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, school_code, school_description, office, phone_extension);

                }
                catch (InvalidUserDataException e)
                {

                    System.out.println(e.getMessage() + id + " contains an invalid ID.  Verify and correct.");
                }

            }
            else	// nothing was retrieved
            {
                throw (new NotFoundException("Problem retrieving Faculty record, ID " + id +" does not exist in the system."));
            }
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return aFaculty;
    }

    /**
     * Method accepts a student object to be used to INSERT into the DB
     * @param aFaculty      an object that contains student details
     * @return inserted     a boolean to tell if the insert passed/failed
     * @throws InvalidUserDataException throws an exception if the user data is invalid
     */
    public static boolean create(Faculty aFaculty) throws DuplicateException, InvalidIdException, InvalidNameException, InvalidPasswordException, InvalidUserDataException, SQLException, NoSuchAlgorithmException
    {

        boolean inserted = false; //insertion success flag
        // retrieve the student attribute values
        id = aFaculty.getId();
        password = aFaculty.getPassword();
        firstName = aFaculty.getFirstName();
        lastName = aFaculty.getLastName();
        emailAddress = aFaculty.getEmailAddress();
        lastAccess = aFaculty.getLastAccess();
        enrolDate = aFaculty.getEnrolDate();
        enabled = aFaculty.isEnabled();
        type = aFaculty.getType();
        school_code = aFaculty.getSchoolCode();
        school_description = aFaculty.getSchoolDescription();
        office = aFaculty.getOffice();
        phone_extension = aFaculty.getExtension();
        java.sql.Date lastAccessDate = new java.sql.Date(lastAccess.getTime());
        java.sql.Date ogEnrolDate = new java.sql.Date(enrolDate.getTime());
        String hashword = aFaculty.hashPassword(password);
        // create the SQL insert statement using attribute values
        //messageDigest.update(Byte.parseByte(password));
        //Prepare the user table statement
        PreparedStatement sqlUserQuery = aConnection.prepareStatement("INSERT INTO users (id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        //Prepare the student table statement
        PreparedStatement sqlFacultyQuery = aConnection.prepareStatement("INSERT INTO faculty (id, school_code, school_description, office, phone_extension) " +
                "VALUES (?, ?, ?, ?, ?);");

        //Set the appropriate values for the faculty prepared statement
        sqlUserQuery.setLong(1, id);
        sqlUserQuery.setString(2, hashword);
        sqlUserQuery.setString(3, firstName);
        sqlUserQuery.setString(4, lastName);
        sqlUserQuery.setString(5, emailAddress);
        sqlUserQuery.setDate(6, lastAccessDate);
        sqlUserQuery.setDate(7, ogEnrolDate);
        sqlUserQuery.setBoolean(8, enabled);
        sqlUserQuery.setString(9, String.valueOf(type));
        //Set the appropriate values for the faculty prepared statement
        sqlFacultyQuery.setLong(1, id);
        sqlFacultyQuery.setString(2, school_code);
        sqlFacultyQuery.setString(3, school_description);
        sqlFacultyQuery.setString(4, office);
        sqlFacultyQuery.setInt(5, phone_extension);

      //  String sqlInsertStudent = "INSERT INTO faculty (id, school_code, school_description, office, phone_extension) " +
        //        "VALUES ('" + id + "', '" + school_code + "', '" + school_description + "', '" + office +"', '" + phone_extension + "');";
        //  System.out.println(sqlInsertStudent);
        //  System.out.println(sqlInsertUser);
        // see if this customer already exists in the database
        try
        {
            retrieve(id);
            throw (new DuplicateException("Problem with creating Student record, the Faculty ID: " + id +"; already exists in the system."));
        }
        // if NotFoundException, add customer to database
        catch(DuplicateException | NotFoundException | SQLException e)
        {
            try
            {  // execute the SQL update statement
                //Execute the statement
                // System.out.println(sqlStudentQuery);

                //System.out.println(sqlUserQuery);
                int rs = sqlUserQuery.executeUpdate();
                int rs1 = sqlFacultyQuery.executeUpdate();
                if (rs > 0 && rs1 > 0)
                {
                    inserted = true;
                }

                // inserted = aStatement.execute(sqlInsertUser);
                // inserted = aStatement.execute(sqlInsertStudent);
            }
            catch (SQLException ee)
            { System.out.println(ee.getMessage());	}
        }
        return inserted;
    }

    /**
     * Method that deletes a user and faculty from the DB
     * @param aFaculty      object containing data
     * @return records      number of records changed
     * @throws NotFoundException     throws an exception if the user cannot be found
     */
    public static int delete(Faculty aFaculty) throws NotFoundException, InvalidIdException, InvalidNameException, InvalidPasswordException, InvalidUserDataException, SQLException {
        int records = 0;
        // retrieve the id (key)
        id = aFaculty.getId();
        // create the SQL delete statement
        String sqlDeleteFaculty = "DELETE FROM faculty WHERE id = ?;";
        String sqlDeleteUser = "DELETE FROM users WHERE id = ?;";
        PreparedStatement sqlUserDelete = aConnection.prepareStatement(sqlDeleteUser);
        PreparedStatement sqlFacultyDelete = aConnection.prepareStatement(sqlDeleteFaculty);
        sqlUserDelete.setLong(1, id);
        sqlFacultyDelete.setLong(1, id);


        // see if this customer already exists in the database
        try
        {
            retrieve(id);  //used to determine if record exists for the passed Customer
            //System.out.println(sqlUserQuery);
            records = sqlFacultyDelete.executeUpdate();
            records = sqlUserDelete.executeUpdate();

            // if found, execute the SQL update statement
            //records = aStatement.executeUpdate(sqlDeleteStudent);
            //records = aStatement.executeUpdate(sqlDeleteUser);
        }catch(NotFoundException e)
        {
            throw new NotFoundException("Faculty with id " + id
                    + " cannot be deleted, does not exist.");
        }catch (SQLException e)
        { System.out.println(e.getMessage());	}
        return records;
    }

    /**
     * Method updates a user in both the users and students table
     * @param aFaculty      object that contains data
     * @return records      number of records updated
     * @throws NotFoundException    throws an exception if the user cannot be found
     */
    public static int update(Faculty aFaculty) throws NotFoundException, InvalidIdException, InvalidNameException, InvalidPasswordException, InvalidUserDataException, SQLException {
        int records = 0;  //records updated in method

        // retrieve the student argument attribute values
        id = aFaculty.getId();
        password = aFaculty.getPassword();
        firstName = aFaculty.getFirstName();
        lastName = aFaculty.getLastName();
        emailAddress = aFaculty.getEmailAddress();
        lastAccess = aFaculty.getLastAccess();
        enrolDate = aFaculty.getEnrolDate();
        enabled = aFaculty.isEnabled();
        type = aFaculty.getType();
        school_code = aFaculty.getSchoolCode();
        school_description = aFaculty.getSchoolDescription();
        office = aFaculty.getOffice();
        phone_extension = aFaculty.getExtension();
        java.sql.Date lastAccessDate = new java.sql.Date(lastAccess.getTime());
        java.sql.Date ogEnrolDate = new java.sql.Date(enrolDate.getTime());

        // define the SQL query statement
        String sqlUpdateUser = "UPDATE users " +
                "SET first_name = ?, " +
                "last_name = ?, " +
                "email_address = ?, " +
                "last_access = ?, " +
                "enrol_date = ?, " +
                "type = ?, " +
                "enabled = ? " +
                "WHERE id = ?";
        PreparedStatement sqlUserUpdate = aConnection.prepareStatement(sqlUpdateUser);
        sqlUserUpdate.setString(1, firstName);
        sqlUserUpdate.setString(2, lastName);
        sqlUserUpdate.setString(3, emailAddress);
        sqlUserUpdate.setDate(4, lastAccessDate);
        sqlUserUpdate.setDate(5, ogEnrolDate);
        sqlUserUpdate.setString(6, String.valueOf(type));
        sqlUserUpdate.setBoolean(7, enabled);
        sqlUserUpdate.setLong(8, id);

        String sqlUpdateFaculty = "UPDATE faculty " +
                "SET school_code = ?, " +
                "school_description = ?, " +
                "office = ?, " +
                "phone_extension = ? " +
                "WHERE id = ?";
        PreparedStatement sqlFacultyUpdate = aConnection.prepareStatement(sqlUpdateFaculty);
        sqlFacultyUpdate.setString(1, school_code);
        sqlFacultyUpdate.setString(2, school_description);
        sqlFacultyUpdate.setString(3, office);
        sqlFacultyUpdate.setInt(4, phone_extension);
        sqlFacultyUpdate.setLong(5, id);

        // see if this customer exists in the database
        // NotFoundException is thrown by find method
        try
        {
            retrieve(id);  //determine if there is a student record to be updated
            // if found, execute the SQL update statement
            records = sqlUserUpdate.executeUpdate();
            records = sqlFacultyUpdate.executeUpdate();
        }catch(NotFoundException e)
        {
            throw new NotFoundException("Faculty with id " + id
                    + " cannot be updated, does not exist in the system.");
        }catch (SQLException e)
        { System.out.println(e.getMessage());}
        return records;
    }
}
