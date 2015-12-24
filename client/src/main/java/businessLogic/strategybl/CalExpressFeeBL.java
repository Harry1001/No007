package businessLogic.strategybl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import blfactory.StrategyFactory;
import businessLogicService.strategyblservice.CalExpressfeeService;
import businessLogicService.strategyblservice.StrategyPatternService.ExpressFeeStrategyPatternService;
import dataService.StrategyDataService;
import dataService._RMI;
import po.strategypo.ExpressFeePO;
import vo.receiptvo.SendReceiptVO;

public class CalExpressFeeBL implements CalExpressfeeService{

	/**
	 * 利用策略模式计算快递费
	 * @see StrategyFactory
	 * @see ExpressFeeStrategyPatternService
	 * @param vo 需要计算快递费的单据
	 * @return 计算出的快递费
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws NotBoundException
	 */
	public double calExpressFee(SendReceiptVO vo) throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, NotBoundException {
		ExpressFeeStrategyPatternService expressFeeService;
		expressFeeService= StrategyFactory.getExpressFeeService();
		double price=expressFeeService.getExpressFee(vo);
		return price;
	}

	
	
	
}