package constent;

import java.text.SimpleDateFormat;

/**
 * 系统通用常量定义
 */
public class Constent {

    /**
     * 默认汽车装载量为10吨
     */
    public static final int TRUCK_LOAD = 10;

    /**
     * 默认火车装载量为2000吨
     */
    public static final int TRAIN_LOAD = 2000;

    /**
     * 默认飞机装载量为50吨
     */
    public static final int PLANE_LOAD = 50;

    /**
     * 同一个城市内营业厅之间距离以及营业厅和本地中转中心之间的距离默认全部都是30km
     */
    public static final int LOCAL_DISTANCE = 30;

    /**
     * 用户工号长度定义
     */
    public static final int USER_ID_LENGTH=9;

    /**
     * 身份证号长度
     */
    public static final int PERSON_ID_LENGTH=18;

    /**
     * 手机号位数
     */
    public static final int PHONE_LENGTH=11;

    /**
     * 营业厅编号长度
     */
    public static final int STORE_ID_LENGTH=6;

    /**
     * 中转中心编号长度
     */
    public static final int HUB_ID_LENGTH=4;

    /**
     * 中转单编号长度
     */
    public static final int Transfer_ID_LENGTH=19;
    
    /**
     * 快递种类
     */
    public static final String[] EXPRESS_TYPE={"经济快递","标准快递","特快快递"};

    /**
     * 包装种类
     */
    public static final String[] PACK_TYPE={"快递袋","纸箱","木箱"};

    /**
     * 时间格式
     */
    public static final SimpleDateFormat DATE_FORMAT =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间格式

    /**
     * 出生日期格式
     */
    public static final SimpleDateFormat BIRTHDAY_FORMAT =new SimpleDateFormat("yyyy-MM-dd");//设置时间格式

    /**
     * 中转单、装车单等单号自动生成时需要的8位日期编码
     */
    public static final SimpleDateFormat RECIEPT_NUM_FORMAT =new SimpleDateFormat("yyyyMMdd");

    /**
     * 订单号位数
     */
    public static final int ORDER_ID_LENGTH=10;

    /**
     * 营业厅或中转中心汽运编号位数，虽然合适不同，但都是19位
     */
    public static final int QIYUN_ID_LENGTH=19;

    /**
     * 车辆代号长度
     */
    public static final int TRUCK_ID_LENGTH=9;

    /**
     * 司机编号位数
     */
    public static final int DRIVER_ID_LENGTH=9;

    /**
     * 收付款时的条目，对应
     * @see typeDefinition.FeeType
     * 中的顺序
     */
    public static final String [] FEE_TYPE_STR = {"租金","运费","工资","奖金","快递费"};

    /**
     * 拥有登录该系统帐号的职位
     */
    public static final String [] USER_ACCOUNR_JOB = {"快递员","营业厅业务员","中转中心业务员","财务人员",
            "仓库管理员","总经理","管理员"};

    /**
     * 对应枚举类
     * @see typeDefinition.Job
     * 中的顺序
     */
    public static final String [] JOB_STRING={"寄件人","快递员","营业厅业务员","中转中心业务员","财务人员",
            "仓库管理员","总经理","管理员","司机"};

    /**
     * 目前支持的地址，寄件单的寄件和收件地址框的前两个字符必须为其中之一
     */
    public static final String[] LOCATIONS={"北京","上海","广州","南京"};

    /**
     * 城市区号，和上一条LOCATIIONS顺序相对应，不可换序
     */
    public static final String[] CITY_ID={"010", "021", "020", "025"};

    /**
     * 常用提示消息，与MessageType枚举类中的顺序相对应，不可颠倒！
     */
    public static final String[] TIP_MESSAGE= {"网络连接超时，请检查网络","该单据已存在","该员工已存在","该机构已存在",
            "该司机已存在","该车辆已存在","该账户已存在","该订单不存在", "提交成功", "添加成功", "删除成功","修改信息成功"};

    /**
     * 付款单编号默认11位
     */
    public static final int PAY_RECEIPT_LENGTH = 11;

    /**
     * 中转方式表，顺序不可变
     */
    public static final String [] VEHICLE_TYPE_STR = {"汽车","火车","飞机"};
}
