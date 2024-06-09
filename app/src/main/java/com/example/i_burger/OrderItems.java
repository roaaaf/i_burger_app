package com.example.i_burger;

public class OrderItems {

    public String oderName;

    public int orderBckGroundImg;

    public int price;
    public int CartQuantity=0;
    public int orderLogoImg;
    int TotalPrice=0;


    public OrderItems(String ordername, int orderBckGroundImg, int price, int orderLogoImg){
        this.oderName=ordername;
        this.orderBckGroundImg=orderBckGroundImg;
        this.orderLogoImg=orderLogoImg;
        this.price=price;

    }

    public String getOrderName(){return oderName;}
    public void setOderName(String orderName){this.oderName=orderName;}

    public int getOrderBackgroundImg(){return orderBckGroundImg;}
    public void setOrderBckGroundImg(int orderBckGroundImg){this.orderBckGroundImg=orderBckGroundImg;}

    public int getOrderlogoImg(){return orderLogoImg;}
    public void setOrderLogoImg(int orderLogoImg){this.orderLogoImg=orderLogoImg;}

    public int getPrice(){return price;}
    public void setPrice(int price){this.price=price;}

    public int getCartQuantity(){return CartQuantity;}
    public void setCartQuantity(int cartQuantity){this.CartQuantity=cartQuantity;}

    public int getTotalPrice(){return TotalPrice;}
    public void setTotalPrice(int totalPrice){this.TotalPrice=totalPrice;}







}