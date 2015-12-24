package businessLogic.strategybl.StrategyPattern;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * 快递员工资策略
 */
public class CourierSalaryStrategy extends SalaryStrategy {
    public CourierSalaryStrategy() throws RemoteException, NotBoundException, MalformedURLException, SQLException {
        super();
    }

    public double getSalary( int times) {
        double salary = salaryVO.getMailerBS()+salaryVO.getMailerAl()*times;
        return salary;
    }
}
