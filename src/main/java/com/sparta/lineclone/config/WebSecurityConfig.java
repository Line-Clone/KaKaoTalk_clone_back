package com.sparta.lineclone.config;

import com.sparta.lineclone.jwt.JwtAuthFilter;
import com.sparta.lineclone.jwt.JwtUtil;
import com.sparta.lineclone.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig implements WebMvcConfigurer {

    private final UserDetailsServiceImpl userDetailsService;
    public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";
    private final JwtUtil jwtUtil;
    private final long MAX_AGE_SECS = 3600;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 가장 먼저 시큐리티를 사용하기 위해선 선언해준다.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring()
//                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "13.209.3.109", "http://sangt.shop:8080", "http://sangt.shop", "http://localhost:1", "http://localhost:22", "http://localhost:80") // 허용할 출처
                .allowedMethods("GET", "POST") // 허용할 HTTP method
                .allowCredentials(true) // 쿠키 인증 요청 허용
                .maxAge(3000); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedOrigin("http://localhost:80");
        config.addAllowedOrigin("http://localhost:22");
        config.addAllowedOrigin("http://localhost:3306");
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin("http://localhost:1");
        config.addAllowedOrigin("13.209.3.109");
//        config.addAllowedOrigin("http://charleybucket.s3-website.ap-northeast-2.amazonaws.com"); //요거 변경하시면 됩니다.
        config.addExposedHeader(JwtUtil.AUTHORIZATION_HEADER);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        config.validateAllowCredentials();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/boards/**").permitAll()
                .antMatchers("/images").permitAll()        // 확인해보기!!
                .antMatchers("/api/comment/**").permitAll()
                .antMatchers("/images").permitAll()
                .antMatchers("/chat/room").permitAll()
                .antMatchers("/chat/room/deatil").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http.formLogin().loginPage("/api/user/login-page").permitAll();
        // 이 부분에서 login 관련 문제 발생
        // jwt 로그인 방식에서는 세션 로그인 방식을 막아줘야 한다.
        // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        return http.build();
    }
}
