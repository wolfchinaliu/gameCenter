package weixin.liuliangbao.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by aa on 2015/12/21.
 */
public class EnDeCode {
    public static final transient Logger LOGGER = Logger.getLogger(EnDeCode.class);
    /**
     *
     */
    public EnDeCode() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        EnDeCode t = new EnDeCode();
//    定义一个list集合
//        List<testEntity> list = new ArrayList<testEntity>();
//        for (int i = 1; i < 4; i++) {
//            testEntity e = new testEntity();
//            e.setId("1" + i);
//            e.setName("幸福");
//            list.add(e);
//        }


//        for (int i = 0; i < list.size(); i++) {
//            String str = "http://xiaona.ngrok.natapp.cn/jfinal/province/Allprovince";
//
////            + "&id=" + list.get(i).getId()
//
//
//            String path = "D:/Qr_pics/" + list.get(i).getId() + ".png";
//            t.encode(str, path);
//            t.decode(path);
//
//        }

//
//    //        可以手动输入要编码的内容
//    Scanner in = new Scanner(System.in);
//    LOGGER.info("编码内容：");
//    String str = in.next();
//    //            String str = "http://www.baidu.com";
//    //        可以手动输入要编码的内容
////        现在改为代码中定义的
//    String path = "D:/Qr_pics/test7.png";
//    t.encode(str, path);
//    t.decode(path);
    }

    /*
     * 编码：
     * 1 将内容转换成二维矩阵
     * 2 将该二维矩阵转换成图片
     * */
    public static void encode(String str, String path) {
        try {
            BitMatrix byteMatrix;
            byteMatrix = new MultiFormatWriter().encode(new String(str.getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, 800, 800); //将文字转换成二维矩阵，并设置矩阵大小，这里的矩阵大小就是后面生成的图片像素大小
            File file = new File(path);//新建矩阵文件
            MatrixToImageWriter.writeToFile(byteMatrix, "gif", file);//将矩阵文件转换成图片文件
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 解码：
     * 1 将图片反解码为二维矩阵
     * 2 将该二维矩阵解码为内容
     * */
    public static void decode(String imgPath) {
        try {
            Reader reader = new MultiFormatReader();
//                String imgPath = "D:/Qr_pics/test7.png";//获取即将被解码图片的路径
            File file = new File(imgPath);//获取该图片文件
            BufferedImage image;
            try {
                image = ImageIO.read(file);
                if (image == null) {
                    LOGGER.info("Could not decode image");
                }
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result;
                Hashtable hints = new Hashtable();//将图片反解码为二维矩阵
                hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
                result = new MultiFormatReader().decode(bitmap, hints);//将该二维矩阵解码成内容
                String resultStr = result.getText();
                LOGGER.info("\n解码结果：\n" + resultStr);

            } catch (IOException ioe) {
                LOGGER.info(ioe.toString());
            } catch (ReaderException re) {
                LOGGER.info(re.toString());
            }

        } catch (Exception ex) {
            LOGGER.info(ex.toString());
        }
    }
}
