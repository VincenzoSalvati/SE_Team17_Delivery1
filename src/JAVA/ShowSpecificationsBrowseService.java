package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("DuplicatedCode")
public class ShowSpecificationsBrowseService {

    private Connection con;

    public String getShowSpecificationsBrowseToJSON(MySqlDbConnection db) {

        String specificationsBrowseJSONFormat = "{\"id\":\"{ID}\",\"work_note\":\"{WORK_NOTE}\",\"int_des\":\"{INT_DES}\",\"id_activity\":\"{ID_ACTIVITY}\",\"week_activity\":\"{WEEK_ACTIVITY}\",\"skill\":\"{SKILL}\"}";
        StringBuilder specificationsBrowseJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();

        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM specifications WHERE id_activity=" + 1 + " AND week_activity = " + 1);
            int i = 0;
            while (rs.next()) {
                JSONRow = specificationsBrowseJSONFormat.replace("{ID}", rs.getString(1));
                JSONRow = JSONRow.replace("{WORK_NOTE}", Util.utf8Encode(rs.getString(2)));
                JSONRow = JSONRow.replace("{INT_DES}", Util.utf8Encode(rs.getString(3)));
                JSONRow = JSONRow.replace("{WEEK_ACTIVITY}", Util.utf8Encode(rs.getString(5)));
                JSONRow = JSONRow.replace("{ID_ACTIVITY}", Util.utf8Encode(this.getActivityNameById(rs.getInt(4))));
                JSONRow = JSONRow.replace("{SKILL}", Util.utf8Encode(this.getSkillNameById(Integer.parseInt(rs.getString(1)))));
                specificationsBrowseJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "[" + Util.removeLastChar(specificationsBrowseJSONResult.toString()) + "]";

    }

    private String getActivityNameById(int activityId) {

        String activityName = "";
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT site FROM activity WHERE id =" + activityId);
            if (rs.next())
                activityName = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return activityId + " - " + activityName;

    }

    private String getSkillNameById(int specificationsId) {

        StringBuilder skillName = new StringBuilder();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT name FROM need as ne, skill as sk WHERE ne.specifications=" + specificationsId + " AND sk.id = ne.skill");
            int i = 0;
            while (rs.next()) {
                if (i == 0) {
                    skillName = new StringBuilder("- " + rs.getString(1) + "<br>");
                    i = i + 1;
                } else
                    skillName.append("- ").append(rs.getString(1)).append("<br>");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return skillName.toString();
    }

}
