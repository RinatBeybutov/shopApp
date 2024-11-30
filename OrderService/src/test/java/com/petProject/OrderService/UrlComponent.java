package com.petProject.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlComponent {

    @LocalServerPort
    private int port;

    public String getBaseUrl() {
        return String.format("http://localhost:%d/api/v1", port);
    }
}
