package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsBrowseService;

public class ShowSpecificationsBrowseServiceTester {

    public static void main(String[] args) {

        String jsonResult;
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        ShowSpecificationsBrowseService service = new ShowSpecificationsBrowseService();
        jsonResult = service.getShowSpecificationsBrowseToJSON(db);
        System.out.println(jsonResult);

    }

}
