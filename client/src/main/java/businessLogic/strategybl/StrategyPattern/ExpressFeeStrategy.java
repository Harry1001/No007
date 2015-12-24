package businessLogic.strategybl.StrategyPattern;

import blfactory.BLFactory;
import businessLogicService.strategyblservice.DistanceService;
import businessLogicService.strategyblservice.FeeStrategyBLService;
import businessLogicService.strategyblservice.StrategyPatternService.ExpressFeeStrategyPatternService;
import vo.receiptvo.SendReceiptVO;
import vo.strategyvo.ExpressFeeVO;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

/**
 * Created by Harry on 2015/12/24.
 */
public class ExpressFeeStrategy implements ExpressFeeStrategyPatternService {
    public double getExpressFee(SendReceiptVO vo) throws IOException, NotBoundException, SQLException, ClassNotFoundException {

        DistanceService distanceService=BLFactory.getDistanceService();
        FeeStrategyBLService feeStrategyBLService= BLFactory.getFeeBLService();
        ExpressFeeVO feeVO=feeStrategyBLService.getExpressFee();

        double totalPrice = 0;
        double w = vo.getWeight();
        double v = vo.getVolume();
        double trueWeight = 0;
        if (v>1) {          //判断体积与实际重量
            trueWeight= (w>v*3? w : v*3);
        }
        else {
            trueWeight=w;
        }

        String s1=vo.getSenderLoc();     //计算距离
        String s11=s1.substring(0, 2);
        String s2=vo.getReceiverLoc();
        String s22=s2.substring(0, 2);
        double dis=distanceService.getDistance(s11, s22);

        if(vo.getExpressType().equals("经济快递")){

            double p=feeVO.getEcoPrice();
            totalPrice+=trueWeight*p*dis;
        }
        else if(vo.getExpressType().equals("标准快递")){

            double p=feeVO.getStdPrice();
            totalPrice+=trueWeight*p*dis;
        }
        else if(vo.getExpressType().equals("特快快递")){

            double p=feeVO.getSpePrice();
            totalPrice+=trueWeight*p*dis;
        }

        if(vo.getPack().equals("快递袋")){
            totalPrice+=1;
        }else if(vo.getPack().equals("纸箱")){
            totalPrice+=5;
        }else if(vo.getPack().equals("木箱")){
            totalPrice+=10;
        }
        return totalPrice;
    }
}
