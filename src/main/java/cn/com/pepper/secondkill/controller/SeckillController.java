package cn.com.pepper.secondkill.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.pepper.secondkill.model.Exposer;
import cn.com.pepper.secondkill.model.Seckill;
import cn.com.pepper.secondkill.model.SeckillExecution;
import cn.com.pepper.secondkill.model.SeckillResult;
import cn.com.pepper.secondkill.service.SeckillService;

@Controller
@RequestMapping("/seckill") 
public class SeckillController {

	
	private Logger logger = LoggerFactory.getLogger(SeckillController.class);
	
	@Autowired
	private SeckillService seckillService;

	/**
	 * 秒杀商品列表
	 * 
	 * @Description
	 * @author niepei
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		logger.debug("-----------------  seckill/list is start    -----------------");
		// 获取列表页
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		logger.debug("-----------------  seckill/list is end      -----------------");
		return "list";
	}

	/**
	 * 秒杀详情
	 * 
	 * @Description
	 * @author niepei
	 * @param seckillId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/detail/{seckillId}", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
		logger.debug("-----------------  seckill/detail is start    -----------------");
		if (seckillId != null) {
			Seckill seckill = seckillService.getById(seckillId);
			model.addAttribute("seckill", seckill);
			if (seckill == null) {
				return "forward:/seckill/list";
			}
		}
		logger.debug("-----------------  seckill/detail is end      -----------------");
		return "detail";
	}

	/**
	 * ajax ,json暴露秒杀接口的方法
	 * 
	 * @Description
	 * @author niepei
	 * @param seckillId
	 * @return
	 */
	@RequestMapping(value = "/exposer/{seckillId}",produces = {"application/json;charset=UTF-8" })
	public @ResponseBody SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
		logger.debug("-----------------  seckill/exposer is start    -----------------");
		SeckillResult<Exposer> result;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			e.printStackTrace();
			result = new SeckillResult<Exposer>(false, e.getMessage());
		}
		logger.debug("-----------------  seckill/exposer is end      -----------------");
		return result;
	}

	/**
	 * 执行秒杀
	 * 
	 * @Description
	 * @author niepei
	 * @param seckillId
	 * @param md5
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/execution/{seckillId}/{md5}", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	public @ResponseBody SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,@PathVariable("md5") String md5, @CookieValue(value = "userPhone", required = false) Long phone) {
		logger.debug("-----------------  seckill/execution is start    -----------------");
		if (phone == null) { 
			return new SeckillResult<SeckillExecution>(false, "未注册");
		}
//		SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
		SeckillExecution execution = seckillService.executeSeckillByProcedure(seckillId, phone, md5);
		logger.debug("-----------------  seckill/execution is end      -----------------");
		return new SeckillResult<SeckillExecution>(true, execution);

	}

	/**
	 * 获取系统时间
	 * 
	 * @Description
	 * @author niepei
	 * @return 
	 */
	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	public @ResponseBody SeckillResult<Long> time() {
		Date now = new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}
}
