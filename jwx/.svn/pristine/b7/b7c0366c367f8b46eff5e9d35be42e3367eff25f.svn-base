package weixin.mailmanager.entity;

/**
 * @Title: CodeEnum.java
 * @Package com.mdy.entity.base.state
 * @Description: 返回状态码及状态码说明 枚举类型.
 * @author mike
 * @version V1.0
 */
public enum CodeEnum {
	// 用户相关---------------------------------------------------------------------
	registerSuccess(1000000, "注册成功"),
	nicknameExists(1000001, "昵称已存在"),
	telephoneExists(1000002, "电话号码已存在"),
	nicknameAndtelephoneExists(1000003, "昵称或电话号码已存在"),
	telePhoneNotExist(1000004, "电话号码或邮箱未注册"),
	illegalPhone(1000005, "非法手机号"),
	telePhoneExist(1000006, "电话号码或邮箱已注册"),
	onlyEnCode(1000007, "昵称不允许中文"),
	passwordErr(1000008, "密码错误"),
	nicknameToshort(1000009, "昵称过短"),
	huanxinErr(1000010, "环信注册发生错误"),
	userIllegal(1000011, "操作用户非法"),
	passwordToshort(1000012, "密码过短"),
	userAuditing(1000013, "用户审核中"),
	userAuditReject(1000014, "用户审核被驳回"),
	mailNotValidate(1000015,"邮箱账号未激活"),
	userNotExists(1000016,"邮箱账号未激活"),
	passwordSuccess(1000017, "密码正确"),
	nicknameNotExist(1000018, "昵称不存在"),
	homepageNotExist(1000019, "个性域名不存在"),
	homepageExist(1000020, "个性域名已存在"),
	weChatLogin(1000021, "微信登录"),
	weChatReg(1000022, "微信注册"),
	nicknameTimeFast(1000023, "昵称一个月只能修改一次"),
	userIdNotExists(1000024, "用户不存在"),
	userOpenIdExists(1000025, "该账号已绑定其他账号"),
	tencentLogin(1000026, "QQ登录"),
	tencentReg(1000027, "QQ注册"),
	weiboLogin(1000028, "微博登录"),
	weiboReg(1000029, "微博注册"),

	// 短信相关---------------------------------------------------------------------
	/** 短信验证码错误(8) */
	identifyingCodeError(2000001, "短信验证码错误"),
	/** CCP信息发送失败 */
	CCPSendFailure(2000002, "CCP信息发送失败"),
	/**邮箱发送失败*/
	MailSendFilure(2000003,"邮箱发送失败！"),

	MailActivition(2000004,"邮箱已激活！"),
	MailUrlOvertime(2000005,"链接超时！"),
	MailUrlTimeFast(2000006,"操作太频繁，请稍后再试！"),

	// 请求相关---------------------------------------------------------------------
	CodeOverTime(3000001, "code过期"),
//	OrderGetErr(3000002, "没有该订单"),
//	OrderGetNull(3000003, "没有订单"),
//	PaymentTypeIsNotZhifubao(3000004, "支付方式不是支付宝"),
//	ServiceTypeNotSupport(3000005, "暂不支持此订单类型"),

	// 优惠券相关--------------------------------------------------------------------
	CouponNone(4000001, "没有该优惠券信息"),
	CouponIsLock(4000002, "该优惠券已经被锁定"),
	CouponIsOverdue(4000003, "该优惠券已过期"),
	CouponGetNull(4000004, "没有优惠卷"),


	// 消息相关--------------------------------------------------------------------
	MessageGetNull(5000001, "没有消息"),

	// 地址相关--------------------------------------------------------------------
	AddressGetNull(6000001, "没有地址信息"),

	// 活动相关--------------------------------------------------------------------
	ActivityGetNull(7000001, "没有活动信息"),

	// 购物相关--------------------------------------------------------------------
	ShopGoodsTypeGetNull(8000001, "没有产品分类"),
	ShopGoodsGetNull(8000002, "没有产品信息"),
	ShopGoodsIsOverdue(8000003, "商品已过期，请重新下单。"),
	ShopGoodsNameIsNull(8000004, "请输入商品名称"),

	// 系统相关---------------------------------------------------------------------
	/** 请求成功(1) */
	success(200, "请求成功"),
	/** 请求失败(0) */
	error(9000001, "请求失败"),
	/** 参数错误(2) */
	paramErr(9000002, "参数错误"),
	/** 空数据(3) */
	nullData(9000003, "空数据"),
	/** 未知错误(30) */
	unknow(9000004, "服务端发生错误， 请联系管理员"),
	/** 增加缓存失败(40) */
	addRedisErr(9000005, "增加缓存失败"),
	/** 包含敏感词汇，请更正(70) **/
	hasSensitiveWord(9000006, "包含敏感词汇，请更正"),
	NoPaymentType(9000007, "暂不支持该支付方式"),
	/** token为空或错误(999999) */
	tokenErr(9999999, "请重新登陆");

	/**
	 * 值 Integer型
	 */
	private final Integer value;
	/**
	 * 描述 String型
	 */
	private final String description;

	CodeEnum(Integer value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * 获取值
	 * 
	 * @return value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * 获取描述信息
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	public static CodeEnum getStatusState(Integer value) {
		if (null == value)
			return null;
		for (CodeEnum _enum : CodeEnum.values()) {
			if (value.equals(_enum.getValue()))
				return _enum;
		}
		return null;
	}

	public static CodeEnum getStatusState(String description) {
		if (null == description)
			return null;
		for (CodeEnum _enum : CodeEnum.values()) {
			if (description.equals(_enum.getDescription()))
				return _enum;
		}
		return null;
	}

}