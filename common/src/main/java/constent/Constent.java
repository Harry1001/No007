package constent;

import java.text.SimpleDateFormat;

/**
 * 系统通用常量定义
 */
public class Constent {
    /**
     * 用户工号长度定义
     */
    public static final int USER_ID_LENGTH=9;

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
     * 对应枚举类
     * @see typeDefinition.Job
     * 中的顺序
     */
    public static final String [] JOB_STRING={"寄件人","快递员","营业厅业务员","中转中心业务员","财务人员" +
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
     * 根据区号获得城市名称
     * @param cityID 城市区号
     * @return 如果找到，返回城市名，否则返回null
     */
    public static String getLocationByCityID(String cityID){
        for (int i=0;i<CITY_ID.length;i++){
            if (CITY_ID[i].equals(cityID)){
                return LOCATIONS[i];
            }
        }
        return null;
    }
}
