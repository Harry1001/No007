package businesslogic.strategybl;

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.strategybl.SalaryStrategyBL;
import typeDefinition.Job;

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