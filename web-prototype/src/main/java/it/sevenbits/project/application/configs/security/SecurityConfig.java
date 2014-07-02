package it.sevenbits.project.application.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security Configuration
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configure spring security
     * @param http    Http Security
     * @throws Exception if fails
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    }

    /**
     * Setup authentication manager
     * @return authentication manager
     * @throws Exception if fails
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    /**
     * Configure authentication manager
     * @param auth    Authentication manager
     * @throws Exception if fails
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    }

    /**
     * Configure Web Security
     * @param builder    Web security builder
     * @throws Exception if fails
     */
    @Override
    public void configure(final WebSecurity builder) throws Exception {
    }
}