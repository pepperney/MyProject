package cn.com.pepper.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("fileService")
public class FileService {

	private Logger logger = LoggerFactory.getLogger(FileService.class);

	/**
	 * 文件上传方法
	 * 
	 * @Description
	 * @author niepei
	 * @param file
	 * @param request
	 * @return
	 */
	public String upload(MultipartFile file, HttpServletRequest request) {
		String folder = request.getParameter("folder");
		if (folder == null) {
			folder = "folder";
		}
		String absolutePath = null;
		String fileName = file.getOriginalFilename();// 得到上传时的文件名
		if (!file.isEmpty()) {
			ServletContext sc = request.getSession().getServletContext();
			String dir = sc.getRealPath("/" + folder);// 设定文件保存的目录
			String strs[] = fileName.split("\\.");
			fileName = "";
			for (int i = 0; i < strs.length - 1; i++) {
				fileName += strs[i];
			}
			fileName = String.valueOf(System.currentTimeMillis()) + "." + strs[strs.length - 1];// 避免文件名重复
																								// --重命名文件
			try {
				FileUtils.writeByteArrayToFile(new File(dir, fileName), file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			absolutePath = dir + "\\" + fileName;
			logger.debug("------------------------>  absolutePath is : " + absolutePath);
		}
		return "/" + folder + "/" + fileName;
	}

	/**
	 * 文件下载方法
	 * 
	 * @Description
	 * @author niepei
	 * @param filename
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public ResponseEntity<byte[]> download(String filename, HttpServletRequest request) throws IOException {
		ResponseEntity<byte[]> file = null;
		ServletContext sc = request.getSession().getServletContext();
		String sourceUrl = sc.getRealPath(filename);// 设定文件保存的目录
		logger.debug("------------------------>  sourceUrl is : " + sourceUrl);
		if (null != filename && !"".equals(filename.trim())) {
			File sourceFile = new File(sourceUrl);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", new String(filename.getBytes("UTF-8"), "ISO8859-1"));
			file = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(sourceFile), headers, HttpStatus.OK);
		}
		return file;
	}

	@Deprecated
	public String uploadFile(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {

		String folder = request.getParameter("folder");
		folder = folder == null ? "folder" : folder;
		String absolutePath = null;
		if (!file.isEmpty()) {
			String localFile = this.getClass().getClassLoader().getResource("").getPath();
			localFile = localFile.replace("WEB-INF/", "").replace("WEB-INF\\", "").replace("classes", folder);
			File newFile = new File(localFile);
			if (!newFile.exists()) {
				newFile.mkdir();
			}
			File newFile2 = new File(newFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
			String UUID = UUIDGenerator.getUUID();
			absolutePath = newFile2.getAbsolutePath();
			int index1 = absolutePath.lastIndexOf(File.separator);
			int index2 = absolutePath.lastIndexOf(".");
			String fileName = absolutePath.substring(index1 + 1, index2);
			absolutePath = absolutePath.replace(fileName, UUID);
			newFile2.renameTo(new File(absolutePath));
			file.transferTo(new File(absolutePath));
			return absolutePath;
		}
		return absolutePath;
	}

}
