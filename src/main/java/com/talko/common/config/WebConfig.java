package com.talko.common.config;

import com.talko.common.jwt.JwtAuthenticationFilter;
import com.talko.common.resolver.AuthInfoArgumentResolver;
import java.util.List;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthInfoArgumentResolver authInfoArgumentResolver;

  public WebConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
      AuthInfoArgumentResolver authInfoArgumentResolver) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.authInfoArgumentResolver = authInfoArgumentResolver;
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {  // 추가
    resolvers.add(authInfoArgumentResolver);
  }


  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
        .allowedOrigins("http://localhost:5500", "http://127.0.0.1:5500", "http://localhost:3000")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(true);
  }

  @Bean
  public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter() {
    FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(jwtAuthenticationFilter);
    registrationBean.addUrlPatterns("/api/*");
    registrationBean.setOrder(1);
    return registrationBean;
  }

}
