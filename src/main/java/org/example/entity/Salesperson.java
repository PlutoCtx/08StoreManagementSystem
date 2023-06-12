package org.example.entity;

/**
 * StoreManagementSystem
 * 售货员
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 10:03
 * @since JDK17
 */

public class Salesperson {
    /**
     * 标识
     */
    int id;
    /**
     * 员工编号
     */
    String workNumber;
    /**
     * 员工姓名
     */
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Salesperson() {
    }

    public Salesperson(int id, String workNumber, String name) {
        this.id = id;
        this.workNumber = workNumber;
        this.name = name;
    }
}
