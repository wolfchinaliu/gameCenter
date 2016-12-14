package weixin.report.model;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @author parallel_line
 * @version 2016年9月27日 上午11:44:01
 */

public class SubMerchantCharge extends MerchantCharge {
    private static final long serialVersionUID = -5836498880605247315L;

    @Excel(exportName = "所属商户",orderNum="11")
    private java.lang.String belogAcct;

    public java.lang.String getBelogAcct() {
        return belogAcct;
    }

    public void setBelogAcct(java.lang.String belogAcct) {
        this.belogAcct = belogAcct;
    }
}
