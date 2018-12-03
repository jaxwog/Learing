package com.love.jax.bean;

/**
 * com.love.jax.bean
 * Created by jax on 2018/11/29 14:52
 * TODO: 订单信息：套餐名称、ID、款式、价格、数量
 */
public class OrderEntity {

   private String title;
   private String id;
   private String type;
   private String  pice;

   public OrderEntity(){

   }

    public OrderEntity(String title, String id, String type, String pice, int number) {
        this.title = title;
        this.id = id;
        this.type = type;
        this.pice = pice;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPice() {
        return pice;
    }

    public void setPice(String pice) {
        this.pice = pice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;

    @Override
    public String toString() {
        return "OrderEntity{" + "title='" + title + '\'' + ", id='" + id + '\'' + ", type='" +
                type + '\'' + ", pice='" + pice + '\'' + ", number=" + number + '}';
    }
}
