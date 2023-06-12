package org.example.view;

import org.example.dao.GoodsDao;
import org.example.entity.Goods;
import org.example.utils.DBUtil;
import org.example.utils.StringUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.Objects;

/**
 * StoreManagementSystem
 * 商品添加
 *
 * @author MaxBrooks 15905898514@163.com
 * @version 2023/6/12 11:21
 * @since JDK17
 */

public class GoodsAddInternalFrame extends JInternalFrame {
    private final DBUtil dbUtil = new DBUtil();
    private final GoodsDao bookDao = new GoodsDao();


    private final JLabel lblStoreNumber;
    private final JLabel lblStoreName;
    private final JLabel lblModel;
    private final JLabel lblPrice;

    private final JTextField storeNumberTxt;
    private final JTextField storeNameTxt;
    private final JTextField modelTxt;
    private final JTextField priceTxt;


    /**
     * Create the frame.
     */
    public GoodsAddInternalFrame() {
        setClosable(true);
        setIconifiable(true);
        setTitle("商品添加");
        setBounds(100, 100, 300, 467);

        lblStoreNumber = new JLabel("商店编号：");
        storeNumberTxt = new JTextField();
        storeNumberTxt.setColumns(10);

        lblStoreName = new JLabel("商品名称：");
        storeNameTxt = new JTextField();
        storeNameTxt.setColumns(10);

        lblModel = new JLabel("商品规格：");
        modelTxt = new JTextField();
        modelTxt.setColumns(10);

        lblPrice = new JLabel("商品单价：");
        priceTxt = new JTextField();
        priceTxt.setColumns(10);


        JButton buttonAdd = new JButton("添加");
        buttonAdd.addActionListener(this::bookAddActionPerformed);
        buttonAdd.setIcon(new ImageIcon(Objects.requireNonNull(GoodsAddInternalFrame.class.getResource("/add.png"))));

        JButton buttonReset = new JButton("重置");
        buttonReset.addActionListener(this::resetValueActionPerformed);
        buttonReset.setIcon(new ImageIcon(Objects.requireNonNull(GoodsAddInternalFrame.class.getResource("/reset.png"))));
        GroupLayout groupLayout = new GroupLayout(getContentPane());


        // 设定水平布局
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap(20, 20)
                        .addComponent(lblStoreNumber)
                        .addGap(10)
                        .addComponent(storeNumberTxt))
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap(20, 20)
                        .addComponent(lblStoreName)
                        .addGap(10)
                        .addComponent(storeNameTxt))
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap(20, 20)
                        .addComponent(lblModel)
                        .addGap(10)
                        .addComponent(modelTxt))
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap(20, 20)
                        .addComponent(lblPrice)
                        .addGap(10)
                        .addComponent(priceTxt))
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap(20, 20)
                        .addComponent(buttonAdd)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonReset))
        );

        // 设定垂直布局
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                        .addGap(25)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStoreNumber)
                        .addComponent(storeNumberTxt))
                .addGap(20)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblStoreName)
                        .addComponent(storeNameTxt))
                .addGap(20)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblModel)
                        .addComponent(modelTxt))
                .addGap(20)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPrice)
                        .addComponent(priceTxt))
                .addGap(20)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAdd)
                        .addComponent(buttonReset))
        );

        getContentPane().setLayout(groupLayout);
    }





    /**
     * 重置事件处理
     * @param e event
     */
    private void resetValueActionPerformed(ActionEvent e) {
        this.resetValue();
    }


    /**
     * 商品添加事件处理
     * @param event event
     */
    private void bookAddActionPerformed(ActionEvent event) {
        String storeNumber = storeNumberTxt.getText();
        String storeName = storeNameTxt.getText();
        String model = modelTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());

        if(StringUtil.isEmpty(storeNumber)){
            JOptionPane.showMessageDialog(null, "商店号不能为空");
            return;
        }
        if(StringUtil.isEmpty(storeName)){
            JOptionPane.showMessageDialog(null, "商品名不能为空");
            return;
        }

        Goods goods = new Goods(storeNumber, storeName, model, price);
        Connection con = null;
        try{
            con = dbUtil.getConnection();
            int addNum = bookDao.add(con, goods);
            if(addNum == 1){
                JOptionPane.showMessageDialog(null, "商品添加成功");
                resetValue();
            }else{
                JOptionPane.showMessageDialog(null, "商品添加失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "商品添加失败");
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

        this.storeNumberTxt.setText("");
        this.storeNameTxt.setText("");
        this.modelTxt.setText("");
        this.priceTxt.setText("");
    }

}
