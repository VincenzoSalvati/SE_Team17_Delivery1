package JAVA.tester;

import JAVA.AssignMaintainerBrowseService;
import JAVA.MySqlDbConnection;

public class AssignMaintainerBrowseServiceTester {
    public static void main(String[] args) {
        String jsonResult;
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        AssignMaintainerBrowseService service = new AssignMaintainerBrowseService();
        jsonResult = service.getAssignMaintainerBrowseToJSON(db);
        System.out.println(jsonResult);

    }
}
