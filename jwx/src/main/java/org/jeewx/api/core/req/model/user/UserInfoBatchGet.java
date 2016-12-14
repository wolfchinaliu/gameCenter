package org.jeewx.api.core.req.model.user;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeewx.api.core.annotation.ReqType;
import org.jeewx.api.core.req.model.WeixinReqParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 取多媒体文件
 * 
 * @author guo liang
 *
 *  {
 *     "userList": [
 *         {
 *         "openid": "otvxTs4dckWG7imySrJd6jSi0CWE",
 *         "lang": "zh-CN"
 *         },
 *         {
 *         "openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg",
 *         "lang": "zh-CN"
 *         }
 *     ]
 *   }
 * 正常情况下，微信会返回下述JSON数据包给公众号
 *
 * {
 *     "user_info_list": [
 *         {
 *             "subscribe": 1,
 *             "openid": "otvxTs4dckWG7imySrJd6jSi0CWE",
 *             "nickname": "iWithery",
 *             "sex": 1,
 *             "language": "zh_CN",
 *             "city": "Jieyang",
 *             "province": "Guangdong",
 *             "country": "China",
 *             "headimgurl": "http://wx.qlogo.cn/mmopen/xbIQx1GRqdvyqkMMhEaGOX802l1CyqMJNgUzKP8MeAeHFicRDSnZH7FY4XB7p8XHXIf6uJA2SCun
 *             TPicGKezDC4saKISzRj3nz/0",
 *             "subscribe_time": 1434093047,
 *             "unionid": "oR5GjjgEhCMJFyzaVZdrxZ2zRRF4",
 *             "remark": "",
 *             "groupid": 0,
 *             "tagid_list":[128,2]
 *         },
 *         {
 *             "subscribe": 0,
 *             "openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg",
 *             "unionid": "oR5GjjjrbqBZbrnPwwmSxFukE41U",
 *         }
 *     ]
 * }
 *
 */
@ReqType("getUserInfoBatch")
public class UserInfoBatchGet extends WeixinReqParam {
	private List<UserBaseInfoGet> user_list;

    public List<UserBaseInfoGet> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<UserBaseInfoGet> user_list) {
        this.user_list = user_list;
    }

    public void addUserList(List<String> userList, String lang) {
        if (CollectionUtils.isEmpty(userList)) return;
        lang = StringUtils.defaultString(lang, "zh_CN");
        List<UserBaseInfoGet> userBaseInfoGets = new ArrayList<>();
        for (String openId : userList) {
            UserBaseInfoGet userBaseInfoGet = new UserBaseInfoGet();
            userBaseInfoGet.setOpenid(openId);
            userBaseInfoGet.setLang(lang);
            userBaseInfoGets.add(userBaseInfoGet);
        }
        this.user_list = userBaseInfoGets;
    }
}
