package weixin.acctlist.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by aa on 2016/6/13.
 */

@Entity
@Table(name = "weixin_acctlist", schema = "")
@SuppressWarnings("serial")
public class WeixinacctListEntity implements Serializable  {
    /*主键*/
    private java.lang.String id;
    /*名称*/
    private java.lang.String acctlistName;
    /*图片id*/
    private java.lang.String pictureId;
    /*图片Url*/
    private java.lang.String pictureUrl;
    /*地址*/
    private java.lang.String address;
    /*地址图片Id*/
    private java.lang.String addressPicId;
    /*地址图片URL*/
    private java.lang.String addressPicUrl;
    /*电话*/
    private java.lang.String phone;
    /*商家介绍*/
    private java.lang.String description;
    /*操作人*/
    private java.lang.String operator;
    /*操作时间*/
    private java.util.Date operateTime;
    /*商家公众号id*/
    private java.lang.String acctId;
    /*商家商城链接地址*/
    private java.lang.String shoppAddress;
    /*商家坐标*/
    private java.lang.String coordinates;
    /*商家地址*/
    private java.lang.String point;
    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  用户在系统的唯一标识
     */
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

    public String getAcctlistName() {
        return acctlistName;
    }

    public void setAcctlistName(String acctlistName) {
        this.acctlistName = acctlistName;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getAddressPicId() {
        return addressPicId;
    }

    public void setAddressPicId(String addressPicId) {
        this.addressPicId = addressPicId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressPicUrl() {
        return addressPicUrl;
    }

    public void setAddressPicUrl(String addressPicUrl) {
        this.addressPicUrl = addressPicUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

	public java.lang.String getShoppAddress() {
		return shoppAddress;
	}

	public void setShoppAddress(java.lang.String shoppAddress) {
		this.shoppAddress = shoppAddress;
	}

	public java.lang.String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(java.lang.String coordinates) {
		this.coordinates = coordinates;
	}

	public java.lang.String getPoint() {
		return point;
	}

	public void setPoint(java.lang.String point) {
		this.point = point;
	}
    
    
}
