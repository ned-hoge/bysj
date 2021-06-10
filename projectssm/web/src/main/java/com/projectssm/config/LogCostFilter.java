package com.projectssm.config;

import org.apache.catalina.Session;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogCostFilter implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("执行过滤操作");
        System.out.println(request.getAttribute("session_user"));//获取用户信息
        System.out.println(request.getServerPort());//获取请求端口

        HttpServletResponse res=(HttpServletResponse)response;
        // 指定允许其他域名访问
        res.setHeader("Access-Control-Allow-Orgin","*");//允许所有
        //响应类型
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        // 预检请求的结果缓存60分钟
        res.setHeader("Access-Control-Max-Age", "3600");
        // 响应头设置
        res.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        chain.doFilter(request, res);
    }
}
