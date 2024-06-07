package com.project.task.config;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/secureEndpoint")
public class SecureEndpointServlet extends HttpServlet {
    // Implementasi servlet untuk endpoint yang memerlukan otentikasi
}

