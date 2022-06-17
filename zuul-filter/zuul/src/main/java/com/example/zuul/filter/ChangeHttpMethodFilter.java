package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.List;

@Component
public class ChangeHttpMethodFilter extends ZuulFilter {

    private static List<String> VALID_METHOD = Arrays.asList("GET", "POST", "PUT", "DELETE");

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
            String changeMethod = request.getHeader("change-method");
            if (changeMethod != null && VALID_METHOD.contains(changeMethod.toUpperCase())) {
                this.method = changeMethod;
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
