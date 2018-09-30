package com.demo.mytask;



public class DishDetail {

   String  itemName,quantityAmount,priceValue,timeValue,customStatus,imageUri,checkboxStatus,buttonStatus,commentStatus,toppingstatus,itemtype,discount,disclaimer,quantitytype;

   public DishDetail(){}

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public DishDetail(String itemName, String quantityAmount, String priceValue, String itemtype, String timeValue, String customStatus, String imageUri, String checkboxStatus, String buttonStatus, String commentStatus,String toppingstatus,String discount,String disclaimer,String quantitytype) {

        this.itemName = itemName;
        this.toppingstatus = toppingstatus;
        this.quantityAmount = quantityAmount;
        this.discount = discount;
        this.priceValue = priceValue;
        this.itemtype = itemtype;
        this.disclaimer = disclaimer;
        this.quantitytype = quantitytype;
        this.timeValue = timeValue;
        this.customStatus=customStatus;
        this.imageUri=imageUri;
        this.checkboxStatus=checkboxStatus;
        this.buttonStatus=buttonStatus;
        this.commentStatus=commentStatus;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getToppingstatus() {
        return toppingstatus;
    }

    public void setToppingstatus(String toppingstatus) {
        this.toppingstatus = toppingstatus;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getCheckboxStatus() {
        return checkboxStatus;
    }

    public void setCheckboxStatus(String checkboxStatus) {
        this.checkboxStatus = checkboxStatus;
    }

    public String getButtonStatus() {
        return buttonStatus;
    }

    public void setButtonStatus(String buttonStatus) {
        this.buttonStatus = buttonStatus;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getCustomStatus() {

        return customStatus;
    }

    public void setCustomStatus(String customStatus) {
        this.customStatus = customStatus;
    }



    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }



    public String getQuantityAmount() {
        return quantityAmount;
    }

    public void setQuantityAmount(String quantityAmount) {
        this.quantityAmount = quantityAmount;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }



    public String getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(String timeValue) {
        this.timeValue = timeValue;
    }
}
