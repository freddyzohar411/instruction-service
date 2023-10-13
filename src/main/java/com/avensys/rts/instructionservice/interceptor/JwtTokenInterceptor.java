package com.avensys.rts.instructionservice.interceptor;

import com.avensys.rts.instructionservice.util.JwtUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class JwtTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", "Bearer " + JwtUtil.getTokenFromContext());
    }
}
