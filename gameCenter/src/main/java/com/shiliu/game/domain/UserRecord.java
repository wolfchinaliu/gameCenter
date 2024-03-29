package com.shiliu.game.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.shiliu.game.utils.LongIdWorker;
/**
 * @version 1.0
 * @author lzh
 * 用户记录实体
 */
public class UserRecord {
    private Long userRecordId = LongIdWorker.getDataId();

    private String openId;

    private String gameId;

    private String phone;

    private String award;//奖品状态：该bean中的奖品集合像对应

    private Date playTime;//参与活动时间

    private String nickName;//微信名称

    private String reserved1;//预留字段

    private String reserved2;//预留字段
    
    //将奖品值放入一个map集合中
    public final static Map<String,String> awardMap ;
    
    //静态常量
    static{
    	awardMap = new HashMap<String,String>();
    	awardMap.put("0", "没有资格玩该游戏");
    	awardMap.put("1", "1G全国流量");
    	awardMap.put("2", "500M全国流量");
    	awardMap.put("3", "30M全国流量");
    	awardMap.put("4", "50元电信话费");
    	awardMap.put("5", "10元电信话费");
    	awardMap.put("6", "蓝瘦、香菇");
    }

    public Long getUserRecordId() {
        return userRecordId;
    }

    public void setUserRecordId(Long userRecordId) {
        this.userRecordId = userRecordId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId == null ? null : gameId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award == null ? null : award.trim();
    }

    public Date getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }
}