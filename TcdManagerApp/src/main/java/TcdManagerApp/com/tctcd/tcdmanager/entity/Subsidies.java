package TcdManagerApp.com.tctcd.tcdmanager.entity;

import cn.bmob.v3.BmobObject;

public class Subsidies extends BmobObject{
    private String name;
    private String wordId;
    private String team;
    private String weekendCount;
    private String money;
    private String afternoonTea;
    private String parking;
    private String taxiMoney;
    private String month;
    private String Group;

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setWeekendCount(String weekendCount) {
        this.weekendCount = weekendCount;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setAfternoonTea(String afternoonTea) {
        this.afternoonTea = afternoonTea;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public void setTaxiMoney(String taxiMoney) {
        this.taxiMoney = taxiMoney;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public String getWordId() {
        return wordId;
    }

    public String getTeam() {
        return team;
    }

    public String getWeekendCount() {
        return weekendCount;
    }

    public String getMoney() {
        return money;
    }

    public String getAfternoonTea() {
        return afternoonTea;
    }

    public String getParking() {
        return parking;
    }

    public String getTaxiMoney() {
        return taxiMoney;
    }

    public String getMonth() {
        return month;
    }
}
