package cn.com.pepper.wechat.serviceImpl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.pepper.common.model.Constant;
import cn.com.pepper.util.HttpUtil;
import cn.com.pepper.wechat.model.AccessToken;
import cn.com.pepper.wechat.model.Button;
import cn.com.pepper.wechat.model.Menu;

public class WeChatUtil {
	
	private Logger logger = LoggerFactory.getLogger(WeChatUtil.class);
	
	private static AccessToken ACCESS_TOKEN;
	
	private static final String REDIRECT_URL = "http://3e77cd2d.ngrok.io/MyProject/general/getOpenId";
	
	/**
	 * 请求微信服务器获取accessToken
	 * 
	 * @Description
	 * @author niepei
	 */
	public static AccessToken getAccessToken() {
		if (null == ACCESS_TOKEN || ACCESS_TOKEN.isExpired()) {
			String url = Constant.ACCESS_TOKEN_URL;
			JSONObject jsonObject = null;
			jsonObject = HttpUtil.doGet(url);
			if (ACCESS_TOKEN == null) {
				ACCESS_TOKEN = new AccessToken();
			}
			if (jsonObject != null) {
				ACCESS_TOKEN.setAccessToken(jsonObject.getString("access_token"));
				ACCESS_TOKEN.setExpiresIn(jsonObject.getInteger("expires_in"));
				ACCESS_TOKEN.setCreateTime(new Date());
			}
		}
		return ACCESS_TOKEN;
	}

	/**
	 * 文件上传
	 * 
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static String upload(String filePath, String accessToken, String type)throws Exception  {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		String url = Constant.UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		URL urlObj = new URL(url);
		
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();// 连接
		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		StringBuilder sb = new StringBuilder();
		sb.append("--");
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
		// 把文件以流文件的方式 推入到url中
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
		String result = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSON.parseObject(result);
		return jsonObj.getString("media_id");
	}


	
	/**
	 * 组装菜单
	 * 
	 * @Description
	 * @author niepei
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static Menu generateMenu() throws UnsupportedEncodingException {

		Menu menu = new Menu();

		Button button1 = new Button();// 第一列以及菜单，click菜单
		button1.setName("imitate");
		button1.setType("click");
		button1.setKey("1");

		Button button2 = new Button();// 第二列一级菜单，view菜单
		button2.setName("index");
		button2.setType("view");
		button2.setUrl(Constant.GET_CODE_URL.replace("REDIRECT_URI", URLEncoder.encode(REDIRECT_URL, "UTF-8")).replace("SCOPE", "snsapi_base"));

				
		Button button3_1 = new Button();// 第三列一级菜单的子菜单的第一行，扫码事件
		button3_1.setName("scan");
		button3_1.setType("scancode_push");
		button3_1.setKey("3_1");

		Button button3_2 = new Button();// 第三列一级菜单的子菜单的第二行，地理位置
		button3_2.setName("location");
		button3_2.setType("location_select");
		button3_2.setKey("3_2");

		Button button3_3 = new Button();// 第三列一级菜单的子菜单的第三行，历史消息
		button3_3.setName("history");
		button3_3.setType("view");
		button3_3.setUrl(Constant.HISTORY_MESSAGE_URL);

		Button button3 = new Button();// 第三列一级菜单
		button3.setName("try");
		button3.setSub_button(new Button[] { button3_1, button3_2,button3_3});

		menu.setButton(new Button[] { button1, button2, button3 });
		return menu;
	}
	
	
	
	
	/**
	 * 创建菜单
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public void createMenu() throws ParseException, IOException {
		
		String url = null;
		JSONObject jsonObject = null;
		int result = 0;
		String accessToken = getAccessToken().getAccessToken();
		logger.debug("------------------------------------------------------>accessToken" + accessToken);
		
		// 查询原有菜单
		url = Constant.QUERY_MENU_URL.replace("ACCESS_TOKEN", accessToken);
		jsonObject = HttpUtil.doGet(url);
		logger.debug("------------------------------------------------------>原菜单" + jsonObject);
		
		// 删除旧菜单
		url = Constant.DELETE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
		jsonObject = HttpUtil.doGet(url);
		if (jsonObject != null && (result = jsonObject.getInteger("errcode"))==0) {
			logger.debug("------------------------------------------------------> 删除成功");	
		}else {
			logger.debug("------------------------------------------------------>" + result);
		}
	
		// 创建新菜单
		String menu = JSON.toJSON(generateMenu()).toString();
		url = Constant.CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
		jsonObject = HttpUtil.doPost(url, menu);
		if (jsonObject != null && (result = jsonObject.getInteger("errcode"))==0) {
			logger.debug("------------------------------------------------------>创建菜单成功");
		}else {
			logger.debug("------------------------------------------------------>" + result);
		}

	
	}
	
	/**
	 * 上传媒体文件
	 * @throws Exception
	 */
	public void uploadMedia() throws Exception {
		AccessToken token = getAccessToken();
		logger.debug("----------------------->请求令牌：" + token.getAccessToken() + ";有效时间：" + token.getExpiresIn());

		String imagePath = "D:/07.jpg";
		String imageId = upload(imagePath, token.getAccessToken(), "image");
		logger.debug("------------------------------------------------------> imageId:" + imageId);

		String audioPath = "D:/WoodyWoody.mp3";
		String audioId = upload(audioPath, token.getAccessToken(), "voice");
		logger.debug("------------------------------------------------------> audioId:" + audioId);
	}
	
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String menu = JSON.toJSON(generateMenu()).toString();
		System.out.println(menu);
	}

}
