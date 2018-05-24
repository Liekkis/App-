package TcdManagerApp.com.tctcd.tcdmanager.entity;

import cn.bmob.v3.BmobObject;

public class Pay extends BmobObject {
    private String Spay;
    private String paid;
    private String Group;
    private String Unpaid;
    private String peopleCount;

    public void setPeopleCount(String peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getPeopleCount() {
        return peopleCount;
    }

    public void setSpay(String spay) {
        Spay = spay;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public void setUnpaid(String unpaid) {
        Unpaid = unpaid;
    }

    public String getSpay() {
        return Spay;
    }

    public String getPaid() {
        return paid;
    }

    public String getGroup() {
        return Group;
    }

    public String getUnpaid() {
        return Unpaid;
    }
}
