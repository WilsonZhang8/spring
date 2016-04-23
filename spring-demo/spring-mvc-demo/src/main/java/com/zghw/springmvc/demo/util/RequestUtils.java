package com.zghw.springmvc.demo.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.context.ThemeSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;

/**
 * DispatcherServlet请求工具类
 * 
 * @author zghw
 *
 */
public class RequestUtils {

	/**
	 * 通过request取得容器
	 * 
	 * @param request
	 * @return
	 */
	public static WebApplicationContext getWebApplicationContext(HttpServletRequest request) {
		WebApplicationContext wac = null;
		Object object = request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		if (object != null) {
			wac = (WebApplicationContext) object;
		}
		return wac;
	}

	/**
	 * 通过request得到本地化解析器
	 * 
	 * @param request
	 * @return
	 */
	public static LocaleResolver getLocalResolver(HttpServletRequest request) {
		LocaleResolver localResolver = null;
		Object object = request.getAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE);
		if (object != null) {
			localResolver = (LocaleResolver) object;
		}
		return localResolver;
	}

	/**
	 * 得到主题解析器
	 * 
	 * @param request
	 * @return
	 */
	public static ThemeResolver getThemeResolver(HttpServletRequest request) {
		ThemeResolver themeResolver = null;
		Object object = request.getAttribute(DispatcherServlet.THEME_RESOLVER_ATTRIBUTE);
		if (object != null) {
			themeResolver = (ThemeResolver) object;
		}
		return themeResolver;
	}

	/**
	 * 得到主题源，一般为WebApplicationContext实例实现。
	 * @param request
	 * @return
	 */
	public static ThemeSource getThemeSource(HttpServletRequest request) {
		ThemeSource themeSource = null;
		Object object = request.getAttribute(DispatcherServlet.THEME_SOURCE_ATTRIBUTE);
		if (object != null) {
			themeSource = (ThemeSource) object;
		}
		return themeSource;
	}
}
