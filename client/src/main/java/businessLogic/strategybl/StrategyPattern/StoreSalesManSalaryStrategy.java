package businessLogic.strategybl.StrategyPattern;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * 营业厅业务员工资策略
 */
public class StoreSalesManSalaryStrategy extends SalaryStrategy {
    public StoreSalesManSalaryStrategy() throws RemoteException, NotBoundException, MalformedURLException, SQLException {
    }

    public double getSalary(int times) {
        return salaryVO.getStoresalesmanBS();
    }
}
