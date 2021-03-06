package project.baonq.model;
// Generated Jun 3, 2018 2:37:40 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthday;
    private Date insertDate = new Date();
    private Date lastUpdate = new Date();
    private int status;
    private UserSetting userSetting;

    public User() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserSetting getUserSetting() {
        return userSetting;
    }

    public void setUserSetting(UserSetting userSetting) {
        this.userSetting = userSetting;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getInsertDate() {
        return this.insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

//    public int getStatus() {
//        return this.status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }

//    public UserSetting getUserSetting() {
//        return this.userSetting;
//    }
//
//    public void setUserSetting(UserSetting userSetting) {
//        this.userSetting = userSetting;
//    }


}
