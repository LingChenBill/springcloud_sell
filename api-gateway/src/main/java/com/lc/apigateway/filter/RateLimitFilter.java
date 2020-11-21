package com.lc.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.lc.apigateway.exception.RateLimitException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流过滤器.
 * @description:
 * @author: lingchen
 * @date: 2020/11/21
 */
@Component
public class RateLimitFilter extends ZuulFilter {

    // google的令牌桶限流.
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        // 获取限流令牌(默认获取一个令牌).
        if (!RATE_LIMITER.tryAcquire()) {
            throw new RateLimitException();
        }

        return null;
    }
}
