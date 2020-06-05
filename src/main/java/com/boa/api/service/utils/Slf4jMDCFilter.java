package com.boa.api.service.utils;

/*import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

//@Data
//@EqualsAndHashCode(callSuper = false)
@Component*/
public class Slf4jMDCFilter /*extends OncePerRequestFilter*/ {
/*
    private final String responseHeader;
    private final String mdcTokenKey;
    private final String requestHeader;
   // ...
    @Override
    protected void doFilterInternal(final HttpServletRequest request, 
    final HttpServletResponse response, final FilterChain chain)
        throws java.io.IOException, ServletException {
        try {
            final String token;
            if (!StringUtils.isEmpty(requestHeader) && !StringUtils.isEmpty(request.getHeader(requestHeader))) {
                token = request.getHeader(requestHeader);
            } else {
                token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
            }
            MDC.put(mdcTokenKey, token);
            if (!StringUtils.isEmpty(responseHeader)) {
                response.addHeader(responseHeader, token);
            }
            chain.doFilter(request, response);
        } finally {
            MDC.remove(mdcTokenKey);
        }
    }

    public Slf4jMDCFilter(String responseHeader, String mdcTokenKey, String requestHeader) {
        this.responseHeader = responseHeader;
        this.mdcTokenKey = mdcTokenKey;
        this.requestHeader = requestHeader;
    }

    public String getResponseHeader() {
        return this.responseHeader;
    }


    public String getMdcTokenKey() {
        return this.mdcTokenKey;
    }


    public String getRequestHeader() {
        return this.requestHeader;
    }

    @Override
    public String toString() {
        return "{" +
            " responseHeader='" + getResponseHeader() + "'" +
            ", mdcTokenKey='" + getMdcTokenKey() + "'" +
            ", requestHeader='" + getRequestHeader() + "'" +
            "}";
    }
  */
}
