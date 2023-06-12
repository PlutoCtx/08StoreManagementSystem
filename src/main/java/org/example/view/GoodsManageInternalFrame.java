package org.example.view;

import org.example.dao.GoodsDao;
import org.example.entity.Goods;
import org.example.utils.DBUtil;
import org.example.utils.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

/**
 * StoreManagementSystem
 * 商品管理
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 11:22
 * @since JDK17
 */

public class GoodsManageInternalFrame extends JInternalFrame {

    // 定义可拆分窗格容器组件
    JSplitPane jSplitPane;

    JTable table;

    DBUtil findRecord = new DBUtil();

    JPanel leftPanel = new JPanel(new BorderLayout());

    /**
     * 返回全部字段名称
     */
    String[] tableHead;
    /**
     * 返回二维数组，即查询的全部记录
     */
    String[][] content;

    JTextField idTxt = new JTextField();
    JTextField storeNumberTxt = new JTextField();
    JTextField storeNameTxt = new JTextField();
    JTextField modelTxt = new JTextField();
    JTextField priceTxt = new JTextField();

    JButton buttonOk = new JButton("OK");
    JButton buttonCancel = new JButton("CANCEL");

    DBUtil db = new DBUtil();
    /**
     * 内部窗体
     */
    public GoodsManageInternalFrame() {

        initTable();

        // 创建TabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("JTable Tabbed Pane", new JScrollPane(table));

        // 创建左右两个面板，放置TabbedPane和其他组件
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(tabbedPane, BorderLayout.CENTER);

        leftPanel = new JPanel(new BorderLayout());
        initLeftPanel(leftPanel);

        // 创建分割面板，左边为TabbedPane，右边为其他组件
        jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);

        JSplitPane jSplitPaneBottom = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        jSplitPaneBottom.setTopComponent(jSplitPane);
        initBottomPanel(jSplitPaneBottom);

        this.add(jSplitPaneBottom);

        // 设置拆分窗格是否可以展开或收起
        jSplitPane.setOneTouchExpandable(true);
        // 设置分频器（分割线）的初始位置
        jSplitPane.setDividerLocation(300);
        jSplitPane.setDividerSize(10);
        // 设置窗体实行
        this.setSize(1530, 750);                // 设置界面像素
        this.setLocation(0, 0);            // 设置界面初始位置
        this.setVisible(true);                // 设置界面可视化
        this.setIconifiable(true);
        this.setClosable(true);
        this.setTitle("商品管理界面");
    }

    /**
     * 初始化底下一栏的按钮
     *
     * @param jSplitPaneBottom bottom
     */
    private void initBottomPanel(JSplitPane jSplitPaneBottom) {

        // 编辑按钮位置
        JPanel panel = new JPanel();
        // 水平布局
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(Box.createGlue());
        panel.add(buttonOk);
        panel.add(Box.createGlue());
        panel.add(buttonCancel);
        panel.add(Box.createGlue());

        // 设置按钮大小
        Dimension buttonSize = new Dimension(90, 10);
        buttonOk.setPreferredSize(buttonSize);
        buttonCancel.setPreferredSize(buttonSize);

        // 盘点功能，确定
        buttonOk.addActionListener(this::inboundAction);

        // 取消功能
        buttonCancel.addActionListener(this::resetValue);

        jSplitPaneBottom.setBottomComponent(panel);
    }


    /**
     * 货物盘点确定
     * @param event event
     */
    private void inboundAction(ActionEvent event) {
        if (StringUtil.isEmpty(idTxt.getText())){
            JOptionPane.showMessageDialog(null, "ID不能为空");
            return;
        }
        if (StringUtil.isEmpty(storeNumberTxt.getText())){
            JOptionPane.showMessageDialog(null, "商店编号不能为空");
            return;
        }
        if (StringUtil.isEmpty(storeNameTxt.getText())){
            JOptionPane.showMessageDialog(null, "商品名称不能为空");
            return;
        }
        if (StringUtil.isEmpty(modelTxt.getText())){
            JOptionPane.showMessageDialog(null, "商品规格不能为空");
            return;
        }
        if (StringUtil.isEmpty(priceTxt.getText())){
            JOptionPane.showMessageDialog(null, "单价不能为空");
            return;
        }


        String ID = idTxt.getText();
        String storeNumber = storeNumberTxt.getText();
        String storeName = storeNameTxt.getText();
        String model = modelTxt.getText();
        String price = priceTxt.getText();

        Goods goods = new Goods(Integer.parseInt(ID), storeNumber, storeName, model, Double.parseDouble(price));


        Connection conn = null;

        try {
            conn = db.getConnection();
            int n = GoodsDao.update(conn, goods);
            if(n == 1){
                JOptionPane.showMessageDialog(null, "修改成功！");
                reset();
            }else{
                JOptionPane.showMessageDialog(null, "修改失败！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "修改失败！");
        }finally{
            try {
                db.closeConnection(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 重置，清空
     *
     * @param event how do I know
     */
    private void resetValue(ActionEvent event) {
        reset();
    }

    /**
     * 重置，清空
     */
    private void reset() {
        idTxt.setText("");
        storeNumberTxt.setText("");
        storeNameTxt.setText("");
        modelTxt.setText("");
        priceTxt.setText("");
    }

    /**
     * 初始化左边栏
     *
     * @param panel 左边一栏
     */
    public void initLeftPanel(JPanel panel) {
        panel.setFont(new Font("楷体", Font.PLAIN, 20));
        panel.setLayout(new GridLayout(5, 2, 5, 4));

        JLabel idLabel = new JLabel("ID");
        JLabel storeNumberLabel = new JLabel("商店编号");
        JLabel storeNameLabel = new JLabel("商品名称");
        JLabel modelLabel = new JLabel("商品规格");
        JLabel priceLabel = new JLabel("单价");

        idTxt = new JTextField("ID");
        storeNumberTxt = new JTextField("商店编号");
        storeNameTxt = new JTextField("商品名称");
        modelTxt = new JTextField("商品规格");
        priceTxt = new JTextField("单价");

        // 票据单号
        panel.add(idLabel);
        panel.add(idTxt);
        // 票据单号
        panel.add(storeNumberLabel);
        panel.add(storeNumberTxt);
        // 仓库名
        panel.add(storeNameLabel);
        panel.add(storeNameTxt);
        // 货号
        panel.add(modelLabel);
        panel.add(modelTxt);
        // 交易数量
        panel.add(priceLabel);
        panel.add(priceTxt);

    }

    /**
     * 初始化表格
     */
    public void initTable() {
        findRecord.setSQL("SELECT * FROM goods");
        content = findRecord.getRecord();
        tableHead = findRecord.getColumnName();

        table = new JTable(content, tableHead);
        // 设置行宽
        table.setRowHeight(20);
        // 设置字体
        table.setFont(new Font("楷体", Font.PLAIN, 15));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tableMousePressed(e);
            }
        });
    }

    /**
     * 表格点击事件处理
     *
     * @param e how do I know
     */
    private void tableMousePressed(MouseEvent e) {
        reset();
        int row = table.getSelectedRow();
        idTxt.setText((String) table.getValueAt(row, 0));
        storeNumberTxt.setText((String) table.getValueAt(row, 1));
        storeNameTxt.setText((String) table.getValueAt(row, 2));
        modelTxt.setText((String) table.getValueAt(row, 3));
        priceTxt.setText((String) table.getValueAt(row, 4));
    }

}