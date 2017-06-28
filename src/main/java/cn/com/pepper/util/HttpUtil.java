package cn.com.pepper.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {

	
	/**
	 * 执行get请求获取json对象
	 * @param url
	 * @return
	 */
	public static JSONObject doGet(String url) {
		JSONObject jsonObject = new JSONObject();
		HttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
				jsonObject = jsonObject.getJSONObject(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	
	
	/**
	 * 执行post请求获取json对象
	 * @param url 请求路径
	 * @param jsonStr  请求参数
	 * @return
	 */
	public static JSONObject doPost(String url,String jsonStr){
		JSONObject jsonObject = new JSONObject();
		HttpClient client = HttpClients.createDefault();
		HttpPost httpost = new HttpPost(url);
		httpost.setEntity(new StringEntity(jsonStr, "UTF-8"));
		try {
			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			jsonObject = jsonObject.getJSONObject(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

}
