package weixin.liuliangbao.business.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by 王硕 on 2016/1/22.
 */
@Entity
@Table( name = "family_number")
public class FamilyNumberEntity implements Serializable {
    /**主键，该条记录id*/
    private String id;
    /**用户昵称*/
    private String nickname;
    /**用户手机号*/
    private String mobilePhone;
    /**用户所绑定的亲情号*/
    private String familyNumber;
    /**绑定时间*/
    private Date addTime;
    /**状态位*/
    private String status;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="id",nullable=false,length=36)
    public java.lang.String getId(){
        return this.id;
    }

    public void setId(java.lang.String id){
        this.id = id;
    }

    @Column(name="nickname",nullable=true,length=255)
    public String getNickname() {
        return nickname;
    }

    @Column(name="mobilePhone",nullable =false,length = 30)
    public String getMobilePhone() {
        return mobilePhone;
    }

    @Column(name="family_number",nullable=true,length=30)
    public String getFamilyNumber() {
        return familyNumber;
    }

    @Column(name="add_time",nullable = false)
    public Date getAddTime() {
        return addTime;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setFamilyNumber(String familyNumber) {
        this.familyNumber = familyNumber;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Column(name="status",nullable =true,length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
