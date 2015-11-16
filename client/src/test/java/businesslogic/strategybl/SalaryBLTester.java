package businesslogic.strategybl;

import businessLogic.strategybl.SalaryStrategyBL;
import org.junit.Test;
import typeDefinition.Job;
import static org.junit.Assert.assertEquals;

/**
 * Created by Harry on 2015/11/16.
 */
public class SalaryBLTester {

    @Test
    public void testSalary(){
        SalaryStrategyBL salaryStrategyBL=new SalaryStrategyBL(new MockSalaryDataImpl());
        assertEquals(4100, (int)salaryStrategyBL.calSalary(Job.COURIER, 100));

    }
}
