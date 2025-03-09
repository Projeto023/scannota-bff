package com.example.desafiobackenditarc.config;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.Markers;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class LoggingFilter extends OncePerRequestFilter {


  @Override
  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                  final FilterChain filterChain) throws ServletException, IOException {

    if (request.getRequestURI().startsWith("/swagger")
            || request.getRequestURI().startsWith("/webjars")
            || request.getRequestURI().startsWith("/v2/api-docs")) {

      filterChain.doFilter(request, response);
      return;
    }

    final long start = System.currentTimeMillis();

    final ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
    final ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

    filterChain.doFilter(requestWrapper, responseWrapper);

    this.logger(requestWrapper, responseWrapper, this.getResponseTimeSeconds(start));
  }

  private void logger(final ContentCachingRequestWrapper requestWrapper,
                      final ContentCachingResponseWrapper responseWrapper, final double responseTimeSeconds)
          throws IOException {

    final String requestBody = this.getRequestBody(requestWrapper);
    final String responseBody = this.getResponseBody(responseWrapper);

    final String basePath = requestWrapper.getServletPath();

    final int status = responseWrapper.getStatus();

    responseWrapper.copyBodyToResponse();

    final String requestHeaders = this.getRequestHeaders(requestWrapper);
    final String responseHeaders = this.getResponseHeaders(responseWrapper);
    final String message = String.format("HttpStatus:%s Verb:%s EndPoint:%s ",
            responseWrapper.getStatus(), requestWrapper.getMethod(), basePath);

    final Map<String, Object> data = new HashMap<>();

    data.put("http.request_method", requestWrapper.getMethod());
    data.put("http.path", basePath);
    data.put("http.request_header", requestHeaders);
    data.put("http.request_body", requestBody);
    data.put("http.response_header", responseHeaders);
    data.put("http.response_body", responseBody);
    data.put("http.status_code", responseWrapper.getStatus());
    data.put("http.latency_seconds", responseTimeSeconds);
    data.put("http.url", requestWrapper.getRequestURL().append("?")
            .append(requestWrapper.getQueryString()).toString());
    data.put("http.queries", requestWrapper.getQueryString());
    data.put("message", message);

    if (status < 300) {
      log.info(Markers.appendEntries(data), message);
    } else if (status < 500) {
      log.warn(Markers.appendEntries(data), message);
    } else {
      log.error(Markers.appendEntries(data), message);
    }
  }

  private double getResponseTimeSeconds(final long start) {
    final long end = System.currentTimeMillis();
    return (double) (end - start) / 1000;
  }

  private String getRequestHeaders(final ContentCachingRequestWrapper requestWrapper) {
    return Collections.list(requestWrapper.getHeaderNames()).stream()
            .map(t -> t + "=" + requestWrapper.getHeader(t)).collect(Collectors.joining(";"));
  }

  private String getResponseHeaders(final ContentCachingResponseWrapper responseWrapper) {
    return responseWrapper.getHeaderNames().stream().map(t -> t + "=" + responseWrapper.getHeader(t))
            .collect(Collectors.joining(";"));
  }

  private String getRequestBody(final ContentCachingRequestWrapper requestWrapper) {
    return removeNewlineTabFromString(new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8));
  }

  private String getResponseBody(final ContentCachingResponseWrapper responseWrapper) {
    return removeNewlineTabFromString(new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8));
  }

  private static String removeNewlineTabFromString(final String jsonString) {
    return Optional.ofNullable(jsonString).filter(t -> !t.isEmpty())
            .map(t -> jsonString.replace("\"", "").replace("\n", "")).orElse(null);
  }
}
