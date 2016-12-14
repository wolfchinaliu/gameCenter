package weixin.liuliangbao.jsonbean;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.poi.excel.annotation.Excel;


import weixin.util.DataDictionaryUtil.Level;

public class ShareRecordBean {
	
	@Excel(exportName = "日期")
	private String Createtime;
	@Excel(exportName = "商户名称",exportFieldWidth = 20)
    private String acctForName;
	@Excel(exportName = "商户等级",exportConvertSign = 1)
	 private String level;
	@Excel(exportName = "所属商户")
    private String belogAcct;
	@Excel(exportName = "软文（标题）",exportFieldWidth = 20)
	private String title;
	@Excel(exportName = "分享转发（次数）")
	private String shareNumber;
	@Excel(exportName = "阅读量")
	private String readNumber;
	
	public String convertGetLevel() {
		if (StringUtils.equals(Level.levela.getCode(), level)) {
			return Level.levela.getName();
		}
		if (StringUtils.equals(Level.levelb.getCode(), level)) {
			return Level.levelb.getName();
		}
		if (StringUtils.equals(Level.levelc.getCode(), level)) {
			return Level.levelc.getName();
		}
		return level;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getShareNumber() {
		return shareNumber;
	}
	public void setShareNumber(String shareNumber) {
		this.shareNumber = shareNumber;
	}
	
	public String getReadNumber() {
		return readNumber;
	}
	public void setReadNumber(String readNumber) {
		this.readNumber = readNumber;
	}
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}

	public String getBelogAcct() {
		return belogAcct;
	}
	public void setBelogAcct(String belogAcct) {
		this.belogAcct = belogAcct;
	}

	public String getAcctForName() {
		return acctForName;
	}
	public void setAcctForName(String acctForName) {
		this.acctForName = acctForName;
	}
	
	public String getCreatetime() {
		return Createtime;
	}
	public void setCreatetime(String createtime) {
		Createtime = createtime;
	}
}
