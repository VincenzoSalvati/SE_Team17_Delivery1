package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowActivitiesBrowseService;

public class ShowActivitiesBrowseServiceTester {

    public static void main(String[] args) {
        String jsonResult;

        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        ShowActivitiesBrowseService service = new ShowActivitiesBrowseService();
        jsonResult = service.getShowActivitiesBrowseToJSON(db);

        System.out.println(jsonResult);
    }

}
