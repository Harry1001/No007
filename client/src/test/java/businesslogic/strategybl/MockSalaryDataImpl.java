package businesslogic.strategybl;

import dataService.StrategyDataService;
import po.strategypo.CarriageFeePO;
import po.strategypo.ExpressFeePO;
import po.strategypo.SalaryPO;

import java.rmi.RemoteException;

/**
 * Created by Harry on 2015/11/16.
 */
public class MockSalaryDataImpl implements StrategyDataService {
    public ExpressFeePO getExpressFee() throws RemoteException {
        return null;
    }

    public CarriageFeePO getCarriageFee() throws RemoteException {
        return null;
    }

    public void updateExpressFeeStrategy(ExpressFeePO efpo) throws RemoteException {

    }

    public void updateCarriageFeeStrategy(CarriageFeePO cfpo) throws RemoteException {

    }

    public SalaryPO getSalary() throws RemoteException {

        return new MockSalaryPO(4000,1);//基本工资4000，提成没送一个快递1元
    }

    public void updateSalaryStrategy(SalaryPO po) throws RemoteException {

    }
}
