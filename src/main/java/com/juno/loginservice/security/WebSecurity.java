package com.juno.loginservice.security;

import com.juno.loginservice.service.GameUserService;
import com.juno.loginservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final GameUserService gameUserService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/join").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();

        /*
         game user 회원 로그인 요청 및 jwt 발급
         */
        http.authorizeRequests().antMatchers("/game").permitAll()
        .and().addFilter(getAuthenticationGameFilter());

        http.authorizeRequests().antMatchers("/**").permitAll()
        .and().addFilter(getAuthenticationFilter()) //filter 추가
        ;

        http.headers().frameOptions().disable();    //h2 error
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter auth = new AuthenticationFilter(userService, env);
        auth.setAuthenticationManager(authenticationManager());

        return auth;
    }

    private GameAuthFilter getAuthenticationGameFilter() throws Exception {
        GameAuthFilter auth = new GameAuthFilter(gameUserService, env);
        auth.setFilterProcessesUrl("/game/login");  //filter 작동 url 변경
        auth.setAuthenticationManager(authenticationManager());

        return auth;
    }

    //인증 service 등록
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(gameUserService).passwordEncoder(passwordEncoder);
    }
}
