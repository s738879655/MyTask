package com.demo.mytask;

/**
 * Created by sudha on 30-Sep-18.
 */

public class ToppingListDetails {
    String toppingname,toppingprice;

    public String getToppingname() {
        return toppingname;
    }

    public void setToppingname(String toppingname) {
        this.toppingname = toppingname;
    }

    public String getToppingprice() {
        return toppingprice;
    }

    public void setToppingprice(String toppingprice) {
        this.toppingprice = toppingprice;
    }

    public ToppingListDetails(String toppingname, String toppingprice) {

        this.toppingname = toppingname;
        this.toppingprice = toppingprice;
    }

    public ToppingListDetails() {

    }
}
