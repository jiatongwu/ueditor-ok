package cn.xvkang.ueditor.controller;

import javax.servlet.http.HttpServletRequest;

public class UeditorCommonsMultipartResolver extends org.springframework.web.multipart.commons.CommonsMultipartResolver{

	@Override
	public boolean isMultipart(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		
		if(requestURI!=null&&requestURI.contains("/ueditor")) {
			return false;
		}
		return super.isMultipart(request);
	}
	

}
