package weixin.liuliangbao.flowcard.Service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.PagerUtil;
import org.jeecgframework.core.common.model.json.DataGridReturn;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.liuliangbao.flowcard.Entity.FlowCardBatchEntity;
import weixin.liuliangbao.flowcard.Entity.FlowCardInfoEntity;
import weixin.liuliangbao.flowcard.Service.FlowCardInfoServiceI;

/**
 * Created by aa on 2015/12/18.
 */
@Service("flowCardInfoService")
@Transactional
public class FlowCardInfoServiceImpl extends CommonServiceImpl implements FlowCardInfoServiceI {

    public <T> void delete(T entity) {
        super.delete(entity);
        //执行删除操作配置的sql增强
        this.doDelSql((FlowCardInfoEntity) entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        //执行新增操作配置的sql增强
        this.doAddSql((FlowCardInfoEntity) entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        //执行更新操作配置的sql增强
        this.doUpdateSql((FlowCardInfoEntity) entity);
    }

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @return
     */
    public boolean doAddSql(FlowCardInfoEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @return
     */
    public boolean doUpdateSql(FlowCardInfoEntity t) {
        return true;
    }

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @return
     */
    public boolean doDelSql(FlowCardInfoEntity t) {
        return true;
    }


    /**
     * 返回easyui datagrid DataGridReturn模型对象
     */
    public DataGridReturn getDataGridReturnConvert(final CriteriaQuery cq,
                                            final boolean isOffset) {
        Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(
                getSession());
        CriteriaImpl impl = (CriteriaImpl) criteria;
        // 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
        Projection projection = impl.getProjection();
        final int allCounts = ((Long) criteria.setProjection(
                Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(projection);
        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        if (StringUtils.isNotBlank(cq.getDataGrid().getSort())) {
            cq.addOrder(cq.getDataGrid().getSort(), cq.getDataGrid().getOrder());
        }

        // 判断是否有排序字段
        if (!cq.getOrdermap().isEmpty()) {
            cq.setOrder(cq.getOrdermap());
        }
        int pageSize = cq.getPageSize();// 每页显示数
        int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage(),
                pageSize);// 当前页
        int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
        if (isOffset) {// 是否分页
            criteria.setFirstResult(offset);
            criteria.setMaxResults(cq.getPageSize());
        } else {
            pageSize = allCounts;
        }
        List list = criteria.list();
        cq.getDataGrid().setResults(list);
        cq.getDataGrid().setTotal(allCounts);
        return new DataGridReturn(allCounts, list);
    }

    @Override
    public List<FlowCardBatchEntity> queryExpiredCardBatch() {
        String hql ="from FlowCardBatchEntity where disabledDate < DATE_FORMAT(NOW(),'%Y-%m-%d') and isValid='是'";
        return this.findHql(hql, null);
    }

    @Override
    public List<Map> queryExpiredCardInfo(Collection<String> expiredCardBatchId) {
        String hql ="select new map(batchId as batchId,sum(flowValue) as flowvalue) from FlowCardInfoEntity  where batchId in (:batchId) and endDate < date_format(now(),'%Y-%m-%d') and isUse='否' group by batchId";
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("batchId", expiredCardBatchId);
        return this.findHqlWithKeyParam(hql, param);
    }

    @Override
    public void expiredCardInfoByBatchId(Collection<String> batchIds) {
        String hql ="update FlowCardInfoEntity set isValid='否' where batchId in (:batchId)";
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("batchId", batchIds);
        this.commonDao.executeHql(hql, param);
    }
}
