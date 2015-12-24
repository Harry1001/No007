package businessLogic.strategybl.StrategyPattern;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * 系统管理员工资策略
 */
public class AdministratorSalaryStrategy extends SalaryStrategy {
    public AdministratorSalaryStrategy() throws RemoteException, NotBoundException, MalformedURLException, SQLException {
    }

    public double getSalary(int times) {
        return salaryVO.getAdministerBS();
    }
}
