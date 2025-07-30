package com.annservice.product_preference_api.security.filter;
// package com.annservice.product_preference_api.filter;

// import jakarta.servlet.*;
// import jakarta.servlet.http.HttpServletRequest;
// import org.springframework.stereotype.Component;

// import java.io.IOException;

// @Component
// public class JwtFilter implements Filter {

// @Override
// public void doFilter(ServletRequest req, ServletResponse res, FilterChain
// chain)
// throws IOException, ServletException {

// HttpServletRequest request = (HttpServletRequest) req;
// String path = request.getRequestURI();

// // 跳過不需要驗證的路徑
// if (path.equals("/api/auth/login") || path.equals("/api/auth/register")) {
// chain.doFilter(req, res);
// return;
// }

// // String authHeader = request.getHeader("Authorization");

// // if (authHeader != null && authHeader.startsWith("Bearer ")) {
// // String jwt = authHeader.substring(7);

// // if (jwtUtil.validateToken(jwt)) {
// // String username = jwtUtil.extractUsername(jwt);

// // // // 加上這段：建立 Authentication 並設進 SecurityContext
// // // UserDetails userDetails =
// userDetailsService.loadUserByUsername(username);
// // // UsernamePasswordAuthenticationToken auth = new
// // UsernamePasswordAuthenticationToken(
// // // userDetails, null, userDetails.getAuthorities());

// // SecurityContextHolder.getContext().setAuthentication(auth);
// // }
// // }

// chain.doFilter(req, res);
// }
// }