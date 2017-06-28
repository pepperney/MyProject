package cn.com.pepper.util;

import java.util.List;


public class ReturnData<T> {

	// 响应码 0 失败 1成功
	private int code;

	// 响应描述
	private String msg;

	// 响应数据
	private Object data = null;

	// 响应数据列表
	private List<T> list;

	// 当前页数
	private int pageNum;

	// 每页显示数
	private int pageSize;

	// 总记录数
	private int total;

	// 总页数
	private int totalPage;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ReturnData [code=" + code + ", data=" + data + ", list=" + list
				+ ", msg=" + msg + ", pageNum=" + pageNum + ", pageSize="
				+ pageSize + ", total=" + total + ", totalPage=" + totalPage
				+ "]";
	}

	
	
}