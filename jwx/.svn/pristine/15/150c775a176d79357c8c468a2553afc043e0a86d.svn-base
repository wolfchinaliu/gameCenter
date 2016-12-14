package weixin.liuliangbao.service.flowcontroller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.liuliangbao.jsonbean.ShareItem;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.HttpUtil;
import weixin.util.DateUtils;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.MessageFormat;

import static org.jeewx.api.wxuser.thread.LoadMemberInfoTask.LOGGER;

/**
 * Created by guoliang on 2016-7-08 15:10:41
 */
@Service("shareService")
@Transactional
public class ShareServiceImpl extends CommonServiceImpl implements ShareService {

    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((ShareItem) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((ShareItem) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((ShareItem) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(ShareItem t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(ShareItem t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(ShareItem t) {
        return true;
    }

    @Override
    public void handleShareBindRecords() {
        String url = ResourceUtil.getConfigByName("jfinalUrl-config") + "share/handleShareBindRecords";
        Gson gson = new Gson();
        JSONObject resultObj = HttpUtil.httpPost(url, new JSONObject(), false);
        String strFlow = gson.toJson(resultObj);
        Type type = new TypeToken<Update>() { }.getType();
        Update update = gson.fromJson(strFlow, type);
        if (null != update) {
            LOGGER.debug(MessageFormat.format("code={0}, message={1}", update.getCode(), update.getMessage()));
        }
    }
}
