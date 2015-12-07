package constent;

/**
 * Created by Harry on 2015/12/5.
 */
public class Constent {
    /**
     * 用户工号长度定义
     */
    public static final int USER_ID_LENGTH=9;

    /**
     * 手机号位数
     */
    public static final int PHONE_LENGTH=9;

    /**
     * 订单号位数
     */
    public static final int ORDER_ID_LENGTH=9;

    /**
     * 对应枚举类
     * @see typeDefinition.Job
     * 中的顺序
     */
    public static final String [] JOB_STRING={"寄件人","快递员","营业厅业务员","中转中心业务员","财务人员" +
            "仓库管理员","总经理","管理员","司机"};

    /**
     * 目前支持的地址，寄件单的寄件和收件地址框的前两个字符必须为其中之一
     */
    public static final String[] LOCATIONS={"南京","北京","上海"};
}
