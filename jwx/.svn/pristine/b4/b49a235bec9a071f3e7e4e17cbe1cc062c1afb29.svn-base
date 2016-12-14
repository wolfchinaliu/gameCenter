package weixin.liuliangbao.service.flowcontroller;

import org.jeecgframework.core.common.service.CommonService;
import weixin.liuliangbao.jsonbean.ShareItem;

/**
 * Created by aa on 2015/12/14.
 */
public interface ShareAccessService extends CommonService {

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

    /**
     * 添加分享页面访问记录
     *
     * @param sharerOpenId    分享人的openId
     * @param viewerOpenId    查看人的openId
     * @param accountId       商家的Id,不是石榴商户的Id
     * @param shareId         分享文章Id
     */
    void addShareAccess(String sharerOpenId, String viewerOpenId, String accountId, String shareId);
}
