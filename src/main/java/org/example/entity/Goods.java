package org.example.entity;

/**
 * StoreManagementSystem
 * 商品
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 10:02
 * @since JDK17
 */

public class Goods {
    /**
     * 标识
     */
    int id;
    /**
     * 商店号
     */
    String storeNumber;
    /**
     * 商店名
     */
    String storeName;
    /**
     * 规格
     */
    String model;
    /**
     * 单价
     */
    String price;

    public Goods() {
    }

    public Goods(int id, String storeNumber, String storeName, String model, String price) {
        this.id = id;
        this.storeNumber = storeNumber;
        this.storeName = storeName;
        this.model = model;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
