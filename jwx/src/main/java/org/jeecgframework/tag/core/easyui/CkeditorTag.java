package org.jeecgframework.tag.core.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;

/**
 * 
 * @author
 *
 */
public class CkeditorTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	protected String name;// 属性名称
	protected String value;// 默认值
	protected boolean isfinder;// 是否加载ckfinder(默认true)
	protected String type;// 其它属性(用法:height:400,uiColor:'#9AB8F3' 用,分割)

	public boolean isIsfinder() {
		return isfinder;
	}

	public void setIsfinder(boolean isfinder) {
		this.isfinder = isfinder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public StringBuffer end() {
		StringBuffer sb = new StringBuffer();

		sb.append("<textarea id=\"" + name + "_text\" name=\"" + name + "\">"
				+ value + "</textarea>");
		sb.append("<script type=\"text/javascript\">var ckeditor_" + name
				+ "=CKEDITOR.replace(\"" + name + "_text\",{");
		if (isfinder) {
			sb.append("filebrowserBrowseUrl:"
					+ ResourceUtil.getFilebrowserBrowseUrl() + ",");
			sb.append("filebrowserImageBrowseUrl:"
					+ ResourceUtil.getFilebrowserImageBrowseUrl() + ",");
			sb.append("filebrowserFlashBrowseUrl:"
					+ ResourceUtil.getFilebrowserFlashBrowseUrl() + ",");
			sb.append("filebrowserUploadUrl:"
					+ ResourceUtil.getFilebrowserUploadUrl() + ",");
			sb.append("filebrowserImageUploadUrl:"
					+ ResourceUtil.getFilebrowserImageUploadUrl() + ",");
			sb.append("filebrowserFlashUploadUrl:"
					+ ResourceUtil.getFilebrowserFlashUploadUrl() + "");
		}
		if (isfinder && StringUtil.isNotEmpty(type))
			sb.append(",");
		if (StringUtil.isNotEmpty(type))
			sb.append(type);
		sb.append("});");
		if (isfinder) {
			sb.append("CKFinder.SetupCKEditor(ckeditor_" + name + ");");
		}
		sb.append("</script>");
		return sb;
	}
}
