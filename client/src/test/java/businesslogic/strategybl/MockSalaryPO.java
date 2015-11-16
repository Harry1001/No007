package businesslogic.strategybl;

import po.strategypo.SalaryPO;

/**
 * Created by Harry on 2015/11/16.
 */
public class MockSalaryPO extends SalaryPO {
    public MockSalaryPO(int mbs, int dbs, int manbs, int acbs, int ssbs, int hbs, int skbs, int adbs, int mal, int dal) {
        super(mbs, dbs, manbs, acbs, ssbs, hbs, skbs, adbs, mal, dal);
    }

    public MockSalaryPO(int mbs, int mal){
        this(mbs, 0,0,0,0,0,0,0,mal,0);
    }


}
