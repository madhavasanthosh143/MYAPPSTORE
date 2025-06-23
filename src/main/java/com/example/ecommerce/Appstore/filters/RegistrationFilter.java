//package com.example.ecommerce.Appstore.filters;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class RegistrationFilter extends OncePerRequestFilter {
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        return !request.getRequestURI().contains("/register");
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, 
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        // Example: Log registration request and is
//        System.out.println("Registration request received: " + request.getRemoteAddr());
//
//        filterChain.doFilter(request, response);
//    }
//}
