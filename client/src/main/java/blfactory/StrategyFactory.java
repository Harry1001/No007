package blfactory;

import businessLogic.strategybl.StrategyPattern.*;
import businessLogicService.strategyblservice.StrategyPatternService.CarriageStrategyPatternService;
import businessLogicService.strategyblservice.StrategyPatternService.ExpressFeeStrategyPatternService;
import businessLogicService.strategyblservice.StrategyPatternService.SalaryStrategyPatternService;
import typeDefinition.Job;
import typeDefinition.ReceiptType;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * 策略模式工厂
 */
public class StrategyFactory {

    /**
     * 根据单据类型返回对应的计算运费的策略
     * @param type 单据类型，目前只支持装车单和中转单
     * @return 运费策略
     * @see CarriageStrategyPatternService
     */
    public static CarriageStrategyPatternService getCarriageService(ReceiptType type){
        switch (type){
            case ENTRUCK:return new EntruckCarriageStrategy();
            case TRANSFER:return new TransferCarriageStrategy();
            default:return null;
        }
    }

    /**
     * 返回计算快递费用的策略
     * @return
     */
    public static ExpressFeeStrategyPatternService getExpressFeeService(){
        return new ExpressFeeStrategy();
    }

    /**
     * 根据职位返回对应的计算工资的策略
     * @param job 职位
     * @return 工资策略
     * @see SalaryStrategyPatternService
     * @throws RemoteException
     * @throws MalformedURLException
     * @throws SQLException
     * @throws NotBoundException
     */
    public static SalaryStrategyPatternService getSalaryService(Job job) throws RemoteException, MalformedURLException, SQLException, NotBoundException {
        switch (job){
            case COURIER:return new CourierSalaryStrategy();
            case DRIVER:return new DriverSalaryStrategy();
            case MANAGER:return new ManagerSalaryStrategy();
            case ACCOUNTANT:return new AccountantSalaryStrategy();
            case STORESALESMAN:return new StoreSalesManSalaryStrategy();
            case HUBSALESMAN:return new HubSalesManSalaryStrategy();
            case STOREKEEPER:return new StoreKeeperSalaryStrategy();
            case ADMINISTRATOR:return new AdministratorSalaryStrategy();
            default: return null;
        }
    }
}
