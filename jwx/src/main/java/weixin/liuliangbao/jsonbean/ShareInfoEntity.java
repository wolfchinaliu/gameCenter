//package weixin.liuliangbao.jsonbean;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.util.Date;
//
///**
// * Created by aa on 2015/12/17.
// */
//@Entity
//@Table(name = "shareInfo", schema = "")
//@SuppressWarnings("serial")
//public class ShareInfoEntity implements java.io.Serializable{
//    private java.lang.String id;
//    private java.lang.String sharePhone;
//    private java.lang.String sharedOpenid;
//    private double shareFlowValue;
//    private Date date;
//    private String accountid;
//
//
//    @Id
//    @GeneratedValue(generator = "paymentableGenerator")
//    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
//    @Column(name ="ID",nullable=false,length=36)
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//    @Column(name ="SHAREPHONE")
//    public String getSharePhone() {
//        return sharePhone;
//    }
//
//    public void setSharePhone(String sharePhone) {
//        this.sharePhone = sharePhone;
//    }
//    @Column(name ="SHAREDOPENID")
//    public String getSharedOpenid() {
//        return sharedOpenid;
//    }
//
//    public void setSharedOpenid(String sharedOpenid) {
//        this.sharedOpenid = sharedOpenid;
//    }
//    @Column(name ="SHAREFLOWVALUE")
//    public double getShareFlowValue() {
//        return shareFlowValue;
//    }
//
//    public void setShareFlowValue(double shareFlowValue) {
//        this.shareFlowValue = shareFlowValue;
//    }
//    @Column(name ="DATE")
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//    @Column(name ="ACCOUNTID")
//    public String getAccountid() {
//        return accountid;
//    }
//
//    public void setAccountid(String accountid) {
//        this.accountid = accountid;
//    }
//}
