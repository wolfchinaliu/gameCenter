package weixin.tenant.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.DataDictionaryUtil.FlowType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aa on 2015/12/1.
 */

@Service("WeixinAcctFlowAccoutService")
@Transactional
public class WeixinAcctFlowAccoutServiceImpl extends CommonServiceImpl implements WeixinAcctFlowAccoutServiceI {

	public <T> void delete(T entity) {
		super.delete(entity);
		// 执行删除操作配置的sql增强
		this.doDelSql((weixinAcctFlowAccountEntity) entity);
	}

	public <T> Serializable save(T entity) {
		Serializable t = super.save(entity);
		// 执行新增操作配置的sql增强
		this.doAddSql((weixinAcctFlowAccountEntity) entity);
		return t;
	}

	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((weixinAcctFlowAccountEntity) entity);
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 *
	 * @param id
	 * @return
	 */
	public boolean doAddSql(weixinAcctFlowAccountEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 *
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(weixinAcctFlowAccountEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 *
	 * @param id
	 * @return
	 */
	public boolean doDelSql(weixinAcctFlowAccountEntity t) {
		return true;
	}

	@Override
	public List<String> findSubAccountIdList(String accountid) {
		String hql = "select id from weixin_account a2 where a2.acct_id in ("
				+ "SELECT id from weixin_acct t where t.pid = (" + "select acct_id from weixin_account a WHERE a.id=?)"
				+ ")";
		List<Map<String, Object>> results = null;
		List<Map<String, Object>> result = this.findForJdbc(hql, accountid);
		List<String> resultList = new ArrayList<>();
		List<String> ra = new ArrayList<>();
		List<String> rb = new ArrayList<>();
		List<String> rc = new ArrayList<>();
		if (result != null) {
			for (Map<String, Object> map : result) {
				ra.add((String) map.get("id"));
			}
			for (String id : ra) {
				String hql1 = "select id from weixin_account a2 where a2.acct_id in ("
						+ "SELECT id from weixin_acct t where t.pid = ("
						+ "select acct_id from weixin_account a WHERE a.id=?)" + ")";
				results = this.findForJdbc(hql1, id);
				for (Map<String, Object> map : results) {
					rb.add((String) map.get("id"));
				}
			}
		}
		if (rb != null) {
			
				for (String id : rb) {
					String hql1 = "select id from weixin_account a2 where a2.acct_id in ("
							+ "SELECT id from weixin_acct t where t.pid = ("
							+ "select acct_id from weixin_account a WHERE a.id=?)" + ")";
					List<Map<String, Object>> resultss = this.findForJdbc(hql1, id);
					for (Map<String, Object> map2 : resultss) {
						rc.add((String) map2.get("id"));
					}
				}
			}
		
		for (String sa : ra) {
			resultList.add(sa);
		}
		for (String sb : rb) {
			resultList.add(sb);
		}
		for (String sc : rc) {
			resultList.add(sc);
		}
		return resultList;
	}

	@Override
	public List<String> findOperator(String phoneNumbers) {
		String sql2 = "select businessCode from phonelocation where beginNumber like " + "'%" + phoneNumbers + "%'"
				+ "";
		List<String> findListbySql = this.findListbySql(sql2);
		return findListbySql;
	}

	@Override
	public List<Map> findFlowCollect(String accountid) {
		String sql = "select operateDate,businessCode,flowType,sum(flowValue) from userflowgiverecords where merchantID=? "
				+ "GROUP BY merchantID,businessCode,flowType ORDER BY operateDate desc;";
		List<Map<String, Object>> list = this.findForJdbc(sql, accountid);
		List<Map> resultList = new ArrayList<Map>();
		for (Map<String, Object> map : list) {
			resultList.add(map);
		}
		return resultList;
	}

    @Override
    public Map<String,Object> findTotalCharge(String acctId) {
        String sql = "SELECT a.flowType flowType, sum(a.flowValue) flowValue FROM  flowcardtraderecords a LEFT JOIN merchantflowaccount mt ON a.toAcc_id = mt.id WHERE a.flowSource = '增加' and mt.tenantId=? group by a.flowType";
        List<Map<String,Object>> totalCharge = this.findForJdbc(sql, acctId);
        Map<String, Object> charge = new HashMap<>();
        if(CollectionUtils.isNotEmpty(totalCharge)){
            for(Map<String,Object> m:totalCharge){
                if(m.get("flowType").equals(FlowType.country.getCode())){
                    charge.put("country", m.get("flowValue"));
                }
                if(m.get("flowType").equals(FlowType.province.getCode())){
                    charge.put("province", m.get("flowValue"));
                }
            }
        }
        return charge;
    }

}
