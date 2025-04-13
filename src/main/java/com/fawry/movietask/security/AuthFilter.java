package com.fawry.movietask.security;

import jakarta.servlet.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AuthFilter implements Filter {
    @Autowired
    private AuthServiceUtil authServiceUtil;
    @Autowired
    JwtService jwtService;

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        String userAction = authServiceUtil.extractUri(servletRequest);
        if(authServiceUtil.isWhiteListedAction(userAction)){
            filterChain.doFilter(servletRequest,servletResponse);
            return ;
        }


        String token = authServiceUtil.extractTokenFromRequest(servletRequest);
        jwtService.isTokenValid(token);
        String userType = jwtService.extractClaimsByKey("userType",token);
        authServiceUtil.isUserAllowed(userType,userAction);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
