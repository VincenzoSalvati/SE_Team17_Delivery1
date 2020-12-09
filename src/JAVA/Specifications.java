package JAVA;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Specifications {

    private final Connection con;
    public int dbOperationStatusCode;
    public String dbOperationMessage;
    private int id;
    private String new_work_note;

    public Specifications(MySqlDbConnection db, int id) {
        this.con = db.connect();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWork_note() {
        return new_work_note;
    }

    public void setWork_note(String new_work_note) {
        this.new_work_note = new_work_note;
    }

    public int getDbOperationStatusCode() {
        return dbOperationStatusCode;
    }

    public String getDbOperationMessage() {
        String specificationsBrowseJSONFormat = "{\"id\":\"{ID}\",\"work_note\":\"{WORK_NOTE}\"}";
        String JSONRow = specificationsBrowseJSONFormat.replace("{ID}", Util.utf8Encode(String.valueOf(this.getId())));
        JSONRow = JSONRow.replace("{WORK_NOTE}", Util.utf8Encode(this.getWork_note()));
        return "[" + JSONRow + "]";
    }

    public void update() {
        try {
            String query = "UPDATE specifications SET " +
                    "work_note = ? WHERE id= ?";
            PreparedStatement preparedStmt = this.con.prepareStatement(query);
            preparedStmt.setString(1, this.new_work_note);
            preparedStmt.setInt(2, this.id);
            preparedStmt.execute();
            this.dbOperationStatusCode = 0;
            this.dbOperationMessage = "Record updated";
        } catch (Exception e) {
            this.dbOperationStatusCode = 1;
            this.dbOperationMessage = "Record not updated: " + e.getMessage();
        }
    }

}





