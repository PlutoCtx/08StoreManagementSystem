package org.example.entity;

/**
 * StoreManagementSystem
 * 顾客
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 10:04
 * @since JDK17
 */

public class Customer {

    /**
     * id
     */
    int id;

    /**
     * 会员编号
     */
    String VIPNumber;

    /**
     * 会员姓名
     */
    String name;
    /**
     * 电话
     */
    String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVIPNumber() {
        return VIPNumber;
    }

    public void setVIPNumber(String VIPNumber) {
        this.VIPNumber = VIPNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer() {
    }

    public Customer(int id, String VIPNumber, String name, String phone) {
        this.id = id;
        this.VIPNumber = VIPNumber;
        this.name = name;
        this.phone = phone;
    }
}
