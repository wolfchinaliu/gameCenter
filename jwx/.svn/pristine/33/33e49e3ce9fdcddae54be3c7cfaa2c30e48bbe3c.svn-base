package weixin.liuliangbao.weigatedoor.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.weigatedoor.entity.WeidoorpptEntity;
import weixin.liuliangbao.weigatedoor.service.WeidoorpptServiceI;
import weixin.source.entity.WeixinSourceEntity;

import java.io.Serializable;

/**
 * Created by aa on 2015/12/9.
 */
@Service("WeidoorpptServiceImpl")
@Transactional
public class WeidoorpptServiceImpl extends CommonServiceImpl implements
        WeidoorpptServiceI {
        public <T> void delete(T entity) {
                super.delete(entity);
                //执行删除操作配置的sql增强
                this.doDelSql((WeidoorpptEntity)entity);
        }

        public <T> Serializable save(T entity) {
                Serializable t = super.save(entity);
                //执行新增操作配置的sql增强
                this.doAddSql((WeidoorpptEntity)entity);
                return t;
        }

        public <T> void saveOrUpdate(T entity) {
                super.saveOrUpdate(entity);
                //执行更新操作配置的sql增强
                this.doUpdateSql((WeidoorpptEntity)entity);
        }

        /**
         * 默认按钮-sql增强-新增操作
         * @param t
         * @return
         */
        public boolean doAddSql(WeidoorpptEntity t){
                return true;
        }
        /**
         * 默认按钮-sql增强-更新操作
         * @param t
         * @return
         */
        public boolean doUpdateSql(WeidoorpptEntity t){
                return true;
        }
        /**
         * 默认按钮-sql增强-删除操作
         * @param t
         * @return
         */
        public boolean doDelSql(WeidoorpptEntity t){
                return true;
        }

}
