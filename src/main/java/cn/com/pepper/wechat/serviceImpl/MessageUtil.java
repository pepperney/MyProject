package cn.com.pepper.wechat.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import cn.com.pepper.common.model.Constant;
import cn.com.pepper.wechat.model.Image;
import cn.com.pepper.wechat.model.Message;
import cn.com.pepper.wechat.model.News;
import cn.com.pepper.wechat.model.Voice;

//语音，视频，图片 消息的格式一样
public class MessageUtil {

	/**
	 * xml转为map
	 * @Description 
	 * @author niepei
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request)  {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream ins = null;
		try {
			ins = request.getInputStream();// 从request中获取输入流
			Document doc = reader.read(ins);
			Element root = doc.getRootElement();// 获取xml根元素
			List<Element> list = root.elements();// 遍历节点
			for (Element e : list) {
				map.put(e.getName(), e.getText());
			}
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return map;
	}
	
	/**
	 * 消息对象转为xml
	 * 
	 * @Description
	 * @author niepei
	 * @param textMessage
	 * @return
	 */
	private static String MessageToXml(Message message) {
		XStream xstream = new XStream();
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}



	/**
	 * 组装文本消息
	 * 
	 * @Description
	 * @author niepei
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String generateTextMessage(String toUserName, String fromUserName, String content) {
		Message text = new Message();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(Constant.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return MessageUtil.MessageToXml(text);
	}

	/**
	 * 组装语音消息
	 * 
	 * @Description
	 * @author niepei
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String generateVoiceMessage(String toUserName, String fromUserName) {
		Voice voice = new Voice();
		voice.setFormat("mp3");
		voice.setMediaId("_eerShMOgpKQQ2G0XlPeW3umcItqaAxuLo5trj6HcfjPj5WjC7zPpAMqRzV5TkLK");
		voice.setMsgID(24);
		Message voiceMessage = new Message();
		voiceMessage.setFromUserName(toUserName);
		voiceMessage.setToUserName(fromUserName);
		voiceMessage.setMsgType(Constant.MESSAGE_VOICE);
		voiceMessage.setCreateTime(new Date().getTime());
		voiceMessage.setVoice(voice);
		return MessageUtil.MessageToXml(voiceMessage);
	}

	/**
	 * 组装图片消息
	 * 
	 * @Description
	 * @author niepei
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String generateImageMessage(String toUserName, String fromUserName) {
		Image image = new Image();
		image.setMediaId("aJ9HOdbetlcZKYzkyI8rDaVvqxx2ozXnhHd2lv06qId-yCB8SreLY5GD3w03D60A");
		Message imageMessage = new Message();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMsgType(Constant.MESSAGE_IMAGE);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
		return MessageUtil.MessageToXml(imageMessage);
	}

	/**
	 * 组装图文消息
	 * 
	 * @Description
	 * @author niepei
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String generateNewsMessage(String toUserName, String fromUserName) {
		List<News> newsList = new ArrayList<News>();
		Message newsMessage = new Message();

		News news = new News();
		news.setTitle("今日推荐");
		news.setDescription("一个小而美的收录纯音的网站");
		news.setPicUrl("http://pepper.applinzi.com/image/07.jpg");
		news.setUrl("http://www.itingwa.com/");
		newsList.add(news);

		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(Constant.MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());

		return MessageUtil.MessageToXml(newsMessage);
	}



	/**
	 * 自定义文本菜单：主菜单
	 * 
	 * @Description
	 * @author niepei
	 * @return
	 */
	public static String mainMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，请根据菜单选择对应操作：\n");
		sb.append("0、menu\n");
		sb.append("1、poem\n");
		sb.append("2、music\n");
		return sb.toString();
	}

	public static String menu1() {
		StringBuffer sb = new StringBuffer();
		sb.append("          When you are old        \n\n" 
				+ "When you are old and grey and full of sleep\n"
				+ "And nodding by the fire, take down this book.\n" + "And slowly read, and dream of the soft look\n"
				+ "Your eyes had once, and of their shadows deep.\n\n" + "How many loved your moments of glad grace.\n"
				+ "And loved your beauty with love false or true.\n" + "But one man loved the pilgrim soul in you.\n"
				+ "And loved the sorrows of your changing face;\n\n" + "And bending down beside the glowing bars,\n"
				+ "Murmur, a little sadly, how Love fled\n" + "And paced upon the mountains overhead\n"
				+ "And hid his face amid a crowd of stars.\n");
		return sb.toString();
	}





	



}
