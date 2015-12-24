package businessLogic.strategybl.StrategyPattern;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * 司机工资策略
 */
public class DriverSalaryStrategy extends SalaryStrategy {
    public DriverSalaryStrategy() throws RemoteException, NotBoundException, MalformedURLException, SQLException {
        super();
    }

    public double getSalary(int times) {
        return salaryVO.getDriverBS()+salaryVO.getDriverAl()*times;
    }
}
