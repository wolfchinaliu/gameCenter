package weixin.liuliangbao.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by yiweibao on 2016/1/3.
 * 该方法只能压缩子集中没有目录的文件
 */
public class ZipUtil {
    //压缩文件
    public static byte[] zip(String fromPath, String zipFile, List<String> pictureName)throws Exception{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(out);
        int len = 0;
        InputStream is=null;
        for(int i=0;i<pictureName.size();i++) {
            zos.setComment("zip测试for单个文件");
            ZipEntry entry = new ZipEntry( pictureName.get(i));
            zos.putNextEntry(entry);
            is= new FileInputStream(fromPath+"/" + pictureName.get(i));
            while ((len = is.read()) != -1)
                zos.write(len);
        }
        zos.flush();
        if (null != is) {
            is.close();
        }
        zos.close();
        return out.toByteArray();
    }
}
