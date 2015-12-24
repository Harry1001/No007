package businessLogic.strategybl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

import blfactory.StrategyFactory;
import businessLogicService.strategyblservice.CalCarriageService;

import businessLogicService.strategyblservice.StrategyPatternService.CarriageStrategyPatternService;
import vo.receiptvo.ReceiptVO;

/**
 * 为装车单和中转单计算运费的逻辑实现类
 */
public class CalCarriageFeeBL implements CalCarriageService{

	/**
	 * 利用策略模式和工厂模式，策略工厂根据单据类型生成相应的策略模式计算方法类，然后计算运费
	 * @param vo 需要计算运费的单据
	 * @return 计算得出的价格
	 * @see StrategyFactory
	 * @see CarriageStrategyPatternService
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws NotBoundException
	 */
	public double calCarriage(ReceiptVO vo) throws SQLException, FileNotFoundException, ClassNotFoundException, IOException, NotBoundException {
		CarriageStrategyPatternService carriageService;
		carriageService= StrategyFactory.getCarriageService(vo.getType());
		double price= carriageService.getCarriageFee(vo);
		return price;
	}
	
}