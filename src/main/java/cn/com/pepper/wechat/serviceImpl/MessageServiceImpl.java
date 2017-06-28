package cn.com.pepper.wechat.serviceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.pepper.common.model.Constant;
import cn.com.pepper.util.SHA1Util;
import cn.com.pepper.wechat.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Override
	public boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] { Constant.WECHAT_TOKEN, timestamp, nonce };
		// 排序
		Arrays.sort(arr);
		// 生成字符串
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		// sha1加密
		String temp = SHA1Util.getSha1(content.toString());
		return temp.equals(signature);
	}

	@Override
	public String handleMessage(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		map = MessageUtil.xmlToMap(request);
		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		String msgType = map.get("MsgType");
		String content = map.get("Content");
		String message = null;
		if (Constant.MESSAGE_TEXT.equals(msgType)) {
			if ("0".equals(content)){// 显示主菜单
				message = MessageUtil.generateTextMessage(toUserName, fromUserName, MessageUtil.mainMenu());
			} else if ("1".equals(content)){// 回复文本消息			
				message = MessageUtil.generateTextMessage(toUserName, fromUserName, MessageUtil.menu1());
			} else if ("2".equals(content)){// 回复图文消息
				message = MessageUtil.generateNewsMessage(toUserName, fromUserName);
			} else if ("3".equals(content)){// 回复图片消息
				message = MessageUtil.generateImageMessage(toUserName, fromUserName);
			} else if ("4".equals(content)){// 回复语音消息
				message = MessageUtil.generateVoiceMessage(toUserName, fromUserName);
			}
		} else if (Constant.MESSAGE_EVNET.equals(msgType)){// 事件消息
			String eventType = map.get("Event");
			if (Constant.MESSAGE_SUBSCRIBE.equals(eventType)) {
				message = MessageUtil.generateTextMessage(toUserName, fromUserName, MessageUtil.mainMenu());
			} else if (Constant.MESSAGE_CLICK.equals(eventType)) {
				message = MessageUtil.generateTextMessage(toUserName, fromUserName, MessageUtil.mainMenu());
			} else if (Constant.MESSAGE_VIEW.equals(eventType)) {
				message = MessageUtil.generateTextMessage(toUserName, fromUserName, map.get("EventKey"));
			} else if (Constant.MESSAGE_SCANCODE.equals(eventType)) {
				message = MessageUtil.generateTextMessage(toUserName, fromUserName, map.get("EventKey"));
			}
		} else if (Constant.MESSAGE_LOCATION.equals(msgType)){// 地理位置消息
			message = MessageUtil.generateTextMessage(toUserName, fromUserName, map.get("Label"));
		}
		logger.debug("------------------------------------------------------>>\n " + message);
		return message;

	}

}
