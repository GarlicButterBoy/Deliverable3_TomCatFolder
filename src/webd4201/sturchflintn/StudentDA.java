/**
 * DatabaseConnect - Used to manage database connectivity
 * @author Nick Sturch-Flint
 * @version 1.1.0 (Feb 6, 2021)
 * @since 1.1.0
 */
package webd4201.sturchflintn;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class StudentDA
{
    static Vector<Student> students = new Vector<>();	// contains student references
    static Student aStudent;

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
     * Private Data variable to store a Program Code
     */
    private static String programCode;
    /**
     * Private Data variable to store a Program Description
     */
    private static String programDescription;
    /**
     * Private Data variable to store the students current year
     */
    private static int year;
    /**
     * Private Vector to store the marks the student has in each class
     */
    private static Vector<Mark> marks;

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
     * @return Student  Object form of the data collected for a student
     * @throws NotFoundException    throws an exception if the user cannot be found
     */
    public static Student retrieve(long id) throws NotFoundException, SQLException, InvalidIdException, InvalidNameException, InvalidPasswordException, InvalidUserDataException
    { // retrieve Customer and Boat data

        aStudent = new Student();
        // define the SQL query statement using the phone number key
        PreparedStatement sqlQuery = aConnection.prepareStatement("SELECT users.id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type, program_code, program_description, year FROM users, students WHERE users.id = students.id AND students.id = ?;");
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
                programCode = rs.getString("program_code");
                programDescription = rs.getString("program_description");
                year = rs.getInt("year");
                marks = new Vector<>();

                // create Student
                try
                {
                   // System.out.println(id + " " + password + " " + firstName + " " + lastName + " " + emailAddress + " " + lastAccess + " " + enrolDate + " " + enabled + " " + type + " " + programCode + " " + programDescription + " " + year + " " + marks );
                    aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year);
                   // aStudent = new Student();
                }
                catch (InvalidUserDataException e)
                {

                    System.out.println(e.getMessage() + id + " contains an invalid ID.  Verify and correct.");
                }

            }
            else	// nothing was retrieved
            {
                throw (new NotFoundException("Problem retrieving Student record, ID " + id +" does not exist in the system."));
            }
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return aStudent;
    }

    /**
     * Method retrieve all of the records in the students table
     * @return students     A vector containing all of the rows
     */
    public static Vector<Student> retrieveAll()
    {
        // retrieve Customers and their boats
        // define the SQL query statement for get all
        String sqlQuery = "SELECT * FROM students";
        try
        {   // execute the SQL query statement
            ResultSet rs = aStatement.executeQuery(sqlQuery);
            boolean moreData = rs.next();

            while (moreData)
            {	// extract the data
                id = rs.getLong(1);
                password = rs.getString(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                emailAddress = rs.getString(5);
                lastAccess = rs.getDate(6);
                enrolDate = rs.getDate(7);
                enabled = rs.getBoolean(8);
                type = rs.getString(9).charAt(0);
                programCode = rs.getString(10);
                programDescription = rs.getString(11);
                year = rs.getInt(12);

                // try tp create Customer instance
                try{
                    aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year, marks);
                }catch (InvalidUserDataException e)
                { System.out.println("Record for " + id + " contains an invalid id.  Verify and correct.");}

                students.addElement(aStudent);
                moreData = rs.next();
            }
            rs.close();
        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
        return students;
    }

    /**
     * Method accepts a student object to be used to INSERT into the DB
     * @param aStudent      an object that contains student details
     * @return inserted     a boolean to tell if the insert passed/failed
     * @throws InvalidUserDataException throws an exception if the user data is invalid
     */
    public static boolean create(Student aStudent) throws DuplicateException, InvalidIdException, InvalidNameException, InvalidPasswordException, InvalidUserDataException, SQLException, NoSuchAlgorithmException {

        //MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");


        boolean inserted = false; //insertion success flag
        // retrieve the student attribute values
        id = aStudent.getId();
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        lastAccess = aStudent.getLastAccess();
        enrolDate = aStudent.getEnrolDate();
        enabled = aStudent.isEnabled();
        type = aStudent.getType();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();
        marks = aStudent.getMarks();
        java.sql.Date lastAccessDate = new java.sql.Date(lastAccess.getTime());
        java.sql.Date ogEnrolDate = new java.sql.Date(enrolDate.getTime());
        String hashword = aStudent.hashPassword(password);
        // create the SQL insert statement using attribute values
        //messageDigest.update(Byte.parseByte(password));
        //Prepare the user table statement
        PreparedStatement sqlUserQuery = aConnection.prepareStatement("INSERT INTO users (id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        //Prepare the student table statement
        PreparedStatement sqlStudentQuery = aConnection.prepareStatement("INSERT INTO students (id, program_code, program_description, year) " +
                "VALUES (?, ?, ?, ?);");

        //Set the appropriate values for the student prepared statement
        sqlUserQuery.setLong(1, id);
        sqlUserQuery.setString(2, hashword);
        sqlUserQuery.setString(3, firstName);
        sqlUserQuery.setString(4, lastName);
        sqlUserQuery.setString(5, emailAddress);
        sqlUserQuery.setDate(6, lastAccessDate);
        sqlUserQuery.setDate(7, ogEnrolDate);
        sqlUserQuery.setBoolean(8, enabled);
        sqlUserQuery.setString(9, String.valueOf(type));
        //Set the appropriate values for the student prepared statement
        sqlStudentQuery.setLong(1, id);
        sqlStudentQuery.setString(2, programCode);
        sqlStudentQuery.setString(3, programDescription);
        sqlStudentQuery.setInt(4, year);


        String sqlInsertStudent = "INSERT INTO students (id, program_code, program_description, year) " +
                "VALUES ('" + id + "', '" + programCode + "', '" + programDescription + "', '" + year +"');";
      //  System.out.println(sqlInsertStudent);
      //  System.out.println(sqlInsertUser);
        // see if this customer already exists in the database
        try
        {
            retrieve(id);
            throw (new DuplicateException("Problem with creating Student record, the Student ID: " + id +"; already exists in the system."));
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
                int rs1 = sqlStudentQuery.executeUpdate();
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
     * Method that deletes a user and student from the DB
     * @param aStudent      object containing data
     * @return records      number of records changed
     * @throws NotFoundException     throws an exception if the user cannot be found
     */
    public static int delete(Student aStudent) throws NotFoundException, InvalidIdException, InvalidNameException, InvalidPasswordException, InvalidUserDataException, SQLException {
        int records = 0;
        // retrieve the id (key)
        id = aStudent.getId();
        // create the SQL delete statement
        String sqlDeleteStudent = "DELETE FROM students WHERE id = ?;";
        String sqlDeleteUser = "DELETE FROM users WHERE id = ?;";
        PreparedStatement sqlUserDelete = aConnection.prepareStatement(sqlDeleteUser);
        PreparedStatement sqlStudentDelete = aConnection.prepareStatement(sqlDeleteStudent);
        sqlUserDelete.setLong(1, id);
        sqlStudentDelete.setLong(1, id);


        // see if this customer already exists in the database
        try
        {
            retrieve(id);  //used to determine if record exists for the passed Customer
            //System.out.println(sqlUserQuery);
            records = sqlStudentDelete.executeUpdate();
            records = sqlUserDelete.executeUpdate();

            // if found, execute the SQL update statement
            //records = aStatement.executeUpdate(sqlDeleteStudent);
            //records = aStatement.executeUpdate(sqlDeleteUser);
        }catch(NotFoundException e)
        {
            throw new NotFoundException("Student with id " + id
                    + " cannot be deleted, does not exist.");
        }catch (SQLException e)
        { System.out.println(e.getMessage());	}
        return records;
    }

    /**
     * Method updates a user in both the users and students table
     * @param aStudent      object that contains data
     * @return records      number of records updated
     * @throws NotFoundException    throws an exception if the user cannot be found
     */
    public static int update(Student aStudent) throws NotFoundException, InvalidIdException, InvalidNameException, InvalidPasswordException, InvalidUserDataException, SQLException {
        int records = 0;  //records updated in method

        // retrieve the student argument attribute values
        id = aStudent.getId();
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        lastAccess = aStudent.getLastAccess();
        enrolDate = aStudent.getEnrolDate();
        enabled = aStudent.isEnabled();
        type = aStudent.getType();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();
        marks = aStudent.getMarks();
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




        String sqlUpdateStudent = "UPDATE students " +
                "SET program_code = ?, " +
                "program_description = ?, " +
                "year = ? " +
                "WHERE id = ?";
        PreparedStatement sqlStudentUpdate = aConnection.prepareStatement(sqlUpdateStudent);
        sqlStudentUpdate.setString(1, programCode);
        sqlStudentUpdate.setString(2, programDescription);
        sqlStudentUpdate.setInt(3, year);
        sqlStudentUpdate.setLong(4, id);



        //System.out.println(sqlUpdateStudent);
        //System.out.println(sqlUpdateUser);
        // see if this customer exists in the database
        // NotFoundException is thrown by find method
        try
        {
            retrieve(id);  //determine if there is a student record to be updated
            // if found, execute the SQL update statement
            records = sqlUserUpdate.executeUpdate();
            records = sqlStudentUpdate.executeUpdate();
        }catch(NotFoundException e)
        {
            throw new NotFoundException("Student with id " + id
                    + " cannot be updated, does not exist in the system.");
        }catch (SQLException e)
        { System.out.println(e.getMessage());}
        return records;
    }

    /**
     * Authenticate Method is used to take a students id and password, if both match a student in the db, then return that student
     * @param studentNum         users student number entered as a long
     * @param password           user's password entered as a string
     * @return aStudent          as an object
     * @throws NotFoundException throws an exception if the user cannot be found
     */
    public static Student authenticate(long studentNum, String password) throws NotFoundException, InvalidIdException, SQLException, InvalidNameException, InvalidPasswordException, InvalidUserDataException {

        try
        {

        if (retrieve(studentNum) != null) //if the retrieve doesn't fail
        {
            Student aStudent = retrieve(studentNum); //then pull the data for comparison
            System.out.println("This student was retrieved and equals: \n" + aStudent.toString());
            //System.out.println(aStudent.getPassword());
            //System.out.println(Student.hashPassword(password));
            if (aStudent.getPassword().equals(Student.hashPassword(password)))
            {
                System.out.println("This password is correct");
                return aStudent;
            }
            else
            {
                throw new InvalidPasswordException("The password is incorrect. Please try again.");
            }
        }
        }
        catch(NotFoundException e)
        {
            throw new NotFoundException("Student with id  " + aStudent.getId()
                    + " cannot be found.");
        }


        return aStudent;
    }
}
