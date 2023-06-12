package org.example.view;

import org.example.dao.CustomerDao;
import org.example.entity.Customer;
import org.example.utils.DBUtil;
import org.example.utils.StringUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.Objects;

/**
 * StoreManagementSystem
 * 顾客添加
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 11:20
 * @since JDK17
 */

public class CustomerAddInternalFrame extends JInternalFrame {

    private final JTextField VIPNumberTxt;
    private final JTextField customerNameTxt;
    private final JTextArea customerDescTxt;


    private final DBUtil dbUtil = new DBUtil();
    private final CustomerDao customerDao = new CustomerDao();


    /**
     * Create the frame.
     */
    public CustomerAddInternalFrame() {
        setClosable(true);
        setIconifiable(true);
        setTitle("顾客添加");
        setBounds(100, 100, 450, 300);

        JLabel lblVIPNumber = new JLabel("会员编号：");
        JLabel lblCustomName = new JLabel("顾客名称：");

        JLabel lblContact = new JLabel("联系方式：");

        VIPNumberTxt = new JTextField();
        VIPNumberTxt.setColumns(10);

        customerNameTxt = new JTextField();
        customerNameTxt.setColumns(10);

        customerDescTxt = new JTextArea();

        JButton btnNewButton = new JButton("添加");
        btnNewButton.addActionListener(this::customerAddActionPerformed);
        btnNewButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/add.png"))));


        JButton btnNewButton1 = new JButton("重置");
        btnNewButton1.addActionListener(this::resetValueActionPerformed);
        btnNewButton1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/reset.png"))));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(86)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblVIPNumber)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(VIPNumberTxt, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblCustomName)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(customerNameTxt, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblContact)
                                                        .addComponent(btnNewButton))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnNewButton1)
                                                        .addComponent(customerDescTxt))))
                                .addContainerGap(69, GroupLayout.PREFERRED_SIZE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(10)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblVIPNumber)
                                        .addComponent(VIPNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(28)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCustomName)
                                        .addComponent(customerNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(29)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblContact)
                                        .addComponent(customerDescTxt, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNewButton)
                                        .addComponent(btnNewButton1))
                                .addGap(28))
        );
        getContentPane().setLayout(groupLayout);

        // 设置文本域边框
        customerDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));

    }

    /**
     * 顾客添加事件处理
     * @param evt 事件
     */
    private void customerAddActionPerformed(ActionEvent evt) {
        String VIPNumber = this.VIPNumberTxt.getText();
        String customerName = this.customerNameTxt.getText();
        String customerDesc = this.customerDescTxt.getText();

        if(StringUtil.isEmpty(customerName)){
            JOptionPane.showMessageDialog(null, "顾客名称不能为空！");
            return;
        }
        Customer customer = new Customer(VIPNumber, customerName, customerDesc);
        Connection con = null;
        try{
            con = dbUtil.getConnection();
            int n = customerDao.add(con, customer);
            if(n == 1){
                JOptionPane.showMessageDialog(null, "顾客添加成功！");
                resetValue();
            }else{
                JOptionPane.showMessageDialog(null, "顾客添加失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "顾客添加失败！");
        }finally{
            try {
                dbUtil.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 重置事件处理
     * @param evt   event
     */
    private void resetValueActionPerformed(ActionEvent evt) {
        this.resetValue();
    }

    /**
     * 重置表单
     */
    private void resetValue(){
        this.VIPNumberTxt.setText("");
        this.customerNameTxt.setText("");
        this.customerDescTxt.setText("");
    }
}
