package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/web/**").and()
                .authorizeRequests().antMatchers("/web/b").permitAll().and()
                .authorizeRequests().anyRequest().authenticated().and()
//                .addFilter(digestAuthenticationFilter())//在过滤链中添加摘要认证过滤器
                .addFilterBefore(digestAuthenticationFilter(), BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(digestAuthenticationEntryPoint())//摘要认证入口端点
                .and()
                .csrf().disable();
    }

    @Bean
    public DigestAuthenticationFilter digestAuthenticationFilter(){
        DigestAuthenticationFilter filter= new DigestAuthenticationFilter();
        filter.setAuthenticationEntryPoint(digestAuthenticationEntryPoint());//必须配置
        filter.setUserDetailsService(userDetailsService());//必须配置
        filter.setCreateAuthenticatedToken(true);
        // 如果为 true，则创建经过身份验证的 UsernamePasswordAuthenticationToken 以避免从数据库中加载用户两次。
        // 但是，此过程会跳过 isAccountNonExpired()、isAccountNonLocked()、isCredentialsNonExpired() 和 isEnabled() 检查，因此正常的权限认证不建议这样做，摘要认证可以用
        // 如果为false会报，There is no PasswordEncoder mapped for the id "null"
        return filter;
    }
    @Bean
    public DigestAuthenticationEntryPoint digestAuthenticationEntryPoint() {
        DigestAuthenticationEntryPoint point = new DigestAuthenticationEntryPoint();
        point.setRealmName("admin");//realm名
        point.setKey("admin");//密钥
        point.setNonceValiditySeconds(10);
        return point;
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                //省略从数据库查询过程
                String password = "admin";
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("auth"));
                return new User(username, password, true, true, true, true, authorities);
            }
        };
    }

}
