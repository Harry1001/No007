package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import businessLogic.ReceiptBLImpl;
import businessLogic.TransportBLImpl;
import businessLogicService.ReceiptBLService;
import businessLogicService.TransportBLService;
import data.ReceiptDataImpl;
import dataService.ReceiptDataService;
import presentation.AddSendReceiptPanel;



public class Main {

	public static void main(String[] args){
		
		ReceiptDataService receiptData=new ReceiptDataImpl();
		ReceiptBLService receiptBL=new ReceiptBLImpl(receiptData);
		JFrame mFrame = new JFrame();
		mFrame.setSize(550, 400);
		mFrame.setLocation(300, 300);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TransportBLService transBL = new TransportBLImpl(receiptBL);
		JPanel panel = new AddSendReceiptPanel(transBL);
		mFrame.getContentPane().add(panel);
		mFrame.setVisible(true);
	}
}
