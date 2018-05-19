package TcdManagerApp.com.tctcd.tcdmanager.entity;

import cn.bmob.v3.BmobObject;

public class UserEntity extends BmobObject {
    private String userName;
    private String password;
    private String department;
    private String question;
    private String answer;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartment() {
        return department;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
