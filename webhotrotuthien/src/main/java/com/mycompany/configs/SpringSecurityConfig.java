/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author vuongthai1205
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.mycompany.controllers", "com.mycompany.repository", "com.mycompany.service", "com.mycompany.utils"})

@PropertySource("classpath:configs.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", this.env.getProperty("cloudinary.cloud_name"),
                        "api_key", this.env.getProperty("cloudinary.api_id"),
                        "api_secret", this.env.getProperty("cloudinary.api_secret"),
                        "secure", true));
        return cloudinary;
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password");

        http.formLogin().defaultSuccessUrl("/")
                .failureUrl("/login?error");

        http.logout().logoutSuccessUrl("/login");

        http.exceptionHandling()
                .accessDeniedPage("/login?accessDenied");

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/post/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')")
                .antMatchers(HttpMethod.GET, "/user-manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')")
                .antMatchers(HttpMethod.GET, "/detail-post/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/delete-post/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/detail-user/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/delete-user/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/add-user/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/stats/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/stats-project/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/report/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/request-form/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')")
                .antMatchers(HttpMethod.GET, "/charityproject/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')")
                .antMatchers(HttpMethod.GET, "/detail-charityproject/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')")
                .antMatchers(HttpMethod.GET, "/delete-charityproject/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')")
                .antMatchers(HttpMethod.GET, "/update-charityproject/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')")
                ;

        http.csrf().disable();
    }
}
