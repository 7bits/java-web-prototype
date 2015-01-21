package it.sevenbits.project.application.config.security;

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
     * Your repository, which should implements UserDetailsService from Spring Security
     */
//    @Autowired
//    private UserRepository userRepository;

    /**
     * Setup success login handler.
     * If no need, remove this method and LoginHandler class
     * @return success login handler
     */
//    @Bean
//    public SuccessLoginHandler successLoginHandler() {
//        return new SuccessLoginHandler(userRepository);
//    }

    /**
     * Setup failure login handler
     * If no need, remove this method and LoginHandler class
     * @return failure login handler
     */
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new FailLoginHandler();
//    }

    /**
     * Configure spring security.
     * @param http    Http Security
     * @throws Exception if fails
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        //example of configuration
        http
            //form-based auth
            .formLogin()
            .loginPage("/")
            //submit login form url
            .loginProcessingUrl("/**/j_spring_security_check")
            //form parameter's names
            .usernameParameter("j_username")
            .passwordParameter("j_password")
            .defaultSuccessUrl("/")
            .failureUrl("/?error=true")
            //enable custom success and failure handlers
//            .successHandler(successLoginHandler())
//            .failureHandler(authenticationFailureHandler())
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/**/j_spring_security_logout")
            .logoutSuccessUrl("/")
            .permitAll()
            .and()
            //example for access to urls depending on roles
            .authorizeRequests()
            .antMatchers(
                "/**/validate/**/**",
                "/user/**"
            ).access(
            "hasRole('ROLE_ADMIN') " +
                "or hasRole('ROLE_ANOTHER')"
            )
        ;
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

//        auth.userDetailsService(userRepository).passwordEncoder(new BCryptPasswordEncoder());
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