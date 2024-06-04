package com.oatss.config;
import com.oatss.entity.User;
import com.oatss.service.UserService;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    private UserService userService;
    @Resource
    private PersistentTokenRepository persistentTokenRepository;
    @Resource
    private UserDetailsService userDetailsService;
    @PostConstruct
    public void init() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL); //多线程环境，子线程中也能够获取到登录用户数据
    }
    //配置 token 持久化对象，记住我功能
    @Bean
    public PersistentTokenRepository persistentTokenRepository(@Autowired DataSource dataSource) {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }
    //配置过滤链
    @Bean
    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/static/**", "/auth/**").permitAll() //静态资源和登录界面
                .antMatchers("/admin/**").hasRole("admin") //管理员操作界面
                .antMatchers("/user/**").hasRole("user") //用户操作界面
                .anyRequest().hasAnyRole("admin", "user")
                .and()
                .formLogin().loginPage("/auth/login").loginProcessingUrl("/auth/login").successHandler((HttpServletRequest req, HttpServletResponse resp, Authentication auth)->{
                    HttpSession session = req.getSession();
                    User user = userService.selectUserByUsername(auth.getName());
                    session.setAttribute("user", user);
                    if (Objects.equals(user.getRole(), "admin") == true) {
                        resp.sendRedirect("/admin/select-ticket");
                    }
                    else if (Objects.equals(user.getRole(), "user") == true) {
                        resp.sendRedirect("/user/select-ticket");
                    }
                }) //登录成功
                .and()
                .logout().logoutUrl("/auth/logout").logoutSuccessUrl("/login") //退出登录成功，返回登录界面
                .and()
                .rememberMe().alwaysRemember(false)
                .tokenValiditySeconds(60 * 60 * 24 * 7).tokenRepository(persistentTokenRepository) //记住我，7 天
                .and()
                .csrf().disable()
                .build();
    }
    //配置密码加密算法
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //配置认证管理器
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }
}