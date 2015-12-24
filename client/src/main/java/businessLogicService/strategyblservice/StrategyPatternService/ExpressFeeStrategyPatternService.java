package businessLogicService.strategyblservice.StrategyPatternService;

import vo.receiptvo.SendReceiptVO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * 计算快递费的策略模式接口，具体策略实现类实现该接口
 */
public interface ExpressFeeStrategyPatternService  {

    public double getExpressFee(SendReceiptVO vo) throws IOException, NotBoundException, SQLException, ClassNotFoundException;
}
