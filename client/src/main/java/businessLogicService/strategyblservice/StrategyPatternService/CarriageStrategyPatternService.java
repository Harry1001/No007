package businessLogicService.strategyblservice.StrategyPatternService;

import vo.receiptvo.ReceiptVO;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;


/**
 * 计算运费的策略模式接口，具体的策略实现该接口
 */
public interface CarriageStrategyPatternService {
    /**
     * 不同的计算运费的策略类实现该方法
     * @param vo 需要计算运费的单据
     * @return 计算的运费
     * @throws IOException
     * @throws NotBoundException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public double getCarriageFee(ReceiptVO vo) throws IOException, NotBoundException, SQLException, ClassNotFoundException;
}
