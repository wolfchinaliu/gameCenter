package weixin.report.model;

import java.io.Serializable;

/**
 * 新增粉丝记录Entity
 * @author dyt
 * */
public class NewMemberEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String num;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}