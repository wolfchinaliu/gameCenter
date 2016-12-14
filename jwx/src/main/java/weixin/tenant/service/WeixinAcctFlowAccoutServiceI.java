package weixin.tenant.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by aa on 2015/12/1.
 */
public interface WeixinAcctFlowAccoutServiceI extends CommonService {
    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(weixinAcctFlowAccountEntity t);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(weixinAcctFlowAccountEntity t);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(weixinAcctFlowAccountEntity t);

	public List<String> findSubAccountIdList(String accountid);

	public List<String> findOperator(String phoneNumbers);

	public List<Map> findFlowCollect(String accountid);
	
	public Map<String,Object> findTotalCharge(String acctId);

}
