package com.fawry.movietask.security;

import feign.FeignException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceUtil {
    private final String BEARER = "Bearer";
    @Autowired
    private JwtService jwtService;

    private List<String> whiteListedActions = Arrays.asList("^/api/auth/login$","^/api/auth/register$");

    private Map<String, List<String>> allowedActions = new HashMap<>();


    public AuthServiceUtil() {
        allowedActions.put("ADMIN", Arrays.asList("^/api/.*"));
        allowedActions.put("USER", Arrays.asList("^/api/user/.*"));
    }

    public String extractTokenFromRequest(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String bearerToke = httpServletRequest.getHeader("Authorization");
        validateAuthHeader(bearerToke);
        return getAccessToken(bearerToke);
    }

    private void validateAuthHeader(String bearerToke) {
        if (bearerToke == null || bearerToke.isBlank() || bearerToke.isEmpty()) {
            throw new FeignException.Unauthorized("forbidden", null, null, null);
        }
    }

    private String getAccessToken(String bearerToke) {
        return BEARER.concat(bearerToke);
    }
    public String extractUri(ServletRequest request) {
        HttpServletRequest httpServletRequet = (HttpServletRequest) request;
        String uri = httpServletRequet.getRequestURI();
        return uri;
    }

    public void isUserAllowed(String userType, String action) {
     List<String> clientAllowedAction =   allowedActions.get(userType);
     for(String allowedAction : clientAllowedAction) {
         if(action.matches(allowedAction)) {
             return;
         }
      }
      throw new FeignException.Unauthorized("forbidden", null, null, null);
    }

    public boolean isWhiteListedAction(String Uri) {
            for (String regex : whiteListedActions) {
                if (Uri.matches(regex)) {
                    return true;
                }
            }
            return false;

    }
}
