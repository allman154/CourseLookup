import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private String courseNum;
    private String courseType;
    private int courseId;
    private Faculty assignedFaculty;
    private List<Student> registeredStudents;
    private List<String> daysOfTheWeek;

    public Course() {
        daysOfTheWeek = new ArrayList<String>();
    }

    /**
     * Populate the additional Course information and return an error code if there
     * was a problem.
     * 
     * @return
     */
    public int populateAdditionalCourseData() {
        int returnCode = 0;

        returnCode = mockPopulateRegisteredStudents(); // mockPopulateRegisteredStudents
        if (returnCode != 0) {
            System.err.println("There was a problem loading the student for course: " + this.courseName);
            return returnCode;
        }
        returnCode = mockPopulateAssignedFaculty(); // mockPopulateAssignedFaculty
        if (returnCode != 0) {
            System.err
                    .println("There was a problem loading the assigned factuly member for course: " + this.courseName);
            return returnCode;
        }

        returnCode = mockPopulateDaysOfTheWeek(); // mockPopulateDaysOfTheWeek
        if (returnCode != 0) {
            System.err.println("There was a problem loading the days of the week for course: " + this.courseName);
            return returnCode;
        }

        returnCode = mockPopulateAssignedFaculty(); // mockPopulateDaysOfTheWeek
        if (returnCode != 0) {
            System.err.println("There was a problem loading the days of the week for course: " + this.courseName);
            return returnCode;
        }

        return returnCode;
    }

    /**
     * -- OJDBC METHOD STUB EXAMPLE
     * Populate the list of students who are registered for THIS course.
     * Return a 0 for success or 1 for failure.
     * 
     * @return int returnCode
     */
    private int demoPopulateRegisteredStudents() {
        int returnCode = 0;
        registeredStudents = new ArrayList<Student>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        // make the db connection and set up the SQL statement and execute.
        try {
            conn = DriverManager.getConnection("URL", "USER", "PASSWORD");
            pstmt = conn.prepareStatement(
                    "   SELECT  stu.first_name  f_name,"
                            + "         stu.last_name l_name,"
                            + "         stu.id st_id,"
                            + "         stu.username st_un"
                            + " FROM    student stu,"
                            + "         registration reg"
                            + " WHERE   reg.stuid = stu.id"
                            + "         AND reg.courseid = ?");
            pstmt.setInt(1, this.courseId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                Student st = new Student();
                st.setFirstName(rset.getString("f_name"));
                st.setLastName(rset.getString("l_name"));
                st.setIdNumber(rset.getInt("st_id"));
                st.setUserId(rset.getString("st_un"));
                st.populateAdditionalStudentInformation();
                registeredStudents.add(st);
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
     * Mock method to populate hardcoded registered student list.
     * 
     * @return
     */
    private int mockPopulateRegisteredStudents() {
        registeredStudents = new ArrayList<>();
        Student stu;

        stu = new Student();
        stu.setFirstName("Biff");
        stu.setLastName("Tannen");
        stu.setIdNumber(123456);
        stu.setUserId("tannenb");
        stu.populateAdditionalStudentInformation();
        registeredStudents.add(stu);
        stu = new Student();
        stu.setFirstName("James");
        stu.setLastName("Kirk");
        stu.setIdNumber(7891011);
        stu.setUserId("kirkj");
        stu.populateAdditionalStudentInformation();
        registeredStudents.add(stu);
        stu = new Student();
        stu.setFirstName("Marty");
        stu.setLastName("McFly");
        stu.setIdNumber(12131415);
        stu.setUserId("mcflym");
        stu.populateAdditionalStudentInformation();
        registeredStudents.add(stu);
        stu = new Student();
        stu.setFirstName("Ellen");
        stu.setLastName("Ripley");
        stu.setIdNumber(16171819);
        stu.setUserId("ripleye");
        stu.populateAdditionalStudentInformation();
        registeredStudents.add(stu);
        stu = new Student();
        stu.setFirstName("Peggy");
        stu.setLastName("Olson");
        stu.setIdNumber(20212223);
        stu.setUserId("olsonp");
        stu.populateAdditionalStudentInformation();
        registeredStudents.add(stu);
        return 0;
    }

    /**
     * -- OJDBC METHOD STUB EXAMPLE
     * Populate the assigned faculty for THIS Course.
     * Return a 0 for success or 1 for failure.
     * 
     * @return int returnCode
     */
    private int demoPopulateAssignedFaculty() {
        int returnCode = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        // make the db connection and set up the SQL statement and execute.
        try {
            conn = DriverManager.getConnection("URL", "USER", "PASSWORD");
            pstmt = conn.prepareStatement(
                    "   SELECT  fac.first_name  f_name,"
                            + "         fac.last_name l_name,"
                            + "         fac.id fac_id,"
                            + "         fac.username fac_un"
                            + " FROM    faculty fac,"
                            + "         courseassignements crs"
                            + " WHERE   crs.facultyid = fac.id"
                            + "         AND crs.courseid = ?");
            pstmt.setInt(1, this.courseId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                assignedFaculty = new Faculty();
                assignedFaculty.setFirstName(rset.getString("f_name"));
                assignedFaculty.setLastName(rset.getString("l_name"));
                assignedFaculty.setIdNumber(rset.getInt("fac_id"));
                assignedFaculty.setUserId(rset.getString("fac_un"));
                assignedFaculty.populateAdditionalDetails();
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
     * Mock method to populate hardcoded assigned faculty
     * This will assign the same faculty member to each class for demo purposes. 
     * @return
     */
    private int mockPopulateAssignedFaculty() {
        this.assignedFaculty = new Faculty();
        assignedFaculty.setFirstName("Jean-Luc");
        assignedFaculty.setLastName("Picard");
        assignedFaculty.setIdNumber(123456);
        assignedFaculty.setDepartment("Science");
        assignedFaculty.setOfficeRoomNum("1701-D");
        assignedFaculty.setUserId("picardj");
        return 0;
    }

    /**
     * -- OJDBC METHOD STUB EXAMPLE
     * Populate the days of the week for THIS Course.
     * Return a 0 for success or 1 for failure.
     * 
     * @return int returnCode
     */
    private int demoPopulateDaysOfTheWeek() {
        int returnCode = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        // make the db connection and set up the SQL statement and execute.
        try {
            conn = DriverManager.getConnection("URL", "USER", "PASSWORD");
            pstmt = conn.prepareStatement(
                    "   SELECT DECODE(course_days,"
                            + "     'M', 'Monday', "
                            + "     'T', 'Tuesday', "
                            + "     'W', 'Wednesday', "
                            + "     'Th', 'Thursday', "
                            + "     'F','Friday', course_days) cdays"
                            + " FROM    courses"
                            + " WHERE   crs.facultyid = fac.id");
            pstmt.setInt(1, this.courseId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                daysOfTheWeek.add(rset.getString("cdays"));
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
     * Mock method to populate hardcoded course days of the week meeting times.
     * 
     * @return
     */
    private int mockPopulateDaysOfTheWeek() {
        daysOfTheWeek.add("Monday");
        daysOfTheWeek.add("Wednesday");
        daysOfTheWeek.add("Friday");
        return 0;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(List<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public List<String> getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public void setDaysOfTheWeek(List<String> daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Faculty getAssignedFaculty() {
        return assignedFaculty;
    }

    public void setAssignedFaculty(Faculty assignedFaculty) {
        this.assignedFaculty = assignedFaculty;
    }

}
