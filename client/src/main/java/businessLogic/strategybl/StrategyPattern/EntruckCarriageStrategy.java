package businessLogic.strategybl.StrategyPattern;

import blfactory.BLFactory;
import businessLogicService.strategyblservice.FeeStrategyBLService;
import businessLogicService.strategyblservice.StrategyPatternService.CarriageStrategyPatternService;
import constent.Constent;
import vo.receiptvo.ReceiptVO;
import vo.strategyvo.CarriageFeeVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;


/**
 * 为装车单计算运费的策略
 *
 */
public class EntruckCarriageStrategy implements CarriageStrategyPatternService {
    public double getCarriageFee(ReceiptVO vo) throws RemoteException, NotBoundException, MalformedURLException, SQLException {
        FeeStrategyBLService feeStrategyBLService = BLFactory.getFeeBLService();
        CarriageFeeVO feeVO = feeStrategyBLService.getCarriageFee();
        double totalPrice= Constent.LOCAL_DISTANCE*Constent.TRUCK_LOAD*(feeVO.getBusPrice());
        return totalPrice;
    }
}
