/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Kim Nha
 */
public class Order {
    private int id;
    private Date orderDate;
    private int status;
    private double total;
    private int accId;
    private ArrayList<OrderDetail> details;
    
    public Order() {
        orderDate = new Date(System.currentTimeMillis());
        details = new ArrayList<>();
    }

    public Order(int id, Date orderDate, int status, double total, int accId, ArrayList<OrderDetail> details) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.total = total;
        this.accId = accId;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public ArrayList<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<OrderDetail> details) {
        this.details = details;
    }
    
    public boolean addOrderDetail(OrderDetail orderDetail) {
        return details.add(orderDetail);
    }
}
