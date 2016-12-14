package weixin.guanjia.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;
import org.jeecgframework.web.system.service.SystemService;

import net.sf.json.JSONObject;
import weixin.guanjia.core.entity.common.AccessToken;
import weixin.guanjia.core.entity.common.WeixinMedia;
import weixin.guanjia.core.entity.model.AccessTokenYw;

/**
 * 公众平台通用接口工具类
 * 
 * @author liuyq
 * @date 2013-08-09
 */
public class WeixinUtil {
    public static final transient Logger LOGGER = Logger.getLogger(WeixinUtil.class);
    // 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	// 客服接口地址
	public static String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	// 创建分组
	public static String create_group_url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";

	// 更新分组
	public static String update_group_url = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";

	//删除分组-刘晓春-2015年11月30日
	public static String del_group_url="https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";

	// 查询所有分组
	public static String group_getall_url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";

	// 根据OpenID列表群发
	public static String send_openid_message_url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";

	// 根据分组进行群发
	public static String send_group_message_url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

	// 获取微信关注用户列表
	public static String download_member_list_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";

	// 获取微信关注用户详细信息
	public static String download_member_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	// 获取用户所在分组ID
	public static String download_member_groupid_info_url = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";

	// 移动用户分组
	public static String update_member_groupid_url = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";

	// 批量移动用户分组
	public static String update_members_groupid_url = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";

	// 上传多媒体文件
	public static String upload_media_url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN";

	// 上传图文消息素材
	public static String uploadnews_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";

	// 上传永久图文素材
	public static String upload_permanent_news_url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";

	// 上传修改永久图文素材
	public static String update_permanent_news_url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";

	// 删除永久素材
	public static String del_permanent_news_url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";

	// 上传门店
	public static String audit_location_url = "https://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=ACCESS_TOKEN";

	// 上传门店
	public static String create_location_url = "https://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=ACCESS_TOKEN";

	// 删除门店
	public static String del_location_url = "https://api.weixin.qq.com/cgi-bin/poi/delpoi?access_token=ACCESS_TOKEN";

	// 修改门店信息
	public static String update_location_url = "https://api.weixin.qq.com/cgi-bin/poi/updatepoi?access_token=ACCESS_TOKEN";

	// 拉取门店列表
	public static String load_location_url = "https://api.weixin.qq.com/cgi-bin/poi/getpoilist?access_token=ACCESS_TOKEN";

	// 上传门店LOGO
	public static String upload_location_logo_url = "https://file.api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";

	// 上传卡券LOGO
	public static String upload_card_logo_url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";

	//拉取卡券列表
	public static String load_card_url = "https://api.weixin.qq.com/card/batchget?access_token=ACCESS_TOKEN";
	
	//拉取卡券详情
	public static String load_cardinfo_url = "https://api.weixin.qq.com/card/get?access_token=ACCESS_TOKEN";
	
	public static String del_card_url = "https://api.weixin.qq.com/card/delete?access_token=ACCESS_TOKEN";
	
	// 创建卡券，获取卡券ID
	public static String create_card_url = "https://api.weixin.qq.com/card/create?access_token=ACCESS_TOKEN";

	// 创建卡券二维码接口
	public static String create_cardqrcode_url = "https://api.weixin.qq.com/card/qrcode/create?access_token=ACCESS_TOKEN";

	// 通过ticket换取二维码
	public static String create_qrcode_byticket_url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";

	// 获取卡券嵌入图文消息
	public static String create_cardarticle_url = "https://api.weixin.qq.com/card/code/get?access_token=ACCESS_TOKEN";

	// 核销卡券
	public static String consume_card_url = "https://api.weixin.qq.com/card/code/consume?access_token=TOKEN";

	// 添加设备
	public static String create_bizwifi_url = "https://api.weixin.qq.com/bizwifi/device/add?access_token=ACCESS_TOKEN";

	// 查询设备
	public static String load_bizwifi_url = "https://api.weixin.qq.com/bizwifi/device/list?access_token=ACCESS_TOKEN";

	// 删除设备
	public static String del_bizwifi_url = "https://api.weixin.qq.com/bizwifi/device/delete?access_token=ACCESS_TOKEN";

	// 获取物料二维码
	public static String get_qrcode_url = "https://api.weixin.qq.com/bizwifi/qrcode/get?access_token=ACCESS_TOKEN";

	//分享调用的地址
	public static String share_url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=accessToken&type=jsapi";

