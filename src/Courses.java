import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Courses {
    private List<Course> courseList;

    public Courses() {
        courseList = new ArrayList<Course>();
    }

    public int populateCourseInformation() {
        int returnCode = 0;
        returnCode = mockPopulateCourseList(); // demoPopulateCourseList();
        return returnCode;
    }

    /**
     * -- OJDBC METHOD STUB EXAMPLE
     * Populate the list of courses for processing.
     * Return a 0 for success or 1 for failure.
     * 
     * @return int returnCode
     */
    private int demoPopulateCourseList() {
        int returnCode = 0;
        Connection conn = null;
        ResultSet rset = null;
        PreparedStatement pstmt = null;
        // make the db connection and set up the SQL statement and execute.
        try {
            conn = DriverManager.getConnection("URL", "USER", "PASSWORD");
            pstmt = conn.prepareStatement(
                    "   SELECT  course_title,"
                            + "         courseNum, "
                            + "         courseType"
                            + " FROM    courses");
            rset = pstmt.executeQuery();
            while (rset.next()) {
                Course crs = new Course();
                crs.setCourseName(rset.getString("course_title"));
                crs.setCourseNum(rset.getString("course_num"));
                crs.setCourseType(rset.getString("course_type"));
                crs.setCourseId(rset.getInt("course_id"));
                crs.populateAdditionalCourseData();
                courseList.add(crs);
            }
        } // Catch any errors, then close connections etc.
        catch (SQLException e) {
            returnCode = 1;
            e.printStackTrace();
        } finally {
            // Close the result set
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
            // close the preparred statement
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
            // close the DB connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
        }
        // return success or failure.
        return returnCode;
    }

    /**
     * Mock populate method for hardcoded course assignement results.
     * Adds days of the week here for demo purposes. 
     */
    private int mockPopulateCourseList() {
        int returnCode = 0;
        Course crs;
        crs = new Course();
        crs.setCourseName("Chemistry");
        crs.setCourseNum("131");
        crs.setCourseType("Course");
        crs.setCourseId(123);
        crs.getDaysOfTheWeek().add("Monday");
        crs.getDaysOfTheWeek().add("Wednesday");
        crs.getDaysOfTheWeek().add("Friday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("Chemistry Lab");
        crs.setCourseNum("131L");
        crs.setCourseType("Lab");
        crs.setCourseId(456);
        crs.getDaysOfTheWeek().add("Monday");
        crs.getDaysOfTheWeek().add("Tuesday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("Programming I");
        crs.setCourseNum("140");
        crs.setCourseType("Course");
        crs.setCourseId(789);
        crs.getDaysOfTheWeek().add("Monday");
        crs.getDaysOfTheWeek().add("Wednesday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("Programming I Lab");
        crs.setCourseNum("140L");
        crs.setCourseType("Lab");
        crs.setCourseId(112);
        crs.getDaysOfTheWeek().add("Monday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("Calculus I");
        crs.setCourseNum("150");
        crs.setCourseType("Course");
        crs.setCourseId(134);
        crs.getDaysOfTheWeek().add("Monday");
        crs.getDaysOfTheWeek().add("Wednesday");
        crs.getDaysOfTheWeek().add("Friday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("Music Theory I");
        crs.setCourseNum("160A");
        crs.setCourseType("Course");
        crs.setCourseId(156);
        crs.getDaysOfTheWeek().add("Monday");
        crs.getDaysOfTheWeek().add("Friday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("Intro to Psychology");
        crs.setCourseNum("120");
        crs.setCourseType("Course");
        crs.setCourseId(212);
        crs.getDaysOfTheWeek().add("Tuesday");
        crs.getDaysOfTheWeek().add("Thursday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("Creative Writing");
        crs.setCourseNum("101A");
        crs.setCourseType("Course");
        crs.setCourseId(223);
        crs.getDaysOfTheWeek().add("Wednesday");
        crs.getDaysOfTheWeek().add("Friday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("General Biology");
        crs.setCourseNum("110");
        crs.setCourseType("Course");
        crs.setCourseId(224);
        crs.getDaysOfTheWeek().add("Monday");
        crs.getDaysOfTheWeek().add("Wednesday");
        crs.getDaysOfTheWeek().add("Friday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("General Biology Lab");
        crs.setCourseNum("110L");
        crs.setCourseType("Lab");
        crs.setCourseId(225);
        crs.getDaysOfTheWeek().add("Tuesday");
        crs.getDaysOfTheWeek().add("Thursday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("U.S. History");
        crs.setCourseNum("210B");
        crs.setCourseType("Course");
        crs.setCourseId(225);
        crs.getDaysOfTheWeek().add("Monday");
        crs.getDaysOfTheWeek().add("Wednesday");
        crs.getDaysOfTheWeek().add("Friday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("Physics I");
        crs.setCourseNum("230");
        crs.setCourseType("Course");
        crs.setCourseId(226);
        crs.getDaysOfTheWeek().add("Wednesday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        crs = new Course();
        crs.setCourseName("Physics I Lab");
        crs.setCourseNum("230L");
        crs.setCourseType("Lab");
        crs.setCourseId(227);
        crs.getDaysOfTheWeek().add("Tuesday");
        crs.getDaysOfTheWeek().add("Thursday");
        crs.getDaysOfTheWeek().add("Friday");
        crs.populateAdditionalCourseData();
        courseList.add(crs);

        return returnCode;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

}
