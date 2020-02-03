/**
 * User class will hold attributes of each User. Each User class will have a userName, index position and userCount
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */


import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private int indexPos;
    private static int userCount;

    public User(String newUserName, int newIndexPos, int newUserCount) {
        this.userName = newUserName;
        this.indexPos = newIndexPos;
        this.userCount = newUserCount;
    }

    public String getUserName() { return this.userName; }
    public int getIndexPos() {
        return this.indexPos;
    }
    public static int getUserCount() {
        return userCount;
    }
    public void setUserName(String newUserName) { this.userName = newUserName; }
    public void setIndexPos(int newIndexPos) { this.indexPos = newIndexPos; }
    public static void setUserCount(int newUserCount) { userCount = newUserCount; }
    public String toString() {
        return userName + " " + indexPos + " " + userCount;
    }
}
