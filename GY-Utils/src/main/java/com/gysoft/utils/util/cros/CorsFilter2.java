package com.gysoft.utils.util.cros;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 
 * @Description: 跨域拦截器 
 * @author DJZ-PJJ
 * @date 2018年4月9日 下午2:25:56 
 */

public class CorsFilter2 implements Filter {

	@Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
  
    }  
  
    @Override  
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {  
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT, HEAD");
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "User-Agent,Origin,Cache-Control,Content-type,Date,Server,withCredentials,AccessToken");
        response.setHeader("Access-Control-Allow-Credentials", "true");  
        filterChain.doFilter(servletRequest, servletResponse);  
    }  
  
    @Override
    public void destroy() {  
  
    }  
}
