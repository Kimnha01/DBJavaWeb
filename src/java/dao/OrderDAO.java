/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Order;
import dto.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtil;

/**
 *
 * @author Kim Nha
 */
public class OrderDAO {
    //ham nay lay order va ordertdetail theo status
    public ArrayList<Order> getAllOrders(int status) {
        ArrayList<Order> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            //1- kết nối app với sql server
            if (cn != null) {
                //2- viet lenh sql
                String sql = "select [OrderID], [OrderDate], [Status], [Total], [AccID]\n" +
                            "from [dbo].[Orders] where [Status] = ?\n" +
                            "order by OrderDate desc";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    //3- doc tung dong trong rs va convert thanh object account
                    while (rs.next()) {
                        Order ord = new Order();
                        int orderId = rs.getInt("OrderID");
                        Date date = rs.getDate("OrderDate");
                        int total = rs.getInt("Total");
                        int accId = rs.getInt("AccID");
                        ord.setId(orderId);
                        ord.setOrderDate(date);
                        ord.setStatus(status);
                        ord.setTotal(total);
                        ord.setAccId(accId);
                        //lay order detail trong DB
                        String sql2 = "select [DetailID], [ItemID], [OrderID], [Quantity]\n" +
                                    "from [dbo].[OrderDetails] \n" +
                                    "where [OrderID] = ?";
                        PreparedStatement pst2 = cn.prepareStatement(sql2);
                        pst2.setInt(1, orderId);
                        ResultSet rs2 = pst2.executeQuery();
                        if (rs2 != null) {
                            while (rs2.next()) {
                                int detailId = rs2.getInt("DetailID");
                                int itemId = rs2.getInt("ItemID");
                                int quantity = rs2.getInt("Quantity");
                                OrderDetail detail = new OrderDetail(detailId, orderId, itemId, quantity);
                                ord.addOrderDetail(detail);
                            }
                        }
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null)
                    cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public int changeOrderStatus(int orderId, int newStatus) {
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            //1- kết nối app với sql server
            if (cn != null) {
                //2- viet lenh sql
                String sql = "update Orders set Status = ? where OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, newStatus);
                pst.setInt(2, orderId);
                result = pst.executeUpdate();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null)
                    cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
