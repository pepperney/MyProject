包结构说明：
=====

## cn.com.learn 包主要是学习，看书的代码 ##

- `cn.com.learn.dataStructure `	读《JAVA数据结构和算法》第1至11章
	
- `cn.com.learn.designPattern` 	读《设计模式之禅》
	
- `cn.com.learn.io`             读《分布式JAVA应用基础与实践》第1章

- `cn.com.learn.thread`         读《JAVA多线程编程核心技术》第1至4章
		
- `cn.com.learn.clone`   		采用两种方式实现深度克隆
	
- `cn.com.learn.dynamicProxy`   反射以及动态代理相关内容
	
## cn.com.pepper 包为自己做的或者看到的一些项目 ##

	
- `cn.com.pepper.common`      一个简单的ssm项目，分层为 controller->service->serviceImpl->dao->mapper，所有的pojo,vo,bo对象均放在model包
	
- `cn.com.pepper.util`        工具包，包括封装的redis工具类以及基于redis实现的分布式锁，md5和SHA1加密工具类,序列化工具类，封装的HttpClient工具类等
	
- `cn.com.pepper.security`    使用SpringMVC拦截器实现登录拦截，过滤除静态文件之外的几乎所有请求
	
- `cn.com.pepper.exception`   自定义异常以及异常处理器
	
- `cn.com.pepper.secondkill`  模拟高并发环境下的商品秒杀系统，ssm架构，cdn存储前端css和js组件，redis做数据缓存，存储过程实现秒杀操作
	
- `cn.com.pepper.wechat` 	  微信公众号开发相关，ssm架构，实现自定义菜单，微信绑定，获取个人用户信息，各种类型的消息推送与回复

