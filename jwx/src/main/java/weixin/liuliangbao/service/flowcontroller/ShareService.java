package weixin.liuliangbao.service.flowcontroller;

import org.jeecgframework.core.common.service.CommonService;
import weixin.liuliangbao.jsonbean.ShareItem;

/**
 * Created by aa on 2015/12/14.
 */
public interface ShareService extends CommonService {

    /**
     * 默认按钮-sql增强-新增操作
     *
     */
    public boolean doAddSql(ShareItem t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     */
    public boolean doUpdateSql(ShareItem t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     */
    public boolean doDelSql(ShareItem t);

    public void handleShareBindRecords();

}
