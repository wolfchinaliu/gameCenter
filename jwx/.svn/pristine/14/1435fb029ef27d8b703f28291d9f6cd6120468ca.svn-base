package weixin.guanjia.account.util;

import org.apache.commons.lang.StringUtils;

public class CheckPic {
	/**
	 * 验证文件是否为图片
	 */
	public static boolean checkImg(String path){
		boolean result;
		if(StringUtils.isBlank(path)){
		    return false;
		}
		 if(path.contains(".")){
			 path = path.substring(path.lastIndexOf(".")+1);
			 switch(path){
					 case "bmp":
						 result = true;
						 break;
					 case "dib":
						 result = true;
						 break;
					 case "gif":
						 result = true;
						 break;
					 case "jfif":
						 result = true;
						 break;
					 case "jpe":
						 result = true;
						 break;
					 case "jpeg":
						 result = true;
						 break;
					 case "jpg":
						 result = true;
						 break;
					 case "tif":
						 result = true;
						 break;
					 case "png":
						 result = true;
						 break;
					 case "tiff":
						 result = true;
						 break;
					 case "ico":
						 result = true;
						 break;
					 default:
						 result =false;
						 break;
			 }
			 return result;
		 }else{
			 return false;
		 }
	}
	public static void main(String[] args) {
		String path ="12346.png";
		System.out.println(checkImg(path));
	}

}
