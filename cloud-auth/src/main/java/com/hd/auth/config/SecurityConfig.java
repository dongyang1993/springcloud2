package com.hd.auth.config;

import com.hd.auth.filter.JwtAuthenticationFilter;
import com.hd.auth.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 如果有多种用户，可以在UserDetailsService里面通过不同的表或者其他数据来源构造不同的UserDetails
     */
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated();

        /**
         * 配置JWT过滤器
         * addFilterBefore在指定的Filter类之前添加过滤器
         */
        http.addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService, tokenHeader, tokenHead, secret));

        /**
         * 配置异常情况处理
         * 不设置也会有默认配置
         */
//        http.exceptionHandling().accessDeniedHandler();

//        http.logout().addLogoutHandler();

        /**
         * 关闭CSRF防护
         */
        http.csrf().disable();

        /**
         * 因为要改成用JWT,所以调整为不创建和使用Session
         * ALWAYS       总是会新建一个Session
         * NEVER        不会新建HttpSession，但是如果有Session存在，就会使用它
         * IF_REQUIRED  如果有要求的话，会新建一个Session
         * STATELESS    不会新建，也不会使用一个HttpSession
         */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
