package JAVA.tester;

import JAVA.AssignMaintainerBrowseServiceJSP;
import JAVA.MySqlDbConnection;

public class AssignMaintainerBrowseServiceTesterJSP {
    public static void main(String[] args) {
        String jsonResult;
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        AssignMaintainerBrowseServiceJSP service = new AssignMaintainerBrowseServiceJSP();
        jsonResult = service.getAssignMaintainerBrowseToJSONJSP(db, 2, 1, 0);
        System.out.println(jsonResult);

    }
}
