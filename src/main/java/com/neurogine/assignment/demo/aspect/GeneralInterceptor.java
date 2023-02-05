package com.neurogine.assignment.demo.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * This class will log any exceptions occurring with endpoint calls
 */
@Component
@Slf4j
public class GeneralInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Pre Handle"+request.getRequestURI());
        return  true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(ex!=null){ //check exception happened
            log.error("Exception occurred in "+request.getMethod()+" for "+request.getRequestURI(),ex); //log the exception
        }
    }
}
