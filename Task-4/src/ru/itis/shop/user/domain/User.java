package ru.itis.shop.user.domain;

public class User {

    private String id;

    private String email;
    private String password;

    private String profileDescription;

    private String userName;

    public User() { }

    public User(String id, String email, String password, String profileDescription, String  userName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
        this.userName = userName;
    }

    public User(String email, String password, String profileDescription, String userName) {
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }
}
