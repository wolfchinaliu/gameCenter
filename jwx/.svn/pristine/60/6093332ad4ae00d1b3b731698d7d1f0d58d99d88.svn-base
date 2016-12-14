package weixin.member.service;
import net.sf.json.JSONObject;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeewx.api.wxuser.user.model.WxUserList;
import weixin.member.entity.WeixinMemberEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WeixinMemberServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinMemberEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinMemberEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinMemberEntity t);
 	
 	public WeixinMemberEntity getWeixinMemberEntityByOpenIdAndAccountId(String openId, String accountId);
 	public WeixinMemberEntity getWeixinMemberEntity(String openId, String accountId);

 	/**
 	 * 批量更新粉丝订阅状态
 	 * @param accountId
 	 */
 	public void updateMembersSubscribe(String accountId);

    /**
     * 根据openId和accountId查找用户的手机号码，并判断手机号码是否在黑名单中，
     * 如果在黑名单中，则返回该手机号码， 否则返回null
     *
     * @param openId       用户的微信Id
     * @param accountId    商户的Id
     * @return
     */
 	boolean getPhoneNumberInBlackList(String phoneNumber);

    public WeixinMemberEntity getWeixinMemberByPhone(String phoneNumber, String accountId);

    public WeixinMemberEntity getWeixinMember(String openId, String accountId);
    public WeixinMemberEntity getWeixinMember(String openId);

    public WeixinMemberEntity getWeixinMember(String openId, String accountId, String subscribe);

    public WeixinMemberEntity getWeixinMemberByShiliuOpenId(String shiliuOpenId, String accountId);

    List getWeixinMemberByOpenIds(Set<String> openIdList, String accountId);

    WxUserList getWeixinUserInfo(String accessToken, List<String> openIdList, PageList pageList, Map<String, WeixinMemberEntity> openIdMemberMap);

    PageList getInCompleteMembers(JSONObject params);

    void batchUpdate(List<WeixinMemberEntity> existsMemberEntities);

    void syncMemberDetailInfo(String accesstoken, JSONObject params, PageList pageList, WxUserList wxUserList, Map<String, WeixinMemberEntity> openIdMemberMap);
}
