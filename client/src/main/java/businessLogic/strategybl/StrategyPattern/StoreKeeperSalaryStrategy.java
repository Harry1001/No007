package businessLogic.strategybl.StrategyPattern;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * 仓库管理员工资策略
 */
public class StoreKeeperSalaryStrategy extends SalaryStrategy {
    public StoreKeeperSalaryStrategy() throws RemoteException, NotBoundException, MalformedURLException, SQLException {
    }

    public double getSalary(int times) {
        return salaryVO.getStorekeeperBS();
    }
}
