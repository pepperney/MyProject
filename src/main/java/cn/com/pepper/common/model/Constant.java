package cn.com.pepper.common.model;

public class Constant {

	public static final String LOGIN_SALT = "pepper";

	public static final int SUCCEED = 1;
	public static final int FAILED = 0;

	public static final int YES = 1;
	public static final int NO = 0;

	// 秒杀系统
	/** 秒杀成功 */
	public static final int SECKILL_SUCCEESD = 1;
	/** 秒杀失败 */
	public static final int SECKILL_FAILED = 0;
	/** 秒杀结束 */
	public static final int SECKILL_END = -1;
	/** 重复秒杀 */
	public static final int REPEAT_KILL = -2;
	/** 系统异常 */
	public static final int INNER_ERROR = -3;
	/** 数据篡改 */
	public static final int DATE_REWRITE = -4;

	// 微信公众号相关参数
	public static final String WECHAT_TOKEN = "enjoyit";
	// public static final String WECHAT_APP_ID = "wx824d933e0741757e";
	// public static final String WECHAT_APP_SECRET = "efac12fb2b8a060727080a4ee1c537f7 ";
	/************** 测试号 **********************************/
	public static final String WECHAT_APP_ID = "wxb8e38e0360a34680";
	public static final String WECHAT_APP_SECRET = "40f8fce447252a324e9bac84823d9ba0";

	// 获取微信相关功能的URL
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WECHAT_APP_ID+"&secret="+WECHAT_APP_SECRET;
	public static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	public static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static final String HISTORY_MESSAGE_URL = "http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzIxMjI3NTIxMA==#wechat_webview_type=1&wechat_redirect";

	// 微信网页授权需要使用的相关URL
	public static final String GET_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WECHAT_APP_ID+"&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=state#wechat_redirect";
	public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WECHAT_APP_ID+"&secret="+WECHAT_APP_SECRET+"&code=CODE&grant_type=authorization_code";
	
	// 微信消息类型参数
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVNET = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE = "scancode_push";

	// 验证码常量
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";

}
