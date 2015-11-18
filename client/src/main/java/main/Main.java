package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import businessLogic.receiptbl.ReceiptBL;
import businessLogic.transportbl.TransportBL;
import businessLogicService.receiptblservice.GetReceiptBLService;
import businessLogicService.transportblservice.TransportBLService;
import data.ReceiptDataImpl;
import dataService.ReceiptDataService;
import presentation.AddSendReceiptPanel;



public class Main {

	public static void main(String[] args){
		
		ReceiptDataService receiptData=new ReceiptDataImpl();
		GetReceiptBLService receiptBL=new ReceiptBL(receiptData);
		JFrame mFrame = new JFrame();
		mFrame.setSize(550, 400);
		mFrame.setLocation(300, 300);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TransportBLService transBL = new TransportBL();
		JPanel panel = new AddSendReceiptPanel(transBL);
		mFrame.getContentPane().add(panel);
		mFrame.setVisible(true);
	}
}
