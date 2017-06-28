package cn.com.pepper.wechat.model;

import java.util.Date;

public class AccessToken {

	private int expiresIn;
	private Date createTime;
	private String accessToken;
	private String openid;
	private String scope;

	public boolean isExpired() {
		long returnLong = createTime.getTime() - new Date().getTime();
		// 提前5分钟失效，保证连续性
		return returnLong + expiresIn - 300000 < 0;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
