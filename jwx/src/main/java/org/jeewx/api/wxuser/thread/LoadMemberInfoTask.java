package org.jeewx.api.wxuser.thread;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxuser.user.JwUserAPI;
import org.jeewx.api.wxuser.user.model.WxUserList;
import org.jeewx.api.wxuser.user.model.Wxuser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.RedisUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;

public class LoadMemberInfoTask implements Runnable {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoadMemberInfoTask.class);

    private String accountId;
    private WeixinMemberServiceI weixinMemberService;
    private WeixinAccountServiceI weixinAccountService;

    public LoadMemberInfoTask(String accountId, WeixinMemberServiceI weixinMemberService, WeixinAccountServiceI weixinAccountService) {
        this.accountId = accountId;
        this.weixinMemberService = weixinMemberService;
        this.weixinAccountService = weixinAccountService;
    }

    @Override
    public void run() {
        JSONObject params = new JSONObject();
        params.element("accountId", accountId);
        int pageNo = Integer.parseInt(StringUtils.defaultString(RedisUtil.getRedis(accountId + ".member.loading.pageNo"), "1"));
        params.element("pageNo", pageNo);
        params.element("pageSize", 100);

        PageList pageList = this.weixinMemberService.getInCompleteMembers(params);
        int count = pageList.getCount();
        while (CollectionUtils.isNotEmpty(pageList.getResultList())) {
            long start = System.currentTimeMillis();
            long tokenTime = System.currentTimeMillis();
            String accessToken = weixinAccountService.getAccessToken(accountId);
            tokenTime = System.currentTimeMillis() - tokenTime;


            long syncDetailTime = System.currentTimeMillis();
            List<String> openIdList = new ArrayList<>();
            Map<String, WeixinMemberEntity> openIdMemberMap = new HashMap<>();

            WxUserList weixinUserInfo = this.weixinMemberService.getWeixinUserInfo(accessToken, openIdList, pageList, openIdMemberMap);
            if (null == weixinUserInfo) {
                continue;
            }
            this.weixinMemberService.syncMemberDetailInfo(accessToken, params, pageList, weixinUserInfo, openIdMemberMap);
            syncDetailTime = System.currentTimeMillis() - syncDetailTime;

            params.element("pageNo", ++pageNo);
            RedisUtil.setRedis(accountId + ".member.loading.pageNo", pageNo + "");

            long nextPageDataTime = System.currentTimeMillis();
            pageList = this.weixinMemberService.getInCompleteMembers(params);
            nextPageDataTime = System.currentTimeMillis() - nextPageDataTime;
            start = System.currentTimeMillis() - start;
            LOGGER.info(MessageFormat.format("更新粉丝:商家的[{0}]第[{1}]页,耗时:{2}ms:token:{3}ms,详细信息:{4}ms,下一页数据:{5}ms",
                    accountId, pageNo, start, tokenTime, syncDetailTime, nextPageDataTime));

        }
        LOGGER.info(MessageFormat.format("商家[{0}]的粉丝列表同步完毕, 共计[{1}]个粉丝", accountId, count));
    }

}
