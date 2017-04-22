package com.argus.zuul.filter;

import com.google.common.collect.Lists;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.AssertFalse;
import java.util.Arrays;
import java.util.List;


@Component
public class AuthFilter extends ZuulFilter {

    //TODO
    private static final String authUrl = "/exchange-service/account,/userInfo";

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public String filterType() {
        return "pre";
        //pre: 请求被路由之前调用
        //route: 在路由请求的时候调用
        //post: 在routing和error过滤之后被调用
        //error: 处理请求发生错误时被调用
    }

    @Override
    public int filterOrder() {
        return 0; //数字越小优先级越高
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        //如果请求uri需要过滤
        List authList = Arrays.asList(authUrl.split(","));
        logger.info(String.format("request uri: %s",req.getRequestURI()));
        return authList.contains(req.getRequestURI());
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        logger.info(String.format("%s request to %s",req.getMethod(), req.getRequestURL().toString()));

        Object token = req.getParameter("token");
        if(token==null){
            logger.warn("token is empty");
            ctx.setSendZuulResponse(false); //通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由
            ctx.setResponseBody("authorized error"); //定制返回
            ctx.setResponseStatusCode(401);
//            ctx.put("success",false);
            return null;
        }
//        ctx.put("success",true);
        logger.info("token is ok");
        return null;
    }
}
