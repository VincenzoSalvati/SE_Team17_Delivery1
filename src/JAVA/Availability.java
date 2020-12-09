package JAVA;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Availability {

    private final Connection con;
    public int dbOperationStatusCode;
    public String dbOperationMessage;
    private int id;
    private int day;
    private int week;
    private int avail_8to9;
    private int avail_9to10;
    private int avail_10to11;
    private int avail_11to12;
    private int avail_14to15;
    private int avail_15to16;
    private int avail_16to17;

    public Availability(MySqlDbConnection db, int id) {
        this.con = db.connect();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getAvail_8to9() {
        return avail_8to9;
    }

    public void setAvail_8to9(int avail_8to9) {
        this.avail_8to9 = avail_8to9;
    }

    public int getAvail_9to10() {
        return avail_9to10;
    }

    public void setAvail_9to10(int avail_9to10) {
        this.avail_9to10 = avail_9to10;
    }

    public int getAvail_10to11() {
        return avail_10to11;
    }

    public void setAvail_10to11(int avail_10to11) {
        this.avail_10to11 = avail_10to11;
    }

    public int getAvail_11to12() {
        return avail_11to12;
    }

    public void setAvail_11to12(int avail_11to12) {
        this.avail_11to12 = avail_11to12;
    }

    public int getAvail_14to15() {
        return avail_14to15;
    }

    public void setAvail_14to15(int avail_14to15) {
        this.avail_14to15 = avail_14to15;
    }

    public int getAvail_15to16() {
        return avail_15to16;
    }

    public void setAvail_15to16(int avail_15to16) {
        this.avail_15to16 = avail_15to16;
    }

    public int getAvail_16to17() {
        return avail_16to17;
    }

    public void setAvail_16to17(int avail_16to17) {
        this.avail_16to17 = avail_16to17;
    }

    public int getDbOperationStatusCode() {
        return this.dbOperationStatusCode;
    }

    public String getDbOperationMessage() {
        return this.dbOperationMessage;
    }

    public String getResults() {
        String availabilityBrowseJSONFormat = "{\"result_code\":\"{code}\"}";
        String JSONRow = availabilityBrowseJSONFormat.replace("{code}", this.getDbOperationMessage());
        return "[" + JSONRow + "]";
    }

    public void update() {
        try {
            String query = "UPDATE availability SET " +
                    "avail_8to9 = ?, avail_9to10 = ?, avail_10to11 = ?, avail_11to12 = ?, avail_14to15 = ?, avail_15to16 = ?, avail_16to17 = ? WHERE id= ?";
            PreparedStatement preparedStmt = this.con.prepareStatement(query);
            preparedStmt.setInt(1, this.getAvail_8to9());
            preparedStmt.setInt(2, this.getAvail_9to10());
            preparedStmt.setInt(3, this.getAvail_10to11());
            preparedStmt.setInt(4, this.getAvail_11to12());
            preparedStmt.setInt(5, this.getAvail_14to15());
            preparedStmt.setInt(6, this.getAvail_15to16());
            preparedStmt.setInt(7, this.getAvail_16to17());
            preparedStmt.setInt(8, this.getId());
            preparedStmt.execute();
            this.dbOperationStatusCode = 0;
            this.dbOperationMessage = "Record updated";
        } catch (Exception e) {
            this.dbOperationStatusCode = 1;
            this.dbOperationMessage = "Record not updated: " + e.getMessage();
        }
    }

}





