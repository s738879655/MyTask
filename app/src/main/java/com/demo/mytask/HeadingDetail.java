package com.demo.mytask;

/**
 * Created by sudha on 30-Sep-18.
 */

public class HeadingDetail {
    String headingname,headingnote;

    public HeadingDetail() {
    }

    public HeadingDetail(String headingname, String headingnote) {
        this.headingname = headingname;
        this.headingnote = headingnote;
    }

    public String getHeadingname() {

        return headingname;
    }

    public void setHeadingname(String headingname) {
        this.headingname = headingname;
    }

    public String getHeadingnote() {
        return headingnote;
    }

    public void setHeadingnote(String headingnote) {
        this.headingnote = headingnote;
    }
}
