package businessLogic.strategybl.StrategyPattern;

import blfactory.BLFactory;
import businessLogicService.strategyblservice.SalaryStrategyBLService;
import businessLogicService.strategyblservice.StrategyPatternService.SalaryStrategyPatternService;
import vo.strategyvo.SalaryVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * 计算人员工资策略的父类，每个不同职位的员工工资的计算策略类继承该类并实现具体计算工资的方法
 * 该父类已经初始化了可能对子类计算工资有用处的 SalaryVO
 * @see SalaryVO
 */
public abstract class SalaryStrategy implements SalaryStrategyPatternService{
    protected SalaryVO salaryVO;
    public SalaryStrategy() throws RemoteException, NotBoundException, MalformedURLException, SQLException {
        SalaryStrategyBLService salaryService= BLFactory.getSalaryBLService();
        salaryVO= salaryService.getSalary();
    }
}
