import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Faculty extends Person {
    private String department;
    private String officeRoomNum;

    public Faculty() {
    }

    public int populateAdditionalDetails() {
        int returnCode = 0;
        returnCode = mockPopulateAdditionalDetails();
        return returnCode;
    }

    /**
     * -- OJDBC METHOD STUB EXAMPLE
     * Populate the department and Office information for THIS faculty memeber.
     * Return a 0 for success or 1 for failure.
     * 
     * @return int returnCode
     */
    private int demoPopulateAdditionalDetails() {
        int returnCode = 0;
        Connection conn = null;
        ResultSet rset = null;
        PreparedStatement pstmt = null;
        // make the db connection and set up the SQL statement and execute.
        try {
            conn = DriverManager.getConnection("URL", "USER", "PASSWORD");
            pstmt = conn.prepareStatement(
                    "       SELECT  department, "
                            + "         building_name || ' ' || room_number office"
                            + " FROM    facultydata, "
                            + "         buildingdata"
                            + " WHERE   fac_id = ? "
                            + "         AND fac_id = building_fac_id");
            pstmt.setInt(1, super.getIdNumber());
            rset = pstmt.executeQuery();
            while (rset.next()) {
                department = rset.getString("department");
                officeRoomNum  = rset.getString("office");
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
     * Mock populate method for hardcoded Department and Office results.
     */
    private int mockPopulateAdditionalDetails() {
        department = "Biology";
        officeRoomNum = "Science Building RM 100";
        return 0;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOfficeRoomNum() {
        return officeRoomNum;
    }

    public void setOfficeRoomNum(String officeRoomNum) {
        this.officeRoomNum = officeRoomNum;
    }

}
