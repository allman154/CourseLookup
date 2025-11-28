import java.util.HashMap;

public class CourseDaySeparator {

    private HashMap<String, Course> monWedFriCourses;
    private HashMap<String, Course> tuesThursCourses;

    public CourseDaySeparator() {
        // Populate the list of Courses.
        Courses courses = new Courses();
        courses.populateCourseInformation();
        // Iterate through the master list and separate them into HashMaps by Course
        // day.
        monWedFriCourses = new HashMap<String, Course>();
        tuesThursCourses = new HashMap<String, Course>();
        for (Course crs : courses.getCourseList()) {
            for (String day : crs.getDaysOfTheWeek()) {
                switch (day) {
                    case "Monday":
                        monWedFriCourses.put(crs.getCourseNum(), crs);
                        break;
                    case "Tuesday":
                        tuesThursCourses.put(crs.getCourseNum(), crs);
                        break;
                    case "Wednesday":
                        monWedFriCourses.put(crs.getCourseNum(), crs);
                        break;
                    case "Thursday":
                        tuesThursCourses.put(crs.getCourseNum(), crs);
                        break;
                    case "Friday":
                        monWedFriCourses.put(crs.getCourseNum(), crs);
                        break;
                    default:
                        break;
                }
            }
        }

        // Print out the results of the HashMaps and their details:
        printCourseGroup("Monday, Wednesday, Friday courses", monWedFriCourses);
        System.out.println();
        printCourseGroup("Tuesday, Thursday courses", tuesThursCourses);
    }

    private void printCourseGroup(String header, HashMap<String, Course> courseMap) {
        System.out.println("=====================");
        System.out.println(header);
        System.out.println("=====================");

        if (courseMap == null || courseMap.isEmpty()) {
            System.out.println("No courses in this list.");
            return;
        }

        for (Course crs : courseMap.values()) {
            printCourseDetails(crs);
        }
    }

    private void printCourseDetails(Course crs) {
        System.out.println(crs.getCourseName() + " - " + crs.getCourseNum());
        System.out.println("---------------------");
        System.out.println("  Type:      " + crs.getCourseType());
        System.out.println("  Course ID: " + crs.getCourseId());
        System.out.println(
                "  Faculty: " + crs.getAssignedFaculty().getFirstName() + " " + crs.getAssignedFaculty().getLastName());
        System.out.println("  " +
                crs.getAssignedFaculty().getDepartment() + " - " + crs.getAssignedFaculty().getOfficeRoomNum());

        System.out.println();
        System.out.println("  Students: ");
        for (Student stu : crs.getRegisteredStudents()) {
            System.out.println("      " + stu.getFirstName() + " " + stu.getLastName());
        }
        System.out.println();
    }

    public HashMap<String, Course> getMonWedFriCourses() {
        return monWedFriCourses;
    }

    public void setMonWedFriCourses(HashMap<String, Course> monWedFriCourses) {
        this.monWedFriCourses = monWedFriCourses;
    }

    public HashMap<String, Course> getTuesThursCourses() {
        return tuesThursCourses;
    }

    public void setTuesThursCourses(HashMap<String, Course> tuesThursCourses) {
        this.tuesThursCourses = tuesThursCourses;
    }

}
