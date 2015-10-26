package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businessLogicService.TransportBLService;
import vo.SendReceiptVO;

public class AddSendReceiptPanel extends JPanel{
	
	private static final int TEXTLENGTH=15;
	
	private TransportBLService transportBL;
	
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
	
	private JTextField goodsNum;
	private JTextField weight;
	private JTextField volumn;
	private JTextField goodsName;
	
	private JTextField orderType;
	private JTextField wrapType;
	private JTextField orderNum;
	private JTextField fee;
	
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
				
				fee.setText("10000");
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
				
			}
		});
		
		this.add(confirmButton,FlowLayout.TRAILING);
	}
	
	private void initCancelButton(){
		
		cancelButton=new JButton(" 取消 ");
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.add(cancelButton,FlowLayout.TRAILING);
	}
	
	private SendReceiptVO creatSendreceipt() {
		return null;
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
		
		goodsNum=new JTextField(TEXTLENGTH);
		weight=new JTextField(TEXTLENGTH);
		volumn=new JTextField(TEXTLENGTH);
		goodsName=new JTextField(TEXTLENGTH);
		JPanel goodsInfoTextField=new JPanel();
		
		goodsInfoTextField.setLayout(new BoxLayout(goodsInfoTextField, BoxLayout.Y_AXIS));
		goodsInfoTextField.add(goodsNum);
		goodsInfoTextField.add(weight);
		goodsInfoTextField.add(volumn);
		goodsInfoTextField.add(goodsName);
		
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
		orderType=new JTextField(TEXTLENGTH);
		wrapType=new JTextField(TEXTLENGTH);
		orderNum=new JTextField(TEXTLENGTH);
		fee=new JTextField(TEXTLENGTH);
		
		orderTextField.setLayout(new BoxLayout(orderTextField, BoxLayout.Y_AXIS));
		orderTextField.add(orderType);
		orderTextField.add(wrapType);
		orderTextField.add(orderNum);
		orderTextField.add(fee);
		
		orderPanel.add(orderField);
		orderPanel.add(orderTextField);
		
		this.add(orderPanel);
	}
	
	
	
	
}
