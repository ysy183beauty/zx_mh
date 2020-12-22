package com.jeecms.common.web;

/**
 * @author Tom
 */
import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jeecms.core.web.util.URLHelper;

public class XssFilter implements Filter {
	private static final String badStr = "(\\.* and \\.*)|(\\.*exec\\.*)|(\\.*execute\\.*)|(\\.*insert\\.*)|(\\.*select\\.*)|(\\.*delete\\.*)" +
			"|(\\.*update\\.*)|(\\.*count\\.*)|(\\.*drop\\.*)|(\\.*master\\.*)|(\\.*truncate\\.*)" +
			"|(\\.*char\\.*)|(\\.*declare\\.*)|(\\.*sitename\\.*)|(\\.*create\\.*)|(\\.*table\\.*)|(\\.*from\\.*)" +
			"|(\\.*grant\\.*)|(\\.*use\\.*)|(\\.*union\\.*)|(\\.*where\\.*)|(\\.*order\\.*)" +
			"|(\\.*like\\.*)|(\\.*script\\.*)|(\\.*document\\.*)|(\\.*eval\\.*)|(\\.*iframe\\.*)";

	private static final String badStr2="(^|\\&)|(\\|)|(\\.)|(\\;)|(\\$)|(\\%)|(\\-\\-)|(\\@)|(\\')|(\\\")|(\\>)|(\\<)|(\\))|(\\()|(\\+)|(\\,)|(\\\\)|(\\#|$)";
	private String excludeUrls;
	FilterConfig filterConfig = null;
	public void init(FilterConfig filterConfig) throws ServletException {
		this.excludeUrls=filterConfig.getInitParameter("excludeUrls");
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String url =req.getRequestURI();
		String method=req.getMethod();
		// 从 HTTP 头中取得 Referer 值

			if(isExcludeUrl(request)){
				chain.doFilter(request, response);
			}else if(("/register.jspx").equals(url) && ("post").equals(method.toLowerCase()) || "/credit/pub/2pub/list.do".equals(url)
					|| "/credit/pub/rbl/list.do".equals(url) || "/credit/pub/travel/list.do".equals(url)
					|| "/credit/pub/ucc/index.do".equals(url)){
				String referer=req.getHeader("Referer");

				// 判断 Referer 是否以 bank.example 开头

				if((referer!=null) &&(referer.trim().startsWith("http://yndlcredit.gov.cn") || referer.trim().startsWith("http://localhost")
                        || referer.trim().startsWith("https://yndlcredit.gov.cn") || referer.trim().startsWith("http://www.yndlcredit.gov.cn")
						|| referer.trim().startsWith("https://www.yndlcredit.gov.cn")||referer.trim().startsWith("http://xy.yndali.gov.cn/")
							|| referer.trim().startsWith("https://xy.yndali.gov.cn/")||referer.trim().startsWith("http://www.yndlcredit.cn")
							||referer.trim().startsWith("https://www.yndlcredit.cn"))){
						Enumeration params = req.getParameterNames();
						Boolean flag=false;
					while (params.hasMoreElements()) {
						String name = params.nextElement().toString();
						String[] value = req.getParameterValues(name);
						if (!("email").equals(name)) {
							for (int j = 0; j < value.length; j++) {

								String result = value[j].replaceAll(badStr, "");
								String result2 = result.replaceAll(badStr2, "");
								if (!value[j].equals(result2)) {
									flag = true;
								}
							}
						}else {
							for (int j = 0; j < value.length; j++) {
								if(checkEmail(value[j])==false){
									flag=true;
								}else {
									String result2 = value[j].replaceAll(badStr, "");
									if (!value[j].equals(result2)) {
										flag = true;
									}
								}
							}
						}
					}
					if(flag==true){
						resp.setHeader("Cache-Control", "no-store");
						resp.setDateHeader("Expires", 0);
						resp.setHeader("Prama", "no-cache");
						resp.sendRedirect("/errorParameter.html");
						return;
					}else {
						chain.doFilter(req, response);
					}
				}else{

					resp.setHeader("Cache-Control", "no-store");
					resp.setDateHeader("Expires", 0);
					resp.setHeader("Prama", "no-cache");
					resp.sendRedirect("/404.html");
					return;
				}

			}else{
				chain.doFilter(new XssHttpServletRequestWrapper(req), response);
			}



	}

	private boolean isExcludeUrl(ServletRequest request){
		boolean exclude=false;
		if(StringUtils.isNotBlank(excludeUrls)){
			 String[]excludeUrl=excludeUrls.split("@");
			 if(excludeUrl!=null&&excludeUrl.length>0){
				 for(String url:excludeUrl){
					 if(URLHelper.getURI((HttpServletRequest)request).startsWith(url)){
						 exclude=true;
					 }
				 }
			 }
		}
		return exclude;
	}

	/**
	 * 验证邮箱
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email){
		boolean flag = false;
		try{
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

}
