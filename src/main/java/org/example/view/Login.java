package org.example.view;

import org.example.Main;
import org.example.dao.SalespersonDao;
import org.example.entity.Salesperson;
import org.example.utils.DBUtil;
import org.example.utils.StringUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Objects;

/**
 * StoreManagementSystem
 * 登录
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 22:32
 * @since JDK17
 */

public class Login extends JFrame {

    private JPanel contentPane;
    private final JTextField userNameText;
    private final JPasswordField passwordText;

    private final DBUtil dbUtil = new DBUtil();
    private final SalespersonDao salespersonDao = new SalespersonDao();


    /**
     * Create the frame
     */
    public Login(){
        //改变系统默认字体
        Font font = new Font("Dialog", Font.PLAIN, 12);
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()){
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource){
                UIManager.put(key, font);
            }
        }
        setResizable(false);
        // 管理员登录
        setTitle("管理员登录");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500,250,450,343);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("商店管理系统");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 23));
        lblNewLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png"))));

        JLabel lblNewLabel1 = new JLabel("用户名：");
        lblNewLabel1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/userName.png"))));


        JLabel lblNewLabel2 = new JLabel("密  码：");
        lblNewLabel2.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/password.png"))));

        userNameText = new JTextField();
        userNameText.setColumns(10);

        passwordText = new JPasswordField();

        JButton btnNewButton1 = new JButton("登录");
        btnNewButton1.addActionListener(this::loginActionPerformed);
        btnNewButton1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/login.png"))));

        JButton btnNewButton2 = new JButton("重置");
        btnNewButton2.addActionListener(this::resetValueActionPerformed);
        btnNewButton2.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/reset.png"))));


        GroupLayout groupLayoutContentPane = new GroupLayout(contentPane);
        groupLayoutContentPane.setHorizontalGroup(
                groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayoutContentPane.createSequentialGroup()
                                .addGroup(groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(groupLayoutContentPane.createSequentialGroup()
                                                .addGap(111)
                                                .addComponent(lblNewLabel))
                                        .addGroup(groupLayoutContentPane.createSequentialGroup()
                                                .addGap(101)
                                                .addGroup(groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblNewLabel1)
                                                        .addComponent(lblNewLabel2)
                                                        .addComponent(btnNewButton1))
                                                .addGap(32)
                                                .addGroup(groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnNewButton2)
                                                        .addGroup(groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(passwordText)
                                                                .addComponent(userNameText, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        groupLayoutContentPane.setVerticalGroup(
                groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayoutContentPane.createSequentialGroup()
                                .addGap(30)
                                .addComponent(lblNewLabel)
                                .addGap(26)
                                .addGroup(groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(groupLayoutContentPane.createSequentialGroup()
                                                .addComponent(lblNewLabel1)
                                                .addGap(29)
                                                .addGroup(groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblNewLabel2)
                                                        .addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(userNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(36)
                                .addGroup(groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNewButton1)
                                        .addComponent(btnNewButton2))
                                .addContainerGap(60, Short.MAX_VALUE))
        );
        contentPane.setLayout(groupLayoutContentPane);
        // 居中
        this.setLocationRelativeTo(null);
    }

    /**
     * 登录事件处理
     * @param evt action
     */
    private void loginActionPerformed(ActionEvent evt) {
        String userName = this.userNameText.getText();
        String password = new String(this.passwordText.getPassword());
        if (StringUtil.isEmpty(userName)){
            JOptionPane.showMessageDialog(null, "用户名不能为空");
            return;
        }
        if (StringUtil.isEmpty(password)){
            JOptionPane.showMessageDialog(null, "密码不能为空");
            return;
        }
        Salesperson user = new Salesperson(userName, password);
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            Salesperson currentUser = salespersonDao.login(con, user);
            if (currentUser != null){
                dispose();
                new MainFrame().setVisible(true);
                JOptionPane.showMessageDialog(null, "登录成功");
            }else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeConnection(con);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 重置事件处理
     * @param evt action
     */
    private void resetValueActionPerformed(ActionEvent evt){
        this.userNameText.setText("");
        this.passwordText.setText("");
    }

}
