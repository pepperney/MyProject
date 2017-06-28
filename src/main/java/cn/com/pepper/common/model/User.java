package cn.com.pepper.common.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User {
	 private Integer userid;

	    private String username;

	    private String realname;

	    private String password;

	    private String email;

	    private Integer mobile;

	    private Integer qq;

	    private String openid;

	    private String nickname;

	    private String headimgurl;

	    private Integer usertype;

	    private Integer userstatus;

		public Integer getUserid() {
			return userid;
		}

		public void setUserid(Integer userid) {
			this.userid = userid;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getRealname() {
			return realname;
		}

		public void setRealname(String realname) {
			this.realname = realname;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getMobile() {
			return mobile;
		}

		public void setMobile(Integer mobile) {
			this.mobile = mobile;
		}

		public Integer getQq() {
			return qq;
		}

		public void setQq(Integer qq) {
			this.qq = qq;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getHeadimgurl() {
			return headimgurl;
		}

		public void setHeadimgurl(String headimgurl) {
			this.headimgurl = headimgurl;
		}

		public Integer getUsertype() {
			return usertype;
		}

		public void setUsertype(Integer usertype) {
			this.usertype = usertype;
		}

		public Integer getUserstatus() {
			return userstatus;
		}

		public void setUserstatus(Integer userstatus) {
			this.userstatus = userstatus;
		}

		@Override
		public String toString() {
			return "User [userid=" + userid + ", username=" + username + ", realname=" + realname + ", password="
					+ password + ", email=" + email + ", mobile=" + mobile + ", qq=" + qq + ", openid=" + openid
					+ ", nickname=" + nickname + ", headimgurl=" + headimgurl + ", usertype=" + usertype
					+ ", userstatus=" + userstatus + "]";
		}
}