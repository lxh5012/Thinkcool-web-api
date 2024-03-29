package com.authine.cloudpivot.web.api.config;

import com.authine.cloudpivot.web.sso.filter.DingTalkAuthenticationFilter;
import com.authine.cloudpivot.web.sso.filter.DingTalkMobileAjaxAuthenticationFilter;
import com.authine.cloudpivot.web.sso.filter.MobilePhoneAuthenticationFilter;
import com.authine.cloudpivot.web.sso.filter.UsernamePasswordAjaxAuthenticationFilter;
import com.authine.cloudpivot.web.sso.handler.*;
import com.authine.cloudpivot.web.sso.security.*;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private LogoutAndRemoveTokenHandler logoutAndRemoveTokenHandler;

    @Autowired
    private DingTalkAuthenticationHandler dingTalkAuthenticationHandler;

    @Autowired
    private DingTalkAuthenticationProvider dingTalkAuthenticationProvider;

    @Autowired
    private DingTalkMobileAuthenticationProvider dingTalkMobileAuthenticationProvider;

    @Autowired
    private UsernamePasswordAjaxAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Autowired
    private DingTalkMobileAjaxAuthenticationProvider dingTalkMobileAjaxAuthenticationProvider;

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private DingTalkAjaxAuthenticationHandler dingTalkAjaxAuthenticationHandler;

    @Autowired
    private UsernamePasswordAjaxAuthenticationHandler usernamePasswordAuthenticationHandler;

    @Autowired
    private DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

    @Autowired
    private DefaultAuthenticationFailureHandler defaultAuthenticationFailureHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() {
//        return super.authenticationManagerBean();
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        //认证后用户信息检查
        UserDetailsCheckerImpl userDetailsChecker = new UserDetailsCheckerImpl();
        daoAuthenticationProvider.setPostAuthenticationChecks(userDetailsChecker);
        return new ProviderManager(ImmutableList.of(daoAuthenticationProvider));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable();
        http.requestMatchers().antMatchers("/login/dingtalk", "login/mobile", "login/mobile/ajax", "login/password","/oauth/authorize","/login/**","/oauth/**","/login","/oauth","/logout/**","/logout")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/actuator/**", "/monitor/**", "/login/dingtalk", "login/mobile", "login/mobile/ajax", "login/password").permitAll()
                .and().formLogin().loginPage("/login").permitAll().failureUrl("/login?error")
                .failureHandler(new DefaultAuthenticationFailureHandler()).and().rememberMe()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .addLogoutHandler(logoutAndRemoveTokenHandler).permitAll()
                .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).accessDeniedPage("/access?error");


        http.addFilterAt(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(phoneAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(dingTalkAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(dingTalkMobileAjaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(usernamePasswordAjaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/vendor/**", "/fonts/**", "/themes/**");
    }

    @Bean
    public FilterRegistrationBean<Filter> corsFilterRegistrationBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<Filter>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }


    public UsernamePasswordAuthenticationFilter loginAuthenticationFilter() {
        UsernamePasswordAuthenticationFilter loginAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        loginAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        loginAuthenticationFilter.setAuthenticationSuccessHandler(defaultAuthenticationSuccessHandler);
        loginAuthenticationFilter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);
        return loginAuthenticationFilter;
    }

    /**
     * 内置扩展用户名密码登录模式
     *
     * @return
     */
    public UsernamePasswordAjaxAuthenticationFilter usernamePasswordAjaxAuthenticationFilter() {
        UsernamePasswordAjaxAuthenticationFilter filter = new UsernamePasswordAjaxAuthenticationFilter();
        ProviderManager providerManager = new ProviderManager(Collections.singletonList(usernamePasswordAuthenticationProvider));
        filter.setAuthenticationManager(providerManager);
        filter.setAuthenticationSuccessHandler(usernamePasswordAuthenticationHandler);
        filter.setAuthenticationFailureHandler(usernamePasswordAuthenticationHandler);
        return filter;
    }

    /**
     * 钉钉应用内免登,废弃
     *
     * @return
     */
    public MobilePhoneAuthenticationFilter phoneAuthenticationFilter() {
        MobilePhoneAuthenticationFilter filter = new MobilePhoneAuthenticationFilter();
        ProviderManager providerManager = new ProviderManager(Collections.singletonList(dingTalkMobileAuthenticationProvider));
        filter.setAuthenticationManager(providerManager);
        filter.setAuthenticationSuccessHandler(dingTalkAuthenticationHandler);
        filter.setAuthenticationFailureHandler(dingTalkAuthenticationHandler);
        return filter;
    }

    /**
     * 钉钉扫码登录
     *
     * @return
     */
    public DingTalkAuthenticationFilter dingTalkAuthenticationFilter() {
        DingTalkAuthenticationFilter filter = new DingTalkAuthenticationFilter();
        ProviderManager providerManager = new ProviderManager(Collections.singletonList(dingTalkAuthenticationProvider));
        filter.setAuthenticationManager(providerManager);
        filter.setAuthenticationSuccessHandler(dingTalkAuthenticationHandler);
        filter.setAuthenticationFailureHandler(dingTalkAuthenticationHandler);
        return filter;
    }

    /**
     * 钉钉应用内免登
     *
     * @return
     */
    public DingTalkMobileAjaxAuthenticationFilter dingTalkMobileAjaxAuthenticationFilter() {
        DingTalkMobileAjaxAuthenticationFilter filter = new DingTalkMobileAjaxAuthenticationFilter();
        ProviderManager providerManager = new ProviderManager(Collections.singletonList(dingTalkMobileAjaxAuthenticationProvider));
        filter.setAuthenticationManager(providerManager);
        filter.setAuthenticationSuccessHandler(dingTalkAjaxAuthenticationHandler);
        filter.setAuthenticationFailureHandler(dingTalkAjaxAuthenticationHandler);
        return filter;
    }

}
