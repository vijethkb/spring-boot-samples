package com.demo.springboot.filters;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Ref https://www.baeldung.com/spring-boot-add-filter
 *
 */
@Component
@Order(1)
public class LogReqInfoFilter implements Filter {

	Logger logger = LogManager.getLogger();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		logger.trace("Starting a transaction for req : {}", req.getRequestURI());
		String invokedBy = req.getHeader(HttpHeaders.ORIGIN);
        Instant start = Instant.now();
        try {
            chain.doFilter(req, resp);
        } finally {
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toMillis();
            logger.debug("{} : {}: {} ms ", invokedBy, ((HttpServletRequest) req).getRequestURI(),  time);
        }
		logger.trace("Committing a transaction for req : {}", req.getRequestURI());
	}

}
