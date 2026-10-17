
package org.example.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Order(3)
public class ResponseFilter implements Filter {

    private final ObjectMapper mapper;

    public ResponseFilter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletResponse httpResponse =
                (HttpServletResponse) servletResponse;

        ContentCachingResponseWrapper response =
                new ContentCachingResponseWrapper(httpResponse);

        try {
            filterChain.doFilter(servletRequest, response);

            byte[] originalBody = response.getContentAsByteArray();

            if (originalBody.length > 0
                    && response.getContentType() != null
                    && response.getContentType().contains("application/json")) {

                ObjectNode json = (ObjectNode) mapper.readTree(originalBody);

                json.put("name", "Rakesh");
                json.put("admnNo", 1005);

                byte[] modifiedBody = mapper.writeValueAsBytes(json);

                response.resetBuffer();
                response.setContentLength(modifiedBody.length);
                response.getOutputStream().write(modifiedBody);
            }
        } finally {
            response.copyBodyToResponse();
        }
    }
}
