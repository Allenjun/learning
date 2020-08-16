package com.allen.learningbootsecurity.security;

import java.io.IOException;
import javax.servlet.ServletException;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

/**
 * @author JUN
 * @Description TODO
 * @createTime 13:02
 */
@Component
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {

    }
}
