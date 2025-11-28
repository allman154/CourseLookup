import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String classYear;
    private List<Major> majorList;

    public Student() {
        majorList = new ArrayList<Major>();
    }

    /**
     * Public access to populate methods. 
     */
    public void populateAdditionalStudentInformation() {
        int returnCode = mockPopulateMajors(); //demoPopulateStudentMajors
        if (returnCode > 0) // failure on major populate - display error message.
        {
            System.err.println("There was a problem loading this student's Majors.");
        }

        returnCode = mockPopulateClassYear(); //demoPopulateClassYear
        if (returnCode > 0) // failure on courses populate - display error message.
        {
            System.err.println("There was a problem loading this student's class year.");
        }
    }

    /**
     * -- OJDBC METHOD STUB EXAMPLE
     * Populate the list of majors for the provided student ID
     * Return a 0 for success or 1 for failure.
     * 
     * @return int returnCode
     */
    private int demoPopulateStudentMajors() {
        int returnCode = 0;
        Connection conn = null;
        ResultSet rset = null;
        PreparedStatement pstmt = null;
        // make the db connection and set up the SQL statement and execute.
        try {
            conn = DriverManager.getConnection("URL", "USER", "PASSWORD");
            pstmt = conn.prepareStatement(
                    "   SELECT  major_desc,"
                            + "         major_code"
                            + " FROM    majors "
                            + " WHERE   major_code NOT IN ('KSC','MGT','ACC')"
                            + "         AND major_stu_id = ? ");
            pstmt.setInt(1, super.getIdNumber());
            rset = pstmt.executeQuery();
            while (rset.next()) {
                Major mj = new Major();
                mj.setMajorDesc("major_desc");
                mj.setMajorCode("major_code");
                majorList.add(mj);
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
     * Mock populate method for hardcoded major assignement results.
     */
    private int mockPopulateMajors() {
        int returnCode = 0;

        Major mj;
        if (super.getIdNumber() == 123456) {
            mj = new Major();
            mj.setMajorDesc("Computer Science");
            mj.setMajorCode("CSE");
            majorList.add(mj);
        }
        if (super.getIdNumber() == 7891011) {
            mj = new Major();
            mj.setMajorDesc("Music");
            mj.setMajorCode("MUS");
            majorList.add(mj);

        }
        if (super.getIdNumber() == 12131415) {
            mj = new Major();
            mj.setMajorDesc("Biology");
            mj.setMajorCode("BIO");
            majorList.add(mj);

        }
        if (super.getIdNumber() == 16171819) {
            mj = new Major();
            mj.setMajorDesc("Education");
            mj.setMajorCode("EDU");
            majorList.add(mj);

        }
        if (super.getIdNumber() == 20212223) {
            mj = new Major();
            mj.setMajorDesc("Business");
            mj.setMajorCode("BUS");
            majorList.add(mj);

        }
        return returnCode;
    }

    /**
     * -- OJDBC METHOD STUB EXAMPLE
     * Populate the class year for THIS student.
     * Return a 0 for success or 1 for failure.
     * 
     * @return int returnCode
     */
    private int demoPopulateClassYear() {
        int returnCode = 0;
        Connection conn = null;
        ResultSet rset = null;
        PreparedStatement pstmt = null;
        // make the db connection and set up the SQL statement and execute.
        try {
            conn = DriverManager.getConnection("URL", "USER", "PASSWORD");
            pstmt = conn.prepareStatement(
                    "   SELECT  classyear"
                            + " FROM    studentdata "
                            + " WHERE   stu_id = ? ");
            pstmt.setInt(1, super.getIdNumber());
            rset = pstmt.executeQuery();
            while (rset.next()) {
                classYear = rset.getString("classyear");
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
     * Mock populate method for hardcoded major assignement results.
     */
    private int mockPopulateClassYear() {
        classYear = "Sophomore";
        return 0;
    }

    public String getClassYear() {
        return classYear;
    }

    public void setClassYear(String classYear) {
        this.classYear = classYear;
    }

    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }

}
