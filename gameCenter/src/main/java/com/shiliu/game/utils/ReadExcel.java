package com.shiliu.game.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.shiliu.game.domain.ExcelUser;
/**
 * @author wkr
 * @Date 2016-11-18
 * 工具类读取Excel类中内容
 */
public class ReadExcel {
	//log4j输出
	private Logger logger = Logger.getLogger(this.getClass());
    //总行数
    private int totalRows = 0;  
    //总列数
    private int totalCells = 0; 
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;} 
    //获取总列数
    public int getTotalCells() {  return totalCells;} 
    //获取错误信息-暂时未用到暂时留着
    public String getErrorInfo() { return errorMsg; }
    
  /**
   * 读EXCEL文件，获取客户信息集合
   * @param fielName
   * @return
   */
  public List<ExcelUser> getExcelInfo(String fileName,MultipartFile Mfile,String gameId){
	  //读取properties文件
	  InputStream inputStream=null;
	  Properties properties=null;
	  String filePath=null;//读取出的文件路径
	  try {
		  inputStream= new FileInputStream(this.getClass().getClassLoader()  
                  .getResource("/config.properties").getPath());
		  properties=new Properties();
		  properties.load(inputStream);
		  filePath = properties.getProperty("file_path");
		} catch (FileNotFoundException e1) {
			logger.error("未找到properties文件", e1);
		} catch (IOException e1) {
			logger.error("打开文件流异常", e1);
		} finally{
			//关闭流
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("关闭文件流异常", e);
				}
			}
		}
      
        //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
       CommonsMultipartFile cf= (CommonsMultipartFile)Mfile; //获取本地存储路径
       
       //根据文件名判断文件是2003版本还是2007版本
       String isExcelVersion = ".xls"; 
       if(WDWUtil.isExcel2007(fileName)){
    	   isExcelVersion = ".xlsx";  
       }
       
       File file = new  File(filePath);
       //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
       if (!file.exists()) file.mkdirs();
       //新建一个文件
       File file1 = new File(filePath+"\\" + LongIdWorker.getDataId() + isExcelVersion); 
       //将上传的文件写入新建的文件中
       try {
           cf.getFileItem().write(file1);
       } catch (Exception e) {
    	   logger.error("文件写入异常", e);
       }
       
       //初始化客户信息的集合    
       List<ExcelUser> customerList=new ArrayList<ExcelUser>();
       //初始化输入流
       FileInputStream is = null;
       try{
          //根据新建的文件实例化输入流
          is = new FileInputStream(file1);
          //根据excel里面的内容读取客户信息
          
          //读取Excel里面客户的信息
          customerList = getExcelInfo(is,isExcelVersion,gameId);
          is.close();
      }catch(Exception e){
          e.printStackTrace();
      } finally{
          if(is !=null)
          {
              try{
                  is.close();
              }catch(IOException e){
                  is = null;    
                  logger.error("IO流异常", e);
              }
          }
      }
      return customerList;
  }
  /**
   * 根据excel里面的内容读取客户信息
   * @param is 输入流
   * @param isExcel2003 excel是2003还是2007版本
   * @return
   * @throws IOException
   */
  public  List<ExcelUser> getExcelInfo(InputStream is,String isExcelVersion,String gameId){
       List<ExcelUser> customerList=null;
       try{
           /** 根据版本选择创建Workbook的方式 */
           Workbook wb = null;
           //当excel是2003时
           if(isExcelVersion.equals(".xls")){
               wb = new HSSFWorkbook(is); 
           }
           else{//当excel是2007时
               wb = new XSSFWorkbook(is); 
           }
           //读取Excel里面客户的信息
           customerList=readExcelValue(wb,gameId);
       }
       catch (IOException e)  {  
    	   logger.error("IO流异常", e);
       }  
       return customerList;
  }
  /**
   * 读取Excel里面客户的信息
   * @param wb
   * @return
   */
  private List<ExcelUser> readExcelValue(Workbook wb,String gameId){ 
      //得到第一个shell  
       Sheet sheet=wb.getSheetAt(0);
       
      //得到Excel的行数
       this.totalRows=sheet.getPhysicalNumberOfRows();
       
      //得到Excel的列数(前提是有行数)
       if(totalRows>=1 && sheet.getRow(0) != null){//判断行数大于一，并且第一行必须有标题（这里有bug若文件第一行没值就完了）
            this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
       }else{
    	   return null;
       }
       
       List<ExcelUser> customerList=new ArrayList<ExcelUser>();//声明一个对象集合
       ExcelUser customer;//声明一个对象
       
      //循环Excel行数,从第二行开始。标题不入库
       for(int r=1;r<totalRows;r++){
           Row row = sheet.getRow(r);
           if (row == null) continue;
           customer = new ExcelUser();
           
           //循环Excel的列
           for(int c = 0; c <this.totalCells; c++){ 
               	Cell cell = row.getCell(c);
               if (null != cell){ //判断第一行不等于空
                   if(c==0){
                       customer.setLab1(getValue(cell));//得到行中第一个值
                   }else if(c==1){
                       customer.setLab2(getValue(cell));//得到行中第二个值
                   }
               }
           }
           //添加gameId
           customer.setGameId(gameId);
           //添加对象到集合中
           customerList.add(customer);
       }
       return customerList;
  }
  
  /**
   * 得到Excel表中的值
   * 
   * @param cell
   *            Excel中的每一个格子
   * @return Excel中每一个格子中的值
   */
  @SuppressWarnings({ "static-access"})
  private String getValue(Cell cell) {
      if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
          // 返回布尔类型的值
          return String.valueOf(cell.getBooleanCellValue());
      } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
          // 返回数值类型的值
    	  Object inputValue = null;// 单元格值  
    	  double doubleVal = cell.getNumericCellValue();
    	  long longVal = Math.round(doubleVal);
    	    if(Double.parseDouble(longVal + ".0") == doubleVal)  
    	        inputValue = longVal;  
    	    else  
    	        inputValue = doubleVal;
    	    
          return inputValue.toString();
      } else {
          // 返回字符串类型的值
          return String.valueOf(cell.getStringCellValue());
      }
  }

}
