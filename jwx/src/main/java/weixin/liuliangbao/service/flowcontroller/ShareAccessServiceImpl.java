package weixin.liuliangbao.service.flowcontroller;

import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import sdk.jfinal.JFinalUtils;
import weixin.liuliangbao.jsonbean.MerchantInfoBean;
import weixin.liuliangbao.jsonbean.ShareItem;
import weixin.liuliangbao.jsonbean.WeixinShareAccess;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by guoliang on 2015/12/14.
 */
@Service("shareAccessService")
@Transactional
public class ShareAccessServiceImpl extends CommonServiceImpl implements ShareAccessService {
    public static final Logger LOGGER = Logger.getLogger(ShareAccessServiceImpl.class);
    public static final int NOT_BIND_PHONE_NUMBER = 0;
    // public static final int HAVE_GIVE_FLOW = 1;
    // public static final int MERCHAT_FLOW_NOT_ENOUGH = 2;
    // public static final int NOT_IN_MERCHAT_COVER_AREA = 3;
    // public static final int HAVE_GIVE_TO_ANOTHER_MEMBER = 4;
    public static final int HAVE_BIND_PHONE_NUMBER = 5;
    public static final int ACCESS_HIMSELF_SHARE_PAGE = 5;

    public static final String SHILIU_ACCOUNT_ID = ResourceUtil.getShiliuAccountId();
    private final WeixinMemberServiceI weixinMemberService;

    @Autowired
    public ShareAccessServiceImpl(WeixinMemberServiceI weixinMemberService) {
        Assert.notNull(weixinMemberService, "weixinMemberService为空, 请检查spring相关的配置是否完善");
        this.weixinMemberService = weixinMemberService;
    }

    /**
     * 默认按钮-sql增强-新增操作
     */
    public boolean doAddSql(ShareItem t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     */
    public boolean doUpdateSql(ShareItem t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     */
    public boolean doDelSql(ShareItem t) {
        return true;
    }

    /**
     * 状态值的定义:
     * 点击分享链接, 保存信息时,
     * 没绑手机号设置为0,
     * 已绑手机号设置为5
     * -- 已赠送流量为1
     * -- 商家流量不够的为2
     * -- 不在商家覆盖区域的为3
     * -- 当前查看人的分享流量已经赠送给他人, 状态为4,
     *
     * @param sharerOpenId 分享人的openId
     * @param viewerOpenId 查看人的openId
     * @param accountId    商家的Id,不是石榴商户的Id
     * @param shareId      分享文章Id
     */
    @Override
    public void addShareAccess(String sharerOpenId, String viewerOpenId, String accountId, String shareId) {
        if (StringUtils.isBlank(sharerOpenId)) {
            LOGGER.warn("分享人OpenId为空, 不记录访问信息");
            return;
        }
        if (StringUtils.isBlank(viewerOpenId)) {
            LOGGER.warn("查看人的OpenId为空, 不记录访问信息");
            return;
        }
        if (StringUtils.isBlank(accountId)) {
            LOGGER.warn("商家的Id为空, 不记录访问信息");
            return;
        }
        if (StringUtils.isBlank(shareId)) {
            LOGGER.warn("分享文章Id为空, 不记录访问信息");
            return;
        }

        synchronized (ShareAccessServiceImpl.class) {
            Integer status = NOT_BIND_PHONE_NUMBER;
            // 分享人openId不能为空, 分享人是accountId下的粉丝
            WeixinMemberEntity memberOfShiliu = this.weixinMemberService.getWeixinMember(viewerOpenId, SHILIU_ACCOUNT_ID);
            if (StringUtils.equals(viewerOpenId, sharerOpenId)) {
                status = ACCESS_HIMSELF_SHARE_PAGE;
            } else if (null == memberOfShiliu || StringUtils.isBlank(memberOfShiliu.getPhoneNumber())) {
                // 没绑手机号设置为0
                status = NOT_BIND_PHONE_NUMBER;
            } else {
                WeixinMemberEntity weixinMemberEntity = this.weixinMemberService.getWeixinMemberByShiliuOpenId(viewerOpenId, accountId);
                if (null != weixinMemberEntity && StringUtils.isNotBlank(weixinMemberEntity.getPhoneNumber())) {
                    status = HAVE_BIND_PHONE_NUMBER;
                }
            }

            // 查看人openId不能为空, 分享人是accountId下的粉丝
            // 查看人没有绑定手机号, 已绑定的状态为5, 没有绑定的为0
            // 查看人和分享相同, 设置为6
            // 不能有重复, 注意事务!

            String hql = "from WeixinShareAccess where sharerOpenId=:sharerOpenId and viewerOpenId=:viewerOpenId and  accountId=:accountId and shareId=:shareId";
            JSONObject param = new JSONObject().element("sharerOpenId", sharerOpenId).element("viewerOpenId", viewerOpenId).element("accountId", accountId).element("shareId", shareId);
            List<WeixinShareAccess> weixinShareAccessList = this.findHqlWithKeyParam(hql, param);
            WeixinShareAccess shareAccess;
            if (CollectionUtils.isEmpty(weixinShareAccessList)) {
                shareAccess = new WeixinShareAccess();
                shareAccess.setId(UUID.randomUUID().toString());
                shareAccess.setAccessTimes(1L);
                shareAccess.setSharerOpenId(sharerOpenId);
                shareAccess.setViewerOpenId(viewerOpenId);
                shareAccess.setAccountId(accountId);
                shareAccess.setShareId(shareId);
                shareAccess.setStatus(status);
                shareAccess.setCreateTime(new Date());
                try {
                    MerchantInfoBean shareBindRule = JFinalUtils.getMerchantFlowRule(accountId, "分享绑定");
                    MerchantInfoBean.DataEntity shareBindResult = shareBindRule.getData().get(0);
                    shareAccess.setFlowType(shareBindResult.getFlowType());
                    shareAccess.setFlowValue(shareBindResult.getCountryFlowValue());
                } catch (Exception e) {
                    LOGGER.error(String.format("获取商家[%s]的'分享绑定'流量赠送规则失败", accountId), e);
                }
                this.save(shareAccess);
                LOGGER.info("保存分享绑定信息:sharerOpenId = [" + sharerOpenId + "], viewerOpenId = [" + viewerOpenId + "], accountId = [" + accountId + "], shareId = [" + shareId + "]");
            } else {
                shareAccess = weixinShareAccessList.get(0);
                shareAccess.setUpdateTime(new Date());
                Long preAccessTimes = shareAccess.getAccessTimes();
                preAccessTimes = null == preAccessTimes ? 1L : preAccessTimes;
                shareAccess.setAccessTimes(preAccessTimes + 1);
                this.updateEntitie(shareAccess);
                LOGGER.info("更新分享绑定信息:sharerOpenId = [" + sharerOpenId + "], viewerOpenId = [" + viewerOpenId + "], accountId = [" + accountId + "], shareId = [" + shareId + "]");
            }
        }
    }
}
