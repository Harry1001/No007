package businessLogic.strategybl.StrategyPattern;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * 总经理工资策略
 */
public class ManagerSalaryStrategy extends SalaryStrategy {
    public ManagerSalaryStrategy() throws RemoteException, NotBoundException, MalformedURLException, SQLException {
    }

    public double getSalary(int times) {
        return salaryVO.getManagerBS();
    }
}
