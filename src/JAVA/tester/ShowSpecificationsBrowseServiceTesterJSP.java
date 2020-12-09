package JAVA.tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowSpecificationsBrowseServiceJSP;

public class ShowSpecificationsBrowseServiceTesterJSP {

    public static void main(String[] args) {

        String jsonResult;
        MySqlDbConnection db = new MySqlDbConnection();
        db.setDbUser("root");
        db.setDbPassword("admin");
        db.setDbName("Project");

        ShowSpecificationsBrowseServiceJSP service = new ShowSpecificationsBrowseServiceJSP();
        jsonResult = service.getShowSpecificationsBrowseToJSONJSP(db, 2, 1);
        System.out.println(jsonResult);

    }

}
