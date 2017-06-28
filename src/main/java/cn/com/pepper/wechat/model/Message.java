package cn.com.pepper.wechat.model;

import java.util.List;

/**
 * 
 * @Description 消息父类
 * @author niepei
 * @date 2016年9月21日 下午10:05:30
 * @version V1.3.1
 */
public class Message {
	
	/** 以下四个属性为公共属性 */
	private String ToUserName;// 接收方微信号
	private String FromUserName;// 发送方微信号
	private long CreateTime;// 创建时间
	private String MsgType;// 消息类型
	
	/** 下面属性为图片消息所有 */
	private Image Image;// 图片消息
	
	/** 下面属性为语音消息所有 */
	private Voice Voice;// 语音消息
	
	/** 以下两个属性为图文消息所有 */
	private int ArticleCount;// 文章数量
	private List<News> Articles;// 文章列表
	
	/** 以下两个属性为文本消息所有 */
	private String Content;// 文本内容
	private String MsgId;// 消息Id

	
	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<News> getArticles() {
		return Articles;
	}

	public void setArticles(List<News> articles) {
		Articles = articles;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

}