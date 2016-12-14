package weixin.liuliangbao.business.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.liuliangbao.business.entity.BusinessInterfaceEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aa on 2015/11/30.
 */
public interface BusinessInterfaceServiceI extends CommonService {

    public List<BusinessInterfaceEntity> findAllBusinessInterface();


}
