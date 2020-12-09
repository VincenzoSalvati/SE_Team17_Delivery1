package JAVA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("SqlResolve")
public class AssignHoursBrowseServiceJSP {
    String name_maint = "";
    int id = 0;
    int skills = 0;
    int skillTot = 0;
    int h8to9 = 0;
    int h9to10 = 0;
    int h10to11 = 0;
    int h11to12 = 0;
    int h14to15 = 0;
    int h15to16 = 0;
    int h16to17 = 0;
    private Connection con;

    public String getAssignHoursBrowseToJSONJSP(MySqlDbConnection db, int activity, int week, int specifications, int maintainer, String day) {
        String assignMaintainerBrowseJSONFormat = "{\"id\":\"{ID}\",\"maint\":\"{MAINT}\",\"skills\":\"{SKILLS}\",\"h8to9\":\"{h8to9}\",\"h9to10\":\"{h9to10}\",\"h10to11\":\"{h10to11}\",\"h11to12\":\"{h11to12}\",\"h14to15\":\"{h14to15}\",\"h15to16\":\"{h15to16}\",\"h16to17\":\"{h16to17}\"}";
        String assignMaintainerJSONResult = "";
        String JSONRow;

        con = db.connect();

        try {

            //prelievo ore disponibili
            getAvailabilityHours(maintainer, day, week);

            //conteggio skills manutentori
            skills = getSkill(maintainer, specifications);
            if (skills == -1) return "";

            //conteggio skills totali
            skillTot = getSkillTot(specifications);
            if (skillTot == -1) return "";


            JSONRow = assignMaintainerBrowseJSONFormat.replace("{ID}", Integer.toString(id));
            JSONRow = JSONRow.replace("{MAINT}", name_maint);
            JSONRow = JSONRow.replace("{SKILLS}", Integer.toString(skills));
            JSONRow = JSONRow.replace("{h8to9}", Integer.toString(h8to9));
            JSONRow = JSONRow.replace("{h9to10}", Integer.toString(h9to10));
            JSONRow = JSONRow.replace("{h10to11}", Integer.toString(h10to11));
            JSONRow = JSONRow.replace("{h11to12}", Integer.toString(h11to12));
            JSONRow = JSONRow.replace("{h14to15}", Integer.toString(h14to15));
            JSONRow = JSONRow.replace("{h15to16}", Integer.toString(h15to16));
            JSONRow = JSONRow.replace("{h16to17}", Integer.toString(h16to17));
            assignMaintainerJSONResult = JSONRow;

            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "[" + assignMaintainerJSONResult + "]";

    }

    //prelievo ore disponibili
    private void getAvailabilityHours(int maintainer, String day, int week) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT a.avail_8to9, a.avail_9to10, a.avail_10to11, a.avail_11to12, a.avail_14to15, a.avail_15to16, a.avail_16to17, m.name, a.id FROM maintainer AS m, availability AS a WHERE a.id_maint=m.id AND a.id_maint=" + maintainer + " AND a.day=\"" + day + "\"" + " AND a.week=\"" + week + "\"");
            while (rs.next()) {
                h8to9 = rs.getInt(1);
                h9to10 = rs.getInt(2);
                h10to11 = rs.getInt(3);
                h11to12 = rs.getInt(4);
                h14to15 = rs.getInt(5);
                h15to16 = rs.getInt(6);
                h16to17 = rs.getInt(7);
                name_maint = rs.getString(8);
                id = rs.getInt(9);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //conteggio skills manutentori
    private int getSkill(int maintainer, int specifications) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT COUNT(*) FROM skill_maintainer_view AS sm, skill_need_view AS sn WHERE sm.skill = sn.skill AND sm.id=" + maintainer + " AND sn.specifications=" + specifications);
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    //conteggio skill totali
    private int getSkillTot(int specifications) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT COUNT(*) FROM need WHERE specifications=" + specifications);
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

}
