package weixin.liuliangbao.weigatedoor.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**
 * @Title: Entity
 * @Description: 门户的幻灯片管理实体
 * @author xudan
 * @date 2015-12-09 19:07:14
 * @version V1.0
 *
 */
@Entity
@Table(name = "weidoor_ppt", schema = "")
@SuppressWarnings("serial")
public class WeidoorpptEntity implements java.io.Serializable {
    /**主键，幻灯片ID*/
    private java.lang.String id;
    /**幻灯片标题*/
    private java.lang.String title;
    /**图片名称*/
    private java.lang.String pictureName;
    /**图片URl*/
    private java.lang.String pictureUrl;
    /**跳转类型*/
    private java.lang.String jumpType;
    /**跳转URL*/
    private java.lang.String jumpUrl;
    /**操作人*/
    private java.lang.String operator;
    /**操作时间*/
    private java.util.Date operatetime;
    /**所属公众号*/
    private java.lang.String accountid;
    /**描述*/
    private java.lang.String description;
    /**描述*/
    private java.lang.String pageUrl;
    /**页面所在位置  门户或者  更多查询流量的位置*/
    private java.lang.String pageLocation;

    private java.lang.String pageContent;

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

    @Column(name ="TITLE",nullable=true,length=30)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name ="PICTURENAME",nullable=true,length=30)
    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    @Column(name ="PICTUREURL",nullable=true,length=100)
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Column(name ="JUMPURL",nullable=true)
    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    @Column(name ="JUMPTYPE",nullable=true,length=30)
    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    @Column(name ="OPERATOR",nullable=true,length=10)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(name ="OPERATETIME",nullable=true)
    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  所属公众号
     */
    @Column(name ="ACCOUNTID",nullable=true,length=100)
    public java.lang.String getAccountid(){
        return this.accountid;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  所属公众号
     */
    public void setAccountid(java.lang.String accountid){
        this.accountid = accountid;
    }

    @Column(name ="DESCRIPTION",nullable=true,length=300)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name ="PAGELOCATION",nullable=true,length=20)
    public String getPageLocation() {
        return pageLocation;
    }

    public void setPageLocation(String pageLocation) {
        this.pageLocation = pageLocation;
    }

    @Column(name ="PAGEURL",nullable=true)
    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @Column(name ="PAGECONTENT",nullable=true)
    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }
}
