package JAVA.tester;

import JAVA.AssignHoursBrowseServiceJSP;
import JAVA.MySqlDbConnection;

public class AssignHoursBrowseServiceTesterJSP {
    public static void main(String[] args) {
        String jsonResult;
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        AssignHoursBrowseServiceJSP service = new AssignHoursBrowseServiceJSP();
        jsonResult = service.getAssignHoursBrowseToJSONJSP(db, 2, 1, 2, 0, "Monday");
        System.out.println(jsonResult);
    }
}
