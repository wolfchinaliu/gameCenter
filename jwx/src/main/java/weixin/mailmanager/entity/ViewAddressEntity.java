package weixin.mailmanager.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "weixin_viewaddress", schema = "")
@SuppressWarnings("serial")
public class ViewAddressEntity implements java.io.Serializable {
    /**主键*/
    private java.lang.String id;
//    商户id
    private java.lang.String accountid;
    /*访问地址*/
    private java.lang.String viewAddress;
    private java.lang.String requestBody;
    private java.lang.String openId;
    private java.util.Date addDate;





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
    @Column(name ="accountid",nullable=false,length=36)
    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }
    @Column(name ="viewAddress",nullable=false,length=2048)
    public String getViewAddress() {
        return viewAddress;
    }

    public void setViewAddress(String viewAddress) {
        this.viewAddress = viewAddress;
    }
    @Column(name ="addDate",nullable=false,length=36)
    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
    @Column(name ="requestBody",nullable=false,length=2048)
    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
    @Column(name ="openId",nullable=false,length=36)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
