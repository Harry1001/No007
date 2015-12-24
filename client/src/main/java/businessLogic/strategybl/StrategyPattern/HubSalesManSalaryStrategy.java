package businessLogic.strategybl.StrategyPattern;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Harry on 2015/12/24.
 */
public class HubSalesManSalaryStrategy extends SalaryStrategy {
    public HubSalesManSalaryStrategy() throws RemoteException, NotBoundException, MalformedURLException, SQLException {
    }

    public double getSalary(int times) {
        return salaryVO.getHubsalesmanBS();
    }
}
