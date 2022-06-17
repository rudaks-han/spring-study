package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@Component
public class CustomZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletRequest requestWrapper = new PostHttpServletRequest(request);
        ctx.setRequest(requestWrapper);

        return null;
    }

    static class PostHttpServletRequest extends HttpServletRequestWrapper {
        private String method;
        public PostHttpServletRequest(HttpServletRequest request) {
            super(request);
            String targetMethod = request.getHeader("target-method");
            if (targetMethod != null) {
                this.method = targetMethod;
            } else {
                this.method = request.getMethod();
            }
        }

        @Override
        public String getMethod() {
            return method;
        }
    }
}
