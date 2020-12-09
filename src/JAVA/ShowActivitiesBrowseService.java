package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowActivitiesBrowseService {

    private Connection con;

    public String getShowActivitiesBrowseToJSON(MySqlDbConnection db) {

        String activityJSONFormat = "{\"id\":\"{ID}\",\"week\":\"{WEEK}\",\"area\":\"{AREA}\",\"type\":\"{TYPE}\",\"estim_time\":\"{ESTIM_TIME}\"}";
        StringBuilder activityJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();

        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            //int id_specification;
            //String name_specification;
            rs = stmt.executeQuery("SELECT * FROM Activity");

            while (rs.next()) {
                JSONRow = activityJSONFormat.replace("{ID}", Integer.toString(rs.getInt(1)));
                JSONRow = JSONRow.replace("{WEEK}", Integer.toString(rs.getInt(2)));
                JSONRow = JSONRow.replace("{AREA}", Util.utf8Encode(rs.getString(3)));
                JSONRow = JSONRow.replace("{TYPE}", Util.utf8Encode(rs.getString(4)));
                //id_specification = rs.getInt(5);
                //name_specification = this.getSpecificationsNameById(id_specification);
                JSONRow = JSONRow.replace("{ESTIM_TIME}", Integer.toString(rs.getInt(5)));
                activityJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "[" + Util.removeLastChar(activityJSONResult.toString()) + "]";
    }

    //metodo utile per fare delle query tra più tabelle
    /*  private String getSpecificationsNameById(int id_specification) {
        String name_specification = "";
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT int_des  FROM Specifications WHERE id =" + id_specification);
            if (rs.next())
                name_specification = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return name_specification;
    }*/

}
