package weixin.liuliangbao.jsonbean;

import org.hibernate.annotations.GenericGenerator;

import java.lang.String;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * Created by aa on 2015/12/4.
 */
@Entity
@Table(name = "liuliang_main", schema = "")
@SuppressWarnings("serial")
public class FlowMainEntity implements java.io.Serializable{
    /**主键*/
    private java.lang.String id;
    private java.lang.String accountid;


    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="ID",nullable=false,length=36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name ="ACCOUNTID",nullable=true,length=36)
    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }
}
