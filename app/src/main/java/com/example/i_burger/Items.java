package com.example.i_burger;

import org.json.JSONObject;

public class Items {

    private int background_img;
    int imglogo;
    private String itemname1;
    private  String itemname2;
    private int itemprice1;
    private int itemprice2;
    int CartQuantity=0;
    private String itemnameselected;
    int price;

    private int backgroundtn=setBackgroundbtn(R.drawable.button1);
    private int plusbtnImage=setPlusBtnImage(R.drawable.button3);
    private int minusbtnImage=setMinusBtnImage(R.drawable.button2);


     public Items(int backgroundImg, String snakNameSelected, int price, int snakImgLogo){
         this.background_img=backgroundImg;
         this.imglogo=snakImgLogo;
         this.price=price;
         this.itemnameselected=snakNameSelected;

     }


     public Items(int backgroundImg, int snaksImgLogo, String snakName1, String snakNmae2, int snakPrice1, int snakPrice2){
         this.background_img=backgroundImg;
         this.imglogo=snaksImgLogo;
         this.itemname1=snakName1;
         this.itemname2=snakNmae2;
         this.itemprice1=snakPrice1;
         this.itemprice2=snakPrice2;

     }


     public String getJsonObject(){
         JSONObject cartItems= new JSONObject();
         try{

             cartItems.put("ProductName",itemnameselected);
             cartItems.put("ProductLogoImg",imglogo);
             cartItems.put("ProductPrice",price);
             cartItems.put("ProductImg",background_img);
             cartItems.put("CartQuantity",CartQuantity);

         }

         catch (Exception e){}

             return cartItems.toString();


     }

     public int getBackgroungImg() {
         return background_img;
     }
     public void setBackground_img(int backgroundImg){
        this.background_img=backgroundImg;
     }


     public int getImglogo(){return imglogo; }
     public void setImglogo(int imglogo){this.imglogo=imglogo; }

    public String getItemName1(){return itemname1;}
    public void setItemname1(String itemname1){this.itemname1=itemname1;}

    public String getItemName2(){return itemname2;}
    public void setItemname2(String itemname2){this.itemname2=itemname2;}

    public int getItemPrice1(){return itemprice1;}
    public void setItemprice1(int itemprice1){this.itemprice1=itemprice1;}

    public int getItemPrice2() {return itemprice2;}
    public void setItemprice2(int itemprice2){this.itemprice2=itemprice2;}

    public int getCartQuantity(){return CartQuantity;}
    public void setCartquantity(int cartquantity){this.CartQuantity=cartquantity;}

    public String getItemNameSelected(){return itemnameselected;}
    public void setItemNameSelected(String itemnameselected){this.itemnameselected=itemnameselected;}

    public int getPrice(){return  price;}
    public void setPrice(int price){this.price=price;}

    public int getBackgroundBtn(){return backgroundtn;}
    public int setBackgroundbtn(int backgroundtn){
         this.backgroundtn=backgroundtn;
         return backgroundtn;
     }

    public int getPlusbtnImage(){return plusbtnImage;}
    public int setPlusBtnImage(int plusbtnImage){
         this.plusbtnImage=plusbtnImage;
         return plusbtnImage;
     }

    public int getMinusbtnImage(){return minusbtnImage;}
    public int setMinusBtnImage(int minusBtnImage){
         this.minusbtnImage=minusBtnImage;
         return minusBtnImage;
     }








}