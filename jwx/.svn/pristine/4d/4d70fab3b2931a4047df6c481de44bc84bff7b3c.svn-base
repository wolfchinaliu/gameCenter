package weixin.liuliangbao.jsonbean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2016/1/21.
 */
@Entity
@Table(name = "weixin_main", schema = "")
@SuppressWarnings("serial")
public class WeixinMainEntity implements java.io.Serializable {
    private String id;
    private String title;
    private String imagepath;
    private Date createDate;
    private Date operateDate;
    private String accountid;
    private String content;
    private String jumptype;
    private String pagetype;
    private String pageurl;
    private String shareId;
    private String shareTitle;
    private String isShare;

    @Column(name ="isShare",nullable=false,length=10)
    public String getIsShare() {
        return isShare;
    }

    public void setIsShare(String isShare) {
        this.isShare = isShare;
    }

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

    @Column(name ="title",nullable=false,length=255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name ="imagepath",nullable=false,length=255)
    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
    @Column(name ="createDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Column(name ="operateDate")
    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }
    @Column(name ="accountid")
    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }
    @Column(name ="content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Column(name ="jumptype")
    public String getJumptype() {
        return jumptype;
    }

    public void setJumptype(String jumptype) {
        this.jumptype = jumptype;
    }
    @Column(name ="pagetype")
    public String getPagetype() {
        return pagetype;
    }

    public void setPagetype(String pagetype) {
        this.pagetype = pagetype;
    }
    @Column(name ="pageurl")
    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }
    @Column(name ="shareId")
    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }
    @Column(name ="shareTitle")
    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }
}
