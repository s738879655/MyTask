package com.demo.mytask;



public class DishDetail {

   String  itemName,quantityAmount,priceValue,timeValue,customStatus,imageUri,checkboxStatus,buttonStatus,commentStatus;

   public DishDetail(){}

    public DishDetail( String itemName,  String quantityAmount, String priceValue, String timeValue,String customStatus,String imageUri,String checkboxStatus,String buttonStatus,String commentStatus) {

        this.itemName = itemName;

        this.quantityAmount = quantityAmount;

        this.priceValue = priceValue;

        this.timeValue = timeValue;
        this.customStatus=customStatus;
        this.imageUri=imageUri;
        this.checkboxStatus=checkboxStatus;
        this.buttonStatus=buttonStatus;
        this.commentStatus=commentStatus;
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
