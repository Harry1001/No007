package presentation.contentpanel;

import constent.Constent;
import presentation.commoncontainer.MyDefaultTableModel;
import presentation.commoncontainer.MyTable;
import typeDefinition.ReceiptType;
import vo.receiptvo.ChargeReceiptVO;
import vo.receiptvo.PayReceiptVO;
import vo.receiptvo.ReceiptVO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Harry on 2015/12/5.
 */
public class JingYingPanel extends JPanel{

    private MyTable chargeTable;
    private MyDefaultTableModel chargeModel;
    private MyTable payTable;
    private MyDefaultTableModel payModel;

    public JingYingPanel(ArrayList<ReceiptVO> list){

        initUI();

        initData(list);
    }

    private void initData(ArrayList<ReceiptVO> list){
        for (ReceiptVO vo : list) {
            if (vo.getType()== ReceiptType.CHARGE){ //收款单
                addChargeReceipt(vo);
            }
            else {  //付款单
                addPayReceipt(vo);
            }
        }
        chargeTable.revalidate();
        chargeTable.updateUI();
        payTable.revalidate();
        payTable.updateUI();
    }

    private void addChargeReceipt(ReceiptVO vo){
        ChargeReceiptVO chargeReceiptVO=(ChargeReceiptVO)vo;
        Object[] data=new Object[3];
        data[0]=chargeReceiptVO.getCourier();
        data[1]= Constent.DATE_FORMAT.format(chargeReceiptVO.getChargeTime());
        data[2]= chargeReceiptVO.getFee();
        chargeModel.addRow(data);
    }

    private void addPayReceipt(ReceiptVO vo){
        PayReceiptVO pvo=(PayReceiptVO)vo;
        Object[] data=new Object[5];
        data[0]=pvo.getPayMan();
        data[1]=Constent.DATE_FORMAT.format(pvo.getPayTime());
        data[2]=pvo.getFee();
        data[3]=pvo.getPayAccount();
        data[4]=Constent.FEE_TYPE_STR[pvo.getPayType().ordinal()];
        payModel.addRow(data);
    }

    private void initUI(){
        String[] chargeNames={"收款快递员","收款日期","收款金额"};
        String[] payNames={"付款人","付款日期","付款金额","付款帐号","付款条目"};
        chargeModel=new MyDefaultTableModel(chargeNames,0);
        payModel=new MyDefaultTableModel(payNames,0);
        chargeTable=new MyTable(chargeModel);
        payTable=new MyTable(payModel);

        chargeTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        payTable.setPreferredScrollableViewportSize(new Dimension(500,200));

        /*
        chargeTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"收款单",
               TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 20)));
        payTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"付款单",
                TitledBorder.LEFT,TitledBorder.TOP,new Font("",Font.BOLD, 20)));
        */


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(10,10,10,10);
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;

        JScrollPane scrollPane1=new JScrollPane(chargeTable);
        JScrollPane scrollPane2=new JScrollPane(payTable);
        scrollPane1.setBorder(BorderFactory.createTitledBorder("收款单"));
        scrollPane2.setBorder(BorderFactory.createTitledBorder("付款单"));

        this.add(scrollPane1,gbc);
        gbc.gridy++;
        this.add(new JSeparator(),gbc);
        gbc.gridy++;
        this.add(scrollPane2,gbc);
    }

}
