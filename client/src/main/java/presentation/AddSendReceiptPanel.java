package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businessLogicService.TransportBLService;
import typeDefinition.myTime;
import vo.SendReceiptVO;

public class AddSendReceiptPanel extends JPanel{
	
	private static final int TEXTLENGTH=15;
	
	private TransportBLService transportBL;
	
	private SendReceiptVO sendReceipt;
	
	private JPanel senderPanel=new JPanel();
	private JPanel receiverPanel=new JPanel();
	private JPanel goodsInfoPanel=new JPanel();
	private JPanel orderPanel=new JPanel();
	private JButton confirmButton;
	private JButton cancelButton;
	private JButton calPriceButton;
	
	private JTextField senderNameText;
	private JTextField senderAddressText;
	private JTextField senderCompanyText;
	private JTextField senderPhoneText;
	
	private JTextField receiverNameText;
	private JTextField receiverAddressText;
	private JTextField receiverCompanyText;
	private JTextField receiverPhoneText;
	
	private JTextField goodsNumText;
	private JTextField weightText;
	private JTextField volumnText;
	private JTextField goodsNameText;
	
	private JTextField orderTypeText;
	private JTextField wrapTypeText;
	private JTextField orderNumText;
	private JTextField feeText;
	
	public AddSendReceiptPanel(TransportBLService trans){
		this.transportBL=trans;
		initSenderPanel();
		initReceiverPanel();
		initGoodsInfoPanel();
		initOrderPanel();
		
		
		initCancelButton();
		initConfirmButton();
		initcalPriceBton();
	}
	
	
	
