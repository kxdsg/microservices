package com.argus.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xingding on 2017/4/22.
 *
 */
@Component
public class PreHandleFilter extends ZuulFilter{

    private static final Logger logger = LoggerFactory.getLogger(PreHandleFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
//        return ctx.get("success")==null || (boolean)ctx.get("success");
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        logger.info(String.format("%s request to %s",req.getMethod(), req.getRequestURL().toString()));
        //设置公共header
        ctx.addZuulRequestHeader("area","000000");
        return null;
    }
}
