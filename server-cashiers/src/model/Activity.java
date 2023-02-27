package model;

public class Activity {
    private String date;
    private int id;
    private String movement;
    private int detail;
    private String comment;
    private String cashier_name;
    private String cashier_identifier;

    public Activity() {
    }

    public Activity(String date, int id, String movement, int detail, String comment, String cashier_name, String cashier_identifier) {
        this.date = date;
        this.id = id;
        this.movement = movement;
        this.detail = detail;
        this.comment = comment;
        this.cashier_name = cashier_name;
        this.cashier_identifier = cashier_identifier;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public int getDetail() {
        return detail;
    }

    public void setDetail(int detail) {
        this.detail = detail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCashier_name() {
        return cashier_name;
    }

    public void setCashier_name(String cashier_name) {
        this.cashier_name = cashier_name;
    }

    public String getCashier_identifier() {
        return cashier_identifier;
    }

    public void setCashier_identifier(String cashier_identifier) {
        this.cashier_identifier = cashier_identifier;
    }
}
