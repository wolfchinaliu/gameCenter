package weixin.guanjia.core.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageInputStream;

import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 缩略图压缩上传处理器
 * @author Administrator
 *
 */
public class CompressUtil {

	/**
	 * 上传缩略图
	 * @param uploadFile
	 * @param width 图片长
	 * @param height 图片高
	 * @return 图片上传至服务器的路径
	 * @throws UnsupportedEncodingException
	 */
	public static String uploadThumb(UploadFile uploadFile, double width, double height) throws UnsupportedEncodingException{
		
		String uploadImgPath = "";
		
		uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
		MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();// 获取上传文件对象
			String fileName = mf.getOriginalFilename();// 获取文件名
			
			try {
				
				BufferedImage newImage=getNewImage(mf, width, height);
				
				String path = ResourceUtil.getConfigByName("uploadpath") + "/";// 文件保存在硬盘的相对路径
//				String realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/") + "/" + path + uploadFile.getCusPath() + "/";// 文件的硬盘真实路径
				String realPath =  ResourceUtil.getConfigByName("media.path.prefix")+"/"+ path + uploadFile.getCusPath() + "/";// 文件的硬盘真实路径

				String noextfilename=DataUtils.getDataString(DataUtils.yyyymmddhhmmss)+StringUtil.random(8);//自定义文件名称
				String extend = FileUtils.getExtend(fileName);// 获取文件扩展名
				
				File destFile = new File(realPath + noextfilename +"."+ extend);
				File parentPath = new File(realPath);
				if(!parentPath.exists()){
				    parentPath.mkdirs();
				}
				ImageIO.write(newImage, extend, destFile);  
				
				uploadImgPath = path + uploadFile.getCusPath() + "/" + noextfilename +"."+ extend;
			} catch (IOException e) {
				
				e.printStackTrace();
			}  
		}
		
		return uploadImgPath;
	}
	
	/**
	 * 绘制缩略图
	 * @param oldImage 原图
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage  getNewImage(MultipartFile oldImage,double width,double height) throws IOException{  
        /*srcURl 原图地址；deskURL 缩略图地址；comBase 压缩基数；scale 压缩限制(宽/高)比例*/  
          
        ByteArrayInputStream bais = new ByteArrayInputStream(oldImage.getBytes());   
        MemoryCacheImageInputStream mciis = new MemoryCacheImageInputStream(bais);        
        Image src = ImageIO.read(mciis);  
        double srcHeight = src.getHeight(null);  
        double srcWidth = src.getWidth(null);  
        double deskHeight = 0;//缩略图高   
        double deskWidth  = 0;//缩略图宽   
        if (srcWidth>srcHeight) {  
              
            if (srcWidth>width) {  
                if (width/height>srcWidth/srcHeight) {  
                    deskHeight = height;  
                    deskWidth = srcWidth/(srcHeight/height);  
                }  
                else {  
                    deskHeight = width/(srcWidth/srcHeight);  
                    deskWidth = width;  
                }  
            }  
            else {  
                  
                if (srcHeight>height) {  
                    deskHeight = height;  
                    deskWidth = srcWidth/(srcHeight/height);  
                }else {  
                    deskHeight=srcHeight;  
                    deskWidth=srcWidth;  
                }  
                  
            }  
              
        }  
        else if(srcHeight>srcWidth)  
        {  
            if (srcHeight>(height)) {  
                if ((height)/width>srcHeight/srcWidth) {  
                    deskHeight = srcHeight/(srcWidth/width);  
                    deskWidth = width;  
                }else {  
                    deskHeight = height;  
                    deskWidth = (height)/(srcHeight/srcWidth);  
                }  
            }  
            else {  
                if (srcWidth>width) {  
                    deskHeight = srcHeight/(srcWidth/width);  
                    deskWidth = width;  
                }else {  
                    deskHeight=srcHeight;  
                    deskWidth=srcWidth;  
                }  
  
            }  
              
        }  
        else if (srcWidth==srcHeight) {  
              
            if (width>=(height)&&srcHeight>(height)) {  
                deskWidth=(height);  
                deskHeight=(height);  
            }  
            else if (width<=(height)&&srcWidth>width) {  
                deskWidth=width;  
                deskHeight=width;  
            }  
            else  if (width==(height)&&srcWidth<width) {  
                deskWidth=srcWidth;  
                deskHeight=srcHeight;  
            }  
            else {  
                deskHeight=srcHeight;  
                deskWidth=srcWidth;  
            }  
  
        }  
        BufferedImage tag = new BufferedImage((int)deskWidth,(int)deskHeight,  
            BufferedImage.TYPE_3BYTE_BGR);  
        tag.getGraphics().drawImage(src, 0, 0, (int)deskWidth, (int)deskHeight, null); //绘制缩小后的图   
        return tag;  
	}
}
