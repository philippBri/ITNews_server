package com.itnews.security;


import com.itnews.servers.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author f.brishtan
 * @since 16.10.18.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true //The prePostEnabled property enables Spring Security pre/post annotations
        //securedEnabled = true, //The securedEnabled property determines if the @Secured annotation should be enabled
        //jsr250Enabled = true //The jsr250Enabled property allows us to use the @RoleAllowed annotation
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name="userService")
    private UserDetailsService userDetailsService;

    //Помечает метод конструктора, поля, сеттера или конфигурационный метод,
    // который должен быть отстроен путем инъекций зависимостей объектов Spring Spring.
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    //Переопределите этот метод, чтобы открыть {@link AuthenticationManager} из
    // {@link #configure (AuthenticationManagerBuilder)}, который будет отображаться как Bean.
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    //Указывает, что метод создает компонент (bean), который должен управляться контейнером Spring
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable(). //disabling the CSRF - Cross Site Request Forgery
                // starts authorizing configurations
                authorizeRequests()
                // ignoring the guest's urls "
                .antMatchers("/token/*", "/signup", "/news",
                        "/userList", "/userCheck", "/news/**", "/commentsList/*").permitAll()
                // authenticate all remaining URLS
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    // this configuration allow the client app to access the this api
    // all the domain that consume this api must be included in the allowed o'rings
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//            }
//        };
//    }

    // This method is for overriding some configuration of the WebSecurity
    // If you want to ignore some request or request patterns then you can
    // specify that inside this method
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.
//                authorizeRequests()
//                    .antMatchers("/", "/news").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .and()
//                .logout()
//                    .permitAll();
//    }


    // This method is used for override HttpSecurity of the web Application.
    // We can specify our authorization criteria inside this method.
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and()
//                // starts authorizing configurations
//                .authorizeRequests()
//                // ignoring the guest's urls "
//                .antMatchers("/news", "/account/signup", "/logout", "/account/userList", "/account/login").permitAll()
//                // authenticate all remaining URLS
//                .anyRequest().fullyAuthenticated().and()
//      /* "/logout" will log the user out by invalidating the HTTP Session,
//       * cleaning up any {link rememberMe()} authentication that was configured, */
//                .logout()
//                .permitAll()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
//                .and()
//                // enabling the basic authentication
//                .httpBasic().and()
//                // configuring the session on the server
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
//                // disabling the CSRF - Cross Site Request Forgery
//                .csrf().disable();
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user")
//                .password("password");
////                .roles("USER");
//    }

}
