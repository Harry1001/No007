package vo.loginvo;

import typeDefinition.Job;

import java.io.Serializable;

/**
 * Created by Harry on 2015/12/5.
 */
public class LoginResultVO implements Serializable{
    
    private Job job;
    private String id;
    private String name;

    public LoginResultVO(String id, Job job, String name) {
        this.id = id;
        this.job = job;
        this.name = name;
    }

    public Job getJob() {
        return job;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
