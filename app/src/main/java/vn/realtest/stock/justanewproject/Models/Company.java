package vn.realtest.stock.justanewproject.Models;

/**
 * Created by Tran on 20-Jan-18.
 */

public class Company {
    private String ID;
    private String Name;

    public Company(String ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public Company() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
