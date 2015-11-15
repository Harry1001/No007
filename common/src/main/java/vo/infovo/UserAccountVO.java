package vo.infovo;

import po.infopo.UserAccountPO;
import typeDefinition.InfoType;
import typeDefinition.Job;

/**
 * Created by Harry on 2015/11/15.
 */
public class UserAccountVO extends InfoVO {
    private static final long serialVersionUID = 1L;
    private String userID;//工号
    private String name;//姓名
    private Job position;
    //private String userName;//用户名
    private String password;

    public UserAccountVO(String userID,String name,
                         Job position,String password) {
        super(InfoType.ACCOUNT);
        // TODO Auto-generated constructor stub
        this.setUserID(userID);
        this.setName(name);
        this.setPosition(position);
        //this.setUserName(userName);
        this.setPassword(password);
    }

    public UserAccountVO(UserAccountPO u){
        this(u.getUserID(),u.getName(),u.getPosition(),u.getPassword());
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job getPosition() {
        return position;
    }

    public void setPosition(Job position) {
        this.position = position;
    }





    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
