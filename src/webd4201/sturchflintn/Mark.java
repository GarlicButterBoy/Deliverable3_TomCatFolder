package webd4201.sturchflintn;

import java.text.DecimalFormat;

/**
 * Mark class that doesn't extend anything will act as a storage for grades from Faculty, for Students
 * @author Nick Sturch-Flint - 100303769
 * @version 1.0 (January 11th, 2021)
 * @since 1.0 (January 8th, 2021)
 */
public class Mark
{
    //CONSTANTS
    /**
     * Minimum Possible GPA
     */
    public static final float MINIMUM_GPA = 0.0f;
    /**
     * Maximum Possible GPA
     */
    public static final float MAXIMUM_GPA = 5.0f;
    /**
     * Pattern to set the format of the printed GPAs
     */
    String pattern = "0#.##"; //sets the format for Decimals
    /**
     * Updates the format
     */
    DecimalFormat GPA = new DecimalFormat(pattern);

    //VARIABLES
    /**
     * Private data variable to store the course code
     */
    private String courseCode;
    /**
     * Private data variable to store the course name
     */
    private String courseName;
    /**
     * Private data variable to store the result
     */
    private int result;
    /**
     * Private data variable that stores the gpa weight
     */
    private float gpaWeighting;

    //CONSTRUCTORS
    /**
     * Parameterized Constructor
     * @param courseCode    Class Code
     * @param courseName    Class Name
     * @param result        Class Grade
     * @param gpaWeighting  GPA Weight
     */
    public Mark(String courseCode, String courseName, int result, float gpaWeighting)
    {
        setCourseCode(courseCode);
        setCourseName(courseName);
        setResult(result);
        setGpaWeighting(gpaWeighting);
    }

    /**
     * DEFAULT CONSTRUCTOR
     */
    public Mark()
    {
        this.courseCode = "00000";
        this.courseName = "DEFAULT";
        this.result = 0;
        this.gpaWeighting = 0.0f;
    }

    //ACCESSORS
    //Getters
    /**
     * Method to retrieve the Course Code
     * @return courseCode    as a String
     */
    public String getCourseCode()
    {
        return courseCode;
    }
    /**
     * Method to retrieve the Course Name
     * @return courseName    as a String
     */
    public String getCourseName()
    {
        return courseName;
    }
    /**
     * Method to retrieve the result
     * @return result        as an int
     */
    public int getResult()
    {
        return result;
    }
    /**
     * Method to retrieve the GPA Weight
     * @return gpaWeighting  as a floar
     */
    public float getGpaWeighting()
    {
        return gpaWeighting;
    }

    //Setters
    /**
     * Method to set the Course Code
     * @param courseCode     in a String format
     */
    public void setCourseCode(String courseCode)
    {
        this.courseCode = courseCode;
    }
    /**
     * Method to set the Course Name
     * @param courseName     in a String format
     */
    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }
    /**
     * Method to set the Result
     * @param result         in an int format
     */
    public void setResult(int result)
    {
        this.result = result;
    }
    /**
     * Method to set the GPA Weight
     * @param gpaWeighting   in a float format
     */
    public void setGpaWeighting(float gpaWeighting)
    {
        this.gpaWeighting = gpaWeighting;
    }

    //METHODS
    /**
     * An Override method that returns the object in a string format
     * @return object as a string
     */
    @Override
    public String toString()
    {
        return  String.format("%-20", getCourseCode()) +
                String.format("%-50", getCourseName()) +
                String.format("%-15", getResult()) +
                String.format("%-15", getGpaWeighting());
    }
}
