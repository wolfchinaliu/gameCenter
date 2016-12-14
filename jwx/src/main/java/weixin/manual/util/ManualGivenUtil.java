package weixin.manual.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jeecgframework.core.util.ResourceUtil;

/**
 * @author gaoqingjia
 * 2016年9月5日
 */
public class ManualGivenUtil {
	
    public static void main(String[] args) throws FileNotFoundException, IOException {

       
	}
    
    public static void write(String path, String content, String encoding)  
            throws IOException {  
        File file = new File(path);  
        file.delete();  
        file.createNewFile();  
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(  
                new FileOutputStream(file), encoding));  
        writer.write(content);  
        writer.close();  
    }  
  
    public static String read(String path, String encoding) throws IOException {  
        String content = "";  
        File file = new File(path);  
        BufferedReader reader = new BufferedReader(new InputStreamReader(  
                new FileInputStream(file), encoding));  
        String line = null;  
        while ((line = reader.readLine()) != null) {  
            content += line + "\n";  
        }  
        reader.close();  
        return content;  
    }  
	
	/*读取文件内容*/
	public static String[] readFromFile(String fromFileUrl) throws FileNotFoundException, IOException{
		File file = new File(fromFileUrl);// 指定要读取的文件  
        FileReader reader = new FileReader(file);// 获取该文件的输入流  
        char[] bb = new char[1024];// 用来保存每次读取到的字符  
        String str = "";// 用来将每次读取到的字符拼接，当然使用StringBuffer类更好  
        int n;// 每次读取到的字符长度  
        while ((n = reader.read(bb)) != -1) {  
            str += new String(bb, 0, n);  
        }  
        reader.close();// 关闭输入流，释放连接  
        if(str.contains("，")){
        	 str = str.replace("，", "\r\n");
        }
        if(str.contains(",")){
           str = str.replace(",", "\r\n");
        }
        String[]   phone =str.split("\r\n");
        return phone;	
	}
  /*写入文件内容*/
  public static String writeToFile(String writerContent) throws IOException {  
 		String filename = System.currentTimeMillis()+".txt";
        File file = new File(ResourceUtil.getMediaTxtPrefix()+"/"+filename);// 要写入的文本文件  
        System.out.println(file);
        //判断目标文件所在的目录是否存在  
        if(!file.getParentFile().exists()) {  
            //如果目标文件所在的目录不存在，则创建父目录  
            System.out.println("目标文件所在目录不存在，准备创建它！");  
            if(!file.getParentFile().mkdirs()) {  
                System.out.println("创建目标文件所在目录失败！");  
                return "创建目标文件所在目录失败！";  
            }  
        }  
        if (!file.exists()) {// 如果文件不存在，则创建该文件  
            file.createNewFile();  
        }  
        FileWriter writer = new FileWriter(file);// 获取该文件的输出流  
        writer.write(writerContent);// 写内容  
        writer.flush();// 清空缓冲区，立即将输出流里的内容写到文件里  
        writer.close();// 关闭输出流，施放资源  
        return filename;
    }  
  /*读取图片*/
  public static void readPic() throws FileNotFoundException,IOException{
		 FileInputStream in = new FileInputStream(new File("F:\\test.jpg"));// 指定要读取的图片  
	        File file = new File("E:\\test.jpg");  
	        if (!file.exists()) {// 如果文件不存在，则创建该文件  
	            file.createNewFile();  
	        }  
	        FileOutputStream out = new FileOutputStream(new File("E:\\test.jpg"));// 指定要写入的图片  
	        int n = 0;// 每次读取的字节长度  
	        byte[] bb = new byte[1024];// 存储每次读取的内容  
	        while ((n = in.read(bb)) != -1) {  
	            out.write(bb, 0, n);// 将读取的内容，写入到输出流当中  
	        }  
	        out.close();// 关闭输入输出流  
	        in.close(); 
	}
	
  /*生成批次好*/
  public static String makePatchNo(){
		String patchno = Long.toString(new Date().getTime());
		Random rd =new Random();
		for(int i=0;i<=3;i++){
			patchno += rd.nextInt(10);
		}
		return patchno;
	}
  
	/*验证手机号匹配正则表达式*/
   public static boolean regixPhone(String phone){
			Pattern p = Pattern.compile("^(((106)|(13[0-9]{1})|(14[5,7,9]{1})|(15[0-9]{1})|(17[0,6-8]){1}|(18[0-9]{1}))+\\d{8})$");
			Matcher m = p.matcher(phone);  
			return m.matches();
   }
   
 //工具方法:将文件内容,写到指定的全路径文件名之中  fileName:完整的路径和文件名,如:/Users/zhixu/Desktop/123.jpg
   public static File writeData2File(String fileName, byte[] by) {
       FileOutputStream fileout = null;
       File file = new File(fileName);
       //如果存在,则删除
       if (file.exists()) {
           file.delete();
       }
       try {
           fileout = new FileOutputStream(file);
           fileout.write(by, 0, by.length);
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           try {
               fileout.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       return file;
   }
   
   /*将从文本框得到的内容进行处理*/
   public static String[] getphone(String phoneno){
	   
	   String[] phone = null;
	  if(phoneno.contains( "，" )){ //判断是否包含中文“，”,包含的话,把中文"，"换成","
		   phoneno = phoneno.replaceAll("，", ",");
		   phone = phoneno.split(",");
	   }else if(phoneno.contains("\r\n")){ //包含换行符
		   phone = phoneno.split("\r\n");
	   }else if(phoneno.contains( "," )){
		   phone = phoneno.split(",");
	   }else{
		   phone = new String[]{phoneno};
	   }	   
	   for(int i =0;i<phone.length;i++){//去空白
		   phone[i] = phone[i].trim();
	   }
	   return phone;
   }
   
   /*按照空格切割字符串*/
   public static String[] cutSpace(String phoneno){
	   String [] phone = phoneno.split("\\s+");
	   return phone;
   }
   
   /*将文本中的逗号换成换行符*/
   public static String ruleProcess(String s){
	   if(s.contains( "，" )){ //判断是否包含中文“，”,包含的话,把中文"，"换成","
		   s = s.replaceAll("，", ",");
	   }
	   return  s.replaceAll(",", "\r\n");
   }


}
