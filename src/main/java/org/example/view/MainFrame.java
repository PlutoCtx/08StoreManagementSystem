package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

/**
 * StoreManagementSystem
 * 主界面
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 10:28
 * @since JDK17
 */

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JDesktopPane table =null;


    /**
     * Create the frame.
     */
    public MainFrame() {
        // 商品管理系统主界面
        setTitle("商店管理系统主界面");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        // 老师要求加的01
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        init();

        // 设置JFrame最大化
        this.setExtendedState(Frame.MAXIMIZED_BOTH);

    }

    public void init(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // 1.基本数据维护
        JMenu mnNewMenu = new JMenu("基本数据维护");
//        mnNewMenu.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/base.png"))));
        menuBar.add(mnNewMenu);

        // 1.1.顾客管理
        JMenu mnNewMenu1 = new JMenu("顾客管理");
//        mnNewMenu1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/bookTypeManager.png"))));
        mnNewMenu.add(mnNewMenu1);

        // 1.1.1.顾客添加
        JMenuItem menuItem = new JMenuItem("顾客添加");
        menuItem.addActionListener(e -> {
            AboutInternalFrame bookTypeAddInterFrm = new AboutInternalFrame();
            bookTypeAddInterFrm.setVisible(true);
            table.add(bookTypeAddInterFrm);
        });
//        menuItem.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/add.png"))));
        mnNewMenu1.add(menuItem);

        // 1.1.2.顾客维护
        JMenuItem menuItem1 = new JMenuItem("顾客维护");
        menuItem1.addActionListener(e -> {
            AboutInternalFrame bookTypeManageInterFrm=new AboutInternalFrame();
            bookTypeManageInterFrm.setVisible(true);
            table.add(bookTypeManageInterFrm);
        });
//        menuItem1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/edit.png"))));
        mnNewMenu1.add(menuItem1);

        // 1.2.商品管理
        JMenu mnNewMenu2 = new JMenu("商品管理");
//        mnNewMenu2.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/bookManager.png"))));
        mnNewMenu.add(mnNewMenu2);

        // 1.2.1.商品添加
        JMenuItem menuItem2 = new JMenuItem("商品添加");
        menuItem2.addActionListener(arg0 -> {
            AboutInternalFrame bookAddInterFrm=new AboutInternalFrame();
            bookAddInterFrm.setVisible(true);
            table.add(bookAddInterFrm);
        });
//        menuItem2.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/add.png"))));
        mnNewMenu2.add(menuItem2);

        // 1.2.2.商品维护
        JMenuItem menuItem3 = new JMenuItem("商品维护");
        menuItem3.addActionListener(arg0 -> {
            AboutInternalFrame bookManageInterFrm=new AboutInternalFrame();
            bookManageInterFrm.setVisible(true);
            table.add(bookManageInterFrm);
        });
//        menuItem3.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/edit.png"))));
        mnNewMenu2.add(menuItem3);

        // 1.3.安全退出
        JMenuItem menuItemExit = new JMenuItem("安全退出");
        menuItemExit.addActionListener(e -> {
            int result=JOptionPane.showConfirmDialog(null, "是否退出系统");
            if(result==0){
                dispose();
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
//        menuItemExit.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/exit.png"))));
        mnNewMenu.add(menuItemExit);

        // 2.关于我们
        JMenu menu = new JMenu("关于我们");
//        menu.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/about.png"))));
        menuBar.add(menu);

        // 2.1关于Java
        JMenuItem jMenuItem = new JMenuItem("关于Java");
        jMenuItem.addActionListener(arg0 -> {
            AboutInternalFrame java1234InterFrm=new AboutInternalFrame();
            java1234InterFrm.setVisible(true);
            table.add(java1234InterFrm);
        });
//        jMenuItem.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/about.png"))));
        menu.add(jMenuItem);


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        table = new JDesktopPane();
        contentPane.add(table, BorderLayout.CENTER);

    }
}
