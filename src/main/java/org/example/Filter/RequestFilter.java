
package org.example.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Order(2)
public class RequestFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest request =
                (HttpServletRequest) servletRequest;

        HttpServletResponse response =
                (HttpServletResponse) servletResponse;

        ContentCachingRequestWrapper wrappedRequest =
                new ContentCachingRequestWrapper(request,2000);

        try {
            // Let the controller read and process the request
            filterChain.doFilter(wrappedRequest, response);
        } finally {
            // Log the body after downstream code has read it
            byte[] body = wrappedRequest.getContentAsByteArray();

            String payload = new String(
                    body,
                    wrappedRequest.getCharacterEncoding() != null
                            ? java.nio.charset.Charset.forName(
                            wrappedRequest.getCharacterEncoding())
                            : StandardCharsets.UTF_8
            );

            System.out.println("Request URI: " + request.getRequestURI());
            System.out.println("Request payload: " + payload);
        }
    }
}
