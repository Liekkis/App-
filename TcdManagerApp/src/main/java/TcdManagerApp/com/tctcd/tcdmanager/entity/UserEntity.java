package TcdManagerApp.com.tctcd.tcdmanager.entity;

import cn.bmob.v3.BmobObject;

public class UserEntity extends BmobObject {
    private String userName;
    private String password;
    private String Team;
    private String permission;
    private String Name;
    private String ID;
    private String Group;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getTeam() {
        return Team;
    }

    public String getPermission() {
        return permission;
    }

    public String getName() {
        return Name;
    }

    public String getID() {
        return ID;
    }

    public String getGroup() {
        return Group;
    }
}
