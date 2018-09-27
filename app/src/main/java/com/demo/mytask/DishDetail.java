package com.demo.mytask;



public class DishDetail {

   String  itemName,quantityAmount,priceValue,timeValue,customStatus,imageUri;

   public DishDetail(){}

    public DishDetail( String itemName,  String quantityAmount, String priceValue, String timeValue,String customStatus,String imageUri) {

        this.itemName = itemName;

        this.quantityAmount = quantityAmount;

        this.priceValue = priceValue;

        this.timeValue = timeValue;
        this.customStatus=customStatus;
        this.imageUri=imageUri;
    }

    public String getImageUri() {
        return imageUri;
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
