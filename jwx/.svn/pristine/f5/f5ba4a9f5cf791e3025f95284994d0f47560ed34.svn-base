package weixin.lottery.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2016/1/20.
 */
@Entity
@Table(name = "weixin_guessRiddlehd", schema = "")
@PrimaryKeyJoinColumn(name = "id")
public class WeixinGuessRiddleEntity extends WeixinCommonforhdEntity implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private java.lang.Double getFlow;    //
    private java.lang.Double allFlow;   //
    //    private java.lang.Double shareFlow;   //
    private java.lang.Double riddleFlow;  //

    private String state;

    @Column(name = "state", nullable = false, length = 8)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Column(name = "GETFLOW", nullable = false, length = 12)
    public Double getGetFlow() {
        return getFlow;
    }

    public void setGetFlow(Double getFlow) {
        this.getFlow = getFlow;
    }

    @Column(name = "ALLFLOW", nullable = false, length = 12)
    public Double getAllFlow() {
        return allFlow;
    }

    public void setAllFlow(Double allFlow) {
        this.allFlow = allFlow;
    }



    @Column(name = "RIDDLEFLOW", nullable = false, length = 12)
    public Double getRiddleFlow() {
        return riddleFlow;
    }

    public void setRiddleFlow(Double riddleFlow) {
        this.riddleFlow = riddleFlow;
    }


}
