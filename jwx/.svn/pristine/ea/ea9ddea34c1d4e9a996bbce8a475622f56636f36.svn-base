package weixin.liuliangbao.flowcard.Entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * Created by aa on 2015/12/21.
 */
@Entity
@Table(name = "FlowCard_Task", schema = "")
@SuppressWarnings("serial")
public class FlowCardTaskEntity implements java.io.Serializable {
    @Excel(exportName = "id")
    private java.lang.String id;
    @Excel(exportName = "线程状态")
    private java.lang.String statusValue;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "STATUSVALUE", nullable = true, length = 1)
    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }
}
