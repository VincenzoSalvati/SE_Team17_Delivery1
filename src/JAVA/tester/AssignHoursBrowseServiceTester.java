package JAVA.tester;

import JAVA.AssignHoursBrowseService;
import JAVA.MySqlDbConnection;

public class AssignHoursBrowseServiceTester {
    public static void main(String[] args) {
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        AssignHoursBrowseService service = new AssignHoursBrowseService();
        String jsonResult = service.getAssignHoursBrowseToJSON(db);
        System.out.println(jsonResult);

    }
}
