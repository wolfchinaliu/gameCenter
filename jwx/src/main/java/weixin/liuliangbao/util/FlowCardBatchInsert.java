package weixin.liuliangbao.util;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import weixin.liuliangbao.flowcard.Entity.FlowCardBatchEntity;
import weixin.liuliangbao.flowcard.Entity.FlowCardInfoEntity;
import weixin.liuliangbao.flowcard.Entity.FlowCardTaskEntity;
import weixin.liuliangbao.flowcard.Service.FlowCardInfoServiceI;
import weixin.liuliangbao.flowcard.Service.FlowCardTaskServiceI;
import weixin.liuliangbao.jsonbean.FlowCard.extractionCodeBean;

import java.io.File;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaona on 2016/3/24. xiaona 关于线程扫描插入制作的流量卡信息
 */
public class FlowCardBatchInsert {
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("00000");

    /**
     * 流量卡的生成
     *
     * @param logger
     *            日志LOGGER
     * @param flowCardTaskService
     *            流量卡任务Service，尚未使用其保存的状态
     * @param flowCardInfoService
     *            流量卡信息Service，对流量卡的CRUD
     * @param systemService
     *            公共的系统Service
     * @param extractionCodeList
     *            提取码列表
     * @param flowCardBatchEntity
     *            流量卡批次表实体
     * @param flowCardTaskEntity
     *            流量卡任务实体，保存了处理完成的状态，但状态尚未使用
     * @param cardCode
     *            流量卡的编号
     * @param flowCardValue
     *            流量卡的流量值
     */
    public static void AddFlowCardBatchInfo(FlowCardTaskServiceI flowCardTaskService, FlowCardInfoServiceI flowCardInfoService,
            SystemService systemService, List<extractionCodeBean> extractionCodeList, FlowCardBatchEntity flowCardBatchEntity,
            FlowCardTaskEntity flowCardTaskEntity, String cardCode, Double flowCardValue) {
        List<FlowCardInfoEntity> cardInfoList = new ArrayList<>();
        String urlprefix = ResourceUtil.getConfigByName("QRcode.storage.path");
        // 二维码的格式以及地址和名字
        File prefixPath = new File(urlprefix);
        if (!prefixPath.exists()) {
            prefixPath.mkdirs();
        }
        String prefix = ResourceUtil.getConfigByName("domain");
        for (int i = 0; i < extractionCodeList.size(); i++) {
            FlowCardInfoEntity f = new FlowCardInfoEntity();
            f.setCardCode(DECIMAL_FORMAT.format(i)); // 卡编码+张数是卡的编码
            f.setCardNumber(flowCardBatchEntity.getBatchNo() + f.getCardCode()); // 卡编码+张数是卡的编码+批次号是总的卡号
            f.setAcctId(flowCardBatchEntity.getAcctId());
            f.setBelongAcctId(flowCardBatchEntity.getAcctId());
            f.setBatchId(flowCardBatchEntity.getId());
            f.setBatchNo(flowCardBatchEntity.getBatchNo());
            f.setFlowValue(flowCardValue);
            f.setFlowUnit("M");
            f.setBeginDate(flowCardBatchEntity.getEnableDate());
            f.setEndDate(flowCardBatchEntity.getDisabledDate());
            f.setIsUse("否");
            f.setExtractionCode(extractionCodeList.get(i).getCode()); // 提取码
            f.setStatusLock(flowCardBatchEntity.getStatus()); // 状态
            f.setIsValid("是");
            f.setFlowType(flowCardBatchEntity.getFlowType());
            // flowCardInfoService.save(f);
            cardInfoList.add(f);
        }
        flowCardInfoService.batchSave(cardInfoList);
        // 生成二维码并且将二维码的地址和路径进行读取，从而将实体进行更新
        // 查找当前商户，以及隶属于当前商户的流量卡信息，并且将二维码以及路径进行设置
        // String hql = "from FlowCardInfoEntity where acctId='" +
        // flowCardBatchEntity.getAcctId() + "'and batchNo='" +
        // flowCardBatchEntity.getBatchNo() + "'";

        // List<FlowCardInfoEntity> listFlow = cardInfoList;//
        // systemService.findHql(hql,
        // null);

        
         for (FlowCardInfoEntity f : cardInfoList) {
             String cards = f.getExtractionCode();
             String batchNo = f.getBatchNo();
             String str = MessageFormat.format("{0}/flowCardController.do?startLoad&extractionCode={1}&batchNo={2}", prefix, cards, batchNo); // 二维码的内容

             String path = urlprefix + f.getCardNumber() + ".png";
             String pathDb = f.getCardNumber() + ".png";
             EnDeCode.encode(str, path);
             // EnDeCode.decode(path);
             f.setQRcode(pathDb);
        // flowCardInfoService.saveOrUpdate(en);
         }
        flowCardInfoService.batchSave(cardInfoList);
        // 状态值为1表示线程成功
        flowCardTaskEntity.setStatusValue("1");
        flowCardTaskService.saveOrUpdate(flowCardTaskEntity);

    }
}
