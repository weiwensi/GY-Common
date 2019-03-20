package com.gysoft.utils.util.cros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @Description: 跨域拦截器 
 * @author DJZ-PJJ
 * @date 2018年4月9日 下午2:25:56 
 */

public class CorsFilter implements Filter {

	private String[] m_allowedOrigins = {"*"};
    private String m_allowedMethods = "GET,POST,HEAD,OPTIONS,PUT,DELETE";
    private List<String> m_lstAllowedMethods = null;
    private String m_allowedHeaders = "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers";
    private List<String> m_lstAllowedHeaders = null;
    private boolean m_supportCredentials = true;
    private String m_preflightMaxAge = "1000";

    @Override
    public void init(FilterConfig config) throws ServletException {
        String sAllowedOrigins = config.getInitParameter("cors.allowed.origins");
        String sAllowedMethods = config.getInitParameter("cors.allowed.methods");
        String sAllowedHeaders = config.getInitParameter("cors.allowed.headers");
        String sSupportCredentials = config.getInitParameter("cors.support.credentials");
        String sPreflightMaxAge = config.getInitParameter("cors.preflight.maxage");
        if (sAllowedOrigins != null) {
            m_allowedOrigins = sAllowedOrigins.toLowerCase().split("\\s*,\\s*");
        }
        if (sAllowedMethods != null) {
            m_allowedMethods = sAllowedMethods;
            m_lstAllowedMethods = Arrays.asList(m_allowedMethods.toLowerCase().split("\\s*,\\s*"));
        }
        if (sAllowedHeaders != null) {
            m_allowedHeaders = sAllowedHeaders;
            m_lstAllowedHeaders = Arrays.asList(m_allowedHeaders.toLowerCase().split("\\s*,\\s*"));
        }
        if (sSupportCredentials != null) {
            m_supportCredentials = sSupportCredentials.equals("true");
        }
        if (sPreflightMaxAge != null) {
            m_preflightMaxAge = sPreflightMaxAge;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String origin = req.getHeader("Origin");
        if (origin != null) {
            boolean matchFound = false;
            for (String s : m_allowedOrigins) {
                if (match(origin, s)) {
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                chain.doFilter(req, res);
                return;
            }
            if (req.getMethod() != null && req.getMethod().equals("OPTIONS")) {
                String method = req.getHeader("Access-Control-Request-Method");
                String strHeaders = req.getHeader("Access-Control-Request-Headers");

                if (method == null) {
                    chain.doFilter(req, res);
                    return;
                }
                List<String> headers = strHeaders == null ? new ArrayList<String>() : Arrays.asList(strHeaders.split("\\s*,\\s*"));
                if (!m_lstAllowedMethods.contains(method.toLowerCase())) {
                    chain.doFilter(req, res);
                    return;
                }
                for (String hdr : headers) {
                    if (!m_lstAllowedHeaders.contains(hdr.toLowerCase())) {
                        chain.doFilter(req, res);
                        return;
                    }
                }
                res.setHeader("Access-Control-Allow-Methods", m_allowedMethods);
                res.setHeader("Access-Control-Allow-Headers", m_allowedHeaders);
                res.setHeader("Access-Control-Max-Age", m_preflightMaxAge);
            }
            res.setHeader("Access-Control-Allow-Origin", origin);
            if (m_supportCredentials) {
                res.setHeader("Access-Control-Allow-Credentials", "true");
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }

    private boolean match(String url, String pattern) {
        return pattern.equals("*") || url.equals(pattern);
    }
}
