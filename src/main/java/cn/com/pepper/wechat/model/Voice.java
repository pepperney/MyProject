package cn.com.pepper.wechat.model;



public class Voice {
    
	private String MediaId;//媒体id
	
	private String Format;//格式
	
	private long MsgID;//消息id
	
	
	public String getMediaId()
	{
		return MediaId;
	}
	public void setMediaId(String mediaId)
	{
		MediaId = mediaId;
	}
	public String getFormat()
	{
		return Format;
	}
	public void setFormat(String format)
	{
		Format = format;
	}
	public long getMsgID()
	{
		return MsgID;
	}
	public void setMsgID(long msgID)
	{
		MsgID = msgID;
	}
	
}
