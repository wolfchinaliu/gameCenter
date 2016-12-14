package weixin.liuliangbao.business.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Title: Entity
 * @Description: 运营商接口信息
 * @author xudan
 * @date 2015-11-30 09:47:14
 * @version V1.0
 *
 */
@Entity
@Table(name = "BusinessInterface")
@SuppressWarnings("serial")
public class BusinessInterfaceEntity implements java.io.Serializable {


    /**主键，运营商接口ID*/
    private java.lang.String id;
    /**省名称*/
    private java.lang.String provinceName;
    /**运营商名称*/
    private java.lang.String businessName;
    /**接口名称*/
    private java.lang.String InterfaceName;
    /**接口描述*/
    private java.lang.String description;
    /**省Code*/
    private java.lang.String provinceCode;
    /**接口状态*/
    private java.lang.String state;
    /**是否默认*/
    private Integer isDefault;
    /***/
    private java.lang.String dispose;
    /**运营商Code*/
    private java.lang.String businessCode;


    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  主键
     */

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="ID",nullable=false,length=36)
    public java.lang.String getId(){
        return this.id;
    }

    public void setId(java.lang.String id){
        this.id = id;
    }



    @Column(name ="provinceName",nullable=true,length=36)
    public java.lang.String getProvinceName(){
        return this.provinceName;
    }

    public void setProvinceName(java.lang.String provinceName){
        this.provinceName = provinceName;
    }



    @Column(name ="businessName",nullable=true,length=36)
    public java.lang.String getBusinessName(){
        return this.businessName;
    }

    public void setBusinessName(java.lang.String businessName){
        this.businessName = businessName;
    }




    @Column(name ="INTERFACENAME",nullable=true,length=10)
    public java.lang.String getInterfaceName(){
        return this.InterfaceName;
    }

    public void setInterfaceName(java.lang.String InterfaceName){
        this.InterfaceName = InterfaceName;
    }


    @Column(name ="DESCRIPTION",nullable=true,length=30)
    public java.lang.String getDescription(){
        return this.description;
    }
    public void setDescription(java.lang.String description){
        this.description = description;
    }




    @Column(name ="PROVINCECODE",nullable=true,length=10)
    public java.lang.String getProvinceCode(){
        return this.provinceCode;
    }

    public void setProvinceCode(java.lang.String provinceCode){
        this.provinceCode = provinceCode;
    }




    @Column(name ="STATE",nullable=true,length=5)
    public java.lang.String getState(){
        return this.state;
    }

    public void setState(java.lang.String state){
        this.state = state;
    }






    @Column(name ="ISDEFAULT",nullable=true,length=1)
    public Integer getIsDefault(){
        return this.isDefault;
    }

    public void setIsDefault(Integer isDefault){
        this.isDefault = isDefault;
    }




    @Column(name ="DISPOSE",nullable=true,length=1)
    public java.lang.String getDispose(){
        return this.dispose;
    }

    public void setDispose(java.lang.String dispose){
        this.dispose = dispose;
    }






    @Column(name ="BUSINESSCODE",nullable=true,length=10)
    public java.lang.String getBusinessCode(){
        return this.businessCode;
    }

    public void setBusinessCode(java.lang.String businessCode){
        this.businessCode = businessCode;
    }

}