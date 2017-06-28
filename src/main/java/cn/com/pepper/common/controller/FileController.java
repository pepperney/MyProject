package cn.com.pepper.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.pepper.common.model.Constant;
import cn.com.pepper.util.FileService;
import cn.com.pepper.util.ReturnData;
@Controller
@RequestMapping("file")
public class FileController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private FileService fileService;
	/**
	 * 上传
	 * @Description 
	 * @author niepei
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="upload", method = RequestMethod.POST)
	public @ResponseBody ReturnData<String> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		logger.debug("-----------------  upload is start    -----------------");
		ReturnData<String> rd = new ReturnData<String>();
		String url = fileService.upload(file, request);
		rd.setCode(Constant.SUCCEED);
		rd.setMsg("upload success");
		rd.setData(url);
		logger.debug("-----------------  upload is end    -----------------");
		return rd;
	}
	
	
	/**
	 * 下载
	 * @Description 
	 * @author niepei
	 * @param filename
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@RequestParam("filename") String filename, HttpServletRequest request) throws Exception {
		logger.debug("-----------------  download is start    -----------------");
		ResponseEntity<byte[]> file = fileService.download(filename,request);
		logger.debug("-----------------  download is end    -----------------");
		return file;
	}
	

}
