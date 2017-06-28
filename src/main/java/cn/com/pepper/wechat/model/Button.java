package cn.com.pepper.wechat.model;

public class Button {
	
	private String type;// 菜单类型
	
	private String name;// 菜单名称
	
	private Button[] sub_button;// 二级菜单

	/** 以上为菜单按钮基本属性 */

	private String url;// view类型菜单url

	private String key;// click类型菜单key

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] subButton) {
		sub_button = subButton;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
