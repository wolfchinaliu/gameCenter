package weixin.liuliangbao.flowcard.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aa on 2015/12/18.
 */
@Entity
@Table(name = "FlowCardDeliveryInfo", schema = "")
@SuppressWarnings("serial")
public class FlowCardDeliveryInfoEntity implements java.io.Serializable {

    private java.lang.String id;           //  varchar(36)                    not null,
    private java.lang.String fromAcctId;       //   varchar(36)                    null,
    private java.lang.String toAcctId;      //   varchar(36)                    null,
    private java.lang.String flowValue;      //  decimal(12,1)                  null,
    private java.util.Date tradingDate;       //  date                           null,
    private java.lang.String batchNo;       //   varchar(36)                    null,
    private java.lang.String cardbegin;        // varchar(10)                    null,
    private java.lang.String cardend;        //    varchar(10)                    null,

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

    @Column(name = "FROMACCTID", nullable = false, length = 36)
    public String getFromAcctId() {
        return fromAcctId;
    }

    public void setFromAcctId(String fromAcctId) {
        this.fromAcctId = fromAcctId;
    }

    @Column(name = "TOACCTID", nullable = false, length = 36)
    public String getToAcctId() {
        return toAcctId;
    }

    public void setToAcctId(String toAcctId) {
        this.toAcctId = toAcctId;
    }

    @Column(name = "FLOWVALUE", nullable = false, length = 36)
    public String getFlowValue() {
        return flowValue;
    }

    public void setFlowValue(String flowValue) {
        this.flowValue = flowValue;
    }

    @Column(name = "TRADINGDATE", nullable = false, length = 12)
    public Date getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(Date tradingDate) {
        this.tradingDate = tradingDate;
    }

    public String getBatchNo() {
        return batchNo;
    }

    @Column(name = "BATCHNO", nullable = false, length = 20)
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * 交易卡号起点
     *
     * @return
     */
    @Column(name = "CARDBEGIN", nullable = false, length = 10)
    public String getCardbegin() {
        return cardbegin;
    }

    public void setCardbegin(String cardbegin) {
        this.cardbegin = cardbegin;
    }

    @Column(name = "CARDEND", nullable = false, length = 10)
    public String getCardend() {
        return cardend;
    }

    public void setCardend(String cardend) {
        this.cardend = cardend;
    }
}
