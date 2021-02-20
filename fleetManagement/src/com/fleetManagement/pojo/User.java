package com.fleetManagement.pojo;

//’Àªß

public class User {

    private String name;
    private String passworld;

    public User(String name, String passworld) {
        this.name = name;
        this.passworld = passworld;
    }
    
    public User() {
    	
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassworld() {
        return passworld;
    }

    public void setPassworld(String passworld) {
        this.passworld = passworld;
    }
    
    @Override
    public String toString() {
        return "{\r\n" +
                "name='" + name + '\'' +
                ", \r\n sex=" + passworld +
                "\r\n}";
    }
}
