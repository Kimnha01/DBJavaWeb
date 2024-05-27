/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtil;

/**
 *
 * @author Kim Nha
 */
public class AccountDAO {
    //DAO: data object access
    //lấy tất cả các dòng trong bảng Account
    //trả về arraylist các object Account
    
    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            //1- kết nối app với sql server
            if (cn != null) {
                //2- viet lenh sql
                String sql = "select [AccId], [FullName], [Email], [Password]\n" +
                            "from dbo.Accounts";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    //3- doc tung dong trong rs va convert thanh object account
                    while (rs.next()) {
                        int id = rs.getInt("AccId");
                        String fullname = rs.getString("FullName");
                        String email = rs.getString("Email");
                        String pw = rs.getString("Password");
                        Account a = new Account(id, fullname, email, pw);
                        list.add(a);
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
    
    public int removeAccount(int accId) {
        Connection cn = null;
        int affected = 0;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "delete from Accounts where AccId = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, accId);
                affected = pst.executeUpdate();
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
        return affected;
    }
    
    public ArrayList<Account> searchAccount(String accEmail) {
        ArrayList<Account> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "select [AccId], [FullName], [Email], [Password]\n" +
                            "from [dbo].[Accounts] where [Email] like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + accEmail + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    //3- doc tung dong trong rs va convert thanh object account
                    while (rs.next()) {
                        int id = rs.getInt("AccId");
                        String fullname = rs.getString("FullName");
                        String email = rs.getString("Email");
                        String pw = rs.getString("Password");
                        Account a = new Account(id, fullname, email, pw);
                        list.add(a);
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
    
    public int insertAccount(String fullname, String email, String password) {
        Connection cn = null;
        int affected = 0;
        try {
            cn = DBUtil.makeConnection();
            if (cn != null) {
                String sql = "insert into  [dbo].[Accounts]([FullName], [Email], [Password])\n" +
                            "values(?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, fullname);
                pst.setString(2, email);
                pst.setString(3, password);
                affected = pst.executeUpdate();
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
        return affected;
    }
}
