package com.demo.mytask;

public class buttonListDetail {


    String btnName,btnValue;

    public buttonListDetail(String btnName, String btnValue) {
        this.btnName = btnName;
        this.btnValue = btnValue;
    }

    public buttonListDetail() {
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
