package cn.xvkang.ueditor.controller;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.ueditor.ActionEnter;

@RestController
@RequestMapping("/ueditor")
public class UeditorController {

	@GetMapping("/test")
	public String test() {
		return "hello,world!";
	}

	@RequestMapping(value="/controller", produces="text/html;charset=UTF-8")
	public String controller(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		String rootPath = "/Users/wu/tmp/upload/";
		System.out.println(rootPath);
		return new ActionEnter(request, rootPath).exec();
	}
	/**
	 * jsp/upload/image/20181212/1544606760944020917.jpg
	 */
	/**
	 * 查看学习资料里面的文件 学习资料上传路径
	 * xuexiziliaoUploadFolderPath=/usr/oracle/tmp/xuexiziliaoUpload/
	 */
	@GetMapping("/uploadimage/{filename:.+}")
	public ResponseEntity<Resource> viewImageFile(@PathVariable String filename) {
		
		Resource file = null;
		String contentType = "application/octet-stream";
		try {
			Path filePath = Paths.get("/Users/wu/tmp/upload/ueditor/uploadimage").resolve(filename);
			
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists() || resource.isReadable()) {
				
				file = resource;
				contentType = Files.probeContentType(filePath);
			} else {
				file = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (file == null) {
			return ResponseEntity.notFound().build();
		} else {
			
				return ResponseEntity.ok()
						// .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
						// file.getFilename() + "\"")
						.header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
			

		}
	}
	@GetMapping("/uploadvideo/{filename:.+}")
	public ResponseEntity<Resource> uploadvideo(@PathVariable String filename) {
		
		Resource file = null;
		String contentType = "application/octet-stream";
		try {
			Path filePath = Paths.get("/Users/wu/tmp/upload/ueditor/uploadvideo").resolve(filename);
			
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists() || resource.isReadable()) {
				
				file = resource;
				contentType = Files.probeContentType(filePath);
			} else {
				file = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (file == null) {
			return ResponseEntity.notFound().build();
		} else {
			
				return ResponseEntity.ok()
						// .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
						// file.getFilename() + "\"")
						.header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
			

		}
	}
}
