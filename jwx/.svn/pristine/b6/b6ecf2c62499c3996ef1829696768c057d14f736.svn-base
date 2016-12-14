package weixin.member.service.impl;

import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.hibernate.qbc.PagerUtil;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxuser.user.JwUserAPI;
import org.jeewx.api.wxuser.user.model.WxUserList;
import org.jeewx.api.wxuser.user.model.Wxuser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.util.RedisUtil;
import weixin.guanjia.message.controller.EmojiFilter;
import weixin.member.entity.WeixinBlackListEntity;
import weixin.member.entity.WeixinGroupEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinGroupServiceI;
import weixin.member.service.WeixinMemberServiceI;
import weixin.util.CommonUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("weixinMemberService")
@Transactional
public class WeixinMemberServiceImpl extends CommonServiceImpl implements WeixinMemberServiceI {
    public static final Logger LOGGER = LoggerFactory.getLogger(WeixinMemberServiceImpl.class);

    @Autowired
    private WeixinGroupServiceI weixinGroupService;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;

    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((WeixinMemberEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((WeixinMemberEntity) entity);
        return t;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((WeixinMemberEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(WeixinMemberEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(WeixinMemberEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(WeixinMemberEntity t) {
        return true;
    }

    /**
     * 替换sql中的变量
     *
     * @param sql
     * @return
     */
    public String replaceVal(String sql, WeixinMemberEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{nick_name}", String.valueOf(t.getNickName()));
        sql = sql.replace("#{sex}", String.valueOf(t.getSex()));
        sql = sql.replace("#{city}", String.valueOf(t.getCity()));
        sql = sql.replace("#{province}", String.valueOf(t.getProvince()));
        sql = sql.replace("#{country}", String.valueOf(t.getCountry()));
        sql = sql.replace("#{language}", String.valueOf(t.getLanguage()));
        sql = sql.replace("#{head_img_url}", String.valueOf(t.getHeadImgUrl()));
        sql = sql.replace("#{subscribe_time}", String.valueOf(t.getSubscribeTime()));
        sql = sql.replace("#{unionid}", String.valueOf(t.getUnionid()));
        sql = sql.replace("#{subscribe}", String.valueOf(t.getSubscribe()));
        sql = sql.replace("#{account_id}", String.valueOf(t.getAccountId()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }

    @Override
    public WeixinMemberEntity getWeixinMemberEntityByOpenIdAndAccountId(String openId, String accountId) {
        String hql = " from WeixinMemberEntity where openId=? and accountId=?";
        List<WeixinMemberEntity> weixinMemberEntityList = this.findHql(hql, openId, accountId);
        if (CollectionUtils.isNotEmpty(weixinMemberEntityList)) {
            return weixinMemberEntityList.get(0);
        }
        return null;
    }

    @Override
    public WeixinMemberEntity getWeixinMemberEntity(String openId, String accountId) {
        String hql = " from WeixinMemberEntity where openId=? and accountId=?";
        List<WeixinMemberEntity> weixinMemberEntityList = this.findHql(hql, openId, accountId);
        if (CollectionUtils.isNotEmpty(weixinMemberEntityList)) {
            return weixinMemberEntityList.get(0);
        }
        return null;
    }

    /**
     * 批量更新粉丝订阅状态
     *
     * @param accountId
     */
    @Override
    public void updateMembersSubscribe(String accountId) {

        //批量更新本地库订阅状态
        String sql = "update weixin_member set subscribe='0' where account_id='" + accountId + "'";
        this.updateBySqlString(sql);
    }

    @Override
    public boolean getPhoneNumberInBlackList(String phoneNumber) {
        // 如果用户的手机号码在黑名单列表中，禁止其进一步的操作
    	WeixinBlackListEntity blackListEntity = this.getEntity(WeixinBlackListEntity.class, phoneNumber);
    	if(blackListEntity !=null && (blackListEntity.getState()==null || blackListEntity.getState() == 1))
    		return true;
       return false;
    }

    @Override
    public WeixinMemberEntity getWeixinMemberByPhone(String phoneNumber, String accountId) {
        if (StringUtils.isBlank(phoneNumber)) {
            return null;
        }
        List<WeixinMemberEntity> list = this.commonDao
                .findHql("from WeixinMemberEntity where phoneNumber=? and accountId=? and subscribe=1", phoneNumber, accountId);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public WeixinMemberEntity getWeixinMember(String openId, String accountId) {
        if (StringUtils.isBlank(openId)) {
            return null;
        }
        List<WeixinMemberEntity> list = this.commonDao.findHql("from WeixinMemberEntity where openId=? and accountId=? and subscribe=1",
                openId, accountId);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public WeixinMemberEntity getWeixinMember(String openId) {
        if (StringUtils.isBlank(openId)) {
            return null;
        }
        List<WeixinMemberEntity> list = this.commonDao.findHql("from WeixinMemberEntity where openId=?", openId);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (list.size() > 1) {
            LOGGER.warn(String.format("根据openId[%s]查询粉丝列表,存在多个粉丝,请确认数据是否存在异常!", openId));
        }
        return list.get(0);
    }

    @Override
    public WeixinMemberEntity getWeixinMember(String openId, String accountId, String subscribe) {
        if (StringUtils.isBlank(openId)) {
            return null;
        }
        List<WeixinMemberEntity> list = this.commonDao.findHql("from WeixinMemberEntity where openId=? and accountId=? and subscribe=?", openId, accountId, subscribe);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public WeixinMemberEntity getWeixinMemberByShiliuOpenId(String shiliuOpenId, String accountId) {
        if (StringUtils.isBlank(shiliuOpenId)) {
            return null;
        }
        List<WeixinMemberEntity> list = this.commonDao.findHql("from WeixinMemberEntity where shiliuOpenId=? and accountId=?", shiliuOpenId, accountId);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<WeixinMemberEntity> getWeixinMemberByOpenIds(Set<String> openIdList, String accountId) {
        if (CollectionUtils.isEmpty(openIdList)) return new ArrayList<>();
        Session session = this.getSession();
        Query query = session.createQuery("from WeixinMemberEntity where openId in :openIdList and accountId = :accountId");
        query.setParameterList("openIdList", openIdList);
        query.setParameter("accountId", accountId);
        return query.list();
    }

    @Override
    public WxUserList getWeixinUserInfo(String accessToken, List<String> openIdList, PageList pageList, Map<String, WeixinMemberEntity> openIdMemberMap) {
        WxUserList wxUsers = null;
        List<WeixinMemberEntity> page = pageList.getResultList();
        for (WeixinMemberEntity memberEntity : page) {
            openIdList.add(memberEntity.getOpenId());
        }

        try {
            wxUsers = JwUserAPI.getWxUsers(accessToken, openIdList);
        } catch (WexinReqException e) {
            e.printStackTrace();
        }
        if (wxUsers == null) {
            return null;
        }

        for (WeixinMemberEntity memberEntity : page) {
            openIdMemberMap.put(memberEntity.getOpenId(), memberEntity);
        }
        return wxUsers;
    }

    @Override
    public PageList getInCompleteMembers(JSONObject params) {
        int pageSize = params.optInt("pageSize");
        int pageNo = params.optInt("pageNo");

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(WeixinMemberEntity.class);
        detachedCriteria.addOrder(Order.asc("id"));
        detachedCriteria.add(Property.forName("accountId").eq(params.optString("accountId")));
        detachedCriteria.add(Property.forName("subscribe").eq("1"));
        if ("true".equals(RedisUtil.getRedis("full.sync.enable"))) {
            detachedCriteria.add(Property.forName("nickName").isNull());
        }
        Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());

        CriteriaImpl impl = (CriteriaImpl) criteria;
        Projection projection = impl.getProjection();
        final int allCounts = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        if (Math.ceil((double) allCounts / pageSize) < pageNo) {
            return new PageList(new ArrayList<>(), null, -1, pageNo, allCounts);
        }
        criteria.setProjection(projection);
        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }

        int offset = PagerUtil.getOffset(allCounts, pageNo, pageSize);
        criteria.setFirstResult(offset);
        criteria.setMaxResults(pageSize);
        return new PageList(criteria.list(), null, offset, pageNo, allCounts);
    }

    @Override
    public void batchUpdate(List<WeixinMemberEntity> existsMemberEntities) {
        long counter = 0;
        Session session = this.getSession();
        try {
            for (WeixinMemberEntity existsMemberEntity : existsMemberEntities) {
                session.update(existsMemberEntity);
                if (++counter % 20 == 0) {
                    session.flush();
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void syncMemberDetailInfo(String accesstoken, JSONObject params, PageList pageList, WxUserList wxUsers, Map<String, WeixinMemberEntity> openIdMemberMap) {
        String accountId = params.optString("accountId");
        List<Wxuser> userInfoList = wxUsers.getUser_info_list();
        List<WeixinMemberEntity> weixinMemberEntities = new ArrayList<>();
        for (Wxuser wxuser : userInfoList) {
            WeixinMemberEntity weixinMemberEntity = openIdMemberMap.get(wxuser.getOpenid());
            //LOGGER.info("根据accountId和openId获取完成微信粉丝");
            // weixinMemberEntity.setAccountId(accountId);
            // weixinMemberEntity.setSubscribe(wxuser.getSubscribe().toString());
            // weixinMemberEntity.setOpenId(wxuser.getOpenid());
            weixinMemberEntity.setNickName(EmojiFilter.filterEmoji(StringUtils.defaultString(wxuser.getNickname())));
            weixinMemberEntity.setSex(wxuser.getSex());
            weixinMemberEntity.setCity(EmojiFilter.filterEmoji(StringUtils.defaultString(wxuser.getCity())));
            weixinMemberEntity.setCountry(EmojiFilter.filterEmoji(StringUtils.defaultString(wxuser.getCountry())));
            weixinMemberEntity.setProvince(EmojiFilter.filterEmoji(StringUtils.defaultString(wxuser.getProvince())));
            weixinMemberEntity.setLanguage(wxuser.getLanguage());
            weixinMemberEntity.setHeadImgUrl(wxuser.getHeadimgurl());
            weixinMemberEntity.setSubscribe(wxuser.getSubscribe() + "");

            if (StringUtils.isNotBlank(wxuser.getSubscribe_time())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int aa = Integer.parseInt(wxuser.getSubscribe_time());
                String sd = sdf.format(aa * 1000L);
                Date date = null;
                try {
                    date = sdf.parse(sd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                weixinMemberEntity.setSubscribeTime(date);
            }

            // 设置分组ID
            WeixinGroupEntity weixinGroupEntity = new WeixinGroupEntity();
            weixinGroupEntity.setGroupId(0);
            weixinGroupEntity.setAccountid(accountId);
            //LOGGER.info("开始获取粉丝所在组的信息");
            weixinGroupEntity = weixinGroupService.getGroupEntityByParam(weixinGroupEntity);
            //LOGGER.info("获取完成粉丝所在组");

            weixinMemberEntity.setWeixinGroupEntity(weixinGroupEntity);
            try {
                // LOGGER.info("更新粉丝信息正常：{}", JSONObject.fromObject(wxuser));
                weixinMemberEntities.add(weixinMemberEntity);
            } catch (Exception e) {
                LOGGER.info("更新粉丝信息异常：{}", JSONObject.fromObject(wxuser));
            }
        }
        if (CollectionUtils.isNotEmpty(weixinMemberEntities)) {
            this.batchUpdate(weixinMemberEntities);
            weixinMemberEntities.clear();
        }
    }

    private String cleanup(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        text = text.trim();
        text = text.replaceAll("[0-9a-zA-Z\u4e00-\u9f85]*", "");
        return text;
    }
}