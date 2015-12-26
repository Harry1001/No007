package typeDefinition;

/**
 * 界面层提示类型，定义一些常用提示，包含rmi超时，单据重复，人员重复，机构重复，司机重复，车辆重复，
 * 账户（银行账户和用户账户）重复，订单不存在, 提交成功, 添加成功
 *
 * 与Constent类中的TIP_MESSAGE顺序对应，不可颠倒！
 *
 * @see constent.Constent
 */
public enum MessageType {
    RMI_LAG, RECEIPT_EXIST, STAFF_EXIST, AGENCY_EXIST, DRIVER_EXIST, TRUCK_EXIST, ACCOUNT_EXIST, ORDER_NOT_FOUND,
    SUBMIT_SUCCESS, ADD_SUCCESS, DELETE_SUCCESS, MODIFY_SUCCESS
}
