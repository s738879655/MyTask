package com.demo.mytask;

public class checkBoxListDetail {

    String btnName,btnValue;

    public checkBoxListDetail(String btnName, String btnValue) {
        this.btnName = btnName;
        this.btnValue = btnValue;
    }

    public checkBoxListDetail() {
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getBtnValue() {
        return btnValue;
    }

    public void setBtnValue(String btnValue) {
        this.btnValue = btnValue;
    }
}
