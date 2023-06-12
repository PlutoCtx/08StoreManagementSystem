package org.example.view;

import org.example.dao.CustomerDao;
import org.example.entity.Customer;
import org.example.utils.DBUtil;
import org.example.utils.StringUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * StoreManagementSystem
 * 顾客管理
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 11:20
 * @since JDK17
 */

public class CustomerManageInternalFrame extends JInternalFrame {

    private final JTable customerTable;
    private final JTextArea customerDescTxt ;
    private final DBUtil dbUtil = new DBUtil();
    private final CustomerDao customerDao = new CustomerDao();
//    private final BookDao bookDao = new BookDao();
    private final JTextField s_customerNameTxt;
    private final JTextField idTxt;
    private final JTextField customerNameTxt;


    /**
     * Create the frame.
     */
    public CustomerManageInternalFrame() {
        setClosable(true);
        setIconifiable(true);
        setTitle("顾客管理");
        setBounds(100, 100, 507, 481);

        JScrollPane scrollPane = new JScrollPane();

        JLabel label = new JLabel("顾客姓名：");

        s_customerNameTxt = new JTextField();
        s_customerNameTxt.setColumns(10);

        JButton button = new JButton("查询");
        button.addActionListener(this::customerSearchActionPerformed);
        button.setIcon(new ImageIcon(Objects.requireNonNull(CustomerManageInternalFrame.class.getResource("/search.png"))));

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(42)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(panel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup()
                                                .addComponent(label)
                                                .addGap(18)
                                                .addComponent(s_customerNameTxt, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(button))
                                        .addComponent(scrollPane, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
                                .addGap(48))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(33)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(s_customerNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button))
                                .addGap(39)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        JLabel lblNewLabel = new JLabel("编号：");

        idTxt = new JTextField();
        idTxt.setEditable(false);
        idTxt.setColumns(10);

        JLabel label01 = new JLabel("顾客姓名：");

        customerNameTxt = new JTextField();
        customerNameTxt.setColumns(10);

        JLabel labelDesc = new JLabel("电话：");

        customerDescTxt = new JTextArea();

        JButton btnNewButton = new JButton("修改");
        btnNewButton.addActionListener(this::customerUpdateActionEvent);
        btnNewButton.setIcon(new ImageIcon(Objects.requireNonNull(CustomerManageInternalFrame.class.getResource("/modify.png"))));

        JButton buttonDelete = new JButton("删除");
        buttonDelete.addActionListener(this::customerDeleteActionEvent);
        buttonDelete.setIcon(new ImageIcon(Objects.requireNonNull(CustomerManageInternalFrame.class.getResource("/delete.png"))));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(lblNewLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(31)
                                                .addComponent(label01)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(customerNameTxt, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(labelDesc)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(customerDescTxt))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(btnNewButton)
                                                .addGap(26)
                                                .addComponent(buttonDelete)))
                                .addContainerGap(37, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label01)
                                        .addComponent(customerNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(labelDesc)
                                        .addComponent(customerDescTxt, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNewButton)
                                        .addComponent(buttonDelete))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        customerTable = new JTable();
        customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                customerTableMousePressed(e);
            }
        });
        customerTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "编号", "会员编号", "顾客姓名", "电话"
                }
        ) {
            final boolean[] columnEditable = new boolean[] {
                    false, false, false, false
            };
            @Override
            public boolean isCellEditable(int row, int column) {
                return columnEditable[column];
            }
        });
        customerTable.getColumnModel().getColumn(1).setPreferredWidth(110);
        customerTable.getColumnModel().getColumn(2).setPreferredWidth(123);
        scrollPane.setViewportView(customerTable);
        getContentPane().setLayout(groupLayout);

        this.fillTable(new Customer());

        customerDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
    }


    /**
     * 顾客删除事件处理
     * @param event event
     */
    private void customerDeleteActionEvent(ActionEvent event) {
        String id = idTxt.getText();
        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null, "请选择要删除的记录");
            return;
        }
        int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
        if(n == 0){
            Connection con = null;
            try{
                con = dbUtil.getConnection();
                int deleteNum = customerDao.delete(con, id);
                if(deleteNum == 1){
                    JOptionPane.showMessageDialog(null, "删除成功");
                    this.resetValue();
                    this.fillTable(new Customer());
                }else{
                    JOptionPane.showMessageDialog(null, "删除失败");
                }
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "删除失败");
            }finally{
                try {
                    dbUtil.closeConnection(con);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 顾客修改事件处理
     * @param event event
     */
    private void customerUpdateActionEvent(ActionEvent event) {
        String id = idTxt.getText();
        Logger.getGlobal().info("idddd:" + id);
        String customerName = customerNameTxt.getText();
        String customerDesc = customerDescTxt.getText();
        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null, "请选择要修改的记录");
            return;
        }
        if(StringUtil.isEmpty(customerName)){
            JOptionPane.showMessageDialog(null, "顾客姓名不能为空");
            return;
        }
        Customer customer = new Customer(Integer.parseInt(id), customerName, customerDesc);
        Connection con = null;
        try{
            con = dbUtil.getConnection();
            int modifyNum = customerDao.update(con, customer);
            if(modifyNum == 1){
                JOptionPane.showMessageDialog(null, "修改成功");
                this.resetValue();
                this.fillTable(new Customer());
            }else{
                JOptionPane.showMessageDialog(null, "修改失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "修改失败");
        }finally{
            try {
                dbUtil.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 表格行点击事件处理
     * @param event event
     */
    private void customerTableMousePressed(MouseEvent event) {
        int row = customerTable.getSelectedRow();
        idTxt.setText((String)customerTable.getValueAt(row, 0));
        customerNameTxt.setText((String)customerTable.getValueAt(row, 2));
        customerDescTxt.setText((String)customerTable.getValueAt(row, 3));
    }


    /**
     * 顾客搜索事件处理
     * @param event event
     */
    private void customerSearchActionPerformed(ActionEvent event) {
        String customerName = this.s_customerNameTxt.getText();
        Customer customer = new Customer();
        customer.setName(customerName);
        this.fillTable(customer);
    }


    /**
     * 初始化表格
     * @param customer
     */
    private void fillTable(Customer customer){
        DefaultTableModel dtm = (DefaultTableModel) customerTable.getModel();
        dtm.setRowCount(0); // 设置成0行
        Connection con = null;
        try{
            con = dbUtil.getConnection();
            ResultSet rs = customerDao.list(con, customer);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("VIPNumber"));
                v.add(rs.getString("name"));
                v.add(rs.getString("phone"));
                dtm.addRow(v);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeConnection(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 重置表单
     */
    private void resetValue(){
        this.idTxt.setText("");
        this.customerNameTxt.setText("");
        this.customerDescTxt.setText("");
    }
}
