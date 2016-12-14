package weixin.liuliangbao.business.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weixin.liuliangbao.business.entity.BusinessInterfaceEntity;
import weixin.liuliangbao.business.service.BusinessInterfaceServiceI;

import java.util.List;

/**
 * Created by aa on 2015/11/30.
 */
@Service("BusinessInterfaceServiceI")
@Transactional
public class BusinessInterfaceServiceImpl  extends CommonServiceImpl implements
        BusinessInterfaceServiceI {

    public List<BusinessInterfaceEntity> findAllBusinessInterface()
    {
        List<BusinessInterfaceEntity> busslst=this.findByQueryString("select * from businessinterface");
        return busslst;
    }

}
