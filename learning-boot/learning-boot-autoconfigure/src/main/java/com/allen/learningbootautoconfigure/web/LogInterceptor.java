package com.allen.learningbootautoconfigure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)
            throws Exception {
        HttpTraceLog traceLog = new HttpTraceLog();
        traceLog.setPath(request.getRequestURI());
        traceLog.setMethod(request.getMethod());
        traceLog.setTime(new Date());
        traceLog.setStatus(response.getStatus());
        traceLog.setParameterMap(request.getParameterMap());
        traceLog.setRequestBody(getRequestBody(request));
        request.setAttribute("traceLog", traceLog);
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HttpTraceLog traceLog = (HttpTraceLog) request.getAttribute("traceLog");
        traceLog.setElapsedTime(System.currentTimeMillis() - traceLog.getTime().getTime());
        log.info("Http Log ==> {}", new ObjectMapper().writeValueAsString(traceLog));
    }

    private String getRequestBody(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper =
                WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            try {
                return new String(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
            }
        }
        return null;
    }

    @Data
    private class HttpTraceLog {

        private String path;
        private String method;
        private Long elapsedTime;
        private Date time;
        private Integer status;
        private Map parameterMap;
        private String requestBody;
    }
}
