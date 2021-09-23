package dev.patika.customerserver.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@NoArgsConstructor
@Component
@SessionScope
public class RequestInfo {
    private String sessionId;
    private String clientUrl;
    private String requestURI;
}
