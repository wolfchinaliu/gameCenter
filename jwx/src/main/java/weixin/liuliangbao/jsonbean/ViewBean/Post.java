package weixin.liuliangbao.jsonbean.ViewBean;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*import net.sf.json.JSONArray;
import net.sf.json.JSONObject;*/

/**
 * Created by aa on 2015/11/26.
 */
public  class Post  implements java.io.Serializable {

    public  String getJson(HttpServletRequest request ){
//        HttpServletResponse response= getResponse();
//        request.setContentType("text/json");
//        request.setCharacterEncoding("UTF-8");

        //post 方式
        StringBuilder stringBuilder = new StringBuilder();
        String str=null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            str=stringBuilder.toString();
            // LOGGER.info(stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }


}