	private void initcalPriceBton() {
		calPriceButton=new JButton("计算价格");
		calPriceButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				sendReceipt=creatSendreceipt();
				transportBL.cal(sendReceipt);
				
				feeText.setText(sendReceipt.getMoney()+"");
				//double price=transportBL.cal
				
			}
		});
		this.add(calPriceButton,FlowLayout.TRAILING);
	}
	
	private void initConfirmButton(){
		confirmButton=new JButton(" 确定 ");
		confirmButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				sendReceipt=creatSendreceipt();
				
				if(sendReceipt.getMoney()<0){
					displayFeeError();
				}
				else if(transportBL.verify(sendReceipt)){
					displaySuccess();
					transportBL.submit(sendReceipt);
				}
				else{
					displayNumFormatError();
				}
			}
		});
		
		this.add(confirmButton,FlowLayout.TRAILING);
	}
	
	private void displayNumFormatError(){
		final JDialog numErrorDialog=new JDialog();
		JLabel errorTip=new JLabel("数据格式错误！");
		JButton okButton=new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				numErrorDialog.setVisible(false);
			}
		});
		//numErrorDialog.setLayout(new FlowLayout(numErrorDialog));
		numErrorDialog.setSize(200,150);
		numErrorDialog.setLocationRelativeTo(this);
	
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(errorTip);
		panel.add(okButton);
		numErrorDialog.getContentPane().add(panel);
		
		//numErrorDialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		numErrorDialog.setVisible(true);
	}
	
	
	private void displaySuccess(){
		final JDialog addSucDialog=new JDialog();
		JLabel errorTip=new JLabel("添加成功！");
		JButton okButton=new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addSucDialog.setVisible(false);
			}
		});
		//calFeeSucDialog.setLayout(new BoxLayout(calFeeSucDialog, BoxLayout.Y_AXIS));
		addSucDialog.setSize(200,150);
		addSucDialog.setLocationRelativeTo(this);
	
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(errorTip);
		panel.add(okButton);
		addSucDialog.getContentPane().add(panel);
		addSucDialog.setVisible(true);
	}
	
	private void displayFeeError(){
		final JDialog calFeeErrorDialog=new JDialog();
		JLabel errorTip=new JLabel("请重新计算价格！");
		JButton okButton=new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				calFeeErrorDialog.setVisible(false);
			}
		});
		//calFeeErrorDialog.setLayout(new BoxLayout(calFeeErrorDialog, BoxLayout.Y_AXIS));
		calFeeErrorDialog.setSize(200,150);
		calFeeErrorDialog.setLocationRelativeTo(this);
	
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(errorTip);
		panel.add(okButton);
		calFeeErrorDialog.getContentPane().add(panel);
		calFeeErrorDialog.setVisible(true);
	}
	
	private void initCancelButton(){
		
		cancelButton=new JButton(" 取消 ");
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteInput();
			}
		});
		
		this.add(cancelButton,FlowLayout.TRAILING);
	}
	
	/*
	 * 点击取消按钮时清空已输入的数据
	 */
	private void deleteInput(){
		senderNameText.setText(null);
		senderAddressText.setText(null);
		senderCompanyText.setText(null);
		senderPhoneText.setText(null);
		
		receiverNameText.setText(null);
		receiverAddressText.setText(null);
		receiverCompanyText.setText(null);
		receiverPhoneText.setText(null);
		
		goodsNumText.setText(null);
		weightText.setText(null);
		volumnText.setText(null);
		goodsNameText.setText(null);
		
		orderTypeText.setText(null);
		wrapTypeText.setText(null);
		orderNumText.setText(null);
		feeText.setText(null);
	}
	
	private SendReceiptVO creatSendreceipt() {
		String senderName=senderNameText.getText();//寄件人姓名
		String senderLoc=senderAddressText.getText();//寄件人住址
		String senderUnit=senderCompanyText.getText();//寄件人单位
		String senderPhone=senderPhoneText.getText();//寄件人手机
		String receiverName=receiverNameText.getText();//收件人姓名
		String receiverLoc=receiverAddressText.getText();//收件人住址
		String receiverUnit=receiverCompanyText.getText();//收件人单位
		String receiverPhone=receiverPhoneText.getText();//收件人手机
		int number=Integer.parseInt(goodsNumText.getText());//原件数
		double weight=Double.parseDouble(weightText.getText());//实际重量
		double volume=Double.parseDouble(volumnText.getText());//体积
		String name=goodsNameText.getText();//内件品名
		String expressType=orderTypeText.getText();//快递类型
		String pack=wrapTypeText.getText();//包装种类
		String expressNumber=orderNumText.getText();//订单条形码号
		double money=0.0;//金额
		myTime predictTime=new myTime();//预计到达时间
		

		return new SendReceiptVO(senderName, senderLoc, senderUnit, senderPhone, 
				receiverName, receiverLoc, receiverUnit, receiverPhone, number, weight, 
				volume, name, expressType, pack, expressNumber, money, predictTime);
	}
	
	private void initSenderPanel(){
		JLabel sender=new JLabel("寄件人");
		
		JPanel senderInfoField=new JPanel();
		JLabel senderName=new JLabel("姓名             ");
		JLabel senderAddress=new JLabel("住址     ");
		JLabel senderCompany=new JLabel("单位     ");
		JLabel senderPhone=new JLabel("手机     ");
		
		JPanel senderTextField=new JPanel();
		senderNameText=new JTextField(TEXTLENGTH);
		senderAddressText=new JTextField(TEXTLENGTH);
		senderCompanyText=new JTextField(TEXTLENGTH);
		senderPhoneText=new JTextField(TEXTLENGTH);
		
		
		senderInfoField.setLayout(new BoxLayout(senderInfoField, BoxLayout.Y_AXIS));
		
		//senderInfoField.add(sender);
		
		senderInfoField.add(senderName);
		senderInfoField.add(senderAddress);
		senderInfoField.add(senderCompany);
		senderInfoField.add(senderPhone);
		
		senderTextField.setLayout(new BoxLayout(senderTextField, BoxLayout.Y_AXIS));
		senderTextField.add(senderNameText);
		senderTextField.add(senderAddressText);
		senderTextField.add(senderCompanyText);
		senderTextField.add(senderPhoneText);
		
		senderPanel.setLayout(new BorderLayout());
		senderPanel.add(BorderLayout.NORTH, sender);
		senderPanel.add(BorderLayout.WEST,senderInfoField);
		senderPanel.add(BorderLayout.CENTER,senderTextField);
		
		this.add(senderPanel);
	}
	
	private void initReceiverPanel(){
		JLabel receiver=new JLabel("收件人");
		
		JPanel receiverInfoField=new JPanel();
		JLabel receiverName=new JLabel("姓名             ");
		JLabel receiverAddress=new JLabel("住址     ");
		JLabel receiverCompany=new JLabel("单位     ");
		JLabel receiverPhone=new JLabel("手机    ");
		
		JPanel receiverTextField=new JPanel();
		receiverNameText=new JTextField(TEXTLENGTH);
		receiverAddressText=new JTextField(TEXTLENGTH);
		receiverCompanyText=new JTextField(TEXTLENGTH);
		receiverPhoneText=new JTextField(TEXTLENGTH);
		
		
		receiverInfoField.setLayout(new BoxLayout(receiverInfoField, BoxLayout.Y_AXIS));
		
		//receiverInfoField.add(receiver);
		
		receiverInfoField.add(receiverName);
		receiverInfoField.add(receiverAddress);
		receiverInfoField.add(receiverCompany);
		receiverInfoField.add(receiverPhone);
		
		receiverTextField.setLayout(new BoxLayout(receiverTextField, BoxLayout.Y_AXIS));
		receiverTextField.add(receiverNameText);
		receiverTextField.add(receiverAddressText);
		receiverTextField.add(receiverCompanyText);
		receiverTextField.add(receiverPhoneText);
		
		receiverPanel.setLayout(new BorderLayout());
		receiverPanel.add(BorderLayout.NORTH, receiver);
		receiverPanel.add(BorderLayout.WEST,receiverInfoField);
		receiverPanel.add(BorderLayout.EAST,receiverTextField);
		
		this.add(receiverPanel);
	}
	
	private void initGoodsInfoPanel(){
		
		JPanel goodsInfoField=new JPanel();
		JLabel goodsNumLable=new JLabel("原件数 ");
		JLabel weightLable=new JLabel("实际重量 ");
		JLabel volumnLable =new JLabel("实际体积 ");
		JLabel goodsNameLable=new JLabel("内件品名 ");
		
		goodsInfoField.setLayout(new BoxLayout(goodsInfoField, BoxLayout.Y_AXIS));
		goodsInfoField.add(goodsNumLable);
		goodsInfoField.add(weightLable);
		goodsInfoField.add(volumnLable);
		goodsInfoField.add(goodsNameLable);
		
		goodsNumText=new JTextField(TEXTLENGTH);
		weightText=new JTextField(TEXTLENGTH);
		volumnText=new JTextField(TEXTLENGTH);
		goodsNameText=new JTextField(TEXTLENGTH);
		JPanel goodsInfoTextField=new JPanel();
		
		goodsInfoTextField.setLayout(new BoxLayout(goodsInfoTextField, BoxLayout.Y_AXIS));
		goodsInfoTextField.add(goodsNumText);
		goodsInfoTextField.add(weightText);
		goodsInfoTextField.add(volumnText);
		goodsInfoTextField.add(goodsNameText);
		
		goodsInfoPanel.add(goodsInfoField);
		goodsInfoPanel.add(goodsInfoTextField);
		
		this.add(goodsInfoPanel);
	}
	
	private void initOrderPanel(){
		
		JPanel orderField=new JPanel();
		JLabel orderTypeLable=new JLabel("快递类型 ");
		JLabel wrapTypeLable=new JLabel("包装种类 ");
		JLabel orderNumLable =new JLabel("订单号 ");
		JLabel feeLable=new JLabel("运费 ");
		
		orderField.setLayout(new BoxLayout(orderField, BoxLayout.Y_AXIS));
		orderField.add(orderTypeLable);
		orderField.add(wrapTypeLable);
		orderField.add(orderNumLable);
		orderField.add(feeLable);
		
		JPanel orderTextField=new JPanel();
		orderTypeText=new JTextField(TEXTLENGTH);
		wrapTypeText=new JTextField(TEXTLENGTH);
		orderNumText=new JTextField(TEXTLENGTH);
		feeText=new JTextField(TEXTLENGTH);
		
		orderTextField.setLayout(new BoxLayout(orderTextField, BoxLayout.Y_AXIS));
		orderTextField.add(orderTypeText);
		orderTextField.add(wrapTypeText);
		orderTextField.add(orderNumText);
		orderTextField.add(feeText);
		
		orderPanel.add(orderField);
		orderPanel.add(orderTextField);
		
		this.add(orderPanel);
	}
	
	
	
	
}
