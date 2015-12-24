package businessLogic.strategybl.StrategyPattern;

import blfactory.BLFactory;
import businessLogicService.strategyblservice.DistanceService;
import businessLogicService.strategyblservice.FeeStrategyBLService;
import businessLogicService.strategyblservice.StrategyPatternService.CarriageStrategyPatternService;
import constent.Constent;
import typeDefinition.Vehicle;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.TransferReceiptVO;
import vo.strategyvo.CarriageFeeVO;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

/**
 * 为转运单计算运费的策略
 */
public class TransferCarriageStrategy implements CarriageStrategyPatternService {
    public double getCarriageFee(ReceiptVO vo) throws IOException, NotBoundException, SQLException, ClassNotFoundException {
        FeeStrategyBLService feeStrategyBLService = BLFactory.getFeeBLService();
        DistanceService distanceService = BLFactory.getDistanceService();

        CarriageFeeVO feeVO= feeStrategyBLService.getCarriageFee();

        TransferReceiptVO trvo=(TransferReceiptVO) vo;
        Vehicle s1=trvo.getTransferType();
        double totalPrice = 0;

        String s2=trvo.getDepartLoc();
        String s22=s2.substring(0, 2);
        String s3=trvo.getArriveLoc();
        String s33=s3.substring(0, 2);
        double dis=distanceService.getDistance(s22, s33);

        if(s1 == Vehicle.PLANE){
            totalPrice= Constent.PLANE_LOAD*dis*feeVO.getPlanePrice();
        }else if(s1 ==Vehicle.TRAIN){
            totalPrice=Constent.TRAIN_LOAD*dis*feeVO.getTrainPrice();
        }else if(s1 == Vehicle.TRUCK){
            totalPrice=Constent.TRUCK_LOAD*dis*feeVO.getBusPrice();
        }

        return totalPrice;
    }
}
