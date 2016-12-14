package weixin.manual.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import weixin.lottery.entity.WeixinCommonforhdEntity;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.manual.entity.ManualGiven;
/**
 * @author Sukin
 * 2016年9月5日
 */
public interface ManualGivenService  extends CommonService{
	public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);
    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(ManualGiven t);

    /**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(ManualGiven t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(ManualGiven t);
    
    /**
     * 按照赠送时间,赠送状态查询赠送记录
     * @return
     */
    public List<ManualGiven> queryGiveTime();
    
    
    public List<ManualGiven> queryState();
    
    /**
     * 赠送流量
     * @param mg
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public String giveFlowToUser(ManualGiven mg) throws FileNotFoundException, IOException;
    


}
