package com.Zpher.reggie.filter;

import com.Zpher.reggie.common.BaseContext;
import com.alibaba.fastjson.JSON;
import com.Zpher.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: LoginCheckFilter
 * Package: com.Zpher.reggie.filter
 * Description:
 *              检查用户是否已经登录
 * @Author WHU-PeterZhang
 * @Create 2024/8/7 19:18
 * @Version 1.0
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("拦截到请求 {}", request.getRequestURL());

        //获取请求的uri
        String uri = request.getRequestURI();

        String[] urls = new String[] {
                "/backend/**",
                "/front/**",
                "/employee/login",
                "/employee/logout"
        };

        //判断本次请求是否需要处理,如果不需要处理则直接放行
        if(checkUrl(urls, uri)) {
            filterChain.doFilter(request, response);
            log.info("请求 {} 放行", request.getRequestURL());
            return;
        }

        //如果未放行，判断用户是否登录
        if(request.getSession().getAttribute("employee") != null) {

            log.info("用户已登录，用户ID位", request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request, response);
            return;
        }

        //如果未登录
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    /**
     * 路径匹配
     * @param urls
     * @param uri
     * @return
     */

    public boolean checkUrl(String[] urls, String uri) {
        for (String url : urls) {
            if(PATH_MATCHER.match(url, uri)) {
                return true;
            }
        }
        return false;
    }

}
