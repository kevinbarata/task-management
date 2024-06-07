package com.project.task.config;

import com.project.task.dao.UserSessionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/task/*")
public class JwtInterceptor implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserSessionDao userSessionDao;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("masuk1");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Ambil path dari URL permintaan
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // Jika endpoint adalah /task/user/login, lanjutkan tanpa memeriksa token
        if (path.equals("/task/user/login")) {
            logger.info("masuk");
            chain.doFilter(request, response);
            return;
        }

        // Ambil userId dari header
        String userId = httpRequest.getHeader("userId");
        String token = httpRequest.getHeader("token");

        if (userId != null && JwtUtil.validateToken(token,userId,userSessionDao)) {
            // Jika userId ditemukan dan sesi valid, lanjutkan ke servlet berikutnya
            chain.doFilter(request, response);
        } else {
            // Jika userId tidak ditemukan atau sesi tidak valid, kirim respons error 401 Unauthorized
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Metode inisialisasi filter
    }

    @Override
    public void destroy() {
        // Metode destruksi filter
    }
}