	//jsapi_ticket获取地址
	public static String jsapi_ticket_url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	//
	// private static final ResourceBundle bundle =
	// ResourceBundle.getBundle("weixin");

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
			// jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			org.jeecgframework.core.util.LogUtil.info("连接微信服务器超时,请检查网络设置 java.net.ConnectException: Connection timed out: connect");
		}catch (Exception e) {
			org.jeecgframework.core.util.LogUtil.info("https request error:{}" + e.getMessage(), e);
		}
		return jsonObject;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(SystemService systemService, String appid, String appsecret) {
		// 第三方用户唯一凭证
		// String appid = bundle.getString("appId");
		// // 第三方用户唯一凭证密钥
		// String appsecret = bundle.getString("appSecret");

		AccessTokenYw accessTocken = getRealAccessToken(systemService);

		if (accessTocken != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date end = new java.util.Date();
			java.util.Date start = new java.util.Date(accessTocken.getAddTime().getTime());
			if (end.getTime() - accessTocken.getAddTime().getTime() > accessTocken.getExpires_in() * 1000) {
				AccessToken accessToken = null;
				String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
				JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
				// 如果请求成功
				if (null != jsonObject) {
					try {
						accessToken = new AccessToken();
						accessToken.setToken(jsonObject.getString("access_token"));
						accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
						// 凭证过期更新凭证
						AccessTokenYw atyw = new AccessTokenYw();
						atyw.setId(accessTocken.getId());
						atyw.setExpires_in(jsonObject.getInt("expires_in"));
						atyw.setAccess_token(jsonObject.getString("access_token"));
						updateAccessToken(atyw, systemService);
					} catch (Exception e) {
						accessToken = null;
						// 获取token失败
						String wrongMessage = "获取token失败 errcode:{} errmsg:{}" + jsonObject.getInt("errcode")
								+ jsonObject.getString("errmsg");
						org.jeecgframework.core.util.LogUtil.info(wrongMessage);
					}
				}
				return accessToken;
			} else {

				AccessToken accessToken = new AccessToken();
				accessToken.setToken(accessTocken.getAccess_token());
				accessToken.setExpiresIn(accessTocken.getExpires_in());
				return accessToken;
			}
		} else {

			AccessToken accessToken = null;
			String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					accessToken = new AccessToken();
					accessToken.setToken(jsonObject.getString("access_token"));
					accessToken.setExpiresIn(jsonObject.getInt("expires_in"));

					AccessTokenYw atyw = new AccessTokenYw();
					atyw.setExpires_in(jsonObject.getInt("expires_in"));
					atyw.setAccess_token(jsonObject.getString("access_token"));
					saveAccessToken(atyw, systemService);

				} catch (Exception e) {
					accessToken = null;
					// 获取token失败
					String wrongMessage = "获取token失败 errcode:{} errmsg:{}" + jsonObject.getInt("errcode")
							+ jsonObject.getString("errmsg");
					org.jeecgframework.core.util.LogUtil.info(wrongMessage);
				}
			}
			return accessToken;
		}
	}

	/**
	 * 从数据库中读取凭证
	 * 
	 * @return
	 */
	public static AccessTokenYw getRealAccessToken(SystemService systemService) {
		List<AccessTokenYw> accessTockenList = systemService.findByQueryString("from AccessTokenYw");
		return accessTockenList.get(0);
	}

	/**
	 * 保存凭证
	 * 
	 * @return
	 */
	public static void saveAccessToken(AccessTokenYw accessTocken, SystemService systemService) {
		systemService.save(accessTocken);
	}

	/**
	 * 更新凭证
	 * 
	 * @return
	 */
	public static void updateAccessToken(AccessTokenYw accessTocken, SystemService systemService) {
		String sql = "update accesstoken set access_token='" + accessTocken.getAccess_token() + "',expires_ib="
				+ accessTocken.getExpires_in() + ",addtime=now() where id='" + accessTocken.getId() + "'";
		systemService.updateBySqlString(sql);
	}

	/**
	 * 编码
	 * 
	 * @param bstr
	 * @return String
	 */
	public static String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	public static byte[] decode(String str) {

		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bt;

	}

	/**
	 * 上传媒体文件
	 * 
	 * @param accessToken
	 * @param type
	 *            媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @param media
	 *            form-data中媒体文件标识，有filename、filelength、content-type等信息
	 * @param mediaFileUrl
	 *            媒体文件的url
	 * @return
	 */
	public static WeixinMedia uploadMedia(String accessToken, String type, String mediaFileUrl) {

		WeixinMedia weixinMedia = null;

		String uploadMediaUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

		uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);

		// 定义数据分隔符
		String boundary = "------------7da2e536604c8";
		try {

			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();

			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
			meidaConn.setDoOutput(true);
			meidaConn.setRequestMethod("GET");

			// 从请求头中获取内容类型
			String contentType = meidaConn.getHeaderField("Content-Type");

			// 根据内容类型判断文件扩展名
			// String fileExt = WeixinUtil.getFileEndWitsh(contentType);
			String fileExt = mediaFileUrl.substring(mediaFileUrl.lastIndexOf(".")).toLowerCase();

			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(
					String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n", fileExt)
							.getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			meidaConn.disconnect();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();

			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();

			// 使用JSON-lib解析返回结果
			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());

			// 测试打印结果
			LOGGER.info("打印测试结果" + jsonObject);
			weixinMedia = new WeixinMedia();
			weixinMedia.setType(jsonObject.getString("type"));
			// type等于 缩略图（thumb） 时的返回结果和其它类型不一样
			if ("thumb".equals(type))
				weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
			else
				weixinMedia.setMediaId(jsonObject.getString("media_id"));
			weixinMedia.setCreatedAt(jsonObject.getInt("created_at"));

		} catch (Exception e) {

			weixinMedia = null;
			String error = String.format("上传媒体文件失败：%s", e);
			LOGGER.info(error);
		}

		return weixinMedia;
	}

	/**
	 * 上传永久媒体文件
	 * 
	 * @param accessToken
	 * @param type
	 *            媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @param media
	 *            form-data中媒体文件标识，有filename、filelength、content-type等信息
	 * @param mediaFileUrl
	 *            媒体文件的url
	 * @return
	 */
	public static WeixinMedia uploadPermanentMedia(String accessToken, String type, String mediaFileUrl) {

		WeixinMedia weixinMedia = null;

		String uploadMediaUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";

		// String uploadMediaUrl =
		// "http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";//
		// 永久素材接口

		uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);

		// 定义数据分隔符
		String boundary = "------------7da2e536604c8";
		try {

			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();

			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
			meidaConn.setDoOutput(true);
			meidaConn.setRequestMethod("GET");

			// 从请求头中获取内容类型
			String contentType = meidaConn.getHeaderField("Content-Type");

			// 根据内容类型判断文件扩展名
			// String fileExt = WeixinUtil.getFileEndWitsh(contentType);
			String fileExt = mediaFileUrl.substring(mediaFileUrl.lastIndexOf(".")).toLowerCase();

			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(
					String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n", fileExt)
							.getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			meidaConn.disconnect();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();

			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();

			// 使用JSON-lib解析返回结果
			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());

			// 测试打印结果
			LOGGER.info("打印测试结果" + jsonObject);
			weixinMedia = new WeixinMedia();
			weixinMedia.setType(type);
			weixinMedia.setMediaId(jsonObject.getString("media_id"));

		} catch (Exception e) {

			weixinMedia = null;
			String error = String.format("上传媒体文件失败：%s", e);
			LOGGER.info(error);
		}

		return weixinMedia;
	}

	/**
	 * 上传logo图片
	 * 
	 * @param accessToken
	 * @param mediaFileUrl
	 * @return 图片地址
	 */
	public static String uploadLocationLogo(String accessToken, String uploadMediaUrl, String mediaFileUrl) {

		String logoUrl = "";

		uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken);

		// 定义数据分隔符
		String boundary = "------------7da2e536604c8";
		try {

			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();

			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
			meidaConn.setDoOutput(true);
			meidaConn.setRequestMethod("GET");

			// 从请求头中获取内容类型
			String contentType = meidaConn.getHeaderField("Content-Type");

			// 根据内容类型判断文件扩展名
			// String fileExt = WeixinUtil.getFileEndWitsh(contentType);
			String fileExt = mediaFileUrl.substring(mediaFileUrl.lastIndexOf(".")).toLowerCase();

			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(
					String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n", fileExt)
							.getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			meidaConn.disconnect();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();

			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();

			// 使用JSON-lib解析返回结果
			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());

			if (null != jsonObject.getString("url"))
				logoUrl = jsonObject.getString("url");
		} catch (Exception e) {

			String error = String.format("上传媒体文件失败：%s", e);
			LOGGER.info(error);
		}

		return logoUrl;
	}

	/**
	 * linux服务器上传图片
	 * 
	 * @param accessToken
	 * @param url
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String uploadLocationLogoByLocal(String accessToken, String url, String filePath) throws IOException {
		String logoUrl = "";
		String result = null;
		url = url.replace("ACCESS_TOKEN", accessToken);
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		/**
		 * 第一部分
		 */
		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		/**
		 * 设置关键值
		 */
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		// 请求正文信息

		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// LOGGER.info(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			LOGGER.info("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}

		}
		// 使用JSON-lib解析返回结果
		// 使用JSON-lib解析返回结果
		JSONObject jsonObject = JSONObject.fromObject(buffer.toString());

		if (null != jsonObject.getString("url"))
			logoUrl = jsonObject.getString("url");

		return logoUrl;
	}

	public static String uploadThumbMedia(String accessToken, String type, String filePath) throws IOException {

		// String url =
		// "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";

		// String uploadMediaUrl =
		// "http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";//
		// 永久素材接口

		// uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN",
		// accessToken).replace("TYPE", type);

		String logoUrl = "";
		String result = null;
		url = url.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		/**
		 * 第一部分
		 */
		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		/**
		 * 设置关键值
		 */
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		// 请求正文信息

		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);

		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// LOGGER.info(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			LOGGER.info("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}

		}
		// 使用JSON-lib解析返回结果
		// 使用JSON-lib解析返回结果
		JSONObject jsonObject = JSONObject.fromObject(buffer.toString());

		if (null != jsonObject.getString("media_id"))
			logoUrl = jsonObject.getString("media_id");

		return logoUrl;
	}

	public static WeixinMedia uploadMediaByLocal(String token, String type, File file) throws IOException {
		WeixinMedia weixinMedia = null;
		if (file == null || token == null || type == null) {
			return null;
		}

		if (!file.exists()) {
			return null;
		}
		// String uploadMediaUrl =
		// "http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";//
		// 永久素材接口
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material";
		JSONObject jsonObject = null;
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Connection", "Keep-Alive");
		post.setRequestHeader("Cache-Control", "no-cache");
		FilePart media = null;
		HttpClient httpClient = new HttpClient();
		// 信任任何类型的证书
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);

		try {
			media = new FilePart("media", file);
			Part[] parts = new Part[] { new StringPart("access_token", token), new StringPart("type", type), media };
			MultipartRequestEntity entity = new MultipartRequestEntity(parts, post.getParams());
			post.setRequestEntity(entity);
			int status = httpClient.executeMethod(post);
			if (status == HttpStatus.SC_OK) {
				String text = post.getResponseBodyAsString();
				jsonObject = JSONObject.fromObject(text);
				if (jsonObject.containsKey("errcode")) {
					if (jsonObject.get("errcode").toString().equals("45009")) {
						return weixinMedia;
					}
				}
				weixinMedia = new WeixinMedia();
				weixinMedia.setType(type);
				// type等于 缩略图（thumb） 时的返回结果和其它类型不一样
				if ("thumb".equals(type))
					weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
				else
					weixinMedia.setMediaId(jsonObject.getString("media_id"));
			} else {
				throw new IOException("数据读取异常");
			}
		} catch (FileNotFoundException execption) {

		} catch (HttpException execption) {

		} catch (IOException execption) {
		}
		return weixinMedia;
	}

	/**
	 * 上传多媒体文件
	 * @param token
	 * @param type
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String uploadMediaForLocal(String token, String type, File file) throws IOException {

		String result = null;

		String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
		url = url.replace("ACCESS_TOKEN", token).replace("TYPE", type);
		/**
		 * 第一部分
		 */
		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		/**
		 * 设置关键值
		 */
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		// 请求正文信息

		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// LOGGER.info(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			LOGGER.info("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}

		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		String mediaId = jsonObj.getString("media_id");
		return mediaId;
	}
	
	/**
	 * 判断文件大小
	 * @param url
	 * @return
	 */
	public static boolean checkSize(String url){
		
		FileInputStream fis= null;  
	    try{  
	        File f= new File(url);  
	        fis= new FileInputStream(f);  
	        long size = fis.available();  
	        if(size > 1024)
	        	return true;
	    }catch(Exception e){
	    	
	    	return false;
	    }
	    return false;
	}
}