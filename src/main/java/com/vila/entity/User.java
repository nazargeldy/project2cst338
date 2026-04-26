/**
 * Represents the user in the application.
 * I added two constructors.
 * One for new users and one for users with an account.
 *
 * @author Adrian Alonso
 * @version 1.0
 * @since 4/25/2026
 */

package com.vila.entity;

public class User {
    private String userName;
    private String userPassword;
    private String userRole;
    private int userID;

    /**
     * This constructor is used to create new users.
     * @param userName the nickname of the user.
     * @param userPassword the password of the user
     * @param userRole the role of the user (admin, guest, or user)
     */
    public User(String userName, String userPassword, String userRole) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

    /**
     * This constructor is used for already existing users.
     * @param userName the nickname of the user.
     * @param userPassword the password of the user.
     * @param userRole the role of the user.
     * @param userID the id of the user.
     */
    public User(String userName, String userPassword, String userRole, int userID) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() { //Will be changed later (maybe)
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userID=" + userID +
                '}';
    }
}
